package com.example.Tosovka_Spring_framework_.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Tosovka_Spring_framework_.dto.CommentsDto;
import com.example.Tosovka_Spring_framework_.dto.EventsDto;
import com.example.Tosovka_Spring_framework_.dto.TypeDto;
import com.example.Tosovka_Spring_framework_.mapper.EventsMapper;
import com.example.Tosovka_Spring_framework_.service.CommentService;
import com.example.Tosovka_Spring_framework_.service.EventService;
import com.example.Tosovka_Spring_framework_.service.ImageService;
import com.example.Tosovka_Spring_framework_.service.TypeService;
import com.example.Tosovka_Spring_framework_.service.UserService;
import com.example.Tosovka_Spring_framework_.service.VisitService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final TypeService typeService;
    private final VisitService visitService;
    private final ImageService imageService;
    private final UserService userService;
    private final CommentService commentService;

    @SuppressWarnings("null")
    private void setAuthentication(Model model, Authentication authentication) {
        boolean isAuthenticated = authentication != null;
        if (isAuthenticated) {
            model.addAttribute("username", authentication.getName());
        }
        model.addAttribute("isAuthenticated", isAuthenticated);
    }

    @GetMapping("/create")
    public String createPage(Model model, Authentication authentication) {
        List<TypeDto> types = typeService.getAllTypes();
        setAuthentication(model, authentication);
        model.addAttribute("types", types);
        return "create";
    }

    @PostMapping("/create")
    public String saveEvent(@RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("eventDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate eventDate,
            @RequestParam("location") String location,
            @RequestParam("type") String type,
            Principal principal,
            @RequestParam("file") MultipartFile file) throws IOException {

        EventsDto eventDto = new EventsDto();
        eventDto.setTitle(title);
        eventDto.setDescription(description);
        eventDto.setEventDate(eventDate);
        eventDto.setLocation(location);
        eventDto.setMainImage(file.getBytes());
        eventService.saveEvent(eventDto, principal, type);
        return "redirect:/";
    }

    @GetMapping("/image/{eventId}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable long eventId) {
        EventsDto eventDto = eventService.getEventById(eventId);
        return ResponseEntity.ok()
                .body(new InputStreamResource(new ByteArrayInputStream(eventDto.getMainImage())));
    }

    @GetMapping("/event/{eventId}")
    public String getEventPage(@PathVariable long eventId, Model model, Principal principal,
            Authentication authentication) {
        boolean isVisited = isEventVisited(eventId, principal);
        setAuthentication(model, authentication);
        EventsDto eventDto = eventService.getEventById(eventId);
        List<CommentsDto> comments = commentService.getAllByEventId(EventsMapper.INSTANCE.toEntity(eventDto));
        model.addAttribute("isVisited", isVisited);
        model.addAttribute("event", eventDto);
        model.addAttribute("comments", comments);
        return "event";
    }

    private boolean isEventVisited(long eventId, Principal principal) {
        return principal != null && visitService.getVisitByEventIdAndUserId(eventId,
                userService.getUserByPrincipal(principal).getId()) != null;
    }

    @PostMapping("/event/{eventId}/delete")
    public String deleteEvent(@PathVariable long eventId, Principal principal) {
        imageService.deleteImageByEventId(eventId);
        visitService.deleteAllVisitsByEventId(eventId);
        eventService.deleteEvent(eventId);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String searchEvents(
            @RequestParam(required = false, name = "dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(required = false, name = "dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
            @RequestParam(required = false, name = "title") String title,
            @RequestParam(required = false, name = "type") String eventType,
            Model model, Authentication authentication) {

        String filteredEventType = filterEventType(eventType);
        String filteredTitle = filterTitle(title);
        setAuthentication(model, authentication);
        List<EventsDto> events = eventService.filterEvents(dateFrom, dateTo, filteredEventType, filteredTitle);
        model.addAttribute("events", events);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        model.addAttribute("types", typeService.getAllTypes());
        return "events";
    }

    private String filterEventType(String eventType) {
        return eventType != null && eventType.equals("Все") ? null : eventType;
    }

    private String filterTitle(String title) {
        return title != null && title.isEmpty() ? null : title;
    }
}
