package br.com.parking.util.builders;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.parking.model.Company;
import br.com.parking.model.ParkingLot;

public class ParkinglotBuilder {
	public ParkingLot build(ResultSet rs) throws SQLException {
		ParkingLot pl = new ParkingLot();
		
		Company comp = new Company();
		comp.setId(rs.getInt("pl_comp_fk"));
		
		pl.setComp(comp);
		pl.setCreate(rs.getDate("pl_create"));
		pl.setUpdate(rs.getDate("pl_update"));
		pl.setId(rs.getInt("pl_id"));
		pl.setName(rs.getString("pl_name"));
		pl.setStatus(rs.getInt("pl_id"));
		
		return pl;
	}
}
