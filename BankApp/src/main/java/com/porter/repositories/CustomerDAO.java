package com.porter.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.porter.beans.Customer;
import com.porter.utils.JDBCConnection;

public class CustomerDAO implements GenericRepository<Customer>{
	
	private Connection conn = JDBCConnection.getConnection();

	@Override
	public Customer add(Customer c) {
		
		String sql = "insert into bankapp.customers values (?, ?, ?, ?) returning *;";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, c.getId());
			ps.setString(2, c.getUsername());
			ps.setString(3, c.getPassword());
			ps.setDouble(4, c.getAccountBalance());
			
			boolean success = ps.execute();
			
			if (success) {
				ResultSet rs = ps.getResultSet();
				
				if (rs.next()) {
					
					c.setId(rs.getInt("id"));
					c.setUsername(rs.getString("username"));
					c.setPassword(rs.getString("password"));
					c.setAccountBalance(rs.getDouble("balance"));
					return c;
					
				}
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return null;
	}

	@Override
	public List<Customer> getAll() {
		
		List<Customer> customers = new ArrayList<Customer>();
		
		String sql = "select * from bankapp.customers;";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Customer c = new Customer();
				c.setId(rs.getInt("id"));
				c.setUsername(rs.getString("username"));
				c.setPassword(rs.getString("password"));
				c.setAccountBalance(rs.getDouble("balance"));
				
				customers.add(c);
			}
			
			return customers;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return null;
	}

	@Override
	public Customer getById(Integer id) {
		return null;
	}

	@Override
	public boolean update(Customer change) {
		return false;
	}

	@Override
	public boolean delete(Customer t) {
		return false;
	}

}
