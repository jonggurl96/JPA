package com.example.jpa.repository;

import com.example.jpa.domain.Participant;
import com.example.jpa.domain.id.ParticipantId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, ParticipantId> {
    
}
