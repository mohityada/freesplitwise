package com.mohit.freesplitwise.CustomDTO;

public class PersonalExpenseDTO {
    private String paidBy;
    private String paidFor;
    private Double amount;
    private String description;

    public PersonalExpenseDTO(String paidBy, String paidFor, Double amount, String description) {
        this.paidBy = paidBy;
        this.paidFor = paidFor;
        this.amount = amount;
        this.description = description;
    }

    public String getPaidBy() { return paidBy; }
    public void setPaidBy(String paidBy) { this.paidBy = paidBy; }
    public String getPaidFor() { return paidFor; }
    public void setPaidFor(String paidFor) { this.paidFor = paidFor; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
