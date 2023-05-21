package com.example.jpa.dto.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContestJpaDto {
    private String name;
    private BigInteger winAmnt;
}
