package com.example.jpa.repository;

import com.example.jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    /**
     * @param minAge 나이 범위 최소값
     * @param maxAge 나이 범위 최대값
     * @return 범위에 포함된 레코드의 수
     */
    public long countByAgeBetween(int minAge, int maxAge);
}
