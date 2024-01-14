package com.atm;

import java.util.Date;

public class Transaction {

    private double amount;
    private Date timeStamp;
    private String memo;

    //* the acc in which the transaction was performed
    private Account inAccount;

    public Transaction(double amount, Account inAccount) {
        this.amount = amount;
        this.inAccount = inAccount;
        this.timeStamp = new Date();
        this.memo = "";
    }

    public Transaction(double amount, String memo, Account inAccount) {
        //? call the two-arg constructor first
        this(amount, inAccount);

        //* set the memo
        this.memo = memo;
    }
}
