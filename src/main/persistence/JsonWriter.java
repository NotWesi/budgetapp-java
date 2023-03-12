package persistence;

import model.*;
import org.json.*;

import java.io.*;

// represents the jsonWriter class that will save data
//to the data directory using JSON
public class JsonWriter {
    private String destination;

    // EFFECTS: constructs reader to read from source file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    @SuppressWarnings("methodlength")
    // REQUIRES: yearlyBudgets != null
    // EFFECTS: saves data for the user's budget from the console application
    public static JSONObject saveYearlyBudgets(YearlyBudgets yearlyBudgets) {
        // initializes a new JSON object and months array
        JSONObject jsonYearlyBudgets = new JSONObject();
        JSONArray jsonYearlyBudgetArray = new JSONArray();

        // loops through the months array to add budget and expenses
        for (YearlyBudget yb: yearlyBudgets.getYearlyBudgets()) {
            JSONObject jsonYearlyBudget = new JSONObject();
            JSONArray jsonMonthsArray = new JSONArray();
            for (int i = 1; i <= 12; i++) {
                // initializes a new month object
                JSONObject jsonMonth = new JSONObject();
                JSONArray jsonBudgets = new JSONArray();
                JSONArray jsonExpenses = new JSONArray();
                // stores the number of the month in jsonMonth
                jsonMonth.put("month", Integer.toString(i));
                // for each budget entry, it stores the name and amount in jsonMonth
                for (Entry budget: yb.getMonth(i).getBudget().getEntries()) {
                    JSONObject jsonBudget = new JSONObject();
                    jsonBudget.put("name", budget.getName());
                    jsonBudget.put("amount", budget.getAmount());
                    jsonBudgets.put(jsonBudget);
                }
                // for each expenses entry, it stores the name and amount in jsonMonth
                for (Entry expenses: yb.getMonth(i).getExpenses().getEntries()) {
                    JSONObject jsonExpense = new JSONObject();
                    jsonExpense.put("name", expenses.getName());
                    jsonExpense.put("amount", expenses.getAmount());
                    jsonExpenses.put(jsonExpense);
                }
                // adds the jsonMonth object to the jsonMonthsArray
                jsonMonth.put("Expenses", jsonExpenses);
                jsonMonth.put("Budget", jsonBudgets);
                jsonMonthsArray.put(jsonMonth);
            }
            // adds the year and months for each yearlyBudget object
            jsonYearlyBudget.put("year", yb.getYearInt());
            jsonYearlyBudget.put("months", jsonMonthsArray);

            // adds the yearlyBudget object to the array of yearlyBudgets
            jsonYearlyBudgetArray.put(jsonYearlyBudget);
        }
        // stores the array of yearlyBudget array into the jsonYearlyBudgets object
        jsonYearlyBudgets.put("yearlyBudgets", jsonYearlyBudgetArray);

        // returns the jsonObject for yearlyBudgets
        return jsonYearlyBudgets;
    }

    // EFFECTS: saves the data from the JSONObject by writing it on a file
    // and saving it in the data direction
    public String saveData(JSONObject jsonObject) {
        File file = new File("data/" + destination + ".json");
        try {
            // create a new file-writer object
            FileWriter writer = new FileWriter(file);

            // Writes the JSON object into the file
            String jsonYBtoString = jsonObject.toString();
            writer.write(jsonYBtoString);

            // close the file-writer object
            writer.close();

            // Returns success message
            String success = ("Yearly budgets data saved successfully");
            return success;
        } catch (IOException exception) {
            // Prints error message if save is unsuccessful
            String error = ("Error writing file: " + exception.getMessage());
            return error;
        }
    }
}
