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
import com.example.Tosovka_Spring_framework_.entity.Images;
import com.example.Tosovka_Spring_framework_.entity.Type;
import com.example.Tosovka_Spring_framework_.entity.User;
import com.example.Tosovka_Spring_framework_.repositories.EventsRepository;
import com.example.Tosovka_Spring_framework_.repositories.ImagesRepository;
import com.example.Tosovka_Spring_framework_.repositories.TypeRepository;
import com.example.Tosovka_Spring_framework_.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

    @Autowired
    private final EventsRepository eventRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TypeRepository typeRepository;

    @Autowired
    private final ImagesRepository imageRepository;

    public void saveEvent(String eventTitle, String eventDescription, LocalDate eventDate, String eventLocation,
            Principal principal, @RequestParam("file") MultipartFile eventImage, String eventType) throws IOException {

        Type type = typeRepository.findByName(eventType);
        Events event = new Events();
        event.setTitle(eventTitle);
        event.setDescription(eventDescription);
        event.setEventDate(eventDate);
        event.setLocation(eventLocation);
        event.setMainImage(eventImage.getBytes());
        event.setUser(getUserByPrincipal(principal));
        event.setType(type);

        Events savedEvent = eventRepository.save(event);

        Images image = new Images();
        image.setImage(eventImage.getBytes());
        image.setEvents(savedEvent);
        image.setUser(getUserByPrincipal(principal));
        imageRepository.save(image);
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
                .collect(Collectors.toList());
    }

    public Events getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null)
            return new User();
        return userRepository.findByUsername(principal.getName());
    }

}
