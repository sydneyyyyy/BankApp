package com.porter.repositories;

import java.util.List;

import com.porter.beans.Account;
import com.porter.beans.Transaction;


public interface TransactionDAO {

	public List<Transaction> getAllUserTransactions(Integer i);
	
	public List<Transaction> getAllAccountTransactions(Account a);
	
	public Transaction createTransaction(Account a, Transaction t);
	
	public List<Transaction> getAllTransactions();
}
