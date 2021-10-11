package com.mba.commons.dataMapping;

import com.mba.apiAutomation.webServiceMethods.ReusableFunctionAPI;
import com.mba.commons.assertEngine.AssertEngine;
import com.mba.commons.controlers.Initilizer;
import com.mba.commons.reusableFunction.FileHandling;
import com.mba.commons.reusableFunction.ResuableFunctionMobile;
import com.mba.commons.reusableFunction.ReusableFunctionBrowser;
import com.mba.commons.reusableFunction.SwitchToFrame;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author Shenilton
 * @version 19-07-2020
 */

public class ActionMapping {

	public String baseURI = DataImp.getInstance().preReqsite("URI");
	private static ActionMapping obj;
	private static ResuableFunctionMobile function;
	private static ReusableFunctionAPI functionApi;
	private static ReusableFunctionBrowser wait;
	private static SwitchToFrame frame;

	private ActionMapping() {
		super();
	}

	public static ActionMapping getInstance() {
		if (obj == null && function == null && functionApi == null)
			obj = new ActionMapping();
		function = new ResuableFunctionMobile();
		functionApi = new ReusableFunctionAPI();
		wait = new ReusableFunctionBrowser();
		frame = new SwitchToFrame();
		return obj;
	}

	public void actionMappingWeb(String action, String ele, String object, String sentValue, String assertType,
			String expected, int indexForMultipleElements) throws Exception {
		String[] splittingValues = ele.split("_");
		switch (action) {
		case "SND":
			wait.wait(20);
			wait.webelementReturn(splittingValues[splittingValues.length - 1], object).sendKeys(sentValue);
			break;
		case "CLK":
			wait.wait(20);
			wait.webelementReturn(splittingValues[splittingValues.length - 1], object).click();
			break;
		case "GET_VERIFY":
			wait.wait(20);
			boolean flag = wait.webelementReturn(splittingValues[splittingValues.length - 1], object).isDisplayed();
			String actualValue = "";
			if (flag == true) {
				actualValue = wait.webelementReturn(splittingValues[splittingValues.length - 1], object).getText();
			}	
			AssertEngine.assertMapping(assertType, actualValue, expected, false);
			break;
		case "GET":
			wait.wait(20);
			String actualValueGet = wait.webelementReturn(splittingValues[splittingValues.length - 1], object)
					.getText();
			AssertEngine.assertMapping(assertType, actualValueGet, expected, false);
			break;
		case "isDisplayed":
			wait.wait(20);
			boolean flagForDisplayed = wait.webelementReturn(splittingValues[splittingValues.length - 1], object)
					.isDisplayed();
			AssertEngine.assertMapping(assertType, "", expected, flagForDisplayed);
			break;
		case "V_isDisplayed":
			wait.wait(20);
			boolean flagForDisplayedVissible = wait
					.explicitWaitWebDriverVisibilityOfElement(splittingValues[splittingValues.length - 1], 20, object)
					.isDisplayed();
			AssertEngine.assertMapping(assertType, "", expected, flagForDisplayedVissible);
			break;
		case "isEnable":
			wait.wait(20);
			boolean isEnabled = wait.webelementReturn(splittingValues[splittingValues.length - 1], object)
					.isDisplayed();
			AssertEngine.assertMapping(assertType, "", expected, isEnabled);
			break;
		case "CLEAR":
			wait.wait(20);
			wait.webelementReturn(splittingValues[splittingValues.length - 1], object).clear();
			break;
		case "WAIT":
			wait.wait(20);
			break;
		case "iFRAME":
			wait.wait(20);
			frame.switchToFrameByNameorId(object);
			break;
		case "D_SWITCH":
			wait.wait(20);
			frame.defaultContent();
			break;

		default:
			System.out.println("Default value");
		}
	}

