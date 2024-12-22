package com.mohit.freesplitwise.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.freesplitwise.Entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    
}
