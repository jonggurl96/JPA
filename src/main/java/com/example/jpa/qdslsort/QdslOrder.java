package com.example.jpa.qdslsort;

import com.querydsl.core.types.dsl.ComparableExpressionBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class QdslOrder {
    /**
     *
     * QdslOrder - 정렬할 alias와 direction 등 정의
     * <p>kwrd - 정렬할 alias 명</p>
     * <p>direction - 정렬 방향</p>
     * <p>property - 정렬할 표현식</p>
     *
     * @author jonggurl
     * @version 1.0.0
     * 작성일 2023-05-26, 금, 17:53
     */
    private String kwrd;
    
    private String direction;
    
    private ComparableExpressionBase<?> property;
}
