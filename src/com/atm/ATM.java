package com.atm;


import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {

        //* init Scanner
        Scanner sc = new Scanner(System.in);

        //* init Bank
        Bank theBank = new Bank("Bank of MyBank");

        //* add a user, which also creates a savings account
        User aUser = theBank.addUser("John", "Doe", "1234");

        //* add a checking account for user
        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User curUser;
        while (true) {

            //* stay in the login prompt until successful login
            curUser = ATM.mainMenuPrompt(theBank, sc);

            //* stay in main menu until user quits
            ATM.printUserMenu(curUser, sc);
        }
    }

    //* print the ATM's login menu
    public static User mainMenuPrompt(Bank theBank, Scanner sc) {
        //* inits
        String userID;
        String pin;
        User authUser;

        //* prompt the user ID/pin combo until a correct one is reached
        do {

            System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
            System.out.print("Enter user ID: ");
            userID = sc.nextLine();
            System.out.print("Enter pin: ");
            pin = sc.nextLine();

            //* try to get the user obj corresponding to the ID and pin combo
            authUser = theBank.userLogin(userID, pin);

            if (authUser == null) {
                System.out.println("Incorrect user ID/pin combination. " +
                "Please try again");
            }
        } while (authUser == null); //* continue looping until successful login

        //
        return authUser;
    }

    //*
    public static void printUserMenu(User theUser, Scanner sc) {

        //* print a summary of the user's accounts
        theUser.printAccountsSummary();

        //* init
        int choice;

        //* user menu
        do {
            System.out.printf("Welcome %s, what would you like to do?",
                    theUser.getFirstName());
            System.out.println("  1) Show account transaction history");
            System.out.println("  2) Withdrawal");
            System.out.println("  3) Deposit");
            System.out.println("  4) Transfer");
            System.out.println("  5) Quit");
            System.out.println();
            System.out.println("Enter choice: ");
            choice = sc.nextInt();

            if (choice < 1|| choice > 5) {
                System.out.println("Invalid choice. Please choose 1-5");
            }
        } while (choice < 1 || choice > 5);

        //* process the choice
        switch (choice) {

            case 1:
                ATM.showTransHistory(theUser, sc);
                break;
            case 2:
                ATM.withdrawlFunds(theUser, sc);
                break;
            case 3:
                ATM.depositFunds(theUser, sc);
                break;
            case 4:
                ATM.transerFunds(theUser, sc);
                break;
        }

        //* redisplay this menu unless the user wants to quit
        if (choice != 5) {
            ATM.printUserMenu(theUser, sc);
        }
    }

    public static void showTransHistory(User theUser, Scanner sc) {

        int theAcct;

        //* get account whose transaction history to look at
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" +
            "whose transactions you want to see: ",
                    theUser.numAccounts());
            theAcct = sc.nextInt() - 1;

            if (theAcct < 0 || theAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (theAcct < 0 || theAcct >= theUser.numAccounts());

        //* print the transaction history
        theUser.printAcctTransHistory(theAcct);
    }

    //*
    public static void transerFunds(User theUser, Scanner sc) {

        //* init
        int fromAcct;
        int toAcct;
        double amount;
        double acctBal;

        //* get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" +
                    "to transfer from: ");
            fromAcct = sc.nextInt()-1;

            if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (fromAcct < 0 || fromAcct >= theUser.numAccounts());

        acctBal = theUser.getAcctBalance(fromAcct);
    }
}
