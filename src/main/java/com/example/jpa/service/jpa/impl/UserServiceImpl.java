package com.example.jpa.service.jpa.impl;

import com.example.jpa.domain.User;
import com.example.jpa.repository.UserRepository;
import com.example.jpa.service.jpa.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repo;
    
    /**
     * @return 전체 user 정보
     * @throws Exception
     */
    @Override
    public List<User> getAll() throws Exception {
        return repo.findAll();
    }
    
    /**
     * @param pageable 페이징 객체
     * @return 전체 user 정보
     * @throws Exception
     */
    @Override
    public Page<User> getAll(Pageable pageable) throws Exception {
        return repo.findAll(pageable);
    }
    
    /**
     * @param minAge 나이 범위 최소값
     * @param maxAge 나이 범위 최대값
     * @return 범위에 포함된 레코드의 수
     * @throws Exception
     */
    @Override
    public long countByAgeRange(int minAge, int maxAge) throws Exception {
        return repo.countByAgeBetween(minAge, maxAge);
    }
}
