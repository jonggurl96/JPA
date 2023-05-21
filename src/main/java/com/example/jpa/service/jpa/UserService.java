package com.example.jpa.service.jpa;

import com.example.jpa.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    
    /**
     * @return 전체 user 정보
     * @throws Exception
     */
    public List<User> getAll() throws Exception;
    
    /**
     * @param pageable 페이징 객체
     * @return 전체 user 정보
     * @throws Exception
     */
    public Page<User> getAll(Pageable pageable) throws Exception;
    
    /**
     * @param minAge 나이 범위 최소값
     * @param maxAge 나이 범위 최대값
     * @return 범위에 포함된 레코드의 수
     * @throws Exception
     */
    public long countByAgeRange(int minAge, int maxAge) throws Exception;
}
