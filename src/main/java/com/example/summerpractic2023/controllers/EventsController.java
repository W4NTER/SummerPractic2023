package com.example.summerpractic2023.controllers;

import com.example.summerpractic2023.base.entity.Events;
import com.example.summerpractic2023.repos.EventsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class EventsController {

    @Autowired
    EventsRepo eventsRepo;

    @GetMapping("/events")
    public String events (Map<String, Object> model){
        Iterable<Events> events = eventsRepo.findAll();
        model.put("events", events);
        return "events";
    }

    @GetMapping("/events/add")
    public String createEevent (Model model){
        return "createEvents";
    }

    @PostMapping("/events/add")
    public String addEvent(@RequestParam String title, @RequestParam String content,
                           @RequestParam String adress, @RequestParam int participants,
                           Map<String, Object> model){
        Events events = new Events(title, content, adress, participants);
        eventsRepo.save(events);

        model.put("events", events);

        return "redirect:/events";
    }
}
