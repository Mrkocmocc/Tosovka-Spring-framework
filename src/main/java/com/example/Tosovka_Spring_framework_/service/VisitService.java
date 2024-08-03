package com.example.Tosovka_Spring_framework_.service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tosovka_Spring_framework_.dto.EventsDto;
import com.example.Tosovka_Spring_framework_.entity.Events;
import com.example.Tosovka_Spring_framework_.entity.User;
import com.example.Tosovka_Spring_framework_.entity.Visits;
import com.example.Tosovka_Spring_framework_.mapper.EventsMapper;
import com.example.Tosovka_Spring_framework_.mapper.UserMapper;
import com.example.Tosovka_Spring_framework_.repositories.VisitRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VisitService {

    @Autowired
    private final VisitRepository visitRepository;

    @Autowired
    private final EventService eventService;

    @Autowired
    private final UserService userService;

    public void addVisit(Principal principal, long eventId) {
        if (!visitRepository.existsByEventsIdAndUserId(eventId, userService.getUserByPrincipal(principal).getId())) {
            Visits visit = new Visits();
            Events event = EventsMapper.INSTANCE.toEntity(eventService.getEventById(eventId));
            visit.setEvents(event);
            visit.setUser(UserMapper.INSTANCE.toEntity(userService.getUserByPrincipal(principal)));
            visitRepository.save(visit);
        }
    }

    public void deleteVisit(Principal principal, long eventId) {
        User user = UserMapper.INSTANCE.toEntity(userService.getUserByPrincipal(principal));
        if (visitRepository.existsByEventsIdAndUserId(eventId, user.getId())) {
            visitRepository.delete(getVisitByEventIdAndUserId(eventId, user.getId()));
        }
    }

    @Transactional
    public void deleteAllVisitsByEventId(long eventId) {
        visitRepository.deleteByEventsId(eventId);
    }

    public Visits getVisitByEventIdAndUserId(long eventId, long userId) {
        return visitRepository.findByEventsIdAndUserId(eventId, userId);
    }

    public List<EventsDto> getVisitsByUserId(long userId) {
        List<Visits> visits = visitRepository.findByUserId(userId);
        List<Events> events = visits.stream().map(Visits::getEvents).collect(Collectors.toList());
        return events.stream().map(EventsMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

}
