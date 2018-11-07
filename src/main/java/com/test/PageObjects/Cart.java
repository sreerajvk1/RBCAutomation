package com.test.PageObjects;

public enum Cart {

	
	ProductTableFirstItem(".//*[@id='checkout_page_container']/div[1]/table/tbody/tr[2]/td[2]/a"),
	CartTableRows(".//*[@id='checkout_page_container']/div[1]/table/tbody/tr"),
	ContinueButton(".//*[@id='checkout_page_container']/div[1]/a/span")
	;
	private String property;
	private Cart(String property){
		this.setProperty(property);
		
	}
	public void setProperty(String property) {
		this.property=property;
		
	}
	public String getProperty(){
		return property;
		
	}
}
