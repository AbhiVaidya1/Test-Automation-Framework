package com.ui.tests;

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

@Listeners(com.ui.listeners.TestListner.class)
public class InvalidCredLoginTest extends TestBase {
	// All the comments are in LoginTest for reference
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private static final String INVALID_EMAIL_ADDRESS = "test1test2@gmail.com";
	private static final String INVALID_PASSWORD = "Qwerty1234";

	//*** To Run the Test from eclipse, Window>Preferences>TestNG>Maven Uncheck all except argLine option and Save
	@Test(description = "Verifies if the error message is shown for the user when they enter invalid credentials", groups = {
			"e2e", "sanity","smoke" })
	public void loginTest() {

		// String userName =
		// homePage.goToLoginPage().doLoginWith("rovem70682@oremal.com",
		// "Password").getUserName();
		// Assert.assertEquals(userName, "Iron Man"); //static import, no need to
		// BrowserName.CHROME, JUST CHROME
		// assertEquals(homePage.goToLoginPage().doLoginWith("rovem70682@oremal.com",
		// "Password").getUserName(), "Iron Man");
		assertEquals(homePage.goToLoginPage().doLoginWithInvalidCredentials(INVALID_EMAIL_ADDRESS, INVALID_PASSWORD)
				.getErrorMessage(), "Authentication failed.");
	}

}