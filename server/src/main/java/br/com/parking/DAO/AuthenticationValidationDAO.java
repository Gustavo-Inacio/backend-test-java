package br.com.parking.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.parking.model.Company;

public class AuthenticationValidationDAO extends DAO{
	
	public boolean validateToken(String token) {
		String tableName = "EmployeeAccessToken";
		boolean tokenExists = false;
		try {
			String query = "SELECT * FROM "+tableName+" WHERE at_code=? AND at_status=1 AND at_expires_at >= NOW();";
			
			Connection con = super.connect();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, token);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.isBeforeFirst())
				tokenExists = true;
			
			super.closeConnection(con);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return tokenExists;
	}
	
	public boolean validateTokenWithCompany(String token, Company comp) {
		boolean isValid = false;
		String acesTokenTable = "EmployeeAccessToken";
		String employeeTable = "Employee";
		String companyTable = "Company";
		
		try {
			String query = 
			"select 1 from "+acesTokenTable+" as emp_accss "
			+ "inner join "+employeeTable+" as emp "
			+ "on emp_accss.at_emp_fk = emp.emp_id "
			+ "inner join "+companyTable+" as comp "
			+ "on comp.comp_id = emp.emp_comp_fk "
			+ "where comp.comp_cnpj=? and emp_accss.at_code=?;"		
			;
			
			Connection con = super.connect();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, comp.getCnpj());
			pst.setString(2, token);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.isBeforeFirst())
				isValid = true;
			
			super.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isValid;
	}
	
	
}
