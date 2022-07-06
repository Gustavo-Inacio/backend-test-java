package br.com.parking.service.actions.employee;

import br.com.parking.model.Employee;
import myExceptions.MissingDataException;
import myExceptions.UniqueDataAlreadyExists;

public class VerifyEmployee {
	public void verifyCreate (Employee emp) throws MissingDataException {
		
		if(emp.getName() == null || emp.getLogin() == null || emp.getPassword() == null || emp.getComp() == null || emp.getComp().getCnpj() == null) {
			throw new MissingDataException("Some employee Values are null");
		}
		
		if(emp.getName().trim().length() < 2 || emp.getLogin().trim().length() < 6 || emp.getPassword().length() < 7 || emp.getComp().getCnpj().length() != 14) {
			throw new MissingDataException("Must have Name >= 2, Login >= 6 and password >= 7 and cnpj == 14");
		} 
		
		return;
		
	}
}
