package com.example.jpa.service.querydsl.impl;

import com.example.jpa.domain.QContest;
import com.example.jpa.domain.QParticipant;
import com.example.jpa.dto.querydsl.ParticipantAvgAgeDto;
import com.example.jpa.dto.querydsl.ParticipantCntDto;
import com.example.jpa.dto.querydsl.ParticipantInfoDto;
import com.example.jpa.service.querydsl.ParticipantQdslService;
import com.querydsl.core.types.OrderSpecifier;
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
public class ParticipantQdslServiceImpl implements ParticipantQdslService {
    private final JPAQueryFactory qf;
    
    /**
     * @param pageable 페이징을 위한 객체
     * @return 정렬되지 않은 참가자 정보
     * @throws Exception
     */
    @Override
    public Page<ParticipantInfoDto> getAllParticipants(Pageable pageable) throws Exception {
        QParticipant participant = QParticipant.participant;
        
        List<ParticipantInfoDto> content = qf.select(Projections.fields(ParticipantInfoDto.class,
                        participant.participantDigit.name,
                        participant.contestName.name.as("contestName"),
                        participant.participantDigit.age,
                        participant.participantDigit.digits.as("digit"),
                        participant.contestName.winAmnt))
                .from(participant)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
        return new PageImpl<>(content, pageable, content.size());
    }
    
    /**
     * @return 공모전별 참가자 수
     * @throws Exception
     */
    @Override
    public List<ParticipantCntDto> getParticipantCnts() throws Exception {
        QParticipant participant = QParticipant.participant;
        
        return qf.select(Projections.bean(ParticipantCntDto.class,
                        participant.contestName.name.as("contestName"),
                        participant.contestName.name.count().as("participantAmnt")))
                .from(participant)
                .groupBy(groupByName(participant))
                .orderBy(orderByName(participant))
                .fetch();
    }
    
    /**
     * @return 각 공모전의 참가자 평균 나이
     * @throws Exception
     */
    @Override
    public List<ParticipantAvgAgeDto> getParticipantAvgAge() throws Exception {
        QParticipant participant = QParticipant.participant;
        
        return qf.select(Projections.constructor(ParticipantAvgAgeDto.class,
                        participant.contestName.name.as("contestName"),
                        participant.participantDigit.age.avg().as("avgAge")))
                .from(participant)
                .groupBy(groupByName(participant))
                .orderBy(orderByName(participant))
                .fetch();
    }
    
    private QContest groupByName(QParticipant participant) {
        return participant.contestName;
    }
    
    private OrderSpecifier<String> orderByName(QParticipant participant) {
        return participant.contestName.name.asc();
    }
}
