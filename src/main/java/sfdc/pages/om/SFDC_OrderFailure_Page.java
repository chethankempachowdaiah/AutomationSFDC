package sfdc.pages.om;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date: 28/march/2020
 *
 *         SFDC Order Failure Page (Order>order Activity>Order failure
 *         activity>Order failure page)
 */
public class SFDC_OrderFailure_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_OrderFailure_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Order_Failure@: ";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify correct order displayed and subject is 'order
	 *                     failure'
	 * 
	 *                     Add Comment and End Mark Status as completed
	 * 
	 *                     Verify Status is completed
	 */
	public void markOrderFailureTaskCompleted() throws IOException {
		try {

			// Verify correct order displayed and subject is 'order failure'
			verifyFieldValue("Related order", sf.orFailure.relatedToOrderLink, sf.dataInput.orderNumber);
			verifyFieldValue("Order Failure Subject", sf.orFailure.orderFailureSubject,
					sf.dataInput.orderFailureSubject);
			if (sf.orFailure.orderStatusFieldValueText.size() == 1) {
				verifyFieldValue("Order Status", sf.orFailure.orderStatusFieldValueText.get(0),
						sf.dataInput.orderFailureStatusOpen);
			} else {
				verifyFieldValue("Order Status", sf.orFailure.orderStatusFieldValueText.get(1),
						sf.dataInput.orderFailureStatusOpen);
			}

			// Add Comment and End Mark Status as completed
			sf.seleU.clickElementByJSE(sf.orFailure.editCommentButton);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.orFailure.commentTextArea, sf.dataInput.orderFailureComment);
			sf.seleU.clickElementByJSE(sf.orFailure.statusDropdown);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.orFailure.orderStatusComletedOptionLink);
			sf.seleU.clickElementByJSE(sf.orFailure.saveButton);
			reportStatusPass(methodName + " Added Comment and End Marked Status as completed", true, true);
			sf.seleU.wait(10000);
			sf.seleU.refreshPage();
			sf.seleU.wait(10000);

			// Verify Status is completed
			if (sf.orFailure.orderStatusFieldValueText.size() == 1) {
				verifyFieldValue("Order Status", sf.orFailure.orderStatusFieldValueText.get(0),
						sf.dataInput.orderFailureStatusCompleted);
			} else {
				verifyFieldValue("Order Status", sf.orFailure.orderStatusFieldValueText.get(1),
						sf.dataInput.orderFailureStatusCompleted);
			}

			verifyFieldValue("Order Failure Comments", sf.orFailure.commentsValueText,
					sf.dataInput.orderFailureComment);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in viewing 'failure task Activity ' in order", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).contains(expectedText)) {
				reportStatusPass("Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail("Actual Value for " + fieldName + " is " + element.getText() + " And Expected One is "
						+ expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
}
