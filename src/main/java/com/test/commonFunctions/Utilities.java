package com.test.commonFunctions;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.test.Constants.DriverConstants;
import com.test.Constants.DriverConstants.Action;
import com.test.Constants.Status;
import com.test.Report.ExtentReporting;

public class Utilities extends ExtentReporting {

	public static WebElement fn_findElementByID(WebDriver driver, String ID) {

		return driver.findElement(By.id(ID));

	}

	public static WebElement fn_findElementByXpath(WebDriver driver, String Xpath) {

		return driver.findElement(By.xpath(Xpath));

	}

	public static void fn_fillTextBox(WebElement WE, String text) {

		WE.sendKeys(text);
	}

	public static void fn_clickElementByID(WebDriver driver, String ID) {

		WebElement WE = driver.findElement(By.id(ID));
		if (WE.isDisplayed()) {
			WE.click();
			ExtentReporting.logger.info("Clicked on Element");
			ExtentReporting.log(Status.PASS, "Clicked on Element");
		} else {

			ExtentReporting.logger.info("Element not displayed");
			ExtentReporting.log(Status.FAIL, "Element not displayed");
		}

	}

	public static void fn_clickElementByXpath(WebDriver driver, String Xpath) {

		WebElement WE = driver.findElement(By.xpath(Xpath));
		if (WE.isDisplayed()) {
			WE.click();
			ExtentReporting.logger.info("Clicked on Element");
			ExtentReporting.log(Status.PASS, "Clicked on Element");
		} else {

			ExtentReporting.logger.info("Element not displayed");
			ExtentReporting.log(Status.FAIL, "Element not displayed");
		}

	}

	// MouseOver action method
	public static void MouseHover(WebElement we, WebDriver driver) {

		try {
			if (driver == null) {
				throw new WebDriverException("Driver instance is null to perform mouseover:");
			}
			Actions actObj = new Actions(driver);
			actObj.moveToElement(we).build().perform();
		} catch (StaleElementReferenceException e) {
			logger.warn("Element no longer attached to DOM to mouseover" + e.getMessage());
		} catch (WebDriverException e) {
			logger.warn(e.getStackTrace());
		}
	}

	// MouseOverBy position
	public static void MouseHoverbyOffset(WebElement we, WebDriver driver, int x, int y) {

		try {
			if (driver == null) {
				throw new WebDriverException("Driver instance is null to perform mouseover:");
			}
			Actions actObj = new Actions(driver);
			actObj.moveByOffset(x, y).build().perform();
		} catch (StaleElementReferenceException e) {
			logger.warn("Element no longer attached to DOM to mouseover by offset" + e.getMessage());
		} catch (WebDriverException e) {
			logger.warn(e.getStackTrace());
		}
	}

	// select the dropdown using "select by visible text", so pass VisibleText
	// as 'Yellow' to funtion
	public static void fn_Selectby_visible_text(WebElement WE, String VisibleText) {

		try {
			Select selObj = new Select(WE);
			selObj.selectByVisibleText(VisibleText);
		} catch (NoSuchElementException e) {
			logger.warn("Check the drop down value/Index or ID: " + e.getMessage());
		}
	}

	// select the dropdown using "select by index", so pass IndexValue as '2'
	public static void fn_Selectby_index(WebElement WE, int IndexValue) {
		try {
			Select selObj = new Select(WE);
			selObj.selectByIndex(IndexValue);
		} catch (NoSuchElementException e) {
			logger.warn("Check the drop down value/Index or ID: " + e.getMessage());
		} catch (IndexOutOfBoundsException e) {
			logger.warn("Check the dropwon index -No such element" + e.getMessage());
		}
	}

	// select the dropdown using "select by value", so pass Value as
	// 'thirdcolor'
	public static void fn_Selectby_value(WebElement WE, String Value) {
		try {
			Select selObj = new Select(WE);
			selObj.selectByValue(Value);
		} catch (NoSuchElementException e) {
			logger.warn("Check the drop down value/Index or ID: " + e.getMessage());
		}

	}

