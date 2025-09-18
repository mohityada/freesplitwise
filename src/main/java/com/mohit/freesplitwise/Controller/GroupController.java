package com.mohit.freesplitwise.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.freesplitwise.CustomDTO.DTOMapper;
import com.mohit.freesplitwise.CustomDTO.GroupDTO;
import com.mohit.freesplitwise.Entity.Group;
import com.mohit.freesplitwise.Service.GroupService;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("api/groups")
public class GroupController {
    
    @Autowired
    private GroupService groupService;

    @PostMapping("/creategroup")
    public ResponseEntity<GroupDTO> createGroup(@RequestBody Group newGroup) {
        Group savedGroup = groupService.addGroup(newGroup);
        return ResponseEntity.ok(DTOMapper.toGroupDTO(savedGroup));
    }

    @GetMapping("/getgroup/{groupId}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable Long groupId) {
        GroupDTO group = groupService.getGroup(groupId);
        return ResponseEntity.ok(group);
    }
    
    @PutMapping(value = "/{groupId}/add/members", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GroupDTO addUserInGroup(@PathVariable Long groupId, @RequestBody List<String> emails) {
        return groupService.addUsersToGroup(groupId, emails);
    }

    @PutMapping(value = "/{groupId}/remove/members", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GroupDTO removeUserFromGroup(@PathVariable Long groupId, @RequestBody List<String> emails) {
        return groupService.removeUsersFromGroup(groupId, emails);
    }
    
    
}
