package com.porter.app;


import java.util.Scanner;
import com.porter.beans.User;
import com.porter.repositories.UserDAO;
import com.porter.services.AccountServices;
import com.porter.services.AccountServicesImpl;
import com.porter.services.UserServices;
import com.porter.services.UserServicesImpl;





// create a method printMainMenu()
// collect user input
// switch statement based on input
// methods with different lists for different answers

public class Driver {
	
	
	private static boolean running = true;
	private static String userInput;
	private static User loggedUser;
	private static User newUser;
	


	
	public static Scanner scanner =  new Scanner(System.in);
	public static UserServices us = new UserServicesImpl();
	public static AccountServices as = new AccountServicesImpl();
	public static UserDAO udao = new UserDAO();

	public static void main(String[] args) {

		
		String mainMenu = "Welcome to the Bank! Please select an option: " +
				"\n 1. Login " +
				"\n 2. Signup " +
				"\n 3. Quit";
		System.out.println(mainMenu);
		
		
		
		while (running) {
			
			String userInput = collectInput();
			System.out.println(userInput);
			switch (userInput) {
				
				case "1" : {
					printLoginMenu(); 
					break;
				}
				
				case "2" : {
					printSignupMenu();
					break;
				}
				
				case "3" : {
					running = false;
					System.out.println("Are you sure you'd like to quit? yes or no.");
					collectInput();
					switch (userInput) {
					
						case "yes" : {
							System.out.println("Goodbye!");
							break;
						}
						
						case "no" : {
							System.out.println(mainMenu);
						}
					}
					break;
				}
			}
		}
		
	}
	

	
	public static void printLoginMenu() {
		loggedUser = us.login(scanner);
		if (loggedUser == null) {
			System.out.println("Invalid Credentials!");
		} else {
			System.out.println("Login successful. You are logged in as: " + loggedUser.getFirstName() + " " + loggedUser.getLastName());
			
			switch (loggedUser.getType()) {
				
				case "Customer" : {
					printCustomerMenu();
					break;
				}
				
				case "Employee" : {
					printEmployeeMenu();
					break;
				}
			}
		}

	}
	
	public static void printSignupMenu() {
		newUser = us.signup(scanner);
		System.out.println(newUser);
		System.out.println("Thank you for being a part of the Bank community " 
							+ newUser.getFirstName() + " " + newUser.getLastName());
		
		printCustomerMenu();
		
	}
	
	/*
	 * apply for an account
	 * view balance of account
	 * withdrawal
	 * deposit
	 * transfer money (accept or decline)
	 * */
	public static void printCustomerMenu() {
		String customerMenu = "\nHello, " + loggedUser.getFirstName() + ". What would you like to do first?" +
								"\n" + "1. Open a Bank Account" +
								"\n" + "2. View Account Balances" + 
								"\n" + "3. Transactions" +
								"\n" + "4. Logout";
		
		System.out.println(customerMenu);
		
		customerMenuInput(collectInput());
		
		
								
		
	}
	
	public static void customerMenuInput(String input) {
		
		if (input != null) {
			
			switch (input) {
				case "1" : {
					as.openAccount(scanner, loggedUser);
					
					printCustomerMenu();
					break;
				} 
				
				case "2" : {
					as.getAllUserAccounts(loggedUser.getId());
					
					printCustomerMenu();
					break;
				}
				
				case "3" : {
					printTransactionMenu();
				}
			}
			
		}
	}
	
	public static void printTransactionMenu() {
		String transactionMenu = "\nPlease select an option below." +
				"\n 1. Deposit Money" +
				"\n 2. Make a Withdrawal" +
				"\n 3. View all Transactions";
		System.out.println(transactionMenu);
	
	}
	

	public static void printEmployeeMenu() {
		
	}
	
	private static String collectInput() {
		userInput = scanner.nextLine();
		return userInput;
	}
	
	
}
