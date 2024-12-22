package com.mohit.freesplitwise.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mohit.freesplitwise.Entity.Expense;
import com.mohit.freesplitwise.Repository.ExpenseRepository;

public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense createExpense(Expense expense) {
        
        return expenseRepository.save(expense);  // Save expense to the database
    }

    public List<Expense> getExpensesByGroup(Long Id) {
        return expenseRepository.findByGroupId(Id);
    }

}
