package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class Test {

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


    public Test() {
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
            boolean Overlap = false;
            // confirms if coins has overlap or not
            for (Point p : coinPositions) {
                if (coinPos.distance(p) < resizedCoinIcon.getIconWidth()) {
                    Overlap = true;
                    // breaks the loop if there is overlap
                    break;
                }
            }

            if (Overlap) {
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

        // create a button to add the entry
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });

        // adds button to panel
        addEntryPanel.add(addButton);

        // Add the panel to the frame and show the window
        addEntryFrame.add(addEntryPanel);
        addEntryFrame.setVisible(true);
    }



    private void modifyEntry() {
        // creates the frame and title for view entries
        JFrame viewEntriesFrame = new JFrame(("View Entries"));
        viewEntriesFrame.setSize(400, 300);
        viewEntriesFrame.setLayout(new BorderLayout());

        // create month and year buttons
        JRadioButton monthButton = new JRadioButton("Month");
        JRadioButton yearButton = new JRadioButton("Year");

        // create button group
        ButtonGroup group = new ButtonGroup();
        group.add(monthButton);
        group.add(yearButton);

        // create text fields for month and year
        JTextField monthTextField = new JTextField(10);
        JTextField yearTextField = new JTextField(10);

        // create labels for month and year text fields
        JLabel monthLabel = new JLabel("Month:");
        JLabel yearLabel = new JLabel("Year:");

        // create panel for month text field and label
        JPanel monthPanel = new JPanel();
        monthPanel.add(monthLabel);
        monthPanel.add(monthTextField);
        monthPanel.add(yearLabel);
        monthPanel.add(yearTextField);

        // create panel for year text field and label
        JPanel yearPanel = new JPanel();
        yearPanel.add(yearLabel);
        yearPanel.add(yearTextField);

        // create panel for buttons and text fields
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.add(monthButton);
        buttonPanel.add(monthPanel);
        buttonPanel.add(yearButton);
        buttonPanel.add(yearPanel);

        // create panel for message and button
        JPanel messagePanel = new JPanel();
        messagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // adds a message label with the message label
        JLabel messageLabel = new JLabel("Please select from the following options");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 12));

        // implements a ok button
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                return;
            }
        });
        messagePanel.add(messageLabel);
        messagePanel.add(okButton);

        // create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(messagePanel, BorderLayout.SOUTH);

        // add main panel to frame
        viewEntriesFrame.add(mainPanel);

        // set frame properties
        viewEntriesFrame.pack();
        viewEntriesFrame.setVisible(true);

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
        Test budgetGUI = new Test();
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
