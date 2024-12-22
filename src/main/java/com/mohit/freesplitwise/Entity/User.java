package com.mohit.freesplitwise.Entity;

import java.util.List;

import org.springframework.format.annotation.NumberFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity //for ORM
public class User {
    @Id // PK of table
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
    
    @NotBlank
    private String Name;

    @NotBlank
    @Email
    private String Email;

    @NotBlank
    @NumberFormat
    private String Mobile;

    //Relationship
    @ManyToMany(mappedBy= "Users")
    private List<Group> Groups;

    public Long getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getMobile() {
        return Mobile;
    }

    public List<Group> getGroups() {
        return Groups;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public void setGroups(List<Group> groups) {
        Groups = groups;
    } 
    
}
