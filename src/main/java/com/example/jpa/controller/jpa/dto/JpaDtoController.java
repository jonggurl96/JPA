package com.example.jpa.controller.jpa.dto;

import com.example.jpa.dto.jpa.ContestJpaDto;
import com.example.jpa.dto.jpa.ParticipantJpaDto;
import com.example.jpa.dto.jpa.UserJpaDto;
import com.example.jpa.service.jpa.dto.ContestDtoService;
import com.example.jpa.service.jpa.dto.ParticipantDtoService;
import com.example.jpa.service.jpa.dto.UserDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/jpa/dto/")
@RequiredArgsConstructor
public class JpaDtoController {
    private final UserDtoService userDtoService;
    
    private final ContestDtoService contestDtoService;
    
    private final ParticipantDtoService participantDtoService;
    
    @PostMapping("user/listAll")
    @ResponseBody
    public List<UserJpaDto> getAllUsers() throws Exception {
        return userDtoService.getAll();
    }
    
    @PostMapping("user/pageAll")
    @ResponseBody
    public Page<UserJpaDto> getAllUsers(Pageable pageable) throws Exception {
        return userDtoService.getAll(pageable);
    }
    
    @PostMapping("contest/listAll")
    @ResponseBody
    public List<ContestJpaDto> getAllContests() throws Exception {
        return contestDtoService.getAll();
    }
    
    @PostMapping("contest/pageAll")
    @ResponseBody
    public Page<ContestJpaDto> getAllContests(Pageable pageable) throws Exception {
        return contestDtoService.getAll(pageable);
    }
    
    @PostMapping("participant/listAll")
    @ResponseBody
    public List<ParticipantJpaDto> getAllParticipants() throws Exception {
        return participantDtoService.getAll();
    }
    
    @PostMapping("participant/pageAll")
    @ResponseBody
    public Page<ParticipantJpaDto> getAllParticipants(Pageable pageable) throws Exception {
        return participantDtoService.getAll(pageable);
    }
}
