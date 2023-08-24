package com.example.summerpractic2023.repos;

import com.example.summerpractic2023.base.entity.Events;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventsRepo extends CrudRepository<Events, Long> {

//    List<Events> findByTitle(String title);
}
