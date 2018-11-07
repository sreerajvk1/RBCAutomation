package com.test.TestSuite;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.NoSuchElementException;

import javax.net.ssl.HostnameVerifier;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.test.Constants.Status;
import com.test.PageObjects.Accessories;
import com.test.PageObjects.Cart;
import com.test.PageObjects.Checkout;
import com.test.PageObjects.TestForm;
import com.test.PageObjects.ToolsHomePage;
import com.test.PageObjects.TransactionResults;
import com.test.Report.ExtentReporting;
import com.test.testData.DataObjects;

@Listeners(ExtentReporting.class)
public class TC01_MagicMouse_Validation extends BaseTest {

	

	public static void validateMainPage() {

		assertEquals(fn_findElementByID(driver, ToolsHomePage.ProductCatrgoryMenuID.getProperty()).isDisplayed(), true,
				"Online Store Page not Loaded with Product Category submenu");
		ExtentReporting.takeScreenshot();
		ExtentReporting.logger.info("Home Page displayed");
		ExtentReporting.log(Status.PASS, "Home Page displayed");

	}

	public static void navigateProductCategories() {

		MouseHover(fn_findElementByID(driver, ToolsHomePage.ProductCatrgoryMenuID.getProperty()), driver);
		ExtentReporting.takeScreenshot();
		ExtentReporting.logger.info("Navigated to Product Categories by Mouse Hover");
		ExtentReporting.log(Status.PASS, "Navigated to Product Categories by Mouse Hover");

	}

