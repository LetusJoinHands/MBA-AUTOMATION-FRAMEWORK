package com.mba.web;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.mba.commons.controlers.CommonCallFunction;
import com.mba.commons.controlers.DriverCreation;
import com.mba.commons.controlers.Initilizer;
import com.mba.commons.dataMapping.ObjectGroups;
import com.mba.commons.identifiers.InitMethodsBrowsers;
import com.mba.commons.listner.ScreenshotUtility;

@Listeners(ScreenshotUtility.class)
public class TestWebAutomation extends DriverCreation   {
	
	private static Logger logger = Logger.getLogger(TestWebAutomation.class);
	
	@DataProvider(name = "UI_SAMPLE")
	public Object[][] dataProvider() throws IOException {
		return CommonCallFunction.dataRequiredToTest("UI_SAMPLE", InitMethodsBrowsers.KEYWORDS);
	}
	
	@Test(dataProvider = "UI_SAMPLE", enabled = true)
	public void gettingDashboard(String FEATURE, String TC_ID, String TC_DESCRIPTION, String OBJECT_KEY, String ACTION_KEY, String OBJECT_GROUP, String CONTROL_FLAG, String COUNTRY_ID, String ASSERTION_TYPE, String ASSERTION_DATA, String STATUS) throws Exception {
		Initilizer.getInstance().logger = Initilizer.getInstance().extent.startTest(FEATURE + "_ " + TC_ID + "_ " + TC_DESCRIPTION);
		try {
			ObjectGroups.getInstance().actionOnElementsWeb(OBJECT_GROUP, OBJECT_KEY, ACTION_KEY, ASSERTION_TYPE, ASSERTION_DATA,InitMethodsBrowsers.OBJECT_GROUP, InitMethodsBrowsers.OBJECT_REPROSITORY, InitMethodsBrowsers.INPUT_DATA, CONTROL_FLAG,STATUS);
		} catch (InterruptedException e) {
			e.printStackTrace();
			 logger.error("ERROR CAUGHT IN TEST CLASSES");
		}
	}
}