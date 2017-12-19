package com.revature.beans;

public class ExpenseReport {
	private int id;
	private int amount;
	private String description;
	private String date;

	public ExpenseReport(int id, int amount, String description, String date) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.date = date;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return ("Amount: " + this.amount + ", For: " + this.description + ", On: " + this.date);
	}

}
