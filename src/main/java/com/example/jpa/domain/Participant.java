package com.example.jpa.domain;

import com.example.jpa.domain.id.ParticipantId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="jpa_participant")
@IdClass(ParticipantId.class)
public class Participant {
    
    /**
     *
     * Participant - 공모전 참가자
     * <p></p>
     * participantDigit - 참가자 연락처
     * contestName - 공모전 이름
     *
     * @author jongg
     * @version 1.0.0
     * 작성일 2023-05-21, 일, 0:6
     */
    
    @Id
    @ManyToOne
    @JoinColumn(name = "participant_digit") // foreign key 이름
    private User participantDigit;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "contest_name")
    private Contest contestName;
}
