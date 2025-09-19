package com.mohit.freesplitwise.Service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.freesplitwise.CustomDTO.DTOMapper;
import com.mohit.freesplitwise.CustomDTO.GroupDTO;
import com.mohit.freesplitwise.CustomException.GroupNotFoundException;
import com.mohit.freesplitwise.Entity.Group;
import com.mohit.freesplitwise.Entity.User;
import com.mohit.freesplitwise.Repository.GroupRepository;

@Service
public class GroupService {
    
    @Autowired
    private GroupRepository groupRepository;
    
    @Autowired 
    private UserService userService;

    public Group addGroup(Group group){
        return groupRepository.save(group);
    }

    public GroupDTO getGroupDTO(Long id){
        Group group = groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException("Group not found!"));
        return DTOMapper.toGroupDTO(group);
    }

     public Group getGroup(Long id){
        Group group = groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException("Group not found!"));
        return group;
    }

    public GroupDTO removeUsersFromGroup(Long groupId, List<String> emails){
        Group group = groupRepository.findById(groupId).orElseThrow(() ->
            new GroupNotFoundException("Group not found!"));   

        List<User> existingUsers = group.getUsers();

        if (!(existingUsers instanceof java.util.ArrayList)) {
            existingUsers = new java.util.ArrayList<>(existingUsers);
            group.setUsers(existingUsers);
        }
        Set<Long> userIdSet = existingUsers.stream()
            .map(User::getId)
            .collect(java.util.stream.Collectors.toSet());

        List<User> users = userService.getUsers(emails);

        for(User user : users){
            if (userIdSet.contains(user.getId())) {
                existingUsers.remove(user);
                userIdSet.remove(user.getId());
            }
        }
        groupRepository.save(group);
        return DTOMapper.toGroupDTO(group);
    }

    
    public GroupDTO addUsersToGroup(Long groupId, List<String> emails){
        Group group = groupRepository.findById(groupId).orElseThrow(() ->
            new GroupNotFoundException("Group not found!"));   

        List<User> existingUsers = group.getUsers();

        if (!(existingUsers instanceof java.util.ArrayList)) {
            existingUsers = new java.util.ArrayList<>(existingUsers);
            group.setUsers(existingUsers);
        }
        Set<Long> userIdSet = existingUsers.stream()
            .map(User::getId)
            .collect(java.util.stream.Collectors.toSet());

        List<User> users = userService.getUsers(emails);

        for(User user : users){
            if (!userIdSet.contains(user.getId())) {
                existingUsers.add(user);
                userIdSet.add(user.getId());
            }
        }
        groupRepository.save(group);
        return DTOMapper.toGroupDTO(group);
    }
}

