package com.example.summerpractic2023.base.entity;

import jakarta.persistence.*;
import org.thymeleaf.spring6.processor.SpringOptionInSelectFieldTagProcessor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;


    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "adress")
    private String adress;

    @Column(name = "participants")
    private int participans;


//    @ManyToMany
//    @JoinTable(
//            name="user_subscriptions",
//            joinColumns = { @JoinColumn(name = "user_ids") },
//            inverseJoinColumns = { @JoinColumn(name = "enent_id") }
//    )
//    private Set<Events> eventsList = new HashSet<>();
//
//
//    @ManyToMany
//    @JoinTable(
//            name="user_subscriptions",
//            joinColumns = { @JoinColumn(name = "enent_id") },
//            inverseJoinColumns = { @JoinColumn(name = "user_ids") }
//    )
//    private Set<Events> usersList = new HashSet<>();

    public Events(String title, String content, String adress, int participans) {
        this.title = title;
        this.content = content;
        this.adress = adress;
        this.participans = participans;
    }

    public Events() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getParticipans() {
        return participans;
    }

    public void setParticipans(int participans) {
        this.participans = participans;
    }

    public Set<Events> getEventsList() {
        return eventsList;
    }

    public void setEventsList(Set<Events> eventsList) {
        this.eventsList = eventsList;
    }

    public Set<Events> getUsersList() {
        return usersList;
    }

    public void setUsersList(Set<Events> usersList) {
        this.usersList = usersList;
    }
}
