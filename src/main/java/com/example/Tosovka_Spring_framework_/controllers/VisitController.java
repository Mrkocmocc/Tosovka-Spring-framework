package com.example.Tosovka_Spring_framework_.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Tosovka_Spring_framework_.service.VisitService;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @PostMapping("/visit/{id}")
    public String visitPage(@PathVariable long id, Model model, Principal principal) {
        if (principal == null)
            return "redirect:/login";

        visitService.addVisit(principal, id);
        return "redirect:/event/" + id;
    }

    @PostMapping("/visit/{id}/delete")
    public String deleteVisit(@PathVariable long id, Principal principal) {
        if (principal == null)
            return "redirect:/login";

        visitService.deleteVisit(principal, id);

        return "redirect:/event/" + id;
    }

}
