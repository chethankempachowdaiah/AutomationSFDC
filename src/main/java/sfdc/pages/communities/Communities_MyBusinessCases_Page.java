package sfdc.pages.communities;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.data.InputData_Service;
import com.sfdc.lib_pages.SFDC_Files_Page;
import com.sfdc.page_objects.SFDC_AllPageObjects;

import sfdc.pages.service.SFDC_Cases_Page;

public class Communities_MyBusinessCases_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public static SFDC_Files_Page filesPage;
	public static Communities_MyBusinessCaseDetails_Page comCaseDetails;

	public Communities_MyBusinessCases_Page() {
		sf = new SFDC_AllPageObjects();
		filesPage = new SFDC_Files_Page();
		comCaseDetails = new Communities_MyBusinessCaseDetails_Page();
		methodName = "Communities_MyBusinessCases@:";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify that eligible customers are able to see a table
	 *                     summarizing of their cases(summary)
	 * 
	 *                     Verify Case Table filters :All, New, In Progress,
	 *                     Awaiting Customer Response and Closed
	 */
	public void verifyMyBusniessCases() throws IOException {

		try {
			sf.seleU.wait(50000);
			verifyFieldDisplayed("Communities My Business Cases Header", sf.comMBC.myBusinessCasesHeader);
			verifyFieldDisplayed("Communities case Summary Table Header", sf.comMBC.caseSummary_caseSummaryTableHeader);
			verifyFieldDisplayed("Communities case Number Column Name", sf.comMBC.caseSummary_caseNumberColumnName);
			verifyFieldDisplayed("Communities Account Column Name", sf.comMBC.caseSummary_accountNameColumnName);
			verifyFieldDisplayed("Communities Status Column Name", sf.comMBC.caseSummary_statusColumnName);
			verifyFieldDisplayed("Communities Reason Column Name", sf.comMBC.caseSummary_reasonColumnName);
			verifyFieldDisplayed("Communities Product Column Name", sf.comMBC.caseSummary_productColumnName);

			verifyFieldDisplayed("Case Filter 'All Cases'", sf.comMBC.caseFilterAllCases);
			verifyFieldDisplayed("Case Filter 'New'", sf.comMBC.caseFilterNew);
			verifyFieldDisplayed("Case Filter 'In Progress'", sf.comMBC.caseFilterInProgress);
			verifyFieldDisplayed("Case Filter 'Awaiting Customer Response'",
					sf.comMBC.caseFilterAwaitingCustomerResponse);
			verifyFieldDisplayed("Case Filter 'Closed'", sf.comMBC.caseFilterClosed);

			verifyFieldDisplayed("Create new Case Link", sf.comMBC.createNewCasesLink);
			verifyFieldDisplayed("Case Link", sf.comMBC.caseSummary_caseNumberAllLinks.get(0));

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying My Business Cases unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify that eligible customers are able to see a table
	 *                     summarizing of their orders(summary)
	 */
	public void verifyMyBusniessOrders() throws IOException {

		try {
			sf.seleU.hardwait(7000);
			sf.seleU.ScrolltoElement(sf.comMBC.orderSummary_orderStatusColumnName);
			sf.seleU.hardwait(2000);
			verifyFieldDisplayed("Communities My Business Cases Header", sf.comMBC.myBusinessCasesHeader);
			verifyFieldDisplayed("Communities Order Summary Table Header", sf.comMBC.orderSummary_tableHeader);
			verifyFieldDisplayed("Communities Order Number Column Name", sf.comMBC.orderSummary_orderNumberColumnName);
			//verifyFieldDisplayed("Communities Account Column Name", sf.comMBC.orderSummary_accountNameColumnName);
			verifyFieldDisplayed("Communities Order Start Date Column Name",
					sf.comMBC.orderSummary_orderDateColumnName);
			verifyFieldDisplayed("Communities Products Column Name", sf.comMBC.orderSummary_productColumnName);
			verifyFieldDisplayed("Communities Order Address Column Name", sf.comMBC.orderSummary_orderAddressColumnName);
			verifyFieldDisplayed("Communities Order Status Column Name", sf.comMBC.orderSummary_orderStatusColumnName);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying My Business Orders unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Create New case
	 * 
	 *                     Validate case creation form layout
	 * 
	 *                     Select Case Account Name
	 * 
	 *                     Select Case Reason
	 * 
	 *                     Select Case Product
	 * 
	 *                     Enter Case Subject and Description
	 * 
	 *                     Click on Submit Button
	 * 
	 *                     Verify Case Creation and created case details
	 */
	public void myBusinessCasesCreateCase() throws IOException {

		try {

			// Click on Create New case
			sf.seleU.clickElementByJSE(sf.comMBC.createNewCasesLink);
			reportStatusPass(methodName + " Clicked on create new case", false, false);
			sf.seleU.wait(6000);

			// Verify Name field is autofilled
			if ((sf.seleU.getTextFromWebElement(sf.comContactUs.nameInput)).isEmpty()) {
				reportStatusFail(methodName + " Name field is not autofilled under case creation", true);
			} else {
				reportStatusPass(methodName + " Name field is autofilled with text "
						+ sf.seleU.getTextFromWebElement(sf.comContactUs.nameInput), true, true);
			}

			// Validate form layout
			//			verifyFieldDisplayed("Account Information Section", sf.comContactUs.accountInformationSection);
			//			verifyFieldDisplayed("Product Information Section", sf.comContactUs.productInformationSection);
			//			verifyFieldDisplayed("Add Any Additional Information Section",
			//					sf.comContactUs.addAnyAdditionalInformationSection);
			//			verifyFieldDisplayed("Add Attachments Section", sf.comContactUs.addAttachmentsSection);

			// Validate Subject field is free-text type field
			//			if ((sf.seleU.getElementAttribute(sf.comContactUs.subjectInput, "type")).equals("text")) {
			//				reportStatusPass(methodName + " Validated subject field is free-text field", false, true);
			//			} else {
			//				reportStatusFail(methodName + " Subject Field is not a free-text type field", true);
			//			}

			// Validate Description field is textarea type field (xpath conatins textarea
			// tag, xpath presence validates textarea is present)
			// verifyFieldDisplayed("Description", sf.comContactUs.descriptionInput);

			// Validate Reasons dropdown is not populated until product is selected
			//			sf.seleU.clickOnElement(sf.comContactUs.reasonDropdown);
			//			verifyFieldNotDisplayed("Reasons dopdown values", sf.comContactUs.dropdownOptions.get(0));

			// Select Case Account Name
			sf.seleU.clickOnElement(sf.comContactUs.accountNameDropdown);
			selectDropdownOption(InputData_Communities.communities_account);
			reportStatusPass(methodName + " Selected Account as " + InputData_Communities.communities_account, true,
					false);
			sf.seleU.wait(2000);

			// Select Case Product
			//			sf.seleU.clickOnElement(sf.comContactUs.productDropdown);
			//			selectDropdownOption(Global.commData.communities_CaseProduct);
			//			
			sf.seleU.clickOnElement(driver.findElement(
					By.xpath("//input[@value='" + Global.commData.communities_CaseProduct + "']/parent::span")));
			reportStatusPass(methodName + " Selected Case Product as " + Global.commData.communities_CaseProduct, true,
					false);
			sf.seleU.wait(2000);

			// Select Case Reason
			sf.seleU.clickOnElement(sf.comCaseDetails.reasonDropdown);
			selectDropdownOption(Global.commData.communities_CaseReason);
			reportStatusPass(methodName + " Selected Case Reason as " + Global.commData.communities_CaseReason, true,
					false);
			sf.seleU.wait(2000);

			// Enter Case Subject and Description
			sf.seleU.clearAndEnterText(sf.comCaseDetails.subjectInput, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.comCaseDetails.descriptionInput, Keys.TAB);
			sf.seleU.clearAndEnterText(sf.comCaseDetails.descriptionInput, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.comCaseDetails.descriptionInput, Keys.TAB);
			reportStatusPass(
					methodName + " Entered Case Subject and Description as " + InputData.caseStatusNew + addOn_1, true,
					false);
			sf.seleU.wait(2000);

			// Click on Submit Button
			sf.seleU.clickElementByJSE(sf.comCaseDetails.submitButton);
			reportStatusPass(methodName + " Clicked on Submit Button", true, false);
			sf.seleU.wait(15000);

			verifyCaseCreatedMessages();
			sf.seleU.clickElementByJSE(sf.comCaseDetails.caseSubmissionCaseSummaryLink);
			reportStatusPass(methodName + " Clicked on Case Summary Link", true, false);
			sf.seleU.wait(13000);

			// Verify Case Creation and created case details

			InputData.caseNumber = sf.seleU.getTextFromWebElement(sf.comMBC.caseSummary_caseNumberAllLinks.get(0));
			reportStatusPass(methodName + "New Created case is " + InputData.caseNumber, true, true);

			verifyFieldValue("Case Account Name", sf.comMBC.caseSummary_accountNameAllValues.get(0),
					InputData_Communities.communities_account);
			verifyFieldValue("Case Status", sf.comMBC.caseSummary_statusAllValues.get(0), InputData.caseStatusNew);
			verifyFieldValue("Case Reason", sf.comMBC.caseSummary_reasonAllValues.get(0),
					Global.commData.communities_CaseReason);
			verifyFieldValue("Case Product", sf.comMBC.caseSummary_productAllValues.get(0),
					Global.commData.communities_CaseProduct);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Creating My Business Case unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public static void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).contains(expectedText)) {
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
	 *                     Verify Field is displayed
	 */
	public static void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(methodName + " " + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param value
	 * @throws Exception
	 * 
	 *                   Select value from case creation dropdown option
	 */
	public static void selectDropdownOption(String value) throws Exception {

		sf.seleU.wait(3000);

		for (int i = 0; i < sf.comCaseDetails.dropdownOptions.size(); i++) {
			// System.out.println(i);
			if (sf.seleU.isElementDisplayed(sf.comCaseDetails.dropdownOptions.get(i))
					&& (sf.seleU.getTextFromWebElement(sf.comCaseDetails.dropdownOptions.get(i))
							.equalsIgnoreCase(value))
					|| (sf.seleU.getElementAttribute(sf.comCaseDetails.dropdownOptions.get(i), "data-value")
							.equalsIgnoreCase(value))) {
				sf.seleU.clickOnElement(sf.comCaseDetails.dropdownOptions.get(i));
				System.out.println("Selected");
				break;
			}

		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Create New case
	 * 
	 *                     Select Case Account Name
	 * 
	 *                     Select Case Product
	 * 
	 *                     Verify Reasons Dropdown List values
	 * 
	 *                     Verify Mandatory fields for specific reasons selected
	 * 
	 */
	public void verifyReasonsInCreateCase(String recordType) throws IOException {

		try {

			// Select Case Account Name
			sf.seleU.clickOnElement(sf.comContactUs.accountNameDropdown);
			selectDropdownOptionFromElement(sf.comContactUs.accountNameOptions,
					InputData_Communities.commCaseAccountName);
			reportStatusPass(methodName + " Selected Account as " + InputData_Communities.commCaseAccountName, true,
					true);
			sf.seleU.wait(4000);

			// Set Mapping for Product and corresponding Reasons list,
			if (recordType.equalsIgnoreCase(InputData_Communities.caseTypeTechnicalSupport))
				Global.commData.setComm_CaseProduct_Reason_MappingTechnicalSupport();
			else
				Global.commData.setComm_CaseProduct_Reason_MappingService();

			// Set Mapping for mandatory fields validations based on Reason selected
			Global.commData.setMandatoryField_Validation_List();

			// Loop over all products one by one and verify Reasons list dynamically loaded
			Iterator mappingList = InputData_Communities.communities_CaseProduct_Reason_Mapping.entrySet().iterator();
			while (mappingList.hasNext()) {

				Map.Entry productReasonPair = (Map.Entry) mappingList.next();
				String productName = productReasonPair.getKey().toString();
				List<String> actualReasonValues = new ArrayList<>();
				List<String> expectedReasonValues = (List<String>) productReasonPair.getValue();
				sf.seleU.wait(5000);
				// Select Product
				sf.seleU.clickElementByJSE(driver.findElement(
						By.xpath("//input[@value='" + productName + "']/parent::span")));

				sf.seleU.clickOnElement(driver.findElement(
						By.xpath("//input[@value='" + productName + "']/parent::span")));
				reportStatusPass(methodName + " Selected Case Product as " + productName, true,
						true);
				sf.seleU.wait(2000);

				// Select Service Type :
				if (recordType.equalsIgnoreCase(InputData_Communities.commCaseBilling_TechSupport)) {
					if (sf.seleU.isElementDisplayed(sf.comCaseDetails.billingAndAccountMgtCaseType))
						sf.seleU.clickOnElement(sf.comCaseDetails.billingAndAccountMgtCaseType);
				} else {
					if (sf.seleU.isElementDisplayed(sf.comCaseDetails.technicalSupportCaseType))
						sf.seleU.clickOnElement(sf.comCaseDetails.technicalSupportCaseType);
				}

				sf.seleU.wait(2000);
				for (int i = 0; i < sf.comCaseDetails.reasonsList.size(); i++) {
					actualReasonValues
					.add(sf.seleU.getTextFromWebElement(sf.comCaseDetails.reasonsList.get(i)));
				}
				sf.seleU.wait(2000);

				// Sort expected and actualValues list to compare
				Collections.sort(expectedReasonValues);
				Collections.sort(actualReasonValues);

				// Verify Lists for expected and actual reasons are equal
				if (expectedReasonValues.equals(actualReasonValues)) {
					reportStatusPass(methodName + " All expected reasons are present in Reason Dropdown for Product : "
							+ productName + " , Expected Reasons --> "
							+ AdditionalUtilities.getAsString(expectedReasonValues) + "  Actual Reasons --> "
							+ AdditionalUtilities.getAsString(actualReasonValues), true, true);
				} else {
					reportStatusFail(methodName
							+ " All expected reasons are not present in Reason Dropdown for Product : " + productName
							+ " , Expected Reasons --> " + AdditionalUtilities.getAsString(expectedReasonValues)
							+ "  Actual Reasons --> " + AdditionalUtilities.getAsString(actualReasonValues), true);
				}

				mappingList.remove(); // avoids a ConcurrentModificationException

				//Out of scope for now
				// Validate Mandatory Fields for specific reasons selected
				//				if (actualReasonValues.containsAll(
				//						InputData_Communities.communities_CaseProd_Reason_MandatoryField_Validations.get("All")))
				//					verifyMandatoryFieldForAllProducts(productName);

			}

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying Reasons list based on product unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify mandatory fields for BillCopy/BillInquiry selected
	 *                     for all products
	 * 
	 */
	private void verifyMandatoryFieldForAllProducts(String productName) throws IOException {

		try {

			// Extract List of mandatory fields to be checked
			Iterator mappingList = InputData_Communities.communities_CaseProd_Reason_MandatoryField_Validations
					.entrySet().iterator();
			while (mappingList.hasNext()) {

				Map.Entry productReasonPair = (Map.Entry) mappingList.next();
				String inputDataProductName = productReasonPair.getKey().toString();
				List<String> reasonsToBeSelected = (List<String>) productReasonPair.getValue();

				// Verify for all product types
				if (inputDataProductName.equals("All")) {
					// Loop over Reasons list
					for (int i = 0; i < reasonsToBeSelected.size(); i++) {
						// Select Reason from reason dropdown
						sf.seleU.clickOnElement(sf.comCaseDetails.reasonDropdown);
						selectDropdownOption(reasonsToBeSelected.get(i));
						reportStatusPass(methodName + " Selected Case Reason as " + reasonsToBeSelected.get(i)
						+ " under product " + productName, true, false);
						sf.seleU.wait(2000);
						verifyFieldIsRequired("Account Number", sf.comContactUs.accountNumberInput);
						verifyFieldIsRequired("Bill Month", sf.comContactUs.invoiceMonth);
					}
				} else if (inputDataProductName.equals(productName)) {

					// Verify mandatory fields for Wireless Product, select Unlock Device
					sf.seleU.clickOnElement(sf.comCaseDetails.reasonDropdown);
					selectDropdownOption(reasonsToBeSelected.get(0));
					reportStatusPass(methodName + " Selected Case Reason as " + reasonsToBeSelected.get(0)
					+ " under product " + productName, true, false);
					sf.seleU.wait(2000);
					verifyFieldIsRequired("IMEI Number", sf.comContactUs.imeiNumberInput.get(0));

					// Verify mandatory fields for Wireless Product, select Reports
					sf.seleU.clickOnElement(sf.comCaseDetails.reasonDropdown);
					selectDropdownOption(reasonsToBeSelected.get(1));
					reportStatusPass(methodName + " Selected Case Reason as " + reasonsToBeSelected.get(1)
					+ " under product " + productName, true, false);
					sf.seleU.wait(2000);

					verifyFieldIsRequired("Report Type", sf.comContactUs.reportsTypeDropdown);
					verifyFieldIsRequired("Account Number", sf.comContactUs.accountNumberInput);

					// Extract Report Type list for validation
					List<String> actualReportTypeValues = new ArrayList<>();
					List<String> expectedReportValues = Global.commData.setComm_Case_ReportTypes();

					// Click Report type dropdown
					sf.seleU.clickOnElement(sf.comContactUs.reportsTypeDropdown);

					// Loop over dropdown options to validate text
					for (int i = 0; i < sf.comCaseDetails.dropdownOptions.size(); i++) {

						if (sf.seleU.isElementDisplayed(sf.comCaseDetails.dropdownOptions.get(i))) {
							actualReportTypeValues
							.add(sf.seleU.getTextFromWebElement(sf.comCaseDetails.dropdownOptions.get(i)));
						}
					}
					sf.seleU.wait(2000);

					// Sort expected and actualValues list to compare
					Collections.sort(expectedReportValues);
					Collections.sort(actualReportTypeValues);

					// Verify Lists for expected and actual report types are equal
					if (expectedReportValues.equals(actualReportTypeValues)) {
						reportStatusPass(methodName + " All expected report types are present for Product : "
								+ productName + " , Reason: " + reasonsToBeSelected.get(1)
								+ ", Expected Report Types --> " + AdditionalUtilities.getAsString(expectedReportValues)
								+ "  Actual Report Types --> "
								+ AdditionalUtilities.getAsString(actualReportTypeValues), true, true);
					} else {
						reportStatusFail(methodName
								+ " All expected report types are not present in Reason Dropdown for Product : "
								+ productName + " , Reason: " + reasonsToBeSelected.get(1)
								+ " , Expected Report Types --> "
								+ AdditionalUtilities.getAsString(expectedReportValues) + "  Actual Report Types --> "
								+ AdditionalUtilities.getAsString(actualReportTypeValues), true);
					}

				}
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying mandatory fields based on product and reason unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     /**
	 * @param field
	 * @throws IOException
	 * 
	 *                     verify given field is mandatory fields
	 */
	public void verifyFieldIsRequired(String fieldName, WebElement field) throws IOException {

		try {

			if (field.getAttribute("required").equals("true")) {
				reportStatusPass(methodName + " Validated " + fieldName + " field is required field", true, true);
			} else {
				reportStatusFail(
						methodName + " Field " + fieldName + "is not a mandatory field, It should be a mandatory field",
						true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in validating required field", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Case Creation messages
	 * 
	 *                     Text 1: You have successfully created a new case. Text 2:
	 *                     To create another new case, please click below. Text 3:
	 *                     Create New Case
	 * 
	 */
	public static void verifyCaseCreatedMessages() throws IOException {

		try {
			verifyFieldDisplayed("Case Creation Thank You Message", sf.comCaseDetails.caseCreationThankYouMessage);
			verifyFieldDisplayed("Create another case message", sf.comContactUs.createAnotherCaseMessage);
			verifyFieldDisplayed("Create New Case Link", sf.comMBC.createNewCasesLink);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying text messages after case creation unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Create New case
	 * 
	 *                     Select Case Account Name
	 * 
	 *                     Select Case Product as Wireless
	 * 
	 *                     Verify Unlock Device is a Reason in dropdown list of
	 *                     Reasons
	 * 
	 *                     Select Case Reason As Unlock Device
	 * 
	 *                     Validate additional fields: 1.IMEI (i)Field label: IMEI
	 *                     (ii)Field type: Numeric field (iii)Field
	 *                     validations/limitations: IMEI hash test to confirm its a
	 *                     valid IMEI 2.Ability for the customer to "+" (add)
	 *                     additional IMEIs (i)Customer to be able to add up to 5
	 *                     IMEIs 3.Ability for the customer to "-" (subtract) an
	 *                     additional IMEI (i)Cannot remove all IMEIs (leave at
	 *                     least 1) 4.IMEI-Note: (i)Field label: Please Note
	 *                     (ii)Field type: Uneditable text area (iii)Field value:If
	 *                     $50 Fee is applicable to your account, you will receive a
	 *                     notification prior to the completion of this request.
	 * 
	 *                     Enter Case Subject and Description
	 * 
	 *                     Click on Submit Button
	 * 
	 *                     Verify Case Creation and created case details
	 */
	public void createWirelessUnlockDeviceCaseWithValidations() throws IOException {

		try {
			sf.seleU.wait(10000);

			// Select Case Account Name
			sf.seleU.clickOnElement(sf.comContactUs.accountNameDropdown);
			selectDropdownOptionFromElement(sf.comContactUs.accountNameOptions,
					InputData_Communities.commCaseAccountName);
			reportStatusPass(methodName + " Selected Account as " + InputData_Communities.commCaseAccountName, true,
					true);
			sf.seleU.wait(4000);

			// Select Product
			sf.seleU.clickElementByJSE(driver.findElement(
					By.xpath("//input[@value='" + InputData_Communities.communities_Case_Product_Wireless + "']/parent::span")));

			sf.seleU.clickOnElement(driver.findElement(
					By.xpath("//input[@value='" + InputData_Communities.communities_Case_Product_Wireless + "']/parent::span")));
			reportStatusPass(methodName + " Selected Case Product as " + InputData_Communities.communities_Case_Product_Wireless, true,
					true);
			sf.seleU.wait(2000);

			verifyFieldDisplayed("Billing And Account Management Service Type",
					sf.comCaseDetails.billingAndAccountMgtCaseType);
			sf.seleU.clickOnElement(sf.comCaseDetails.billingAndAccountMgtCaseType);


			// Select Reason
			sf.seleU.clickOnElement(driver.findElement(
					By.xpath("//input[@value='" + InputData_Communities.communities_Case_Reason_Unlock_Device + "']/parent::span")));
			reportStatusPass(methodName + " Selected Case Reason as " + InputData_Communities.communities_Case_Reason_Unlock_Device, true,
					true);
			sf.seleU.wait(2000);
			// Call function to validate IMEI field, fill it at the end
			validateIMEIAndFill();

			verifyFieldDisplayed("IMEI Number", sf.comContactUs.imeiNumberInput.get(0));
			verifyFieldIsRequired("IMEI Number", sf.comContactUs.imeiNumberInput.get(0));
			sf.seleU.clearAndEnterText(sf.comContactUs.imeiNumberInput.get(0),
					InputData_Communities.commCaseIMEINumberForSpecificCase);
			sf.seleU.enterText(sf.comContactUs.imeiNumberInput.get(0), Keys.TAB);
			reportStatusPass(methodName + " Entered IMEI number : " + InputData_Communities.commCaseIMEINumberForSpecificCase,
					true, true);

			// Fill Details
			sf.seleU.clearAndEnterText(sf.comCaseDetails.additionalDetailsTextArea, InputData.caseStatusNew + addOn_1);
			reportStatusPass(methodName + " Entered Case Description as " + InputData.caseStatusNew + addOn_1, true,
					true);
			sf.seleU.enterText(sf.comCaseDetails.additionalDetailsTextArea, Keys.TAB);

			sf.seleU.wait(7000);
			// Click on Create Case

			sf.seleU.clickElementByJSE(sf.comCaseDetails.createCaseButton);
			sf.seleU.wait(18000);

			// Verify Success Message
			verifyFieldDisplayed("Case creation success Message", sf.comCaseDetails.caseCreatedSuccessMessage);
			verifyFieldDisplayed("Case Number", sf.comCaseDetails.caseNumberOnCaseCreation);

			// Store case number for future ref
			InputData_Communities.caseNumber = sf.seleU
					.getTextFromWebElement(sf.comCaseDetails.caseNumberOnCaseCreation).replace("#", "");
			reportStatusPass(methodName + " Saved Case Number for future ref ( Sl No: "
					+ InputData_Communities.commCaseSlNo + ": ) " + InputData_Communities.caseNumber, false, false);

			if (InputData_Communities.commCaseSlNo != -1)
				saveTestData(Constants.TESTDATA_COMM_FILE, Constants.W2C_SERVICE_SHEET,
						InputData_Communities.commCaseNumberColumn, InputData_Communities.caseNumber,
						InputData_Communities.commCaseSlNo + 1);

			Communities_Home_Page communities_Home_Page = new Communities_Home_Page();
			communities_Home_Page.openCommunityCases();
			comCaseDetails.myBusinessCasesViewCaseDetails(InputData_Communities.communities_Case_Product_Wireless,InputData_Communities.communities_Case_Reason_Unlock_Device);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Creating My Business Case for Unlock Device unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Create New case
	 * 
	 *                     Select Case Account Name
	 * 
	 *                     Select Case Product as Wireless
	 * 
	 *                     Verify Bill Inquiry is a Reason in dropdown list of
	 *                     Reasons
	 * 
	 *                     Select Case Reason As Bill Inquiry
	 * 
	 *                     Validate additional fields " BAN/CTN" & "Bill Month" are
	 *                     added
	 * 
	 *                     Validate following for CTN/BAN: (i)Field label: Account
	 *                     Number or Phone Number (ii)Field type: Numeric field
	 *                     (iii)Field validations/limitations: Numbers, spaces and
	 *                     dashes accepted only
	 * 
	 *                     Select the account number/phone number from the drop down
	 * 
	 *                     Validate following for Bill Month (i)Field label: Invoice
	 *                     Month (ii)Field type: Picklist (iii)Field values: Last 1
	 *                     month Last 3 months Last 6 months Last 12 months Last 18
	 *                     months
	 * 
	 *                     Enter Case Subject and Description
	 * 
	 *                     Click on Submit Button
	 * 
	 *                     Verify Case Creation and created case details
	 */
	public void createBillInquiryCaseWithValidations() throws IOException {

		try {
			sf.seleU.wait(10000);

			// Click on Create New case
			sf.seleU.clickElementByJSE(sf.comMBC.createNewCasesLink);
			reportStatusPass(methodName + " Clicked on create new case", false, false);
			sf.seleU.wait(7000);

			// Call function to fill form for new case
			createCaseFillForm(InputData_Communities.communities_Case_Product_Wireless,
					InputData_Communities.communities_Case_Reason_Bill_Inquiry);
			sf.seleU.wait(2000);

			// Call function to validate CTN/BAN field present and their corresponding field
			// validations, fill it at the end
			validateCTNBANandFill();

			// Call function to validate and fill bill month
			validateBillMonthAndFill();

			// Call function to sumbit form and verify success messages
			sumbitCaseAndVerifySuccess(InputData_Communities.communities_Case_Product_Wireless,
					InputData_Communities.communities_Case_Reason_Bill_Inquiry);

			comCaseDetails.myBusinessCasesViewCaseDetails(InputData_Communities.communities_Case_Product_Wireless,
					InputData_Communities.communities_Case_Reason_Bill_Inquiry);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Creating My Business Case for Bill Inquiry unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Create New case
	 * 
	 *                     Select Case Account Name
	 * 
	 *                     Select Case Product as Wireless
	 * 
	 *                     Verify Bill Copy is a Reason in dropdown list of Reasons
	 * 
	 *                     Select Case Reason As Bill Copy
	 * 
	 *                     Validate additional fields " BAN/CTN" & "Bill Month" are
	 *                     added
	 * 
	 *                     Validate following for CTN/BAN: (i)Field label: Account
	 *                     Number or Phone Number (ii)Field type: Numeric field
	 *                     (iii)Field validations/limitations: Numbers, spaces and
	 *                     dashes accepted only
	 * 
	 *                     Select the account number/phone number from the drop down
	 * 
	 *                     Validate following for Bill Month (i)Field label: Invoice
	 *                     Month (ii)Field type: Picklist (iii)Field values: Last 1
	 *                     month Last 3 months Last 6 months Last 12 months Last 18
	 *                     months
	 * 
	 *                     Enter Case Subject and Description
	 * 
	 *                     Click on Submit Button
	 * 
	 *                     Verify Case Creation and created case details
	 */
	public void createBillCopyCaseWithValidations() throws IOException {

		try {
			sf.seleU.wait(10000);

			// Click on Create New case
			sf.seleU.clickElementByJSE(sf.comMBC.createNewCasesLink);
			reportStatusPass(methodName + " Clicked on create new case", false, false);
			sf.seleU.wait(7000);

			// Call function to fill form for new case
			createCaseFillForm(InputData_Communities.communities_Case_Product_Wireless,
					InputData_Communities.communities_Case_Reason_Bill_Copy);
			sf.seleU.wait(2000);

			// Call function to validate CTN/BAN field present and their corresponding field
			// validations, fill it at the end
			validateCTNBANandFill();

			// Call function to validate and fill bill month
			validateBillMonthAndFill();

			// Call function to sumbit form and verify success messages
			sumbitCaseAndVerifySuccess(InputData_Communities.communities_Case_Product_Wireless,
					InputData_Communities.communities_Case_Reason_Bill_Copy);

			comCaseDetails.myBusinessCasesViewCaseDetails(InputData_Communities.communities_Case_Product_Wireless,
					InputData_Communities.communities_Case_Reason_Bill_Copy);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Creating My Business Case for Bill Inquiry unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Create New case
	 * 
	 *                     Select Case Account Name
	 * 
	 *                     Select Case Product as given in argument1
	 * 
	 *                     Verify Reason present as given in argument2
	 * 
	 *                     Select Case Reason As argument2
	 * 
	 *                     Enter Case Subject and Description
	 * 
	 */
	public void createCaseFillForm(String productName, String reasonName) throws IOException {

		boolean reasonFound = false;

		try {

			// Select Case Account Name
			sf.seleU.clickOnElement(sf.comContactUs.accountNameDropdown);
			sf.seleU.wait(4000);
			selectDropdownOption(InputData_Communities.commCaseAccountName);
			reportStatusPass(methodName + " Selected Account as " + InputData_Communities.communities_account, true,
					false);
			sf.seleU.wait(2000);

			// Select Case Product
			sf.seleU.clickOnElement(sf.comCaseDetails.productDropdown);
			selectDropdownOption(productName);
			reportStatusPass(methodName + " Selected Case Product as " + productName, true, false);
			sf.seleU.wait(2000);

			// Validate reasonName is populated in Reasons list
			sf.seleU.clickOnElement(sf.comCaseDetails.reasonDropdown);
			for (int i = 0; i < sf.comCaseDetails.dropdownOptions.size(); i++) {

				if (sf.seleU.isElementDisplayed(sf.comCaseDetails.dropdownOptions.get(i))) {
					if (sf.seleU.getTextFromWebElement(sf.comCaseDetails.dropdownOptions.get(i)).equals(reasonName)) {
						reasonFound = true;
						break;
					}
				}
			}

			if (reasonFound)
				reportStatusPass(methodName + " Expected reason " + reasonName
						+ " is present in reason dropdown for Product : " + productName, true, true);
			else
				reportStatusFail(methodName + " Expected reason " + reasonName
						+ " is not present in reason dropdown for Product : " + productName, true);

			sf.seleU.wait(2000);

			// Select Case Reason
			selectDropdownOption(reasonName);
			reportStatusPass(methodName + " Selected Case Reason as " + reasonName, true, false);
			sf.seleU.wait(2000);

			// Enter Case Subject and Description
			sf.seleU.clearAndEnterText(sf.comCaseDetails.subjectInput, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.comCaseDetails.descriptionInput, Keys.TAB);
			sf.seleU.clearAndEnterText(sf.comCaseDetails.descriptionInput, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.comCaseDetails.descriptionInput, Keys.TAB);
			reportStatusPass(
					methodName + " Entered Case Subject and Description as " + InputData.caseStatusNew + addOn_1, true,
					false);
			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Filling form for My Business Case unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate IMEI field: 1.IMEI (i)Field label: IMEI
	 *                     (ii)Field type: Numeric field (iii)Field
	 *                     validations/limitations: IMEI hash test to confirm its a
	 *                     valid IMEI 2.Ability for the customer to "+" (add)
	 *                     additional IMEIs (i)Customer to be able to add up to 5
	 *                     IMEIs 3.Ability for the customer to "-" (subtract) an
	 *                     additional IMEI (i)Cannot remove all IMEIs (leave at
	 *                     least 1) 4.IMEI-Note: (i)Field label: Please Note
	 *                     (ii)Field type: Uneditable text area (iii)Field value:If
	 *                     $50 Fee is applicable to your account, you will receive a
	 *                     notification prior to the completion of this request.
	 * 
	 */
	public void validateIMEIAndFill() throws IOException {

		try {
			// Scroll to IMEI field
			sf.seleU.scrollByCoOrdinates(1);

			// Verify IMEI field present
			verifyFieldDisplayed("IMEI Number", sf.comContactUs.imeiNumberInput.get(0));

			// Verify IMEI field Label
			verifyFieldDisplayed("IMEI Number Label", sf.comContactUs.imeiNumberLabel);

			// Verify IMEI field is of numeric type
			verifyFieldAcceptsOnlyNumeric("IMEI Number", sf.comContactUs.imeiNumberInput.get(0));

			// Verify Error is displayed on filling invalid IMEI number
			sf.seleU.clearAndEnterText(sf.comContactUs.imeiNumberInput.get(0),
					AdditionalUtilities.generateRandomDigits(3));
			sf.seleU.enterText(sf.comContactUs.imeiNumberInput.get(0), Keys.TAB);
			reportStatusPass(methodName + "Entered invalid IMEI number to verify error message is displayed or not. ",
					true, false);
			verifyFieldDisplayed("Invalid IMEI Error Message", sf.comContactUs.invalidIMEIErrorMessage);
			sf.seleU.wait(2000);

			// Fill valid IMEI NUmber and verify error is removed
			sf.seleU.clearAndEnterText(sf.comContactUs.imeiNumberInput.get(0),
					InputData_Communities.communities_Case_IMEI_Number);
			sf.seleU.enterText(sf.comContactUs.imeiNumberInput.get(0), Keys.TAB);
			reportStatusPass(
					methodName + " Entered valid IMEI number to verify error message is removed/not displayed. ", true,
					false);
			verifyFieldNotDisplayed("Invalid IMEI Error Message", sf.comContactUs.invalidIMEIErrorMessage);
			sf.seleU.wait(2000);

			verifyFieldDisplayed("IMEI Number field (Field No. : 1 )", sf.comContactUs.imeiNumberInput.get(0));

			// Add 5 IMEI numbers and validate not more than 5 can be entered
			for (int i = 2; i < 6; i++) {
				sf.seleU.clickOnElement(sf.comContactUs.addImeiButton);
				sf.seleU.wait(3000);
				if (sf.comContactUs.imeiNumberInput.size() > i)
					;
				reportStatusPass(methodName + " Added new IMEI number field (Field No. : " + i + " ) ", true, false);
				sf.seleU.wait(2000);
			}

			// Add button should not be displayed after adding 5 IMEIs which will verify one
			// cannot add more than 5 IMEIs
			verifyFieldNotDisplayed("Adding more IMEI numbers option", sf.comContactUs.addImeiButton);

			// Delete 4 IMEIs and validate cannot delete 5th one as atleast one should be
			// left
			for (int i = 5; i > 1; i--) {
				sf.seleU.clickOnElement(sf.comContactUs.deleteImeiButton);
				sf.seleU.wait(2000);
				reportStatusPass(methodName + " Deleted IMEI number. (Field No. : " + i + " ) ", true, false);
			}

			// Delete button should not be displayed after subtracting 4 IMEIs which will
			// verify atleast one IMEI Number should be present
			verifyFieldNotDisplayed("Deleting more IMEI numbers option", sf.comContactUs.deleteImeiButton);

			// Verify Note message for IMEI
			verifyFieldValue("IMEI Please Note Message", sf.comContactUs.imeiPleaseNoteMessage,
					InputData_Communities.imeiPleaseNoteMessage);

			// Fill IMEI number
			sf.seleU.clearAndEnterText(sf.comContactUs.imeiNumberInput.get(0),
					InputData_Communities.communities_Case_IMEI_Number);
			sf.seleU.enterText(sf.comContactUs.imeiNumberInput.get(0), Keys.TAB);
			reportStatusPass(methodName + " Entered valid IMEI number.", true, false);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Entering/Validating IMEI number field unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate CTN/BAN account number/phone number field and
	 *                     fill it with random 9 digit number
	 */
	public void validateCTNBANandFill() throws IOException {

		try {

			// Verify CTN/BAN field present and their corresponding field validations
			verifyFieldDisplayed("Account number/Phone Number", sf.comContactUs.accountNumberInput);
			verifyFieldValue("Label for account number", sf.comContactUs.accountNumberLabel,
					InputData_Communities.accountNumberLabelText);
			// extra validations to be taken later on , defect raised for numeric,spaces
			// validations

			// Input Account number/Phone Number
			sf.seleU.enterText(sf.comContactUs.accountNumberInput, AdditionalUtilities.generateRandomDigits(9));
			reportStatusPass(methodName + "Entered account number/Phone Number value ", true, false);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Entering/Validating Account/Phone number field unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate Bill Month field and select first value from
	 *                     list
	 */
	public void validateBillMonthAndFill() throws IOException {

		try {

			// Verify Invoice Month field present, label and list values
			verifyFieldDisplayed("Bill Month", sf.comContactUs.invoiceMonth);
			verifyFieldValue("label for bill/invoice month", sf.comContactUs.invoiceMonthLabel,
					InputData_Communities.invoiceMonthLabel);

			// Extract Bill Month List values and verify with expected list
			List<String> actualBillMonthValues = new ArrayList<>();
			List<String> expectedBillMonthValues = Global.commData.setBillMonthList();

			sf.seleU.clickOnElement(sf.comContactUs.invoiceMonth);
			for (int i = 0; i < sf.comCaseDetails.billMonthOptions.size(); i++) {

				if (sf.seleU.isElementDisplayed(sf.comCaseDetails.billMonthOptions.get(i))) {
					actualBillMonthValues
					.add(sf.seleU.getTextFromWebElement(sf.comCaseDetails.billMonthOptions.get(i)));
				}
			}
			sf.seleU.wait(2000);

			// Sort expected and actualValues list to compare
			Collections.sort(expectedBillMonthValues);
			Collections.sort(actualBillMonthValues);

			// Verify Lists for expected and actual values are equal
			if (expectedBillMonthValues.equals(actualBillMonthValues)) {
				reportStatusPass(
						methodName + " All bill month values are present in Bill Month dropdown  , Expected Values --> "
								+ AdditionalUtilities.getAsString(expectedBillMonthValues) + "  Actual Values --> "
								+ AdditionalUtilities.getAsString(actualBillMonthValues),
								true, true);
			} else {
				reportStatusFail(methodName
						+ " All bill month values are not present in Bill Month dropdown  , Expected Values --> "
						+ AdditionalUtilities.getAsString(expectedBillMonthValues) + "  Actual Values --> "
						+ AdditionalUtilities.getAsString(actualBillMonthValues), true);
			}

			// Select Bill Month

			selectDropdownOptionFromElement(sf.comCaseDetails.billMonthOptions, actualBillMonthValues.get(0));

			reportStatusPass(methodName + " Selected bill month as " + actualBillMonthValues.get(0), true, false);
			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Entering/Validating bill month field unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Submit for case creation
	 * 
	 *                     Verify Case Creation
	 */
	public static void sumbitCaseAndVerifySuccess(String productName, String reasonName) throws IOException {

		try {

			// Click on Submit Button
			sf.seleU.clickElementByJSE(sf.comCaseDetails.submitButton);
			reportStatusPass(methodName + " Clicked on Submit Button", true, false);
			sf.seleU.wait(12000);

			verifyCaseCreatedMessages();

			sf.seleU.clickElementByJSE(sf.comCaseDetails.caseSubmissionCaseSummaryLink);
			reportStatusPass(methodName + " Clicked on Case Summary Link", true, false);
			sf.seleU.wait(15000);

			// Verify Case Creation and created case details

			InputData.caseNumber = sf.seleU.getTextFromWebElement(sf.comMBC.caseSummary_caseNumberAllLinks.get(0));
			reportStatusPass(methodName + "New Created case is " + InputData.caseNumber, true, true);

			verifyFieldValue("Case Account Name", sf.comMBC.caseSummary_accountNameAllValues.get(0),
					InputData_Communities.communities_account);
			verifyFieldValue("Case Status", sf.comMBC.caseSummary_statusAllValues.get(0), InputData.caseStatusNew);
			verifyFieldValue("Case Reason", sf.comMBC.caseSummary_reasonAllValues.get(0), reasonName);
			verifyFieldValue("Case Product", sf.comMBC.caseSummary_productAllValues.get(0), productName);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Submitting form and verifying success for case creation unsuccessful", e);
			e.printStackTrace();
		}
	}


	/**
	 * @throws IOException
	 * 
	 *                     Click on Create New case
	 * 
	 *                     Click on dropdown for Account Name
	 * 
	 *                     Validate dropdown contains only business accounts
	 * 
	 */
	public void verifyBusinessAccountsInAccountNameDropdown() throws IOException {

		try {

			//sf.seleU.wait(40000);
			// Click on Create New case
			navigateToCreateCasePage();

			// Select Case Account Name
			sf.seleU.clickOnElement(sf.comContactUs.accountNameDropdown);
			sf.seleU.wait(2000);
			reportStatusPass(methodName + " Clicked on Account Name dropdown", true, false);

			// Set expected account name values from inputdata
			List<String> expectedAccountNameValues = InputData_Communities.communitiesBusinessAccounts;

			List<String> actualAccountNameValues = new ArrayList<>();

			for (int i = 0; i < sf.comContactUs.accountNameOptions.size(); i++) {

				if (sf.seleU.isElementDisplayed(sf.comContactUs.accountNameOptions.get(i))) {
					actualAccountNameValues
					.add(sf.seleU.getTextFromWebElement(sf.comContactUs.accountNameOptions.get(i)));
				}
			}
			sf.seleU.wait(2000);

			// Sort expected and actualValues list to compare
			Collections.sort(expectedAccountNameValues);
			Collections.sort(actualAccountNameValues);

			// Verify List for expected and actual account names is equal
			if (expectedAccountNameValues.equals(actualAccountNameValues)) {
				reportStatusPass(
						methodName + " All accounts present in dropdown are Business Accounts, Expected Accounts --> "
								+ AdditionalUtilities.getAsString(expectedAccountNameValues) + "  Actual Accounts --> "
								+ AdditionalUtilities.getAsString(actualAccountNameValues),
								true, true);
			} else {
				reportStatusFail(methodName
						+ " All accounts are not business accounts in Account Name dropdown, Expected Accounts --> "
						+ AdditionalUtilities.getAsString(expectedAccountNameValues) + "  Actual Accounts --> "
						+ AdditionalUtilities.getAsString(actualAccountNameValues), true);
			}

			// Close dropdown
			sf.seleU.clickOnElement(sf.comContactUs.accountNameDropdown);

			// CLose browser and switch back to main window
			sf.seleU.closeRecentlyOpenedWindow();

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying business account names in case creation unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify field is not displayed
	 */
	public void verifyFieldNotDisplayed(String fieldName, WebElement element) throws IOException {

		try {
			// Verify field is not displayed
			if (!sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + " Verified " + fieldName + " is not displayed", true, true);
			} else {
				reportStatusFail(methodName + " " + fieldName + " is displayed, It should not be displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify field accepts only numeric data
	 */
	public static void verifyFieldAcceptsOnlyNumeric(String fieldName, WebElement element) throws IOException {

		try {

			// Send Alphabetic value and verify If the field is accepting or not
			sf.seleU.enterText(element, AdditionalUtilities.generateRandomCharacters(3));
			sf.seleU.wait(2000);
			// Retrieve typed value
			String typedValue = sf.seleU.getElementAttribute(element, "value");

			// Get the length of typed value
			int size = typedValue.length();

			if (size == 0) {
				reportStatusPass(methodName + " Verified " + fieldName + " does not accepts alphabets", true, true);
			} else {
				reportStatusFail(methodName + " " + fieldName
						+ " is accepting alphabets, It should not be accepting alphabets value", true);
			}

			sf.seleU.wait(2000);

			// Type special Characters
			sf.seleU.clearAndEnterText(element, "#%$%&");
			sf.seleU.wait(2000);

			// Retrieve typed value
			typedValue = sf.seleU.getElementAttribute(element, "value");

			// Get the length of typed value
			size = typedValue.length();

			if (size == 0) {
				reportStatusPass(methodName + " Verified " + fieldName + " does not accepts special characters", true,
						true);
			} else {
				reportStatusFail(
						methodName + " " + fieldName
						+ " is accepting special characters, It should not be accepting special characters",
						true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Numeric Field verification", e);
			e.printStackTrace();
		}
	}

	// Below are the new functions created according to Digital Scrum and for
	// Multiset data from excel
	/**
	 * @throws IOException
	 * 
	 *                     Click on Create New case
	 * 
	 *                     Validate case creation form present
	 * 
	 * 
	 */
	public void navigateToCreateCasePage() throws IOException {

		try {

			if (sf.seleU.isElementPresent(sf.comHome.communitiesBadgeViewCases)) {
				sf.seleU.clickElementByJSE(sf.comHome.communitiesBadgeViewCases);
				sf.seleU.wait(8000);
				sf.seleU.switchWindow(2);
			}
			sf.seleU.wait(40000);
			for (int i = 0; i < 20; i++) {
				sf.seleU.scrollByCoOrdinates(1);
				if (sf.seleU.isElementPresent(sf.comMBC.createNewCasesLink))
					break;
			}


			// Click on Create a case
			sf.seleU.clickElementByJSE(sf.comMBC.createNewCasesLink);
			sf.seleU.wait(10000);

			// Verify landing page
			verifyFieldDisplayed("Create Case Header", sf.comCaseDetails.createCaseHeader);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in navigating to create case page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Fill Create Case Form with details (service)
	 * 
	 * 
	 */
	public void createServiceCase() throws IOException {

		try {

			// Verify landing page
			verifyFieldDisplayed("Create Case Header", sf.comCaseDetails.createCaseHeader);

			reportStatusPass(methodName + " Creating Case of type :" + InputData_Communities.commCaseServiceGroup + "-"
					+ InputData_Communities.commCaseRecordType, true, true);

			// Select Case Account Name
			sf.seleU.clickOnElement(sf.comContactUs.accountNameDropdown);
			selectDropdownOptionFromElement(sf.comContactUs.accountNameOptions,
					InputData_Communities.commCaseAccountName);
			reportStatusPass(methodName + " Selected Account as " + InputData_Communities.commCaseAccountName, true,
					true);
			sf.seleU.wait(5000);

			// Select Product
			sf.seleU.clickElementByJSE(driver.findElement(
					By.xpath("//input[@value='" + InputData_Communities.commCaseProduct + "']/parent::span")));

			sf.seleU.clickOnElement(driver.findElement(
					By.xpath("//input[@value='" + InputData_Communities.commCaseProduct + "']/parent::span")));
			reportStatusPass(methodName + " Selected Case Product as " + InputData_Communities.commCaseProduct, true,
					true);
			sf.seleU.wait(2000);

			// Select Service Type :
			if (!InputData_Communities.commCaseBilling_TechSupport.equalsIgnoreCase("NA")) {
				verifyFieldDisplayed("Billing And Account Management Service Type",
						sf.comCaseDetails.billingAndAccountMgtCaseType);
				sf.seleU.clickOnElement(sf.comCaseDetails.billingAndAccountMgtCaseType);
			}
			sf.seleU.scrollByCoOrdinates(1);
			// Select Reason
			sf.seleU.clickOnElement(driver.findElement(
					By.xpath("//input[@value='" + InputData_Communities.commCaseReason + "']/parent::span")));
			reportStatusPass(methodName + " Selected Case Reason as " + InputData_Communities.commCaseReason, true,
					true);
			sf.seleU.wait(2000);

			if (!InputData_Communities.commCaseDoFormValidations.equalsIgnoreCase("No"))
				validateCaseFormLayoutForService();

			// Fill Phone Number
			if (!InputData_Communities.commCaseAccPhoneNumber.equalsIgnoreCase("NA")) {
				if (sf.seleU.isElementDisplayed(sf.comContactUs.accountNumberInput)) {
					verifyFieldDisplayed("Account number/Phone Number", sf.comContactUs.accountNumberInput);
					verifyFieldIsRequired("Account Number", sf.comContactUs.accountNumberInput);
					// Input Account number/Phone Number
					sf.seleU.clickOnElement(sf.comContactUs.accountNumberInput);
					sf.seleU.enterText(sf.comContactUs.accountNumberInput,
							InputData_Communities.commCaseAccPhoneNumber.substring(0, 1));
					
					
				} else {
					verifyFieldDisplayed("Account number/Phone Number", sf.comContactUs.billingAccountNumberInput);
					verifyFieldIsRequired("Account Number", sf.comContactUs.billingAccountNumberInput);
					// Input Account number/Phone Number
					sf.seleU.clickOnElement(sf.comContactUs.billingAccountNumberInput);
					sf.seleU.enterText(sf.comContactUs.billingAccountNumberInput,
							InputData_Communities.commCaseAccPhoneNumber.substring(0, 1));
				}
				sf.seleU.wait(6000);
				boolean accNumberFound = false;
				for (int i = 0; i < sf.comCaseDetails.accountPhoneNumberDropdownOptions.size(); i++) {
					System.out.println(sf.seleU.getTextFromWebElement(sf.comCaseDetails.accountPhoneNumberDropdownOptions.get(i)));
					if (sf.seleU.getTextFromWebElement(sf.comCaseDetails.accountPhoneNumberDropdownOptions.get(i))

							.contains(InputData_Communities.commCaseAccPhoneNumber)) {
						sf.seleU.clickOnElement(sf.comCaseDetails.accountPhoneNumberDropdownOptions.get(i));
						sf.seleU.wait(2000);
						sf.seleU.enterText(sf.comContactUs.accountNumberInput, Keys.TAB);
						accNumberFound = true;
						break;
					}
				}
				if (accNumberFound)
					reportStatusPass(methodName + " Entered account number/Phone Number value "
							+ InputData_Communities.commCaseAccPhoneNumber, true, true);
				else
					reportStatusFail(
							methodName + " Could Not find expected Account/Phone Number from typeahead options :"
									+ InputData_Communities.commCaseAccPhoneNumber,
									true);
			}
			// Select Invoice Month
			if (!InputData_Communities.commCaseInvoiceMonth.equalsIgnoreCase("NA")) {
				verifyFieldDisplayed("Bill Month", sf.comContactUs.invoiceMonth);
				verifyFieldIsRequired("Bill Month", sf.comContactUs.invoiceMonth);
				sf.seleU.clickOnElement(sf.comContactUs.invoiceMonth);
				selectDropdownOptionFromElement(sf.comCaseDetails.billMonthOptions,
						InputData_Communities.commCaseInvoiceMonth);
				reportStatusPass(methodName + " Selected bill month as " + InputData_Communities.commCaseInvoiceMonth,
						true, true);
				sf.seleU.wait(2000);
			}
			
			// Select Charge Type
			if (!InputData_Communities.commCaseChargeType.equalsIgnoreCase("NA")) {
				verifyFieldDisplayed("Charge Type dropdown", sf.comContactUs.typeOfChargeInput);
				verifyFieldIsRequired("Charge Type", sf.comContactUs.typeOfChargeInput);
				sf.seleU.clickOnElement(sf.comContactUs.typeOfChargeInput);
				selectDropdownOptionFromElement(sf.comContactUs.typeOfChargeValues,
						InputData_Communities.commCaseChargeType);
				reportStatusPass(methodName + " Selected charge type as " + InputData_Communities.commCaseChargeType,
						true, true);
				sf.seleU.wait(2000);
			}
						
			// Select Report Type
			if (!InputData_Communities.commCaseReportType.equalsIgnoreCase("NA")) {
				verifyFieldDisplayed("Report Type dropdown", sf.comContactUs.reportsTypeDropdown);
				verifyFieldIsRequired("Report Type", sf.comContactUs.reportsTypeDropdown);
				sf.seleU.clickOnElement(sf.comContactUs.reportsTypeDropdown);
				selectDropdownOptionFromElement(sf.comContactUs.reportTypeOptions,
						InputData_Communities.commCaseReportType);
				reportStatusPass(methodName + " Selected report type as " + InputData_Communities.commCaseReportType,
						true, true);
				sf.seleU.wait(2000);
			}
			
			// Select Option for transfer of Responsibility
			if (!InputData_Communities.commCaseTransferOfResponsibility.equalsIgnoreCase("NA")) {
				sf.seleU.scrollByCoOrdinates(2);
				if (InputData_Communities.commCaseTransferOfResponsibility.equalsIgnoreCase("No"))
					sf.seleU.clickOnElement(sf.comContactUs.torNoOption);
				else
					sf.seleU.clickOnElement(sf.comContactUs.torYesOption);
					
				reportStatusPass(methodName + " Selected transfer of responsibility as " + InputData_Communities.commCaseTransferOfResponsibility,
						true, true);
				sf.seleU.wait(2000);
			}
						
			// Fill IMEI Details
			if (!InputData_Communities.commCaseIMEINumber.equalsIgnoreCase("NA")) {
				verifyFieldDisplayed("IMEI Number", sf.comContactUs.imeiNumberInput.get(0));
				verifyFieldIsRequired("IMEI Number", sf.comContactUs.imeiNumberInput.get(0));
				sf.seleU.clearAndEnterText(sf.comContactUs.imeiNumberInput.get(0),
						InputData_Communities.commCaseIMEINumber);
				sf.seleU.enterText(sf.comContactUs.imeiNumberInput.get(0), Keys.TAB);
				reportStatusPass(methodName + " Entered IMEI number : " + InputData_Communities.commCaseIMEINumber,
						true, true);
			}
			// Fill Details
			sf.seleU.clearAndEnterText(sf.comCaseDetails.additionalDetailsTextArea, InputData.caseStatusNew + addOn_1);
			reportStatusPass(methodName + " Entered Case Description as " + InputData.caseStatusNew + addOn_1, true,
					true);
			sf.seleU.enterText(sf.comCaseDetails.additionalDetailsTextArea, Keys.TAB);

			sf.seleU.wait(2000);

			// Add Attachment
			if (!InputData_Communities.commCaseAddAttachment.equalsIgnoreCase("No")) {
				filesPage.uploadFiles();
			}
			sf.seleU.wait(7000);
			// Click on Create Case

			sf.seleU.clickElementByJSE(sf.comCaseDetails.createCaseButton);
			sf.seleU.wait(30000);

			// Verify Success Message
			verifyFieldDisplayed(methodName + "Case creation success Message", sf.comCaseDetails.caseCreatedSuccessMessage);
			verifyFieldDisplayed(methodName + "Case Number", sf.comCaseDetails.caseNumberOnCaseCreation);
			verifyFieldDisplayed(methodName + "Case Creation detailed Thankyou message", sf.comCaseDetails.caseCreationThanksDetailedMessage);

			// Store case number for future ref
			InputData_Communities.caseNumber = sf.seleU
					.getTextFromWebElement(sf.comCaseDetails.caseNumberOnCaseCreation).replace("#", "");
			reportStatusPass(methodName + " Saved Case Number for future ref ( Sl No: "
					+ InputData_Communities.commCaseSlNo + ": ) " + InputData_Communities.caseNumber, false, false);

			if (InputData_Communities.commCaseSlNo != -1)
				saveTestData(Constants.TESTDATA_COMM_FILE, Constants.W2C_SERVICE_SHEET,
						InputData_Communities.commCaseNumberColumn, InputData_Communities.caseNumber,
						InputData_Communities.commCaseSlNo + 1);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Creating a Case", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Fill Community Create Case Form with details (Technical
	 *                     Case)
	 * 
	 * 
	 */
	public void createTechnicalCase() throws IOException {

		try {

			// Verify landing page
			verifyFieldDisplayed("Create Case Header", sf.comCaseDetails.createCaseHeader);

			reportStatusPass(methodName + " Creating Case of type :" + InputData_Communities.commCaseServiceGroup + "-"
					+ InputData_Communities.commCaseRecordType, true, true);

			// Select Case Account Name
			sf.seleU.clickOnElement(sf.comContactUs.accountNameDropdown);
			selectDropdownOptionFromElement(sf.comContactUs.accountNameOptions,
					InputData_Communities.commCaseAccountName);
			reportStatusPass(methodName + " Selected Account as " + InputData_Communities.commCaseAccountName, true,
					true);
			sf.seleU.wait(4000);

			// Select Main Product
			sf.seleU.clickElementByJSE(driver
					.findElement(By.xpath("//span[contains(text(),'" + InputData_Communities.commCaseProduct + "')]")));

			sf.seleU.clickOnElement(driver
					.findElement(By.xpath("//span[contains(text(),'" + InputData_Communities.commCaseProduct + "')]")));
			reportStatusPass(methodName + " Selected Case Product as " + InputData_Communities.commCaseProduct, true,
					true);
			sf.seleU.wait(2000);

			// Select Service Type :
			if (!InputData_Communities.commCaseBilling_TechSupport.equalsIgnoreCase("NA")) {
				verifyFieldDisplayed("Billing And Account Management Service Type",
						sf.comCaseDetails.technicalSupportCaseType);
				sf.seleU.clickOnElement(sf.comCaseDetails.technicalSupportCaseType);
			}

			// Select Product (category) :
			if(!InputData_Communities.commCaseCategory.equalsIgnoreCase("NA")) {
				sf.seleU.clickOnElement(driver.findElement(
						By.xpath("//span[contains(text(),'" + InputData_Communities.commCaseCategory + "')]")));
				reportStatusPass(methodName + " Selected Case Product category as " + InputData_Communities.commCaseCategory, true,
						true);
			}
			sf.seleU.wait(3000);

			// Select Reason :
			sf.seleU.clickOnElement(driver
					.findElement(By.xpath("//span[contains(text(),'" + InputData_Communities.commCaseReason + "')]")));
			reportStatusPass(methodName + " Selected Case Reason as " + InputData_Communities.commCaseReason, true,
					true);
			sf.seleU.wait(2000);

			/**
			 * Select the flow based on the product (Category) selected
			 */

			if (InputData_Communities.commCaseCategory.equalsIgnoreCase(InputData_Communities.caseCategoryBusinessInternet)) {

				// Selecting the address radio button
				sf.seleU.clickElementByJSE(sf.comCaseDetails.commCaseSelectAddressRadioBtn);
				sf.seleU.wait(4000);
				InputData_Communities.commCaseSiteAddress = sf.seleU.getTextFromWebElement(sf.comPBF.serviceAddressValues.get(0));
				reportStatusPass(methodName + " Selected Site Address radio Button :" + InputData_Communities.commCaseSiteAddress
						, true, true);
				
				
				//sf.seleU.robotClick(sf.comCaseDetails.commCaseSelectAddressRadioBtn);
				//Verify Site Contact is Editable
				sf.seleU.clickElementByJSE(sf.comContactUs.editSiteContactButton);
				sf.seleU.wait(4000);
				verifyFieldDisplayed("Select/Edit Site Contact Page Header", sf.comContactUs.selectSiteContactPageHeader);
				reportStatusPass(methodName + " Selected  Edit SIte Contact button", true, true);
//				sf.seleU.clickElementByJSE(sf.comContactUs.selectSiteContactCancelButton);
//				sf.seleU.wait(4000);
				//Extract Contact Name from Site Contact 
				sf.dataInput.siteContactName =  sf.seleU.getTextFromWebElement(sf.comPBF.siteContactName);
				InputData_Communities.commCaseSiteContact= sf.dataInput.siteContactName;
				sf.seleU.clickElementByJSE(sf.comPBF.serviceAddressRadioButtons.get(0));
				sf.seleU.wait(3000);
				sf.seleU.clickElementByJSE(sf.siteConPBF.continueButton);
				sf.seleU.wait(3000);

			} else if (InputData_Communities.commCaseCategory.equalsIgnoreCase(InputData_Communities.caseCategoryDedicatedInternet)) {
				
				sf.seleU.scrollByCoOrdinates(3);
				enterServiceCircuitID();
				
			} else if (InputData_Communities.commCaseCategory.equalsIgnoreCase(InputData_Communities.caseCategoryDarkFibre)) {
				// Entering the Street Address
				enterStreetAddress();
				enterServiceCircuitID();
				
			} else if(!InputData_Communities.commCaseCategory.equalsIgnoreCase("NA"))// option for WaveLength
			{
				enterStreetAddress();
			}

			if (!InputData_Communities.commCaseDoFormValidations.equalsIgnoreCase("No"))
				validateCaseFormLayoutForTechnicalSupport();

			// Fill Phone Number
			if (!InputData_Communities.commCaseAccPhoneNumber.equalsIgnoreCase("NA")) {
				


				if(InputData_Communities.commCaseProduct.equalsIgnoreCase(InputData_Communities.caseProductBusinessPhone))
				{

					verifyFieldDisplayed("Wireless phone number", sf.comContactUs.businessNumberInput);
					verifyFieldIsRequired("Wireless phone number", sf.comContactUs.businessNumberInput);
					// Input Account number/Phone Number
					sf.seleU.clickOnElement(sf.comContactUs.businessNumberInput);
					sf.seleU.clearText(sf.comContactUs.businessNumberInput);
					sf.seleU.clearAndEnterText(sf.comContactUs.businessNumberInput,
							InputData_Communities.commCaseAccPhoneNumber.substring(0, 1));
					sf.seleU.wait(6000);
					boolean accNumberFound = false;

					for (int i = 0; i < sf.comContactUs.businessPhoneNumberDropdownOptions.size(); i++) {
						if (sf.seleU.getTextFromWebElement(sf.comContactUs.businessPhoneNumberDropdownOptions.get(i))
								.contains(InputData_Communities.commCaseAccPhoneNumber)) {
							sf.seleU.clickOnElement(sf.comContactUs.businessPhoneNumberDropdownOptions.get(i));
							sf.seleU.wait(2000);
							sf.seleU.enterText(sf.comContactUs.businessNumberInput, Keys.TAB);
							accNumberFound = true;
							break;
						}
					}
					if (accNumberFound)
						reportStatusPass(methodName + " Entered account number/Phone Number value "
								+ InputData_Communities.commCaseAccPhoneNumber, true, true);
					else
						reportStatusFail(
								methodName + " Could Not find expected Account/Phone Number from typeahead options :"
										+ InputData_Communities.commCaseAccPhoneNumber,
										true);
				}
				else
				{

					verifyFieldDisplayed("Wireless phone number", sf.comContactUs.wirelessNumberInput);
					verifyFieldIsRequired("Wireless phone number", sf.comContactUs.wirelessNumberInput);
					// Input Account number/Phone Number
					sf.seleU.clickOnElement(sf.comContactUs.wirelessNumberInput);
					sf.seleU.clearAndEnterText(sf.comContactUs.wirelessNumberInput,
							InputData_Communities.commCaseAccPhoneNumber.substring(0, 1));
					sf.seleU.wait(6000);
					boolean accNumberFound = false;

					for (int i = 0; i < sf.comContactUs.wirelessPhoneNumberDropdownOptions.size(); i++) {
						if (sf.seleU.getTextFromWebElement(sf.comContactUs.wirelessPhoneNumberDropdownOptions.get(i))
								.contains(InputData_Communities.commCaseAccPhoneNumber)) {
							sf.seleU.clickOnElement(sf.comContactUs.wirelessPhoneNumberDropdownOptions.get(i));
							sf.seleU.wait(2000);
							sf.seleU.enterText(sf.comContactUs.wirelessNumberInput, Keys.TAB);
							accNumberFound = true;
							break;
						}
					}
					if (accNumberFound)
						reportStatusPass(methodName + " Entered account number/Phone Number value "
								+ InputData_Communities.commCaseAccPhoneNumber, true, true);
					else
						reportStatusFail(
								methodName + " Could Not find expected Account/Phone Number from typeahead options :"
										+ InputData_Communities.commCaseAccPhoneNumber,
										true);
				}
			}

			// Fill Details
			sf.seleU.clearAndEnterText(sf.comCaseDetails.additionalDetailsTextArea, InputData.caseStatusNew + addOn_1);
			reportStatusPass(methodName + " Entered Case Description as " + InputData.caseStatusNew + addOn_1, true,
					true);

			sf.seleU.enterText(sf.comCaseDetails.additionalDetailsTextArea, Keys.TAB);
			sf.seleU.wait(2000);

			// Add Attachment
			if (!InputData_Communities.commCaseAddAttachment.equalsIgnoreCase("No")) {
				filesPage.uploadFiles();
			}
			sf.seleU.wait(7000);
			// Click on Create Case
			sf.seleU.clickElementByJSE(sf.comCaseDetails.createCaseButton);
			sf.seleU.wait(17000);

			// Verify Success Message
			verifyFieldDisplayed("Case creation success Message", sf.comCaseDetails.caseCreatedSuccessMessage);
			verifyFieldDisplayed("Case Number", sf.comCaseDetails.caseNumberOnCaseCreation);

			// Store case number for future ref
			InputData_Communities.caseNumber = sf.seleU
					.getTextFromWebElement(sf.comCaseDetails.caseNumberOnCaseCreation).replace("#", "");
			reportStatusPass(methodName + " Saved Case Number for future ref ( Sl No: "
					+ InputData_Communities.commCaseSlNo + ": ) " + InputData_Communities.caseNumber, false, false);

			saveTestData(Constants.TESTDATA_COMM_FILE, Constants.W2C_SERVICE_SHEET,
					InputData_Communities.commCaseNumberColumn, InputData_Communities.caseNumber,
					InputData_Communities.commCaseSlNo + 1);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Creating a Case", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Enters the Street Address in the Community Case Page
	 */
	public void enterStreetAddress() throws Exception {

		sf.seleU.enterText(sf.comCaseDetails.commCaseStrtAdd, Global.dataInput.addressStreet);
		sf.seleU.enterText(sf.comCaseDetails.commCaseCity, Global.dataInput.addressCity);
		sf.seleU.clickOnElement(sf.comCaseDetails.commCaseProvince);
		sf.seleU.clickOnElement(sf.comCaseDetails.commCaseDdOptn_ON);
		reportStatusPass(methodName + "Entered Address Details - Street,City,Province", true, true);

		sf.seleU.enterText(sf.comCaseDetails.commCaseFirstName, InputData_Service.commCaseFirstName);
		sf.seleU.enterText(sf.comCaseDetails.commCaseLastName, InputData_Service.commCaseLastName);
		sf.seleU.enterText(sf.comCaseDetails.commCaseEmailAdd, InputData_Service.commCaseEmail);
		reportStatusPass(methodName + "Entered Address Person Details - First Name,Last Name, Email", true, true);

		// sf.seleU.enterText(sf.comLogin.phoneNumber,
		// String.valueOf(Global.dataInput.phoneNum));
		// sf.seleU.enterText(sf.comLogin.phoneNumber, Global.dataInput.phoneNum);
		// sf.seleU.moveToThenSlowClickElement(sf.comLogin.phoneNumber);
		// sf.seleU.wait(3000);

		// Entering PhoneNumber keep the cursor on phone Field

		sf.seleU.clickElementByJSE(sf.comCaseDetails.commCasePhoneNumber);
		sf.seleU.clickOnElement(sf.comCaseDetails.commCasePhoneNumber);

		sf.seleU.wait(3000);
		sf.seleU.robotPressKey(10, KeyEvent.VK_3);
		sf.seleU.wait(4000);
		// Entering Service ID

		//sf.seleU.enterText(sf.comCaseDetails.commCaseServiceID, InputData_Service.commCaseServiceID);
		reportStatusPass(methodName + "Entered Phone NUmber, Email", true, true);

		sf.seleU.wait(4000);
	}
	
	/**
	 * @throws IOException
	 * 333333
	 *                     Fill Service ID/ Circuit ID or both
	 * 
	 * 
	 */
	public void enterServiceCircuitID() throws IOException {

		try {
			// entering only service id
			if (!InputData_Communities.commCaseServiceID.equalsIgnoreCase("Not Known")) {
				sf.seleU.enterText(sf.comCaseDetails.commCaseServiceID, InputData_Communities.commCaseServiceID);
				reportStatusPass(methodName + " Entered Case Service ID", true, true);
				sf.seleU.wait(4000);
			} else {
				sf.seleU.clickOnElement(sf.comCaseDetails.noServieIdKnownRadio);
				reportStatusPass(methodName + " Clicked on 'Service ID not known radio button", true, true);
				sf.seleU.wait(4000);
				verifyFieldDisplayed("Do you have a circuit identidication Number?", sf.comCaseDetails.doYouHaveCircuitIDLabel);
				verifyFieldDisplayed("Find your Circuit ID Message", sf.comCaseDetails.circuitIDHelpText);
				verifyFieldDisplayed("Circuit ID - Yes I have option", sf.comCaseDetails.yesCircuitIDKnownRadio);
				verifyFieldDisplayed("Circuit ID - No I don't have option", sf.comCaseDetails.noCircuitIdKnownRadio);
				
				if (!InputData_Communities.commCaseCircuitID.equalsIgnoreCase("Not Known")) {
					sf.seleU.enterText(sf.comCaseDetails.commCaseCircuitID, InputData_Communities.commCaseCircuitID);
					reportStatusPass(methodName + " Entered Case Circuit ID", true, true);
					sf.seleU.wait(4000);
				}
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in filling Service/Circuit ID", e);
			e.printStackTrace();
		}
	}
		
	/**
	 * @param value
	 * @throws IOException
	 * 
	 *                     Validate Case form Layout
	 */
	public void validateCaseFormLayoutForService() throws IOException {

		try {

//			// Do Account/Phone Number Validations
//			if (!InputData_Communities.commCaseAccPhoneNumber.equalsIgnoreCase("NA")) {
//				verifyFieldValue("Label for Account Number/Phone Number", sf.comContactUs.accountNumberLabel,
//						InputData_Communities.accountNumberLabelText);
//			}
			// Do Invoice Month Validations
			if (!InputData_Communities.commCaseInvoiceMonth.equalsIgnoreCase("NA")) {
				validateBillMonthAndFill();
			}

			// Validate Only service type is present for specific products
			if (InputData_Communities.commCaseProduct.equalsIgnoreCase(InputData_Communities.caseProductDataCentre))
				verifyFieldNotDisplayed("Technical Support Case type", sf.comCaseDetails.technicalSupportCaseType);

			// Select Report Type
			if (!InputData_Communities.commCaseReportType.equalsIgnoreCase("NA")) {
				verifyFieldDisplayed("Report Type Label", sf.comContactUs.reportTypeLabel);
				// Validate Report Tip Message
				String reportTypeTipMessage = sf.seleU
						.getTextFromWebElement(sf.comContactUs.reportTypeTipMessageSpan.get(0))
						+ sf.seleU.getTextFromWebElement(sf.comContactUs.reportTypeTipMessageLink)
						+ sf.seleU.getTextFromWebElement(sf.comContactUs.reportTypeTipMessageSpan.get(1));
				if (reportTypeTipMessage.equalsIgnoreCase(InputData_Communities.reportTypeTipMessage))
					reportStatusPass(methodName + " Report Type Tip Message is correct : " + reportTypeTipMessage, true,
							true);
				else
					reportStatusFail(
							methodName + " Report Type Tip Message is not as expected , Expected : "
									+ InputData_Communities.reportTypeTipMessage + ", Actual : " + reportTypeTipMessage,
									true);
			}
			// Fill IMEI Details
			if (!InputData_Communities.commCaseIMEINumber.equalsIgnoreCase("NA")) {
				InputData_Communities.communities_Case_IMEI_Number = InputData_Communities.commCaseIMEINumber;
				validateIMEIAndFill();
			}


		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Validating Case Form Layout", e);
			e.printStackTrace();
		}

	}
	/**
	 * @param value
	 * @throws IOException
	 * 
	 *                     Validate Case form Layout
	 */
	public void validateCaseFormLayoutForTechnicalSupport() throws IOException {

		try {
			verifyFieldDisplayed("Note for Description", sf.comContactUs.caseDescNoteText);
			verifyFieldDisplayed("Help Text for Case Description Textarea", sf.comContactUs.caseDescTextAreaHelpText);
			if (InputData_Communities.commCaseCategory.equalsIgnoreCase(InputData_Communities.caseCategoryBusinessInternet)) {
				verifyFieldDisplayed("Select Site Address Label", sf.comContactUs.selectAddressLabel);
				verifyFieldDisplayed("Select Address Radio Button", sf.comCaseDetails.commCaseSelectAddressRadioBtn);
				verifyFieldDisplayed("Street Address label", sf.comCaseDetails.commCaseStrtAddLabel);
				verifyFieldDisplayed("Address - City label", sf.comCaseDetails.commCaseCityLabel);
				verifyFieldDisplayed("Address - Province label", sf.comCaseDetails.commCaseProvinceLabel);
				verifyFieldDisplayed("Address - Postal Code label", sf.comCaseDetails.commCasePostalCodeLabel);
				verifyFieldDisplayed("Site Contact", sf.comContactUs.siteContactLabel);
				verifyFieldDisplayed("Site Contact Details", sf.comContactUs.siteContactDetails);
				//verifyFieldDisplayed("Edit Site Contact button", sf.comContactUs.editSiteContactButton);

			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Validating Case Form Layout", e);
			e.printStackTrace();
		}

	}
	/**
	 * @throws IOException
	 * 
	 *                     Fill Create Case Form with details (service)
	 * 
	 * 
	 */
	public void createServiceCaseDataCentreOthers(boolean attachment) throws IOException {

		try {

			//			// Verify landing page
			//			verifyFieldDisplayed("Create Case Header", sf.comCaseDetails.createCaseHeader);

			reportStatusPass(methodName + " Creating Case of type :" + InputData_Communities.commCaseServiceGroup + "-"
					+ InputData_Communities.commCaseRecordType, true, true);

			// Select Case Account Name
			sf.seleU.clickOnElement(sf.comContactUs.accountNameDropdown);
			selectDropdownOptionFromElement(sf.comContactUs.accountNameOptions,
					InputData_Communities.commCaseAccountName);
			reportStatusPass(methodName + " Selected Account as " + InputData_Communities.commCaseAccountName, true,
					true);
			sf.seleU.wait(4000);

			// Select Product
			sf.seleU.clickElementByJSE(driver.findElement(
					By.xpath("//input[@value='" + InputData_Communities.caseProductDataCentre + "']/parent::span")));

			sf.seleU.clickOnElement(driver.findElement(
					By.xpath("//input[@value='" + InputData_Communities.caseProductDataCentre + "']/parent::span")));
			reportStatusPass(methodName + " Selected Case Product as " + InputData_Communities.caseProductDataCentre, true,
					true);
			sf.seleU.wait(2000);

			// Select Reason
			sf.seleU.clickOnElement(driver.findElement(
					By.xpath("//input[@value='" + InputData_Communities.caseReason + "']/parent::span")));
			reportStatusPass(methodName + " Selected Case Reason as " + InputData_Communities.caseReason, true,
					true);
			sf.seleU.wait(2000);

			verifyFieldDisplayed("Help Text for Case Description Textarea", sf.comContactUs.caseDescTextAreaHelpText);
			verifyFieldDisplayed("Note for Description", sf.comContactUs.caseDescNoteText);

			// Fill Details
			sf.seleU.clearAndEnterText(sf.comCaseDetails.additionalDetailsTextArea, InputData_Communities.caseDesc255Chars);
			reportStatusPass(methodName + " Entered Case Description as " + InputData_Communities.caseDesc255Chars, true,
					true);
			sf.seleU.enterText(sf.comCaseDetails.additionalDetailsTextArea, Keys.TAB);

			if(attachment) {
				for (int i = 0; i < InputData_Communities.noOfFilesToBeUploadedInCases; i++)
					filesPage.uploadFiles();
			}
			sf.seleU.wait(7000);
			// Click on Create Case

			sf.seleU.clickElementByJSE(sf.comCaseDetails.createCaseButton);
			sf.seleU.wait(18000);

			// Verify Success Message
			verifyFieldDisplayed("Case creation success Message", sf.comCaseDetails.caseCreatedSuccessMessage);
			InputData_Communities.caseNumber = sf.seleU
					.getTextFromWebElement(sf.comCaseDetails.caseNumberOnCaseCreation).replace("#", "");
			reportStatusPass(methodName + " Saved Case Number for future ref ( Sl No: "
					+ InputData_Communities.commCaseSlNo + ": ) " + InputData_Communities.caseNumber, false, false);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Creating a Case", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                     Fill Create Case Form with details (service)
	 * 
	 * 
	 */
	public void createServiceCase(String product, String reason) throws IOException {

		try {

			// Verify landing page
			verifyFieldDisplayed("Create Case Header", sf.comCaseDetails.createCaseHeader);

			reportStatusPass(methodName + " Creating Case of type :" + InputData_Communities.commCaseServiceGroup + "-"
					+ InputData_Communities.commCaseRecordType, true, true);

			// Select Case Account Name
			sf.seleU.clickOnElement(sf.comContactUs.accountNameDropdown);
			selectDropdownOptionFromElement(sf.comContactUs.accountNameOptions,
					InputData_Communities.commCaseAccountName);
			reportStatusPass(methodName + " Selected Account as " + InputData_Communities.commCaseAccountName, true,
					true);
			sf.seleU.wait(4000);

			// Select Product
			sf.seleU.clickElementByJSE(driver.findElement(
					By.xpath("//input[@value='" + product + "']/parent::span")));

			sf.seleU.clickOnElement(driver.findElement(
					By.xpath("//input[@value='" + product + "']/parent::span")));
			reportStatusPass(methodName + " Selected Case Product as " + product, true,
					true);
			sf.seleU.wait(2000);

			if(sf.seleU.isElementDisplayed(sf.comCaseDetails.billingAndAccountMgtCaseType))
				sf.seleU.clickOnElement(sf.comCaseDetails.billingAndAccountMgtCaseType);
			sf.seleU.wait(2000);

			// Select Reason
			sf.seleU.clickOnElement(driver.findElement(
					By.xpath("//input[@value='" + reason + "']/parent::span")));
			reportStatusPass(methodName + " Selected Case Reason as " + reason, true,
					true);
			sf.seleU.wait(2000);

			verifyFieldDisplayed("Help Text for Case Description Textarea", sf.comContactUs.caseDescTextAreaHelpText);
			verifyFieldDisplayed("Note for Description", sf.comContactUs.caseDescNoteText);

			// Fill Details
			sf.seleU.clearAndEnterText(sf.comCaseDetails.additionalDetailsTextArea, InputData.caseStatusNew + addOn_1);
			reportStatusPass(methodName + " Entered Case Description as " + InputData.caseStatusNew + addOn_1, true,
					true);
			sf.seleU.enterText(sf.comCaseDetails.additionalDetailsTextArea, Keys.TAB);

			sf.seleU.wait(7000);
			// Click on Create Case

			sf.seleU.clickElementByJSE(sf.comCaseDetails.createCaseButton);
			sf.seleU.wait(18000);

			// Verify Success Message
			verifyFieldDisplayed("Case creation success Message", sf.comCaseDetails.caseCreatedSuccessMessage);
			InputData_Communities.caseNumber = sf.seleU
					.getTextFromWebElement(sf.comCaseDetails.caseNumberOnCaseCreation).replace("#", "");
			reportStatusPass(methodName + " Saved Case Number for future ref ( Sl No: "
					+ InputData_Communities.commCaseSlNo + ": ) " + InputData_Communities.caseNumber, false, false);
			InputData_Communities.commCaseReason = reason;
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Creating a Case", e);
			e.printStackTrace();
		}
	}
	/**
	 * @param value
	 * @throws Exception
	 * 
	 *                   Select value from case creation dropdown option
	 */
	public static String selectFirstDropdownOption() throws Exception {

		sf.seleU.wait(3000);
		String selectedValue = "";
		for (int i = 0; i < sf.comCaseDetails.dropdownOptions.size(); i++) {

			if (sf.seleU.isElementDisplayed(sf.comCaseDetails.dropdownOptions.get(i))) {
				selectedValue = sf.seleU.getTextFromWebElement(sf.comCaseDetails.dropdownOptions.get(i));
				sf.seleU.clickOnElement(sf.comCaseDetails.dropdownOptions.get(i));
				System.out.println("clicked");
			}

		}
		return selectedValue;
	}
	
	public void validateFraudCaseSearch() throws IOException 
	{
		try
		{
			sf.seleU.hardwait(10000);
			sf.seleU.enterText(sf.comMBC.searchCasesTextBox, InputData.fraudCaseNumber);			
			sf.seleU.hardwait(10000);
			sf.seleU.enterText(sf.comMBC.searchCasesTextBox, Keys.ENTER);
			if(sf.seleU.isElementDisplayed(sf.comMBC.noRecordsToDisplayMessage))
			{
				reportStatusPass(methodName + " Community User Unable to see Fraud Cases ", true, true);	
			}
			else
			{
				reportStatusFail(methodName + " Error in validating fraud case", true);
			}			
		}
		catch (Throwable e) 
		{
			reportStatusFail(methodName + " Error in validating fraud case", e);
			e.printStackTrace();
		}
	}
	
	public void validateProductsForNewCase() throws IOException 
	{
		try
		{
            // click on create new case
			sf.seleU.hardwait(10000);
			sf.seleU.clickElementByJSE(sf.comMBC.createNewCasesLink);
            sf.seleU.hardwait(20000);
            InputData_Service.setDataForProductsOptions();
            List<String> expectedProductList = InputData_Service.productOptions;
			List<String> actualProductList = new ArrayList<String>();
			
			// Create list of WebElements to be verified
			List<WebElement> products = new ArrayList<>(Arrays.asList(sf.comMBC.productBusinessPhone.get(1),
		    sf.comMBC.productInternetAndAdvancedNetwork.get(1), sf.comMBC.productWireless.get(1),
		    sf.comMBC.productDataCentreAndCloud.get(1), sf.comMBC.productInternetOfThings.get(1), 
		    sf.comMBC.productEnterpriseMobilityManagement.get(1), sf.comMBC.productSaaS.get(1)));
			
			// Loop over number of products to be verified
			for (int i = 0; i < products.size(); i++) 
			{
				actualProductList.add(sf.seleU.getTextFromWebElement(products.get(i)));
				sf.seleU.hardwait(3000);
			}
			reportStatusPass(methodName + " Extracted titles from Subscription table to verify with expected list",
					         false, false);
			
			// sort lists for comparison
			Collections.sort(expectedProductList);
			Collections.sort(actualProductList);
			
			// Verify expected product list is equal to actual product List
			if (expectedProductList.equals(actualProductList)) 
			{
				reportStatusPass(methodName + " All expected products are present in actual product list : "
						        + AdditionalUtilities.getAsString(actualProductList), true, true);
			} 
			else 
			{
				reportStatusFail(methodName + " All expected products are not present :: Expected Products--> "
						        + AdditionalUtilities.getAsString(expectedProductList) + "  Actual Products--> "
						        + AdditionalUtilities.getAsString(actualProductList), true);
			}			
		}
		catch (Throwable e) 
		{
			reportStatusFail(methodName + " Error in validating products for new case", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param value
	 * @throws Exception
	 * 
	 *                   Select value from case creation dropdown option
	 */
	public static void selectDropdownOptionFromElement(List<WebElement> dropDownOptionsElement, String value)
			throws Exception {

		sf.seleU.wait(3000);

		for (int i = 0; i < dropDownOptionsElement.size(); i++) {
			// System.out.println(i);
			if (sf.seleU.isElementDisplayed(dropDownOptionsElement.get(i))
					&& (sf.seleU.getTextFromWebElement(dropDownOptionsElement.get(i)).equalsIgnoreCase(value))
					|| (sf.seleU.getElementAttribute(dropDownOptionsElement.get(i), "data-value")
							.equalsIgnoreCase(value))) {
				sf.seleU.clickOnElement(dropDownOptionsElement.get(i));
				System.out.println("Selected");
				break;
			}

		}
	}


}
