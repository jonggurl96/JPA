package com.example.jpa.service.jpa.dto.impl;

import com.example.jpa.domain.Participant;
import com.example.jpa.dto.converter.JpaDtoConverter;
import com.example.jpa.dto.jpa.ParticipantJpaDto;
import com.example.jpa.repository.ParticipantRepository;
import com.example.jpa.service.jpa.dto.ParticipantDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantDtoServiceImpl implements ParticipantDtoService {
    private final ParticipantRepository repo;
    
    private final JpaDtoConverter jpaDtoConverter;
    
    /**
     * @return 모든 공모전의 참가자 목록
     * @throws Exception
     */
    @Override
    public List<ParticipantJpaDto> getAll() throws Exception {
        return jpaDtoConverter.toParticipantDtos(repo.findAll());
    }
    
    /**
     * @param pageable 페이징을 위한 객체
     * @return 모든 공모전의 참가자 목록
     * @throws Exception
     */
    @Override
    public Page<ParticipantJpaDto> getAll(Pageable pageable) throws Exception {
        Page<Participant> entityRes = repo.findAll(pageable);
        List<ParticipantJpaDto> content = jpaDtoConverter.toParticipantDtos(entityRes.getContent());
        return new PageImpl<>(content, pageable, content.size());
    }
}
