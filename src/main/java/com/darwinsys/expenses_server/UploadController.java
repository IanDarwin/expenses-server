package com.darwinsys.expenses_server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * A simple REST / JSON server which accepts a PUT containing an array of Expenses and stores them on the local file system
 * The URL for the PUT is expected to be /expenses
 * The same array can then be retrieved via GET /expenses
 *
 * The JSON Format should look like this:
 * {"expense":[{"description":"Description0","amount":"10.1","expenseDate":"1303492691292"},{"description":"Description1"...
 *
 * @author Ian Darwin, based on earlier incantation from "LT 2771 Team"
 */
@RestController
public class UploadController {

	final static String FILE_NAME = "expenses.ser";
	final static File FILE = new File(FILE_NAME);

	@GetMapping("/")
	public String index() {
		return "This is the Spring-based Upload server";
	}

	@GetMapping("/info")
	public String mapping() {
		return FILE.getAbsolutePath();
	}

	@PutMapping("/expenses")
	public ExpenseListWrapper upload(@RequestBody ExpenseListWrapper expenses) throws IOException {
		expenses.getExpensesList().forEach(System.out::println);
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FILE));
		os.writeObject(expenses);
		os.close();
		return expenses;
	}

	@GetMapping("/expenses")
	public ExpenseListWrapper download() throws IOException, ClassNotFoundException {
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(FILE));
		ExpenseListWrapper expenses = (ExpenseListWrapper) is.readObject();
		return expenses;
	}
}
