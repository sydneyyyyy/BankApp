package com.porter.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.porter.beans.User;
import com.porter.utils.JDBCConnection;

public class UserDAO implements GenericRepository<User> {
	
	private Connection conn = JDBCConnection.getConnection();

	@Override
	public User addUser(User user) {
		
		

		try {
			String sql = "call add_user( ?, ?, ?, ?, ?);";
			
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
		
		List<User> users = new ArrayList<User>();
		
		try {
			
			String sql = "select * from users order by id;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFirstName(rs.getString("firstName"));
				u.setLastName(rs.getString("lastName"));
				u.setType(rs.getString("type"));
				
				users.add(u);
				
			}
			
			for (User u: users) {
				System.out.println(u.getId() + "  " + u.getUsername() + "  " + u.getPassword() + "  " + u.getFirstName() + "  " + u.getLastName() + "  " + u.getType());
			}
			
			return users;
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
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
				u.setFirstName(rs.getString("firstName"));
				u.setLastName(rs.getString("lastName"));
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
	public boolean update(User user) {
		
		try {
			String sql = "update users set firstName = ?, lastName = ?, username = ?, \r\n"
					+ "password = ?, type = ? where id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getType());
			ps.setString(6, Integer.toString(user.getId()));
			
			boolean success = ps.execute();
			return success;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return false;
	}

	@Override
	public boolean delete(User u) {
		
		String sql = "delete from users where id = ?;";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(u.getId()));
			
			boolean success = ps.execute();
			
			if (success) {
				return true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return false;
	}



	

	

}
