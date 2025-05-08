package com.controller;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dto.ContactDto;
import com.google.gson.Gson;
import com.main.Main;
import com.service.ContactService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ContactController {
	
	private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
		
	
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
			
			try {
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
						if(id!=-1) {
							ContactDto contact = service.getContactByid(id);
							if (contact != null) {
	                            respond(exchange, contact);
	                        } else {
	                            respond(exchange, "Contact not found");
	                        }
						}else {
							List<ContactDto> contacts = service.getAllContacts();
							respond(exchange,contacts);
						}
						break;
					  default:
			                // Unsupported method
			                exchange.sendResponseHeaders(405, -1); 
			                
					  case "POST" :
						  try {
							  ContactDto newContact = gson.fromJson(new InputStreamReader(exchange.getRequestBody()), ContactDto.class);
							  service.addContact(newContact);
							  respond(exchange,newContact);
						  }catch(Exception e) {
							  logger.error("Exception occurs in adding contact"+e);
							  respond(exchange,"invalid data");
						  }
						 
				}
			}catch (Exception e) {
		        e.printStackTrace();  // Show error in console
		        String errorMessage = "{\"error\":\"Internal Server Error\"}";
		        exchange.sendResponseHeaders(500, errorMessage.getBytes().length);
		        OutputStream os = exchange.getResponseBody();
		        os.write(errorMessage.getBytes());
		        os.close();
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
