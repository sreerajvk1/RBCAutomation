package com.test.dataExtract;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.test.Constants.Status;
import com.test.Report.ExtentReporting;
import com.test.config.Configuration;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelObjects {
	// private static final Logger logger =
	// Logger.getLogger(ExcelObjects.class);
	private static HashMap<String, String> testDataMap = new HashMap<String, String>();
	private static FileInputStream fs;
	private static Workbook wb;
	private static Sheet sh;

	// Read Test data from excel based on file path and sheet Name

	public static void testDataReading(String sheetName) {

		try {

			fs = new FileInputStream(
					Configuration.TestDataFilePath.getProperty() + Configuration.FileName.getProperty());
			wb = Workbook.getWorkbook(fs);
			sh = wb.getSheet(Configuration.SheetName.getProperty());
			ExtentReporting.logger.info("Test Data XL instance created....");
		} catch (FileNotFoundException e) {
			ExtentReporting.logger.warn("XL file not found in the path" + e.getMessage());
		} catch (IOException e) {
			ExtentReporting.logger.warn("IO exception , check the file ..." + e.getMessage());
		} catch (BiffException e) {
			ExtentReporting.logger.warn("Biff exception..." + e.getMessage());

		}
	}

	// Read Specific data to a map

	public static Map<String, String> readSpecificData(String testCaseName) {

		
		for (int i = 0; i < sh.getRows(); i++) {
			if (sh.getCell(0, i).getContents().equalsIgnoreCase(testCaseName)) {

				for (int j = 1; j < sh.getColumns(); j++) {

					testDataMap.put(sh.getCell(j, 0).getContents(), sh.getCell(j, i).getContents());

				}
				break;
			}
		}
		if (testDataMap.isEmpty()) {
			ExtentReporting.logger.warn("Test Case Not found on the Test data sheet");

		}

	/*	for (String name : testDataMap.keySet()) {

			String key = name.toString();
			String value = testDataMap.get(name).toString();
			System.out.println(key + " " + value);

		}*/
		return testDataMap;

	}

	public static void closeExcel() {

		try {
			wb.close();
			fs.close();
			ExtentReporting.log(Status.INFO, "Test data excel object closed");
			ExtentReporting.logger.info("Test data excel object closed");
		} catch (IOException e) {
			ExtentReporting.log(Status.FAIL, "I/O exception while closing the object" + e.getMessage());
			ExtentReporting.logger.warn("I/O exception while closing the object" + e.getMessage());
		}
	}
}
