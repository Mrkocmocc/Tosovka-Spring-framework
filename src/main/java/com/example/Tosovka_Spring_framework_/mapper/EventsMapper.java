package com.example.Tosovka_Spring_framework_.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.Tosovka_Spring_framework_.dto.EventsDto;
import com.example.Tosovka_Spring_framework_.entity.Events;

@Mapper(uses = { UserMapper.class })
public interface EventsMapper {

    EventsMapper INSTANCE = Mappers.getMapper(EventsMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "eventDate", target = "eventDate")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "mainImage", target = "mainImage")
    @Mapping(source = "activeEvent", target = "activeEvent")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "type", target = "type")
    EventsDto toDto(Events events);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "eventDate", target = "eventDate")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "mainImage", target = "mainImage")
    @Mapping(source = "activeEvent", target = "activeEvent")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "type", target = "type")
    Events toEntity(EventsDto eventsDto);

}