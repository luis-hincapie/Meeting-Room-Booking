package com.javainterns.bookingroom.controller;

import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user")
    public String saveUser(){
        User user = new User(

        );
        user.setName("Juan");
        user.setEmail("juan@hola.com");

        userService.resgistration(user);
        return "Creado";
    }

}
