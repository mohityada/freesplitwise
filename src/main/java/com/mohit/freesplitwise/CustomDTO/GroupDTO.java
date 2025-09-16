package com.mohit.freesplitwise.CustomDTO;

import java.util.List;

public class GroupDTO {
    private Long id;
    private String name;
    private List<UserDTO> users;
    private List<ExpenseDTO> expenses;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<UserDTO> getUsers() {
        return users;
    }
    public List<ExpenseDTO> getExpenses() {
        return expenses;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
    public void setExpenses(List<ExpenseDTO> expenses) {
        this.expenses = expenses;
    }
}
