package com.darwinsys.expenses_server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * A simple REST / JSON server which accepts a POST containing an array of Expenses and stores them on the local file system
 * The URL for the POST is expected to be /expenses
 * The same array can then be retrieved via GET /expenses
 *
 * The JSON Format should look like this:
 * {"expense":[{"description":"Description0","amount":"10.1","expenseDate":"1303492691292"},{"description":"Description1"...
 *
 * @author Ian Darwin, written from scratch to be compatible with
 * an earlier incantation from "LT 2771 Team"
 */
@RestController
public class ExpensesController {

	final static String FILE_NAME = "expenses.ser";
	final static File FILE = new File(FILE_NAME);

	@GetMapping("/")
	public String index() {
		return "<h1>Welcome!</h1><p>This is the Spring-based <b>Upload server</p>"
				+ "<p>There is no real user interface to this service; use REST!</p>";
	}
	
	@GetMapping("/index.jsp")
	public String compat() {
		return index();
	}

	@GetMapping("/info")
	public String mapping() {
		return FILE.getAbsolutePath();
	}

	@PostMapping("/expenses")
	public ExpenseListWrapper upload(@RequestBody ExpenseListWrapper expenses) throws IOException {
		System.out.println("Expenses Controller: Got " + expenses);
		expenses.getExpensesList().forEach(System.out::println);
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FILE));
		os.writeObject(expenses);
		os.close();
		return expenses;
	}

	@GetMapping("/expenses")
	public ExpenseListWrapper download() throws IOException, ClassNotFoundException {
		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(FILE));) {
			ExpenseListWrapper expenses = (ExpenseListWrapper) is.readObject();
			return expenses;
		}
	}
}
