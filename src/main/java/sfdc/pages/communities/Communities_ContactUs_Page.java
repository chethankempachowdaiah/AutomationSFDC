package sfdc.pages.communities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.lib_pages.SFDC_Files_Page;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date:10/06/2020
 *
 *         Communities> Contact Us page objects
 */
public class Communities_ContactUs_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static Communities_MyBusinessCases_Page comPage;
	public static SFDC_Files_Page filesPage;
	public static String methodName;

	public Communities_ContactUs_Page() {
		sf = new SFDC_AllPageObjects();
		comPage = new Communities_MyBusinessCases_Page();
		filesPage = new SFDC_Files_Page();
		methodName = "Communities_ContactUs@:";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on sign-In Link in Communities page
	 * 
	 *                     Enter Credentials for communities Login
	 * 
	 *                     Click on sign-In Button
	 * 
	 */
	public void createCaseViaWebEmail(Hashtable<String, String> dataTable) throws IOException {
		try {

			if (!sf.seleU.isElementPresent(sf.comContactUs.emailButton)) {

				// Click on Contact Us in Footer Link
				sf.seleU.clickElementByJSE(sf.comHome.footerContactUsLink);
				reportStatusPass(methodName + " Clicked on Contact Us in Footer Link", true, false);
				sf.seleU.switchWindow(2);
				sf.seleU.wait(4000);
			}

			// Click on Email Button
			sf.seleU.clickElementByJSE(sf.comContactUs.emailButton);
			reportStatusPass(methodName + " Clicked on Email Button", true, false);
			sf.seleU.wait(2000);

			// Select Case Account Name
			sf.seleU.clickOnElement(sf.comContactUs.accountNameDropdown);
			selectDropdownOption(dataTable.get("Account_Name"));
			reportStatusPass(methodName + " Selected Account as " + dataTable.get("Account_Name"), true, false);

			// Select Case Reason
			sf.seleU.clickOnElement(sf.comCaseDetails.reasonDropdown);
			selectDropdownOption(dataTable.get("Case_Reason"));
			reportStatusPass(methodName + " Selected Case Reason as " + dataTable.get("Case_Reason"), true, false);

			// Select Case Product
			sf.seleU.clickOnElement(sf.comCaseDetails.productDropdown);
			selectDropdownOption(dataTable.get("Case_Product"));
			reportStatusPass(methodName + " Selected Case Product as " + dataTable.get("Case_Product"), true, false);

			// Enter Case Subject and Description
			sf.seleU.clearAndEnterText(sf.comCaseDetails.subjectInput, sf.dataInput.caseStatusNew + addOn_1);
			sf.seleU.clearAndEnterText(sf.comCaseDetails.descriptionInput, sf.dataInput.caseStatusNew + addOn_1);
			sf.seleU.enterText(sf.comCaseDetails.descriptionInput, Keys.TAB);
			reportStatusPass(
					methodName + " Entered Case Subject and Description as " + sf.dataInput.caseStatusNew + addOn_1,
					true, false);

			// Click on Submit Button
			sf.seleU.clickElementByJSE(sf.comCaseDetails.submitButton);
			reportStatusPass(methodName + " Clicked on Submit Button", true, false);
			sf.seleU.wait(4000);

			// Verify Case Creation in Web to Case Channel
			if (sf.seleU.isElementDisplayed(sf.comContactUs.thankYouMsgHeader)
					&& sf.seleU.isElementDisplayed(sf.comContactUs.thankYouMsg)) {
				reportStatusPass(methodName + "Created case in Web to Case Channel", true, true);
			} else {
				reportStatusFail(methodName + " Unsuccessful Case Creation in Web to Case Channel", true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + "Creating Web to case is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Contact Us in Footer Link
	 * 
	 *                     Verify 3 main tabs work as expected (Email, Chat, Phone)
	 * 
	 */
	public void verifyCommunities3MainTabs() throws IOException {

		try {

			// Click on Contact Us in Footer Link
			sf.seleU.clickElementByJSE(sf.comHome.footerContactUsLink);
			reportStatusPass(methodName + " Clicked on Contact Us in Footer Link", true, false);
			sf.seleU.switchWindow(3);
			sf.seleU.wait(4000);

			// Verify 3 main tabs work as expected (Email, Chat, Phone)
			verifyFieldDisplayed("contact Us Header", sf.comContactUs.contactUsTextHeader);
			verifyFieldDisplayed("email Button", sf.comContactUs.emailButton);
			verifyFieldDisplayed("phone Button", sf.comContactUs.phoneButton);
			verifyFieldDisplayed("chat Button", sf.comContactUs.chatButton);

			// Verify Phone Sections
			sf.seleU.clickElementByJSE(sf.comContactUs.phoneButton);
			sf.seleU.wait(2000);

			verifyFieldDisplayed("For Wireless Solutions Header", sf.comContactUs.forWirelessSolutionsHeader);
			verifyFieldDisplayed("Wireless Solution Phone", sf.comContactUs.wirelessSolutionPhone);
			verifyFieldDisplayed("For Technical Support Header", sf.comContactUs.forTechnicalSupportHeader);
			verifyFieldDisplayed("Tech Support Phone", sf.comContactUs.techSupportPhone);
			verifyFieldDisplayed("Wireless Solution Hours", sf.comContactUs.wirelessSolutionHours);
			verifyFieldDisplayed("Outside Canada Call ", sf.comContactUs.outsideCanadaCall.get(0));
			verifyFieldDisplayed("Hours Of Operation 24By7", sf.comContactUs.hoursOfOperation24By7);
			verifyFieldDisplayed("Outside Canada Call Tech Support", sf.comContactUs.outsideCanadaCall.get(1));

			// Verify Email Section
			sf.seleU.clickElementByJSE(sf.comContactUs.emailButton);
			sf.seleU.wait(2000);

			verifyFieldDisplayed("Account Name Dropdown", sf.comContactUs.accountNameDropdown);
			verifyFieldDisplayed("Reason Dropdown", sf.comCaseDetails.reasonDropdown);
			verifyFieldDisplayed("Product Dropdown", sf.comCaseDetails.productDropdown);
			verifyFieldDisplayed("Subject Input", sf.comCaseDetails.subjectInput);
			verifyFieldDisplayed("Description Input", sf.comCaseDetails.descriptionInput);
			verifyFieldDisplayed("Submit Button", sf.comCaseDetails.submitButton);

			// Verify Chat Section
			sf.seleU.clickElementByJSE(sf.comContactUs.chatButton);
			sf.seleU.wait(2000);

			verifyFieldDisplayed("Chat Hours Of Operation", sf.comContactUs.chatHoursOfOperation);
			verifyFieldDisplayed("Chat If You Have Account Inquiry Text",
					sf.comContactUs.chatIfYouHaveAccountInquiryText);
			verifyFieldDisplayed("Agent Offline Button", sf.comContactUs.agentOfflineButton);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Invalid communities tab", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Phone Tab and its service groups
	 * 
	 */
	public void verifyPhoneServiceGroups() throws IOException {
		try {

			// sf.seleU.hardwait(4000);
			// driver.get(sf.commData.communities_url);
			// sf.seleU.wait(10000);

			// Verify Contact Us Footer Link
			verifyFieldDisplayed("Footer Contact Us Link", sf.comHome.footerContactUsLink);
			sf.seleU.clickElementByJSE(sf.comHome.footerContactUsLink);
			sf.seleU.wait(10000);
			sf.seleU.switchWindow(2);
			sf.seleU.wait(5000);
			verifyFieldDisplayed("Contact Us Header", sf.comContactUs.contactUsTextHeader);

			// Verify service Groups under Phone Button
			sf.seleU.clickOnElement(sf.comContactUs.phoneButton);
			sf.seleU.wait(2000);
			verifyFieldDisplayed("'For Wireless, Cable Internet and TV'",
					sf.comContactUs.contactByPhoneForWirelessCIAndTV);
			verifyFieldDisplayed("'For Wireline'", sf.comContactUs.contactByPhoneForWireline);
			verifyFieldDisplayed("'For Data Centre and Cloud'", sf.comContactUs.contactByPhoneForDataCentreAndCloud);
			verifyFieldDisplayed("'For Technical Support'", sf.comContactUs.forTechnicalSupportHeader);

			// Verify Phone numbers
			verifyFieldValue("'For Wireless, Cable Internet and TV Phone number'",
					sf.comContactUs.contactByPhoneForWirelessCIAndTVValue, "1 877 274 3375");
			verifyFieldValue("'For Wireless, Cable Internet and TV Outside of Canada Phone number'",
					sf.comContactUs.contactByPhoneOutsideCanadaForWirelessCIAndTV, "Call 1-524-734-7699");
			verifyFieldValue("'For Wireline Phone number'", sf.comContactUs.contactByPhoneForWirelineValue,
					"1 800 496 4401");
			verifyFieldValue("'For Data Centre and Cloud Phone number'",
					sf.comContactUs.contactByPhoneForDataCentreAndCloudValue, "1 800 958 5000");
			verifyFieldValue("'For Technical Support Phone number'", sf.comContactUs.techSupportPhone,
					"1 866 727 2141");

			// Verify Hours of Operations
			verifyFieldValue("'For Technical Support , Hours Of Operation :'",
					sf.comContactUs.hoursOfOperationForTechnicalSupport, "24/7");
			verifyFieldValue("'For Wireless, Cable Internet and TV , Hours Of Operation :'",
					sf.comContactUs.hoursOfOperationForWirelessCIAndTV, "8:30 AM to 5:00 PM (ET)");
			verifyFieldValue("'For Wireline , Hours Of Operation :'", sf.comContactUs.hoursOfOperationForWireline,
					"8:30 AM to 5:00 PM (ET)");
			verifyFieldValue("'For Data Centre and Cloud , Hours Of Operation :'",
					sf.comContactUs.hoursOfOperationForDataCentreAndCloud, "8:30 AM to 5:00 PM (ET)");


		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying Phone tab and its service groups unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Email Tab and its form layout
	 * 
	 *                     Create case through this email tab and validate case was
	 *                     created successfully
	 * 
	 */
	public void verifyEmailTab() throws IOException {
		try {
			// Verify service Groups under Phone Button
			sf.seleU.clickOnElement(sf.comContactUs.emailButton);
			sf.seleU.wait(2000);
			reportStatusPass(methodName + " Opened Email Us Tab in COntact Us Page", true, true);
			
			//**********************Obselete Validations///////
//			// Validate form layout--- 
//			verifyFieldDisplayed("Account Information Section", sf.comContactUs.accountInformationSection);
//			verifyFieldDisplayed("Product Information Section", sf.comContactUs.productInformationSection);
//			verifyFieldDisplayed("Add Any Additional Information Section",
//					sf.comContactUs.addAnyAdditionalInformationSection);
//			verifyFieldDisplayed("Add Attachments Section", sf.comContactUs.addAttachmentsSection);
//
//			// Validate Reasons dropdown is not populated until product is selected
//			sf.seleU.clickOnElement(sf.comCaseDetails.reasonDropdown);
//			verifyFieldNotDisplayed("Reasons dopdown values", sf.comCaseDetails.dropdownOptions.get(0));
			
//			// Verify AccountName, Name, Product, Reason, Subject, Description
//			verifyFieldDisplayed("Account Name dropdown", sf.comContactUs.accountNameDropdown);
//			verifyFieldDisplayed("Product dropdown", sf.comCaseDetails.productDropdown);
//			verifyFieldDisplayed("Reason dropdown", sf.comCaseDetails.reasonDropdown);
//			verifyFieldDisplayed("Subject textfield", sf.comCaseDetails.subjectInput);
//			verifyFieldDisplayed("Description textarea", sf.comCaseDetails.descriptionInput);
			
			//**********************Obselete Validations///////
			
			// Call function to fill case creation form
			comPage.createServiceCaseDataCentreOthers(false);
			
			// Verify Case Creation in Web to Case Channel
			if (sf.seleU.isElementDisplayed(sf.comCaseDetails.caseNumberOnCaseCreation)
					&& sf.seleU.isElementDisplayed(sf.comCaseDetails.caseCreatedSuccessMessage)) {
				reportStatusPass(methodName + "Created case in Web to Case Channel", true, true);
			} else {
				reportStatusFail(methodName + " Unsuccessful Case Creation in Web to Case Channel", true);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying Email tab and its form layout unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Chat tab
	 */
	public void verifyChatTab() throws IOException {
		try {
			// Validate chat section
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.comContactUs.chatButton);
			sf.seleU.wait(2000);

			verifyFieldDisplayed("Chat Hours Of Operation", sf.comContactUs.chatHoursOfOperation);
			verifyFieldDisplayed("Chat If You Have Account Inquiry Text",
					sf.comContactUs.chatIfYouHaveAccountInquiryText);
			verifyFieldDisplayed("Agent Offline Button", sf.comContactUs.agentOfflineButton);
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
	 *                     Verify field is not displayed
	 */
	public static void verifyFieldNotDisplayed(String fieldName, WebElement element) throws IOException {

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
	 *                     Verify Field is displayed
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field value matches the expected result
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
	 * @param value
	 * @throws Exception
	 * 
	 *                   Select value from case creation dropdown option
	 */
	public void selectDropdownOption(String value) throws Exception {

		sf.seleU.wait(3000);

		for (int i = 0; i < sf.comCaseDetails.dropdownOptions.size(); i++) {

			if (sf.seleU.isElementDisplayed(sf.comCaseDetails.dropdownOptions.get(i))
					&& sf.seleU.getTextFromWebElement(sf.comCaseDetails.dropdownOptions.get(i)).equals(value)) {
				sf.seleU.clickElementByJSE(sf.comCaseDetails.dropdownOptions.get(i));
			}

		}
	}

}
