package com.porter.daotests;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import com.porter.beans.User;
import com.porter.repositories.UserDAO;



public class UserDAOTest {

	

	private UserDAO udao = new UserDAO();
	
	@Test
	public void getAllUsersTest() {
		
		List<User> users = new ArrayList<User>();
		// create new users
		User u1 = new User(1, "jane", "pass", "Jane", "Doe", "Customer");
		User u2 = new User(2, "richard", "dogs", "Richard", "Joseph", "Customer");
		User u3 = new User(3, "patricia", "coop", "Patti", "Cakes", "Customer");
		User u4 = new User(4, "michael", "montana", "Michael", "Cluck", "Customer");
		User u5 = new User(5, "janet", "sweetie", "Janet", "Logston", "Employee");
		User u6 = new User(6, "alex", "tabasco", "Alex", "Nurse", "Employee");
		User u7 = new User(7, "dani", "password", "Danielle", "Rose", "Employee");
		User u8 = new User(23, "Enstein", "pass", "Spencer", "Reid", "Customer");
		
		// add users to users list
		users.add(u1);
		users.add(u2);
		users.add(u3);
		users.add(u4);
		users.add(u5);
		users.add(u6);
		users.add(u7);
		users.add(u8);
	
		
		Assert.assertEquals(users, udao.getAll());
	}
}
