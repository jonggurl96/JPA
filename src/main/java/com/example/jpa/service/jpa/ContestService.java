package com.example.jpa.service.jpa;

import com.example.jpa.domain.Contest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContestService {
    /**
     * @return 전체 공모전
     * @throws Exception
     */
    public List<Contest> getAll() throws Exception;
    
    /**
     * @param pageable 페이징을 위한 객체
     * @return 전체 공모전
     * @throws Exception
     */
    public Page<Contest> getAll(Pageable pageable) throws Exception;
    
    /**
     * @param kwrd 검색 키워드
     * @return 검색 키워드를 포함한 이름의 공모전 수
     * @throws Exception
     */
    public long countByName(String kwrd) throws Exception;
}
