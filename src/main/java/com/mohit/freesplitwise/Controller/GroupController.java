package com.mohit.freesplitwise.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.freesplitwise.Entity.Group;
import com.mohit.freesplitwise.Service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("api/groups")
public class GroupController {
    
    @Autowired
    private GroupService groupService;

    @PostMapping("/creategroup")
    public ResponseEntity<Group> createGroup(@RequestBody Group newGroup) {
        Group savedGroup = groupService.addGroup(newGroup);
        return ResponseEntity.ok(savedGroup);
    }

    @GetMapping("/getgroup/{groupId}")
    public ResponseEntity<Group> getGroupById(@PathVariable Long groupId) {
        Group group = groupService.getGroup(groupId);
        return ResponseEntity.ok(group);
    }
    
    
}
