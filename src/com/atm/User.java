package com.atm;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;

public class User {

    private String firstName;
    private String lastName;
    private String uuid;
    private byte pinHash[];     //* MD5 hash of pin

    private ArrayList<Account> accounts;

    public User(String firstName, String lastName,
                String pin, Bank theBank) {
        this.firstName = firstName;
        this.lastName = lastName;

        //* store pin's MD5 hash, rather than the original value, for
        //* security reasons
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());

        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        //* get a new, unique id for user
        this.uuid = theBank.getNewUserUUID();

        //* create ampty list of accounts
        this.accounts = new ArrayList<Account>();

        //* print log message
        System.out.printf("New user %s, %s with ID %s created.\n",
                        lastName, firstName, this.uuid);
    }

    /**
     * add an account for the user
     * @param anAcct     the account to add
     */
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    /**
     * return the user's UUID
     * @return the uuid
     */
    public String getUUID() {
        return this.uuid;
    }

    //* check whether a given pin matches the true User pin
    // * return the pin is valid or not
    public boolean validatePin(String  aPin) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()),
                    this.pinHash);

        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        return false;
    }

    //* return the user's first name
    public String getFirstName() {
        return this.firstName;
    }

    public void printAccountsSummary() {
        System.out.printf("\n\n%'s account summary", this.firstName);

        for (int a = 0; a < this.accounts.size(); a++) {
            System.out.printf("%d) %s\n", a+1,
                    this.accounts.get(a).getSummaryLine());
        }
        System.out.println();
    }

    //* get the number of accounts of the user
    public int numAccounts() {
        return this.accounts.size();
    }
}
