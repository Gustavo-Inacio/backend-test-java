package br.com.parking.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.parking.model.Company;
import br.com.parking.service.AuthenticationValidation;
import messages.Message;
import myExceptions.InvalidCredentialsException;
import myExceptions.MissingDataException;

//@WebFilter("/*")
public class AuthorizationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		List<String> freeActions = new ArrayList<String>();
		freeActions.add("/company/create");
		freeActions.add("/employee/accessToken");
		
		String projectName = "/ParkingLot";
		String URI = request.getRequestURI();
		String path = URI.replace(projectName, "");
		
		if(freeActions.contains(path)) {
			chain.doFilter(request, response);
			return;
		}
		
		Message msg = new Message();
		
		msg = this.filtering(request, response);
		if(msg != null) {
			String json = this.generateJsonToReturn(msg);
			response.setContentType("application/json");
			response.getWriter().print(json);
			return;
		}
		
		chain.doFilter(request, response);
		

	}
	
	private Message filtering(HttpServletRequest request, HttpServletResponse response) {
		Message msg = new Message();
		
		try {
			this.verifyRequiredData(request, response);
			this.validadeTokenWithCompany(request, response);
		} catch (MissingDataException e) {
			msg.setTitle("Missing Data");
			msg.setMessage(e.getMessage());
			return msg;
		} catch (InvalidCredentialsException e) {
			msg.setTitle("Inavlid Credentials");
			msg.setMessage(e.getMessage());
			return msg;
		}
		
		return null;
	}
	
	private void verifyRequiredData(HttpServletRequest request, HttpServletResponse response) throws MissingDataException {
		if(request.getHeader("cnpj") == null) {
			throw new MissingDataException("cnpj requeried for this action.");
		}
		
		if(request.getHeader("Auth") == null) {
			throw new MissingDataException("Auth is requerid for this action.");
		}
	}
	
	private void validadeTokenWithCompany(HttpServletRequest request, HttpServletResponse response) throws InvalidCredentialsException{
		Company comp = new Company();
		comp.setCnpj(request.getHeader("cnpj"));
		String token = request.getHeader("Auth");
		
		AuthenticationValidation authent = new AuthenticationValidation();
		
		if(!authent.validateTokenWithCompany(token, comp)) {
			throw new InvalidCredentialsException("Your token may have expired or this employee does not belong to the cnpj informed.");
		}
		else {
			System.out.println("The token is valid!");
		}
	}
	
	private String generateJsonToReturn (Message msg) {
		Gson gson = new Gson();
		String json = gson.toJson(msg); 
		
		return json;
	}

}
