package com.example.Tosovka_Spring_framework_.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Tosovka_Spring_framework_.entity.User;
import com.example.Tosovka_Spring_framework_.service.EventService;
import com.example.Tosovka_Spring_framework_.service.UserService;
import com.example.Tosovka_Spring_framework_.service.VisitService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final VisitService visitService;
    private final EventService eventService;

    @GetMapping("/profile/{username}")
    public String profilePage(@PathVariable String username, Model model, Principal principal) {
        User user = userService.getUserByUsername(username);
        boolean isAuthenticated = principal != null;
        String name = principal != null ? principal.getName() : "Гость";
        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("username", name);
        model.addAttribute("user", user);
        model.addAttribute("events", eventService.getByUserId(user.getId()));
        model.addAttribute("visits", visitService.getVisitsByUserId(user.getId()));
        return "profile";
    }
}
