package com.mba.commons.baseTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.mba.commons.dataMapping.DataImp;
import com.mba.commons.identifiers.InitMethodWindows;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

/**
@author Shenilton
@version 2.1
*/
public class DriversInitilization {

	protected DesiredCapabilities caps = null;
	public RemoteWebDriver driver;
	public String pathName = "";

	public RemoteWebDriver initilizeAndroid(String appName) throws MalformedURLException {
		File file = new File(InitMethodWindows.ProjectWorkingDirectory + "/ListOfAPK's/" + appName + ".apk");
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", DataImp.getInstance().preReqsite("deviceName"));
		caps.setCapability("platformVersion", DataImp.getInstance().preReqsite("platformVersion"));
		caps.setCapability("platformName", DataImp.getInstance().preReqsite("platformName"));
		caps.setCapability("skipUnlock", "true");
		caps.setCapability("app", file.getAbsolutePath());
		caps.setCapability("appPackage", DataImp.getInstance().preReqsite("appPackage "));
		caps.setCapability("appActivity", DataImp.getInstance().preReqsite("appActivity "));
		caps.setCapability("androidInstallTimeout", 150000);
		caps.setCapability("autoGrantPermissions", true);
		return driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
	}

	public RemoteWebDriver initilizeiOS(String appName) throws MalformedURLException {
		File file = new File(InitMethodWindows.ProjectWorkingDirectory + "/ListOfAPK's/" + appName + ".app");
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", DataImp.getInstance().preReqsite("deviceNameIOS"));
		caps.setCapability("platformName", DataImp.getInstance().preReqsite("iOS"));
		caps.setCapability("platformVersion", DataImp.getInstance().preReqsite("platformVersionIOS"));
		caps.setCapability("bundleId", DataImp.getInstance().preReqsite("bundleID"));
		caps.setCapability("app", file.getAbsolutePath().toString());
		System.out.println("Shenilton 1 " + file.getAbsolutePath().toString());
		caps.setCapability("udid", DataImp.getInstance().preReqsite("udidIOS"));
		caps.setCapability("newCommandTimeout", 60 * 50);
		System.out.println(caps.toString());
		caps.setCapability("autoAcceptAlerts", true);
		System.out.println(caps.toString());
		return driver = new IOSDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
	}
}