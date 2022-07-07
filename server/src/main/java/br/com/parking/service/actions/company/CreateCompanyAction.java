package br.com.parking.service.actions.company;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.parking.DAO.CompanyDAO;
import br.com.parking.model.Company;
import br.com.parking.model.Employee;
import br.com.parking.model.comunication.CompanyEmployee;
import br.com.parking.service.ServletRequestParametersExtractor;
import br.com.parking.service.actions.Action;
import br.com.parking.service.actions.employee.CreateEmployeeAction;
import br.com.parking.service.actions.employee.VerifyEmployee;
import messages.Message;


public class CreateCompanyAction implements Action {
	@Override
	public Message execute(HttpServletRequest request, HttpServletResponse response) {
		Company comp = new Company();
		Employee owner = new Employee();
	
		ServletRequestParametersExtractor<CompanyEmployee> srpe = new ServletRequestParametersExtractor<>(CompanyEmployee.class);
		CompanyEmployee compEmp = srpe.getRequestParameters(request);
		
		comp = compEmp.getCompany();
		owner = compEmp.getEmployee();
		owner.setComp(comp);


		CompanyDAO compDAO = new CompanyDAO();
		
		Message msg = new Message();
		
		try {
			VerifyCompany verif = new VerifyCompany();
			VerifyEmployee verifEmp = new VerifyEmployee();
			verif.verifyCreate(comp);
			verifEmp.verifyCreate(owner);
		} catch (Exception e) {
			msg.setTitle("An error Ocurred");
			msg.setMessage(e.getMessage());
			
			return msg;
		}
		
		compDAO.insert(comp);
		CreateEmployeeAction createEmp = new CreateEmployeeAction();
		try {
			String token = createEmp.create(owner);
			
			msg.setTitle("Company and User created. Your acces token is:");
			msg.setMessage(token);
		} catch (Exception e) {
			msg.setTitle("An error ocurred");
			msg.setMessage(e.getMessage());
			return msg;
		}
		
		return msg;
	}
}
