package com.ui.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.utility.BrowserUtility;


public class LoginTestOLD {

	public static void main(String[] args) {
		WebDriver wd = new ChromeDriver(); // Launches Browser session
		//wd.get("http://www.automationpractice.pl/index.php");
		BrowserUtility browserUtility = new BrowserUtility(wd);
	
		browserUtility.goToWebsite("http://www.automationpractice.pl/index.php");
		//wd.manage().window().maximize();
		browserUtility.maximizeWindow();
		
		By signInLinkLocator = By.xpath("//a[contains(text(), \"Sign in\")]");
//		//a[contains(text(), "Sign in")]
//		WebElement signInLinkWebElement =wd.findElement(signInLinkLocator);// Finds the element
//		signInLinkWebElement.click();
		browserUtility.clickOn(signInLinkLocator);
		
		By emailTextLocator = By.id("email");
//		WebElement emailTextWebElement = wd.findElement(emailTextLocator);
//		emailTextWebElement.sendKeys("test@test.com");
		browserUtility.enterText(emailTextLocator, "test@test.com");
		
		By emailIDTextBoxLocator = By.id("email");
//		WebElement emailTextboxWebElement = wd.findElement(emailIDTextBoxLocator);
//		emailTextboxWebElement.sendKeys("test@test.com");
		browserUtility.enterText(emailIDTextBoxLocator, "test@test.com");
		By passwordTextLocator = By.id("passwd");
		browserUtility.enterText(passwordTextLocator, "password"); // Abstraction - find element & send keys are hidden in enterText method
		
		By signInLocator = By.id("SubmitLogin");
//		WebElement signInbuttonWebElement = wd.findElement(signInLocator);
//		signInbuttonWebElement.click();
		browserUtility.clickOn(signInLocator);
		
		// We are not supposed to use webdriver methods (SendKeys, Click etc) directly 
		// We should always use customized methods - wrapper calss,
		
	}

}
