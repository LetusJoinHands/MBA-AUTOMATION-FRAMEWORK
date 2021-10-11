package com.mba.commons.reusableFunction;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.mba.commons.controlers.Initilizer;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class ReusableFunctionBrowser {

	RemoteWebDriver driver;
	private Logger logger = Logger.getLogger(ReusableFunctionBrowser.class);

	public RemoteWebDriver gettingDriverInit() {
		return this.driver = Initilizer.getInstance().getDriver();
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

	public void File_Upload_using_AutoIT(String Path) throws IOException {
		try {
			Runtime.getRuntime().exec(Path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void implicitlyWaitTimeOut(int timeout) {
		try {
			gettingDriverInit().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.getMessage();
			logger.error(e.getMessage());
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

	public WebElement webelementReturn(String action, String element) throws InterruptedException {
		WebElement waitForFormLabel = null;
		switch (action) {
		case "ID":
			waitForFormLabel = gettingDriverInit().findElement(By.id(element));
			break;
		case "NAME":
			waitForFormLabel = gettingDriverInit().findElement(By.xpath(element));
			break;
		case "XPATH":
			waitForFormLabel = gettingDriverInit().findElement(By.xpath(element));
			break;
		case "LINKTXT":
			waitForFormLabel = gettingDriverInit().findElement(By.linkText(element));
			break;
		case "P_LINKTXT":
			waitForFormLabel = gettingDriverInit().findElement(By.partialLinkText(element));
			break;
		case "TAG":
			waitForFormLabel = gettingDriverInit().findElement(By.tagName(element));
			break;
		case "CSS":
			waitForFormLabel = gettingDriverInit().findElement(By.cssSelector(element));
			break;
		case "CLASS":
			waitForFormLabel = gettingDriverInit().findElement(By.className(element));
			break;
		default:
			logger.error("Error to be printed here");
		}
		return waitForFormLabel;

	}

	/** To Wait Until Element to be Clickable */
	public WebElement explicitWaitWebDriverElementToBeClickable(String locatorValue, int waitTime, String locator) {
		WebElement element = null;
		try {
			WebDriverWait clickableWait = new WebDriverWait(gettingDriverInit(), waitTime);
			switch (locatorValue) {
			case "ID":
				element = clickableWait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
				break;
			case "NAME":
				element = clickableWait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
				break;
			case "XPATH":
				element = clickableWait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
				break;
			case "LINKTXT":
				element = clickableWait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));
				break;
			case "P_LINKTXT":
				element = clickableWait
						.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));
				break;
			case "TAG":
				element = clickableWait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));
				break;
			case "CSS":
				element = clickableWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
				break;
			case "CLASS":
				element = clickableWait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));
				break;
			default:
				logger.error("Error to be printed here");
			}
		} catch (ElementNotVisibleException e) {
			e.getMessage();
			logger.error(e.getMessage());
		}
		return element;
	}

	/** To Wait Until Element to be Selectable */
	public boolean explicitWaitWebDriverElementToBeSelected(int waitTime, WebElement elementToBeSelected) {
		Boolean bolean = null;
		try {
			WebDriverWait selectableWait = new WebDriverWait(gettingDriverInit(), waitTime);
			bolean = selectableWait.until(ExpectedConditions.elementToBeSelected(elementToBeSelected));
		} catch (ElementNotVisibleException e) {
			e.getMessage();
			logger.error(e.getMessage());
		}
		return bolean;
	}

	/** To Wait Until Element to be Visible */
	public WebElement explicitWaitWebDriverVisibilityOfElement(String locatorValue, int waitTime, String locator) {
		WebElement element = null;
		try {
			WebDriverWait elementToBeVisible = new WebDriverWait(gettingDriverInit(), waitTime);
			switch (locatorValue) {
			case "ID":
				element = elementToBeVisible.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
				break;
			case "NAME":
				element = elementToBeVisible
						.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
				break;
			case "XPATH":
				element = elementToBeVisible
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
				break;
			case "LINKTXT":
				element = elementToBeVisible
						.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
				break;
			case "P_LINKTXT":
				element = elementToBeVisible
						.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
				break;
			case "TAG":
				element = elementToBeVisible
						.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
				break;
			case "CSS":
				element = elementToBeVisible
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
				break;
			case "CLASS":
				element = elementToBeVisible
						.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
				break;
			default:
				logger.error("Error to be printed here");
			}
		} catch (ElementNotVisibleException e) {
			e.getMessage();
			logger.error(e.getMessage());
		}
		return element;

	}

	/** To Wait for the Alert */
	public void explicitWaitWebDriverForAlert(int waitTime) {
		try {
			WebDriverWait waitForAlert = new WebDriverWait(gettingDriverInit(), waitTime);
			waitForAlert.until(ExpectedConditions.alertIsPresent());
		} catch (ElementNotVisibleException e) {
			e.getMessage();
			logger.error(e.getMessage());
		}
	}

	/** To Wait Until Element Located */
	public boolean explicitWaitWebDriverTextToBePresentInElementLocated(String locatorValue, int waitTime, String text,
			String locator) {
		boolean element = false;
		try {
			WebDriverWait textToBePresent = new WebDriverWait(gettingDriverInit(), waitTime);
			switch (locatorValue) {
			case "ID":
				element = textToBePresent
						.until(ExpectedConditions.textToBePresentInElementLocated(By.id(locatorValue), text));
				break;
			case "NAME":
				element = textToBePresent
						.until(ExpectedConditions.textToBePresentInElementLocated(By.name(locatorValue), text));
				break;
			case "XPATH":
				element = textToBePresent
						.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(locatorValue), text));
				break;
			case "LINKTXT":
				element = textToBePresent
						.until(ExpectedConditions.textToBePresentInElementLocated(By.linkText(locatorValue), text));
				break;
			case "P_LINKTXT":
				element = textToBePresent.until(
						ExpectedConditions.textToBePresentInElementLocated(By.partialLinkText(locatorValue), text));
				break;
			case "TAG":
				element = textToBePresent
						.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName(locatorValue), text));
				break;
			case "CSS":
				element = textToBePresent
						.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(locatorValue), text));
				break;
			case "CLASS":
				element = textToBePresent
						.until(ExpectedConditions.textToBePresentInElementLocated(By.className(locatorValue), text));
				break;
			default:
				logger.error("Error to be printed here");
			}
		} catch (ElementNotVisibleException e) {
			e.getMessage();
			logger.error(e.getMessage());
		}
		return element;

	}

	/** To Wait Until Title is */
	public void explicitWaitWebDriverTitleIs(int waitTime, String title) {
		try {
			WebDriverWait titleIs = new WebDriverWait(gettingDriverInit(), waitTime);
			titleIs.until(ExpectedConditions.titleIs(title));
		} catch (ElementNotVisibleException e) {
			e.getMessage();
			logger.error(e.getMessage());
		}
	}

	/** To Wait Until Element to be Visible */
	public void explicitWaitWebDriverVisibilityOf(WebElement element, int waitTime) {
		try {
			WebDriverWait elementToBeVisible = new WebDriverWait(gettingDriverInit(), waitTime);
			elementToBeVisible.until(ExpectedConditions.visibilityOf(element));
		} catch (ElementNotVisibleException e) {
			e.getMessage();
			logger.error(e.getMessage());
		}
	}

	/** To Wait Until Elements to be Visible Located By */
	public List<WebElement> explicitWaitWebDriverVisibilityOfElementsLocatedBy(String locatorValue, int waitTime,
			String locator) {
		List<WebElement> element = null;
		try {
			WebDriverWait elementsToBeVisible = new WebDriverWait(gettingDriverInit(), waitTime);
			switch (locatorValue) {
			case "ID":
				element = elementsToBeVisible
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locatorValue)));
				break;
			case "NAME":
				element = elementsToBeVisible
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(locatorValue)));
				break;
			case "XPATH":
				element = elementsToBeVisible
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locatorValue)));
				break;
			case "LINKTXT":
				element = elementsToBeVisible
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(locatorValue)));
				break;
			case "P_LINKTXT":
				element = elementsToBeVisible
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.partialLinkText(locatorValue)));
				break;
			case "TAG":
				element = elementsToBeVisible
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName(locatorValue)));
				break;
			case "CSS":
				element = elementsToBeVisible
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(locatorValue)));
				break;
			case "CLASS":
				element = elementsToBeVisible
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(locatorValue)));
				break;
			default:
				logger.error("Error to be printed here");
			}
		} catch (ElementNotVisibleException e) {
			e.getMessage();
			logger.error(e.getMessage());
		}
		return element;
	}

	/** To Wait Until String to be Sent by SendKeys */
	public WebElement explicitWaitWebDriverStringToBeSent(String locatorValue, int waitTime, String locator) {
		WebElement element = null;
		try {
			WebDriverWait elementToBeVisible = new WebDriverWait(gettingDriverInit(), waitTime);
			switch (locatorValue) {
			case "ID":
				element = elementToBeVisible
						.until(ExpectedConditions.visibilityOf(gettingDriverInit().findElement(By.id(locatorValue))));
				break;
			case "NAME":
				element = elementToBeVisible
						.until(ExpectedConditions.visibilityOf(gettingDriverInit().findElement(By.name(locatorValue))));
				break;
			case "XPATH":
				int size = driver.findElements(By.xpath(locatorValue)).size();
				System.out.println(size);
				// element =
				// elementToBeVisible.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locatorValue))));
				break;
			case "LINKTXT":
				element = elementToBeVisible
						.until(ExpectedConditions.visibilityOf(gettingDriverInit().findElement(By.linkText(locatorValue))));
				break;
			case "P_LINKTXT":
				element = elementToBeVisible
						.until(ExpectedConditions.visibilityOf(gettingDriverInit().findElement(By.partialLinkText(locatorValue))));
				break;
			case "TAG":
				element = elementToBeVisible
						.until(ExpectedConditions.visibilityOf(gettingDriverInit().findElement(By.tagName(locatorValue))));
				break;
			case "CSS":
				element = elementToBeVisible
						.until(ExpectedConditions.visibilityOf(gettingDriverInit().findElement(By.cssSelector(locatorValue))));
				break;
			case "CLASS":
				element = elementToBeVisible
						.until(ExpectedConditions.visibilityOf(gettingDriverInit().findElement(By.className(locatorValue))));
				break;
			default:
				logger.error("Error to be printed here");
			}
		} catch (ElementNotVisibleException e) {
			e.getMessage();
			logger.error(e.getMessage());
		}
		return element;
	}

	public void wait(int time) {
		gettingDriverInit().manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public void switchToFrameByNameorId(String frameNameorId) {
		try {
			gettingDriverInit().switchTo().frame(frameNameorId);
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}
}
