package com.test.config;

import java.io.File;

public enum Configuration {

	
	HOSTName("Windows7"),
	username("Sreeraj"),
	environment("Test"),
	TestDataFilePath(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"TestData"+File.separator),
	driverPath(System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"Drivers"+File.separator),
	reportFilePath(System.getProperty("user.dir")+File.separator+"test-output"+File.separator+"Reports"),
	
	FileName("TestData.xls"),
	SheetName("Details"),
	URI("http://store.demoqa.com"),
	
	;
	private String property;
	private Configuration(String property){
		this.setProperty(property);
		
	}
	public void setProperty(String property) {
		this.property=property;
		
	}
	public String getProperty(){
		return property;
		
	}
}
