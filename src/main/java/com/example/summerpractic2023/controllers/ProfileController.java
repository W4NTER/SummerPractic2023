package com.example.summerpractic2023.controllers;

import com.example.summerpractic2023.base.entity.Events;
import com.example.summerpractic2023.base.entity.User;
import com.example.summerpractic2023.base.entity.User_Interests;
import com.example.summerpractic2023.service.EventsService;
import com.example.summerpractic2023.service.InterestsService;
import com.example.summerpractic2023.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ProfileController {

    private final EventsService eventsService;
    private final InterestsService interestsService;
    private final UserService userService;

    public ProfileController(EventsService eventsService, InterestsService interestsService, UserService userService) {
        this.eventsService = eventsService;
        this.interestsService = interestsService;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String userProfile(@AuthenticationPrincipal User user, Map<String, Object> model){

        interestsService.interestsView(user, model);
        eventsService.eventsViewByAuthor(user, model);
        return "profile";
    }


    @PostMapping("/profile")
    public String add(@AuthenticationPrincipal User user,
            @RequestParam String name, @RequestParam Integer age,
                      @RequestParam String sport, @RequestParam Integer level_sport,
                      Map<String, Object> model) {
        User_Interests user_interests = new User_Interests(name, age, sport, level_sport, user);
        interestsService.saveInterests(user_interests, user, model);
        eventsService.eventsViewByAuthor(user, model);
        return "profile";
    }

    @GetMapping("profile/editProfile/{id}")
    public String profileEdit(@PathVariable(value = "id") Long id, Model model){
        User_Interests interests = interestsService.findById(id);
        model.addAttribute("interests", interests);
        return "editProfile";
    }

    @PostMapping("profile/editProfile/{id}")
    public String editProfile(@PathVariable(value = "id") Long id,
                              @RequestParam String name,
                              @RequestParam Integer age,
                              @RequestParam String sport,
                              @RequestParam Integer level_sport){
        User_Interests interests = interestsService.findById(id);
        interests.setName(name);
        interests.setAge(age);
        interests.setSport(sport);
        interests.setLevel_sport(level_sport);
        interestsService.save(interests);
        return "redirect:/profile";
    }


    @GetMapping("profile/edit/{id}")
    public String eventEdit(@PathVariable(value = "id") Long id, Model model){
        Events events = eventsService.findById(id);
        model.addAttribute("events", events);

        return "editEvent";
    }

    @PostMapping("profile/edit/{id}")
    public String editEvent(@PathVariable(value = "id") Long id,
                            @RequestParam String title,
                            @RequestParam String content,
                            @RequestParam String adress,
                            @RequestParam int participants){
        Events events = eventsService.findById(id);
        events.setTitle(title);
        events.setContent(content);
        events.setAdress(adress);
        events.setParticipans(participants);
        eventsService.save(events);
        return "redirect:/profile";
    }

    @PostMapping("profile/remove/{id}")
    public String removeEvent(@PathVariable(value = "id") Long id){
        Events events = eventsService.findById(id);

        eventsService.delete(events);
        return "redirect:/profile";
    }



    @GetMapping("/registration")
    public String registration(Model model){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDb = userService.findByUsername(user);

        if (userFromDb != null){
            model.put("message", "User exists!");
            return "registration";
        }

        userService.createUser(user);
        return "redirect:/login";
    }


}
