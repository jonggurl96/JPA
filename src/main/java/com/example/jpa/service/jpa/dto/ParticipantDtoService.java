package com.example.jpa.service.jpa.dto;

import com.example.jpa.dto.jpa.ParticipantJpaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ParticipantDtoService {
    
    /**
     * @return 모든 공모전의 참가자 목록
     * @throws Exception
     */
    public List<ParticipantJpaDto> getAll() throws Exception;
    
    /**
     * @param pageable 페이징을 위한 객체
     * @return 모든 공모전의 참가자 목록
     * @throws Exception
     */
    public Page<ParticipantJpaDto> getAll(Pageable pageable) throws Exception;
}
