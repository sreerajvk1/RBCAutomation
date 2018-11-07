package com.test.commonFunctions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.test.config.Configuration;

public class Driver {

	static WebDriver driver;


	public static WebDriver OpenApp(String BrowserName, String url) {
		fn_LaunchBrowser(BrowserName);
		fn_OpenURL(url);
		return driver;
	}

	public static void fn_OpenURL(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	public static WebDriver fn_LaunchBrowser(String browsername) {
		if (browsername.equalsIgnoreCase("CHROME")) {
			
			System.setProperty("webdriver.chrome.driver", Configuration.driverPath.getProperty()+"chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browsername.equalsIgnoreCase("FIREFOX")) {
			System.setProperty("webdriver.gecko.driver", Configuration.driverPath+"geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browsername.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", Configuration.driverPath+"IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		return driver;
	}
	public static WebDriver getDriver(){
		return driver;
	}

}
