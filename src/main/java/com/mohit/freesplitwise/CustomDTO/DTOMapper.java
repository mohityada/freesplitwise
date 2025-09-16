package com.mohit.freesplitwise.CustomDTO;

import java.util.List;
import java.util.stream.Collectors;

import com.mohit.freesplitwise.Entity.Expense;
import com.mohit.freesplitwise.Entity.Group;
import com.mohit.freesplitwise.Entity.User;

public class DTOMapper {
    public static GroupDTO toGroupDTO(Group group) {
        GroupDTO dto = new GroupDTO();
        dto.setId(group.getId());
        dto.setName(group.getName());

        // map users
        dto.setUsers(group.getUsers().stream()
            .map(user -> {
                UserDTO u = new UserDTO();
                u.setId(user.getId());
                u.setName(user.getName());
                u.setEmail(user.getEmail());
                u.setMobile(user.getMobile());
                return u;
            }).collect(Collectors.toList()));

        // map expenses
        // dto.setExpenses(group.getExpenses().stream()
        //     .map(expense -> {
        //         ExpenseDTO e = new ExpenseDTO();
        //         e.setId(expense.getId());
        //         e.setDescription(expense.getDescription());
        //         e.setAmount(expense.getTotalAmount());
        //         return e;
        //     }).collect(Collectors.toList()));

        return dto;
    }

    public static UserDTO toUserDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setEmail(user.getEmail());
        dto.setId(user.getId());
        dto.setMobile(user.getMobile());
        dto.setName(user.getName());
        dto.setGroupSummaryDTO(toGroupSummaryDTO(user.getGroups()));
        return dto;
    }

    public static ExpenseDTO toExpenseDTO(Expense expense){
        ExpenseDTO dto = new ExpenseDTO();
        dto.setId(expense.getId());
        dto.setAmount(expense.getTotalAmount());
        dto.setDescription(expense.getDescription());
        return dto;
    }

    public static List<GroupSummaryDTO> toGroupSummaryDTO(List<Group> groups){
        return groups.stream().map(group -> {
            GroupSummaryDTO dto = new GroupSummaryDTO();
            dto.setGroupName(group.getName());
            dto.setId(group.getId());
            return dto;
        }).collect(Collectors.toList());
    }
}
