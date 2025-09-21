package com.mohit.freesplitwise.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.freesplitwise.CustomException.UserNotFoundException;
import com.mohit.freesplitwise.Entity.User;
import com.mohit.freesplitwise.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getUserOptional(String email){
        return userRepository.findByEmail(email);
    }

    public User getUser(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("This user does not exists, invite user to join freesplitwise."));
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(() ->
            new UserNotFoundException("This user does not exists, invite user to join freesplitwise.")
        );
    }

    public List<User> getUsers(List<String> emails){
        List<User> users = new ArrayList<>();

        for(String email : emails){
            users.add(getUser(email));
        }
        
        return users;
    }

}
