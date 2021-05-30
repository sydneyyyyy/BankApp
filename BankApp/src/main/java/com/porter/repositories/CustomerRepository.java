package com.porter.repositories;

import java.util.List;

import com.porter.beans.Customer;
import com.porter.utils.MockDB;

public class CustomerRepository implements GenericRepository<Customer> {

	public List<Customer> getAll() {
		return MockDB.customers;
		
	}

	public Customer add(Customer c) {
		return null;
	}

	public Customer getById(Integer id) {
		return null;
	}

	public void update(Customer change) {
		
	}

	public void delete(Customer c) {
		
	}

}
