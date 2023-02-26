package model;

import java.util.ArrayList;
import java.util.Objects;

public class YearlyBudget {
    private int year;
    private ArrayList<Month> months;

    public YearlyBudget(int year) {
        this.year = year;
        months = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            months.add(new Month());
        }
    }


    // MODIFIES: months
    // EFFECTS: sets the month info for each month
    public void setMonth(int month, Month monthInfo) {
        months.set(month - 1, monthInfo);
    }

    // EFFECTS: returns the month object
    public Month getMonth(int month) {
        return months.get(month - 1);
    }

    // EFFECTS: calculates the nud budget from total budget and expenses
    public double getNetBudget() {
        double total = 0.0;

        //  Go through each month
        // Adds the net budget for each month to total
        for (Month month : months) {
            total += month.netmonthlyBudget(month);
        }
        // Calculate the yearly net budget
        return total;
    }

    // EFFECTS: return the int year for the object
    public int getYearInt() {
        return this.year;
    }



}
