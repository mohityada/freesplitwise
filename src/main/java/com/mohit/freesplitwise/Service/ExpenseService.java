package com.mohit.freesplitwise.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.freesplitwise.Entity.Expense;
import com.mohit.freesplitwise.Entity.Group;
import com.mohit.freesplitwise.Repository.ExpenseRepository;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private GroupService groupService;

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense createGroupExpense(Expense expense) {
        Long groupId = expense.getGroup().getId();
        Group group = groupService.getGroup(groupId);
        group.getExpenses().add(expense);
        System.out.println("Saved new expense : " + expense.getDescription() + " into " + group.getName());
        groupService.addGroup(group);
        return expenseRepository.save(expense);
    }

   public String getExpensePaidByUserOweToAll(Long userId) {
        List<Expense> expenses = expenseRepository.findByPaidById(userId);

        // Map<otherUserId, total owed amount>
        Map<Long, Double> owedAmounts = new HashMap<>();

        for (Expense expense : expenses) {
            Map<Long, Double> splits = expense.getSplitDetails();

            for (Map.Entry<Long, Double> entry : splits.entrySet()) {
                Long otherUserId = entry.getKey();
                Double share = entry.getValue();
                System.out.println("otherUserId : " + otherUserId + " share : " + share);
                if (!otherUserId.equals(userId)) { 
                    owedAmounts.put(otherUserId, 
                        owedAmounts.getOrDefault(otherUserId, 0.0) + share);
                }
            }
        }

        return owedAmounts.entrySet().stream()
            .map(e -> "User " + e.getKey() + " owes " + e.getValue() + " to User " + userId)
            .collect(Collectors.joining("\n"));
    }


    public List<Expense> getExpensesByGroup(Long Id) {
        return expenseRepository.findByGroupId(Id);
    }

}
