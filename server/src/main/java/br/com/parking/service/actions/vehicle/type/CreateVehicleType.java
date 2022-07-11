package br.com.parking.service.actions.vehicle.type;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.parking.DAO.vehicle.VehicleTypeDAO;
import br.com.parking.model.VehicleType;
import br.com.parking.service.ServletRequestParametersExtractor;
import br.com.parking.service.actions.Action;
import messages.Message;

public class CreateVehicleType implements Action {

	@Override
	public Message execute(HttpServletRequest request, HttpServletResponse response) {
		ServletRequestParametersExtractor<Vector> srpe = new ServletRequestParametersExtractor<>(Vector.class);
		List<String> items = new ArrayList<String>(srpe.getRequestParameters(request));
		List<VehicleType> typesList = new ArrayList<>();
		
		Message msg = new Message();
		
		items.forEach(item -> {
			typesList.add(new VehicleType(item));
		});
		
		try {
			this.create(typesList);
		} catch (SQLException e) {
			e.printStackTrace();
			
			msg.setTitle("An error ocurred");
			msg.setMessage("Contact the dev team!");
			
			return msg;
		}
		
		return new Message("Ok", "Created");
	}
	
	public void create(List<VehicleType> typesList) throws SQLException { // creates from List<VehicleType>
		for (VehicleType type : typesList) {
			this.create(type);
		}
	}
	
	public void create(VehicleType vtype) throws SQLException {
		VehicleTypeDAO vtDAO = new VehicleTypeDAO();
		vtDAO.insert(vtype);
	}
	
	

}
