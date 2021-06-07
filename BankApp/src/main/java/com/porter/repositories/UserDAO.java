package com.porter.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.porter.beans.User;
import com.porter.utils.JDBCConnection;

public class UserDAO implements GenericRepository<User> {
	
	private Connection conn = JDBCConnection.getConnection();

	@Override
	public User addUser(User user) {
		
		

		try {
			String sql = "call add_user(?, ?, ?, ?, ?);";
			
			CallableStatement cs = conn.prepareCall(sql);
			
//			cs.setInt(1, user.getId());
			cs.setString(1, user.getUsername());
			cs.setString(2, user.getPassword());
			cs.setString(3,  user.getFirstName());
			cs.setString(4, user.getLastName());
			cs.setString(5,  user.getType());
			
			cs.execute();

			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User getUser(String username, String password) {
		
		String sql = "select * from users where username = ? and password = ?;";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFirstName(rs.getString("firstName"));
				u.setLastName(rs.getString("lastName"));
				u.setType(rs.getString("type"));
				
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public User getById(Integer id) {
		
		String sql = "select * from users where id = ?;"; 
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				// create user object
				User u = new User();
				
				// set user values
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFirstName(rs.getString("first name"));
				u.setLastName(rs.getString("last name"));
				u.setType(rs.getString("type"));
				
				// return user
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// return null if no user is found
		return null;
	}

	@Override
	public boolean update(User change) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User add(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
