package sfdc.pages.sales;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Pankaj.Agarwal, Date : 19/12/2020
 *
 *         B2B Business Account Creation along with the account details
 *         validation
 */
public class SFDC_b2bAccount_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_b2bAccount_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * Click on Home tab button to navigate to home page
	 * 
	 * @throws IOException
	 */

	public void homeTabClick() throws IOException {

		try {

			String methodName = "SFDC_Home Tab Button Click@: ";

			// 1-Clicking home tab button
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.b2bAccountObj.homeTabButton);
			reportStatusPass(methodName + "Clicked on the home tab button", true, false);
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail("Selecting on the home tab button is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * Select Account from navigation menu tab and click on new account button
	 * 
	 * @throws IOException
	 */

	public void newAccount() throws IOException {

		try {

			String methodName = "SFDC_New Account@: ";

			// 1-Clicking Accounts from menu tab
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.b2bAccountObj.accountNavigationButton);
			// sf.seleU.hardwait(2000);
			reportStatusPass(methodName + " Selected Accounts Navigation from menu", true, false);
			sf.seleU.hardwait(2000);

			// 2- Click New Account Button
			sf.seleU.clickElementByJSE(sf.b2bAccountObj.newNavigationButton);
			sf.seleU.hardwait(4000);
			reportStatusPass(methodName + " Selected New Account Button", true, false);

			// 3- Select Account Record Type as submitted
			sf.seleU.selectTextFromDropDown(sf.b2bAccountObj.accountRecordTypeText, sf.dataInput.b2b_accountRecordType);
			sf.seleU.hardwait(5000);
			reportStatusPass(methodName + "Selected account record type as " + sf.dataInput.b2b_accountRecordType, true,
					false);

			// 3- Click Continue Button
			sf.seleU.clickElementByJSE(sf.b2bAccountObj.accountContinueButton);
			sf.seleU.hardwait(4000);
			reportStatusPass(methodName + " Clicked on Continue Button", true, false);

		} catch (Throwable e) {
			reportStatusFail(" Creating new account is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * Enter businsAccount details to create an new Account and click on next
	 * 
	 * @throws IOException
	 */
	public void enterAccountDetails(String businessAccountName, String malID) throws IOException {

		try {

			String methodName = "SFDC_Enter Account Details@: ";

			// 1-Enter Company Name
			sf.seleU.hardwait(5000);
			sf.seleU.enterText(sf.b2bAccountObj.companyNameInput, businessAccountName);
			// sf.seleU.hardwait(2000);
			reportStatusPass(methodName + " Entered Company Name " + businessAccountName, true, false);
			sf.seleU.hardwait(2000);

			// 2- Select Account Status
			sf.seleU.selectTextFromDropDown(sf.b2bAccountObj.accountStatusDropdownInput,
					sf.dataInput.b2b_accountStatus);
			sf.seleU.hardwait(4000);
			reportStatusPass(methodName + " Selected Account Status as " + sf.dataInput.b2b_accountStatus, true, false);

			// 3- Select Franchise Location
			sf.seleU.selectTextFromDropDown(sf.b2bAccountObj.franchiseLocationDropDownInput,
					sf.dataInput.b2b_franchiseLocation);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "Selected franchise location as " + sf.dataInput.b2b_franchiseLocation, true,
					false);

			// 3- Select Franchise Owner
			sf.seleU.selectTextFromDropDown(sf.b2bAccountObj.franchiseOwnerDropdownInput,
					sf.dataInput.b2b_franchiseOwner);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "Selected franchise owner as " + sf.dataInput.b2b_franchiseOwner, true,
					false);

			// 4- Enter No of Employees
			sf.seleU.enterText(sf.b2bAccountObj.noOfEmployeesInput, sf.dataInput.numberOfEmployees);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "Entered the emp size as " + sf.dataInput.numberOfEmployees, true, false);

			// 5- Enter Mal ID
			sf.seleU.enterText(sf.b2bAccountObj.malIDInput, malID);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "Entered MalId as " + malID, true, false);

			// 6- Click on Save Button
			sf.seleU.ScrolltoElement(sf.b2bAccountObj.accountSaveButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.b2bAccountObj.accountSaveButton);
			sf.seleU.hardwait(4000);
			reportStatusPass(methodName + " Clicked on Save Button", true, false);

		} catch (Throwable e) {
			reportStatusFail(" Invalid Entry for Account Details", e);
			e.printStackTrace();
		}
	}

	/**
	 * Verify Account got created successfully in the details page and AutoDuns
	 * populated for the Business account
	 * 
	 * @throws IOException
	 */
	public void verifyAccountCreated() throws IOException {
		try {

			String methodName = "SFDC_Verify Creatd businessAccount@: ";

			sf.seleU.ScrolltoElement(sf.b2bAccountObj.accDetailsPhoneTextBox);
			sf.seleU.hardwait(3000);

			// If the account details text tag is present and autoduns is populating with
			// the status as prospect then the
			// business account is successfully been created.
			if (sf.seleU.isElementPresent(sf.b2bAccountObj.accountDetailTagText)
					&& sf.seleU.isElementPresent(sf.b2bAccountObj.accDetailsAutoDunsNumText)) {

				if (sf.seleU.getTextFromWebElement(sf.b2bAccountObj.accountDetailTagText)
						.contains(sf.dataInput.b2b_accountDetailTag)
						&& sf.seleU.getTextFromWebElement(sf.b2bAccountObj.accountDetailStatusText)
								.contains(sf.dataInput.b2b_status_Prospect)) {

					reportStatusPass(methodName + "Business account "
							+ sf.seleU.getTextFromWebElement(sf.b2bAccountObj.accDetailsAutoDunsNumText)
							+ " created succesfully and autoDuns populated", true, true);
				} else {
					reportStatusFail("Invalid Account Creation", true);
				}

			}

		} catch (Throwable e) {
			reportStatusFail(" Verifying Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * Search Business Account from search bar in Home Page
	 * 
	 * @throws IOException
	 */
	public void searchAccountCreated(String businessAccountName) throws IOException {
		try {
			String methodName = "SFDC_Search Account@: ";
			boolean isStatusfound = true;

			sf.seleU.wait(10000);
			sf.seleU.refreshPage();
			sf.seleU.wait(10000);
			// Enter the account name and press enter
			sf.seleU.clearAndEnterText(sf.b2bAccountObj.searchEnterInput, businessAccountName);
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + "Business Account name entered as " + businessAccountName, true, false);

			sf.seleU.clickElementByJSE(sf.b2bAccountObj.searchButton);
			reportStatusPass(methodName + "Clicked on search button", true, false);

			// if the list size is greater then equal to 1 and is equal to searched account
			// name then the account is found
			if (sf.b2bAccountObj.accountsAllRecords.size() >= 1) {

				if (sf.seleU.getTextFromWebElement(sf.b2bAccountObj.accountsAllRecordsTradeText).trim()
						.equals(businessAccountName)) {
					sf.seleU.hardwait(2000);
					sf.seleU.clickElementByJSE(sf.b2bAccountObj.accountsAllRecordsCompanyLink);
					isStatusfound = true;

				} else {
					isStatusfound = false;
				}
			}
			sf.seleU.hardwait(2000);
			sf.seleU.ScrolltoElement(sf.b2bAccountObj.accountDetailTagText);

			// validate Account search
			if (isStatusfound) {
				reportStatusPass(methodName + " Account search results are valid and the account " + businessAccountName
						+ "was found successfully", true, true);
			} else {
				reportStatusFail(methodName + " Account search results are Invalid", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Searching an business account is Unsuccessful", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * Search Business Account from search bar in Home Page and validate 
	 *  Account is be created in R4B and synced into B2B successfully.
	 * 
	 * @throws IOException
	 */
	public void searchAccountCreatedValidation(String businessAccountName) throws IOException {
		try {
			String methodName = "SFDC_Search Account@: ";
			boolean isStatusfound = true;

			sf.seleU.wait(2000);

			// Enter the account name and press enter
			sf.seleU.clearAndEnterText(sf.b2bAccountObj.searchEnterInput, businessAccountName);
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + "Business Account name entered as " + businessAccountName, true, false);

			sf.seleU.clickElementByJSE(sf.b2bAccountObj.searchButton);
			reportStatusPass(methodName + "Clicked on search button", true, false);

			// if the list size is greater then equal to 1 and is equal to searched account
			// name then the account is found
			if (sf.b2bAccountObj.accountsAllRecords.size() >= 1) {

				if (sf.seleU.getTextFromWebElement(sf.b2bAccountObj.accountsAllRecordsTradeText).trim()
						.equals(businessAccountName)) {
					sf.seleU.hardwait(2000);
					sf.seleU.clickElementByJSE(sf.b2bAccountObj.accountsAllRecordsCompanyLink);
					isStatusfound = true;

				} else {
					isStatusfound = false;
				}
			}
			sf.seleU.hardwait(2000);
			sf.seleU.ScrolltoElement(sf.b2bAccountObj.accountDetailTagText);

			// validate Account search
			if (isStatusfound) {
				reportStatusPass(methodName + " Account is be created in R4B and synced into B2B successfully." + businessAccountName
						+ "was found successfully", true, true);
			} else {
				reportStatusFail(methodName + " Account synced in R4B and  B2B is unsuccessfull.", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Searching an business account is Unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * Flip the status of the business account from active to Inactive in account
	 * details page
	 * 
	 * @throws IOException
	 */

	public void flipActiveStatus() throws IOException {

		try {
			String methodName = "SFDC_Flip Account Status@: ";

			sf.seleU.ScrolltoElement(sf.b2bAccountObj.accountDetailTagText);
			sf.seleU.hardwait(2000);

			// Double click the account status dropdown to edit
			sf.seleU.doubleclickElementByJSE(sf.b2bAccountObj.accountStatusTextBox.get(0));

			reportStatusPass(methodName + "Double clicked on the Employee Successfully ", true, false);

			// changing the account status to Inactive
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.b2bAccountObj.accountStatusDropdown);
			sf.seleU.selectTextFromDropDown(sf.b2bAccountObj.accountStatusDropdown, sf.dataInput.b2b_status_Inactive);
			sf.seleU.hardwait(2000);

			// Click on save button after changing the status
			sf.seleU.clickElementByJSE(sf.b2bAccountObj.accDetailsSaveButton);
			sf.seleU.hardwait(2000);
			sf.seleU.ScrolltoElement(sf.b2bAccountObj.accountDetailTagText);
			sf.seleU.hardwait(2000);

			reportStatusPass(
					methodName + " Fliping the account status is successfull " + sf.dataInput.b2b_status_Inactive, true,
					false);

		} catch (Throwable e) {
			reportStatusFail(" Fliping the account status is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * Verify the business account phone number in Account details page
	 * 
	 * @throws IOException
	 */
	public void verifyAccountPhoneNumber(String contactNumber) throws IOException {
		try {
			String methodName = "SFDC_Verify Account Phone@: ";

			// Verifying the contact phone number
			sf.seleU.hardwait(2000);
			sf.seleU.ScrolltoElement(sf.b2bAccountObj.accDetailsPhoneTextBox);
			sf.seleU.hardwait(2000);

			verifyFieldValue("Contact Phone Number", sf.b2bAccountObj.accDetailsPhoneTextBox, contactNumber);

		} catch (Throwable e) {
			reportStatusFail(" Verying the phone number was unsuccessfull", e);
			e.printStackTrace();
		}

	}

	/**
	 * Verify the business account Emp size in Account details page
	 * 
	 * @throws IOException
	 */
	public void verifyEmployeeSize(String empSize) throws IOException {
		try {
			String methodName = "SFDC_Verify Account Emp Size@: ";

			// Verifying the contact phone number
			sf.seleU.hardwait(2000);
			sf.seleU.ScrolltoElement(sf.b2bAccountObj.r4BEmpSizeTextBox);
			sf.seleU.hardwait(4000);

			verifyFieldValue("Employees size", sf.b2bAccountObj.r4BEmpSizeTextBox, empSize);

		} catch (Throwable e) {
			reportStatusFail(" Verying the Emp Size was unsuccessfull", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     1. Double edit for the the No of emp in Account details
	 * 
	 *                     2. Update Employee Size
	 * 
	 */
	public void selectChangeEmployeeSize(String empSize) throws IOException {
		try {

			SFDC_AllPages sfdc = new SFDC_AllPages();
			String methodName = "SFDC_change Emp Size@: ";

			sf.seleU.ScrolltoElement(sf.b2bAccountObj.accountDetailTagText);
			sf.seleU.hardwait(2000);

			// Double click the emp field to edit
			sf.seleU.doubleclickElementByJSE(sf.b2bAccountObj.editR4bEmpTextBox);
			reportStatusPass(methodName + "Double clicked on the Employee Successfully ", true, false);

			// Enter the Updated the no of Emp size and click save
			sf.seleU.hardwait(2000);
			sf.seleU.clearAndEnterText(sf.b2bAccountObj.accDetailsEnterR4bEmp, empSize);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.b2bAccountObj.accDetailsSaveButton);
			sf.seleU.hardwait(2000);
			sf.seleU.ScrolltoElement(sf.b2bAccountObj.accountDetailTagText);
			sf.seleU.hardwait(2000);

			reportStatusPass(methodName + " Updating the emp size as " + empSize + " was successfull", true, false);
			sf.seleU.hardwait(10000);
			sf.seleU.refreshPage();
		} catch (Throwable e) {
			reportStatusFail(" Selecting Change Employee Size was unsuccessful", e);
			e.printStackTrace();
		}
	}

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
	
	/**
	 * Verify the R4B Rogers business ID in Account details page
	 * 
	 * @throws IOException
	 */
	public void verifyR4BExternalField(String rogersBusID) throws IOException {
		try {
			String methodName = "SFDC_Verify R4B Rogers Bus ID@: ";

			sf.seleU.hardwait(15000);
			sf.seleU.ScrolltoElement(sf.b2bAccountObj.r4B_B2B_Field);
			sf.seleU.hardwait(4000);
			verifyFieldValue("R4B Rogers Business ID", sf.b2bAccountObj.r4B_B2B_Field, rogersBusID);
		} catch (Throwable e) {
			reportStatusFail(" Verying the Emp Size was unsuccessfull", e);
			e.printStackTrace();
		}

	}


}
