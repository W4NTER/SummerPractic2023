package com.example.summerpractic2023.service;

import com.example.summerpractic2023.base.entity.Events;
import com.example.summerpractic2023.base.entity.User;
import com.example.summerpractic2023.repos.EventsRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class EventsService {

    private final EventsRepo eventsRepo;

    public EventsService(EventsRepo eventsRepo) {
        this.eventsRepo = eventsRepo;
    }

    @Value("${upload.path}")
    private String uploadPath;


    public Iterable<Events> findByAuthor(User user) {
        return eventsRepo.findByAuthor(user);
    }

    public void eventsViewByAuthor(User user, Map<String, Object> model) {
        Iterable<Events> events_view = eventsRepo.findByAuthor(user);
        model.put("events_view", events_view);
    }

    public Iterable<Events> findAll() {
        return eventsRepo.findAll();
    }

    public void save(Events events) {
        eventsRepo.save(events);
    }

    public Events findById(Long id) {
        return eventsRepo.findById(id).orElseThrow();
    }

    public void delete(Events events) {
        eventsRepo.delete(events);
    }

    public void addEvent(User user, MultipartFile file, String title, String content,
                         String adress, int participants, Map<String, Object> model) throws IOException {

        Events events = new Events(user, title, content, adress, participants);

        if (file != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            events.setFilename(resultFilename);
        }

        eventsRepo.save(events);

        model.put("events", events);
    }
}
