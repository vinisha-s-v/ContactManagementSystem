package com.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.DBConnectionUtil;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		DBConnectionUtil.init();

	}

}
