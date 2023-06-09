package com.example.jpa.qdslsort.controller;

import com.example.jpa.dto.querydsl.ParticipantAvgAgeDto;
import com.example.jpa.qdslsort.QdslSortStrategy;
import com.example.jpa.qdslsort.service.QdslSortParticipantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/qdslsort/")
@RequiredArgsConstructor
public class QdslSortController {
    private final QdslSortParticipantService service;
    
    @PostMapping("avgage")
    @ResponseBody
    public Page<ParticipantAvgAgeDto> getAvgAge(Pageable pageable, @RequestBody QdslSortStrategy qdslSortStrategy) throws Exception {
        log.info("offset: {}, page size: {}", pageable.getOffset(), pageable.getPageSize());
        return service.getSortedAvgAge(pageable, qdslSortStrategy);
    }
}
