package com.example.summerpractic2023.repos;

import com.example.summerpractic2023.base.entity.User;
import com.example.summerpractic2023.base.entity.User_Interests;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InterestsRepo extends CrudRepository<User_Interests, Long> {

    List<User_Interests> findByMasterKey(User masterKey);

}
