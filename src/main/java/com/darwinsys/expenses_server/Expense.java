package com.darwinsys.expenses_server;

import java.io.Serializable;

/**
 * One expense item.
 */
public class Expense implements Serializable {
    // expenseData should be a LocalDateTime not this rubbish, but
    // we have to be compatible with the old server.
    public long expenseDate;
    public String description;
    public double amount;

    public Expense(long expenseDate, String description, double amount) {
        this.expenseDate = expenseDate;
        this.description = description;
        this.amount = amount;
    }
}
