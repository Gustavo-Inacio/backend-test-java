package br.com.parking.model;

import java.util.Date;

public class ParkingLotAvailability extends Model {
	private VehicleType type;
	private ParkingLot parkingLot;
	private int availability;
	
	
	public ParkingLot getParkingLot() {
		return parkingLot;
	}
	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}
	
	public VehicleType getType() {
		return type;
	}
	public void setType(VehicleType type) {
		this.type = type;
	}
	public int getAvailability() {
		return availability;
	}
	public void setAvailability(int availability) {
		this.availability = availability;
	}
	
	
	
}
