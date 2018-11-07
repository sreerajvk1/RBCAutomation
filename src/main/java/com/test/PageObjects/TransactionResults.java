package com.test.PageObjects;

public enum TransactionResults {

	
	TransactionTableFirstItem(".//*[@id='post-30']/div/div[2]/table/tbody/tr/td[1]"),
	TransactionTableRows(".//*[@id='post-30']/div/div[2]/table/tbody/tr"),
	
	;
	private String property;
	private TransactionResults(String property){
		this.setProperty(property);
		
	}
	public void setProperty(String property) {
		this.property=property;
		
	}
	public String getProperty(){
		return property;
		
	}
}
