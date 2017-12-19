package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import com.revature.dao.ExpenseDAO;

public class ExpenseDAOTest {
	ExpenseDAO dao = null;
	
	@Before
	public void setup() {
		dao = new ExpenseDAO();
	}
	
	@Test
	public void wrongPasswordCantLogin() {
		assertEquals(dao.verifyLogin("1","asdf"),"invalid");
	}
	
	@Test
	public void nonEmployeeIdCantLogin() {
		assertEquals(dao.verifyLogin("111", "word"),"invalid");
	}
	
	@Test
	public void regularEmployeeReturnsUser() {
		assertEquals(dao.verifyLogin("1", "pass"), "user");
	}
	
	@Test
	public void adminLoginReturnsAdmin() {
		assertEquals(dao.verifyLogin("10", "word"), "admin");
	}
	
	@Test
	public void getReportsReturnsReportsIfExist() {
		assertNotEquals(dao.getReports(2), null);
	}
}
