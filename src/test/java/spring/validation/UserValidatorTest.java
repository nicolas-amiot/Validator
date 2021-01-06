/**
 * 
 */
package spring.validation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.MessageSource;

import spring.model.Login;
import spring.model.User;
import spring.validation.custom.UserValidator;

/**
 * @author Nicolas
 *
 */
class UserValidatorTest {
	
	@Spy
	@InjectMocks
	private UserValidator validator;
	
	@Mock
	private MessageSource messageSource;
	
	@BeforeEach
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
    }

	@Test
	public void errorFieldTest() {
		/*
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(messageSource).getMessage(captor.capture(), Mockito.any(), Mockito.any());
		assertEquals("register.lastname.empty", captor.getValue());
		*/
		Mockito.when(messageSource.getMessage(Mockito.eq("register.lastname.empty"), Mockito.any(), Mockito.any())).thenReturn("Empty lastname");
		ErrorField error = validator.validateLastname("lastname", "");
		assertNotNull(error);
		assertEquals("lastname", error.getField());
		assertEquals("Empty lastname", error.getMessage());
	}
	
	@Test
	public void successFieldTest() {
		ErrorField error = validator.validateLastname("lastname", "myLastname");
		assertNull(error);
	}
	
	@Test
	public void validateFormTest() {
		User user = new User();
		user.setLogin(new Login());
		user.setFirstname(new ArrayList<String>());
		user.getLogin().setUsername("");
		user.getLogin().setPassword("");
		user.getFirstname().add("");
		user.getFirstname().add("");
		user.setLastname("");
		user.setAge("");
		List<ErrorField> errors = validator.validate(user);
		assertEquals(6, errors.size());
	}
	
	@Test
	public void validateErrorTest() {
		ErrorField error = validator.validate("erreur", "404");
		assertNull(error);
	}
	
	@Test
	public void validateSimpleTest() {
		ErrorField error = validator.validate("age", "20");
		Mockito.verify(validator).validateAge("age", "20");
		assertNull(error);
	}
	
	@Test
	public void validateObjectTest() {
		ErrorField error = validator.validate("login.username", "myUsername");
		Mockito.verify(validator).validateLoginUsername("login.username", "myUsername");
		assertNull(error);
	}
	
	@Test
	public void validateArrayTest() {
		ErrorField error = validator.validate("firstname[0]", "myFirstname");
		Mockito.verify(validator).validateFirstname("firstname[0]", "myFirstname");
		assertNull(error);
	}

}
