package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class BudgetGUI {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JTextArea display;
    private JButton addYearlyBudgetButton;
    private JButton addEntryButton;
    private JButton modifyEntryButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton removeEntryButton;
    private JButton viewEntriesButton;
    private JButton quitButton;
    YearlyBudgets yearlyBudgets;


    public BudgetGUI() {
        prepareGUI();
    }

    private void prepareGUI() {
        yearlyBudgets = new YearlyBudgets();

        mainFrame = new JFrame("Budget Tracker");
        mainFrame.setSize(500, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        display = new JTextArea();
        display.setEditable(false);


        // creates a new JLabel with the image as its icon
        ImageIcon coinIcon = new ImageIcon("data/coin.png");
        Image coin = coinIcon.getImage();
        int width = 50; // new width
        int height = 50; // new height
        Image resizedCoin = coin.getScaledInstance(width, height, Image.SCALE_SMOOTH); // resize the image
        ImageIcon resizedCoinIcon = new ImageIcon(resizedCoin);

        // get the size of the main panel
        Dimension backgroundSize = mainPanel.getSize();

        // Calculate the number of coins that can fit on the background
        int numCoins = (backgroundSize.width * backgroundSize.height) / (resizedCoinIcon.getIconWidth() * resizedCoinIcon.getIconHeight());

        // generate random coordinates for each coin
        Random random = new Random();
        ArrayList<Point> coinPositions = new ArrayList<>();
        for (int i = 0; i < numCoins; i++) {
            int x = random.nextInt(backgroundSize.width - resizedCoinIcon.getIconWidth());
            int y = random.nextInt(backgroundSize.height - resizedCoinIcon.getIconHeight());
            Point coinPos = new Point(x, y);
            boolean overlap = false;
            // confirms if coins has overlap or not
            for (Point p : coinPositions) {
                if (coinPos.distance(p) < resizedCoinIcon.getIconWidth()) {
                    overlap = true;
                    // breaks the loop if there is overlap
                    break;
                }
            }

            if (overlap) {
                coinPositions.add(coinPos);
            }
        }

        // adds the coins to the main panel
        for (Point p : coinPositions) {
            JLabel coinLabel = new JLabel(resizedCoinIcon);
            coinLabel.setBounds(p.x, p.y, resizedCoinIcon.getIconWidth(), resizedCoinIcon.getIconHeight());
            mainPanel.add(coinLabel);
        }

        addYearlyBudgetButton = new JButton("Add a New Yearly Budget");
        addYearlyBudgetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addYearlyBudgetButton.addActionListener(e -> addYearlyBudget());

        addEntryButton = new JButton("Add Entry");
        addEntryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addEntryButton.addActionListener(e -> addEntry());

        modifyEntryButton = new JButton("Modify Entry");
        modifyEntryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        modifyEntryButton.setBackground(Color.RED);
        modifyEntryButton.addActionListener(e -> modifyEntry());

        removeEntryButton = new JButton("Remove Entry");
        removeEntryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeEntryButton.setBackground(Color.RED);
        removeEntryButton.addActionListener(e -> deleteEntry());

        viewEntriesButton = new JButton("View Entries");
        viewEntriesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewEntriesButton.setBackground(Color.RED);
        viewEntriesButton.addActionListener(e -> viewEntries());

        saveButton = new JButton("Save progress");
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setBackground(Color.RED);
        saveButton.addActionListener(e -> saveButton());

        loadButton = new JButton("Load progress");
        loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadButton.setBackground(Color.RED);
        loadButton.addActionListener(e -> loadButton());

        quitButton = new JButton("Quit");
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setBackground(Color.RED);
        quitButton.addActionListener(e -> quit());

        mainPanel.add(addYearlyBudgetButton);
        mainPanel.add(addEntryButton);
        mainPanel.add(modifyEntryButton);
        mainPanel.add(removeEntryButton);
        mainPanel.add(viewEntriesButton);
        mainPanel.add(saveButton);
        mainPanel.add(loadButton);
        mainPanel.add(quitButton);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    // EFFECTS: adds a new yearly budget for the year
    private void addYearlyBudget() {
        // prompts user for budget year
        int year = Integer.parseInt(JOptionPane.showInputDialog("Enter the year: "));

        // creates a new yearly budget with the respective year
        YearlyBudget yearlyBudget = new YearlyBudget(year);

        // Prompt the user for monthly budget and expense limits
        // loops for each month of the year
        for (int i = 1; i <= 12; i++) {
            // asks the user for the monthly budget
            double monthlyBudgetLimit = Double.parseDouble(JOptionPane.showInputDialog("Enter the budget limit for month " + i + ":"));

            // initializes a new Month object and stores the monthly budget amount
            Month month = new Month();
            month.getBudget().addEntry(new Entry("Monthly Budget", monthlyBudgetLimit));
            // sets the month data in the yearBudget to the corresponding month
            yearlyBudget.setMonth(i, month);

            // asks the user for the monthly expenses
            double monthlyExpensesLimit = Double.parseDouble(JOptionPane.showInputDialog("Enter the expenses for month " + i + ":"));

            // stores the monthly expenses amount
            month.getExpenses().addEntry(new Entry("Monthly Expenses", monthlyExpensesLimit));
            // sets the month data in the yearBudget to the corresponding month
            yearlyBudget.setMonth(i, month);
        }

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
        JLabel typeLabel = new JLabel("Type budget or expense: ");
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

        // add entry to appropriate list
        if (budgetOrExpenseField.getText() == "budget") {
            budget.addEntry(entry);
        } else {
            expenses.addEntry(entry);
        }
        // update display
        updateDisplay("Your entry had been added successfully");
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
        JLabel typeLabel = new JLabel("Type budget or expense: ");
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
        Entry entry = new Entry(descriptionField.getText(), Double.parseDouble(amountField.getText()));

        // checks if the entry can be modified
        if (budgetOrExpenseField.getText() == "budget") {
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

    private void deleteEntry() {

    }


    private void viewEntries() {
        // View entries code here
    }

    private void saveButton() {

    }

    private void loadButton() {

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

