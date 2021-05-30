package com.porter.utils;

import java.util.ArrayList;
import java.util.List;

import com.porter.beans.Customer;
import com.porter.beans.Employee;

public class MockDB {
	
	public static List<Customer> customers = new ArrayList<Customer>();
	public static List<Employee> employees = new ArrayList<Employee>();
	
	static {
		
		// CUSTOMERS
		customers.add(new Customer(1, "jane101", "pass", 10000.0));
		customers.add(new Customer(2, "richard", "dogs", 5000.00));
		customers.add(new Customer(3, "patricia", "coop", 20000.00));
		customers.add(new Customer(4, "michael", "montana", 1000.00));
		
		// EMPLOYEES
		employees.add(new Employee("janet", "sweetie"));
		employees.add(new Employee("alex", "tabasco"));
		employees.add(new Employee("dani", "password"));
	}
	
	
	
}
