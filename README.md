# Personal Project - Personal Finance

## A tool to make budgeting easier

This application is designed to represent a simple personal
budgeting tool that anyone can use to improve their money management skills.
As inflation continues to rise and costs increase, it is becoming more important
to understand how to budget properly in order to prevent financial-related issues.
The demographic for this application can be anyone aged above 13 ranging from young
students budgeting their allowance to working adults managing this expenses.


I *chose* to pursue this as my project idea because:
- Personal finance is something I have struggled with
- I wanted my project to be something that is applicable to a large demographic 
- I want to take this opportunity to challenge myself to create an engaging and well-developed project
- As a computer science student who is learning, this project will help me develop my technical skills

The application will be designed to be a simple personal budgeting tool. You will be able to
add expenses and sources of income to create a balance sheet of each month of the year.
The application will be visually appealing with a high contrast for readability.
The user will be able to log in and have their unique data saved to be used as you use the app in the future.
The application will also allow you to create graphs of your expenses or income
to visualize how your finances are allocated.


## User Stories

The following are summaries of actions that a user is able to perform
on the application

- As a user, when I start the application, I want to be given the option to load my budget application from file.
- As a user, when I select the quit option from the application menu, I want to be reminded to save my budget app to file and have the option to do so or not.
- As a user, I want to be able to add my income or other sources of money to my budget (list of incoming money)
- As a user, I want to be able to create an expense and add it to my list of expenses.
- As a user, I want to view my monthly entries organized in a graphical manner.
- As a user, I want to be able to edit my budget or expenses entries if applicable
- As a user, I want to be able to add a new yearly budget for a specific year
- As a user, I want to be able to sort my finances depending on the year and month
- (Extra) As a user, I want to be able to select different themes or colour schemes for the application

## Instructions for GUI Interface

- You can generate the first required action related to adding Xs to a Y by running the GUI from MainGUI, going to the main menu
and clicking "Add Entry" to add a specific budget or expenses entry to a specific month of a year. However, you are required to load a previous
data file from "Load Progress" or create a new yearly budget by clicking "Add Yearly Budget" in the main menu


- You can generate the second required action related to adding Xs to a Y by clicking the "View Entries" button in the main menu. You will be asked
to input specific parameters for year, month and type of entries (budget or expenses). The data will then be displayed in a table format with
a description with it's corresponding amount


