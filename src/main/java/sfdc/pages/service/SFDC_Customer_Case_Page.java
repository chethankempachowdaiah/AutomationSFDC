package sfdc.pages.service;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date: 25/Jan/2020
 *
 *         SFDC Cases page objects
 */
public class SFDC_Customer_Case_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName = "SFDC_New_Customer_Case@: ";

	public SFDC_Customer_Case_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     New Customer Case"" button is displayed on the account
	 *                     page.
	 * 
	 *                     Click on New Customer Case.
	 * 
	 *                     New Customer Case creation is initiated.
	 * 
	 *                     Case type, case origin, product family, subject are
	 *                     mandatory fields.
	 * 
	 *                     Fill in the case type, case origin, product family,
	 *                     subject and description.
	 * 
	 *                     Select wireless or business phone as product.
	 * 
	 *                     Subscriptions related to the billing account is listed.
	 * 
	 *                     Search for the subscription and search for contact
	 * 
	 *                     Business account and billing account is pre-populated and
	 *                     read- only. Constraint the contact search based on the
	 *                     business and billing account.
	 * 
	 *                     Checkbox create new contact is available.
	 * 
	 *                     Check on create new contact and enter contact
	 *                     information.
	 * 
	 *                     Create contact flow is invoked. In the relationship type,
	 *                     business roles are available.
	 * 
	 *                     CASL fields are selected by default. New contact is
	 *                     created.
	 * 
	 *                     Validate Case is created and case page is displayed.
	 * 
	 */
	public void createWirelessCustomerCaseFromBillingAcc() throws IOException {
		try {

			sf.seleU.wait(5000);
			InputData.chosenAccountNameForCustomerCase = InputData.billingAccountWithSubscription;
			InputData.chosenAccountType = Global.dataInput.acc_RecordType_Billing;
			
			// click on new customer case
			sf.seleU.refreshPage();
			sf.seleU.clickElementByJSE(sf.customerCase.newCustomerCaseButton);
			sf.seleU.wait(9000);
			reportStatusPass(methodName + " Clicked on New Customer Case button ", true, false);
			
			// select technical support
			sf.seleU.switchToElementFrame(sf.cases.casestechnicalsupportRadio);
			sf.seleU.clickElementByJSE(sf.cases.casestechnicalsupportRadio.get(0));
			reportStatusPass(methodName + " Selected case as Technical Support ", true, false);
			sf.seleU.hardwait(2000);
			
			// select case origin
			sf.seleU.selectTextFromDropDown(sf.cases.caseOrigin, InputData.caseOriginPhone);
			reportStatusPass(methodName + " Selected Case origin as " + InputData.caseOriginPhone, true, false);
			
			//select product family
			sf.seleU.selectTextFromDropDown(sf.cases.caseProductFamily, InputData.wirelessSubscription);
			reportStatusPass(methodName + " Selected Case Product as Wireless", true, false);
			InputData_Communities.recentCaseProduct= InputData.wirelessSubscription;
			
			// Select Case Subject, Description
			sf.seleU.enterText(sf.cases.caseSubject, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, Keys.TAB);
			reportStatusPass(methodName + " Selected Subject, Description , Comments  as " + InputData.caseStatusNew,
								true, false);
			
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.cases.caseNextBtn);

//			fillBasicDetailsForCusCase(InputData_Communities.communities_Case_Product_Wireless, false);

			// Fill Subscription and account details
			sf.seleU.clickElementByJSE(sf.customerCase.selectSubscriptionRadioButton);
			sf.seleU.wait(3000);
			verifyFieldDisplayed("Subscription Input", sf.customerCase.subscriptionsInput);
			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsInput);

			// Select Subscription dropdown Value
			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsValue);
			reportStatusPass(methodName + " Selected subscription number ", true, false);
			sf.seleU.wait(5000);

			// Verify business and billing account field values present or not
			verifyFieldDisplayed("Business Account Name Field", sf.customerCase.businessAccountName);
			reportStatusPass(methodName + " Business Account name value is :  "
					+ sf.seleU.getTextFromWebElement(sf.customerCase.businessAccountName), false, false);
			verifyFieldDisplayed("Billing Account Name", sf.customerCase.billingAccountName);
			reportStatusPass(methodName + " Billing Account name value is :  "
					+ sf.seleU.getTextFromWebElement(sf.customerCase.billingAccountName), false, false);
			
			// Click on Next
			sf.seleU.clickOnElement(sf.customerCase.associatedRecordNextButton);
			sf.seleU.wait(4000);
						
            //Script deleted due to flow change as new contact script launched to remove create a contact from case
			// MPOSS-36059, MPOSS-50701
			
//			// Verify Create contact checkbox is present and click it
//			verifyFieldDisplayed("Create Contact Checkbox", sf.customerCase.createNewContactCheckBox);
//			sf.seleU.clickElementByJSE(sf.customerCase.createNewContactCheckBox);
//
//			// Click on NExt
//			sf.seleU.clickOnElement(sf.customerCase.associatedRecordNextButton);
//
//			sf.seleU.wait(4000);
//
//			// Fill Contact Details- Refresh data for new contact
//			fillContactDetails();
//
//			// Verify CASL field are checked by default
//			verifyFieldIsSelected("Do Not Email Checkbox", sf.customerCase.doNotEmailCheckBox);
//			verifyFieldIsSelected("Do Not Call Checkbox", sf.customerCase.doNotCallCheckBox);
//
//			// Select account type as business
//			sf.seleU.clickElementByJSE(sf.customerCase.accountTypeBusinessRadioButton);
//			sf.seleU.wait(5000);
//			reportStatusPass(methodName + " Selected relationship type as Business ", true, false);
//
//			// Verify Select Relationship type section is present
//			verifyFieldDisplayed("Select Relationship type", sf.customerCase.selectRelationshipTypeLabel);
//
//			// Print the business roles present under Business account type
//			for (int i = 0; i < sf.customerCase.businessRelationshipTypeValues.size(); i++)
//				reportStatusPass(
//						methodName + " Business Role value no.  " + i + " : "
//								+ sf.seleU.getTextFromWebElement(sf.customerCase.businessRelationshipTypeValues.get(i)),
//						false, false);
//
//			// Search and select business acc
//			sf.seleU.scrollByCoOrdinates(1);
//			sf.seleU.clearAndEnterText(sf.customerCase.searchAccountInput, InputData.relatedBusinessAccountForCusCase);
//			reportStatusPass(methodName + " Searched business account : " + InputData.relatedBusinessAccountForCusCase,
//					true, false);
//			sf.seleU.wait(5000);
//			verifyFieldDisplayed("Business account search result list ", sf.customerCase.businessAccountSearchResult);
//
//			reportStatusPass(
//					methodName + " Selecting Business Acc Name: "
//							+ sf.seleU.getTextFromWebElement(sf.customerCase.businessAccountSearchResult),
//					false, false);
//
//			sf.seleU.clickElementByJSE(sf.customerCase.businessAccountSearchResult);
//			sf.seleU.wait(5000);
//
//			// Choose General Relationship type
//			// sf.seleU.clickElementByJSE(sf.customerCase.relationshipTypeGeneral);
//
//			// Click on Next
//			sf.seleU.clickElementByJSE(sf.customerCase.contactDetailsNextBtn);
//
//			sf.seleU.wait(10000);
//
//			// Verify Contact created
//			verifyContactCreated();
//
//			// Validate we are on Review Page
//			verifyFieldDisplayed("Review Page Next Button", sf.customerCase.reviewContactNextBtn);
//
//			// Click on Next
//			sf.seleU.clickElementByJSE(sf.customerCase.reviewContactNextBtn);
//
//			sf.seleU.wait(10000);
//			sf.seleU.switchToDefaultContent();
//
//			// Verify Case is created
//			verifyFieldDisplayed("Case Created Span", sf.caseDetails.caseCreatedSpan);

		} catch (Throwable e) {
			reportStatusFail(" Error in create customer case", e);
			e.printStackTrace();
		}
	}
	
	public void createInternetandAdvancedNetworkCaseFromServiceAcc() throws IOException {
		try {
			
			sf.seleU.wait(5000);
			InputData.chosenAccountNameForCustomerCase = InputData.billingAccountWithSubscription;
//			InputData.chosenAccountType = Global.dataInput.acc_RecordType_Billing;
			
			// click on new customer case
			sf.seleU.refreshPage();
			sf.seleU.clickElementByJSE(sf.customerCase.newCustomerCaseButton);
			sf.seleU.wait(9000);
			reportStatusPass(methodName + " Clicked on New Customer Case button ", true, false);
			
			// select technical support
			sf.seleU.switchToElementFrame(sf.cases.casestechnicalsupportRadio);
			sf.seleU.clickElementByJSE(sf.cases.casestechnicalsupportRadio.get(0));
			reportStatusPass(methodName + " Selected case as Technical Support ", true, false);
			sf.seleU.hardwait(2000);
			
			// select case origin
			sf.seleU.selectTextFromDropDown(sf.cases.caseOrigin, InputData.caseOriginPhone);
			reportStatusPass(methodName + " Selected Case origin as " + InputData.caseOriginPhone, true, false);
			
			//select product family
			sf.seleU.selectTextFromDropDown(sf.cases.caseProductFamily, InputData.caseProductInternetAndAdvancedNetwork);
			reportStatusPass(methodName + " Selected Case Product as Internet And Advanced Network", true, false);
			InputData_Communities.recentCaseProduct= InputData.caseProductInternetAndAdvancedNetwork;
			
			// Select Case Subject, Description
			sf.seleU.enterText(sf.cases.caseSubject, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, Keys.TAB);
			reportStatusPass(methodName + " Selected Subject, Description , Comments  as " + InputData.caseStatusNew,
								true, false);
			
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.cases.caseNextBtn);
			
			// Fill Subscription and account details
			sf.seleU.clickElementByJSE(sf.customerCase.selectSubscriptionRadioButton);
			sf.seleU.wait(3000);
			verifyFieldDisplayed("Subscription Input", sf.customerCase.subscriptionsInput);
			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsInput);

			// Select Subscription dropdown Value
			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsValue);
			reportStatusPass(methodName + " Selected subscription number ", true, false);
			sf.seleU.wait(5000);

			// Verify business and billing account field values present or not
			verifyFieldDisplayed("Business Account Name Field", sf.customerCase.businessAccountName);
		    reportStatusPass(methodName + " Business Account name value is :  "
							+ sf.seleU.getTextFromWebElement(sf.customerCase.businessAccountName), false, false);
			verifyFieldDisplayed("Billing Account Name", sf.customerCase.billingAccountName);
			reportStatusPass(methodName + " Billing Account name value is :  "
							+ sf.seleU.getTextFromWebElement(sf.customerCase.billingAccountName), false, false);
						
			// Click on Next
			sf.seleU.clickOnElement(sf.customerCase.associatedRecordNextButton);
			sf.seleU.wait(4000);
			
            //Script deleted due to flow change as new contact script launched to remove create a contact from case
			// MPOSS-36059, MPOSS-50701
