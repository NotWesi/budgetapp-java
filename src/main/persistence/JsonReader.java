package persistence;

import model.*;
import org.json.*;

import java.io.*;

// represents a reader that reads the string data from data files stored in
// the data directory
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: returns the source of the file
    public String returnSource() {
        return source;
    }

    // EFFECTS: converts the loaded data from JSONReader into a yearlyBudgets object
    @SuppressWarnings("methodlength")
    public static YearlyBudgets loadYearlyBudgets(JSONObject jsonYearlyBudgets) {
        YearlyBudgets yearlyBudgets = new YearlyBudgets();

        // gets the jsonYearlyBudget Array from jsonYearlyBudgets
        JSONArray jsonYearlyBudgetArray = jsonYearlyBudgets.getJSONArray("yearlyBudgets");

        // loops through the yearlyBudgets array to get
        for (int i = 0; i < jsonYearlyBudgetArray.length(); i++) {
            JSONObject jsonYearlyBudget = jsonYearlyBudgetArray.getJSONObject(i);

            int year = jsonYearlyBudget.getInt("year");
            JSONArray jsonMonthsArray = jsonYearlyBudget.getJSONArray("months");

            // creates a new yearlyBudget object with the relevant year
            YearlyBudget yearlyBudget = new YearlyBudget(year);

            // loops through the months array and retrieves each
            for (int j = 0; j < jsonMonthsArray.length(); j++) {
                Month month = new Month();
                JSONObject jsonMonth = jsonMonthsArray.getJSONObject(j);
                JSONArray jsonBudgetArray = jsonMonth.getJSONArray("Budget");
                JSONArray jsonExpensesArray = jsonMonth.getJSONArray("Expenses");


                // loops through the budgets array and adds each entry to the budgets array
                // of each month
                for (int k = 0; k < jsonBudgetArray.length(); k++) {
                    JSONObject jsonBudget = jsonBudgetArray.getJSONObject(k);
                    String name = jsonBudget.getString("name");
                    Double amount = jsonBudget.getDouble("amount");
                    month.getBudget().addEntry(new Entry(name, amount));
                }

                // loops through the expenses array and adds each entry to the expenses array
                // of each month
                for (int m = 0; m < jsonExpensesArray.length(); m++) {
                    JSONObject jsonExpenses = jsonExpensesArray.getJSONObject(m);
                    String name = jsonExpenses.getString("name");
                    Double amount = jsonExpenses.getDouble("amount");
                    month.getExpenses().addEntry(new Entry(name, amount));
                }

                // adds the month info to its respective month
                yearlyBudget.setMonth(j + 1, month);
            }
            // adds the yearlyBudget to the list of yearlyBudgets
            yearlyBudgets.addYearlyBudget(yearlyBudget);
        }
        // returns the yearlyBudgets list
        return yearlyBudgets;
    }

    // REQUIRES: source to exist in data directory
    // EFFECTS: loads the file from the data directory and converts all the
    // text into a JSONObject with the same format as it was saved in
    public JSONObject loadData(String source) {
        // initializes a new empty string for jsonString
        String loadFileName = ("data/" + source + ".json");
        // initialize a StringBuilder object to copy text into JSONString
        // refer to string builder documentation https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html
        StringBuilder jsonString = new StringBuilder();

        // uses a buffered reader to read through the file
        // Refer to documentation: https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
        try {
            BufferedReader reader = new BufferedReader(new FileReader(loadFileName));
            String line = "";
            // runs a while loop until there are no more lines left to read
            // from the file
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            // returns the jsonData from the file
            JSONObject jsonData = new JSONObject(jsonString.toString());
            return jsonData;
            // catches an IOException if there is an error with the file
        } catch (IOException exception) {
            return null;
        }
    }
}
