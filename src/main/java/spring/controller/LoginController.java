package spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.model.Login;
import spring.model.User;
import spring.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("login");
		model.addObject("login", new Login());
		return model;
	}

	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("login") Login login) {
		ModelAndView model = null;
		User user = userService.login(login);
		if (user != null) {
			model = new ModelAndView("welcome");
			model.addObject("login", user.getLogin().getUsername());
		} else {
			model = new ModelAndView("login");
			model.addObject("message", "Username or Password is wrong!!");
		}
		return model;
	}
}