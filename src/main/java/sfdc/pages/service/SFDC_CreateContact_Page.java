package sfdc.pages.service;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.FetchTestData;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Service;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.lib_pages.SFDC_Files_Page;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 10/jan/2020
 * 
 *         SFDC Create Contact
 *
 */
public class SFDC_CreateContact_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_CreateContact_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     1- Enter Salutation and Title
	 * 
	 *                     2- Enter First Name and Last Name
	 * 
	 *                     3- Enter Email Address and phone Number
	 * 
	 *                     4- Select Relationship type
	 * 
	 *                     5- Enter Mailing Address Information
	 * 
	 * 
	 * 
	 */
	public void enterCreateContactInfo(boolean isSiteContact) throws IOException {
		try {

			String methodName = "SFDC_Create Contact@: ";

			// 1- Enter Salutation and Title
			sf.seleU.selectTextFromDropDown(sf.cc.contactSalutationDropdown.get(0), Global.dataInput.contactSalutation);
			sf.seleU.clearAndEnterText(sf.cc.contactTitleInput, Global.dataInput.contactTitle);
			reportStatusPass(methodName + " Entered contact Saluation and Title As:"
					+ Global.dataInput.contactSalutation + " " + Global.dataInput.contactTitle, true, false);

			// 2- Enter First Name and Last Name
			sf.seleU.enterText(sf.cc.contactFirstNameInput, Global.dataInput.contactFirstName);
			reportStatusPass(methodName + " Entered contact First Name as : " + Global.dataInput.contactFirstName, true,
					false);
			sf.seleU.enterText(sf.cc.contactLastNameInput, Global.dataInput.contactLastName);
			reportStatusPass(methodName + " Entered contact Last Name as : " + Global.dataInput.contactLastName, true,
					false);
			
			Global.dataInput.contactName = ((Global.dataInput.contactFirstName).concat(" ").concat(Global.dataInput.contactLastName));
			
			// 3- Enter Email Address and phone Number
			sf.seleU.enterText(sf.cc.contactEmailAddressInput, Global.dataInput.contactEmailAddress);
			reportStatusPass(methodName + " Entered contact Email Address as : " + Global.dataInput.contactEmailAddress,
					true, false);

			// To use this email id for generating duplicate contacts scenario
			// sf.dataInput.emailIdValue = sf.dataInput.contactEmailAddress;
			Global.dataInput.duplicateEmailAddress = Global.dataInput.contactEmailAddress;

			sf.seleU.enterText(sf.cc.contactPhoneInput, Global.dataInput.phoneNumber);
			reportStatusPass(methodName + " Entered contact Phone Number as : " + Global.dataInput.phoneNumber, true,
					false);

			// 4- Select Relationship type
			/*
			 * if (sf.seleU.isElementPresent(sf.cc.contactRelationshipTypeDropdown)) { if
			 * (isSiteContact) {
			 * sf.seleU.selectTextFromDropDown(sf.cc.contactRelationshipTypeDropdown,
			 * sf.dataInput.siteContact); reportStatusPass(methodName +
			 * " Selected Relationship type as : " + sf.dataInput.siteContact, true, false);
			 * } else {
			 * sf.seleU.selectTextFromDropDown(sf.cc.contactRelationshipTypeDropdown,
			 * sf.dataInput.contactRelationshipType); reportStatusPass( methodName +
			 * " Selected Relationship type as : " + sf.dataInput.contactRelationshipType,
			 * true, false); } }
			 */

			// Mp13 changes

//			if (sf.seleU.isElementDisplayed(sf.cc.businessContactRadio)) {
//
//				// Search Account
//				String accountToBeSearched;
//
//				if (isSiteContact) {
//					sf.seleU.clickElementByJSE(sf.cc.siteContactRadio);
//					sf.seleU.hardwait(1000);
//					sf.seleU.clickElementByJSE(sf.cc.contactSelectAccountInput);
//					accountToBeSearched = Global.dataInput.serviceAccountName;
//
//				} else {
//					sf.seleU.clickElementByJSE(sf.cc.businessContactRadio);
//					sf.seleU.hardwait(1000);
//					sf.seleU.clickElementByJSE(sf.cc.contactSelectAccountInput);
//
//					accountToBeSearched = sf.dataInput.account_ON;
//				}
//				sf.seleU.hardwait(2000);
//				sf.seleU.enterText(sf.cc.contactSelectAccountInput, accountToBeSearched);
//
//				sf.seleU.hardwait(6000);
//				sf.seleU.enterText(sf.cc.contactSelectAccountInput, Keys.ARROW_DOWN);
//				sf.seleU.enterText(sf.cc.contactSelectAccountInput, Keys.ENTER);
//				sf.seleU.hardwait(2000);
//				reportStatusPass(methodName + " Entered Account as " + accountToBeSearched + " to be searched for",
//						true, false);
//
//			}

			if (sf.seleU.isElementDisplayed(sf.cc.contactRelationshipTypeGeneral)) {
				sf.seleU.clickElementByJSE(sf.cc.contactRelationshipTypeGeneral);
				reportStatusPass(methodName + " Selected Relationship type as : General", true, false);
			}

			if (isSiteContact) {
				Global.dataInput.siteContactName = Global.dataInput.contactFirstName + " "
						+ Global.dataInput.contactLastName;
			}

			if (isSiteContact && sf.seleU.isElementDisplayed(sf.cc.siteContactCheckbox)) {
				sf.seleU.clickElementByJSE(sf.cc.siteContactCheckbox);
				reportStatusPass(methodName + " Selected Site Contact", true, false);
			}

			// Verify language
			if (sf.seleU.getDropDownOptions(sf.cc.languageDropdown).size() == 2
					&& sf.seleU.getDropDownOptions(sf.cc.languageDropdown).get(0).equals(InputData.langEnglish)
					&& sf.seleU.getDropDownOptions(sf.cc.languageDropdown).get(1).equals(InputData.langFrench)) {
				reportStatusPass(methodName + " Validated Contact Lanuage Options as  "
						+ sf.seleU.getDropDownOptions(sf.cc.languageDropdown), true, true);
			} else {
				reportStatusFail(methodName + " Invalid options in Contact Language "
						+ sf.seleU.getDropDownOptions(sf.cc.languageDropdown), true);
			}

			// Select Language
			sf.seleU.selectTextFromDropDown(sf.cc.languageDropdown, Global.dataInput.contactLanguage);
			reportStatusPass(methodName + " Selected Language as : " + Global.dataInput.contactLanguage, true, false);

			sf.seleU.wait(3000);

			// 5- Enter Mailing Address Information
			if (Global.dataInput.selectCanNotFindMailingAddress.equals("Yes")) {
				createContactAddress();
			} else {
				sf.seleU.enterText(sf.cc.contactMailingAddress, Global.dataInput.address);
				sf.seleU.wait(4000);
				try {
					sf.seleU.clickElementByJSE(sf.cc.mailingAddressTypeAheadOption);
				} catch (StaleElementReferenceException e) {

				}
				sf.seleU.wait(2000);
				if (sf.seleU.isElementPresent(sf.cc.mailingAddressTypeAheadOption)) {
					sf.seleU.clickElementByJSE(sf.cc.mailingAddressTypeAheadOption);
					sf.seleU.wait(2000);
				}
			}

			reportStatusPass(methodName + " Enter Mailing Address Information As : " + Global.dataInput.addressStreet,
					true, false);

			sf.seleU.wait(3000);

		} catch (

		Throwable e) {
			reportStatusFail(" Creating contact is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	public void enterCreateContactInfo(boolean isSiteContact, boolean signingAuthority) throws IOException {
		try {

			String methodName = "SFDC_Create Contact@: ";

			// 1- Enter Salutation and Title
			sf.seleU.selectTextFromDropDown(sf.cc.contactSalutationDropdown.get(0), Global.dataInput.contactSalutation);
			sf.seleU.clearAndEnterText(sf.cc.contactTitleInput, Global.dataInput.contactTitle);
			reportStatusPass(methodName + " Entered contact Saluation and Title As:"
					+ Global.dataInput.contactSalutation + " " + Global.dataInput.contactTitle, true, false);

			// 2- Enter First Name and Last Name
			sf.seleU.enterText(sf.cc.contactFirstNameInput, Global.dataInput.contactFirstName);
			reportStatusPass(methodName + " Entered contact First Name as : " + Global.dataInput.contactFirstName, true,
					false);
			sf.seleU.enterText(sf.cc.contactLastNameInput, Global.dataInput.contactLastName);
			reportStatusPass(methodName + " Entered contact Last Name as : " + Global.dataInput.contactLastName, true,
					false);
			
			Global.dataInput.contactName = ((Global.dataInput.contactFirstName).concat(" ").concat(Global.dataInput.contactLastName));
			
			// 3- Enter Email Address and phone Number
			sf.seleU.enterText(sf.cc.contactEmailAddressInput, Global.dataInput.contactEmailAddress);
			reportStatusPass(methodName + " Entered contact Email Address as : " + Global.dataInput.contactEmailAddress,
					true, false);

			// To use this email id for generating duplicate contacts scenario
			// sf.dataInput.emailIdValue = sf.dataInput.contactEmailAddress;
			Global.dataInput.duplicateEmailAddress = Global.dataInput.contactEmailAddress;

			sf.seleU.enterText(sf.cc.contactPhoneInput, Global.dataInput.phoneNumber);
			reportStatusPass(methodName + " Entered contact Phone Number as : " + Global.dataInput.phoneNumber, true,
					false);


			if(signingAuthority)
			{
				sf.seleU.clickElementByJSE(sf.cc.buttonSigningAuthority);
				reportStatusPass(methodName + "Selected Relationship type as : Signing Authority", true, false);
				sf.seleU.hardwait(4000);
			}
			
			if(!signingAuthority)
			{
		       	sf.seleU.clickElementByJSE(sf.cc.roleEndorser);
		       	sf.seleU.hardwait(3000);
		       	sf.seleU.clickElementByJSE(sf.cc.roleAssessor);
		       	sf.seleU.hardwait(3000);
		       	sf.seleU.clickElementByJSE(sf.cc.roleDecider);
		       	sf.seleU.hardwait(3000);
		       	sf.seleU.clickElementByJSE(sf.cc.roleCoachInfluencer);
		       	reportStatusPass(methodName + "Selected all RWOS roles", true, false);
		       	sf.seleU.hardwait(3000);
			}
			
			if (isSiteContact) {
				Global.dataInput.siteContactName = Global.dataInput.contactFirstName + " "
						+ Global.dataInput.contactLastName;
			}

			if (isSiteContact && sf.seleU.isElementDisplayed(sf.cc.siteContactCheckbox)) {
				sf.seleU.clickElementByJSE(sf.cc.siteContactCheckbox);
				reportStatusPass(methodName + " Selected Site Contact", true, false);
			}

			// Verify language
			if (sf.seleU.getDropDownOptions(sf.cc.languageDropdown).size() == 2
					&& sf.seleU.getDropDownOptions(sf.cc.languageDropdown).get(0).equals(InputData.langEnglish)
					&& sf.seleU.getDropDownOptions(sf.cc.languageDropdown).get(1).equals(InputData.langFrench)) {
				reportStatusPass(methodName + " Validated Contact Lanuage Options as  "
						+ sf.seleU.getDropDownOptions(sf.cc.languageDropdown), true, true);
			} else {
				reportStatusFail(methodName + " Invalid options in Contact Language "
						+ sf.seleU.getDropDownOptions(sf.cc.languageDropdown), true);
			}

			// Select Language
			sf.seleU.selectTextFromDropDown(sf.cc.languageDropdown, Global.dataInput.contactLanguage);
			reportStatusPass(methodName + " Selected Language as : " + Global.dataInput.contactLanguage, true, false);

			sf.seleU.wait(3000);

			// 5- Enter Mailing Address Information
			if (Global.dataInput.selectCanNotFindMailingAddress.equals("Yes")) {
				createContactAddress();
			} else {
				sf.seleU.enterText(sf.cc.contactMailingAddress, Global.dataInput.address);
				sf.seleU.wait(4000);
				try {
					sf.seleU.clickElementByJSE(sf.cc.mailingAddressTypeAheadOption);
				} catch (StaleElementReferenceException e) {

				}
				sf.seleU.wait(2000);
				if (sf.seleU.isElementPresent(sf.cc.mailingAddressTypeAheadOption)) {
					sf.seleU.clickElementByJSE(sf.cc.mailingAddressTypeAheadOption);
					sf.seleU.wait(2000);
				}
			}

			reportStatusPass(methodName + " Enter Mailing Address Information As : " + Global.dataInput.addressStreet,
					true, false);

			sf.seleU.wait(3000);

		} catch (

		Throwable e) {
			reportStatusFail(" Creating contact is Unsuccessful", e);
			e.printStackTrace();
		}
	}


	public void enterNewContactInfo(boolean sameBusinessAccount, boolean accessLevelBusiness) throws IOException 
	{
		try 
		{
			String methodName = "SFDC_Create Contact@: ";
						
			// 1- Enter Salutation and Title
			sf.seleU.hardwait(5000);
			sf.seleU.clickOnElement(sf.cc.salutationsDropdown);
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.cc.salutationOption.get(3));			
			sf.seleU.hardwait(6000);
			sf.seleU.clearAndEnterText(sf.cc.TitleInput, Global.dataInput.contactTitle);
			reportStatusPass(methodName + " Entered contact Saluation and Title As:"
					+ Global.dataInput.contactSalutation + " " + Global.dataInput.contactTitle, true, false);
			sf.seleU.hardwait(5000);
			
			// 2- Enter First Name and Last Name
			sf.seleU.enterText(sf.cc.FirstNameInput, Global.dataInput.contactFirstName);
			reportStatusPass(methodName + " Entered contact First Name as : " + Global.dataInput.contactFirstName, true, false);
			sf.seleU.hardwait(5000);

			sf.seleU.enterText(sf.cc.LastNameInput, Global.dataInput.contactLastName);
			reportStatusPass(methodName + " Entered contact Last Name as : " + Global.dataInput.contactLastName, true, false);
			sf.seleU.hardwait(5000);
			
			Global.dataInput.contactName = ((Global.dataInput.contactFirstName).concat(" ").concat(Global.dataInput.contactLastName));
               			
			// 3- Enter Email Address and phone Number
			sf.seleU.enterText(sf.cc.EmailAddressInput, Global.dataInput.duplicateEmailAddress);
			reportStatusPass(methodName + " Entered contact Email Address as : " + Global.dataInput.duplicateEmailAddress, true, false);
			sf.seleU.hardwait(5000);
						
			reportStatusPass(methodName + " Entered contact Phone Number as : " + Global.dataInput.phoneNumber, true, false);
			int j = 3;
			for (int i = 0; i <= 12; i = i + 3) 
			{
			  sf.seleU.enterText(sf.cc.phoneInput, Global.dataInput.phoneNumber.substring(i, j));
			  reportStatusPass(methodName + " Entered contact Phone Number as : " + Global.dataInput.phoneNumber.substring(i, j), true, false);
			  sf.seleU.clickElementByJSE(sf.cc.languageButton);
			  sf.seleU.wait(4000);
			  j = j + 3;
			}
		
			// Verify language as English selected by default			
			if(sf.seleU.isElementSelected(sf.cc.languageRadioButton) && (sf.seleU.getTextFromWebElement(sf.cc.englishLanguage).equalsIgnoreCase(InputData.langEnglish)))
			{
			  reportStatusPass(methodName + " Validated Contact Lanuage Options as  " + InputData.langEnglish, true, true);	
			}
			
			else 
			{
			  reportStatusFail(methodName + " Invalid options in Contact Language " + InputData.langEnglish, true);
			}
			
			sf.seleU.clickElementByJSE(sf.cc.nextStep);
			sf.seleU.hardwait(6000);

			// Enter New Contact Mailing Address Information
//			if (Global.dataInput.selectCanNotFindMailingAddress.equals("Yes")) {
//				createNewContactAddress();
//			} else {
			
			sf.seleU.hardwait(5000);
			sf.seleU.enterText(sf.cc.contactMailingAddress, Global.dataInput.address);
			sf.seleU.hardwait(5000);
			sf.seleU.enterText(sf.cc.contactMailingAddress, Keys.TAB);
			reportStatusPass(methodName + " Enter Mailing Address Information As : " + Global.dataInput.address, true, false);
			sf.seleU.hardwait(5000);			
			sf.seleU.scrollByCoOrdinates(2);
			sf.seleU.hardwait(3000);

			// click on product influence
			sf.seleU.clickElementByJSE(sf.cc.internetOfThingsOption);
			reportStatusPass(methodName + " Selected Internet Of Things ", true, false);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.cc.nextStep);
			sf.seleU.hardwait(5000);

			// check for duplicate contacts
			sf.seleU.hardwait(3000);
			verifyFieldValue("Duplicate contact check", sf.cc.noDuplicatesFound, "Duplicate contacts found");
			sf.seleU.hardwait(3000);
			verifyFieldValue("Match", sf.cc.matchingField, "100");
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + " Found Duplicate Contact Match as 100% : ", true, false);
			
			//select the contact to be updated
			sf.seleU.clickElementByJSE(sf.cc.updateContactRadio);			
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.cc.selectNextActionUpdateExistingContactRadio);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + " Selected the option to update existing contact : ", true, false);			
			sf.seleU.clickElementByJSE(sf.cc.nextStep);
			sf.seleU.hardwait(4000);
             
			// click on search business account created on choose access level page
			if(sameBusinessAccount)
			{
			sf.seleU.hardwait(4000);			
			sf.seleU.enterText(sf.cc.searchAccount, Global.dataInput.businessAccountName);
			sf.seleU.wait(5000);			
			sf.seleU.clickElementByJSE(driver.findElement(By.xpath("//span[contains(text(),'" + Global.dataInput.businessAccountName + "')]")));
			sf.seleU.wait(3000);						
			reportStatusPass(methodName + " Searched Business Account : " + Global.dataInput.businessAccountName, true, false);
			}
			
            if(!sameBusinessAccount)
            {
			sf.seleU.hardwait(4000);			
			sf.seleU.enterText(sf.cc.searchAccount, InputData_Service.businessAccountForInternalGuidedCase);
			sf.seleU.wait(5000);			
			sf.seleU.clickElementByJSE(driver.findElement(By.xpath("//span[contains(text(),'" + InputData_Service.businessAccountForInternalGuidedCase + "')]")));
			sf.seleU.wait(3000);						
			reportStatusPass(methodName + " Searched Business Account : " + InputData_Service.businessAccountForInternalGuidedCase, true, false);
            }
            
            if(accessLevelBusiness)
            {
		    //select access level business			
        	sf.seleU.clickElementByJSE(sf.cc.accessLevelBusiness);
        	sf.seleU.hardwait(3000);
        	reportStatusPass(methodName + " Selected access level as Business : ", true, false);
                 	   
            // select relationship type as Administrator
      		sf.seleU.clickElementByJSE(sf.cc.checkboxAdministrator);
      		sf.seleU.hardwait(4000);
      		reportStatusPass(methodName + " Selected relationship type as Administrator : ", true, false);
      		sf.seleU.clickElementByJSE(sf.cc.nextStep);
    		sf.seleU.hardwait(3000);
            }
           
            if(!accessLevelBusiness)
            {
            //select access level custom
            sf.seleU.clickElementByJSE(sf.cc.accessLevelCustom); 
            sf.seleU.wait(3000);
            sf.seleU.clickElementByJSE(sf.cc.nextStep);
 		    sf.seleU.wait(3000);
 		    
 		    //validate parent account showing on top, table headers are displayed, account field and satus are displayed
 		    verifyFieldValue("Showing table for Accounts in:", sf.cc.parentBusinessAccount, InputData_Service.businessAccountForInternalGuidedCase);
 		    sf.seleU.hardwait(4000);		    
 		    if(sf.seleU.isElementDisplayed(sf.cc.billingAccountField) && sf.seleU.isElementDisplayed(sf.cc.activeStatusField)
 		    && sf.seleU.isElementDisplayed(sf.cc.accountNameField) && sf.seleU.isElementDisplayed(sf.cc.accountNumberField)
 		    && sf.seleU.isElementDisplayed(sf.cc.addressField) && sf.seleU.isElementDisplayed(sf.cc.roleField))
 		    {
 		    	reportStatusPass(methodName + " Account Field, Accout Status and table headers are displayed ", true, true);
 		    	sf.seleU.hardwait(4000);
 		    }
 		    //assign role to service account
 		    sf.seleU.clickElementByJSE(sf.cc.filterButton);
 		    sf.seleU.hardwait(3000);
 		    if(sf.seleU.isElementSelected(sf.cc.activeRadioButton))
 		    {
 		    	reportStatusPass(methodName + " Active is selected by default ", true, true);
 		    	sf.seleU.hardwait(3000);
 		    }
 		    sf.seleU.clickElementByJSE(sf.cc.serviceAccountRadioButton);
 		    sf.seleU.hardwait(3000);
 		    sf.seleU.clickElementByJSE(sf.cc.applyButton);
 		    sf.seleU.hardwait(3000);
 		    sf.seleU.clickElementByJSE(sf.cc.showMoreButton);
 		    sf.seleU.hardwait(2000);
 		    sf.seleU.clickElementByJSE(sf.cc.assignRoleOption.get(2));
		    sf.seleU.hardwait(2000);
		    sf.seleU.clickElementByJSE(sf.cc.proceedButton);
 		    sf.seleU.hardwait(3000);
 		    
 		    //click on next
 		    sf.seleU.scrollByCoOrdinates(2);
 		    sf.seleU.hardwait(3000);
 		    sf.seleU.clickElementByJSE(sf.cc.nextStep);
 		    sf.seleU.wait(3000);		    
            }			
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Creating contact is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	public void enterNewContactInfoLWC(boolean accessLevelBusiness) throws IOException 
	{
		try 
		{
			String methodName = "SFDC_Create Contact@: ";
						
			// 1- Enter Salutation and Title
			sf.seleU.hardwait(5000);
			sf.seleU.clickOnElement(sf.cc.salutationsDropdown);
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.cc.salutationOption.get(3));			
			sf.seleU.hardwait(6000);
			sf.seleU.clearAndEnterText(sf.cc.TitleInput, Global.dataInput.contactTitle);
			reportStatusPass(methodName + " Entered contact Saluation and Title As:"
					+ Global.dataInput.contactSalutation + " " + Global.dataInput.contactTitle, true, false);
			sf.seleU.hardwait(5000);
			
			// 2- Enter First Name and Last Name
			sf.seleU.enterText(sf.cc.FirstNameInput, Global.dataInput.contactFirstName);
			reportStatusPass(methodName + " Entered contact First Name as : " + Global.dataInput.contactFirstName, true, false);
			sf.seleU.hardwait(5000);

			sf.seleU.enterText(sf.cc.LastNameInput, Global.dataInput.contactLastName);
			reportStatusPass(methodName + " Entered contact Last Name as : " + Global.dataInput.contactLastName, true, false);
			sf.seleU.hardwait(5000);
			
			Global.dataInput.contactName = ((Global.dataInput.contactFirstName).concat(" ").concat(Global.dataInput.contactLastName));
               			
			// 3- Enter Email Address and phone Number
			sf.seleU.enterText(sf.cc.EmailAddressInput, Global.dataInput.contactEmailAddress);
			reportStatusPass(methodName + " Entered contact Email Address as : " + Global.dataInput.contactEmailAddress, true, false);
			sf.seleU.hardwait(5000);
						
			reportStatusPass(methodName + " Entered contact Phone Number as : " + Global.dataInput.phoneNumber, true, false);
			int j = 3;
			for (int i = 0; i <= 12; i = i + 3) 
			{
			  sf.seleU.enterText(sf.cc.phoneInput, Global.dataInput.phoneNumber.substring(i, j));
			  reportStatusPass(methodName + " Entered contact Phone Number as : " + Global.dataInput.phoneNumber.substring(i, j), true, false);
			  sf.seleU.clickElementByJSE(sf.cc.languageButton);
			  sf.seleU.wait(4000);
			  j = j + 3;
			}
		
			// Verify language as English selected by default			
			if(sf.seleU.isElementSelected(sf.cc.languageRadioButton) && (sf.seleU.getTextFromWebElement(sf.cc.englishLanguage).equalsIgnoreCase(InputData.langEnglish)))
			{
			  reportStatusPass(methodName + " Validated Contact Lanuage Options as  " + InputData.langEnglish, true, true);	
			}
			
			else 
			{
			  reportStatusFail(methodName + " Invalid options in Contact Language " + InputData.langEnglish, true);
			}
			
			sf.seleU.clickElementByJSE(sf.cc.continueButton); 
			sf.seleU.hardwait(6000);

			
			sf.seleU.hardwait(5000);
			sf.seleU.enterText(sf.cc.contactMailingAddress, Global.dataInput.address);
			sf.seleU.hardwait(5000);
			sf.seleU.enterText(sf.cc.contactMailingAddress, Keys.TAB);
			reportStatusPass(methodName + " Enter Mailing Address Information As : " + Global.dataInput.address, true, false);
			sf.seleU.hardwait(5000);			
			sf.seleU.scrollByCoOrdinates(2);
			sf.seleU.hardwait(3000);

			// click on product influence
			sf.seleU.clickElementByJSE(sf.cc.internetOfThingsOption);
			reportStatusPass(methodName + " Selected Internet Of Things ", true, false);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.cc.continueButton);
			sf.seleU.hardwait(5000);

			// check for duplicate contacts
			
			if (sf.seleU.isElementPresent(sf.cc.uniqueContact)) 
			{
				sf.seleU.hardwait(3000);
				sf.seleU.clickElementByJSE(sf.cc.continueButton);
				sf.seleU.hardwait(3000);
			}
			else
			{
			
			sf.seleU.hardwait(3000);
			verifyFieldValue("Duplicate contact check", sf.cc.noDuplicatesFound, "Duplicate contacts found");
			sf.seleU.hardwait(3000);
			verifyFieldValue("Match", sf.cc.matchingField, "100");
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + " Found Duplicate Contact Match as 100% : ", true, false);
			
			//select the contact to be updated
			sf.seleU.clickElementByJSE(sf.cc.updateContactRadio);			
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.cc.selectNextActionUpdateExistingContactRadio);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + " Selected the option to update existing contact : ", true, false);			
			sf.seleU.clickElementByJSE(sf.cc.continueButton);
			sf.seleU.hardwait(4000);
			}        
			if(accessLevelBusiness)
            {
		    //select access level business			
        	sf.seleU.clickElementByJSE(sf.cc.accessLevelBusiness);
        	sf.seleU.hardwait(3000);
        	reportStatusPass(methodName + " Selected access level as Business : ", true, false);
                 	   
            // select relationship type as Administrator
      		sf.seleU.clickElementByJSE(sf.cc.checkboxAdministrator);
      		sf.seleU.hardwait(4000);
      		reportStatusPass(methodName + " Selected relationship type as Administrator : ", true, false);
      		sf.seleU.clickElementByJSE(sf.cc.continueButton);
    		sf.seleU.hardwait(3000);
            }
           
            if(!accessLevelBusiness)
            {
            //select access level custom
            sf.seleU.clickElementByJSE(sf.cc.accessLevelCustom); 
            sf.seleU.wait(3000);
            sf.seleU.clickElementByJSE(sf.cc.continueButton);
 		    sf.seleU.wait(3000);
 		    
 		    //validate parent account showing on top, table headers are displayed, account field and satus are displayed
 		    verifyFieldValue("Showing table for Accounts in:", sf.cc.parentBusinessAccount, InputData_Service.businessAccountACR);
 		    sf.seleU.hardwait(4000);		    
 		    if(sf.seleU.isElementDisplayed(sf.cc.billingAccountField) && sf.seleU.isElementDisplayed(sf.cc.activeStatusField)
 		    && sf.seleU.isElementDisplayed(sf.cc.accountNameField) && sf.seleU.isElementDisplayed(sf.cc.accountNumberField)
 		    && sf.seleU.isElementDisplayed(sf.cc.addressField) && sf.seleU.isElementDisplayed(sf.cc.roleField))
 		    {
 		    	reportStatusPass(methodName + " Account Field, Accout Status and table headers are displayed ", true, true);
 		    	sf.seleU.hardwait(4000);
 		    }
 		    //assign role to service account
 		    sf.seleU.clickElementByJSE(sf.cc.filterButton);
 		    sf.seleU.hardwait(3000);
 		    if(sf.seleU.isElementSelected(sf.cc.activeRadioButton))
 		    {
 		    	reportStatusPass(methodName + " Active is selected by default ", true, true);
 		    	sf.seleU.hardwait(3000);
 		    }
 		    sf.seleU.clickElementByJSE(sf.cc.serviceAccountRadioButton);
 		    sf.seleU.hardwait(3000);
 		    sf.seleU.clickElementByJSE(sf.cc.applyButton);
 		    sf.seleU.hardwait(3000);
 		    sf.seleU.clickElementByJSE(sf.cc.showMoreButton);
 		    sf.seleU.hardwait(2000);
 		    sf.seleU.clickElementByJSE(sf.cc.assignRoleOption.get(2));
		    sf.seleU.hardwait(2000);
		    sf.seleU.clickElementByJSE(sf.cc.proceedButton);
 		    sf.seleU.hardwait(3000);
 		    
 		    //click on next
 		    sf.seleU.scrollByCoOrdinates(2);
 		    sf.seleU.hardwait(3000);
 		    sf.seleU.clickElementByJSE(sf.cc.continueButton);
 		    sf.seleU.wait(3000);		    
           
            }
            reportStatusPass(methodName + " Creating contact is successfull ", true, true);
            
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Creating contact is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	public void enterNewContactInfo(boolean isFromAccount, boolean accessLevelBusiness, boolean relationshipTypeSigningAuthority, boolean relationshipTypeAdmin, boolean relationshipTypeNone) throws IOException {
		try {

			String methodName = "SFDC_Create Contact@: ";
						
			// 1- Enter Salutation and Title
			sf.seleU.hardwait(5000);
			sf.seleU.clickOnElement(sf.cc.salutationsDropdown);
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.cc.salutationOption.get(3));			
			sf.seleU.hardwait(6000);
			sf.seleU.clearAndEnterText(sf.cc.TitleInput, Global.dataInput.contactTitle);
			reportStatusPass(methodName + " Entered contact Saluation and Title As:"
					+ Global.dataInput.contactSalutation + " " + Global.dataInput.contactTitle, true, false);
			sf.seleU.hardwait(5000);
			
			// 2- Enter First Name and Last Name
			sf.seleU.enterText(sf.cc.FirstNameInput, Global.dataInput.contactFirstName);
			reportStatusPass(methodName + " Entered contact First Name as : " + Global.dataInput.contactFirstName, true, false);
			sf.seleU.hardwait(5000);

			sf.seleU.enterText(sf.cc.LastNameInput, Global.dataInput.contactLastName);
			reportStatusPass(methodName + " Entered contact Last Name as : " + Global.dataInput.contactLastName, true, false);
			sf.seleU.hardwait(5000);
			
			Global.dataInput.contactName = ((Global.dataInput.contactFirstName).concat(" ").concat(Global.dataInput.contactLastName));
               			
			// 3- Enter Email Address and phone Number
			sf.seleU.enterText(sf.cc.EmailAddressInput, Global.dataInput.contactEmailAddress);
			reportStatusPass(methodName + " Entered contact Email Address as : " + Global.dataInput.contactEmailAddress, true, false);
			sf.seleU.hardwait(5000);
			
			// To use this email id for generating duplicate contacts scenario
			// sf.dataInput.emailIdValue = sf.dataInput.contactEmailAddress;
			Global.dataInput.duplicateEmailAddress = Global.dataInput.contactEmailAddress;
			
			reportStatusPass(methodName + " Entered contact Phone Number as : " + Global.dataInput.phoneNumber, true, false);
			int j = 3;
			for (int i = 0; i <= 12; i = i + 3) 
			{
			  sf.seleU.enterText(sf.cc.phoneInput, Global.dataInput.phoneNumber.substring(i, j));
			  reportStatusPass(methodName + " Entered contact Phone Number as : " + Global.dataInput.phoneNumber.substring(i, j), true, false);
			  sf.seleU.clickElementByJSE(sf.cc.languageButton);
			  sf.seleU.wait(4000);
			  j = j + 3;
			}
		
			// Verify language as English selected by default			
			if(sf.seleU.isElementSelected(sf.cc.languageRadioButton) && (sf.seleU.getTextFromWebElement(sf.cc.englishLanguage).equalsIgnoreCase(InputData.langEnglish)))
			{
			  reportStatusPass(methodName + " Validated Contact Lanuage Options as  " + InputData.langEnglish, true, true);	
			}
			
			else 
			{
			  reportStatusFail(methodName + " Invalid options in Contact Language " + InputData.langEnglish, true);
			}
			
			sf.seleU.clickElementByJSE(sf.cc.nextStep);
			sf.seleU.hardwait(6000);

			// Enter New Contact Mailing Address Information
//			if (Global.dataInput.selectCanNotFindMailingAddress.equals("Yes")) {
//				createNewContactAddress();
//			} else {
			
			sf.seleU.hardwait(5000);
			sf.seleU.enterText(sf.cc.contactMailingAddress, Global.dataInput.address);
			sf.seleU.hardwait(5000);
			sf.seleU.enterText(sf.cc.contactMailingAddress, Keys.TAB);
			reportStatusPass(methodName + " Enter Mailing Address Information As : " + Global.dataInput.address, true, false);
			sf.seleU.hardwait(5000);			
			sf.seleU.scrollByCoOrdinates(2);
			sf.seleU.hardwait(3000);

			// click on product influence
			sf.seleU.clickElementByJSE(sf.cc.internetOfThingsOption);
			reportStatusPass(methodName + " Selected Internet Of Things ", true, false);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.cc.nextStep);
			sf.seleU.hardwait(5000);

			// check for duplicate contacts
			sf.seleU.hardwait(3000);
			verifyFieldValue("Duplicate contact check", sf.cc.noDuplicatesFound, "No Duplicates Found");			
			sf.seleU.clickElementByJSE(sf.cc.nextStep);
			sf.seleU.hardwait(4000);

			// click on search business account created on choose access level page
			if(!isFromAccount)
			{
			sf.seleU.hardwait(4000);
			sf.seleU.enterText(sf.cc.searchAccount, InputData.account_ON);
//			sf.seleU.enterText(sf.cc.searchAccount, InputData_Service.businessAccountForInternalGuidedCase);
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(driver.findElement(By.xpath("//span[contains(text(),'" + InputData.account_ON + "')]")));
//			sf.seleU.clickElementByJSE(driver.findElement(By.xpath("//span[contains(text(),'" + InputData_Service.businessAccountForInternalGuidedCase + "')]")));
			sf.seleU.wait(3000);			
			
//			sf.seleU.enterText(sf.cc.searchAccount, Keys.TAB);
//			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Searched Business Account : " + InputData.account_ON, true, false);
//			reportStatusPass(methodName + " Searched Business Account : " + InputData_Service.businessAccountForInternalGuidedCase, true, false);
			}
			
		   //select access level			
           if(accessLevelBusiness) 
           { 
        	   sf.seleU.clickElementByJSE(sf.cc.accessLevelBusiness);
        	   
           if(relationshipTypeSigningAuthority)
           {
		   // select relationship type as signing authority
		   sf.seleU.clickElementByJSE(sf.cc.buttonSigningAuthority);
		   sf.seleU.hardwait(4000);
		   
		   //check for signing authority approval message
		   verifyFieldValue("Signing Authority Approval", sf.cc.signingAuthorityMessage, "Signing Authority role requires an additional approval");
           sf.seleU.hardwait(5000);
		   sf.seleU.clickElementByJSE(sf.cc.nextStep);
		   sf.seleU.hardwait(3000);

		  // choose signing authority
		  sf.seleU.hardwait(5000);
		  sf.seleU.clickElementByJSE(sf.cc.buttonManualProcess);
		  sf.seleU.wait(5000);
		  sf.seleU.clickElementByJSE(sf.cc.nextStep);			
		  sf.seleU.wait(3000);
	
		  //upload NON PII documents
	      sf.seleU.hardwait(5000);	      
	      sf.seleU.scrollByCoOrdinates(2);
	      sf.seleU.clickOnElement(sf.files.filesUpload);
		  sf.seleU.wait(5000);
		  
//		  uploadFilesWithOutVerifying();
	      		  		  
//		  uploadFileRobotClass(Constants.BUSINESS_CARD);
		  
		  SFDC_Files_Page.uploadFileRobotClass(Constants.BUSINESS_CARD);
		  		  				
//		  uploadFilesThroughSikuli();
		  
		  sf.seleU.clickElementByJSE(sf.files.uploadDoneButton);
		  sf.seleU.wait(3000);
	      sf.seleU.clickOnElement(sf.files.filesUpload);
		  sf.seleU.wait(5000);
		  SFDC_Files_Page.uploadFileRobotClass(Constants.COMPANY_LETTER);
		  sf.seleU.clickElementByJSE(sf.files.uploadDoneButton);
		  sf.seleU.wait(3000);

		  sf.seleU.clickElementByJSE(sf.cc.nextStep);
		  sf.seleU.wait(3000);		  
          }
         
         if(relationshipTypeAdmin)
         {
           // select relationship type as Administrator
  		   sf.seleU.clickElementByJSE(sf.cc.checkboxAdministrator);
  		   sf.seleU.hardwait(4000);
  		   sf.seleU.clickElementByJSE(sf.cc.nextStep);
		   sf.seleU.hardwait(3000);
         }
           
         if(relationshipTypeNone)//relationship type 'None' RWOS role must be selected
         {

       	  // select relationship type as none
       	  sf.seleU.clickElementByJSE(sf.cc.buttonNone);
       			
       	  //select RWOS role
       	  sf.seleU.clickElementByJSE(sf.cc.roleEndorser);
       	  sf.seleU.hardwait(3000);
       	  sf.seleU.clickElementByJSE(sf.cc.nextStep);
		  sf.seleU.wait(3000);
       	  
         }
           }  
          else //access level custom
          {
           sf.seleU.clickElementByJSE(sf.cc.accessLevelCustom); 
           sf.seleU.wait(3000);
           sf.seleU.clickElementByJSE(sf.cc.nextStep);
		   sf.seleU.wait(3000);
		   sf.seleU.scrollByCoOrdinates(2);
		   sf.seleU.hardwait(3000);
		   sf.seleU.clickElementByJSE(sf.cc.nextStep);
		   sf.seleU.wait(3000);           
          }

           reportStatusPass(methodName + " Contact is created Successfully ", true, false);
		} catch (Throwable e) {
			reportStatusFail(" Creating contact is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	public void enterNewContactInfoForBusinessAccount(boolean accessLevelBusiness, boolean relationshipTypeSigningAuthority, boolean relationshipTypeAdmin, boolean relationshipTypeNone) throws IOException {
		try {

			String methodName = "SFDC_Create Contact@: ";
						
			// 1- Enter Salutation and Title
			sf.seleU.hardwait(8000);
			sf.seleU.clickOnElement(sf.cc.salutationsDropdown);
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.cc.salutationOption.get(3));			
			sf.seleU.hardwait(6000);
			sf.seleU.clearAndEnterText(sf.cc.TitleInput, Global.dataInput.contactTitle);
			reportStatusPass(methodName + " Entered contact Saluation and Title As:"
					+ Global.dataInput.contactSalutation + " " + Global.dataInput.contactTitle, true, false);
			sf.seleU.hardwait(5000);
			
			// 2- Enter First Name and Last Name
			sf.seleU.enterText(sf.cc.FirstNameInput, Global.dataInput.contactFirstName);
			reportStatusPass(methodName + " Entered contact First Name as : " + Global.dataInput.contactFirstName, true, false);
			sf.seleU.hardwait(5000);

			sf.seleU.enterText(sf.cc.LastNameInput, Global.dataInput.contactLastName);
			reportStatusPass(methodName + " Entered contact Last Name as : " + Global.dataInput.contactLastName, true, false);
			sf.seleU.hardwait(5000);
			
			Global.dataInput.contactName = ((Global.dataInput.contactFirstName).concat(" ").concat(Global.dataInput.contactLastName));
               			
			// 3- Enter Email Address and phone Number
			sf.seleU.enterText(sf.cc.EmailAddressInput, Global.dataInput.contactEmailAddress);
			reportStatusPass(methodName + " Entered contact Email Address as : " + Global.dataInput.contactEmailAddress, true, false);
			sf.seleU.hardwait(5000);
			
			// To use this email id for generating duplicate contacts scenario
			// sf.dataInput.emailIdValue = sf.dataInput.contactEmailAddress;
			Global.dataInput.duplicateEmailAddress = Global.dataInput.contactEmailAddress;
			
			reportStatusPass(methodName + " Entered contact Phone Number as : " + Global.dataInput.phoneNumber, true, false);
			int j = 3;
			for (int i = 0; i <= 12; i = i + 3) 
			{
			  sf.seleU.enterText(sf.cc.phoneInput, Global.dataInput.phoneNumber.substring(i, j));
			  reportStatusPass(methodName + " Entered contact Phone Number as : " + Global.dataInput.phoneNumber.substring(i, j), true, false);
			  sf.seleU.clickElementByJSE(sf.cc.languageButton);
			  sf.seleU.wait(4000);
			  j = j + 3;
			}
		
			// Verify language as English selected by default			
			if(sf.seleU.isElementSelected(sf.cc.languageRadioButton) && (sf.seleU.getTextFromWebElement(sf.cc.englishLanguage).equalsIgnoreCase(InputData.langEnglish)))
			{
			  reportStatusPass(methodName + " Validated Contact Lanuage Options as  " + InputData.langEnglish, true, true);	
			}
			
			else 
			{
			  reportStatusFail(methodName + " Invalid options in Contact Language " + InputData.langEnglish, true);
			}
			
			// Verify "Mark as Primary Contact" selected automatically and is read-only
			if (sf.seleU.getElementAttribute(sf.cc.primaryContactCheckbox, "type").equals("checkbox")
			&& sf.seleU.getElementAttribute(sf.cc.primaryContactCheckbox, "aria-invalid").equals("false")
			&& sf.cc.primaryContactCheckbox.isSelected()) 
			{
				reportStatusPass(methodName + " Verified 'Mark as Primary Contact' checkbox is read-only and selected automatically ", true, true);
			} 
			else 
			{
				reportStatusFail(methodName + " Verification failure for 'Mark as Primary Contact' checkbox", true);
			}
			
			sf.seleU.clickElementByJSE(sf.cc.nextStep);
			sf.seleU.hardwait(6000);

			// Enter New Contact Mailing Address Information
//			if (Global.dataInput.selectCanNotFindMailingAddress.equals("Yes")) {
//				createNewContactAddress();
//			} else {
			
	//		sf.seleU.hardwait(5000);
			sf.seleU.enterText(sf.cc.contactMailingAddress, Global.dataInput.address);
			sf.seleU.hardwait(5000);
			sf.seleU.enterText(sf.cc.contactMailingAddress, Keys.TAB);
			reportStatusPass(methodName + " Enter Mailing Address Information As : " + Global.dataInput.address, true, false);
			sf.seleU.hardwait(5000);			
			sf.seleU.scrollByCoOrdinates(2);
			sf.seleU.hardwait(3000);

			// click on product influence
			sf.seleU.clickElementByJSE(sf.cc.internetOfThingsOption);
			reportStatusPass(methodName + " Selected Internet Of Things ", true, false);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.cc.nextStep);
			sf.seleU.hardwait(5000);

			// check for duplicate contacts
			sf.seleU.hardwait(3000);
			verifyFieldValue("Duplicate contact check", sf.cc.noDuplicatesFound, "No Duplicates Found");			
			sf.seleU.clickElementByJSE(sf.cc.nextStep);
			sf.seleU.hardwait(4000);
			
			sf.seleU.scrollByCoOrdinates(2);
			sf.seleU.hardwait(3000);

		   //select access level			
           if(accessLevelBusiness) 
           { 
        	   sf.seleU.clickElementByJSE(sf.cc.accessLevelBusiness);
        	   
           if(relationshipTypeSigningAuthority)
           {
		   // select relationship type as signing authority
		   sf.seleU.clickElementByJSE(sf.cc.buttonSigningAuthority);
		   sf.seleU.hardwait(4000);
		   
		   //check for signing authority approval message
		   verifyFieldValue("Signing Authority Approval", sf.cc.signingAuthorityMessage, "Signing Authority role requires an additional approval");
           sf.seleU.hardwait(5000);
		   sf.seleU.clickElementByJSE(sf.cc.nextStep);
		   sf.seleU.hardwait(3000);

		  // choose signing authority
		  sf.seleU.hardwait(5000);
		  sf.seleU.clickElementByJSE(sf.cc.buttonManualProcess);
		  sf.seleU.wait(5000);
		  sf.seleU.clickElementByJSE(sf.cc.nextStep);			
		  sf.seleU.wait(3000);
	
		  //upload NON PII documents
	      sf.seleU.hardwait(5000);	      
	      sf.seleU.scrollByCoOrdinates(2);
	      sf.seleU.clickOnElement(sf.files.filesUpload);
		  sf.seleU.wait(5000);
		  
//		  uploadFilesWithOutVerifying();
	      		  		  
//		  uploadFileRobotClass(Constants.BUSINESS_CARD);
		  
		  SFDC_Files_Page.uploadFileRobotClass(Constants.BUSINESS_CARD);
		  		  				
//		  uploadFilesThroughSikuli();
		  
		  sf.seleU.clickElementByJSE(sf.files.uploadDoneButton);
		  sf.seleU.wait(3000);
	      sf.seleU.clickOnElement(sf.files.filesUpload);
		  sf.seleU.wait(5000);
		  SFDC_Files_Page.uploadFileRobotClass(Constants.COMPANY_LETTER);
		  sf.seleU.clickElementByJSE(sf.files.uploadDoneButton);
		  sf.seleU.wait(3000);

		  sf.seleU.clickElementByJSE(sf.cc.nextStep);
		  sf.seleU.wait(3000);		  
          }
         
         if(relationshipTypeAdmin)
         {
           // select relationship type as Administrator
  		   sf.seleU.clickElementByJSE(sf.cc.checkboxAdministrator);
  		   sf.seleU.hardwait(4000);
  		   sf.seleU.clickElementByJSE(sf.cc.nextStep);
		   sf.seleU.hardwait(3000);
         }
           
         if(relationshipTypeNone)//relationship type 'None' RWOS role must be selected
         {

       	  // select relationship type as none
       	  sf.seleU.clickElementByJSE(sf.cc.buttonNone);
       			
       	  //select RWOS role
       	  sf.seleU.clickElementByJSE(sf.cc.roleEndorser);
       	  sf.seleU.hardwait(3000);
	      sf.seleU.clickElementByJSE(sf.cc.roleAssessor);
	      sf.seleU.hardwait(3000);
	      sf.seleU.clickElementByJSE(sf.cc.roleDecider);
	      sf.seleU.hardwait(3000);
	      sf.seleU.clickElementByJSE(sf.cc.roleCoachInfluencer);
	      sf.seleU.hardwait(3000);
       	  sf.seleU.clickElementByJSE(sf.cc.nextStep);
		  sf.seleU.wait(3000);       	  
         }
           }  
          else //access level custom
          {
           sf.seleU.clickElementByJSE(sf.cc.accessLevelCustom); 
           sf.seleU.wait(3000);
           sf.seleU.clickElementByJSE(sf.cc.nextStep);
		   sf.seleU.wait(3000);
		   sf.seleU.scrollByCoOrdinates(2);
		   sf.seleU.hardwait(3000);
		   sf.seleU.clickElementByJSE(sf.cc.nextStep);
		   sf.seleU.wait(3000);           
          }
           reportStatusPass(methodName + " Contact created ", true, false);
           
		} catch (Throwable e) {
			reportStatusFail(" Creating contact is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	
	/* Verify new contact created
	 * 
	 */
	public void validateNewContact() throws IOException{
		String methodName = "SFDC_Verify Contact@: ";
		try {
									
			//verify contact created
			boolean isContactFound = false;
			// click on newly created contact
				if (sf.seleU.getTextFromWebElement(sf.cc.recentlyViewedContacts.get(0)).contains(Global.dataInput.contactFirstName)) {
					sf.seleU.clickElementByJSE(sf.cc.recentlyViewedContacts.get(0));
					sf.seleU.wait(5000);
					reportStatusPass(methodName + " Found and Clicked on  Contact ", true, true);
					isContactFound = true;
				}		
			if (!isContactFound) {
				reportStatusFail(methodName + " No such contact found as  " + Global.dataInput.contactFirstName, true);
			}		
			// Verify Contact details
			if (sf.seleU.getTextFromWebElement(sf.cc.contactName).contains(Global.dataInput.contactFirstName)
					&& sf.seleU.getTextFromWebElement(sf.cc.contactName)
							.contains(Global.dataInput.contactLastName)) {
				reportStatusPass(methodName + "Verifying contact creation is successful",
						true, true);
				sf.seleU.wait(5000);
			}
						
			 else 
			 {
			   reportStatusFail(methodName + " Verifying contact creation is Unsuccessful ", true);
			 }
			
			//verify contact as General			
			 sf.seleU.hardwait(5000);
			 sf.seleU.clickElementByJSE(sf.cd.relatedTab.get(0));						
			 reportStatusPass(methodName + " Clicked on related tab ", true, false);
			 sf.seleU.hardwait(4000);			 
			 verifyFieldValue("Roles ", sf.cd.rolesAdmin, "General");
			
		}
			catch (Throwable e) {
				reportStatusFail(methodName + " Verifying contact creation is Unsuccessful", e);
				e.printStackTrace();
			}			
		}
	
	public void validateNewContactforCase() throws IOException{
		String methodName = "SFDC_Verify Contact@: ";
		try {
										
			// Verify Contact details
			if (sf.seleU.getTextFromWebElement(sf.cc.contactName).contains(Global.dataInput.contactFirstName)
					&& sf.seleU.getTextFromWebElement(sf.cc.contactName)
							.contains(Global.dataInput.contactLastName)) {
				reportStatusPass(methodName + "Verifying contact creation is successful",
						true, true);
				sf.seleU.wait(5000);
			}
						
			 else 
			 {
			   reportStatusFail(methodName + " Verifying contact creation is Unsuccessful ", true);
			 }
			
			//verify contact as General			
		
		}
			catch (Throwable e) {
				reportStatusFail(methodName + " Verifying contact creation is Unsuccessful", e);
				e.printStackTrace();
			}			
		}
	
	public void validateBusinessContact() throws IOException
	{
		String methodName = "SFDC_Verify Contact@: ";
		try 
		{									
			// Verify Contact details
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.ad.contactLink.get(0));
			sf.seleU.hardwait(3000);
					
			if (sf.seleU.getTextFromWebElement(sf.cc.contactName).contains(Global.dataInput.contactFirstName)
			&& sf.seleU.getTextFromWebElement(sf.cc.contactName).contains(Global.dataInput.contactLastName)) 
			{
				reportStatusPass(methodName + "Verifying contact creation is successful", true, true);			
			}						
			else 
			{
			   reportStatusFail(methodName + " Verifying contact creation is Unsuccessful ", true);
			}
			sf.seleU.wait(5000);
			
			//verify contact as Administrator		
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.cd.relatedTab.get(0));						
			reportStatusPass(methodName + " Clicked on related tab ", true, false);
			sf.seleU.hardwait(4000);			 
			verifyFieldValue("Roles ", sf.cd.rolesAdmin, "Administrator");		
		}
			catch (Throwable e) 
		{
			reportStatusFail(methodName + " Verifying contact creation is Unsuccessful", e);
			e.printStackTrace();
		}			
	}
	
	public void validateContactForACRPermission(boolean signingAuthority) throws IOException
	{
		String methodName = "SFDC_Verify Contact@: ";
		try 
		{									
			//validate ACR Permissions value
			sf.seleU.clickElementByJSE(sf.ar.contactTab);
			sf.seleU.hardwait(4000);
			
			sf.seleU.clickElementByJSE(sf.ar.relatedContactsViewAll);
			sf.seleU.hardwait(4000);
												
			if(signingAuthority)
		{
			String[] ACRPermissionValues = sf.seleU.getTextFromWebElement(sf.cd.contactAcrPermissionText.get(0)).split(";");				
			List<String> actualACRPermissionValues = new ArrayList<String>(Arrays.asList(ACRPermissionValues));				
			InputData_Service.setDataForACRPermissionValues();				
			List<String> expectedACRPermissionValues = InputData_Service.ACRPermissionValues;				
			Collections.sort(actualACRPermissionValues);				
			Collections.sort(expectedACRPermissionValues);
			
			if (sf.seleU.getElementAttribute(sf.cd.checkBoxes.get(0), "alt").equals("True")
			&& actualACRPermissionValues.equals(expectedACRPermissionValues))
			{
			  reportStatusPass(methodName + " Verified relationship Type  is Direct & ACR Permissions values are present ", true, true);
			  sf.seleU.hardwait(4000);
			}
			else 
			{
			  reportStatusFail(methodName + " Error in Verifying Relationship Type as Direct and ACR Permission value ", true);
			}
		}
			if(!signingAuthority)
		{
			if (sf.seleU.getElementAttribute(sf.cd.checkBoxes.get(0), "alt").equals("True")
			&& sf.seleU.getTextFromWebElement(sf.cd.contactAcrPermissionText.get(0)).contains("General"))
			{
				reportStatusPass(methodName + " Verified relationship Type  is Direct & no ACR permission is selected ", true, true);
				sf.seleU.hardwait(4000);
			}
			else 
			{
			    reportStatusFail(methodName + " Error in Verifying Relationship Type as Direct and ACR Permission value ", true);
			}			
		}			
			sf.seleU.clickElementByJSE(sf.cd.contactNameIcon);
			sf.seleU.hardwait(4000);
			
			//validate contact record type as Business Contact
			verifyFieldValue(" Contact Record Type ", sf.cd.contactRecordTypeText, "Business Contacts");
            sf.seleU.hardwait(4000);
            
			sf.seleU.clickElementByJSE(sf.cd.relatedTab.get(0));
			sf.seleU.hardwait(4000);
			
            // validate added relation is direct and active and ACRPermission is General
			sf.seleU.clickElementByJSE(sf.cd.viewAllRelatedAccount);
			sf.seleU.hardwait(4000);
						
			if(signingAuthority)
		  {
			String[] ACRPermissionValues = sf.seleU.getTextFromWebElement(sf.ar.contactAcrPermissionText.get(0)).split(";");					
			List<String> actualACRPermissionValues = new ArrayList<String>(Arrays.asList(ACRPermissionValues));					
			InputData_Service.setDataForACRPermissionValues();					
			List<String> expectedACRPermissionValues = InputData_Service.ACRPermissionValues;										
			Collections.sort(actualACRPermissionValues);					
			Collections.sort(expectedACRPermissionValues);
									
			if (sf.seleU.getElementAttribute(sf.ar.contactAcrDirectCheckBox.get(0), "alt").equals("True")
			&& actualACRPermissionValues.equals(expectedACRPermissionValues))
		{
			reportStatusPass(methodName + " Verified relationship Type  is Direct & ACR Permissions values are present ", true, true);
			sf.seleU.hardwait(4000);
		}
			else 
		{
		   reportStatusFail(methodName + " Error in Verifying Relationship Type as Direct and ACR Permission value ", true);
		}	
		    }
			
			if(!signingAuthority)
		    {
			  if (sf.seleU.getElementAttribute(sf.ar.contactAcrDirectCheckBox.get(0), "alt").equals("True")
			  && sf.seleU.getTextFromWebElement(sf.ar.contactAcrPermissionText.get(0)).contains("General"))
			{
				reportStatusPass(methodName + " Verified relationship Type  is Direct & no ACR permission is selected ", true, true);
				sf.seleU.hardwait(4000);
			}
			else 
			{
			    reportStatusFail(methodName + " Error in Verifying Relationship Type as Direct and ACR Permission value ", true);
			}			
		   }						
	}
			catch (Throwable e) 
		{
				reportStatusFail(methodName + " Validating contact for ACR Permission is Unsuccessful ", e);
				e.printStackTrace();
		}			
   }
	
	/* Verify new contact created
	 * 
	 */
	public void validateNewContactForDuplicateContact() throws IOException
	{
		String methodName = "SFDC_Verify Contact@: ";
		try 
		{
			//verify contact created
			boolean isContactFound = false;
			// click on newly created contact
				if (sf.seleU.getTextFromWebElement(sf.cc.recentlyViewedContacts.get(0)).contains(Global.dataInput.contactFirstName)) 
				{
					sf.seleU.clickElementByJSE(sf.cc.recentlyViewedContacts.get(0));
					sf.seleU.wait(5000);
					reportStatusPass(methodName + " Found and Clicked on  Contact ", true, true);
					isContactFound = true;
				}		
			if (!isContactFound) 
			{
				reportStatusFail(methodName + " No such contact found as  " + Global.dataInput.contactFirstName, true);
			}		
			// Verify Contact details
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
			
			//verify contact as Administrator		
			 sf.seleU.hardwait(5000);
			 sf.seleU.clickElementByJSE(sf.cd.relatedTab.get(0));						
			 reportStatusPass(methodName + " Clicked on related tab ", true, false);
			 sf.seleU.hardwait(4000);			 
			 verifyFieldValue("Roles ", sf.cd.rolesAdmin, "Administrator");			
		}
			catch (Throwable e) 
		{
			reportStatusFail(methodName + " Verifying contact creation is Unsuccessful", e);
			e.printStackTrace();
		}			
	}

	
	/* Verify new contact created with RWOS role
	 * 
	 */
	public void validateNewContactWithRWOSRole() throws IOException{
		String methodName = "SFDC_Verify Contact@: ";
		try {
			//verify contact created
			boolean isContactFound = false;
			// click on newly created contact
				if (sf.seleU.getTextFromWebElement(sf.cc.recentlyViewedContacts.get(0)).contains(Global.dataInput.contactFirstName)) {
					sf.seleU.clickElementByJSE(sf.cc.recentlyViewedContacts.get(0));
					sf.seleU.hardwait(5000);
					reportStatusPass(methodName + " Found and Clicked on  Contact ", true, true);
					isContactFound = true;
				}		
			if (!isContactFound) {
				reportStatusFail(methodName + " No such contact found as  " + Global.dataInput.contactFirstName, true);
			}		
			// Verify Contact details
			if (sf.seleU.getTextFromWebElement(sf.cc.contactName).contains(Global.dataInput.contactFirstName)
					&& sf.seleU.getTextFromWebElement(sf.cc.contactName)
							.contains(Global.dataInput.contactLastName)) {
				reportStatusPass(methodName + "Verifying contact creation is successful",
						true, true);
				sf.seleU.hardwait(5000);
			}
			 else {
			 reportStatusFail(methodName + " Verifying contact creation is Unsuccessful ", true);
			 }
			//verify contact role as Endorser		
			 sf.seleU.hardwait(5000);
			 sf.seleU.clickElementByJSE(sf.cd.relatedTab.get(0));						
			 reportStatusPass(methodName + " Clicked on related tab ", true, false);
			 sf.seleU.hardwait(4000);
			 verifyFieldValue("Roles ", sf.cd.rolesAdmin, "Endorser");		  
		}
			catch (Throwable e) {
				reportStatusFail(methodName + " Verifying contact creation is Unsuccessful", e);
				e.printStackTrace();
			}			
		}
	
	public static void setClipBoard(String file) 
	  {
		try
		{
	     StringSelection obj= new StringSelection(file);
		 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(obj, null);
	    }
		catch(Throwable e) 
		{
		  e.printStackTrace();
		}
	  }
	  
	  public static void uploadFileRobotClass(String filePath) throws AWTException 
	  {
		  try
		  {
		  setClipBoard(filePath);
		  Robot rb= new Robot();
		  rb.keyPress(KeyEvent.VK_CONTROL);
		  rb.keyPress(KeyEvent.VK_V);
		  rb.keyRelease(KeyEvent.VK_CONTROL);
		  rb.keyRelease(KeyEvent.VK_V);
		  rb.setAutoDelay(2000);
		  rb.keyPress(KeyEvent.VK_ENTER);
		  rb.keyRelease(KeyEvent.VK_ENTER);
	      }
		  catch(Throwable e) 
		  {
			e.printStackTrace();
		  }
	  } 
			
	public void uploadFilesThroughSikuli() throws IOException  {

		try {
									           
			String path= "C:\\Users\\Robin.Mangla\\Documents\\MPAutomation\\sfdc_Automation\\src\\test\\resources\\sikulifiles\\";
			
//			Pattern fileInputTextBox = new Pattern(Constants.FILE_TEXT_BOX);
			Pattern fileInputTextBox = new Pattern(path+"file_Text_Box.PNG");
//			Pattern openButton = new Pattern(Constants.OPEN_BUTTON);
			Pattern openButton = new Pattern(path+"open_Button.PNG");

			Screen s = new Screen();

			s.wait(fileInputTextBox,2);
			s.type(fileInputTextBox,path+"Sample_Upload_File.txt");
//			s.type(fileInputTextBox, Constants.BUSINESS_CARD);
			s.click(openButton);

		} 
		catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Uploads Files from particular section
	 * 
	 */
	public void uploadFilesWithOutVerifying() throws IOException {

		String methodName = "SFDC_Create Contact@: ";
		
		try {
			
			Runtime.getRuntime().exec(
				Constants.RESOURCES_DIR + "\\fileSelector.exe" + " " + Constants.SAMPLE_FILE_FOR_FILES_UPLOAD_PAGE);
			
			sf.seleU.hardwait(10000);
			sf.seleU.clickElementByJSE(sf.files.uploadDoneButton);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " File Uploading Successful ", true, false);
		   } 
		catch (Throwable e) 
		{
			reportStatusFail(methodName + " Error in uploading file", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Select 'Can not find address checkbox'
	 * 
	 * 
	 *                     Enter Fields for Mailing Address
	 */
	public void createContactAddress() throws IOException {
		try {

			String methodName = "SFDC_Create Contact@: ";

			// Select 'Can not find address checkbox'
			sf.seleU.clickElementByJSE(sf.cc.cannotFindAddressCheckbox);
			sf.seleU.wait(1000);
			sf.seleU.waitElementToBeVisible(sf.cc.mailingAddressstreet);
			reportStatusPass(methodName + "Selected 'Can not find address checkbox", true, false);

			// Enter Fields for Mailing Address
			sf.seleU.enterText(sf.cc.mailingAddressstreet, Global.dataInput.addressStreet);
			sf.seleU.wait(2000);
			sf.seleU.selectValueFromDropDown(sf.cc.mailingAddressProvince, Global.dataInput.addressState);
			sf.seleU.enterText(sf.cc.mailingAddressCity, Global.dataInput.addressCity);
			sf.seleU.wait(2000);
			sf.seleU.enterText(sf.cc.mailingAddressPostalCode, Global.dataInput.addressPostalCode);
			sf.seleU.clearAndEnterText(sf.cc.mailingAddressCountry, Global.dataInput.addressCountry);
			reportStatusPass(methodName + " Entered mailing Address for contact as " + Global.dataInput.address, true,
					false);

		} catch (Throwable e) {
			reportStatusFail(" Entering details for Mailing Address is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Select 'Can not find address checkbox'
	 * 
	 * 
	 *                     Enter Fields for Mailing Address
	 */
	public void createNewContactAddress() throws IOException {
		try {


			String methodName = "SFDC_Create Contact@: ";

			// Select 'Can not find address checkbox'
			sf.seleU.clickElementByJSE(sf.cc.cannotFindAddress);
			sf.seleU.wait(2000);
			sf.seleU.waitElementToBeVisible(sf.cc.mailingstreet);
			reportStatusPass(methodName + "Selected 'Can not find address checkbox", true, false);

			// Enter Fields for Mailing Address
			sf.seleU.enterText(sf.cc.mailingstreet, Global.dataInput.addressStreet);
//			sf.seleU.wait(2000);
			sf.seleU.enterText(sf.cc.mailingCity, Global.dataInput.addressCity);
//			sf.seleU.wait(2000);
			sf.seleU.clearAndEnterText(sf.cc.mailingCountry, Global.dataInput.addressCountry);
			sf.seleU.wait(2000);
			
			
//			reportStatusPass(methodName + " Entered Postal Code as : " + Global.dataInput.addressPostalCode, true,
//					false);
//			int j = 3;
//			for (int i = 0; i <= 4; i = i + 4) {
////				sf.seleU.wait(4000);
//				sf.seleU.enterText(sf.cc.mailingPostalCode, Global.dataInput.addressPostalCode.substring(i, j));
//				reportStatusPass(methodName + " Entered Postal Code as : "
//						+ Global.dataInput.addressPostalCode.substring(i, j), true, false);			
//				sf.seleU.wait(4000);
//				j = j + 4;
//			}
						
//			sf.seleU.enterText(sf.cc.mailingPostalCode, Global.dataInput.addressPostalCode);
			
			sf.seleU.wait(4000);
			sf.seleU.clickOnElement(sf.cc.dropDownMailingProvince);	
			sf.seleU.clickOnElement(sf.cc.mailingProvince.get(8));		
			sf.seleU.wait(4000);	
			
			reportStatusPass(methodName + " Entered Postal Code as : " + Global.dataInput.addressPostalCode, true,
					false);
			
//			int j = 3;
//			for (int i = 0; i <= 4; i = i + 4) {
//				sf.seleU.enterText(sf.cc.mailingPostalCode, Global.dataInput.addressPostalCode.substring(i, j));
//				reportStatusPass(methodName + " Entered Postal Code as : "
//						+ Global.dataInput.addressPostalCode.substring(i, j), true, false);			
//				sf.seleU.wait(4000);
//				j = j + 4;
//			}

			
			sf.seleU.enterText(sf.cc.mailingPostalCode, Global.dataInput.addressPostalCode);
					
			reportStatusPass(methodName + " Entered mailing Address for contact as " + Global.dataInput.address, true,
					false);
			

		} catch (Throwable e) {
			reportStatusFail(" Entering details for Mailing Address is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on select site contact radio button
	 */
	public void selectSiteContactRadio() throws IOException {
		try {

			String methodName = "SFDC_Create Contact@: ";

			// Click on select site contact radio button
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.cc.contactSalutationDropdown);
			sf.seleU.clickElementByJSE(sf.cc.siteContactRadio);
			reportStatusPass(methodName + " Clicked on Site Contact Radio Button", true, false);

			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Selecting Site Contact radio is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on select Business contact radio button
	 */
	public void selectBusinessContactRadio() throws IOException {
		try {

			String methodName = "SFDC_Create Contact@: ";

			// Click on select site contact radio button
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.cc.contactSalutationDropdown);
			// sf.seleU.clickElementByJSE(sf.cc.businessContactRadio);
			reportStatusPass(methodName + " Clicked on Business Contact Radio Button", true, false);

			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Selecting Business Contact radio is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Search Account
	 * 
	 *                     Click on Next Button
	 */
	public void contactSearchAccount(boolean iSiteContact) throws IOException {
		try {

			String methodName = "SFDC_Create Contact@: ";

			// Search Account
			sf.seleU.clickElementByJSE(sf.cc.contactSearchAccountInput);

			if (iSiteContact) {
				sf.seleU.enterText(sf.cc.contactSearchAccountInput, Global.dataInput.serviceAccountName);
			} else {
				sf.seleU.enterText(sf.cc.contactSearchAccountInput, FetchTestData.getMPTestData("CBA_Name"));
			}
			sf.seleU.hardwait(6000);
			sf.seleU.enterText(sf.cc.contactSearchAccountInput, Keys.ARROW_DOWN);
			sf.seleU.enterText(sf.cc.contactSearchAccountInput, Keys.ENTER);
			sf.seleU.hardwait(2000);
			reportStatusPass(
					methodName + " Entered Account as " + Global.dataInput.serviceAccountName + " to be searched for",
					true, false);

			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.cc.cc_SearchAccount_nextBtn);
			reportStatusPass(methodName + " Clicked on Next Button", true, false);

			sf.seleU.wait(11000);

		} catch (Throwable e) {
			reportStatusFail(" Searching Account for contact is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Site Contact Created Successfully
	 */
	public void verifySiteContactCreatedSuccessfully() throws IOException {

		try {

			String methodName = "SFDC_Create Contact@: ";

			// Verify Site Contact Created Successfully
			/*
			 * if (sf.seleU.getTextFromWebElement(sf.cc.contactDetailsContactNameLink)
			 * .equals(sf.dataInput.contactFirstName + " " + sf.dataInput.contactLastName)
			 * && sf.seleU.getTextFromWebElement(sf.cc.contactDetailsAccountNameLink)
			 * .equals(sf.dataInput.serviceAccountName)) {
			 */

			if (sf.seleU.getTextFromWebElement(sf.cc.contactDetailsContactNameLink)
					.equals(Global.dataInput.contactFirstName + " " + Global.dataInput.contactLastName)) {

				reportStatusPass(methodName + " Site Contact Created Successfully", true, true);
			} else {
				reportStatusFail(methodName + " Error in creating site contact", true);
			}

			// sf.seleU.clickElementByJSE(sf.cc.reviewContactNextButton);
			sf.seleU.clickElementByJSE(sf.cc.createNextContact_nextBtn);
			reportStatusPass(methodName + " Clicked on Next Button in Site Contact Review page", true, false);

			sf.seleU.wait(15000);

		} catch (Throwable e) {
			reportStatusFail(" Error in creating site contact", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on create contact next button
	 */
	public void clickContactDetailsNext() throws IOException {
		try {

			String methodName = "SFDC_Create Contact@: ";

			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.cc.contactDetails_nextBtn);
			reportStatusPass(methodName + " Clicked on Next Button on create contact page", true, false);

			sf.seleU.hardwait(7000);

		} catch (Throwable e) {
			reportStatusFail(" Error in clicking create contact next button ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1- Verify "Mark as Primary Contact" selected
	 *                     automatically and is read-only
	 * 
	 *                     2- Click on Next Button
	 * 
	 */
	public void verifyMarkPrimaryContactReadOnly() throws IOException {
		try {

			String methodName = "SFDC_Create Contact@: ";
			sf.seleU.wait(3000);

			// Verify "Mark as Primary Contact" selected automatically and is read-only
			if (sf.seleU.getElementAttribute(sf.cc.primaryContactCheckbox, "type").equals("checkbox")
					&& sf.seleU.getElementAttribute(sf.cc.primaryContactCheckbox, "disabled").equals("true")
					&& sf.cc.primaryContactCheckbox.isSelected()) {
				reportStatusPass(methodName
						+ " Verified 'Mark as Primary Contact' checkbox is read-only and selected automatically ", true,
						true);
				Global.dataInput.accountContact = Global.dataInput.contactFirstName + " "
						+ Global.dataInput.contactLastName;
			} else {
				reportStatusFail(methodName + " Verification failure for 'Mark as Primary Contact' checkbox", true);
			}

			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.cc.contactDetails_nextBtn);
			reportStatusPass(methodName + " Clicked on Next Button on create contact page", true, false);

			sf.seleU.wait(10000);

		} catch (Throwable e) {
			reportStatusFail(" Contact creation  is Unsuccessful", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Check 'Mark as Primary Contact' checkbox
	 * 
	 *                     Check 'Mark as Primary Contact' checkbox
	 */

	public void checkMarkAsPrimaryContact_Deprecated(boolean updateExistingContact) throws IOException {
		try {

			String methodName = "SFDC_Create Contact@: ";

			// Check 'Mark as Primary Contact' checkbox
			sf.seleU.clickElementByJSE(sf.cc.primaryContactCheckbox);
			reportStatusPass(methodName + " Checked 'Mark as Primary Contact' checkbox", true, false);
			Global.dataInput.accountContact = Global.dataInput.contactFirstName + " "
					+ Global.dataInput.contactLastName;
			sf.seleU.hardwait(5000);

			// Select Yes/No radio for 'Do you want to update the existing primary contact?'
			if (sf.seleU.isElementDisplayed(sf.cc.updateExistingContactYesRadio)
					&& sf.seleU.isElementDisplayed(sf.cc.updateExistingContactNoRadio)) {

				if (updateExistingContact) {
					sf.seleU.clickOnElement(sf.cc.updateExistingContactYesRadio);
					reportStatusPass(
							methodName + " Selected Yes for 'Do you want to update the existing primary contact?'",
							true, false);
				} else {
					sf.seleU.clickOnElement(sf.cc.updateExistingContactNoRadio);
					reportStatusPass(
							methodName + " Selected No for 'Do you want to update the existing primary contact?'", true,
							false);
				}
			} else {
				reportStatusFail(
						" Yes/No Option for  for 'Do you want to update the existing primary contact?' not displayed",
						true);
			}

			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.cc.contactDetails_nextBtn);
			reportStatusPass(methodName + " Clicked on Next Button on create contact page", true, false);

			sf.seleU.hardwait(7000);
		} catch (Throwable e) {
			reportStatusFail(" Marking as Primary contact during Contact creation  is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1. Open an business account
	 * 
	 *                     2. Click Create Contact button on the account summary
	 *                     section
	 * 
	 *                     3-Preapare test data for another contact
	 * 
	 */
	public void createContact() throws IOException {
		try {
			SFDC_AllPages sfdc = new SFDC_AllPages();
			String methodName = "SFDC_Account@: ";

			// Search Account
			sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);

			// Select Change Control option DropDown
			sf.seleU.wait(2000);
			sf.seleU.switchToElementFrame(sf.acc.controlDropDown);
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.acc.controlDropDown.get(0));
			sf.seleU.wait(4000);
			// Click on create contact icon
			sf.seleU.switchToElementFrame(sf.acc.createContactIcon);
			sf.seleU.clickElementByJSE(sf.acc.createContactIcon.get(0));
			reportStatusPass(methodName + " Clicked on create contact icon", true, false);
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(5000);
			sf.seleU.switchToElementFrame(sf.cc.contactSalutationDropdown);

			// Preapare test data for another contact
			Global.dataInput.secondContact_prepareContactData();

		} catch (Throwable e) {
			reportStatusFail(" Creating Contact is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select change primary contact checkbox
	 * 
	 *                     Verify Existing Primary Contact
	 * 
	 *                     Click on Change primary contact checkbox
	 * 
	 *                     Select new primary contact
	 * 
	 *                     Verify Rest contact fields autopopulated
	 * 
	 *                     upload evidence for contact change
	 * 
	 *                     Click on Next button
	 */
	public void changePrimaryContact() throws IOException {
		try {
			String methodName = "SFDC_Account@: ";

			// Select Change Control option DropDown
			sf.seleU.wait(2000);
			sf.seleU.switchToElementFrame(sf.acc.controlDropDown);
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.acc.controlDropDown.get(0));
			sf.seleU.wait(4000);

			// Select change primary contact checkbox
			sf.seleU.switchToElementFrame(sf.acc.changePrimaryContactIcon);
			sf.seleU.clickElementByJSE(sf.acc.changePrimaryContactIcon.get(0));
			reportStatusPass(methodName + " Clicked on 'change primary contact' icon", true, false);
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(5000);
			sf.seleU.switchToElementFrame(sf.cpc.changePrimaryContactCheckbox);

			// Verify Existing Primary Contact
			if (sf.seleU.getTextFromWebElement(sf.cpc.primaryContactText).equals(Global.dataInput.primaryContact)) {
				reportStatusPass(methodName + " Current Primary contact is " + Global.dataInput.primaryContact, true,
						true);
			} else {
				// reportStatusFail(" Expected Current Primary contact is :" +
				// sf.dataInput.primaryContact
				// + " Actual contact is :" +
				// sf.seleU.getTextFromWebElement(sf.cpc.primaryContactText), true);
			}

			// Click on Change primary contact checkbox
			sf.seleU.clickElementByJSE(sf.cpc.changePrimaryContactCheckbox.get(0));
			reportStatusPass(methodName + " Clicked on 'change primary contact' checkbox", true, false);
			sf.seleU.hardwait(2000);

			// Select new primary contact
			sf.seleU.clearAndEnterText(sf.cpc.selectPrimaryContactInput,
					Global.dataInput.contactFirstName + " " + Global.dataInput.contactLastName);
			sf.seleU.wait(3000);
			sf.seleU.enterText(sf.cpc.selectPrimaryContactInput, Keys.ARROW_DOWN);
			sf.seleU.hardwait(1000);
			sf.seleU.enterText(sf.cpc.selectPrimaryContactInput, Keys.ENTER);
			reportStatusPass(methodName + " Selected New primary contact as " + Global.dataInput.contactFirstName + " "
					+ Global.dataInput.contactLastName, true, false);
			sf.seleU.hardwait(2000);

			// Verify Rest contact fields autopopulated
			if (sf.seleU.getTextFromWebElement(sf.cpc.contactFirstName).equals(Global.dataInput.contactFirstName)
					&& sf.seleU.getTextFromWebElement(sf.cpc.contactLastName).equals(Global.dataInput.contactLastName)
					&& sf.seleU.getTextFromWebElement(sf.cpc.contactEmail)
							.equals(Global.dataInput.contactEmailAddress)) {
				reportStatusPass(methodName + " Verified new primary contact fields are autopopulated ", true, true);
			} else {
				reportStatusFail(" Invalid new primary contact details", true);
			}

			// upload evidence for contact change
			sf.seleU.enterText(sf.cpc.contactEvidenceUpload, Constants.SAMPLE_UPLOAD_FILE);
			sf.seleU.hardwait(5000);

			// Click on Next button
			sf.seleU.clickElementByJSE(sf.cpc.updatePrimaryContactNextButton);
			reportStatusPass(methodName + " Clicked on Next button in change primary contact page", true, false);

			Global.dataInput.primaryContact = Global.dataInput.contactFirstName + " "
					+ Global.dataInput.contactLastName;
			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Selecting 'change primary contact' is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * 1- Verify Created Contact Name
	 * 
	 * 2- Click on Next Button
	 * 
	 * @throws IOException
	 */
	public void verifyContactCreated() throws IOException {

		try {

			String methodName = "SFDC_Verify Contact Created@: ";

			if (sf.seleU.isElementPresent(sf.cc.alertOkButton)) {
				sf.seleU.clickElementByJSE(sf.cc.alertOkButton);
			}

			if (sf.seleU.isElementPresent(sf.cc.continueButton)) {
				sf.seleU.clickElementByJSE(sf.cc.continueButton);
			}

			// If duplicate found, Select'Create a new contact'
			if (sf.seleU.isElementPresent(sf.cc.contactDuplicateMessage)) {

				if (sf.seleU.isElementDisplayed(sf.cc.updateContactRadio)) {
					sf.seleU.clickElementByJSE(sf.cc.duplicateContactCheckboxAll.get(0));
					reportStatusPass(
							methodName + " Selected contact: "
									+ sf.seleU.getTextFromWebElement(sf.cc.potentialMatchDuplicateContactName.get(0)),
							true, false);
					sf.seleU.wait(3000);

					sf.seleU.clickElementByJSE(sf.cc.updateContactRadio);

					sf.seleU.wait(3000);
					reportStatusPass(
							methodName + sf.seleU.getTextFromWebElement(sf.cc.contactDuplicateMessage) + ",,, "
									+ sf.seleU.getTextFromWebElement(sf.cc.updateContactRadio) + " "
									+ sf.seleU.getTextFromWebElement(sf.cc.potentialMatchDuplicateContactName.get(0)),
							true, true);
				}
									
				// 2- Click on next Button
				sf.seleU.clickElementByJSE(sf.cc.validateContact_nextBtn);
				reportStatusPass(methodName + " Clicked on Next Button on Duplicate Contacts for create contact page",
						true, false);

				sf.seleU.wait(9000);
			}
			
			//validate manage relationship message is displayed				
			if(sf.seleU.isElementDisplayed(sf.cc.manageRelationshipsText))
			{
				reportStatusPass(methodName + " Manage Relationship message is displayed ", true, true);
				sf.seleU.hardwait(3000);
			}
			else
			{
				reportStatusFail(methodName + " Error in verifying Manage Relationship message ", true);
			}

			// Verify Created Contact Name
			if (sf.seleU.getTextFromWebElement(sf.cc.reviewContactNameText).contains(Global.dataInput.contactFirstName)
					&& sf.seleU.getTextFromWebElement(sf.cc.reviewContactNameText)
							.contains(Global.dataInput.contactLastName)) {
				reportStatusPass(methodName + " Contact :" + Global.dataInput.contactFirstName + "created successfully",
						true, true);
			}
			// else {
			// reportStatusFail(methodName + " Verifying contact creation is Unsuccessful ",
			// true);
			// }

		} catch (Throwable e) {
			reportStatusFail(" Verifying contact creation  is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * 1- Verify Created Contact Name- update the contact by selecting the duplicate
	 * contact from the list Selecting only business account 2. Once selected then
	 * the contact account number will be updated 2- Click on Next Button
	 * 
	 * @throws IOException
	 */
	public void verifyContactCreatedUpdated() throws IOException {

		try {
			String methodName = "SFDC_Verify Contact Created@: ";
			int index = 0;

			if (sf.seleU.isElementPresent(sf.cc.alertOkButton)) {
				sf.seleU.clickElementByJSE(sf.cc.alertOkButton);
			}

			if (sf.seleU.isElementPresent(sf.cc.continueButton)) {
				sf.seleU.clickElementByJSE(sf.cc.continueButton);
			}

			// If duplicate found
			if (sf.seleU.isElementPresent(sf.cc.contactDuplicateMessage)) {

				if (sf.seleU.isElementDisplayed(sf.cc.updateContactRadio)
						|| sf.seleU.isElementDisplayed(sf.cc.updateContactRadio_WithCreateNewContact)) {

					// Select only business account instead of service or billing account from the
					// duplicate matching contact list
					for (int i = 0; i < sf.cc.potentialMatchContactAccountName.size(); i++) {
						if (sf.seleU.getTextFromWebElement(sf.cc.potentialMatchContactAccountName.get(i)).trim()
								// .contains(sf.dataInput.busAccAbbr)) {
								.contains("ABQAAuto")
								|| sf.seleU.getTextFromWebElement(sf.cc.potentialMatchContactAccountName.get(i)).trim()
										// .contains(sf.dataInput.busAccAbbr)) {
										.contains("Auto_QA")) {
							sf.seleU.clickElementByJSE(sf.cc.duplicateContactCheckboxAll.get(i));
							index = i;
							break;
						}
					}

					sf.seleU.wait(2000);
					reportStatusPass(
							methodName + sf.seleU.getTextFromWebElement(sf.cc.contactDuplicateMessage) + ",,, "
									+ sf.seleU.getTextFromWebElement(sf.cc.updateContactRadio)
									+ " Contact Account Name is "
									+ sf.seleU.getTextFromWebElement(sf.cc.potentialMatchContactAccountName.get(index))
									+ "\n" + " Contact name is  "
									+ sf.seleU
											.getTextFromWebElement(sf.cc.potentialMatchDuplicateContactName.get(index)),
							true, true);

					Global.dataInput.businessAccountName = sf.seleU
							.getTextFromWebElement(sf.cc.potentialMatchContactAccountName.get(index));
					// System.out.println("Account name is" + sf.dataInput.businessAccountName);
				}

				// 2- Click on next Button
				sf.seleU.wait(4000);
				sf.seleU.clickElementByJSE(sf.cc.validateContact_nextBtn);
				reportStatusPass(methodName + " Clicked on Next Button on Duplicate Contacts for create contact page",
						true, false);

				sf.seleU.wait(5000);
			}

			sf.seleU.wait(5000);
			// Verify Created Contact Name
			if (sf.seleU.getTextFromWebElement(sf.cc.reviewContactNameText).contains(Global.dataInput.contactFirstName)
					&& sf.seleU.getTextFromWebElement(sf.cc.reviewContactNameText)
							.contains(Global.dataInput.contactLastName)) {
				reportStatusPass(methodName + " Contact :" + Global.dataInput.contactFirstName + "created successfully",
						true, true);
			}

			sf.seleU.wait(3000);
		} catch (Throwable e) {
			reportStatusFail(" Verifying contact creation  is Unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Next Button in contact cretaion page
	 * 
	 */
	public void clickOnNextInCreateContact() throws IOException {
		try {

			String methodName = "SFDC_Create Contact@: ";
			sf.seleU.wait(5000);
			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.cc.createNextContact_nextBtn);
			reportStatusPass(methodName + " Clicked on Next Button on Contact Details page", true, false);
			sf.seleU.wait(10000);
		} catch (Throwable e) {
			reportStatusFail(" Clicking Next button on Contact Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 * 
	 *                     1-Click on 'Create Another Contact' Button
	 * 
	 *                     2-Click on Next Button
	 * 
	 *                     3-Preapare test data for another contact
	 */
	public void selectCreateAnotherContact() throws IOException {
		try {

			String methodName = "SFDC_Contact Details@: ";

			// Click on 'Create Another Contact' Button
			sf.seleU.clickElementByJSE(sf.cc.createAnotherContactButton);
			reportStatusPass(methodName + " Clicked on Create Another Contact Button ", true, true);

			// Preapare test data for another contact
			Global.dataInput.secondContact_prepareContactData();

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Creating Multiple Contact   is Unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 * 
	 *                     1-Creating array to select the relationship type for the
	 *                     contact, which is being passed to the test class
	 */
	public String[] selectRelationshipType(int choice) throws IOException {

		String methodName = "SFDC_Select Relationship Type@: ";
		List<String> roles = new ArrayList<String>();
		roles.clear();
		roles.removeAll(roles);
		Global.dataInput.directRoles_Field();
		// *** Below are the relationships which populate for business, billing and
		// service account***
		// 0- Administrator
		// 1- Decider
		// 2 - Coach/Influencer
		// 3- Signing Authority
		// 4- Assessor
		// 5- Endorser
		// 6- General
		// 7- Technical
		// 8-Site Contact
		System.out.println(choice);
		switch (choice) {
		case 1:
			roles.add(Global.dataInput.directRoleOptions.get(2));
			roles.add(Global.dataInput.directRoleOptions.get(4));
			roles.add(Global.dataInput.directRoleOptions.get(5));
			roles.add(Global.dataInput.directRoleOptions.get(6));
			break;

		case 2:

			roles.add(Global.dataInput.directRoleOptions.get(3));
			break;

		case 3:
			roles.add(Global.dataInput.directRoleOptions.get(1));
			roles.add(Global.dataInput.directRoleOptions.get(2));
			roles.add(Global.dataInput.directRoleOptions.get(4));
			roles.add(Global.dataInput.directRoleOptions.get(5));
			break;

		case 4:
			roles.add(Global.dataInput.directRoleOptions.get(0));
			roles.add(Global.dataInput.directRoleOptions.get(3));
			break;

		case 5:
			roles.add(Global.dataInput.directRoleOptions.get(3));
			roles.add(Global.dataInput.directRoleOptions.get(8));
			break;

		case 7:
			roles.add(Global.dataInput.directRoleOptions.get(0));
			break;

		case 8:
			roles.add(Global.dataInput.directRoleOptions.get(6));
			roles.add(Global.dataInput.directRoleOptions.get(8));
			break;

		case 9:
			roles.add(Global.dataInput.directRoleOptions.get(6));
			break;

		case 10:
			roles.add(Global.dataInput.directRoleOptions.get(6));
			roles.add(Global.dataInput.directRoleOptions.get(7));
			break;

		case 11:
			roles.add(Global.dataInput.directRoleOptions.get(0));
			roles.add(Global.dataInput.directRoleOptions.get(6));
			break;

		case 12:
			roles.add(Global.dataInput.directRoleOptions.get(8));
			break;
		}

		String[] contact_rolesType = roles.toArray(new String[0]);
		Global.dataInput.directRoleOptions.clear();
		return contact_rolesType;
	}

	/**
	 * @throws IOException
	 * 
	 *                     1. Enter new contact information, First Name, Last Name,
	 *                     Email Id, Phone Number, mailing address information 2.
	 *                     Select business account or service account 3. Select
	 *                     relationship type roles 4. Provide email id based on
	 *                     duplicate or new contact email id. 5. Providing duplicate
	 *                     email id will generate duplicate contacts 6. Click on
	 *                     next button
	 */
	public void enterNewContactInfo(String relationshipType[], boolean noDuplicateEmail, boolean isServiceAccount,
			String accountToBeSearched) throws IOException {
		try {

			String methodName = "SFDC_Create Contact@: ";

			// 1- Enter Salutation and Title
			sf.seleU.switchToElementFrame(sf.cc.contactSalutationDropdown);
			sf.seleU.hardwait(5000);

			sf.seleU.selectTextFromDropDown(sf.cc.contactSalutationNewDropdown, Global.dataInput.contactSalutation);
			sf.seleU.clearAndEnterText(sf.cc.contactTitleInput, Global.dataInput.contactTitle);
			reportStatusPass(methodName + " Entered contact Saluation and Title As:"
					+ Global.dataInput.contactSalutation + " " + Global.dataInput.contactTitle, true, false);

			// 2- Enter First Name and Last Name
			sf.seleU.enterText(sf.cc.contactFirstNameInput, Global.dataInput.contactFirstName);
			reportStatusPass(methodName + " Entered contact First Name as : " + Global.dataInput.contactFirstName, true,
					false);
			sf.seleU.enterText(sf.cc.contactLastNameInput, Global.dataInput.contactLastName);
			reportStatusPass(methodName + " Entered contact Last Name as : " + Global.dataInput.contactLastName, true,
					false);

			// 3- Enter Email Address and phone Number
			// To enter new email id
			if (noDuplicateEmail) {
				sf.seleU.enterText(sf.cc.contactEmailAddressInput, Global.dataInput.contactEmailAddress);
				reportStatusPass(
						methodName + " Entered contact Email Address as : " + Global.dataInput.contactEmailAddress,
						true, false);

			} // To enter duplicate email id
			else {

				sf.seleU.enterText(sf.cc.contactEmailAddressInput, Global.dataInput.duplicateEmailAddress);
				reportStatusPass(
						methodName + " Entered contact Email Address as : " + Global.dataInput.duplicateEmailAddress,
						true, false);
			}
			// Enter phone number
			sf.seleU.enterText(sf.cc.contactPhoneInput, Global.dataInput.contactPhoneNumber);
			reportStatusPass(methodName + " Entered contact Phone Number as : " + Global.dataInput.contactPhoneNumber,
					true, false);

			// If Service account is selected
			if (isServiceAccount) {

				if (sf.seleU.isElementDisplayed(sf.cc.serviceContactRadio)) {
					sf.seleU.clickElementByJSE(sf.cc.serviceContactRadio);
					reportStatusPass(methodName + " Selected service account radio button ", true, false);
					sf.seleU.hardwait(1000);
					sf.seleU.clickElementByJSE(sf.cc.contactSelectAccountInput);

//					// Account to be searched
//					String serviceAccountToBeSearched;
//					serviceAccountToBeSearched = sf.dataInput.serviceAccountName;

					sf.seleU.hardwait(1000);
					sf.seleU.enterText(sf.cc.contactSelectAccountInput, accountToBeSearched);
					sf.seleU.hardwait(6000);
					sf.seleU.enterText(sf.cc.contactSelectAccountInput, Keys.ARROW_DOWN);
					sf.seleU.enterText(sf.cc.contactSelectAccountInput, Keys.ENTER);
					sf.seleU.hardwait(2000);
					reportStatusPass(methodName + " Entered Account as " + accountToBeSearched + " to be searched for",
							true, false);

					// check site contact is clicked by default while selecting service account
					if (sf.seleU.getElementAttribute(sf.cd.checkServiceRelationshipSiteContact, "class")
							.contains("ng-not-empty")) {
						reportStatusPass(methodName
								+ "Site contact is clicked by default as service account is selected instead of business account",
								true, false);
					} else {
						reportStatusFail("site contact in relationship type is not selected as default", true);
					}
				}
			}

			// if Business account needs to be selected
			else {
				if (sf.seleU.isElementDisplayed(sf.cc.businessContactRadio)) {

					// Search Account
					// String accountToBeSearched;

					sf.seleU.clickElementByJSE(sf.cc.businessContactRadio);
					sf.seleU.hardwait(1000);
					sf.seleU.clickElementByJSE(sf.cc.contactSelectAccountInput);

					// accountToBeSearched = sf.dataInput.businessAccountName;

					sf.seleU.hardwait(1000);
					sf.seleU.enterText(sf.cc.contactSelectAccountInput, accountToBeSearched);

					sf.seleU.hardwait(6000);
					sf.seleU.enterText(sf.cc.contactSelectAccountInput, Keys.ARROW_DOWN);
					sf.seleU.enterText(sf.cc.contactSelectAccountInput, Keys.ENTER);
					sf.seleU.hardwait(2000);
					reportStatusPass(methodName + " Entered Account as " + accountToBeSearched + " to be searched for",
							true, false);

					// Select and clicking the role option
					for (int i = 0; i < sf.cc.businessAccountCombineRoles.size(); i++) {
						String text = sf.seleU.getTextFromWebElement(sf.cc.businessAccountCombineRoles.get(i));
						sf.seleU.hardwait(2000);
						for (int k = 0; k < relationshipType.length; k++) {
							if (text.equals(relationshipType[k])) {
								sf.seleU.hardwait(2000);
								sf.seleU.clickElementByJSE(sf.cc.businessAccountCombineCheckBox.get(i));
								reportStatusPass(
										methodName + "Clicked on " + sf.seleU
												.getTextFromWebElement(sf.cc.businessAccountCombineRoles.get(i)),
										true, false);
							}
						}
					}
				}
			}

			sf.seleU.wait(2000);
			// Select Language
			sf.seleU.selectTextFromDropDown(sf.cc.languageDropdown, Global.dataInput.contactLanguage);
			reportStatusPass(methodName + " Selected Language as : " + Global.dataInput.contactLanguage, true, false);

			sf.seleU.wait(3000);

			// 5- Enter Mailing Address Information
			if (Global.dataInput.selectCanNotFindMailingAddress.equals("Yes")) {
				createContactAddress();
			} else {
				sf.seleU.enterText(sf.cc.contactMailingAddress, Global.dataInput.address);
				sf.seleU.wait(4000);
				try {
					sf.seleU.clickElementByJSE(sf.cc.mailingAddressTypeAheadOption);
				} catch (StaleElementReferenceException e) {

				}
				sf.seleU.wait(2000);
				if (sf.seleU.isElementPresent(sf.cc.mailingAddressTypeAheadOption)) {
					sf.seleU.clickElementByJSE(sf.cc.mailingAddressTypeAheadOption);
					sf.seleU.wait(2000);
				}
			}
			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.cc.contactDetails_nextBtn);
			reportStatusPass(methodName + " Clicked on Next Button on create contact page", true, false);

			sf.seleU.wait(10000);

			reportStatusPass(methodName + " Enter Mailing Address Information As : " + Global.dataInput.addressStreet,
					true, false);

			sf.seleU.wait(3000);
		} catch (Throwable e) {
			reportStatusFail(" Creating new contact is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * 1- Verify Created Contact Name and if Duplicate contact exist for the same
	 * email id then replace existing contact with new contact. 2- Click on Next
	 * Button to go to contact details page
	 * 
	 * 
	 * @throws IOException
	 */
	public void validateUpdatedContact(boolean newContactOption, String contactemail) throws IOException {

		try {

			String methodName = "SFDC_Verify Duplicate Contact And Update@: ";
			boolean noduplicate_Found = false;

			// If duplicate found, Select the any contact from the list'
			sf.seleU.wait(2000);
			if (sf.seleU.isElementPresent(sf.cc.contactDuplicateMessage)) {

				if (sf.cc.potentialMatchContacts.size() >= 1) {
					// Click the duplicate contact.
					if (sf.seleU
							.getTextFromWebElement(
									sf.cc.potentialMatchContactEmail.get(sf.cc.potentialMatchContactEmail.size() - 1))
							.contains(contactemail)) {
						sf.seleU.hardwait(2000);
						sf.seleU.wait(2000);
						sf.seleU.clickElementByJSE(sf.cc.potentialMatchCheckBoxContacts
								.get(sf.cc.potentialMatchCheckBoxContacts.size() - 1));
						sf.seleU.wait(2000);
						reportStatusPass(methodName + sf.seleU.getTextFromWebElement(sf.cc.contactDuplicateMessage)
								+ " and cliked on matched contact name " + "\n"
								+ sf.seleU.getTextFromWebElement(
										sf.cc.potentialMatchContactName.get(sf.cc.potentialMatchContactName.size() - 1))
								+ " with contact status as"
								+ sf.seleU.getTextFromWebElement(sf.cc.potentialMatchContactStatusText
										.get(sf.cc.potentialMatchContactStatusText.size() - 1))
								+ "\n" + " record type as "
								+ sf.seleU.getTextFromWebElement(sf.cc.potentialMatchContactRecordTypeText
										.get(sf.cc.potentialMatchContactRecordTypeText.size() - 1))
								+ " with matching percent "
								+ sf.seleU.getTextFromWebElement(sf.cc.potentialMatchContactMatchPercent
										.get(sf.cc.potentialMatchContactMatchPercent.size() - 1))
								+ "\n" + " email id as "
								+ sf.seleU.getTextFromWebElement(sf.cc.potentialMatchContactEmail
										.get(sf.cc.potentialMatchContactEmail.size() - 1))
								+ " Business Account Name is"
								+ sf.seleU.getTextFromWebElement(sf.cc.potentialMatchContactAccountName
										.get(sf.cc.potentialMatchContactAccountName.size() - 1)),
								true, true);

					} else {
						for (int i = 0; i < sf.cc.potentialMatchContactName.size(); i++) {
							sf.seleU.wait(2000);
							// Click the duplicate contact.
							if (sf.seleU.getTextFromWebElement(sf.cc.potentialMatchContactEmail.get(i)).trim()
									.contains(contactemail)) {
								System.out.println("Contact found");
								sf.seleU.hardwait(2000);
								sf.seleU.wait(2000);
								sf.seleU.clickElementByJSE(sf.cc.potentialMatchCheckBoxContacts.get(i));
								sf.seleU.wait(2000);
								reportStatusPass(methodName
										+ sf.seleU.getTextFromWebElement(sf.cc.contactDuplicateMessage)
										+ " and cliked on matched contact name " + "\n"
										+ sf.seleU.getTextFromWebElement(sf.cc.potentialMatchContactName.get(i))
										+ " with contact status as"
										+ sf.seleU.getTextFromWebElement(sf.cc.potentialMatchContactStatusText.get(i))
										+ "\n" + " record type as "
										+ sf.seleU
												.getTextFromWebElement(sf.cc.potentialMatchContactRecordTypeText.get(i))
										+ " with matching percent "
										+ sf.seleU.getTextFromWebElement(sf.cc.potentialMatchContactMatchPercent.get(i))
										+ " email id as "
										+ sf.seleU.getTextFromWebElement(sf.cc.potentialMatchContactEmail.get(i))
										+ " Business Account Name is"
										+ sf.seleU.getTextFromWebElement(sf.cc.potentialMatchContactAccountName.get(i)),
										true, true);

								break;
							}
						}
					}

				}
			} else {
				// No Duplicate contact found
				reportStatusPass(methodName + "No Duplicate contact found + ", true, false);
				sf.seleU.hardwait(2000);
				sf.seleU.switchToFrame(sf.cc.reviewContact_Frame);

				if (sf.seleU.getTextFromWebElement(sf.cc.reviewContactNameText)
						.contains(Global.dataInput.contactFirstName)
						&& sf.seleU.getTextFromWebElement(sf.cc.reviewContactNameText)
								.contains(Global.dataInput.contactLastName)) {

					reportStatusPass(methodName + " Contact :" + Global.dataInput.contactFirstName
							+ " contact created successfully", true, false);

					sf.seleU.clickElementByJSE(sf.cc.validateContact_NextBtnNoDuplicates);
					reportStatusPass(methodName + "Successfully clicked on Next button in review contact page + ", true,
							false);
					noduplicate_Found = true;
				}
			}

			// Select create new contact option radio button instead of update contact radio
			// button
			if (newContactOption) {
				if (sf.seleU.isElementDisplayed(sf.cc.selectNextActionCreateNewContactRadio)) {
					sf.seleU.clickElementByJSE(sf.cc.selectNextActionCreateNewContactRadio);

					sf.seleU.hardwait(2000);
					reportStatusPass(
							methodName + sf.seleU.getTextFromWebElement(sf.cc.contactDuplicateMessage) + ",,, "
									+ sf.seleU.getTextFromWebElement(sf.cc.selectNextActionCreateNewContactRadio),
							true, true);

					// 2- Click on next Button
					sf.seleU.clickElementByJSE(sf.cc.validateContact_nextBtn);
					reportStatusPass(methodName + " Clicked on Next Button on Duplicate Contact Verification Page ",
							true, false);

					sf.seleU.wait(4000);

					// Verify Created Contact Name
					if (sf.seleU.getTextFromWebElement(sf.cc.reviewContactNameText)
							.contains(Global.dataInput.contactFirstName)
							&& sf.seleU.getTextFromWebElement(sf.cc.reviewContactNameText)
									.contains(Global.dataInput.contactLastName)) {
						reportStatusPass(methodName + " Contact :" + Global.dataInput.contactFirstName
								+ " contact created successfully", true, false);
					}

					sf.seleU.clickElementByJSE(sf.cc.nextClickAfterContact);
					reportStatusPass(methodName + " Clicked on Next Button to go contact details page", true, false);

					sf.seleU.wait(5000);
				}
			}

			// Need to click on one more next button if we click update contact radio button
			// for duplicate contact
			else if (noduplicate_Found == false) {
				sf.seleU.wait(2000);

				// 2- Click on next Button
				sf.seleU.clickElementByJSE(sf.cc.validateContact_nextBtn);
				reportStatusPass(methodName + " Clicked on Next Button on Duplicate Contact Verification Page ", true,
						false);
				sf.seleU.wait(4000);

				// Verify Created Contact Name
				if (sf.seleU.getTextFromWebElement(sf.cc.reviewContactNameText)
						.contains(Global.dataInput.contactFirstName)
						&& sf.seleU.getTextFromWebElement(sf.cc.reviewContactNameText)
								.contains(Global.dataInput.contactLastName)) {
					reportStatusPass(methodName + " Contact :" + Global.dataInput.contactFirstName
							+ " contact created successfully", true, false);
				}
				sf.seleU.hardwait(4000);
				sf.seleU.clickElementByJSE(sf.cc.validateContact_nextBtn);
				reportStatusPass(methodName + " Clicked on Next Button in Review Contact Page", true, false);

				sf.seleU.wait(8000);
				sf.seleU.waitElementToBeVisible(sf.cc.nextClickAfterContact);
				sf.seleU.clickElementByJSE(sf.cc.nextClickAfterContact);
				reportStatusPass(methodName + " Clicked on Next Button to go contact details page", true, false);

				sf.seleU.wait(5000);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verifying contact creation  is Unsuccessful", e);
			e.printStackTrace();
		}
	}

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

	/**
	 * Verify Field is present
	 * 
	 * @throws IOException
	 */
	public void verifyFieldPresent(String fieldName, WebElement element) throws IOException {

		try {

			// Verify Field is present
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass("Validated " + fieldName + " is present", true, true);
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
	 *                     1.Select Navigation Menu
	 * 
	 *                     2.Select Contact
	 */
	public void selectContacts() throws IOException {

		String methodName = "SFDC_Create Contact@: ";
		try {
			// Select Navigation Menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Home
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.contactsMenu);
			reportStatusPass(methodName + " Selected Contact from menu", true, false);
			sf.seleU.hardwait(10000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not open Contact tab", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     1.Select Navigation Menu
	 * 
	 *                     2.Select Contact
	 *                     
	 *                     3.Open Any contact
	 */
	public void selectAndOpenAnyContacts() throws IOException {

		String methodName = "SFDC_Create Contact@: ";
		try {
			// Select Navigation Menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Home
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.contactsMenu);
			reportStatusPass(methodName + " Selected Contact from menu", true, false);
			sf.seleU.hardwait(10000);
			
			// 2- Select All Contacts
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.cc.allContactsOption);
			sf.seleU.hardwait(4000);
			reportStatusPass(methodName + " Selected All Contact Option", true, false);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.lead.leadsAllRecords.get(0));
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + " Contact found and Opening First Contact:  " ,true,false);
			sf.seleU.hardwait(2000);			

		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not open Contact ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Voice of R4B links are present on Contact Page
	 * 
	 *                     Verify if link is open in new tab
	 */
	public void verifyVoiceOfLinksPresentAtContact() throws IOException {
		try {
			String methodName = "SFDC_Account@: ";

			// Select Links from Accounts page
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.acc.links);
			sf.seleU.hardwait(2000);

			// Verify Verify Voice of R4B links are present on Account Page
			String str = "Voice of R4B";
			if (str.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.acc.voiceOfLinks)))
				reportStatusPass(methodName + " Verifed Voice of R4B links are present on Contact Page", true, true);

			else
				reportStatusFail(" Verification Failed- Verifed Voice of R4B links are not present on Contact Page ",
						true);

			// Select account
			sf.seleU.clickElementByJSE(sf.acc.voiceOfLinks);
			reportStatusPass(methodName + " Clicked on Voice of R4B Links from Cotact", true, false);
			sf.seleU.hardwait(4000);
			sf.seleU.switchWindow(4);
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
	

}
