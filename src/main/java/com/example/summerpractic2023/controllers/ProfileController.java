package com.example.summerpractic2023.controllers;

import com.example.summerpractic2023.base.Role;
import com.example.summerpractic2023.base.entity.User;
import com.example.summerpractic2023.base.entity.User_Interests;
import com.example.summerpractic2023.repos.InterestsRepo;
import com.example.summerpractic2023.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class ProfileController {
    @Autowired
    private InterestsRepo interestsRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/profile")
    public String userProfile(@AuthenticationPrincipal User user, Map<String, Object> model){
        Iterable<User_Interests> interests;

        if (user != null ) {
            interests=interestsRepo.findByMasterKey(user);
        } else {

            model.put("interests", "You didn't fill out a profile");
            return "profile";
        }
        model.put("interests", interests);
        return "profile";
    }

    @PostMapping("/profile")
    public String add(@AuthenticationPrincipal User user,
            @RequestParam String name, @RequestParam Integer age,
                      @RequestParam String sport, @RequestParam Integer level_sport, Map<String, Object> model) {
        User_Interests user_interests = new User_Interests(name, age, sport, level_sport, user);


        try {
            interestsRepo.save(user_interests);


            Iterable<User_Interests> interests = interestsRepo.findByMasterKey(user);

            model.put("interests", interests);
            return "profile";
        } finally {
            model.put("message", "You complete it");

            Iterable<User_Interests> interests = interestsRepo.findByMasterKey(user);

            model.put("interests", interests);
            return "profile";
        }

    }






    @GetMapping("/registration")
    public String registration(Model model){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null){
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
