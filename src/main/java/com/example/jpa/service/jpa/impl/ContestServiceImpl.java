package com.example.jpa.service.jpa.impl;

import com.example.jpa.domain.Contest;
import com.example.jpa.repository.ContestRepository;
import com.example.jpa.service.jpa.ContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContestServiceImpl implements ContestService {
    private final ContestRepository repo;
    
    /**
     * @return 전체 공모전
     * @throws Exception
     */
    @Override
    public List<Contest> getAll() throws Exception {
        return repo.findAll();
    }
    
    /**
     * @param pageable 페이징을 위한 객체
     * @return 전체 공모전
     * @throws Exception
     */
    @Override
    public Page<Contest> getAll(Pageable pageable) throws Exception {
        return repo.findAll(pageable);
    }
    
    /**
     * @param kwrd 검색 키워드
     * @return 검색 키워드를 포함한 이름의 공모전 수
     * @throws Exception
     */
    @Override
    public long countByName(String kwrd) throws Exception {
        return repo.countAllByNameLike("%" + kwrd + "%");
    }
}
