package com.example.Tosovka_Spring_framework_.service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tosovka_Spring_framework_.entity.Events;
import com.example.Tosovka_Spring_framework_.entity.User;
import com.example.Tosovka_Spring_framework_.entity.Visits;
import com.example.Tosovka_Spring_framework_.repositories.VisitRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VisitService {

    @Autowired
    private final VisitRepository visitRepository;

    @Autowired
    private final EventService eventService;

    public void addVisit(Principal principal, long eventId) {
        if (!visitRepository.existsByEventsIdAndUserId(eventId, eventService.getUserByPrincipal(principal).getId())) {
            Visits visit = new Visits();
            Events event = eventService.getEventById(eventId);
            visit.setEvents(event);
            visit.setUser(eventService.getUserByPrincipal(principal));
            visitRepository.save(visit);
        }
    }

    public void deleteVisit(Principal principal, long eventId) {
        User user = eventService.getUserByPrincipal(principal);
        if (visitRepository.existsByEventsIdAndUserId(eventId, user.getId())) {
            visitRepository.delete(getVisitByEventIdAndUserId(eventId, user.getId()));
        }
    }

    public Visits getVisitByEventIdAndUserId(long eventId, long userId) {
        return visitRepository.findByEventsIdAndUserId(eventId, userId);
    }

    public List<Events> getVisitsByUserId(long userId) {
        List<Visits> visits = visitRepository.findByUserId(userId);
        return visits.stream().map(Visits::getEvents).collect(Collectors.toList());
    }

}
