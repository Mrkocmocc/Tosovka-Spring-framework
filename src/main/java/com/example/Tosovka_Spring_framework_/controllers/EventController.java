package com.example.Tosovka_Spring_framework_.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Tosovka_Spring_framework_.entity.Events;
import com.example.Tosovka_Spring_framework_.entity.Type;
import com.example.Tosovka_Spring_framework_.service.EventService;
import com.example.Tosovka_Spring_framework_.service.TypeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final TypeService typeService;

    @GetMapping("/create")
    public String createPage(Model model) {
        List<Type> types = typeService.getAllTypes();
        model.addAttribute("types", types);
        return "create";
    }

    @PostMapping("/create")
    public String saveEvent(@RequestParam("title") String title, @RequestParam("description") String description,
            @RequestParam("eventDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate eventDate,
            @RequestParam("location") String location,
            @RequestParam("type") String type,
            Principal principal, @RequestParam("file") MultipartFile file) throws IOException {
        eventService.saveEvent(title, description, eventDate, location, principal, file, type);
        return "redirect:/";
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<?> getImage(@PathVariable long id) {
        return ResponseEntity.ok()
                .body(new InputStreamResource(new ByteArrayInputStream(eventService.getEventById(id).getMainImage())));
    }

    @GetMapping("/search")
    public String searchPage(
            @RequestParam(required = false, name = "dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(required = false, name = "dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
            @RequestParam(required = false, name = "title") String title, String type,
            Model model) {

        if (type != null && type.equals("Все"))
            type = null;
        if (title != null && title.equals(""))
            title = null;

        List<Events> events = eventService.filterEvents(dateFrom, dateTo, type, title);
        model.addAttribute("events", events);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        model.addAttribute("types", typeService.getAllTypes());
        return "events";
    }

}
