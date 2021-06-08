package com.porter.repositories;

import java.util.List;
import com.porter.beans.Account;


public interface BankAccountDAO {

//	public boolean createAccount(Account a);
	
	public List<Account> getAllAccounts();
	
	public Account updateAccount(Account a);
	
	public List<Account> getAllUserAccounts(Integer i);
	
	public boolean deleteAccount(Account a);
	
	public List<Account> getAllAccountBalances();

//	Account createAccount(Account a, Integer acctNum, Double balance, String type, Integer userId);

	Account createAccount(Account acct);


}
