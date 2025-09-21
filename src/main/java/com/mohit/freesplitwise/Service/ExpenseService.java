package com.mohit.freesplitwise.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.freesplitwise.CustomDTO.PersonalExpenseDTO;
import com.mohit.freesplitwise.Entity.Expense;
import com.mohit.freesplitwise.Entity.Group;
import com.mohit.freesplitwise.Repository.ExpenseRepository;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private GroupService groupService;

    @Autowired 
    private UserService userService;

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense createGroupExpense(Expense expense) {
        Long groupId = expense.getGroup().getId();
        Group group = groupService.getGroup(groupId);
        group.getExpenses().add(expense);
        System.out.println("Saved new expense : " + expense.getDescription() + " into " + group.getName());
        return expenseRepository.save(expense);
    }

    public Expense createUserExpense(Expense expense) {
        System.out.println("Saving new expense for : " + expense.getPaidBy());
        return expenseRepository.save(expense);
    }

    public List<PersonalExpenseDTO> getPersonalExpenseUserOweToSpecificUser(Long userId, Long otherUserId){
        List<Expense> expenses = expenseRepository.findByPaidById(userId);
        String userName = userService.getUserById(userId).getName();
        String otherUserName = userService.getUserById(otherUserId).getName();

        return expenses.stream()
            .flatMap(expense -> expense.getSplitDetails().entrySet().stream()
                .filter(entry -> entry.getKey().equals(otherUserId))
                .map(entry -> new PersonalExpenseDTO(
                    userName,
                    otherUserName,
                    entry.getValue(),
                    expense.getDescription()
                ))
            )
            .collect(Collectors.toList());
    }
    

    public List<PersonalExpenseDTO> getExpensePaidByUserOweToAll(Long userId) {
        Map<Long, Double> owedAmounts = getOwedAmounts(userId);
        String userName = userService.getUserById(userId).getName();

        return owedAmounts.entrySet().stream()
            .filter(e -> !e.getKey().equals(userId))
            .map(e -> {
                String otherUserName = userService.getUserById(e.getKey()).getName();
                return new PersonalExpenseDTO(
                    userName,
                    otherUserName,
                    e.getValue(),
                    "Total owed"
                );
            })
            .collect(Collectors.toList());
    }


    public List<Expense> getExpensesByGroup(Long Id) {
        return expenseRepository.findByGroupId(Id);
    }

    private Map<Long, Double> getOwedAmounts(Long userId){
        List<Expense> expenses = expenseRepository.findByPaidById(userId);

        return expenses.stream()
            .flatMap(expense -> expense.getSplitDetails().entrySet().stream())
            .collect(Collectors.groupingBy(
                Map.Entry::getKey,
                Collectors.summingDouble(Map.Entry::getValue)
        ));
    }

}
