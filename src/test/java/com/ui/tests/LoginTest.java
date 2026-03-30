package com.ui.tests;


//static import, no need to BrowserName.CHROME, JUST CHROME
import static com.constants.Browser.CHROME;
import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;

/*
 * Clean code Test Method!!
 * 1. Test script has to be small
 * 2. you cannot have conditional statements, loops, try catch in test methods
 * 3. test scripts ---> Test steps
 * 4. Reduce the use of local variables
 * 5. At least 1 assertion
 */

@Listeners(com.ui.listeners.TestListner.class)
public class LoginTest extends TestBase{
	//HomePage homePage; //moved to TestBase class
	Logger logger =LoggerUtility.getLogger(this.getClass()); 
	
//	@BeforeMethod(description = "Load homepage of the website!")
//	public void setUp() {
//		homePage = new HomePage(CHROME); //static import, no need to BrowserName.CHROME, JUST CHROME
//		logger.info("Trying to perform click to go to Sign In page");
//	}// Moved to TestBase class
	
	/************JSON*****************************/
	@Test(description = "Verifies user able to login to the application", groups = {"e2e","sanity"}, 
			dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider", retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
	public void loginTest(User user) {
				
		//String userName = homePage.goToLoginPage().doLoginWith("rovem70682@oremal.com", "Password").getUserName();
		//Assert.assertEquals(userName, "Iron Man"); //static import, no need to BrowserName.CHROME, JUST CHROME
		//assertEquals(homePage.goToLoginPage().doLoginWith("rovem70682@oremal.com", "Password").getUserName(), "Iron Man"); 
	assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Hawk Eye");
	}
//	/************CSV*****************************/
//	@Test(description = "Verifies user able to login to the application", groups = {"e2e","sanity"}, 
//			dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider")
//	public void loginCSVTest(User user) {
//				
//	assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Hawk Eye");
//	}
//	/************Excel*****************************/
//	@Test(description = "Verifies user able to login to the application", groups = {"e2e","sanity"}, 
//			dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestExcelDataProvider",
//			retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
//	public void loginExcelTest(User user) {
//			//Logger logger =LoggerUtility.getLogger(this.getClass()); //placed above
////			logger.info("Started my login Excel Test");
//	assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Hawk Eye1");
////			logger.info("Login Excel Test Completed");
//	}

}