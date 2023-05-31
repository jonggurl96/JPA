package com.example.jpa.qdslsort;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.core.types.dsl.ComparablePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QdslSortStrategy {
    private String nullHandling = "native";
    
    private ArrayList<QdslOrder> orders;
    
    public OrderSpecifier<?>[] toOrders() {
        return orders.stream().map(order -> {
            PathBuilder<?> pathBuilder = new PathBuilder<>(order.getType(), order.getTypeAlias());
            OrderSpecifier<?> os = switch(order.getAggrTag()) {
                case "avg" -> getOrderedSpec(getNumberPath(pathBuilder, order).avg(), order.getDirection());
                case "max" -> getOrderedSpec(getNumberPath(pathBuilder, order).max(), order.getDirection());
                case "min" -> getOrderedSpec(getNumberPath(pathBuilder, order).min(), order.getDirection());
                case "count" -> getOrderedSpec(getNumberPath(pathBuilder, order).count(), order.getDirection());
                case "abs" -> getOrderedSpec(getNumberPath(pathBuilder, order).abs(), order.getDirection());
                default -> getOrderedSpec(getComparablePath(pathBuilder, order), order.getDirection());
            };
            return switch(nullHandling.toUpperCase()) {
                case "F" -> os.nullsFirst();
                case "L" -> os.nullsLast();
                default -> os;
            };
        }).toArray(OrderSpecifier[]::new);
    }
    
    @SuppressWarnings("unchecked")
    private <T extends Number & Comparable<?>> NumberPath<T> getNumberPath(PathBuilder<?> pathBuilder, QdslOrder order) {
        return pathBuilder.getNumber(order.getKwrd(), (Class<T>)order.getType());
    }
    
    @SuppressWarnings("unchecked")
    private <T extends Comparable<?>> ComparablePath<T> getComparablePath(PathBuilder<?> pathBuilder, QdslOrder order) {
        return pathBuilder.getComparable(order.getKwrd(), (Class<T>)order.getType());
    }
    
    private <T extends Comparable<?>> OrderSpecifier<T> getOrderedSpec(
            ComparableExpressionBase<T> comparableExpressionBase, String direction) {
        return direction.equalsIgnoreCase("asc") ? comparableExpressionBase.asc() : comparableExpressionBase.desc();
    }
}
