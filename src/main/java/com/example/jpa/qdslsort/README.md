# QueryDSL Sort 개요
- PathBuilder 사용
  - PathBuilder pathBuilder = new PathBuilder(ParticipantDto.class, "participant");
  - ComparablePath - pathBuilder.getComparable("contestName.name", String.class);
  - NumberPath - pathBuilder.getNumber("participantDigit.age", Integer.class);


- propertyName은 Service 내부에서 key값이랑 매핑
- propertyType은 QClass.getType().getField("field").getType()
- propertyType instanceof Number ? getNumber() : getComparable();


- aggregation은 어떻게 하냐
  - ComparableExpression orderKwrd = Expressions.numberPath("alias");
  - select(Projections.bean(Participant.class, p.c.avg().as("alias")))
  - orderBy(orderKwrd.asc());
- 키워드가 alias라면 클래스 필드 타입도 필요 없겠네?
  - ComparableExpression SortKwrd = Expressions.comparablePath("alias");
  - 


- 이번엔 nullHandling 한 개만 쓴다

그러면 필요한 거
- String sortKwrds...
- String directions...

```java
private List<SKD> list;
private String nullHandling;

public void setter(SKDS...) {
    Arrays.stream(SKDS).map(add);
}
// 이러면 리스트로 한 번에 받을 수 있지 않을까
public class SKD {
    private String kwrd;
    private String direction;
}
```

집계함수는 n, 그 외는 a를 태그로 같이 주자

