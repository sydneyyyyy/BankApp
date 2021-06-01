package com.porter.repositories;

import java.util.List;

import com.porter.beans.Customer;
import com.porter.utils.MockDB;

public class CustomerRepository implements GenericRepository<Customer> {

	public List<Customer> getAll() {
		return MockDB.customers;
		
	}

	@Override 
	public Customer add(Customer c) {
		Customer customer = MockDB.customers.stream().max((c1, c2) -> c1.getId().compareTo(c2.getId())).orElse(null);
		Integer id = (customer != null) ? customer.getId() + 1 : 1;
		c.setId(id);
		MockDB.customers.add(c);
		return c;
	}

	public Customer getById(Integer id) {
		Customer c = MockDB.customers.stream().filter((customer) -> customer.getId() == id).findFirst().orElse(null);
		return c;
	}

	public void update(Customer change) {
		
	}

	public void delete(Customer c) {
		MockDB.customers.remove(c);
	}

}
