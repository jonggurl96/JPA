package com.example.jpa.service.jpa.dto.impl;

import com.example.jpa.domain.User;
import com.example.jpa.dto.converter.JpaDtoConverter;
import com.example.jpa.dto.jpa.UserJpaDto;
import com.example.jpa.repository.UserRepository;
import com.example.jpa.service.jpa.dto.UserDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDtoServiceImpl implements UserDtoService {
    
    private final UserRepository repo;
    
    private final JpaDtoConverter jpaDtoConverter;
    
    /**
     * @return 전체 user 정보
     * @throws Exception
     */
    @Override
    public List<UserJpaDto> getAll() throws Exception {
        return jpaDtoConverter.toUserDtos(repo.findAll());
    }
    
    /**
     * @param pageable 페이징 객체
     * @return 전체 user 정보
     * @throws Exception
     */
    @Override
    public Page<UserJpaDto> getAll(Pageable pageable) throws Exception {
        Page<User> entityRes = repo.findAll(pageable);
        List<UserJpaDto> content = jpaDtoConverter.toUserDtos(entityRes.getContent());
        return new PageImpl<>(content, pageable, content.size());
    }
}
