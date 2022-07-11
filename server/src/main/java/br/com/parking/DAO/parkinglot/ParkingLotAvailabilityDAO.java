package br.com.parking.DAO.parkinglot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.parking.DAO.DAO;
import br.com.parking.DAO.QueriesMethodsDAO;
import br.com.parking.model.ParkingLotAvailability;

public class ParkingLotAvailabilityDAO extends DAO{
	private static String tableName = "ParkingLotAvailability";
	private QueriesMethodsDAO emDAO = new QueriesMethodsDAO();
	
	public void insert(ParkingLotAvailability plAvailability) throws SQLException {
//		try {
//			String query = "INSERT INTO "+tableName+" (pla_parkinglot_fk, pla_type_fk) VALUES (?,?)";
//			
//			Connection con = super.connect();
//			PreparedStatement pst = con.prepareStatement(query);
//			
//			pst.setInt(0, plAvailability.getParkingLot().getId());
//			pst.setInt(1, plAvailability.getType().getId());
//			
//			pst.executeUpdate();
//			
//			super.closeConnection(con);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		};
//		
		
		
		emDAO.query("INSERT INTO "+tableName+" (pla_parkinglot_fk, pla_type_fk, pla_availability) VALUES (?,?,?)")
			.setIntParam(1, plAvailability.getParkingLot().getId())
			.setIntParam(2, plAvailability.getType().getId())
			.setIntParam(3, plAvailability.getAvailability())
			.execute();
		
	}
	
	public void insert(List<ParkingLotAvailability> plaList) throws SQLException {
		for (ParkingLotAvailability pla : plaList) {
			this.insert(pla);
		}
	}
}
