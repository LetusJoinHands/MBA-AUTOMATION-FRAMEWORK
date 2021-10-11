package com.mba.commons.assertEngine;

import org.testng.Assert;

/**
 * @Author Shenilton
 * @Date sept 21 2019
 */

public class AssertEngine {

	public static void assertMapping(String assertTypt, String actual, String expected, boolean expectedResult) {
		switch (assertTypt) {
		case "equals":
			Assert.assertEquals(actual, expected);
			break;
		case "notEquals":
			Assert.assertNotEquals(actual, expected);
			break;
		case "display":
			Assert.assertTrue(expectedResult);
			break;
		case "contains":
			Assert.assertTrue(actual.contains(expected));
			break;
		case "trueMessage":
			Assert.assertTrue(expectedResult, expected);
			break;
		default:
			System.out.println("Invalid input");
		}
	}
}