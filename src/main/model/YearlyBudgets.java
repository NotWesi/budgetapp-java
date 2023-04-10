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
        // logs the event that yearly budgets have been returned
        EventLog.getInstance().logEvent(new Event("Yearly budgets have been returned."));
        return yearlyBudgets;
    }

    // EFFECTS: adds a yearlyBudget to yearlyBudgets
    public void addYearlyBudget(YearlyBudget yb) {
        yearlyBudgets.add(yb);
        // logs the event that yearly budget has been added
        EventLog.getInstance().logEvent(new Event("Yearly budget has been added."));
    }

    // MODIFIES: yearlyBudgets
    // EFFECTS: sets the year info for each month
    public void setYear(YearlyBudget yearInfo) {
        yearlyBudgets.add(yearInfo);
        // logs the event that yearly budget info has been set
        EventLog.getInstance().logEvent(new Event("Yearly budget information has been set."));
    }

    // REQUIRES: int year for YearlyBudget
    // EFFECTS: returns the yearlyBudget object
    public YearlyBudget getYear(int year) {
        YearlyBudget yb = yearlyBudgets.get(year);
        if (yb == null) {
            // logs the event that yearly budget does not exist
            EventLog.getInstance().logEvent(new Event("Yearly budget does not exist."));
            return null;
        } else {
            // logs the event that yearly budget exists
            EventLog.getInstance().logEvent(new Event("Yearly budget exists."));
            return yb;
        }
    }

    // EFFECTS: returns length of the list of yearly objects
    public int getSize() {
        // logs the event that yearly budgets size has been returned
        EventLog.getInstance().logEvent(new Event("Yearly budgets size has been returned."));
        return yearlyBudgets.size();
    }

    // REQUIRES: int i for index of the YearlyBudget
    // EFFECTS: return the yearlyBudget at an index
    public YearlyBudget getYearlyBudget(int i) {
        YearlyBudget yb = yearlyBudgets.get(i);
        if (yb == null) {
            // logs the event that yearly budget does not exist
            EventLog.getInstance().logEvent(new Event("Yearly budget does not exist."));
            return null;
        } else {
            // logs the event that yearly budget exists
            EventLog.getInstance().logEvent(new Event("Yearly budget exists."));
            return yb;
        }
    }
}
