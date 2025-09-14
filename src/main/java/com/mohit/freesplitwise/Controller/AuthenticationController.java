package com.mohit.freesplitwise.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.freesplitwise.Configuration.JwtUtil;
import com.mohit.freesplitwise.Entity.User;
import com.mohit.freesplitwise.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/authenticate")
public class AuthenticationController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signin")
    public String signinUser(@RequestBody User newUser) {
        User user = userService.getUser(newUser.getEmail());
        
        if(user == null){
            User savedUser = userService.addUser(user);
            return jwtUtil.generateToken(savedUser.getEmail());
        }

        return "User already exists, try login.";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        User savedUser = userService.getUser(user.getEmail());
        
        System.out.println("han bhai");

        if(savedUser == null){
            return "User doesn't exists, try signin.";
        }

        return jwtUtil.generateToken(savedUser.getEmail());
    }
    
    
}
