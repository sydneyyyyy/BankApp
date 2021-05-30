package com.porter.app;

import java.util.List;
import java.util.Scanner;

import com.porter.beans.Customer;
import com.porter.repositories.CustomerRepository;


// create a method printMainMenu()
// collect user input
// switch statement based on input
// methods with different lists for different answers

public class Driver {
	
	public Scanner scanner =  new Scanner(System.in);

	public static void main(String[] args) {
		CustomerRepository cr = new CustomerRepository();
		List<Customer> customers = cr.getAll();
		
		for (Customer c : customers) {
			System.out.println(c);
		}
		
		
	}
	
	
}
