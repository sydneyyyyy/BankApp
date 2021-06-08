package com.porter.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.porter.beans.Account;
import com.porter.beans.User;
import com.porter.repositories.BankAccountDAO;
import com.porter.repositories.BankAccountDAOImpl;

public class AccountServicesImpl implements AccountServices {
	
	public BankAccountDAO bdao = new BankAccountDAOImpl();

	@Override
	public Account openAccount(Scanner scanner, User u) {
		Account a = new Account();
		a.setUserId(u.getId());
		System.out.println("Please enter a Account Number");
		a.setAccountNumber(scanner.nextInt());
		System.out.println("Please enter a starting balance: ");
		a.setBalance(scanner.nextDouble());
		System.out.println("Please select what type of account you are creating.." +
							"\n Checking " +
							"\n Savings ");
		a.setType(scanner.nextLine());
		
//		switch (type) {
//		
//			case "Checking" : {
//				a.setType("Checking");
//				break;
//			}
//			
//			case "Savings" : {
//				a.setType("Savings");
//				break;
//			}
//			
//		}
		
		
		bdao.createAccount(a);
		return a;
	}
	
	@Override
	public List<Account> getAllUserAccounts(Integer i) {
		List<Account> userAccounts = new ArrayList<Account>();
		userAccounts = bdao.getAllUserAccounts(i);
		
		System.out.println("--------------- Your Accounts -------------------");
		System.out.println("Account Number -- Balance ------------ Type");
		
		for (Account acct : userAccounts) {
			System.out.println(acct.getAccountNumber() + "              " + acct.getBalance() + "                " + acct.getType());
		}
		
		return userAccounts;
		
	}

	@Override
	public List<Account> getAllAccounts() {
		List<Account> allAccounts = new ArrayList<Account>();
		allAccounts = bdao.getAllAccounts();
		return allAccounts;
	}

	@Override
	public List<Account> getAllAccountBalances() {
		List<Account> allAccountBalances = new ArrayList<Account>();
		allAccountBalances = bdao.getAllAccountBalances();
		return allAccountBalances;
	}

}
