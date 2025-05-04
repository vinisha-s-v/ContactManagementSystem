package com.controller;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;


import com.dto.ContactDto;
import com.google.gson.Gson;
import com.service.ContactService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ContactController {
		
	
	public static void registerRoutes(HttpServer httpServer) {
		ContactService service = new ContactService();
		Gson gson = new Gson();

		httpServer.createContext("/contacts", new ContactHandler(service,gson));
	}
	
	static class ContactHandler implements HttpHandler {
		
		private final ContactService service;
		private final Gson gson;
		
		public ContactHandler(ContactService service,Gson gson) {
			this.service = service ;
			this.gson = gson ;
		}

		@Override
		public void handle(HttpExchange exchange) throws IOException {	
			String method = exchange.getRequestMethod();
			URI uri = exchange.getRequestURI();
			String path = uri.getPath();
			String[] pathParts = path.split("/");
			int id = -1;
			if(pathParts.length == 3) {
				id = Integer.parseInt(pathParts[2]);
			}
			
			switch(method) {
				case "GET" :
					List<ContactDto> contacts = service.getAllContacts();
					respond(exchange,contacts);
					break;
			}
		}
		
		
		
		private void respond(HttpExchange exchange, Object objectRes) throws IOException {
			String response = gson.toJson(objectRes);
			exchange.getResponseHeaders().set("Content-Type", "application/json");
			exchange.sendResponseHeaders(200, response.getBytes().length);
			OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
		}
		
	}



}
