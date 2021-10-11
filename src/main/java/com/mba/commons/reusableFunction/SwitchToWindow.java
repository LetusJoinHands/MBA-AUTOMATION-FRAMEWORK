package com.mba.commons.reusableFunction;

import java.util.Set;

import org.openqa.selenium.NoSuchWindowException;

import com.mba.commons.baseTest.BrowserFactory;

public class SwitchToWindow extends BrowserFactory {
	public String getMainWindowHandle() {
		return driver.getWindowHandle();
	}

	public static void childWindowHandle(String childWindowString) {
		try {
			Set<String> alWindow = driver.getWindowHandles();

			for (String ChildWindow : alWindow) {
				if (childWindowString.contains(driver.getTitle())) {
					try {
						driver.switchTo().window(ChildWindow);
						// perform the action on child window
					} catch (NoSuchWindowException e) {
						e.printStackTrace();
					}
				} else if (childWindowString.contains(driver.getCurrentUrl())) {
					try {
						driver.switchTo().window(ChildWindow);
						// perform the action on child window
					} catch (NoSuchWindowException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void parentWindowHandle() {
		try {
			driver.switchTo().window(getMainWindowHandle());
		} catch (NoSuchWindowException e) {
			e.printStackTrace();
		}
	}
}