package sfdc.pages.sales;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Pankaj.Agarwal, Date : 19/12/2020
 *
 *         B2B contact creation and verify contact details
 */
public class SFDC_b2bContact_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_b2bContact_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * Create the contact for the new account
	 * 
	 * @throws IOException
	 */

	public void createNewContact() throws IOException {

		try {

			String methodName = "SFDC_Create Contact@: ";

			// 1-Click New Contact Button
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.b2bContactObj.newContactButton);
			reportStatusPass(methodName + " Clicked New Contact Navigation Button from Accounts Details Page", true,
					false);

		} catch (Throwable e) {
			reportStatusFail(" Creating new contact is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * Enter the contact details and click the save button
	 * 
	 * @throws IOException
	 */

	public void enterContactDetails() throws IOException {

		try {

			String methodName = "SFDC_Enter Contact Details@: ";

			// 1-Enter Contact First Name
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.b2bContactObj.contactFirstNameInput, sf.dataInput.contactFirstName);
			// sf.seleU.hardwait(2000);
			reportStatusPass(methodName + " Entered Contact First Name  " + sf.dataInput.contactFirstName, true, false);
			sf.seleU.hardwait(2000);

			// 1-Enter Contact phone number
			sf.seleU.enterText(sf.b2bContactObj.contactPhoneInput, sf.dataInput.phoneNumber);
			// sf.seleU.hardwait(2000);
			reportStatusPass(methodName + " Entered Contact phone number " + sf.dataInput.phoneNumber, true, false);
			sf.seleU.hardwait(2000);

			// 1-Enter last name
			sf.seleU.enterText(sf.b2bContactObj.contactLastNameInput, sf.dataInput.contactLastName);
			// sf.seleU.hardwait(2000);
			reportStatusPass(methodName + " Entered Contact Last Name  " + sf.dataInput.contactLastName, true, false);
			sf.seleU.hardwait(2000);
			
			// 1-Enter Email
			sf.seleU.enterText(sf.b2bContactObj.emailInput, sf.dataInput.contactEmailAddress);
			// sf.seleU.hardwait(2000);
			reportStatusPass(methodName + " Entered Contact Email  " + sf.dataInput.contactEmailAddress, true, false);
			sf.seleU.hardwait(2000);

			// 2- Select Contact Type
			sf.seleU.selectTextFromDropDown(sf.b2bContactObj.contactTypeDropdownOption, sf.dataInput.b2b_contType);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + " Selected Contact Type as " + sf.dataInput.b2b_contType, true, false);

			// 3- Select Language Preference
			sf.seleU.selectTextFromDropDown(sf.b2bContactObj.languagePreferenceDropdownOption,
					sf.dataInput.contactLanguage);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "Selected Language Preference as " + sf.dataInput.contactLanguage, true,
					false);

			// 3- Click on Save Button
			sf.seleU.clickElementByJSE(sf.b2bContactObj.contactSaveButton);
			sf.seleU.hardwait(4000);
			reportStatusPass(methodName + " Clicked on Contact Save Button", true, false);

		} catch (Throwable e) {
			reportStatusFail(" Invalid Entry for New Contact Creation", e);
			e.printStackTrace();
		}
	}

	/**
	 * Verify the new contact created by verifying the contact name in the contact
	 * details page
	 * 
	 * 
	 * @throws IOException
	 */
	public void verifyContactCreated() throws IOException {
		try {

			String methodName = "SFDC_Verify Contact Created@: ";

			// if contact text tag is present and contact name is matched with the actual
			// contact name
			if (sf.seleU.isElementPresent(sf.b2bContactObj.contactDetailsTagText)) {

				if (sf.seleU.getTextFromWebElement(sf.b2bContactObj.contactDetailsNameText)
						.contains(sf.dataInput.contactFirstName)) {

					reportStatusPass(methodName + "Contact Created succesfully and has been validated", true, true);
				} else {
					reportStatusFail("Invalid Contact Creation", true);
				}
			}
			sf.seleU.ScrolltoElement(sf.b2bContactObj.contactDetailsTagText);

		} catch (Throwable e) {
			reportStatusFail(" Verifying Contact Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * Search Contact from search bar in Page If found click on that contact
	 * 
	 * @throws IOException
	 */
	public void searchContactCreated(String contactName) throws IOException {
		try {
			String methodName = "SFDC_search Contact Created@: ";
			boolean isStatusfound = true;

			sf.seleU.hardwait(2000);
			// Enter the contact name to be searched
			sf.seleU.clearAndEnterText(sf.b2bAccountObj.searchEnterInput, contactName);
			reportStatusPass(methodName + "Contact name entered as " + contactName, true, false);
			sf.seleU.hardwait(2000);

			// click on search button
			sf.seleU.clickElementByJSE(sf.b2bAccountObj.searchButton);
			reportStatusPass(methodName + "Clicked on search button", true, false);

			// if the list size is greater then 1 and contact name is matched with actual
			// contact name
			if (sf.b2bContactObj.contactNameTextAllRecordLink.size() >= 1) {

				for (int i = 0; i < sf.b2bContactObj.contactNameTextAllRecordLink.size(); i++) {

					if (sf.seleU.getTextFromWebElement(sf.b2bContactObj.contactNameTextAllRecordLink.get(i)).trim()
							.contains(contactName)) {
						sf.seleU.clickElementByJSE(sf.b2bContactObj.contactNameTextAllRecordLink.get(i));
						reportStatusPass(methodName + "Clicked on the serached contact successfully", true, false);
						isStatusfound = true;
						break;
					} else {
						isStatusfound = false;
					}
				}
			}
			// validate Contact search
			if (isStatusfound) {
				reportStatusPass(
						methodName + " Contact search results are valid and the contact was found successfully", true,
						false);
			} else {
				reportStatusFail(methodName + " Contact search results are Invalid", true);
			}

			sf.seleU.ScrolltoElement(sf.b2bAccountObj.searchButton);

		} catch (Throwable e) {
			reportStatusFail(" Searching the contact was Unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * Edit contact phone number and click save button
	 *
	 * @throws IOException
	 */
	public void editContactPhoneNumber(String contactNumber) throws IOException {
		try {
			String methodName = "SFDC_edit Contact PhoneNumber@: ";

			// Verifying the contact phone number
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.b2bContactObj.editButton);
			reportStatusPass(methodName + "Clicked on the edit contact successfully", true, false);

			// Enter the updated contact phone number
			sf.seleU.hardwait(2000);
			sf.seleU.clearAndEnterText(sf.b2bContactObj.contactDetailsPhoneEditInput, contactNumber);
			reportStatusPass(methodName + "Entered the updated contact phone number successfully " + contactNumber,
					true, false);

			// Click the save button
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.b2bContactObj.contactDetailsEditSaveButton);
			reportStatusPass(methodName + "Clicked on Save Button successfully", true, false);
			sf.seleU.ScrolltoElement(sf.b2bContactObj.editButton);
			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail("Edit the phone number field was unsuccessfull", e);
			e.printStackTrace();
		}

	}

	/**
	 * Verify contact phone number in contact details page
	 * 
	 * @throws IOException
	 */
	public void verifyContactPhoneNumber(String contactNumber) throws IOException {
		try {
			String methodName = "SFDC_verify Contact PhoneNumber@: ";

			// Verifying the contact phone number
			sf.seleU.hardwait(3000);
			verifyFieldValue("Contact Phone Number", sf.b2bContactObj.contactDetailsTextPhoneNum, contactNumber);

		} catch (Throwable e) {
			reportStatusFail(" Verying the phone number was unsuccessfull", e);
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

}
