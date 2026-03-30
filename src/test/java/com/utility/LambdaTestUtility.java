package com.utility;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LambdaTestUtility {
	private static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
	private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<WebDriver>();
	private static ThreadLocal<DesiredCapabilities> capabilitiesLocal = new ThreadLocal<DesiredCapabilities>();

	public static WebDriver initializeLambdaTestSession(String Browser, String testName) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", Browser);
		capabilities.setCapability("browserVersion", "latest");
		Map<String, Object> ltOptions = new HashMap();
		ltOptions.put("user", "vaidyaabhi20"); // added from LambdaTest website
		ltOptions.put("accessKey", "LT_YnF955vZdnhori4XELZdDSCm08tedbvUcTdx9EypdcPvvKW"); // added from LambdaTest
																							// website
		ltOptions.put("build", "Selenium 4");
		ltOptions.put("name", testName);
		ltOptions.put("platformName", "Windows 10");
		ltOptions.put("seCdp", true);
		ltOptions.put("selenium_version", "latest");
		capabilities.setCapability("LT:Options", ltOptions);

		capabilitiesLocal.set(capabilities);
		// WebDriver driver = new RemoteWebDriver(new URL(HUB_URL), capabilities); //URL
		// is deprecated
		WebDriver driver=null;
		try {
			driver = new RemoteWebDriver(new URI(HUB_URL).toURL(), capabilitiesLocal.get());
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		}
		driverLocal.set(driver);
		return driverLocal.get();
	}
	
	public static void quitSession() {
		if(driverLocal.get()!=null)
			driverLocal.get().quit();
	}
}
