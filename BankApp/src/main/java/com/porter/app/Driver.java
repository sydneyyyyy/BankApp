package com.porter.app;


import java.util.Scanner;

import com.porter.beans.User;
import com.porter.repositories.UserDAO;
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
	


	
	public static Scanner scanner =  new Scanner(System.in);
	public static UserServices us = new UserServicesImpl();
	public static UserDAO udao = new UserDAO();

	public static void main(String[] args) {


		
		printMainMenu();
		
		while (running) {
			
//			collectInput();
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
							printMainMenu();
						}
					}
					break;
				}
			}
		}
		
//		scanner.close();
	}
	
	public static void printMainMenu() {
		String mainMenu = "Welcome to the Bank! Please select an option: " +
				"\n 1. Login " +
				"\n 2. Signup " +
				"\n 3. Quit";
		System.out.println(mainMenu);
		
		userInput = collectInput();
		
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

	}
	
	public static void printCustomerMenu() {

		
	}
	
	public static void printEmployeeMenu() {
		
	}
	
	private static String collectInput() {
		userInput = scanner.nextLine();
		return userInput;
	}
	
	
}
