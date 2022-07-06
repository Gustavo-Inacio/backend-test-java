package br.com.parking.service.actions.company;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.parking.DAO.CompanyDAO;
import br.com.parking.DAO.EmployeeDAO;
import br.com.parking.model.Company;
import br.com.parking.model.CompanyEmployee;
import br.com.parking.model.Employee;
import br.com.parking.service.ServletRequestParametersExtractor;
import br.com.parking.service.actions.Action;
import br.com.parking.service.actions.employee.VerifyEmployee;
import messages.Message;
import myExceptions.UniqueDataAlreadyExists;

public class CreateCompanyAction implements Action {
	@Override
	public Message execute(HttpServletRequest request, HttpServletResponse response) {
		Company comp = new Company();
		Employee owner = new Employee();
		Gson gson = new Gson();
		
		System.out.println(request.getAttribute("bodyData"));
		
		//CompanyEmployee compEmp = gson.fromJson((String) request.getAttribute("bodyData/json"), CompanyEmployee.class);
		ServletRequestParametersExtractor<CompanyEmployee> srpe = new ServletRequestParametersExtractor<>(CompanyEmployee.class);
		CompanyEmployee compEmp = srpe.getRequestParameters(request);
		comp = compEmp.getCompany();
		owner = compEmp.getEmployee();
		owner.setComp(comp);
		
		System.out.println("[comp name] " + comp.getName());
		System.out.println("[emp name] " + owner.getName());
				
		CompanyDAO compDAO = new CompanyDAO();
		EmployeeDAO empDAO = new EmployeeDAO();
		
		Message msg = new Message();
		
		System.out.println(comp.getCnpj());
		
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
		try {
			empDAO.insert(owner);
		} catch (UniqueDataAlreadyExists e) {
			msg.setTitle("An error ocurred");
			msg.setMessage(e.getMessage());
			return msg;
		}
		
		msg.setTitle("Ok");
		
		return msg;
	}
	
	private Object getRequestParameters(HttpServletRequest request) {
		if(request.getAttribute("bodyData") != null) {
			String requestData = (String) request.getAttribute("bodyData");
			String header = request.getHeader("Accept");
			
			if(header.endsWith("json")) {
				return this.getJsonParameters(requestData);
			}
		}
		
		return null;
	}
	
	private CompanyEmployee getJsonParameters(String requestData) {
		Gson gson = new Gson();
		CompanyEmployee compEmp = gson.fromJson((String) requestData, CompanyEmployee.class);
		
		return compEmp;
	}

	
}
