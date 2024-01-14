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
}
