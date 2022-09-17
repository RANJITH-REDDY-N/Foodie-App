package com.cgi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.exception.AlreadyExistsException;
import com.cgi.exception.NotFoundException;
import com.cgi.model.User;
import com.cgi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public List<User> usersList() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public boolean validate(String username, String password) throws NotFoundException {
		if(userRepository.validate(username, password)!=null)
		{
			return true;
		}
		else {
			throw new NotFoundException();
		}
	}

	@Override
	public User save(User user) throws AlreadyExistsException {
		if(userRepository.existsById(user.getUsername()))
		{
			throw new AlreadyExistsException();
		}
		return userRepository.save(user);
	}

	@Override
	public User update(User user) throws NotFoundException {
		if(userRepository.existsById(user.getUsername()))
		{
			return userRepository.save(user);
		}
		else {
			throw new NotFoundException();
		}
	}
	@Override
	public Optional<User> get(String username) throws NotFoundException {
		if(!(userRepository.existsById(username)))
		{
			throw new NotFoundException();
		}
		else {
			return userRepository.findById(username);
		}

	}

	@Override
	public boolean exists(String username) {
		if(!(userRepository.existsById(username)))
		{
			return false;
		}
		else {
			return true;
		}
	}
	

}
