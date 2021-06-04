package com.testbackend.repository;


import com.testbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    public User findByUsername(String username);
    @Query("SELECT u FROM User u WHERE CONCAT(u.fullname) LIKE %?1%")
    public List<User> search(String keyword);
}
