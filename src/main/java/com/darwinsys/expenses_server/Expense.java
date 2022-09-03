package com.darwinsys.expenses_server;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * One expense item.
 */
public class Expense implements Serializable {
    @Serial
    private static final long serialVersionUID = 832098432189L;

    // expenseData should be a LocalDateTime, but
    // we have to be compatible with the old server.
    public long expenseDate;
    public String description;
    public double amount;

    public Expense(long expenseDate, String description, double amount) {
        this.expenseDate = expenseDate;
        this.description = description;
        this.amount = amount;
    }

    @SuppressWarnings("unused")
    public Expense() {
        // Empty, needed for Jackson
    }

    public String toString() {
        return String.format("Expense['%s' on %s for %.2f]", description,
                LocalDateTime.ofEpochSecond(expenseDate, 0, ZoneOffset.UTC), amount);
    }
}
