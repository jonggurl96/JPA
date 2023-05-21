package com.example.jpa.service.jpa.dto;

import com.example.jpa.dto.jpa.ContestJpaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContestDtoService {
    /**
     * @return 전체 공모전
     * @throws Exception
     */
    public List<ContestJpaDto> getAll() throws Exception;
    
    /**
     * @param pageable 페이징을 위한 객체
     * @return 전체 공모전
     * @throws Exception
     */
    public Page<ContestJpaDto> getAll(Pageable pageable) throws Exception;
}
