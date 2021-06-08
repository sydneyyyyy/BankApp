package com.porter.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.porter.beans.Account;
import com.porter.utils.JDBCConnection;


public class BankAccountDAOImpl implements BankAccountDAO{
	
	public static Connection conn = JDBCConnection.getConnection();
	public static UserDAO udao = new UserDAO();
	


	@Override
	public Account createAccount(Account acct) {
		
		try {

			String sql = "call create_account(?, ?, ?, ?);";
			
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setInt(1, acct.getAccountNumber());
			cs.setDouble(2, acct.getBalance());
			cs.setString(3, acct.getType());
			cs.setInt(4, acct.getUserId());
			
			cs.execute();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return null;
	}

	@Override
	public List<Account> getAllAccounts() {
		
		List<Account> accounts = new ArrayList<Account>();
		
		try {
			
			String sql = "select * from accounts order by id;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("id"));
				a.setAccountNumber(rs.getInt("accountNumber"));
				a.setBalance(rs.getDouble("balance"));
				a.setType(rs.getString("type"));
				
				accounts.add(a);
			}
			
			return accounts;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return null;
	}

	@Override
	public Account updateAccount(Account a) {
		
		try {
			
			String sql = "update accounts set accountNumber = ?, balance = ?, type = ? where id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, a.getAccountNumber());
			ps.setDouble(2, a.getBalance());
			ps.setString(3, a.getType());
			
			ps.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return null;
	}

	@Override
	public List<Account> getAllUserAccounts(Integer i) {
		
		List<Account> accounts = new ArrayList<Account>();
		
		String sql = "select * from accounts where userId = ?;";
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("id"));
				a.setAccountNumber(rs.getInt("accountNumber"));
				a.setBalance(rs.getDouble("balance"));
				a.setType(rs.getString("type"));
				
				accounts.add(a);
			}
			
			return accounts;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return null;
	}


	@Override
	public boolean deleteAccount(Account a) {
		
		String sql = "delete from accounts where id = ?;";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getId());
			
			boolean success = ps.execute();
			
			if (success) {
				return true;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return false;
	}

	@Override
	public List<Account> getAllAccountBalances() {
		
		List<Account> accounts = new ArrayList<Account>();
		
		try {
			
			String sql = "select * from accounts where id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Account a = new Account();
				ps.setInt(1, a.getId());
				ps.setDouble(2, a.getBalance());
				
				accounts.add(a);
				
			}
			
			return accounts;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return null;
	}

}
