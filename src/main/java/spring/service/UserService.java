package spring.service;

import org.springframework.stereotype.Component;

import spring.model.Login;
import spring.model.User;

@Component
public class UserService {

	public void register(User user) {
		// NONE
	}

	public User login(Login login) {
		User user = new User();
		user.setLogin(login);
		return user;
	}

}
