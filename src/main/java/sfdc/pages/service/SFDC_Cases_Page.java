package sfdc.pages.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.ScreenDocs;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.data.InputData_Service;
import com.sfdc.lib_pages.SFDC_EmailToCase_Lib;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date: 25/Jan/2020
 *
 *         SFDC Cases page objects
 */
public class SFDC_Cases_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_Cases_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Navigation Menu
	 * 
	 *                     Select Cases
	 * 
	 *                     Click on list view dropdown and select all open cases
	 * 
	 *                     Verify Cases are displayed
	 * 
	 */
	public void verifyCasesObject() throws IOException {
		try {
			String methodName = "SFDC_Cases@: ";

			// Select Navigation Menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Cases
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.casesMenu);
			reportStatusPass(methodName + " Selected Cases from menu", true, false);
			sf.seleU.hardwait(2000);

			// Click on list view dropdown and select all open cases
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.cases.allOpenCasesOption);
			reportStatusPass(methodName + " Selected All Open Cases Option", true, false);
			sf.seleU.hardwait(2000);

			// Verify Cases are displayed
			if (sf.cases.caseNumberAllRows.size() > 0) {
				reportStatusPass(methodName + " Cases Objects verified", true, true);
			} else {
				reportStatusFail(methodName + " Invalid Cases Object", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Cases Object", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select and open the case
	 */
	public void selectAndOpenCase() throws IOException {
		try {

			String methodName = "SFDC_Cases@: ";

			sf.seleU.clickElementByJSE(sf.cases.caseNumberAllRows.get(0));
			reportStatusPass(methodName + " Selected the case ", true, true);
			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Selecting the case in queue", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify the created case and select the same
	 */
	public void selectCaseInQueue() throws IOException {
		try {

			String methodName = "SFDC_Cases@: ";

			// Verify the case created and Click on it
			boolean isCaseFound = false;
			for (int i = 0; i < sf.cases.caseSubjectAllRows.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.cases.caseSubjectAllRows.get(i))
						.equals(SFDC_EmailToCase_Lib.subjectLine)) {

					sf.seleU.clickElementByJSE(sf.cases.caseSubjectAllRows.get(i));
					isCaseFound = true;
					break;

				}
			}

			if (isCaseFound) {
				reportStatusPass(methodName + " Selected the case in Queue", true, true);
			} else {
				reportStatusFail(methodName + " Can't find created case in queue", true);
			}

			sf.seleU.wait(7000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Selecting the case in queue", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify newly created case is present in cases section
	 */
	public void verifyNewCasePresent() throws IOException {
		try {

			String methodName = "SFDC_Cases@: ";

			int i = 0;
			int maxAttempts = 15;
			boolean isCaseFound = false;
			while (true) {
				sf.seleU.refreshPage();
				sf.seleU.wait(3000);
				if (sf.seleU.isElementDisplayed(sf.cases.caseSubjectAllRows.get(0))
						&& sf.seleU.getTextFromWebElement(sf.cases.caseSubjectAllRows.get(0))
								.equals(SFDC_EmailToCase_Lib.subjectLine)) {
					isCaseFound = true;
					break;

				} else {
					sf.seleU.wait(10000);
				}

				if (i == maxAttempts) {
					break;
				}
				i++;
			}

			if (isCaseFound) {
				reportStatusPass(methodName + " The newly created case found to be present in cases", true, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for New Case Object", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Navigation Menu and Select Cases
	 * 
	 *                     Click on new Case Button
	 * 
	 *                     Select Category Service/Tier 1 Tech Support
	 * 
	 *                     Click on Next Button
	 * 
	 * 
	 */
	public void selectCaseCategory(String category) throws IOException 
	{
		try 
		{
			String methodName = "SFDC_Cases@: ";

			// Select Navigation Menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Cases
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.casesMenu);
			reportStatusPass(methodName + " Selected Cases from menu", true, false);
			sf.seleU.hardwait(2000);

			// Click on new Case Button
			sf.seleU.clickElementByJSE(sf.cases.newCaseButton);
			reportStatusPass(methodName + " Clicked on new Case button", true, false);
			sf.seleU.hardwait(5000);

			// Select Category Service/Tier 1 Tech Support
			if (category.contains("Service")) 
			{
				sf.seleU.switchToElementFrame(sf.cases.caseserviceRadio);
				sf.seleU.clickElementByJSE(sf.cases.caseserviceRadio.get(0));
				reportStatusPass(methodName + " Selected case as Customer Service ", true, false);
				sf.seleU.hardwait(2000);
			}
			else 
			{
				sf.seleU.switchToElementFrame(sf.cases.casestechnicalsupportRadio);
				sf.seleU.clickElementByJSE(sf.cases.casestechnicalsupportRadio.get(0));
				reportStatusPass(methodName + " Selected case as Technical Support ", true, false);
				sf.seleU.hardwait(2000);
			}
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Error in Selecting case category ", e);
			e.printStackTrace();
		}
	}
	
	public void createWebToCase() throws IOException 
	{
		try 
		{
			String methodName = "SFDC_Cases@: ";
			
			// Click on new Case Button
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.cases.newCaseButton);
			reportStatusPass(methodName + " Clicked on new Case button", true, false);
			sf.seleU.hardwait(20000);
			
			//select product
			sf.seleU.clickElementByJSE(sf.cases.casePhoneOptn);
			reportStatusPass(methodName + " Selected Product as Business Phone", true, false);
			sf.seleU.hardwait(4000);
			
			//select category
			sf.seleU.clickElementByJSE(sf.cases.caseserviceRadio.get(0));
			reportStatusPass(methodName + " Selected case as Customer Service ", true, false);
			sf.seleU.hardwait(3000);
			
			//select case reason
			sf.seleU.clickElementByJSE(sf.cases.caseReasonField);
			reportStatusPass(methodName + " Selected case Reason as Other ", true, false);
			sf.seleU.hardwait(3000);
			
			//enter description
			sf.seleU.enterText(sf.cases.caseDescription, InputData.caseStatusNew + addOn_1);
			reportStatusPass(methodName + " Entered Case Description as " + InputData.caseStatusNew + addOn_1, true, false);
			sf.seleU.hardwait(3000);
			
			sf.seleU.enterText(sf.cases.caseDescription, Keys.TAB);
			sf.seleU.hardwait(2000);
			
			sf.seleU.clickElementByJSE(sf.cases.createCaseButton);
			sf.seleU.hardwait(20000);
			
            String temporaryCaseNumber = sf.seleU.getTextFromWebElement(sf.cases.caseNumber);
            System.out.println(temporaryCaseNumber);
            
			InputData.caseNumber = temporaryCaseNumber.substring(1,10);
			System.out.println(InputData.caseNumber);
			sf.seleU.hardwait(3000);			
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Error in Selecting case category ", e);
			e.printStackTrace();
		}
	}


	/**
	 * @throws IOException
	 * 
	 *                     Click on Next Button
	 */
	public void selectNewCaseButton() throws IOException {
		try {

			String methodName = "SFDC_Cases@: ";

			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.cases.newCaseButton);
			reportStatusPass(methodName + " Clicked on New Case Button", true, false);
			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Selecting new case button", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Select Category for "Product" , "Category", "Type",
	 *                     "Sub-Type"
	 * 
	 *                     Verify Selected Category
	 */
	public void verifyCaseCategory(Hashtable<String, String> dataTable, boolean validateCustReason) throws IOException {
		try {

			// Select Category for "Product" & verify corresponding values in sub-category
			if (InputData.frenchEnabled.equals("Yes")) {
				sf.seleU.clickElementByJSE(sf.cases.productFamilyFieldFrench);
			} else {
				sf.seleU.clickElementByJSE(sf.cases.productFamilyField);
			}
			sf.seleU.hardwait(2000);

			// comapareDropdownCount("Product", prodct_map.entrySet().size(),
			// countOptionsInDropdown(AdditionalUtilities.convertSetToList(prodct_map.keySet())));

			selectCategory(dataTable, "Product");
			sf.seleU.hardwait(1000);

			// Select Category for "Category" & verify corresponding values in sub-category
			if (InputData.frenchEnabled.equals("Yes")) {
				sf.seleU.clickElementByJSE(sf.cases.categoryFieldFrench);
			} else {
				sf.seleU.clickElementByJSE(sf.cases.categoryField);
			}
			sf.seleU.hardwait(2000);

			/*
			 * comapareDropdownCount("Category :" + dataTable.get("Product"),
			 * prodct_map.get(dataTable.get("Product")).size(),
			 * countOptionsInDropdown(prodct_map.get(dataTable.get("Product"))));
			 */

			selectCategory(dataTable, "Category");
			sf.seleU.hardwait(1000);

			// Select Category for "Type" and verify corresponding values in sub -category
			if (InputData.frenchEnabled.equals("Yes")) {
				sf.seleU.clickElementByJSE(sf.cases.classFieldFrench);
			} else {
				sf.seleU.clickElementByJSE(sf.cases.classField);
			}
			sf.seleU.hardwait(2000);

			/*
			 * comapareDropdownCount("Type :" + dataTable.get("Category"),
			 * category_map.get(dataTable.get("Category")).size(),
			 * countOptionsInDropdown(category_map.get(dataTable.get("Category"))));
			 */

			selectCategory(dataTable, "Type");
			sf.seleU.hardwait(1000);

			// Select Category for "Sub-Type" & verify corresponding values in sub-category
			if (InputData.frenchEnabled.equals("Yes")) {
				sf.seleU.clickElementByJSE(sf.cases.subClassFieldFrench);
			} else {
				sf.seleU.clickElementByJSE(sf.cases.subClassField);
			}
			sf.seleU.hardwait(2000);

			InputData.type_map.get(dataTable.get("Type")).remove("");

			/*
			 * comapareDropdownCount("Sub-Type :" + dataTable.get("Type"),
			 * type_map.get(dataTable.get("Type")).size(),
			 * countOptionsInDropdown(type_map.get(dataTable.get("Type"))));
			 */

			selectCategory(dataTable, "Sub-Type");
			sf.seleU.hardwait(3000);

			if (validateCustReason) {
				sf.seleU.clickElementByJSE(sf.cases.customerReasonField);
				sf.seleU.hardwait(2000);
				selectCategory(dataTable, "Customer Reason");
			}

			sf.seleU.ScrolltoElement(sf.cases.caseOriginField);
			sf.seleU.hardwait(1000);

			// Verify Selected category
			if (InputData.frenchEnabled.equals("Yes")) {
				if (verifySelectedCategory(sf.cases.productFamilyFieldFrench, dataTable.get("Product"))
						&& verifySelectedCategory(sf.cases.categoryFieldFrench, dataTable.get("Category"))
						&& verifySelectedCategory(sf.cases.classFieldFrench, dataTable.get("Type"))
						&& verifySelectedCategory(sf.cases.subClassFieldFrench, dataTable.get("Sub-Type"))) {
					reportStatusPass("validated Selected  dropdown options :" + dataTable, true, true);
				} else {
					reportStatusFail(" Invalid Options for " + dataTable, true);
				}
			} else {
				if (validateCustReason) {
					if (verifySelectedCategory(sf.cases.productFamilyField, dataTable.get("Product"))
							&& verifySelectedCategory(sf.cases.categoryField, dataTable.get("Category"))
							&& verifySelectedCategory(sf.cases.classField, dataTable.get("Type"))
							&& verifySelectedCategory(sf.cases.subClassField, dataTable.get("Sub-Type"))
							&& verifySelectedCategory(sf.cases.customerReasonField, dataTable.get("Customer Reason"))) {

						reportStatusPass("validated Selected  dropdown options :" + dataTable, true, true);
					} else {
						reportStatusFail(" Invalid Options for " + dataTable, true);
					}
				} else {
					if (verifySelectedCategory(sf.cases.productFamilyField, dataTable.get("Product"))
							&& verifySelectedCategory(sf.cases.categoryField, dataTable.get("Category"))
							&& verifySelectedCategory(sf.cases.classField, dataTable.get("Type"))
							&& verifySelectedCategory(sf.cases.subClassField, dataTable.get("Sub-Type"))) {

						reportStatusPass("validated Selected  dropdown options :" + dataTable, true, true);
					} else {
						reportStatusFail(" Invalid Options for " + dataTable, true);
					}
				}

			}

			ScreenDocs.captureScreenShot(docxDataSpecific, runDataSpecific, outDataSpecific, InputData.caseIdentifier);
			ScreenDocs.saveScreenDoc(InputData.caseIdentifier);

			sf.seleU.refreshPage();
			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Verifying Case category", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     select case status, priority, type, sub-type, reason,
	 *                     origin, subject.
	 * 
	 *                     Enter description
	 * 
	 *                     Click on Next Button
	 */
	public void createCaseProactively() throws IOException {
		try {

			String methodName = "SFDC_New Cases@: ";

//			// Select Case Status
//			sf.seleU.clickElementByJSE(sf.cases.statusField);
//			sf.seleU.clickElementByJSE(sf.cases.newStatusValue);
//			reportStatusPass(methodName + " Selected Case Status as " + InputData.caseStatusNew, true, false);
//
//			// Select Case Priority
//			sf.seleU.clickElementByJSE(sf.cases.priorityField);
//			sf.seleU.clickElementByJSE(sf.cases.mediumPriorityValue);
//			reportStatusPass(methodName + " Selected Case Priority as " + InputData.casePriorityMedium, true, false);
//
//			/*
//			 * // Select Case Type sf.seleU.clickElementByJSE(sf.cases.classField);
//			 * sf.seleU.clickElementByJSE(sf.cases.serviceRequestTypeValue);
//			 * reportStatusPass(methodName + " Selected Case Type as " +
//			 * sf.dataInput.caseTypeServiceRequest, true, false);
//			 * 
//			 * // Select Case Sub-Type sf.seleU.clickElementByJSE(sf.cases.subClassField);
//			 * sf.seleU.clickElementByJSE(sf.cases.addWifiCallingSubTypeValue);
//			 * reportStatusPass(methodName + " Selected Case Sub-Type as " +
//			 * sf.dataInput.caseSubTypeAddWiFiCalling, true, false);
//			 */
//
//			// Select Case case origin
//			sf.seleU.clickElementByJSE(sf.cases.caseOriginField);
//			sf.seleU.clickElementByJSE(sf.cases.PhonecaseOriginValue);
//			reportStatusPass(methodName + " Selected Case origin as " + InputData.caseOriginPhone, true, false);
//
//			// Select Case Reason
//			sf.seleU.clickElementByJSE(sf.cases.caseReasonField);
//			if (sf.seleU.isElementDisplayed(sf.cases.billingInquirycaseReasonValue)) {
//				sf.seleU.clickElementByJSE(sf.cases.billingInquirycaseReasonValue);
//				reportStatusPass(methodName + " Selected Case Reason as " + InputData.caseReasonBillingInquiry, true,
//						false);
//			}
//
//			// Select Case Subject, Description
//			sf.seleU.enterText(sf.cases.subjectField, InputData.caseStatusNew + addOn_1);
//			sf.seleU.enterText(sf.cases.descriptionField, InputData.caseStatusNew + addOn_1);
//			sf.seleU.enterText(sf.cases.descriptionField, Keys.TAB);
//			reportStatusPass(methodName + " Selected Subject, Description , Comments  as " + InputData.caseStatusNew,
//					true, false);
//
//			// Click on Save Button
//			sf.seleU.clickElementByJSE(sf.cases.saveButton);
//			reportStatusPass(methodName + " Clicked on Save Button ", true, false);
//			sf.seleU.wait(4000);
//
//			verifyFieldDisplayed("Case Created Span ", sf.caseDetails.caseCreatedSpan);
//			sf.seleU.wait(2000);
//
//			InputData.caseNumber = sf.seleU.getTextFromWebElement(sf.caseDetails.caseNumberFieldValueText);
//
			// Select Case case origin

			sf.seleU.selectTextFromDropDown(sf.cases.caseOrigin, InputData.caseOriginPhone);
			reportStatusPass(methodName + " Selected Case origin as " + InputData.caseOriginPhone, true, false);

//			sf.seleU.clickElementByJSE(sf.cases.caseOrigin);
//			sf.seleU.clickElementByJSE(sf.cases.casePhoneOptn);
//			reportStatusPass(methodName + " Selected Case origin as " + InputData.caseOriginPhone, true, false);

			// Select Case case product

//			sf.seleU.selectTextFromDropDown(sf.cases.caseProductFamily, InputData.productFamily_InternetOfThings);
			sf.seleU.selectTextFromDropDown(sf.cases.caseProductFamily, InputData.wirelessSubscription);
			reportStatusPass(methodName + " Selected Case Product as Wireless", true, false);

//						sf.seleU.clickElementByJSE(sf.cases.caseProductFamily);
//						sf.seleU.clickElementByJSE(sf.cases.caseIOTOptn);
//						reportStatusPass(methodName + " Selected Case Product as IOT" , true, false);

			// Select Case Subject, Description
			sf.seleU.enterText(sf.cases.caseSubject, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, Keys.TAB);
			reportStatusPass(methodName + " Selected Subject, Description , Comments  as " + InputData.caseStatusNew,
					true, false);

			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.cases.caseNextBtn);

			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.cases.caseCheckBox);

			sf.seleU.clickElementByJSE(sf.cases.caseSearchByOptnNextBtn);
			sf.seleU.wait(4000);

			sf.seleU.switchToDefaultContent();

		} catch (Throwable e) {
			reportStatusFail(" Error in Proactively create a case", e);
			e.printStackTrace();
		}
	}
	
	public void validateWebAndMonitoringNotSelected() throws IOException 
	{
		try 
		{
			String methodName = "SFDC_New Cases@: ";

			// Select Case case origin as Web
			sf.seleU.selectTextFromDropDown(sf.cases.caseOrigin, InputData.caseOriginWeb);
			reportStatusPass(methodName + " Selected Case origin as " + InputData.caseOriginWeb, true, false);
			sf.seleU.hardwait(4000);

			// Select Case case product
			sf.seleU.selectTextFromDropDown(sf.cases.caseProductFamily, InputData.wirelessSubscription);
			reportStatusPass(methodName + " Selected Case Product as Wireless", true, false);
			sf.seleU.hardwait(4000);

			// Select Case Subject, Description
			sf.seleU.enterText(sf.cases.caseSubject, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, Keys.TAB);
			reportStatusPass(methodName + " Selected Subject, Description , Comments  as " + InputData.caseStatusNew, true, false);
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.cases.caseNextBtn);
			sf.seleU.hardwait(2000);
			
			//validate correct case origin message 
			sf.seleU.clickElementByJSE(sf.cases.okButton);
			sf.seleU.hardwait(3000);
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.cases.caseOriginMessage), "Error in validating Case Origin");
			sf.seleU.hardwait(3000);
			
			//select case origin as Monitoring
			sf.seleU.selectTextFromDropDown(sf.cases.caseOrigin, InputData.caseOriginMonitoring);
			reportStatusPass(methodName + " Selected Case origin as " + InputData.caseOriginMonitoring, true, false);
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.cases.caseNextBtn);
			sf.seleU.hardwait(2000);
			
			//validate correct case origin message 
			sf.seleU.clickElementByJSE(sf.cases.okButton);
			sf.seleU.hardwait(3000);
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.cases.caseOriginMessage), "Error in validating Case Origin");
			sf.seleU.hardwait(3000);
			
			sf.seleU.switchToDefaultContent();
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Error in Validating Web and Monitoring not selected ", e);
			e.printStackTrace();
		}
	}
		
	/**
	 * @throws IOException
	 * 
	 *                     select case status, priority, type, sub-type, reason,
	 *                     origin, subject.
	 * 
	 *                     Enter description
	 * 
	 *                     Click on Next Button
	 */
	public void createCaseProactivelyForETMCase() throws IOException 
	{
		try 
		{
			String methodName = "SFDC_New Cases@: ";
			
			// Select Case case origin
			sf.seleU.selectTextFromDropDown(sf.cases.caseOrigin, InputData.caseOriginPhone);
			reportStatusPass(methodName + " Selected Case origin as " + InputData.caseOriginPhone, true, false);

			// Select Case case product
			sf.seleU.selectTextFromDropDown(sf.cases.caseProductFamily, InputData.wirelessSubscription);
			reportStatusPass(methodName + " Selected Case Product as Wireless", true, false);

			// Select Case Subject, Description
			sf.seleU.enterText(sf.cases.caseSubject, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, Keys.TAB);
			reportStatusPass(methodName + " Selected Subject, Description , Comments  as " + InputData.caseStatusNew, true, false);

			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.cases.caseNextBtn);
			
			//select Billing Account
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.customerCase.billingAccRadioButton);
			sf.seleU.hardwait(3000);
//	        sf.seleU.enterText(sf.customerCase.searchBillingAccountForBusiness, Global.dataInput.billingAccountName);
	        sf.seleU.enterText(sf.customerCase.searchBillingAccountForBusiness, InputData_Service.billingAccountForInternalGuidedCase);
	        sf.seleU.hardwait(5000);
	        sf.seleU.enterText(sf.customerCase.searchBillingAccountForBusiness, Keys.ARROW_DOWN);
	        sf.seleU.hardwait(3000);
	        sf.seleU.enterText(sf.customerCase.searchBillingAccountForBusiness, Keys.ENTER);
//	        reportStatusPass(methodName + " Entered Billing Account as  " + Global.dataInput.billingAccountName, true, false);
	        reportStatusPass(methodName + " Entered Billing Account as  " + InputData_Service.billingAccountForInternalGuidedCase, true, false);
            sf.seleU.hardwait(4000);			
            sf.seleU.clickElementByJSE(sf.customerCase.searchByOptionsNextButton);
			sf.seleU.wait(4000);
			
			//select contact associated with the account
//			sf.seleU.enterText(sf.cases.contactInputBox, Global.dataInput.contactName);
			sf.seleU.enterText(sf.cases.contactInputBox, InputData_Service.contactForInternalGuidedCase);
			sf.seleU.hardwait(5000);
			sf.seleU.enterText(sf.cases.contactInputBox, Keys.ARROW_DOWN);
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.cases.contactInputBox, Keys.ENTER);
//			reportStatusPass(methodName + " Entered Contact as  " + Global.dataInput.contactName, true, false);
			reportStatusPass(methodName + " Entered Contact as  " + InputData_Service.contactForInternalGuidedCase, true, false);
			sf.seleU.hardwait(4000);
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.hardwait(3000);
			
			//click on next button
			sf.seleU.clickElementByJSE(sf.customerCase.associatedRecordNextButton);
			sf.seleU.hardwait(4000);			
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(4000);
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Error in Proactively create a case", e);
			e.printStackTrace();
		}
	}
	
	public void createCaseProactivelyForSubscription(boolean recordTypeValueWireline) throws IOException 
	{
		try 
		{
			String methodName = "SFDC_New Cases@: ";
			
			// Select Case case origin
			sf.seleU.selectTextFromDropDown(sf.cases.caseOrigin, InputData.caseOriginPhone);
			reportStatusPass(methodName + " Selected Case origin as " + InputData.caseOriginPhone, true, false);

			// Select Case case product
			sf.seleU.selectTextFromDropDown(sf.cases.caseProductFamily, InputData.wirelessSubscription);
			reportStatusPass(methodName + " Selected Case Product as Wireless", true, false);

			// Select Case Subject, Description
			sf.seleU.enterText(sf.cases.caseSubject, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, Keys.TAB);
			reportStatusPass(methodName + " Selected Subject, Description , Comments  as " + InputData.caseStatusNew, true, false);

			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.cases.caseNextBtn);
			
			//select Billing Account
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.customerCase.billingAccRadioButton);
			sf.seleU.hardwait(3000);
