package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.constants.Browser;

public class BrowserUtility { // Made concrete so it can be instantiated from tests

	// private WebDriver driver; // instance variable,
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>(); // to make exec threadsafe
	// To initialize these constructors are created, RightCLick>Source>
	private Logger logger = LoggerUtility.getLogger(this.getClass());
	private WebDriverWait wait;

	public WebDriver getDriver() {
		// return driver;
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		// this.driver = driver; // this local variable initialize the instance variable
		this.driver.set(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30L)); // Suffix L because of its long value
	}

	public BrowserUtility(String browserName) {
		logger.info("Launching Browser for " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else {
			System.err.print("Invalid Browser Name... Please select Chrome or Edge");
		}
	}

	public BrowserUtility(Browser browserName) {
		logger.info("Launching Browser for " + browserName);
		if (browserName == Browser.CHROME) {

			driver.set(new ChromeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else if (browserName == Browser.EDGE) {
			driver.set(new EdgeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else if (browserName == Browser.FIREFOX) {
			driver.set(new FirefoxDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else {
			logger.error("Invalid Browser Name... Please select Chrome or Edge");
			System.err.print("Invalid Browser Name... Please select Chrome or Edge");
		}
	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("Launching Browser for " + browserName);
		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				// Use modern headless mode and add common flags that prevent Chrome from
				// crashing
				// options.setHeadless(true); // uses the standard headless flag (not available
				// in older Selenium versions)
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--disable-gpu"); // recommended for some Windows setups
				options.addArguments("--no-sandbox"); // for linux vm in github actions
				options.addArguments("--disable-dev-shm-usage");
				// Allow remote origins to avoid ChromeDriver/Chrome handshake issues on newer
				// versions
				options.addArguments("--remote-allow-origins=*");
				// In some environments giving an explicit user-data-dir avoids
				// DevToolsActivePort errors
				// Use a temp directory (unique per run) to avoid collisions/locks
				String userDir = System.getProperty("java.io.tmpdir") + File.separator + "chrome-profile-"
						+ System.nanoTime();
				options.addArguments("--user-data-dir=" + userDir);
				// Add additional flags to improve stability in CI/Windows environments
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-background-networking");
				options.addArguments("--disable-sync");
				options.addArguments("--no-first-run");
				options.addArguments("--disable-features=VizDisplayCompositor");
				driver.set(new ChromeDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
				driver.set(new ChromeDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}

		} else if (browserName == Browser.EDGE) {
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("disable-gpu");
				driver.set(new EdgeDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
				driver.set(new EdgeDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}
		} else if (browserName == Browser.FIREFOX) {
			if (isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old");
				options.addArguments("disable-gpu");
				driver.set(new FirefoxDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
				driver.set(new FirefoxDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}
		} else {
			logger.error("Invalid Browser Name... Please select Chrome or Edge");
			System.err.print("Invalid Browser Name... Please select Chrome or Edge");
		}
	}

	public void goToWebsite(String url) {
		logger.info("Visiting the website" + url);
		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximizing hte browser window");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("Finding Element with the locator" + locator);
		//WebElement element = driver.get().findElement(locator);// Finds the element
		//comments above line to use wait
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		logger.info("Element found and now performing Click");
		element.click();
	}
	
	public void clickOnCheckBox(By locator) {
		logger.info("Finding Element with the locator" + locator);
		//WebElement element = driver.get().findElement(locator);// Finds the element
		//comments above line to use wait
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.info("Element found and now performing Click");
		element.click();
	}

	public void clickOn(WebElement element) {
		logger.info("Element found and now performing Click");
		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		logger.info("Finding Element with the locator" + locator);
		//WebElement element = driver.get().findElement(locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.info("Element found and Entering the text" + textToEnter);
		element.sendKeys(textToEnter);
	}

	public void clearText(By textBoxLocator) {
		logger.info("Finding Element with the locator" + textBoxLocator);
		//WebElement element = driver.get().findElement(textBoxLocator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textBoxLocator));
		logger.info("Element found and Clearing the text box field");
		element.clear();
	}

	public void enterSpecialKey(By locator, Keys keyToEnter) {
		logger.info("Finding Element with the locator" + locator);
		//WebElement element = driver.get().findElement(locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.info("Element found and now enter special Key" + keyToEnter);
		element.sendKeys(keyToEnter);
	}

	public void selectFromDropdown(By dropdownLocator, String optionToSelect) {
		logger.info("Finding Element with the locator" + dropdownLocator);
		WebElement element = driver.get().findElement(dropdownLocator);
		Select select = new Select(element);
		logger.info("Selecting the option " + optionToSelect);
		try {
			select.selectByVisibleText(optionToSelect);
		} catch (Exception e) {
			System.out.println("Unable to locate california from the state dropdown");
			logger.info("Unable to locate california from the state dropdown");
		}
		// That's why value is being used, Note: its not a good practice
		select.selectByIndex(1);
		// selectByValue("5");

	}

	public String getVisibleText(By locator) {
		logger.info("Finding Element with the locator" + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and returning the visible Text" + element.getText());
		return element.getText();
	}

	public List<String> getAllVisibleText(By locator) {
		logger.info("Finding all Elements with the locator" + locator);
		List<WebElement> elementList = driver.get().findElements(locator);
		logger.info("Elements found and now printing the list of Elements");
		List<String> visibleTextList = new ArrayList<String>();
		for (WebElement element : elementList) {
			System.out.println(getVisibleText(element));
			visibleTextList.add(getVisibleText(element));
		}

		return visibleTextList;
	}

	public List<WebElement> getAllElements(By locator) {
		logger.info("Finding all Elements with the locator" + locator);
		List<WebElement> elementList = driver.get().findElements(locator);
		logger.info("Elements found and now printing the list of Elements");

		return elementList;
	}

	public String getVisibleText(WebElement element) {

		logger.info("Returning the visible Text" + element.getText());
		return element.getText();
	}

	public void quit() {
		driver.get().quit();
	}

	public String takeScreenShot(String name) {
		// TakesScreenshot screenshot = (TakesScreenshot)driver; //Casting WebD instance
		// to TakesScreenshot
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();

		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE); // gets SS
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);
		// String path = System.getProperty("user.dir")+"//screenshots//"+ name+" -
		// "+timeStamp+".png"; //need a path to store SS
		// can be written above step as below, making it as relative path instead
		// absolute path
		String path = "./screenshots/" + name + " - " + timeStamp + ".png";
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

}