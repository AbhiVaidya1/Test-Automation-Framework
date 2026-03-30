package com.ui.pages;

import org.openqa.selenium.By;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import static com.utility.PropertiesUtil.*;

public final class HomePage extends BrowserUtility{ // if parent class is abstract then child must be final

	public HomePage(Browser browserName, boolean isHeadless) {
		super(browserName,isHeadless); // To call the Parent class constructor from the child class constructor
		//goToWebsite(PropertiesUtil.readProperty(Env.QA, "URL")); // read from properties file
		//goToWebsite(readProperty(QA, "URL")); //Static Import of prop and Env
		//goToWebsite(JSONUtility.readJSON(Env.QA));
		goToWebsite(JSONUtility.readJSON(QA).getUrl());
		
	}
	// Page Object Design Pattern - This is a Page class
	private static final  By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(), \"Sign in\")]"); 
	// Since the locator is not going to be changed, final is used, if final then static must be there 
	// Final variables are usually mentioned in Capitals
	
	public LoginPage goToLoginPage() { // Page Functions, void cannot be used in POM pattern
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;
	}
}
