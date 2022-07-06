package br.com.parking.model;

import java.util.Date;

public class Company extends Model{
	private String name;
	private String cnpj;
	private String phone;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return (
			"{name: " + this.name + ", phone: " + this.phone + ", cnpj: " + this.cnpj + ", id: " + this.id + "}"	
		);
	}
}
