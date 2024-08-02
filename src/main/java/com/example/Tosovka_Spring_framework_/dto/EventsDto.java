package com.example.Tosovka_Spring_framework_.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EventsDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate eventDate;
    private String location;
    private byte[] mainImage;
    private boolean activeEvent;
    private UserDto user;
    private TypeDto type;

}
