package com.mba.commons.controlers;

import java.io.IOException;

import com.mba.commons.dataDriven.CommomReadFunction;
import com.mba.commons.dataDriven.DataInputProvider;
import com.mba.commons.identifiers.InitMethodWindows;

/**
@author Shenilton
@version 2.1
*/

public class CommonCallFunction {
	
	public static String callPropertyValueInputDataWithPathDetails(String redturnValue, String propertyPath) {
		String value = CommomReadFunction.getInstance().propertyValueCall(redturnValue, propertyPath);
		return value;
	}
	
	public static String callPropertyValueInputDataWithPathObject(String redturnValue, String propertyPath) {
		String value = CommomReadFunction.getInstance().propertyValueCall(redturnValue, propertyPath);
		return value;
	}
	
	public static Object[][] dataRequiredToTest(String sheetName, String pathOfTheSheet) throws IOException {
		return DataInputProvider.getInstance().getSheet(sheetName, pathOfTheSheet);
	}
	
}
