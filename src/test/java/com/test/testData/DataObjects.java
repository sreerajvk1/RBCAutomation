package com.test.testData;

import java.util.Map;

import com.test.dataExtract.ExcelObjects;

public class DataObjects {

	private String field1;
	private String field2;
	private String field3;
	private String field4;
	private String field5;
	private String field6;
	private String field7;
	private String field8;
	private String field9;
	private String field10;
	private String field11;
	private String field12;
	public String getField1() {
		return field1;
	}

	private void setField1(String field1) {
		this.field1 = field1;

	}
	public String getField2() {
		return field2;
	}

	private void setField2(String field2) {
		this.field2 = field2;

	}
	public String getField3() {
		return field3;
	}

	private void setField3(String field3) {
		this.field3 = field3;

	}
	public String getField4() {
		return field4;
	}

	private void setField4(String field4) {
		this.field4 = field4;

	}
	public String getField5() {
		return field5;
	}

	private void setField5(String field5) {
		this.field5 = field5;

	}
	
	
	public String getField6() {
		return field6;
	}

	private void setField6(String field6) {
		this.field6 = field6;

	}
	public String getField7() {
		return field7;
	}

	private void setField7(String field7) {
		this.field7 = field7;

	}
	public String getField8() {
		return field8;
	}

	private void setField8(String field8) {
		this.field8 = field8;

	}
	public String getField9() {
		return field9;
	}

	private void setField9(String field9) {
		this.field9 = field9;

	}
	public String getField10() {
		return field10;
	}

	private void setField10(String field10) {
		this.field10 = field10;

	}
	public String getField11() {
		return field11;
	}

	private void setField11(String field11) {
		this.field11 = field11;

	}
	public String getField12() {
		return field12;
	}

	private void setField12(String field12) {
		this.field12 = field12;

	}
	
	
	public void setTestData(String testCaseName){
		
		Map<String,String> dataMap=ExcelObjects.readSpecificData(testCaseName);
		setField1(dataMap.get("Field1"));
		
		setField2(dataMap.get("Field2"));
		setField3(dataMap.get("Field3"));
		setField4(dataMap.get("Field4"));
		setField5(dataMap.get("Field5"));
		setField6(dataMap.get("Field6"));
		setField7(dataMap.get("Field7"));
		setField8(dataMap.get("Field8"));
		setField9(dataMap.get("Field9"));
		setField10(dataMap.get("Field10"));
		setField11(dataMap.get("Field11"));
		setField12(dataMap.get("Field12"));
		
		
		
	}
	
	
}
