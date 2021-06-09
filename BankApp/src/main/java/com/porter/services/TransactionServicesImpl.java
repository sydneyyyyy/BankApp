package com.porter.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.porter.beans.Account;
import com.porter.beans.Transaction;
import com.porter.beans.User;
import com.porter.repositories.BankAccountDAO;
import com.porter.repositories.BankAccountDAOImpl;
import com.porter.repositories.TransactionDAO;
import com.porter.repositories.TransactionDAOImpl;

public class TransactionServicesImpl implements TransactionServices {

	public static Scanner scanner = new Scanner(System.in);
	public AccountServices as = new AccountServicesImpl();
	public BankAccountDAO bdao = new BankAccountDAOImpl();
	public TransactionDAO tdao = new TransactionDAOImpl();

	
	private static double depositAmount = 0;
	private static double withdrawalAmount = 0;
	private static double accountBalance = 0;
	
	@Override
	public Account makeDeposit(Scanner scanner, User u) {

		System.out.println("\nPlease type the account number you'd like to make a deposit to.\n");
		as.getAllUserAccounts(u.getId());
		
		Integer input = scanner.nextInt();
		
		Account acctNum = bdao.getAccountByAccountNumber(input);
		
		System.out.println("\nHow much would you like to deposit?");
		depositAmount = scanner.nextDouble();
		accountBalance = acctNum.getBalance() + depositAmount;
		
		acctNum.setBalance(accountBalance);
		bdao.updateAccount(acctNum);
		
		return acctNum;
	}

	@Override
	public Account makeWithdrawal(Scanner scanner, User u) {
		
		System.out.println("\nPlease type the account number you'd like to make a withdrawal from.\n");
		as.getAllUserAccounts(u.getId());
		
		Integer input = scanner.nextInt();
		Account acctNum = bdao.getAccountByAccountNumber(input);
		System.out.println(acctNum);
		System.out.println("\nHow much would you like to withdraw?");
		withdrawalAmount = scanner.nextDouble();
		
		if (acctNum.getBalance() < withdrawalAmount) {
			System.out.println("You do not have the funds for this transaction.");
		
		} else {
			accountBalance = acctNum.getBalance() - withdrawalAmount;
			acctNum.setBalance(accountBalance);
			bdao.updateAccount(acctNum);
			System.out.println(acctNum);
		}
		
		
		return acctNum;
	}

	@Override
	public Account makeTransfer(Scanner scanner, User u) {
		
		System.out.println("\nPlease type the account number you'd like to transfer from.\n");
		as.getAllUserAccounts(u.getId());
		
		Integer firstAccount = scanner.nextInt();
		Account firstAccountNum = bdao.getAccountByAccountNumber(firstAccount);
		
		System.out.println(firstAccountNum);
		
		System.out.println("\nHow much would you like to transfer?");
		withdrawalAmount = scanner.nextDouble();
		
		if (firstAccountNum.getBalance() < withdrawalAmount) {
			System.out.println("You do not have the funds for this transaction.");
		} else {
			accountBalance = firstAccountNum.getBalance() - withdrawalAmount;
			firstAccountNum.setBalance(accountBalance);
			bdao.updateAccount(firstAccountNum);
			
			System.out.println("\nPlease type the account number you'd like to transfer to.\n");
			as.getAllUserAccounts(u.getId());
			
			Integer secondAccount = scanner.nextInt();
			Account secondAccountNum = bdao.getAccountByAccountNumber(secondAccount);
			
			System.out.println(secondAccountNum);
			accountBalance = secondAccountNum.getBalance() + withdrawalAmount;
			secondAccountNum.setBalance(accountBalance);
			bdao.updateAccount(secondAccountNum);
			System.out.println(secondAccountNum);
			
		}
		
		return firstAccountNum;
	}

	@Override
	public List<Transaction> viewAllUserTransactions(User u) {
		
		List<Transaction> userTr = new ArrayList<Transaction>();
		userTr = tdao.getAllUserTransactions(u.getId());
		
		System.out.println("-----------------------" + u.getFirstName() + "'s" + " Transactions ----------------------");
		System.out.println("Account Number ----- Transaction ----- Transaction Amount ----- Account Balance");
		
		for (Transaction t : userTr) {
			System.out.println(t.getAccountNumber() + "                " + t.getTransactionType() + "              " + t.getTransactionAmount() + "                  " + t.getAccountBalance());
		}
		
		return userTr;
	}

	
	@Override
	public List<Transaction> viewAllAccountTransactions(User u, Account a) {
		
		List<Transaction> accountTr = new ArrayList<Transaction>();
		
		System.out.println("\nWhich account would you like to view transactions for?");
		as.getAllUserAccounts(u.getId());
		
		Integer input = scanner.nextInt();
		Account account = bdao.getAccountByAccountNumber(input);
		
		accountTr = tdao.getAllAccountTransactions(account);
		
		System.out.println("\nAccount Number: " + account.getAccountNumber());
		System.out.println("---------------------------- Transactions -------------------------------");
		System.out.println("Transaction ------------ Transaction Amount ------------- Account Balance");
		
		for (Transaction t : accountTr) {
			System.out.println(t.getTransactionType() + "                       " + t.getTransactionAmount() + "                         " + t.getAccountBalance());
		}
		
		return accountTr;
		
	}

	@Override
	public List<Transaction> viewAllTransactions() {
		
		// view all transactions similar to above
		return null;
	}

}
