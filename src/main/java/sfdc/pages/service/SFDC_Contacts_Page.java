package sfdc.pages.service;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date: 25/Jan/2020
 *
 *         SFDC Contacts page objects
 */
public class SFDC_Contacts_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_Contacts_Page() {
		methodName = "SFDC_Contacts@: ";
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Select Navigation Menu
	 * 
	 *                     2.Select Contact
	 * 
	 *                     3.Click on list view dropdown and select all contacts
	 * 
	 *                     4.Verify contacts are displayed
	 */
	public void verifyContactsObject() throws IOException {
		try {

			// Select Navigation Menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Contact
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.contactsMenu);
			reportStatusPass(methodName + " Selected Contacts from menu", true, false);
			sf.seleU.hardwait(2000);

//			// Click on list view dropdown and select all contacts
//			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
//			sf.seleU.hardwait(2000);
//			sf.seleU.clickElementByJSE(sf.contacts.allContactsOption);
//			reportStatusPass(methodName + " Selected All Contacts Option", true, false);
//			sf.seleU.hardwait(2000);
//			
//			// Verify contacts are displayed
//			if (sf.contacts.contactsAllRecords.size() > 0) {
//				reportStatusPass(methodName + " Contacts Objects verified", true, true);
//			} else {
//				reportStatusFail(methodName + " Invalid Contacts Object", true);
//			}
//
//			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Contacts Object", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Search Contact
	 */
	public void searchContact() throws IOException {

		try {

			// 3- Enter Contact to be Searched
			sf.seleU.enterText(sf.home.searchInput, dataInput.contactFirstName + " " + dataInput.contactLastName);
			sf.seleU.hardwait(4000);
			sf.seleU.enterText(sf.home.searchInput, Keys.ENTER);
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.contacts.contactsAllRecords.get(0));
			reportStatusPass(methodName + " Selected  Contact " + dataInput.contactFirstName, true, false);
			sf.seleU.wait(9000);
		} catch (Throwable e) {
			reportStatusFail(" Error while Searching Contact", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Click on new Contact Button
	 */
	public void selectAndClickNewContact() throws IOException {
		try 
		{
			String methodName = "SFDC_Contacts@: ";

			// Select Navigation Menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Contact
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.contactsMenu);
			reportStatusPass(methodName + " Selected Contacts from menu", true, false);
			sf.seleU.hardwait(5000);

			// Click on new Contact Button
			sf.seleU.clickElementByJSE(sf.contacts.newContactButton);
			reportStatusPass(methodName + " Clicked on new contact button", true, false);
			sf.seleU.hardwait(10000);
		} 
		
		catch (Throwable e) {
			reportStatusFail(" Error in Clicking on New Contact", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Search Contact By full name
	 */
	public void searchContactWithFullName() throws IOException {

		try {

			// 3- Enter Contact to be Searched
			sf.seleU.enterText(sf.home.searchInput, sf.dataInput.contactFullName);
			sf.seleU.hardwait(4000);
			sf.seleU.enterText(sf.home.searchInput, Keys.ENTER);
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.contacts.contactsAllRecords.get(0));
			reportStatusPass(methodName + " Selected  Contact " + sf.dataInput.contactFullName, true, false);
			sf.seleU.wait(9000);
		} catch (Throwable e) {
			reportStatusFail(" Error while Searching Contact", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Search Contact By full name
	 */
	public void searchContactFromGlobalSearch(String contactName) throws IOException {

		try {

			// 1-Selecting Contacts from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.home.contactsMenu);
			reportStatusPass(methodName + " Selected Contacts from menu", true, false);
			sf.seleU.hardwait(4000);

			// 2- Enter Contact to be Searched in global search
			sf.seleU.clickElementByJSE(sf.acc.search);
            sf.seleU.hardwait(4000);
            if (sf.seleU.isElementDisplayed(sf.acc.searchAccountGlobalAfterClick)) 
            {
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, contactName);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, Keys.ENTER);
			}
            
            else//after click if search more......is displayed perform else part
            {
            sf.seleU.hardwait(2000);
            sf.seleU.enterText(sf.contacts.globalContactSearch, contactName);
            sf.seleU.hardwait(2000);
            sf.seleU.enterText(sf.contacts.globalContactSearch, Keys.ENTER);
            }
            
            sf.seleU.hardwait(5000);
			sf.seleU.scrollByCoOrdinates(2);			
			
			boolean isContactFound = false;
			
			//click on contact menu			
			if (sf.contacts.contactLinkGlobalSearchResult.size() == 1) 
			{
			  sf.seleU.clickElementByJSE(sf.contacts.contactLinkGlobalSearchResult.get(0));
			  reportStatusPass(methodName + " Found and Clicked on  Contact " + contactName, true, false);
			  isContactFound = true;
			}
			else
			{
			for (int i = 0; i < sf.contacts.contactLinkGlobalSearchResult.size(); i++) 
			{
			if ( sf.seleU.getElementAttribute(sf.contacts.contactLinkGlobalSearchResult.get(i), "title").equalsIgnoreCase(contactName)
				||	sf.seleU.getTextFromWebElement(sf.contacts.contactLinkGlobalSearchResult.get(i))
				.equalsIgnoreCase(contactName)) 
			{
			  sf.seleU.clickElementByJSE(sf.contacts.contactLinkGlobalSearchResult.get(i));
			  reportStatusPass(methodName + " Found and Clicked on Contact " + contactName, true, true);
			  isContactFound = true;
			  break;
			}
				}	
			}			
			if (!isContactFound) 
			{
			  reportStatusFail(methodName + " No such contact found for contactName  " + contactName, true);
			}
			
			if (sf.seleU.isElementPresent(sf.ad.closeButton)) 
			{
				sf.seleU.clickElementByJSE(sf.ad.closeButton);
			}
			sf.seleU.hardwait(9000);

		} catch (Throwable e) {
			reportStatusFail(" Error while Searching Contact from global serach", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Search Contact in all the options if found click on it.
	 */

	public void clickContactAndSearch(String contactName) throws IOException {
		try {

			// Select Navigation Menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Contact
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.contactsMenu);
			reportStatusPass(methodName + " Selected Contacts from menu", true, false);
			sf.seleU.hardwait(2000);

			// Click on list view dropdown and select All contacts
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.contacts.allContactsOption);
			reportStatusPass(methodName + " Selected Recently Contacts Option", true, false);
			sf.seleU.hardwait(4000);

			// 3- Enter Contact to be Searched
			sf.seleU.clearAndEnterText(sf.home.searchInput, contactName);
			sf.seleU.wait(4000);
			sf.seleU.enterText(sf.home.searchInput, Keys.ENTER);
			sf.seleU.wait(5000);
			if (sf.acc.accountsAllRecords.size() > 1 && sf.seleU
					.getTextFromWebElement(sf.acc.accountsAllRecords.get(sf.acc.accountsAllRecords.size() - 1))
					.equals(contactName)) {
				sf.seleU.clickElementByJSE(sf.acc.accountsAllRecords.get(sf.acc.accountsAllRecords.size() - 1));
			}

			else if (sf.acc.accountsAllRecords.size() == 0) {

				// 2- Select My Contacts
				sf.seleU.clickElementByJSE(sf.home.listViewIcon);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.contacts.myContactsOption);
				reportStatusPass(methodName + " Selected My Contacts Option", true, false);

				// 3- Enter Account to be Searched
				sf.seleU.clearAndEnterText(sf.home.searchInput, contactName);
				sf.seleU.hardwait(4000);
				sf.seleU.enterText(sf.home.searchInput, Keys.ENTER);
				sf.seleU.hardwait(5000);

				if (sf.acc.accountsAllRecords.size() == 0) {
					// 2- Select Recently viewed Contact Option
					sf.seleU.clickElementByJSE(sf.home.listViewIcon);
					sf.seleU.hardwait(2000);
					sf.seleU.clickElementByJSE(sf.contacts.recentlyViewedContactsOption);
					reportStatusPass(methodName + " Selected Recently Viewed Contact Option", true, false);

					// 3- Enter Account to be Searched
					sf.seleU.clearAndEnterText(sf.home.searchInput, contactName);
					sf.seleU.hardwait(4000);
					sf.seleU.enterText(sf.home.searchInput, Keys.ENTER);
					sf.seleU.hardwait(5000);
				}

				sf.seleU.clickElementByJSE(sf.acc.accountsAllRecords.get(0));
			}

			else {

				sf.seleU.clickElementByJSE(sf.acc.accountsAllRecords.get(0));
			}

			reportStatusPass(methodName + " Found and Clicked on  Contact " + contactName, true, false);
			sf.seleU.wait(9000);
		} catch (Throwable e) {
			reportStatusFail(" Searching an contact is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Open First contact from list under 'My Contacts'
	 */
	public void openFirstContactFromMyContacts() throws IOException {

		try {

			// Select Navigation Menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Contact
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.contactsMenu);
			reportStatusPass(methodName + " Selected Contacts from menu", true, false);
			sf.seleU.hardwait(2000);

			// Click on list view dropdown and select my contacts
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.contacts.myContactsOption);
			reportStatusPass(methodName + " Selected My Contacts Option", true, false);
			sf.seleU.hardwait(2000);
			sf.seleU.hardwait(2000);

			// Verify atleast one contact is displayed and click on it
			if (sf.contacts.contactsAllRecords.size() > 0) {
				reportStatusPass(methodName + " My contact is present", true, false);
				reportStatusPass(methodName + " Selecting contact : "
						+ sf.seleU.getTextFromWebElement(sf.contacts.firstContactInList), false, true);
				sf.seleU.clickElementByJSE(sf.contacts.firstContactInList);
				reportStatusPass(methodName + " Clicked on first contact from the list", true, true);

			} else {
				reportStatusFail(methodName + " No records found in My Contacts", true);
			}

			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(" Error while Searching Contact", e);
			e.printStackTrace();
		}
	}

	/**
	 * Select Contact from global search
	 * 
	 * @throws IOException
	 */
	public void searchContactGlobalSearchByEmail(String email) throws IOException {
		try {
			// 1-Selecting Contacts from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.contactsMenu);
			reportStatusPass(methodName + " Selected Contacts from menu", true, false);
			sf.seleU.hardwait(2000);
			// 2- Enter Contact to be Searched in global search
			sf.seleU.enterText(sf.contacts.globalContactSearch, email);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.contacts.globalContactSearch, Keys.ENTER);
			sf.seleU.hardwait(5000);
			boolean isContactFound = false;
			for (int i = 0; i < sf.contacts.contactLinkGlobalSearchResult.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.contacts.contactEmailGlobalSearchResult.get(i)).equals(email)) {
					sf.seleU.clickElementByJSE(sf.contacts.contactLinkGlobalSearchResult.get(i));
					reportStatusPass(methodName + " Found and Clicked on Contact for email " + email, true, true);
					isContactFound = true;
					break;
				}
			}
			if (!isContactFound) {
				reportStatusFail(methodName + " No such contact found for email:  " + email, true);
			}
			sf.seleU.hardwait(9000);
		} catch (Throwable e) {
			reportStatusFail(" Searching and selecting a contact is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @throws IOException
	 * 
	 *                     Verify Campaign Fields present under contact related list 
	 */
	public void verifyCampaignFields() throws IOException {

		try {
			sf.seleU.hardwait(2000);
			if (sf.seleU.isElementDisplayed(sf.ar.relatedTab))
				sf.seleU.clickElementByJSE(sf.ar.relatedTab);
			
			
		} catch (Throwable e) {
			reportStatusFail(" Error while Verifying Campaign Fields under Contact", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Verufy the Site Contact Role
	 * @throws IOException 
	 */
	public void validateSiteContactRole(String expectedBusinessAccountName, String expectedContactRole) throws IOException {		
		try {
			// Click Related tab
			sf.seleU.clickElementByJSE(sf.cd.relatedTab.get(0));
			reportStatusPass(methodName + " Clicked on Related tab for Contact Role", true, true);
			
			// Click on View All in Related Accounts
			sf.seleU.clickElementByJSE(sf.cd.viewAllRelatedAccount);
			reportStatusPass(methodName + " Clicked on View All for Related Accounts", true, true);
			
			// Checking Account type for first row
			String accountType = sf.seleU.getElementAttribute(sf.cd.accountType, "title");
			if(!accountType.equalsIgnoreCase(InputData.acc_RecordType_Business)) {
				// Sorting the Related Accounts
				sf.seleU.clickElementByJSE(sf.cd.accountTypeHeader);
			}
			
			boolean isFound = false;
			// Loop over Accounts listed
			for(int i = 0; i < sf.cd.listBusinessAccountName.size(); i++) {
				String actualBusinessAccountName = sf.seleU.getElementAttribute(sf.cd.listBusinessAccountName.get(i), "title");
				String actualRole = sf.seleU.getTextFromWebElement(sf.cd.listContactRole.get(i));						
				if((actualBusinessAccountName.equalsIgnoreCase(expectedBusinessAccountName)) && (actualRole.equalsIgnoreCase(expectedContactRole))) {
						reportStatusPass(methodName + actualBusinessAccountName + ":"+ " assigned to Role " + actualRole,
								true, false);
						isFound = true;
						break;
				}
			}
			
			if(!isFound) {
				reportStatusFail(" General Role Not Found for Business Account", true);
			}
			
		} catch (Throwable e) {
			reportStatusFail(" Error in while Verifying Site Contact Role", e);
			e.printStackTrace();
		}
	}

}
