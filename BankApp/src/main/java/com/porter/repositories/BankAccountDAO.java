package com.porter.repositories;

import java.util.List;

import com.porter.beans.Account;
import com.porter.beans.User;

public interface BankAccountDAO {

	public boolean createAccount(User user);
	
	public List<Account> getAllAccounts();
	
	public Account updateAccount(Account a);
	
	public List<Account> getAllUserAccounts();
	
	public boolean deleteAccount(Account a);
	
	public List<Account> getAllAccountBalances();


}
