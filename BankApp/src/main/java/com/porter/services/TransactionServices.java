package com.porter.services;

import java.util.Scanner;

import com.porter.beans.Account;
import com.porter.beans.User;

public interface TransactionServices {

	public Account makeDeposit(Scanner scanner, User u);
}