	public static void navigateToAccessories() {

		try {
			waitForVisibilityByLinkText(driver, 4, ToolsHomePage.AccessoriesLinkText.getProperty());
			selctorWithLinkText(driver, ToolsHomePage.AccessoriesLinkText.getProperty());
			ExtentReporting.takeScreenshot();
			ExtentReporting.logger.info("Navigated to Product Categories by Mouse Hover");
			ExtentReporting.log(Status.PASS, "Navigated to Product Categories by Mouse Hover");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void addItemToCart() {

		try {

			waitForVisibilityByXpath(driver, 10, Accessories.AddToCartButtonMagicMouse.getProperty());
			selctorWithXpath(driver, Accessories.AddToCartButtonMagicMouse.getProperty());
			ExtentReporting.takeScreenshot();
			ExtentReporting.logger.info("Clicked on Add to Cart");
			ExtentReporting.log(Status.PASS, "Clicked on Add to Cart");
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}
	
	public static void navigateToCart() {

		try {
			
			
			waitForVisibilityByID(driver, 10, ToolsHomePage.CartIconByID.getProperty());
			selctorWithID(driver, ToolsHomePage.CartIconByID.getProperty());
			ExtentReporting.takeScreenshot();
			ExtentReporting.logger.info("Clicked on Cart Icon");
			ExtentReporting.log(Status.PASS, "Clicked on Cart Icon");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	public static void validateAddedItemInCart() {

		try {
			
			
			waitForVisibilityByXpath(driver, 10, Cart.ProductTableFirstItem.getProperty());
			
			int rows =(findmoreByXpath(driver, Cart.CartTableRows.getProperty())).size();
			int flag =0;
			for(int row=2;row<=rows;row++){
				
				
				if (dataObjects.getField1().equalsIgnoreCase(findByXpath(driver, ".//*[@id='checkout_page_container']/div[1]/table/tbody/tr[" + row + "]/td[2]/a").getText())){
					
					
					
					assertEquals(dataObjects.getField2().equalsIgnoreCase(findByXpath(driver, ".//*[@id='checkout_page_container']/div[1]/table/tbody/tr[" + row + "]/td[3]/form/input[1]").getAttribute("value")), true,"Count doesnt match");
					
					assertEquals(dataObjects.getField3().equalsIgnoreCase(findByXpath(driver, ".//*[@id='checkout_page_container']/div[1]/table/tbody/tr[" + row + "]/td[4]/span").getText()), true,"Price doesnt match");	
					assertEquals(dataObjects.getField4().equalsIgnoreCase(findByXpath(driver, ".//*[@id='checkout_page_container']/div[1]/table/tbody/tr[" + row + "]/td[5]/span/span").getText()), true,"Total doesnt match");	
	
					System.out.println(findByXpath(driver, ".//*[@id='checkout_page_container']/div[1]/table/tbody/tr[" + row + "]/td[2]/a").getText());
					System.out.println(findByXpath(driver, ".//*[@id='checkout_page_container']/div[1]/table/tbody/tr[" + row + "]/td[3]/form/input[1]").getAttribute("value"));
					System.out.println(findByXpath(driver, ".//*[@id='checkout_page_container']/div[1]/table/tbody/tr[" + row + "]/td[4]/span").getText());
					System.out.println(findByXpath(driver, ".//*[@id='checkout_page_container']/div[1]/table/tbody/tr[" + row + "]/td[5]/span/span").getText());
	
					flag=1;
					
				}
			}	
			assertEquals(flag!=0, true,"Item did not found in Cart");
			
			ExtentReporting.takeScreenshot();
			ExtentReporting.logger.info("Cart Item validated");
			ExtentReporting.log(Status.PASS, "Cart Item validated");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	public static void selectContinueToCheckout() {

		try {
			
			
			waitForVisibilityByXpath(driver, 10, Cart.ContinueButton.getProperty());
			selctorWithXpath(driver, Cart.ContinueButton.getProperty());
			ExtentReporting.takeScreenshot();
			ExtentReporting.logger.info("Clicked on Cart Continue");
			ExtentReporting.log(Status.PASS, "Clicked on Cart Continue");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	public static void fillDetailsInCheckout() {

		try {
			
			
			waitForVisibilityByID(driver, 10, Checkout.EmailByID.getProperty());
						
			fn_fillTextBox(findByID(driver,Checkout.FirstNameByID.getProperty()), dataObjects.getField5());
			fn_fillTextBox(findByID(driver,Checkout.LastNameByID.getProperty()), dataObjects.getField6());
			fn_fillTextBox(findByID(driver,Checkout.AddressByID.getProperty()), dataObjects.getField7());	
			fn_fillTextBox(findByID(driver,Checkout.CityByID.getProperty()), dataObjects.getField8());
			fn_fillTextBox(findByID(driver,Checkout.StateByID.getProperty()), dataObjects.getField9());
			fn_fillTextBox(findByID(driver,Checkout.PhoneByID.getProperty()), dataObjects.getField11());
			fn_fillTextBox(findByID(driver,Checkout.EmailByID.getProperty()), dataObjects.getField12());
			
			scrolluntilElement(driver, findByID(driver,Checkout.ShippingAddressCheckBoxID.getProperty()));
			selctorWithID(driver, Checkout.ShippingAddressCheckBoxID.getProperty());
			dropdownSelectByText(findByID(driver, Checkout.CountryListByID.getProperty()),  dataObjects.getField10());
			
			ExtentReporting.takeScreenshot();
			selctorWithXpath(driver, Checkout.PurchaseButtonXpath.getProperty());
			
			ExtentReporting.logger.info("Clicked on Purchase");
			ExtentReporting.log(Status.PASS, "Clicked on Purchase");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	public static void validatePurchaseConfirmation() {

		try {
			
			
			waitForVisibilityByXpath(driver, 10, TransactionResults.TransactionTableFirstItem.getProperty());
			
			int rows =(findmoreByXpath(driver, TransactionResults.TransactionTableRows.getProperty())).size();
			int flag =0;
			for(int row=1;row<=rows;row++){
				
				
				if (dataObjects.getField1().equalsIgnoreCase(findByXpath(driver, ".//*[@id='post-30']/div/div[2]/table/tbody/tr[" + row + "]/td[1]").getText())){
					
					
					
					assertEquals(dataObjects.getField2().equalsIgnoreCase(findByXpath(driver, ".//*[@id='post-30']/div/div[2]/table/tbody/tr[" + row + "]/td[3]").getText()), true,"Price doesnt match ");
					
					assertEquals(dataObjects.getField3().equalsIgnoreCase(findByXpath(driver, ".//*[@id='post-30']/div/div[2]/table/tbody/tr[" + row + "]/td[2]").getText()), true,"Count doesnt match");	
					assertEquals(dataObjects.getField4().equalsIgnoreCase(findByXpath(driver, ".//*[@id='post-30']/div/div[2]/table/tbody/tr[" + row + "]/td[4]").getText()), true,"Total doesnt match");	
	
					
					flag=1;
					
				}
			}	
			assertEquals(flag!=0, true,"Item not found in Transaction details");
			
			ExtentReporting.takeScreenshot();
			ExtentReporting.logger.info("Transaction details validated");
			ExtentReporting.log(Status.PASS, "Transaction details validated");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	@Test
	public void Validate_MagicMouse() {
		try {
			dataObjects.setTestData("TC01_MagicMouse_Validation");

			validateMainPage();
			navigateProductCategories();
			navigateToAccessories();
			addItemToCart();
			navigateToCart();
			validateAddedItemInCart();
			selectContinueToCheckout();
			fillDetailsInCheckout();
			validatePurchaseConfirmation();
			

		} catch (NoSuchElementException e) {
			ExtentReporting.takeScreenshot();
			ExtentReporting.logger.info("No such Element" + e.getMessage());
			ExtentReporting.log(Status.PASS, "No such Element" + e.getMessage());

		} finally {
			driver.close();
			driver.quit();
		}

	}

}
