package sfdc.pages.om;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.ScreenDocs;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 03/FEB/2020
 * 
 *         SFDC Create Supersystem Order (Manual Queue>Account Provisioning
 *         Queue Details>Pick Up> Create Supersystem Order)
 *
 */
public class SFDC_CreateSuperSystemOrder_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_CreateSuperSystemOrder_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Create Supersystem Order@: ";
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
	 *                     Click on Create Supersystem Order Next Button
	 * 
	 *                     Enter Supersystem CAN
	 * 
	 *                     Click on Persist Super System CAN Complete Button
	 * 
	 */

	public void createSuperSystemOrder() throws IOException {
		try {

			verifyFieldValue("Business Account Name  ", sf.crSSOdr.businessAccountName, InputData.currentAccount);
			verifyFieldValue("Order Number  ", sf.crSSOdr.orderNumber, Global.dataInput.orderNumber);

			verifyFieldValue("Order State  ", sf.crSSOdr.orderState, Global.dataInput.orderStatusInProgress);
			verifyFieldValue("Site Name ", sf.crSSOdr.siteName, Global.dataInput.serviceAccountName);
			// verifyFieldValue("Site Contact Name ", sf.crSSOdr.siteContactName,
			// sf.dataInput.siteContactName);

			verifyFieldValue("V21 Dealer Code ", sf.crSSOdr.v21DealerCode, InputData.v21DealerCode);
			verifyFieldValue("SGI Dealer Code ", sf.crSSOdr.sgiDealerCode, InputData.sgiDealerCode);

			assignTask();
			selectNextAndEnterCAN();

		} catch (Throwable e) {
			reportStatusFail(" Error in creating super system order", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 * 
	 * 
	 *                     Click on Create Supersystem Order Next Button
	 * 
	 *                     Enter Supersystem CAN
	 * 
	 *                     Click on Persist Super System CAN Complete Button
	 * 
	 */
	public void selectNextAndEnterCAN() throws IOException {
		try {

			// Enter Supersystem CAN
			sf.seleU.clearAndEnterText(sf.crSSOdr.superSystemCANNumberInput, InputData.superSystemCAN);
			sf.seleU.enterText(sf.crSSOdr.superSystemCANNumberInput, Keys.ENTER);
			sf.seleU.wait(2000);

			reportStatusPass(methodName + " Entered Supersystem CAN Input " + InputData.superSystemCAN, true, false);

			// Enter Supersystem Work Order Number
			sf.seleU.clearAndEnterText(sf.crSSOdr.superSystemWorkOrderNumber1Input,
					InputData.superSystemWorkOrderNumber1);
			sf.seleU.enterText(sf.crSSOdr.superSystemWorkOrderNumber1Input, Keys.ENTER);
			sf.seleU.wait(2000);

			sf.seleU.clickElementByJSE(sf.crSSOdr.workOrderAddButton);
			sf.seleU.wait(2000);

			sf.seleU.clearAndEnterText(sf.crSSOdr.superSystemWorkOrderNumber2Input,
					InputData.superSystemWorkOrderNumber2);
			// sf.seleU.enterText(sf.crSSOdr.superSystemWorkOrderNumber2Input, Keys.TAB);
			sf.seleU.wait(2000);
			reportStatusPass(methodName + " Entered Supersystem Work Order Number Input "
					+ InputData.superSystemWorkOrderNumber1, true, false);

			// Enter Consolidated V21 BAN
			if (sf.seleU.isElementDisplayed(sf.crSSOdr.consolidatedV21BANInput)) {
				sf.seleU.clearAndEnterText(sf.crSSOdr.consolidatedV21BANInput, InputData.v21BAN);
				// sf.seleU.enterText(sf.crSSOdr.consolidatedV21BANInput, Keys.TAB);
				sf.seleU.wait(2000);
				reportStatusPass(methodName + " Entered Consolidated V21 BAN " + InputData.v21BAN, true, false);

			}

			// Validate Order Status
			sf.seleU.clickElementByJSE(sf.crSSOdr.validateOrderStatusCheck);
			reportStatusPass(methodName + " Validated Order Status ", true, false);
			sf.seleU.hardwait(2000);

			verifyFieldValue("Success Proceed With The Task msg ", sf.crSSOdr.successTaskProceed, "Success");

			// Click on Persist Super System CAN Complete Button
			sf.seleU.clickElementByJSE(sf.crSSOdr.persistSupersystemCANCompleteButton);
			reportStatusPass(methodName + " Clicked on Persist Super System CAN Complete Button ", true, false);

			sf.seleU.hardwait(5000);

			try {
				sf.seleU.switchWindow(1);
			} catch (Exception e) {
			}
			sf.seleU.closeRecentlyOpenedWindow();

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in creating super system order", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 */
	public void selectCancelInSuperSystemOrder() throws IOException {
		try {

			// Click on cancel in Create Super system Order
			sf.seleU.clickElementByJSE(sf.crSSOdr.cancelButton);
			reportStatusPass(methodName + " Clicked on cancel in Create Super system Order", true, false);

			// Click on cancel OK in Create Super system Order
			sf.seleU.clickElementByJSE(sf.crSSOdr.cancelOkButton);
			reportStatusPass(methodName + " Clicked on cancel OK in Create Super system Order", true, false);

			sf.seleU.switchWindow(1);
			sf.seleU.closeRecentlyOpenedWindow();

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in cancelling super system order", e);
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
	 *                     Click on Create Super system Order Next Button
	 */
	public void failTask1() throws IOException {
		try {

			verifyFieldValue("Business Account Name ", sf.crSSOdr.businessAccountName, InputData.currentAccount);
			verifyFieldValue("Order Number  ", sf.crSSOdr.orderNumber, Global.dataInput.orderNumber);
			verifyFieldValue("V21 Dealer Code ", sf.crSSOdr.v21DealerCode, InputData.v21DealerCode);
			verifyFieldValue("SGI Dealer Code ", sf.crSSOdr.sgiDealerCode, InputData.sgiDealerCode);

			assignTask();

			// Click on Fail Task Checkbox
			sf.seleU.clickElementByJSE(sf.crSSOdr.failTaskCheckBox);
			reportStatusPass(methodName + " Clicked on Failed task Checkbox ", true, false);
			sf.seleU.hardwait(2000);

			// Enter Busniess Failure Text
			sf.seleU.clearAndEnterText(sf.crSSOdr.businessFailureText, Global.dataInput.businessFailureText);
			reportStatusPass(methodName + " Entered Busniess Failure Text ", true, false);

			sf.seleU.clickElementByJSE(sf.crSSOdr.persistSupersystemCANCompleteButton);
			reportStatusPass(methodName + " Clicked on Persist Super System CAN Complete Button ", true, false);

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
	 * 
	 * Verify is dealer code is displayed
	 * 
	 * Validate Business Account Name
	 * 
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
			sf.seleU.clickElementByJSE(sf.crSSOdr.assignToMeCheckbox);
			reportStatusPass(methodName + " Clicked on Assign to me check box ", true, false);
			sf.seleU.hardwait(2000);

			verifyFieldValue("Task State ", sf.crSSOdr.displayStateText, Global.dataInput.taskStatusRunning);
			verifyFieldValue("Task assigned to user ", sf.crSSOdr.assignedToUserText,
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
			reportStatusPass(methodName + " Selected 'Pending' value from dropdown ", true, false);
			sf.seleU.hardwait(2000);

			// Verify status changed to Pending
			verifyFieldValue("Task State ", sf.crSSOdr.displayStateText, Global.dataInput.taskStatusPending);
			verifyFieldValue("Task assigned to user ", sf.crSSOdr.assignedToUserText,
					Global.dataInput.taskAssignedToYouText);

			// Validate Order Status
			sf.seleU.clickElementByJSE(sf.crSSOdr.validateOrderStatusCheck);
			reportStatusPass(methodName + " Validated Order Status ", true, false);
			sf.seleU.hardwait(2000);

			verifyFieldValue("Success Proceed With The Task msg ", sf.crSSOdr.successTaskProceed, "Success");

		} catch (Throwable e) {
			reportStatusFail(" Error in Changing State", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Validate Contract code and name for TV and internet
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public void validateContractCodeName(Hashtable<String, String> dataTable) throws IOException {

		try {

			if (InputData.env.equals("ITCPQSQA")) {
				sf.seleU.scrollToBottom();
			} else {
				sf.seleU.ScrolltoElement(sf.crSSOdr.assignToMeCheckbox);
			}
			verifyInternetTvContractCodes(dataTable);
			String contractCodeInfo = " . Expected Contract Codes are ::  Internet:::"
					+ dataTable.get("ContractCode_Int") + "____" + dataTable.get("ContractName_Int") + " TV:::"
					+ dataTable.get("ContractCode_TV") + "____" + dataTable.get("ContractName_TV");

			ScreenDocs.captureScreenShot(docxDataSpecific, runDataSpecific, outDataSpecific,
					dataTable.get("Product") + ":: Order Number___" + Global.dataInput.orderNumber + contractCodeInfo);

			sf.seleU.switchWindow(1);
			sf.seleU.closeRecentlyOpenedWindow();

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in validating Contact code and name", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Verify Contract Code for TV and Internet
	 */
	public void verifyInternetTvContractCodes(Hashtable<String, String> dataTable) throws IOException {
		try {

			sf.seleU.ScrolltoElement(sf.crSSOdr.assignToMeCheckbox);

			verifyFieldValue("TV Contact Name", sf.crSSOdr.tvProductsContractName, dataTable.get("ContractName_TV"));
			verifyFieldValue("TV Contact Code", sf.crSSOdr.tvProductsContractCode, dataTable.get("ContractCode_TV"));
			verifyFieldValue("Internet Contact Name", sf.crSSOdr.internetProductsContractName,
					dataTable.get("ContractName_Int"));
			verifyFieldValue("Internet Contact Code", sf.crSSOdr.internetProductsContractCode,
					dataTable.get("ContractCode_Int"));

			// verifyFieldValue("Internet Speed", sf.crSSOdr.speedInInternetProduct,
			// dataTable.get("Speed In Task1"));

		} catch (Throwable e) {
			reportStatusFail(" Error in validating Contact code and name", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Validate Contract code and name for internet
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public void validateInternetContractCode(Hashtable<String, String> dataTable) throws IOException {
		try {

			sf.seleU.ScrolltoElement(sf.crSSOdr.assignToMeCheckbox);

			verifyFieldValue("Internet Contact Name", sf.crSSOdr.internetProductsContractName,
					dataTable.get("Contract_Code"));
			verifyFieldValue("Internet Contact Code", sf.crSSOdr.internetProductsContractCode,
					dataTable.get("Contract_Code"));

			sf.seleU.navigateBack();
			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in validating Contact code and name", e);
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
			if (!expectedText.equals("NA")) {
				// Verify Field value matches the expected result
				if (sf.seleU.getTextFromWebElement(element).trim().contains(expectedText.trim())) {
					reportStatusPass("Validated " + fieldName + " is " + expectedText, true, true);
				} else {
					reportStatusFail("Actual Value for " + fieldName + " is " + element.getText()
							+ " And Expected One is " + expectedText, true);
				}
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

}
