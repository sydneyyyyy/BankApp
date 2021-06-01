package com.porter.app;

import java.util.List;
import java.util.Scanner;

import com.porter.beans.Customer;
import com.porter.repositories.CustomerRepository;
import com.porter.utils.MockDB;


// create a method printMainMenu()
// collect user input
// switch statement based on input
// methods with different lists for different answers

public class Driver {
	
	
	private static boolean running = true;
	private static String userInput;
	private static String usernameInput;
//	private static boolean loggedIn = true;
//	private static String loginUsername;
	
	public static Scanner scanner =  new Scanner(System.in);

	public static void main(String[] args) {
		CustomerRepository cr = new CustomerRepository();
//		Customer c = new Customer();
		List<Customer> customers = cr.getAll();
		
//		for (Customer c : customers) {
//			System.out.println(c);
//		}
		
		
		printMainMenu();
		
		while (running) {
			collectInput();
			System.out.println(userInput);
			if (userInput.equals("1")) {
				printLoginMenu();
				// checks if username and password matches current customer
			} else {
				printSignupMenu();
			}
		}
		
		// if logged in print out main menu
		// else print login/signup options
		
		
		// if user.getId == customer.getId {
		// print currentCustomer menu options
		// else show newCustomer menu options
	}
	
	// prints the main menu to the user wants logged in. 
	public static void printMainMenu() {
		System.out.println("Welcome to The Bank! Please select an option below.");
		System.out.println("1. Login");
		System.out.println("2. Signup");
	}
	
	public static String printLoginMenu() {
		System.out.println("Username: ");
		String usernameInput = scanner.nextLine();
		System.out.println(usernameInput);
		return usernameInput;
//		System.out.println(loginUsername);
		
		// if username and password is not null 
		// print currentCustomer menu options
	}
	
	public static void printSignupMenu() {
		System.out.println("Please enter a username: ");
		System.out.println("Please enter a password: ");
		// add the userInput to the database = addUser();
	}
	
	public static void printNewCustomerMenu() {
		System.out.println("Welcome to the Bank! What would you like to do first?");
		System.out.println("1. Apply for a new Checking Account");
		System.out.println("2. Apply for a new Savings Account");
		// userInput == 1 then ask for getUserId.getCheckingAccount.setBalance();
		// userInput == 2 then ask for getUserId.getSavingsAccount.setBalance();
		
	}
	
	// might change to ask what account the customer want to use and then print the list of options
	public static void printCurrentCustomerMenu() {
		System.out.println("Welcome back! What would you like to do first?");
		System.out.println("1. Check your balance"); // prints a list of your current accounts 
		// getUserId.getAccounts.getAll() 
		System.out.println("2. Deposit to your account");
		System.out.println("3. Withdraw from your account");
		System.out.println("4. See recent transactions"); // prints a list of all past deposits and withdrawals
		System.out.println("5. Create a new account"); // prints a list of accounts: Checking or Savings
	}
	
	private static String collectInput() {
		userInput = scanner.nextLine();
		return userInput;
	}
	
	
}
