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

# Instructions for Grader

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