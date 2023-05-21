package com.example.jpa.controller.jpa;

import com.example.jpa.domain.Contest;
import com.example.jpa.domain.Participant;
import com.example.jpa.domain.User;
import com.example.jpa.service.jpa.ContestService;
import com.example.jpa.service.jpa.ParticipantService;
import com.example.jpa.service.jpa.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jpa/")
@RequiredArgsConstructor
public class JpaController {
    private final ContestService contestService;
    
    private final ParticipantService participantService;
    
    private final UserService userService;
    
    @PostMapping("contest/listAll")
    @ResponseBody
    public List<Contest> getAllContest() throws Exception {
        return contestService.getAll();
    }
    
    @PostMapping("contest/pageAll")
    @ResponseBody
    public Page<Contest> getAllContest(Pageable pageable) throws Exception {
        return contestService.getAll(pageable);
    }
    
    @GetMapping("contest/countName/{kwrd}")
    @ResponseBody
    public long countName(@PathVariable("kwrd") String kwrd) throws Exception {
        return contestService.countByName(kwrd);
    }
    
    @PostMapping("participant/listAll")
    @ResponseBody
    public List<Participant> getAllParticipant() throws Exception {
        return participantService.getAll();
    }
    
    @PostMapping("participant/pageAll")
    @ResponseBody
    public Page<Participant> getAllParticipant(Pageable pageable) throws Exception {
        return participantService.getAll(pageable);
    }
    
    @PostMapping("user/listAll")
    @ResponseBody
    public List<User> getAllUser() throws Exception {
        return userService.getAll();
    }
    
    @PostMapping("user/pageAll")
    @ResponseBody
    public Page<User> getAllUser(Pageable pageable) throws Exception {
        return userService.getAll(pageable);
    }
    
    @GetMapping("user/ageRange/{minAge}/{maxAge}")
    @ResponseBody
    public long countAge(@PathVariable("minAge") int minAge, @PathVariable("maxAge") int maxAge) throws Exception {
        return userService.countByAgeRange(minAge, maxAge);
    }
}
