package com.example.jpa.qdslsort;

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
     * QdslOrder - 정렬할 alias와 direction 정의
     * <p>rootPath - Service에서 입력할 QClass</p>
     *
     * @author jonggurl
     * @version 1.0.0
     * 작성일 2023-05-26, 금, 17:53
     */
    private String kwrd;
    
    private String direction;
    
    private Class<?> type;
    
    private String typeAlias;
    
    private String aggrTag;
}
