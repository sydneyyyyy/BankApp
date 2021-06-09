package com.porter.services;

import java.util.List;
import java.util.Scanner;

import com.porter.beans.Account;
import com.porter.beans.Transaction;
import com.porter.beans.User;

public interface TransactionServices {

	public Account makeDeposit(Scanner scanner, User u);
	
	public Account makeWithdrawal(Scanner scanner, User u);
	
	public Account makeTransfer(Scanner scanner, User u);
	
	public List<Transaction> viewAllUserTransactions(User u);
	
	public List<Transaction> viewAllAccountTransactions(User u, Account a);
	
	public List<Transaction> viewAllTransactions();
}
