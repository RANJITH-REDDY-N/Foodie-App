package com.cgi.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.model.User;
import com.cgi.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserAuthenticateController {
	static final long EXPIRATIONTIME = 300000;
	Map<String, String> map = new HashMap<>();

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String serverStarted() {
		return "Authentication server started";
	}


	@PutMapping("login")
	public ResponseEntity<?> login(@RequestBody User user) throws ServletException {

		String jwtToken = "";

		try {
			jwtToken = getToken(user.getUsername(), user.getPassword());
			map.clear();
			map.put("message", "user successfully logged in");
			map.put("token", jwtToken);
			
			Optional<User> optional = userService.get(user.getUsername());
			User user1 = optional.get();
			if (!userService.exists(user.getUsername())) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
			else if(userService.validate(user.getUsername(),user.getPassword())){
				if(!user1.isStatus()) 
				{
					user1.setStatus(true);
					userService.update(user1);
//					return new ResponseEntity<User>(user1, HttpStatus.OK);
				}}
				
		} catch (Exception e) {
			String exceptionMessage = e.getMessage();
			map.clear();
			map.put("token", null);
			map.put("message", exceptionMessage);
			return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	
	

	public String getToken(String username, String password) throws Exception {

		if (username == null || password == null) {
			throw new ServletException("Please fill in username and password");
		}

		boolean flag = userService.validate(username, password);
         		if (!flag) {
			throw new ServletException("Invalid credentials.");
		}
		

		String jwtToken = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

		
		
		return jwtToken;

	}

}
