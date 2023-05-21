package com.example.jpa.dto.jpa;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserJpaDto {
    private String digits;
    private String name;
    private int age;
}
