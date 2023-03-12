package model;

import java.util.ArrayList;

// represents an array list of yearlyBudget objects
public class YearlyBudgets {
    private ArrayList<YearlyBudget> yearlyBudgets;

    public YearlyBudgets() {
        this.yearlyBudgets = new ArrayList<>();
    }

    // EFFECTS: returns list of yearly budgets
    public ArrayList<YearlyBudget> getYearlyBudgets() {
        return yearlyBudgets;
    }

    // EFFECTS: adds a yearlyBudget to yearlyBudgets
    public void addYearlyBudget(YearlyBudget yb) {
        yearlyBudgets.add(yb);
    }

    // MODIFIES: yearlyBudgets
    // EFFECTS: sets the year info for each month
    public void setYear(YearlyBudget yearInfo) {
        yearlyBudgets.add(yearInfo);
    }

    // REQUIRES: int year for YearlyBudget
    // EFFECTS: returns the yearlyBudget object
    public YearlyBudget getYear(int year) {
        return yearlyBudgets.get(year);
    }

    // EFFECTS: returns length of the list of yearly objects
    public int getSize() {
        return yearlyBudgets.size();
    }

    // REQUIRES: int i for index of the YearlyBudget
    // EFFECTS: return the yearlyBudget at an index
    public YearlyBudget getYearlyBudget(int i) {
        return yearlyBudgets.get(i);
    }

}
