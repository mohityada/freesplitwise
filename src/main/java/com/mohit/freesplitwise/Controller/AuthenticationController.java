package com.mohit.freesplitwise.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.freesplitwise.Configuration.JwtUtil;
import com.mohit.freesplitwise.Entity.User;
import com.mohit.freesplitwise.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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
    public ResponseEntity<?> signinUser(@RequestBody User newUser) {
        User savedUser = userService.signup(newUser);
        String token = jwtUtil.generateToken(savedUser.getEmail());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            userService.authenticate(user);
            String token = jwtUtil.generateToken(user.getEmail());
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Incorrect email or password",HttpStatus.NO_CONTENT);
        }

       
    }
    
    
}
