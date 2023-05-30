package com.example.jpa.qdslsort.service;

import com.example.jpa.dto.querydsl.ParticipantAvgAgeDto;
import com.example.jpa.qdslsort.QdslSortStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QdslSortParticipantService {
    /**
     * <p>
     * QdslSortParticipantService -
     * </p>
     * @author jongg
     * @version 1.0.0
     * 작성일 2023-05-30, 화, 14:57
     */
    public Page<ParticipantAvgAgeDto> getSortedAvgAge(Pageable pageable, QdslSortStrategy qdslSortStrategy) throws Exception;
}
