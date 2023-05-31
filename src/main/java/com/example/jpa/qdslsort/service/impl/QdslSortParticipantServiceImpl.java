package com.example.jpa.qdslsort.service.impl;

import com.example.jpa.domain.*;
import com.example.jpa.dto.querydsl.ParticipantAvgAgeDto;
import com.example.jpa.qdslsort.QdslOrder;
import com.example.jpa.qdslsort.QdslSortStrategy;
import com.example.jpa.qdslsort.service.QdslSortParticipantService;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QdslSortParticipantServiceImpl implements QdslSortParticipantService {
    private final JPAQueryFactory qf;
    
    /**
     * <p>
     * QdslSortParticipantService -
     * </p>
     *
     * @param pageable
     * @param qdslSortStrategy
     * @author jongg
     * @version 1.0.0
     * 작성일 2023-05-30, 화, 14:57
     */
    @Override
    public Page<ParticipantAvgAgeDto> getSortedAvgAge(Pageable pageable, QdslSortStrategy qdslSortStrategy) throws Exception {
        QParticipant participant = QParticipant.participant;
    
        for(QdslOrder order : qdslSortStrategy.getOrders()) {
            if(order.getKwrd().equalsIgnoreCase("avgage")) {
                order.setProperty(participant.participantDigit.age.avg());
            }
        }
    
        List<ParticipantAvgAgeDto> content = qf.select(Projections.constructor(ParticipantAvgAgeDto.class,
                        participant.contestName.name.as("contestName"),
                        participant.participantDigit.age.avg().as("avgAge")))
                .from(participant)
                .groupBy(participant.contestName.name)
                .orderBy(qdslSortStrategy.toOrders())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
        return new PageImpl<>(content, pageable, content.size());
    }
}
