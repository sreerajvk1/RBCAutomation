package com.test.PageObjects;

public enum TestForm {

	
	PageTitle("/html/body/h1"),
	PageTitleLabel("Test Form"),
	FirstName("fname"),
	LastName("lname"),
	PhoneNumber("phone"),
	
	Submit("submit"),
	Clear("//form/button[2]"),
	
	FirstNameError("fname-error"),
	FirstNameTypeErrorMessage("First name can only be characters"),
	FirstNameLengthErrorMessage("The max length of first name is 20"),
	FirstNameMandatoryErrorMessage("First name is a required field"),
	
	errorMessageColor("rgba(255, 0, 0, 1)"),
	
	LastNameError("lname-error"),
	LastNameTypeErrorMessage("Last name can only be characters"),
	LastNameLengthErrorMessage("The max length of first name is 20"),
	LastNameMandatoryErrorMessage("Last name is a required field"),
	
	PhoneNumberError("phone-error"),
	PhoneNubmerIncorrectErrorMessage("Phone is incorrect"),
	PhoneNubmerMandatoryErrorMessage("Phone number is a required input"),
	
	;
	private String property;
	private TestForm(String property){
		this.setProperty(property);
		
	}
	public void setProperty(String property) {
		this.property=property;
		
	}
	public String getProperty(){
		return property;
		
	}
}
