package com.porter.daotests;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.Assert;
import com.porter.beans.Customer;
import com.porter.repositories.CustomerDAO;



public class CustomerDaoTests {

	private CustomerDAO cdao = new CustomerDAO();
	
	@Test
	public void getAllCustomersTest() {
		
		List<Customer> customers = new ArrayList<Customer>();
		Customer c1 = new Customer(1, "jane", "pass", 10000.0);
		Customer c2 = new Customer(2, "richard", "dogs", 5000.0);
		Customer c3 = new Customer(3, "patricia", "coop", 20000.0);
		Customer c4 = new Customer(4, "michael", "montana", 1000.0);
		Customer c5 = new Customer(5, "victor", "weekend", 2500.0);
		
		customers.add(c1);
		customers.add(c2);
		customers.add(c3);
		customers.add(c4);
		customers.add(c5);
		
		Assert.assertEquals(customers, cdao.getAll());
	}
//	
//	@Test
//	public void addCustomerTest() {
//		
//		Customer c = new Customer(5, "victor", "weekend", 2500.0);
//		Assert.assertEquals(true, cdao.add(c));
//		
//	}
}
