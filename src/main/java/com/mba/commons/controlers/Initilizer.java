package com.mba.commons.controlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import com.mba.commons.baseTest.BrowserFactory;
import com.mba.commons.baseTest.DriversInitilization;
import com.mba.commons.dataMapping.DataImp;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.InteractsWithApps;

/**
@author Shenilton
@version 2.1
*/

public class Initilizer {

	DriversInitilization init = null;
	public ExtentReports extent;
	public ITestResult result;
	public ExtentTest logger;
	public RemoteWebDriver driver;

	private static Initilizer obj;

	protected Initilizer() {
	}

	public static Initilizer getInstance() {
		if (obj == null)
			obj = new Initilizer();
		return obj;
	}

	public void driverCreation(RemoteWebDriver driver) {
		this.init = new DriversInitilization();
		if (DataImp.getInstance().preReqsite("platformToBeTested").equalsIgnoreCase("iOS")) {
			try {
				driver = init.initilizeiOS(DataImp.getInstance().preReqsite("appName"));
				((InteractsWithApps)driver).resetApp();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		if (DataImp.getInstance().preReqsite("platformToBeTested").equalsIgnoreCase("Android")) {
			try {
				driver = init.initilizeAndroid("GLMOB_SG");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else if (DataImp.getInstance().preReqsite("platformToBeTested").equalsIgnoreCase("web")) {
			try {
				driver = (RemoteWebDriver) BrowserFactory.createDriver(DataImp.getInstance().preReqsite("BrowserTested"),DataImp.getInstance().preReqsite("WebsiteURL"), 70, 100);	    
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		setDriver(driver);
	}

	public String addScreenshot() {
		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String encodedBase64 = null;
		FileInputStream fileInputStreamReader = null;
		try {
			fileInputStreamReader = new FileInputStream(scrFile);
			byte[] bytes = new byte[(int) scrFile.length()];
			fileInputStreamReader.read(bytes);
			encodedBase64 = new String(Base64.encodeBase64(bytes));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "data:image/png;base64," + encodedBase64;
	}

	public File mkDirCreation() {
		String fileName = new SimpleDateFormat("yyyyMMddHH").format(new Date());
		String directaryName = System.getProperty("user.dir") + "/" + "ConsolidatedReport" + "/" + fileName;
		File theDir = new File(directaryName);
		if (!theDir.exists()) {
			boolean result = false;
			try {
				theDir.mkdir();
				result = true;
			} catch (SecurityException se) {
			}
			if (result) {
				System.out.println("DIR created");
			}
		}
		return theDir;
	}

	public RemoteWebDriver getDriver() {
		return driver;
	}

	public void setDriver(RemoteWebDriver driver) {
		this.driver = driver;
	}
	
	public static void main(String[] args) {
		System.out.println(DataImp.getInstance().preReqsite("platformToBeTested"));
	}
}