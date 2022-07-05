package br.com.parking.service.actions.company;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.parking.DAO.CompanyDAO;
import br.com.parking.model.Company;
import br.com.parking.service.actions.Action;
import messages.Message;

public class CreateCompanyAction implements Action {
	@Override
	public Message execute(HttpServletRequest request, HttpServletResponse response) {
		Company comp = new Company();
		
//		comp.setCnpj(request.getParameter("cnpj"));
//		comp.setName(request.getParameter("name"));
//		comp.setPhone(request.getParameter("phone"));
//		
		comp.setCnpj("123456781212112");
		comp.setName("nomeaaaa");
		comp.setPhone("(11)2345-4567");
		
		CompanyDAO compDAO = new CompanyDAO();
		
		Message msg = new Message();
		
		try {
			VerifyCompany verif = new VerifyCompany();
			verif.verifyCreate(comp);
		} catch (Exception e) {
			msg.setTitle("An error Ocurred");
			msg.setMessage(e.getMessage());
			
			System.out.println(msg);
			return msg;
			
			
		}
		
		compDAO.insert(comp);
		
		msg.setTitle("Ok");
		
		System.out.println("bla");
		
		return msg;
	}

	
}
