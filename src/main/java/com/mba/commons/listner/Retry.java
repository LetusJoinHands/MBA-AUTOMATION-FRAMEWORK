package com.mba.commons.listner;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author Shenilton
 * @version 2.1
 */

public class Retry implements IRetryAnalyzer {

	private int count = 0;
	private static int maxTry = 1;

	public boolean retry(ITestResult iTestResult) {
		if (!iTestResult.isSuccess()) {
			if (count < maxTry) {
				count++;
				iTestResult.setStatus(ITestResult.FAILURE);
				return true;
			}
		} else {
			iTestResult.setStatus(ITestResult.SUCCESS);
		}
		return false;
	}
}