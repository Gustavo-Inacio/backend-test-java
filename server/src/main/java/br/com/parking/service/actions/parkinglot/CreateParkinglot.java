package br.com.parking.service.actions.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.parking.model.Company;
import br.com.parking.model.ParkingLotAvailability;
import br.com.parking.model.VehicleType;
import br.com.parking.model.comunication.CreateParkinglotData;
import br.com.parking.service.ServletRequestParametersExtractor;
import br.com.parking.service.actions.Action;
import messages.Message;

public class CreateParkinglot implements Action{

	@Override
	public Message execute(HttpServletRequest request, HttpServletResponse response) {
		ServletRequestParametersExtractor<CreateParkinglotData> extract = new ServletRequestParametersExtractor<>(CreateParkinglotData.class);
		
		CreateParkinglotData plData = extract.getRequestParameters(request);
		Company comp = new Company();
		comp.setCnpj(request.getHeader("cnpj"));
		
		plData.setCompany(comp);
		
		return null;
	}
	
	public void create(CreateParkinglotData cpd) {
		Map<String, Integer> parkingTypesQuantity = cpd.getAvailability();
		
		List<ParkingLotAvailability> plaAvailabilities = new ArrayList<>(); // list of all parkingAvailabilities of this parkiglot
		
		for (Map.Entry<String, Integer> type : parkingTypesQuantity.entrySet()) {
			VehicleType vt = new VehicleType();
			ParkingLotAvailability pla = new ParkingLotAvailability();
			
			pla.setAvailability(type.getValue());
			pla.setType(vt);
			
			plaAvailabilities.add(pla);
		}
		
		
		
	}

}
