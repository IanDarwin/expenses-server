package com.darwinsys.expenses_server;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * One expense item. No longer compatible with 2771 version.
 */
public class Expense implements Serializable {
    @Serial
    private static final long serialVersionUID = 832098432189L;

    // expenseData should be a LocalDateTime, but
    // we have to be compatible with the old server.
    long id;			// A primary key
    public long expenseDate;	// time_t value, aka seconds since Unix invented
    public String description;	// What was spent
    public String location;	// Where
    public double amount;	// How much you spent.

    /**
     * Java supports method overloading so we have two constructors, one with an ID
     * and one without This is the full version
     * @param id          The primary key
     * @param expenseDate The date, as a Unix time_t
     * @param description What the expense was
     * @param location    Where the expense was spent
     * @param amount      How much money was spent.
     */
    public Expense(long id, long expenseDate, String description, String location, double amount) {
        this.id = id;
        this.expenseDate = expenseDate;
        this.description = description;
        this.location = location;
        this.amount = amount;
    }

    /** This is the constructor when you don't have a prinary key yet, as for new Expense items */
    public Expense(long expenseDate, String description, String location, double amount) {
        this(0, expenseDate, description, location, amount);
    }

    @SuppressWarnings("unused")
    public Expense() {
        // Empty, needed for Jackson
    }

    public String toString() {
        return String.format("Expense['%s' on %s for %.2f]", description,
                LocalDateTime.ofEpochSecond(expenseDate, 0, ZoneOffset.UTC), amount);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
