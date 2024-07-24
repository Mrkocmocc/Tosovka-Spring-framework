package com.example.Tosovka_Spring_framework_.service;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

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

    public List<Events> getAllEvents() {
        return eventRepository.findAll();
    }

    public void saveEvent(String eventTitle, String eventDescription, LocalDate eventDate, String eventLocation,
            Principal principal, @RequestParam("file") MultipartFile eventImage, String eventType) throws IOException {

        Type type = typeRepository.findByName(eventType);
        Events event = new Events();
        event.setTitle(eventTitle);
        event.setDescription(eventDescription);
        event.setEventDate(eventDate);
        event.setLocation(eventLocation);
        System.out.println("User: " + userRepository.findByUsername(principal.getName()));
        event.setUser(getUserByPrincipal(principal));
        event.setType(type);

        Events savedEvent = eventRepository.save(event);

        Images image = new Images();
        image.setImage(eventImage.getBytes());
        image.setEvents(savedEvent);
        image.setUser(getUserByPrincipal(principal));
        imageRepository.save(image);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null)
            return new User();
        return userRepository.findByUsername(principal.getName());
    }

    public Long getIdUserByPrincipal(Principal principal) {
        if (principal == null)
            return 0L;
        return userRepository.findByUsername(principal.getName()).getId();
    }

}
