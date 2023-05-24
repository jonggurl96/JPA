package com.example.jpa.controller.querydsl;

import com.example.jpa.dto.querydsl.ParticipantAvgAgeDto;
import com.example.jpa.dto.querydsl.ParticipantCntDto;
import com.example.jpa.dto.querydsl.ParticipantInfoDto;
import com.example.jpa.service.querydsl.ParticipantQdslService;
import com.example.jpa.sort.querydsl.QdslOrderSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/qdsl/")
@RequiredArgsConstructor
public class QueryDSLController {
    private final ParticipantQdslService service;
    
    @GetMapping("participants")
    @ResponseBody
    public Page<ParticipantInfoDto> getAll(Pageable pageable) throws Exception {
        return service.getAllParticipants(pageable);
    }
    
    @GetMapping("cnt")
    @ResponseBody
    public List<ParticipantCntDto> getCnts() throws Exception {
        return service.getParticipantCnts();
    }
    
    @GetMapping("avgAge")
    @ResponseBody
    public List<ParticipantAvgAgeDto> getAvgAge() throws Exception {
        return service.getParticipantAvgAge();
    }
    
    @PostMapping("avgAge/sorted")
    @ResponseBody
    public Page<ParticipantAvgAgeDto> getSortedAvgAge(@RequestBody QdslOrderSpecs qdslOrderSpecs,
                                                      Pageable pageable) throws Exception {
        return service.getParticipantAvgAge(pageable, qdslOrderSpecs);
    }
}
