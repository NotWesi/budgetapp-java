package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    // below are relevant objects for the following tests
    public Budget budget;
    public Expenses expenses;
    public ArrayList<Entry> entries;
    public Entry entryOne;
    public Entry entryTwo;
    public Month january;
    public YearlyBudget year2021;
    public YearlyBudget year2022;
    public YearlyBudgets yearlyBudgets;
    public int year;

    @BeforeEach
    void runBefore() {
        budget = new Budget();
        expenses = new Expenses();
        entryOne = new Entry("Salary", 2000.75);
        entryTwo = new Entry("Rent", 1200.0);
        january = new Month();
        year = 2021;
        year2021 = new YearlyBudget(year);
        year2022 = new YearlyBudget(2022);
        yearlyBudgets = new YearlyBudgets();

        // add some entries to the month january
        january.getBudget().addEntry(new Entry("Refund", 175.0));
        january.getBudget().addEntry(new Entry("Salary", 2000.75));
        january.getExpenses().addEntry(new Entry("Rent", 1200.0));

        // set the month in the yearly budget
        year2021.setMonth(1, january);
        year2022.setMonth(1, january);

        // adds the yearlyBudget to yearlyBudgets
        yearlyBudgets.addYearlyBudget(year2021);
        yearlyBudgets.addYearlyBudget(year2022);
    }

    @Test
    void testReaderNoFile() {
        // initializes a file that doesn't exist
        JsonReader reader = new JsonReader("noSuchFile");
        // asserts if loadData returns null if file doesn't exist
        assertEquals(null, reader.loadData("noSuchFile"));
    }

    @Test
    void testSourceGetter() {
        // initializes a file that doesn't exist
        JsonReader reader = new JsonReader("noSuchFile");
        // asserts if source is being retrieved as expected
        assertEquals("noSuchFile", reader.returnSource());
    }

    @Test
    void testSaveYearlyBudgets() {
        // saves the yearlyBudgets to the desired destination
        String saveFileName = "TestData";
        JsonWriter testJsonWriter = new JsonWriter(saveFileName);
        JSONObject testJsonData = testJsonWriter.saveYearlyBudgets(yearlyBudgets);
        // asserts if success message is returned from saveData method
        assertEquals("Yearly budgets data saved successfully", testJsonWriter.saveData(testJsonData));

    }

    @Test
    void testLoadYearlyBudgets() {
        // loads the JSONData into the loadedYearlyBudgets object
        String loadFileName = "TestData";
        JsonReader testJsonReader = new JsonReader(loadFileName);
        JSONObject testJsonData2 = testJsonReader.loadData(loadFileName);
        // asserts if testJsonData2 is not null
        assertTrue(testJsonData2 != null);
        // loads the data back into a yearlyBudgets array
        YearlyBudgets loadedYearlyBudgets = testJsonReader.loadYearlyBudgets(testJsonData2);
        // checks the length of the loadedYearlyBudgets and yearlyBudgets array
        assertEquals(yearlyBudgets.getSize(), loadedYearlyBudgets.getSize());
        // checks if the names match the expected and actual for different entries
        String entry1 = yearlyBudgets.getYearlyBudget(0).getMonth(1).getBudget().getSpecificEntry(0).getName();
        String loadedEntry1 = loadedYearlyBudgets.getYearlyBudget(0).getMonth(1).getBudget().getSpecificEntry(0).getName();
        assertEquals(entry1, loadedEntry1);
        // checks if the amounts match the expected and actual for a different entry
        Double entry2 = yearlyBudgets.getYearlyBudget(0).getMonth(1).getBudget().getSpecificEntry(1).getAmount();
        Double loadedEntry2 = loadedYearlyBudgets.getYearlyBudget(0).getMonth(1).getBudget().getSpecificEntry(1).getAmount();
        assertEquals(entry2, loadedEntry2);
    }



}
