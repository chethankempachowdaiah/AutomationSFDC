package sfdc.pages.service;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 15/JULY/2020
 * 
 *         Contact>Enable Community User
 *
 */

public class SFDC_EnableCommunityUser_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public SFDC_AllPageObjects sf;

	public SFDC_EnableCommunityUser_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Enable Community user Access
	 * 
	 *                     Verify Community Contact
	 * 
	 *                     Create New Support Group
	 * 
	 *                     Enter Support Group Details
	 * 
	 *                     Click on Save Button
	 * 
	 *                     Click On next Button
	 * 
	 *                     Verify User Creation Message and click on Next button
	 */
	public void enableCommunityUser() throws IOException {
		try {

			String methodName = "SFDC_Enable Community User@: ";

			// Click on Enable Community user Access
			sf.seleU.clickElementByJSE(sf.cd.enableCommunityUserButton);
			reportStatusPass(methodName + "Clicked On Enable Community User Button", true, false);
			sf.seleU.wait(7000);
			sf.seleU.switchToFrame(sf.ecu.EnableCommunityFrame);

			// Verify Community Contact
			verifyFieldValue("Contact First Name", sf.ecu.contactFirstNameValue, sf.dataInput.contactFirstName);
			verifyFieldValue("Contact last Name", sf.ecu.contactLastNameValue, sf.dataInput.contactLastName);
			verifyFieldValue("Contact Email ", sf.ecu.contactEmailValue, sf.dataInput.contactEmailAddress);
			verifyFieldValue("Contact Preferred Language ", sf.ecu.contactPreferredLanguageValue,
					sf.dataInput.contactLanguage);

			// Support group functionality deprecated from community enablement
			/*
			 * // Create New Support Group
			 * sf.seleU.clickOnElement(sf.ecu.supportDetailsNewButton);
			 * reportStatusPass(methodName + "Clicked On New Service Group Button", true,
			 * false); sf.seleU.wait(3000);
			 * 
			 * // Enter Support Group Details
			 * sf.seleU.enterText(sf.ecu.supportGroupNameInput,
			 * sf.dataInput.supportGroupName);
			 * sf.seleU.enterText(sf.ecu.supportGroupEmailInput,
			 * sf.dataInput.supportGroupEmail);
			 * sf.seleU.enterText(sf.ecu.suppportGroupPhoneInput,
			 * sf.dataInput.supportGroupPhone);
			 * sf.seleU.enterText(sf.ecu.suppportGroupPhoneInput, Keys.TAB);
			 * sf.seleU.selectValueFromDropDown(sf.ecu.productSupportedDropdownDropdown,
			 * sf.dataInput.suuportGroupProductsSupported);
			 * 
			 * reportStatusPass(methodName + "Entered Support Group Details", true, false);
			 * sf.seleU.wait(3000);
			 * 
			 * // Click on Save Button sf.seleU.clickElementByJSE(sf.ecu.saveButton);
			 * reportStatusPass(methodName + "Clicked On Save Button", true, false);
			 * sf.seleU.wait(5000); verifyFieldDisplayed("Support Group Details",
			 * sf.ecu.supportGroupDetails);
			 */
			// Click On next Button
			sf.seleU.clickElementByJSE(sf.ecu.createUserNextButton);
			reportStatusPass(methodName + "Clicked On Next Button", true, false);
			sf.seleU.wait(8000);

			/*
			 * // Verify User Creation Message and click on Next button
			 * verifyFieldDisplayed("User Creation Message", sf.ecu.userCreatedSuccessMsg);
			 * sf.seleU.clickElementByJSE(sf.ecu.reviewUserNextButton);
			 * reportStatusPass(methodName + "Clicked On Next Button", true, false);
			 * sf.seleU.wait(5000);
			 */

		} catch (Throwable e) {
			reportStatusFail(" Enabling Community User is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Enable Community user Access
	 * 
	 *                     Verify Community User Fields and Community user exits
	 *                     message
	 * 
	 *                     Click On next Button
	 * 
	 */
	public void verifyCommunityUserExists() throws IOException {
		try {

			String methodName = "SFDC_Enable Community User@: ";

			// Click on Enable Community user Access
			sf.seleU.switchToDefaultContent();
			sf.seleU.clickElementByJSE(sf.cd.enableCommunityUserButton);
			reportStatusPass(methodName + "Clicked On Enable Community User Button", true, false);
			sf.seleU.wait(5000);
			sf.seleU.switchToFrame(sf.ecu.EnableCommunityFrame);

			// Verify Community User Fields and Community user exits message
			verifyFieldValue("Contact First Name", sf.ecu.contactFirstNameValue, sf.dataInput.contactFirstName);
			verifyFieldValue("Contact last Name", sf.ecu.contactLastNameValue, sf.dataInput.contactLastName);
			verifyFieldValue("Contact Email ", sf.ecu.contactEmailValue, sf.dataInput.contactEmailAddress);
			verifyFieldValue("Contact Preferred Language ", sf.ecu.contactPreferredLanguageValue,
					sf.dataInput.contactLanguage);

			// verifyFieldDisplayed("Support Group Details", sf.ecu.supportGroupDetails);
			verifyFieldDisplayed("Community User Exists", sf.ecu.communityUserAlreadyExistsMsg);

			// Click On next Button
			sf.seleU.clickElementByJSE(sf.ecu.createUserNextButton);
			reportStatusPass(methodName + "Clicked On Next Button", true, false);
			sf.seleU.wait(6000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Verifying Existing community user", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                     Click on Detail Tab	  
	 *                     
	 *                     Verify Community User Id Fields is empty
	 *                                          
	 * 
	 *                    
	 * 
	 */
	public void verifyCommunityUserAccess() throws IOException {
		try {

			String methodName = "SFDC_Enable Community User@: ";

			//click on related tab
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.cd.relatedTab.get(0));
			reportStatusPass(methodName + " Clicked on related tab ", true, false);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.cd.detailsTab.get(0));
			reportStatusPass(methodName + " Clicked on details tab ", true, false);
			sf.seleU.hardwait(2000);
			verifyFieldValue("User ID", sf.cd.useridValue, InputData.prospect_UsrValue);
			reportStatusPass(methodName + " Created SA contact can't be the community user for prospect account", true, false);
			
		} catch (Throwable e) {
			reportStatusFail(" Error in Verifying community user access", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Troubleshoot User
	 * 
	 *                     Verify Community Contact and Activation Time Out Msg
	 * 
	 *                     Click On next Button
	 * 
	 */
	public void troubleshootUser() throws IOException {
		try {

			String methodName = "SFDC_Troubleshoot User@: ";

			// Click on Troubleshoot User
			sf.seleU.switchToDefaultContent();
			sf.seleU.clickElementByJSE(sf.cd.troubleshootUserButton);
			reportStatusPass(methodName + "Clicked On Troubleshoot User Button", true, false);
			sf.seleU.wait(10000);
			sf.seleU.switchToFrame(sf.ecu.EnableCommunityFrame);

			// Verify Community Contact and Activation Time Out Msg
			verifyFieldValue("Contact First Name", sf.ecu.contactFirstNameValue, sf.dataInput.contactFirstName);
			verifyFieldValue("Contact last Name", sf.ecu.contactLastNameValue, sf.dataInput.contactLastName);
			verifyFieldValue("Contact Email ", sf.ecu.contactEmailValue, sf.dataInput.contactEmailAddress);
			verifyFieldValue("Contact Preferred Language ", sf.ecu.contactPreferredLanguageValue,
					sf.dataInput.contactLanguage);
			verifyFieldDisplayed("Community User GUID", sf.ecu.commuserGUID);

			// Click On next Button
			sf.seleU.clickElementByJSE(sf.ecu.createUserNextButton);
			reportStatusPass(methodName + "Clicked On Next Button", true, false);
			sf.seleU.wait(6000);

			verifyFieldDisplayed("Activation Time Out Msg", sf.ecu.activationTimeOutMsg);

			// Click On next Button
			sf.seleU.clickElementByJSE(sf.ecu.getUserStatusNextButton);
			reportStatusPass(methodName + "Clicked On Next Button", true, false);
			sf.seleU.wait(6000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Verifying Troubleshoot user", e);
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
			if (sf.seleU.getTextFromWebElement(element).equals(expectedText)) {
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
}
