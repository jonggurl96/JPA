package com.example.jpa.dto.querydsl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantAvgAgeDto {
    private String contestName;
    private double avgAge;
}
