package sfdc.pages.om;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date: 03/02/2020
 *
 *         SFDC Complete Vlocity Order Page(Manual Queues> Service Delivery
 *         Queue>Pick Up>Complete Vlocity Order)
 */
public class SFDC_CompleteVlocityOrder_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_CompleteVlocityOrder_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Complete Vlocity Order@: ";

	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Assign to me check box
	 * 
	 *                     Click on confirm button
	 * 
	 *                     Verify Task is assigned
	 * 
	 *                     Verify Task status is Running
	 * 
	 *                     Verify that Supersystem order CAN is displayed
	 * 
	 *                     Click on 'Complete Vlocity Order' Confirmed Checkbox
	 * 
	 *                     Click on 'Complete Vlocity Order' Complete Button
	 */
	public void completeVlocityOrder() throws IOException {

		try {

			assignTask();

			// Verify that Supersystem CAN is displayed
			verifyFieldValue("Supersystem order  CAN", sf.compVlctOdr.superSystemCANText, InputData.superSystemCAN);
			verifyFieldValue("Order Number", sf.compVlctOdr.orderNumber, Global.dataInput.orderNumber);
			verifyFieldValue("Work order Number 1", sf.compVlctOdr.workOrderNumber1,
					InputData.superSystemWorkOrderNumber1);
			verifyFieldValue("Work Order Number 2", sf.compVlctOdr.workOrderNumber2,
					InputData.superSystemWorkOrderNumber2);

			selectConfirmAndClickComplte();

		} catch (Throwable e) {
			reportStatusFail(" Error in completing Vlocity Order", e);
			e.printStackTrace();
		}
	}

	public void selectConfirmAndClickComplte() throws IOException {
		try {

			// Click on 'Complete Vlocity Order' Confirmed Checkbox

			for (int i = 0; i < sf.compVlctOdr.confirmedCheckbox.size(); i++) {
				sf.seleU.clickElementByJSE(sf.compVlctOdr.confirmedCheckbox.get(i));
			}
			reportStatusPass(methodName + " Clicked on 'Work Order Number' Confirmed Checkbox", true, false);

			sf.seleU.hardwait(2000);

			// Click on 'Complete Vlocity Order' Complete Button
			sf.seleU.clickElementByJSE(sf.compVlctOdr.completeButton);
			reportStatusPass(methodName + " Clicked on 'Complete Vlocity Order' Complete Button", true, false);

			sf.seleU.wait(10000);

			sf.seleU.switchWindow(1);
			sf.seleU.closeRecentlyOpenedWindow();

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in completing Vlocity Order", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Assign Task
	 * 
	 *                     Enter Busniess Failure Text
	 * 
	 *                     Click on Complete Button
	 */
	public void failTask2() throws IOException {
		try {

			assignTask();

			// Click on Fail Task Checkbox
			sf.seleU.clickElementByJSE(sf.compVlctOdr.failTaskCheckBox);
			reportStatusPass(methodName + " Clicked on Failed task Checkbox ", true, false);
			sf.seleU.hardwait(2000);

			// Enter Busniess Failure Text
			sf.seleU.clearAndEnterText(sf.compVlctOdr.businessFailureText, Global.dataInput.businessFailureText);
			reportStatusPass(methodName + " Entered Busniess Failure Text ", true, false);

			sf.seleU.hardwait(2000);

			// Click on 'Complete Vlocity Order' Complete Button
			sf.seleU.clickElementByJSE(sf.compVlctOdr.completeButton);
			reportStatusPass(methodName + " Clicked on 'Complete Vlocity Order' Complete Button", true, false);

			sf.seleU.hardwait(5000);

			sf.seleU.switchWindow(1);
			sf.seleU.closeRecentlyOpenedWindow();

			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in Task Failure", e);
			e.printStackTrace();
		}
	}

	/**
	 * Click on Assign to me check box
	 * 
	 * Click on confirm button
	 * 
	 * Verify Task is assigned
	 * 
	 * Verify Task status is Running
	 * 
	 * @throws IOException
	 */
	public void assignTask() throws IOException {

		try {

			// Click on Assign to me check box
			/*
			 * sf.seleU.clickElementByJSE(sf.compVlctOdr.assignToMeCheckbox);
			 * reportStatusPass(methodName + " Clicked on Assign to me check box ", true,
			 * false); sf.seleU.hardwait(2000);
			 * 
			 * // Click on confirm button
			 * sf.seleU.clickElementByJSE(sf.compVlctOdr.assignTaskConfirmButton);
			 * reportStatusPass(methodName + " Clicked on confirm button ", true, false);
			 * sf.seleU.hardwait(6000);
			 */

			// Verify Task is assigned and Running
			verifyFieldValue("Task State ", sf.compVlctOdr.displayStateText, Global.dataInput.taskStatusRunning);
			verifyFieldValue("Task assigned to user ", sf.compVlctOdr.assignedToUserText,
					Global.dataInput.taskAssignedToYouText);

		} catch (Throwable e) {
			reportStatusFail(" Error in Assigning Task", e);
			e.printStackTrace();
		}

	}

	/**
	 * Click on Change State check box
	 * 
	 * Select Pending value from state dropdown
	 * 
	 * Click on Change State button
	 * 
	 * Verify status changed to Pending
	 * 
	 * @throws IOException
	 */
	public void changeStateToPending() throws IOException {
		try {

			// Click on Change State check box
			sf.seleU.clickElementByJSE(sf.crSSOdr.changeStateCheckbox);
			reportStatusPass(methodName + " Clicked on Change State check box ", true, false);
			sf.seleU.hardwait(2000);

			// Select Pending value from state dropdown
			sf.seleU.selectTextFromDropDown(sf.crSSOdr.selectStateDropdown, Global.dataInput.taskStatusPending);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.crSSOdr.changeStateButton);

			reportStatusPass(methodName + " Selected 'Pending' value from dropdown ", true, false);
			sf.seleU.hardwait(2000);

			// Verify status changed to Pending
			verifyFieldValue("Task State ", sf.compVlctOdr.displayStateText, Global.dataInput.taskStatusPending);
			verifyFieldValue("Task assigned to user ", sf.compVlctOdr.assignedToUserText,
					Global.dataInput.taskAssignedToYouText);

			// Click on 'Complete Vlocity Order' Confirmed Checkbox

			for (int i = 0; i < sf.compVlctOdr.confirmedCheckbox.size(); i++) {
				sf.seleU.clickElementByJSE(sf.compVlctOdr.confirmedCheckbox.get(i));
			}
			reportStatusPass(methodName + " Clicked on 'Work Order Number' Confirmed Checkbox", true, false);

			sf.seleU.hardwait(2000);

			// Validate Order Status
			// sf.seleU.clickElementByJSE(sf.crSSOdr.validateOrderStatusCheck);
			// reportStatusPass(methodName + " Validated Order Status ", true, false);
			// sf.seleU.hardwait(2000);

			// verifyFieldValue("Success Proceed With The Task msg ",
			// sf.crSSOdr.successTaskProceed, "Success");

		} catch (Throwable e) {
			reportStatusFail(" Error in Changing State", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Assign task and hit next
	 */
	public void completeOffice365Order() throws IOException {
		try {

			verifyFieldValue("Order Number", sf.compVlctOdr.orderNumber, Global.dataInput.orderNumber);
			verifyFieldValue("Order Status", sf.compVlctOdr.orderStatus, Global.dataInput.orderStatusInProgress);

			verifyFieldValue("Business Account Name", sf.compVlctOdr.businessAccountName,
					Global.dataInput.businessAccountName);

			verifyFieldValue("SGI Dealer Code", sf.compVlctOdr.sgiDealerCode, InputData.sgiDealerCode);
			verifyFieldValue("V21 Dealer Code", sf.compVlctOdr.v21DealerCode, InputData.v21DealerCode);
			verifyFieldValue("Super System CAN", sf.compVlctOdr.superSystemCANIntentProducts, InputData.superSystemCAN);

			verifyFieldValue("Office 365 product name", sf.compVlctOdr.office365ProductName,
					Global.dataInput.office365ProductName);

			assignTask();

			// Validate Order Status
			sf.seleU.clickElementByJSE(sf.compVlctOdr.office365NextButton);
			reportStatusPass(methodName + " Clicked on 'Create Office 365 Order' Next Button ", true, false);
			sf.seleU.wait(10000);

			sf.seleU.switchWindow(1);
			sf.seleU.closeRecentlyOpenedWindow();

			sf.seleU.wait(5000);
			sf.seleU.refreshPage();
			sf.seleU.wait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in Completing Office 365 Order", e);
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
			if (sf.seleU.getTextFromWebElement(element).trim().contains(expectedText.trim())) {
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
