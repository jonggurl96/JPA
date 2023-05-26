package com.example.jpa.qdslsort;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QdslSortStrategy {
    private String nullHandling;
    
    @Setter(AccessLevel.NONE)
    private List<QdslOrder> orders;
    
    public void setOrders(QdslOrder... orders) {
        this.orders = new ArrayList<>();
        Arrays.stream(orders).iterator(order -> {
            this.orders.add(order);
        });
    }
    
    public void setNullHandling(String nullHandling) {
        this.nullHandling = nullHandling;
    }
}
