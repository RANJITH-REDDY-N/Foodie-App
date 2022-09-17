package com.cgi.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.cgi.exception.AlreadyExistsException;
import com.cgi.exception.NotFoundException;
import com.cgi.model.User;
import com.cgi.repository.UserRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	
	@Test
	public void testUsersList() {
		User user1 = new User("ranjith reddy","ranjith@reddy.com","9876554321","ran123",false);
		User user2 = new User("sahana Shree","sahana@bm.com","9821887722","sahaa123",false);
		User user3 = new User("ayisha b","ayisha@b.com","9876556789","ayisha123",false);
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		List<User> usersList = new ArrayList<User>();
		usersList.add(user1);
		usersList.add(user2);
		usersList.add(user3);
		when(userRepository.findAll()).thenReturn(usersList);
		List<User> testList = userServiceImpl.usersList();
		assertTrue(testList.get(1).getPhone()=="9821887722");
		assertEquals(usersList,testList);
		verify(userRepository,times(1)).save(user2);
		verify(userRepository,times(1)).findAll();
	}
	
	@Test
	public void testSave() throws AlreadyExistsException
	{
		User user1 = new User("ranjith reddy","ranjith@reddy.com","9876554321","ran123",false);
		when(userRepository.save(any())).thenReturn(user1);
		userServiceImpl.save(user1);
		verify(userRepository,times(1)).save(any());
	}

	@Test
	public void testUpdate() throws AlreadyExistsException, NotFoundException 
	{
		User user1 = new User("ranjith reddy","ranjith@reddy.com","9876554321","ran123",false);
		when(userRepository.save(any())).thenReturn(user1);
		userServiceImpl.save(user1);
		when(userRepository.existsById(any())).thenReturn(true);
		User usertest = new User("ranjith reddy","ranjith@reddy.com","9876554321","ran123",true);
		userServiceImpl.update(usertest);
		verify(userRepository,times(1)).save(usertest);
	}

	@Test
	public void testValidate() throws AlreadyExistsException, NotFoundException
	{
		User user1 = new User("ranjith reddy","ranjith@reddy.com","9876554321","ran123",false);
		when(userRepository.save(any())).thenReturn(user1);
		userServiceImpl.save(user1);
		when(userRepository.validate(any(), any())).thenReturn(user1);
		boolean flag = userServiceImpl.validate("ranjith reddy", "ran123");
		assertTrue(flag);
		verify(userRepository,times(1)).validate("ranjith reddy","ran123");
	}
	
	@Test
	public void testExists() throws AlreadyExistsException, NotFoundException
	{
		User user1 = new User("ranjith reddy","ranjith@reddy.com","9876554321","ran123",false);
		when(userRepository.save(any())).thenReturn(user1);
		userServiceImpl.save(user1);
		when(userRepository.existsById(any())).thenReturn(true);
		boolean flag = userServiceImpl.exists(user1.getUsername());
		assertTrue(flag);
	}
}
