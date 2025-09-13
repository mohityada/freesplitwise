package com.mohit.freesplitwise.Entity;

import java.time.LocalDate;
import java.util.Map;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="expense_table")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String description;

    private Double totalAmount;

    @ManyToOne
    private User paidBy;

    @ManyToOne
    private Group group;

    @ElementCollection
    private Map<Long, Double> splitDetails; // User ID -> Amount

    private LocalDate date;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setSplitDetails(Map<Long, Double> splitDetails) {
        this.splitDetails = splitDetails;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public Group getGroup() {
        return group;
    }

    public Map<Long, Double> getSplitDetails() {
        return splitDetails;
    }

    public LocalDate getDate() {
        return date;
    }

}
