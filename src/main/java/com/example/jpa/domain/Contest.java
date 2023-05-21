package com.example.jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="jpa_contest")
public class Contest {
    /**
     *
     * Contest - 공모전
     * <p></p>
     * name - 공모전 이름
     * winAmnt - 상금 총합
     *
     * @author jongg
     * @version 1.0.0
     * 작성일 2023-05-21, 일, 0:6
     */
    
    @Id
    private String name;
    
    private BigInteger winAmnt;
    
    @OneToMany(mappedBy = "contestName")
    private List<Participant> participants;
}
