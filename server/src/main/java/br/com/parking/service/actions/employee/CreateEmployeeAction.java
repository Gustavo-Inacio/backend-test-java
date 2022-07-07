package br.com.parking.service.actions.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.parking.DAO.EmployeeDAO;
import br.com.parking.model.Employee;
import br.com.parking.service.ServletRequestParametersExtractor;
import br.com.parking.service.accesToken.CreateAccessToken;
import br.com.parking.service.actions.Action;
import messages.Message;
import myExceptions.MissingDataException;
import myExceptions.UniqueDataAlreadyExists;

public class CreateEmployeeAction implements Action {

	@Override
	public Message execute(HttpServletRequest request, HttpServletResponse response) {
		ServletRequestParametersExtractor<Employee> srpe = new ServletRequestParametersExtractor<>(Employee.class);
		Employee emp = srpe.getRequestParameters(request);
		
		Message msg = new Message();
		
		try {
			String token = this.create(emp);
			msg.setTitle("Emplye Created and this is your token");
			msg.setMessage(token);
			
		} catch (Exception e) {
			msg.setTitle("An error ocured");
			msg.setMessage(e.getMessage());
		}
		
		
		return msg;
	}
	
	public String create(Employee emp) throws UniqueDataAlreadyExists, MissingDataException {
		VerifyEmployee verif = new VerifyEmployee();
		verif.verifyCreate(emp);
		
		System.out.println("creatingggggggggggg");
			
		EmployeeDAO empDAO = new EmployeeDAO();
		empDAO.insert(emp);
		
		CreateAccessToken cat = new CreateAccessToken();
		String token = cat.create(emp);

		return token;
	}

	
}
