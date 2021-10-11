package com.mba.mobile;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.mba.commons.controlers.CommonCallFunction;
import com.mba.commons.controlers.Initilizer;
import com.mba.commons.dataMapping.ObjectGroups;
import com.mba.commons.identifiers.InitMethodiOS;
import com.mba.commons.listner.ScreenshotUtility;

@Listeners(ScreenshotUtility.class)
public class TestMobile {

	@DataProvider(name = "mobile_test")
	public Object[][] dataProvider() throws IOException {
		return CommonCallFunction.dataRequiredToTest("mobile_test", "");
	}

	@Test(dataProvider = "mobile_test")
	public void gettingDashboard(String FEATURE, String TC_ID, String TC_DESCRIPTION, String OBJECT_KEY,
			String ACTION_KEY, String OBJECT_GROUP, String CONTROL_FLAG, String COUNTRY_ID, String ASSERTION_TYPE,
			String ASSERTION_DATA, String STATUS) throws Exception {
		Initilizer.getInstance().logger = Initilizer.getInstance().extent.startTest(FEATURE + "_ " + TC_ID + "_ " + TC_DESCRIPTION);
		try {
			ObjectGroups.getInstance().actionOnElementsMobileWeb(OBJECT_GROUP, OBJECT_KEY, ACTION_KEY, ASSERTION_TYPE,ASSERTION_DATA, InitMethodiOS.OBJECT_GROUP, InitMethodiOS.OBJECT_REPROSITORY,InitMethodiOS.INPUT_DATA, CONTROL_FLAG,STATUS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}