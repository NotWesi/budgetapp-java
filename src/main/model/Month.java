package model;

// presents the budget and expenses entries for a month of a year
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
        // logs event that month budget has been returned
        EventLog.getInstance().logEvent(new Event("Month budget has been returned."));
        return budget;
    }

    // EFFECTS: returns expenses
    public Expenses getExpenses() {
        // logs event that month expenses has been returned
        EventLog.getInstance().logEvent(new Event("Month expenses have been returned."));
        return expenses;
    }

    // REQUIRES: int index and double newAmount for entry
    // MODIFIES: budget
    // EFFECTS: changes entry amount of budget to new amount
    public void modifyBudgetEntryAmount(int index, double newAmount) {
        budget.modifyEntryAmount(index, newAmount);
    }

    // REQUIRES: int index and double newAmount for entry
    // MODIFIES: budget
    // EFFECTS: changes entry description of budget to new description
    public void modifyBudgetEntryName(int index, String newName) {
        budget.modifyEntryDescription(index, newName);
    }

    // REQUIRES: int index and double newAmount for entry
    // MODIFIES: expenses
    // EFFECTS: changes entry amount of expenses to new amount
    public void modifyExpensesEntryAmount(int index, double newAmount) {
        expenses.modifyEntryAmount(index, newAmount);
    }

    // REQUIRES: int index and double newAmount for entry
    // MODIFIES: expenses
    // EFFECTS: changes entry description of expenses to new description
    public void modifyExpensesEntryName(int index, String newName) {
        expenses.modifyEntryDescription(index, newName);
    }

    // REQUIRES: Month for which netBudget is calculated for
    // EFFECTS: returns the net budget for the month
    // by subtracting total budget by total expenses
    public double netmonthlyBudget() {
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

