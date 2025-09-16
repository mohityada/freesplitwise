package com.mohit.freesplitwise.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.freesplitwise.CustomDTO.DTOMapper;
import com.mohit.freesplitwise.CustomDTO.GroupDTO;
import com.mohit.freesplitwise.CustomException.GroupNotFoundException;
import com.mohit.freesplitwise.Entity.Group;
import com.mohit.freesplitwise.Repository.GroupRepository;

@Service
public class GroupService {
    
    @Autowired
    private GroupRepository groupRepository;

    public Group addGroup(Group group){
        return groupRepository.save(group);
    }

    public GroupDTO getGroup(Long id){
        Group group = groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException("User not found!"));
        return DTOMapper.toGroupDTO(group);
    }
}

