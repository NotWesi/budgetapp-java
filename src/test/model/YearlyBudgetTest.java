package model;

import model.Budget;
import model.Entry;
import model.Expenses;
import model.Month;
import model.YearlyBudget;
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

    @BeforeEach
    void runbefore() {
        budget = new Budget();
        expenses = new Expenses();
        entryOne = new Entry("Salary", 2000.75);
        entryTwo = new Entry("Rent", 1200.0);
        january = new Month();
        year2021 = new YearlyBudget();
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
        assertEquals(entryOne.getName(), budget.getEntry(1).getName());
    }

    @Test
    public void testExpensesaddEntry() {
        // add entries to expenses object
        expenses.addEntry(new Entry("Rent", 1200.0));

        //testing add entry
        assertEquals(entryTwo.getName(), expenses.getEntry(0).getName());
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
    public void testSetMonth() {
        // add some entries to the month january
        january.getBudget().addEntry(new Entry("Refund", 175.0));
        january.getBudget().addEntry(entryOne);
        january.getExpenses().addEntry(entryTwo);

        // set the month in the yearly budget
        year2021.setMonth(1, january);

        // testing set month method
        assertEquals(january.getBudget().getEntry(1), entryOne);
        assertEquals(january.getExpenses().getEntry(0), entryTwo);
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
        assertEquals(2000.75, januaryInfo.getBudget().getEntry(1).getAmount(), 0.001);
        Month februaryInfo = year2021.getMonth(2);
        assertEquals(2300.75, februaryInfo.getBudget().getEntry(1).getAmount(), 0.001);

        // modify the amount of an entry in the budget for january
        januaryInfo.modifyBudgetEntryAmount(0,250.0);
        assertEquals(250.0, januaryInfo.getBudget().getEntry(0).getAmount(), 0.001);

        // modify the amount of an entry in the expenses for february
        februaryInfo.modifyExpensesEntryAmount(0,300.0);
        assertEquals(300.0, februaryInfo.getExpenses().getEntry(0).getAmount(), 0.001);

        // modify the description of an entry in the budget for january
        januaryInfo.modifyBudgetEntryName(0, "Tax Return");
        assertEquals("Tax Return", januaryInfo.getBudget().getEntry(0).getName());

        // modify the description of an entry in the expenses for february
        februaryInfo.modifyExpensesEntryName(0, "Phone Purchase");
        assertEquals("Phone Purchase", februaryInfo.getExpenses().getEntry(0).getName());
    }


}