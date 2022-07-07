package br.com.parking.model.comunication;

import br.com.parking.model.Company;
import br.com.parking.model.Employee;

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
