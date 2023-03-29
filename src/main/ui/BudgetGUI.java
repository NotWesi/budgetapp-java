package ui;

import model.*;
import org.json.JSONObject;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.PieStyler;
import org.knowm.xchart.style.Styler;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import org.knowm.xchart.*;
import org.knowm.xchart.PieChart;

public class BudgetGUI {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JTextArea display;
    private JButton continueButton;
    private JButton addYearlyBudgetButton;
    private JButton addEntryButton;
    private JButton modifyEntryButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton removeEntryButton;
    private JButton viewEntriesButton;
    private JButton viewEntriesTableButton;
    private JButton quitButton;
    YearlyBudgets yearlyBudgets;


    public BudgetGUI() {
        prepareGUI();
    }

    private void prepareGUI() {
        yearlyBudgets = new YearlyBudgets();

        // creates the title of the mainframe and dimensions
        mainFrame = new JFrame("Budget Tracker");
        mainFrame.setSize(500, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creates a blank white panel with an empty border for padding
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        // initializes a new display
        display = new JTextArea();
        display.setEditable(false);

        // creates a new JLabel with the image as its icon
        ImageIcon coinIcon = new ImageIcon("data/coin.png");
        Image coin = coinIcon.getImage();
        int width = 50; // new width
        int height = 50; // new height
        Image resizedCoin = coin.getScaledInstance(width, height, Image.SCALE_SMOOTH); // resize the image
        ImageIcon resizedCoinIcon = new ImageIcon(resizedCoin);

        // creates a welcome panel with respective elements
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        JLabel label = new JLabel("Welcome to the Budget Tracker v1.0", resizedCoinIcon, JLabel.CENTER);
        label.setFont(new Font("Sans Serif", Font.BOLD, 18));
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);
        welcomePanel.add(label, BorderLayout.CENTER);
        continueButton = new JButton("Continue");
        continueButton.addActionListener(e -> showMainPanel());
        // creates a panel and adds the button to it
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(continueButton);
        welcomePanel.add(buttonPanel, BorderLayout.SOUTH);

        // creates an add new yearly budget button to create a new yearly Budget
        addYearlyBudgetButton = new JButton("Add a New Yearly Budget");
        addYearlyBudgetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addYearlyBudgetButton.addActionListener(e -> addYearlyBudget());

        // creates an add entry button to add a specific entry
        addEntryButton = new JButton("Add Entry");
        addEntryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addEntryButton.addActionListener(e -> addEntry());

        // creates modify entry button to modify a specific entry
        modifyEntryButton = new JButton("Modify Entry");
        modifyEntryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        modifyEntryButton.addActionListener(e -> modifyEntry());

        // creates a remove entry button to remove a specific entry
        removeEntryButton = new JButton("Remove Entry");
        removeEntryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeEntryButton.addActionListener(e -> deleteEntry());

        // creates a view entries button to view the entries as a table
        viewEntriesTableButton = new JButton("View Entries");
        viewEntriesTableButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewEntriesTableButton.addActionListener(e -> viewEntriesTable());

        // creates a view entries button to view the entries as a pie chart
        viewEntriesButton = new JButton("Illustrate Entries");
        viewEntriesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewEntriesButton.addActionListener(e -> viewEntries());

        // creates a save progress button to save the users current progress
        saveButton = new JButton("Save progress");
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.addActionListener(e -> saveData());

        // creates a load progress button to load a users previous progress
        loadButton = new JButton("Load progress");
        loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadButton.addActionListener(e -> loadData());

        // allows the user to quit and exit the application
        quitButton = new JButton("Quit");
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.addActionListener(e -> quit());

        // adds the buttons to the frame
        mainPanel.add(addYearlyBudgetButton);
        mainPanel.add(addEntryButton);
        mainPanel.add(modifyEntryButton);
        mainPanel.add(removeEntryButton);
        mainPanel.add(viewEntriesTableButton);
        mainPanel.add(viewEntriesButton);
        mainPanel.add(saveButton);
        mainPanel.add(loadButton);
        mainPanel.add(quitButton);

        mainFrame.add(welcomePanel);
        mainFrame.setVisible(true);
    }

