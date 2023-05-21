package com.example.jpa.repository;

import com.example.jpa.domain.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestRepository extends JpaRepository<Contest, String> {
    /**
     * @param kwrd 검색 키워드
     * @return 검색 키워드를 포함한 이름의 모든 공모전의 수
     * @throws Exception
     */
    public long countAllByNameLike(String kwrd) throws Exception;
}
