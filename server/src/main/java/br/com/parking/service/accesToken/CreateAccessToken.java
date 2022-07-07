package br.com.parking.service.accesToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.parking.DAO.AccesTokenDAO;
import br.com.parking.model.Employee;
import br.com.parking.service.ServletRequestParametersExtractor;
import br.com.parking.service.actions.Action;
import messages.Message;
import myExceptions.UniqueDataAlreadyExists;

public class CreateAccessToken implements Action{
	@Override
	public Message execute(HttpServletRequest request, HttpServletResponse response) {
		ServletRequestParametersExtractor<Employee> extractor = new ServletRequestParametersExtractor<>(Employee.class);
		Employee emp = extractor.getRequestParameters(request);
		
		Message msg = new Message("ok", "ok");
		
		try {
			String token = this.create(emp);
			msg.setMessage("{token: "+token+"}");
		} catch (Exception e) {
			msg.setTitle("An error ocured.");
			msg.setMessage(e.getMessage());
			
			return msg;
		}
		
		
		
		return msg;
	}
	
	public String create(Employee emp) throws UniqueDataAlreadyExists {
		AccesTokenDAO atDAO = new AccesTokenDAO();
		String token = atDAO.insert(emp);
		
		return token;
	}
}
