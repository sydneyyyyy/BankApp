package com.porter.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.porter.beans.Account;
import com.porter.beans.Transaction;
import com.porter.utils.JDBCConnection;

public class TransactionDAOImpl implements TransactionDAO {

	public static Connection conn = JDBCConnection.getConnection();
	
	
	@Override
	public Transaction createTransaction() {
		
		Transaction t = new Transaction();
		
		try {
			
			String sql = "call create_transactions(?, ?, ?, ?, ?);";
			
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setInt(1, t.getAccountNumber());
			cs.setString(2, t.getTransactionType());
			cs.setDouble(3, t.getTransactionAmount());
			cs.setDouble(4, t.getAccountBalance());
			cs.setInt(5, t.getAccountId());
			
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@Override
	public List<Transaction> getAllUserTransactions(Integer i) {

		List<Transaction> userTr = new ArrayList<Transaction>();
		
		String sql = "select * from transactions where userId = ?;";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Transaction t = new Transaction();
				t.setAccountNumber(rs.getInt("accountNumber"));
				t.setTransactionType(rs.getString("transactionType"));
				t.setTransactionAmount(rs.getDouble("transactionAmount"));
				t.setAccountBalance(rs.getDouble("accountBalance"));
				
				userTr.add(t);
				
			}
			
			return userTr;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Transaction> getAllAccountTransactions(Account a) {
	
		List<Transaction> accountTr = new ArrayList<Transaction>();
		
		String sql = "select * from transactions where accountNumber = ?;";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, a.getAccountNumber());
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Transaction t = new Transaction();
				t.setTransactionType(rs.getString("transactionType"));
				t.setTransactionAmount(rs.getDouble("transactionAmount"));
				t.setAccountBalance(rs.getDouble("accountBalance"));
				
				accountTr.add(t);
				
			}
			
			return accountTr;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public List<Transaction> getAllTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
