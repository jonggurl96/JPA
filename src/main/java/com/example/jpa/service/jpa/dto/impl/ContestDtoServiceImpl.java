package com.example.jpa.service.jpa.dto.impl;

import com.example.jpa.domain.Contest;
import com.example.jpa.dto.converter.JpaDtoConverter;
import com.example.jpa.dto.jpa.ContestJpaDto;
import com.example.jpa.repository.ContestRepository;
import com.example.jpa.service.jpa.dto.ContestDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContestDtoServiceImpl implements ContestDtoService {
    private final ContestRepository repo;
    
    private final JpaDtoConverter jpaDtoConverter;
    
    /**
     * @return 전체 공모전
     * @throws Exception
     */
    @Override
    public List<ContestJpaDto> getAll() throws Exception {
        return jpaDtoConverter.toContestDtos(repo.findAll());
    }
    
    /**
     * @param pageable 페이징을 위한 객체
     * @return 전체 공모전
     * @throws Exception
     */
    @Override
    public Page<ContestJpaDto> getAll(Pageable pageable) throws Exception {
        Page<Contest> entityRes = repo.findAll(pageable);
        List<ContestJpaDto> content = jpaDtoConverter.toContestDtos(entityRes.getContent());
        return new PageImpl<>(content, pageable, content.size());
    }
}