//			sf.seleU.wait(5000);
////			InputData.chosenAccountNameForCustomerCase = InputData.serviceAccountWithSusbscriptions;
////			InputData.chosenAccountType = Global.dataInput.acc_RecordType_Billing;
//
//			fillBasicDetailsForCusCase(InputData.caseProductInternetAndAdvancedNetwork, false);
//
//			// Fill Subscription and account details
//			sf.seleU.clickElementByJSE(sf.customerCase.selectSubscriptionRadioButton);
//			sf.seleU.wait(3000);
//			verifyFieldDisplayed("Subscription Input", sf.customerCase.subscriptionsInput);
//			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsInput);
//
//			// Select Subscription dropdown Value
//			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsValue);
//			reportStatusPass(methodName + " Selected subscription number ", true, false);
//			sf.seleU.wait(5000);
//
//			// Verify service, business and billing account field values present or not
//			
//			verifyFieldDisplayed("Service Account Name", sf.customerCase.serviceAccountName);
//			reportStatusPass(methodName + " Service Account name value is :  "
//					+ sf.seleU.getTextFromWebElement(sf.customerCase.serviceAccountName), false, false);
//			
//			verifyFieldDisplayed("Billing Account Name", sf.customerCase.billingAccountName);
//			reportStatusPass(methodName + " Billing Account name value is :  "
//					+ sf.seleU.getTextFromWebElement(sf.customerCase.billingAccountName), false, false);
//			
//			verifyFieldDisplayed("Business Account Name Field", sf.customerCase.businessAccountName);
//			reportStatusPass(methodName + " Business Account name value is :  "
//					+ sf.seleU.getTextFromWebElement(sf.customerCase.businessAccountName), false, false);
//			
//			sf.seleU.wait(5000);
//			
//			// Verify Create contact checkbox is present and click it
//			verifyFieldDisplayed("Create Contact Checkbox", sf.customerCase.createNewContactCheckBox);
//			sf.seleU.clickElementByJSE(sf.customerCase.createNewContactCheckBox);
//			
//			// Click on NExt
//			sf.seleU.clickOnElement(sf.customerCase.associatedRecordNextButton);
//
//			sf.seleU.wait(4000);
//
//			// Fill Contact Details- Refresh data for new contact
//			fillContactDetails();
//
//			// Verify CASL field are checked by default
//			verifyFieldIsSelected("Do Not Email Checkbox", sf.customerCase.doNotEmailCheckBox);
//			verifyFieldIsSelected("Do Not Call Checkbox", sf.customerCase.doNotCallCheckBox);
//
//			// Select account type as business
//			sf.seleU.clickElementByJSE(sf.customerCase.accountTypeBusinessRadioButton);
//			sf.seleU.wait(5000);
//			reportStatusPass(methodName + " Selected relationship type as Business ", true, false);
//
//			// Verify Select Relationship type section is present
//			verifyFieldDisplayed("Select Relationship type", sf.customerCase.selectRelationshipTypeLabel);
//
//			// Print the business roles present under Business account type
//			for (int i = 0; i < sf.customerCase.businessRelationshipTypeValues.size(); i++)
//				reportStatusPass(
//						methodName + " Business Role value no.  " + i + " : "
//								+ sf.seleU.getTextFromWebElement(sf.customerCase.businessRelationshipTypeValues.get(i)),
//						false, false);
//
//			// Search and select business acc
//			sf.seleU.scrollByCoOrdinates(1);
//			sf.seleU.clearAndEnterText(sf.customerCase.searchAccountInput, InputData.relatedBusinessAccountForCusCase);
//			reportStatusPass(methodName + " Searched business account : " + InputData.relatedBusinessAccountForCusCase,
//					true, false);
//			sf.seleU.wait(5000);
//			verifyFieldDisplayed("Business account search result list ", sf.customerCase.businessAccountSearchResult);
//
//			reportStatusPass(
//					methodName + " Selecting Business Acc Name: "
//							+ sf.seleU.getTextFromWebElement(sf.customerCase.businessAccountSearchResult),
//					false, false);
//
//			sf.seleU.clickElementByJSE(sf.customerCase.businessAccountSearchResult);
//			sf.seleU.wait(5000);
//
//			// Choose General Relationship type
//			// sf.seleU.clickElementByJSE(sf.customerCase.relationshipTypeGeneral);
//
//			// Click on Next
//			sf.seleU.clickElementByJSE(sf.customerCase.contactDetailsNextBtn);
//
//			sf.seleU.wait(10000);
//
//			// Verify Contact created
//			verifyContactCreated();
//
//			// Validate we are on Review Page
//			verifyFieldDisplayed("Review Page Next Button", sf.customerCase.reviewContactNextBtn);
//
//			// Click on Next
//			sf.seleU.clickElementByJSE(sf.customerCase.reviewContactNextBtn);
//
//			sf.seleU.wait(10000);
//			sf.seleU.switchToDefaultContent();
//
//			// Verify Case is created
//			verifyFieldDisplayed("Case Created Span", sf.caseDetails.caseCreatedSpan);

		} catch (Throwable e) {
			reportStatusFail(" Error in create customer case", e);
			e.printStackTrace();
		}
	}
	
	public void createBusinessPhoneCustomerCaseFromBillingAcc() throws IOException {
		try {

			sf.seleU.wait(5000);
			InputData.chosenAccountNameForCustomerCase = InputData.billingAccountWithSubscription;
			InputData.chosenAccountType = Global.dataInput.acc_RecordType_Billing;
			
			// click on new customer case
			sf.seleU.clickElementByJSE(sf.customerCase.newCustomerCaseButton);
			sf.seleU.wait(9000);
			reportStatusPass(methodName + " Clicked on New Customer Case button ", true, false);
						
			// select technical support
			sf.seleU.switchToElementFrame(sf.cases.casestechnicalsupportRadio);
			sf.seleU.clickElementByJSE(sf.cases.casestechnicalsupportRadio.get(0));
			reportStatusPass(methodName + " Selected case as Technical Support ", true, false);
			sf.seleU.hardwait(2000);
						
			// select case origin
			sf.seleU.selectTextFromDropDown(sf.cases.caseOrigin, InputData.caseOriginPhone);
			reportStatusPass(methodName + " Selected Case origin as " + InputData.caseOriginPhone, true, false);
						
			//select product family
			sf.seleU.selectTextFromDropDown(sf.cases.caseProductFamily, InputData.productFamily_BusinessPhone);
			reportStatusPass(methodName + " Selected Case Product as Business Phone", true, false);
			InputData_Communities.recentCaseProduct= InputData.productFamily_BusinessPhone;
						
			// Select Case Subject, Description
			sf.seleU.enterText(sf.cases.caseSubject, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, Keys.TAB);
			reportStatusPass(methodName + " Selected Subject, Description , Comments  as " + InputData.caseStatusNew,
											true, false);
			
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.cases.caseNextBtn);
						
//			fillBasicDetailsForCusCase(InputData.productFamily_BusinessPhone, false);
			
			// Fill Subscription and account details
			sf.seleU.clickElementByJSE(sf.customerCase.selectSubscriptionRadioButton);
			sf.seleU.wait(3000);
			verifyFieldDisplayed("Subscription Input", sf.customerCase.subscriptionsInput);
			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsInput);

			// Select Subscription dropdown Value
			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsValue);
			reportStatusPass(methodName + " Selected subscription number ", true, false);
			sf.seleU.wait(5000);

			// Verify business and billing account field values present or not
			verifyFieldDisplayed("Business Account Name Field", sf.customerCase.businessAccountName);
			reportStatusPass(methodName + " Business Account name value is :  "
					+ sf.seleU.getTextFromWebElement(sf.customerCase.businessAccountName), false, false);
			verifyFieldDisplayed("Billing Account Name", sf.customerCase.billingAccountName);
			reportStatusPass(methodName + " Billing Account name value is :  "
					+ sf.seleU.getTextFromWebElement(sf.customerCase.billingAccountName), false, false);
			
			// Click on Next
			sf.seleU.clickOnElement(sf.customerCase.associatedRecordNextButton);
			sf.seleU.wait(4000);
			
			//Script deleted due to flow change as new contact script launched to remove create a contact from case
			// MPOSS-36059, MPOSS-50701

