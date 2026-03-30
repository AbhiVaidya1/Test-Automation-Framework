package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utility.JSONUtility;
import com.utility.PropertiesUtil;

public class MyRetryAnalyzer implements IRetryAnalyzer{

	//private static final int MAX_NO_OF_ATTEMPTS = 3;
	//private static final int MAX_NO_OF_ATTEMPTS = Integer.parseInt(PropertiesUtil.readProperty(Env.DEV, "MAX_NO_OF_ATTEMPTS" )); 
	private static final int MAX_NO_OF_ATTEMPTS = JSONUtility.readJSON(Env.QA).getMAX_NO_OF_ATTEMPTS();
	private static int currentAttempt = 1;
	
	@Override
	public boolean retry(ITestResult result) { //retry is method of IRetryAnalyzer interface
											   //ItestResult is a class
		if(currentAttempt<=MAX_NO_OF_ATTEMPTS)
		{
			currentAttempt++;
			return true;
		}
		return false;
	}

	
}
