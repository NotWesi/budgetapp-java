package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class YearlyBudgetTest {
    // below are relevant objects for the following tests
    public Budget budget;
    public Expenses expenses;
    public ArrayList<Entry> entries;
    public Entry entryOne;
    public Entry entryTwo;
    public Month january;
    public YearlyBudget year2021;
    public YearlyBudgets yearlyBudgets;
    public int year;

    @BeforeEach
    void runbefore() {
        budget = new Budget();
        expenses = new Expenses();
        entryOne = new Entry("Salary", 2000.75);
        entryTwo = new Entry("Rent", 1200.0);
        january = new Month();
        year = 2021;
        year2021 = new YearlyBudget(year);
        yearlyBudgets = new YearlyBudgets();
    }

    @Test
    public void testEntryConstructor() {
        assertTrue(entryOne.getName().equals("Salary"));
        assertEquals(entryOne.getAmount(), 2000.75);
        assertTrue(entryTwo.getName().equals("Rent"));
        assertEquals(entryTwo.getAmount(), 1200.0);
    }

    @Test
    public void testBudgetaddEntry() {
        // add entries to budget object
        budget.addEntry(new Entry("Refund", 175.0));
        budget.addEntry(new Entry("Salary", 2000.75));

        //testing add entry
        assertEquals(entryOne.getName(), budget.getEntry("Salary", 2000.75).getName());
    }

    @Test
    public void testExpensesaddEntry() {
        // add entries to expenses object
        expenses.addEntry(new Entry("Rent", 1200.0));

        //testing add entry
        assertEquals(entryTwo.getName(), expenses.getEntry("Rent", 1200.0).getName());
    }

    @Test
    public void testgetandremoveEntry() {
        // add some entries to the month january
        january.getBudget().addEntry(new Entry("Refund", 175.0));
        january.getBudget().addEntry(new Entry("Salary", 2000.75));
        january.getExpenses().addEntry(new Entry("Rent", 1200.0));

        // set the month in the yearly budget
        year2021.setMonth(1, january);

        // assert if the get entry is functional
        assertEquals(entryOne.getName(), year2021.getMonth(1).getBudget().getEntry("Salary", 2000.75).getName());
        assertEquals(entryOne.getAmount(), year2021.getMonth(1).getBudget().getEntry("Salary", 2000.75).getAmount());
        assertEquals(null, year2021.getMonth(1).getBudget().getEntry("Salary", 3000));

        // assert if the remove entry is functional
        year2021.getMonth(1).getBudget().removeEntry("Salary", 2000.75);
        assertEquals(1, year2021.getMonth(1).getBudget().getEntries().size());
    }

    @Test
    public void testcheckEntry() {
        // add some entries to the month january
        budget.addEntry(new Entry("Refund", 175.0));

        // checks if the entry exists
        assertTrue(budget.checkEntry("Refund", 175.0));
        assertFalse(budget.checkEntry("Refund", 200.0));
    }

    @Test
    public void testGetEntryIndex() {
        // adds an entry to the budget object
        budget.addEntry(new Entry("Refund", 175.0));

        // gets the entry's index (one pass and fail case)
        assertEquals(0, budget.getEntryIndex("Refund", 175.0));
        assertEquals(-1, budget.getEntryIndex("Refund", 165.0));
    }

    @Test
    public void testmodifyEntry() {
        // adds an entry to the budget object
        budget.addEntry(new Entry("Refund", 175.0));

        // modifies the entry description
        budget.modifyEntryDescription(0, "Tax Refund");
        assertEquals("Tax Refund", budget.getEntry("Tax Refund", 175.0).getName());

        // modifies the entry amount
        budget.modifyEntryAmount(0, 250.0);
        assertEquals(250.0, budget.getEntry("Tax Refund", 250.0).getAmount());
    }

    @Test
    public void testgetEntries() {
        // add some entries to the month january
        january.getBudget().addEntry(new Entry("Refund", 175.0));
        january.getBudget().addEntry(new Entry("Salary", 2000.75));
        january.getExpenses().addEntry(new Entry("Rent", 1200.0));

        // set the month in the yearly budget
        year2021.setMonth(1, january);

        // assert if get entries is functional
        entries = year2021.getMonth(1).getBudget().getEntries();
        assertEquals(entries, year2021.getMonth(1).getBudget().getEntries());

    }

    @Test
    public void testgetTotalAmount() {
        // assert that the method works for an empty list for budget and expenses
        assertEquals(0, january.getBudget().getTotalAmount());
        assertEquals(0, january.getExpenses().getTotalAmount());

        // add some entries to the month january
        january.getBudget().addEntry(new Entry("Refund", 175.0));
        january.getBudget().addEntry(new Entry("Salary", 2000.75));
        january.getExpenses().addEntry(new Entry("Rent", 1200.0));

        // set the month in the yearly budget
        year2021.setMonth(1, january);

        // assert if total amount is functional for both expenses and budget
        assertEquals(2175.75, year2021.getMonth(1).getBudget().getTotalAmount(), 0.001);
        assertEquals(1200.0, year2021.getMonth(1).getExpenses().getTotalAmount(),0.001);

    }

    @Test
    public void testSetMonth() {
        // add some entries to the month january
        january.getBudget().addEntry(new Entry("Refund", 175.0));
        january.getBudget().addEntry(entryOne);
        january.getExpenses().addEntry(entryTwo);

        // set the month in the yearly budget
        year2021.setMonth(1, january);

        // testing set month method
        assertEquals(january.getBudget().getEntry("Salary", 2000.75), entryOne);
        assertEquals(january.getExpenses().getEntry("Rent", 1200.0), entryTwo);
    }

    @Test
    public void testgetBudgetandgetExpenses() {
        // add some entries to the month january
        january.getBudget().addEntry(new Entry("Refund", 175.0));
        january.getBudget().addEntry(new Entry("Salary", 2000.75));
        january.getExpenses().addEntry(new Entry("Rent", 1200.0));

        // tests get budget and expenses methods work properly (more specific with entry description)
        assertEquals("Salary", january.getBudget().getEntry("Salary", 2000.75).getName());
        assertEquals(1200.0, january.getExpenses().getEntry("Rent", 1200.0).getAmount());
    }
    @Test
    public void testGetMonth() {
        // add some entries to the month january
        january.getBudget().addEntry(new Entry("Refund", 175.0));
        january.getBudget().addEntry(new Entry("Salary", 2000.75));
        january.getExpenses().addEntry(new Entry("Rent", 1200.0));

        // set the month in the yearly budget
        year2021.setMonth(1, january);

        // Get the month from the yearly budget
        Month januaryInfo = year2021.getMonth(1);
        assertEquals(january, januaryInfo);
    }

    @Test
    public void testModifyEntryAmountandDescription() {
        // add some entries to the month january
        january.getBudget().addEntry(new Entry("Refund", 175.0));
        january.getBudget().addEntry(new Entry("Salary", 2000.75));
        january.getExpenses().addEntry(new Entry("Rent", 1200.0));

        // add some entries to the month february
        Month february = new Month();
        // add some entries to the month january
        february.getBudget().addEntry(new Entry("Phone Discount", 575.0));
        february.getBudget().addEntry(new Entry("Salary", 2300.75));
        february.getExpenses().addEntry(new Entry("Rent", 1200.0));

        // set the months in the yearly budget
        year2021.setMonth(1, january);
        year2021.setMonth(2, february);

        // check if the current amount matches the amount we added
        Month januaryInfo = year2021.getMonth(1);
        assertEquals(2000.75, januaryInfo.getBudget().getEntry("Salary", 2000.75).getAmount(), 0.001);
        Month februaryInfo = year2021.getMonth(2);
        assertEquals(2300.75, februaryInfo.getBudget().getEntry("Salary", 2300.75).getAmount(), 0.001);

        // modify the amount of an entry in the budget for january
        januaryInfo.modifyBudgetEntryAmount(0,250.0);
        assertEquals(250.0, januaryInfo.getBudget().getEntry("Refund", 250.0).getAmount(), 0.001);

        // modify the amount of an entry in the expenses for february
        februaryInfo.modifyExpensesEntryAmount(0,300.0);
            assertEquals(300.0, februaryInfo.getExpenses().getEntry("Rent", 300.0).getAmount(), 0.001);

        // modify the description of an entry in the budget for january
        januaryInfo.modifyBudgetEntryName(0, "Tax Return");
        assertEquals("Tax Return", januaryInfo.getBudget().getEntry("Tax Return", 250.0).getName());

        // modify the description of an entry in the expenses for february
        februaryInfo.modifyExpensesEntryName(0, "Phone Purchase");
        assertEquals("Phone Purchase", februaryInfo.getExpenses().getEntry("Phone Purchase", 300.0).getName());
    }

    @Test
    public void testNetBudget() {
        // add some entries to the month january
        january.getBudget().addEntry(new Entry("Refund", 175.0));
        january.getBudget().addEntry(new Entry("Salary", 2000.75));
        january.getExpenses().addEntry(new Entry("Rent", 1200.0));

        // add some entries to the month february
        Month february = new Month();
        // add some entries to the month january
        february.getBudget().addEntry(new Entry("Phone Discount", 575.0));
        february.getBudget().addEntry(new Entry("Salary", 2300.75));
        february.getExpenses().addEntry(new Entry("Rent", 1200.0));

        // set the months in the yearly budget
        year2021.setMonth(1, january);
        year2021.setMonth(2, february);

        // assert if the net budget is equal to expected value
        assertEquals(4301.5, year2021.getNetBudget());

    }

    @Test
    public void testYearlyBudgets() {
        // tests set year method for YearlyBudgets
        yearlyBudgets.setYear(year2021);

        // checks the length of the list if yearBudget was added
        assertEquals(1, yearlyBudgets.getSize());

        // tests of get yearlyBudget method works for YearlyBudgets
        assertEquals(year2021, yearlyBudgets.getYearlyBudget(0));

        YearlyBudget yearFound = new YearlyBudget(2021);
        // tests if get year method works for YearlyBudgets
        for (int i = 0; i < yearlyBudgets.getSize(); i++) {
            YearlyBudget budget = yearlyBudgets.getYear(i);
            if (budget.getYearInt() == year) {
                yearFound = budget;
            }
        }
        assertEquals(year2021, yearFound);

        assertEquals(1, yearlyBudgets.getYearlyBudgets().size());
    }


}