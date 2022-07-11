package br.com.parking.DAO.vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.parking.DAO.DAO;
import br.com.parking.DAO.QueriesMethodsDAO;
import br.com.parking.model.VehicleType;

public class VehicleTypeDAO extends DAO{
	private static String tableName = "VehicleType";

	public void insert(VehicleType vtype) throws SQLException {
		QueriesMethodsDAO emDAO = new QueriesMethodsDAO();
		
		String query = "INSERT INTO "+tableName+" (vt_name) VALUES (?)";
		
		emDAO.query(query)
			.setStringParam(1, vtype.getName())
			.execute();
		
	}
	
	public List<VehicleType> selectAll () {
		List<VehicleType> veTypes = new ArrayList<>();
		try {
			String query = "SELECT * FROM "+tableName;
			
			Connection con = super.connect();
			PreparedStatement pst = con.prepareStatement(query);
				
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				VehicleType vt = new VehicleType();
				vt.setCreate(rs.getDate("vt_create"));
				vt.setStatus(rs.getInt("vt_status"));
				vt.setUpdate(rs.getDate("vt_update"));
				vt.setName(rs.getString("vt_name"));
				vt.setId(rs.getInt("vt_id"));
				
				veTypes.add(vt);
			}
			
			super.closeConnection(con);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return veTypes;
	}
}
