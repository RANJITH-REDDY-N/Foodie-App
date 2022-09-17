package com.cgi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cgi.exception.AlreadyExistsException;
import com.cgi.exception.NotFoundException;
import com.cgi.model.User;

@Service
public interface UserService {

	public User save(User user) throws AlreadyExistsException;
	public User update(User user) throws NotFoundException;
	public List<User> usersList();
	public boolean validate(String username,String password) throws NotFoundException;
	public Optional<User> get(String username) throws NotFoundException;
	public boolean exists(String username);
}
