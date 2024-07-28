package com.example.Tosovka_Spring_framework_.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Tosovka_Spring_framework_.entity.Comments;
import com.example.Tosovka_Spring_framework_.entity.Events;

public interface CommentRepository extends JpaRepository<Comments, Long> {
    List<Comments> getAllByEvents(Events event);
}
