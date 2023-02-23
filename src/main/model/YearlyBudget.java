package model;

import java.util.ArrayList;

public class YearlyBudget {
    private ArrayList<Month> months;

    public YearlyBudget() {
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
}
