package com.mohit.freesplitwise.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.freesplitwise.Entity.Expense;
import com.mohit.freesplitwise.Service.ExpenseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add")
    public ResponseEntity<Expense> addGroupExpense(@RequestBody Expense expense) {
        Expense savedExpense = expenseService.createGroupExpense(expense);
        return ResponseEntity.ok(savedExpense);
    }
    
    @GetMapping("/getsplits/{paidById}/getall")
    public String getSplitsOwesToAll(@PathVariable Long paidById) {
        return expenseService.getExpensePaidByUserOweToAll(paidById);
    }

    @GetMapping("/getsplits/{paidById}/{paidForId}")
    public String getSplitsOwesToId(@PathVariable Long paidById, @PathVariable Long paidForId) {
        return new String();
    }
    
    
}
