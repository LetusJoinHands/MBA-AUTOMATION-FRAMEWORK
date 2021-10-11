package com.mba.commons.autoInvoking;

import java.io.IOException;
import org.apache.log4j.Logger;
import io.appium.java_client.service.local.AppiumDriverLocalService;

/**
@author Shenilton
@version 2.1
*/

public class OpeningAppiumServer {

	protected static AppiumDriverLocalService service;
	private static Logger logger = Logger.getLogger(InvokingEmulator.class);

	public static void stopAppiumServer() {
		try {
			service = AppiumDriverLocalService.buildDefaultService();
			System.out.println("server close");
			service.stop();
		} catch (Exception e) {
			logger.error("Serrver didnt get stopped");
		}
	}

	public static void startAppiumServer() throws IOException, InterruptedException {
		try {
		System.out.println("starting appium server");
		service = AppiumDriverLocalService.buildDefaultService();
		service.stop();
		service.start();
		System.out.println("appium server started");
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
}