package br.com.parking.service;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class ServletRequestParametersExtractor<T> {
	final Class<T> paramClass ;
	
	public ServletRequestParametersExtractor(Class<T> myClass) {
		this.paramClass = myClass;
	}
	
	
	public T getRequestParameters(HttpServletRequest request) {
		if(request.getAttribute("bodyData") != null) {
			String requestData = (String) request.getAttribute("bodyData");
			String header = request.getHeader("Accept");
			
			if(header.endsWith("json")) {
				return this.getJsonParameters(requestData);
			}
		}
		
		return null;
	}
	
	private T getJsonParameters(String requestData) {
		Gson gson = new Gson();
		T compEmp = (T) gson.fromJson((String) requestData, this.paramClass);
		
		return compEmp;
	}
}
