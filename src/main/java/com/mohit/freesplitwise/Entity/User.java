package com.mohit.freesplitwise.Entity;

import java.util.List;

import org.springframework.format.annotation.NumberFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity //for ORM
@Table(name = "\"user\"", schema = "freesplitwise")
public class User {
    @Id // PK of table
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @NumberFormat
    private String mobile;

    //Relationship
    @ManyToMany(mappedBy= "users")
    private List<Group> groups;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    } 
    
}
