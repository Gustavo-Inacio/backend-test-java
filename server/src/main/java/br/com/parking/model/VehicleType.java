package br.com.parking.model;

import java.util.Date;

public class VehicleType extends Model{
	private String name;
	
	public VehicleType() {
		
	}

	public VehicleType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