	public void actionMappingAPI(String action, String ele, String object, String sentValue, String assertType,String expected, int indexForMultipleElements) throws Exception {
		String pathTillTemplate = System.getProperty("user.dir") + "/src/main/resources/apiAutomation/templates/";
		switch (action) {
		case "POST-STATUS":
			int statusCode = functionApi.methodConsolidation(action, sentValue, object).statusCode();
			String resBodyStausPost = functionApi.methodConsolidation(action, sentValue, object).body().asString();
			if (resBodyStausPost != null) {
				new FileHandling().writtingToFile(pathTillTemplate + "Post/Response/" + ele + ".json", resBodyStausPost);
			}
			Initilizer.getInstance().logger.log(LogStatus.INFO, "API Information -> " + baseURI+object);
			Initilizer.getInstance().logger.log(LogStatus.INFO, "Response -> Status Code " + statusCode);
			Initilizer.getInstance().logger.log(LogStatus.INFO, "Response Message " + resBodyStausPost);
			AssertEngine.assertMapping(assertType, String.valueOf(statusCode), expected, false);
			break;
		case "GET-STATUS":
			int statusCodegetWithParmStaus = functionApi.methodConsolidation(action, sentValue, object).statusCode();
			String resBody = functionApi.methodConsolidation(action, sentValue, object).body().asString();
			if (resBody != null) {
				new FileHandling().writtingToFile(pathTillTemplate + "Get/Response/" + ele + ".json", resBody);
			}
			Initilizer.getInstance().logger.log(LogStatus.INFO, "API Information -> " + baseURI+object);
			Initilizer.getInstance().logger.log(LogStatus.INFO, "Response -> Status Code " + statusCodegetWithParmStaus);
			Initilizer.getInstance().logger.log(LogStatus.INFO, "Response Message " + resBody);
			AssertEngine.assertMapping(assertType, String.valueOf(statusCodegetWithParmStaus), expected, false);
			break;
		case "DELETE-STATUS":
			int statusCodepostWithHeaderStatus = functionApi.methodConsolidation(action, sentValue, object).statusCode();
			String resBodySTATUSdelete = functionApi.methodConsolidation(action, sentValue, object).body().asString();
			if (resBodySTATUSdelete != null) {
				new FileHandling().writtingToFile(pathTillTemplate + "Delete/Response/" + ele + ".json", resBodySTATUSdelete);
			}
			Initilizer.getInstance().logger.log(LogStatus.INFO, "API Information -> " + baseURI+object);
			Initilizer.getInstance().logger.log(LogStatus.INFO, "Response -> Status Code " + statusCodepostWithHeaderStatus);
			Initilizer.getInstance().logger.log(LogStatus.INFO, "Response Message " + resBodySTATUSdelete);
			AssertEngine.assertMapping(assertType, String.valueOf(statusCodepostWithHeaderStatus), expected, false);
			break;
		case "PUT-STATUS":
			int statusCodepostWithHeaderStatusPUT = functionApi.methodConsolidation(action, sentValue, object).statusCode();
			String resBodySTATUSdeletePUT = functionApi.methodConsolidation(action, sentValue, object).body().asString();
			if (resBodySTATUSdeletePUT != null) {
				new FileHandling().writtingToFile(pathTillTemplate + "Delete/Response/" + ele + ".json", resBodySTATUSdeletePUT);
			}
			Initilizer.getInstance().logger.log(LogStatus.INFO, "API Information -> " + baseURI+object);
			Initilizer.getInstance().logger.log(LogStatus.INFO, "Response Message " + resBodySTATUSdeletePUT);
			Initilizer.getInstance().logger.log(LogStatus.INFO, "Response - > Status Code  " + statusCodepostWithHeaderStatusPUT);
			AssertEngine.assertMapping(assertType, String.valueOf(statusCodepostWithHeaderStatusPUT), expected, false);
			break;
		case "POST-DATA-VALIDATION":
			String statusCodeputStatus = functionApi.methodConsolidation(action, sentValue, object).body().jsonPath().get("");
			String resBodyPOST = functionApi.methodConsolidation(action, sentValue, object).body().asString();
			if (resBodyPOST != null) {
				new FileHandling().writtingToFile(pathTillTemplate + "Post/Response/" + ele + ",.json", resBodyPOST);
			}
			Initilizer.getInstance().logger.log(LogStatus.INFO, "Response Message " + resBodyPOST);
			AssertEngine.assertMapping(assertType, String.valueOf(statusCodeputStatus), expected, false);
			break;
		case "PUT-DATA-VALIDATION":
			int statusCodeputWithoutHeaderStatus = functionApi.methodConsolidation(action, sentValue, object).statusCode();
			String resBodyPUT = functionApi.methodConsolidation(action, sentValue, object).body().asString();
			if (resBodyPUT != null) {
				new FileHandling().writtingToFile(pathTillTemplate + "put/Response/" + ele + ",.json", resBodyPUT);
			}
			AssertEngine.assertMapping(assertType, String.valueOf(statusCodeputWithoutHeaderStatus), expected, false);
			break;
		case "GET-DATA-VALIDATION":
			int statusCodedeleteStatus = functionApi.methodConsolidation(action, sentValue, object).statusCode();
			String resBodyGET = functionApi.methodConsolidation(action, sentValue, object).body().asString();
			if (resBodyGET != null) {
				new FileHandling().writtingToFile(pathTillTemplate + "Get/Response/" + ele + ",.json", resBodyGET);
			}
			Initilizer.getInstance().logger.log(LogStatus.INFO, "Response Message " + resBodyGET);
			AssertEngine.assertMapping(assertType, String.valueOf(statusCodedeleteStatus), expected, false);
			break;
		case "DELETE-DATA-VALIDATION":
			int statusCodedeleteWithoutParmStatus = functionApi.methodConsolidation(action, sentValue, object).statusCode();
			String resBodyDELETE = functionApi.methodConsolidation(action, sentValue, object).body().asString();
			if (resBodyDELETE != null) {
				new FileHandling().writtingToFile(pathTillTemplate + "Delete/Response/" + ele + ",.json",
						resBodyDELETE);
			}
			Initilizer.getInstance().logger.log(LogStatus.INFO, "Response Message " + resBodyDELETE);
			AssertEngine.assertMapping(assertType, String.valueOf(statusCodedeleteWithoutParmStatus), expected, false);
			break;
		default:
			System.out.println("Default value");
		}
	}

