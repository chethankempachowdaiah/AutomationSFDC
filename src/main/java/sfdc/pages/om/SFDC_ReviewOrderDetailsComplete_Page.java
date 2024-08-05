package sfdc.pages.om;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.framework.base.MyListener;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.lib_pages.SFDC_Files_Page;
import com.sfdc.lib_pages.SFDC_Home_Page;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class SFDC_ReviewOrderDetailsComplete_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_ReviewOrderDetailsComplete_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_ReviewOrderDetailsComplete_Page@: ";

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify the field details in review order details page and
	 *                     select complete
	 */
	public void reviewOrderDetailsComplete_Page(Hashtable<String, String> dataTable) throws IOException {

		try {
			String methodName = "SFDC_Verify field value in review order details@: ";

			String dedicatedInternetPrice = "";
			double dedicatedProductPrice = 0;
			String cirProductPrice = "";
			double cirPrice = 0;
			double total_MRR = 0;
			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();
			// 1. Verify General Information Field
			verifyGeneralInformationField();

			// 2. Verify Additional Information Field
			sf.seleU.ScrolltoElement(sf.rODComplete.orderTypeText);
			verifyFieldPresent("Order Type", sf.rODComplete.orderTypeText);
			verifyFieldPresent("Sales region", sf.rODComplete.salesRegion);
			verifyFieldPresent("Sales Segment", sf.rODComplete.salesSegmentText);		

			// Business Info
			verifyFieldValue("Trade Name", sf.rODComplete.tradeNameText, sf.dataInput.businessAccountLegalName);
			verifyFieldValue("Site Name", sf.rODComplete.siteNameText, sf.dataInput.serviceAccountName);
			verifyFieldPresent("Type Of Business", sf.rODComplete.typeOfBusinessText);		
			verifyFieldValue("Number of Emp", sf.rODComplete.noOfEmpText, sf.dataInput.numberOfEmployees); // chnage it
			sf.seleU.hardwait(1000);

			sf.seleU.ScrolltoElement(sf.rODComplete.premisesInformationText);
			verifyFieldPresent("Premise Information", sf.rODComplete.premisesInformationText);
			verifyFieldValue("Demarcation Loc", sf.rODComplete.demarcationLocationText, sf.omData.demarcationLOC);
			verifyFieldPresent("Hours of Business",sf.rODComplete.hoursOfOperationValue);
			sf.seleU.ScrolltoElement(sf.rODComplete.contractTermText);
			verifyFieldPresent("Contract Term", sf.rODComplete.contractTermText);
			verifyFieldPresent("Credit Limit Approved Text", sf.rODComplete.creditLimitApprovedValue);
			sf.seleU.wait(1000);

			// 2. Verify Additional Information Field
			// Verify Signing Authority Contact
			sf.seleU.ScrolltoElement(sf.rODComplete.signingAuthorityContactNameText.get(0));
			verifyFieldValue("Signing Authority Contact Text", sf.rODComplete.signingAuthorityContactNameText.get(0),
					sf.dataInput.primaryContact);
			verifyFieldValue("Signing Authority Contact Email", sf.rODComplete.signingAuthorityEmailText.get(0),
					sf.dataInput.contactEmailAddress);
			verifyFieldValue("Signing Authority Contact Phone", sf.rODComplete.signingAuthorityPhoneText.get(0),
					sf.dataInput.phoneNumber);
			verifyFieldValue("Signing Authority Language Pref", sf.rODComplete.signingAuthorityLanguagePrefText.get(0),
					sf.dataInput.langEnglish);
			sf.seleU.wait(1000);

			// Verify Site Contact
			verifyFieldValue("Site Contact Name Text", sf.rODComplete.siteContactNameText,
					sf.dataInput.siteContact);
			verifyFieldValue("Site Contact Email", sf.rODComplete.siteContactEmailText,
					sf.dataInput.emailIdValue);
			sf.seleU.wait(1000);		
			verifyFieldValue("Site Contact Phone",sf.rODComplete.siteContactPhoneText,
					sf.dataInput.contactPhoneNumber);
			sf.seleU.wait(1000);
			verifyFieldValue("Site Contact Language Pref", sf.rODComplete.siteContactLanguageText,
					sf.dataInput.langEnglish);
			sf.seleU.wait(1000);

			// Verify Sales Contact
			//			verifyFieldValue("Sales Contact Name Text", sf.rODComplete.salesContactNameText,
			//					sf.dataInput.primaryContact);
			//			sf.seleU.scrollByCoOrdinates(2);
			//			verifyFieldValue("Sales Contact Email", sf.rODComplete.salesContactEmailText,
			//					sf.dataInput.contactEmailAddress);
			//			sf.seleU.wait(1000);
			//			verifyFieldValue("Sales Contact Phone", sf.rODComplete.salesContactPhoneValue,
			//					sf.dataInput.phoneNumber);

			// Validate Sales Agent Information
			verifyFieldValue("Sales Contact Name", sf.rODComplete.salesContactNameText, sf.dataInput.userProfileAe);
			verifyFieldPresent("Sales Contact Email", sf.rODComplete.salesContactEmailText);
			// verifyFieldPresent("Sales Contact Phone",
			// sf.rODComplete.salesContactPhoneValue);

			sf.seleU.scrollByCoOrdinates(2);

			// ****** 3. Validate Product Information
			sf.seleU.hardwait(2000);
			sf.seleU.ScrolltoElement(sf.rODComplete.productInformationHeaderText);
			verifyFieldPresent("Product Information", sf.rODComplete.productInformationHeaderText);
			verifyFieldValue("Product Speed", sf.rODComplete.productSpeedDetailsText,
					sf.dataInput.quoteInternetUploadSpeed);
			verifyFieldValue("Product Name", sf.rODComplete.productDetailsNameText, sf.dataInput.quoteProductName);
			verifyFieldValue("Encapsulation type", sf.rODComplete.encapsulationTypeValue, dataTable.get("encapsulationType"));
			verifyFieldValue("Demarcation Location", sf.rODComplete.demarcationLocationText, sf.dataInput.addressState);
			verifyFieldPresent("Service provider", sf.rODComplete.serviceProviderValue);
			verifyFieldPresent("Contract Details", sf.rODComplete.contractDetailsText);

			sf.seleU.ScrolltoElement(sf.rODComplete.circuitDetailsHeaderText);
			verifyFieldPresent("Circuit Details Text Header", sf.rODComplete.circuitDetailsHeaderText);
			sf.seleU.ScrolltoElement(sf.rODComplete.cirDetailsNameText);
			verifyFieldPresent("Circuit Prod Name", sf.rODComplete.cirDetailsNameText);	
			verifyFieldValueWithFormat("Access Type", sf.rODComplete.accessTypeValue, sf.dataInput.accessTypeNet);
			verifyFieldPresent("Access Speed", sf.rODComplete.accessSpeed);
			verifyFieldPresent("Access Provider", sf.rODComplete.accessProviderValue);
			verifyFieldValue("hand off", sf.rODComplete.handOff, dataTable.get("handOffInterface"));
			sf.seleU.wait(1000);


			// // Verifying Monthly Product Price
			//// dedicatedInternetPrice =
			// sf.seleU.getTextFromWebElement(sf.rODComplete.mRRProductPrice).trim().replace("CAD","").replace("
			// ","");
			//// dedicatedProductPrice = Double.parseDouble(dedicatedInternetPrice);
			////
			//// cirProductPrice =
			// sf.seleU.getTextFromWebElement(sf.rODComplete.mRRCirProductPrice).trim().replace("CAD","").replace("
			// ","");
			//// cirPrice = Double.parseDouble(cirProductPrice);
			////
			//// total_MRR = dedicatedProductPrice + cirPrice;
			////
			//// if(total_MRR == sf.dataInput.totalPrice) {
			//// reportStatusPass(methodName + "Matched the Monthly product Price that is "
			// + sf.dataInput.totalPrice, true, false);
			//// }
			//


			// verifyFieldValue("Term Details", sf.rODComplete.termDetailsText,
			// sf.dataInput.contractTerm36Months);

			// Service Billing Details chhange it for both the env
			sf.seleU.ScrolltoElement(sf.rODComplete.serviceBillingDetailsText);
			verifyFieldPresent("Service NRR Price", sf.rODComplete.serviceNRRPrice);
			verifyFieldPresent("Service MRR Price", sf.rODComplete.serviceMRRPrice);
			verifyFieldPresent("Access NRR Price", sf.rODComplete.accessNRRPrice);			
			verifyFieldPresent("Access MRR Price", sf.rODComplete.accessMRRPrice);

			verifyFieldPresent("Term Details", sf.rODComplete.termDetailsText);
			verifyFieldPresent("Validate Status", sf.rODComplete.validateStatusHeaderText);
			verifyFieldPresent("Cancel Button", sf.rODComplete.cancelButton);

			// Validation for with out status check box click
			//			sf.seleU.ScrolltoElement(sf.compVlctOdr.completeButton);
			//			sf.seleU.clickElementByJSE(sf.compVlctOdr.completeButton);
			//			sf.seleU.hardwait(1000);
			//			reportStatusPass(methodName + " Clicked on complete button before validate status check box", true, false);
			//			if (sf.seleU.isElementDisplayed(sf.rODComplete.vaidateOrderStatusCheckBoxError)) {
			//				sf.seleU.hardwait(3000);
			//				sf.seleU.clickElementByJSE(sf.rODComplete.vaidateOrderStatusCheckBoxError);
			//				reportStatusPass(methodName + " Clicked on please fix all errors alert ok button", true, false);
			//			}

			sf.seleU.clickElementByJSE(sf.rODComplete.vaidateOrderStatusCheckBox);
			reportStatusPass(methodName + " Clicked on 'Validate Order Status Check Box", true, false);

			sf.seleU.clickElementByJSE(sf.compVlctOdr.newCompleteButton);
			reportStatusPass(methodName + " Clicked on 'Review Order details Page' Complete Button", true, false);
			// selectConfirmAndClickComplte();

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying filed values in review order details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                    verify and Complete Task In Request Cancellation Status
	 */
	public void validateTaskStatus_ForRequestCancellationOrder_AndComplete(String product) throws IOException {

		try {
			String methodName = "SFDC_Verify field value in review order details@: ";

			// Validation for with out status check box click
			//	sf.seleU.ScrolltoElement(sf.compVlctOdr.completeButton);

			sf.seleU.clickElementByJSE(sf.rODComplete.vaidateOrderStatusCheckBox);
			reportStatusPass(methodName + " Clicked on 'Validate Order Status Check Box", true, false);

			// verify Order Cancellation Message text
			verifyFieldValue("Order Cancellation Message", sf.rODComplete.orderCancellationMessageText, sf.omData.taskStatusOrderCancellationMessage);

			if(product.equals("RDI")) {
				sf.seleU.clickElementByJSE(sf.compVlctOdr.completeButton);
			} else {
				sf.seleU.clickElementByJSE(sf.cableTaskItems.completeButton);
			}

			reportStatusPass(methodName + " Clicked on 'Review Order details Page' Complete Button", true, false);

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying order cancellation message", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                   validate the task status if it's frozen and Complete Task In Frozen Status
	 */
	public void validateTaskStatus_ForFrozenOrder() throws IOException {

		try {
			String methodName = "SFDC_Verify field value in review order details@: ";

			// Validation for with out status check box click
			//		sf.seleU.ScrolltoElement(sf.compVlctOdr.completeButton);

			sf.seleU.clickElementByJSE(sf.rODComplete.vaidateOrderStatusCheckBox);
			reportStatusPass(methodName + " Clicked on 'Validate Order Status Check Box", true, false);

			// verify Order Frozen Message text
			verifyFieldValue("Order Frozen Message", sf.rODComplete.orderFrozenMessageText, sf.omData.taskStatusOrderFrozenMessage);

			//			sf.seleU.clickElementByJSE(sf.compVlctOdr.completeButton);
			//			reportStatusPass(methodName + " Clicked on 'Review Order details Page' Complete Button", true, false);

		} catch (Throwable e) {
			reportStatusFail(" Error in completing the task for frozen status", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                    1. RDI, verify mandatory field is not visible once the RDI order is cancelled
	 *                    2. if it's Cable order, validate the cancel message and complete the task
	 */
	public void validateTaskStatus_ForCanceledOrder(String product) throws IOException {

		try {
			String methodName = "SFDC_Verify field value in review order details@: ";
			sf.seleU.switchToDefaultContent();
			sf.seleU.wait(10000);

			if(product.equals("RDI")) {


				//		sf.seleU.switchToFrame(sf.rODComplete.taskFrame.get(1));

				// Validation for with out status check box click
				sf.seleU.wait(8000);

				sf.seleU.clickElementByJSE(sf.rODComplete.vaidateOrderStatusCheckBox);
				reportStatusPass(methodName + " Clicked on 'Validate Order Status Check Box", true, false);

				// verify Order Cancel Message text
				verifyFieldValue("Order Cancel Message", sf.rODComplete.orderCanceledMessageText, sf.omData.taskStatusOrderCanceledMessage);
				sf.seleU.wait(2000);

				// verify if the task is cancelled then mandatory field value should be hidden
				if(!sf.seleU.isElementDisplayed(sf.rODComplete.cLFIValueEnter) || !sf.seleU.isElementDisplayed(sf.rODComplete.textAreaEnter) ||
						!sf.seleU.isElementDisplayed(sf.rODComplete.billingAccountNumberEnter) || !sf.seleU.isElementDisplayed(sf.rODComplete.portTypeInSpecText) ||
						!sf.seleU.isElementDisplayed(sf.rODComplete.oracleAmountInput) || !sf.seleU.isElementDisplayed(sf.rODComplete.ipAssignmentIpload2)
						|| !sf.seleU.isElementDisplayed(sf.rODComplete.portConfig)) {

					reportStatusPass(methodName + " mandatory fields are not visible after validate the task status", true, false);
				} else {
					reportStatusPass(methodName + " mandatory fields are visible after validate the task status with order as cancel status", true, false);
				}
			}
			else { // for cable order				

				sf.seleU.clickElementByJSE(sf.rODComplete.vaidateOrderStatusCheckBox);
				reportStatusPass(methodName + " Clicked on 'Validate Order Status Check Box", true, false);

				verifyFieldValue("Order Cancel Message", sf.cableTaskItems.orderCancelledText, sf.omData.taskStatusOrderCanceledMessage_ForCable);
				sf.seleU.wait(2000);

				sf.seleU.clickElementByJSE(sf.cableTaskItems.completeButton);
			}
			sf.seleU.wait(6000);
			sf.seleU.switchToDefaultContent();

		} catch (Throwable e) {
			reportStatusFail(" Error in completing the task for canceled status", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                    Complete Review Order details Task
	 */
	public void completeReviewOrderDetailsTask(String complete, String order) throws IOException {

		try {
			String methodName = "SFDC_Verify field value in review order details@: ";

			// Validation for with out status check box click
			//		sf.seleU.ScrolltoElement(sf.compVlctOdr.completeButton);

			sf.seleU.clickElementByJSE(sf.rODComplete.vaidateOrderStatusCheckBox);
			reportStatusPass(methodName + " Clicked on 'Validate Order Status Check Box", true, false);


			if(complete.equals("completeTask") && order.equals("RDI")) {
				sf.seleU.clickElementByJSE(sf.compVlctOdr.completeButton);		
				reportStatusPass(methodName + " Clicked on 'Review Order details Page' Complete Button for RDI", true, false);
			} else if(complete.equals("completeTask") && order.equals("cable")) {
				sf.seleU.clickElementByJSE(sf.cableTaskItems.completeButton);
				reportStatusPass(methodName + " Clicked on 'Review Order details Page' Complete Button for cable", true, false);
			} else {
				// verify Order Cancellation Message text
				verifyFieldPresent("Order Message", sf.rODComplete.successMessageText);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in completing the task ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify general information fields
	 */
	public void verifyGeneralInformationField() throws IOException {

		try {
			String methodName = "SFDC_Verify field value in general information@: ";
			sf.seleU.wait(2000);
			// 1. Verify General Information Field

			verifyFieldValue("Order Number", sf.rODComplete.orderDetailsText, sf.dataInput.orderNumber);
			verifyFieldValue("Order State", sf.rODComplete.orderStatusText, sf.dataInput.orderStatusInProgress);
			sf.seleU.wait(1000);
			verifyFieldValue("Order BusinessName", sf.rODComplete.businessNameText,
					sf.dataInput.tempBusinessAccountName);

			verifyFieldValueByFomrat("Order ServiceAddress", sf.rODComplete.serviceAddressText,
					sf.dataInput.serviceAddress);

			verifyFieldValue("Order Delivery Specialist", sf.rODComplete.deliverySpecialistText,
					sf.dataInput.userProfileDelivery);

			//			verifyFieldValue("Order Account Executive", sf.rODComplete.accountExecutiveText,
			//					sf.dataInput.userProfileAe);
			sf.seleU.wait(1000);

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying general Information fields", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify all the field value details
	 * 
	 *                     Enter the billing information
	 * 
	 *                     Click on Complete Button
	 */
	public void createVerifyBilling_Complete_Page() throws IOException {

		try {
			String methodName = "SFDC_Verify field value in Create verify billing account complete page@: ";
			sf.seleU.wait(5000);
			sf.seleU.switchToDefaultContent();
			verifyFieldPresent("Create/Verify Billing Account", sf.rODComplete.billingHeaderText);

			// ***** 1. Verify General Information Field
			// verifyGeneralInformationField();
			sf.seleU.hardwait(2000);

			// *****2. Verify Additional Information Fields
			sf.seleU.ScrolltoElement(sf.rODComplete.orderTypeText);
			verifyFieldPresent("Order Type", sf.rODComplete.orderTypeText);
			verifyFieldPresent("Sales Segment", sf.rODComplete.salesSegmentText);	
			verifyFieldValue("Trade Name", sf.rODComplete.tradeNameText, sf.dataInput.businessAccountLegalName);
			verifyFieldValue("Site Name", sf.rODComplete.siteNameText, sf.dataInput.serviceAccountName);
			verifyFieldPresent("Type Of Business", sf.rODComplete.typeOfBusinessText);		
			verifyFieldValue("Number of Emp", sf.rODComplete.noOfEmpText, sf.dataInput.numberOfEmployees); // chnage it
			sf.seleU.hardwait(1000);

			verifyFieldPresent("Premise Information", sf.rODComplete.premisesInformationText);
			verifyFieldPresent("Demarcation Loc", sf.rODComplete.demarcationLocationText);
			verifyFieldPresent("Hours of Operation",sf.rODComplete.hoursOfOperationValue);
			verifyFieldPresent("Contract Term", sf.rODComplete.contractTermText);
			verifyFieldPresent("Credit Limit Approved Text", sf.rODComplete.creditLimitApprovedValue);
			sf.seleU.wait(1000);

			// 2. Verify Additional Information Field
			// Verify Signing Authority Contact
			verifyFieldValue("Signing Authority Contact Text", sf.rODComplete.signingAuthorityContactNameText.get(0),
					sf.dataInput.primaryContact);
			verifyFieldValue("Signing Authority Contact Email", sf.rODComplete.signingAuthorityEmailText.get(0),
					sf.dataInput.contactEmailAddress);
			verifyFieldValue("Signing Authority Contact Phone", sf.rODComplete.signingAuthorityPhoneText.get(0),
					sf.dataInput.phoneNumber);
			verifyFieldValue("Signing Authority Language Pref", sf.rODComplete.signingAuthorityLanguagePrefText.get(0),
					sf.dataInput.langEnglish);
			sf.seleU.wait(1000);

			// Verify Site Contact
			verifyFieldValue("Site Contact Name Text", sf.rODComplete.siteContactNameText,
					sf.dataInput.siteContact);
			verifyFieldValue("Site Contact Email", sf.rODComplete.siteContactEmailText,
					sf.dataInput.emailIdValue);
			sf.seleU.wait(1000);		
			verifyFieldValue("Site Contact Phone",sf.rODComplete.siteContactPhoneText,
					sf.dataInput.contactPhoneNumber);
			sf.seleU.wait(1000);
			verifyFieldValue("Site Contact Language Pref", sf.rODComplete.siteContactLanguageText,
					sf.dataInput.langEnglish);
			sf.seleU.wait(1000);

			// Validate Sales Agent Information
			verifyFieldValue("Sales Contact Name", sf.rODComplete.salesContactNameText, sf.dataInput.userProfileAe);
			verifyFieldPresent("Sales Contact Email", sf.rODComplete.salesContactEmailText);
			// verifyFieldPresent("Sales Contact Phone",
			// sf.rODComplete.salesContactPhoneValue);

			// Verify Billing Contact
			verifyFieldValue("Billing Contact Name Text", sf.rODComplete.billingContactNameText.get(0),
					sf.dataInput.primaryContact);
			sf.seleU.scrollByCoOrdinates(1);
			verifyFieldValue("Billing Contact Email", sf.rODComplete.billingContactEmailText.get(0),
					sf.dataInput.contactEmailAddress);
			sf.seleU.wait(1000);
			verifyFieldValue("Billing Contact Phone", sf.rODComplete.billingContactPhoneText.get(0),
					sf.dataInput.phoneNumber);
			verifyFieldValue("Billing Contact Language Pref", sf.rODComplete.billingLanguagePrefText.get(0),
					sf.dataInput.langEnglish);

			// Verify Technical Contact
			//			verifyFieldValue("Technical Contact Name Text", sf.rODComplete.technicalContactNameText.get(0),
			//					sf.dataInput.primaryContact);
			//			verifyFieldValue("Technical Contact Email", sf.rODComplete.technicalContactEmailText.get(0),
			//					sf.dataInput.contactEmailAddress);
			//			verifyFieldValue("Technical Contact Phone", sf.rODComplete.technicalContactPhoneText.get(0),
			//					sf.dataInput.phoneNumber);
			//			verifyFieldValue("Technical Contact Language Pref", sf.rODComplete.technicalLanguagePrefText.get(0),
			//					sf.dataInput.langEnglish);
			//			sf.seleU.wait(1000);

			// Verify and enter Billing Information Field
			verifyFieldPresent("Billing Information", sf.rODComplete.billingInformationHeaderText);
			sf.seleU.enterText(sf.rODComplete.billingAddressEnter, sf.dataInput.address);
			reportStatusPass(methodName + "Enter the billing address " + sf.dataInput.address, true, false);
			// Enter years of business
			sf.seleU.enterText(sf.rODComplete.billingAddressEnterYearBusiness, "20");
			reportStatusPass(methodName + "Enter the billing years of business " + "20", true, false);

			//			// Validate error validation if the task is complete without filling mandatory
			//			// fields
			//			sf.seleU.clickElementByJSE(sf.compVlctOdr.completeButton);
			//			reportStatusPass(
			//					methodName + " It should populate error popup as some of the field information are not been filled",
			//					true, false);
			//			sf.seleU.clickElementByJSE(sf.rODComplete.vaidateOrderStatusCheckBoxError);
			//			reportStatusPass(methodName
			//					+ " Clicked on please fix all errors alert ok button as all the mandatory fields is not complete",
			//					true, false);

			sf.seleU.ScrolltoElement(sf.rODComplete.billingAccountNumberEnter);
			sf.seleU.wait(1000);

			// Validation for billing account no it should be 6 digit
			sf.dataInput.oracleErrorCheckNo = "1222334";
			if (countNoOfDigits(Integer.valueOf(sf.dataInput.oracleErrorCheckNo)) > 6){
				sf.seleU.enterText(sf.rODComplete.billingAccountNumberEnter, sf.dataInput.oracleErrorCheckNo);
				sf.seleU.wait(4000);
				sf.seleU.enterText(sf.rODComplete.billingAccountNumberEnter, Keys.TAB);
				sf.seleU.wait(4000);
				reportStatusPass(methodName + "It should throw error as  "
						+ sf.seleU.getTextFromWebElement(sf.rODComplete.accountNoSixDigitErrorText) + "As entered was "
						+ sf.dataInput.oracleErrorCheckNo, true, true);

			}

			// validate if the digit are less then 6
			sf.seleU.wait(2000);
			sf.seleU.clearText(sf.rODComplete.billingAccountNumberEnter);
			sf.seleU.enterText(sf.rODComplete.billingAccountNumberEnter, "144");
			reportStatusPass(methodName + "Entered account no was 144", true, true);
			sf.seleU.wait(2000);
			sf.seleU.enterText(sf.rODComplete.billingAccountNumberEnter, Keys.TAB);
			sf.seleU.wait(4000);
			reportStatusPass(methodName + "It should throw error as  "
					+ sf.seleU.getTextFromWebElement(sf.rODComplete.accountNoSixDigitErrorText) + "As entered was "
					+ "144", true, true);

			// entered 6 digit oracle no
			sf.seleU.clearText(sf.rODComplete.billingAccountNumberEnter);
			sf.seleU.wait(2000);
			sf.seleU.clearAndEnterText(sf.rODComplete.billingAccountNumberEnter, sf.dataInput.oracleNumber);
			reportStatusPass(methodName + "Enter the corrected billing Account Number as " + sf.dataInput.oracleNumber, true, false);
			sf.seleU.wait(1000);

			sf.seleU.clickElementByJSE(sf.rODComplete.sendEmailButton);
			reportStatusPass(methodName + "Clicked On Send Email Button", true, false);

			// Validate Authorized User Information
			sf.seleU.clickElementByJSE(sf.rODComplete.validateStatusCheckBoxClick);
			reportStatusPass(methodName + " Clicked on 'Validate Check Status check box", true, false);
			sf.seleU.wait(8000);

			//			// verify success message text
			//			verifyFieldPresent("success Message Text", sf.rODComplete.successMessageText);

			// Click on the complete button
			sf.seleU.clickElementByJSE(sf.compVlctOdr.monitorCompleteButton);
			reportStatusPass(methodName + " Clicked on 'create/Verify Billing details Page' Complete Button", true,
					false);

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying filed values in create verify billing page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify all the field value details
	 * 
	 *                     Enter the billing information
	 * 
	 *                     Click on Complete Button
	 */
	public void completeCreateVerifyBilling() throws IOException {

		try {
			String methodName = "SFDC_Verify field value in Create verify billing account complete page@: ";
			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();
			verifyFieldPresent("Create/Verify Billing Account", sf.rODComplete.billingHeaderText);
			// Verify and enter Billing Information Field
			verifyFieldPresent("Billing Information", sf.rODComplete.billingInformationHeaderText);
			sf.seleU.enterText(sf.rODComplete.billingAddressEnter, sf.dataInput.address);
			reportStatusPass(methodName + "Enter the billing address " + sf.dataInput.address, true, false);

			sf.seleU.enterText(sf.rODComplete.billingAddressEnterYearBusiness, "20");
			reportStatusPass(methodName + "Enter the billing years of business " + "20", true, false);

			sf.seleU.ScrolltoElement(sf.rODComplete.billingAccountNumberEnter);
			sf.seleU.wait(1000);

			sf.seleU.clearAndEnterText(sf.rODComplete.billingAccountNumberEnter, sf.dataInput.oracleNumber);
			reportStatusPass(methodName + "Enter the billing Number as " + sf.dataInput.oracleNumber, true, false);
			sf.seleU.wait(1000);

			// Validate Authorized User Information
			sf.seleU.clickElementByJSE(sf.rODComplete.validateStatusCheckBoxClick);
			reportStatusPass(methodName + " Clicked on 'Validate Check Status check box", true, false);
			sf.seleU.wait(1000);

			// verify success message text
			verifyFieldPresent("success Message Text", sf.rODComplete.successMessageText);

			// Click on the complete button
			sf.seleU.clickElementByJSE(sf.compVlctOdr.completeButton);
			reportStatusPass(methodName + " Clicked on 'create/Verify Billing details Page' Complete Button", true,
					false);

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying filed values in review order details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify all the field value details in review spec sheet
	 *                     page Click on Complete Button
	 */
	public void verifyReviewSpecSheet_Complete_Page(Hashtable<String, String> dataTable, boolean newIpBlock)
			throws IOException {

		try {
			sf.seleU.wait(2000);

			// ***1. Verify General Information Field
			sf.seleU.switchToDefaultContent();
			// verifyGeneralInformationField();

			//	****2. Verify Access Information Field
			verifyFieldValue("Demarication Location", sf.rODComplete.demarcationLocTextInSpecSheet,
					sf.dataInput.addressState);
			verifyFieldPresent("Access Provider ", sf.rODComplete.accessProviderTextInSpecSheet);
			sf.seleU.wait(1000);
			verifyFieldPresent("Service provider", sf.rODComplete.serviceProviderTextInSpecSheet);
			verifyFieldValue_ByAttribute("HandOff Interface", sf.rODComplete.handOffInterfaceDropDown,"data-value",
					dataTable.get("handOffInterface"));
			verifyFieldPresent("Circuit Type ", sf.rODComplete.circuitTypeTextInSpecSheet);
			verifyFieldValueWithFormat("Access Type", sf.rODComplete.accessTypeTextInSpecSheet,
					sf.dataInput.accessTypeNet.toLowerCase().replace("-", "").replaceAll(" ", ""));
			verifyFieldPresent("Access Type", sf.rODComplete.accessTypeTextInSpecSheet);

			// 3. Service Information****
			verifyFieldValue("Service Speed", sf.rODComplete.serviceSpeedTextInSpec,
					sf.dataInput.quoteInternetUploadSpeed);
			verifyFieldPresent("Service Type", sf.rODComplete.serviceSpeedTextInSpec);
			System.out.println(sf.rODComplete.serviceSpeedTextInSpec.getAttribute("textContent"));
			verifyFieldPresent("Order Type", sf.rODComplete.orderTypeInSpecText);
			sf.seleU.ScrolltoElement(sf.rODComplete.encapsulationTypeDropDown);
			verifyFieldValue_ByAttribute("Encapsulation Type", sf.rODComplete.encapsulationTypeDropDown,"data-value",
					dataTable.get("encapsulationType"));
			verifyFieldPresent_WithAttribute("Port Type ", sf.rODComplete.portTypeInSpecText, "data-value");
			sf.seleU.ScrolltoElement(sf.rODComplete.portSpeedDropDown);
			verifyFieldPresent_WithAttribute("Port Speed ", sf.rODComplete.portSpeedDropDown, "data-value");
			sf.seleU.wait(2000);

			if(!dataTable.get("vLanID").equals("NA")) {
				verifyFieldPresent("VLanID ", sf.rODComplete.vLanIdValueInSpecSheet);
				// use getattribute
			}

			// //verifyFieldPresent("Portable Ip Value", sf.rODComplete.portableIpBoxValue);
			// // // use getattribute

			// Technical Information****
			sf.seleU.wait(1000);
			//		sf.seleU.clickElementByJSE(sf.rODComplete.dnsRadioButtonYesOption);
			/*	if(sf.rODComplete.dnsRadioButtonNoOption.isEnabled()) {
				reportStatusPass(methodName + " No option radio button is selected for DNS required", true, false);
			} else {
				reportStatusPass(methodName + " Yes option radio button is selected for DNS required", true, false);
			} */
			sf.seleU.wait(1000);
			// If it's not dual stack
			if(sf.omData.iPversion.trim().equals("IPv4")){
				verifyFieldValue("Ip Version ",sf.rODComplete.iPVersion, sf.omData.iPversion);
				verifyFieldValue("IpV4 WanBlock ",sf.rODComplete.ipV4WAnBlockSpecSheet, sf.omData.ipV4WAnBlockSpecSheet);
				verifyFieldValue("IpV4 LanBlock ",sf.rODComplete.ipV4LAnBlockSpecSheet, sf.omData.ipV4LAnBlockSpecSheet);
			} else if (sf.omData.iPversion.equals("IPv6")) {
				verifyFieldValue("Ip Version ",sf.rODComplete.iPVersion, sf.omData.iPversion);
				verifyFieldValue("IpV6 WanBlock ",sf.rODComplete.ipV4WAnBlockSpecSheet, sf.omData.ipV6WAnBlockSpecSheet);
				verifyFieldValue("IpV6 LanBlock ",sf.rODComplete.ipV4LAnBlockSpecSheet, sf.omData.ipV6LAnBlockSpecSheet);
			}	else {
				verifyFieldValue("Ip Version ",sf.rODComplete.iPVersion, sf.omData.iPversion);
				verifyFieldValue("IpV4 WanBlock ",sf.rODComplete.ipV4WAnBlockSpecSheet, sf.omData.ipV4WAnBlockSpecSheet);
				verifyFieldValue("IpV4 LanBlock ",sf.rODComplete.ipV4LAnBlockSpecSheet, sf.omData.ipV4LAnBlockSpecSheet);
				verifyFieldValue("IpV6 WanBlock ",sf.rODComplete.ipV6WAnBlockSpecSheet, sf.omData.ipV6WAnBlockSpecSheet);
				verifyFieldValue("IpV6 LanBlock ",sf.rODComplete.ipV6LAnBlockSpecSheet, sf.omData.ipV6LAnBlockSpecSheet);
			}
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.rODComplete.validateStatusCheckBoxClick);
			reportStatusPass(methodName + " Clicked on 'Validate Check Status check box", true, false);
			sf.seleU.wait(6000);

			// verify success message text
			//verifyFieldPresent("success Message Text", sf.rODComplete.successMessageText);

			// Click on the complete button
			sf.seleU.clickElementByJSE(sf.compVlctOdr.monitorCompleteButton);
			reportStatusPass(methodName + " Clicked on 'Review Spec Sheet details Page' Complete Button", true, false);
			sf.seleU.wait(6000);

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying filed values in review spect sheet page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify all the field value details
	 * 
	 *                     Enter the billing information
	 * 
	 *                     Click on Complete Button
	 */
	public void verifyCompleteCircutDesign_IPAssignment_Complete_Page(Hashtable<String, String> dataTable) throws IOException {

		try {
			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();

			// ***1. Verify General Information Field
			// verifyGeneralInformationField();
			sf.seleU.wait(1000);

			//***verify Product Information Field*******
			// Service Details:
			verifyFieldPresent("Service Details Text", sf.rODComplete.serviceDetailsHeaderText);
			verifyFieldValue("Product name ", sf.rODComplete.productDetailsNameText, dataTable.get("Dedicated_Internet_Product"));
			verifyFieldValue("Service Provider ", sf.rODComplete.serviceProviderValue, "Rogers");
			verifyFieldValue("Demarcation Loc ", sf.rODComplete.demarcationLocationText, sf.dataInput.addressState);
			verifyFieldValue("Speed ", sf.rODComplete.productSpeedValue.get(0),
					sf.dataInput.quoteInternetUploadSpeed);
			verifyFieldValue("Encapsulation Type ", sf.rODComplete.encapsulationTypeValue,dataTable.get("encapsulationType"));

			// Circuit Details:
			sf.seleU.ScrolltoElement(sf.rODComplete.circuitDetailsHeaderText);
			verifyFieldPresent("Circuit Details Text", sf.rODComplete.circuitDetailsHeaderText);
			verifyFieldPresent("Circuit Prod Name", sf.rODComplete.cirDetailsNameText);	
			verifyFieldPresent("Access Speed", sf.rODComplete.accessSpeed);
			verifyFieldValue("HandOff ", sf.rODComplete.handOff, dataTable.get("handOffInterface"));
			verifyFieldValueWithFormat("Access type ", sf.rODComplete.accessTypeValue, sf.dataInput.accessTypeNet);
			verifyFieldValue("Access Provider", sf.rODComplete.accessProviderValue, "Rogers");

			// Equipment Detail
			sf.seleU.ScrolltoElement(sf.rODComplete.equipmentDetailsHeaderText);
			verifyFieldPresent("Equipment Details Text", sf.rODComplete.equipmentDetailsHeaderText);
			verifyFieldPresent("Equipment", sf.rODComplete.equipment);
			verifyFieldPresent("Port No", sf.rODComplete.portNoVAlue);
			verifyFieldPresent("Port Speed", sf.rODComplete.portSpeed);
			verifyFieldPresent("Port Type", sf.rODComplete.portTypeValue);

			// Rpads details
			verifyFieldPresent("PHUB Value in complete circuit design ", sf.rODComplete.pHUBValueInCircuit);
			sf.omData.pHUBValue = sf.seleU.getTextFromWebElement(sf.rODComplete.pHUBValueInCircuit);
			sf.seleU.wait(2000);
			verifyFieldPresent("Project Owner ", sf.rODComplete.projectOwnerInCompleCircuit);
			sf.seleU.wait(2000);
			verifyFieldPresent("Project Number In Complete Circuit Design ", sf.rODComplete.projectNumberInCompleCircuit);
			sf.seleU.wait(2000);
			sf.omData.projectNumber = sf.seleU.getTextFromWebElement(sf.rODComplete.projectNumberInCompleCircuit);		
			sf.seleU.wait(2000);

			// verify VLAn Information
			// sf.seleU.ScrolltoElement(sf.rODComplete.vLanIdInCircuitValue);
			if(!dataTable.get("vLanID").equals("NA")){
				verifyFieldPresent("VLanID ", sf.rODComplete.vLanIdInCircuitValue);
			}

			// IP Address Assignment
			if (sf.seleU.isElementPresent(sf.rODComplete.ipVersionValue)) {
				sf.seleU.ScrolltoElement(sf.rODComplete.ipVersionValue);
				sf.seleU.wait(1000);
				if(sf.omData.iPversion.equals("IPv4")){				
					verifyFieldValue("Ip Version ",sf.rODComplete.ipVersionValue, sf.omData.iPversion);
					verifyFieldValue("IpV4 WanBlock ",sf.rODComplete.iPV4WanValue, sf.omData.ipV4WAnBlockSpecSheet);
					verifyFieldValue("IpV4 LanBlock ",sf.rODComplete.iPV4LanValue, sf.omData.ipV4LAnBlockSpecSheet);
				} else if (sf.omData.iPversion.equals("IPv6")) {
					verifyFieldValue("Ip Version ",sf.rODComplete.ipVersionValue, sf.omData.iPversion);
					verifyFieldValue("IpV6 WanBlock ",sf.rODComplete.iPV6WanValue, sf.omData.ipV6WAnBlockSpecSheet);
					verifyFieldValue("IpV6 LanBlock ",sf.rODComplete.iPV6WanValue, sf.omData.ipV6LAnBlockSpecSheet);
				}	else {
					verifyFieldValue("Ip Version ",sf.rODComplete.ipVersionValue, sf.omData.iPversion);
					verifyFieldValue("IpV4 WanBlock ",sf.rODComplete.iPV4WanValue, sf.omData.ipV4WAnBlockSpecSheet);
					verifyFieldValue("IpV4 LanBlock ",sf.rODComplete.iPV4LanValue, sf.omData.ipV4LAnBlockSpecSheet);
					sf.seleU.wait(4000);
					verifyFieldValue("IpV6 WanBlock ",sf.rODComplete.iPV6WanValue, sf.omData.ipV6WAnBlockSpecSheet);
					sf.seleU.wait(4000);
					verifyFieldValue("IpV6 LanBlock ",sf.rODComplete.iPV6LanValue, sf.omData.ipV6LAnBlockSpecSheet);
				}
			}

			/*if(!sf.omData.existingPortableIP.equals("NA")) {
				String existingPortableIp = omData.existingPortableIP.trim().replaceAll("[^a-zA-Z0-9]", "");
				System.out.println(existingPortableIp);
				String portableIP = sf.seleU.getTextFromWebElement(sf.rODComplete.existingPortableIP).replaceAll("[^a-zA-Z0-9]", "");
				if(portableIP.equals(existingPortableIp)) {
					reportStatusPass(methodName + "matched the portable ip values as expected " + omData.existingPortableIP, true, true);	
				}

			}*/
			sf.seleU.wait(1000);
			//			if (sf.seleU.isElementPresent(sf.rODComplete.iPVersion)) {
			//				sf.seleU.ScrolltoElement(sf.rODComplete.iPVersion);
			//				verifyFieldValue("IP Version", sf.rODComplete.iPVersion, sf.omData.iPversion);
			//				verifyFieldValue("IPv4 WAN Block Assignment ", sf.rODComplete.iPV4Wan,
			//						sf.omData.ipV4WAnBlockSpecSheet);
			//				verifyFieldValue("IPv4 LAN Block Assignment ", sf.rODComplete.iPV4LanValue,
			//						sf.omData.ipV4LAnBlockSpecSheet);
			//			}

			// verify Circuit Information Field
			sf.seleU.ScrolltoElement(sf.rODComplete.circuitInformationHeaderText);
			verifyFieldPresent("Circuit Information Header Text ", sf.rODComplete.circuitInformationHeaderText);

			// If the CLFI value has less then 8 digits
			sf.dataInput.cLFIValueErrorCheckNoWithLessDigits = "123223";
			if (countNoOfCharacters(sf.dataInput.cLFIValueErrorCheckNoWithLessDigits) < 8) {
				sf.seleU.clearText(sf.rODComplete.cLFIValueEnter);
				sf.seleU.wait(4000);
				sf.seleU.clearText(sf.rODComplete.cLFIValueEnter);
				sf.seleU.enterText(sf.rODComplete.cLFIValueEnter, sf.dataInput.cLFIValueErrorCheckNoWithLessDigits);
				sf.seleU.wait(4000);
				sf.seleU.enterText(sf.rODComplete.cLFIValueEnter, Keys.TAB);
				sf.seleU.wait(1000);
				reportStatusPass(methodName + "It will throw error for CLFI value as  "
						+ sf.seleU.getTextFromWebElement(sf.rODComplete.cLFIValueEnterErrorValidation) + " instead of "
						+ sf.dataInput.cLFIValueErrorCheckNoWithLessDigits, true, true);

				// If it's more then 8 digit and includes special characters
				if (countNoOfCharacters("123456789%!") > 8) {
					sf.seleU.clearText(sf.rODComplete.cLFIValueEnter);
					sf.seleU.clearAndEnterText(sf.rODComplete.cLFIValueEnter, "123456789%!");
					sf.seleU.wait(1000);
					reportStatusPass(methodName
							+ "It will throw error for CLFI values as if it includes special characters as  "
							+ sf.seleU.getTextFromWebElement(sf.rODComplete.cLFIValueEnterErrorValidation)
							+ " instead of " + "123456789%!" + " with count as " + countNoOfCharacters("123456789%!"),
							true, true);
				}

				// If the CLFI value has more then 22 digits only
				if (countNoOfCharacters(sf.dataInput.cLFIValueErrorCheckNoWithMoreDigits) > 22) {
					sf.seleU.clearText(sf.rODComplete.cLFIValueEnter);
					sf.seleU.clearAndEnterText(sf.rODComplete.cLFIValueEnter,
							sf.dataInput.cLFIValueErrorCheckNoWithMoreDigits);
					sf.seleU.wait(1000);
					reportStatusPass(methodName + "It should throw error for CLFI value as  "
							+ sf.seleU.getTextFromWebElement(sf.rODComplete.cLFIValueEnterErrorValidation)
							+ " instead of " + sf.dataInput.cLFIValueErrorCheckNoWithMoreDigits + " with count as "
							+ countNoOfCharacters(sf.dataInput.cLFIValueErrorCheckNoWithMoreDigits), true, true);
				}
			} else {
				reportStatusFail(" Error in verifying CLFI field values for Complete Circuit design", true);
			}
			sf.seleU.wait(3000);

			// Enter the corrected CLFI value:
			sf.seleU.clearText(sf.rODComplete.cLFIValueEnter);
			sf.seleU.clearAndEnterText(sf.rODComplete.cLFIValueEnter, sf.dataInput.cLFIValue);
			reportStatusPass(methodName + "Entered the Correct CLFI values as " + sf.dataInput.cLFIValue, true, false);

			sf.seleU.wait(1000);
			sf.seleU.clearText(sf.rODComplete.textAreaEnter);
			sf.seleU.enterText(sf.rODComplete.textAreaEnter, sf.dataInput.opportunityAdditionalInfo);
			reportStatusPass(methodName + "Enter the evc1 notes as as " + sf.dataInput.opportunityAdditionalInfo, true,
					false);
			sf.seleU.wait(1000);

			// Verify evc id value
			if (countNoOfCharacters(sf.dataInput.cLFIValueErrorCheckNoWithLessDigits) < 22) {
				sf.seleU.clearText(sf.rODComplete.evcIdEnter);
				sf.seleU.enterText(sf.rODComplete.evcIdEnter, sf.dataInput.cLFIValueErrorCheckNoWithLessDigits);

				//				reportStatusPass(methodName + "It should throw error for EVC ID value as  "
				//						+ sf.seleU.getTextFromWebElement(sf.rODComplete.evcIdEnterErrorValidationMinLen)
				//						+ " instead of " + sf.dataInput.cLFIValueErrorCheckNoWithLessDigits + " with count as "
				//						+ countNoOfCharacters(sf.dataInput.cLFIValueErrorCheckNoWithLessDigits), true, true);

				// If the EVCID value has more then 22 digits only
				//				if (countNoOfCharacters(sf.dataInput.cLFIValueErrorCheckNoWithMoreDigits) > 22) {
				//					sf.seleU.clearAndEnterText(sf.rODComplete.evcIdEnter,
				//							sf.dataInput.cLFIValueErrorCheckNoWithMoreDigits);
				//					sf.seleU.wait(1000);
				//					reportStatusPass(methodName + "It should throw error for EVC ID value as  "
				//							+ sf.seleU.getTextFromWebElement(sf.rODComplete.evcIdEnterErrorValidationMaxValue)
				//							+ "instead of " + sf.dataInput.cLFIValueErrorCheckNoWithMoreDigits + " with count as "
				//							+ countNoOfCharacters(sf.dataInput.cLFIValueErrorCheckNoWithMoreDigits), true, true);
				//
				//				}
			}
			// Enter the corrected Evc ID:
			sf.seleU.wait(6000);
			sf.seleU.enterText(sf.rODComplete.evcIdEnter, Keys.TAB);
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.rODComplete.textAreaEnter);
			sf.seleU.wait(6000);
			sf.seleU.ScrolltoElement(sf.rODComplete.evcIdEnter);
			sf.seleU.clearText(sf.rODComplete.evcIdEnter);
			sf.seleU.wait(4000);
			sf.rODComplete.evcIdEnter.clear();
			sf.seleU.wait(4000);
			sf.seleU.enterText(sf.rODComplete.evcIdEnter, sf.dataInput.evcID);
			reportStatusPass(methodName + "Entered the evc id as " + sf.dataInput.evcID, true, false);
			sf.seleU.wait(4000);

			// ENter Cusotmer Port ID
			sf.seleU.clearText(sf.rODComplete.customerPortIdEnter);
			sf.seleU.enterText(sf.rODComplete.customerPortIdEnter, sf.dataInput.customerPortNo);
			reportStatusPass(methodName + "Enter the cusotmer port id as " + sf.dataInput.customerPortNo, true, false);
			sf.seleU.wait(1000);

			// Enter device ID
			sf.seleU.clearText(sf.rODComplete.deviceIdEnter);
			sf.seleU.enterText(sf.rODComplete.deviceIdEnter, sf.dataInput.deviceID);
			reportStatusPass(methodName + "Enter the device id as " + sf.dataInput.deviceID, true, false);
			sf.seleU.wait(4000);

			sf.seleU.clearText(sf.rODComplete.accessCirCidEnter);
			sf.seleU.enterText(sf.rODComplete.accessCirCidEnter, sf.dataInput.oracleNumber);
			reportStatusPass(methodName + "Enter the accessCir id as " + sf.dataInput.oracleNumber, true, false);
			sf.seleU.wait(1000);
			sf.seleU.enterText(sf.rODComplete.accessCirCidEnter, Keys.TAB);

			// Validate error validation text for attach section and verify if able to
			// complete the task with out it
			sf.seleU.hardwait(4000);
			sf.seleU.wait(2000);
			if (sf.seleU.isElementDisplayedWithYellowHighlight(sf.rODComplete.uploadErrorValidationText.get(0))) {
				sf.seleU.ScrolltoElement(sf.rODComplete.uploadErrorValidationText.get(0));
				reportStatusPass(
						methodName + "Error validation text is present for upload with value as "
								+ sf.seleU.getTextFromWebElement(sf.rODComplete.uploadErrorValidationText.get(0)),
								true, false);

				//				// Click on the complete button
				//				sf.seleU.clickElementByJSE(sf.compVlctOdr.completeButton);
				//				reportStatusPass(
				//						methodName + " Clicked on Complete Button for Complete Circuite Design with out uploading file",
				//						true, false);
				//
				//				sf.seleU.clickElementByJSE(sf.rODComplete.vaidateOrderStatusCheckBoxError);
				//				reportStatusPass(methodName + " Clicked on please fix all errors alert ok button", true, false);
				sf.seleU.ScrolltoElement(sf.rODComplete.clrupload);
				sf.seleU.wait(6000);
				sf.seleU.ScrolltoElement(sf.rODComplete.circuitInformationHeaderText);
				sf.seleU.wait(6000);
				sf.seleU.moveToThenSlowClickElement(sf.rODComplete.clrupload);
				sf.seleU.hardwait(5000);
				upload_File();
				sf.seleU.hardwait(2000);
			} else {
				reportStatusFail(" Upload Errortext is not visible for complete circuit", true);
			}

			// complete IP assignment task is removed from orchestration plan and merged with complete circuit design task
			// IP assignment upload block should be present in complete circuit design task only when new IP block is 
			// selected from review spec sheet or community spec sheet

			sf.dataInput.completeIpAssignment = true;
			if (sf.dataInput.completeIpAssignment == true) {
				sf.seleU.hardwait(4000);
				verifyFieldPresent("IP Assignment Information header is present", sf.rODComplete.ipAssignmentInformationText);	
				sf.seleU.hardwait(2000);
				if (sf.seleU.isElementDisplayedWithYellowHighlight(sf.rODComplete.uploadErrorValidationText.get(0))) {
					sf.seleU.ScrolltoElement(sf.rODComplete.ipAddressAssignmentAttachment);
					sf.seleU.wait(4000);
					sf.seleU.ScrolltoElement(sf.rODComplete.circuitInformationHeaderText);
					sf.seleU.wait(4000);
					sf.seleU.wait(2000);
					sf.seleU.moveToThenSlowClickElement(sf.rODComplete.ipAddressAssignmentAttachment);
					sf.seleU.hardwait(5000);
					upload_File();
					sf.seleU.hardwait(2000);

				} else {
					reportStatusFail(" Upload Errort ext is not visible for IP assignment", true);
				}

			} else {
				reportStatusPass(" IP Assignment Header is not present", true, false);
			}

			// Click on the save button
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.rODComplete.saveButton);
			reportStatusPass(methodName + " Clicked on save button", true,false);
			sf.seleU.wait(8000);
			verifyFieldPresent("Task is updated text", sf.rODComplete.taskUpdatedText);	

			//			 Verify saved fields
			//			 1. close the task and open the task again from the plan
			closeAndOpenCompleteCircuitTaskAfterSave();
			//			sf.seleU.switchWindow(3);
			//			openFromOrchestrationPlan("open the task", sf.orchPlan.orchesItemPlanCompleteCircuitDesignTask, sf.orchPlan.orchesItemPlanCompleteCircuitDesignClick);
			sf.seleU.wait(6000);
			sf.seleU.ScrolltoElement(sf.rODComplete.customerPortIdEnter);
			verifyFieldValue("Cusotmer Port ID after saved ", sf.rODComplete.customerPortIdEnter, sf.dataInput.customerPortNo);
			verifyFieldValue("EVC ID after saved", sf.rODComplete.evcIdEnter, sf.dataInput.evcID);
			verifyFieldValue("Device ID after saved", sf.rODComplete.deviceIdEnter, sf.dataInput.deviceID);
			verifyFieldValue("CIRC ID after saved", sf.rODComplete.accessCirCidEnter, sf.dataInput.oracleNumber);
			verifyFieldValue("CLL ID after saved", sf.rODComplete.cLFIValueEnter, sf.dataInput.cLFIValue);
			verifyFieldValue("Enter Notes after saved", sf.rODComplete.textAreaEnter, "QA Automation Test");
			verifyFieldPresent("CLR Attachement Attached after saving ", sf.rODComplete.clRResultsAttachmentValue);

			//			if (!sf.seleU.isElementDisplayedWithYellowHighlight(sf.rODComplete.uploadErrorValidationText.get(0))) {
			//				reportStatusPass(" File is successfully uploaded and error is message is not visible as expected ", true, false);
			//			}

			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.rODComplete.validateStatusCheckBoxClick);
			reportStatusPass(methodName + " Clicked on 'Validate Check Status check box", true, false);
			sf.seleU.wait(4000);

			// verify success message text
			verifyFieldPresent("success Message Text", sf.rODComplete.successMessageText);
			sf.seleU.wait(10000);

			// Click on the complete button
			sf.seleU.clickElementByJSE(sf.compVlctOdr.monitorCompleteButton);
			reportStatusPass(methodName + " Clicked on 'complete circuit design and ip assignment' Complete Button", true,
					false);
			sf.seleU.wait(20000);

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying filed values in complete circuit design page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify all the field value details in send order to P&I
	 *                     Click on Complete Button
	 */
	public void verifySendOrderToPI_Complete_Page(Hashtable<String, String> dataTable) throws IOException {

		try {
			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();

			// ***1. Verify General Information Field
			// verifyGeneralInformationField();

			// ****2. Verify Additional Information Field
			verifyFieldValue("Site Contact Name", sf.rODComplete.siteContactNameInCompleteAccessText, sf.dataInput.siteContact);
			verifyFieldValue("Site Contact Email Address", sf.rODComplete.siteContactEmailInCompleteAccessText,
					sf.dataInput.emailIdValue);
			verifyFieldValue("Site Contact lang pref", sf.rODComplete.siteContactLanguageText,
					sf.dataInput.langEnglish);
			verifyFieldValue("Site Contact Phone",sf.rODComplete.siteContactPhoneText,
					sf.dataInput.contactPhoneNumber);

			// **3. Product Information
			verifyFieldValue("Demarication Location", sf.rODComplete.demarcationLocationText,
					sf.dataInput.addressState);
			//		verifyFieldValue("Service Type", sf.rODComplete.serviceTypeProductDetails, sf.dataInput.quoteProductName);
			sf.seleU.wait(1000);

			verifyFieldValueWithFormat("Access Type", sf.rODComplete.accessTypeValue, sf.dataInput.accessTypeNet);
			verifyFieldValue("Product Speed", sf.rODComplete.productSpeedValue.get(0),
					sf.dataInput.quoteInternetUploadSpeed);
			verifyFieldPresent("Access Speed ", sf.rODComplete.accessSpeed);
			sf.seleU.hardwait(1000);

			// ****** 3. Validate Product Information
			// Service Details
			sf.seleU.hardwait(2000);
			sf.seleU.ScrolltoElement(sf.rODComplete.productInformationHeaderText);
			verifyFieldPresent("Product Information", sf.rODComplete.productInformationHeaderText);
			verifyFieldValue("Product Name", sf.rODComplete.productDetailsNameText, sf.dataInput.quoteProductName);
			verifyFieldValue("Product Speed", sf.rODComplete.productSpeedValue.get(0),
					sf.dataInput.quoteInternetUploadSpeed);	
			verifyFieldValue("Encapsulation type", sf.rODComplete.encapsulationTypeValue, dataTable.get("encapsulationType"));
			verifyFieldValue("Demarcation Location", sf.rODComplete.demarcationLocationTexts.get(0), sf.dataInput.addressState);
			verifyFieldPresent("Service provider", sf.rODComplete.serviceProviderValue);

			// Circuit Details:
			sf.seleU.ScrolltoElement(sf.rODComplete.circuitDetailsHeaderText);
			verifyFieldPresent("Circuit Details Text Header", sf.rODComplete.circuitDetailsHeaderText);
			sf.seleU.ScrolltoElement(sf.rODComplete.cirDetailsNameText);
			verifyFieldPresent("Circuit Prod Name", sf.rODComplete.cirDetailsNameText);	
			verifyFieldValueWithFormat("Access Type", sf.rODComplete.accessTypeValue, sf.dataInput.accessTypeNet);
			verifyFieldPresent("Access Speed", sf.rODComplete.productSpeedValue.get(1));
			verifyFieldPresent("Access Provider", sf.rODComplete.accessProviderValue);
			verifyFieldValue("hand off", sf.rODComplete.handOff, dataTable.get("handOffInterface"));
			sf.seleU.wait(2000);

			// Enter Oracle Amount field values
			sf.seleU.enterText(sf.rODComplete.oracleAmountInput, sf.dataInput.opportunityAmount);
			reportStatusPass(methodName + "Entered the Opp Amount as " + sf.dataInput.opportunityAmount, true, false);

			// Click on the complete button to verify mandatory field
			//			sf.seleU.clickElementByJSE(sf.compVlctOdr.completeButton);
			//			reportStatusPass(
			//					methodName + " Clicked on Complete Button for Complete Circuite Design with out uploading file",
			//					true, false);
			//			sf.seleU.clickElementByJSE(sf.rODComplete.vaidateOrderStatusCheckBoxError);
			//			reportStatusPass(methodName + " Clicked on please fix all errors alert ok button", true, false);

			// Enter the oracle number
			sf.seleU.enterText(sf.rODComplete.oracleNumberInput, sf.dataInput.oracleNumber);
			reportStatusPass(methodName + "Enter the Oracle Number as " + sf.dataInput.oracleNumber, true, false);
			sf.seleU.wait(1000);		
			sf.seleU.enterText(sf.rODComplete.oracleNumberInput, Keys.TAB);
			sf.seleU.wait(1000);

			sf.seleU.clickElementByJSE(sf.rODComplete.validateStatusCheckBoxClick);
			reportStatusPass(methodName + " Clicked on 'Validate Check Status check box", true, false);
			sf.seleU.wait(1000);

			// verify success message text
			verifyFieldPresent("success Message Text", sf.rODComplete.successMessageText);
			sf.seleU.wait(2000);

			// Validate send email button
			verifyFieldPresent("Click on Send Email button before complete", sf.rODComplete.validateSendButtonCompleteMsg);
			sf.seleU.clickElementByJSE(sf.rODComplete.sendEmailButton);
			reportStatusPass(methodName + " Clicked on 'Send Email Button", true, false);
			sf.seleU.wait(6000);

			// Click on the complete button
			sf.seleU.clickElementByJSE(sf.compVlctOdr.sendOrderToPICompleteButton);
			reportStatusPass(methodName + " Clicked on 'send order to P&I page' Complete Button", true, false);
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying filed values in send order to P&I  page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify all the field value details
	 * 
	 * 
	 * 
	 *                     Click on Complete Button
	 */
	public void verifyCapturePHUB_Complete_Page(Hashtable<String, String> dataTable) throws IOException {

		try {
			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();

			// ***1. Verify General Information Field
			// verifyGeneralInformationField();

			// Enter PHUB Information field values
			sf.seleU.enterText(sf.rODComplete.pHUBInput, sf.dataInput.oracleNumber);
			reportStatusPass(methodName + "Entered the Phub Input as " + sf.dataInput.oracleNumber, true, false);

			sf.seleU.clickElementByJSE(sf.rODComplete.validateStatusCheckBoxClick);
			reportStatusPass(methodName + " Clicked on 'Validate Check Status check box", true, false);
			sf.seleU.wait(3000);

			// verify success message text
			verifyFieldPresent("success Message Text", sf.rODComplete.successMessageText);

			// Click on the complete button
			sf.seleU.clickElementByJSE(sf.compVlctOdr.completeButton);
			reportStatusPass(methodName + " Clicked on 'create/Verify Billing details Page' Complete Button", true,
					false);

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying filed values in capture phub data", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify all the field value details in monitor rpats fibre
	 *                     build complete page
	 * 
	 *                     Click on Complete Button
	 */
	public void verifyMonitorRpats_Build_Complete_Page(Hashtable<String, String> dataTable) throws IOException {

		try {
			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();

			// ***1. Verify General Information Field
			// verifyGeneralInformationField();
			sf.seleU.wait(1000);

			// verify Project Information Field
			verifyFieldPresent("Project Number", sf.rODComplete.projectNumber);
			sf.seleU.ScrolltoElement(sf.rODComplete.projectNumber);
			verifyFieldValue("Project Number value is same as circuit design task ", 
					sf.rODComplete.projectNumber, sf.omData.projectNumber);
			verifyFieldPresent("Project Owner", sf.rODComplete.projectOwner);
			verifyFieldPresent("Project Type", sf.rODComplete.projectType);
			verifyFieldPresent("Project Manager", sf.rODComplete.projectManager);
			verifyFieldPresent("Associated Project", sf.rODComplete.associatedProject);
			sf.seleU.wait(1000);

			// Verify Monitor Information field

			sf.seleU.ScrolltoElement(sf.rODComplete.commitedServiceDate);
			verifyFieldPresent("Service Date ", sf.rODComplete.commitedServiceDate);
			verifyFieldPresent("Group", sf.rODComplete.group);
			verifyFieldPresent("cretaed Date ", sf.rODComplete.createdDate);
			verifyFieldPresent("start Date ", sf.rODComplete.startDate);
			verifyFieldPresent("Optics Activate Completion Date ", sf.rODComplete.opticeActivateCompletionDate);
			sf.seleU.ScrolltoElement(sf.rODComplete.designCompletedDate);
			verifyFieldPresent("Survey Completed Date ", sf.rODComplete.surveyCompletedDate);
			verifyFieldPresent("Design Completed Date ", sf.rODComplete.designCompletedDate);
			verifyFieldPresent("Optics Activated Date ", sf.rODComplete.opticeActivatedDate);
			verifyFieldPresent("Project Issued To NCC", sf.rODComplete.projectIssueDate);
			sf.seleU.wait(1000);

			sf.seleU.ScrolltoElement(sf.rODComplete.status);
			verifyFieldPresent("Rpats current completion date ", sf.rODComplete.rpatsCurrentCompletionDate);
			verifyFieldPresent("Status", sf.rODComplete.status);
			verifyFieldPresent("PHUB Value ", sf.rODComplete.phub);
			verifyFieldValue("PHUB Value is same as complete circuit design task ", sf.rODComplete.phub, sf.omData.pHUBValue);
			verifyFieldPresent("Splice Assign Req Date ", sf.rODComplete.spliceAssignReqDate);
			//		verifyFieldPresent("Splice Assigned Date ", sf.rODComplete.assignedDate);		
			verifyFieldPresent("EE to SS date ", sf.rODComplete.eeToSSDate);
			sf.seleU.wait(1000);

			sf.seleU.ScrolltoElement(sf.rODComplete.planningCommentsText);
			verifyFieldPresent("Planning Comments Text ", sf.rODComplete.planningCommentsText);
			verifyFieldPresent("Construction Comments Text ", sf.rODComplete.constructionCommentsText);

			sf.seleU.ScrolltoElement(sf.rODComplete.sendFocLetterButton);
			sf.seleU.scrollUpByCoOrdinates();
			if (sf.seleU.isElementDisplayed(sf.rODComplete.sendFocLetterButton)) {
				// verify complete button is visible or not if foc letter is not sent until
				if (!sf.seleU.isElementDisplayed(sf.compVlctOdr.monitorCompleteButton)) {
					reportStatusPass(methodName + " complete button is not visible as foc letter is not sent", true,
							true);
					// Click on FOC letter button
					sf.seleU.clickElementByJSE(sf.rODComplete.sendFocLetterButton);
					reportStatusPass(methodName + "Clicked on Send FOC letter", true, false);
					sf.seleU.wait(1000);
				}

			}

			//			sf.seleU.clickElementByJSE(sf.rODComplete.sendFocLetterButton);
			//			reportStatusPass(methodName + "Clicked on Send FOC letter", true, false);

			sf.seleU.clickElementByJSE(sf.rODComplete.validateStatusCheckBoxClick);
			reportStatusPass(methodName + " Clicked on 'Validate Check Status check box", true, false);
			sf.seleU.wait(1000);

			// verify success message text
			verifyFieldPresent("success Message Text", sf.rODComplete.successMessageText);
			sf.seleU.wait(6000);
			// Click on the complete button
			sf.seleU.clickElementByJSE(sf.compVlctOdr.monitorCompleteButton);
			reportStatusPass(methodName + " Clicked on 'Monitor Rpads Fibre Build details Page' Complete Button", true,
					false);
			sf.seleU.wait(4000);
		} catch (Throwable e) {
			reportStatusFail(" Error in verifying filed values in monitor rpats fibre build page", e);
			e.printStackTrace();
		}
	}

	//Verify all fields for Closure Letter

	public void verifyFieldValuesInEmail_ClosureLetter(Hashtable<String, String> dataTable) throws IOException {

		try {
			sf.seleU.wait(10000);

			driver.switchTo().frame("emailuiFrame");
			String methodName = "SFDC_Verify field value in Closure Letter@: "; int k=  0; WebElement servicesToBeInstalled;
			/*sf.dataInput.sf.omData.iNServivceDate = " 2013-01-11"; 
			sf.dataInput.quoteInternetUploadSpeed = "50 Mbps";
			sf.dataInput.accessspeed = "100 Mbps";
			sf.dataInput.port = "7";
			sf.dataInput.evcID = "4643546457657657654365";
			sf.dataInput.quoteInternetUploadSpeed = "50 Mbps";
			sf.dataInput.serviceAddress = "37 MOWREY LANE, NASONWORTH, NB, E3B9G2";
			sf.dataInput.addressState = "ON";
			sf.dataInput.primaryContact = "bh nasn";
			sf.omData.siteContactName = "ABQAAuto200923105646";
			sf.dataInput.userProfileDelivery = "Manthan Jadhav Del";*/

			sf.seleU.wait(5000);

			//0. Verify closure letter first message text
			sf.seleU.ScrolltoElement(sf.rODComplete.activationconfirmmsg);
			verifyFieldValueByFomrat(" Your activation has been successfully confirmed.", sf.rODComplete.activationconfirmmsg, sf.omData.RDIClosureLetter_Message1);

			//Verify Thank you message
			verifyFieldValueByFomrat("Thank you message", sf.rODComplete.thankyou, sf.omData.RDIClosureLetter_Message2);

			//1. Verify greetings message 1

			WebElement installation_FirstMsg1 = driver.findElement(By.xpath("//span[contains(text(),'Hello')]"));
			sf.seleU.wait(2000);
			sf.seleU.ScrolltoElement(installation_FirstMsg1);
			verifyFieldPresent("Hello Message with Primary Contact ", installation_FirstMsg1);

			if(installation_FirstMsg1.getText().trim().contains(sf.dataInput.primaryContact) ) {

				reportStatusPass(sf.dataInput.primaryContact + " Signing Authority is present in the message " , true, true);
			}

			//2 Verify Greetings message 2

			WebElement installation_FirstMsg2 = driver.findElement(By.xpath("//span[contains(text(),'Please read')]"));
			sf.seleU.wait(2000);
			sf.seleU.ScrolltoElement(installation_FirstMsg2);
			verifyFieldPresent("Hello Message with Site contact ", installation_FirstMsg2);

			if(installation_FirstMsg1.getText().trim().contains(sf.dataInput.siteContact) ) {

				reportStatusPass(sf.dataInput.siteContact + "Site Contact is present in the message " , true, true);
			}

			//3. Verify Order Details on the letter
			verifyFieldPresent("Order Details box 1 ", sf.rODComplete.servicesinstalledbox);

			verifyFieldValueByFomrat("Equipment and plan ", sf.rODComplete.servicesinstalledbox,
					sf.dataInput.quoteInternetUploadSpeed);

			if(sf.rODComplete.servicesinstalledbox.getText().trim().contains(sf.dataInput.serviceAddress)&& 
					sf.rODComplete.servicesinstalledbox.getText().trim().contains(sf.dataInput.port)) {
				reportStatusPass(sf.dataInput.serviceAddress + " service address is present in the message " +
						" port number is present as "+ sf.dataInput.port    , true, true);
			}

			if(sf.rODComplete.servicesinstalledbox.getText().trim().contains(sf.dataInput.accessspeed) && 
					sf.rODComplete.servicesinstalledbox.getText().trim().contains(sf.dataInput.quoteInternetUploadSpeed) && 
					sf.rODComplete.servicesinstalledbox.getText().trim().contains(sf.dataInput.addressState)) {
				reportStatusPass(sf.dataInput.accessspeed + " Access Speed is present in the message " +
						"And Service Speed is also present " + sf.dataInput.quoteInternetUploadSpeed + " Demarcation Location is present as "+ sf.dataInput.addressState, true, true);
			}

			if(sf.rODComplete.servicesinstalledbox.getText().trim().contains(sf.dataInput.evcID) ) {

				reportStatusPass("And evcID is also present " + sf.dataInput.evcID, true, true);
			}

			//4. verify account number and billing date
			verifyFieldPresent("Order Details box 2 ", sf.rODComplete.accountnobox);

			if(installation_FirstMsg1.getText().trim().contains(sf.dataInput.oracleNumber) ) {

				reportStatusPass(sf.dataInput.oracleNumber + "Billing number is present in the message " , true, true);
			}

			//6. Video Link
			sf.seleU.ScrolltoElement(sf.rODComplete.videolink);
			verifyFieldValueByFomrat("Video Link ", sf.rODComplete.videolink,
					sf.omData.RDIClosureLetter_Message3);

			verifyFieldValueByFomrat("As a valued cx ", sf.rODComplete.valuedcxmsg,
					sf.omData.RDIClosureLetter_Message4);

			verifyFieldValueByFomrat("Phone Number ", sf.rODComplete.phonenumber,
					sf.omData.RDIClosureLetter_Message5);

			verifyFieldPresent("Order Details Technical Support ", sf.rODComplete.technicalsupport);

			verifyFieldPresent("Order Details Business Inquiries ", sf.rODComplete.businessinquiries);

			verifyFieldValueByFomrat("Please ensure ", sf.rODComplete.Closureletter_msg1,
					sf.omData.RDIClosureLetter_Message9);

			verifyFieldValueByFomrat("You can also get 24/7 support through our self-serve option ", sf.rODComplete.Closureletter_msg2,
					sf.omData.RDIClosureLetter_Message10);

			verifyFieldValueByFomrat("View Invoices & Payment History ", sf.rODComplete.Closureletter_msg3,
					sf.omData.RDIClosureLetter_Message11);

			verifyFieldValueByFomrat("View Invoices & Payment History ", sf.rODComplete.Closureletter_msg6,
					sf.omData.RDIClosureLetter_Message15);

			verifyFieldValueByFomrat("Enterprise Self-Serve Web Portal https://eportal.rogers.com ", sf.rODComplete.Closureletter_msg4,
					sf.omData.RDIClosureLetter_Message12);

			verifyFieldValueByFomrat("I personally want to thank you for choosing Rogers. ", sf.rODComplete.Closureletter_msg5,
					sf.omData.RDIClosureLetter_Message13);

			//7. Verify Delivery specialist name
			verifyFieldValueByFomrat("Delivery Specialist Name ", sf.rODComplete.Closureletter_msg5,sf.dataInput.userProfileDelivery);


			verifyFieldValueByFomrat("Year in the footer ", sf.rODComplete.yearverification,
					sf.omData.focLetter_Message9);


		}catch (Throwable e) {
			reportStatusFail(" Error in verifying filed values in Closure Letter", e);
			e.printStackTrace();
		}
	}




	// Verify all fields for Welcome Letter for RDI
	public void verifyFieldValuesInEmail_WelcomeLetter(Hashtable<String, String> dataTable) throws IOException {

		try {

			/*sf.dataInput.sf.omData.iNServivceDate = " 2013-01-11"; 
			sf.dataInput.quoteInternetUploadSpeed = "50 Mbps";
			sf.dataInput.accessspeed = "100 Mbps";
			sf.dataInput.port = "7";
			sf.dataInput.evcID = "4643546457657657654365";
			sf.dataInput.quoteInternetUploadSpeed = "50 Mbps";
			sf.dataInput.serviceAddress = "37 MOWREY LANE, NASONWORTH, NB, E3B9G2";
			sf.dataInput.addressState = "ON";
			sf.dataInput.primaryContact = "bh nasn";
			sf.omData.siteContactName = "ABQAAuto200923105646";
			sf.dataInput.userProfileDelivery = "Manthan Jadhav Del";*/
			sf.seleU.wait(10000);


			driver.switchTo().frame("emailuiFrame");
			String methodName = "SFDC_Verify field value in Welcome Letter@: "; int k=  0; WebElement servicesToBeInstalled;

			sf.seleU.wait(5000);

			//0. Verify Welcome letter first message text
			WebElement Welcomeletter_FirstMsg = driver.findElement(By.xpath("//span[normalize-space()='Here is a breakdown of your installation.']"));
			sf.seleU.wait(2000);
			sf.seleU.ScrolltoElement(Welcomeletter_FirstMsg);
			verifyFieldValueByFomrat(" Here is breakdown of your installation.", Welcomeletter_FirstMsg, sf.omData.focLetter_Message2);

			WebElement Welcomeletter_Title = driver.findElement(By.xpath("//span[normalize-space()='Getting Started']"));
			sf.seleU.ScrolltoElement(Welcomeletter_Title);
			verifyFieldValueByFomrat("Welcome Letter Title ", Welcomeletter_Title, sf.omData.RDIWelcomeLetter_Message3);	

			//1. Verify Hello Message information
			WebElement contactName_HelloMsg = driver.findElement(By.xpath("//tbody/tr[4]/td[1]/p[1]"));
			sf.seleU.wait(2000);
			sf.seleU.ScrolltoElement(contactName_HelloMsg);
			if(contactName_HelloMsg.getText().trim().contains(sf.dataInput.userProfileDelivery) && 
					contactName_HelloMsg.getText().trim().contains(sf.dataInput.primaryContact)) {

				reportStatusPass(sf.dataInput.userProfileDelivery + " delivery profile is present in the message " +
						"And primary contact " + sf.dataInput.primaryContact, true, true);
			}
			//verifyFieldPresent("Hello Message with Contact Name Authorized ", contactName_HelloMsg);

			//2 Verify Initial message
			verifyFieldValueByFomrat("Initial Message ", contactName_HelloMsg, sf.omData.RDIWelcomeLetter_Message1);
			System.out.println(contactName_HelloMsg.getText().split("\n")[1]);

			//3. Verify date should be same as service date in monitor rpats manual task
			//verifyFieldValueByFomrat("Service Date ", sf.rODComplete.installationAndActivationHeaderText.get(1), sf.dataInput.sf.omData.iNServivceDate);	
			//  sf.seleU.wait(2000);


			//4. verify services to be installed section
			servicesToBeInstalled = driver.findElement(By.xpath("//span[contains(text(),'Services to be installed: Dedicated Internet Servi')]"));

			verifyFieldPresent("Services To Be Present ", servicesToBeInstalled);
			sf.seleU.wait(2000);

			verifyFieldValueByFomrat("Equipment and plan ", servicesToBeInstalled,
					sf.dataInput.quoteInternetUploadSpeed);
			//5. Verify service address
			sf.seleU.waitElementToBeVisible(sf.cableTaskItems.servicesAddressHeaderText);
			verifyFieldPresent("Service Address Hearder Text ", servicesToBeInstalled);
			verifyFieldValueByFomrat("Service Address Value ", servicesToBeInstalled,
					sf.dataInput.serviceAddress);

			//6. Following is an overview of whatever you expect
			sf.seleU.ScrolltoElement(sf.rODComplete.Overview_msg);
			verifyFieldValueByFomrat("Following is an overview ", sf.rODComplete.Overview_msg,
					sf.omData.RDIWelcomeLetter_Message4);

			verifyFieldValueByFomrat("Confirm Billing ", sf.rODComplete.cnfrmbillinf,
					sf.omData.RDIWelcomeLetter_Message5);

			verifyFieldValueByFomrat("Confirm Billing Description ", sf.rODComplete.cnfrmbillinf_desc,
					sf.omData.RDIWelcomeLetter_Message6);

			verifyFieldValueByFomrat("Site Survey ", sf.rODComplete.sitesurvey,
					sf.omData.RDIWelcomeLetter_Message7);

			verifyFieldValueByFomrat("Site Surevey Message 1 ", sf.rODComplete.sitesurveymsg1,
					sf.omData.RDIWelcomeLetter_Message8);

			verifyFieldValueByFomrat("Site Surevey Message 2 ", sf.rODComplete.sitesurveymsg2,
					sf.omData.RDIWelcomeLetter_Message9);

			verifyFieldValueByFomrat("Site Surevey Message 3 ", sf.rODComplete.sitesurveymsg3,
					sf.omData.RDIWelcomeLetter_Message10);

			verifyFieldValueByFomrat("Site Surevey Message 4 ", sf.rODComplete.sitesurveymsg4,
					sf.omData.RDIWelcomeLetter_Message11);

			verifyFieldValueByFomrat("Installation Details message ", sf.rODComplete.Installationdetails,
					sf.omData.RDIWelcomeLetter_Message12);

			verifyFieldValueByFomrat("Installation Details message 1 ", sf.rODComplete.Installationdetailsmsg1,
					sf.omData.RDIWelcomeLetter_Message13);

			verifyFieldValueByFomrat("Installation Details message 2 ", sf.rODComplete.Installationdetailsmsg2,
					sf.omData.RDIWelcomeLetter_Message14);

			verifyFieldValueByFomrat("Activation Message ", sf.rODComplete.activation,
					sf.omData.RDIWelcomeLetter_Message15);

			verifyFieldValueByFomrat("Activation Message 1 ", sf.rODComplete.activationmsg1,
					sf.omData.RDIWelcomeLetter_Message16);

			verifyFieldValueByFomrat("Activation Message 2 ", sf.rODComplete.activationmsg2,
					sf.omData.RDIWelcomeLetter_Message17);

			verifyFieldValueByFomrat("Billing and support ", sf.rODComplete.billingandsupport,
					sf.omData.RDIWelcomeLetter_Message18);

			verifyFieldValueByFomrat("Billing and support message 1 ", sf.rODComplete.billingandsupportmsg1,
					sf.omData.RDIWelcomeLetter_Message19);

			verifyFieldValueByFomrat("Billing and support message 2 ", sf.rODComplete.billingandsupportmsg2,
					sf.omData.RDIWelcomeLetter_Message20);

			verifyFieldValueByFomrat("Billing and support message 3 ", sf.rODComplete.billingandsupportmsg3,
					sf.omData.RDIWelcomeLetter_Message21);

			verifyFieldPresent("Billing and support message 4 ",sf.rODComplete.billingandsupportmsg4 );
			//7. Verify Delivery specialist email and phone

			//verifyFieldValueByFomrat("Delivery Specialist Phone ", sf.rODComplete.onceYourServiceMsgText,sf.omData.deliverySpecialistPhone);
			verifyFieldValueByFomrat("Delivery Specialist Email", sf.rODComplete.billingandsupportmsg4,
					sf.omData.deliverySpecialistEmail);

			verifyFieldValueByFomrat("Year in the footer ", sf.rODComplete.yearverificationWelcomeletter,
					sf.omData.RDIWelcomeLetter_Message22);


		}catch (Throwable e) {
			reportStatusFail(" Error in verifying filed values in Welcome Letter", e);
			e.printStackTrace();
		}
	}





	/**
	 * @throws IOException
	 * 
	 *                     Verify the field details in FOC Letter sent to signing authority, once FOC button is 
	 *                     clicked from monitor rpats fibre build task
	 */
	public void verifyFieldValuesInEmail_FOCLetter(Hashtable<String, String> dataTable) throws IOException {

		try {
			sf.seleU.wait(10000);

			driver.switchTo().frame("emailuiFrame");
			String methodName = "SFDC_Verify field value in FOC Letter@: "; int k=  0; WebElement servicesToBeInstalled;
			/*sf.dataInput.sf.omData.iNServivceDate = " 2013-01-11"; 
			sf.dataInput.quoteInternetUploadSpeed = "50 Mbps";
			sf.dataInput.accessspeed = "100 Mbps";
			sf.dataInput.port = "7";
			sf.dataInput.evcID = "4643546457657657654365";
			sf.dataInput.quoteInternetUploadSpeed = "50 Mbps";
			sf.dataInput.serviceAddress = "37 MOWREY LANE, NASONWORTH, NB, E3B9G2";
			sf.dataInput.addressState = "ON";
			sf.dataInput.primaryContact = "bh nasn";
			sf.omData.siteContactName = "ABQAAuto200923105646";
			sf.dataInput.userProfileDelivery = "Manthan Jadhav Del";*/

			//			WebElement contactName = driver.findElement(By.xpath("//td[contains(text(),'"+sf.dataInput.sf.dataInput.primaryContact+"')]"));
			//			verifyFieldValueByFomrat("Contact Name Authorized ", contactName, sf.dataInput.primaryContact);	


			//0. Verify installation and activation first message text
			WebElement installation_FirstMsg = driver.findElement(By.xpath("//span[contains(text(),'Here is confirmation of your installation and')]"));
			sf.seleU.wait(2000);
			sf.seleU.ScrolltoElement(installation_FirstMsg);
			verifyFieldValueByFomrat(" Here is confirmation of your installation and activation.", installation_FirstMsg, sf.omData.focLetter_Message2);

			sf.seleU.ScrolltoElement(sf.rODComplete.installationAndActivationHeaderText.get(0));
			verifyFieldPresent("Installation And Activation Header ", sf.rODComplete.installationAndActivationHeaderText.get(0));

			//1. Verify Hello Message information
			WebElement contactName_HelloMsg = driver.findElement(By.xpath("//span[contains(text(),'Hello')]"));
			sf.seleU.wait(2000);
			sf.seleU.ScrolltoElement(contactName_HelloMsg);
			verifyFieldPresent("Hello Message with Contact Name Authorized ", contactName_HelloMsg);

			//2 Verify Great News message
			verifyFieldValueByFomrat("Great News ", contactName_HelloMsg, sf.omData.focLetter_Message1);
			System.out.println(contactName_HelloMsg.getText().split("\n")[1]);

			//3. Verify date should be same as service date in monitor rpats manual task
			verifyFieldValueByFomrat("Service Date ", sf.rODComplete.installationAndActivationHeaderText.get(1), sf.dataInput.sf.omData.iNServivceDate);	
			sf.seleU.wait(2000);


			//4. verify services to be installed section
			servicesToBeInstalled = driver.findElement(By.xpath("//span[contains(text(),'Services to be installed: Dedicated Internet Servi')]"));

			verifyFieldPresent("Services To Be Present ", servicesToBeInstalled);
			sf.seleU.wait(2000);

			verifyFieldValueByFomrat("Equipment and plan ", servicesToBeInstalled,
					sf.dataInput.quoteInternetUploadSpeed);
			//5. Verify service address
			sf.seleU.waitElementToBeVisible(sf.cableTaskItems.servicesAddressHeaderText);
			verifyFieldPresent("Service Address Hearder Text ", servicesToBeInstalled);
			verifyFieldValueByFomrat("Service Address Value ", servicesToBeInstalled,
					sf.dataInput.serviceAddress);
			verifyFieldValueByFomrat("Demarcation Location ", servicesToBeInstalled,
					sf.dataInput.addressState);

			//6. Following is completed before installation section
			sf.seleU.ScrolltoElement(sf.rODComplete.ensureFollowingCompletedMsgText);
			verifyFieldValueByFomrat("Following is completed before installation ", sf.rODComplete.ensureFollowingCompletedMsgText,
					sf.omData.focLetter_Message3);

			verifyFieldValueByFomrat("Space is available ", sf.rODComplete.spaceIsAvailableMsgText,
					sf.omData.focLetter_Message4);

			verifyFieldValueByFomrat("Access to the main building ", sf.rODComplete.accessToMainBuildingMsgText,
					sf.omData.focLetter_Message5);

			System.out.println(sf.rODComplete.accessToMainBuildingMsgText.getText());

			verifyFieldValueByFomrat("A surge protected ", sf.rODComplete.asurgeProtectedMsgText,
					sf.omData.focLetter_Message6);

			System.out.println(sf.rODComplete.asurgeProtectedMsgText.getText());

			verifyFieldValueByFomrat("Once your services are installed ", sf.rODComplete.onceYourServiceMsgText,
					sf.omData.focLetter_Message7);

			//7. Verify Delivery specialist message and name
			verifyFieldValueByFomrat("If you have any question ", sf.rODComplete.onceYourServiceMsgText,sf.omData.focLetter_Message8);
			verifyFieldValueByFomrat("Delivery Specialist Name ", sf.rODComplete.onceYourServiceMsgText,sf.dataInput.userProfileDelivery);
			verifyFieldValueByFomrat("Delivery Specialist Email", sf.rODComplete.onceYourServiceMsgText,
					sf.omData.deliverySpecialistEmail);

			verifyFieldValueByFomrat("Year in the footer ", sf.rODComplete.yearverification,
					sf.omData.focLetter_Message9);


		}catch (Throwable e) {
			reportStatusFail(" Error in verifying filed values in FOC Letter", e);
			e.printStackTrace();
		}
	}


	/**
	 * @throws IOException
	 * 
	 *                     Verify all the field value details
	 * 
	 *                     Enter the billing information
	 * 
	 *                     Click on Complete Button
	 */
	public void verifyScheduleTisIp_Complete_Page(Hashtable<String, String> dataTable) throws IOException {

		try {
			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();

			// ***1. Verify General Information Field
			// verifyGeneralInformationField();
			sf.seleU.wait(1000);

			// verify Product Information Field

			// verifyFieldPresent("Access Speed", sf.rODComplete.accessSpeed);
			verifyFieldValue("Demarcation Location ", sf.rODComplete.demarcationLocationText,
					sf.dataInput.addressState);
			verifyFieldValue("Hand Off ", sf.rODComplete.handOff, dataTable.get("handOffInterface"));

			verifyFieldPresent("VLan Id", sf.rODComplete.vLanIdInCircuitValue);

			sf.seleU.ScrolltoElement(sf.rODComplete.vLanIdInCircuitValue);
			verifyFieldPresent("DNS Required ", sf.rODComplete.dNSValue);
			// verifyFieldPresent("Portable IP Value ", sf.rODComplete.portableIpValue);
			if (sf.seleU.isElementDisplayed(sf.rODComplete.iPVersion)) {
				verifyFieldValue("IP Version", sf.rODComplete.iPVersion, sf.omData.iPversion);
				verifyFieldValue("IPv4 WAN Block Assignment ", sf.rODComplete.iPV4Wan,
						sf.omData.ipV4WAnBlockSpecSheet);
				verifyFieldValue("IPv4 LAN Block Assignment ", sf.rODComplete.iPV4Lan,
						sf.omData.ipV4LAnBlockSpecSheet);
			}
			//
			// sf.rODComplete.iPV4LanValue);
			sf.seleU.wait(1000);

			sf.seleU.clickElementByJSE(sf.rODComplete.validateStatusCheckBoxClick);
			reportStatusPass(methodName + " Clicked on 'Validate Check Status check box", true, false);
			sf.seleU.wait(1000);

			// verify success message text
			verifyFieldPresent("success Message Text", sf.rODComplete.successMessageText);
			sf.seleU.hardwait(5000);
			// Click on the complete button
			sf.seleU.clickElementByJSE(sf.compVlctOdr.completeButton);

			reportStatusPass(methodName + " Clicked on 'in schedule TS & IP' Complete Button", true, false);
			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying filed values in schedule TS & IP", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify all the field value details
	 * 
	 *                     Enter the billing information
	 * 
	 *                     Click on Complete Button
	 */
	public void verifyIpAssignment_Complete_Page(Hashtable<String, String> dataTable) throws IOException {

		try {
			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();

			// ***1. Verify General Information Field
			// verifyGeneralInformationField();
			sf.seleU.wait(1000);

			// verify Product Information Field

			//			 verifyFieldPresent("Access Speed", sf.rODComplete.accessSpeed);
			//			 verifyFieldPresent("Demarcation Location ",
			//			 sf.rODComplete.demarcationLocationText);
			//			 verifyFieldValue("Hand Off ", sf.rODComplete.handOff, dataTable.get("handOffInterface"));

			// verifyFieldPresent("VLan Id", sf.rODComplete.vLanIdInCircuitValue);

			// sf.seleU.ScrolltoElement(sf.rODComplete.vLanIdInCircuitValue);
			// verifyFieldPresent("DNS Required ", sf.rODComplete.dNSValue);
			// verifyFieldPresent("Portable IP Value ", sf.rODComplete.portableIpValue);

			sf.seleU.wait(1000);
			if (sf.seleU.isElementDisplayed(sf.rODComplete.iPVersion)) {
				verifyFieldValue("IP Version", sf.rODComplete.iPVersion, sf.omData.iPversion);
				verifyFieldValue("IPv4 WAN Block Assignment ", sf.rODComplete.iPV4Wan,
						sf.omData.ipV4WAnBlockSpecSheet);
				verifyFieldValue("IPv4 LAN Block Assignment ", sf.rODComplete.iPV4Lan,
						sf.omData.ipV4LAnBlockSpecSheet);
			}
			// sf.seleU.wait(4000);

			WebElement ele = driver.findElement(By.xpath("//div[@class='vlc-slds-transparent']/input"));
			sf.seleU.clickElementByJSE(sf.rODComplete.ipAssignmentIpload2);
			sf.seleU.moveToThenSlowClickElement(ele);
			upload_File();

			sf.seleU.clickElementByJSE(sf.rODComplete.validateStatusCheckBoxClick);
			reportStatusPass(methodName + " Clicked on 'Validate Check Status check box", true, false);
			sf.seleU.wait(1000);

			// verify success message text
			verifyFieldPresent("success Message Text", sf.rODComplete.successMessageText);
			sf.seleU.hardwait(2000);
			// Click on the complete button
			sf.seleU.clickElementByJSE(sf.compVlctOdr.completeButton);
			reportStatusPass(methodName + " Clicked on 'verifying complete IP assignment' Complete Button", true,
					false);
			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying IP assignment complete page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify all the field value details
	 * 
	 *                     Enter the billing information
	 * 
	 *                     Click on Complete Button
	 */
	public void verifyScheduleTis_Complete_Page(Hashtable<String, String> dataTable) throws IOException {

		try {
			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();

			// ***1. Verify General Information Field
			// verifyGeneralInformationField();
			sf.seleU.wait(1000);

			// verify Product Information Field

			verifyFieldValue("Product Name", sf.rODComplete.productDetailsNameText, sf.dataInput.quoteProductName);
			verifyFieldValue("Product Speed", sf.rODComplete.productSpeedValue.get(0),
					sf.dataInput.quoteInternetUploadSpeed);	
			verifyFieldValue("Encapsulation type", sf.rODComplete.encapsulationTypeValue, dataTable.get("encapsulationType"));
			verifyFieldValue("Demarcation Location", sf.rODComplete.demarcationLocationTexts.get(0), sf.dataInput.addressState);
			verifyFieldPresent("Service provider", sf.rODComplete.serviceProviderValue);

			verifyFieldPresent("Circuit Name", sf.rODComplete.cirDetailsNameText);
			verifyFieldPresent("Service Speed ", sf.rODComplete.productSpeedValue.get(1));

			verifyFieldValueWithFormat("Access type ", sf.rODComplete.accessTypeValue, sf.dataInput.accessTypeNet);
			verifyFieldPresent("Access Provider ", sf.rODComplete.accessProviderValue);
			verifyFieldPresent("Service Provider ", sf.rODComplete.serviceProviderValue);
			sf.seleU.wait(1000);
			sf.seleU.ScrolltoElement(sf.rODComplete.handOff);
			verifyFieldValue("Hand Off ", sf.rODComplete.handOff, dataTable.get("handOffInterface"));

			verifyFieldPresent("VLan Id", sf.rODComplete.vLanIdInCircuitValue);
			verifyFieldPresent("TErminating Equipment ", sf.rODComplete.equipment);
			verifyFieldPresent("Port No ", sf.rODComplete.portNo);
			verifyFieldPresent("Port Type ", sf.rODComplete.portTypeValue);
			verifyFieldPresent("Port Speed ", sf.rODComplete.portSpeed);

			//			if (sf.seleU.isElementDisplayed(sf.rODComplete.iPV4Wan)) {
			//				sf.seleU.ScrolltoElement(sf.rODComplete.iPVersion);
			//				verifyFieldValue("IP Version", sf.rODComplete.portableIpValue, sf.omData.iPversion);
			//				verifyFieldValue("IPv4 WAN Block Assignment ", sf.rODComplete.iPV4WanValue,
			//						sf.omData.ipV4WAnBlockSpecSheet);
			//				verifyFieldValue("IPv4 LAN Block Assignment ", sf.rODComplete.iPV4LanValue,
			//						sf.omData.ipV4LAnBlockSpecSheet);
			//			}

			// If it's not dual stack
			if(sf.omData.iPversion.trim().equals("IPv4")){
				verifyFieldValue("Ip Version ",sf.rODComplete.ipVersionValue, sf.omData.iPversion);
				verifyFieldValue("IpV4 WanBlock ",sf.rODComplete.iPV4WanValue, sf.omData.ipV4WAnBlockSpecSheet);
				verifyFieldValue("IpV4 LanBlock ",sf.rODComplete.iPV4LanValue, sf.omData.ipV4LAnBlockSpecSheet);
			} else if (sf.omData.iPversion.equals("IPv6")) {
				verifyFieldValue("Ip Version ",sf.rODComplete.ipVersionValue, sf.omData.iPversion);
				verifyFieldValue("IpV6 WanBlock ",sf.rODComplete.iPV6WanValue, sf.omData.ipV6WAnBlockSpecSheet);
				verifyFieldValue("IpV6 LanBlock ",sf.rODComplete.iPV6LanValue, sf.omData.ipV6LAnBlockSpecSheet);
			}	else {
				verifyFieldValue("Ip Version ",sf.rODComplete.ipVersionValue, sf.omData.iPversion);
				verifyFieldValue("IpV4 WanBlock ",sf.rODComplete.iPV4WanValue, sf.omData.ipV4WAnBlockSpecSheet);
				verifyFieldValue("IpV4 LanBlock ",sf.rODComplete.iPV4LanValue, sf.omData.ipV4LAnBlockSpecSheet);
				sf.seleU.wait(2000);
				verifyFieldValue("IpV6 WanBlock ",sf.rODComplete.iPV6WanValue, sf.omData.ipV6WAnBlockSpecSheet);
				sf.seleU.wait(2000);
				verifyFieldValue("IpV6 LanBlock ",sf.rODComplete.iPV6LanValue, sf.omData.ipV6LAnBlockSpecSheet);
			}

		/*	if(!sf.omData.existingPortableIP.equals("NA")) {
				String existingPortableIp = omData.existingPortableIP.trim().replaceAll("[^a-zA-Z0-9]", "");
				System.out.println(existingPortableIp);
				String portableIP = sf.seleU.getTextFromWebElement(sf.rODComplete.existingPortableIP).replaceAll("[^a-zA-Z0-9]", "");
				if(portableIP.equals(existingPortableIp)) {
					reportStatusPass(methodName + "matched the portable ip values as expected " + omData.existingPortableIP, true, true);	
				}

			}*/

			sf.seleU.wait(1000);
			sf.seleU.clickElementByJSE(sf.rODComplete.validateStatusCheckBoxClick);
			reportStatusPass(methodName + " Clicked on 'Validate Check Status check box", true, false);
			sf.seleU.wait(1000);

			// verify success message text
			verifyFieldPresent("success Message Text", sf.rODComplete.successMessageText);
			sf.seleU.hardwait(2000);
			// Click on the complete button
			sf.seleU.clickElementByJSE(sf.compVlctOdr.sendOrderToPICompleteButton);
			reportStatusPass(methodName + " Clicked on 'Schedule TIS For TTC' Complete Button", true, false);
			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying schedule TIS complete page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify all the field value details Click on Complete
	 *                     Button
	 */
	public void verifyComplete_Accessand_Service_Provisioning_Complete_Page(Hashtable<String, String> dataTable)
			throws IOException {

		try {
			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();

			// ***1. Verify General Information Field
			// verifyGeneralInformationField();
			sf.seleU.wait(2000);

			//*****Verify Additional Information section field***
			verifyFieldPresent("Contract Term", sf.rODComplete.contractTermText);
			verifyFieldPresent("Credit Limit Approved Text", sf.rODComplete.creditLimitApprovedValue);		
			verifyFieldValue("Billing Account Number", sf.rODComplete.billingAccountNumberText,
					sf.dataInput.oracleNumber);
			verifyFieldPresent("Billing Account Source", sf.rODComplete.billingAccountSourcerText);
			verifyFieldPresent("Billing Account", sf.rODComplete.billingAccountName);

			// Verify Site Contact
			verifyFieldValue("Site Contact Name Text", sf.rODComplete.siteContactNameText,
					sf.dataInput.siteContact);
			verifyFieldValue("Site Contact Email", sf.rODComplete.siteContactEmailText,
					sf.dataInput.emailIdValue);
			sf.seleU.wait(1000);
			verifyFieldValue("Site Contact Phone",sf.rODComplete.siteContactPhoneText,sf.dataInput.contactPhoneNumber);
			sf.seleU.wait(1000);
			verifyFieldValue("Site Contact Language Pref", sf.rODComplete.siteContactLanguageText,
					sf.dataInput.langEnglish);

			//***verify Product Information Field
			// Service Details
			verifyFieldValue("Product Name", sf.rODComplete.productDetailsNameText, sf.dataInput.quoteProductName);
			verifyFieldValue("Service Speed ", sf.rODComplete.productSpeedValue.get(0),
					sf.dataInput.quoteInternetUploadSpeed);
			verifyFieldPresent("Service Provider ", sf.rODComplete.serviceProviderValue);
			verifyFieldValue("Demarcation Location ", sf.rODComplete.demarcationLocationText,
					sf.dataInput.addressState);
			verifyFieldValue("Encapsulation type", sf.rODComplete.encapsulationTypeValue, dataTable.get("encapsulationType"));

			// Circuit Details:
			sf.seleU.ScrolltoElement(sf.rODComplete.circuitDetailsHeaderText);
			verifyFieldPresent("Circuit Details Text", sf.rODComplete.circuitDetailsHeaderText);
			verifyFieldPresent("Circuit Access Name", sf.rODComplete.cirDetailsNameText);
			verifyFieldValue("Access Speed", sf.rODComplete.accessSpeed, sf.dataInput.accessspeed);
			verifyFieldValue("HandOff ", sf.rODComplete.handOff, dataTable.get("handOffInterface"));
			verifyFieldValueWithFormat("Access type ", sf.rODComplete.accessTypeValue, sf.dataInput.accessTypeNet);
			verifyFieldValue("Access Provider", sf.rODComplete.accessProviderValue, "Rogers");

			// Equipment Details
			sf.seleU.ScrolltoElement(sf.rODComplete.equipmentDetailsHeaderText);
			verifyFieldPresent("Equipment Details Text", sf.rODComplete.equipmentDetailsHeaderText);
			verifyFieldPresent("Equipment", sf.rODComplete.equipment);
			verifyFieldValue("Port No", sf.rODComplete.portNoVAlue, sf.dataInput.port);
			verifyFieldPresent("Port Speed", sf.rODComplete.portSpeed);
			verifyFieldPresent("Port Type", sf.rODComplete.portTypeValue);

			// Enter port config field information
			sf.seleU.wait(2000);
			sf.seleU.clearText(sf.rODComplete.portConfig);
			sf.seleU.wait(1000);
			sf.seleU.clearAndEnterText(sf.rODComplete.portConfig, sf.dataInput.portConfigNo);
			reportStatusPass(methodName + "Enter the port config no " + sf.dataInput.portConfigNo, true,
					false);
			sf.seleU.wait(1000);

			// Verify demarcation loc and hand off interface field value
			//			verifyFieldValueFromDropDown("Demarcation Loc", sf.rODComplete.demarcationLocationTextInAccessProvsioning,
			//					sf.dataInput.addressState);
			//			verifyFieldValueFromDropDown("Hand-Off Interface", sf.rODComplete.handOffInterfacedropDownInAccess,
			//					dataTable.get("handOffInterface"));
			verifyFieldValue_ByAttribute("HandOff Interface", sf.rODComplete.handOffInterfacedropDownInAccess,"data-value",
					dataTable.get("handOffInterface"));
			verifyFieldValue("Demarcation Loc", sf.rODComplete.demarcationLocationTextInAccessProvsioning,
					sf.dataInput.addressState);
			sf.seleU.ScrolltoElement(sf.rODComplete.demarcationLocationTextInAccessProvsioning);
			// upload ethernet file attachment
			sf.seleU.moveToThenSlowClickElement(sf.rODComplete.ethernetTestResultUpload);
			upload_File();
			sf.seleU.wait(5000);

			verifyFieldValue("Device ID ", sf.rODComplete.deviceIdValue, sf.dataInput.deviceID);
			verifyFieldValue("Customer Port ID ", sf.rODComplete.customerPortIdValue, sf.dataInput.customerPortNo);
			verifyFieldValue("Access CIRC ID ", sf.rODComplete.accessCIRCIdValue, sf.dataInput.oracleNumber);
			sf.seleU.wait(1000);
			sf.seleU.ScrolltoElement(sf.rODComplete.cLFITypeText);
			verifyFieldValue("CLFI ", sf.rODComplete.cLFITypeText, sf.dataInput.cLFIValue);
			verifyFieldValue("EVC Notes ", sf.rODComplete.evcNotesText, sf.dataInput.opportunityAdditionalInfo);
			verifyFieldValue("EVC ID ", sf.rODComplete.evcIDText, sf.dataInput.evcID);		
			verifyFieldPresent("IP Address Assignment Attachement ", sf.rODComplete.ipAddressAttachmentValue);
			verifyFieldPresent("Circuit Design Attachement ", sf.rODComplete.circuitDesignAttachmentValue);
			sf.seleU.wait(6000);
			//	sf.seleU.ScrolltoElement(sf.rODComplete.ethernetTestResultsAttachmentValue);

			// Click on the save button
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.rODComplete.saveButtonForCompleteAccess);
			reportStatusPass(methodName + " Clicked on save button", true,false);
			sf.seleU.wait(6000);
			verifyFieldPresent("Task Updated Text ", sf.rODComplete.taskUpdatedText);	

			// Verify saved fields
			// 1. close the task and open the task again from the plan
			closeAndOpenCompleteAccessTaskAfterSave();
			//	openFromOrchestrationPlan("open the task", sf.orchPlan.orchesItemPlanCompleteCircuitDesignTask, sf.orchPlan.orchesItemPlanCompleteCircuitDesignClick);
			sf.seleU.wait(6000);
			verifyFieldValue("After saving Port Config No ", sf.rODComplete.portConfig, sf.dataInput.portConfigNo);
			verifyFieldPresent("Ethernet File Attachement ", sf.rODComplete.ethernetTestResultsAttachmentValue);

			sf.seleU.clickElementByJSE(sf.rODComplete.validateStatusCheckBoxClick);
			reportStatusPass(methodName + " Clicked on 'Validate Check Status check box", true, false);

			// verify success message text
			//	verifyFieldPresent("success Message Text", sf.rODComplete.successMessageText);
			sf.seleU.wait(6000);
			// Click on the complete button
			sf.seleU.clickElementByJSE(sf.compVlctOdr.monitorCompleteButton);
			reportStatusPass(methodName + " Clicked on 'complete access and service provisioning' Complete Button",
					true, false);
			sf.seleU.wait(6000);

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying field values in complete service provisioning", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify all the field value details
	 * 
	 *                     Enter the billing information
	 * 
	 *                     Click on Complete Button
	 */
	public void verifyBillingActivationFailure_Complete_Page(Hashtable<String, String> dataTable) throws IOException {

		try {
			sf.seleU.wait(2000);

			sf.seleU.switchToDefaultContent();

			// ***1. Verify General Information Field
			// verifyGeneralInformationField();

			sf.seleU.wait(1000);
			sf.seleU.clickElementByJSE(sf.rODComplete.billingActivatonCheckBox);
			reportStatusPass(methodName + " Clicked on Billing Activation Completed checkbox", true, false);
			sf.seleU.wait(2000);
			// Click on the complete button
			sf.seleU.clickElementByJSE(sf.compVlctOdr.billingActivationCompleteButton);
			reportStatusPass(methodName + " Clicked on 'Billing Activation FailurePage' Complete Button",
					true, false);
			sf.seleU.wait(25000);

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying filed values in review order details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Creating the object of file upload page to call upload
	 *                     method
	 * 
	 */
	private void upload_File() throws IOException {
		// Auto-generated method stub
		try {
			SFDC_Files_Page fileUploadPage = new SFDC_Files_Page();

			fileUploadPage.uploadFilesWithOutVerify();
			//	fileUploadPage.uploadFiles();
		} catch (Throwable e) {
			reportStatusFail("upload file failed", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Creating the object of file upload page to call upload
	 *                     method
	 * 
	 */
	private void closeTabAndVerifySavedFields() throws IOException {
		// Auto-generated method stub
		try {
			SFDC_Home_Page homepage = new SFDC_Home_Page();
			homepage.closeLastOpenedTab();
			//	fileUploadPage.uploadFiles();
		} catch (Throwable e) {
			reportStatusFail("upload file failed", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Creating the object of file upload page to call upload
	 *                     method
	 * 
	 */
	private void closeAndOpenCompleteCircuitTaskAfterSave() throws IOException {
		// Auto-generated method stub
		try {
			SFDC_AllPages sfdc = new SFDC_AllPages();
			sfdc.home.closeTabIfOpen();
			sf.seleU.closeRecentlyOpenedWindow();
			sfdc.mques.clickOnManualQueuesObject();
			sf.dataInput.items_InQueue();
			sfdc.mques.selectServiceDesignerQueue();
			sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(7));

			//	fileUploadPage.uploadFiles();
		} catch (Throwable e) {
			reportStatusFail("Not able to open the task after saved ", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Creating the object of file upload page to call upload
	 *                     method
	 * 
	 */
	private void closeAndOpenCompleteAccessTaskAfterSave() throws IOException {
		// Auto-generated method stub
		try {
			SFDC_AllPages sfdc = new SFDC_AllPages();
			sfdc.home.closeTabIfOpen();
			sf.seleU.closeRecentlyOpenedWindow();
			sfdc.mques.clickOnManualQueuesObject();
			sf.dataInput.items_InQueue();
			sfdc.mques.selectTechImpSpecialistQueue();
			sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(11));
			//	fileUploadPage.uploadFiles();
		} catch (Throwable e) {
			reportStatusFail("Not able to open the task after saved ", e);
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
			sf.seleU.wait(2000);
			if (sf.seleU.getTextFromWebElement(element).trim().contains(expectedText.trim())) {
				reportStatusPass("Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail("Actual Value for " + fieldName + " is " + sf.seleU.getTextFromWebElement(element) + " And Expected One is "
						+ expectedText, true);
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
	public void verifyFieldValue_ByAttribute(String fieldName, WebElement element, String attributeName, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getElementAttribute(element, attributeName).trim().contains(expectedText.trim())) {
				reportStatusPass("Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail("Actual Value for " + fieldName + " is " + element.getAttribute(attributeName) + " And Expected One is "
						+ expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * Verify Field is present
	 * 
	 * @throws IOException
	 */
	public void verifyFieldPresent_WithAttribute(String fieldName, WebElement element, String attributeName) throws IOException {

		try {
			// Verify Field is present
			if (!sf.seleU.getElementAttribute(element, attributeName).isEmpty()) {
				reportStatusPass("Validated " + fieldName + " is present" + " with value as "
						+ sf.seleU.getElementAttribute(element, attributeName), true, true);
			} else {
				reportStatusFail(fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field Present in dropdown", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldValueByFomrat(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).trim().replaceAll(" ", "")
					.contains(expectedText.trim().replaceAll(" ", ""))) {
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

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldValueWithFormat(String fieldName, WebElement element, String expectedText)
			throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).trim().toLowerCase().replace("-", "")
					.contains(expectedText.trim())) {
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

	/**
	 * Verify Field is present
	 * 
	 * @throws IOException
	 */
	public void verifyFieldPresent(String fieldName, WebElement element) throws IOException {

		try {

			// Verify Field is present
			sf.seleU.wait(1000);
			if (sf.seleU.isElementDisplayedWithYellowHighlight(element)) {
				reportStatusPass(fieldName + " is present" + " with value as "
						+ sf.seleU.getTextFromWebElementWithYellowHighlight(element), true, false);
			} else {
				reportStatusFail(fieldName + " is not present", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}

	}

	/**
	 * Verify Field is present
	 * 
	 * @throws IOException
	 */
	public void verifyFieldNotPresent(String fieldName, WebElement element) throws IOException {

		try {

			// Verify Field is present
			if (!sf.seleU.isElementDisplayedWithYellowHighlight(element)) {
				reportStatusPass(fieldName + " is not present as expected", true, true);
			} else {
				reportStatusFail(fieldName + " is present", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}

	}

	/**
	 * Verify Field is present
	 * 
	 * @throws IOException
	 */
	public void verifyFieldPresentFromDropDown(String fieldName, WebElement element) throws IOException {

		try {
			// Verify Field is present
			if (!sf.seleU.getSelectedTextFromDropDownWithYellowHightlight(element).isEmpty()) {
				reportStatusPass("Validated " + fieldName + " is present" + " with value as "
						+ sf.seleU.getSelectedTextFromDropDownWithYellowHightlight(element), true, true);
			} else {
				reportStatusFail(fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field Present in dropdown", e);
			e.printStackTrace();
		}
	}



	/**
	 * Verify Field is present
	 * 
	 * @throws IOException
	 */
	public void verifyFieldValueFromDropDown(String fieldName, WebElement element, String expectedText)
			throws IOException {

		try {
			// Verify Field is present
			if (!sf.seleU.getSelectedTextFromDropDownWithYellowHightlight(element).isEmpty()) {

				if (sf.seleU.getSelectedTextFromDropDownWithYellowHightlight(element).trim()
						.contains(expectedText.trim())) {
					reportStatusPass("Validated " + fieldName + " is " + expectedText, true, true);
				} else {
					reportStatusFail("Actual Value for " + fieldName + " is "
							+ sf.seleU.getSelectedTextFromDropDownWithYellowHightlight(element)
							+ " And Expected One is " + expectedText, true);
				}
			} else {
				reportStatusFail(fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification in dropdown", e);
			e.printStackTrace();
		}

	}



	public int countNoOfDigits(int num) throws IOException {
		int count = 0;
		while (num != 0) {
			// num = num/10
			num /= 10;
			++count;
		}
		System.out.println("Number of digits: " + count);
		return count;
	}

	public int countNoOfCharacters(String string) throws IOException {
		int count = 0;
		// Counts each character except space
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) != ' ')
				count++;
		}
		// Displays the total number of characters present in the given string
		System.out.println("Total number of characters in a string: " + count);
		return count;
	}

	/**PA PI24PSp1
	 * @throws IOException
	 * 
	 *                    open the task from the orchestration plan       
	 * 
	 */
	public void openFromOrchestrationPlan(String taskName, WebElement taskLink, WebElement taskClick) throws IOException {
		try {
			/*
			 * Open the task from the orchestartion plan
			 */
			sf.seleU.switchToDefaultContent();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesPlanItemMinusClick);
			sf.seleU.scrollByCoOrdinates(1);
			for(int i=0; i < 1; i ++) {
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}
			sf.seleU.wait(2000);

			//		sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanReviewOrderDetailsCompletionLinkCable);
			verifyFieldPresent(taskName, taskLink);

			sf.seleU.waitElementToBeVisible(taskClick);
			sf.seleU.wait(6000);
			sf.seleU.clickElementByJSE(taskClick);
			reportStatusPass(methodName + " Clicked on 'Review Order Details Task", true, false);
			sf.seleU.switchToDefaultContent();
			//	sf.seleU.refreshPage();

		} catch (Exception e) {
			reportStatusFail(" Error in clicking review order details task", e);
		}
	}

}
