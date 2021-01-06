package spring.validation.custom;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import spring.model.User;
import spring.validation.ErrorField;
import spring.validation.Validator;

@Component
public class UserValidator extends Validator<User> {
	
	public List<ErrorField> validate(User user) {
		List<ErrorField> errors = new ArrayList<>();
		errors.add(validateLoginUsername("login.username", user.getLogin().getUsername()));
		errors.add(validateLoginUsername("login.password", user.getLogin().getPassword()));
		errors.add(validateLoginUsername("firstname[0]", user.getFirstname().get(0)));
		errors.add(validateLoginUsername("firstname[1]", user.getFirstname().get(1)));
		errors.add(validateLoginUsername("lastname", user.getLastname()));
		errors.add(validateLoginUsername("age", user.getAge()));
		return errors;
	}

	public ErrorField validateLoginUsername(String field, String username) {
		ErrorField error = null;
		if("".equals(username.trim())) {
			error = new ErrorField(field, getMessage("register.username.empty"));
		}
		return error;
	}
	
	public ErrorField validateLoginPassword(String field, String password) {
		ErrorField error = null;
		if("".equals(password.trim())) {
			error = new ErrorField(field, getMessage("register.password.empty"));
		}
		return error;
	}
	
	public ErrorField validateFirstname(String field, String firstname) {
		ErrorField error = null;
		if("".equals(firstname.trim())) {
			error = new ErrorField(field, getMessage("register.firstname.empty"));
		}
		return error;
	}
	
	public ErrorField validateLastname(String field, String lastname) {
		ErrorField error = null;
		if("".equals(lastname.trim())) {
			error = new ErrorField(field, getMessage("register.lastname.empty"));
		}
		return error;
	}
	
	public ErrorField validateAge(String field, String age) {
		ErrorField error = null;
		if("".equals(age.trim())) {
			error = new ErrorField(field, getMessage("register.age.empty"));
		}
		return error;
	}

}
