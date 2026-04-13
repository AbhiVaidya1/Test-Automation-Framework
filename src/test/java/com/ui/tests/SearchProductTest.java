package com.ui.tests;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.MyAccountPage;
import com.utility.LoggerUtility;

@Listeners(com.ui.listeners.TestListner.class)
public class SearchProductTest extends TestBase {
	
	private MyAccountPage myAccountPage;
	private static final String SEARCH_TERM = "Printed Summer Dress";
	//private static final String SEARCH_TERM = "Mens wallet";
	Logger logger = LoggerUtility.getLogger(this.getClass());
	@BeforeMethod(description= "Valid user logs into the application")
	public void setup() {
		myAccountPage = homePage.goToLoginPage().doLoginWith("dejic37640@helesco.com","Password");
	}
	
	@Test(description = "Verifies if the logged in user is able to search for a product and correct products search resultes are displayed", groups = {"e2e","smoke","sanity"} 
	)
	public void verifyproductSeachTest() {
				
		boolean actualResult = myAccountPage.searchForProduct(SEARCH_TERM).isSearchTermPresentInProductList(SEARCH_TERM);
		Assert.assertEquals(actualResult, true);
	
	}

}