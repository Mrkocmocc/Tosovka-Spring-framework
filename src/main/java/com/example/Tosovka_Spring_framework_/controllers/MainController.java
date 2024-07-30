package com.example.Tosovka_Spring_framework_.controllers;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String mainPage(Model model, Principal principal, Authentication authentication) {
        boolean isAuthenticated = false;
        if (authentication != null) {
            model.addAttribute("username", authentication.getName());
            isAuthenticated = true;
        }
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "main";
    }

}
