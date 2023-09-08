package com.example.summerpractic2023.service;

import com.example.summerpractic2023.base.entity.User;
import com.example.summerpractic2023.base.entity.User_Interests;
import com.example.summerpractic2023.repos.InterestsRepo;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Map;

@Service
public class InterestsService {
    private final InterestsRepo interestsRepo;

    public InterestsService(InterestsRepo interestsRepo) {
        this.interestsRepo = interestsRepo;
    }

    public void saveInterests(User_Interests user_interests, User user, Map<String, Object> model) {
        Iterable<User_Interests> interests;
        if (interestsRepo.findByMasterKey(user).isEmpty()){
            interestsRepo.save(user_interests);
            interests = interestsRepo.findByMasterKey(user);
        } else {
            interests = interestsRepo.findByMasterKey(user);
            model.put("message", "You complete it");
        }
        model.put("interests", interests);
    }

    public void interestsView(User user, Map<String, Object> model) {
        Iterable<User_Interests> interests = interestsRepo.findByMasterKey(user);

        model.put("interests", interests);
    }

    public User_Interests findById(Long id) {
        return interestsRepo.findById(id).orElseThrow();
    }

    public void save(User_Interests interests) {
        interestsRepo.save(interests);
    }
}
