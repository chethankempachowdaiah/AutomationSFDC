package com.framework.utilities;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Sakshi.Lnu
 * @Date: 20.09.2021
 *  This class methods represents all the UI fields related verification
 *
 */
public class VerificationUtilities extends MyListener {
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public VerificationUtilities() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * Verify Field is present
	 * @return 
	 * 
	 * @throws IOException
	 */
	public static void verifyFieldPresent(String fieldName, WebElement element) throws IOException {
		methodName = "Field Verification@: " + fieldName;
		try {

			// Verify Field is present
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(
						fieldName + " is present" + " with value as " + sf.seleU.getTextFromWebElement(element), true,
						true);
			} else {
				reportStatusPass(
						fieldName + " is not present" + " with value as " + sf.seleU.getTextFromWebElement(element),
						true, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * @throws IOException
	 * 
	 *             Verify Field value matches the expected result
	 */
	public static void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			sf.seleU.wait(5000);
			if (sf.seleU.getTextFromWebElement(element).trim().contains(expectedText)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail(methodName + " Actual Value for " + fieldName + " is " + element.getText()
				+ " And Expected One is " + expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public static void compareFieldValue(String fieldName, String screenText, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (screenText.trim().equals(expectedText)) {
				reportStatusPass(" Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail(" Actual Value for " + fieldName + " is " + screenText
						+ " And Expected One is " + expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *             Verify Field is displayed
	 */
	public static void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify field is displayed on page or not
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(methodName + " " + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in verifying field is displayed", e);
			e.printStackTrace();
		}
	}


	/**
	 * @throws IOException
	 * 
	 *             Verify Field has value/text
	 */
	public static void verifyFieldHasValue(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field has some value/non-empty
			if (!sf.seleU.getTextFromWebElement(element).isEmpty())
				reportStatusPass(
						methodName + " " + fieldName + " field has value : " + sf.seleU.getTextFromWebElement(element),
						true, true);
			else
				reportStatusFail(methodName + " " + fieldName + " field value is not populated and is empty", true);

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public static void compareFieldValueIfContains(String fieldName, String screenText, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (screenText.trim().contains(expectedText)) {
				reportStatusPass(" Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail(" Actual Value for " + fieldName + " is " + screenText
						+ " And Expected One is " + expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

}
