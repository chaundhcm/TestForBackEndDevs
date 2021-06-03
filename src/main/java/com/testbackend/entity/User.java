package com.testbackend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "fullname", nullable = false, length = 50)
    private String fullname;
}
