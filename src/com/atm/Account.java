package com.atm;

import java.util.ArrayList;

public class Account {

    private String name;
    //* private double balance;
    private String uuid;
    private User holder;
    private ArrayList<Transaction> transactions;

    public Account(String name, User holder,
                   Bank theBank) {
        //* set acc name and holder
        this.name = name;
        this.holder = holder;

        //* get new acc ID
        this.uuid = theBank.getNewAccountUUID();

        //* init transactions
        this.transactions = new ArrayList<Transaction>();

        //* add to holder and bank lists
        //! NOT COPY OF OBJECT, BUT THE SAME OBJECT
        //holder.addAccount(this);
        //theBank.addAccount(this);
    }

    /**
     * get the account ID
     * @return the uuid
     */
    public String getUUID() {
        return this.uuid;
    }


    //* get summary line for the account
    public String getSummaryLine() {

        //* get the account's balance
        double balance = this.getBalance();

        //* format the summary line, depending on the whether the balance is
        // negative
        if (balance >= 0) {
            return String.format("%s : $%.02f : %s",
                    this.uuid, balance, this.name);
        } else {
            return String.format("%s : $(%.02f)k : %s",
                    this.uuid, balance, this.name);
        }
    }

    //* get the balance of this account by adding the amounts of the
    // transactions
    public double getBalance() {
        double balance = 0;
        for (Transaction t : this.transactions) {
            balance += t.getAmount();
        }
        return balance;
    }

    //* print the transaction history of the account
    public void printTransHistory() {
        System.out.printf("\nTransaction history for account %s\n", this.uuid);

        for (int t = this.transactions.size() -1; t >= 0; t--) {
            System.out.printf(this.transactions.get(t).getSummaryLine());
        }

        System.out.println();
    }

    //* add a new transaction in this account
    public void addTransaction(double amount, String memo) {
        //* create new transaction object and add it to our list
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);
    }
}
