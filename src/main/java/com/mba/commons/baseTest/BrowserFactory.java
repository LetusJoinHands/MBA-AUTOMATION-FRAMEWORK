package com.mba.commons.baseTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import com.mba.commons.identifiers.InitMethodWindows;

public class BrowserFactory extends InitMethodWindows {
	protected static WebDriver driver;
	static DesiredCapabilities capabilities;
	Map<String, List<Map<String, Map<String, Integer>>>> cars = new HashMap<String, List<Map<String, Map<String, Integer>>>>();

	@SuppressWarnings("deprecation")
	public static WebDriver createDriver(String Browser, String WebsiteURL, int ImplicitlyWait, int MaxPageLoadTime) throws Exception {
		String value = Browser;
		switch (value) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/browserAutomation/Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "chrome_headless":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/browserAutomation/Drivers/chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--disable-gpu");
			driver = new ChromeDriver(chromeOptions);
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/browserAutomation/Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			break;

		case "ie":
		case "internet explorer":
			System.setProperty("webdriver.ie.driver", "src/main/resources/browserAutomation/Drivers/IEDriverServer.exe");
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			break;

		case "opera":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/browserAutomation/Drivers/chromedriver.exe");
			capabilities = DesiredCapabilities.opera();
			ChromeOptions optionsOpera = new ChromeOptions();
			optionsOpera.setBinary("C:/Program Files/Opera/launcher.exe");
			capabilities.setCapability(ChromeOptions.CAPABILITY, optionsOpera);
			driver = new ChromeDriver(capabilities);
			break;

		case "safari":
			driver = new SafariDriver();
			break;

		default:
			throw new NotFoundException("Browser Not Found. Please Provide a Valid Browser");
		}

		if (ImplicitlyWait > 0) {
			implicitlywait(ImplicitlyWait);
		}

		if (MaxPageLoadTime > 0) {
			setMaxPageLoadTime(MaxPageLoadTime);
		}
		driver.get(WebsiteURL);
		if (!Browser.toLowerCase().contains("unit") || !Browser.toLowerCase().contains("ghost")
				|| !Browser.toLowerCase().contains("phantom")) {
			driver.manage().window().maximize();
		}
		return driver;
	}

	public static void implicitlywait(int timeInSeconds) throws Exception {
		driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
	}

	public static void setMaxPageLoadTime(int timeInSeconds) throws Exception {
		driver.manage().timeouts().pageLoadTimeout(timeInSeconds, TimeUnit.SECONDS);
	}
}
