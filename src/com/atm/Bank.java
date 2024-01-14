package com.atm;

import java.util.ArrayList;
import java.util.Random;

public class Bank {

    private String name;
    private ArrayList<User> users;

    private ArrayList<Account> accounts;

    /**
     * generate a new universally unique ID for a user
     * @return the uuid
     */
    public String getNewUserUUID() {
        //* initialization
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        //* continue looping until we get a unique ID
        do {
            //* generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer)rng.nextInt()).toString();
            }

            //* check to make sure it's unique
            nonUnique = false;
            for (User u : this.users) {
                if (uuid.compareTo(u.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);

        return uuid;
    }

    /**
     * generate a new universally unique ID for an account
     * @return the uuid
     */
    public String getNewAccountUUID() {
        //* initialization
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;

        //* continue looping until we get a unique ID
        do {
            //* generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer)rng.nextInt()).toString();
            }

            //* check to make sure it's unique
            nonUnique = false;
            for (Account a : this.accounts) {
                if (uuid.compareTo(a.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);

        return uuid;
    }


    /**
    * add an account
    * @param anAcct     the account to add
    */

    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    public User addUser(String firstName, String lastName, String pin) {

        //* create a new User obj and add it to list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        //* create a savings account for the user and add to User
        //* and Bank account lists
        Account newAccount = new Account("Savings", newUser, this);

        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;
    }

}
