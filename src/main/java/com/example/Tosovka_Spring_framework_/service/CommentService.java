package com.example.Tosovka_Spring_framework_.service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tosovka_Spring_framework_.entity.Comments;
import com.example.Tosovka_Spring_framework_.entity.Events;
import com.example.Tosovka_Spring_framework_.repositories.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final EventService eventService;

    @Autowired
    private final UserService userService;

    public void createComment(String comment, Principal principal, long eventId) {
        Comments comments = new Comments();
        comments.setCommentText(comment);
        comments.setCommentDate(LocalDateTime.now());
        comments.setEvents(eventService.getEventById(eventId));
        comments.setUser(userService.getUserByPrincipal(principal));
        commentRepository.save(comments);
    }

    public List<Comments> getAllByEventId(Events event) {
        return commentRepository.getAllByEvents(event);
    }

}
