package com.example.jpa.service.querydsl;

import com.example.jpa.dto.querydsl.ParticipantAvgAgeDto;
import com.example.jpa.dto.querydsl.ParticipantCntDto;
import com.example.jpa.dto.querydsl.ParticipantInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ParticipantQdslService {
    
    /**
     * @param pageable 페이징을 위한 객체
     * @return 정렬되지 않은 참가자 정보
     * @throws Exception
     */
    public Page<ParticipantInfoDto> getAllParticipants(Pageable pageable) throws Exception;
    
    /**
     * @return 공모전별 참가자 수
     * @throws Exception
     */
    public List<ParticipantCntDto> getParticipantCnts() throws Exception;
    
    /**
     * @return 각 공모전의 참가자 평균 나이
     * @throws Exception
     */
    public List<ParticipantAvgAgeDto> getParticipantAvgAge() throws Exception;
}
