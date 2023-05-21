package com.example.jpa.service.jpa.dto;

import com.example.jpa.dto.jpa.UserJpaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserDtoService {
    
    /**
     * @return 전체 user 정보
     * @throws Exception
     */
    public List<UserJpaDto> getAll() throws Exception;
    
    /**
     * @param pageable 페이징 객체
     * @return 전체 user 정보
     * @throws Exception
     */
    public Page<UserJpaDto> getAll(Pageable pageable) throws Exception;
}
