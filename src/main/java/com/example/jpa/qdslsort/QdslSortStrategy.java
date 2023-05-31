package com.example.jpa.qdslsort;

import com.querydsl.core.types.OrderSpecifier;
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
            OrderSpecifier<?> os = order.getDirection().equalsIgnoreCase("asc") ? order.getProperty().asc() : order.getProperty().desc();
            return switch(nullHandling.toUpperCase()) {
                case "F" -> os.nullsFirst();
                case "L" -> os.nullsLast();
                default -> os;
            };
        }).toArray(OrderSpecifier[]::new);
    }
    
}
