package com.cgi.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cgi.exception.NotFoundException;
import com.cgi.model.User;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	private User user1;
	
	@BeforeEach
	public void setUp() {
		user1 = new User("ranjith reddy","ranjith@gmail.in","9998887776","ran@123", false);
	}
	@Test
	public void testSave()
	{
		userRepository.save(user1);
		User usertest = userRepository.findById(user1.getUsername()).get();
		assertNotNull(usertest);
		assertEquals(user1.getPhone(),usertest.getPhone());
		userRepository.deleteAll();
	}
	
	@Test
	public void testFindAll()
	{
		User user2 = new User("sahana shree","sahanashree@gmail.in","9876598765","saha@321", false);
		User user3 = new User("ayisha b","ayishab@gmail.in","9988776655","ayisha@987", false);
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		List<User> users = (List<User>) userRepository.findAll();
		assertEquals("sahana shree", users.get(2).getUsername());
		userRepository.deleteAll();
	}
	
	@Test
	public void CheckingExistanceOfARecordForUpdatingTheMovieRecord()
	{
		userRepository.save(user1);
		User usertest = userRepository.findById(user1.getUsername()).get();
		assertNotNull(usertest);
		user1.setPhone("9988112233");
		userRepository.save(user1);
		userRepository.deleteAll();
	}
	
	@Test
	public void ValidateTest() throws NotFoundException
	{
		userRepository.save(user1);
		User usertest = userRepository.validate(user1.getUsername(), user1.getPassword());
		assertNotNull(usertest);
		assertEquals("ranjith reddy", usertest.getUsername());
		userRepository.deleteAll();
	}
	
	@Test
	public void testDeleteById()
	{
		userRepository.save(user1);
		boolean flag = userRepository.findById(user1.getUsername()).isPresent();
		assertTrue(flag);
		userRepository.deleteById(user1.getUsername());
		flag = userRepository.findById(user1.getUsername()).isPresent();
		assertFalse(flag);
		userRepository.deleteAll();
	}

}
