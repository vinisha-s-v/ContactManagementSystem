package com.controller;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ContactController {
	
	public static void registerRoutes(HttpServer httpServer) {
		httpServer.createContext("/contacts", new ContactHandler());
	}
	
	static class ContactHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange exchange) throws IOException {			
		}
		
	}



}
