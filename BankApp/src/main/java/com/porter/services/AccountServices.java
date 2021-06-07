package com.porter.services;

import java.util.List;
import com.porter.beans.Account;
import com.porter.beans.User;


public interface AccountServices {

	public boolean openAccount(User user);
	
	public List<Account> getAllAccounts();
	
	public List<Account> getAllUserAccounts();
	
	public List<Account> getAllAccountBalances();
}
