package com.example.Tosovka_Spring_framework_.service;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tosovka_Spring_framework_.dto.EventsDto;
import com.example.Tosovka_Spring_framework_.entity.Events;
import com.example.Tosovka_Spring_framework_.entity.Type;
import com.example.Tosovka_Spring_framework_.mapper.EventsMapper;
import com.example.Tosovka_Spring_framework_.repositories.EventsRepository;
import com.example.Tosovka_Spring_framework_.repositories.TypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

    @Autowired
    private final EventsRepository eventRepository;

    @Autowired
    private final TypeRepository typeRepository;

    @Autowired
    private final ImageService imageService;

    @Autowired
    private final UserService userService;

    public void saveEvent(EventsDto eventsDto, Principal principal, String eventType) throws IOException {
        Type type = typeRepository.findByName(eventType);
        eventsDto.setUser(userService.getUserByPrincipal(principal));
        Events event = EventsMapper.INSTANCE.toEntity(eventsDto);
        event.setType(type);

        Events savedEvent = eventRepository.save(event);
        imageService.saveImage(savedEvent, event.getMainImage(), principal);
    }

    public List<EventsDto> filterEvents(LocalDate dateFrom, LocalDate dateTo, String eventType, String eventTitle) {
        List<Events> events = eventRepository.findAll();
        if (eventTitle != null) {
            events = eventRepository.findByTitleContains(eventTitle);
        }

        Type type = typeRepository.findByName(eventType);
        return events.stream().filter(event -> dateFrom == null || event.getEventDate().isAfter(dateFrom))
                .filter(event -> dateTo == null || event.getEventDate().isBefore(dateTo))
                .filter(event -> eventType == null || event.getType().equals(type))
                .filter(event -> event.isActiveEvent())
                .map(EventsMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public void deleteEvent(long id) {
        eventRepository.deleteById(id);
    }

    public List<EventsDto> getByUserId(long id) {
        return eventRepository.findByUserId(id).stream().map(EventsMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    public EventsDto getEventById(Long id) {
        return EventsMapper.INSTANCE.toDto(eventRepository.findById(id).orElse(null));
    }

    public List<EventsDto> getAllEvents() {
        return eventRepository.findAll().stream().map(EventsMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

}
