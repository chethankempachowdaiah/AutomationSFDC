package sfdc.pages.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 27/09/2019
 *
 *         Account Home page
 */
public class SFDC_Accounts_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_Accounts_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * Select Account from navigation menu
	 * 
	 * @throws IOException
	 */
	public void selectAccount() throws IOException {

		try {

			String methodName = "SFDC_Account@: ";

			// 1-Selecting Accounts from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(4000);

			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
			reportStatusPass(methodName + " Selected Accounts from menu", true, false);
			sf.seleU.hardwait(2000);

			// 2- Select All Accounts
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.acc.myAccountsOption);
			sf.seleU.hardwait(4000);
			reportStatusPass(methodName + " Selected All Accounts Option", true, false);

			// 3- Enter Account to be Searched
			sf.seleU.enterText(sf.home.searchInput, Global.dataInput.parentAccountName);
			InputData.currentAccount = Global.dataInput.parentAccountName;
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.home.searchInput, Keys.ENTER);
			sf.seleU.hardwait(5000);

			boolean isAccountFound = false;
			// click on account menu
			for (int i = 0; i < sf.acc.accountsAllRecords.size(); i++) {

				if (sf.seleU.getTextFromWebElement(sf.acc.accountsAllRecords.get(i))
						.equals(Global.dataInput.parentAccountName) && sf.acc.accountsAllRecords.size() > 1) {
					sf.seleU.clickElementByJSE(sf.acc.accountsAllRecords.get(sf.acc.accountsAllRecords.size() - 1));
					reportStatusPass(
							methodName + " Found and Clicked on  Account " + Global.dataInput.parentAccountName, true,
							false);
					isAccountFound = true;
					break;
				} else if (sf.seleU.getTextFromWebElement(sf.acc.accountsAllRecords.get(i))
						.equals(Global.dataInput.parentAccountName)) {
					sf.seleU.clickElementByJSE(sf.acc.accountsAllRecords.get(i));
					reportStatusPass(
							methodName + " Found and Clicked on  Account " + Global.dataInput.parentAccountName, true,
							false);
					isAccountFound = true;
					break;
				}
			}

			if (!isAccountFound) {
				reportStatusFail(methodName + " No such account found as:  " + Global.dataInput.parentAccountName,
						true);
			}

			sf.seleU.hardwait(9000);

		} catch (Throwable e) {
			reportStatusFail(" Selecting an account is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Search opportunity in Accounts Page
	 * 
	 * @throws IOException
	 */
	public void searchOpportunity() throws IOException {
		try {
			String methodName = "SFDC_Account@: ";

			// Search for opportunity
			sf.seleU.switchToElementFrame(sf.acc.opportunityOwnerAllRecords);
			sf.seleU.ScrolltoElement(sf.acc.opportunitySearchInput);
			sf.seleU.enterText(sf.acc.opportunitySearchInput, Global.dataInput.stage);
			sf.seleU.hardwait(2000);
			boolean isStagefound = true;
			for (int i = 0; i < sf.acc.opportunityOwnerAllRecords.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.acc.opportunityStageAllRecords.get(i))
						.equals(Global.dataInput.stage)) {
					isStagefound = true;
				} else {
					isStagefound = false;
					break;
				}
			}

			// validate opportunity search
			if (isStagefound) {
				reportStatusPass(methodName + " Opportunity search results are valid", true, true);
			} else {
				reportStatusFail(methodName + " Opportunity search results are Invalid", true);
			}
			sf.seleU.switchToDefaultContent();
		} catch (Throwable e) {
			reportStatusFail(" Searching an account is Unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * Search case in Accounts Page
	 * 
	 * @throws IOException
	 */
	public void searchCase() throws IOException {
		try {
			String methodName = "SFDC_Account@: ";

			// search for case
			sf.seleU.switchToElementFrame(sf.acc.caseStatusAllRecords);
			sf.seleU.ScrolltoElement(sf.acc.caseSearchInput);
			sf.seleU.enterText(sf.acc.caseSearchInput, Global.dataInput.statusNew);
			sf.seleU.hardwait(2000);
			boolean isStatusfound = true;
			for (int i = 0; i < sf.acc.caseStatusAllRecords.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.acc.caseStatusAllRecords.get(i))
						.equals(Global.dataInput.statusNew)) {
					isStatusfound = true;
				} else {
					isStatusfound = false;
					break;
				}
			}

			// validate case search
			if (isStatusfound) {
				reportStatusPass(methodName + " Case search results are valid", true, true);
			} else {
				reportStatusFail(methodName + " case search results are Invalid", true);
			}
			sf.seleU.switchToDefaultContent();
		} catch (Throwable e) {
			reportStatusFail(" Searching a case is Unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Accounts from menu
	 * 
	 *                     Select account
	 * 
	 *                     Click on new Button
	 */
	public void createNewBusinessAccount() throws IOException {
		try {
			String methodName = "SFDC_Account@: ";

			// Select Accounts from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(2000);

			// Select account
			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
			reportStatusPass(methodName + " Selected Accounts from menu", true, false);
			sf.seleU.hardwait(2000);

			String mainWinHandel = driver.getWindowHandle();

			// Click on new Button
			sf.seleU.clickElementByJSE(sf.acc.newAccountButton);
			reportStatusPass(methodName + " Clicked on new button in accounts page", true, false);
			sf.seleU.wait(9000);

			Set<String> windowList = driver.getWindowHandles();
			Iterator<String> it = windowList.iterator();

			while (it.hasNext()) {
				String childWinHandel = it.next();
				if (!mainWinHandel.equals(childWinHandel)) {
					driver.switchTo().window(childWinHandel);
				}
			}

			// click on business account radio button and click on next
			sf.seleU.switchToElementFrame(sf.acc.businessAccountRadio);
			sf.seleU.clickElementByJSE(sf.acc.businessAccountRadio.get(0));
			sf.seleU.clickElementByJSE(sf.acc.selectAccountType_nextBtn);
			reportStatusPass(methodName + " Selected account as Business and clicked on Next", true, false);
			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Creating Busniess Account is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	public void createNewBusinessAccountRevised() throws IOException 
	{
		try 
		{
			String methodName = "SFDC_Account@: ";
			
			// Select Accounts from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(2000);

			// Select account
			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
			reportStatusPass(methodName + " Selected Accounts from menu", true, false);
			sf.seleU.hardwait(2000);

			String mainWinHandel = driver.getWindowHandle();

			// Click on new Button
			sf.seleU.clickElementByJSE(sf.acc.newAccountButton);
			reportStatusPass(methodName + " Clicked on new button in accounts page", true, false);
			sf.seleU.wait(9000);

			Set<String> windowList = driver.getWindowHandles();
			Iterator<String> it = windowList.iterator();

			while (it.hasNext()) 
			{
				String childWinHandel = it.next();
				if (!mainWinHandel.equals(childWinHandel)) 
				{
					driver.switchTo().window(childWinHandel);
				}
			}
		}
		catch (Throwable e) 
		{
			reportStatusFail(" Creating Busniess Account is Unsuccessful", e);
			e.printStackTrace();
		}
	}
			

	/**
	 * @throws IOException
	 * 
	 *                     Select Accounts from menu
	 * 
	 *                     Select account
	 * 
	 *                     Click on new Button LWC flow
	 */
	public void createNewBusinessAccountLWC() throws IOException {
		try {
			String methodName = "SFDC_AccountLWC@: ";
			// Select Accounts from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			sf.seleU.hardwait(3000);
			// Select account
			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
			reportStatusPass(methodName + " Selected Accounts from menu", true, false);
			sf.seleU.hardwait(3000);

			// Click on new Button
			sf.seleU.clickElementByJSE(sf.acc.newAccountButton);
			reportStatusPass(methodName + " Clicked on new button in accounts page", true, false);
			sf.seleU.wait(5000);
		} 
		catch (Throwable e)
		{
			reportStatusFail(" Creating Busniess Account is Unsuccessful", e);
			e.printStackTrace();
		} 
	}

	/**
	 * Date: 15/Jan/2020
	 * 
	 * @throws IOException
	 * 
	 *                     1. Open an business account
	 * 
	 *                     2. Click Create Account button on the account summary
	 *                     section
	 * 
	 *                     3. Select Account Record Type: Service
	 * 
	 * 
	 * 
	 */
	public void createNewServiceAccount() throws IOException {
		try {
			String methodName = "SFDC_Account@: ";

			// Select Accounts from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(2000);

			// Select account
			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
			reportStatusPass(methodName + " Selected Accounts from menu", true, false);
			sf.seleU.hardwait(2000);
			String mainWinHandel = driver.getWindowHandle();

			// Click on new Button
			sf.seleU.clickElementByJSE(sf.acc.newAccountButton);
			reportStatusPass(methodName + " Clicked on new button in accounts page", true, false);
			sf.seleU.wait(6000);

			Set<String> windowList = driver.getWindowHandles();
			Iterator<String> it = windowList.iterator();

			while (it.hasNext()) {
				String childWinHandel = it.next();
				if (!mainWinHandel.equals(childWinHandel)) {
					driver.switchTo().window(childWinHandel);
				}
			}

			// click on Service account radio button and click on next
			sf.seleU.switchToElementFrame(sf.acc.serviceAccountRadio);
			sf.seleU.clickElementByJSE(sf.acc.serviceAccountRadio.get(0));
			sf.seleU.clickElementByJSE(sf.acc.selectAccountType_nextBtn);
			reportStatusPass(methodName + " Selected account as Service and clicked on Next", true, false);
			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Creating Service Account is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	public void createNewServiceAccountRevised() throws IOException
	{
		try
		{
			//select new service account option
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.acc.controlDropDown.get(0));
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.acc.newServiceAccountIcon.get(0));
			sf.seleU.hardwait(3000);
		}
		catch (Throwable e)
		{
			reportStatusFail(" Creating Service Account is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Date: 15/Jan/2020
	 * 
	 * @throws IOException
	 * 
	 *                     1. Open an business account
	 * 
	 *                     2. Click Create Account button on the account summary
	 *                     section
	 * 
	 *                     3. Select Account Record Type: Billing
	 * 
	 * 
	 * 
	 */
	public void createNewBillingAccount() throws IOException {
		try {
			String methodName = "SFDC_Account@: ";

			// Select Accounts from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(2000);

			// Select account
			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
			reportStatusPass(methodName + " Selected Accounts from menu", true, false);
			sf.seleU.hardwait(2000);

			String mainWinHandel = driver.getWindowHandle();
			// Click on new Button
			sf.seleU.clickElementByJSE(sf.acc.newAccountButton);
			reportStatusPass(methodName + " Clicked on new button in accounts page", true, false);
			sf.seleU.wait(6000);

			Set<String> windowList = driver.getWindowHandles();
			Iterator<String> it = windowList.iterator();

			while (it.hasNext()) {
				String childWinHandel = it.next();
				if (!mainWinHandel.equals(childWinHandel)) {
					driver.switchTo().window(childWinHandel);
				}
			}

			// click on Service account radio button and click on next
			sf.seleU.switchToElementFrame(sf.acc.billingAccountRadio);
			sf.seleU.clickElementByJSE(sf.acc.billingAccountRadio.get(0));
			sf.seleU.clickElementByJSE(sf.acc.selectAccountType_nextBtn);
			reportStatusPass(methodName + " Selected account as Billing and clicked on Next", true, false);
			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Creating Billing Account is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1-Select Account option ftrom dropdown
	 * 
	 *                     2-Select All Accounts
	 * 
	 *                     3-Verify Access To Account Object
	 * 
	 */
	public void verifyAccountsObject() throws IOException {
		try {
			String methodName = "SFDC_Account@: ";

			// 1- Select Account option ftrom dropdown
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
			sf.seleU.hardwait(3000);

			// Verify Access To Account Object
			if (sf.acc.accountsAllRecords.size() > 0) {
				reportStatusPass(methodName + " Accounts Objects verified", true, true);
			} else {
				reportStatusFail(methodName + " Invalid Accounts Object", true);

			}
		} catch (Throwable e) {
			reportStatusFail(" verfification Failure for Accounts Object", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1. Open an business account
	 * 
	 *                     2.Select Change Employee Size Icon
	 * 
	 */
	public void selectChangeEmployeeSize() throws IOException {
		try {

			SFDC_AllPages sfdc = new SFDC_AllPages();
			String methodName = "SFDC_Home@: ";
			if (sf.seleU.isElementPresent(sf.ad.closeButton)) {
				sf.seleU.clickElementByJSE(sf.ad.closeButton);
			}
			//sf.seleU.wait(2000);
			//sf.seleU.clickElementByJSE(sf.cd.closeAlert);
			//sf.seleU.wait(2000);
			// Search Account
			sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
			// sfdc.accDetails.searchAccount(Global.dataInput.businessAccountName);

			// Select Change Control option DropDown
			sf.seleU.wait(2000);
			sf.seleU.switchToElementFrame(sf.acc.controlDropDown);
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.acc.controlDropDown.get(0));
			sf.seleU.wait(4000);

			// select change Employee Size option
			sf.seleU.clickElementByJSE(sf.acc.changeEmployeeSizeIcon.get(0));
			reportStatusPass(methodName + " Clicked on Change Employee Size Icon", true, false);
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(5000);
			sf.seleU.switchToElementFrame(sf.ad.businessAccountNameAsCompanyName);

		} catch (Throwable e) {
			reportStatusFail(" Selecting Change Employee Size was unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	public void selectChangePrimaryContact() throws IOException{
		try {
			
			SFDC_AllPages sfdc = new SFDC_AllPages();
			String methodName = "SFDC_Home@: ";
			// Search Account
			sf.seleU.hardwait(3000);
			sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);

			// Select Change Control option DropDown
			sf.seleU.wait(2000);
			sf.seleU.switchToElementFrame(sf.acc.controlDropDown);
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.acc.controlDropDown.get(0));
			sf.seleU.wait(4000);
			
			// select change Primary Contact option
			sf.seleU.clickElementByJSE(sf.acc.changePrimaryContact.get(0));
			reportStatusPass(methodName + " Clicked on Change Primary Contact Icon", true, false);
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(5000);
			sf.seleU.switchToElementFrame(sf.acc.changePrimaryContactCheckBox);
			sf.seleU.hardwait(3000);
            sf.seleU.clickElementByJSE(sf.acc.changePrimaryContactCheckBox.get(0));
            sf.seleU.hardwait(3000);
            
            //enter new contact details
            sf.seleU.enterText(sf.acc.selectPrimaryContactTextBox, Global.dataInput.contactName);
            sf.seleU.hardwait(5000);
            sf.seleU.enterText(sf.acc.selectPrimaryContactTextBox, Keys.ARROW_DOWN);
            sf.seleU.hardwait(2000);
            sf.seleU.enterText(sf.acc.selectPrimaryContactTextBox, Keys.ENTER);
            sf.seleU.hardwait(5000);
            reportStatusPass(methodName + " Entered Contact Name as " + Global.dataInput.contactName, true, false);
            sf.seleU.hardwait(3000);
            Global.dataInput.secondContact_prepareContactData();
            sf.seleU.hardwait(3000);
			sf.seleU.clearAndEnterText(sf.cc.contactFirstNameInput, Global.dataInput.contactFirstName);
			reportStatusPass(methodName + " Entered contact First Name as : " + Global.dataInput.contactFirstName, true, false);
			sf.seleU.hardwait(3000);
			sf.seleU.clearAndEnterText(sf.cc.contactLastNameInput, Global.dataInput.contactLastName);
			reportStatusPass(methodName + " Entered contact Last Name as : " + Global.dataInput.contactLastName, true, false);
			sf.seleU.hardwait(3000);
			sf.seleU.clearAndEnterText(sf.cc.contactEmailAddressInput, Global.dataInput.contactEmailAddress);
			reportStatusPass(methodName + " Entered contact Email Address as : " + Global.dataInput.contactEmailAddress, true, false);
			sf.seleU.hardwait(3000);
			sf.seleU.clearAndEnterText(sf.cc.contactPhoneInput, Global.dataInput.phoneNumber);
			reportStatusPass(methodName + " Entered contact Phone Number as : " + Global.dataInput.phoneNumber, true, false);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.cc.contactDetails_nextBtn);
			sf.seleU.hardwait(4000);
		}
		catch (Throwable e) {
			reportStatusFail(" Error in Changing Primary Contact ", e);
			e.printStackTrace();
		}
	}
	
	public void validateNewPrimaryContact() throws IOException {
		try {
			
//			SFDC_AllPages sfdc = new SFDC_AllPages();
			String methodName = "SFDC_Home@: ";
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.acc.primaryContactIcon);
			reportStatusPass(methodName + " Clicked on Primary Contact ", true, false);
            sf.seleU.hardwait(5000);
			if (sf.seleU.getTextFromWebElement(sf.cc.contactName).contains(Global.dataInput.contactFirstName)
					&& sf.seleU.getTextFromWebElement(sf.cc.contactName).contains(Global.dataInput.contactLastName)) 
			{
				reportStatusPass(methodName + "Verifying contact creation is successful", true, true);
				sf.seleU.wait(5000);
			}						
			else 
			{
			   reportStatusFail(methodName + " Verifying contact creation is Unsuccessful ", true);
			}

		}
		catch (Throwable e) {
			reportStatusFail(" Error in Changing Primary Contact ", e);
			e.printStackTrace();
		}

	}
	/**
	 * @throws IOException
	 * 
	 *                     1. Open an business account
	 * 
	 *                     2.Select Change Address
	 * 
	 */
	public void selectChangeBusinessAddress() throws IOException {
		try {

			SFDC_AllPages sfdc = new SFDC_AllPages();
			String methodName = "SFDC_Home@: ";
			// Search Account
			sf.seleU.hardwait(3000);
			sfdc.accDetails.searchBusAccGlobalSearch("TESTAuto_PVT220517027754");
			//sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.parentAccountName);
			// Select Change Control option DropDown
			sf.seleU.wait(2000);
			sf.seleU.switchToElementFrame(sf.acc.controlDropDown);
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.acc.controlDropDown.get(0));
			sf.seleU.wait(4000);

			// select change Business Address option
			sf.seleU.clickElementByJSE(sf.acc.changeBusinessAddress.get(0));
			reportStatusPass(methodName + " Clicked on Change Business Address Icon", true, false);
			sf.seleU.switchToDefaultContent();
			sf.seleU.clearAndEnterText(sf.cba.busAddressInput, Global.dataInput.address);
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.cba.busAddressInputclick);
			sf.seleU.wait(3000);
			reportStatusPass(
						methodName + " Entered Billing Address for Business Account as " + Global.dataInput.address,
						true, false);
			sf.seleU.hardwait(5000);
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.cba.uploadDocument, Constants.SAMPLE_UPLOAD_FILE);
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.ad.done);
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.ad.accountDetailsNextButton.get(0));
			sf.seleU.hardwait(5000);
			
		} catch (Throwable e) {
			reportStatusFail(" Error in Changing Business Address ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     1. Open an business account
	 * 
	 *                     2.Select Change Address
	 * 
	 */
	public void validateNewBusinessAddress() throws IOException {
		try {

			String methodName = "SFDC_Home@: ";
			sf.seleU.hardwait(4000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(4000);
						
			System.out.println(sf.seleU.getTextFromWebElement(sf.cba.billingAddressIcon.get(0)).concat(" ").concat
					(sf.seleU.getTextFromWebElement(sf.cba.billingAddressIcon.get(1))));
			String newBusinessAddress = (sf.seleU.getTextFromWebElement(sf.cba.billingAddressIcon.get(0)).concat(" ").concat
					(sf.seleU.getTextFromWebElement(sf.cba.billingAddressIcon.get(1))));
			
			System.out.println(Global.dataInput.address);
			
			System.out.println(newBusinessAddress.contains(Global.dataInput.address));
			
			boolean addressUpdated = newBusinessAddress.contains(Global.dataInput.address);
			
			if(addressUpdated)
			
//			if (newBusinessAddress.equalsIgnoreCase(Global.dataInput.address));
				
			{
				reportStatusPass(methodName + " Validated New Business Address ", true, true);	
			}
			
//			if (newBusinessAddress.equalsIgnoreCase(Global.dataInput.address)==false);
			
			else
			{
				reportStatusFail(methodName + " Error in validating New Business Address ", true);
		    }
					
		} catch (Throwable e) 
		{
			reportStatusFail(" Error in validating new Business Address ", e);
			e.printStackTrace();
		}
	}
		
		/**
		 * @throws IOException
		 * 
		 *                     Select 'Can not find Business address checkbox'
		 * 
		 * 
		 *                     Enter Fields for New Business Address
		 */
		public void createNewBusinessAddress() throws IOException {
			try {

				String methodName = "SFDC_Create Buniess Account@: ";

				// Select 'Can not find Business address checkbox'
				sf.seleU.switchToElementFrame(sf.cba.cannotFindAddressCheckbox);
				sf.seleU.clickElementByJSE(sf.cba.cannotFindAddressCheckbox.get(0));
				sf.seleU.wait(3000);
				reportStatusPass(methodName + "Selected 'Can not find business address checkbox", true, false);

				// Enter Fields for Business Address
				sf.seleU.clearAndEnterText(sf.cba.billingAddressstreet, Global.dataInput.addressStreet);
				sf.seleU.selectValueFromDropDown(sf.cba.billingAddressProvince, Global.dataInput.addressState);
				sf.seleU.clearAndEnterText(sf.cba.billingAddressCity, Global.dataInput.addressCity);
				sf.seleU.clearAndEnterText(sf.cba.billingAddressPostalCode, Global.dataInput.addressPostalCode);
				sf.seleU.clearAndEnterText(sf.cba.billingAddressCountry, Global.dataInput.addressCountry);
				reportStatusPass(methodName + " Entered  New Billing Address for Business Account as " + Global.dataInput.address, true, false);
								
			} catch (Throwable e) {
				reportStatusFail(" Entering details for Buniess Account Address is Unsuccessful", e);
				e.printStackTrace();
			}
		}



	/**
	 * @throws IOException
	 * 
	 *                     1. Open an business account
	 * 
	 *                     2. Select Change Legal Name Icon
	 * 
	 */
	public void selectChangeLegalName() throws IOException {
		try {

			SFDC_AllPages sfdc = new SFDC_AllPages();
			String methodName = "SFDC_Account_Home@: ";

			// Search Account
			sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);

			// Select Change Control option DropDown
			sf.seleU.wait(2000);
			sf.seleU.switchToElementFrame(sf.acc.controlDropDown);
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.acc.controlDropDown.get(0));
			sf.seleU.wait(4000);

			// Select Change Legal Name
			sf.seleU.clickElementByJSE(sf.acc.changeLegalNameIcon.get(0));
			reportStatusPass(methodName + " Clicked on Change Legal Name Icon", true, false);
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(5000);
			sf.seleU.switchToElementFrame(sf.cln.legalNameInput);

		} catch (Throwable e) {
			reportStatusFail(" Selecting Change Legal Name was unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Create Relationship Button
	 */
	public void selectCreateRelationshipIcon() throws IOException {
		try {

			String methodName = "SFDC_Account_Home@: ";

			// Select Create Relationship Button
			sf.seleU.switchToElementFrame(sf.acc.createRelationshipIcon);
			sf.seleU.clickElementByJSE(sf.acc.createRelationshipIcon.get(0));
			reportStatusPass(methodName + " Clicked on Create Relationship Icon", true, false);
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(5000);
			sf.seleU.switchToElementFrame(sf.liMAcc.addMasterAccountIconAllRows);

		} catch (Throwable e) {
			reportStatusFail(" Selecting Create Relationship was unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Remove Relationship Button
	 */
	public void selectRemoveRelationshipIcon() throws IOException {
		try {

			String methodName = "SFDC_Account_Home@: ";

			// Select Remove Relationship Button
			sf.seleU.switchToElementFrame(sf.acc.removeRelationshipIcon);
			sf.seleU.clickElementByJSE(sf.acc.removeRelationshipIcon.get(0));
			reportStatusPass(methodName + " Clicked on Remove Relationship Icon", true, false);
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(5000);
			sf.seleU.switchToElementFrame(sf.liMAcc.addSelectIconAllRows);

		} catch (Throwable e) {
			reportStatusFail(" Selecting Remove Relationship was unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1. Verify LegalName Column exist
	 * 
	 */
	public void verifyLegalNameColumnExist() throws IOException {
		try {

			verifyFieldDisplayed("Legal Name Column", sf.acc.legalNameColumn);

		} catch (Throwable e) {
			reportStatusFail(" Verifying Legal Name column was unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1. Verify LegalName Column doesn't exist
	 * 
	 */
	public void verifyLegalNameColumnNotExist() throws IOException {
		try {

			verifyFieldNotDisplayed("Legal Name Column", sf.acc.legalNameColumn);

		} catch (Throwable e) {
			reportStatusFail(" Verifying Legal Name column absence was unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1. Select Business acc radio button
	 * 
	 */
	public void selectBusinessAccType() throws IOException {
		try {
			// click on business account radio button and click on next
			sf.seleU.switchToElementFrame(sf.acc.businessAccountRadio);
			sf.seleU.clickElementByJSE(sf.acc.businessAccountRadio.get(0));
			sf.seleU.clickElementByJSE(sf.acc.selectAccountType_nextBtn);
			reportStatusPass(" Selected account as Business and clicked on Next", true, false);
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Selecting account Type as business was unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1. Verify Presence/absence of billing account radio
	 *                     button
	 * 
	 */
	public void verifyBillingAccRadioButton(boolean presence) throws IOException {
		try {
			// Validate billing acc radio button is present/absent
			sf.seleU.switchToElementFrame(sf.acc.businessAccountRadio);
			if (presence)
				verifyFieldDisplayed("Billing Account Radio Button", sf.acc.billingAccountRadio.get(0));
			else
				verifyFieldNotDisplayed("Billing Account Radio Button", sf.acc.billingAccountRadio, 0);
			sf.seleU.hardwait(2000);
			sf.seleU.switchToDefaultContent();
		} catch (Throwable e) {
			reportStatusFail(" Validating Billing acc radio button presence/absence is unsuccessfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1-Select All Business Account option from List View
	 * 
	 *                     2-Open Any Active Business Account
	 * 
	 */
	public void openAnyActiveBusinessAccountFromList() throws IOException {

		String methodName = "SFDC_Account@: ";

		try {
			Boolean accFound = false;
			// 1- Select All Business Accounts
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.acc.businessAccountsOption);
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + " Selected Business Accounts Option", true, false);
			sf.seleU.hardwait(2000);

			// Verify Access To Account Object
			if (sf.acc.parentAccountsAllRecords.size() > 0) {
				for (int i = 0; i < sf.acc.parentAccountsAllRecords.size(); i++) {
					if (!(sf.seleU.getTextFromWebElement(sf.acc.statusTextRows.get(i)) == null))
						if (sf.seleU.getTextFromWebElement(sf.acc.statusTextRows.get(i))
								.equalsIgnoreCase(sf.dataInput.status_Active)) {
							sf.seleU.clickElementByJSE(sf.acc.parentAccountsAllRecords.get(i));
							reportStatusPass(methodName + " Selected first Active business account from list", true,
									true);
							accFound = true;
							break;
						}
				}
			} else {
				reportStatusFail(methodName + " No Business Account found", true);
			}
			if (!accFound)
				reportStatusFail(methodName + " No Active Business Account found", true);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not open active business account", e);
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
	 *                     Verify Field is not displayed
	 */
	public static void verifyFieldNotDisplayed(String fieldName, List<WebElement> element, int i) throws IOException {
		try {
			try {
				if (sf.seleU.isElementDisplayed(element.get(i))) {
					reportStatusFail(fieldName + " is displayed, It should not be present", true);

				} else {
					reportStatusPass(" Validated " + fieldName + " is not displayed", true, true);
				}
			} catch (IndexOutOfBoundsException ex) {
				reportStatusPass(" Validated " + fieldName + " is not displayed", true, true);
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
	public void verifyFieldNotDisplayed(String fieldName, WebElement element) throws IOException {
		try {
			sf.seleU.wait(3000);

			// Verify Field displayed
			if (!sf.seleU.isElementDisplayed(element)) {
				reportStatusPass("Validated " + fieldName + " is not displayed as expected", true, true);
			} else {
				reportStatusFail(fieldName + " is displayed, It should be absent", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Accounts from Menu
	 * 
	 *                     Select Account from Menu
	 * 
	 *                     Click on New Button
	 * 
	 *                     Switch to Window 2
	 * 
	 *                     Create Business Account
	 */
	public void launchCreateNewBusinessAccount() throws IOException {

		try {
			String methodName = "SFDC_Account@: ";

			// Select Accounts from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(2000);

			// Select account
			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
			reportStatusPass(methodName + " Selected Accounts from menu", true, false);
			sf.seleU.hardwait(2000);

			// Click on new Button
			sf.seleU.clickElementByJSE(sf.acc.newAccountButton);
			reportStatusPass(methodName + " Clicked on new button in accounts page", true, false);
			sf.seleU.wait(9000);
			sf.seleU.switchWindow(2);

			// click on business account radio button and click on next
			sf.seleU.switchToElementFrame(sf.acc.businessAccountRadio);
			sf.seleU.clickElementByJSE(sf.acc.businessAccountRadio.get(0));
			sf.seleU.clickElementByJSE(sf.acc.selectAccountType_nextBtn);
			reportStatusPass(methodName + " Selected account as Business and clicked on Next", true, false);
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail("  Launching new account omniscript and Creating Busniess Account is Unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Accounts from menu
	 * 
	 *                     Select account
	 */
	public void selectAccountTab() throws IOException {
		try {
			String methodName = "SFDC_Account@: ";

			// Select Accounts from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(2000);

			// Select account
			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
			reportStatusPass(methodName + " Selected Accounts from menu", true, false);
			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(" Selecting Account tab is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Voice of R4B links are present on Account Page
	 * 
	 *                     Verify if link is open in new tab
	 */
	public void verifyVoiceOfLinksPresentAtAcc() throws IOException {
		try {
			String methodName = "SFDC_Account@: ";

			// Select Links from Accounts page
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.acc.links);
			sf.seleU.hardwait(2000);

			// Verify Verify Voice of R4B links are present on Account Page
			String str = "Voice of R4B";
			if (str.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.acc.voiceOfLinks)))
				reportStatusPass(methodName + " Verifed Voice of R4B links are present on Account Page", true, true);

			else
				reportStatusFail(" Verification Failed- Verifed Voice of R4B links are not present on Account Page ",
						true);

			// Select account
			sf.seleU.clickElementByJSE(sf.acc.voiceOfLinks);
			reportStatusPass(methodName + " Clicked on Voice of R4B Links from Accounts", true, false);
			sf.seleU.hardwait(4000);
			sf.seleU.switchWindow(2);
			String str2 = sf.seleU.getCurrentUrl();
			reportStatusPass(str2 + " Verified Voice of R4B links are opened in new tab", true, true);
			sf.seleU.hardwait(2000);
			sf.seleU.switchWindow(1);
			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Selecting Voice of R4B Link is Unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Create New Salesforce Case link
	 * 
	 *                     Verify if link is open in new tab
	 */
	public void verifyCreateNewSalesforcecaselink() throws IOException {
		try {
			String methodName = "SFDC_Account@: ";

			// Select Links from Home page
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.acc.links);
			sf.seleU.hardwait(2000);

			// Verify Create New Salesforce Case link is present

			if (Global.dataInput.createNewSalesforceCase.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.acc.createNewCase)))
				reportStatusPass(methodName + " Verifed Create New Salesforce Case is present on Home Page", true, true);

			else
				reportStatusFail(" Verification Failed- Verifed Create New Salesforce Case is not present on Home Page ",
						true);

			// Verify Create New Salesforce Case link is functional 
			sf.seleU.clickElementByJSE(sf.acc.createNewCase);
			reportStatusPass(methodName + " Clicked on Create New Salesforce Case from Links", true, false);
			sf.seleU.hardwait(4000);
			
			String url = sf.acc.createNewCase.getAttribute("href");       
			sf.seleU.switchWindow(2);
			String str2 = sf.seleU.getCurrentUrl();
			reportStatusPass(str2 + " Verified Create New Salesforce Case is opened in new tab", true, true);
			// Verify Create New Compensation Case is opening in new tab and shows actual URL address
			if(InputData_Sales.createNewSalesforceCaseURL.equalsIgnoreCase(url))
			reportStatusPass(url + " Verified Create New Salesforce Case is opened in new tab with Actual URL Address", true, true);
			else
				reportStatusFail(" Verification Fail - Create New Salesforce Case Expected URL is not equal to Actual URL Address ", true);
			sf.seleU.hardwait(2000);
			sf.seleU.switchWindow(1);
			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Selecting Create New Salesforce Case is Unsuccessful", e);
			e.printStackTrace();
		}

	}
	
	
	/** 
	 * 
	 * @throws IOException 
	 * 					   Create New Compensation Case links
	 *                     Salesforce Classic Issues and Workarounds,Salesforce Lightning Sales Support Chatter Group
	 *                     Wireless Offer Grid,Wireline Offer Grid
	 *                     Mission Possible Hub links are present 
	 *  
	 *                   
	 */
	public void verifyCreateNewCompensationCaselink() throws IOException {
		try {
			String methodName = "SFDC_Account@: ";
			sf.seleU.closeRecentlyOpenedWindow();
			selectEnv();
			// Select Links from Home page
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.acc.links);
			sf.seleU.hardwait(2000);

			// Verify Create New Compensation Case link is present

			if (Global.dataInput.createNewCompensationCase.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.acc.createCompensationCase)))
				reportStatusPass(methodName + " Verifed Create New Compensation Case link is present on Home Page", true, true);

			else
				reportStatusFail(" Verification Failed- Verifed Create New Compensation Case link is not present on Home Page ",
						true);

			// Verify Create New Compensation Case link is functional 
			sf.seleU.clickElementByJSE(sf.acc.createCompensationCase);
			reportStatusPass(methodName + " Clicked on Create New Compensation Case from Links", true, false);
			sf.seleU.hardwait(4000);
			String url = sf.acc.createCompensationCase.getAttribute("href"); 
			sf.seleU.switchWindow(2);
			String str2 = sf.seleU.getCurrentUrl();
			reportStatusPass(str2 + " Verified Create New Compensation Case link is opened in new tab", true, true);
			// Verify Create New Compensation Case link is opening in new tab and shows actual URL address
			if(InputData_Sales.createNewCompensationCaseURL.equalsIgnoreCase(url))
			reportStatusPass(url + " Verified Create New Compensation Case link is opened in new tab with Actual URL Address", true, true);
			else
				reportStatusFail(" Verification Fail - Create New Compensation Casee Expected URL is not equal to Actual URL Address ", true);
				
			sf.seleU.hardwait(2000);
			sf.seleU.switchWindow(1);
			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Selecting Create New Compensation Case link is Unsuccessful", e);
			e.printStackTrace();
		}

	}
	
	/** 
	 * 
	 * @throws IOException 
	 * 					   Salesforce Classic Issues and Workarounds
	 *  
	 *                   
	 */
	public void verifySalesforceClassicIAWlink() throws IOException {
		try {
			String methodName = "SFDC_Account@: ";
			sf.seleU.closeRecentlyOpenedWindow();
			selectEnv();
			// Select Links from Home page
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.acc.links);
			sf.seleU.hardwait(2000);

			// Verify Salesforce Classic Issues and Workarounds link is present

			if (Global.dataInput.salesforceClassicIssuessAW.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.acc.salesforceClassIAW)))
				reportStatusPass(methodName + " Verifed Salesforce Classic Issues and Workarounds link is present on Home Page", true, true);

			else
				reportStatusFail(" Verification Failed- Verifed Salesforce Classic Issues and Workarounds link is not present on Home Page ",
						true);

			// Verify Salesforce Classic Issues and Workarounds link is functional 
			sf.seleU.clickElementByJSE(sf.acc.salesforceClassIAW);
			reportStatusPass(methodName + " Clicked on Salesforce Classic Issues and Workarounds from Links", true, false);
			sf.seleU.hardwait(4000);
			String url = sf.acc.salesforceClassIAW.getAttribute("href"); 
			sf.seleU.switchWindow(2);
			String str2 = sf.seleU.getCurrentUrl();
			reportStatusPass(str2 + " Verified Salesforce Classic Issues and Workarounds link is opened in new tab with", true, true);
			// Verify Salesforce Classic Issues and Workarounds link is opening in new tab and shows actual URL address
			if(InputData_Sales.salesforceClassicIssuessAWURL.equalsIgnoreCase(url))
			reportStatusPass(url + " Verified Salesforce Classic Issues and Workarounds link is opened in new tab with Actual URL Address", true, true);
			else
				reportStatusFail(" Verification Fail - Salesforce Classic Issues and Workarounds Expected URL is not equal to Actual URL Address ", true);
			sf.seleU.hardwait(2000);
			sf.seleU.switchWindow(1);
			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Selecting Salesforce Classic Issues and Workarounds link is Unsuccessful", e);
			e.printStackTrace();
		}

	}
	
		
	/** 
	 * 
	 * @throws IOException 
	 * 					   Salesforce Lightning Sales Support Chatter Group
	 *                     Wireless Offer Grid,Wireline Offer Grid
	 *                     Mission Possible Hub links are present 
	 *  
	 *                   
	 */
	public void verifySalesforceLightingSalesSSCGlink() throws IOException {
		try {
			String methodName = "SFDC_Account@: ";
			sf.seleU.closeRecentlyOpenedWindow();
			selectEnv();
			// Select Links from Home page
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.acc.links);
			sf.seleU.hardwait(2000);

			// Verify Salesforce Lightning Sales Support Chatter Group link is present

			if (dataInput.salesforceLightingSalesCG.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.acc.salesforceLSSCG)))
				reportStatusPass(methodName + " Verifed Salesforce Lightning Sales Support Chatter Group link is present on Home Page", true, true);

			else
				reportStatusFail(" Verification Failed- Verifed Salesforce Lightning Sales Support Chatter Group link is not present on Home Page ",
						true);

			// Verify Salesforce Lightning Sales Support Chatter Group link is functional 
			sf.seleU.clickElementByJSE(sf.acc.salesforceLSSCG);
			reportStatusPass(methodName + " Clicked on Salesforce Lightning Sales Support Chatter Group from Links", true, false);
			sf.seleU.hardwait(4000);
			String url = sf.acc.salesforceLSSCG.getAttribute("href");
			sf.seleU.switchWindow(2);
			String str2 = sf.seleU.getCurrentUrl();
			reportStatusPass(str2 + " Verified Salesforce Lightning Sales Support Chatter Group link is opened in new tab", true, true);
			// Verify Salesforce Lightning Sales Support Chatter Group link is opening in new tab and shows actual URL address
			if(InputData_Sales.salesforceLightingSalesCGURL.equalsIgnoreCase(url))
			reportStatusPass(url + " Verified Salesforce Lightning Sales Support Chatter Group link is opened in new tab with Actual URL Address", true, true);
			else
				reportStatusFail(" Verification Fail - Salesforce Lightning Sales Support Chatter Group Expected URL is not equal to Actual URL Address ", true);
			sf.seleU.hardwait(2000);
			sf.seleU.switchWindow(1);
			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Selecting Salesforce Lightning Sales Support Chatter Group link is Unsuccessful", e);
			e.printStackTrace();
		}

	}
	
	/** 
	 * 
	 * @throws IOException 
	 * 					
	 *                     Wireless Offer Grid,Wireline Offer Grid
	 *                     Mission Possible Hub links are present 
	 *  
	 *                   
	 */
	public void verifyWirelessOfferGridlink() throws IOException {
		try {
			String methodName = "SFDC_Account@: ";
			sf.seleU.closeRecentlyOpenedWindow();
			selectEnv();
			// Select Links from Home page
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.acc.links);
			sf.seleU.hardwait(2000);

			// Verify Wireless Offer Grid link is present

			if (Global.dataInput.wirelessOfferGrid.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.acc.wirelessOfferGrid)))
				reportStatusPass(methodName + " Verifed Wireless Offer Grid link is present on Home Page", true, true);

			else
				reportStatusFail(" Verification Failed- Verifed Wireless Offer Grid link is not present on Home Page ",
						true);

			// Verify Wireless Offer Grid link is functional 
			sf.seleU.clickElementByJSE(sf.acc.wirelessOfferGrid);
			reportStatusPass(methodName + " Clicked on Wireless Offer Grid from Links", true, false);
			sf.seleU.hardwait(4000);
			String url = sf.acc.wirelessOfferGrid.getAttribute("href");
			sf.seleU.switchWindow(2);
			String str2 = sf.seleU.getCurrentUrl();
			reportStatusPass(str2 + " Verified Wireless Offer Grid link is opened in new tab", true, true);
			// Verify Wireless Offer Grid link is opening in new tab and shows actual URL address
			if(InputData_Sales.wirelessOfferGridURL.equalsIgnoreCase(url))
			reportStatusPass(url + " Verified Wireless Offer Grid link is opened in new tab with Actual URL Address", true, true);
			else
				reportStatusFail(" Verification Fail - Wireless Offer Grid link Expected URL is not equal to Actual URL Address ", true);
			sf.seleU.hardwait(2000);
			sf.seleU.switchWindow(1);
			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Selecting Wireless Offer Grid link is Unsuccessful", e);
			e.printStackTrace();
		}

	}
	
	/** 
	 * 
	 * @throws IOException 
	 * 					
	 *                     Wireline Offer Grid
	 *                     Mission Possible Hub links are present 
	 *  
	 *                   
	 */
	public void verifyWirelineOfferGridlink() throws IOException {
		try {
			String methodName = "SFDC_Account@: ";
			sf.seleU.closeRecentlyOpenedWindow();
			selectEnv();
			// Select Links from Home page
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.acc.links);
			sf.seleU.hardwait(2000);

			// Verify  Wireline Offer Grid link is present

			if (Global.dataInput.wirelineOfferGrid.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.acc.wirelineOfferGrid)))
				reportStatusPass(methodName + " Verifed Wireline Offer Grid link is present on Home Page", true, true);

			else
				reportStatusFail(" Verification Failed- Verifed Wireline Offer Grid link is not present on Home Page ",
						true);

			// Verify Wireline Offer Grid link is functional 
			sf.seleU.clickElementByJSE(sf.acc.wirelineOfferGrid);
			reportStatusPass(methodName + " Clicked on Wireline Offer Grid from Links", true, false);
			sf.seleU.hardwait(4000);
			String url = sf.acc.wirelineOfferGrid.getAttribute("href");
			sf.seleU.switchWindow(2);
			String str2 = sf.seleU.getCurrentUrl();
			reportStatusPass(str2 + " Verified Wireline Offer Grid link is opened in new tab", true, true);
			// Verify Wireline Offer Grid link is opening in new tab and shows actual URL address
			if(InputData_Sales.wirelineOfferGrideURL.equalsIgnoreCase(url))
			reportStatusPass(url + " Verified Wireline Offer Grid link is opened in new tab with Actual URL Address", true, true);
			else
				reportStatusFail(" Verification Fail -  Wireline Offer Grid Expected URL is not equal to Actual URL Address ", true);
			sf.seleU.hardwait(2000);
			sf.seleU.switchWindow(1);
			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Selecting Wireline Offer Grid link is Unsuccessful", e);
			e.printStackTrace();
		}

	}
	
	/** 
	 * 
	 * @throws IOException 				
	 *                    
	 *                     Verify Mission Possible Hub link is present 
	 *                 
	 */
	public void verifyMissionPossibleHubLink() throws IOException {
		try {
			String methodName = "SFDC_Account@: ";
			sf.seleU.closeRecentlyOpenedWindow();
			selectEnv();
			// Select Links from Home page
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.acc.links);
			sf.seleU.hardwait(2000);

			// Verify  Mission Possible Hub link is present

			if (Global.dataInput.missionPossibleHub.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.acc.missionPosiibleHub)))
				reportStatusPass(methodName + " Verifed Mission Possible Hub link is present on Home Page", true, true);

			else
				reportStatusFail(" Verification Failed- Verifed Mission Possible Hub link is not present on Home Page ",
						true);

			// Verify Mission Possible Hub link is functional 
			sf.seleU.clickElementByJSE(sf.acc.missionPosiibleHub);
			reportStatusPass(methodName + " Clicked on Mission Possible Hub from Links", true, false);
			sf.seleU.hardwait(4000);
			String url = sf.acc.missionPosiibleHub.getAttribute("href");
			sf.seleU.switchWindow(2);
			String str2 = sf.seleU.getCurrentUrl();
			reportStatusPass(str2 + " Verified Mission Possible Hub link is opened in new tab", true, true);
			// Verify Mission Possible Hub link is opening in new tab and shows actual URL address
			if(InputData_Sales.missionPossibleHubURL.equalsIgnoreCase(url))
			reportStatusPass(url + " Verified Mission Possible Hub link is opened in new tab with Actual URL Address", true, true);
			else
				reportStatusFail(" Verification Fail -  Mission Possible Hub Expected URL is not equal to Actual URL Address ", true);
			sf.seleU.hardwait(2000);
			sf.seleU.switchWindow(1);
			sf.seleU.hardwait(2000);
			sf.seleU.closeRecentlyOpenedWindow();
			selectEnv();
		} catch (Throwable e) {
			reportStatusFail(" Selecting Mission Possible Hub link is Unsuccessful", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * @throws IOException
	 * 
	 *                     1. Open an business account through Data Gov Profile
	 * 
	 *                     2.Select Change Employee Size Icon
	 * 
	 */
	public void selectChangeEmployeeSizeThruDataGov() throws IOException {
		try {

			SFDC_AllPages sfdc = new SFDC_AllPages();
			String methodName = "SFDC_Home@: ";

			// Search Account
			// sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
			// sfdc.accDetails.searchAccount(Global.dataInput.businessAccountName);

			if (sf.seleU.isElementDisplayed(sf.ar.detailsTab))
				sf.seleU.clickElementByJSE(sf.ar.detailsTab);
			else {
				sf.seleU.clickElementByJSE(sf.ar.moreTabs);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.ar.detailsTab);

			}

			sf.seleU.hardwait(3000);
			// select change Employee Size option
			sf.seleU.clickElementByJSE(sf.ad.editEmployeesButton);
			sf.seleU.wait(2000);

			reportStatusPass(methodName + " Clicked on Details Tab", true, false);
//			sf.seleU.switchToDefaultContent();
//			sf.seleU.hardwait(5000);
//			sf.seleU.switchToElementFrame(sf.ad.businessAccountNameAsCompanyName);

		} catch (Throwable e) {
			reportStatusFail(" Selecting Change Employee Size was unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     1. Open an service account 
	 * 
	 *                     2. Select one account  
	 * 
	 */
	public void selectAnyServiceAccount() throws IOException {
		try {

			String methodName = "SFDC_Account@: ";
			// Select All Service Accounts
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(2000);
			// Select All Service Accounts
			sf.seleU.clickElementByJSE(sf.acc.allServiceAccountOption);
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.acc.accountsAllRecords.get(0));
			sf.seleU.hardwait(4000);
			
			reportStatusPass(methodName + " Selected One Service Accounts from Option", true, false);

		} catch (Throwable e) {
			reportStatusFail(" Selecting  One Service Accounts from Option was unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Select Account from navigation menu
	 * And open first Business Account
	 * @throws IOException
	 */
	public void selectAndOpenAnyBusAcc() throws IOException {

		try {
			String methodName = "SFDC_Account@: ";
			// 1-Selecting Leads from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(2000);

			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
			reportStatusPass(methodName + " Selected Account from menu", true, false);
			sf.seleU.hardwait(2000);

			// 2- Select All Leads
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.acc.allBusAccountOption);
			sf.seleU.hardwait(4000);
			reportStatusPass(methodName + " Selected All Busniness Account Option", true, false);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.lead.leadsAllRecords.get(0));
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + " Account found and Opening First Bus Account::  " ,true,false);
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Selecting and Opening a Account is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to select Env 
	 * @throws IOException
	 */
	public void selectEnv() throws IOException {

		try {
			String methodName = "@Select Env: ";
		
			sf.seleU.hardwait(5000);
			if (Global.dataInput.env.equalsIgnoreCase("ITDEVSTAGE"))
				sf.seleU.openURL("https://r4b--itdevstage.my.salesforce.com/");
			else if (Global.dataInput.env.equalsIgnoreCase("PREFIT") || 
					Global.dataInput.env.equalsIgnoreCase("R4BPREFIT"))
				sf.seleU.openURL("https://r4b--r4bprefit.lightning.force.com/");
			else if (Global.dataInput.env.equalsIgnoreCase("PREPOD"))
				sf.seleU.openURL("https://r4b--r4bpreprod.my.salesforce.com/");
			else if (Global.dataInput.env.equalsIgnoreCase("ITQATEST"))
				sf.seleU.openURL("https://r4b--itqatest.my.salesforce.com/");
			else if (Global.dataInput.env.equalsIgnoreCase("ITPROD")|| 
					Global.dataInput.env.equalsIgnoreCase("PROD"))
				sf.seleU.openURL("https://r4b.my.salesforce.com");
			else
				sf.seleU.openURL("https://r4b--itqatest.my.salesforce.com/");
		} catch (Throwable e) {
			reportStatusFail(" Selecting and Opening an Env is Unsuccessful", e);
			e.printStackTrace();
		}
	}

}
