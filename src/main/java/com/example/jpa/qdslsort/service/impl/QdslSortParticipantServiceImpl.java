package com.example.jpa.qdslsort.service.impl;

import com.example.jpa.domain.QContest;
import com.example.jpa.domain.QParticipant;
import com.example.jpa.domain.QUser;
import com.example.jpa.dto.querydsl.ParticipantAvgAgeDto;
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
        QContest contest = QContest.contest;
        QUser user = QUser.user;
    
        List<ParticipantAvgAgeDto> content = qf.select(Projections.constructor(ParticipantAvgAgeDto.class,
                        contest.name.as("contestName"),
                        user.age.avg().as("avgAge")))
                .from(participant)
                .innerJoin(contest).on(contest.name.eq(participant.contestName.name))
                .innerJoin(user).on(user.digits.eq(participant.participantDigit.digits))
                .groupBy(contest.name)
                .orderBy(qdslSortStrategy.getOrders())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
        return new PageImpl<>(content, pageable, content.size());
    }
}
