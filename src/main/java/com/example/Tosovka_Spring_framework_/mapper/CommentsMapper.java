package com.example.Tosovka_Spring_framework_.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.Tosovka_Spring_framework_.dto.CommentsDto;
import com.example.Tosovka_Spring_framework_.entity.Comments;

@Mapper(uses = { UserMapper.class, EventsMapper.class })
public interface CommentsMapper {
    CommentsMapper INSTANCE = Mappers.getMapper(CommentsMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "commentText", target = "commentText")
    @Mapping(source = "commentDate", target = "commentDate")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "events", target = "events")
    CommentsDto toDto(Comments comments);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "commentText", target = "commentText")
    @Mapping(source = "commentDate", target = "commentDate")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "events", target = "events")
    Comments toEntity(CommentsDto commentsDto);
}
