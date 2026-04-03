package com.ui.tests;

import com.constants.Browser;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;

public class TestBase {
//*****Whenever we are going to create a Test class, which will be going to extend this TestBase Class
	
	protected HomePage homePage;
	Logger logger =LoggerUtility.getLogger(this.getClass()); 
	
	private boolean isLambdaTest;
	//private boolean isHeadless = true; used below
	

	@Parameters({"browser", "isLambdaTest","isHeadless"}) //testng parameters are mapped to method's parameters
	@BeforeMethod(description = "Load homepage of the website!")
	//public void setUp(String browser, boolean isLambdaTest, boolean isHeadless, ITestResult result) {
	public void setUp(
			@Optional("chrome") String browser, 
			@Optional("false") boolean isLambdaTest, 
			@Optional("true") boolean isHeadless, ITestResult result) {
		
		
		this.isLambdaTest = isLambdaTest; //left value refers to instance variable
		WebDriver lambdaDriver;
		if(isLambdaTest) {
			
			LambdaTestUtility.initializeLambdaTestSession(browser,result.getMethod().getMethodName());
			//homePage = new HomePage(CHROME,isHeadless);
			homePage = new HomePage(Browser.valueOf(browser.toUpperCase()),isHeadless);
		}
		else {
			//Running test on Local Machine
		homePage = new HomePage(Browser.CHROME,isHeadless); //static import, no need to BrowserName.CHROME, JUST CHROME
			//homePage = new HomePage(Browser.valueOf(browser),isHeadless); //not working
			logger.info("Trying to perform click to go to Sign In page");
		}
	}
	
	public BrowserUtility getInstance() {
		return homePage; //returning object from child class HomePage, but adding return Type as Parent 
		//class i.e., BrowserUtitlity
	}
	
	@AfterMethod(description = "Tear Down the browser")
	public void tearDown() {
		
		if(isLambdaTest) {
			LambdaTestUtility.quitSession(); //quit or close browsers session on LambdaTest cloud
		}
		else {
		homePage.quit(); //local
		}
	}
}
