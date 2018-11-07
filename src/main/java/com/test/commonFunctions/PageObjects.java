package com.test.commonFunctions;

public enum PageObjects {

	URl("https://www.capitalone.com/"),
	DestFilePath("E:\\Selenium1\\sreerajvk1\\test-output\\Screenshots\\"),
	dropDownXpath("//*[@id='navWrapper']/div/div[1]/div/ul[2]/li[1]/ul/li[2]/a"),
	LinkText("View Top Rewards Cards"),
	HomePageMsg("Home page loaded Successfully"),
	MousoverMsg("Mousover worked and Link displayed"),
	LinkClickedMsg("Link clicked");
	
	
	private String property;
	private PageObjects(String property){
		this.setProperty(property);
		
	}
	
	private void setProperty(String property) {
		
		this.property =property;
		
	}

	public String getProperty(){
		
		return property;
	}
}
