package com.porter.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.porter.beans.Account;
import com.porter.beans.User;
import com.porter.repositories.BankAccountDAO;
import com.porter.repositories.BankAccountDAOImpl;
import com.porter.utils.AppLogger;

public class AccountServicesImpl implements AccountServices {
	
	
	public BankAccountDAO bdao = new BankAccountDAOImpl();
	private Scanner scanner = new Scanner(System.in);
	private static Account updatedAccount;
	private static String adminInput;
	

	@Override
	public Account openAccount(Scanner scanner, User u) {
		Account a = new Account();
		a.setUserId(u.getId());
		a.setIsApproved("false");
		System.out.println("Please enter a Account Number");
		a.setAccountNumber(scanner.nextInt());
		System.out.println("Please enter a starting balance: ");
		a.setBalance(scanner.nextDouble());
		
		bdao.createAccount(a);
		AppLogger.logger.info(u.getFirstName() + " has created account " + a.getAccountNumber() + "!");
		return a;
	}

	
	@Override
	public List<Account> getAllUserAccounts(Integer i) {
		List<Account> userAccounts = new ArrayList<Account>();
		userAccounts = bdao.getAllUserAccounts(i);
		
		System.out.println("\n--------- Your Accounts ----------");
		System.out.println("Account Number ------------- Balance");
		
		for (Account acct : userAccounts) {
			System.out.println(acct.getAccountNumber() + "                           " + acct.getBalance());
		}
		
		return userAccounts;
		
	}

	@Override
	public List<Account> getAllAccounts() {
		List<Account> allAccounts = new ArrayList<Account>();
		allAccounts = bdao.getAllAccounts();
		
		System.out.println("\n---------- User Accounts ----------");
		System.out.println("Account Number ------ Account Balance");
		
		for (Account a : allAccounts) {
			System.out.println(a.getAccountNumber() + "                 " + a.getBalance());
		}
		return allAccounts;
	}

	@Override
	public List<Account> getAllAccountBalances() {
		List<Account> allAccountBalances = new ArrayList<Account>();
		allAccountBalances = bdao.getAllAccountBalances();
		return allAccountBalances;
	}


	@Override
	public List<Account> getAllPendingAccounts() {
		
		List<Account> allPendingAccounts = new ArrayList<Account>();
		String notApproved = "false";
		allPendingAccounts = bdao.getAllPendingAccounts(notApproved);
		
		System.out.println("\n----------------- Accounts Pending Approval --------------------");
		System.out.println("Account Id ---- Account Number ---- Account Balance ---- isApproved");
		
		for (Account a : allPendingAccounts) {
			System.out.println(a.getAccountId() + "               " + 
								a.getAccountNumber() + "                " +
								a.getBalance() + "                " + 
								a.getIsApproved());
			
			
		}
		
		return allPendingAccounts;
		
	}
		
	@Override
	public Account updateAccountApproved() {
		
		System.out.println("Which account would you like to adjust? Enter an account number.");
		getAllPendingAccounts();
		int accountNum = scanner.nextInt();
		
		Account firstAccount = bdao.getAccountByAccountNumber(accountNum);


		System.out.println("Are you sure you would like to approve this account? (y/n)");
		adminInput = scanner.nextLine();
		
		
		if (adminInput != "n") {
			firstAccount.setIsApproved("true");
			bdao.updateAccountApproved(firstAccount);
			System.out.println(firstAccount);
			getAllPendingAccounts();
		}
		
		
		AppLogger.logger.info(firstAccount.getAccountNumber() + " was approved by the Bank.");
		
		return updatedAccount;
		
		
	}

		
		
		
}
