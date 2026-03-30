package com.ui.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;

public class TestListner implements ITestListener {
	Logger logger = LoggerUtility.getLogger(this.getClass());
	//Note: Extent Reports using here is not Thread safe(If Many tests are there, order of execution might vary)
	// A separate custom utility will be created for extent reports
	ExtentSparkReporter extentSparkReporter; //helps in create html file
	ExtentReports extentReports; //heavy lifting,ie, data to be dumped in html file is done by this
	ExtentTest extentTest;//to Store Info about test
	//Return Type of non primitiv data type is null
	

	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));
		//extentTest = extentReports.createTest(result.getMethod().getMethodName());
		ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName()); // getting from utitly class
	}

	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + " "+"PASSED");
		ExtentReporterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + " "+"PASSED");
	}

	public void onTestFailure(ITestResult result) {
		logger.error(result.getMethod().getMethodName() + " "+"FAILED");
		logger.error(result.getThrowable().getMessage());
		ExtentReporterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " "+"FAILED");
		ExtentReporterUtility.getTest().log(Status.FAIL, result.getThrowable().getMessage());

		Object testclass=result.getInstance();
		BrowserUtility browserUtility= ((TestBase)testclass).getInstance();
		logger.info("Capturing Screenshot for the failed tests");
		String screenshotPath = browserUtility.takeScreenShot(result.getMethod().getMethodName());
		logger.info("Attaching the dcreenshot to HTML file");
		ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenshotPath);
	}

	public void onTestSkipped(ITestResult result) {
		logger.warn(result.getMethod().getMethodName() + " "+"SKIPPED");
		ExtentReporterUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " "+"SKIPPED");
	}

	public void onStart(ITestContext context) {
		logger.info("Test Suite Started");
		ExtentReporterUtility.setUpSparkReporter("report.html");//From Utility class
	}

	public void onFinish(ITestContext context) {
		logger.info("Test Finished");
		//extentReports.flush(); //must and should be there
		ExtentReporterUtility.flushReport(); // getting from Utility class
	}

}
