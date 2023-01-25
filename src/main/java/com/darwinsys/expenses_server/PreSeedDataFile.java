package com.darwinsys.expenses_server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * This will OVERWRITE the datafile on the server side with the entries in "expenses" below.
 * Good for initial setup and testing; not much else.
 */
public class PreSeedDataFile {
    public static void main(String[] args) throws Exception {
        List<Expense> expenses = List.of(
                new Expense(1673275262, "Dinner", "Tasty Takeout", 12.50),
                new Expense(1674075262, "Breakfast", "Testy Tiffany's", 42.50)
        );
        ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(ExpensesController.FILE));
        expenses.forEach(data -> {
            try {
                ois.writeObject(data);
            } catch (IOException e) {
                throw new RuntimeException("Writing failed!!" + e, e);
            }
        });
        ois.close();
        System.out.println(ExpensesController.FILE.getAbsoluteFile() + " written OK");
    }
}
