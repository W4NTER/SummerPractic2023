package com.example.summerpractic2023.controllers;

import com.example.summerpractic2023.base.entity.Events;
import com.example.summerpractic2023.base.entity.User;
import com.example.summerpractic2023.repos.EventsRepo;
import com.example.summerpractic2023.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class EventsController {

    private final EventsService eventsService;

    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/events")
    public String events (Map<String, Object> model){
        Iterable<Events> events = eventsService.findAll();
        model.put("events", events);
        return "events";
    }

    @GetMapping("/events/add")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('ORG')")
    public String createEevent (Model model){
        return "createEvents";
    }

    @PostMapping("/events/add")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('ORG')")
    public String addEvent(@AuthenticationPrincipal User user,
            @RequestParam("file") MultipartFile file,
            @RequestParam String title, @RequestParam String content,
                           @RequestParam String adress, @RequestParam int participants,
                           Map<String, Object> model) throws IOException {
        eventsService.addEvent(user, file, title, content, adress, participants, model);

        return "redirect:/events";
    }
}
