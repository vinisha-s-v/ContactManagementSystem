package com.main;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.controller.ContactController;
import com.sun.net.httpserver.HttpServer;

import com.util.DBConnectionUtil;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws IOException {
		DBConnectionUtil.init();
		HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0) ;
		ContactController.registerRoutes(httpServer);
		httpServer.setExecutor(null);
		httpServer.start();
		logger.info("Server started at http://localhost:8080");
	}

}
