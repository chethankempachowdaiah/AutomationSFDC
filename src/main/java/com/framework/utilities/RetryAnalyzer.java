package com.framework.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 *
 * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
 * 
 *      This method decides how many times a test needs to be rerun. TestNg will
 *      call this method every time a test fails. So we can put some code in
 *      here to decide when to rerun the test.
 * 
 *      Note: This method will return true if a tests needs to be retried and
 *      false it not.
 *
 */
public class RetryAnalyzer implements IRetryAnalyzer {

	private int count = 0;
	private static int maxTry = 2;

	/**
	 * Check if test not succeed ...... ..........Increase the maxTry count by 1
	 * Mark test as failed.........................Tells TestNG to re-run the test
	 * If maxCount reached,test marked as failed..................................
	 * If test passesTestNG marks it as passed....................................
	 *
	 */
	public boolean retry(ITestResult iTestResult) {

		if (!iTestResult.isSuccess()) {

			if (count < maxTry) {
				count++;
				iTestResult.setStatus(ITestResult.FAILURE);
				return true;
			} else {
				iTestResult.setStatus(ITestResult.FAILURE);
			}

		} else {
			iTestResult.setStatus(ITestResult.SUCCESS);
		}
		return false;
	}

}