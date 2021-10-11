package com.mba.commons.controlers;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.mba.apiAutomation.webServiceMethods.ReusableFunctionAPI;
import com.mba.commons.autoInvoking.InvokingEmulator;
import com.mba.commons.autoInvoking.OpeningAppiumServer;
import com.mba.commons.dataMapping.DataImp;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.InteractsWithApps;

/**
@author Shenilton
@version 28-07-2020
*/
public class DriverCreation {
	
	RemoteWebDriver driver;
	static String log;
	
	public String retriveLogFromParentClass(String log) {
		return this.log = log;
	}
	
	
	@BeforeSuite()
	public void appiumDriverInitialization() throws InterruptedException, IOException {
		Initilizer.getInstance().driverCreation(this.driver);
	}

	@AfterSuite
	public void closeOpenItems() throws IOException, InterruptedException {
		if (DataImp.getInstance().preReqsite("platformToBeTested").equalsIgnoreCase("iOS")) {
			driver.quit();
			OpeningAppiumServer.stopAppiumServer();
		} else if (DataImp.getInstance().preReqsite("platformToBeTested").equalsIgnoreCase("Android")) {
			this.driver.quit();
			InvokingEmulator.stopAvd();
			OpeningAppiumServer.stopAppiumServer();
		}else if (DataImp.getInstance().preReqsite("platformToBeTested").equalsIgnoreCase("API")) {
			new ReusableFunctionAPI().getURI(DataImp.getInstance().preReqsite("uri"));
		}
	}

	@BeforeClass()
	public void reset() {
		if (DataImp.getInstance().preReqsite("platformToBeTested").equalsIgnoreCase("iOS")|| DataImp.getInstance().preReqsite("platformToBeTested").equalsIgnoreCase("Android")) {
			((InteractsWithApps) Initilizer.getInstance().getDriver()).resetApp();
		}
	}

	@BeforeClass
	public boolean startReport() {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
			Initilizer.getInstance().extent = new ExtentReports(Initilizer.getInstance().mkDirCreation() + "/" + dateFormat.format(new Date()) + ".html", true);
			Initilizer.getInstance().extent.addSystemInfo("Environment", "TEST");
			Initilizer.getInstance().extent.addSystemInfo("Host Name", "LOCALHOST").addSystemInfo("Environment", "TEST").addSystemInfo("User Name", "Shenilton");
			Initilizer.getInstance().extent.loadConfig(new File(System.getProperty("user.dir") + "/extend-config.xml"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@AfterClass
	public boolean endReport() throws InterruptedException {
		try {
			Initilizer.getInstance().extent.flush();
			//Initilizer.getInstance().extent.close();
			//Initilizer.getInstance().getDriver().close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@AfterMethod
	public boolean getResult(ITestResult result) throws IOException {
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				Initilizer.getInstance().logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
				Initilizer.getInstance().logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
				Initilizer.getInstance().logger.log(LogStatus.FAIL,
						Initilizer.getInstance().logger.addBase64ScreenShot(Initilizer.getInstance().addScreenshot()));
			} else if (result.getStatus() == ITestResult.SKIP) {
				Initilizer.getInstance().logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				try {
					Initilizer.getInstance().logger.log(LogStatus.PASS, "Test Case passed is " + result.getName());
					boolean flag = DataImp.getInstance().preReqsite("platformToBeTested").equalsIgnoreCase("API");
					if (flag == false){
					System.out.println(DataImp.getInstance().preReqsite("platformToBeTested"));
					Initilizer.getInstance().logger.log(LogStatus.PASS, Initilizer.getInstance().logger.addBase64ScreenShot(Initilizer.getInstance().addScreenshot()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				Initilizer.getInstance().extent.endTest(Initilizer.getInstance().logger);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public RemoteWebDriver getDriver() {
		return driver;
	}

	public void setDriver(RemoteWebDriver driver) {
		this.driver = driver;
	}
}
