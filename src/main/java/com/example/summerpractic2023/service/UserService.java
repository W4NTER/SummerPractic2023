package com.example.summerpractic2023.service;

import com.example.summerpractic2023.base.Role;
import com.example.summerpractic2023.base.entity.User;
import com.example.summerpractic2023.repos.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
//    @Autowired
    private final UserRepo userRepo;
//    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
//        this.passwordEncoder = passwordEncoder;

    }
//    public boolean addUser(User user){
//        User userFromDb = userRepo.findByUsername(user.getUsername());
//
//        if(userFromDb != null) {
//            return false;
//        }
//
//        user.setActive(true);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public Object findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles =
                Arrays.stream(Role.values())
                        .map(Role::name)
                        .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet() ){
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
    }

    public void createUser(User user) {
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
    }

    public User findByUsername(User user) {
        return userRepo.findByUsername(user.getUsername());
    }
}
