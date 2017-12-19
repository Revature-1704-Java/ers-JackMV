package com.revature;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.ExpenseReport;
import com.revature.dao.ExpenseDAO;


public class ERS {
	static Scanner s = new Scanner(System.in);
	static ExpenseDAO dao = new ExpenseDAO();
	
	public static void main(String[] args) {
		System.out.println("Welcome to the Expense Reimbursment Portal.\nPlease enter your employee ID and password to login.");
		login();
	}
	
	private static void login() {
		String empID;
		String pass;
		String loginStatus;
		while(true) {
			System.out.print("Employee ID: ");
			empID = s.nextLine();
			System.out.print("\nPassword: ");
			pass = s.nextLine();
			loginStatus = dao.verifyLogin(empID, pass);
			System.out.println(loginStatus);
			if (loginStatus.equals("exception")) {
				System.out.println("An error occured, please try again.");
				continue;
			} else if (loginStatus.equals("invalid")) {
				System.out.println("Invalid username or password, please try again.");
				continue;
			} else if (loginStatus.equals("admin")) {
				adminAccess();
				break;
			} else {
				userAccess(empID);
				break;
			}
		}
	}

	private static void userAccess(String id) {
		while(true) {
			int amount;
			String description;
			System.out.println("Welcome, to submit a new expense report, please enter new.");
			String input = s.nextLine();
			if(input.equals("new")) {
				System.out.print("Please enter the amount of your reimbursement: ");
				amount = Integer.parseInt(s.nextLine());
				System.out.print("Please enter a description for your reimbursement request: ");
				description = s.nextLine();
				dao.createExpenseReport(id, amount, description);
			}
			
		}
		
	}

	private static void adminAccess() {
		while(true) {
			List<ExpenseReport> reports = new ArrayList<>();
			System.out.println("To view expense reports, please type view followed by an employee number.");
			String input = s.next();
			int employeeID;
			if(input.equals("view")) {
				employeeID = Integer.parseInt(s.next());
				reports = dao.getReports(employeeID);
				for (ExpenseReport r : reports) {
					System.out.println(r);
				}
			}
		}
		
	}
	
	
}
