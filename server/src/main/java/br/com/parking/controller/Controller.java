package br.com.parking.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.parking.model.Company;
import br.com.parking.service.actions.Action;
import messages.Message;
import myExceptions.RouteNotFoundException;

//@WebServlet("/*")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String valor = req.getHeader("Accept");
		System.out.println("header --- > " + req.getHeader("Accept"));
		Gson gson = new Gson();
		
		 if(valor.endsWith("json")) {
			InputStream inputData =  req.getInputStream();
			Scanner scan = new Scanner(inputData);
			String body = "";
			
			while(scan.hasNextLine()) {
				body += scan.nextLine();
			}
			
			
			try {
				String json = gson.toJson(body);
				req.setAttribute("bodyData", body);
				
				System.out.println(json);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		String URI = req.getRequestURI();
		Action action = null;
		
		try {
			action = Routes.getAction(URI);
			Message msg = action.execute(req, resp);
			
			String json = gson.toJson(msg);  
			
			resp.setContentType("application/json");
			resp.getWriter().print(json);
			return;
			
		} catch (RouteNotFoundException e1) {
			e1.printStackTrace();
		}
		
	}

}
