package com.example.jpa.dto.converter;

import com.example.jpa.domain.Contest;
import com.example.jpa.domain.Participant;
import com.example.jpa.domain.User;
import com.example.jpa.dto.jpa.ContestJpaDto;
import com.example.jpa.dto.jpa.ParticipantJpaDto;
import com.example.jpa.dto.jpa.UserJpaDto;

import java.util.List;
import java.util.stream.Collectors;

public class JpaDtoConverter {
    public UserJpaDto toDto(User user) {
        return UserJpaDto
                .builder()
                .digits(user.getDigits())
                .name(user.getName())
                .age(user.getAge())
                .build();
    }
    
    public List<UserJpaDto> toUserDtos(List<User> userList) {
        return userList.stream().map(this::toDto).collect(Collectors.toList());
    }
    
    public ContestJpaDto toDto(Contest contest) {
        return ContestJpaDto
                .builder()
                .name(contest.getName())
                .winAmnt(contest.getWinAmnt())
                .build();
    }
    
    public List<ContestJpaDto> toContestDtos(List<Contest> contestList) {
        return contestList.stream().map(this::toDto).collect(Collectors.toList());
    }
    
    public ParticipantJpaDto toDto(Participant participant) {
        return ParticipantJpaDto
                .builder()
                .contestName(participant.getContestName().getName())
                .participantDigit(participant.getParticipantDigit().getDigits())
                .build();
    }
    
    public List<ParticipantJpaDto> toParticipantDtos(List<Participant> participantList) {
        return participantList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
