package com.mohit.freesplitwise.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Group {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotBlank
    private String Name;

    @ManyToMany
    @JoinTable(
        name = "GroupUsers",
        joinColumns = @JoinColumn(name = "GroupId"),
        inverseJoinColumns = @JoinColumn(name = "UserId")
    )
    private List<User> Users;

    @OneToMany(mappedBy="Group")
    private List<Expense> Expenses;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setUsers(List<User> users) {
        Users = users;
    }

    public void setExpenses(List<Expense> expenses) {
        Expenses = expenses;
    }

    public String getName() {
        return Name;
    }

    public List<User> getUsers() {
        return Users;
    }

    public List<Expense> getExpenses() {
        return Expenses;
    }
}
