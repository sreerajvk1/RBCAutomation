package com.test.PageObjects;

public enum ToolsHomePage {

	
	PageTitle("ONLINE STORE | Toolsqa Dummy Test site"),
	ProductCatrgoryMenuID("menu-item-33"),
	AccessoriesLinkText("Accessories"),
	CartIconByID("header_cart")	;
	
	
	private String property;
	private ToolsHomePage(String property){
		this.setProperty(property);
		
	}
	public void setProperty(String property) {
		this.property=property;
		
	}
	public String getProperty(){
		return property;
		
	}
}