	public void actionMappingMobile(String action, String ele, String object, String sentValue, String assertType,
			String expected, int indexForMultipleElements) throws Exception {
		switch (action) {
		case "SND":
			if (ele.contains("XPATH")) {
				function.clcikByXpath(object);
				function.sendByWaitByString("Xpath", object, sentValue);
				System.out.println(sentValue);
			} else if (ele.contains("ID")) {
				function.clcikByID(object);
				function.sendByWaitByString("ID", object, sentValue);
			}
			break;
		case "CLK":
			if (ele.contains("XPATH")) {
				function.clcikForWaitByXpath(object);
			} else if (ele.contains("ID")) {
				function.clcikForWaitByID(object);
			}
			break;
		case "GET":
			if (ele.contains("XPATH")) {
				String assertByTextXPATH = function.getTextFromElementByWait("xpath", object, 20);
				AssertEngine.assertMapping(assertType, assertByTextXPATH, expected, false);
			} else if (ele.contains("ID")) {
				String assertByTextID = function.getTextFromElementByWait("id", object, 20);
				AssertEngine.assertMapping(assertType, assertByTextID, expected, false);
			}
			break;
		case "SND_iOS":
			if (ele.contains("XPATH")) {
				function.clcikByXpath(object);
				function.sendByWaitByString("Xpath", object, sentValue);
			} else if (ele.contains("ID")) {
				function.clcikByXpath(object);
				function.sendByWaitByString("Xpath", object, sentValue);
			}
			break;
		case "GET_BY_AT":
			if (ele.contains("XPATH")) {
				String assertByTextXPATH = function.getTextFromElementByWait("xpathAttribute", object, 20);
				AssertEngine.assertMapping(assertType, assertByTextXPATH, expected, false);
			} else if (ele.contains("ID")) {
				String assertByTextXPATH = function.getTextFromElementByWait("idAttribute", object, 20);
				AssertEngine.assertMapping(assertType, assertByTextXPATH, expected, false);
			}
			break;
		case "isDisplayed":
			if (ele.contains("XPATH")) {
				boolean displayedInDomXPATH = false;
				displayedInDomXPATH = function.isDisplayedInDom("xpath", object);
				AssertEngine.assertMapping(assertType, "", expected, displayedInDomXPATH);
			} else if (ele.contains("ID")) {
				boolean displayedInDomXPATH = false;
				displayedInDomXPATH = function.isDisplayedInDom("id", object);
				AssertEngine.assertMapping(assertType, "", expected, displayedInDomXPATH);
			}
			break;
		case "isEnabled":
			if (ele.contains("XPATH")) {
				boolean displayedInDomXPATH = false;
				displayedInDomXPATH = function.isEnabledInDom("xpath", object);
				AssertEngine.assertMapping(assertType, "", expected, displayedInDomXPATH);
			} else if (ele.contains("ID")) {
				boolean displayedInDomXPATH = false;
				displayedInDomXPATH = function.isEnabledInDom("id", object);
				AssertEngine.assertMapping(assertType, "", expected, displayedInDomXPATH);
			}
			break;
		default:
			System.out.println("Default value");
		}
	}

}