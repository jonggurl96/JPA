package com.example.jpa.dto.querydsl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantInfoDto {
    private String name;
    private String contestName;
    private int age;
    private String digit;
    private BigInteger winAmnt;
}
