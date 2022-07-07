package br.com.parking.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;

import br.com.parking.model.Employee;
import br.com.parking.model.comunication.AuthenticationData;
import myExceptions.InvalidLoginException;
import myExceptions.UniqueDataAlreadyExists;

public class AccesTokenDAO extends DAO{
	private static String tableName = "EmployeeAccessToken";
	 
	public String insert(Employee emp) throws UniqueDataAlreadyExists {
		String code = null;
		try {
			String query = "";
	
			query = "INSERT INTO "+tableName+" (at_code, at_emp_fk, at_expires_at) VALUES (?,"
					+ "(select emp_id from employee where emp_login=?) ,?)";	
	
			Connection con = super.connect();
			PreparedStatement pst = con.prepareStatement(query);
			
			code = "" + System.currentTimeMillis() / 100;
			Date expires = new Date(System.currentTimeMillis() + (1000*60*60*24*365));
			
			pst.setString(1, code);
			pst.setString(2, emp.getLogin());
			pst.setDate(3, expires);
			
			pst.executeUpdate();
			
			super.closeConnection(con);
		} catch (SQLException e) {
			if(e.getErrorCode() == 1062) { // 1062
				throw new UniqueDataAlreadyExists("Token already exixts");
			}
		}
		
		return code;
	}
	
	public String getToken(AuthenticationData auth) throws InvalidLoginException {
		String tableName = "EmployeeAccessToken";
		String token = null;
		
		try {
			String query = 
			"SELECT eat.at_code as at_code FROM employee as emp "
			+ "inner join "+tableName+" as eat "
			+ "on emp.emp_id = eat.at_emp_fk "
			+ "where emp.emp_login = ? and emp.emp_password = ? and "
			+ "at_status=1 and at_expires_at >= NOW()";
			
			Connection con = super.connect();
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, auth.getLogin());
			pst.setString(2, auth.getPassword());
			
			ResultSet rs = pst.executeQuery();
			
			if(!rs.next())
				throw new InvalidLoginException("Invalid login or pass");
			
			token = rs.getNString("at_code");
			
			super.closeConnection(con);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (InvalidLoginException e){
			throw new InvalidLoginException(e.getMessage());
		}
		
		return token;
	}
}