	// Function to launch URL
	public void launchURL(WebDriver driver, String url) {

		try {
			if (driver == null) {
				throw new WebDriverException("Driver instance is null:");
			}
			driver.get(new URL(url).toString());
			logger.info("URL launched");

		} catch (MalformedURLException e) {
			logger.warn(url + " not valid");
		} catch (WebDriverException e) {
			logger.warn(e.getStackTrace());
		}
	}

	// Method to click on a WebElement
	public void click(WebElement element) {
		try {
			element.click();
			logger.info("Element Clicked");
		} catch (StaleElementReferenceException e) {
			logger.info("Element no longer attached to DOM to click:" + e.getMessage());
		}

	}

	// Method to enter test int a Textbox
	public void sendKeys(WebElement element, String text) {

		try {
			element.sendKeys(text);
			logger.info("Text entered successfully");

		} catch (StaleElementReferenceException e) {
			logger.info("Element no longer attched to DOM to enter" + e.getMessage());
		}
	}
	// Wait for a specific web Element to load on a page

	public void webDriverWait(WebDriver driver, WebElement element, Action action) {

		try {

			if (driver == null) {
				throw new WebDriverException("Driver instance null to perform webDriverwait :");
			}
			WebDriverWait wait = new WebDriverWait(driver, DriverConstants.TIMEOUT);
			switch (action) {
			case CLICKABLE:
				wait.until(ExpectedConditions.elementToBeClickable(element));
				break;
			case VISIBILITY:
				wait.until(ExpectedConditions.visibilityOf(element));
				break;
			default:
				logger.warn("invalid element to perform wait");
			}
		} catch (StaleElementReferenceException e) {
			logger.warn("Element no longer attached to DOM: " + e.getMessage());
		} catch (WebDriverException e) {
			logger.warn(e.getMessage());
		}
	}

	// Fluent wait on the specific element

