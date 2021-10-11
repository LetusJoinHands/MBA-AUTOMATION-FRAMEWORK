package com.mba.commons.reusableFunction;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mba.commons.baseTest.DriversInitilization;
import com.mba.commons.controlers.Initilizer;
import com.mba.commons.dataMapping.DataImp;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ResuableFunctionMobile extends DriversInitilization {

	RemoteWebDriver driver;
	private Logger logger = Logger.getLogger(ResuableFunctionMobile.class);

	public RemoteWebDriver gettingDriverInit() {
		return this.driver = Initilizer.getInstance().getDriver();
	}

	public void implicitlyWaitTimeOut(int timeout) {
		try {
			gettingDriverInit().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.getMessage();
			logger.error(e.getMessage());
		}
	}

	public void clcikByID(String value) {
		try {
			gettingDriverInit().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			gettingDriverInit().findElement(By.id(value)).click();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	public boolean isExist(String Id) {
		try {
			gettingDriverInit().findElement(By.id(Id)).click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isExistSend(String Id, String keysToSend) {
		try {
			gettingDriverInit().findElement(By.id(Id)).sendKeys(keysToSend);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public void checkForTheElementIsPresentInDOM(String className) {
		List<MobileElement> inputs;
		Long startTime = System.currentTimeMillis();
		do {
			inputs = (List<MobileElement>) ((AppiumDriver<MobileElement>) gettingDriverInit())
					.findElements(MobileBy.className(className));
			if (!inputs.isEmpty()) {
				System.out.println("FOUND!");
				break;
			} else {
				System.out.println("NoT Found..!!");
			}
		} while ((System.currentTimeMillis() - startTime) / 1000 < 100);
	}

	public void clearForWaitByID(String identifier, String value) throws InterruptedException {
		if (identifier.equals("id")) {
			Thread.sleep(4000);
			gettingDriverInit().findElement(By.id(value)).clear();
		}
		if (identifier.equals("xpath")) {
			Thread.sleep(4000);
			gettingDriverInit().findElement(By.xpath(value)).clear();
		}
	}

	public void clcikForWaitByID(String value) throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(gettingDriverInit(), 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(value))).click();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	public void clcikForWaitByXpath(String value) throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(gettingDriverInit(), 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value))).click();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	public Set<String> findDuplicates(List<String> listContainingDuplicates) {
		final Set<String> setToReturn = new HashSet<String>();
		final Set<String> set1 = new HashSet<String>();
		for (String yourInt : listContainingDuplicates) {
			if (!set1.add(yourInt)) {
				setToReturn.add(yourInt);
			}
		}
		return setToReturn;
	}

	public boolean sortOrder(List<String> value) {
		String previous = "";
		value = new ArrayList<String>();
		for (final String current : value) {
			value.add(current);
			if (current.compareTo(previous) < 0)
				return false;
			previous = current;
		}
		return true;
	}

	public boolean containsDuplication(List<String> list) {
		Set<String> set = new HashSet<String>(list);
		if (set.size() < list.size()) {
			return true;
		} else {
			return false;
		}
	}

	public void sendByForWaitByID(String value, String value2) throws InterruptedException {
		Thread.sleep(4000);
		gettingDriverInit().findElement(By.id(value)).sendKeys(value2);
	}

	public void sendByWaitByString(String locator, String value, String valueToBeSent) {
		WebDriverWait wait = new WebDriverWait(gettingDriverInit(), 20);
		if (locator.equalsIgnoreCase("ID")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(value))).sendKeys(valueToBeSent);
		} else if (locator.equalsIgnoreCase("xpath")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value))).sendKeys(valueToBeSent);
		}
	}

	public void clcikByXpath(String value) {
		try {
			gettingDriverInit().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			gettingDriverInit().findElement(By.xpath(value)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* ==================Newly added Function====================== */
	public String getTextFromElementByWait(String identifier, String locator, int waitTime) {
		String returnValue = null;
		if (identifier.equalsIgnoreCase("id")) {
			WebDriverWait wait = new WebDriverWait(gettingDriverInit(), waitTime);
			MobileElement element = (MobileElement) wait
					.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
			returnValue = element.getText();
		} else if (identifier.equalsIgnoreCase("xpath")) {
			WebDriverWait wait = new WebDriverWait(gettingDriverInit(), waitTime);
			MobileElement element = (MobileElement) wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
			returnValue = element.getText();
		} else if (identifier.equalsIgnoreCase("idAttribute")) {
			WebDriverWait wait = new WebDriverWait(gettingDriverInit(), waitTime);
			MobileElement element = (MobileElement) wait
					.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
			returnValue = element.getAttribute("name").trim();
		} else if (identifier.equalsIgnoreCase("xpathAttribute")) {
			WebDriverWait wait = new WebDriverWait(gettingDriverInit(), waitTime);
			MobileElement element = (MobileElement) wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
			returnValue = element.getAttribute("name").trim();
		}

		return returnValue;
	}

	public boolean swipeToDirection_Android(MobileElement el, String direction) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) gettingDriverInit();
			HashMap<String, String> swipeObject = new HashMap<String, String>();
			if (direction.equals("d")) {
				swipeObject.put("direction", "down");
			} else if (direction.equals("u")) {
				swipeObject.put("direction", "up");
			} else if (direction.equals("l")) {
				swipeObject.put("direction", "left");
			} else if (direction.equals("r")) {
				swipeObject.put("direction", "right");
			}
			swipeObject.put("element", el.getId());
			js.executeScript("mobile:swipe", swipeObject);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public boolean isDisplayedInDom(String identifier, String element) {
		boolean value = false;
		try {
			if (identifier.equalsIgnoreCase("id")) {
				WebDriverWait wait = new WebDriverWait(gettingDriverInit(), 30);
				MobileElement elements = (MobileElement) wait
						.until(ExpectedConditions.presenceOfElementLocated(By.id(element)));
				value = elements.isDisplayed();
			}
			if (identifier.equalsIgnoreCase("xpath")) {
				WebDriverWait wait = new WebDriverWait(gettingDriverInit(), 30);
				MobileElement elements = (MobileElement) wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
				value = elements.isDisplayed();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return value;
	}

	public boolean isEnabledInDom(String identifier, String element) {
		boolean value = false;
		try {
			if (identifier.equalsIgnoreCase("id")) {
				WebDriverWait wait = new WebDriverWait(gettingDriverInit(), 30);
				MobileElement elements = (MobileElement) wait
						.until(ExpectedConditions.presenceOfElementLocated(By.id(element)));
				value = elements.isEnabled();
			}
			if (identifier.equalsIgnoreCase("xpath")) {
				WebDriverWait wait = new WebDriverWait(gettingDriverInit(), 30);
				MobileElement elements = (MobileElement) wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
				value = elements.isEnabled();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return value;
	}

	public void waitForElement(AppiumDriver<MobileElement> driver, long time, String element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(element))).click();
	}

	public MobileElement returnMobileElement(String element) {
		WebDriverWait wait = new WebDriverWait(gettingDriverInit(), 30);
		MobileElement elements = (MobileElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(By.id(element)));
		return elements;
	}

	public MobileElement returnMobileElementXPATH(String element) {
		WebDriverWait wait = new WebDriverWait(gettingDriverInit(), 30);
		MobileElement elements = (MobileElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
		return elements;
	}

	public void longPress(MobileElement element) {
		TouchActions action = new TouchActions(gettingDriverInit());
		action.longPress(element);
		action.perform();
	}

	@SuppressWarnings("unchecked")
	public void hiddingKeyBoard() {
		try {
			((AppiumDriver<MobileElement>) gettingDriverInit()).hideKeyboard();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean contains(int[] arr, int item) {
		for (int n : arr) {
			if (item == n) {
				return true;
			}
		}
		return false;
	}

	public boolean waitElementIsDisplayed(String by) {
		boolean returnValue = false;
		try {
			WebDriverWait wait = new WebDriverWait(gettingDriverInit(), 70);
			returnValue = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(by))).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	public Map<String, List<String>> splitFunction(List<String> value) {
		Map<String, List<String>> mapping = new HashMap<String, List<String>>();
		List<String> addingRefNumber = new ArrayList<String>();
		List<String> addingBillersName = new ArrayList<String>();
		String[] seperator = null;
		for (String firstValue : value) {
			seperator = firstValue.split("\\|");
			addingRefNumber.add(seperator[0].trim().toString());
			addingBillersName.add(seperator[1].trim().toString());
		}
		mapping.put("RefNumber", addingRefNumber);
		mapping.put("BillersName", addingBillersName);
		return mapping;
	}

	public String todayDate() {
		String pattern = "dd MMM yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		return date;
	}

	public String tommarowDate() {
		final SimpleDateFormat format = new SimpleDateFormat("EEEE, dd MMM yyyy");
		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		return format.format(calendar.getTime());
	}

	public void clcikForWaitByXpathMobileElementsFromList(MobileElement ele) throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(gettingDriverInit(), 60);
			wait.until(ExpectedConditions.visibilityOf(ele)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listOfElements(String action, String id, int index, String keysToSend) throws InterruptedException {
		WebDriverWait waitForFormLabel = new WebDriverWait(gettingDriverInit(), 30);
		@SuppressWarnings("unchecked")
		List<MobileElement> myIput = ((AppiumDriver<MobileElement>) gettingDriverInit()).findElements(By.id(id));
		if (action.equals("snd") && action.equals("ID")) {
			waitForFormLabel.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(id)));
			myIput.get(index).sendKeys(keysToSend);
		} else if (action.equals("clk") && action.equals("ID")) {
			waitForFormLabel.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(id)));
			myIput.get(index).sendKeys(keysToSend);
		} else if (action.equals("get") && action.equals("ID")) {
			waitForFormLabel.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(id)));
			myIput.get(index).sendKeys(keysToSend);
		}
	}

	public void listOfElementsXPATH(String action, String id, int index, String keysToSend)
			throws InterruptedException {
		WebDriverWait waitForFormLabel = new WebDriverWait(gettingDriverInit(), 30);
		@SuppressWarnings("unchecked")
		List<MobileElement> myIput = ((AppiumDriver<MobileElement>) gettingDriverInit()).findElements(By.xpath(id));
		if (action.equals("snd") && action.equals("XPATH")) {
			waitForFormLabel.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(id)));
			myIput.get(index).sendKeys(keysToSend);
		} else if (action.equals("clk") && action.equals("XPATH")) {
			waitForFormLabel.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(id)));
			myIput.get(index).sendKeys(keysToSend);
		} else if (action.equals("get") && action.equals("XPATH")) {
			waitForFormLabel.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(id)));
			myIput.get(index).sendKeys(keysToSend);
		}
	}

	public void scroll() throws InterruptedException {
		Thread.sleep(10000);
		@SuppressWarnings("rawtypes")
		TouchAction action = new TouchAction((PerformsTouchActions) gettingDriverInit());
		Dimension dimensions = gettingDriverInit().manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.5;
		int h1 = screenHeightStart.intValue();
		Double screenHeightEnd = dimensions.getHeight() * 0.2;
		int h2 = screenHeightEnd.intValue();
		action.press(PointOption.point(0, h1)).moveTo(PointOption.point(0, -h2)).release().perform();
	}

	public void ScrollIOS(String input, String object, String elementToScrolled) {
		if (input.equalsIgnoreCase("xpath")) {
			@SuppressWarnings("unchecked")
			MobileElement drawee = ((AppiumDriver<MobileElement>) gettingDriverInit()).findElement(By.xpath(object));
			Actions act = new Actions(gettingDriverInit());
			act.clickAndHold(drawee);
			JavascriptExecutor js = (JavascriptExecutor) gettingDriverInit();
			HashMap scrollObject = new HashMap<>();
			scrollObject.put("predicateString", "value == '" + elementToScrolled + "'");
			js.executeScript("mobile: scroll", scrollObject);
			act.release();
		}
		if (input.equalsIgnoreCase("id")) {
			MobileElement drawee = ((AppiumDriver<MobileElement>) gettingDriverInit()).findElement(By.id(object));
			Actions act = new Actions(gettingDriverInit());
			act.clickAndHold(drawee);
			JavascriptExecutor js = (JavascriptExecutor) gettingDriverInit();
			HashMap scrollObject = new HashMap<>();
			scrollObject.put("predicateString", "value == '" + elementToScrolled + "'");
			js.executeScript("mobile: scroll", scrollObject);
			act.release();
		}
	}

	public void ScrollIOSclick(String input, String object, String elementToScrolled,
			String elementToBeClickedAfterScrolling) {
		if (input.equalsIgnoreCase("xpath")) {
			MobileElement drawee = ((AppiumDriver<MobileElement>) gettingDriverInit()).findElement(By.xpath(object));
			Actions act = new Actions(gettingDriverInit());
			act.clickAndHold(drawee);
			JavascriptExecutor js = (JavascriptExecutor) gettingDriverInit();
			HashMap scrollObject = new HashMap<>();
			scrollObject.put("predicateString", "value == '" + elementToScrolled + "'");
			js.executeScript("mobile: scroll", scrollObject);
			act.release();
		}
		if (input.equalsIgnoreCase("id")) {
			MobileElement drawee = ((AppiumDriver<MobileElement>) gettingDriverInit()).findElement(By.id(object));
			Actions act = new Actions(driver);
			act.clickAndHold(drawee);
			JavascriptExecutor js = (JavascriptExecutor) gettingDriverInit();
			HashMap scrollObject = new HashMap<>();
			scrollObject.put("predicateString", "value == '" + elementToScrolled + "'");
			js.executeScript("mobile: scroll", scrollObject);
			act.release();
		}
	}

	public void clickOnAlert() {
		try {
			gettingDriverInit().switchTo().alert().accept();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void hideKeyboard() throws InterruptedException {
		try {
			((AppiumDriver<MobileElement>) gettingDriverInit()).hideKeyboard();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void switchTo(String string) {
		// TODO Auto-generated method stub
		if (string.equalsIgnoreCase("WEB_VIEW")) {
			switchToWebView(gettingDriverInit());
		} else if (string.equalsIgnoreCase("NATIVE_VIEW")) {
			switchToNativeView(gettingDriverInit());
		}

	}

	public void switchToWebView(RemoteWebDriver driver) {
		Set<String> allContext = ((AppiumDriver<MobileElement>) driver).getContextHandles();
		for (String context : allContext) {
			if (context.contains("WEBVIEW"))
				((AppiumDriver<MobileElement>) driver).context(context);
		}
	}

	public void switchToNativeView(RemoteWebDriver driver) {
		Set<String> contextNames = ((AppiumDriver<MobileElement>) driver).getContextHandles();
		for (String contextName : contextNames) {
			if (contextName.contains("NATIVE"))
				((AppiumDriver<MobileElement>) driver).context(contextName);
		}
	}

	public void waitforload() throws InterruptedException {
		System.out.println("Waiting");
		Thread.sleep(10000);
	}

	public void category_open_close(String text) throws InterruptedException {
		System.out.println("category click");
		String element = "/android.widget.TextView [contains(@text, 'replace')]";
		element.replace("replace", text);
		gettingDriverInit().findElement(By.xpath(element)).click();
		Thread.sleep(10000);
	}

	public void scrolliOS(String text, String direction) {
		JavascriptExecutor js = (JavascriptExecutor) gettingDriverInit();
		HashMap<Object, Object> scrollObject = new HashMap<>();
		scrollObject.put("predicateString", "value == " + text + "");
		scrollObject.put("direction", direction);
		js.executeScript("mobile: scroll", scrollObject);
	}

	public void scrollElementiOS(MobileElement element, String direction) {
		String el = element.getId();
		JavascriptExecutor js = (JavascriptExecutor) gettingDriverInit();
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", el);
		scrollObject.put("direction", direction);
		js.executeScript("mobile: scroll", scrollObject);
		try {
			gettingDriverInit().findElement(By.id(el)).click();
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void clearForWaitXPATH(String value) throws InterruptedException {
		Thread.sleep(4000);
		gettingDriverInit().findElement(By.xpath(value)).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	public void sleeping(String inputTime) {
		long value = (long) (Integer.valueOf(inputTime));
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	public void errorMessageTrack(String errorMessage) {
		ArrayList<String> list = new ArrayList<>();
		list.add("Generic error");
		list.add(
				"Your User ID or password may be incorrect. Please try again. If you are still unable to log in, try resetting your password or call CitiPhone Banking for assistance.");
		list.add("Unable to Load");
		list.add("Sorry, we are unable to process your request right now. Please try again later.");
		try {
			for (String individualErrorMessage : list) {
				if (individualErrorMessage.equals(errorMessage)) {
					Initilizer.getInstance().logger.log(LogStatus.WARNING,
							"Due to this error " + errorMessage + " Application is quit");
					driver.quit();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void File_upload_using_Robot(String pathname) {
		StringSelection stringSelection = new StringSelection(pathname);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(5000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	public void Passdata_using_Robot(String pathname) {
		StringSelection stringSelection = new StringSelection(pathname);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(150);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public void Passdata_Date_From_using_Robot(String pathname) {
		StringSelection stringSelection = new StringSelection(pathname);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);

		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		robot.delay(2000);

		for (int i = 1; i <= 27; i++) {
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
		}
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(150);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	public void Passdata_Date_To_using_Robot(String pathname) {

		StringSelection stringSelection = new StringSelection(pathname);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);

		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		robot.delay(2000);

		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(150);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	public void Expandall_using_Robot(String pathname) {

		StringSelection stringSelection = new StringSelection(pathname);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);

		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		robot.delay(2000);
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(150);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	public void Press_TAB() {
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

	}

	public void Press_TAB_Enter() {
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(150);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	/* Auto it script not working */
	public void File_Upload_using_AutoIT(String Path) throws IOException {
		try {
			Runtime.getRuntime().exec(Path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void tapByCoordinates(int x, int y) throws InterruptedException {
		if (DataImp.getInstance().preReqsite("platformToBeTested").equalsIgnoreCase("Android")) {
			Thread.sleep(5000);
			new TouchAction((AndroidDriver<MobileElement>) gettingDriverInit()).tap(PointOption.point(x, y))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(250))).perform();
		}
		if (DataImp.getInstance().preReqsite("platformToBeTested").equalsIgnoreCase("iOS")) {
			Thread.sleep(5000);
			new TouchAction((IOSDriver<MobileElement>) gettingDriverInit()).tap(PointOption.point(x, y))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(250))).perform();
		}
	}

}