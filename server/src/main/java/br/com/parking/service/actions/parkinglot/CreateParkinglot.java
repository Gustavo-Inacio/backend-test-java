package br.com.parking.service.actions.parkinglot;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.parking.DAO.parkinglot.ParkingLotAvailabilityDAO;
import br.com.parking.DAO.parkinglot.ParkinglotDAO;
import br.com.parking.model.Company;
import br.com.parking.model.ParkingLot;
import br.com.parking.model.ParkingLotAvailability;
import br.com.parking.model.VehicleType;
import br.com.parking.model.comunication.CreateParkinglotData;
import br.com.parking.service.ServletRequestParametersExtractor;
import br.com.parking.service.actions.Action;
import messages.Message;
import myExceptions.DatabaseAccessException;

public class CreateParkinglot implements Action{

	@Override
	public Message execute(HttpServletRequest request, HttpServletResponse response) {
		ServletRequestParametersExtractor<CreateParkinglotData> extract = new ServletRequestParametersExtractor<>(CreateParkinglotData.class);
		CreateParkinglotData plData = extract.getRequestParameters(request);
		
		Message msg = new Message();
		
		if(plData.getAvailability() == null || plData.getName() == null) {
			System.out.println("This thing is null!!!!!!!!");
			msg.setTitle("An errr Ocurred");
			msg.setMessage("Invalid parameters strucuture. Consult the docs");
			return msg;
		}
		
		Company comp = new Company();
		comp.setCnpj(request.getHeader("cnpj"));
		
		plData.setCompany(comp);
		
		try {
			this.create(plData);
		} catch (SQLException e) {
			msg.setTitle("An error ocurred");
			msg.setMessage("contact the devs");
			
			e.printStackTrace();
			return msg;
		} catch (DatabaseAccessException e) {
			msg.setTitle("An error ocurred");
			msg.setMessage("contact the devs");
			
			e.printStackTrace();
			return msg;
		}
		
		return null;
	}
	
	public void create(CreateParkinglotData cpd) throws SQLException, DatabaseAccessException {
		Map<String, Integer> parkingTypesQuantity = cpd.getAvailability();
		
		List<ParkingLotAvailability> plaAvailabilities = new ArrayList<>(); // list of all parkingAvailabilities of this parkiglot
		
		ParkingLot pl = new ParkingLot(cpd.getCompany(), cpd.getName());
		
		for (Map.Entry<String, Integer> type : parkingTypesQuantity.entrySet()) {
			VehicleType vt = new VehicleType(type.getKey());
			ParkingLotAvailability pla = new ParkingLotAvailability();
			
			pla.setAvailability(type.getValue());
			pla.setType(vt);
			
			plaAvailabilities.add(pla);
		}
		
		for (ParkingLotAvailability parkingLotAvailability : plaAvailabilities) {
			System.out.println(parkingLotAvailability.getType().getName());
		}
		
		ParkinglotDAO plDAO = new ParkinglotDAO();
		plDAO.insert(pl);
		
		List<ParkingLot> parkinglotList = plDAO.getParkingLot(pl);
		
		for (ParkingLotAvailability parkingLotAvailability : plaAvailabilities) {
			parkingLotAvailability.setParkingLot(parkinglotList.get(0));
		}
		
		ParkingLotAvailabilityDAO plaDAO = new ParkingLotAvailabilityDAO();
		plaDAO.insert(plaAvailabilities);
		
		
	}

}