	public void fluentWait(WebDriver driver, final WebElement element) {
		try {
			if (driver == null) {
				throw new WebDriverException("Driver instance null to perform webDriverwait :");
			}

			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(DriverConstants.TIMEOUT, TimeUnit.SECONDS)
					.pollingEvery(DriverConstants.POLLING, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return element;
				}
			});

		} catch (StaleElementReferenceException e) {
			logger.warn("Element no longer attached to DOM :" + e.getMessage());
		} catch (WebDriverException e) {
			logger.warn(e.getMessage());
		}
	}

	// Switch to a frame method usig element

	public void switchToFrame(WebDriver driver, WebElement frameElement) {
		try {
			if (driver == null) {
				throw new WebDriverException("Driver instance null to perform switch to frame :");
			}

			driver.switchTo().frame(frameElement);
		} catch (StaleElementReferenceException e) {
			logger.warn("Element no longer attached to DOM :" + e.getMessage());
		} catch (WebDriverException e) {
			logger.warn(e.getMessage());
		}
	}

	// Switch to frame through index

	public void switchToFrame(WebDriver driver, int index) {
		try {
			if (driver == null) {
				throw new WebDriverException("Driver instance null to perform switch to frame :");
			}

			driver.switchTo().frame(index);
		} catch (StaleElementReferenceException e) {
			logger.warn("Element no longer attached to DOM :" + e.getMessage());
		} catch (WebDriverException e) {
			logger.warn(e.getMessage());
		}
	}
	// Switch to frame using name/id

	public void switchToFrame(WebDriver driver, String nameOrId) {
		try {
			if (driver == null) {
				throw new WebDriverException("Driver instance null to perform switch to frame :");
			}

			driver.switchTo().frame(nameOrId);
		} catch (StaleElementReferenceException e) {
			logger.warn("Element no longer attached to DOM :" + e.getMessage());
		} catch (WebDriverException e) {
			logger.warn(e.getMessage());
		}
	}

	// Perform switch to Default which will come out of frame
	public void switchtoDefaultContent(WebDriver driver) {
		try {
			if (driver == null) {
				throw new WebDriverException("Driver instance null to perform switch to Default content :");
			}

			driver.switchTo().defaultContent();
		} catch (WebDriverException e) {
			logger.warn(e.getMessage());
		}
	}

	// Switch to window by title

	public void switchToWindow(WebDriver driver, String title) {
		try {
			if (driver == null) {
				throw new WebDriverException(
						"Driver instance null to perform switch to window through nam eor handle :");
			}
			Set<String> windows = driver.getWindowHandles();
			boolean present = false;
			for (String window : windows) {
				if (driver.switchTo().window(window).getTitle().equalsIgnoreCase(title)) {
					driver.switchTo().window(window);
					present = true;
					break;
				}
			}
			if (present == false) {
				throw new NoSuchWindowException("Element no longer attached to DOM to perform switch to windows");

			}
		} catch (StaleElementReferenceException e) {
			logger.warn("Element no longer attached to DOM :" + e.getMessage());
		} catch (WebDriverException e) {
			logger.warn(e.getMessage());
		}
	}

	// Get All window names

	public void getAllActiveWindowNames(WebDriver driver) {
		try {
			if (driver == null) {
				throw new WebDriverException("Driver instance null to perform getwindowName :");
			}
			Set<String> wins = driver.getWindowHandles();
			logger.info("Number of windows currently active is :" + wins.size());
			log(Status.INFO, "Number of windows currently active is :" + wins.size());
			for (String win : wins) {

				String title = driver.switchTo().window(win).getTitle();
				logger.info(title);
				log(Status.INFO, title);

			}

		} catch (WebDriverException e) {
			logger.warn(e.getMessage());
		}
	}

	// Get the current URL

	public static String getCurrentURL(WebDriver driver) {

		return driver.getCurrentUrl();
	}

	// Close the browser

	public static void closeBrowser(WebDriver driver) {
		driver.close();
	}

	// Quit browser

	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}

	// Pasue the driver execution

	public static void pause(int sec) {
		try {
			Thread.sleep(sec * 1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Find by CSS selector

	public static WebElement findByCSSSelector(WebDriver driver, String selector) {
		return driver.findElement(By.cssSelector(selector));
	}

	// Find more by CSS selector

	public static List<WebElement> findmoreByCSSSelector(WebDriver driver, String selector) {
		return driver.findElements(By.cssSelector(selector));
	}

	// Find by ID

	public static WebElement findByID(WebDriver driver, String id) {
		return driver.findElement(By.id(id));
	}

	// Find by name

	public static WebElement findByName(WebDriver driver, String name) {
		return driver.findElement(By.name(name));
	}

	// Find by xpath

	public static WebElement findByXpath(WebDriver driver, String xpath) {
		return driver.findElement(By.xpath(xpath));
	}

	// Find more by xpath

	public static List<WebElement> findmoreByXpath(WebDriver driver, String xpath) {
		return driver.findElements(By.xpath(xpath));
	}
	// Find by classname

	public static WebElement findByClassName(WebDriver driver, String className) {
		return driver.findElement(By.className(className));
	}
	// Find more by class name

	public static List<WebElement> findmoreByClassName(WebDriver driver, String className) {
		return driver.findElements(By.className(className));
	}
	// Find by Link text

	public static WebElement findByLinkText(WebDriver driver, String linkText) {
		return driver.findElement(By.linkText(linkText));
	}

	// Find by partial Link text

	public static WebElement findBypartialLinkText(WebDriver driver, String partiallinkText) {
		return driver.findElement(By.partialLinkText(partiallinkText));
	}

	// Check whether an aleart is present or not

	public static boolean isAlertPresent(WebDriver driver) {

		try {
			driver.switchTo().alert();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	// Accept the alert

	public static void acceptAlert(WebDriver driver) {

		if (isAlertPresent(driver)) {
			driver.switchTo().alert();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();

		}
	}

	// Dismiss alert

	public static void dismissAlert(WebDriver driver) {

		if (isAlertPresent(driver)) {
			driver.switchTo().alert();
			driver.switchTo().alert().dismiss();

		}
	}

	// wait for visibility of an element using Xpath selector
	public static WebElement waitForVisibilityByXpath(WebDriver driver, int secs, String xpath) {

		WebElement elem = (new WebDriverWait(driver, secs))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		return elem;

	}

	// wait for visibility of an element using ID selector
	public static WebElement waitForVisibilityByID(WebDriver driver, int secs, String id) {

		WebElement elem = (new WebDriverWait(driver, secs))
				.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		return elem;

	}

	// Wait for the presence of elements by CSS
	public static List<WebElement> waitForPresencemorebyCss(WebDriver driver, int secs, String selector) {
		List<WebElement> elms = (new WebDriverWait(driver, secs))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(selector)));
		return elms;
	}

	// Wait for the presence of elements by CSS
	public static WebElement waitForPresencebyCss(WebDriver driver, int secs, String selector) {
		WebElement elm = (new WebDriverWait(driver, secs))
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selector)));
		return elm;
	}

	// Wait for the presence of elements by ID
	public static WebElement waitForPresencebyID(WebDriver driver, int secs, String id) {
		WebElement elm = (new WebDriverWait(driver, secs))
				.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
		return elm;
	}

	// Wait for the presence of elements by Xpath
	public static WebElement waitForPresencebyXpath(WebDriver driver, int secs, String xpath) {
		WebElement elm = (new WebDriverWait(driver, secs))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		return elm;
	}
	// Selector with ID

	public static void selctorWithID(WebDriver driver, String ID) {
		WebElement tab = driver.findElement(By.id(ID));
		tab.click();
	}

	// Scroll until an element
	public static void scrolluntilElement(WebDriver driver, WebElement element) throws InterruptedException {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
	}

	// Selector with xpath

	public static void selctorWithXpath(WebDriver driver, String xpath) throws InterruptedException {
		WebElement tab = driver.findElement(By.xpath(xpath));
		scrolluntilElement(driver, tab);
		Thread.sleep(2000);
		tab.click();
	}

	// Selector with partial link text

	public static void selctorWithPartialLinkText(WebDriver driver, String partialLinkText)
			throws InterruptedException {
		WebElement tab = driver.findElement(By.partialLinkText(partialLinkText));
		scrolluntilElement(driver, tab);
		Thread.sleep(2000);
		tab.click();
	}

	// Selector with link text

	public static void selctorWithLinkText(WebDriver driver, String linkText) throws InterruptedException {
		WebElement tab = driver.findElement(By.linkText(linkText));
		scrolluntilElement(driver, tab);
		Thread.sleep(2000);
		tab.click();
	}

	// get window Handle
	public static String getWindowHandle(WebDriver driver) {
		return driver.getWindowHandle();
	}

	// Get page source

	public static String getPageSource(WebDriver driver, String currentWindowHandler) {
		return driver.switchTo().window(currentWindowHandler).getPageSource();
	}

	// Navigate back
	public static void navigateback(WebDriver driver) {
		driver.navigate().back();
	}

	// get Window handles
	public static Set<String> getWindowHandles(WebDriver driver) {
		return driver.getWindowHandles();
	}

	// Switch to a window and get the url

	public static String swithcWindowGetURL(WebDriver driver, String currentWindowHandler) {
		return driver.switchTo().window(currentWindowHandler).getCurrentUrl();
	}

	// Is radio button selected
	public static boolean isRadioButtonSelected(WebDriver driver, String selector) {

		WebElement elem = findByCSSSelector(driver, selector);
		boolean isOptionSelected = false;
		if (elem != null) {
			isOptionSelected = elem.isSelected();
			System.out.println("Radio button selected" + elem.getAttribute("checked"));
		}
		System.out.println("isOPtion Selected" + isOptionSelected);
		return isOptionSelected;

	}

	// Is element present by
	public static boolean isElementPresent(WebDriver driver, By by) {

		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// Drop down select by text

	public static void dropdownSelectByText(WebElement WE, String visibleText) {

		try {
			Select selObj = new Select(WE);
			selObj.selectByVisibleText(visibleText);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Drop down select by index

	public static void dropdownSelectByIndex(WebElement WE, int index) {
		if (WE.isEnabled() && WE.isDisplayed()) {
			Select selObj = new Select(WE);
			selObj.selectByIndex(index);

		} else
			System.out.println("Dropdown cannot be slected");
	}
	// Drop down select by value

	public static void dropdownSelectByValue(WebElement WE, String value) {
		if (WE.isEnabled() && WE.isDisplayed()) {
			Select selObj = new Select(WE);
			List<WebElement> options = selObj.getOptions();
			for (WebElement option : options) {
				if (option.getText().contains(value)) {

					option.click();
				}
			}

		} else
			System.out.println("Dropdown cannot be slected");

	}

	// TakeSnapshot
	public static String takeSnapshot(WebDriver driver, String DestFilePath) throws IOException {
		System.out.println(driver.getTitle() + DestFilePath);
		String TS = GetTimeStamp();
		TakesScreenshot tss = (TakesScreenshot) driver;
		File srcFileObj = tss.getScreenshotAs(OutputType.FILE);
		DestFilePath = DestFilePath + TS + ".jpg";
		File DestFileObj = new File(DestFilePath);
		FileUtils.copyFile(srcFileObj, DestFileObj);
		return DestFilePath;
	}

	// Get timestamp function

	public static String GetTimeStamp() {
		DateFormat DF = DateFormat.getDateTimeInstance();
		Date dte = new Date();
		String DateValue = DF.format(dte);
		DateValue = DateValue.replaceAll(":", "_");
		DateValue = DateValue.replaceAll(",", "");
		return DateValue;
	}

	// Double click by xpath
	public void doubleClickByXpath(WebDriver driver, String xpath) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(xpath))).doubleClick().build().perform();
	}

	// Clear Text from text boxes

	public void clearTextFromTextBoxes(WebDriver driver) {
		List<WebElement> textboxes = driver.findElements(By.xpath("//input[@type='text']"));
		for (WebElement textbox : textboxes) {
			if (textbox.isEnabled() && textbox.isDisplayed()) {
				textbox.clear();
			}
		}
	}
	// Clear Text from text Area

	public void clearTextFromTextArea(WebDriver driver) {
		List<WebElement> textAreas = driver.findElements(By.xpath("//textarea[@type='text']"));
		for (WebElement textArea : textAreas) {
			if (textArea.isEnabled() && textArea.isDisplayed()) {
				textArea.clear();
			}
		}
	}

	// Convert amount with dollar to number
	public Number convertAmountWithDollar(String amount) throws ParseException {
		Locale locale = Locale.US;
		Number commintmentAmount = NumberFormat.getCurrencyInstance(locale).parse(amount);
		return commintmentAmount;
	}

	// Convert alphabet to its integer position

	public static int findPositionOfAlphabet(char searchItem) {
		return (letterToAlphabetPos(searchItem));
	}

	public static int letterToAlphabetPos(char letter) {
		// TODO Auto-generated method stub
		return Character.toUpperCase(letter) - 64;
	}

	// Select checkBox
	public void selectCheckBox(WebDriver driver, WebElement WE, String option) throws InterruptedException {

		if (option.equalsIgnoreCase("Yes") && !(WE.isSelected())) {

			scrolluntilElement(driver, WE);
			WE.click();
		} else if (option.equalsIgnoreCase("No") && (WE.isSelected())) {
			scrolluntilElement(driver, WE);
			WE.click();
		}
	}

	// Multiple dropdown Select by Text
	public void multipleDropdownSelectByText(WebElement WE, String option) {

		Select oSelect = new Select(WE);

		List<WebElement> elementCount = oSelect.getOptions();
		int Size = elementCount.size();
		int setFlag = 0;
		for (int i = 0; i < Size; i++) {
			String sValue = elementCount.get(i).getText().trim();
			if (sValue.equalsIgnoreCase(option)) {
				setFlag = 1;
				dropdownSelectByText(WE, sValue);
			}
		}
		if (setFlag == 0) {
			System.out.println("Item not present or already selected");
		}

	}

	// Get Selected options in dropdown

	public String getSlectedoptionsDropDown(WebElement WE) {
		Select select = new Select(WE);
		return select.getFirstSelectedOption().getText();
	}

	// Get Number from String

	public int getNumberFromString(WebElement WE, int numberPositionInString) {
		String str = WE.getText().trim();
		System.out.println(str);
		String[] parts = str.split(" ");
		String OS = parts[numberPositionInString];
		int a = Integer.parseInt(OS);
		return a;
	}

	// wait for visibility of an element using css selector
	public static WebElement waitForVisibilityByCSS(WebDriver driver, int secs, String css) {

		WebElement elem = (new WebDriverWait(driver, secs))
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
		return elem;

	}

	// wait for visibility of an element using LinkText selector
	public static WebElement waitForVisibilityByLinkText(WebDriver driver, int secs, String linkText) {

		WebElement elem = (new WebDriverWait(driver, secs))
				.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(linkText)));
		return elem;

	}
}
