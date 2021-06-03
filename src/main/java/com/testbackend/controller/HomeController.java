package com.testbackend.controller;

import com.testbackend.entity.User;
import com.testbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String viewLoginPage(){

        return "login";
    }

//    @GetMapping("/logout")
//    public String viewLogoutPage(){
//
//        return "logout";
//    }

    @GetMapping("/register")
    public String viewRegisterPage(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/register")
    public String processRegister(User user, Model model){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.saveUser(user);
        model.addAttribute("messages", "Đăng ký thành công");
        return "signup";
    }
}
