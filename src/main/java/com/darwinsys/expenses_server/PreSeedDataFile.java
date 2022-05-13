package com.darwinsys.expenses_server;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class PreSeedDataFile {
    public static void main(String[] args) throws Exception {
        List<Expense> expenses = List.of(
                new Expense(123456789, "Dinner", 42.50),
                new Expense(987654321, "Breakfast", 12.50)
        );
        ExpenseListWrapper data = new ExpenseListWrapper(expenses);
        ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(UploadController.FILE));
        ois.writeObject(data);
        ois.close();
        System.out.println(UploadController.FILE.getAbsoluteFile() + " written OK");
    }
}