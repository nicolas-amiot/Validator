package spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import spring.model.User;
import spring.service.UserService;
import spring.validation.ErrorField;
import spring.validation.custom.UserValidator;


@Controller
@RequestMapping(value = "/register")
public class RegistrationController {
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public UserValidator userValidator;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("register");
		model.addObject("user", new User());
		return model;
	}
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) {
		if(userValidator.validate(user).isEmpty()) {
			userService.register(user);
			return new ModelAndView("welcome", "login", user.getLogin().getUsername());
		} else {
			ModelAndView model = new ModelAndView("redirect:/register");
			return model;
		}
	}
	
	@RequestMapping(value = "/validation", method = RequestMethod.GET)
	public @ResponseBody ErrorField validate(@RequestParam String field, @RequestParam String value, HttpServletRequest request, HttpServletResponse response) {
		/* With org.json */
		// ErrorField error = userValidator.validate(field, value);
		// if(error != null) {
		// 	  JSONObject jsonObject = new JSONObject(error);
		// 	  return jsonObject.toString();
		// } else {
		// 	  return null;
		// }
		/* With Jackson */
		return userValidator.validate(field, value);
	}

	@RequestMapping(value = "/validation", method = RequestMethod.POST)
	public @ResponseBody List<ErrorField> validate(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
		return userValidator.validate(user);
	}
	
}