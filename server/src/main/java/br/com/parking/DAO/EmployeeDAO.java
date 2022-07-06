package br.com.parking.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.parking.model.Employee;
import messages.Message;
import myExceptions.UniqueDataAlreadyExists;

public class EmployeeDAO extends DAO {
	private static String tableName = "Employee";
	
	public void insert(Employee emp) throws UniqueDataAlreadyExists {
		try {
			String query = "";
	
			query = "INSERT INTO "+tableName+" (emp_name, emp_login, emp_password, emp_comp_fk) VALUES (?, ?, ?, "
					+ " (SELECT comp_id from COMPANY where comp_cnpj=?))";	
	
			Connection con = super.connect();
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, emp.getName());
			pst.setString(2, emp.getLogin());
			pst.setString(3, emp.getPassword());
			pst.setString(4, emp.getComp().getCnpj());
			
			pst.executeUpdate();
			
			super.closeConnection(con);
		} catch (SQLException e) {
			if(e.getErrorCode() == 1062) {
				throw new UniqueDataAlreadyExists("Login already exixts");
			}
		}
	}
	
}
