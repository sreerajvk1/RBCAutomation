package com.test.PageObjects;

public enum Accessories {

	
	AddToCartButtonMagicMouse("//a[text()='Magic Mouse']/ancestor::div[@class='productcol']/form/div[2]/div[1]/span/input"),
	
	;
	private String property;
	private Accessories(String property){
		this.setProperty(property);
		
	}
	public void setProperty(String property) {
		this.property=property;
		
	}
	public String getProperty(){
		return property;
		
	}
}
