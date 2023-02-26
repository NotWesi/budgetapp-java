package model;

import java.util.ArrayList;

public class Month {
    private Budget budget;
    private Expenses expenses;

    // EFFECTS: represents a month with its corresponding budget and expenses
    public Month() {
        budget = new Budget();
        expenses = new Expenses();
    }

    // EFFECTS: returns budget
    public Budget getBudget() {
        return budget;
    }

    // EFFECTS: returns expenses
    public Expenses getExpenses() {
        return expenses;
    }

    // MODIFIES: budget
    // EFFECTS: changes entry amount of budget to new amount
    public void modifyBudgetEntryAmount(int index, double newAmount) {
        budget.modifyEntryAmount(index, newAmount);
    }

    // MODIFIES: budget
    // EFFECTS: changes entry description of budget to new description
    public void modifyBudgetEntryName(int index, String newName) {
        budget.modifyEntryDescription(index, newName);
    }

    // MODIFIES: expenses
    // EFFECTS: changes entry amount of expenses to new amount
    public void modifyExpensesEntryAmount(int index, double newAmount) {
        expenses.modifyEntryAmount(index, newAmount);
    }

    // MODIFIES: expenses
    // EFFECTS: changes entry description of expenses to new description
    public void modifyExpensesEntryName(int index, String newName) {
        expenses.modifyEntryDescription(index, newName);
    }

    // EFFECTS: returns the net budget for the month
    // by subtracting total budget by total expenses
    public double netmonthlyBudget(Month month) {
        // initializes empty total budget and expenses
        double totalBudget = 0.0;
        double totalExpenses = 0.0;

        // Sum the budget and expenses for the month
        for (int i = 1; i < budget.getSize(); i++) {
            totalBudget += budget.getSpecificEntry(i).getAmount();
        }
        for (int i = 1; i < expenses.getSize(); i++) {
            totalExpenses += expenses.getSpecificEntry(i).getAmount();
        }
        // Calculate the net budget
        double netBudget = totalBudget - totalExpenses;
        return netBudget;
    }
}

