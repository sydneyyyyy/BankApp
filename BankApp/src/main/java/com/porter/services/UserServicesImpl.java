package com.porter.services;


import java.util.Scanner;
import com.porter.beans.User;
import com.porter.repositories.UserDAO;
import com.porter.utils.AppLogger;



public class UserServicesImpl implements UserServices {
	
	private static UserDAO udao = new UserDAO();

	@Override
	public User login(Scanner scanner) {
		System.out.println("Please enter a username: ");
		String username = scanner.nextLine();
		System.out.println("Please enter a password: ");
		String password = scanner.nextLine();
		
		User u = udao.getUser(username, password);
		AppLogger.logger.info(u.getFirstName() + " " + u.getLastName() + " has logged in.");
		return u;
		
		
	}
	
	
	
	
	@Override
	public boolean login(User user, String username, String password) {
		
		if (username.equalsIgnoreCase(user.getUsername()) & 
			password.equalsIgnoreCase(user.getPassword())) {
			
			udao.getById(user.getId());
			
			return true;
			
		} else {
			
			return false;
			
		}
	}
	
	@Override
	public User signup(Scanner scanner) {
		User u = new User();
		u.setType("Customer");
		System.out.println("Thank you for choosing the Bank!");
		System.out.println("Please enter a username: ");
		u.setUsername(scanner.nextLine());
		System.out.println("Please enter a password: ");
		u.setPassword(scanner.nextLine());
		System.out.println("What is your first name?");
		u.setFirstName(scanner.nextLine());
		System.out.println("What is your last name?");
		u.setLastName(scanner.nextLine());
		udao.addUser(u);
		
		AppLogger.logger.info(u.getFirstName() + " " + u.getLastName() + " has joined the Bank.");
		return u;
	}


	@Override
	public User logout(User user) {
		User loggedUser = udao.getById(user.getId());
		loggedUser = null;
		
		AppLogger.logger.info(user.getFirstName() + " " + user.getLastName() + " has logged out.");
		
		return loggedUser;
	}






	

	

	

}
