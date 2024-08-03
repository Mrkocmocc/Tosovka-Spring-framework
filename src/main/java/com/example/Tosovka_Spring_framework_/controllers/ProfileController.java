package com.example.Tosovka_Spring_framework_.controllers;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Tosovka_Spring_framework_.dto.UserDto;
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
    public String profilePage(@PathVariable String username, Model model, Principal principal,
            Authentication authentication) {
        UserDto userDto = userService.getUserByUsername(username);
        boolean isAuthenticated = false;
        if (authentication != null) {
            model.addAttribute("username", authentication.getName());
            isAuthenticated = true;
        }
        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("user", userDto);
        model.addAttribute("events", eventService.getByUserId(userDto.getId()));
        model.addAttribute("visits", visitService.getVisitsByUserId(userDto.getId()));
        return "profile";
    }
}
