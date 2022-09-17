package com.cgi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.cgi.exception.NotFoundException;
import com.cgi.model.Login;
import com.cgi.model.User;
import com.cgi.service.UserService;

@RunWith(SpringRunner.class)
@WebFluxTest(UserController.class)
class UserControllerTest {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void addUserTest() throws Exception {
		User user = new User("ram","ram@gmail.com","9888888888","ram3",false);
		when(userService.save(user)).thenReturn(null);
		webTestClient.post().uri("/api/grill/register").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().is4xxClientError();
	}
	
	@Test
	public void loginTest() throws NotFoundException
	{
		User user = new User("ram","ram@gmail.com","9888888888","ram3",false);
		when(userService.update(any())).thenReturn(user);
		webTestClient.put().uri("/api/grill/login").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().is4xxClientError();
		verify(userService, times(0)).update(any());
	}
	@Test
	public void logoutTest() throws NotFoundException
	{
		User user = new User("ram","ram@gmail.com","9888888888","ram3",false);
		when(userService.update(any())).thenReturn(user);
		webTestClient.put().uri("/api/grill/logout").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().is4xxClientError();
		verify(userService, times(0)).update(any());
	}
}