    // EFFECTS: switches from welcome panel to main panel
    private void showMainPanel() {
        // Replaces welcome panel with the main panel
        mainFrame.getContentPane().removeAll();
        mainFrame.add(mainPanel);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    // EFFECTS: adds a new yearly budget for the year
    private void addYearlyBudget() {
        // prompts user for budget year
        int year = Integer.parseInt(JOptionPane.showInputDialog("Enter the year: "));

        // creates a new yearly budget with the respective year
        YearlyBudget yearlyBudget = new YearlyBudget(year);

        // adds the yearBudget object to the list of objects
        yearlyBudgets.setYear(yearlyBudget);

        // update the display
        updateDisplay("Yearly Budget has been added successfully!");
    }

    // add an entry to a specific month
    public void addEntry() {
        // Create a new JFrame for the add entry window
        JFrame addEntryFrame = new JFrame("Add Entry");
        addEntryFrame.setSize(400, 300);
        addEntryFrame.setLocationRelativeTo(null); // Center the window

        // Create a panel for the add entry form
        JPanel addEntryPanel = new JPanel(new GridLayout(6, 2));

        // Create the input fields
        JTextField yearField = new JTextField();
        JTextField monthField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField budgetOrExpenseField = new JTextField();

        addEntryMenu(addEntryPanel, yearField, monthField, descriptionField, amountField, budgetOrExpenseField);

        // create a button to add the entry
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addEntryHelper(addEntryPanel, yearField, monthField, descriptionField, amountField, budgetOrExpenseField));

        // adds button to panel
        addEntryPanel.add(addButton);

        // Add the panel to the frame and show the window
        addEntryFrame.add(addEntryPanel);
        addEntryFrame.setVisible(true);
    }

    // EFFECTS: creates the menu with fields to enter year, month, description
    // amount and type of entry
    private void addEntryMenu(JPanel addEntryPanel, JTextField yearField, JTextField monthField, JTextField descriptionField, JTextField amountField, JTextField budgetOrExpenseField) {
        // Add the input fields to the panel
        JLabel yearLabel = new JLabel("Year: ");
        yearLabel.setHorizontalAlignment(JLabel.CENTER);
        addEntryPanel.add(yearLabel);
        addEntryPanel.add(yearField);
        JLabel monthLabel = new JLabel("Month (1-12): ");
        monthLabel.setHorizontalAlignment(JLabel.CENTER);
        addEntryPanel.add(monthLabel);
        addEntryPanel.add(monthField);
        JLabel descriptionLabel = new JLabel("Enter the description: ");
        descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
        addEntryPanel.add(descriptionLabel);
        addEntryPanel.add(descriptionField);
        JLabel amountLabel = new JLabel("Enter the amount: ");
        amountLabel.setHorizontalAlignment(JLabel.CENTER);
        addEntryPanel.add(amountLabel);
        addEntryPanel.add(amountField);
        JLabel typeLabel = new JLabel("Type 1 (budget) or 2 (expense): ");
        typeLabel.setHorizontalAlignment(JLabel.CENTER);
        addEntryPanel.add(typeLabel);
        addEntryPanel.add(budgetOrExpenseField);
    }


