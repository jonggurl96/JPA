package com.example.jpa.dto.querydsl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantCntDto {
    private String contestName;
    private long participantAmnt;
}
