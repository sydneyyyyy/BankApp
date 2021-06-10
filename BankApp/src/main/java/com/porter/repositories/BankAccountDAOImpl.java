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
			cs.setString(3, acct.getIsApproved());
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
			
			String sql = "select * from accounts order by accountId;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Account a = new Account();
				a.setAccountId(rs.getInt("accountId"));
				a.setAccountNumber(rs.getInt("accountNumber"));
				a.setBalance(rs.getDouble("balance"));
				a.setIsApproved(rs.getString("isApproved"));
				
				accounts.add(a);
			}
			
			return accounts;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return null;
	}
	
	@Override
	public List<Account> getAllPendingAccounts(String isApproved) {
		
		List<Account> allPendingAccounts = new ArrayList<Account>();
		
		try {
			
			String sql = "select * from accounts where isApproved = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, isApproved);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Account a = new Account();
				a.setAccountId(rs.getInt("accountId"));
				a.setAccountNumber(rs.getInt("accountNumber"));
				a.setBalance(rs.getDouble("balance"));
				a.setIsApproved(rs.getString("isApproved"));
				
				allPendingAccounts.add(a);
			}
			
			return allPendingAccounts;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Account updateAccountBalance(Account a) {
		
		try {
			
			String sql = "update accounts set balance = ? where accountNumber = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, a.getBalance());
			ps.setInt(2, a.getAccountNumber());
			
			ps.execute();
			
			return a;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return null;
	}
	
	@Override
	public Account updateAccountApproved(Account a) {
		
		try {
			String sql = "update accounts set isApproved = ? where accountNumber = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, a.getIsApproved());
			ps.setInt(2, a.getAccountNumber());
			
			ps.execute();
			
			return a;
			
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
				a.setAccountId(rs.getInt("accountId"));
				a.setAccountNumber(rs.getInt("accountNumber"));
				a.setBalance(rs.getDouble("balance"));
				
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
		
		String sql = "delete from accounts where accountId = ?;";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getAccountId());
			
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
			
			String sql = "select * from accounts where accountId = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Account a = new Account();
				ps.setInt(1, a.getAccountId());
				ps.setDouble(2, a.getBalance());
				
				accounts.add(a);
				
			}
			
			return accounts;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return null;
	}

	@Override
	public Account getAccountByAccountNumber(int accountNumber) {
		
		String sql = "select * from accounts where accountnumber = ?;";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accountNumber);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				Account a = new Account();
				
				a.setAccountId(rs.getInt("accountId"));
				a.setAccountNumber(rs.getInt("accountNumber"));
				a.setBalance(rs.getDouble("balance"));
				a.setIsApproved(rs.getString("isApproved"));
				a.setUserId(rs.getInt("userId"));
				
				return a;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return null;
	}

	@Override
	public List<Account> getAllPendingAccounts() {
		
		List<Account> pendingAccounts = new ArrayList<Account>();
		
		String sql = "select * from accounts where isApproved = ?;";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, accountId);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Account a = new Account();
				a.setAccountId(rs.getInt("accountId"));
				a.setAccountNumber(rs.getInt("accountNumber"));
				a.setBalance(rs.getDouble("balance"));
				a.setIsApproved(rs.getString("isApproved"));
				
				pendingAccounts.add(a);
				
			}
			
			return pendingAccounts;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}



	

}
