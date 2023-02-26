package model;

import java.util.ArrayList;

public class YearlyBudgets {
    private ArrayList<YearlyBudget> yearlyBudgets;

    public YearlyBudgets() {
        this.yearlyBudgets = new ArrayList<>();
    }

    // returns list of yearly budgets
    public ArrayList<YearlyBudget> getYearlyBudgets() {
        return yearlyBudgets;
    }

    // MODIFIES: yearlyBudgets
    // EFFECTS: sets the year info for each month
    public void setYear(YearlyBudget yearInfo) {
        yearlyBudgets.add(yearInfo);
    }

    // EFFECTS: returns the yearlyBudget object
    public YearlyBudget getYear(int year) {
        return yearlyBudgets.get(year);
    }

    // EFFECTS: returns length of the list of yearly objects
    public int getSize() {
        return yearlyBudgets.size();
    }

    // EFFECTS: return the yearlyBudget at an index
    public YearlyBudget getYearlyBudget(int i) {
        return yearlyBudgets.get(i);
    }

}