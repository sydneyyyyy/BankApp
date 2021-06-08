package com.porter.services;

import java.util.List;
import java.util.Scanner;

import com.porter.beans.Account;
import com.porter.beans.User;



public interface AccountServices {

	public Account openAccount(Scanner scanner, User u);
	
	public List<Account> getAllAccounts();
	
	public List<Account> getAllUserAccounts(Integer i);
	
	public List<Account> getAllAccountBalances();
}
