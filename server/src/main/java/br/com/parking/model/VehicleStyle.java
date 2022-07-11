package br.com.parking.model;

import java.util.Date;

public class VehicleStyle extends Model {
	private String name;
	private VehicleType type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public VehicleType getType() {
		return type;
	}
	public void setType(VehicleType type) {
		this.type = type;
	}

}
