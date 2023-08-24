package com.example.summerpractic2023.base.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "user_Interests")
public class User_Interests {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "sport")
    private String sport;

    @Column(name = "level_sport")
    private Integer level_sport;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User masterKey;



    public User_Interests() {
    }

    public User_Interests(String name, Integer age, String sport, Integer level_sport, User user) {
        this.masterKey = user;
        this.name = name;
        this.age = age;
        this.sport = sport;
        this.level_sport = level_sport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public int getLevel_sport() {
        return level_sport;
    }

    public void setLevel_sport(int level) {
        this.level_sport = level;
    }

    public User getMasterKey() {
        return masterKey;
    }

    public void setMasterKey(User masterKey) {
        this.masterKey = masterKey;
    }
}
