package com.example.Tosovka_Spring_framework_.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Tosovka_Spring_framework_.dto.TypeDto;
import com.example.Tosovka_Spring_framework_.service.EventService;
import com.example.Tosovka_Spring_framework_.service.TypeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final TypeService typeService;
    private final EventService eventService;

    @GetMapping
    public String admin(Model model) {
        List<TypeDto> typeDto = typeService.getAllTypes();
        model.addAttribute("events", eventService.getAllEvents());
        model.addAttribute("types", typeDto);
        return "admin";
    }

    @PostMapping("/type/add")
    public String addType(@RequestParam("type_name") String typeName) {
        TypeDto typeDto = new TypeDto();
        typeDto.setName(typeName);
        typeService.saveType(typeDto);
        return "redirect:/admin";
    }

    @PostMapping("/type/{id}/delete")
    public String deleteType(@PathVariable("id") Long id) {
        typeService.deleteType(id);
        return "redirect:/admin";
    }

    @PostMapping("/event/delete/{id}")
    public String deleteEvent(@PathVariable("id") Long id) {
        return "redirect:/admin";
    }

}
