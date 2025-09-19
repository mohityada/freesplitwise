package com.mohit.freesplitwise.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.freesplitwise.Entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // Optional<Expense> findById(Long Id); no need to define as this method is already present in the JpaRepo
    List<Expense> findByGroupId(Long groupId);
    List<Expense> findByPaidById(Long paidById);
}
