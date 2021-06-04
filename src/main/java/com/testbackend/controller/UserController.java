package com.testbackend.controller;

import com.testbackend.entity.User;
import com.testbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String viewUserPage(Model model, @Param("keyword") String keyword){
        List<User> users = userService.getAllUser(keyword);
        model.addAttribute("users", users);
        model.addAttribute("keyword", keyword);
        return "user/index";
    }

    @GetMapping("/add")
    public String viewAddUserPage(Model model){
        model.addAttribute("user", new User());
        return "user/addUser";
    }

    @PostMapping("/add")
    public String processAddUser(User user, Model model){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.saveUser(user);
        model.addAttribute("messages", "addSuccess");
        return viewUserPage(model, null);
    }

    @GetMapping("/edit")
    public String viewEditUserPage(Model model, @RequestParam("id") String username){
        User user = userService.findByUsername(username);
        model.addAttribute("user",user);
        return "user/editUser";
    }

    @PostMapping("/edit")
    public String processEditUser(User user, Model model, @RequestParam("password") String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        userService.saveUser(user);
        model.addAttribute("messages", "editSuccess");
        return viewUserPage(model, null);
    }

    @GetMapping("/delete")
    public String procesDeleteUserPage(Model model, @RequestParam("id") String username){
        userService.deleteUser(username);
        model.addAttribute("messages","deleteSuccess");
        return viewUserPage(model, null);
    }

}
