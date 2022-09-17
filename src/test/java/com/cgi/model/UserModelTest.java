package com.cgi.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserModelTest {

	private User user;
	@BeforeEach
	public void setUp() throws Exception {
		user = new User();
		user.setEmail("ranjith@gmail.com");
		user.setUsername("ranjith");
		user.setPassword("ran123");
		user.setStatus(false);
		user.setPhone("9876543210");
		}
	@Test
	public void testGetEmail() {
		String expected ="ranjith@gmail.com";
		String actual = user.getEmail();
		assertEquals(expected,actual);
		
	}
	@Test
	public void testGetUsername() {
		String expected ="ranjith";
		String actual = user.getUsername();
		assertEquals(expected,actual);
		
	}
	
	@Test
	public void testGetPassword() {
		String expected ="ran123";
		String actual = user.getPassword();
		assertEquals(expected,actual);
		
	}
	@Test
	public void testGetMobileno() {
		String expected ="9876543210";
		String actual = user.getPhone();
		assertEquals(expected,actual);
		
	}
	@Test
	public void testGetStatus() {
		boolean expected = false;
		boolean actual = user.isStatus();
		assertEquals(expected,actual);
	}
	

}
