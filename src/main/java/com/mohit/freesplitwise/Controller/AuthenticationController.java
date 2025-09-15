package com.mohit.freesplitwise.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.freesplitwise.Configuration.JwtUtil;
import com.mohit.freesplitwise.CustomException.UserNotFoundException;
import com.mohit.freesplitwise.Entity.User;
import com.mohit.freesplitwise.Service.UserService;

import java.util.Optional;

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
        Optional<User> user = userService.getUser(newUser.getEmail());
        
        if(!user.isPresent()){
            User savedUser = userService.addUser(newUser);
            return jwtUtil.generateToken(savedUser.getEmail());
        }

        return "User already exists, try login.";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        User savedUser = userService.getUser(user.getEmail()).
            orElseThrow(() -> new UserNotFoundException("User not found!"));

        return jwtUtil.generateToken(savedUser.getEmail());
    }
    
    
}
