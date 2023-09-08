package com.example.summerpractic2023.base.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "adress")
    private String adress;

    @Column(name = "participants")
    private int participans;

    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;


//    @ManyToMany
//    @JoinTable(
//            name="user_subscriptions",
//            joinColumns = { @JoinColumn(name = "user_ids") },
//            inverseJoinColumns = { @JoinColumn(name = "event_id") }
//    )
//    private Set<Events> eventsList = new HashSet<>();
//
//
//    @ManyToMany
//    @JoinTable(
//            name="user_subscriptions",
//            joinColumns = { @JoinColumn(name = "event_id") },
//            inverseJoinColumns = { @JoinColumn(name = "user_ids") }
//    )
//    private Set<Events> usersList = new HashSet<>();

    public Events(User user ,String title, String content, String adress, int participans) {
        this.author = user;
        this.title = title;
        this.content = content;
        this.adress = adress;
        this.participans = participans;
    }

    public Events() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User masterKeyEvent) {
        this.author = masterKeyEvent;
    }

//        public Set<Events> getEventsList() {
//        return eventsList;
//    }
//
//    public void setEventsList(Set<Events> eventsList) {
//        this.eventsList = eventsList;
//    }
//
//    public Set<Events> getUsersList() {
//        return usersList;
//    }
//
//    public void setUsersList(Set<Events> usersList) {
//        this.usersList = usersList;
//    }
}
