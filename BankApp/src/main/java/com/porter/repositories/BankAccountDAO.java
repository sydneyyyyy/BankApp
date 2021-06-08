package com.porter.repositories;

import java.util.List;
import com.porter.beans.Account;


public interface BankAccountDAO {
	
	public List<Account> getAllAccounts();
	
	public Account updateAccount(Account a);
	
	public List<Account> getAllUserAccounts(Integer i);
	
	public boolean deleteAccount(Account a);
	
	public List<Account> getAllAccountBalances();

	Account createAccount(Account acct);

	public Account getAccountByAccountNumber(int accountNumber);

}
