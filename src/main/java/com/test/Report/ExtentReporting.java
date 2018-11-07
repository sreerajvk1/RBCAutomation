package com.test.Report;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.test.Constants.Status;
import com.test.commonFunctions.Driver;
import com.test.config.Configuration;

public class ExtentReporting implements ITestListener, ISuiteListener {

	public static final Logger logger = Logger.getLogger(ExtentReporting.class);
	//public Logger logger = Logger.getLogger(ExtentReporting.class);
	public static String filePath;
	public static String hostName;
	public static String environment;
	public static String userName;
	public static String reportPath;
	public static String testCaseName;

	private static ExtentReports report;
	private static ExtentTest extentLog;

	

	// Log status of the testCase
	public static void log(Status status, String comment) {
		switch (status) {

		case PASS:
			extentLog.log(LogStatus.PASS, comment);
			break;
		case FAIL:
			extentLog.log(LogStatus.FAIL, comment);
			break;
		case INFO:
			extentLog.log(LogStatus.INFO, comment);
			break;
		case FATAL:
			extentLog.log(LogStatus.FATAL, comment);
			break;
		default:
			logger.info("Check the status entered");

		}
	}

	private static WebDriver getDrivers() {

		if (Driver.getDriver() == null) {
			log(Status.FAIL, "Driver is null");
			logger.warn("Driver is null");
			throw new WebDriverException("Driver is null- check the driver call");

		}
		return Driver.getDriver();

	}

	private static String getCurrentDate() {
		return new SimpleDateFormat("MM-dd-yyyy HH-mm-ss").format(new Date()).toString().replaceFirst(" ", "-");
	}

	public static void takeScreenshot() {
		String screenshotPath = Configuration.reportFilePath.getProperty()+reportPath + File.separator + testCaseName + "-Screenshot/" + testCaseName.split("-")[1]
				+ "-" + getCurrentDate() + ".jpg";
		
		System.out.println(screenshotPath);
		try{
			FileUtils.copyFile(((TakesScreenshot) getDrivers()).getScreenshotAs(OutputType.FILE),new File(screenshotPath));
			log(Status.INFO, extentLog.addScreenCapture(screenshotPath));
			log(Status.INFO, "Screenshot taken for " +testCaseName);
			logger.info("Screenshot taken for " +testCaseName);
		
		}
		catch(WebDriverException e){
			log(Status.FAIL, "Driver is null"+e.getMessage());
			logger.warn("Driver is null"+e.getMessage());
		}
		catch(IOException e){
			log(Status.FAIL, "Driver is null"+e.getMessage());
			logger.warn("Driver is null");
		}
	}

	public void onFinish(ISuite suite) {
		report.endTest(extentLog);
		report.flush();

	}

	public void onStart(ISuite suite) {
		
		reportPath = File.separator+suite.getName()+File.separator+suite.getName()+"-"+getCurrentDate();
		report = new ExtentReports(filePath+reportPath+"/report.html",true);
		
		if(hostName == null|| hostName ==""){
			
			hostName="null";
		}else if(environment == null|| environment ==""){
			environment="null";
		}
		else if(userName == null|| userName ==""){
			userName="null";
		}
		report.addSystemInfo("Host Name",hostName).addSystemInfo("Environment",environment).addSystemInfo("User Name",userName);

	}

	public void onFinish(ITestContext context) {
		
		System.out.println("test finish");
		report.endTest(extentLog);
		if(Driver.getDriver()!=null){
			
			Driver.getDriver().quit();
		}

	}

	public void onStart(ITestContext context) {
		
		System.out.println("onstart");
	

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	

	}

	public void onTestFailure(ITestResult result) {
		
		System.out.println("onteststart");
		testCaseName=result.getMethod().getRealClass().getSimpleName()+"-"+ result.getMethod().getMethodName();
		extentLog = report.startTest(testCaseName);
		log(Status.PASS, result.getMethod().getMethodName());
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestStart(ITestResult result) {
		System.out.println("onteststart");
		testCaseName= result.getMethod().getRealClass().getSimpleName()+"-"+ result.getMethod().getMethodName();
		extentLog = report.startTest(testCaseName);
		log(Status.INFO, result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("ontestsuccess");
		extentLog.log(LogStatus.PASS, result.getMethod().getMethodName());

	}

	public static void extentReportConfig(String filepath, String hostName, String environment, String userName) {

		ExtentReporting.filePath = filepath;
		ExtentReporting.hostName = hostName;
		ExtentReporting.environment = environment;
		ExtentReporting.userName = userName;

	}

}
