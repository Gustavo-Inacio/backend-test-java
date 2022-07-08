package br.com.parking.model.comunication;

import java.util.Map;
import java.util.Set;

import br.com.parking.model.Company;
import br.com.parking.model.ParkingLot;

public class CreateParkinglotData {
	private Company company;
	private Map<String, Integer> availability;
	private String name;
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Map<String, Integer> getAvailability() {
		return availability;
	}
	public void setAvailability(Map<String, Integer> availability) {
		this.availability = availability;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
