package br.com.parking.DAO.parkinglot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import br.com.parking.DAO.DAO;
import br.com.parking.DAO.QueriesMethodsDAO;
import br.com.parking.model.Company;
import br.com.parking.model.ParkingLot;
import br.com.parking.util.builders.CompanyBuilder;
import br.com.parking.util.builders.ParkinglotBuilder;
import myExceptions.DatabaseAccessException;

public class ParkinglotDAO extends DAO{
	private static String tableName = "parkinglot";
	private CompanyBuilder compBuild = new CompanyBuilder();
	
//	public void insert(ParkingLot parklot) throws Exception {
//		try {
//			String query = "INSERT INTO "+tableName+" (pl_comp_fk, pl_name) VALUES (?,?)";
//			
//			Connection con = super.connect();
//			PreparedStatement pst = con.prepareStatement(query);
//			
//			pst.setInt(0, parklot.getComp().getId());
//			pst.setString(1, parklot.getName());
//			
//			pst.executeUpdate();
//			
//			super.closeConnection(con);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//			
//			throw e;
//		}
//	}
	
	public void insert(ParkingLot parklot) throws SQLException {
		QueriesMethodsDAO emDAO = new QueriesMethodsDAO();
		
		if(parklot.getComp().getId() != null) {
			String query = "INSERT INTO "+tableName+" (pl_comp_fk, pl_name) VALUES (?,?)";
			emDAO.query(query)
				.setIntParam(1, parklot.getComp().getId())
				.setStringParam(2, parklot.getName())
				.execute();
		}
		
		if(parklot.getComp().getCnpj() != null) {
			String query = "INSERT INTO "+tableName+" (pl_comp_fk, pl_name) VALUES ((SELECT comp_id from COMPANY where comp_cnpj=?),?)";
			emDAO.query(query)
				.setStringParam(1, parklot.getComp().getCnpj())
				.setStringParam(2, parklot.getName())
				.execute();
		}
		
		
		
	}

	public List<ParkingLot> getParkingLot(ParkingLot pl) throws DatabaseAccessException, SQLException {
		QueriesMethodsDAO<ParkingLot> emDAO = new QueriesMethodsDAO<>();
		
		String query = "SELECT * from parkinglot as pl "
				+ "inner join company as comp "
				+ "on pl.pl_comp_fk = comp.comp_id "
				+ "where comp.comp_cnpj=? AND pl_name=?";
		
		CachedRowSet rs = emDAO.query(query)
			.setStringParam(1, pl.getComp().getCnpj())
			.setStringParam(2, pl.getName())
			.execute();
		
		if(rs == null) {
			throw new DatabaseAccessException("Problem on getting data");
		}
		
		rs.next();
		System.out.println(rs.getString("pl_name")); 
		
		List<ParkingLot> parkinglotList = this.resultSetToParkingLot(rs);
		
		return parkinglotList;
	}
	
	private List<ParkingLot> resultSetToParkingLot(CachedRowSet rs) throws SQLException {
		List<ParkingLot> plList = new ArrayList<>();
		
		while(rs.next()) {
//			Company comp = new Company();
//			comp.setCnpj(rs.getNString("comp_cnpj"));
//			comp.setCreate(rs.getDate("comp_create"));
//			comp.setId(rs.getInt("comp_id"));
//			comp.setName(rs.getString("comp_name"));
//			comp.setPhone(rs.getString("comp_phone"));
//			comp.setStatus(rs.getInt("comp_id"));
			
			Company comp = this.compBuild.build(rs);
			ParkingLot pl = new ParkinglotBuilder().build(rs);
			pl.setComp(comp);
			
			plList.add(pl);
			
		}
		
		return plList;
	}
	
//	public void insert1() {
//		insert(query)
//			.setParam(1, "nome")
//			.setParam(2, "uu");
//			.execute();
//	}
}
