package br.com.parking.model;

public class CompanyEmployee{
	Company company;
	Employee employee;
	
	public CompanyEmployee() {
		company = new Company();
		employee = new Employee();
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
