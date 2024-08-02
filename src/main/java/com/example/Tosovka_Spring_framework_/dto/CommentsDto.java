package com.example.Tosovka_Spring_framework_.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentsDto {

    private Long id;
    private LocalDateTime commentDate;
    private UserDto user;
    private String commentText;
    private EventsDto events;

}
