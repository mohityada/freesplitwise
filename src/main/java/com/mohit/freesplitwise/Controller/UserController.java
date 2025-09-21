package com.mohit.freesplitwise.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.freesplitwise.CustomDTO.DTOMapper;
import com.mohit.freesplitwise.CustomDTO.UserDTO;
import com.mohit.freesplitwise.Entity.User;
import com.mohit.freesplitwise.Service.UserService;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    UserService userService;
    
    @GetMapping("/getuser")
    public ResponseEntity<UserDTO> getUser(@RequestParam String email) {
        User savedUser = userService.getUser(email);
        return ResponseEntity.ok(DTOMapper.toUserDTO(savedUser));
    }
    
    
    
}
