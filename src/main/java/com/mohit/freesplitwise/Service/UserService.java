package com.mohit.freesplitwise.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.freesplitwise.Entity.User;
import com.mohit.freesplitwise.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getUser(String email){
        return userRepository.findByEmail(email);
    }


}
