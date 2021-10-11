package com.mba.commons.reusableFunction;

import org.openqa.selenium.NoAlertPresentException;

import com.mba.commons.baseTest.BrowserFactory;

public class WebPopUp extends BrowserFactory{

	public static void popUpAccept() {
		try{
			driver.switchTo().alert().accept();
		}catch(NoAlertPresentException e) {
			e.printStackTrace();
		}
	}
	
	public static void popUpDismiss() {
		try{
			driver.switchTo().alert().dismiss();
		}catch(NoAlertPresentException e) {
			e.printStackTrace();
		}
	}
	
	public static String popUpGetText() {
		String popUpText = null;
		try{
			popUpText=driver.switchTo().alert().getText();
		}catch(NoAlertPresentException e) {
			e.printStackTrace();
		}
		return popUpText;
	}
	
	public static void popUpSendkeys(String keysToSend) {
		try{
			driver.switchTo().alert().sendKeys(keysToSend);
		}catch(NoAlertPresentException e) {
			e.printStackTrace();
		}
	}
}
