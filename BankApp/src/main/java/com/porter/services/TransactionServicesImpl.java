package com.porter.services;

import java.util.Scanner;

import com.porter.beans.Account;
import com.porter.beans.User;
import com.porter.repositories.BankAccountDAO;
import com.porter.repositories.BankAccountDAOImpl;

public class TransactionServicesImpl implements TransactionServices {

	public static Scanner scanner = new Scanner(System.in);
	public AccountServices as = new AccountServicesImpl();
	public BankAccountDAO bdao = new BankAccountDAOImpl();
	
	private static double depositAmount = 0;
	private static double accountBalance = 0;
	
	@Override
	public Account makeDeposit(Scanner scanner, User u) {
		// list user accounts to select from
		System.out.println("\nPlease type the account number you'd like to make a deposit to.");
		as.getAllUserAccounts(u.getId());
		Integer input = scanner.nextInt();
		// take user input 
		Account acctNum = bdao.getAccountByAccountNumber(input);
		System.out.println(acctNum);
		
		System.out.println("\nHow much would you like to deposit?");
		depositAmount = scanner.nextDouble();
		accountBalance = acctNum.getBalance() + depositAmount;
		System.out.println(accountBalance);
		acctNum.setBalance(accountBalance);
		bdao.updateAccount(acctNum);
		
		// send information to the tdao
		// return account 
		return acctNum;
	}

}
