package com.porter.services;

import java.util.ArrayList;
import java.util.List;

import com.porter.beans.Account;
import com.porter.beans.User;
import com.porter.repositories.BankAccountDAO;
import com.porter.repositories.BankAccountDAOImpl;

public class AccountServicesImpl implements AccountServices {
	
	public BankAccountDAO bdao = new BankAccountDAOImpl();

	@Override
	public boolean openAccount(User user) {
		boolean success = bdao.createAccount(user);
		return success;
	}
	
	@Override
	public List<Account> getAllUserAccounts() {
		List<Account> userAccounts = new ArrayList<Account>();
		userAccounts = bdao.getAllUserAccounts();
		System.out.println("--------------- Your Accounts -------------------");
		System.out.println("Account Number ------------ Balance ------------ Type");
		
		for (Account a : userAccounts) {
			System.out.println(a.getAccountNumber() + "              " + a.getBalance() + "              " + a.getType());
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
		return bdao.getAllAccountBalances();
	}

}
