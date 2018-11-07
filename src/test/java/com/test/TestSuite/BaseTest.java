package com.test.TestSuite;

import com.test.config.*;
import com.test.commonFunctions.*;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.test.Constants.*;
import com.test.Report.ExtentReporting;

import com.test.testData.DataObjects;
import com.test.dataExtract.*;

@Listeners(ExtentReporting.class)
public class BaseTest extends Utilities{

	public String browserName = "";
	protected static WebDriver driver;
	protected String reportFilepath = Configuration.reportFilePath.getProperty();
	protected String URL = Configuration.URI.getProperty();
	protected String hostName = Configuration.HOSTName.getProperty();
	protected String environment = Configuration.environment.getProperty();
	protected String userName = Configuration.username.getProperty();
	protected static DataObjects dataObjects;

	public BaseTest() {

		ExtentReporting.extentReportConfig(reportFilepath, hostName, environment, userName);
		ExcelObjects.testDataReading(Configuration.SheetName.getProperty());
		DOMConfigurator.configure("log4j.xml");
	}

	public WebDriver init(){
		
	
		if(browserName.equalsIgnoreCase("IE")){
			driver = Driver.fn_LaunchBrowser("IE");
			
		}
		else if(browserName.equalsIgnoreCase("CHROME")){
			driver = Driver.fn_LaunchBrowser("CHROME");
			
		}
		else if(browserName.equalsIgnoreCase("FIREFOX")){
			driver = Driver.fn_LaunchBrowser("FIREFOX");
			
		}else{
			ExtentReporting.logger.info("Unsupported browser");
			ExtentReporting.log(Status.FAIL, "Unsupported browser");
			assertEquals(false, true);
		}
		dataObjects = new DataObjects();
		return driver;
	}
	
	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
public void beforemethode(String browser) throws InterruptedException{
		browserName = browser;
		driver = init();
		
		Driver.fn_OpenURL(URL);
		System.out.println("before methode");
	}
	@AfterTest(alwaysRun = true)
	public void aftertest(){
		
		ExcelObjects.closeExcel();
	}
	
	

}