//			// Verify Create contact checkbox is present and click it
//			verifyFieldDisplayed("Create Contact Checkbox", sf.customerCase.createNewContactCheckBox);
//			sf.seleU.clickElementByJSE(sf.customerCase.createNewContactCheckBox);
//
//			// Click on NExt
//			sf.seleU.clickOnElement(sf.customerCase.associatedRecordNextButton);
//
//			sf.seleU.wait(4000);
//
//			// Fill Contact Details- Refresh data for new contact
//			fillContactDetails();
//
//			// Verify CASL field are checked by default
//			verifyFieldIsSelected("Do Not Email Checkbox", sf.customerCase.doNotEmailCheckBox);
//			verifyFieldIsSelected("Do Not Call Checkbox", sf.customerCase.doNotCallCheckBox);
//
//			// Select account type as business
//			sf.seleU.clickElementByJSE(sf.customerCase.accountTypeBusinessRadioButton);
//			sf.seleU.wait(5000);
//			reportStatusPass(methodName + " Selected relationship type as Business ", true, false);
//
//			// Verify Select Relationship type section is present
//			verifyFieldDisplayed("Select Relationship type", sf.customerCase.selectRelationshipTypeLabel);
//
//			// Print the business roles present under Business account type
//			for (int i = 0; i < sf.customerCase.businessRelationshipTypeValues.size(); i++)
//				reportStatusPass(
//						methodName + " Business Role value no.  " + i + " : "
//								+ sf.seleU.getTextFromWebElement(sf.customerCase.businessRelationshipTypeValues.get(i)),
//						false, false);
//
//			// Search and select business acc
//			sf.seleU.scrollByCoOrdinates(1);
//			sf.seleU.clearAndEnterText(sf.customerCase.searchAccountInput, InputData.relatedBusinessAccountForCusCase);
//			reportStatusPass(methodName + " Searched business account : " + InputData.relatedBusinessAccountForCusCase,
//					true, false);
//			sf.seleU.wait(5000);
//			verifyFieldDisplayed("Business account search result list ", sf.customerCase.businessAccountSearchResult);
//
//			reportStatusPass(
//					methodName + " Selecting Business Acc Name: "
//							+ sf.seleU.getTextFromWebElement(sf.customerCase.businessAccountSearchResult),
//					false, false);
//
//			sf.seleU.clickElementByJSE(sf.customerCase.businessAccountSearchResult);
//			sf.seleU.wait(5000);
//
//			// Choose General Relationship type
//			// sf.seleU.clickElementByJSE(sf.customerCase.relationshipTypeGeneral);
//
//			// Click on Next
//			sf.seleU.clickElementByJSE(sf.customerCase.contactDetailsNextBtn);
//
//			sf.seleU.wait(10000);
//
//			// Verify Contact created
//			verifyContactCreated();
//
//			// Validate we are on Review Page
//			verifyFieldDisplayed("Review Page Next Button", sf.customerCase.reviewContactNextBtn);
//
//			// Click on Next
//			sf.seleU.clickElementByJSE(sf.customerCase.reviewContactNextBtn);
//
//			sf.seleU.wait(10000);
//			sf.seleU.switchToDefaultContent();
//
//			// Verify Case is created
//			verifyFieldDisplayed("Case Created Span", sf.caseDetails.caseCreatedSpan);

		} catch (Throwable e) {
			reportStatusFail(" Error in create customer case", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     "Create Customer Case" button is displayed on the
	 *                     overview of account page.
	 * 
	 *                     Click on Create Customer Case button
	 * 
	 *                     New Customer Case creation is initiated.
	 * 
	 *                     Case type, case origin, product family, subject are
	 *                     mandatory fields.
	 * 
	 *                     Fill in the case type, case origin, product family,
	 *                     subject and description.
	 * 
	 *                     Select wireless as product.
	 * 
	 *                     Business account is populated and read only. Validate
	 *                     business account and search for Billing account.
	 * 
	 *                     Associated billing account can be searched.
	 * 
	 *                     On the next screen, search for subscription and contact
	 * 
	 *                     Contacts and related contacts associated to the billing
	 *                     account and business account can be searched.
	 * 
	 *                     Select one contact
	 * 
	 *                     There is no edit option when search for contact.
	 * 
	 *                     Case is created and case page is displayed.
	 * 
	 */
	public void createWirelessCustomerCaseFromBusinessAccCasePanel() throws IOException {
		try {

			sf.seleU.wait(5000);
			// sf.dataInput.chosenAccountNameForCustomerCase = sf.dataInput.billingAccountWithSubscription;
			InputData.chosenAccountType = InputData.acc_RecordType_Business;
            
			// click on new customer case
			sf.seleU.clickElementByJSE(sf.acc.controlDropDown.get(0));
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.customerCase.newCustomerCaseButton);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Clicked on New Customer Case button ", true, false);
									
			// select technical support
			sf.seleU.switchToElementFrame(sf.cases.casestechnicalsupportRadio);
			sf.seleU.clickElementByJSE(sf.cases.casestechnicalsupportRadio.get(0));
			reportStatusPass(methodName + " Selected case as Technical Support ", true, false);
			sf.seleU.hardwait(2000);
									
		    // select case origin
			sf.seleU.selectTextFromDropDown(sf.cases.caseOrigin, InputData.caseOriginPhone);
			reportStatusPass(methodName + " Selected Case origin as " + InputData.caseOriginPhone, true, false);
									
			//select product family
			sf.seleU.selectTextFromDropDown(sf.cases.caseProductFamily, InputData.wirelessSubscription);
			reportStatusPass(methodName + " Selected Case Product as Wireless", true, false);
			InputData_Communities.recentCaseProduct= InputData.wirelessSubscription;
									
			// Select Case Subject, Description
			sf.seleU.enterText(sf.cases.caseSubject, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, Keys.TAB);
			reportStatusPass(methodName + " Selected Subject, Description , Comments  as " + InputData.caseStatusNew,
														true, false);
						
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.cases.caseNextBtn);
			
			sf.seleU.wait(5000);
			sf.seleU.enterText(sf.customerCase.searchBillingAccountForBusiness, InputData.relatedBusinessAccountForCusCase);
			reportStatusPass(methodName + " Searched billing account related to contact", true, false);
			sf.seleU.wait(5000);
			verifyFieldDisplayed("Billing account search result list ", sf.customerCase.billingAccountSearchResult);
			
			// Click on first billing account
			reportStatusPass(methodName + " Selecting Billing Account :  "
							+ sf.seleU.getTextFromWebElement(sf.customerCase.billingAccountSearchResult), false, false);
			sf.seleU.clickOnElement(sf.customerCase.billingAccountSearchResult);
			
			// Click Next
		    sf.seleU.clickElementByJSE(sf.customerCase.searchByOptionsNextButton);
		 	sf.seleU.wait(5000);
			
			// Fill Subscription and account details
			sf.seleU.clickElementByJSE(sf.customerCase.selectSubscriptionRadioButton);
			sf.seleU.wait(3000);
			verifyFieldDisplayed("Subscription Input", sf.customerCase.subscriptionsInput);
			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsInput);

			// Select Subscription dropdown Value
			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsValue);
			reportStatusPass(methodName + " Selected subscription number ", true, false);
		    sf.seleU.wait(5000);
		    
			// Verify billing account name value present or not
//			verifyFieldDisplayed("Billing Account Name", sf.customerCase.billingAccountName);
//			reportStatusPass(methodName + " Billing Account name value is : ", false, false);

			// Verify business account field values present or not
//			verifyFieldDisplayed("Business Account Name Field", sf.customerCase.businessAccountName);
//			reportStatusPass(methodName + " Business Account name value is : ", false, false);
			
			// Click on Next
			sf.seleU.clickOnElement(sf.customerCase.associatedRecordNextButton);
			sf.seleU.wait(4000);
			
//.........Script deleted due to flow change as new contact script launched to remove create a contact from case MPOSS-36059, MPOSS-50701	
		  
