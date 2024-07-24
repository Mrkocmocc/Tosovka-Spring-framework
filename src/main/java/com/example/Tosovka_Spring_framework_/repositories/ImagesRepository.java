package com.example.Tosovka_Spring_framework_.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Tosovka_Spring_framework_.entity.Images;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long> {
    @SuppressWarnings("null")
    Optional<Images> findById(Long id);
}
