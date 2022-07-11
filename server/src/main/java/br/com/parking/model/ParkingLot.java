package br.com.parking.model;

public class ParkingLot extends Model {
	private Company comp;
	private String name;
	
	public ParkingLot(Company comp, String name) {
		this.comp = comp;
		this.name = name;
	}

	public ParkingLot() {
		// TODO Auto-generated constructor stub
	}

	public Company getComp() {
		return comp;
	}

	public void setComp(Company comp) {
		this.comp = comp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
