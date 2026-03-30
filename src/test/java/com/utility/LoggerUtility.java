package com.utility;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {
	//Singleton Design Pattern->only 1 object created for a class
	//private static Logger logger; //removed because each class must show the logs, before it was showing only for 1 class
	
	private LoggerUtility() { //In singleton pattern, constructor is private, so we cannot create object outside
		
	}

	public static Logger getLogger(Class<?> clazz) {
		Logger logger=null; //added because each class must show the logs, before it was showing only for 1 class
		if(logger == null) {
		logger = LogManager.getLogger(clazz);
		}
		return logger;
	}
}
