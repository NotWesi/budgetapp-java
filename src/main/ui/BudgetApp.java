package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class BudgetApp {
    // intializes a new scanner for user input and a yearBudget class
    Scanner scanner = new Scanner(System.in);
    YearlyBudgets yearlyBudgets = new YearlyBudgets();

    // EFFECTS: runs the budget application
    @SuppressWarnings("methodlength")
    public BudgetApp() {
        // main loop that runs the console program
        while (true) {
            System.out.println("Welcome to the budget tracking application!");
            System.out.println("Please select from the following options or exit the program");
            System.out.println("1. Add a new yearly budget");
            System.out.println("2. Add a budget or expense entry to a month");
            System.out.println("3. View the budget, expense or both entries for a month");
            System.out.println("4. View net budget for a month");
            System.out.println("5. Remove an entry for a month");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                // the option to create a new yearly budget
                case 1:
                    addYearlyBudget(scanner, yearlyBudgets);
                    break;

                // the option to add a new entry to a month of a year
                case 2:
                    // checks if there is a year budget already added
                    if (yearlyBudgets.getYearlyBudgets().size() == 0) {
                        System.out.println("Please add a year budget first!");
                        break;
                    }
                    // runs the addEntry method
                    addEntry(scanner, yearlyBudgets);
                    break;

                //  the option to view all budgets, expenses or both for a month of a year
                case 3:
                    // checks if there is a year budget already added
                    if (yearlyBudgets.getYearlyBudgets().size() == 0) {
                        System.out.println("Please add a year budget first!");
                        break;
                    }
                    viewEntries(scanner, yearlyBudgets);
                    break;

                // the option to view the net budget for a month or a year
                case 4:
                    // checks if there is a year budget already added
                    if (yearlyBudgets.getYearlyBudgets().size() == 0) {
                        System.out.println("Please add a year budget first!");
                        break;
                    }
                    viewNetBudget(scanner, yearlyBudgets);
                    break;

                // the option to remove an entry from the month
                case 5:
                    // checks if there is a year budget already added
                    if (yearlyBudgets.getYearlyBudgets().size() == 0) {
                        System.out.println("Please add a year budget first!");
                        break;
                    }
                    removeEntry(scanner, yearlyBudgets);
                    break;

                // the sequence for exiting the program
                case 6:
                    System.out.println("Exiting the program now...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("That choice is invalid. Please enter again.");
            }
        }
    }

    public static void addYearlyBudget(Scanner scanner, YearlyBudgets yearlyBudgets) {
        // asks the user to input a year for the budget
        System.out.println("Enter the year for the budget:");
        int year = scanner.nextInt();
        scanner.nextLine();

        // initializes a new yearlyBudget object and a list of yearlyBudgets
        YearlyBudget yearBudget = new YearlyBudget(year);

        // loops for each month of the year
        for (int i = 1; i <= 12; i++) {
            // asks the user for the monthly budget
            System.out.println("Enter the budget for month " + i + ":");
            double budgetAmount = scanner.nextDouble();
            scanner.nextLine();
            // initializes a new Month object and stores the monthly budget amount
            Month month = new Month();
            month.getBudget().addEntry(new Entry("Monthly Budget", budgetAmount));
            // sets the month data in the yearBudget to the corresponding month
            yearBudget.setMonth(i, month);

            // asks the user for the monthly expenses
            System.out.println("Enter the expenses for month " + i + ":");
            double expensesAmount = scanner.nextDouble();
            scanner.nextLine();
            // stores the monthly expenses amount
            month.getExpenses().addEntry(new Entry("Monthly expenses", expensesAmount));
            // sets the month data in the yearBudget to the corresponding month
            yearBudget.setMonth(i, month);
        }

        // adds the yearBudget object to the list of objects
        yearlyBudgets.setYear(yearBudget);
        System.out.println("Yearly budget added successfully!");
    }

    public static void addEntry(Scanner scanner, YearlyBudgets yearlyBudgets) {
        //asks the user what year does he want to add the entry for
        System.out.println("Which year would you like to add this entry for?");
        int selectedYear = scanner.nextInt();
        scanner.nextLine();
        // checks if the year budget exists
        YearlyBudget selectedYearBudget = findYearlyBudgetByYear(yearlyBudgets, selectedYear);
        if (selectedYearBudget == null) {
            System.out.println("Year budget not found!");
            return;
        }
        // asks the user what month they want to add the entry to
        System.out.println("Which month would you like to add this entry for?");
        int selectedMonth = scanner.nextInt();
        // asks user the description of the entry
        System.out.println("Please type the description you would like to add");
        String selectedName = scanner.next();
        // asks user the amount of the entry
        System.out.println("Please type the amount you would like to add");
        double selectedAmount = scanner.nextDouble();
        // creates an entry object with the selected name and amount
        Entry selectedEntry = new Entry(selectedName, selectedAmount);
        // asks user if it's a budget or expenses entry
        System.out.println("Is this a budget or expenses entry? Type 1 for budget or 2 for expenses ");
        int entryType = scanner.nextInt();
        // checks what entry type the user wants and adds it accordingly
        if (entryType == 1) {
            selectedYearBudget.getMonth(selectedMonth).getBudget().addEntry(selectedEntry);
        } else {
            selectedYearBudget.getMonth(selectedMonth).getExpenses().addEntry(selectedEntry);
        }
        System.out.println("Entry has been added successfully!");
    }

    public static void viewEntries(Scanner scanner, YearlyBudgets yearlyBudgets) {
        //asks the user what year does he want to add the entry for
        System.out.println("Which year would you like to view entries for?");
        int selectedYear = scanner.nextInt();
        scanner.nextLine();
        // checks if the year budget exists
        YearlyBudget selectedYearBudget = findYearlyBudgetByYear(yearlyBudgets, selectedYear);
        if (selectedYearBudget == null) {
            System.out.println("Year budget not found!");
            return;
        }
        // asks user which month they want to view entries for
        System.out.println("Which month would you like to view entries for?");
        int selectedMonth = scanner.nextInt();

        Month monthBudget = selectedYearBudget.getMonth(selectedMonth);
        // checks if there are entries for the specified month
        if (monthBudget == null) {
            System.out.println("No entries found for the specified month.");
            return;
        }
        // initializes an ArrayList of Entry objects for budgets or expenses
        System.out.println("Would you like to view budget or expenses? Type 1 for budget and 2 for expenses");
        int entryType = scanner.nextInt();

        ArrayList<Entry> entries;

        if (entryType == 1) {
            entries = monthBudget.getBudget().getEntries();
        } else {
            entries = monthBudget.getExpenses().getEntries();
        }

        if (entries.isEmpty()) {
            System.out.println("No entries found for the specified month.");
            return;
        }
        // prints out for entries in the month
        System.out.println("Entries for month " + selectedMonth + " " + selectedYear + ":");
        for (Entry entry : entries) {
            System.out.println("Description: " + entry.getName() + ", Amount: " + entry.getAmount());
        }
    }

    public static void viewNetBudget(Scanner scanner, YearlyBudgets yearlyBudgets) {
        System.out.println("Which year would you like to add this entry for?");
        int selectedYear = scanner.nextInt();
        scanner.nextLine();

        // checks if the year budget exists
        YearlyBudget selectedYearBudget = findYearlyBudgetByYear(yearlyBudgets, selectedYear);
        if (selectedYearBudget == null) {
            System.out.println("Year budget not found!");
            return;
        }

        // asks the user if they want to view net budget for a year or month
        System.out.println("Would you like to view net budget for a month or year? Type 1 for year or 2 for month ");
        int selectedOption = scanner.nextInt();

        double netBudget;
        // condition for year or month
        if (selectedOption == 1) {
            // calculates net budget for the year
            netBudget = selectedYearBudget.getNetBudget();
            // prints out net budget
            System.out.println("The net budget for the year " + selectedYear + " is " + netBudget);
            return;
        } else {
            System.out.println("Which month would you like to add this entry for?");
            int selectedMonth = scanner.nextInt();

            //calculates net budget for the month
            Month selectMonth = selectedYearBudget.getMonth(selectedMonth);
            netBudget = selectMonth.netmonthlyBudget(selectMonth);
            // prints out net budget
            System.out.println("The net budget for the month " + selectedMonth + " is " + netBudget);
        }
    }

    public static void removeEntry(Scanner scanner, YearlyBudgets yearlyBudgets) {
        System.out.println("Which year would you like to remove this entry for?");
        int selectedYear = scanner.nextInt();
        scanner.nextLine();

        // checks if the year budget exists
        YearlyBudget selectedYearBudget = findYearlyBudgetByYear(yearlyBudgets, selectedYear);
        if (selectedYearBudget == null) {
            System.out.println("Year budget not found!");
            return;
        }

        // asks user what month they want to remove an entry for
        System.out.println("Which month would you like to remove this entry for?");
        int selectedMonth = scanner.nextInt();

        System.out.println("Is this a budget or expenses entry? Type 1 for budget or 2 for expenses");
        int selectedOption = scanner.nextInt();

        // asks user the description of the entry they want to remove
        System.out.println("Please type the description for the entry");
        String selectedName = scanner.next();
        // asks user the amount of the entry
        System.out.println("Please type the amount for the entry");
        double selectedAmount = scanner.nextDouble();

        if (selectedOption == 1) {
            // loops through budget entries
            Budget budget = selectedYearBudget.getMonth(selectedMonth).getBudget();
            boolean budgetCheck = budget.checkEntry(selectedName, selectedAmount);
            // if entry exists, remove the entry
            if (budgetCheck) {
                selectedYearBudget.getMonth(selectedMonth).getBudget().removeEntry(selectedName, selectedAmount);
                System.out.println("The entry was removed! ");
            } else {
                // entry does not exist, error message and returns
                System.out.println("The entry does not exist! ");
            }
        } else {
            Expenses expenses = selectedYearBudget.getMonth(selectedMonth).getExpenses();
            boolean expensesCheck = expenses.checkEntry(selectedName, selectedAmount);
            if (expensesCheck) {
                selectedYearBudget.getMonth(selectedMonth).getExpenses().removeEntry(selectedName, selectedAmount);
                System.out.println("The entry was removed! ");
            } else {
                // entry does not exist, error message and returns
                System.out.println("The entry does not exist! ");
            }
        }
    }

    // REQUIRES: YearlyBudgets and int year
    // EFFECTS: checks if year is present in yearlyBudget list
    public static YearlyBudget findYearlyBudgetByYear(YearlyBudgets budgets, int year) {
        // loops according to the size of the budgets list
        for (int i = 0; i < budgets.getSize(); i++) {
            YearlyBudget budget = budgets.getYear(i);
            if (budget.getYearInt() == year) {
                return budget; // returns budget if it is present in list
            }
        }
        return null; // returns null if not found
    }
}



