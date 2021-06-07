package com.porter.repositories;

import java.util.List;

import com.porter.beans.User;

public interface GenericRepository<T> {
	
	// CREATE - ADD TO DATABASE
	User addUser(User user);

	// READ - Get things from database
	public List<T> getAll();
	
	public T getById(Integer id);
	
	
	public T getUser(String username, String password);
	
	// UPDATE
	public boolean update(T change);
	
	
	// DELETE
	public boolean delete(T t);


	
	
	
}
