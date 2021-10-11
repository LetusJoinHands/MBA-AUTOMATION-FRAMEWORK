package com.mba.commons.reusableFunction;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mba.commons.baseTest.BrowserFactory;

public class SelectDropDown extends BrowserFactory{

	
	Select select;
	String multivalue;

	public void singleValueSelect(String stringToBeSelect, String selectMethodName,int waitTime, By locator) {
		select=new Select(driver.findElement(locator));
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		if(selectMethodName.equalsIgnoreCase("selectByVisibleText")) {
			try{
				select.selectByVisibleText(stringToBeSelect);
			}catch(ElementNotSelectableException e) {
				e.printStackTrace();
			}
		}else if(selectMethodName.equalsIgnoreCase("selectByValue")) {
			try{
			select.selectByValue(stringToBeSelect);
			}catch(ElementNotSelectableException e) {
				e.printStackTrace();
			}
		}else if(selectMethodName.equalsIgnoreCase("selectByIndex")) {
			try{
				int index = Integer.parseInt(stringToBeSelect);	
				select.selectByIndex(index);
			}catch(ElementNotSelectableException e) {
				e.printStackTrace();
			}
		}
	}	

	public void multiValueSelect(By locator,String[] stringToBeSelect,  String selectMethodName) throws InterruptedException {
		Select select = new Select(driver.findElement(locator)); 
		List<WebElement> list=select.getOptions();
		for(int i=0;i<list.size();i++) {
			String value=list.get(i).getText();
			for(int j=0;j<stringToBeSelect.length;j++) {
				if(value.contains(stringToBeSelect[j])) {
					if(selectMethodName.equalsIgnoreCase("selectByVisibleText")) {
						try{
							select.selectByVisibleText(stringToBeSelect[j]);
						}catch(ElementNotSelectableException e) {
							e.printStackTrace();
						}
					}else if(selectMethodName.equalsIgnoreCase("selectByValue")) {
						try{
							select.selectByValue(stringToBeSelect[j]);
						}catch(ElementNotSelectableException e) {
							e.printStackTrace();
						}
					}else if(selectMethodName.equalsIgnoreCase("selectByIndex")) {
						try{
							int index = Integer.parseInt(stringToBeSelect[j]);	
							select.selectByIndex(index);
						}catch(ElementNotSelectableException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}