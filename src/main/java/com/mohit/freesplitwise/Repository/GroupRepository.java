package com.mohit.freesplitwise.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.freesplitwise.Entity.Group;
import java.util.Optional;
import java.util.List;



@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    
    Optional<Group> findById(Long id);

    List<Group> findByName(String name);
}
