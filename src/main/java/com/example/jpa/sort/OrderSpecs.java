package com.example.jpa.sort;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderSpecs {
    protected List<String> directions;
    
    protected List<String> properties;
    
    protected List<Boolean> ignoreCases;
    
    protected List<String> nullHandlings;
    
    public List<Sort.Order> toOrders() {
        return properties.stream().map(m -> toOrder(properties.indexOf(m))).collect(Collectors.toList());
    }
    
    private Sort.Order toOrder(int idx) {
        Sort.Direction direction = Sort.Direction.fromString(directions.get(idx));
        Sort.NullHandling nullHandling = switch(nullHandlings.get(idx)) {
            case "nullsFirst" -> Sort.NullHandling.NULLS_FIRST;
            case "nullsLast" -> Sort.NullHandling.NULLS_LAST;
            default -> Sort.NullHandling.NATIVE;
        };
        return new Sort.Order(direction, properties.get(idx), ignoreCases.get(idx), nullHandling);
    }
    
}
