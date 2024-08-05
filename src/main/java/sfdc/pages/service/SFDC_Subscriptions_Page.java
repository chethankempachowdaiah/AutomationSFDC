package sfdc.pages.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla, Date : 15/Sep/2020
 * 
 *         SFDC Subscriptions page
 */
public class SFDC_Subscriptions_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	SFDC_AllPageObjects sf;
	static String methodName = "SFDC_Subscription@: ";

	public SFDC_Subscriptions_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     Searches Subscriptions from "Search App and item" through
	 *                     App Launcher
	 * 
	 *                     List All the subscriptions
	 * 
	 */
	public void listAllSubscriptions() throws IOException {
		try {

			// Click on app launcher and search subscriptions
			sf.seleU.clickOnElement(sf.home.applauncher);
			sf.seleU.clickOnElement(sf.subs.searchAppsAndItemsTextBox);
			sf.seleU.clearAndEnterText(sf.subs.searchAppsAndItemsTextBox, InputData.itemName);
			reportStatusPass(methodName + " Searched Subscriptions from Apps and Items", false, false);
			// select subscriptions and list All
			sf.seleU.clickElementByJSE(sf.subs.subsciptionsItem);
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.subs.listViewNavigationButton);
			sf.seleU.clickElementByJSE(sf.subs.allOption);
			reportStatusPass(methodName + " Listed All subscriptions", true, false);
			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in listing subscriptions", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify subscription List Titles present or not
	 * 
	 *                     Titles verified : Name, Product Type, Record Type,
	 *                     Status, Billing Account, Phone, Subscription Start Date
	 *                     and End Date
	 * 
	 */
	public void verifySusbcriptionListTitles() throws IOException {
		try {

			// Set titles to be verified in a list
			if (InputData.subscription_titles.isEmpty())
				Global.dataInput.setSubscriptionTitles();

			List<String> expectedSubscriptionTitles = InputData.subscription_titles;
			List<String> actualSubscriptionTitles = new ArrayList<String>();

			// Create list of WebElements to be verified
			List<WebElement> titlesSubscription = new ArrayList<>(Arrays.asList(sf.subs.listTitleName.get(0),
					sf.subs.listTitleProductType.get(0), sf.subs.listTitleRecordType.get(0),
					sf.subs.listTitleStatus.get(0), sf.subs.listTitleBillingAccount.get(0),
					sf.subs.listTitlePhone.get(0), sf.subs.listTitleSubScriptionStartDate.get(0),
					sf.subs.listTitleSubScriptionEndDate.get(0)));

			// Loop over number of titles to be verified
			for (int i = 0; i < titlesSubscription.size(); i++) {
				actualSubscriptionTitles.add(sf.seleU.getTextFromWebElement(titlesSubscription.get(i)));
			}
			reportStatusPass(methodName + " Extracted titles from Subscription table to verify with expected list",
					false, false);

			// sort lists for comparison
			Collections.sort(expectedSubscriptionTitles);
			Collections.sort(actualSubscriptionTitles);

			// Verify expectedTitles list is equal to actualTitles List

			if (expectedSubscriptionTitles.equals(actualSubscriptionTitles)) {
				reportStatusPass(methodName + " All expected titles are present in subscription table : "
						+ AdditionalUtilities.getAsString(actualSubscriptionTitles), true, true);
			} else {
				reportStatusFail(methodName + " All expected titles are not present :: Expected Titles--> "
						+ AdditionalUtilities.getAsString(expectedSubscriptionTitles) + "  Actual Titles--> "
						+ AdditionalUtilities.getAsString(actualSubscriptionTitles), true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in verifying subscription list titles", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate data presence in subscription list
	 * 
	 *                     Verify number of rows greater than 0
	 * 
	 */
	public void verifySusbcriptionListDataSize() throws IOException {

		try {

			// Verify Subscription list table has some data in it
			if (sf.subs.listTrSubscriptions.size() > 0) {
				reportStatusPass(methodName + " Validated subscriptions list, " + sf.subs.listTrSubscriptions.size()
						+ " rows are present", true, true);
			} else {
				reportStatusFail(methodName + " No data in subscriptions list", true);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in verifying data presence in subscription list", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Extracts first subscription name and searches from global
	 *                     search
	 * 
	 *                     Verify number of rows greater than 0
	 * 
	 */
	public void searchSubscriptionGlobally() throws IOException {

		try {
			// Check whether any subscription is available to search
			if (sf.subs.listTrSubscriptions.size() > 0) {

				// Get first subscription name
				String subscriptionName = sf.seleU.getTextFromWebElement(sf.subs.nameOfFirstSubscription);
				reportStatusPass(methodName + " Extracted first subscription name to search: " + subscriptionName,
						false, false);

				if (!sf.seleU.isElementDisplayed(sf.subs.globalSearchSubScription)) {
					sf.seleU.clickOnElement(sf.home.searchBoxButton);
					sf.seleU.hardwait(5000);
				}
				
				// Search extracted subscription name in Global search
				sf.seleU.clearAndEnterText(sf.subs.globalSearchSubScription, subscriptionName);
				sf.seleU.enterText(sf.subs.globalSearchSubScription, Keys.ENTER);
				reportStatusPass(methodName + " Searched from global search: " + subscriptionName, true, false);

			} else {
				reportStatusFail(methodName + " No data in subscriptions list to search globally", true);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in verifying data presence in subscription list", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify subscription List Titles present or not
	 * 
	 *                     Titles verified : Name, Product Type, Record Type,
	 *                     Status, Billing Account, Phone
	 * 
	 */
	public void verifySearchedSusbcriptionListTitles() throws IOException {

		try {

			// Set titles to be verified in a list
			if (InputData.subscription_titles.isEmpty())
				Global.dataInput.setSubscriptionTitles();

			List<String> expectedSubscriptionTitles = InputData.subscription_titles;
			List<String> actualSubscriptionTitles = new ArrayList<String>();

			// Remove subscription start date and end date title from the verification list
			expectedSubscriptionTitles.remove("Subscription Start Date");
			expectedSubscriptionTitles.remove("Subscription End Date");
			expectedSubscriptionTitles.remove("Name");

			// Create list of WebElements to be verified
			List<WebElement> titlesSubscription = new ArrayList<>(
					Arrays.asList(sf.subs.searchListTitleProductType,
							sf.subs.searchListTitleRecordType, sf.subs.searchListTitleStatus,
							sf.subs.searchListTitleBillingAccount, sf.subs.searchListTitlePhone));

			// Loop over number of titles to be verified
			for (int i = 0; i < titlesSubscription.size(); i++) {
				actualSubscriptionTitles.add(sf.seleU.getTextFromWebElement(titlesSubscription.get(i)));
			}
			reportStatusPass(methodName + " Extracted titles from Subscription table to verify with expected list",
					false, false);

			// sort lists for comparison
			Collections.sort(expectedSubscriptionTitles);
			Collections.sort(actualSubscriptionTitles);

			// Verify expectedTitles list is equal to actualTitles List

			if (expectedSubscriptionTitles.equals(actualSubscriptionTitles)) {
				reportStatusPass(methodName + " All expected titles are present in subscription table : "
						+ AdditionalUtilities.getAsString(actualSubscriptionTitles), true, true);
			} else {
				reportStatusFail(methodName + " All expected titles are not present :: Expected Titles--> "
						+ AdditionalUtilities.getAsString(expectedSubscriptionTitles) + "  Actual Titles--> "
						+ AdditionalUtilities.getAsString(actualSubscriptionTitles), true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in verifying subscription list titles", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Extracts first subscription's billing account
	 * 
	 *                     Opens the billing Account and verifies Account name
	 * 
	 */
	public void openBillingAccountFromSubscriptionList() throws IOException {
		try {
			sf.seleU.wait(3000);
			if (sf.subs.listTrSubscriptions.size() > 0) {
				 String billAccName = null;

				/*// Sort to get Active accounts at the top if not present by default
				if (!sf.seleU.isElementPresent(sf.subs.activeStatusSpan))
					sf.seleU.clickElementByJSE(sf.subs.listTitleStatus.get(0));
*/
				//Search for active status type in search from list
				sf.seleU.enterText(sf.cases.searchCaseInputBox, Global.dataInput.status_Active);
				sf.seleU.enterText(sf.cases.searchCaseInputBox, Keys.ENTER);				
				sf.seleU.wait(10000);
				if (sf.seleU.isElementDisplayed(sf.subs.activeStatusSpan)) 
				{
					billAccName = sf.seleU.getElementAttribute(sf.subs.billingAccountLink, "title");
					reportStatusPass(methodName + " Extracted billing account " + billAccName, false, false);					
					InputData.billingAccountWithSubscription = billAccName;
										
					// Click on the extracted billing account
					sf.seleU.clickElementByJSE(sf.subs.billingAccountLink);
					sf.seleU.wait(3000);

					// Verify Billing account opened correctly
					if (sf.seleU.getTextFromWebElement(sf.subs.billingAccountName).equalsIgnoreCase(billAccName)) {
						reportStatusPass(methodName + " Validated: Billing account " + billAccName + " opened", true,
								true);
					} else {
						reportStatusFail(methodName + " Billing account" + billAccName + " is not displayed", true);
					}
				} else
					reportStatusFail(
							methodName + " Could not find an active subscription billing account from the list", true);
			} else {
				reportStatusFail(methodName + " No data in subscriptions list", true);
			}
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in opening billing account from subscription list", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Opens Related Tab of billing Account
	 * 
	 *                     Clicks on ViewAll billing subscriptions
	 * 
	 *                     Verify Billed subscription List Titles present or not
	 * 
	 *                     Titles verified : Name, Product Type, Record Type,
	 *                     Status, Phone, Subscription Start Date and End Date
	 * 
	 * 
	 */
	public void openBilledSubscriptionsFromRelatedListAndVerifyTitles() throws IOException {

		try {
			boolean billedSubscriptionfound = false;
			// Click on Related tab from Account details
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.subs.relatedTabItem);
			sf.seleU.hardwait(4000);
			sf.seleU.ScrolltoElement(sf.subs.relatedContacts);
			sf.seleU.hardwait(4000);

			// Click on View All Blling subscriptions for the account
			try {
				sf.seleU.clickElementByJSE(sf.subs.viewAllBilledSubscriptions.get(2));
				reportStatusPass(methodName + " Listed all billed subscriptions under the selected billing account", true, false);
				billedSubscriptionfound = true;

			} catch (org.openqa.selenium.NoSuchElementException e) {
				reportStatusPass(methodName + " Could not find any billed subscriptions under the selected billing account",
						true, false);

			}
			if (billedSubscriptionfound) {
				// Set titles to be verified in a list
				InputData.subscription_titles = new ArrayList<String>();
				Global.dataInput.setSubscriptionTitles();

				List<String> expectedSubscriptionTitles = InputData.subscription_titles;

				// Remove Billing Account title from the verification list
				expectedSubscriptionTitles.remove("Billing Account");
				List<String> actualSubscriptionTitles = new ArrayList<String>();				
				List<WebElement> titlesSubscription = new ArrayList<>(
						Arrays.asList(sf.subs.billedSubscriptionsName, sf.subs.billedSubscriptionsProductType, sf.subs.billedSubscriptionsRecordType,
								sf.subs.billedSubscriptionsStatus, sf.subs.billedSubscriptionsPhone, sf.subs.billedSubscriptionsStartDate,
								sf.subs.billedSubscriptionsEndDate));

				// Loop over number of titles to be verified
				for (int i = 0; i < titlesSubscription.size(); i++) {
					actualSubscriptionTitles.add(sf.seleU.getTextFromWebElement(titlesSubscription.get(i)));
				}
				reportStatusPass(methodName + " Extracted titles from Subscription table to verify with expected list",
						false, false);

				// sort lists for comparison
				Collections.sort(expectedSubscriptionTitles);
				Collections.sort(actualSubscriptionTitles);

				// Verify expectedTitles list is equal to actualTitles List
				if (expectedSubscriptionTitles.equals(actualSubscriptionTitles)) {
					reportStatusPass(methodName + " All expected titles are present in subscription table : "
							+ AdditionalUtilities.getAsString(actualSubscriptionTitles), true, true);
				} else {
					reportStatusFail(
							methodName + " All expected titles are not present :: Expected Titles--> "
									+ AdditionalUtilities.getAsString(expectedSubscriptionTitles)
									+ "  Actual Titles--> " + AdditionalUtilities.getAsString(actualSubscriptionTitles),
							true);
				}
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in opening billing subscriptions from Related tab", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Searches wireless and wireline in subscriptions list
	 * 
	 *                     Opens the subscription based on argument passed for
	 *                     Record Type
	 * 
	 */
	public void openSubscriptionByRecordType(String recordType) throws IOException {
		try {
			boolean foundRecord = false;
			sf.seleU.wait(3000);

			// Search record by record type
			foundRecord = searchRecord(recordType);

			// Sorting table to get other record type rows display on screen
			for (int i = 0; i < 2; i++) {
				if (!foundRecord) {
					//sf.seleU.clickElementByJSE(sf.subs.listTitleRecordType.get(0));
					sf.seleU.enterText(sf.cases.searchCaseInputBox, recordType);
					sf.seleU.enterText(sf.cases.searchCaseInputBox, Keys.ENTER);
					foundRecord = searchRecord(recordType);
				} else
					break;
			}
			if (!foundRecord)
				reportStatusPass(methodName + " No subscription found with Record Type " + recordType, false, false);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in opening subscription based on Record type", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Searches wireless and wireline in subscriptions list
	 * 
	 *                     Opens the subscription based on argument passed for
	 *                     Record Type
	 * 
	 */
	private boolean searchRecord(String recordType) throws IOException {
		boolean foundRecord = false;
		try {
			sf.seleU.wait(26000);
			if (sf.subs.listTrSubscriptions.size() > 0) {

				// Loop over Data in subscription table
				for (int i = 0; i < (sf.subs.recordTypeListSubscription.size() - 1); i++) {
					try {
						if (sf.seleU.getTextFromWebElement(sf.subs.recordTypeListSubscription.get(i))
								.equalsIgnoreCase(recordType)) {

							reportStatusPass(
									methodName + " Found subscription with Record Type " + recordType + " : "
											+ sf.seleU.getTextFromWebElement(sf.subs.nameListSubscription.get(i)),
									false, false);
							// Click on selected row
							sf.seleU.clickElementByJSE(sf.subs.nameListSubscription.get(i));
							foundRecord = true;
							break;
						}
					} catch (NullPointerException ex) {
					}
				}
			} else {
				reportStatusFail(methodName + " No data in subscriptions list", true);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in opening subscription based on Record type", e);
			e.printStackTrace();
		}
		return foundRecord;
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verifies System Information labels and values under
	 *                     Wireless/Wireline subscriptions
	 * 
	 *                     Verifies Created By, Last Modified By and Subscription
	 *                     Number
	 * 
	 */
	public void verifySystemInformationDetails() throws IOException {
		try {

			// Set titles to be verified in a list
			if (InputData.sysInfo_titles.isEmpty())
				Global.dataInput.setSysInfoTitles();

			List<String> expectedSysInfoTitles = InputData.sysInfo_titles;
			List<String> actualSysInfoTitles = new ArrayList<String>();

			// Create list of label WebElements to be verified
//			List<WebElement> sysInfoTitleElements = new ArrayList<>(
//					Arrays.asList(sf.subs.sysInfoSubscriptionNumberLabel, sf.subs.sysInfoCreatedByLabel,
//							sf.subs.sysInfoLastModifiedByLabel));

			List<WebElement> sysInfoTitleElements = new ArrayList<>(
			Arrays.asList(sf.subs.sysInfoCreatedByLabel, sf.subs.sysInfoLastModifiedByLabel));
			
			// Loop over number of titles to be verified
			for (int i = 0; i < sysInfoTitleElements.size(); i++) {
				actualSysInfoTitles.add(sf.seleU.getTextFromWebElement(sysInfoTitleElements.get(i)));
				sf.seleU.wait(3000);
			}
			reportStatusPass(methodName + " Extracted titles from System Information section to verify with expected labels list",
					false, false);

			// sort lists for comparison
			Collections.sort(expectedSysInfoTitles);
			Collections.sort(actualSysInfoTitles);

			// Verify expectedTitles list is equal to actualTitles List
			if (expectedSysInfoTitles.equals(actualSysInfoTitles)) {
				reportStatusPass(methodName + " All expected titles are present in system Information section : "
						+ AdditionalUtilities.getAsString(actualSysInfoTitles), true, true);
			} else {
				reportStatusFail(methodName
						+ " All expected titles are not present in system Information section :: Expected Titles--> "
						+ AdditionalUtilities.getAsString(expectedSysInfoTitles) + "  Actual Titles--> "
						+ AdditionalUtilities.getAsString(actualSysInfoTitles), true);
			}

			// Create list of value WebElements to be verified
//			List<WebElement> sysInfoValueElements = new ArrayList<>(
//					Arrays.asList(sf.subs.sysInfoSubscriptionNumberValue, sf.subs.sysInfoCreatedByValue,
//							sf.subs.sysInfoLastModifiedByValue));
			
			List<WebElement> sysInfoValueElements = new ArrayList<>(
			Arrays.asList(sf.subs.sysInfoCreatedByValue, sf.subs.sysInfoLastModifiedByValue));
			
			// Loop over number of titles to be verified
			for (int i = 0; i < sysInfoValueElements.size(); i++) {

			// Verify values
			String value = sf.seleU.getTextFromWebElement(sysInfoValueElements.get(i));
			sf.seleU.wait(2000);
			if (!value.isEmpty())
				reportStatusPass(methodName + " Validated value for " + InputData.sysInfo_titles.get(i)
							+ " is present: " + value, true, true);
				else
					reportStatusFail(methodName + InputData.sysInfo_titles.get(i) + " value is not present", true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in verifing system information for subscription", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Searches subscription from global search
	 * 
	 *                     Select the subscription
	 * 
	 *                     Verify opened correctly
	 * 
	 */
	public void searchAndSelectSubscription() throws IOException {

		try {

			// Search extracted subscription name in Global search
			sf.seleU.clearAndEnterText(sf.subs.globalSearchSubScription, InputData.subscriptionName);
			sf.seleU.enterText(sf.subs.globalSearchSubScription, Keys.ENTER);
			reportStatusPass(methodName + " Searched from global search: " + InputData.subscriptionName, true,
					false);

			// Select subscription
			sf.seleU.clickOnElement(sf.subs.subscriptionLinkGlobalSearchResult);
			sf.seleU.wait(10000);

			// Verify Subscription opened correctly
			if (sf.seleU.getTextFromWebElement(sf.subs.subscriptionNameHeader)
					.equalsIgnoreCase(InputData.subscriptionName))
				reportStatusPass(
						methodName + " Correct subscription has been opened : " + InputData.subscriptionName, true,
						true);
			else
				reportStatusFail(methodName + " Incoorect subscription is opened, Expected one was: "
						+ InputData.subscriptionName, true);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in searching and selecting subscription", e);
			e.printStackTrace();
		}
	}
	
	public void searchSubscriptionGlobalSearch(String subscription) throws IOException
	{
		try {

			// 1-Selecting Subscriptions from menu
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.subs.subscriptionsMenu);
			reportStatusPass(methodName + " Selected Subscriptions from menu ", true, false);
			sf.seleU.wait(4000);

			// 2- Enter Subscription to be Searched in global search
			sf.seleU.clickElementByJSE(sf.acc.search);
			sf.seleU.wait(4000);

			if (sf.seleU.isElementDisplayed(sf.acc.searchAccountGlobalAfterClick)) {
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, subscription);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, Keys.ENTER);
			}

			else { // after click if search more .. is displayed then perform else part
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.subs.globalSubscriptionAndMoreSearch, subscription);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.subs.globalSubscriptionAndMoreSearch, Keys.ENTER);
			}
			sf.seleU.hardwait(5000);
			sf.seleU.scrollByCoOrdinates(2);

			boolean isSubscriptionFound = false;
			// click on Subscription menu

			if (sf.subs.subscriptionGlobalSearchResult.size() == 1) {
				sf.seleU.clickElementByJSE(sf.subs.subscriptionGlobalSearchResult.get(0));
				reportStatusPass(methodName + " Found and Clicked on  Subscription " + subscription, true, false);
				isSubscriptionFound = true;
			} else {
				for (int i = 0; i < sf.subs.subscriptionGlobalSearchResult.size(); i++) {

					if (sf.seleU.getElementAttribute(sf.subs.subscriptionGlobalSearchResult.get(i),"title")
							.contains(subscription)) 
					{
						sf.seleU.clickElementByJSE(sf.subs.subscriptionGlobalSearchResult.get(i));
						reportStatusPass(methodName + " Found and Clicked on  Subscription " + subscription, true, false);
						isSubscriptionFound = true;
						break;
					}
				}
			}
			if (!isSubscriptionFound) {
				reportStatusFail(methodName + " No such subscription found as:  " + subscription, true);
			}

			if (sf.seleU.isElementPresent(sf.ad.closeButton)) {
				sf.seleU.clickElementByJSE(sf.ad.closeButton);
			}

			sf.seleU.hardwait(9000);

		} catch (Throwable e) {
			reportStatusFail(" Selecting an subscription is Unsuccessful", e);
			e.printStackTrace();
		}
	}		
	}
	

