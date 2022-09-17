package com.cgi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.exception.AlreadyExistsException;
import com.cgi.exception.NotFoundException;
import com.cgi.model.Login;
import com.cgi.model.User;
import com.cgi.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/grill")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	@GetMapping
	public ResponseEntity<List<User>> listAllUsers() {
		return new ResponseEntity<List<User>>(userService.usersList(), HttpStatus.OK);
	}

	@GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<User>> getUser(@PathVariable("username") String username) throws NotFoundException {
		
		Optional<User> user = userService.get(username);
		if (user == null) {
			return new ResponseEntity<Optional<User>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Optional<User>>(user,HttpStatus.OK);
	}

	//Register
	@PostMapping("/register")
	public ResponseEntity<User> createUser(@RequestBody User user) throws AlreadyExistsException {
		if (userService.exists(user.getUsername())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		else{
		userService.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	}
	
	//Login
	@PutMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Login user) throws NotFoundException {
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
				return new ResponseEntity<User>(user1, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Already Logged In", HttpStatus.CONFLICT);
			}
		}
		else {
			return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
		}
		
			
		}

	
//	Logout
	@PutMapping("/logout")
	public ResponseEntity<?> logout(@RequestBody Login user) throws NotFoundException {
		
		Optional<User> optional = userService.get(user.getUsername());
		User user1 = optional.get();
		if (!userService.exists(user.getUsername())) {
			return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
		}
		else{
			if(user1.isStatus()) 
			{
				user1.setStatus(false);
				userService.update(user1);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("User not logged out", HttpStatus.CONFLICT);
			}
		}
			
		}

}
