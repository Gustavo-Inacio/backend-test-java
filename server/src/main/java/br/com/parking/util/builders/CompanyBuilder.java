package br.com.parking.util.builders;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.parking.model.Company;

public class CompanyBuilder {
	
	public Company build(ResultSet rs) throws SQLException {
		Company comp = new Company();
		
		comp.setCnpj(rs.getString("comp_cnpj"));
		comp.setCreate(rs.getDate("comp_create"));
		comp.setId(rs.getInt("comp_id"));
		comp.setName(rs.getString("comp_name"));
		comp.setPhone(rs.getString("comp_phone"));
		comp.setStatus(rs.getInt("comp_id"));
		
		return comp;
	}
}
