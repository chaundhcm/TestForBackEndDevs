package com.testbackend.service;

import com.testbackend.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    void saveUser(User user);
    void deleteUser(String username);
    User findByUsername(String username);
}
