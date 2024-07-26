package com.example.Tosovka_Spring_framework_.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String mainPage(Model model, Principal principal) {
        boolean isAuthenticated = principal != null;
        String username = principal != null ? principal.getName() : "Гость";
        model.addAttribute("username", username);
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "main";
    }

}
