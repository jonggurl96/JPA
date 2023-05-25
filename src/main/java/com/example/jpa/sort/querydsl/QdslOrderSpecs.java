package com.example.jpa.sort.querydsl;

import com.example.jpa.sort.OrderSpecs;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BeanPath;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import jakarta.annotation.Nullable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QdslOrderSpecs extends OrderSpecs {
    /**
     * <p>정렬할 {@code property}에 사용할 집계함수</p>
     * <p>집계함수를 사용하지 않을 경우 null값을 넣어야 함</p>
     */
    private List<String> aggregations;
    
    /**
     * <p>정렬할 속성이 저장된 DB 테이블의 암호화된 이름</p>
     */
    private List<String> tables;
    
    public static final String[] AGGREGATION_LIST = {"avg", "sum"};
    
    /**
     * <p>orderBy 절을 사용하는 쿼리가 참조하는 테이블의 {@code QClass}들과 정렬할 {@code property}를 매핑</p>
     * <p>집계함수를 적용하고 정렬 방향과 null값 핸들링 전략을 정한 OrderSpecifier 배열을 반환</p>
     * @param rootPaths 쿼리가 참조하는 모든 {@code QCLass} 타입
     * @return {@code OrderSpecifier} 배열
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public <T extends Comparable<?>> OrderSpecifier<?>[] toOrders(BeanPath<?>... rootPaths) {
        
        List<BeanPath<?>> rootPathList = List.of(rootPaths);
    
        /*
          정렬할 property들 별로 OrderSpecifier 객체를 만들고 배열로 반환
          QueryBase 객체의 orderBy(OrderSpecifier...)메서드 활용
         */
        return properties.stream().map(property -> {
            
            int idx = properties.indexOf(property);
    
            Path<?> parent = findParent(rootPathList, tables.get(idx));
    
            Class<?> propertyType = propertyTypeFromEntity(parent, property);
            
            String aggr = aggregations.get(idx);
    
            ComparableExpressionBase<?> cExprBase = comparableExpressionBase((Class<T>)propertyType, parent, property);
    
            if(isAggregation(aggr)) {
                cExprBase = aggregate(propertyType, parent, property, aggr);
            }
            
            Order direction = directions.get(idx).equals("asc") ? Order.ASC : Order.DESC;
    
            OrderSpecifier.NullHandling nullHandling = switch(nullHandlings.get(idx)) {
                case "nullsFirst" -> OrderSpecifier.NullHandling.NullsFirst;
                case "nullsLast" -> OrderSpecifier.NullHandling.NullsLast;
                default -> OrderSpecifier.NullHandling.Default;
            };
            
            return new OrderSpecifier<>(direction, cExprBase, nullHandling);
        }).toArray(OrderSpecifier[]::new);
    }
    
    /**
     * public static final String[] 타입으로 정의된 AGGREGATION_LIST에 aggr값이 포함되어있는지 확인
     * @param aggr 집계함수 이름
     * @return 정렬이 구현된 집계함수라면 true
     */
    private boolean isAggregation(@Nullable String aggr) {
        return Arrays.asList(QdslOrderSpecs.AGGREGATION_LIST).contains(aggr);
    }
    
    /**
     * QClass로부터 Entity 클래스를 찾고 property 명을 통해 필드 변수와 그 타입을 검색
     * @param rootPath QClass
     * @param property 정렬할 속성명
     * @return 필드를 찾으면 그 타입을 반환하고 찾지 못하면 null을 반환
     */
    private Class<?> propertyTypeFromEntity(Path<?> rootPath, String property) {
        try {
            return rootPath.getType().getField(property).getType();
        } catch(NoSuchFieldException e) {
            return null;
        }
    }
    
    /**
     * <p>
     * 입력받은 QClass들과 매핑된 엔티티 클래스의 {@code @Table(name = tableName)} 어노테이션을 탐색
     * </p>
     * 어노테이션의 name값이 {@code tableName}과 같은 엔티티 클래스를 찾아 반환
     * @param rootPaths QCLass들
     * @param tableName 실제로 DB에 저장된 테이블의 암호화된 이름
     * @return 찾은 테이블로부터 만들어진 QCLass 타입 또는 null
     */
    private BeanPath<?> findParent(List<BeanPath<?>> rootPaths, String tableName) {
        Optional<BeanPath<?>> parent = rootPaths.stream().filter(rootPath -> {
            return Objects.equals(
                    rootPath.getType().getAnnotation(Table.class).name(), TblCodec.decodeOne(tableName));
        }).findFirst();
        return parent.orElse(null);
    }
    
    /**
     * {@code parent} 타입의 RootPath로부터
     * 속성명 {@code property}와 {@code propertyType}의 Path를 찾아 집계함수를 적용한 표현식으로 변환
     * @param propertyType 속성 타입
     * @param parent RootPath {@code QClass} 타입
     * @param property 정렬할 속성 명
     * @param aggr 집계함수 명
     * @return 집계함수가 적용된 표현식 반환
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    private <T extends Number & Comparable<?>> ComparableExpressionBase<?> aggregate(Class<?> propertyType,
                                                                                     Path<?> parent,
                                                                                     String property,
                                                                                     String aggr) {
        NumberExpression<T> nExpr = numberExpression((Class<T>)propertyType, parent, property);
        return switch(aggr) {
            case "avg" -> nExpr.avg();
            case "sum" -> nExpr.sum();
            default -> nExpr;
        };
    }
    
    private <T extends Comparable<?>> ComparableExpressionBase<T> comparableExpressionBase(Class<T> propertyType,
                                                                                           Path<?> parent,
                                                                                           String property) {
        return Expressions.comparablePath(propertyType, parent, property);
    }
    
    private <T extends Number & Comparable<?>> NumberExpression<T> numberExpression(Class<T> propertyType,
                                                                                    Path<?> parent,
                                                                                    String property) {
        return Expressions.numberPath(propertyType, parent, property);
    }
}
