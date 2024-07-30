package com.example.Tosovka_Spring_framework_.service;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Tosovka_Spring_framework_.entity.Events;
import com.example.Tosovka_Spring_framework_.entity.Type;
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

    public void saveEvent(String eventTitle, String eventDescription, LocalDate eventDate, String eventLocation,
            Principal principal, @RequestParam("file") MultipartFile eventImage, String eventType) throws IOException {

        Type type = typeRepository.findByName(eventType);
        Events event = new Events();
        event.setTitle(eventTitle);
        event.setDescription(eventDescription);
        event.setEventDate(eventDate);
        event.setLocation(eventLocation);
        event.setMainImage(eventImage.getBytes());
        event.setUser(userService.getUserByPrincipal(principal));
        event.setType(type);

        Events savedEvent = eventRepository.save(event);

        imageService.saveImage(savedEvent, eventImage.getBytes(), principal);
    }

    public List<Events> filterEvents(LocalDate dateFrom, LocalDate dateTo, String eventType, String eventTitle) {
        List<Events> events = eventRepository.findAll();
        if (eventTitle != null) {
            events = eventRepository.findByTitleContains(eventTitle);
        }

        Type type = typeRepository.findByName(eventType);
        return events.stream().filter(event -> dateFrom == null || event.getEventDate().isAfter(dateFrom))
                .filter(event -> dateTo == null || event.getEventDate().isBefore(dateTo))
                .filter(event -> eventType == null || event.getType().equals(type))
                .filter(event -> event.isActive())
                .collect(Collectors.toList());
    }

    public void deleteEvent(Principal principal, long id) {
        eventRepository.deleteById(id);
    }

    public List<Events> getByUserId(long id) {
        return eventRepository.findByUserId(id);
    }

    public Events getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

}
