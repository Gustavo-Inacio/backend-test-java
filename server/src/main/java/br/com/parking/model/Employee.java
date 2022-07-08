package br.com.parking.model;

public class Employee extends Model{
	private String name;
	private String login;
	private String password;
	private Company company;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Company getComp() {
		return company;
	}
	public void setComp(Company comp) {
		this.company = comp;
	}
	
}
