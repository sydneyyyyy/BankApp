package com.porter.services;


import java.util.Scanner;
import com.porter.beans.User;



public interface UserServices {
	
	public User login(Scanner scanner);

	public boolean login(User user, String username, String password);
	
	public User logout(User user);

	
	
	
}
