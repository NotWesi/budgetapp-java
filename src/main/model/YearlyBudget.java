package model;

import java.util.ArrayList;

// represents a yearly budget for a specific year with a list of months
// each with their unique budget and expenses entries
public class YearlyBudget {
    private int year;
    private ArrayList<Month> months;

    // constructs the YearlyBudget object
    public YearlyBudget(int year) {
        this.year = year;
        // creates a list of Month objects for the year
        months = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            months.add(new Month());
        }
    }

    // REQUIRES: int month and Month monthInfo
    // MODIFIES: months
    // EFFECTS: sets the month info for each month
    public void setMonth(int month, Month monthInfo) {
        months.set(month - 1, monthInfo);
    }

    // EFFECTS: returns the month object
    public Month getMonth(int month) {
        return months.get(month - 1);
    }

    // EFFECTS: returns the list of months
    public ArrayList<Month> getMonths() {
        return months;
    }

    // EFFECTS: calculates the net budget from total budget and expenses
    public double getNetBudget() {
        // initializes a total as a double
        double total = 0.0;

        //  Go through each month
        // Adds the net budget for each month to total
        for (Month month : months) {
            total += month.netmonthlyBudget();
        }
        // Calculate the yearly net budget
        return total;
    }

    // EFFECTS: return the int year for the object
    public int getYearInt() {
        return this.year;
    }



}