//			sf.seleU.wait(5000);
//			InputData.chosenAccountType = Global.dataInput.acc_RecordType_Business;
//
//			Global.dataInput.refreshAddOnValue();
//			sf.seleU.switchToDefaultContent();
//			sf.seleU.wait(4000);
//
////			sf.seleU.switchToElementFrame(sf.customerCase.createCustomerCaseButton);
////			sf.seleU.clickElementByJSE(sf.customerCase.createCustomerCaseButton.get(0));
////			
//			sf.seleU.clickElementByJSE(sf.ad.overviewTab);
//			sf.seleU.hardwait(4000);
//			boolean caseSectionFound = false;
//			for (int i = 0; i < 100; i++) {
//				sf.seleU.enterText(sf.ad.overviewTab, Keys.PAGE_DOWN);
//				sf.seleU.enterText(sf.ad.overviewTab, Keys.PAGE_DOWN);
//				if (sf.seleU.isElementPresent(sf.customerCase.createCustomerCaseButton)) {
//					caseSectionFound = true;
//					break;
//				}
//			}
//			if (caseSectionFound) {
//				// click on New Opportunity Button
//				//sf.seleU.switchToElementFrame(sf.customerCase.createCustomerCaseButton);
//				sf.seleU.clickElementByJSE(sf.customerCase.createCustomerCaseButton.get(0));
//				reportStatusPass(methodName + " Clicked on New Customer Case Button", true, false);
//				sf.seleU.hardwait(5000);
//			} else {
//				reportStatusFail(methodName + " Could Not find cases Section", true);
//			}			
//			sf.seleU.switchToDefaultContent();
//			sf.seleU.wait(3000);
//			sf.seleU.switchToElementFrame(sf.customerCase.caseTypeLabelList);
//			sf.seleU.wait(3000);
//
//			// Verify New Customer Case form opened
//			verifyFieldDisplayed("New Customer Case Form - Case Type", sf.customerCase.caseTypeLabel);
//
//			// Verify Case type, case origin, product family, subject are mandatory fields.
//			verifyFieldIsRequired("Case Type", sf.customerCase.technicalSupportCaseTypeRadioButton);
//			verifyFieldIsRequired("Case Origin", sf.customerCase.caseOriginDropdown);
//			verifyFieldIsRequired("Case Product Family", sf.customerCase.productFamilyDropdown);
//			verifyFieldIsRequired("Case Subject", sf.customerCase.subjectInputField);
//
//			// Select Case Type as Technical Support
//			sf.seleU.clickElementByJSE(sf.customerCase.technicalSupportCaseTypeRadioButton);
//			sf.seleU.wait(3000);
//			InputData.recentCaseType = InputData.caseTypeTechnicalSupport;
//
//			// Select Case origin
//			sf.seleU.selectIndexFromDropDown(sf.customerCase.caseOriginDropdown, 1);
//			reportStatusPass(methodName + " Selected Case type as Technical Support and first origin in the dropdown ",
//					false, false);
//
//			// Select Case Product
//			sf.seleU.selectTextFromDropDown(sf.customerCase.productFamilyDropdown,
//					InputData_Communities.communities_Case_Product_Wireless);
//			reportStatusPass(
//					methodName + " Selected Case Product as " + InputData_Communities.communities_Case_Product_Wireless,
//					true, false);
//			InputData_Communities.recentCaseProduct = InputData_Communities.communities_Case_Product_Wireless;
//
//			// Enter Case Subject, Description
//			sf.seleU.enterText(sf.customerCase.subjectInputField, InputData.caseStatusNew + addOn_1);
//			sf.seleU.enterText(sf.customerCase.descriptionInputField, InputData.caseStatusNew + addOn_1);
//			sf.seleU.enterText(sf.customerCase.descriptionInputField, Keys.TAB);
//			reportStatusPass(methodName + " Entered Subject and Description" + InputData.caseStatusNew, true, false);
//
//			// Click on next Button
//			sf.seleU.clickElementByJSE(sf.customerCase.caseCaseCreationBasicDetailsNextBtn);
//			reportStatusPass(methodName + " Clicked on Next Button ", true, false);
//			sf.seleU.wait(5000);
//
//			// Verify business account field values present or not
////			sf.seleU.switchToElementFrame(sf.customerCase.businessAccountNameInFrame);
////			verifyFieldDisplayed("Business Account Name Field", sf.customerCase.businessAccountNameInFrame.get(0));
////			reportStatusPass(
////					methodName + " Business Account name value is :  "
////							+ sf.seleU.getTextFromWebElement(sf.customerCase.businessAccountNameInFrame.get(0)),
////					false, false);
//			sf.seleU.clickElementByJSE(sf.customerCase.billingAccRadioButton);
//			sf.seleU.wait(5000);
//			sf.seleU.enterText(sf.customerCase.searchBillingAccountForBusiness,
//					InputData.relatedBusinessAccountForCusCase);
//			reportStatusPass(methodName + " Searched billing account related to contact", true, false);
//			sf.seleU.wait(5000);
//			verifyFieldDisplayed("Billing account search result list ", sf.customerCase.billingAccountSearchResult);
//
//			// Click on first billing account
//			reportStatusPass(methodName + " Selecting Billing Account :  "
//					+ sf.seleU.getTextFromWebElement(sf.customerCase.billingAccountSearchResult), false, false);
//			sf.seleU.clickOnElement(sf.customerCase.billingAccountSearchResult);
//
//			// Click Next
//			sf.seleU.clickElementByJSE(sf.customerCase.searchByOptionsNextButton);
//			sf.seleU.wait(5000);
//
//			// Fill Subscription and account details
//			sf.seleU.clickElementByJSE(sf.customerCase.selectSubscriptionRadioButton);
//			sf.seleU.wait(3000);
//			verifyFieldDisplayed("Subscription Input", sf.customerCase.subscriptionsInput);
//			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsInput);
//
//			// Select Subscription dropdown Value
//			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsValue);
//			reportStatusPass(methodName + " Selected subscription number ", true, false);
//			sf.seleU.wait(5000);
//
//			// Verify Create contact checkbox is present
//			verifyFieldDisplayed("Create Contact Checkbox", sf.customerCase.createNewContactCheckBox);
//
//			// Search for contact and choose it
//			sf.seleU.enterText(sf.customerCase.searchContactInput, InputData.relatedContactForCusCaseBusAcc);
//			reportStatusPass(methodName + " Searched Contact related to Business Account", true, false);
//			sf.seleU.wait(5000);
//			verifyFieldDisplayed("Contact search result list ", sf.customerCase.contactSearchResult);
//
//			// Click on first contact
//			reportStatusPass(methodName + " Selecting Contact :  "
//					+ sf.seleU.getTextFromWebElement(sf.customerCase.contactSearchResult), false, false);
//			sf.seleU.clickOnElement(sf.customerCase.contactSearchResult);
//			sf.seleU.wait(5000);
//			// Click on NExt
//			sf.seleU.clickElementByJSE(sf.customerCase.associatedRecordNextButton);
//
//			sf.seleU.wait(10000);
//
//			// Verify Case is created
//			verifyFieldDisplayed("Case Created Span", sf.caseDetails.caseCreatedSpan);

		} catch (Throwable e) {
			reportStatusFail(" Error in create customer case", e);
			e.printStackTrace();
		}
	}
	
	public void createWirelessCustomerCaseFromBusinessAccWithSubscriptions() throws IOException {
		try {

			sf.seleU.wait(5000);
//			InputData.chosenAccountNameForCustomerCase = sf.dataInput.billingAccountWithSubscription;
			InputData.chosenAccountType = InputData.acc_RecordType_Business;
            
			// click on new customer case
			sf.seleU.clickElementByJSE(sf.acc.controlDropDown.get(0));
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.customerCase.newCustomerCaseButton);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Clicked on New Customer Case button ", true, false);
									
			// select technical support
			sf.seleU.switchToElementFrame(sf.cases.casestechnicalsupportRadio);
			sf.seleU.clickElementByJSE(sf.cases.casestechnicalsupportRadio.get(0));
			reportStatusPass(methodName + " Selected case as Technical Support ", true, false);
			sf.seleU.hardwait(2000);
									
		    // select case origin
			sf.seleU.selectTextFromDropDown(sf.cases.caseOrigin, InputData.caseOriginPhone);
			reportStatusPass(methodName + " Selected Case origin as " + InputData.caseOriginPhone, true, false);
									
			//select product family
			sf.seleU.selectTextFromDropDown(sf.cases.caseProductFamily, InputData.wirelessSubscription);
			reportStatusPass(methodName + " Selected Case Product as Wireless", true, false);
			InputData_Communities.recentCaseProduct= InputData.wirelessSubscription;
									
			// Select Case Subject, Description
			sf.seleU.enterText(sf.cases.caseSubject, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, Keys.TAB);
			reportStatusPass(methodName + " Selected Subject, Description , Comments  as " + InputData.caseStatusNew,
														true, false);
						
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.cases.caseNextBtn);
			
            //Search for billing accounts
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.customerCase.billingAccRadioButton);
			sf.seleU.wait(5000);
			sf.seleU.enterText(sf.customerCase.searchBillingAccountForBusiness, InputData.billingAccountWithSubscription);
			reportStatusPass(methodName + " Searched billing account related to contact", true, false);
			sf.seleU.wait(5000);
			verifyFieldDisplayed("Billing account search result list ", sf.customerCase.billingAccountSearchResult);
			
			// Click on first billing account
			reportStatusPass(methodName + " Selecting Billing Account :  "
							+ sf.seleU.getTextFromWebElement(sf.customerCase.billingAccountSearchResult), false, false);
			sf.seleU.clickOnElement(sf.customerCase.billingAccountSearchResult);
			
			// Click Next
		    sf.seleU.clickElementByJSE(sf.customerCase.searchByOptionsNextButton);
		 	sf.seleU.wait(5000);
		 	
		 // Fill Subscription and account details
		 	sf.seleU.clickElementByJSE(sf.customerCase.selectSubscriptionRadioButton);
		 	sf.seleU.wait(3000);
		 	verifyFieldDisplayed("Subscription Input", sf.customerCase.subscriptionsInput);
		 	sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsInput);

		 	// Select Subscription dropdown Value
		 	sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsValue);
		 	reportStatusPass(methodName + " Selected subscription number ", true, false);
		 	sf.seleU.wait(5000);
			
			// Fill Subscription and account details
//			sf.seleU.clickElementByJSE(sf.customerCase.selectSubscriptionRadioButton);
//			sf.seleU.wait(3000);
//			verifyFieldDisplayed("Subscription Input", sf.customerCase.subscriptionsInput);
//			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsInput);

			// Select Subscription dropdown Value
//			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsValue);
//			reportStatusPass(methodName + " Selected subscription number ", true, false);
//		    sf.seleU.wait(5000);
		    
		 // Click Next
		    sf.seleU.clickElementByJSE(sf.customerCase.searchByOptionsNextButton);
		 	sf.seleU.wait(5000);

