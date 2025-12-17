package com.projetiw.partiservice.repository;

import com.projetiw.partiservice.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    Participation findByUserIdAndEventId(Long userId, Long eventId);

    List<Participation> findByUserId(Long userId);

    List<Participation> findByEventId(Long eventId);
}
