package com.example.Tosovka_Spring_framework_.controllers;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

}