//.........Script deleted due to flow change as new contact script launched to remove create a contact from case MPOSS-36059, MPOSS-50701			

////			sf.seleU.wait(5000);
////			InputData.chosenAccountType = Global.dataInput.acc_RecordType_Business;
//
//			Global.dataInput.refreshAddOnValue();
//			sf.seleU.switchToDefaultContent();
//			sf.seleU.wait(4000);
//
////			sf.seleU.switchToElementFrame(sf.customerCase.createCustomerCaseButton);
////			sf.seleU.clickElementByJSE(sf.customerCase.createCustomerCaseButton.get(0));
////			
//			sf.seleU.clickElementByJSE(sf.ad.overviewTab);
//			sf.seleU.hardwait(4000);
//			boolean caseSectionFound = false;
//			for (int i = 0; i < 100; i++) {
//				sf.seleU.enterText(sf.ad.overviewTab, Keys.PAGE_DOWN);
//				sf.seleU.enterText(sf.ad.overviewTab, Keys.PAGE_DOWN);
//				if (sf.seleU.isElementPresent(sf.customerCase.createCustomerCaseButton)) {
//					caseSectionFound = true;
//					break;
//				}
//			}
//			if (caseSectionFound) {
//				// click on New Opportunity Button
//				//sf.seleU.switchToElementFrame(sf.customerCase.createCustomerCaseButton);
//				sf.seleU.clickElementByJSE(sf.customerCase.createCustomerCaseButton.get(0));
//				reportStatusPass(methodName + " Clicked on New Customer Case Button", true, false);
//				sf.seleU.hardwait(5000);
//			} else {
//				reportStatusFail(methodName + " Could Not find cases Section", true);
//			}			
//			sf.seleU.switchToDefaultContent();
//			sf.seleU.wait(3000);
//			sf.seleU.switchToElementFrame(sf.customerCase.caseTypeLabelList);
//			sf.seleU.wait(3000);
//
//			// Verify New Customer Case form opened
//			verifyFieldDisplayed("New Customer Case Form - Case Type", sf.customerCase.caseTypeLabel);
//
//			// Verify Case type, case origin, product family, subject are mandatory fields.
//			verifyFieldIsRequired("Case Type", sf.customerCase.technicalSupportCaseTypeRadioButton);
//			verifyFieldIsRequired("Case Origin", sf.customerCase.caseOriginDropdown);
//			verifyFieldIsRequired("Case Product Family", sf.customerCase.productFamilyDropdown);
//			verifyFieldIsRequired("Case Subject", sf.customerCase.subjectInputField);
//
//			// Select Case Type as Technical Support
//			sf.seleU.clickElementByJSE(sf.customerCase.technicalSupportCaseTypeRadioButton);
//			sf.seleU.wait(3000);
//			InputData.recentCaseType = InputData.caseTypeTechnicalSupport;
//
//			// Select Case origin
//			sf.seleU.selectIndexFromDropDown(sf.customerCase.caseOriginDropdown, 1);
//			reportStatusPass(methodName + " Selected Case type as Technical Support and first origin in the dropdown ",
//					false, false);
//
//			// Select Case Product
//			sf.seleU.selectTextFromDropDown(sf.customerCase.productFamilyDropdown,
//					InputData_Communities.communities_Case_Product_Wireless);
//			reportStatusPass(
//					methodName + " Selected Case Product as " + InputData_Communities.communities_Case_Product_Wireless,
//					true, false);
//			InputData_Communities.recentCaseProduct = InputData_Communities.communities_Case_Product_Wireless;
//
//			// Enter Case Subject, Description
//			sf.seleU.enterText(sf.customerCase.subjectInputField, InputData.caseStatusNew + addOn_1);
//			sf.seleU.enterText(sf.customerCase.descriptionInputField, InputData.caseStatusNew + addOn_1);
//			sf.seleU.enterText(sf.customerCase.descriptionInputField, Keys.TAB);
//			reportStatusPass(methodName + " Entered Subject and Description" + InputData.caseStatusNew, true, false);
//
//			// Click on next Button
//			sf.seleU.clickElementByJSE(sf.customerCase.caseCaseCreationBasicDetailsNextBtn);
//			reportStatusPass(methodName + " Clicked on Next Button ", true, false);
//			sf.seleU.wait(5000);
//
//			// Verify business account field values present or not
////			sf.seleU.switchToElementFrame(sf.customerCase.businessAccountNameInFrame);
////			verifyFieldDisplayed("Business Account Name Field", sf.customerCase.businessAccountNameInFrame.get(0));
////			reportStatusPass(
////					methodName + " Business Account name value is :  "
////							+ sf.seleU.getTextFromWebElement(sf.customerCase.businessAccountNameInFrame.get(0)),
////					false, false);
//			sf.seleU.clickElementByJSE(sf.customerCase.billingAccRadioButton);
//			sf.seleU.wait(5000);
//			sf.seleU.enterText(sf.customerCase.searchBillingAccountForBusiness,
//					InputData.serviceAccountWithSusbscriptions);
//			reportStatusPass(methodName + " Searched billing account related to contact", true, false);
//			sf.seleU.wait(5000);
//			verifyFieldDisplayed("Billing account search result list ", sf.customerCase.billingAccountSearchResult);
//
//			// Click on first billing account
//			reportStatusPass(methodName + " Selecting Billing Account :  "
//					+ sf.seleU.getTextFromWebElement(sf.customerCase.billingAccountSearchResult), false, false);
//			sf.seleU.clickOnElement(sf.customerCase.billingAccountSearchResult);
//
//			// Click Next
//			sf.seleU.clickElementByJSE(sf.customerCase.searchByOptionsNextButton);
//			sf.seleU.wait(5000);
//
//			// Fill Subscription and account details
//			sf.seleU.clickElementByJSE(sf.customerCase.selectSubscriptionRadioButton);
//			sf.seleU.wait(3000);
//			verifyFieldDisplayed("Subscription Input", sf.customerCase.subscriptionsInput);
//			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsInput);
//
//			// Select Subscription dropdown Value
//			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsValue);
//			reportStatusPass(methodName + " Selected subscription number ", true, false);
//			sf.seleU.wait(5000);
//
//			// Verify Create contact checkbox is present
//			verifyFieldDisplayed("Create Contact Checkbox", sf.customerCase.createNewContactCheckBox);
//
//			// Search for contact and choose it
//			sf.seleU.enterText(sf.customerCase.searchContactInput, InputData.relatedContactForCusCaseBusAcc);
//			reportStatusPass(methodName + " Searched Contact related to Business Account", true, false);
//			sf.seleU.wait(5000);
//			verifyFieldDisplayed("Contact search result list ", sf.customerCase.contactSearchResult);
//
//			// Click on first contact
//			reportStatusPass(methodName + " Selecting Contact :  "
//					+ sf.seleU.getTextFromWebElement(sf.customerCase.contactSearchResult), false, false);
//			sf.seleU.clickOnElement(sf.customerCase.contactSearchResult);
//			sf.seleU.wait(5000);
//			// Click on NExt
//			sf.seleU.clickElementByJSE(sf.customerCase.associatedRecordNextButton);
//
//			sf.seleU.wait(10000);
//
//			// Verify Case is created
//			verifyFieldDisplayed("Case Created Span", sf.caseDetails.caseCreatedSpan);

		} catch (Throwable e) {
			reportStatusFail(" Error in create customer case", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     "New Customer Case" button is displayed on the account
	 *                     page.
	 * 
	 *                     Click on New Customer Case button
	 * 
	 *                     New Customer Case creation is initiated.
	 * 
	 *                     Case type, case origin, product family, subject are
	 *                     mandatory fields.
	 * 
	 *                     Fill in the case type, case origin, product family,
	 *                     subject and description.
	 * 
	 *                     Select wireline as product.
	 * 
	 *                     Business account is populated and read only. Validate
	 *                     business account and search for Service account.
	 * 
	 *                     Select one service account and validate subscription,
	 *                     billing account, service account, business account
	 * 
	 *                     Associated billing account can be searched.
	 * 
	 *                     On the next screen, search for subscription and contact
	 *                     is displayed.
	 * 
	 *                     Business account, service account, billing account,
	 *                     subscription is pre-populated and read- only.
	 * 
	 *                     Contacts and related contacts associated to the billing
	 *                     account and business account can be searched.
	 * 
	 *                     Select one contact
	 * 
	 *                     There is no edit option when search for contact. Checkbox
	 *                     create new contact is available. Click on it
	 * 
	 *                     Create contact flow is invoked. In the relationship type,
	 *                     business roles are available.
	 * 
	 *                     CASL fields are selected by default. New contact is
	 *                     created.
	 * 
	 *                     Case is created and case page is displayed.
	 * 
	 */
	public void createWirelineCustomerCaseFromBusinessAcc() throws IOException {
		try {

			sf.seleU.wait(5000);
			// sf.dataInput.chosenAccountNameForCustomerCase = sf.dataInput.billingAccountWithSubscription;
			InputData.chosenAccountType = InputData.acc_RecordType_Business;
            
			// click on new customer case
			sf.seleU.clickElementByJSE(sf.acc.controlDropDown.get(0));
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.customerCase.newCustomerCaseButton);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Clicked on New Customer Case button ", true, false);
									
			// select technical support
			sf.seleU.switchToElementFrame(sf.cases.casestechnicalsupportRadio);
			sf.seleU.clickElementByJSE(sf.cases.casestechnicalsupportRadio.get(0));
			reportStatusPass(methodName + " Selected case as Technical Support ", true, false);
			sf.seleU.hardwait(2000);
									
		    // select case origin
			sf.seleU.selectTextFromDropDown(sf.cases.caseOrigin, InputData.caseOriginPhone);
			reportStatusPass(methodName + " Selected Case origin as " + InputData.caseOriginPhone, true, false);
									
			//select product family
			sf.seleU.selectTextFromDropDown(sf.cases.caseProductFamily, InputData.caseProductInternetAndAdvancedNetwork);
			reportStatusPass(methodName + " Selected Case Product as Internet And Advanced Network", true, false);
			InputData_Communities.recentCaseProduct= InputData.caseProductInternetAndAdvancedNetwork;
									
			// Select Case Subject, Description
			sf.seleU.enterText(sf.cases.caseSubject, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, Keys.TAB);
			reportStatusPass(methodName + " Selected Subject, Description , Comments  as " + InputData.caseStatusNew,
														true, false);
						
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.cases.caseNextBtn);
			
//			fillBasicDetailsForCusCase(InputData.caseProductInternetAndAdvancedNetwork, false);

			sf.seleU.wait(5000);

			// Search Service Account number and select first in the result
			sf.seleU.enterText(sf.customerCase.searchServiceAccountInput, InputData.serviceAccountNumberForCusCase);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.customerCase.searchServiceAccountButton);
			reportStatusPass(methodName + " Searched service account :  " + InputData.serviceAccountNumberForCusCase,
					true, false);
			sf.seleU.wait(7000);
			sf.seleU.clickElementByJSE(sf.customerCase.firstServiceAccountInSearchList);

			// Click Next
			sf.seleU.clickElementByJSE(sf.customerCase.searchByOptionsNextButton);

			/*
			 * // Verify subscription value present or not
			 * verifyFieldDisplayed("Subscription Value Field",
			 * sf.customerCase.subscriptionName); reportStatusPass(methodName +
			 * " Subscription value is :  " +
			 * sf.seleU.getTextFromWebElement(sf.customerCase.subscriptionName), false,
			 * false);
			 */
			// Fill Subscription and account details
			sf.seleU.clickElementByJSE(sf.customerCase.selectSubscriptionRadioButton);
			sf.seleU.wait(3000);
			verifyFieldDisplayed("Subscription Input", sf.customerCase.subscriptionsInput);
			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsInput);

			// Select Subscription dropdown Value
			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsValue);
			reportStatusPass(methodName + " Selected subscription number ", true, false);
			sf.seleU.wait(5000);

			// Verify service account name value present or not
			verifyFieldDisplayed("Service Account name Value", sf.customerCase.serviceAccountName);
			reportStatusPass(methodName + " Service account value is :  "
					+ sf.seleU.getTextFromWebElement(sf.customerCase.serviceAccountName), false, false);

			// Verify billing account name value present or not
			verifyFieldDisplayed("Billing Account Name", sf.customerCase.billingAccountName);
			reportStatusPass(methodName + " Billing Account name value is :  "
					+ sf.seleU.getTextFromWebElement(sf.customerCase.billingAccountName), false, false);

			// Verify business account field values present or not
			verifyFieldDisplayed("Business Account Name Field", sf.customerCase.businessAccountName);
			reportStatusPass(methodName + " Business Account name value is :  "
					+ sf.seleU.getTextFromWebElement(sf.customerCase.businessAccountName), false, false);
			
			// Click on Next
			sf.seleU.clickOnElement(sf.customerCase.associatedRecordNextButton);
			sf.seleU.wait(4000);
			
		   //Script deleted due to flow change as new contact script launched to remove create a contact from case
		  // MPOSS-36059, MPOSS-50701

			// Verify Create contact checkbox is present and click it
