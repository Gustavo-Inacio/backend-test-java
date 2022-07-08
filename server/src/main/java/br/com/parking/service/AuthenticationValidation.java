package br.com.parking.service;

import br.com.parking.DAO.AuthenticationValidationDAO;
import br.com.parking.model.Company;

public class AuthenticationValidation {
	public Boolean validateToken(String token) {
		
		AuthenticationValidationDAO authenticationDAO = new AuthenticationValidationDAO();
		boolean tokenExists = authenticationDAO.validateToken(token);
		
		return tokenExists;
		
	}
	
	public Boolean validateTokenWithCompany(String token, Company comp) {
		AuthenticationValidationDAO authDAO = new AuthenticationValidationDAO();
		boolean isValid = authDAO.validateTokenWithCompany(token, comp);
		
		return isValid;
	}
}
