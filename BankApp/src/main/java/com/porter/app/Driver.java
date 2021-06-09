package com.porter.app;


import java.util.Scanner;
import com.porter.beans.Account;
import com.porter.beans.User;
import com.porter.repositories.UserDAO;
import com.porter.services.AccountServices;
import com.porter.services.AccountServicesImpl;
import com.porter.services.TransactionServices;
import com.porter.services.TransactionServicesImpl;
import com.porter.services.UserServices;
import com.porter.services.UserServicesImpl;
import com.porter.utils.AppLogger;





// create a method printMainMenu()
// collect user input
// switch statement based on input
// methods with different lists for different answers

public class Driver {
	
	
	private static boolean running = true;
	private static String userInput;
	private static User loggedUser;
	private static User newUser;
	private static Account currentAccount;
	



	
	public static Scanner scanner =  new Scanner(System.in);
	public static UserServices us = new UserServicesImpl();
	public static AccountServices as = new AccountServicesImpl();
	public static TransactionServices ts = new TransactionServicesImpl();
	
	
	// do i need to make this separate from driver???
	public static UserDAO udao = new UserDAO();

	
	public static void main(String[] args) {

		
		String mainMenu = "Welcome to the Bank! Please select an option: " +
				"\n 1. Login " +
				"\n 2. Signup " +
				"\n 3. Quit\n";
		System.out.println(mainMenu);
		
		AppLogger.logger.info("Program Started");
		
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
							AppLogger.logger.info("Program ended");
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
			System.out.println("\nLogin successful. You are logged in as: " + loggedUser.getFirstName() + " " + loggedUser.getLastName());
			
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
					break;
				}
				
				case "4" : {
					us.logout(loggedUser);
					System.out.println("Logout successful!");
				}
				
				default : {
					System.out.println("Please make a valid selection.");
					// print out customer menu again????
				}
			}
			
		} 
	}
	
	public static void printTransactionMenu() {
		String transactionMenu = "\nPlease select an option below." +
				"\n 1. Deposit Money" +
				"\n 2. Make a Withdrawal" +
				"\n 3. Transfer Money" +
				"\n 4. View Transactions";

		System.out.println(transactionMenu);
		transactionMenuInput(collectInput());
	}
	
	public static void transactionMenuInput(String input) {
		
		if (input != null) {
			
			switch (input) {
			
			case "1" : {
				ts.makeDeposit(scanner, loggedUser);
				printCustomerMenu();
				break;
			}
			
			case "2" : {
				ts.makeWithdrawal(scanner, loggedUser);
				printCustomerMenu();
				break;
			}
			
			case "3" : {
				ts.makeTransfer(scanner, loggedUser);
				printCustomerMenu();
				break;
			}
			
			case "4" : {
				System.out.println("\nWould you like to...." + 
									"\n 1. View all transactions" +
									"\n 2. View transactions for one of your accounts");
				String userInput = collectInput();
				
				switch (userInput) {
				
					case "1" : {
						ts.viewAllUserTransactions(loggedUser);
						printCustomerMenu();
						break;
					}
					
					case "2" : {
						ts.viewAllAccountTransactions(loggedUser, currentAccount);
						printCustomerMenu();
						break;
					}
				}
				
			}
			
			}
		}
		
	}
	

	public static void printEmployeeMenu() {
		
		String employeeMenu = "\n\nHello " + loggedUser.getFirstName() + ", what would you like to do first?" +
								"\n1. View User Bank Accounts" + 
								"\n2. View Bank Transactions" +
								"\n3. View Pending Accounts" +
								"\n4. Logout";
		System.out.println(employeeMenu);
		employeeMenu(collectInput());
		
	}
	
	public static void employeeMenu(String input) {
		
		if (input != null) {
	
			switch (input) {
			
				case "1" : {
					as.getAllAccounts();
					printEmployeeMenu();
					break;
				}
				
				case "2" : {
					ts.viewAllTransactions();
					printEmployeeMenu();
					break;
				}
				
				case "3" : {
					
				}
				
				case "4" : {
					us.logout(loggedUser);
					System.out.println("Logout Successful!");
				}
				
				default : {
					System.out.println("Please make a selection!");
					printEmployeeMenu();
				}
			}
			
			
		} 
	 
	}
	
	private static String collectInput() {
		userInput = scanner.nextLine();
		return userInput;
	}
	
	
}
