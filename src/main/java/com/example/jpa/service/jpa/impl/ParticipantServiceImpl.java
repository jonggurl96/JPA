package com.example.jpa.service.jpa.impl;

import com.example.jpa.domain.Participant;
import com.example.jpa.repository.ParticipantRepository;
import com.example.jpa.service.jpa.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepository repo;
    
    /**
     * @return 모든 공모전의 참가자 목록
     * @throws Exception
     */
    @Override
    public List<Participant> getAll() throws Exception {
        return repo.findAll();
    }
    
    /**
     * @param pageable 페이징을 위한 객체
     * @return 모든 공모전의 참가자 목록
     * @throws Exception
     */
    @Override
    public Page<Participant> getAll(Pageable pageable) throws Exception {
        return repo.findAll(pageable);
    }
}
