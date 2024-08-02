package com.example.Tosovka_Spring_framework_.service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tosovka_Spring_framework_.dto.CommentsDto;
import com.example.Tosovka_Spring_framework_.entity.Comments;
import com.example.Tosovka_Spring_framework_.entity.Events;
import com.example.Tosovka_Spring_framework_.mapper.CommentsMapper;
import com.example.Tosovka_Spring_framework_.mapper.EventsMapper;
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

    public void createComment(CommentsDto comment, Principal principal, long eventId) {
        comment.setUser(userService.getUserByPrincipal(principal));
        Comments comments = CommentsMapper.INSTANCE.toEntity(comment);
        comments.setEvents(EventsMapper.INSTANCE.toEntity(eventService.getEventById(eventId)));
        commentRepository.save(comments);
    }

    public List<CommentsDto> getAllByEventId(Events event) {
        return commentRepository.getAllByEvents(event).stream().map(CommentsMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

}
