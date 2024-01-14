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
    }
}
