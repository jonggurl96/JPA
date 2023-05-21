package com.example.jpa.service.jpa;

import com.example.jpa.domain.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ParticipantService {
    
    /**
     * @return 모든 공모전의 참가자 목록
     * @throws Exception
     */
    public List<Participant> getAll() throws Exception;
    
    /**
     * @param pageable 페이징을 위한 객체
     * @return 모든 공모전의 참가자 목록
     * @throws Exception
     */
    public Page<Participant> getAll(Pageable pageable) throws Exception;
}
