package sfdc.pages.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.data.InputData_Sales;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya,Date 13/jan/2020
 * 
 *         SFDC Account Details page
 *
 */

public class SFDC_AccountDetails_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_AccountDetails_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate Business Account Details
	 */
	public void validateBusinessAccount() throws IOException {
		try {

			String phoneInExpectedFormat = "(" + Global.dataInput.phoneNumber.substring(0, 3) + ")" + " "
					+ Global.dataInput.phoneNumber.substring(3, 6) + "-"
					+ Global.dataInput.phoneNumber.substring(6, 10);

			// Search Account and Click on Details Tab
			if (!(Global.dataInput.isChildBusinessAccount.equals("Yes"))) {
				searchBusAccGlobalSearchStatusPendingApproval(Global.dataInput.businessAccountName);
			}

			selectDetailsTab();
			sf.seleU.hardwait(2000);

			if (sf.seleU.isElementPresent(sf.ad.closeButton)) {
				sf.seleU.clickElementByJSE(sf.ad.closeButton);
			}

			// Verify Fields in Details Tab
			if (Global.dataInput.isChildBusinessAccount.equals("Yes")) {
				// verifyFieldValue("Business Account Name", sf.ad.accountNameValueText,
				// Global.dataInput.parentAccount);
				verifyFieldValue("Business Account Status", sf.ad.statusValueText,
						Global.dataInput.status_PendingApproval);
			} else {
				verifyFieldValue("Business Account Name", sf.ad.accountNameValueText,
						Global.dataInput.businessAccountName);
				verifyFieldValue("Business Account Status", sf.ad.statusValueText,
						Global.dataInput.businessAccountStatus);
			}

			// verifyFieldValue("Primary Contact", sf.ad.contactValueText,
			// Global.dataInput.contactFirstName + " " + Global.dataInput.contactLastName);
			// verifyFieldValue("business Account Owner", sf.ad.accountOwnerValueText,
			// InputData.username);
			// verifyFieldValue("business Account Employess", sf.ad.employeesValueText,
			// Global.dataInput.numberOfEmployees);
			// verifyFieldValue("Buniess Account Legal Name", sf.ad.legalNameValueText,
			// Global.dataInput.businessAccountLegalName);

			verifyFieldValue("Business Account Record Type", sf.ad.accountRecordTypeValueText,
					Global.dataInput.acc_RecordType_Business);

			verifyFieldValue("Buniess Account Phone Number", sf.ad.phoneValueText, phoneInExpectedFormat);

			if (Global.dataInput.isChildBusinessAccount.equals("Yes")) {
				verifyFieldValue("Business Account Parent Account", sf.ad.parentAccountValueText,
						Global.dataInput.parentAccountName);
			}

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Verifying Busniess Account Field Values", e);
			e.printStackTrace();
		}
	}
	
	public void validateShippingAddressEmpty() throws IOException
	{
		try
		{
            //validate shipping address is empty
			sf.seleU.clickElementByJSE(sf.accHi.accountHierarchyButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.ad.serviceAccountLink);
			sf.seleU.hardwait(3000);			
			softassert.assertTrue((sf.ad.shippingAddressValueText.getText()).isEmpty(), " Error in validating Shipping Address Field empty ");			
			sf.seleU.hardwait(3000);
			
			//validate premises address has value
			sf.seleU.clickElementByJSE(sf.ad.premisesValueInputLink);
			sf.seleU.hardwait(3000);
			softassert.assertEquals(sf.seleU.getTextFromWebElement(sf.premises.premisesStreetAddressValueText), Global.dataInput.addressStreet, " Error in Validating street ");
			sf.seleU.hardwait(3000);
			softassert.assertEquals(sf.seleU.getTextFromWebElement(sf.premises.premisesCityValueText), Global.dataInput.addressCity, " Error in Validating city ");
			sf.seleU.hardwait(3000);
			softassert.assertEquals(sf.seleU.getTextFromWebElement(sf.premises.premisesStatAddressValueText), Global.dataInput.addressState, " Error in Validating state ");
			sf.seleU.hardwait(3000);
			softassert.assertEquals(sf.seleU.getTextFromWebElement(sf.premises.postalCodeValueText), Global.dataInput.addressPostalCode, " Error in Validating postal code ");
			sf.seleU.hardwait(3000);
			softassert.assertEquals(sf.seleU.getTextFromWebElement(sf.premises.premisesCountryValueText), Global.dataInput.addressCountry, " Error in Validating postal country ");			
			sf.seleU.hardwait(3000);
		}
		catch(Throwable e)
		{
			reportStatusFail(" Error in validationg shipping address is empty ", e);
			e.printStackTrace();
		}
	}
	
	public void deactivateActivateBusinessContact(boolean isWithActivation) throws IOException
	{
		try
		{
			//deactivate the contact
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.ad.contactLink.get(0));
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.cd.deactivateContactButton);
			sf.seleU.hardwait(3000);
			sf.seleU.switchToFrame(sf.cd.deactivateContactIframe);
			sf.seleU.hardwait(3000);
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.cd.deactivateValidateMessage), " Error in validating only contact assosciated with business account message ");
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.cd.deactivateContactCheckBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.cd.deactivateNextButtonClick);
			sf.seleU.hardwait(4000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(2000);
			verifyFieldValue("Status", sf.cd.contactStatusText, Global.dataInput.b2b_status_Inactive);
			sf.seleU.hardwait(2000);
			sf.seleU.scrollUpByCoOrdinates();
			sf.seleU.hardwait(2000);
			
			//validate deactivation message
			sf.seleU.clickElementByJSE(sf.cd.deactivateContactButton);
			sf.seleU.hardwait(3000);
			sf.seleU.switchToFrame(sf.cd.deactivateContactIframe);
			sf.seleU.hardwait(3000);
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.cd.alreadyDeactivateMsg), " Error in validating message that contact is already inactivated ");
            sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.cd.deactivateNextButtonClick);
			sf.seleU.hardwait(4000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(2000);
			
			//activate the contact
			if(isWithActivation)
			{
			sf.seleU.clickElementByJSE(sf.cd.activateContactButton);
			sf.seleU.hardwait(3000);
			sf.seleU.switchToFrame(sf.cd.deactivateContactIframe);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.cd.activateContactCheckBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.cd.deactivateNextButtonClick);
			sf.seleU.hardwait(4000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(2000);
			verifyFieldValue("Status", sf.cd.contactStatusText, Global.dataInput.status_Active);
			}
		}
		catch (Throwable e) 
		{
			reportStatusFail(" Error in validating reactivation of inactive contact", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate Service Account Details
	 * 
	 *                     If ( it Service account is created along with bunisess
	 *                     account, then parent account should be business account)
	 */
	public void validateServiceAccount(boolean isWithBusinessAccount) throws IOException 
	{
		try 
		{

			String methodName = "SFDC_Service_Account Details@: ";

			// Search Account and Click on Details Tab
			searchAccountFromGlobalSearch(Global.dataInput.serviceAccountName, "Service");
			// selectDetailsTab();
			sf.seleU.hardwait(2000);

			if (sf.seleU.isElementPresent(sf.ad.closeButton)) 
			{
				sf.seleU.clickElementByJSE(sf.ad.closeButton);
			}

			// Verify Fields in Details Tab
			verifyFieldValue("Service Account Name", sf.ad.accountNameValueText, Global.dataInput.serviceAccountName);
			sf.seleU.hardwait(4000);
			verifyFieldValue("Service Account Owner", sf.ad.accountOwnerValueText, InputData.username);
			sf.seleU.hardwait(4000);
			verifyFieldValue("Service Account Status", sf.ad.statusValueText, Global.dataInput.status_Active);
			sf.seleU.hardwait(4000);
			verifyFieldValue("Service Account Record Type", sf.ad.accountRecordTypeValueText, Global.dataInput.acc_RecordType_Service);
			sf.seleU.hardwait(4000);

			// verifyFieldValue("Service Account Shipping Address", sf.ad.shippingAddressValueText, sf.dataInput.addressStreet);

			// If Service account is created along with bunisess account, then parent account should be business account
			if (isWithBusinessAccount) 
			{
				verifyFieldValue("Service Account Parent Account", sf.ad.parentAccountValueText, Global.dataInput.businessAccountName);
			} 
			else 
			{
				verifyFieldValue("Service Account Parent Account", sf.ad.parentAccountValueText, Global.dataInput.parentAccountName);
			}

			// Verify Service Account Premises field value is not empty
			if (sf.seleU.getTextFromWebElement(sf.ad.premisesValueInput).length() > 0) 
			{
				reportStatusPass(methodName + " Validated Premises field value as " + sf.ad.premisesValueInput.getText()
						+ " in  service account details page", true, true);
				
				sf.seleU.clickElementByJSE(sf.ad.premisesValueInputLink);
			} 
			else 
			{
				reportStatusFail(methodName + " Invalid value in premises field in service account details page", true);
			}

			sf.seleU.hardwait(5000);
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Error in Verifying Service Account Field Values", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate Billing Account Details
	 * 
	 *                     If Billing account is created along with bunisess
	 *                     account, then parent account should be business account
	 */
	public void validateBillingAccount(boolean isWithBusinessAccount) throws IOException 
	{
		try 
		{
			// Search Account and Click on Details Tab
			searchAccountFromGlobalSearch(Global.dataInput.billingAccountName, "Billing");
			// selectDetailsTab();
			sf.seleU.hardwait(2000);

			if (sf.seleU.isElementPresent(sf.ad.closeButton)) 
			{
				sf.seleU.clickElementByJSE(sf.ad.closeButton);
			}

			// Verify Fields in Details Tab
			verifyFieldValue("Billing Account Name", sf.ad.accountNameValueText, Global.dataInput.billingAccountName);
			verifyFieldValue("Billing Account Owner", sf.ad.accountOwnerValueText, InputData.username);
			verifyFieldValue("Billing Account Status", sf.ad.statusValueText, Global.dataInput.status_Active);
			// verifyFieldValue("Billing Account Record Type",
			// sf.ad.accountRecordTypeValueText.get(0),
			// Global.dataInput.acc_RecordType_Billing);

			verifyFieldValue("Billing Account Record Type", sf.ad.accountRecordTypeValueText, Global.dataInput.acc_RecordType_Billing);

			// If Billing account is created along with bunisess account, then parent
			// account should be business account
			if (isWithBusinessAccount) 
			{
				verifyFieldValue("Billing Account Parent Account", sf.ad.parentAccountValueText, Global.dataInput.businessAccountName);
			} 
			else 
			{
				verifyFieldValue("Billing Account Parent Account", sf.ad.parentAccountValueText, Global.dataInput.parentAccountName);
			}
			sf.seleU.hardwait(5000);
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Error in Verifying Billing Account Field Values", e);
			e.printStackTrace();
		}
	}

	/**
	 * 1- Select Account option frrom dropdown
	 * 
	 * 2- Select All Accounts
	 * 
	 * 3- Enter Account to be Searched
	 * 
	 * @throws IOException
	 */
	public void searchAccount(String accountName) throws IOException {
		try {
			String methodName = "SFDC_Account@: ";

			// Selecting Accounts from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(2000);

			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
			reportStatusPass(methodName + " Selected Accounts from menu", true, false);
			sf.seleU.hardwait(2000);

			// 2- Select All Accounts
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.acc.allAccountsOption);
			reportStatusPass(methodName + " Selected All Accounts Option", true, false);

			// 3- Enter Account to be Searched
			sf.seleU.clearAndEnterText(sf.home.searchInput, accountName);
			sf.seleU.wait(4000);
			sf.seleU.enterText(sf.home.searchInput, Keys.ENTER);
			sf.seleU.wait(5000);
			if (sf.acc.accountsAllRecords.size() > 1 && sf.seleU
					.getTextFromWebElement(sf.acc.accountsAllRecords.get(sf.acc.accountsAllRecords.size() - 1))
					.equals(accountName)) {
				sf.seleU.clickElementByJSE(sf.acc.accountsAllRecords.get(sf.acc.accountsAllRecords.size() - 1));
			}

			else if (sf.acc.accountsAllRecords.size() == 0) {

				// 2- Select My Accounts
				sf.seleU.clickElementByJSE(sf.home.listViewIcon);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.acc.myAccountsOption);
				reportStatusPass(methodName + " Selected My Accounts Option", true, false);

				// 3- Enter Account to be Searched
				sf.seleU.clearAndEnterText(sf.home.searchInput, accountName);
				sf.seleU.hardwait(4000);
				sf.seleU.enterText(sf.home.searchInput, Keys.ENTER);
				sf.seleU.hardwait(5000);

				if (sf.acc.accountsAllRecords.size() == 0) {
					// 2- Select My Accounts
					sf.seleU.clickElementByJSE(sf.home.listViewIcon);
					sf.seleU.hardwait(2000);
					sf.seleU.clickElementByJSE(sf.acc.recentlyViewedAccountOption);
					reportStatusPass(methodName + " Selected Recently Viewed Accounts Option", true, false);

					// 3- Enter Account to be Searched
					sf.seleU.clearAndEnterText(sf.home.searchInput, accountName);
					sf.seleU.hardwait(4000);
					sf.seleU.enterText(sf.home.searchInput, Keys.ENTER);
					sf.seleU.hardwait(5000);
				}

				sf.seleU.clickElementByJSE(sf.acc.accountsAllRecords.get(0));

			}

			else {

				sf.seleU.clickElementByJSE(sf.acc.accountsAllRecords.get(0));
			}
			reportStatusPass(methodName + " Found and Clicked on  Account " + accountName, true, false);

			sf.seleU.wait(9000);
		} catch (

		Throwable e) {
			reportStatusFail(" Searching an account is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	public void validateRelationshipScore() throws IOException 
	{
		try
		{
			sf.seleU.hardwait(3000);
			// 1-Selecting Accounts from menu
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
			reportStatusPass(" Selected Accounts from menu", true, false);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.home.searchInputBox, InputData.surveyAccounts);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.home.surveyAccountsLink);
			sf.seleU.hardwait(3000);
			
			for(int i=0; i<=sf.home.relationshipScoreValues.size(); i++)
			{
	            String relationshipScore = sf.seleU.getTextFromWebElement(sf.home.relationshipScoreValues.get(i));
	            int relationshipScoreValue = Integer.parseInt(relationshipScore);
	            
				if(relationshipScoreValue>0)
				{
					sf.seleU.clickElementByJSE(sf.home.accountNameLinks.get(i));
					break;
				}
			}
			sf.seleU.hardwait(3000);
						
			if(sf.seleU.isElementDisplayed(sf.ad.relationshipScoreIcon))
			{
				sf.seleU.clickElementByJSE(sf.ad.relationshipScoreDetails);
				if(sf.seleU.isElementDisplayed(sf.ad.relationshipScoreSurveyReport))
				{
					reportStatusPass("Relationship Score Survey Report and Score Displayed", true, true);
				}				
				else
				{
					reportStatusFail("Relationship Score Survey Report is not displayed", true);
				}				
			}
			else
			{
				reportStatusFail("Error in validating Relationship Score", true);
			}			
		}
		catch(Throwable e)
		{
			reportStatusFail("Error in validating Relationship Score", e);
		}
	}

	/**
	 * Select Account from global search
	 * 
	 * @throws IOException
	 */
	public void searchBusAccGlobalSearch(String businessAcc) throws IOException {

		try {

			String methodName = "SFDC_Account@: ";

			// 1-Selecting Accounts from menu
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.wait(2000);

			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
			reportStatusPass(methodName + " Selected Accounts from menu", true, false);
			sf.seleU.wait(2000);

			// 2- Enter Account to be Searched in global search
			sf.seleU.clickElementByJSE(sf.acc.search);
			sf.seleU.wait(2000);

			if (sf.seleU.isElementDisplayed(sf.acc.searchAccountGlobalAfterClick)) {
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, businessAcc);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, Keys.ENTER);
			}

			else { // after click if search more .. is displayed then perform else part
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.globalAccountAndMoreSearch, businessAcc);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.globalAccountAndMoreSearch, Keys.ENTER);
			}
			sf.seleU.hardwait(3000);
			sf.seleU.scrollByCoOrdinates(2);

			boolean isAccountFound = false;
			// click on account menu

			if (sf.acc.accountLinkGlobalSearchResult.size() == 1) {
				sf.seleU.clickElementByJSE(sf.acc.accountLinkGlobalSearchResult.get(0));
				reportStatusPass(methodName + " Found and Clicked on  Account " + businessAcc, true, false);
				isAccountFound = true;
			} else {
				for (int i = 0; i < sf.acc.accountLinkGlobalSearchResult.size(); i++) {

					if (sf.seleU.getElementAttribute(sf.acc.accountLinkGlobalSearchResult.get(i),"title").equalsIgnoreCase(businessAcc)
							&& (sf.seleU.getElementAttribute(sf.acc.accountTypeGlobalSearchResult.get(i),"title").equalsIgnoreCase(InputData.acc_RecordType_Business) || 
									sf.seleU.getElementAttribute(sf.acc.accountTypeGlobalSearchResult.get(i),"title").equalsIgnoreCase(InputData.acc_RecordType_Business_Fr))) {
						sf.seleU.clickElementByJSE(sf.acc.accountLinkGlobalSearchResult.get(i));
						reportStatusPass(methodName + " Found and Clicked on  Account " + businessAcc, true, false);
						isAccountFound = true;
						break;
					}
				}
			}
			if (!isAccountFound) {
				reportStatusFail(methodName + " No such account found as:  " + businessAcc, true);
			}

			if (sf.seleU.isElementPresent(sf.ad.closeButton)) {
				sf.seleU.clickElementByJSE(sf.ad.closeButton);
			}

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Selecting an account is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	public void searchBusAccGlobalSearchInB2B(String businessAcc) throws IOException {

		try {

			String methodName = "SFDC_Account@: ";

			// 1-Selecting Accounts from menu
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
			reportStatusPass(methodName + " Selected Accounts from menu", true, false);
			sf.seleU.wait(2000);

			if (sf.seleU.isElementDisplayed(sf.acc.searchAccountGlobalAfterClick)) {
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, businessAcc);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, Keys.ENTER);
			}

			else { // after click if search more .. is displayed then perform else part
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.globalAccountAndMoreSearch, businessAcc);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.globalAccountAndMoreSearch, Keys.ENTER);
			}
			sf.seleU.hardwait(3000);
			sf.seleU.scrollByCoOrdinates(2);

			boolean isAccountFound = false;
			// click on account menu

			if (sf.acc.accountLinkGlobalSearchResult.size() == 1) {
				sf.seleU.clickElementByJSE(sf.acc.accountLinkGlobalSearchResult.get(0));
				reportStatusPass(methodName + " Found and Clicked on  Account " + businessAcc, true, false);
				isAccountFound = true;
			} else {
				for (int i = 0; i < sf.acc.accountLinkGlobalSearchResult.size(); i++) {

					if (sf.seleU.getElementAttribute(sf.acc.accountLinkGlobalSearchResult.get(i),"title").equalsIgnoreCase(businessAcc)
							&& (sf.seleU.getElementAttribute(sf.acc.accountTypeGlobalSearchResult.get(i),"title").equalsIgnoreCase(InputData.acc_RecordType_Business) || 
									sf.seleU.getElementAttribute(sf.acc.accountTypeGlobalSearchResult.get(i),"title").equalsIgnoreCase(InputData.acc_RecordType_Business_Fr))) {
						sf.seleU.clickElementByJSE(sf.acc.accountLinkGlobalSearchResult.get(i));
						reportStatusPass(methodName + " Found and Clicked on  Account " + businessAcc, true, false);
						isAccountFound = true;
						break;
					}
				}
			}
			if (!isAccountFound) {
				reportStatusFail(methodName + " No such account found as:  " + businessAcc, true);
			}

			if (sf.seleU.isElementPresent(sf.ad.closeButton)) {
				sf.seleU.clickElementByJSE(sf.ad.closeButton);
			}

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Selecting an account is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	
	public void createNewCustomerCase() throws IOException {
		try {
			String methodName= "SFDC_New Customer Case@: ";
			
			sf.seleU.clickElementByJSE(sf.acc.controlDropDown.get(0));
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.acc.newCustomerCaseIcon);
			reportStatusPass(methodName + "Selected New Customer Case ", true, false);
			sf.seleU.hardwait(4000);
			sf.seleU.switchToElementFrame(sf.cases.caseserviceRadio);
			sf.seleU.clickElementByJSE(sf.cases.caseserviceRadio.get(0));
			reportStatusPass(methodName + " Selected case as Customer Service ", true, false);
			sf.seleU.hardwait(2000);
		}
		catch (Throwable e) {
			reportStatusFail(" Error in selecting new customer case", e);
			e.printStackTrace();
		}
	}
	
	public void searchBusAccGlobalSearchStatusPendingApproval(String businessAcc) throws IOException {

		try {

			String methodName = "SFDC_Account@: ";

			// 1-Selecting Accounts from menu

			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.wait(3000);

			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
			reportStatusPass(methodName + " Selected Accounts from menu", true, false);
			sf.seleU.wait(4000);

			// 2- Enter Account to be Searched in global search
			sf.seleU.clickElementByJSE(sf.acc.search);
			sf.seleU.wait(4000);
			if (sf.seleU.isElementDisplayed(sf.acc.searchAccountGlobalAfterClick)) {
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, businessAcc);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, Keys.ENTER);
			}

			else { // after click if sercah more .. is displayed then perform else part
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.globalAccountAndMoreSearch, businessAcc);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.globalAccountAndMoreSearch, Keys.ENTER);
			}
			sf.seleU.hardwait(5000);
			sf.seleU.scrollByCoOrdinates(2);

			boolean isAccountFound = false;
			// click on account menu
			for (int i = 0; i < sf.acc.accountLinkGlobalSearchResult.size(); i++) {

				if (sf.seleU.getTextFromWebElement(sf.acc.accountLinkGlobalSearchResult.get(i)).contains(businessAcc)
						&& sf.seleU.getTextFromWebElement(sf.acc.accountTypeGlobalSearchResult.get(i))

								.equals(Global.dataInput.acc_RecordType_Business)
						&& sf.seleU.getTextFromWebElement(sf.acc.statusFieldAllColumnValues.get(i))
								.equals("Pending Approval")) {

					sf.seleU.clickElementByJSE(sf.acc.accountLinkGlobalSearchResult.get(i));
					reportStatusPass(methodName + " Found and Clicked on  Account " + businessAcc, true, false);
					isAccountFound = true;
					break;
				}
			}

			if (!isAccountFound) {
				reportStatusFail(methodName + " No such account found as:  " + businessAcc, true);
			}

			if (sf.seleU.isElementPresent(sf.ad.closeButton)) {
				sf.seleU.clickElementByJSE(sf.ad.closeButton);
			}

			sf.seleU.hardwait(9000);

		} catch (Throwable e) {
			reportStatusFail(" Selecting an account is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	public void searchBusAccGlobalSearch_DataGovern(String businessAcc) throws IOException {

		try {

			String methodName = "SFDC_Account@: ";

			// 1-Selecting Accounts from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(2000);

			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
			reportStatusPass(methodName + " Selected Accounts from menu", true, false);
			sf.seleU.hardwait(2000);

			sf.seleU.clickElementByJSE(sf.acc.search);
			sf.seleU.wait(4000);

			if (sf.seleU.isElementDisplayed(sf.acc.searchAccountGlobalAfterClick)) {

				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, businessAcc);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, Keys.ENTER);
			}

			else { // after click if sercah more .. is displayed then perform else part
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.globalAccountAndMoreSearch, businessAcc);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.globalAccountAndMoreSearch, Keys.ENTER);
			}

			sf.seleU.hardwait(5000);
			sf.seleU.scrollByCoOrdinates(2);
			sf.seleU.scrollToBottom();

			boolean isAccountFound = false;
			// click on account menu
			for (int i = 0; i < sf.acc.accountLinkGlobalSearchResult.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.acc.accountLinkGlobalSearchResult.get(i)).contains(businessAcc)
						&& sf.seleU.getTextFromWebElement(sf.acc.accountTypeGlobalSearchResult.get(i))
								.equals(Global.dataInput.acc_RecordType_Business)) {
					sf.seleU.clickElementByJSE(sf.acc.accountLinkGlobalSearchResult.get(i));
					reportStatusPass(methodName + " Found and Clicked on  Account " + businessAcc, true, false);
					isAccountFound = true;
					break;
				}
			}

			if (!isAccountFound) {
				reportStatusFail(methodName + " No such account found as:  " + businessAcc, true);
			}

			if (sf.seleU.isElementPresent(sf.ad.closeButton)) {
				sf.seleU.clickElementByJSE(sf.ad.closeButton);
			}

			sf.seleU.hardwait(9000);

		} catch (Throwable e) {
			reportStatusFail(" Selecting an account is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * Select Account from global search and Validate if Business Account is Sync
	 * Present at R4B which was created in B2B.
	 * 
	 * @throws IOException
	 */
	public void b2BSyncR4BSearchBusAccGlobalSearch(String businessAcc) throws IOException {

		try {

			
			String methodName = "SFDC_Account@: ";
			if (sf.seleU.isElementPresent(sf.ad.closeButton)) {
				sf.seleU.clickElementByJSE(sf.ad.closeButton);
			}
			// 1-Selecting Accounts from menu
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.wait(3000);

			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
			reportStatusPass(methodName + " Selected Accounts from menu", true, false);
			sf.seleU.wait(4000);

			// 2- Enter Account to be Searched in global search
			sf.seleU.clickElementByJSE(sf.acc.search);
			sf.seleU.wait(4000);

			if (sf.seleU.isElementDisplayed(sf.acc.searchAccountGlobalAfterClick)) {
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, businessAcc);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, Keys.ENTER);
			}

			else { // after click if search more .. is displayed then perform else part
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.globalAccountAndMoreSearch, businessAcc);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.globalAccountAndMoreSearch, Keys.ENTER);
			}
			sf.seleU.hardwait(5000);
			sf.seleU.scrollByCoOrdinates(2);

			boolean isAccountFound = false;
			// click on account menu

			if (sf.acc.accountLinkGlobalSearchResult.size() == 1) {
				sf.seleU.clickElementByJSE(sf.acc.accountLinkGlobalSearchResult.get(0));
				reportStatusPass(methodName + " Found and Clicked on  Account " + businessAcc, true, false);
				isAccountFound = true;
			} else {
				for (int i = 0; i < sf.acc.accountLinkGlobalSearchResult.size(); i++) {

					if (sf.seleU.getElementAttribute(sf.acc.accountLinkGlobalSearchResult.get(i),"title").equalsIgnoreCase(businessAcc)
							&& (sf.seleU.getElementAttribute(sf.acc.accountTypeGlobalSearchResult.get(i),"title").equalsIgnoreCase(InputData.acc_RecordType_Business) || 
									sf.seleU.getElementAttribute(sf.acc.accountTypeGlobalSearchResult.get(i),"title").equalsIgnoreCase(InputData.acc_RecordType_Business_Fr))) {
						sf.seleU.clickElementByJSE(sf.acc.accountLinkGlobalSearchResult.get(i));
						reportStatusPass(methodName + " Validation Successful - Account is be created in B2B and synced into R4B successfully -Found and Clicked on  Account " + businessAcc, true, false);
						isAccountFound = true;
						break;
					}
				}
			}
			if (!isAccountFound) {
				reportStatusFail(methodName + " No such account found as:  " + businessAcc, true);
			}

			if (sf.seleU.isElementPresent(sf.ad.closeButton)) {
				sf.seleU.clickElementByJSE(sf.ad.closeButton);
			}

			sf.seleU.hardwait(9000);

		} catch (Throwable e) {
			reportStatusFail(" Selecting an account is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	public void searchAccountFromGlobalSearch(String accountName, String accType) throws IOException {

		try {

			String methodName = "SFDC_Account@: ";

			// 1-Selecting Accounts from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(3000);

			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
//			sf.seleU.refreshPage();
			reportStatusPass(methodName + " Selected Accounts from menu", true, false);
			sf.seleU.hardwait(4000);
            
			//Enter Account to be searched in global search
			sf.seleU.clickElementByJSE(sf.acc.search);
			sf.seleU.wait(4000);

			if (sf.seleU.isElementDisplayed(sf.acc.searchAccountGlobalAfterClick)) {
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, accountName);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, Keys.ENTER);
			}

			else { // after click if search more .. is displayed then perform else part
				sf.seleU.hardwait(4000);
				sf.seleU.enterText(sf.acc.globalAccountAndMoreSearch, accountName);
				sf.seleU.hardwait(5000);
				sf.seleU.enterText(sf.acc.globalAccountAndMoreSearch, Keys.ENTER);
			}
			
			sf.seleU.hardwait(5000);
			sf.seleU.scrollByCoOrdinates(2);

			boolean isAccountFound = false;
			// click on account menu
			
			if (sf.acc.accountLinkGlobalSearchResult.size() == 1) {
				sf.seleU.clickElementByJSE(sf.acc.accountLinkGlobalSearchResult.get(0));
				reportStatusPass(methodName + " Found and Clicked on  Account " + accountName + " , Account Type : " + accType, true, false);
				isAccountFound = true;
			} else {
			for (int i = 0; i < sf.acc.accountLinkGlobalSearchResult.size(); i++) {
				if (sf.seleU.getElementAttribute(sf.acc.accountLinkGlobalSearchResult.get(i),"title").contains(accountName)
						&& sf.seleU.getElementAttribute(sf.acc.accountTypeGlobalSearchResult.get(i),"title")
								.equals(accType)) {
					sf.seleU.clickElementByJSE(sf.acc.accountLinkGlobalSearchResult.get(i));
					reportStatusPass(methodName + " Found and Clicked on  Account " + accountName + ", Account Type : "
							+ accType, true, true);
					isAccountFound = true;
					break;
				}
			}

			if (!isAccountFound) {
				reportStatusFail(methodName + " No such account found as:  " + accountName, true);
			}

			sf.seleU.hardwait(9000);

		}
		}
		catch (Throwable e) {
			reportStatusFail(" Selecting an account is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	public void validateRootAccount() throws IOException
	{
		try
		{
			//validate root account field is displayed
			softassert.assertEquals(sf.seleU.getTextFromWebElement(sf.ad.accountNameValueText), Global.dataInput.billingAccountName, " Error in validating Billing Account ");
			sf.seleU.hardwait(3000);
			softassert.assertEquals(sf.seleU.getTextFromWebElement(sf.ad.parentAccountValueText), Global.dataInput.tempbillingAccountName, " Error in validating Billing Account ");
			sf.seleU.hardwait(3000);
			softassert.assertEquals(sf.seleU.getTextFromWebElement(sf.ad.accountRecordTypeValueText), Global.dataInput.acc_RecordType_Billing, " Error in validating Billing Account ");
			sf.seleU.hardwait(3000);
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.ad.rootAccountField), " Error in validating root account field displayed ");
			sf.seleU.hardwait(3000);
			softassert.assertEquals(sf.seleU.getTextFromWebElement(sf.ad.rootAccountValueText), Global.dataInput.businessAccountName, " Error in validating Business Account Name ");
			sf.seleU.hardwait(3000);			
		}
		catch (Throwable e)
		{
			reportStatusFail(" Error in validating Root Account ", e);
			e.printStackTrace();
		}
	}
	
	public void changeBillingParentAccount() throws IOException
	{
		try
		{
            //change billing parent account
			sf.seleU.clickElementByJSE(sf.ad.editParentAccountButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.ad.parentAccountBox);
			sf.seleU.hardwait(4000);
			sf.seleU.enterText(sf.ad.parentAccountInputText, Global.dataInput.tempbillingAccountName);
			sf.seleU.hardwait(5000);
			sf.seleU.enterText(sf.ad.parentAccountInputText, Keys.ARROW_DOWN);
			sf.seleU.hardwait(4000);
			sf.seleU.enterText(sf.ad.parentAccountInputText, Keys.ENTER);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.ad.saveButton);
			sf.seleU.hardwait(3000);			
		}
		catch (Throwable e)
		{
			reportStatusFail(" Error in changing Billing Parent Account ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Select Business Account from global search
	 * 
	 * @throws IOException
	 */
	public void searchBusinessAccountFromGlobalSearch() throws IOException {

		try {

			searchAccountFromGlobalSearch(InputData.relatedBusinessAccountForCusCase,
					Global.dataInput.acc_RecordType_Business);

		} catch (Throwable e) {
			reportStatusFail(" Selecting a business account is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * Select Service Account from global search
	 * 
	 * @throws IOException
	 */
	public void searchServiceAccountFromGlobalSearch() throws IOException {

		try {

			searchAccountFromGlobalSearch(InputData.serviceAccountNameForLogAnActivity,
					Global.dataInput.acc_RecordType_Service);

		} catch (Throwable e) {
			reportStatusFail(" Selecting a service account is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * Select Service Account with Subscriptions from global search
	 * 
	 * @throws IOException
	 */
	public void searchServiceAccountWithSubscriptionsFromGlobalSearch() throws IOException {

		try {

			searchAccountFromGlobalSearch(InputData.serviceAccountWithSusbscriptions,
					Global.dataInput.acc_RecordType_Service);

		} catch (Throwable e) {
			reportStatusFail(" Selecting a service account is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * Select Account from global search after accounts value set in search combobox
	 * 
	 * @throws IOException
	 */
	public void searchAccRestrictedToAccountResults(String accName) throws IOException {

		try {

			String methodName = "SFDC_Account@: ";

			// 1-Selecting Accounts from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showMenuOptionsForSearch);
			sf.seleU.hardwait(2000);

			sf.seleU.clickElementByJSE(sf.home.accountsFromMenuOptionsForSearch);
			reportStatusPass(methodName + " Selected Accounts from search related menu", true, true);
			sf.seleU.hardwait(2000);

			// 2- Enter Account to be Searched in global search
			sf.seleU.enterText(sf.home.searchBox, accName);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.home.searchBox, Keys.ENTER);
			sf.seleU.hardwait(5000);

			if (sf.acc.accountLinkSearchResult.size() > 0) {

				reportStatusPass(methodName + " Found results for the search and displayed ", true, true);
			} else {
				reportStatusFail(methodName + " No such account found as:  " + accName, true);
			}

			sf.seleU.hardwait(9000);

		} catch (Throwable e) {
			reportStatusFail(" Seraching an account is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Search business Account
	 * 
	 *                     Select Account Hierarchy and verify Current Account in
	 *                     Hierarchy
	 * 
	 *                     Validate Billing Account Created
	 */
	public void verifyBillingAccountCreated(String billingType) throws IOException {
		try {

			String methodName = "SFDC_Account@: ";
			String billingAccount = Global.dataInput.businessAccountName + " " + billingType;

			// Search business Account
			searchBusAccGlobalSearch(Global.dataInput.businessAccountName);

			// Select Account Hierarchy and verify Current Account in Hierarchy
			selectDetailsTab();
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.ad.accountHierarchyButton);
			sf.seleU.wait(2000);
			verifyFieldValue("Current Account", sf.ad.currentAccountInHierarchy, Global.dataInput.businessAccountName);

			// Verify Billing Account Created
			boolean isBiliingAccCreated = false;
			for (int i = 0; i < sf.ad.allAccountsInHierarchy.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.ad.allAccountsInHierarchy.get(i)).equals(billingAccount)) {
					sf.seleU.clickElementByJSE(sf.ad.allAccountsInHierarchy.get(i));
					isBiliingAccCreated = true;
					break;
				}
			}

			if (isBiliingAccCreated) {
				reportStatusPass(methodName + billingType + "Billing Account Created : " + billingAccount, true, false);
			} else {
				reportStatusFail(methodName + " No Billing account found as " + billingAccount, true);
			}

			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.ad.detailsTab);
			sf.seleU.wait(2000);

			// Validate Billing Account

			verifyFieldValue(billingType + " Account Record Type", sf.ad.accountRecordTypeValueText,
					Global.dataInput.acc_RecordType_Billing);
			verifyFieldValue(billingType + "Account Parent Account", sf.ad.parentAccountValueText,
					Global.dataInput.businessAccountName);

			if (billingType.contains("CAN")) {
				verifyFieldValue(billingType + "Account Number", sf.ad.accountNumberFieldValue.get(0),
						InputData.superSystemCAN);
				verifyAssetsInBillingAccount();
			} else {
				verifyFieldValue(billingType + " Account Number", sf.ad.accountNumberFieldValue.get(0),
						InputData.v21BAN);
			}

			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail("Billing Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify assets are present in Account>related>assets
	 */
	public void verifyAssetsInBillingAccount() throws IOException {
		try {

			String methodName = "SFDC_Account Related @: ";
			sf.seleU.clickElementByJSE(sf.ar.allRelatedTab.get(1));
			reportStatusPass(methodName + " Clicked on related tab in Accounts Page", true, false);
			sf.seleU.hardwait(5000);

			// Verify assets are present in Account>related>assets
			sf.seleU.ScrolltoElement(sf.ar.billingAssetsSubTabLink);
			if (sf.ar.assetsAllLinks.size() > 0) {
				reportStatusPass(methodName + "Validated assets present in Account>related>assets", true, true);
			} else {
				reportStatusFail(" No assets present in account>realted>assets", true);
			}

		} catch (Throwable e) {
			reportStatusFail("Error in Verifying asserts in Billing Account", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Business Account Name and Address
	 * 
	 *                     Enter Updated Employee Size
	 * 
	 *                     Upload evidence for Employee Size change
	 * 
	 *                     Click on Next button
	 * 
	 */
	public void enterInputForChangeInEmployeeSize(String empSize) throws IOException {
		try {
			String methodName = "SFDC_Account Details@: ";

			if (sf.seleU.isElementPresent(sf.ad.closeButton)) {
				sf.seleU.clickElementByJSE(sf.ad.closeButton);
			}
			// Verify Business Account Name
			// verifyFieldValue("Business Account Name",
			// sf.ad.businessAccountNameAsCompanyName.get(0),
			// Global.dataInput.businessAccountName);

			// verify business addreee
			// verifyFieldValue("Business Address", sf.ad.businessAddressAsCurrentAddress,
			// sf.dataInput.address);

			// Enter Updated Employee Size
			sf.seleU.hardwait(5000);
			sf.seleU.clearAndEnterText(sf.ad.noOfEmployeesInput.get(0), empSize);
			// sf.seleU.clickElementByJSE(sf.ad.noOfEmployeesInput);
//			sf.seleU.switchToElementFrame(sf.ad.noOfEmployeesInput);
//			sf.seleU.hardwait(2000);
//			sf.seleU.ScrolltoElement(sf.ad.noOfEmployeesInput.get(0));
//			sf.seleU.hardwait(2000);
//			sf.seleU.clearAndEnterText(sf.ad.noOfEmployeesInput.get(0), empSize);
		    sf.seleU.hardwait(5000);

			// click on the employee tag to move the cursor control from input text box
//			sf.seleU.hardwait(2000);
//			sf.seleU.clickElementByJSE(sf.ad.employeeTag.get(0));

			// Upload evidence for Employee Size change
			sf.seleU.enterText(sf.ad.uploadDocumentInput.get(0), Constants.SAMPLE_UPLOAD_FILE);
			sf.seleU.hardwait(5000);
			reportStatusPass(methodName + " Entered new Numbere of Employees for update is:" + empSize, true, false);
			sf.seleU.clickElementByJSE(sf.files.uploadDoneButton);
			sf.seleU.hardwait(5000);
			
			// Click on Next button
			sf.seleU.clickElementByJSE(sf.ad.accountDetailsNextButton.get(0));
			reportStatusPass(methodName + " Clicked on Next button in change Employee Size in Account Details page", true, false);
			sf.seleU.hardwait(7000);
		} 
		catch (Throwable e) 
		{
			reportStatusFail("Error in changing Employee Size", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Cutsomer Story Created
	 * 
	 *                     Verify Account in Customer Story
	 * 
	 *                     Click in Customer Story
	 */
	public void verifyCustomerStoryForEmployeeSizeChangeCreated() throws IOException {
		try {

			String methodName = "SFDC_Account@: ";

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.acc.employeeSizeChangeCustomerStory);

			// Verify Cutsomer Story Created
			verifyFieldValue("Customer Story ", sf.acc.employeeSizeChangeCustomerStory.get(0),
					Global.dataInput.customerStoryEmployeeSizeChange);

			// click on cutsomer Story
			sf.seleU.clickElementByJSE(sf.acc.employeeSizeChangeCustomerStory.get(0));
			reportStatusPass(methodName + " Clicked on Customer Story of change Employee Size in Account Details page",
					true, false);
			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail("Error in viewing Customer Story for Employee Size Change", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Account Status and Legal Name
	 * 
	 *                     Verify Account History ( Record Locked and old/new legal
	 *                     name )
	 * 
	 *                     Verify attached document in files section
	 * 
	 *                     Verify there is only one account icon
	 */
	public void verifyLegalNameChangePendingApproval() throws IOException {
		try {

			String methodName = "SFDC_Account@: ";
			sf.seleU.switchToDefaultContent();
//			sf.seleU.switchToElementFrame(sf.ad.detailsTabAll);

//			selectDetailsTab();
			sf.seleU.clickElementByJSE(sf.ad.overviewTab);
			sf.seleU.hardwait(2000);

			// Verify Account Status and Legal Name
			verifyFieldValue("Business Account Status", sf.ad.statusValueText, Global.dataInput.status_PendingApproval);
			verifyFieldValue("Buniess Account Legal Name", sf.ad.legalNameValueText,
					Global.dataInput.updatedBusinessAccountLegalName);

//			if (sf.seleU.isElementDisplayed(sf.ar.relatedTab))
				sf.seleU.clickElementByJSE(sf.ar.relatedTab);

			sf.seleU.wait(10000);

			// Verify Account History ( Record Locked and old/new legal name )

//			sf.seleU.ScrolltoElement(sf.ar.accountHistorySubTabLink);
//			verifyFieldValue("Business Account History", sf.ar.accountHistoryFieldAll.get(0),
//					Global.dataInput.field_RecordLocked);
//			verifyFieldValue("Buniess Account History Original Legal Name value",
//					sf.ar.accountHistoryFieldAll.get(4), Global.dataInput.businessAccountLegalName);
//			verifyFieldValue("Buniess Account History New Legal Name value", sf.ar.accountHistoryFieldAll.get(5),
//					Global.dataInput.updatedBusinessAccountLegalName);

			// Verify attached document in files section
//			sf.seleU.scrollUpByCoOrdinates();
//			sf.seleU.ScrolltoElement(sf.ar.fileRowAllValues.get(0));
//			if (sf.seleU.isElementPresent(sf.ar.fileRowAllValues)) {
//				reportStatusPass(
//						methodName + "Verified uploaded evidence for legal name change is attached in files section",
//						true, true);
//			} else {
//				reportStatusFail(methodName + "Missing evidence for legal name change  in files section", true);
//			}

//			sf.seleU.switchToDefaultContent();
			sf.seleU.ScrolltoElement(sf.ar.approvalHistorySubTabLink);
			sf.seleU.hardwait(3000);
			sf.seleU.ScrolltoElement(sf.ar.competitiveInsights);
			sf.seleU.hardwait(3000);
			sf.seleU.ScrolltoElement(sf.ar.entitlements);			
			sf.seleU.wait(3000);			
			sf.seleU.ScrolltoElement(sf.ar.notes);						
			sf.seleU.wait(4000);			
		String files= sf.seleU.getTextFromWebElement(sf.ar.numberOfFiles).replaceAll("[\\[\\](){}]","");			
		int numberOfFiles= Integer.parseInt(files);		
		System.out.println(numberOfFiles);		
		if(numberOfFiles>0) {
			reportStatusPass(methodName + "Verified uploaded evidence for legal name change is attached in files section",
					true, true);	
		}
		else {
			reportStatusFail(methodName + "Missing evidence for legal name change  in files section", true);
		}
			
		} catch (Throwable e) {
			reportStatusFail("Error in verifying legal name change status", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify All 5 icons are present in the account section
	 * 
	 *                     Verify Account Status and Legal Name
	 * 
	 *                     Verify Account History ( Record Unlocked and Status
	 *                     change from 'pending approval' to 'active' )
	 */
	public void verifyLegalNameChangeApprovedRejected() throws IOException {
		try {

			String methodName = "SFDC_Account@: ";

			sf.seleU.switchToDefaultContent();
			selectDetailsTab();
			sf.seleU.hardwait(2000);

			// Verify Account Status and Legal Name
			verifyFieldValue("Business Account Status", sf.ad.statusValueText, Global.dataInput.status_Active);
			verifyFieldValue("Buniess Account Legal Name", sf.ad.legalNameValueText,
					Global.dataInput.updatedBusinessAccountLegalName);

			if (sf.seleU.isElementDisplayed(sf.ar.relatedTab))
				sf.seleU.clickElementByJSE(sf.ar.relatedTab);

			sf.seleU.wait(10000);

			// Verify Account History ( Record Unlocked and Status change from 'pending
			// approval' to 'active )
			sf.seleU.ScrolltoElement(sf.ar.approvalHistorySubTabLink);
			sf.seleU.hardwait(3000);
			sf.seleU.ScrolltoElement(sf.ar.accountHistorySubTabLink);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Business Account History", sf.ar.accountHistoryFieldAllRows.get(0),
					Global.dataInput.field_RecordUnlocked);
			verifyFieldValue("Buniess Account History Original Value", sf.ar.accountHistoryOriginalValueAllRows.get(1),
					Global.dataInput.status_PendingApproval);
			verifyFieldValue("Buniess Account History New  value", sf.ar.accountHistoryNewValueAllRows.get(1),
					Global.dataInput.status_Active);

		} catch (Throwable e) {
			reportStatusFail("Error in verifying approved legal name change", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Account Details and Edit Sales Segement
	 * 
	 *                     Save Sales Segement as Mid-Market
	 */
	public void addSalesSegmentInAccountDetails() throws IOException {
		try {

			String methodName = "SFDC_Account Details@: ";

			// Click on Account Details and Edit Sales Segement
			selectDetailsTab();
			sf.seleU.hardwait(2000);

			sf.seleU.clickElementByJSE(sf.ad.editSalesSegmentButton);
			sf.seleU.hardwait(2000);

			reportStatusPass(methodName + " Clicked on Edit Segment Button", true, false);

			sf.seleU.clickElementByJSE(sf.ad.editSalesSegmentInput);
			sf.seleU.hardwait(2000);

			// Save Sales Segement as Mid-Market
			// sf.seleU.clickElementByJSE(sf.ad.midMarketSegment);
			sf.seleU.clickElementByJSE(sf.ad.smbSegment);

			if (sf.seleU.isElementDisplayed(sf.ad.industryInput)) {
				sf.seleU.clickElementByJSE(sf.ad.industryInput);
				sf.seleU.clickElementByJSE(sf.ad.fsSegment);
			}
			sf.seleU.clickElementByJSE(sf.ad.saveButton);

			reportStatusPass(methodName + " Clicked On SMB Segment", true, false);
			sf.seleU.hardwait(2000);

			// verifyFieldValue("Sales Segment", sf.ad.SalesSegementValueInput,
			// sf.dataInput.salesSegment);
//			verifyFieldValue("Sales Segment", sf.ad.salesSegementValueInput, "SMB");
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail("Error in Adding Sales Segement in Account Details", e);
			e.printStackTrace();

		}
	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Click on Edit Credit Review Button
	 * 
	 *                              Click on Exempt Credit Review Checkbox
	 * 
	 *                              Click on Save Button
	 */
	public void checkCreditFraudExempt() throws IOException, InterruptedException {
		String methodName = "SFDC_Account Details@: ";

		selectDetailsTab();
		sf.seleU.hardwait(2000);

		// Click on Edit Credit Review Button
		sf.seleU.clickElementByJSE(sf.ad.editCreditReviewButton);
		reportStatusPass(methodName + " Clicked on Edit Credit Review Button", true, false);
		sf.seleU.hardwait(2000);

		// Click on Exempt Credit Review Checkbox
		sf.seleU.clickElementByJSE(sf.ad.creditReviewExemptCheckbox);
		reportStatusPass(methodName + " Clicked on Exempt Credit Review Checkbox", true, false);
		sf.seleU.hardwait(2000);

		// Click on Save Button
		sf.seleU.clickElementByJSE(sf.ad.saveButton);
		reportStatusPass(methodName + " Clicked On Save Button", true, false);
		sf.seleU.hardwait(2000);

	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Click on account details tab
	 * 
	 *                              Enter the credit check assigned, available and
	 *                              last review date values based on the test data
	 * 
	 *                              Click on Save Button
	 */
	public void creditCheckEnterPrice(Hashtable<String, String> dataTable) throws IOException, InterruptedException {
		try {
			String methodName = "SFDC_Credit Check Required@: ";

			sf.seleU.hardwait(5000);
			// click on account details tab button
			selectDetailsTab();
			sf.seleU.hardwait(2000);

			sf.seleU.ScrolltoElement(sf.ad.creditLimitAssignedEditButton);
			sf.seleU.clickElementByJSE(sf.ad.creditLimitAssignedEditButton);
			sf.seleU.hardwait(2000);

			String creditLimitAssigned = dataTable.get("Credit_Limit_Assigned").trim();
			String creditLimitAvailable = dataTable.get("Credit_Limit_Available").trim();
			sf.seleU.hardwait(2000);

			// Enter the credit limit assign and available value as
			sf.seleU.clearAndEnterText(sf.ad.enterCreditLimitAssign, creditLimitAssigned);
			reportStatusPass(methodName + " Entered Credit Limit Assigned Value as" + creditLimitAssigned, true, false);
			sf.seleU.hardwait(2000);

			sf.seleU.clearAndEnterText(sf.ad.enterCreditLimitAvailable, creditLimitAvailable);
			reportStatusPass(methodName + " Entered Credit Limit Available Value as" + creditLimitAvailable, true,
					false);
			sf.seleU.hardwait(2000);

			// Enter the last review date depending on the test data
			if (dataTable.get("Credit_Check_LastReviewDate").trim().equals("More Then Thirty Days")) {
				sf.seleU.clearAndEnterText(sf.ad.enterLastCreditReviewDate,
						Global.dataInput.creditCheckLastReviewDateFormattedThirtyDaysBefore);
				reportStatusPass(methodName + " Entered the last review date as "
						+ Global.dataInput.creditCheckLastReviewDateFormattedThirtyDaysBefore, true, false);
			} else if (dataTable.get("Credit_Check_LastReviewDate").trim().equals("Less Then Thirty Days")) {
				sf.seleU.clearAndEnterText(sf.ad.enterLastCreditReviewDate,
						Global.dataInput.creditCheckLastReviewDateFormattedTenDaysBefore);
				reportStatusPass(methodName + " Entered the last review date as "
						+ Global.dataInput.creditCheckLastReviewDateFormattedTenDaysBefore, true, false);

			} else {
				reportStatusPass(methodName + " Entered the last review Date as empty", true, false);
			}

			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.ad.enterCreditLimitAvailable, Keys.TAB);

			sf.seleU.clickElementByJSE(sf.ad.saveButtonCreditCheck);
			reportStatusPass(methodName + " Clicked on save button for credit check review", true, false);

			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail("Error in enter the credit check values for validation", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Click on account details tab
	 * 
	 *                              Extract the credit check entered values for
	 *                              validation
	 * 
	 *                              Click on Save Button
	 */
	public void extractCreditCheckRequiredValidationValues() throws IOException, InterruptedException {
		try {
			String methodName = "SFDC_Credit Check Required@: ";

			String credit_ReviewDate = "";
			String credit_LimitAssigned = "";
			String credit_LimitAvailable = "";
			Global.dataInput.creditCheck_ValidationFlag = false;
			sf.seleU.hardwait(5000);

			// click on account details tab button
			selectDetailsTab();
			sf.seleU.hardwait(5000);
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.ScrolltoElement(sf.ad.creditLimitAssignedValue);
			sf.seleU.wait(3000);
			credit_LimitAssigned = sf.seleU.getTextFromWebElement(sf.ad.creditLimitAssignedValue).split("\\.")[0]
					.replace("$", "").replace(",", "");
			credit_LimitAvailable = sf.seleU.getTextFromWebElement(sf.ad.creditLimitAvailableValue).split("\\.")[0]
					.replace("$", "").replace(",", "");
			sf.seleU.hardwait(2000);
			credit_ReviewDate = sf.seleU.getTextFromWebElement(sf.ad.lastCreditReviewValue);

			reportStatusPass(methodName + " Extracted credit limit assigned date as " + credit_LimitAssigned
					+ " Extracted credit limit available date as " + credit_LimitAvailable + " Credit_review Date as"
					+ credit_ReviewDate, true, false);
			Global.dataInput.credit_Assigned = Integer.valueOf(credit_LimitAssigned);
			Global.dataInput.credit_Available = Integer.valueOf(credit_LimitAvailable);

			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail("Error in extracting credit check required", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Emp size in the account details page
	 */
	public void verifyEmployeeSizeInDetails(String empSize) throws IOException {
		try {

			String methodName = "SFDC_Account Details@: ";
			if (sf.seleU.isElementPresent(sf.ad.closeButton)) {
				sf.seleU.clickElementByJSE(sf.ad.closeButton);
			}
			// sf.seleU.hardwait(3000);
			// sf.seleU.clickElementByJSE(sf.cd.closeAlert);
			sf.seleU.wait(2000);
			// Click on Account Details and verify the field
			selectDetailsTab();
			sf.seleU.hardwait(2000);
			verifyFieldValue(methodName + " Employee size is ", sf.ad.newEmployeesValueText, empSize);
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail("Error in Adding Sales Segement in Account Details", e);
			e.printStackTrace();

		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify business account status in the account details
	 *                     page
	 */
	public void verifyAccountStatusInDetails() throws IOException {
		try {
			String methodName = "SFDC_Account Details@: ";
			sf.seleU.hardwait(5000);

			// Click on Account Details and verify the status value
			verifyFieldValue(methodName + " Account status is ", sf.ad.accountInactive,
					Global.dataInput.b2b_status_Inactive);
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail("Error in veryfing the account status in Account Details", e);
			e.printStackTrace();

		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify business account status in the account details
	 *                     page matches passed argument
	 */
	public void verifyAccountStatusInDetails(String status) throws IOException {
		try {
			String methodName = "SFDC_Account Details@: ";
			sf.seleU.hardwait(5000);

			// Click on Account Details and verify the status value
			verifyFieldValue(methodName + " Account status ", sf.ad.accountInactive, status);
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail("Error in veryfing the account status in Account Details", e);
			e.printStackTrace();

		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Log An Activity button
	 */
	public void logAnActivity() throws IOException {

		String methodName = "SFDC_Account Details@: ";

		try {

			sf.seleU.wait(3000);
			if (!sf.seleU.isElementDisplayed(sf.cd.logASalesActivityButton))
				sf.seleU.clickElementByJSE(sf.lead.showMoreActionsButton);
			sf.seleU.clickElementByJSE(sf.cd.logASalesActivityButton);

			sf.seleU.wait(3000);
			reportStatusPass(methodName + " Clicked on Log An Activity button from Account ", true, false);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not click on Log An Activity", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Log Activity button text - Log A Sales Activity
	 */
	public void validateLogSalesActivityButton() throws IOException {

		String methodName = "SFDC_Account Details@: ";

		try {

			sf.seleU.wait(3000);
			if (!sf.seleU.isElementDisplayed(sf.cd.logASalesActivityButton))
				sf.seleU.clickElementByJSE(sf.lead.showMoreActionsButton);

			verifyFieldValue("Log Activity Button Label text", sf.cd.logASalesActivityButton,
					InputData.logActivityLabel);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not verify Log An Activity button text", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Edit Sub industry for Account
	 */
	public void editSubIndustryForBusinessAccount(int option) throws IOException {

		String methodName = "SFDC_Account Details@: ";

		try {
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.ad.editIndustryButton);
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.ad.subIndustryInput);
			sf.seleU.wait(3000);
			if (option == 1)
				sf.seleU.clickElementByJSE(sf.ad.otherTransportationSubIndOption);
			else
				sf.seleU.clickElementByJSE(sf.ad.utilitiesSubIndOption);
			sf.seleU.wait(3000);

			reportStatusPass(methodName + " Changed Sub-Industry for Account", true, true);
			sf.seleU.clickElementByJSE(sf.ad.saveButton);
			sf.seleU.wait(3000);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not edit Sub Industry for Account", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Edit Industry for Account
	 */
	public void editIndustryForBusinessAccount() throws IOException {

		String methodName = "SFDC_Account Details@: ";

		try {
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.ad.editIndustryButton);
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.ad.industryInput);
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.ad.infraServicesIndustryOption);
			sf.seleU.wait(3000);
			reportStatusPass(methodName + " Changed Industry for Account", true, true);

			sf.seleU.clickElementByJSE(sf.ad.saveButton);
			sf.seleU.wait(3000);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not edit Industry for Account", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate Industry - SubIndustry Help text
	 */
	public void verifyIndSubIndHelpText() throws IOException {

		String methodName = "SFDC_Account Details@: ";

		try {
			sf.seleU.wait(3000);
			verifyFieldDisplayed("Industry Field", sf.ad.editIndustryButton);
			verifyFieldDisplayed("Sub-Industry Field", sf.ad.editSubIndustryButton);
			sf.seleU.wait(3000);

			// Verify Help Text
			sf.seleU.mouseOverOnElement(sf.ad.helpTextIndustryButton);
			sf.seleU.wait(3000);
			verifyFieldValue("Industry Help Text", sf.ad.helpTextIndustry, InputData_Sales.industryHelpText);

			sf.seleU.mouseOverOnElement(sf.ad.helpTextSubIndustryButton);
			sf.seleU.wait(3000);

			verifyFieldValue("Sub-Industry Help Text", sf.ad.helpTextIndustry, InputData_Sales.industryHelpText);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not validate help text for Industry/Sub-Industry for Account", e);
			e.printStackTrace();
		}
	}
	
	public void validateAccountAndContactDetails() throws IOException
	{
		try
		{
			sf.seleU.clickElementByJSE(sf.ar.contactTab);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.cd.contactNameIcon);
			sf.seleU.hardwait(4000);
			sf.seleU.switchToElementFrame(sf.cd.contactNumberValue);
			sf.seleU.hardwait(3000);
			softassert.assertEquals(sf.seleU.getTextFromWebElement(sf.cd.contactNumberValue.get(0)), Global.dataInput.contactPhoneNumber, "Error in validating contact number");
			sf.seleU.hardwait(3000);
			softassert.assertEquals(sf.seleU.getTextFromWebElement(sf.cd.companyNameValue), Global.dataInput.businessAccountLegalName, "Error in validating company name");
			sf.seleU.hardwait(3000);
			reportStatusPass(" Validated Account and Contact Details Sync from R4B to B2B ", true, true);
			sf.seleU.hardwait(3000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(2000);
		}
		catch (Throwable e)
		{
			reportStatusFail(" Error in validating account and contact details ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate Bus Acc Page Layout
	 */
	public void validateBusAccDetailsPageLayout() throws IOException {

		String methodName = "SFDC_Account Details@: ";

		try {
			sf.seleU.wait(3000);
			verifyFieldNotDisplayed("Details Tab", sf.ad.detailsTab);
			verifyFieldDisplayed("Overview Tab", sf.ad.overviewTab);
			sf.seleU.clickElementByJSE(sf.ad.overviewTab);
			sf.seleU.hardwait(2000);
			boolean oppSectionFound = false;
			for (int i = 0; i < 100; i++) {
				sf.seleU.enterText(sf.ad.overviewTab, Keys.PAGE_DOWN);
				if (sf.seleU.isElementPresent(sf.ar.opportunitiesSectionText)) {
					oppSectionFound = true;
					break;
				}
			}
			if (oppSectionFound) {
				// click on New Opportunity Button
				sf.seleU.ScrolltoElement(sf.ar.opportunitiesSectionText);
				reportStatusPass(methodName + " Found Opportunity Section in Overview Tab as per new layout", true,
						true);
				sf.seleU.hardwait(5000);
			} else {
				reportStatusFail(methodName + " Could Not find opportunity Section", true);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not verify business acc layout", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     View All Opportunities
	 */
	public void viewAllOpportunities() throws IOException {

		String methodName = "SFDC_Account Details@: ";

		try {
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.ad.overviewTab);
			boolean oppSectionFound = false;
			for (int i = 0; i < 100; i++) {
				sf.seleU.enterText(sf.ad.overviewTab, Keys.PAGE_DOWN);
				if (sf.seleU.isElementPresent(sf.ar.opportunitiesSectionText)) {
					oppSectionFound = true;
					break;
				}
			}
			if (oppSectionFound) {

				sf.seleU.ScrolltoElement(sf.ar.opportunitiesSectionText);

				sf.seleU.clickElementByJSE(sf.ad.viewAllOpportunities);
				sf.seleU.hardwait(5000);
				reportStatusPass(
						methodName
								+ " Found Opportunity Section in Overview Tab as per new layout, Clicked on View All",
						true, true);
			} else {
				reportStatusFail(methodName + " Could Not find opportunity Section", true);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not view all opportunities", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is Displayed
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass("Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail("Field " + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is not Displayed
	 */
	public void verifyFieldNotDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (!sf.seleU.isElementDisplayed(element)) {
				reportStatusPass("Validated " + fieldName + " is not displayed", true, true);
			} else {
				reportStatusFail("Field " + fieldName + " is displayed, it should not be there", true);
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

	public void selectDetailsTab() {

		if (sf.seleU.isElementDisplayed(sf.ad.showMoreTabs))
			sf.seleU.clickElementByJSE(sf.ad.showMoreTabs);

		if (sf.seleU.isElementDisplayed(sf.ad.detailsTab))
			sf.seleU.clickElementByJSE(sf.ad.detailsTab);
		else {

			if (sf.seleU.isElementPresent(sf.ad.overviewTab)) {
				sf.seleU.clickElementByJSE(sf.ad.overviewTab);
			}

		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Account Ownership Transfer button
	 */
	public void accountOwnershipTransfer() throws IOException {

		String methodName = "SFDC_Account Details@: ";

		try {

			sf.seleU.wait(3000);
			if (!sf.seleU.isElementDisplayed(sf.cd.logASalesActivityButton))
				sf.seleU.clickElementByJSE(sf.lead.showMoreActionsButton);
			sf.seleU.clickElementByJSE(sf.cd.accountOwnershipTransfer);

			sf.seleU.wait(3000);
			reportStatusPass(methodName + " Clicked on Account Ownership Transfer button from Account ", true, false);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not click on Account Ownership Transfer", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate as AE , clicking "Account Ownership Transfer"
	 *                     button, if the current account owner does NOT have
	 *                     manager assigned
	 */
	public void validateAccountOwnershipTransfer() throws IOException {

		String methodName = "SFDC_Account Details@: ";

		try {

			sf.seleU.wait(3000);
			String str1 = "The Manager User of the Account Owner is not populated. Please update the current Owner SumanthReddy Bolledl User record with a Manager.";

			if (str1.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.cd.accountOwnershipTransferMessage)))
				reportStatusPass(methodName
						+ " Validation Successful- Message Displayed - Please update the current Owner {!Account OwnerName} User record with a Manager.",
						true, true);
			sf.seleU.wait(3000);

		} catch (Throwable e) {
			reportStatusFail(methodName
					+ " Validation Fail- Not able to see message- Please update the current Owner {!Account OwnerName} User record with a Manager.",
					e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate as AE , clicking "Account Ownership Transfer"
	 *                     button, if the current account owner does NOT have
	 *                     manager assigned and account owner has manager access
	 */
	public void validateAccountOwnershipTransferManagerAccess() throws IOException {

		String methodName = "SFDC_Account Details@: ";

		try {

			sf.seleU.wait(3000);
			String str2 = "The Manager User of the New Account Owner is not populated. Please update the new Owner nandan more_AE User record with a Manager.";
			String str3 = "The Manager User of the Account owner is not Active. Please update the current owner's Manager field with an active user";
			sf.seleU.wait(3000);
			sf.seleU.enterText(sf.cd.nameOfNewContactOwner.get(0), Global.dataInput.aEProfileNameForAccOwnerTransfer);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.cd.nameOfNewContactOwner.get(0), Keys.ENTER);
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.cd.selectAEProfile);
			sf.seleU.hardwait(2000);
			sf.seleU.selectTextFromDropDown(sf.cd.baseRevenueQuotaTransfer.get(0),
					Global.dataInput.baseRevenueQuotaTransfer);
			 sf.seleU.wait(2000);
			sf.seleU.enterText(sf.cd.acquisitionMarginTCVQuotaTransfer,
					Global.dataInput.acquisitionMarginTCVQuotaTransfer);
			 sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.cd.nextButtonAccOwnerTransferManagerFlow);
			sf.seleU.wait(6000);
			if (str2.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.cd.accountOwnershipTransferMessageManagerFlow)))
				reportStatusPass(methodName
						+ " Validation Successful- Message Displayed - Please update the new Owner {!NewAccountOwner} User record with a Role.",
						true, true);
			else if(str3.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.cd.accountOwnershipTransferMessageManagerFlow)))
				reportStatusPass(methodName
						+ " Validation Successful- Message Displayed - The Manager User of the Account owner is not Active.Please update the current owner's Manager field with an active user",
						true, true);
			else
				reportStatusPass(methodName
						+ " Validation Successful- (TAG) Account transfered sucessfully",
						true, true);
			
			sf.seleU.wait(6000);

		} catch (Throwable e) {
			reportStatusFail(methodName
					+ " Validation Fail- Not able to see message or transfered account",
					e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate- the fields Credit limit, Last credit review and
	 *                     Credit Risk field is editable for any assignment on
	 *                     account
	 */
	public void verifyMandatoryFieldsAreEditable() throws IOException {

		String methodName = "SFDC_Account Details@: ";
		try {
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.ad.overviewTab);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + " Before Hitting the Total Credit Limit Assigned Field Button ", true, true);
			// Click on Edit button around The Total Credit Limit Assigned
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.ad.creditLimitAssignedEditButton);
			sf.seleU.wait(5000);

			// Verify Cancel and Save Filed in Pop up and Hit Cancel
			String strCancel = "Cancel";
			if (strCancel.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.ad.cancelEdit)))
				reportStatusPass(methodName + " The Total Credit Limit Assigned Field is Editable", true, true);

			else
				reportStatusFail("The Total Credit Limit Assigned Field is Not Editable ", true);

			sf.seleU.wait(5000);
			// Hit Cancel as we are not modifying anything
			sf.seleU.clickElementByJSE(sf.ad.cancelEdit);
			sf.seleU.wait(5000);

			reportStatusPass(methodName + " Before Hitting the Last credit review Field Button ", true, true);
			// Click on Edit button around The Last credit review
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.ad.lastCreditReviewEditButton);
			sf.seleU.wait(5000);

			// Verify Cancel and Save Filed in Pop up and Hit Cancel
			String strCancel2 = "Cancel";
			if (strCancel2.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.ad.cancelEdit)))
				reportStatusPass(methodName + " The Last credit review Field is Editable", true, true);

			else
				reportStatusFail("The Last credit review Field is Not Editable ", true);

			sf.seleU.wait(5000);
			// Hit Cancel as we are not modifying anything
			sf.seleU.clickElementByJSE(sf.ad.cancelEdit);
			sf.seleU.wait(5000);

			reportStatusPass(methodName + " Before Hitting the Credit Risk Value Field Button ", true, true);
			// Click on Edit button around Credit Risk Value
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.ad.creditRiskValueEditButton);
			sf.seleU.wait(5000);

			// Verify Cancel and Save Filed in Pop up and Hit Cancel
			String strCancel3 = "Cancel";
			if (strCancel2.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.ad.cancelEdit)))
				reportStatusPass(methodName + " The Credit Risk Value Field is Editable", true, true);

			else
				reportStatusFail("The Credit Risk Value Field is Not Editable ", true);

			sf.seleU.wait(5000);
			// Hit Cancel as we are not modifying anything
			sf.seleU.clickElementByJSE(sf.ad.cancelEdit);
			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Credit Limit, Last Credit Review and Credit Risk field",
					e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Business Account Name and Address
	 * 
	 *                     Enter Updated Employee Size
	 * 
	 *                     Upload evidence for Employee Size change
	 * 
	 *                     Click on Next button
	 * 
	 */
	public void enterInputForChangeInEmployeeSizeThruDataGov(String empSize) throws IOException {
		try {
			String methodName = "SFDC_Account Details@: ";

			sf.seleU.hardwait(2000);
			sf.seleU.clearAndEnterText(sf.ad.enterNumberOfEmployees, empSize);
			sf.seleU.hardwait(2000);

			// Added Industry and Sub-Industry Field as Account created in B2B and those
			// values are missing
			sf.seleU.clickElementByJSE(sf.ad.industryInput);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.ad.fsSegment);
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.ad.subIndustryInput);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.ad.bankingSubIndOption);
			sf.seleU.clickElementByJSE(sf.ad.saveButton);
			sf.seleU.hardwait(5000);
			reportStatusPass(methodName + " Entered new Numbere of Employees for update is:" + empSize, true, false);

			// Click on Next button

			// reportStatusPass(methodName + " Clicked on Save button in change Employee
			// Size in Account Details page",
			// true, false);

			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail("Error in changing Employee Size", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Emp size in the account details page
	 */
	public void verifyEmployeeSizeInDetailsThruDataGov(String empSize) throws IOException {
		try {

			String methodName = "SFDC_Account Details@: ";
			sf.seleU.hardwait(3000);

			// Click on Account Details and verify the field
			// selectDetailsTab();
			sf.seleU.hardwait(2000);
			verifyFieldValue(methodName + " Employee size is ", sf.ad.employeesValueText, empSize);
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail("Error in Adding Employee Size in Account Details", e);
			e.printStackTrace();

		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Check Serviceability Button is Available on
	 *                     Service Account
	 */
	public void verifyCheckServiceabilityButtonExixt() throws IOException {
		try {
			// Click on Account Details and verify the field
			sf.seleU.clickElementByJSE(sf.ad.premiseDetailsLink);
			sf.seleU.hardwait(2000);
			verifyFieldDisplayed("Check Serviceability", sf.ad.checkServiceability);

		} catch (Throwable e) {
			reportStatusFail(" Verifying Check Serviceability presence was unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify User is able to add Service Location
	 */
	public void addExistingServiceableLocationToPremise() throws IOException {
		try {

			String methodName = "SFDC_Account Details@: ";
			sf.seleU.hardwait(3000);

			sf.seleU.clickElementByJSE(sf.ad.checkServiceability);
			sf.seleU.hardwait(5000);
			// verifyFieldValue(methodName + " Employee size is ", sf.ad.employeesValueText,
			// empSize);
			// sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail("Error in Adding Employee Size in Account Details", e);
			e.printStackTrace();

		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate as DG , clicking "Account Owners" button, if the
	 *                     current account owner does NOT have manager assigned and
	 *                     account owner has manager access
	 */
	public void changeOwner() throws IOException {

		String methodName = "SFDC_Account Details@: ";

		try {

			sf.seleU.wait(3000);
			if (sf.seleU.isElementDisplayed(sf.ar.detailsTab))
				sf.seleU.clickElementByJSE(sf.ar.detailsTab);
			else {
				sf.seleU.clickElementByJSE(sf.ar.moreTabs);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.ar.detailsTab);

			}
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.ad.changeOwnerButton);
			sf.seleU.wait(3000);
			sf.seleU.enterText(sf.ad.searchUsersInput.get(0), Global.dataInput.changeOwer);
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.ad.searchUsersInput.get(0), Keys.ENTER);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.ad.selectOwner);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.ad.changeOwnerAfterSelection);
			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail(methodName + "Not able to change owner.", e);
			e.printStackTrace();
		}
	}

	/**
	 * If Searched account is at the bottom of the page (WACC) Select Accounts from
	 * global search accounts in Account
	 * 
	 * @throws IOException
	 */
	public void searchAccountFromGlobSearch(String accountName, String accType) throws IOException {

		try {

			String methodName = "WACC_Account@: ";

			// 1-Selecting Accounts from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(2000);

			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
			sf.seleU.refreshPage();
			reportStatusPass(methodName + " Selected Accounts from menu", true, false);
			sf.seleU.hardwait(2000);

			// 2- Enter Account to be Searched in global search
			if (!sf.seleU.isElementDisplayed(sf.acc.globalAccountSearch)) {
				sf.seleU.clickOnElement(sf.home.searchBoxButton);
				sf.seleU.hardwait(5000);
			}
			sf.seleU.enterText(sf.acc.globalAccountSearch, accountName);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.acc.accGlobalSearchMark);
			boolean isAccountFound = false;
			// click on account menu	
			for (int i = 0; i < sf.acc.accountLinkGlobalSearchItems.size(); i++) {
			sf.seleU.hardwait(2000);
				if (sf.seleU.getTextFromWebElement(sf.acc.accountLinkGlobalSearchItems.get(i)).equals(accountName)
						&& sf.seleU.getTextFromWebElement(sf.acc.accountTypeGlobalSearchResultItems.get(i))
								.equals(accType)
						&& sf.seleU.getTextFromWebElement(sf.acc.statusFieldAllColumnItems.get(i)).equals("Active")) {
					sf.seleU.clickElementByJSE(sf.acc.accountLinkGlobalSearchItems.get(i));
					reportStatusPass(methodName + " Found and Clicked on  Account " + accountName + ", Account Type : "
							+ accType, true, true);
					isAccountFound = true;
					break;
				}
			}

			if (!isAccountFound) {
				reportStatusFail(methodName + " No such account found as:  " + accountName, true);
			}

			sf.seleU.hardwait(9000);

		} catch (Throwable e) {
			reportStatusFail(" Selecting an account is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * Verify Newly created account has Territories Assigned as per Territories
	 * rule.
	 * 
	 * @throws IOException
	 */
	public void VerifyTerritories() throws IOException {

		try {
			String methodName = "SFDC_Account@: ";
			sf.seleU.wait(8000);
			sf.seleU.refreshPage();
			sf.seleU.wait(4000);

			if (sf.seleU.isElementDisplayed(sf.ar.relatedTab))
				sf.seleU.clickElementByJSE(sf.ar.relatedTab);
			sf.seleU.wait(6000);
			sf.seleU.switchToElementFrame(sf.ad.viewall);
			sf.seleU.wait(2000);
			sf.seleU.ScrolltoElement(sf.ad.viewall.get(0));
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.ad.viewall.get(0));
			sf.seleU.wait(5000);
			sf.seleU.switchToElementFrame(sf.ad.assignedTerritories);
			sf.seleU.wait(8000);
			if (Global.dataInput.territories.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.ad.assignedTerritories.get(0))))
				reportStatusPass(methodName + " Verification Successful "+" Expected Territory = " + Global.dataInput.territories + "is equals to "+" Actual Territory = " + sf.seleU.getTextFromWebElement(sf.ad.assignedTerritories.get(0)) +" as per rule", true, true);

			else
				reportStatusFail("Verification Failed- The Expected Territories is not equal to Actual one for this account i.e  "+" Expected Territory = " + Global.dataInput.territories + " and  "+ " Actual Territory = " + sf.seleU.getTextFromWebElement(sf.ad.assignedTerritories.get(0)) , true);

			sf.seleU.wait(7000);
		} catch (Throwable e) {
			reportStatusFail(" Verifying  Territories to Newly created account is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Click on Account Details and Verify Sales Segement
	 */
	public void verifySalesSegments() throws IOException {
		try {

			String methodName = "SFDC_Account Details@: ";

			// Click on Account Details and Sales Segement
			sf.seleU.clickElementByJSE(sf.ad.overviewTab);
			sf.seleU.hardwait(2000);
			//sf.seleU.getTextFromWebElement(sf.ad.salesSegementValueInput);
			//verifyFieldValue("Sales Segment", sf.ad.salesSegementValueInput, "Public Sector");
			reportStatusPass(methodName + " Verified Sales Segment as " +sf.seleU.getTextFromWebElement(sf.ad.salesSegementValueInput), true, false);
			if(sf.seleU.getTextFromWebElement(sf.ad.salesSegementValueInput).equalsIgnoreCase(InputData_Sales.salesSegment))
			{
				reportStatusPass(methodName + " Verification Pass- Verified Sales Segment as " +sf.seleU.getTextFromWebElement(sf.ad.salesSegementValueInput), true, false);
			}
			else
				reportStatusFail("Verification Fail- The Expected Sales segment is not equal  "+ sf.seleU.getTextFromWebElement(sf.ad.salesSegementValueInput), true);
				
			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail("Error in Verifing Sales Segement in Account Details", e);
			e.printStackTrace();

		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     After clicking on Opportunity button,
	 *                     whether user is able to redirect to B2b Org
	 *                     or Opportunity flow based on Sales segment or not.
	 */
	public void verifyOpportunityRedirection() throws IOException {
		try {

			String methodName = "SFDC_Account Details@: ";
			sf.seleU.hardwait(6000);
			if(InputData_Sales.salesSegment.equalsIgnoreCase("Public Sector")||InputData_Sales.salesSegment.equalsIgnoreCase("Federal")||InputData_Sales.salesSegment.equalsIgnoreCase("Wholesale"))
			{
				sf.seleU.hardwait(2000);
				sf.seleU.switchWindow(2);
				sf.seleU.hardwait(2000);
				String str2 = sf.seleU.getCurrentUrl();
				sf.seleU.hardwait(2000);
				//if(str2.equalsIgnoreCase(InputData_Sales.url))
				String str;
				str= str2.substring(8, 17);
				sf.seleU.hardwait(2000);
				System.out.println(str);
				if(str.equalsIgnoreCase(InputData_Sales.url))
				{
				sf.seleU.hardwait(2000);
				reportStatusPass(methodName + "Verification Pass- A Buss Account whose sales segment is Public Sector/Federal/Wholesale and after clicking Opportunity button  user redirected to B2B org.", true, false);
				}else
					reportStatusFail("Verification Fail-  A Buss Account whose sales segment is Public Sector/Federal/Wholesale and after clicking Opportunity button  user not redirected to B2B org "+" Expected URL = " + InputData_Sales.url + " and  "+ " Actual URL = " + str2 , true);	
				sf.seleU.hardwait(2000);
				sf.seleU.closeRecentlyOpenedWindow();
				sf.seleU.hardwait(2000);
				if (sf.dataInput.env.equals("ITDEVSTAGE") )		
				sf.seleU.openURL("https://r4b--itdevstage.my.salesforce.com/");
				else if (sf.dataInput.env.equals("ITQATEST") )	
				sf.seleU.openURL("https://r4b--itqatest.my.salesforce.com/");
				else if (sf.dataInput.env.equals("PREFIT") )	
				sf.seleU.openURL("https://r4b--r4bprefit.my.salesforce.com/");
				else 
				sf.seleU.openURL("https://r4b.my.salesforce.com ");
	
			}
			else
			{
				sf.seleU.hardwait(6000);
				String str3 = sf.seleU.getCurrentUrl();
				sf.seleU.hardwait(2000);
				String str= str3.substring(8, 11);
				//if(str3.equalsIgnoreCase(InputData_Sales.oppUrl))
				if(str.contains(InputData_Sales.oppUrl))
				reportStatusPass(methodName + "Verification Pass- A Buss Account whose sales segment is SMB/Small/Corporate/Commercial and after clicking Opportunity button  user redirected to Oppoenunity Creation flow.", true, false);
				else
					reportStatusFail("Verification Fail- A Buss Account whose sales segment is SMB/Small/Corporate/Commercial and after clicking Opportunity button  user not redirected to Oppoenunity Creation flow. "+" Expected URL = " + InputData_Sales.oppUrl + " and  "+ " Actual URL = " + str3 , true);	
				sf.seleU.hardwait(2000);
				sf.seleU.closeRecentlyOpenedWindow();
				sf.seleU.hardwait(2000);
				if (sf.dataInput.env.equals("ITDEVSTAGE") )		
				sf.seleU.openURL("https://r4b--itdevstage.my.salesforce.com/");
				else if (sf.dataInput.env.equals("ITQATEST") )	
				sf.seleU.openURL("https://r4b--itqatest.my.salesforce.com/");
				else if (sf.dataInput.env.equals("PREFIT") )	
				sf.seleU.openURL("https://r4b--r4bprefit.my.salesforce.com/");
				else 
				sf.seleU.openURL("https://r4b.my.salesforce.com ");
			}
			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail("Error in Verifing Oppotunity redirection and creation based on sales segments in Account Details", e);
			e.printStackTrace();

		}
	}

	public void softAssert(boolean condition, String passMsg, String failMsg) throws IOException, InterruptedException {
		if(condition) {
			System.out.println(passMsg);
			reportStatusPass(passMsg, true, false);
		} else {
			System.out.println(failMsg);
			reportStatusFail(failMsg, true);
			logger.info("Soft assert failed due to exception: - "+failMsg);
		}
	}


	public void hardAssert(boolean condition, String passMsg, String failMsg) throws IOException, InterruptedException {
		if(condition) {
			System.out.println(passMsg);
			reportStatusPass(passMsg, true, false);
		} else {
			System.out.println(failMsg);
			reportStatusFail(failMsg, true);
			throw new WebDriverException("Hard assert failed due to exception: -"+failMsg);
		}
	}
	
	public void validateCreditValuesAfterEquifaxCheck() throws IOException, InterruptedException {
		try {
			String methodName = "SFDC_Credit Check Required@: ";
			if (InputData_Sales.credit_Assigned_FNF== Global.dataInput.credit_Assigned &&
			InputData_Sales.credit_Available_FNF==Global.dataInput.credit_Available)
			{
				reportStatusPass(methodName + " Verification Pass-  Validated,  $1000 has been credited"
						+ " to Credit Limit Available and Total Credit Limit Assigned repectively at account details ", true, false);
			}
			else
			{
				reportStatusFail("Verification Fail-   $1000 has not been credited to Credit Limit Available and"
						+ "Total Credit Limit Assigned respectively at account details ", true);	
			}
			

			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail("Error in extracting credit check required", e);
			e.printStackTrace();
		}
	}
	
	public void validateFnFValueAfterEquifax() throws IOException, InterruptedException {
		try {
			String methodName = "SFDC_validateFnFValueAfterEquifaxCheck@: ";
			sf.seleU.scrollByCoOrdinates(1);
			if (sf.seleU.getTextFromWebElement(sf.ad.highRiskFnF).equalsIgnoreCase(("High Risk-FnF")))
			{
				reportStatusPass(methodName + " Verification Pass-  Validated,  High Risk-FnF has been added"
						+ " to Credit Risk Value after equifax check ", true, false);
			}
			else
			{
				reportStatusFail("Verification Fail-  Validated,  High Risk-FnF has not been added"
						+ "to Credit Risk Value after equifax check ", true);	
			}
			

			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail("Error in extracting Credit Risk Value", e);
			e.printStackTrace();
		}
	}
	

	
	/**
	 * @throws IOException
	 * 
	 *                     Click on Service Account Creation button
	 */
	public void newServiceAccountButton() throws IOException {

		String methodName = "SFDC_Account Details@: ";

		try {

			sf.seleU.wait(3000);
			if (!sf.seleU.isElementDisplayed(sf.cd.newServiceAccButton))
				sf.seleU.clickElementByJSE(sf.cd.showMoreActionsButton);
			sf.seleU.clickElementByJSE(sf.cd.newServiceAccButton);

			sf.seleU.wait(3000);
			reportStatusPass(methodName + " Clicked on New Service Account button from Account ", true, false);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not click on New Service Account button", e);
			e.printStackTrace();
		}
	}
	
	

	/**@author Satish.Doraiswamy
	 * @throws IOException 
	 * @Date Mar 9,2022
	 * Credit Check & Fraud Check Credit check Option movement
	 */
	public void creditNFraud_CreditRiskValue_Option(String availableCreditRiskOptionValue) throws IOException {
		// TODO Auto-generated method stub
		boolean status =false;
		String headerText=null;
		String choseOptionValueListPath="//span[contains(text(),'Chosen')]//parent::div//li";
		
		String methodName = "SFDC_Credit And Fraud Check Credit Risk Value Option @: ";
		try {
			sf.seleU.ScrolltoElement(sf.ad.creditNFraudCheckHeaderElement);
			sf.seleU.hardwait(2000);
		status=sf.seleU.isElementDisplayed(sf.ad.creditNFraudCheckHeaderElement);
		if(status) {
			headerText=sf.seleU.getTextFromWebElement(sf.ad.creditNFraudCheckHeaderElement);
			reportStatusPass(methodName+"Credit and Fraud Check Header text is "+headerText+"", true, false);
			sf.seleU.hardwait(2000);
			
			sf.seleU.clickElementByJSE(sf.ad.creditNFraudCheckEditButton);
			sf.seleU.hardwait(2000);
			List<WebElement> optionElement=sf.seleU.findElements(By.xpath(choseOptionValueListPath));
			for(WebElement webElement:optionElement) {
				sf.seleU.clickElementByJSE(webElement);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.ad.creditNFraudCheckCreditRiskMoveSelectionToAvailableButton);
			}
			String availableCreditRiskOption="//span[contains(text(),'Available')]//parent::div//span[contains(text(),'"+availableCreditRiskOptionValue+"')]";
			sf.seleU.clickElementByJSE(sf.seleU.findElement(By.xpath(availableCreditRiskOption)));
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.ad.creditNFraudCheckCreditRiskMoveSelectionToChosenButton);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.ad.creditNFraudCheckCreditRiskSaveButton);
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName+"Credit and Fraud Check Credit Risk Option added successfully !!!", true, false);
		} else {
			headerText=sf.seleU.getTextFromWebElement(sf.ad.creditNFraudCheckHeaderElement);
			reportStatusFail(methodName+"Credit and Fraud Check Header text is "+headerText+" and No Credit and Fraud Check Header!!!! ", true);
		}
		}catch (Throwable e) {
			reportStatusFail("Error in extracting Credit Risk Value", e);
			e.printStackTrace();
		}

	}
	/*
	 *	Store R4B Rogers Business ID
	 */
	public void store_R4B_External_Field() throws IOException, InterruptedException {
		try {
			String methodName = "SFDC_store_R4B_External_Field: ";
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.hardwait(6000); 
			InputData_Sales.rogersBusID = sf.seleU.getTextFromWebElement(sf.ad.r4BField);
			System.out.println("Outpot "+InputData_Sales.rogersBusID);
			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail("Error in extracting T4B Field Value", e);
			e.printStackTrace();
		}
	}

	/**
	 * @author Satish.Doraiswamy
	 *May 2, 2022
	 * Verify Total Credit limit Assigned Value
	 * @throws IOException 
	 */
	public  String getTotalCreditLimitAssignedValue() throws IOException {
		String methodName = "SFDC_Account Details@: ";
		String totalCreditLimitAssignedValue=null;

		try {
			if (!sf.seleU.isElementDisplayed(sf.acc.totalCreditLimitAssignedText))
				totalCreditLimitAssignedValue=sf.seleU.getTextFromWebElementWithYellowHighlight(sf.acc.totalCreditLimitAssignedText);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not click on New Service Account button", e);
			e.printStackTrace();
		}
		return totalCreditLimitAssignedValue;
	}
	

}
