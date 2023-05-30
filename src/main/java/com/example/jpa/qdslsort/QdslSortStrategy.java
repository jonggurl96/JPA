package com.example.jpa.qdslsort;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.core.types.dsl.Expressions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QdslSortStrategy {
    private String nullHandling;
    
    private ArrayList<QdslOrder> orders;
    
    public OrderSpecifier<?>[] getOrders() {
        return orders.stream().map(order -> {
            ComparableExpressionBase comparableExpressionBase = Expressions.stringPath(order.getKwrd());
            return order.getDirection().toUpperCase().equals("ASC") ? comparableExpressionBase.asc()
                    : comparableExpressionBase.desc();
        }).toArray(OrderSpecifier[]::new);
    }
}
