package com.darwinsys.expenses_server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

/**
 * A simple REST / JSON server which accepts a POST containing an array of Expenses 
 * and stores them on the local file system
 * The URL for the POST is expected to be /expenses
 * The same array can then be retrieved via GET /expenses
 * <br/>
 * The JSON Format should look like this:
 * {"expense":[{"description":"Description0","amount":"10.1","expenseDate":"1303492691292"},{"description":"Description1"...
 *
 * @author Ian Darwin, written from scratch to be mostly compatible with
 * an earlier incantation from "LT 2771 Team", but no longer compatible
 * with the 2771 version.
 */
@RestController
public class ExpensesController {

	final static String FILE_NAME = "expenses.ser";
	final static File FILE = new File(FILE_NAME);

	static int primaryKey = 0;

	/** A simple HTML (well, plain) text confirmation that we're here */
	@GetMapping("/")
	public String index() {
		return "<h1>Welcome!</h1><p>This is the Spring-based <b>Upload server</b></p>"
				+ "<p>There is no real user interface to this service; please use REST!</p>";
	}

	@GetMapping("/index.jsp")
	public String compatibility() {
		return index();
	}

	/** Debugging info; NOT something you'd normally do on a real internet site! */
	@GetMapping("/info")
	public String mapping() {
		return FILE.getAbsolutePath();
	}

	/** Upload ONE expense item.
	 * @return Just the primary key of the new entry.
	 */
	@PostMapping("/add-expense")
	public long addOneExpense(@RequestBody Expense expense) throws IOException {
		System.out.println("Add: " + expense);
		boolean append = FILE.exists(); // if file exists then append, otherwise create new
		FileOutputStream fout = new FileOutputStream(FILE, append);
		AppendableObjectOutputStream objectOutput = new AppendableObjectOutputStream(fout, append);
		ensureHasPkey(expense);
		objectOutput.writeObject(expense);
		objectOutput.close();
		return expense.getId();
	}

	/** Upload multiple expenses; if they lack primary keys these will be assigned.
	 * Unlike 2771 version, DOES NOT REPLACE ENTIRE LIST on the server.
	 * @return The entire list, for verification purposes
	 */
	@PostMapping("/expenses")
	@ResponseBody
	public ExpenseListWrapper addMultiple(@RequestBody ExpenseListWrapper expenses) throws IOException, ClassNotFoundException {
		System.out.println("Expenses Controller: Got " + expenses);
		boolean append = FILE.exists(); // if file exists then append, otherwise create new
		FileOutputStream fout = new FileOutputStream(FILE, append);
		AppendableObjectOutputStream objectOutput = new AppendableObjectOutputStream(fout, append);
		for (Expense expense : expenses.getExpensesList()) {
			ensureHasPkey(expense);
			System.out.println("Saving expense item " + expense);
			objectOutput.writeObject(expense);
		}
		objectOutput.close();
		return listAll(); // In case we were appending
	}

	void ensureHasPkey(Expense expense) {
		if (expense.getId() == 0) {
			expense.setId(++primaryKey);
		}
	}

	/** Echo the last-uploaded expenses, a very crude kind of confirmation */
	@GetMapping("/expenses")
	public ExpenseListWrapper listAll() throws ClassNotFoundException {
		List<Expense> all = innerListAll();
		return new ExpenseListWrapper(all);
	}

	private static List<Expense> innerListAll() throws ClassNotFoundException {
		List<Expense> all = new ArrayList<>();
		// Hate to use IOException as loop terminator, but no other reliable way at hand
		// (pull request that genuinely improves the code would be welcome).
		try (ObjectInputStream infile = new ObjectInputStream(new FileInputStream(FILE));) {
			do {
				all.add((Expense) infile.readObject());
			} while (true);
		} catch (IOException  e) {
			System.out.println("Got IOException after reading " + all.size() + " expenses");
		}
		return all;
	}

	@GetMapping("/get-data/{id}")
	public Expense findExpenseById(@RequestParam int id) throws ClassNotFoundException {
		System.out.println("Get: " + id);
		for (Expense expense : innerListAll()) {
			if (expense.getId() == id) {
				return expense;
			}
		};
		throw new RuntimeException(String.format("Item %d not found, sorry", id));
	}
}