//			verifyFieldDisplayed("Create Contact Checkbox", sf.customerCase.createNewContactCheckBox);
//			sf.seleU.clickElementByJSE(sf.customerCase.createNewContactCheckBox);
//			sf.seleU.wait(4000);
//
//			// Click on NExt
//			sf.seleU.clickOnElement(sf.customerCase.associatedRecordNextButton);
//
//			sf.seleU.wait(4000);
//			
//			// Fill Contact Details
//			fillContactDetails();
//
//			// Verify CASL field are checked by default
//			verifyFieldIsSelected("Do Not Email Checkbox", sf.customerCase.doNotEmailCheckBox);
//			verifyFieldIsSelected("Do Not Call Checkbox", sf.customerCase.doNotCallCheckBox);
//
//			// Verify Select Relationship type section is present
//			verifyFieldDisplayed("Select Relationship type", sf.customerCase.selectRelationshipTypeLabel);
//
//			// Choose General Relationship type
//			// sf.seleU.clickElementByJSE(sf.customerCase.relationshipTypeGeneral);
//
//			// Click on Next
//			sf.seleU.clickElementByJSE(sf.customerCase.contactDetailsNextBtn);
//
//			sf.seleU.wait(15000);
//
//			// Verify Contact created
//			verifyContactCreated();
//
//			// Validate we are on Review Page
//			verifyFieldDisplayed("Review Page Next Button", sf.customerCase.reviewContactNextBtn);
//
//			// Click on Next
//			sf.seleU.clickElementByJSE(sf.customerCase.reviewContactNextBtn);
//
//			sf.seleU.wait(10000);
//
//			// Verify Case is created
//			verifyFieldDisplayed("Case Created Span", sf.caseDetails.caseCreatedSpan);

		} catch (Throwable e) {
			reportStatusFail(" Error in create customer case", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     "New Customer Case" button is displayed on the
	 *                     subscription page.
	 * 
	 *                     Click on New Customer Case button
	 * 
	 *                     New Customer Case creation is initiated.
	 * 
	 *                     Case type, case origin, product family, subject are
	 *                     mandatory fields.
	 * 
	 *                     Fill in the case type, case origin, product family,
	 *                     subject and description.
	 * 
	 *                     Select wireless as product.
	 * 
	 *                     Validate subscription, billing account, business account
	 *                     and contact
	 * 
	 *                     Business account and billing account are pre-populated
	 *                     and read- only. Constraint contact search based on the
	 *                     business and billing account. There is no edit option
	 *                     when search for contact.
	 * 
	 *                     Checkbox create new contact is available. Check on create
	 *                     new contact .
	 * 
	 *                     Create contact flow is invoked. Enter contact
	 *                     information.
	 * 
	 *                     In the relationship type, business roles are available.
	 * 
	 *                     CASL fields are selected by default.
	 * 
	 *                     New contact is created.
	 * 
	 *                     It has direct relationship with the business account.
	 * 
	 *                     Case is created and case page is displayed.
	 * 
	 */
	public void createWirelessCustomerCaseFromSubscription() throws IOException {
		try {
			
			// click on new customer case
			sf.seleU.wait(5000);
//			sf.seleU.refreshPage();
			sf.seleU.clickElementByJSE(sf.customerCase.newCustomerCaseButton);
			sf.seleU.wait(9000);
			reportStatusPass(methodName + " Clicked on New Customer Case button ", true, false);
						
			// select technical support
			sf.seleU.switchToElementFrame(sf.cases.casestechnicalsupportRadio);
			sf.seleU.clickElementByJSE(sf.cases.casestechnicalsupportRadio.get(0));
			reportStatusPass(methodName + " Selected case as Technical Support ", true, false);
			sf.seleU.hardwait(2000);
						
			// select case origin
			sf.seleU.selectTextFromDropDown(sf.cases.caseOrigin, InputData.caseOriginPhone);
			reportStatusPass(methodName + " Selected Case origin as " + InputData.caseOriginPhone, true, false);
						
			//select product family
			sf.seleU.selectTextFromDropDown(sf.cases.caseProductFamily, InputData.wirelessSubscription);
			reportStatusPass(methodName + " Selected Case Product as Wireless", true, false);
			InputData_Communities.recentCaseProduct= InputData.wirelessSubscription;
						
			// Select Case Subject, Description
			sf.seleU.enterText(sf.cases.caseSubject, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, Keys.TAB);
			reportStatusPass(methodName + " Selected Subject, Description , Comments  as " + InputData.caseStatusNew,
							true, false);
						
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.cases.caseNextBtn);
			
			// Verify business and billing account field values present or not
			sf.seleU.hardwait(5000);
			verifyFieldDisplayed("Business Account Name Field", sf.customerCase.businessAccountName);
			reportStatusPass(methodName + " Business Account name value is :  "
						    + sf.seleU.getTextFromWebElement(sf.customerCase.businessAccountName), false, false);
			verifyFieldDisplayed("Billing Account Name", sf.customerCase.billingAccountName);
			reportStatusPass(methodName + " Billing Account name value is :  "
							+ sf.seleU.getTextFromWebElement(sf.customerCase.billingAccountName), false, false);
						
			// Click on Next
			sf.seleU.clickOnElement(sf.customerCase.associatedRecordNextButton);
			sf.seleU.wait(4000);
						
//			createCustomerCaseFromSubscription(InputData.caseProductInternetAndAdvancedNetwork);

		} 
		catch (Throwable e) {
			reportStatusFail(" Error in create customer case", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     "New Customer Case" button is displayed on the
	 *                     subscription page.
	 * 
	 *                     Click on New Customer Case button
	 * 
	 *                     New Customer Case creation is initiated.
	 * 
	 *                     Case type, case origin, product family, subject are
	 *                     mandatory fields.
	 * 
	 *                     Fill in the case type, case origin, product family,
	 *                     subject and description.
	 * 
	 *                     Select wireline(Internet) as product.
	 * 
	 *                     Validate subscription, billing account, business account
	 *                     and contact
	 * 
	 *                     Business account and billing account are pre-populated
	 *                     and read- only. Constraint contact search based on the
	 *                     business and billing account. There is no edit option
	 *                     when search for contact.
	 * 
	 *                     Checkbox create new contact is available. Check on create
	 *                     new contact .
	 * 
	 *                     Create contact flow is invoked. Enter contact
	 *                     information.
	 * 
	 *                     In the relationship type, business roles are available.
	 * 
	 *                     CASL fields are selected by default.
	 * 
	 *                     New contact is created.
	 * 
	 *                     It has direct relationship with the business account.
	 * 
	 *                     Case is created and case page is displayed.
	 * 
	 */
	public void createWirelineCustomerCaseFromSubscription() throws IOException {
		try {
			
			// click on new customer case
			sf.seleU.wait(5000);
//			sf.seleU.refreshPage();
			sf.seleU.clickElementByJSE(sf.customerCase.newCustomerCaseButton);
			sf.seleU.wait(9000);
			reportStatusPass(methodName + " Clicked on New Customer Case button ", true, false);
									
			// select technical support
		    sf.seleU.switchToElementFrame(sf.cases.casestechnicalsupportRadio);
			sf.seleU.clickElementByJSE(sf.cases.casestechnicalsupportRadio.get(0));
			reportStatusPass(methodName + " Selected case as Technical Support ", true, false);
			sf.seleU.hardwait(2000);
									
			// select case origin
			sf.seleU.selectTextFromDropDown(sf.cases.caseOrigin, InputData.caseOriginPhone);
		    reportStatusPass(methodName + " Selected Case origin as " + InputData.caseOriginPhone, true, false);
									
			//select product family
			sf.seleU.selectTextFromDropDown(sf.cases.caseProductFamily, InputData.caseProductInternetAndAdvancedNetwork);
			reportStatusPass(methodName + " Selected Case Product as Internet And Advanced Network", true, false);
			InputData_Communities.recentCaseProduct= InputData.caseProductInternetAndAdvancedNetwork;
									
			// Select Case Subject, Description
			sf.seleU.enterText(sf.cases.caseSubject, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, Keys.TAB);
			reportStatusPass(methodName + " Selected Subject, Description , Comments  as " + InputData.caseStatusNew,
														true, false);
									
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.cases.caseNextBtn);
						
			// Verify business and billing account field values present or not
			sf.seleU.hardwait(5000);
			verifyFieldDisplayed("Business Account Name Field", sf.customerCase.businessAccountName);
			reportStatusPass(methodName + " Business Account name value is :  "
							+ sf.seleU.getTextFromWebElement(sf.customerCase.businessAccountName), false, false);
			verifyFieldDisplayed("Billing Account Name", sf.customerCase.billingAccountName);
			reportStatusPass(methodName + " Billing Account name value is :  "
							+ sf.seleU.getTextFromWebElement(sf.customerCase.billingAccountName), false, false);
									
			// Click on Next
			sf.seleU.clickOnElement(sf.customerCase.associatedRecordNextButton);
			sf.seleU.wait(4000);

//			createCustomerCaseFromSubscription(InputData.caseProductInternetAndAdvancedNetwork);

		} catch (Throwable e) {
			reportStatusFail(" Error in create customer case", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Create customer case from subscription
	 * 
	 */
	public void createCustomerCaseFromSubscription(String productName) throws IOException {
		try {
			
			sf.seleU.wait(5000);
			fillBasicDetailsForCusCase(productName, false);

			// Verify business and billing account field values present or not
			verifyFieldDisplayed("Business Account Name Field", sf.customerCase.businessAccountName);
			reportStatusPass(methodName + " Business Account name value is :  "
					+ sf.seleU.getTextFromWebElement(sf.customerCase.businessAccountName), false, false);
			verifyFieldDisplayed("Billing Account Name", sf.customerCase.billingAccountName);
			reportStatusPass(methodName + " Billing Account name value is :  "
					+ sf.seleU.getTextFromWebElement(sf.customerCase.billingAccountName), false, false);

			// Verify Create contact checkbox is present and click it
			verifyFieldDisplayed("Create Contact Checkbox", sf.customerCase.createNewContactCheckBox);
			sf.seleU.clickElementByJSE(sf.customerCase.createNewContactCheckBox);

			// Click on NExt
			sf.seleU.clickOnElement(sf.customerCase.associatedRecordNextButton);

			sf.seleU.wait(4000);

			// Fill Contact Details
			fillContactDetails();

			// Verify CASL field are checked by default
			verifyFieldIsSelected("Do Not Email Checkbox", sf.customerCase.doNotEmailCheckBox);
			verifyFieldIsSelected("Do Not Call Checkbox", sf.customerCase.doNotCallCheckBox);

			// Select relationship type as business
			sf.seleU.clickElementByJSE(sf.customerCase.accountTypeBusinessRadioButton);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Selected relationship type as Business ", true, false);

			// Verify Select Relationship type section is present
			verifyFieldDisplayed("Select Relationship type", sf.customerCase.selectRelationshipTypeLabel);

			// Search and select business acc
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.clearAndEnterText(sf.customerCase.searchAccountInput, InputData.relatedBusinessAccountForCusCase);
			reportStatusPass(methodName + " Searched business account : " + InputData.relatedBusinessAccountForCusCase,
					true, false);
			sf.seleU.wait(5000);
			verifyFieldDisplayed("Business account search result list ", sf.customerCase.businessAccountSearchResult);

			reportStatusPass(
					methodName + " Selecting Business Acc Name: "
							+ sf.seleU.getTextFromWebElement(sf.customerCase.businessAccountSearchResult),
					false, false);

			sf.seleU.clickElementByJSE(sf.customerCase.businessAccountSearchResult);
			sf.seleU.wait(5000);
			// Choose General Relationship type
			// sf.seleU.clickElementByJSE(sf.customerCase.relationshipTypeGeneral);

			// Click on Next
			sf.seleU.clickElementByJSE(sf.customerCase.contactDetailsNextBtn);
			reportStatusPass(methodName + " Selected relationship type as General ", true, false);

			sf.seleU.wait(10000);

			// Verify Contact created
			verifyContactCreated();

			// Validate we are on Review Page
			verifyFieldDisplayed("Review Page Next Button", sf.customerCase.reviewContactNextBtn);

			// Click on Next
			sf.seleU.clickElementByJSE(sf.customerCase.reviewContactNextBtn);

			sf.seleU.wait(10000);
			sf.seleU.switchToDefaultContent();

			// sf.dataInput.chosenAccountNameForCustomerCase =
			// sf.dataInput.billingAccountWithSubscription;
			// sf.dataInput.chosenAccountType = sf.dataInput.acc_RecordType_Business;

			// Verify Case is created
			verifyFieldDisplayed("Case Created Span", sf.caseDetails.caseCreatedSpan);

		} catch (Throwable e) {
			reportStatusFail(" Error in create customer case", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Refresh data and fill contact details
	 * 
	 */
	private void fillContactDetails() throws IOException {
		// TODO Auto-generated method stub
		try {
			SFDC_CreateContact_Page contactObject = new SFDC_CreateContact_Page();
			Global.dataInput.firstContact_prepareContactData();
			contactObject.enterCreateContactInfo(false);

		} catch (Throwable e) {
			reportStatusFail(" Error in filling contact details for customer case", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Contact created *
	 */
	private void verifyContactCreated() throws IOException {
		// TODO Auto-generated method stub
		try {
			SFDC_CreateContact_Page contactObject = new SFDC_CreateContact_Page();
			contactObject.verifyContactCreated();

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying contact created or not for customer case", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     "New Customer Case" button is displayed on the account
	 *                     page.
	 * 
	 *                     Click on New Customer Case.
	 * 
	 *                     New Customer Case creation is initiated.
	 * 
	 *                     Case type, case origin, product family, subject are
	 *                     mandatory fields.
	 * 
	 *                     Fill in the case type, case origin, product family,
	 *                     subject and description.
	 * 
	 *                     Select wireless as product.
	 * 
	 *                     On the next screen, contact information is prepopulated.
	 * 
	 *                     Related business account can be searched by account name
	 *                     and account number.
	 * 
	 *                     Option to create case without related details is
	 *                     available.
	 * 
	 *                     Skip searching for business account and next.
	 * 
	 *                     Search for billing account and check "Load Subscription".
	 *                     Search for subscription.
	 * 
	 *                     Billing accounts related to the contact are displayed in
	 *                     the results.
	 * 
	 *                     Once check "Load subscription", subscription search box
	 *                     is displayed.
	 * 
	 *                     Associated subscriptions can be searched.
	 * 
	 *                     Business account, billing account, subscription is
	 *                     pre-populated and read- only.
	 * 
	 *                     Validate Case is created and case page is displayed.
	 * 
	 */
	public void createWirelessCustomerCaseFromServiceContact() throws IOException {
		try {
			
			// click on new customer case
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.customerCase.newCustomerCaseButton);
			sf.seleU.wait(9000);
		    reportStatusPass(methodName + " Clicked on New Customer Case button ", true, false);
									
			// select technical support
			sf.seleU.switchToElementFrame(sf.cases.casestechnicalsupportRadio);
			sf.seleU.clickElementByJSE(sf.cases.casestechnicalsupportRadio.get(0));
			reportStatusPass(methodName + " Selected case as Technical Support ", true, false);
			sf.seleU.hardwait(2000);
									
			// select case origin
			sf.seleU.selectTextFromDropDown(sf.cases.caseOrigin, InputData.caseOriginPhone);
			reportStatusPass(methodName + " Selected Case origin as " + InputData.caseOriginPhone, true, false);
									
			//select product family
			sf.seleU.selectTextFromDropDown(sf.cases.caseProductFamily, InputData.wirelessSubscription);
			reportStatusPass(methodName + " Selected Case Product as Wireless", true, false);
			InputData_Communities.recentCaseProduct= InputData.wirelessSubscription;
									
			// Select Case Subject, Description
			sf.seleU.enterText(sf.cases.caseSubject, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.cases.caseDescription, Keys.TAB);
			reportStatusPass(methodName + " Selected Subject, Description , Comments  as " + InputData.caseStatusNew,
							true, false);
						
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.cases.caseNextBtn);

			// Select New Customer Case button and fill Basic Details
//			fillBasicDetailsForCusCase(InputData_Communities.communities_Case_Product_Wireless, false);

			// Verify Contact field is prepopulated
			verifyFieldDisplayed("Contact Name Field", sf.customerCase.contactName);
			reportStatusPass(methodName + " Contact name value is : "+ sf.seleU.getTextFromWebElement(sf.customerCase.contactName), false, false);

			// Verify contact name is uneditable/read-only
			verifyFieldIsDisabled("Contact Name text", sf.customerCase.contactName);

			// Search for business account
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.enterText(sf.customerCase.searchBusinessAccount, InputData.relatedBusinessAccountForCusCaseServiceContact);
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.customerCase.searchBusinessAccount, Keys.ARROW_DOWN);
			sf.seleU.enterText(sf.customerCase.searchBusinessAccount, Keys.ENTER);
			reportStatusPass(methodName + " Searched business account : " + InputData.relatedBusinessAccountForCusCaseServiceContact, true, false);
			sf.seleU.wait(5000);
//			verifyFieldDisplayed("Business account search result list ", sf.customerCase.businessAccountSearchResult);
//			reportStatusPass(methodName + " Business Acc Name listed : "
//			+ sf.seleU.getTextFromWebElement(sf.customerCase.businessAccountSearchResult),false, false);

			// Close and start again to clear filled business acc( Subscription not getting
			// populated at the end if this field is not completely refreshed)
//			sf.seleU.switchToDefaultContent();
//			sf.seleU.clickElementByJSE(sf.customerCase.closeCreateCusCase);
//			sf.seleU.wait(10000);			
//			fillBasicDetailsForCusCase(InputData_Communities.communities_Case_Product_Wireless, false);			

			// Verify Case create without related details checkbox is present
			verifyFieldDisplayed("Case create without related details checkbox",sf.customerCase.createCaseWithoutRelatedDetailsCheckBox);

			// Click on NExt
			sf.seleU.clickOnElement(sf.customerCase.searchByOptionsNextButton);
			sf.seleU.wait(4000);

			// Enter billing account number
			sf.seleU.hardwait(4000);
//			sf.seleU.enterText(sf.customerCase.searchBillingAccount, InputData.relatedBillingAccForCusCase);
			sf.seleU.enterText(sf.customerCase.billingAccount, InputData.relatedBillingAccForCusCase);
			sf.seleU.hardwait(5000);
//			sf.seleU.enterText(sf.customerCase.billingAccount, Keys.ARROW_DOWN);
//			sf.seleU.enterText(sf.customerCase.billingAccount, Keys.ENTER);			
			reportStatusPass(methodName + " Searched billing account related to contact", true, false);
			sf.seleU.wait(5000);
			verifyFieldDisplayed("Billing account search result list ", sf.customerCase.billingAccountSearchResult);

			// Click on first billing account			
			sf.seleU.clickOnElement(sf.customerCase.billingAccountSearchResult);
//			reportStatusPass(methodName + " Selecting Billing Account : " 
//			+ sf.seleU.getTextFromWebElement(sf.customerCase.billingAccountSearchResult), false, false);
			sf.seleU.hardwait(4000);

			// Select Load Subscription Checkbox
			sf.seleU.clickElementByJSE(sf.customerCase.loadSubscriptionCheckBox);
			sf.seleU.wait(5000);

			// Verify Search subscription input box is displayed
			sf.seleU.clickElementByJSE(sf.customerCase.selectSubscriptionRadioButton);
			sf.seleU.wait(3000);
			verifyFieldDisplayed("Subscription Input", sf.customerCase.subscriptionsInput);
			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsInput);

			// Select Subscription dropdown Value
			sf.seleU.clickElementByJSE(sf.customerCase.subscriptionsValue);
			reportStatusPass(methodName + " Selected subscription number ", true, false);
			sf.seleU.wait(5000);

			// Click on Next
			sf.seleU.clickElementByJSE(sf.customerCase.associatedRecordNextButton);

			sf.seleU.wait(10000);
			sf.seleU.switchToDefaultContent();

			// Verify Case is created
//			verifyFieldDisplayed("Case Created Span", sf.caseDetails.caseCreatedSpan);

		} catch (Throwable e) {
			reportStatusFail(" Error in create customer case through Service Contact", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     "New Customer Case" button is displayed on the account
	 *                     page.
	 * 
	 *                     Click on New Customer Case.
	 * 
	 *                     New Customer Case creation is initiated.
	 * 
	 *                     Case type, case origin, product family, subject are
	 *                     mandatory fields.
	 * 
	 *                     Fill in the case type, case origin, product family,
	 *                     subject and description.
	 * 
	 *                     Select product as given in the argument *
	 */

	private void fillBasicDetailsForCusCase(String productName, boolean frame) throws IOException {

		try {

			// refresh addOn Value
			Global.dataInput.refreshAddOnValue();
			sf.seleU.switchToDefaultContent();
			sf.seleU.wait(4000);
			// Click on New customer case
			if (frame)
			sf.seleU.switchToFrame(sf.customerCase.newCusCaseFrame);
			sf.seleU.wait(2000);
			
			//Click 
//			if (sf.seleU.isElementPresent(sf.ad.showMoreActions) && sf.seleU.isElementDisplayed(sf.ad.showMoreActions))
//				sf.seleU.clickElementByJSE(sf.ad.showMoreActions);
			
			sf.seleU.clickElementByJSE(sf.customerCase.newCustomerCaseButton);
				
			sf.seleU.wait(9000);
			reportStatusPass(methodName + " Clicked on New Customer Case button ", true, false);

			if (frame) {
				sf.seleU.switchToDefaultContent();
				sf.seleU.wait(3000);
				// sf.seleU.switchToFrame(sf.customerCase.caseFormForBusinessCaseIframe);*/
				sf.seleU.switchToElementFrame(sf.customerCase.caseTypeLabelList);
				sf.seleU.wait(3000);
			} else {

				sf.seleU.switchToFrame(sf.customerCase.caseFormIframe);
				sf.seleU.wait(3000);
			}
			// Verify New Customer Case form opened
			verifyFieldDisplayed("New Customer Case Form - Case Type", sf.customerCase.caseTypeLabel);

			// Verify Case type, case origin, product family, subject are mandatory fields.
			verifyFieldIsRequired("Case Type", sf.customerCase.technicalSupportCaseTypeRadioButton);
			verifyFieldIsRequired("Case Origin", sf.customerCase.caseOriginDropdown);
			verifyFieldIsRequired("Case Product Family", sf.customerCase.productFamilyDropdown);
			verifyFieldIsRequired("Case Subject", sf.customerCase.subjectInputField);

			// Select Case Type as Technical Support
			sf.seleU.clickElementByJSE(sf.customerCase.technicalSupportCaseTypeRadioButton);
			sf.seleU.wait(3000);
			InputData.recentCaseType = InputData.caseTypeTechnicalSupport;

			// Select Case origin
			sf.seleU.selectIndexFromDropDown(sf.customerCase.caseOriginDropdown, 1);
			reportStatusPass(methodName + " Selected Case type as Technical Support and first origin in the dropdown ",
					false, false);

			// Select Case Product
			sf.seleU.selectTextFromDropDown(sf.customerCase.productFamilyDropdown, productName);
			reportStatusPass(methodName + " Selected Case Product as " + productName, true, false);
			Global.commData.recentCaseProduct = productName;

			// Enter Case Subject, Description
			sf.seleU.enterText(sf.customerCase.subjectInputField, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.customerCase.descriptionInputField, InputData.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.customerCase.descriptionInputField, Keys.TAB);
			reportStatusPass(methodName + " Entered Subject and Description" + InputData.caseStatusNew, true, false);

			// Click on next Button
			sf.seleU.clickElementByJSE(sf.customerCase.caseCaseCreationBasicDetailsNextBtn);
			reportStatusPass(methodName + " Clicked on Next Button ", true, false);
			sf.seleU.wait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in filling customer case basic details", e);
			e.printStackTrace();
		}
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

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is disabled
	 */
	public void verifyFieldIsDisabled(String fieldName, WebElement element) throws IOException {
		try {
			sf.seleU.wait(3000);

			// Verify Field displayed
			if (!element.isEnabled()) {
				reportStatusPass("Validated " + fieldName + " is disabled", true, true);
			} else {
				reportStatusFail(fieldName + " is not disabled", true);
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
	public void verifyFieldIsSelected(String fieldName, WebElement element) throws IOException {
		try {
			sf.seleU.wait(3000);

			// Verify Field selected
			if (element.isSelected()) {
				reportStatusPass("Validated " + fieldName + " is selected", true, true);
			} else {
				reportStatusFail(fieldName + " is not selected", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field selection verification", e);
			e.printStackTrace();
		}
	}

	/**
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
}
