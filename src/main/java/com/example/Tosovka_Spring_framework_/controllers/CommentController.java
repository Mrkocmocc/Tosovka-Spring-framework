package com.example.Tosovka_Spring_framework_.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Tosovka_Spring_framework_.service.CommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    
    @PostMapping("/comment/{id}/post")
    public String commentPost(@PathVariable long id, @RequestParam("comment") String text, Principal principal) {
        if (principal == null)
            return "redirect:/login";
        commentService.createComment(text, principal, id);
        return "redirect:/event/" + id;
    }
}
