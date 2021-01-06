package spring.model;

import java.util.List;

public class User {

	private Login login;
	private List<String> firstname;
	private String lastname;
	private String age;

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<String> getFirstname() {
		return firstname;
	}

	public void setFirstname(List<String> firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
