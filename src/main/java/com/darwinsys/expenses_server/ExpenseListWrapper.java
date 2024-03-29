package com.darwinsys.expenses_server;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Needed because The JSON Format should look like this, to be more self-identifying:
 * {"expense":[
 *   {"description":"Description0","amount":"10.1","expenseDate":"1303492691292"},
 *   {"description":"Description1"...}
 * ]}
 *
 */
public class ExpenseListWrapper implements Serializable {
    @Serial
    private static final long serialVersionUID = 1234567890L;
    List<Expense> expenses;

    /** Construct this wrapper given a list of expenses */
    public ExpenseListWrapper(List<Expense> expenses) {
        this.expenses = expenses;
    }

    /** No-arg constructor needed for Jackson */
    @SuppressWarnings("unused")
    public ExpenseListWrapper() {
	    // Empty
    }

    /**
     * Return the list. Again, the stupid name is mandated by the structure of the old app.
     * @return The list of Expense items
     */
    @JsonProperty("expense")
    public List<Expense> getExpensesList() {
        return expenses;
    }

    @SuppressWarnings("unused")
    public void setExpensesList(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public String toString() {
        return String.format("ExpenseListWrapper with %d Expenses", expenses.size());
    }
}