//	        sf.seleU.enterText(sf.customerCase.searchBillingAccountForBusiness, Global.dataInput.billingAccountName);
	        sf.seleU.enterText(sf.customerCase.searchBillingAccountForBusiness, InputData_Service.billingAccountForSubscription);
	        sf.seleU.hardwait(5000);
	        sf.seleU.enterText(sf.customerCase.searchBillingAccountForBusiness, Keys.ARROW_DOWN);
	        sf.seleU.hardwait(3000);
	        sf.seleU.enterText(sf.customerCase.searchBillingAccountForBusiness, Keys.ENTER);
//	        reportStatusPass(methodName + " Entered Billing Account as  " + Global.dataInput.billingAccountName, true, false);
	        reportStatusPass(methodName + " Entered Billing Account as  " + InputData_Service.billingAccountForSubscription, true, false);
            sf.seleU.hardwait(4000);			
            sf.seleU.clickElementByJSE(sf.customerCase.searchByOptionsNextButton);
			sf.seleU.wait(4000);
			
			//select contact associated with the account
//			sf.seleU.enterText(sf.cases.contactInputBox, Global.dataInput.contactName);
			if(recordTypeValueWireline)
			{
				sf.seleU.enterText(sf.cases.contactInputBox, InputData_Service.contactForSubscription);
			}
			if(!recordTypeValueWireline)
			{
				sf.seleU.enterText(sf.cases.contactInputBox, InputData_Service.contactForSubscriptionWireless);
			}
			sf.seleU.hardwait(5000);
			sf.seleU.enterText(sf.cases.contactInputBox, Keys.ARROW_DOWN);
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.cases.contactInputBox, Keys.ENTER);
//			reportStatusPass(methodName + " Entered Contact as  " + Global.dataInput.contactName, true, false);
			reportStatusPass(methodName + " Entered Contact as  " + InputData_Service.contactForSubscription, true, false);
			sf.seleU.hardwait(4000);
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.hardwait(3000);
			
			//click on next button
			sf.seleU.clickElementByJSE(sf.customerCase.associatedRecordNextButton);
			sf.seleU.hardwait(4000);			
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(4000);
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Error in Proactively create a case", e);
			e.printStackTrace();
		}
	}

	public void createGuidedCasewithBusinessphone() throws IOException {
		try {

			String methodName = "SFDC_New Cases@: ";

			// Select Case case origin

			sf.seleU.selectTextFromDropDown(sf.cases.caseOrigin, InputData.caseOriginPhone);
			reportStatusPass(methodName + " Selected Case origin as " + InputData.caseOriginPhone, true, false);

			// Select Case case product

			sf.seleU.selectTextFromDropDown(sf.cases.caseProductFamily, InputData.productFamily_BusinessPhone);
			reportStatusPass(methodName + " Selected Case Product as BusinessPhone", true, false);

			// Select Case Subject, Description
			sf.seleU.enterText(sf.cases.caseSubject, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, Keys.TAB);
			reportStatusPass(methodName + " Selected Subject, Description , Comments  as " + InputData.caseStatusNew,
					true, false);

			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.cases.caseNextBtn);

			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.cases.caseCheckBox);

			sf.seleU.clickElementByJSE(sf.cases.caseSearchByOptnNextBtn);
			sf.seleU.wait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Create Guided Case with Business phone", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     select case status, priority, type, sub-type, reason,
	 *                     origin, subject.
	 * 
	 *                     Enter description
	 * 
	 *                     Click on Next Button
	 */
	public void createCaseProactivelyFromExcel() throws IOException {
		try {

			String methodName = "SFDC_New Cases@: ";

			// Select Case Status
			sf.seleU.clickElementByJSE(sf.cases.caseOriginField);
			// sf.seleU.clickElementByJSE(sf.cases.newStatusValue);
			sf.seleU.clickElementByJSE(
					driver.findElement(By.xpath("//div[@class='select-options']//li[@role='presentation']//a[@title='"
							+ InputData_Service.sfdcCaseOrigin + "']")));
			reportStatusPass(methodName + " Selected Case Status as " + InputData_Service.sfdcCaseOrigin, true, false);

			// Select Case Priority
			sf.seleU.clickElementByJSE(sf.cases.priorityField);
			// sf.seleU.clickElementByJSE(sf.cases.mediumPriorityValue);
			sf.seleU.clickElementByJSE(
					driver.findElement(By.xpath("//div[@class='select-options']//li[@role='presentation']//a[@title='"
							+ InputData_Service.sfdcCasePriority + "']")));
			reportStatusPass(methodName + " Selected Case Priority as " + InputData_Service.sfdcCasePriority, true,
					false);

			// Select Case Product
			sf.seleU.clickElementByJSE(sf.cases.productFamilyField);
			sf.seleU.clickElementByJSE(
					driver.findElement(By.xpath("//div[@class='select-options']//li[@role='presentation']//a[@title='"
							+ InputData_Service.sfdcCaseProduct + "']")));
			reportStatusPass(methodName + " Selected Case Priority as " + InputData_Service.sfdcCaseProduct, true,
					false);

			/*
			 * // Select Case Type sf.seleU.clickElementByJSE(sf.cases.classField);
			 * sf.seleU.clickElementByJSE(sf.cases.serviceRequestTypeValue);
			 * reportStatusPass(methodName + " Selected Case Type as " +
			 * sf.dataInput.caseTypeServiceRequest, true, false);
			 * 
			 * // Select Case Sub-Type sf.seleU.clickElementByJSE(sf.cases.subClassField);
			 * sf.seleU.clickElementByJSE(sf.cases.addWifiCallingSubTypeValue);
			 * reportStatusPass(methodName + " Selected Case Sub-Type as " +
			 * sf.dataInput.caseSubTypeAddWiFiCalling, true, false);
			 */

			// Select Case case origin
			sf.seleU.clickElementByJSE(sf.cases.statusField);
			// sf.seleU.clickElementByJSE(sf.cases.PhonecaseOriginValue);
			sf.seleU.clickElementByJSE(
					driver.findElement(By.xpath("//div[@class='select-options']//li[@role='presentation']//a[@title='"
							+ InputData_Service.sfdcCaseStatus + "']")));
			reportStatusPass(methodName + " Selected Case origin as " + InputData_Service.sfdcCaseStatus, true, false);

			// Select Case Reason
			sf.seleU.clickElementByJSE(sf.cases.caseReasonField);
			if (sf.seleU.isElementDisplayed(
					driver.findElement(By.xpath("//div[@class='select-options']//li[@role='presentation']//a[@title='"
							+ InputData_Service.sfdcCaseReason + "']")))) {
				// sf.seleU.clickElementByJSE(sf.cases.billingInquirycaseReasonValue);
				sf.seleU.clickElementByJSE(driver
						.findElement(By.xpath("//div[@class='select-options']//li[@role='presentation']//a[@title='"
								+ InputData_Service.sfdcCaseReason + "']")));
				reportStatusPass(methodName + " Selected Case Reason as " + InputData_Service.sfdcCaseReason, true,
						false);
			}

			// Select Case Subject, Description
			sf.seleU.enterText(sf.cases.subjectField, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.descriptionField, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.descriptionField, Keys.TAB);
			reportStatusPass(methodName + " Selected Subject, Description , Comments  as " + InputData.caseStatusNew,
					true, false);

			// Click on Save Button
			sf.seleU.clickElementByJSE(sf.cases.saveButton);
			reportStatusPass(methodName + " Clicked on Save Button ", true, false);
			sf.seleU.wait(4000);

			verifyFieldDisplayed("Case Created Span ", sf.caseDetails.caseCreatedSpan);
			sf.seleU.wait(2000);

			InputData.caseNumber = sf.seleU.getTextFromWebElement(sf.caseDetails.caseNumberFieldValueText);

		} catch (Throwable e) {
			reportStatusFail(" Error in Proactively create a case", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select case status as Awaiting Third Party
	 * 
	 *                     Select Type
	 * 
	 *                     Select origin and fill subject.
	 * 
	 *                     Click on Next Button
	 */
	public void createCaseWithAwaitingThirdPartyStatus() throws IOException {
		try {

			String methodName = "SFDC_New Cases@: ";

			// Select Case Status
			sf.seleU.clickElementByJSE(sf.cases.statusField);
			sf.seleU.clickElementByJSE(sf.cases.awaitingThirdPartyStatusValue);
			reportStatusPass(methodName + " Selected Case Status as " + InputData.caseStatusAwaitingThirdParty, true,
					false);

			// Select Case case origin
			sf.seleU.clickElementByJSE(sf.cases.caseOriginField);
			sf.seleU.clickElementByJSE(sf.cases.PhonecaseOriginValue);
			reportStatusPass(methodName + " Selected Case origin as " + InputData.caseOriginPhone, true, false);

			// Select Case Subject, Description
			sf.seleU.enterText(sf.cases.subjectField, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.descriptionField, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.descriptionField, Keys.TAB);
			reportStatusPass(methodName + " Selected Subject, Description , Comments  as " + InputData.caseStatusNew,
					true, false);

			// Click on Save Button
			sf.seleU.clickElementByJSE(sf.cases.saveButton);
			reportStatusPass(methodName + " Clicked on Save Button ", true, false);
			sf.seleU.wait(4000);

			verifyFieldDisplayed("Case Created Span ", sf.caseDetails.caseCreatedSpan);
			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Creation of Case", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @param categoryColumn
	 * @throws Exception
	 * 
	 *                   Iterate and select given value from dropdown
	 */
	public void selectCategory(Hashtable<String, String> dataTable, String categoryColumn) throws Exception {

		for (int i = 0; i < sf.cases.categoryAllOptions.size(); i++) {
			if (sf.seleU.isElementDisplayed(sf.cases.categoryAllOptions.get(i))) {

				String expectedText = dataTable.get(categoryColumn).trim().replaceAll("\u0000.*", "")
						.replaceAll("[^a-zA-Z0-9 ]", "").replaceAll("\\s", "");
				String actualText = sf.seleU.getTextFromWebElement(sf.cases.categoryAllOptions.get(i)).trim()
						.replaceAll("\u0000.*", "").replaceAll("[^a-zA-Z0-9 ]", "").replaceAll("\\s", "");

				if (expectedText.equalsIgnoreCase(actualText)) {
					sf.seleU.clickElementByJSE(sf.cases.categoryAllOptions.get(i));
					break;
				}

			}

		}
	}

	/**
	 * @param element
	 * @param text
	 * @throws IOException
	 * 
	 *                     Verify Selected Category
	 */
	public boolean verifySelectedCategory(WebElement element, String text) throws IOException {
		boolean validatationSuccess = false;

		try {

			String methodName = "SFDC_Cases@: ";
			// Verify Selected Category
			String expectedText = text.trim().replaceAll("\u0000.*", "").replaceAll("[^a-zA-Z0-9 ]", "")
					.replaceAll("\\s", "");
			String actualText = sf.seleU.getTextFromWebElement(element).trim().replaceAll("\u0000.*", "")
					.replaceAll("[^a-zA-Z0-9 ]", "").replaceAll("\\s", "");

			if ((actualText.contains("None") || actualText.contains("Aucun")) && expectedText.length() == 0) {
				validatationSuccess = true;
			} else if (expectedText.equalsIgnoreCase(actualText)) {
				validatationSuccess = true;
			} else {
				reportStatusFail(methodName + " Expected case category selection should be " + expectedText
						+ " , Actual is " + actualText, true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Error in Verifying Case category", e);
			e.printStackTrace();
		}
		return validatationSuccess;
	}

	/**
	 * @param categoryColumn
	 * @return
	 * 
	 *         Count the list of options present in a dropdown
	 * @throws Exception
	 */
	public int countOptionsInDropdown(ArrayList<String> expectedList) throws Exception {

		ArrayList<String> actualList = new ArrayList<String>();

		int categoryDisplayedCount = 0;

		sf.seleU.hardwait(2000);
		for (int i = 0; i < sf.cases.categoryAllOptions.size(); i++) {

			if (sf.seleU.isElementDisplayed(sf.cases.categoryAllOptions.get(i))) {

				if (!(sf.seleU.getTextFromWebElement(sf.cases.categoryAllOptions.get(i)).equals("--None--")
						|| sf.seleU.getTextFromWebElement(sf.cases.categoryAllOptions.get(i)).equals("- Aucun -"))) {
					actualList.add(sf.seleU.getTextFromWebElement(sf.cases.categoryAllOptions.get(i)));
					categoryDisplayedCount++;
				}
			}

		}

		/*
		 * expectedList.remove("");
		 * 
		 * Collections.sort(expectedList); Collections.sort(actualList);
		 * 
		 * System.out.println(expectedList); System.out.println(actualList);
		 * 
		 * if (expectedList.equals(actualList)) {
		 * reportStatusPass(" Verified Both Expected Dropdown options and Actual Dropdown Options are Matching"
		 * , false, false); } else { reportStatusFail( "Expected Dropdown options are" +
		 * expectedList + ", Actual Dropdown Options are" + actualList, true); }
		 */

		return categoryDisplayedCount;
	}

	/**
	 * @param fieldname
	 * @param expectedCount
	 * @param actualCount
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Compare DropdownOptions Count
	 */
	public void comapareDropdownCount(String fieldname, int expectedCount, int actualCount)
			throws IOException, InterruptedException {
		if (actualCount != 0) {
			if (expectedCount <= actualCount) {
				reportStatusPass("validated for " + fieldname + " same number of dropdown options :" + expectedCount,
						true, true);
			} else {
				reportStatusFail("Invalid count for  " + fieldname + " in dropdown options, Expected Count should be : "
						+ expectedCount + " But, Actual Count is : " + (actualCount), true);
			}
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Search and open the case
	 */
	public void searchAndOpenCase() throws IOException {
		try {

			String methodName = "SFDC_Cases@: ";

			sf.seleU.wait(10000);
			sf.seleU.enterText(sf.cases.searchCaseInputBox, InputData.caseNumber);
			sf.seleU.enterText(sf.cases.searchCaseInputBox, Keys.ENTER);
			sf.seleU.wait(15000);
			sf.seleU.clickElementByJSE(sf.cases.caseNumberAllRows.get(0));
			reportStatusPass(methodName + " Selected the case : " + InputData.caseNumber, true, true);
			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in searching and selecting the case", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Search and open the case
	 */
	public void searchAndOpenCaseForKnowledgeSection() throws IOException {
		try {

			if (InputData.caseNumber == null)
				Global.dataInput.setCaseNumberToSearch();
			searchAndOpenCase();

		} catch (Throwable e) {
			reportStatusFail(" Error in searching and selecting the case", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Search case globally and open the case
	 */
	public void searchCaseGloballyAndOpen() throws IOException {
		try {
			String methodName = "SFDC_Cases@: ";

			sf.seleU.wait(10000);
			sf.seleU.clickElementByJSE(sf.acc.search);
			sf.seleU.wait(2000);
			sf.seleU.enterText(sf.cases.caseGlobalSearchInput, InputData.caseNumber);
			sf.seleU.enterText(sf.cases.caseGlobalSearchInput, Keys.ENTER);
			sf.seleU.wait(15000);
			sf.seleU.clickElementByJSE(sf.cases.caseSearchResult.get(0));
			reportStatusPass(methodName + " Selected the case : " + InputData.caseNumber, true, true);
			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in searching and selecting the case", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select MyCases from List View
	 */
	public void openMyCasesListView() throws IOException {
		try {

			String methodName = "SFDC_Cases@: ";

			sf.seleU.hardwait(2000);
			// Select Navigation Menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Cases
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.casesMenu);
			reportStatusPass(methodName + " Selected Cases from menu", true, false);
			sf.seleU.hardwait(2000);
			// Click on list view dropdown and select my cases
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.cases.myCasesOption);
			reportStatusPass(methodName + " Selected MyCases Option", true, false);
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Error in opening MyCases", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException s Search Contact As community user and select on the
	 *                     searched contact
	 */
	public void chooseCaseContactAsCommunityUser() throws IOException {

		String methodName = "SFDC_Cases@: ";

		try {
			String communitiesUser = InputData_Communities.communities_userid.split("@")[0];
			// Enter Contact to be Searched
			sf.seleU.scrollToTop();
			sf.seleU.clickElementByJSE(sf.cases.caseContactName);
			reportStatusPass(methodName + "Contact name field is selected", true, false);
			sf.seleU.wait(2000);

			sf.seleU.enterText(sf.cases.caseContactName, communitiesUser);
			sf.seleU.hardwait(4000);
			sf.seleU.enterText(sf.cases.caseContactName, Keys.ENTER);
			sf.seleU.hardwait(5000);

			sf.seleU.clickElementByJSE(sf.cases.searchedContactsAllRecords.get(0));
			reportStatusPass(methodName + " Selected  Contact " + communitiesUser, true, false);
			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error while Searching Contact", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Search Tier 1 Technical Support Case by status
	 * 
	 */
	public boolean searchTier1CaseByStatus(String status) throws IOException {

		verifyCasesObject();
		return searchCaseByrecordTypeAndStatus(Global.dataInput.acc_RecordType_Service, status);

	}

	/**
	 * @throws IOException
	 * 
	 *                     Search and open the case by record type and status
	 * 
	 */
	public boolean searchCaseByrecordTypeAndStatus(String recordType, String status) throws IOException {
		boolean isCaseFound = false;

		try {
			String methodName = "SFDC_Cases@: ";

			sf.seleU.wait(10000);
			sf.seleU.clearAndEnterText(sf.cases.searchCaseInputBox, status);
			sf.seleU.enterText(sf.cases.searchCaseInputBox, Keys.ENTER);
			reportStatusPass(methodName + " Case status is applied : " + status, true, true);
			sf.seleU.wait(30000);

			for (int i = 0; i < sf.cases.caseStatusAllRows.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.cases.caseStatusAllRows.get(i))
						.equalsIgnoreCase(status)
						&& sf.seleU.getTextFromWebElement(sf.cases.caseRecordTypeAllRows.get(i))
								.equalsIgnoreCase(recordType)) {
					sf.seleU.highLightElement(sf.cases.caseRecordTypeAllRows.get(i));
					sf.seleU.highLightElement(sf.cases.caseStatusAllRows.get(i));
					sf.seleU.clickElementByJSE(sf.cases.caseNumberAllRows.get(i));
					isCaseFound = true;
					break;
				} else {
					continue;
				}

			}

			if (isCaseFound) {
				reportStatusPass(methodName + " Selected the case : " + recordType + " as record Type "
						+ " and status as " + status, true, true);
				sf.seleU.wait(5000);
			} else {
				reportStatusPass(methodName + "No case found as:  " + recordType + " as record Type "
						+ " and status as " + status, true, true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Error in searching and selecting the case", e);
			e.printStackTrace();
		}
		return isCaseFound;
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {
			sf.seleU.wait(3000);

			// Verify Field displayed
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass("Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

}
