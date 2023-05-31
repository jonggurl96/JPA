# QueryDSL Sort 개요
[qdslsort package 내에 정리]()
- QdslOrder
  - Service 단에서 kwrd와 query의 alias를 비교, property에 expression 저장
    ```java
    if(order.getKwrd().equalsIgnoreCase("avgage")) {
        order.setProperty(participant.participantDigit.age.avg());
    }
    ```
- QdslSortStrategy
```java
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
```
