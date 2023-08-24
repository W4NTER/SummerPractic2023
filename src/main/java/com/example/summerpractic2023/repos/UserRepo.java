package com.example.summerpractic2023.repos;

import com.example.summerpractic2023.base.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo  extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
