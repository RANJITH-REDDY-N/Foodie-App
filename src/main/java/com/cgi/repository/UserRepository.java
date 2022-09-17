package com.cgi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cgi.exception.NotFoundException;
import com.cgi.model.User;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, String> {
	
	@Query("select u from User u where u.username = (?1) and u.password = (?2)")
	User validate(String username, String password) throws NotFoundException;
	
}
 