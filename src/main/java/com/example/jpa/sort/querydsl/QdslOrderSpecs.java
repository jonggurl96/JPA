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
    private List<String> aggregations;
    
    private List<String> tables;
    
    public static final String[] AGGREGATION_LIST = {"avg", "sum"};
    
    @SuppressWarnings("unchecked")
    public <T extends Comparable<?>> OrderSpecifier<?>[] toOrders(BeanPath<?>... rootPaths) {
        
        List<BeanPath<?>> rootPathList = List.of(rootPaths);
        
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
    
    private boolean isAggregation(@Nullable String aggr) {
        return Arrays.asList(QdslOrderSpecs.AGGREGATION_LIST).contains(aggr);
    }
    
    private Class<?> propertyTypeFromEntity(Path<?> parent, String property) {
        try {
            return parent.getType().getField(property).getType();
        } catch(NoSuchFieldException e) {
            return null;
        }
    }
    
    private BeanPath<?> findParent(List<BeanPath<?>> rootPaths, String tableName) {
        Optional<BeanPath<?>> parent = rootPaths.stream().filter(rootPath -> Objects.equals(
                rootPath.getType().getAnnotation(Table.class).name(), tableName)).findFirst();
        return parent.orElse(null);
    }
    
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
