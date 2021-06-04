package com.porter.repositories;

import java.util.List;

public interface GenericRepository<T> {
	
	// CREATE - ADD TO DATABASE
	public T add(T t);
	
	// READ - Get things from database
	public List<T> getAll();
	
	public T getById(Integer id);
	
	
	// UPDATE
	public boolean update(T change);
	
	
	// DELETE
	public boolean delete(T t);
	
	
}
