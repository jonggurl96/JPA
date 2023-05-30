package com.example.jpa.qdslsort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QdslOrder {
    /**
     *
     * QdslOrder - 정렬할 alias와 direction 정의
     * <p>aggrTag n, if aggregated property else a</p>
     *
     * @author jonggurl
     * @version 1.0.0
     * 작성일 2023-05-26, 금, 17:53
     */
    private String kwrd;
    
    private String direction;
    
    private String aggrTag;
}
