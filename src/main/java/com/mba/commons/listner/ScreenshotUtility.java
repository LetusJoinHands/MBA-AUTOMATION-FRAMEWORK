package com.mba.commons.listner;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.mba.commons.controlers.Initilizer;
import com.mba.commons.dataDriven.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

/**
@author Shenilton
@version 2.1
*/

public class ScreenshotUtility implements ITestListener {
	public RemoteWebDriver driver = Initilizer.getInstance().driver;
	public static FileInputStream is = null;
	public static FileOutputStream fos = null;
	public static Xls_Reader data = null;
	public static int count = 1;

	public ScreenshotUtility() {
	}

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	public void onStart(ITestContext tr) {
		System.out.println("I am in onStart method " + tr.getName());
		tr.setAttribute("AppiumDriver", driver);
	}
	
	public void onFinish(ITestContext tr) {
		System.out.println("I am in onFinish method " + tr.getName());
	}

	public void onTestSuccess(ITestResult tr) {
		System.out.println("I am in onTestSuccess method " + getTestMethodName(tr) + " succeed");
	}

	public void onTestFailure(ITestResult tr) {
	}

	public void onTestStart(ITestResult tr) {
		System.out.println("I am in onTestStart method " + getTestMethodName(tr) + " start");
	}

	public void onTestSkipped(ITestResult tr) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {
	}

}