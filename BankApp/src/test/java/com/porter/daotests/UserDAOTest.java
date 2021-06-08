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
		
		// add users to users list
		
	
		
		Assert.assertEquals(users, udao.getAll());
	}
}
