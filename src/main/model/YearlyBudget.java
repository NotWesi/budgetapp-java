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
        double totalBudget = 0.0;
        double totalExpenses = 0.0;

        //  Go through each month
        for (Month month : months) {
            ArrayList<Entry> budgets = month.getBudget().getEntries();
            ArrayList<Entry> expenses = month.getExpenses().getEntries();

            // Sum the budget and expenses for the month
            for (Entry budget : budgets) {
                totalBudget += budget.getAmount();
            }
            for (Entry expense : expenses) {
                totalExpenses += expense.getAmount();
            }
        }

        // Calculate the net budget
        double netBudget = totalBudget - totalExpenses;
        return netBudget;
    }

}
