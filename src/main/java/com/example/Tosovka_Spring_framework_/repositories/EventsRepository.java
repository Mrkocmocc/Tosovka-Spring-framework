package com.example.Tosovka_Spring_framework_.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Tosovka_Spring_framework_.entity.Events;

@Repository
public interface EventsRepository extends JpaRepository<Events, Long> {
    Events findByTitle(String title);
}
