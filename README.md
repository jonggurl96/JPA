# JPA - QueryDSL
* Java Persistenct API
* Query Domain Specifc Langauage
## Intro
User, Contest, Participant 세 개의 Entity를 이용해
SpringBoot에서 JPA와 QueryDSL 사용

### Spring Initializr
Spring Boot 3.1.0
Java SDK 17
Gradle-groovy 프로젝트

#### Dependencies
Developer Tools
- GraalVM Native Support
- Spring Boot DevTools
- Lombok
- Spring Configuration Processor
- 
Web
- Spring Web

SQL
- Spring Data JPA
- PostgreSQL Driver

## Entity
@Entity annotation으로 엔티티임을 명시

@Table(name = "...") annotation으로 테이블과 매핑, 
JPA는 Camel case를 Underscore로 스스로 번역하기 때문에 
테이블 이름이 user이고 엔티티 클래스 이름이 User일 때는 매핑할 필요가 없으므로 생략 가능

@Setter 사용을 지양하기 위해 @Builder annotation 사용.

@Builder annotation을 사용하기 위한 요구사항
> @NoArgsConstructor
> 
> @AllArgsConstructor

PK값을 설정해주기 위해 @GeneratedValue(strategy = "...")를 사용하며 전략은 다음과 같음
> GenerationType.AUTO - JPA 구현체가 DBMS에 맞는 방식을 설정 (default)
> 
> GenerationiType.IDENTITY - auto increment를 지원하는 MySQL 등에서 사용
> 
> GenerationType.SEQUENCE - auto increment를 지원하지 않는 DBMS에서 사용, @SequenceGenerator와 함께 사용
> 
> GenerationType.TABLE - 키 생성 전용 테이블, @TableGenerator와 함께 사용

@Column(name = "...") - 테이블 칼럼과 엔티티 멤버 이름이 다를 때 사용, or 생략 가능

@Id - PK값임을 명시하는 annotation으로 하나만 사용 가능하며 두 개 이상 사용하려면 IdClass가 필요함.

@IdClass(<<class>>) - 복합키를 가진 엔티티에 @Id annotation을 두 개 이상 사용하기 위한 annotation. 아래 IdClass와 함께 사용함

```java
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class IdClass implements Serializable {
    @EqualsAndHashCode.Include
    private String id; // 이 IdClass를 사용하는 엔티티의 필드명과 동일해야 함
    @EqualsAndHashCode.Include
    private String name;
}
```

![](github_resources/IdClass.png)

## 이 프로젝트에서 사용할 Entity들
> [domain](src/main/java/com/example/jpa/domain) 패키지에 저장

### User
> 사용자 엔티티
- digit(PK) - 사용자 연락처
- name - 사용자 이름
- age - 사용자 나이

### Contest
> 공모전 엔티티
- name(PK) - 공모전 이름
- winAmnt - 공모전 상금 총합

### Participant
> 공모전 참가자 엔티티
> User와 Contest 엔티티의 다대다 관계를 중재할 엔티티
> Id Class 사용
- participantDigit(PK) - 참가자 연락처
- contestName(PK) - 공모전 이름

> [application.properties](src/main/resources/application.properties)의 *spring.jpa.hibernate.ddl-auto=create*에 의해
> Entity로부터 테이블이 자동으로 생성되며 테스트를 위한 초기값은 [data.sql](src/main/resources/data/data.sql)에 스크립트로 저장하여 ddl이 수행되어 테이블이 생성된 후
> 테스트용 데이터를 추가한다.





