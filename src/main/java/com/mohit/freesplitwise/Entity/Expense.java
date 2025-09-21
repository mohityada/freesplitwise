package com.mohit.freesplitwise.Entity;

import java.time.LocalDate;
import java.util.Map;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String description;

    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "paid_by", nullable = false)
    private User paidBy;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = true)
    private Group group;

    // UserID -> Amount mapping
    @ElementCollection
    @CollectionTable(
        name = "expense_splits",
        joinColumns = @JoinColumn(name = "expense_id")
    )
    @MapKeyColumn(name = "user_id")
    @Column(name = "amount")
    private Map<Long, Double> splitDetails;

    private LocalDate date;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public User getPaidBy() { return paidBy; }
    public void setPaidBy(User paidBy) { this.paidBy = paidBy; }

    public Group getGroup() { return group; }
    public void setGroup(Group group) { this.group = group; }

    public Map<Long, Double> getSplitDetails() { return splitDetails; }
    public void setSplitDetails(Map<Long, Double> splitDetails) { this.splitDetails = splitDetails; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}