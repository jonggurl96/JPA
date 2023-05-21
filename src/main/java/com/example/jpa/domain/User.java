package com.example.jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="jpa_user")
public class User {
    
    /**
     *
     * User - 사용자
     * <p></p>
     * digits - 연락처(PK)
     * name - 이름
     * age - 나이
     *
     * @author jongg
     * @version 1.0.0
     * 작성일 2023-05-21, 일, 0:5
     */
    
    @Id
    private String digits;
    
    private String name;
    
    private int age;
    
    @OneToMany(mappedBy = "participantDigit") // entity field name
    private List<Participant> joinedContests;
}
