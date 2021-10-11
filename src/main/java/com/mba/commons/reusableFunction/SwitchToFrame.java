package com.mba.commons.reusableFunction;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.mba.commons.baseTest.BrowserFactory;
import com.mba.commons.controlers.Initilizer;

public class SwitchToFrame {
		
	RemoteWebDriver driver = Initilizer.getInstance().getDriver();
	
	public  void defaultContent() {
		try {
			driver.switchTo().defaultContent();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void parentFrame() {
		try {
			driver.switchTo().parentFrame();
		}catch(NoSuchFrameException e) {
			e.printStackTrace();
		}
	}

	public void switchToFrameByIndex(By locator, int index) throws InterruptedException {
		List<WebElement> iframeElements = driver.findElements(locator);
		for(int i=0;i<iframeElements.size();i++) {
			try {
				if(i==index) {
					driver.switchTo().frame(i);
					break;
				}
			}catch (NoSuchFrameException e) {
				e.printStackTrace();
			}catch (StaleElementReferenceException e) {
				e.printStackTrace();
			}
		}
	}

	public void switchToFrameByNameorId(String frameNameorId) {
		try {
			driver.switchTo().frame(frameNameorId);
		}catch(NoSuchFrameException e) {
			e.printStackTrace();
		}
	}
	public void switchToFrameByWebElement(WebElement frameElement) {
		try {
			driver.switchTo().frame(frameElement);
		}catch(NoSuchFrameException e) {
			e.printStackTrace();
		}
	}	
}