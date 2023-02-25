package model;

import java.util.ArrayList;

public class YearlyBudgets {
    private ArrayList<YearlyBudget> yearlyBudgets;

    public YearlyBudgets() {
        yearlyBudgets = new ArrayList<>();
    }

    // returns list of yearly budgets
    public ArrayList<YearlyBudget> getYearlyBudgets() {
        return yearlyBudgets;
    }

    // MODIFIES: yearlyBudgets
    // EFFECTS: sets the month info for each month
    public void setYear(YearlyBudget yearInfo) {
        yearlyBudgets.add(yearInfo);
    }

    // EFFECTS: returns the month object
    public YearlyBudget getYear(int year) {
        return yearlyBudgets.get(year);
    }


    // EFFECTS: checks if year is present in yearlyBudget list
    public YearlyBudget checkYear(YearlyBudget yearlyBudget) {
        if (yearlyBudgets.contains(yearlyBudget)) {
            return yearlyBudget;
        }
        return null;
    }
}
