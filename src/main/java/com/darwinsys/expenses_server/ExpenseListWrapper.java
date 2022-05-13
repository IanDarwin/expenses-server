package com.darwinsys.expenses_server;

import java.io.Serializable;
import java.util.List;

/**
 * Needed because The JSON Format should look like this:
 * {"expense":[
 *   {"description":"Description0","amount":"10.1","expenseDate":"1303492691292"},
 *   {"description":"Description1"...
 *
 */
public class ExpenseListWrapper implements Serializable {
    List<Expense> expense; // Do not change to expenses; must match JSON name.

    /** Construct this wrapper given a list of expenses */
    public ExpenseListWrapper(List<Expense> expenses) {
        this.expense = expenses;
    }

    /**
     * Return the list. Again, the stupid name is mandated by the structure of the old app.
     * XXX FIXME use a Jackson annotation to solve that hideous name
     * @return The list of Expense items
     */
    public List<Expense> getExpense() {
        return expense;
    }
}
