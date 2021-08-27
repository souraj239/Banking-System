package com.authorizationservice.authorization.util;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest(classes = JwtUtil.class)
class JwtUtilTest {

	UserDetails userDetails;

	@Autowired
	JwtUtil jwtUtil;
    /**
     * Test method to check the whether token is generating.
     */
	@Test
	void generateTokenTest() {
		userDetails = new User("admin", "admin", new ArrayList<>());
		String generateToken = jwtUtil.generateToken(userDetails);
		assertNotNull(generateToken);
	}
	 /**
     * Test method to check the whether token is valid.
     */
	@Test
	void validateTokenTest() {
		userDetails = new User("admin", "admin", new ArrayList<>());
		String generateToken = jwtUtil.generateToken(userDetails);
		Boolean validateToken = jwtUtil.validateToken(generateToken, userDetails);
		assertEquals(true, validateToken);
	}
	 /**
     * Test method to validate token based on user details is correct.
     */
	@Test
	void validateTokenWithNameTest() {
		userDetails = new User("admin", "admin", new ArrayList<>());
		String generateToken = jwtUtil.generateToken(userDetails);
		Boolean validateToken = jwtUtil.validateToken(generateToken, userDetails);
		assertEquals(true, validateToken);
	}
	 /**
     * Test method to validate token based on wrong user details.
     */
	@Test
	void validateTokenWithNameFalseTest() {
		userDetails = new User("admin", "admin", new ArrayList<>());
		String generateToken = jwtUtil.generateToken(userDetails);
		Boolean validateToken = jwtUtil.validateToken(generateToken, userDetails);
		assertEquals(true, validateToken);
    }
}

