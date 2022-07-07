package br.com.parking.model.comunication;

public class AuthenticationData {
	private String login;
	private String password;
	
	public AuthenticationData(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Auth: [login: " + this.login + ", password: " + this.password + "]";
	}
	
	
}
