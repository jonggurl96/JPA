package com.example.jpa.domain.id;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ParticipantId implements Serializable {
    @EqualsAndHashCode.Include
    private String participantDigit;
    
    @EqualsAndHashCode.Include
    private String contestName;
}
