package sfdc.pages.sales;

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
import com.sfdc.data.InputData_Sales;
import com.sfdc.data.InputData_Service;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla, Date : 20/Apr/2021
 * 
 *         SFDC Lead page
 */
public class SFDC_Lead_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	SFDC_AllPageObjects sf;
	static String methodName = "SFDC_Lead@: ";

	public SFDC_Lead_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 * 				Select the standard fields
	 * 
	 *  			Create Lead
	 *  
	 *   			Lead Details page will be appeared 
	 *                  
	 * 
	 */
	public void createLead() throws IOException {
		try {

			sf.seleU.wait(5000);
			
			// 1-Selecting Leads from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(2000);

			sf.seleU.clickElementByJSE(sf.home.leadMenu);
			reportStatusPass(methodName + " Selected Leads from menu", true, true);
			sf.seleU.hardwait(2000);
			
			sf.seleU.clickElementByJSE(sf.lead.newLeadButton);
			
			// Verify Create Lead form is displayed
			verifyFieldDisplayed("Create Lead Form", sf.lead.leadPageHeader);
			
			//Enter text for Name - First and Last
			sf.seleU.enterText(sf.lead.firstNameTextBox, sf.dataInput.contactFirstName);
			reportStatusPass(methodName + " Entered Lead first Name as : " +  sf.dataInput.contactFirstName, true, true); 	
			
			sf.seleU.enterText(sf.lead.lastNameTextBox, sf.dataInput.contactLastName);
			reportStatusPass(methodName + " Entered Lead last Name as : " +  sf.dataInput.contactLastName, true, true);
					
			//Enter text for Company
			sf.seleU.enterText(sf.lead.companyTextBox, sf.dataInput.contactFirstName);
			reportStatusPass(methodName + " Entered Comapny Name as : " +  sf.dataInput.contactFirstName, true, true); 
			InputData_Sales.leadBusAccountName = sf.dataInput.contactFirstName;
			//Enter text for Email
			sf.seleU.enterText(sf.lead.emailTextBox, sf.dataInput.contactEmailAddress);
			reportStatusPass(methodName + " Entered Email : " +  sf.dataInput.contactEmailAddress, true, true); 
			
			//Enter number for Phone
			sf.seleU.enterText(sf.lead.phoneTextBox, sf.dataInput.phoneNumberWithoutCode);
			reportStatusPass(methodName + " Entered Phone : " +  sf.dataInput.phoneNumberWithoutCode, true, true); 
			
			sf.seleU.isElementDisplayed(sf.lead.subIndustrylabel);
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + "Sub Industry label present : ", true, true); 

			//Enter number for numberOfEmployees
			sf.seleU.enterText(sf.lead.noEmpTextBox, sf.dataInput.numberOfEmployees);
			reportStatusPass(methodName + " Entered Number Of Employees : " +  sf.dataInput.numberOfEmployees, true, true);
			
			//Enter text for Street
			sf.seleU.enterText(sf.lead.streetTextBox, sf.dataInput.addressStreet);
			reportStatusPass(methodName + " Entered Street : " +  sf.dataInput.addressStreet, true, true);
			
			//Enter text for City
			sf.seleU.enterText(sf.lead.cityTextBox, sf.dataInput.addressCity);
			reportStatusPass(methodName + " Entered City : " +  sf.dataInput.addressCity, true, true);
			
			//Enter text for State
			sf.seleU.enterText(sf.lead.stateTextBox, sf.dataInput.addressState);
			reportStatusPass(methodName + " Entered State : " +  sf.dataInput.addressState, true, true);
			
			//Enter text for Postal Code
			sf.seleU.enterText(sf.lead.postalCodeTextBox, sf.dataInput.addressPostalCode);
			reportStatusPass(methodName + " Entered Postal Code : " +  sf.dataInput.addressPostalCode, true, true);
			
			//Enter text for Country
			sf.seleU.enterText(sf.lead.countryTextBox, sf.dataInput.addressCountry);
			reportStatusPass(methodName + " Entered Country  : " +  sf.dataInput.addressCountry, true, true);
			
			//Save the lead
			sf.seleU.clickElementByJSE(sf.lead.saveLead);
			reportStatusPass(methodName + " Saved the lead", true, true); 
			
			sf.seleU.wait(10000);  //added extra time as it fails
			//Verify lead detail page
			verifyFieldDisplayed("Lead", sf.lead.leadDetailPageHeader);
			
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in creating lead", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Convert button
	 */
	public void covertButtonLead() throws IOException {

		String methodName = "SFDC_Lead_Details@: ";

		try {

			sf.seleU.wait(3000);
			if (!sf.seleU.isElementDisplayed(sf.lead.convertLead))
				sf.seleU.clickElementByJSE(sf.lead.showMoreActionsButton);
			sf.seleU.clickElementByJSE(sf.lead.convertLead);

			sf.seleU.wait(3000);
			reportStatusPass(methodName + " Clicked on Convert button from Lead ", true, false);
			sf.seleU.wait(6000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not click on Convert Button", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value is displayed
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
	 * Select Lead from navigation menu
	 * And open first lead
	 * @throws IOException
	 */
	public void selectAndOpenAnyLead() throws IOException {

		try {

			// 1-Selecting Leads from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(2000);

			sf.seleU.clickElementByJSE(sf.home.leadMenu);
			reportStatusPass(methodName + " Selected Lead from menu", true, false);
			sf.seleU.hardwait(2000);

			// 2- Select All Leads
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.lead.allOpenLeadsOption);
			sf.seleU.hardwait(4000);
			reportStatusPass(methodName + " Selected All Open Leads Option", true, false);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.lead.leadsAllRecords.get(0));
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + " Lead found and Opening First Lead::  " ,true,false);
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Selecting and Opening a Lead is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @throws IOException
	 * 
	 *                     Validation of Lead Conversion to Business Account
	 */
	public void covertLeadToBusAcc() throws IOException {

		String methodName = "SFDC_Lead_Details@: ";

		try {

			sf.seleU.wait(3000);
			// Select Industry
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.cba.industryClick);
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.cba.selectFinancialSer);
			sf.seleU.wait(3000);
			reportStatusPass(methodName + " Selected Industry as " + "Financial Services", true, false);
			
			//Select Sub- Industry
			sf.seleU.clickOnElement(sf.cba.subIndustryClick);
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.cba.selectBanking);
			sf.seleU.wait(3000);
			reportStatusPass(methodName + " Selected Sub-Industry as " + "Banking", true, false);
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.wait(3000);
			sf.cba.busAddressInput.sendKeys(Keys.BACK_SPACE);
			sf.seleU.wait(6000);
			sf.seleU.clickOnElement(sf.cba.busAddressInputclick);
			sf.seleU.wait(6000);
			
			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.cba.continueToNextStep);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Click on Next Button on create buniess account page", true, false);			

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Lead Conversion to Business Account", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Validation of Lead Conversion to Business Account's Contact
	 */
	public void covertLeadToBusAccContact() throws IOException {

		String methodName = "SFDC_Lead_Details@: ";

		try {

			sf.seleU.hardwait(5000);
				sf.seleU.clickElementByJSE(sf.cc.continueButton); 
				sf.seleU.hardwait(6000);

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
			   

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Lead Conversion to Business Account's Contact", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Validation of Business Account, Contact info w.r.to lead info
	 */
	public void ValidateDetailsOnBusAcc() throws IOException {

		String methodName = "SFDC_Lead_Details@: ";

		try {

				sf.seleU.hardwait(5000);
				// Verify Lead details are matching with Bus Account details or not 

				if (InputData_Sales.leadBusAccountName.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.ad.accountNameValueText)) &&
						sf.dataInput.numberOfEmployees.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.ad.noOfEmpInDetails)) )
					reportStatusPass(methodName + " Verification Passed- Verifed Lead has been sucessfully conerted. The Account Number, Phone and Number of employees are the same as of lead details", true, true);

				else
					reportStatusFail(" Verification Failed- Lead has been transfered but there is difference in matching details ",
							true);
								
				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating info on Business Account's Page", e);
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
	 * Select Lead from navigation menu
	 * @throws IOException
	 */
	public void selectLead() throws IOException {

		try {

			// Selecting Leads from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(2000);

			sf.seleU.clickElementByJSE(sf.home.leadMenu);
			reportStatusPass(methodName + " Selected Lead from menu", true, false);
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Selecting a Lead is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Search Lead from lead page
	 * @throws IOException
	 */
	public void searchLead() throws IOException {

		try {

			// Selecting Leads from menu
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.lead.leadSearch,InputData_Sales.leadBusAccountName);
			sf.seleU.clickEnter();
			sf.seleU.hardwait(2000);
			if (InputData_Sales.noResultsLead.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.lead.noSearch)))
				reportStatusPass(methodName + " Verification Sucessful- Verifed Converted lead is not present in Lead", true, true);
			else
				reportStatusFail(" Verification Failed- Converted lead is present in Lead ",
						true);
		} catch (Throwable e) {
			reportStatusFail(" Verifying Coverted Lead is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
}