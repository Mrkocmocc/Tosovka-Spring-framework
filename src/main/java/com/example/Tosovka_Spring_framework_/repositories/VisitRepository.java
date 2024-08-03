package com.example.Tosovka_Spring_framework_.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Tosovka_Spring_framework_.entity.Visits;

public interface VisitRepository extends JpaRepository<Visits, Long> {

    Visits findByEventsIdAndUserId(Long eventId, Long userId);

    List<Visits> findByUserId(Long userId);

    void deleteByEventsId(Long id);

    boolean existsByEventsIdAndUserId(Long eventId, Long userId);
}