    // EFFECTS: adds the entry to the specific month of the year selected
    private void addEntryHelper(JPanel addEntryPanel, JTextField yearField, JTextField monthField, JTextField descriptionField, JTextField amountField, JTextField budgetOrExpenseField) {
        // converts the relevant input values into their respective types
        int year = Integer.parseInt(yearField.getText());
        int month = Integer.parseInt(monthField.getText());
        YearlyBudget selectedYearBudget = findYearlyBudgetByYear(yearlyBudgets, year);
        if (selectedYearBudget == null) {
            JOptionPane.showMessageDialog(mainPanel, "This yearly budget does not exist. Please create a yearly budget first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Budget budget = selectedYearBudget.getMonth(month).getBudget();
        Expenses expenses = selectedYearBudget.getMonth(month).getExpenses();
        Entry entry = new Entry(descriptionField.getText(), Double.parseDouble(amountField.getText()));
        int type = Integer.parseInt(budgetOrExpenseField.getText());

        // add entry to appropriate list
        if (type == 1) {
            budget.addEntry(entry);
            // update display
            updateDisplay("Your entry had been added successfully");
        } else if (type == 2) {
            expenses.addEntry(entry);
            // update display
            updateDisplay("Your entry had been added successfully");
        } else {
            updateDisplay("This method is not working properly!");
        }
    }


    // EFFECTS: modifies a specific entry of a month of a year
    private void modifyEntry() {
        // Create a new JFrame for the add entry window
        JFrame modifyEntryFrame = new JFrame("Add Entry");
        modifyEntryFrame.setSize(400, 300);
        modifyEntryFrame.setLocationRelativeTo(null); // Center the window

        // Create a panel for the add entry form
        JPanel modifyEntryPanel = new JPanel(new GridLayout(8, 2));

        // Create the input fields
        JTextField yearField = new JTextField();
        JTextField monthField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField newDescriptionField = new JTextField();
        JTextField newAmountField = new JTextField();
        JTextField budgetOrExpenseField = new JTextField();

        // creates a panel menu to type the details for the entry
        modifyEntryMenu(modifyEntryPanel, yearField, monthField, descriptionField,
                amountField, newDescriptionField, newAmountField, budgetOrExpenseField);

        // creates the modify button
        JButton modifyButton = new JButton("Modify");
        modifyButton.addActionListener(e -> modifyEntryHelper(modifyEntryPanel, yearField, monthField, descriptionField, amountField,
                newDescriptionField, newAmountField, budgetOrExpenseField));

        // adds button to panel
        modifyEntryPanel.add(modifyButton);

        // Add the panel to the frame and show the window
        modifyEntryFrame.add(modifyEntryPanel);
        modifyEntryFrame.setVisible(true);

    }

    // EFFECTS: creates the menu with fields to enter year, month, description
    // amount and type of entry
    private void modifyEntryMenu(JPanel modifyEntryPanel, JTextField yearField, JTextField monthField, JTextField descriptionField, JTextField amountField,
                                 JTextField newDescriptionField, JTextField newAmountField, JTextField budgetOrExpenseField) {
        // Add the input fields to the panel
        JLabel yearLabel = new JLabel("Year: ");
        yearLabel.setHorizontalAlignment(JLabel.CENTER);
        modifyEntryPanel.add(yearLabel);
        modifyEntryPanel.add(yearField);
        JLabel monthLabel = new JLabel("Month (1-12): ");
        monthLabel.setHorizontalAlignment(JLabel.CENTER);
        modifyEntryPanel.add(monthLabel);
        modifyEntryPanel.add(monthField);
        JLabel descriptionLabel = new JLabel("Enter the description: ");
        descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
        modifyEntryPanel.add(descriptionLabel);
        modifyEntryPanel.add(descriptionField);
        JLabel amountLabel = new JLabel("Enter the amount: ");
        amountLabel.setHorizontalAlignment(JLabel.CENTER);
        modifyEntryPanel.add(amountLabel);
        modifyEntryPanel.add(amountField);
        JLabel newDescriptionLabel = new JLabel("Enter the new description: ");
        newDescriptionLabel.setHorizontalAlignment(JLabel.CENTER);
        modifyEntryPanel.add(newDescriptionLabel);
        modifyEntryPanel.add(newDescriptionField);
        JLabel newAmountLabel = new JLabel("Enter the new amount: ");
        newAmountLabel.setHorizontalAlignment(JLabel.CENTER);
        modifyEntryPanel.add(newAmountLabel);
        modifyEntryPanel.add(newAmountField);
        JLabel typeLabel = new JLabel("Type 1 (budget) or 2 (expense): ");
        typeLabel.setHorizontalAlignment(JLabel.CENTER);
        modifyEntryPanel.add(typeLabel);
        modifyEntryPanel.add(budgetOrExpenseField);
    }

    // EFFECTS: modifies the entry to the specific month of the year selected
    // converts the relevant input values into their respective types
    private void modifyEntryHelper(JPanel modifyEntryPanel, JTextField yearField, JTextField monthField, JTextField descriptionField, JTextField amountField,
                                   JTextField newDescriptionField, JTextField newAmountField, JTextField budgetOrExpenseField) {
        // converts the fields into their respective strings and doubles
        int year = Integer.parseInt(yearField.getText());
        int month = Integer.parseInt(monthField.getText());
        String description = descriptionField.getText();
        Double amount = Double.parseDouble(amountField.getText());
        String newDescription = newDescriptionField.getText();
        Double newAmount = Double.parseDouble(newAmountField.getText());
        // find the yearly budget depending on the year
        YearlyBudget selectedYearBudget = findYearlyBudgetByYear(yearlyBudgets, year);
        // checks if the yearly budget exists
        if (selectedYearBudget == null) {
            JOptionPane.showMessageDialog(mainPanel, "This yearly budget does not exist. Please create a yearly budget first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Budget budget = selectedYearBudget.getMonth(month).getBudget();
        Expenses expenses = selectedYearBudget.getMonth(month).getExpenses();
        int type = Integer.parseInt(budgetOrExpenseField.getText());

        // checks if the entry can be modified
        if (type == 1) {
            // checks if the budget entries exists
            boolean budgetCheck = budget.checkEntry(description, amount);
            // if entry exists, modify the entry in budget
            if (budgetCheck) {
                int i = budget.getEntryIndex(description, amount);
                budget.modifyEntryAmount(i, newAmount);
                budget.modifyEntryDescription(i, newDescription);
                updateDisplay("The entry was edited! ");
            } else {
                // entry does not exist, error message and returns
                updateDisplay("The entry does not exist! ");
            }
        } else {
            // checks if the expenses entry exists
            boolean expensesCheck = expenses.checkEntry(description, amount);
            // if entry exists, modify the entry in expenses
            if (expensesCheck) {
                int i = expenses.getEntryIndex(description, amount);
                expenses.modifyEntryAmount(i, newAmount);
                expenses.modifyEntryDescription(i, newDescription);
                updateDisplay("The entry was edited! ");
            } else {
                // entry does not exist, error message and returns
                updateDisplay("The entry does not exist! ");
            }
        }
    }

    // EFFECTS: removes an entry from a specific month
    private void deleteEntry() {
        // Create a new JFrame for delete entry window
        JFrame deleteEntryFrame = new JFrame("Delete Entry");
        deleteEntryFrame.setSize(400, 300);
        deleteEntryFrame.setLocationRelativeTo(null); // Center the window

        // Create a panel for the add entry form
        JPanel deleteEntryPanel = new JPanel(new GridLayout(6, 2));

        // Create the input fields
        JTextField yearField = new JTextField();
        JTextField monthField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField budgetOrExpenseField = new JTextField();

        deleteEntryMenu(deleteEntryPanel, yearField, monthField, descriptionField, amountField, budgetOrExpenseField);

        // create a button to add the entry
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteEntryHelper(deleteEntryPanel, yearField, monthField, descriptionField, amountField, budgetOrExpenseField));

        // adds button to panel
        deleteEntryPanel.add(deleteButton);

        // Add the panel to the frame and show the window
        deleteEntryFrame.add(deleteEntryPanel);
        deleteEntryFrame.setVisible(true);
    }

    // EFFECTS: creates a JFrame object that asks the user to input the respective values
    // for the entry they want to delete
    private void deleteEntryMenu(JPanel deleteEntryPanel, JTextField yearField, JTextField monthField,
                                 JTextField descriptionField, JTextField amountField, JTextField budgetOrExpenseField) {
        // calls the add entry menu method as the options for both these options are identical
        addEntryMenu(deleteEntryPanel, yearField, monthField, descriptionField, amountField, budgetOrExpenseField);
    }

    // EFFECTS: removes the specific entry from a month's budget or expenses depending
    // on the year
    private void deleteEntryHelper(JPanel deleteEntryPanel, JTextField yearField, JTextField monthField,
                                   JTextField descriptionField, JTextField amountField, JTextField budgetOrExpenseField) {
        // converts the relevant input values into their respective types
        int year = Integer.parseInt(yearField.getText());
        int month = Integer.parseInt(monthField.getText());
        YearlyBudget selectedYearBudget = findYearlyBudgetByYear(yearlyBudgets, year);
        if (selectedYearBudget == null) {
            JOptionPane.showMessageDialog(mainPanel, "This yearly budget does not exist. Please create a yearly budget first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Budget budget = selectedYearBudget.getMonth(month).getBudget();
        Expenses expenses = selectedYearBudget.getMonth(month).getExpenses();
        String selectedName = descriptionField.getText();
        Double selectedAmount = Double.parseDouble(amountField.getText());
        int type = Integer.parseInt(budgetOrExpenseField.getText());

        if (type == 1) {
            // checks if entry exists within budget
            boolean budgetCheck = budget.checkEntry(selectedName, selectedAmount);
            // if entry exists, remove the entry
            if (budgetCheck) {
                budget.removeEntry(selectedName, selectedAmount);
                updateDisplay("The entry was removed! ");
            } else {
                // entry does not exist, error message and returns
                updateDisplay("The entry does not exist! ");
            }
        } else {
            // checks if entry exists within expenses
            boolean expensesCheck = expenses.checkEntry(selectedName, selectedAmount);
            // if entry exists, remove the entry
            if (expensesCheck) {
                expenses.removeEntry(selectedName, selectedAmount);
                updateDisplay("The entry was removed! ");
            } else {
                // entry does not exist, error message and returns
                updateDisplay("The entry does not exist! ");
            }
        }
    }

    // EFFECTS: displays the user entries in the form of a table
    private void viewEntriesTable() {
        // initialize the variables
        final String[][] monthAndYear = new String[1][1];

        // creates the frame and title for view entries
        JFrame viewEntriesFrame = new JFrame(("View Entries"));
        viewEntriesFrame.setSize(400, 300);
        viewEntriesFrame.setLayout(new BorderLayout());

        // creates the panel
        JPanel viewEntriesPanel = new JPanel();
        viewEntriesPanel.setLayout(new BoxLayout(viewEntriesPanel, BoxLayout.Y_AXIS));

        // adds a message on the top asking user to select from the following options
        JLabel messageLabel = new JLabel("Please select a month and budget/expenses");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 12));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        messageLabel.setHorizontalAlignment(JLabel.CENTER);

        // this is implemented using the following documentation:
        // https://docs.oracle.com/javase/tutorial/uiswing/components/buttongroup.html
        ButtonGroup timePeriodGroup = new ButtonGroup();
        JRadioButton monthButton = new JRadioButton("Month");

        // adds the buttons to the button group
        timePeriodGroup.add(monthButton);

        // adds the action listeners for each button
        monthButton.addActionListener(e -> {
            // implemented using string arrays: https://www.w3schools.com/java/java_arrays.asp
            String[] monthAndYear1 = monthAndYearInput("Select a month and year");
            if (monthAndYear1.length != 0) {
                updateDisplay("Your input was successful!");
            }
            monthAndYear[0] = monthAndYear1;
        });


        // Follows same format for budget and expenses
        ButtonGroup typeEntryGroup = new ButtonGroup();
        JRadioButton budgetButton = new JRadioButton("Budget");
        JRadioButton expensesButton = new JRadioButton("Expenses");
        typeEntryGroup.add(budgetButton);
        typeEntryGroup.add(expensesButton);

        // adds the buttons for view entries
        JButton viewButton = new JButton("View Entries");
        viewButton.addActionListener(e -> {
            if (monthButton.isSelected() && budgetButton.isSelected()) {
                viewEntriesTableHelper(monthAndYear[0], 1); // creates a table of budget entries
            } else if (monthButton.isSelected() && expensesButton.isSelected()) {
                viewEntriesTableHelper(monthAndYear[0], 2); // creates a table of expenses entries
            }
        });

        // adds the buttons to the panel
        viewEntriesPanel.add(messageLabel);
        viewEntriesPanel.add(monthButton);
        viewEntriesPanel.add(budgetButton);
        viewEntriesPanel.add(expensesButton);
        viewEntriesPanel.add(viewButton);

        // adds the panel to the frame
        viewEntriesFrame.add(viewEntriesPanel);
        viewEntriesFrame.setVisible(true);
    }

    // EFFECTS: creates a table for the parameters passed and
    // displays the entries in a table
    private void viewEntriesTableHelper(String[] monthAndYear, int type) {
        // this table was modelled similarly to the documentation provided here
        // https://docs.oracle.com/javase/tutorial/uiswing/components/table.html

        // creates a new JFrame
        JFrame tableFrame = new JFrame("Entries Table");
        tableFrame.setSize(500, 500);
        // initializes the relevant variables from the string array
        int month = Integer.parseInt(monthAndYear[0]);
        int year = Integer.parseInt(monthAndYear[1]);

        // finds the selected month based on the variable passed
        YearlyBudget selectedYearBudget = findYearlyBudgetByYear(yearlyBudgets, year);
        Month selectedMonth = selectedYearBudget.getMonth(month);

        // adds column names
        String[] columnName = {"Description", "Amount"};
        Object[][] data;

        // creates a table with the data
        if (type == 1) {
            Budget budget = selectedMonth.getBudget();
            data = new Object[budget.getSize()][2];
            // loops through the entries and creates a table of each entry
            for (int i = 0; i < budget.getSize(); i++) {
                Entry entry = budget.getSpecificEntry(i);
                // adds the description and amount of each entry
                data[i][0] = entry.getName();
                data[i][1] = entry.getAmount();
            }
        } else {
            Expenses expenses = selectedMonth.getExpenses();
            data = new Object[expenses.getSize()][2];

            for (int j = 0; j < expenses.getSize(); j++) {
                Entry entry = expenses.getSpecificEntry(j);
                // adds the description and amount of each entry
                data[j][0] = entry.getName();
                data[j][1] = entry.getAmount();
            }
        }
        // creates a new table with data and column names
        JTable table = new JTable(data, columnName);

        // Creates a JScrollPane to add the JTable to
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the JScrollPane to JFrame
        tableFrame.add(scrollPane);

        // Set the size and visibility of the JFrame
        tableFrame.setVisible(true);

    }

    // EFFECTS: asks the user if they want to view the entries for a month or year
    // and if they want to view budget or expenses in the form of a pie chart
    private void viewEntries() {
        // initialize the variables
        final String[][] monthAndYear = new String[1][1];
        final String[] year = new String[1];

        // creates the frame and title for view entries
        JFrame viewEntriesFrame = new JFrame(("View Entries"));
        viewEntriesFrame.setSize(400, 300);
        viewEntriesFrame.setLayout(new BorderLayout());

        // creates the panel
        JPanel viewEntriesPanel = new JPanel();
        viewEntriesPanel.setLayout(new BoxLayout(viewEntriesPanel, BoxLayout.Y_AXIS));

        // adds a message on the top asking user to select from the following options
        JLabel messageLabel = new JLabel("Please select between year/month and budget/expenses");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 12));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        messageLabel.setHorizontalAlignment(JLabel.CENTER);

        // this is implemented using the following documentation:
        // https://docs.oracle.com/javase/tutorial/uiswing/components/buttongroup.html
        ButtonGroup timePeriodGroup = new ButtonGroup();
        JRadioButton monthButton = new JRadioButton("Month");
        JRadioButton yearButton = new JRadioButton("Year");

        // adds the buttons to the button group
        timePeriodGroup.add(monthButton);
        timePeriodGroup.add(yearButton);

        // adds the action listeners for each button
        monthButton.addActionListener(e -> {
            // implemented using string arrays: https://www.w3schools.com/java/java_arrays.asp
            String[] monthAndYear1 = monthAndYearInput("Select a month and year");
            if (monthAndYear1[0].length() > 0) {
                updateDisplay("The input is valid");
            }
            monthAndYear[0] = monthAndYear1;
        });

        // add the action listener for the year button
        yearButton.addActionListener(e -> {
            // implemented using string arrays: https://www.w3schools.com/java/java_arrays.asp
            year[0] = yearInput("Select a year");
            if (year[0] == "") {
                updateDisplay("Test: this is working");
            }
        });

        // Follows same format for budget and expenses
        ButtonGroup typeEntryGroup = new ButtonGroup();
        JRadioButton budgetButton = new JRadioButton("Budget");
        JRadioButton expensesButton = new JRadioButton("Expenses");
        typeEntryGroup.add(budgetButton);
        typeEntryGroup.add(expensesButton);

        // adds the buttons for view entries
        JButton viewButton = new JButton("View Entries");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (monthButton.isSelected() && budgetButton.isSelected()) {
                    viewEntriesMonthHelper(monthAndYear[0], 1); // retrieve selected
                    updateDisplay("Option 1");
                } else if (monthButton.isSelected() && expensesButton.isSelected()) {
                    viewEntriesMonthHelper(monthAndYear[0], 2);
                    updateDisplay("Option 2");
                } else if (yearButton.isSelected() && budgetButton.isSelected()) {
                    viewEntriesYearHelper(year[0], 1);
                    updateDisplay("Option 3");
                } else {
                    viewEntriesYearHelper(year[0], 2);
                    updateDisplay("Option 4");
                }
            }
        });

        // adds the buttons to the panel
        viewEntriesPanel.add(messageLabel);
        viewEntriesPanel.add(monthButton);
        viewEntriesPanel.add(yearButton);
        viewEntriesPanel.add(budgetButton);
        viewEntriesPanel.add(expensesButton);
        viewEntriesPanel.add(viewButton);

        // adds the panel to the frame
        viewEntriesFrame.add(viewEntriesPanel);
        viewEntriesFrame.setVisible(true);
    }


    // EFFECTS: returns a string array with the month and corresponding year
    private String[] monthAndYearInput(String title) {
        // initializes a strong with two characters
        String[] monthAndYear = new String[2];

        // creates a panel with dialog prompts
        JPanel panel = new JPanel();
        JLabel monthLabel = new JLabel("Month (1-12)");
        JTextField monthField = new JTextField(10);
        JLabel yearLabel = new JLabel("Year: ");
        JTextField yearField = new JTextField(10);

        // create panel for labels and text fields
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        optionsPanel.add(monthLabel);
        optionsPanel.add(monthField);
        optionsPanel.add(yearLabel);
        optionsPanel.add(yearField);

        // creates a layout for the panel
        panel.setLayout(new BorderLayout());
        panel.add(optionsPanel, BorderLayout.CENTER);

        String[] strings = monthAndYearHelper(title, monthAndYear, panel, monthField, yearField);
        if (strings != null) {
            return strings;
        }

        // returns the string array as empty if input is invalid
        return monthAndYear;
    }

    // EFFECTS: stores the month and year input depending on the values passed
    // checks if values match proper parameters and ranges
    private String[] monthAndYearHelper(String title, String[] monthAndYear, JPanel panel, JTextField monthField, JTextField yearField) {
        // checks if choice has been selected
        int choice = JOptionPane.showConfirmDialog(null, panel, title, JOptionPane.OK_CANCEL_OPTION);
        if (choice == JOptionPane.OK_OPTION) {
            // gets the year and month fields
            String month = monthField.getText();
            String year = yearField.getText();

            try {
                // coverts the month into an int value
                int monthVal = Integer.parseInt(month);
                int yearVal = Integer.parseInt(year);
                // checks if yearly budget for year exists
                if (findYearlyBudgetByYear(yearlyBudgets, yearVal) == null) {
                    JOptionPane.showMessageDialog(null, "That yearly budget does not exist");
                    return monthAndYearInput(title);
                }
                // checks if month is within range and asks user to try again
                // if invalid input
                if (monthVal < 1 || monthVal > 12) {
                    JOptionPane.showMessageDialog(null, "That month is invalid. Please try again.");
                    return monthAndYearInput(title);
                }
                // stores the month at the 0-index and year at the 1-index
                monthAndYear[0] = month;
                monthAndYear[1] = year;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "The input is invalid. Please try again.");
                return monthAndYearInput(title);
            }
        }
        return null;
    }

    // EFFECTS: stores the year input passed as a string and returns it
    private String yearInput(String title) {
        // initializes a strong with two characters
        String year = "";

        // creates a panel with dialog prompts
        JPanel panel = new JPanel();
        JLabel yearLabel = new JLabel("Year: ");
        JTextField yearField = new JTextField(10);

        // create panel for labels and text fields
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        optionsPanel.add(yearLabel);
        optionsPanel.add(yearField);

        // creates a layout for the panel
        panel.setLayout(new BorderLayout());
        panel.add(optionsPanel, BorderLayout.CENTER);

        int choice = JOptionPane.showConfirmDialog(null, panel, title, JOptionPane.OK_CANCEL_OPTION);
        if (choice == JOptionPane.OK_OPTION) {
            // gets the year and month fields
            String yearStr = yearField.getText();

            try {
                // converts the month into an int value
                int yearVal = Integer.parseInt(yearStr);
                // checks if yearly budget for year exists
                if (findYearlyBudgetByYear(yearlyBudgets, yearVal) == null) {
                    JOptionPane.showMessageDialog(null, "That yearly budget does not exist");
                    return yearInput(title);
                }
                year = yearStr;
                // catches a number format exception if user types non-numeric input
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "The input is invalid. Please try again.");
                return yearInput(title);
            }
        }
        return year;
    }


    private void viewEntriesMonthHelper(String[] monthAndYear, int type) {
        // initializes the relevant variables from the string array
        int month = Integer.parseInt(monthAndYear[0]);
        int year = Integer.parseInt(monthAndYear[1]);

        // creates the pie chart data depending on the type passed
        if (type == 1) {
            pieChartHelper(month, year, type);
        } else {
            pieChartHelper(month, year, type);
        }

    }

    private void pieChartHelper(int month, int year, int type) {
        // creates a JFrame with dimensions
        JFrame pieChartFrame = new JFrame("Pie Chart");
        pieChartFrame.setSize(400, 400);
        String title = "";
        // converts the monthly budget data into a HashMap
        HashMap<String, Double> monthData;
        // finds the selected month based on the variable passed
        YearlyBudget selectedYearBudget = findYearlyBudgetByYear(yearlyBudgets, year);
        Month selectedMonth = selectedYearBudget.getMonth(month);

        if (type == 1) {
            monthData = getMonthlyBudgetData(year, month);
            // data = selectedMonth.getBudget().getEntries();
            title = ("Monthly Budget For " + convertMonth(month));
        } else {
            // data = selectedMonth.getExpenses().getEntries();
            monthData = getMonthlyExpensesData(year, month);
            title = ("Monthly Expenses" + convertMonth(month));
        }

        // creates a pie chart from the data passed
        // Refer to the top answer of this stackoverflow page: https://stackoverflow.com/questions/13662984/creating-pie-charts-programmatically
        PieChart chart = new PieChartBuilder().width(800).height(600).title(title).theme(Styler.ChartTheme.GGPlot2).build();

        // Customize Chart
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setPlotContentSize(.7);
        chart.getStyler().setStartAngleInDegrees(90);
        chart.getStyler().setLegendPosition(PieStyler.LegendPosition.InsideNW);

        // Used https://sentry.io/answers/iterate-hashmap-java/#:~:text=Perhaps%20the%20most%20straightforward%20approach,or%20entries%20in%20the%20HashMap.
        // for reference
        monthData.forEach((key, value) -> {
            chart.addSeries(key, value);
        });
        // for (int i = 0; i < data.size(); i++) {
        //    chart.addSeries(data.get(i).getName(), data.get(i).getAmount());
        // }

        // Get total value
        double total = 0;
        for (double value : monthData.values()) {
            total += value;
         }

        // Create panel for chart and label
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add total label to panel
        JLabel totalLabel = new JLabel("Total: $" + total);
        totalLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create chart panel and add to frame
        XChartPanel chartPanel = new XChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.add(totalLabel, BorderLayout.SOUTH);
        pieChartFrame.add(panel, BorderLayout.CENTER);

        // Set frame properties
        pieChartFrame.setTitle("Info For Month");
        pieChartFrame.pack();
        pieChartFrame.setLocationRelativeTo(null);
        pieChartFrame.setVisible(true);
    }

    private void viewEntriesYearHelper(String year, int type) {
        // stub
    }

    // REQUIRES: budget have at least one entry
    // EFFECTS: creates a hashmap with a string key and double entry to pass data
    // for the pie chart
    private HashMap<String, Double> getMonthlyBudgetData(int year, int month) {
        // initializes a new hash map
        HashMap<String, Double> monthData = new HashMap<>();

        // finds the selected month based on the variable passed
        YearlyBudget selectedYearBudget = findYearlyBudgetByYear(yearlyBudgets, year);
        Month selectedMonth = selectedYearBudget.getMonth(month);

        // conditional statement that accounts for either budget or expenses
        Budget budget = selectedMonth.getBudget();
        for (int i = 0; i < budget.getSize(); i++) {
            String description = budget.getSpecificEntry(i).getName();
            Double amount = budget.getSpecificEntry(i).getAmount();
            // checks for duplicate entries (e.g: if user inputted Salary twice,
            // it will add that amount to that key
            if (monthData.containsKey(description)) {
                monthData.put(description, monthData.get(description) + amount);
            } else {
                monthData.put(description, amount);
            }
        }
        // returns the HashMap created with the monthly budget data
        return monthData;
    }

    // REQUIRES: expenses have at least one entry
    // EFFECTS: creates a hashmap with a string key and double entry to pass data
    // for the pie chart
    private HashMap<String, Double> getMonthlyExpensesData(int year, int month) {
        // initializes a new hash map
        HashMap<String, Double> monthData = new HashMap<>();

        // finds the selected month based on the variable passed
        YearlyBudget selectedYearBudget = findYearlyBudgetByYear(yearlyBudgets, year);
        Month selectedMonth = selectedYearBudget.getMonth(month);

        // conditional statement that accounts for either budget or expenses
        Expenses expenses = selectedMonth.getExpenses();
        for (int i = 0; i < expenses.getSize(); i++) {
            String description = expenses.getSpecificEntry(i).getName();
            Double amount = expenses.getSpecificEntry(i).getAmount();
            // checks for duplicate entries (e.g: if user inputted Rent twice,
            // it will add that amount to that key
            if (monthData.containsKey(description)) {
                monthData.put(description, monthData.get(description) + amount);
            } else {
                monthData.put(description, amount);
            }
        }
        // returns the HashMap created with the monthly budget data
        return monthData;
    }


    private void saveData() {
        // calls the save current method to save the progress as a JSONObject
        // checks if there is a year budget already added
        if (yearlyBudgets.getYearlyBudgets().size() == 0) {
            updateDisplay("Please add a year budget first!");
            return;
        }
        saveCurrentData();
        updateDisplay("The file has been saved successfully!");
    }

    // EFFECTS: saves the current data of the user int the data directory under
    // desired file name
    private String saveCurrentData() {
        // asks the user what filename they want to save their current progress
        String saveFilename = JOptionPane.showInputDialog("Enter the filename to save data:");
        // saves the user session into the data directory
        JsonWriter jsonWriter = new JsonWriter(saveFilename);
        JSONObject jsonData = JsonWriter.saveYearlyBudgets(yearlyBudgets);
        // returns the message after save is completed
        return jsonWriter.saveData(jsonData);
    }

    // EFFECTS: converts month int to the respective name
    private String convertMonth(int month) {
        String monthName;
        // loop that adds the appropriate month name and breaks
        switch (month) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
            default:
                monthName = "";
                break;
        }
        // returns month name
        return monthName;
    }

    private void loadData() {
        // checks if there is a year budget data to load
        YearlyBudgets loadedYearlyBudgets = loadPreviousData();

        if (loadedYearlyBudgets == null) {
            updateDisplay("That file does not exist!");
            return;
        } else {
            // sets yearlyBudgets equal to loaded data
            yearlyBudgets = loadedYearlyBudgets;
            updateDisplay("The file has been loaded successfully!");
        }
        return;
    }

    // EFFECTS: checks if the file entered exists; returns the loadedYearlyBudgets
    // if file exists; null otherwise
    private YearlyBudgets loadPreviousData() {
        String loadFilename = JOptionPane.showInputDialog("Enter the filename to load data:");
        // loads the file from the data directory
        JsonReader jsonReader = new JsonReader(loadFilename);
        JSONObject jsonYearlyBudgets = jsonReader.loadData(loadFilename);
        // if data does not exist, returns null
        if (jsonYearlyBudgets == null) {
            return null;
        } else {
            // returns the yearlyBudgets object that matches the saved data
            return jsonReader.loadYearlyBudgets(jsonYearlyBudgets);
        }
    }



    private void quit() {
        System.exit(0);
    }

    // EFFECTS: prints out the success message with an OK button to return to the main panel
    private void updateDisplay(String msg) {
        // creates a new JFrame to show the message
        JFrame msgFrame = new JFrame();
        msgFrame.setTitle("Success!");
        msgFrame.setSize(400,100);

        // creates a JPanel to hold the message and OK button
        JPanel msgPanel = new JPanel(new BorderLayout());

        // creates a JLabel with the message text
        JLabel msgLabel = new JLabel(msg);
        msgLabel.setHorizontalAlignment(JLabel.CENTER);

        // creates a button that says OK
        JButton okButton = new JButton("OK");

        // adds an action listener to the ok button to close the message
        okButton.addActionListener(e -> msgFrame.dispose());

        // adds the message label and ok button to the message panel
        msgPanel.add(msgLabel, BorderLayout.CENTER);
        msgPanel.add(okButton, BorderLayout.SOUTH);

        // adds the message panel to the message frame
        msgFrame.add(msgPanel);

        // make the message visible
        msgFrame.setVisible(true);
    }

    public static void main(String[] args) {
        BudgetGUI budgetGUI = new BudgetGUI();
    }

    // EFFECTS: creates a back button
    public void backButton() {
        // adds a back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            // returns to main panel
            mainFrame.getContentPane().removeAll();
            mainFrame.getContentPane().add(mainPanel);
            mainFrame.getContentPane().revalidate();
            mainFrame.getContentPane().repaint();
        });
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

