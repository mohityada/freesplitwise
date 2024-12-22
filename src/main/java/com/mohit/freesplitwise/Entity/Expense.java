package com.mohit.freesplitwise.Entity;

import java.time.LocalDate;
import java.util.Map;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;


public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank
    private String Description;

    private Double TotalAmount;

    @ManyToOne
    private User PaidBy;

    @ManyToOne
    private Group Group;

    @ElementCollection
    private Map<Long, Double> SplitDetails; // User ID -> Amount

    private LocalDate Date;

    public Long getId() {
        return Id;
    }

    public String getDescription() {
        return Description;
    }

    public Double getTotalAmount() {
        return TotalAmount;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setTotalAmount(Double totalAmount) {
        TotalAmount = totalAmount;
    }

    public void setPaidBy(User paidBy) {
        PaidBy = paidBy;
    }

    public void setGroup(Group group) {
        Group = group;
    }

    public void setSplitDetails(Map<Long, Double> splitDetails) {
        SplitDetails = splitDetails;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public User getPaidBy() {
        return PaidBy;
    }

    public Group getGroup() {
        return Group;
    }

    public Map<Long, Double> getSplitDetails() {
        return SplitDetails;
    }

    public LocalDate getDate() {
        return Date;
    }

}