- You can locate my visual component by clicking the "Illustrate Entries" button in the main menu. You will be asked to input specific parameters for year, month and type of entries (budget or expenses).
If any of the parameters are incorrect (yearly budget doesn't exist or month is out of bounds), it will ask you to input again. You can view a
pie chart of a specific set of budget or expense entries for a month. However, this works best when you have at least two entries added to your month's 
budget or expense entries due to the labels getting cut off sometimes which I was not able to fix this for the meantime. There is also a visual panel
that pops up when you start the application that displays an image of a coin and the title of the app. You can press continue to go to the main
menu.


- You can save the state of my application by clicking on the "Save Progress" button in the main menu and typing in a file name for the .json
file to be saved.


- You can reload the state of my application by clicking on the "Load Progress" button in the main menu and typing in a file name for the .json
  file to be loaded if it exists.

## Event Log Sample
The following event log represent the events of user adding en entry to yearly budget then trying to remove an entry 
that doesn't exist then modifying an existing entry during an application runtime
- The events logged since the application started
- Wed Apr 12 11:25:16 PDT 2023:
Yearly budget information has been set.
- Wed Apr 12 11:25:25 PDT 2023:
Yearly budgets size has been returned.
- Wed Apr 12 11:25:25 PDT 2023:
Yearly budget exists: model.YearlyBudget@5467ebb6
- Wed Apr 12 11:25:25 PDT 2023:
Year has been returned.
- Wed Apr 12 11:25:25 PDT 2023:
The specific month has been returned: model.Month@751f4a72
- Wed Apr 12 11:25:25 PDT 2023:
Month budget has been returned: model.Budget@5319dab7
- Wed Apr 12 11:25:25 PDT 2023:
The specific month has been returned: model.Month@751f4a72
- Wed Apr 12 11:25:25 PDT 2023:
Month expenses have been returned: model.Expenses@205195e5
- Wed Apr 12 11:25:25 PDT 2023:
Entry has been added: model.Entry@44c36fca
- Wed Apr 12 11:25:36 PDT 2023:
Yearly budgets size has been returned.
- Wed Apr 12 11:25:36 PDT 2023:
Yearly budget exists: model.YearlyBudget@5467ebb6
- Wed Apr 12 11:25:36 PDT 2023:
Year has been returned.
- Wed Apr 12 11:25:36 PDT 2023:
The specific month has been returned: model.Month@13c81afd
- Wed Apr 12 11:25:36 PDT 2023:
Month budget has been returned: model.Budget@63e3ca6a
- Wed Apr 12 11:25:36 PDT 2023:
The specific month has been returned: model.Month@13c81afd
- Wed Apr 12 11:25:36 PDT 2023:
Month expenses have been returned: model.Expenses@39a3daee
- Wed Apr 12 11:25:36 PDT 2023:
Entry does not exist.
- Wed Apr 12 11:25:49 PDT 2023:
Yearly budgets size has been returned.
- Wed Apr 12 11:25:49 PDT 2023:
Yearly budget exists: model.YearlyBudget@5467ebb6
- Wed Apr 12 11:25:49 PDT 2023:
Year has been returned.
- Wed Apr 12 11:25:49 PDT 2023:
The specific month has been returned: model.Month@751f4a72
- Wed Apr 12 11:25:49 PDT 2023:
Month budget has been returned: model.Budget@5319dab7
- Wed Apr 12 11:25:49 PDT 2023:
The specific month has been returned: model.Month@751f4a72
- Wed Apr 12 11:25:49 PDT 2023:
Month expenses have been returned: model.Expenses@205195e5
- Wed Apr 12 11:25:49 PDT 2023:
Entry description has been returned: Rent
- Wed Apr 12 11:25:49 PDT 2023:
Entry amount has been returned: 2000.0
- Wed Apr 12 11:25:49 PDT 2023:
Entry exists: model.Entry@44c36fca
- Wed Apr 12 11:25:49 PDT 2023:
Entry description has been returned: Rent
- Wed Apr 12 11:25:49 PDT 2023:
Entry amount has been returned: 2000.0
- Wed Apr 12 11:25:49 PDT 2023:
Entry exists model.Entry@44c36fca and index returned.
- Wed Apr 12 11:25:49 PDT 2023:
Entry amount has been set: 2500.0
- Wed Apr 12 11:25:49 PDT 2023:
Entry amount has been modified: model.Entry@44c36fca
- Wed Apr 12 11:25:49 PDT 2023:
Entry description has been set: Rent
- Wed Apr 12 11:25:49 PDT 2023:
Entry description has been modified: model.Entry@44c36fca

## Future Improvements
- The most obvious refactoring that can be done is reducing the length of the methods that ended up being over 25 lines. There were some methods
that exceeded the limit because due to habit of trying to make a method that performs a user story then refactoring it afterwards. This caused some methods to
perform more than one specific task which is bad coding practice that leads to a decline in code readability especially when you are working in groups
and other people may have to review your code. Performing method refactoring simultaneously while making sure one method does one specific task is an important
habit to adopt and would improve code design and readability.


- Referring to the UML_Design_Diagram.png, there is some refactoring that immediately comes to mind. Firstly, we will look at the relationship
between the Budget and Expenses classes. Currently, the Expenses class extends the Budget class as the methods and constructor used for both methods
is identical. This is a valid abstraction approach that was taken to minimize duplicate code among the classes. A possible refactoring approach could
be to create an abstract class (say MonthlyInfo) that implements the methods that are used in both Budget and Expenses classes which can be 
extended by these classes. The Entry class would then form a dependency relationship with the abstract class that is extended to Budget and Expenses. 
This would produce a triangle hierarchy between the three classes and would be a better code design approach and
improve code readability.


- Another refactoring approach is completely removing the Month class and implementing it as part of YearlyBudget. The Month class has two fields:
one for budget and one for expenses. The refactoring would result in a nested list field for YearlyBudget where the index would relate to the
month of the year (index 0 for Month 1 for example). The methods described in Month would then be implemented as part of the YearlyBudget using
the appropriate list within the nested list for budget and expenses that match the index for the appropriate month. The disadvantages for this
refactoring would result in nested loops within the method that could be possibly detriment code readability. It may improve code efficiency as the
number of lines would likely reduce. The current implementation chose code readability over code efficiency. Understanding the limitations of
different refactoring approaches and their effect on your code is an important and critical part of software design.
