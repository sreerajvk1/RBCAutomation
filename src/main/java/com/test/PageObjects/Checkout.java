package com.test.PageObjects;

public enum Checkout {

	
	EmailByID("wpsc_checkout_form_9"),
	FirstNameByID("wpsc_checkout_form_2"),
	LastNameByID("wpsc_checkout_form_3"),
	AddressByID("wpsc_checkout_form_4"),
	CityByID("wpsc_checkout_form_5"),
	StateByID("wpsc_checkout_form_6"),
	PhoneByID("wpsc_checkout_form_18"),
	CountryListByID("wpsc_checkout_form_7"),
	ShippingAddressCheckBoxID("shippingSameBilling"),
	PurchaseButtonXpath(".//*[@id='wpsc_shopping_cart_container']/form/div[4]/div/div/span/input");
	
	;
	private String property;
	private Checkout(String property){
		this.setProperty(property);
		
	}
	public void setProperty(String property) {
		this.property=property;
		
	}
	public String getProperty(){
		return property;
		
	}
}
