package br.com.parking.service.actions.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.parking.DAO.AccesTokenDAO;
import br.com.parking.model.Employee;
import br.com.parking.model.comunication.AuthenticationData;
import br.com.parking.service.ServletRequestParametersExtractor;
import br.com.parking.service.actions.Action;
import messages.Message;
import myExceptions.InvalidLoginException;

public class GetAccessToken implements Action{

	@Override
	public Message execute(HttpServletRequest request, HttpServletResponse response) {
		ServletRequestParametersExtractor<AuthenticationData> extractor = new ServletRequestParametersExtractor<>(AuthenticationData.class);
		AuthenticationData auth = extractor.getRequestParameters(request);
		
		Message msg = new Message();
		
		try {
			AccesTokenDAO atDAO = new AccesTokenDAO();
			String token = atDAO.getToken(auth);
			
			msg.setTitle("Token");
			msg.setMessage(token);
			
		} catch (Exception e) {
			msg.setTitle("An error ocured");
			msg.setMessage(e.getMessage());
		}
		
		return msg;
	}
}
