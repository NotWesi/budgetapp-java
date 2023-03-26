package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BudgetGUI extends JFrame implements ActionListener {
    // initializes the relevant objects
    private JLabel yearLabel;
    private JLabel monthLabel;
    private JLabel budgetLabel;
    private JLabel expenseLabel;
    private JTextField yearTextField;
    private JTextField monthTextField;
    private JTextField budgetTextField;
    private JTextField expenseTextField;
    private JButton addEntryButton;
    private JButton viewEntriesButton;
    private JButton editEntryButton;
    private JButton removeEntryButton;
    private JButton viewNetBudgetButton;
    private JButton newBudgetButton;
    private JButton exitButton;
    private JTextArea display;
    YearlyBudgets yearlyBudgets = new YearlyBudgets();
    private Budget budget;
    private Expenses expenses;


    // sets up the budget app user interface
    public BudgetGUI() {
        // sets up the main window
        setTitle("Budget Management Tool");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1));

        // adds the components to the main window
        // adds the relevant text labels
        yearLabel = new JLabel("Year: ");
        add(yearLabel);
        yearTextField = new JTextField();
        add(yearTextField);
        monthLabel = new JLabel("Month: ");
        add(monthLabel);
        monthTextField = new JTextField();
        add(monthTextField);
        budgetLabel = new JLabel("Budget: ");
        add(budgetLabel);
        budgetTextField = new JTextField();
        add(budgetTextField);
        expenseLabel = new JLabel("Expenses: ");
        add(expenseLabel);
        expenseTextField = new JTextField();
        add(expenseTextField);

        // adds the buttons for the different options
        // A button to create a new yearly budget
        newBudgetButton = new JButton("New Yearly Budget");
        add(newBudgetButton);
        addEntryButton.addActionListener(this);

        addEntryButton = new JButton("Add an entry");
        add(addEntryButton);
        addEntryButton.addActionListener(this);

        editEntryButton = new JButton("Modify an entry");
        add(editEntryButton);
        editEntryButton.addActionListener(this);

        removeEntryButton = new JButton("Remove an entry");
        add(removeEntryButton);
        removeEntryButton.addActionListener(this);

        viewEntriesButton = new JButton("View entries for a specific month");
        add(viewEntriesButton);
        viewEntriesButton.addActionListener(this);

        viewNetBudgetButton = new JButton("Remove an entry");
        add(viewNetBudgetButton);
        viewNetBudgetButton.addActionListener(this);

        exitButton = new JButton("Exit the program");
        add(exitButton);
        exitButton.addActionListener(this);

        // create display area and add to GUI
        display = new JTextArea(20, 50);
        JScrollPane scrollPane = new JScrollPane(display);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // call updateDisplay() to show initial budget and expenses
        updateDisplay();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // handles the newBudgetButton and calls createNewYearlyBudget()
        if (e.getSource() == newBudgetButton) {
            createNewYearlyBudget();
        } else if (e.getSource() == addEntryButton) {
            // Call the addEntry method from your console app
            addEntry();
            JOptionPane.showMessageDialog(null, "Entry added.");
        } else if (e.getSource() == editEntryButton) {
            editEntry();
            JOptionPane.showMessageDialog(null, "Entry has been modified.");
        } else if (e.getSource() == removeEntryButton) {
            removeEntry();
        } else if (e.getSource() == viewEntriesButton) {
            // Call the viewEntries method from your console app
            String entries = viewEntries();
            JOptionPane.showMessageDialog(null, entries);
        }
    }

    // add an entry to a specific month
    public void addEntry() {
        // Create a new dialog window to get user input
        JDialog dialog = new JDialog();
        dialog.setTitle("Add Entry");
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        dialog.add(panel, BorderLayout.CENTER);

        // Create the input fields
        JTextField yearField = new JTextField();
        JTextField monthField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField budgetOrExpenseField = new JTextField();

        // Add the input fields to the panel
        panel.add(new JLabel("Year: "));
        panel.add(yearField);
        panel.add(new JLabel("Month (1-12): "));
        panel.add(monthField);
        panel.add(new JLabel("Enter the description: "));
        panel.add(descriptionField);
        panel.add(new JLabel("Enter the amount: "));
        panel.add(amountField);
        panel.add(new JLabel("Budget (1) or Expense (2) ?: "));
        panel.add(budgetOrExpenseField);

        // converts the relevant input values into their respective types
        int year = Integer.parseInt(yearField.getText());
        int month = Integer.parseInt(monthField.getText());
        YearlyBudget selectedYearBudget = findYearlyBudgetByYear(yearlyBudgets, year);
        Budget budget = selectedYearBudget.getMonth(month).getBudget();
        Expenses expenses = selectedYearBudget.getMonth(month).getExpenses();
        Entry entry = new Entry(descriptionField.getText(), Double.parseDouble(amountField.getText()));

        // add entry to appropriate list
        if (Integer.parseInt(budgetOrExpenseField.getText()) == 1) {
            budget.addEntry(entry);
        } else {
            expenses.addEntry(entry);
        }

        // update display
        updateDisplay();
    }

    private void updateDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("Budget:\n");
        for (Entry e : budget.getEntries()) {
            sb.append(e.getName()).append(": $").append(e.getAmount()).append("\n");
        }
        sb.append("\nExpenses:\n");
        for (Entry e : expenses.getEntries()) {
            sb.append(e.getName()).append(": $").append(e.getAmount()).append("\n");
        }
        display.setText(sb.toString());
    }


    public String viewEntries() {

    }

    // EFFECTS: modifies a specific entry from a specific month
    public void editEntry() {

    }

    // EFFECTS: removes a specific entry from a specific month
    public void removeEntry() {

    }

    // EFFECTS: A method to handle the creation of a new yearly budget
    public void createNewYearlyBudget() {
        // Get the year from the user
        String yearString = JOptionPane.showInputDialog("Enter the year for the new yearly budget:");
        int year = Integer.parseInt(yearString);

        // Create a new yearlyBudget object and add it to the list of yearlyBudgets
        YearlyBudget yearlyBudget = new YearlyBudget(year);
        yearlyBudgets.addYearlyBudget(yearlyBudget);

        // Display a message to the user
        JOptionPane.showMessageDialog(this, "New yearly budget created for " + year);
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

    // EFFECTS: runs the main program for the GUI
    public static void main(String[] args) {
        // Create an instance of the BudgetGUI class and show it
        BudgetGUI gui = new BudgetGUI();
        gui.setVisible(true);
    }

}
