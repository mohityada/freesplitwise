package com.mohit.freesplitwise.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.freesplitwise.CustomDTO.PersonalExpenseDTO;
import com.mohit.freesplitwise.Entity.Expense;
import com.mohit.freesplitwise.Service.ExpenseService;

import java.util.List;
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

    @PostMapping("group/add")
    public ResponseEntity<Expense> addGroupExpense(@RequestBody Expense expense) {
        Expense savedExpense = expenseService.createGroupExpense(expense);
        return ResponseEntity.ok(savedExpense);
    }

    @PostMapping("user/add")
    public ResponseEntity<Expense> addUserExpense(@RequestBody Expense expense) {
        Expense savedExpense = expenseService.createUserExpense(expense);
        return ResponseEntity.ok(savedExpense);
    }
    
    @GetMapping("/getsplits/{paidById}/getall")
    public ResponseEntity<List<PersonalExpenseDTO>> getSplitsOwesToAll(@PathVariable Long paidById) {
        List<PersonalExpenseDTO> personalExpenseDTO = expenseService.getExpensePaidByUserOweToAll(paidById);
        return ResponseEntity.ok(personalExpenseDTO);
    }

    @GetMapping("/getsplits/{paidById}/getfrom/{paidForId}")
    public ResponseEntity<List<PersonalExpenseDTO>> getSplitsOwesToId(@PathVariable Long paidById, @PathVariable Long paidForId) {
        List<PersonalExpenseDTO> personalExpenseDTO = expenseService.getPersonalExpenseUserOweToSpecificUser(paidById, paidForId);
        return ResponseEntity.ok(personalExpenseDTO);
    }
        
}
