package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.revature.beans.ExpenseReport;
import com.revature.util.ConnectionUtil;

public class ExpenseDAO {

	public ExpenseDAO() {
		super();
	}

	public String verifyLogin(String employeeID, String password) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String actualPassword = null;
		String permissionLevel = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(employeeID));
			
			rs = ps.executeQuery();
			while(rs.next()) {
				actualPassword = rs.getString("PASSWORD");
				permissionLevel = rs.getString("EMPLOYEE_PERMISSIONS");
			}			
			if (password.equals(actualPassword)) {
				return permissionLevel;
			} else {
				return "invalid";
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return "exception";
		} catch (IOException ex) {
			ex.printStackTrace();
			return "exception";
		}
		
	}

	public void createExpenseReport(String id, int amount, String description) {
		PreparedStatement ps = null;
		Calendar c = Calendar.getInstance();
		Timestamp time = new Timestamp(c.getTime().getTime());
		String timeString = time.toString();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, DESCRIPTION, EXPENSEDATE) VALUES (?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setInt(2, amount);
			ps.setString(3, description);
			ps.setString(4, timeString);
			int debug;
			debug = ps.executeUpdate();
			System.out.println(debug);
			
		} catch (Exception ex) {
			ex.getMessage();
		}
		
	}

	public List<ExpenseReport> getReports(int employeeID) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ExpenseReport> reports = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REIMBURSEMENT WHERE EMPLOYEE_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, employeeID);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				int reimbursementId = rs.getInt("REIMBURSEMENT_ID");
				int amount = rs.getInt("AMOUNT");
				String description = rs.getString("DESCRIPTION");
				String date = rs.getString("EXPENSEDATE");
				reports.add(new ExpenseReport(reimbursementId, amount, description, date));
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.getMessage();
		}
		return reports;
		
	}

}
