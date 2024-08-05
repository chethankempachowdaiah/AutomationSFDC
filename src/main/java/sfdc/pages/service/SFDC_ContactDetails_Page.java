package sfdc.pages.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.data.InputData_Sales;
import com.sfdc.data.InputData_Service;
import com.sfdc.lib_pages.SFDC_Files_Page;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 27/April/2020
 * 
 *         SFDC Contact Details page
 */
public class SFDC_ContactDetails_Page extends MyListener {

	// Creating all the pages Object to interact with pages
//	SFDC_AllPageObjects sf;
	public static SFDC_AllPageObjects sf;
	String methodName = "SFDC_Contact Details@: ";

	public SFDC_ContactDetails_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate Service Account and Roles as Site Contact in
	 *                     Contact Related Tab
	 * 
	 *                     Validate Contact Details in Contact Details tab
	 * 
	 */
	public void validateSiteContact() throws IOException {
		try {

			String phoneInExpectedFormat = "(" + Global.dataInput.phoneNumber.substring(0, 3) + ")"
					+ Global.dataInput.phoneNumber.substring(3, 6) + "-" + Global.dataInput.phoneNumber.substring(6, 10)
					+ "x" + Global.dataInput.phoneNumber.substring(10, 16);

			// Validate Contact Details in Contact details tab
			sf.seleU.clickElementByJSE(sf.cd.detailsTab.get(1));
			sf.seleU.wait(3000);
			verifyFieldValue("Contact Record Type", sf.cd.contactRecordTypeText, Global.dataInput.contactRecordType);
			verifyFieldValue("Contact Owner", sf.cd.contactOwnerText, InputData.username);
			verifyFieldValue("Contact Name", sf.cd.contactNameText, Global.dataInput.contactSalutation + " "
					+ Global.dataInput.contactFirstName + " " + Global.dataInput.contactLastName);
			verifyFieldValue("Contact Account Name", sf.cd.accountNameText, Global.dataInput.serviceAccountName);
			verifyFieldValue("Contact Title", sf.cd.contactTitle, Global.dataInput.contactTitle);
			verifyFieldValue("Contact Language", sf.cd.languagePreferenceText, Global.dataInput.contactLanguage);
			verifyFieldValue("Contact Status", sf.cd.contactStatusText, Global.dataInput.status_Active);
			verifyFieldValue("Contact Phone", sf.cd.contactPhoneText, phoneInExpectedFormat);
			verifyFieldValue("Contact Email", sf.cd.contactEmailText, Global.dataInput.contactEmailAddress);

			// Validate Service Account and Roles as Site Contact in Contact Related Tab
			sf.seleU.clickElementByJSE(sf.cd.relatedTab.get(1));
			verifyFieldValue("Service Account Name", sf.cd.serviceAccountInRelatedAccount,
					Global.dataInput.serviceAccountName);
			verifyFieldValue("Contact Role", sf.cd.roleSiteContactText, Global.dataInput.siteContact);

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying Site Contact field values", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Contact Related Tab
	 * 
	 *                     Click on 'Add Relationship Button'
	 * 
	 *                     Enter Account Name to be added as relationship
	 * 
	 *                     Select Available Role
	 * 
	 *                     Save Relationship
	 * 
	 *                     Verify Relationship added successfully
	 * 
	 *                     Verify Direct Relationship Options( View, Edit)
	 * 
	 *                     Verify Indirect Relationship Options(View, Edit, Remove)
	 */
	public void createAndRemoveRelationshipContactBA() throws IOException {
		try {

//			String chosenRole = "";

			// Click on Contact Related Tab
			sf.seleU.hardwait(5000);
		    sf.seleU.clickElementByJSE(sf.cd.relatedTab.get(0));
		    reportStatusPass("Clicked on Contact Related Button", true, false);
		    sf.seleU.hardwait(5000);
			
			//Click on Manage Relationship
			sf.seleU.clickElementByJSE(sf.cd.manageRelationshipTabButton);
            reportStatusPass("Clicked on Manage Relationships Button", true, false);
            sf.seleU.hardwait(5000);
            
            // Search business account to add indirect role
            sf.seleU.hardwait(4000);
//          sf.seleU.clearAndEnterText(sf.cc.searchAccount, InputData.account_ON);
            sf.seleU.clearAndEnterText(sf.cc.searchAccount, InputData_Service.businessAccountForInternalGuidedCase);
			sf.seleU.hardwait(5000);
//			sf.seleU.clickElementByJSE(driver.findElement(By.xpath("//span[contains(text(),'" + InputData.account_ON + "')]")));
			sf.seleU.clickElementByJSE(driver.findElement(By.xpath("//span[contains(text(),'" + InputData_Service.businessAccountForInternalGuidedCase + "')]")));
			sf.seleU.wait(3000);			
//			reportStatusPass(methodName + " Searched Business Account :  " + InputData.account_ON, true, false);
			reportStatusPass(methodName + " Searched Business Account :  " + InputData_Service.businessAccountForInternalGuidedCase, true, false);
			
			// Select access level Business
			sf.seleU.clickElementByJSE(sf.cc.accessLevelBusiness);
			reportStatusPass(methodName + " Selected access level as 'Business' ", true, false);

			// select relationship type
			sf.seleU.clickElementByJSE(sf.cc.checkboxAdministrator);
			sf.seleU.hardwait(5000);
			reportStatusPass(methodName + " Selected role as 'Administrator' ", true, false);
			
			//click on Next
			sf.seleU.clickElementByJSE(sf.cc.nextStep);
			sf.seleU.hardwait(3000);
						
			//Click on Add Indirect Roles
//          sf.seleU.switchToElementFrame(sf.cd.addIndirectRoles);
//			sf.seleU.wait(3000);
//			sf.seleU.clickElementByJSE(sf.cd.addIndirectRoles.get(0));
//			reportStatusPass("Clicked on Add Indirect Roles Button", true, false);
			
			//Click and enter Business account in Search account
//			sf.seleU.enterText(sf.cd.searchAccount, Global.dataInput.parentAccountName );
//			sf.seleU.wait(2000);
			
//			File file = new File("C:\\Users\\Robin.Mangla\\Documents\\sample.txt");
//			//Instantiating the PrintStream class
//			PrintStream stream = new PrintStream(file);
//			System.out.println("From now on "+file.getAbsolutePath()+" will be your console");
//			System.setOut(stream);			 
//			System.out.println(driver.findElement(By.xpath("//*")).getAttribute("innerHTML"));
//			sf.seleU.wait(3000);
//			driver.findElement(By.xpath("//a[.='Integrated_BA1 -  - Business']")).click();
//			sf.seleU.clickElementByJSE(driver.findElement(By.xpath("//a[.='" + InputData.busAccNameContactRelationship + "']")));
//			sf.seleU.wait(3000);
//			System.out.println(driver.findElement(By.xpath("//a[.='" + InputData.busAccNameContactRelationship + "']")));
//			sf.seleU.enterText(sf.cd.searchAccount, Keys.ARROW_DOWN);
//			sf.seleU.enterText(sf.cd.searchAccount, Keys.ENTER);
//			sf.seleU.enterText(sf.cd.searchAccount, Keys.TAB);
//			sf.seleU.wait(3000);
//			reportStatusPass("Entered Account Name to be searched as " + Global.dataInput.parentAccountName, true, false);
			
			//Select indirect role
//			sf.seleU.clickElementByJSE(sf.cd.roles.get(0));
//			reportStatusPass("Clicked on 'Indirect Role'", true, false);
//			sf.seleU.wait(3000);
			
			//click on Next
//			sf.seleU.clickElementByJSE(sf.cd.nextButton);
//			sf.seleU.wait(3000);
			
			//click on view all under Related Accounts
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.cd.viewAllButton);
			reportStatusPass("Clicked on 'View All'", true, false);
			sf.seleU.hardwait(4000);		
			
//			// Click on 'Add Relationship Button'
//			sf.seleU.clickElementByJSE(sf.cd.addRelationShipButton);
//			sf.seleU.wait(5000);
//			reportStatusPass("Clicked on 'Add Relationship Button'", true, false);
//
//			// Enter Account Name to be added as relationship
//			sf.seleU.enterText(sf.cd.searchAccountsInput, Global.dataInput.parentAccountName);
//			sf.seleU.enterText(sf.cd.searchAccountsInput, Keys.ENTER);
//			sf.seleU.wait(2000);
//			sf.seleU.clickElementByJSE(sf.cd.searchedAccountName);
//			reportStatusPass("Entered Account Name to be added as " + Global.dataInput.parentAccountName, true, false);
//
//			// Select Available Role
//			sf.seleU.clickElementByJSE(sf.cd.availableRoleList.get(0));
//			sf.seleU.clickElementByJSE(sf.cd.moveSelectionToChosenButton);
//			chosenRole = sf.seleU.getTextFromWebElement(sf.cd.chosenRole);
//			reportStatusPass("Selected Contact Role as  " + chosenRole, true, false);
//
//			// Save Relationship
//			sf.seleU.clickElementByJSE(sf.cd.saveButton);
//			reportStatusPass("Clicked on Save Button", true, false);
//
//			// Verify Relationship added successfully
//			verifyFieldPresent("Account Contact Relationship created msg", sf.cd.accountContactRelationCreatedMsg);

			// Verify Added Relationship is Indirect
			if (sf.seleU.getElementAttribute(sf.cd.checkBoxes.get(2), "alt").equals("False")
					&& sf.seleU.getElementAttribute(sf.cd.checkBoxes.get(3), "alt").equals("True")){
			reportStatusPass("Verified relationship Type  is Indirect for the added account", true, true);
			}
			else {
				reportStatusFail(" Error in Verifying Relationship Type as indirect for added account ", true);
			}
			
			boolean isIndirectRelationshipRemoved = false;
			// click on account menu
			for (int i = 0; i < sf.cd.accountNames.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.cd.accountNames.get(i)).contains(InputData_Service.businessAccountForInternalGuidedCase)){
					sf.seleU.clickElementByJSE(sf.cd.actionsButton);
					sf.seleU.hardwait(3000);
					sf.seleU.clickElementByJSE(sf.cd.removeRelationship.get(2));
					sf.seleU.hardwait(2000);
					sf.seleU.clickElementByJSE(sf.cd.removeRelationshipWindow);					
					verifyFieldPresent("Account Contact Relationship Deleted msg", sf.cd.accountContactRelationDeletedMsg);
					reportStatusPass(methodName + " Indirect Relationship Removed " , true, false);					
					isIndirectRelationshipRemoved = true;
					break;
				}
			}
			
			if (!isIndirectRelationshipRemoved ) {
				reportStatusFail(methodName + " Error in removing Indirect Relationship:  " , true);
			}
			
//			if ((sf.seleU.getElementAttribute(sf.cd.directCheckboxes.get(0), "alt").equals("True")
//					&& sf.seleU.getElementAttribute(sf.cd.directCheckboxes.get(1), "alt").equals("False"))
//					|| (sf.seleU.getElementAttribute(sf.cd.directCheckboxes.get(0), "alt").equals("False")
//							&& sf.seleU.getElementAttribute(sf.cd.directCheckboxes.get(1), "alt").equals("True"))) {
//				reportStatusPass("Verified relationshipType  is Indirect for the added account", true, true);
//
//			} else {
//				reportStatusFail(" Error in Verifying Relationship Type as indirect for added account ", true);
//			}
//
//			int indirectAccIndex = 0;
//			for (int accIndex = 0; accIndex < sf.cd.relatedAccountShowMoreActions.size(); accIndex++) {
//
//				sf.seleU.clickElementByJSE(sf.cd.relatedAccountShowMoreActions.get(accIndex));
//				sf.seleU.wait(2000);
//				ArrayList<String> relationshipList = new ArrayList();
//				for (int i = 0; i < sf.cd.showMoreActuionsAllRelationpOptions.size(); i++) {
//					if (sf.seleU.isElementDisplayed(sf.cd.showMoreActuionsAllRelationpOptions.get(i))) {
//						relationshipList.add(sf.cd.showMoreActuionsAllRelationpOptions.get(i).getText());
//					}
//				}
//
//				if (sf.seleU.getElementAttribute(sf.cd.directCheckboxes.get(accIndex), "alt").equals("True")) {
//					System.out.println(relationshipList);
//					validateRelationship("Direct", relationshipList);
//				} else {
//					System.out.println(relationshipList);
//					indirectAccIndex = accIndex;
//					validateRelationship("Indirect", relationshipList);
//
//					verifyFieldValue("Contact Account Name", sf.cd.accountsInRelatedAccounts.get(accIndex),
//							Global.dataInput.parentAccountName);
//					verifyFieldValue("Contact Role", sf.cd.rolesValue.get(accIndex), chosenRole);
//
//				}
//
//				sf.seleU.clickElementByJSE(sf.cd.relatedAccountShowMoreActions.get(accIndex));
//				sf.seleU.wait(4000);
//
//			}
//
//			sf.seleU.clickElementByJSE(sf.cd.relatedAccountShowMoreActions.get(indirectAccIndex));
//			removeIndirectRelationship();

		} catch (Throwable e) {
			reportStatusFail(" Error in Creating and Removing Relationship Between Contact and Business Account ", e);
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * @throws IOException
	 * 
	 *                     Click on Contact Related Tab
	 * 
	 *                     Click on 'Manage Relationship Button'
	 * 
	 *                     Verify Account Name to be added as relationship
	 * 
	 *                     Select SA Role
	 * 
	 *                     select available SA to approve newly created contact as SA
	 * 
	 *                     Verify Relationship added successfully
	 * 
	 *                     Verify Direct Relationship Options( View, Edit)
	 * 
	 *                     Verify Indirect Relationship Options(View, Edit, Remove)
	 */
	public void assignSigningAuthority() throws IOException {
		try {

//			String chosenRole = "";

			// Click on Contact Related Tab
			sf.seleU.hardwait(5000);
		    sf.seleU.clickElementByJSE(sf.cd.relatedTab.get(0));
		    reportStatusPass("Clicked on Contact Related Button", true, false);
		    sf.seleU.hardwait(5000);
			
			//Click on Manage Relationship
			sf.seleU.clickElementByJSE(sf.cd.manageRelationshipTabButton);
            reportStatusPass("Clicked on Manage Relationships Button", true, false);
            sf.seleU.hardwait(5000);
            
            // validate  business account to add indirect role
            sf.seleU.hardwait(4000);
//          sf.seleU.clearAndEnterText(sf.cc.searchAccount, InputData.account_ON);
            
            verifyFieldValue("Bussiness Account", sf.cc.searchAccount, Global.dataInput.businessAccountName);
          
			sf.seleU.hardwait(5000);

			reportStatusPass(methodName + " Verified Business Account is:  " + Global.dataInput.businessAccountName, true, false);
			
			// Select access level Business
			sf.seleU.clickElementByJSE(sf.cc.accessLevelBusiness);
			reportStatusPass(methodName + " Selected access level as 'Business' ", true, false);

			
			// select relationship type as signing authority
			   sf.seleU.clickElementByJSE(sf.cc.buttonSigningAuthority);
			   sf.seleU.hardwait(4000);
				reportStatusPass(methodName + " Selected role as 'Signing Authority' ", true, false);
				
		    //check for signing authority approval message
			   verifyFieldValue("Signing Authority Approval", sf.cc.signingAuthorityMessage, "Signing Authority role requires an additional approval");
	           sf.seleU.hardwait(5000);
			   sf.seleU.clickElementByJSE(sf.cc.nextStep);
			   sf.seleU.hardwait(3000);

			// choose From signing authority List
			  sf.seleU.hardwait(5000);
			  sf.seleU.clickElementByJSE(sf.cc.SAContactRbtn);
			  sf.seleU.wait(5000);
			  sf.seleU.clickElementByJSE(sf.cc.nextStep);			
			  sf.seleU.wait(3000);
		
				    
			
		
		} catch (Throwable e) {
			reportStatusFail(" Error in Creating and Removing Relationship Between Contact and Business Account ", e);
			e.printStackTrace();
		}
	}
	
	public void validateDuplicateBusinessContactActivation(boolean accessLevelBusiness) throws IOException
	{
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
			
			//validate duplicate contact gets activated		
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.cd.relatedTab.get(0));						
			reportStatusPass(methodName + " Clicked on related tab ", true, false);
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.cd.viewAllRelatedAccount);
			sf.seleU.hardwait(3000);
			
			if(accessLevelBusiness)
			{
				if (sf.seleU.getElementAttribute(sf.cd.checkBoxes.get(2), "alt").equals("False")
			      && sf.seleU.getElementAttribute(sf.cd.checkBoxes.get(3), "alt").equals("True")
			      && sf.seleU.getTextFromWebElement(sf.ar.accountNameValue.get(1)).equals(InputData.account_ON)
			      && sf.seleU.getElementAttribute(sf.cd.checkBoxes.get(0), "alt").equals("True")
			      && sf.seleU.getElementAttribute(sf.cd.checkBoxes.get(1), "alt").equals("True")
			      && sf.seleU.getTextFromWebElement(sf.ar.accountNameValue.get(0)).equals(Global.dataInput.businessAccountName))
			   {
			     reportStatusPass(methodName + "Inactive business contact becomes active and relationship type as Indirect to  " 
			     + InputData_Service.businessAccountForInternalGuidedCase + " Business Contact is Active and Relationship Type is Direct to Account " + Global.dataInput.businessAccountName, true, true);
			   }
			
			else
			   {
			     reportStatusFail(methodName + "Error in activating Inactive Business Contact ", true);
			   }
			}
			if(!accessLevelBusiness)
			{
				if (sf.seleU.getElementAttribute(sf.cd.checkBoxes.get(2), "alt").equals("False")
				   && sf.seleU.getElementAttribute(sf.cd.checkBoxes.get(3), "alt").equals("True")
				   && sf.seleU.getTextFromWebElement(sf.ar.accountNameValue.get(1)).equals(InputData_Service.businessAccountForInternalGuidedCase)
				   && sf.seleU.getElementAttribute(sf.cd.checkBoxes.get(4), "alt").equals("True")
				   && sf.seleU.getElementAttribute(sf.cd.checkBoxes.get(5), "alt").equals("True")
				   && sf.seleU.getTextFromWebElement(sf.ar.accountNameValue.get(2)).equals(Global.dataInput.businessAccountName))
				{
				  reportStatusPass(methodName + "Inactive business contact becomes active and relationship type as direct to  " 
				  + Global.dataInput.businessAccountName + " Business Contact is Active and Relationship Type is Indirect to Account " + InputData_Service.businessAccountForInternalGuidedCase, true, true);
				}
						
			else
				{
				  reportStatusFail(methodName + "Error in activating Inactive Business Contact ", true);
				}
			}
		}
		catch (Throwable e) 
		{
			reportStatusFail(" Error in Creating Relationship Contact Billing And ServiceAccount ", e);
			e.printStackTrace();
		}
	}
		
	public void createRelationshipContactBillingAndServiceAccount() throws IOException 
	{
		try {
			// Click on Contact Related Tab
			sf.seleU.hardwait(5000);
		    sf.seleU.clickElementByJSE(sf.cd.relatedTab.get(0));
		    reportStatusPass(methodName + "Clicked on Contact Related Button", true, false);
		    sf.seleU.hardwait(5000);
			
			//Click on Manage Relationship
			sf.seleU.clickElementByJSE(sf.cd.manageRelationshipTabButton);
            reportStatusPass(methodName + "Clicked on Manage Relationships Button", true, false);
            sf.seleU.hardwait(5000);
            
            //select access level
            sf.seleU.clickElementByJSE(sf.cc.accessLevelCustom); 
            sf.seleU.wait(3000);
            sf.seleU.clickElementByJSE(sf.cc.nextStep);
 		    sf.seleU.wait(3000);
 		    
 		    //validate parent account showing on top, table headers are displayed, account field and satus are displayed
 		    verifyFieldValue("Showing table for Accounts in:", sf.cc.parentBusinessAccount, Global.dataInput.businessAccountName);
// 		    verifyFieldValue("Showing table for Accounts in:", sf.cc.parentBusinessAccount, InputData_Service.businessAccountForInternalGuidedCase);
 		    sf.seleU.hardwait(4000);		    
// 		    if(sf.seleU.isElementDisplayed(sf.cc.billingAccountField) && sf.seleU.isElementDisplayed(sf.cc.activeStatusField)
// 		    && sf.seleU.isElementDisplayed(sf.cc.accountNameField) && sf.seleU.isElementDisplayed(sf.cc.accountNumberField)
// 		    && sf.seleU.isElementDisplayed(sf.cc.addressField) && sf.seleU.isElementDisplayed(sf.cc.roleField))
// 		    {
// 		    	reportStatusPass(methodName + " Account Field, Accout Status and table headers are displayed ", true, true);
// 		    	sf.seleU.hardwait(4000);
// 		    }
 		    
 		    if (sf.seleU.isElementDisplayed(sf.cc.accountNameField) && sf.seleU.isElementDisplayed(sf.cc.accountNumberField)
 		    && sf.seleU.isElementDisplayed(sf.cc.addressField) && sf.seleU.isElementDisplayed(sf.cc.roleField))
 		    {
 		    	reportStatusPass(methodName + " Account Field, Accout Status and table headers are displayed ", true, true); 		    	
 		    }
 		    sf.seleU.hardwait(4000);
 		    
            //validate billing account is displayed
// 		    verifyFieldValue("Account Name", sf.cc.accountNameValue, Global.dataInput.billingAccountName);
// 		    verifyFieldValue("Account Name", sf.cc.accountNameValue, InputData_Service.billingAccountForInternalGuidedCase);
// 		    sf.seleU.hardwait(4000);
 		    
 		    //assign role to billing account
// 		    sf.seleU.clickElementByJSE(sf.cc.showMoreButton);
// 		    sf.seleU.hardwait(2000);
// 		    sf.seleU.clickElementByJSE(sf.cc.assignRoleOption.get(1));
// 		    sf.seleU.hardwait(3000);
// 		    sf.seleU.clickElementByJSE(sf.cc.proceedButton);
// 		    sf.seleU.hardwait(3000);
 		    
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
		catch (Throwable e) 
		{
			reportStatusFail(" Error in Creating Relationship Contact Billing And ServiceAccount ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *                   Remove Relationship Between Account And Contact
	 */
	public void removeIndirectRelationship() throws Exception {
		boolean isRelationshipDeleted = false;
		for (int i = 0; i < sf.cd.showMoreActuionsAllRelationpOptions.size(); i++) {
			if (sf.seleU.isElementDisplayed(sf.cd.showMoreActuionsAllRelationpOptions.get(i))
					&& sf.seleU.getTextFromWebElement(sf.cd.showMoreActuionsAllRelationpOptions.get(i))
					.contains(Global.dataInput.removeRelationshipOption)) {

				sf.seleU.clickElementByJSE(sf.cd.showMoreActuionsAllRelationpOptions.get(i));
				sf.seleU.clickElementByJSE(sf.cd.removeRelationshipButton);
				sf.seleU.wait(2000);
				verifyFieldPresent("Account Contact Relationship Deleted msg", sf.cd.accountContactRelationDeletedMsg);
				isRelationshipDeleted = true;
				break;

			}
		}

		sf.seleU.wait(3000);
		if (isRelationshipDeleted && sf.cd.relatedAccountShowMoreActions.size() == 1) {
			reportStatusPass("Successfully Deleted Indirect Relationship Between Account and Contact", true, true);
		} else {
			reportStatusFail(" Error in Deleting Relationship Between Contact and Business Account", true);
		}
	}

	/**
	 * @param relationshipType
	 * @param relationshipList
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Verify that Relationship Options for Direct and
	 *                              Indirect Relation
	 */
	public void validateRelationship(String relationshipType, ArrayList<String> relationshipList)
			throws IOException, InterruptedException {

		if (relationshipType.equals("Direct")) {

			if (relationshipList.size() == 2 && relationshipList.contains(Global.dataInput.viewRelationshipOption)
					&& relationshipList.contains(Global.dataInput.editRelationshipOption)) {

				reportStatusPass("Verified  Relationship Options for Direct Relation", true, true);
			}

			else {
				reportStatusFail("Invalid  Relationship Options for Direct Relation", true);

			}

		} else {

			if (relationshipList.size() == 3 && relationshipList.contains(Global.dataInput.viewRelationshipOption)
					&& relationshipList.contains(Global.dataInput.editRelationshipOption)
					&& relationshipList.contains(Global.dataInput.removeRelationshipOption)) {

				reportStatusPass("Verified that Relationship Options for Indirect Relation", true, true);
			}

			else {
				reportStatusFail("Invalid  Relationship Options for Indirect Relation", true);

			}

		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Contact details page opened successfully
	 */
	public void verifyContactDetailsPageHeader() throws IOException {

		try {

			// Verify contact header
			sf.seleU.hardwait(4000);

			verifyFieldPresent("Contact Label", sf.cd.contactSpan);

			if (sf.seleU.getTextFromWebElement(sf.cd.contactNameText).equalsIgnoreCase(InputData.contactFullName))
				reportStatusPass(methodName + " Opened  Contact " + InputData.contactFullName, true, true);
			else
				reportStatusFail(methodName + " Contact " + InputData.contactFullName + " is not opened successfully",
						true);

		} catch (Throwable e) {
			reportStatusFail(" Error while verifying contact header", e);
			e.printStackTrace();
		}
	}
	
	public void approveContactRoleSigningAuthority() throws IOException {
		try
		{
			//click on related tab
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.cd.contactRelatedTabButton);						
			reportStatusPass(methodName + " Clicked on related tab ", true, false);
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.cd.r4bQuotesApprovalIcon);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.cd.approvalId);
			sf.seleU.hardwait(3000);
						
			verifyFieldValue("ContactID", sf.cd.contactidValue.get(1), Global.dataInput.contactName);
		    sf.seleU.clickElementByJSE(sf.cd.editApprovalStatus);
		    sf.seleU.hardwait(4000);
		    sf.seleU.clickElementByJSE(sf.cd.approvalStatusBox.get(2));
		    sf.seleU.hardwait(2000);
//		    for(int i=0; i<1; i++)
//		    {
//		    	sf.seleU.enterText(sf.cd.approvalStatusBox.get(2), Keys.ARROW_DOWN);
//		    	sf.seleU.hardwait(2000);
//		    }
//		    sf.seleU.enterText(sf.cd.approvalStatusBox.get(2), Keys.ENTER);
		    
		    sf.seleU.clickElementByJSE(sf.cd.approvedoption);
		    reportStatusPass(methodName + " Relationship Type as 'Signing Authority' approved ", true, false);
		    sf.seleU.wait(3000);
		    sf.seleU.clickElementByJSE(sf.cd.saveEditButton);
		    sf.seleU.hardwait(4000);		    		   			
		}
		catch (Throwable e) {
			reportStatusFail(" Error while approving contact as 'Signing Authority' ", e);
			e.printStackTrace();
		}
	}
	
	public void validateContactRoleSigningAuthority() throws IOException {
		try
		{
			//click on related tab
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.cd.relatedTab.get(0));
			reportStatusPass(methodName + " Clicked on related tab ", true, false);
			sf.seleU.hardwait(4000);
			verifyFieldValue("Roles ", sf.cd.rolesAdmin, "Signing Authority");
		}
		catch (Throwable e) {
			reportStatusFail(" Error while validating contact as 'Signing Authority' ", e);
			e.printStackTrace();
		}
	}
	
	public void validateInternalGuidedCaseForContact() throws IOException{
		try
		{
			//click on related tab
			sf.seleU.hardwait(4000);
	        sf.seleU.clickElementByJSE(sf.cd.relatedTab.get(0));		
			reportStatusPass(methodName + " Clicked on related tab ", true, false);
			sf.seleU.hardwait(4000);
			
			//click on new created fraud case
			sf.seleU.ScrolltoElement(sf.cd.relatedAccounts);
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.cd.casesIcon);
			sf.seleU.hardwait(4000);			
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.fraudCaseNumberLink);
			reportStatusPass(methodName + " Clicked on fraud case ", true, false);
			sf.seleU.hardwait(3000);
			InputData.fraudCaseNumber=sf.seleU.getTextFromWebElement(sf.caseDetails.caseNumberFieldValueText);	
			sf.seleU.hardwait(4000);
			verifyFieldValue("Case Owner", sf.caseRelatedDetails.fraudRiskCaseOwner, InputData.fraudRiskSelection);
			sf.seleU.hardwait(3000);
//			verifyFieldValue("Billing Account", sf.caseRelatedDetails.billingAccountField, Global.dataInput.billingAccountName);
//			verifyFieldValue("Billing Account", sf.caseRelatedDetails.billingAccountField, "ABiQAAuto220308049431");
			verifyFieldValue("Case  Status", sf.caseRelatedDetails.caseStatus, InputData.caseStatusNew);
			verifyFieldValue("Priority", sf.caseDetails.priorityFieldValueText, InputData.casePriorityMedium);				
		}
		catch(Throwable e) 
		{
			reportStatusFail(" Error validating internal guided case ", e);
			e.printStackTrace();
		}
	}

	/**
	 * PA
	 * 
	 * @throws IOException
	 * 
	 *                     Click on Business Account from Contact details page
	 */
	public void clickOnBusinessAccountFromContactDetails() throws IOException {

		try {

			// Verify contact header
			sf.seleU.hardwait(2000);

			sf.seleU.clickElementByJSE(sf.cd.accountNameText);
			reportStatusPass(methodName + " Clicked On Business Account", true, false);

		} catch (Throwable e) {
			reportStatusFail(" Error while verifying contact header", e);
			e.printStackTrace();
		}
	}

	/**
	 * 1- Verify account name, phone number, record type, mailing address, email id
	 * in contact details page.
	 * 
	 * 
	 * @throws IOException
	 */
	public void verifyUpdatedContactInDetails(String emailId, String businessAccountName) throws IOException {

		try {
			String methodName = "SFDC_verify Updated Contact In Details@: ";

			sf.seleU.wait(7000);
			//	sf.seleU.hardwait(7000);
			// 1. Verifying Account Name for the updated contact
			verifyFieldValue("Account Name is ", sf.cd.accountNameText, businessAccountName);

			// 2. Verifying Phone number for the updated contact
			verifyFieldValueContains("Contact phone no is ", sf.cd.conatctDetailsPhoneNoText,
					Global.dataInput.contactPhoneNumber);
			sf.seleU.hardwait(2000);

			// 3. Verifying Record Type
			verifyFieldValueWithNoFormat("Contact Record Type is ", sf.cd.contactRecordTypeText,
					Global.dataInput.contactRecordType);
			sf.seleU.wait(2000);

			// 4. Validate Mailing Address
			Global.dataInput.setContact_Mailing_Address();

			List<String> actualAddressMail = new ArrayList<>(
					Arrays.asList(Global.dataInput.actualContact_Address.trim().split(" ")));
			List<String> expectedAddressMail = new ArrayList<String>();

			// if service console is selected then locators are different

	//		if (sf.seleU.getTextFromWebElement(sf.home.consoleTitleText).equals("Service Console")) {

				for (int i = 0; i < sf.cd.contactDetailsMailingAddressText.size(); i++) {
					String expectedAddressArray[] = sf.seleU
							.getTextFromWebElement(sf.cd.contactDetailsMailingAddressText.get(i)).trim().split(" ");

					for (int k = 0; k < expectedAddressArray.length; k++) {
						expectedAddressMail.add(expectedAddressArray[k]);
					}
				}

//				// 6. Contact Status
//				sf.seleU.waitElementToBeVisible(sf.cd.contactStatusText_ForServiceConsole);
//				verifyFieldValue("Contact Status", sf.cd.contactStatusText_ForServiceConsole, Global.dataInput.status_Active);

//			} else {
//				for (int i = 3; i < sf.cd.contactDetailsMailingAddressText.size(); i++) {
//					String expectedAddressArray[] = sf.seleU
//							.getTextFromWebElement(sf.cd.contactDetailsMailingAddressText.get(i)).trim().split(" ");
//
//					for (int k = 0; k < expectedAddressArray.length; k++) {
//						expectedAddressMail.add(expectedAddressArray[k]);
//					}
//				}
//
				// 6. Contact Status : locator is different for service console
				if (sf.seleU.getTextFromWebElement(sf.home.consoleTitleText).equals("Service Console")) {
				
				} else {
					sf.seleU.waitElementToBeVisible(sf.cd.contactStatusText);
					verifyFieldValue("Contact Status", sf.cd.contactStatusText, Global.dataInput.status_Active);
				}
			
				
//			}

			verifyFieldValueWithCollections("Mailing address is matched with", actualAddressMail, expectedAddressMail);
			sf.seleU.wait(4000);

			// 5. Validate Email
			verifyFieldValueWithNoFormat("Contact Email", sf.cd.contactEmailText, emailId);
			sf.seleU.wait(4000);

			//			// 6. Contact Status
			//			sf.seleU.waitElementToBeVisible(sf.cd.contactStatusText);
			//			verifyFieldValue("Contact Status", sf.cd.contactStatusText, Global.dataInput.status_Active);

			// Commented out as of now, removed from UI
			// 7. Verify Email Opt Out
			// verifyFieldPresent("Do not call", sf.cd.contactDoNotText);

			sf.seleU.wait(2000);
			// Verify email opt out field check box is editable or not

			// Commented out as of now, removed from UI
			// if (sf.seleU.isElementDisplayed(sf.cd.contactDoNotCallEditClick)) {
			//
			// sf.seleU.clickElementByJSE(sf.cd.contactDoNotCallEditClick);
			//
			// if (sf.seleU.isElementDisplayed(sf.cd.saveEditButton)) {
			// sf.seleU.hardwait(2000);
			// sf.seleU.clickElementByJSE(sf.cd.saveEditButton);
			// reportStatusPass(methodName + "Do not call out option is editable", true,
			// false);
			// }
			//
			// } else {
			// reportStatusFail(" Do not call option is not editable", true);
			// }
			// sf.seleU.wait(2000);

			// 8. Verify Do not Call Field
			// verifyFieldPresent("Email Opt Out", sf.cd.contactEmailOptOutText);

			// Verify Do not call field check box is editable or not

			// Commented out as of now, removed from UI
			// if (sf.seleU.isElementDisplayed(sf.cd.contactEmailOptOutEditClick)) {
			//
			// sf.seleU.clickElementByJSE(sf.cd.contactEmailOptOutEditClick);
			//
			// if (sf.seleU.isElementDisplayed(sf.cd.saveEditButton)) {
			// sf.seleU.hardwait(2000);
			// sf.seleU.clickElementByJSE(sf.cd.saveEditButton);
			// reportStatusPass(methodName + "Email opt out option is editable", true,
			// false);
			// }
			// } else {
			// reportStatusFail("Email opt out option is not editable", true);
			// }

			// 9. Verify Community access is enabled
			verifyFieldPresent("Community Access field is", sf.cd.contactCommunityAccessLabelText);
			sf.seleU.hardwait(2000);

			// Verify Do not call field check box is editable or not
			if (sf.seleU.isElementPresent(sf.cd.contactCommunityAccessUserIdValue)
					|| sf.seleU.isElementDisplayed(sf.cd.contactCommunityAccessUserIdValue)) {
				reportStatusPass(methodName + "Community access is active", true, false);

			}
			sf.seleU.hardwait(3000);
		} catch (Throwable e) {
			reportStatusFail(" Verifying contact details is Unsuccessful", e);
			e.printStackTrace();

		}

	}

	/**
	 * 1- Make the contact from active to deActive status
	 * 
	 * 
	 * @throws IOException
	 */
	public void deactivateContact() throws IOException {

		try {

			String methodName = "SFDC_change the staus of contact@: ";

			sf.seleU.wait(1000);
			sf.seleU.waitElementToBeVisible(sf.cd.deactivateContactButton);

			// Click on deactivate button on contact details page
			sf.seleU.clickElementByJSE(sf.cd.deactivateContactButton);
			reportStatusPass(methodName + "Clicked on the deactivate conatct", true, false);

			sf.seleU.switchToFrame(sf.cd.deactivateContactIframe);

			// Verifying successfully navigating to deactivate page
			// sf.seleU.wait(3000);
			// sf.seleU.hardwait(2000);
			sf.seleU.waitElementToBeVisible(sf.cd.deactivateContactHeaderMessage);
			verifyFieldPresent("Deactivate contact header", sf.cd.deactivateContactHeaderMessage);

			sf.seleU.waitElementToBeVisible(sf.cd.deactivateValidateMessage);
			verifyFieldPresent("The contact is the only contact with the directly associated business account",
					sf.cd.deactivateValidateMessage);

			// Verify contact has an active community access
			if (sf.seleU.isElementDisplayed(sf.cd.contactActiveCommunityMsg)) {
				verifyFieldPresent("  The contact has an active Community access. ", sf.cd.contactActiveCommunityMsg);
			}
			sf.seleU.hardwait(2000);

			// Click on deactivate contact checkbox
			if (sf.seleU.isElementDisplayed(sf.cd.deactivateContactCheckBox)) {
				sf.seleU.clickElementByJSE(sf.cd.deactivateContactCheckBox);
				reportStatusPass(methodName + "Checked on the deactivate conatct checkbox", true, false);
			}

			sf.seleU.waitElementToBeVisible(sf.cd.deactivateNextButtonClick);

			// Click on next button
			sf.seleU.clickElementByJSE(sf.cd.deactivateNextButtonClick);
			reportStatusPass(methodName + "Clicked on the deactivate conatact next button", true, false);

			// Click on deactivate continue button
			if (sf.seleU.isElementPresent(sf.cd.deactivateContinueButton)) {
				sf.seleU.clickElementByJSE(sf.cd.deactivateContinueButton);
				reportStatusPass(methodName + "clicked the deactivate insert error continue button", true, false);
			}
			// Verify on the contact detail page whether the contact is active or deactive
			// verifyFieldValue("Contact status changed", sf.cd.contactStatusText,
			// sf.dataInput.status_Inactive);
			// sf.seleU.hardwait(3000);
			sf.seleU.hardwait(3000);
		} catch (Throwable e) {
			reportStatusFail("Making contact status to deactivate is Unsuccessful", e);
			e.printStackTrace();

		}

	}

	/**
	 * 1- Make the contact from active to deActive status
	 * 
	 * 
	 * @throws IOException
	 */
	public void vaidateAlreadyDeactivateContact() throws IOException {

		try {

			String methodName = "SFDC_vaidate Already Deactivate Contact@: ";

			sf.seleU.waitElementToBeVisible(sf.cd.deactivateContactButton);

			sf.seleU.scrollToTop();
			sf.seleU.waitElementToBeVisible(sf.cd.deactivateContactButton);

			// Click on deactivate button on contact details page
			sf.seleU.clickElementByJSE(sf.cd.deactivateContactButton);
			reportStatusPass(methodName + "Clicked on the deactivate conatct", true, false);

			sf.seleU.waitElementToBeVisible(sf.cd.deactivateContactIframe);
			sf.seleU.switchToFrame(sf.cd.deactivateContactIframe);

			// Verifying successfully navigating to deactivate page

			sf.seleU.hardwait(2000);
			verifyFieldPresent("The Contact is already Inactive. Please click Next button to exit.",
					sf.cd.alreadyDeactivateMsg);

			sf.seleU.wait(1000);

			// Community user details is not present after deactivating the contact
			// sf.seleU.hardwait(2000);
			// verifyFieldPresent("Contact community user details is ",
			// sf.cd.contactCommunityUserTagText);
			//
			// sf.seleU.hardwait(2000);
			// verifyFieldValueWithNoFormat("Contact Community User Status is ",
			// sf.cd.contactCommunityUserStatus,
			// sf.dataInput.b2b_status_Inactive);
			// sf.seleU.hardwait(2000);

			// Click on next button
			sf.seleU.waitElementToBeVisible(sf.cd.deactivateNextButtonClick);
			sf.seleU.clickElementByJSE(sf.cd.deactivateNextButtonClick);
			reportStatusPass(methodName + "Clicked on the deactivate conatact next button", true, false);

			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail("Making contact status to deactivate is Unsuccessful", e);
			e.printStackTrace();

		}

	}

	/**
	 * 1- Verify Commnunity User Status after clicking the activate contact button
	 * 
	 * 
	 * @throws IOException
	 */
	public void vaidateContactCommunityUserStatus() throws IOException {

		try {

			String methodName = "SFDC_Vaidate Contact Community User Status@: ";

			sf.seleU.wait(3000);

			// Click on deactivate button on contact details page
			sf.seleU.clickElementByJSE(sf.cd.activateContactButton);
			reportStatusPass(methodName + "Clicked on the activate conatct", true, false);

			sf.seleU.switchToFrame(sf.cd.deactivateContactIframe);

			// Verifying successfully navigating to deactivate page
			sf.seleU.wait(3000);
			sf.seleU.hardwait(2000);
			verifyFieldPresent("The Contact is already active. Please click Next button to exit.",
					sf.cd.alreadyActivateMsg);

			sf.seleU.wait(1000);
			sf.seleU.hardwait(2000);
			// Community user details is not present in activate contact page
			// verifyFieldPresent("Contact community user details is ",
			// sf.cd.contactCommunityUserTagText);

			sf.seleU.hardwait(2000);
			if (sf.seleU.isElementDisplayed(sf.cd.contactCommunityUserStatus)) {
				verifyFieldValueWithNoFormat("Contact Community User Status is ", sf.cd.contactCommunityUserStatus,
						Global.dataInput.status_Active);
			}

			sf.seleU.hardwait(2000);

			// Click on next button
			sf.seleU.clickElementByJSE(sf.cd.deactivateNextButtonClick);
			reportStatusPass(methodName + "Clicked on the already activate contact next button", true, false);

			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail("Verfying community user status is Unsuccessful in activate contact page", e);
			e.printStackTrace();

		}

	}

	/**
	 * 1- Verify Updated Contact phone no in verify history 2. New contact number
	 * for the updated contact should be updated in the list.
	 * 
	 * @throws IOException
	 */
	public void validateUpdatedContactDetailsInHistory(String phoneNumber) throws IOException {

		try {

			String methodName = "SFDC_Verify Contact Created Phone number@: ";

			// 5. Click related tab in contact details page
			sf.seleU.wait(2000);

			sf.seleU.clickElementByJSE(sf.cd.contactRelatedTabButton);
			reportStatusPass(methodName + "Clicked on the Related tab", true, false);
			sf.seleU.wait(2000);

			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.cd.contactHistViewAllClick);
			reportStatusPass(methodName + "Clicked on the contact history view all"
					+ sf.seleU.getTextFromWebElement(sf.cd.contactHistViewAllClick), true, true);

			sf.seleU.wait(2000);

			for (int i = 0; i < sf.cd.contactPhoneInViewAllText.size(); i++) {
				sf.seleU.wait(2000);
				// if the contact history list has the new phone number of the contact and
				// matched then contact is updated
				if (sf.seleU.getTextFromWebElement(sf.cd.contactPhoneInViewAllText.get(i))
						.contains(Global.dataInput.contactType)
						&& phoneNumber.contains(sf.seleU.getTextFromWebElement(sf.cd.contactNewValuePhoneNumText.get(i))
								.trim().replaceAll("[^A-Za-z0-9]", "").replaceAll("x", ""))) {

					reportStatusPass(
							methodName + " New value for the phone is "
									+ sf.seleU.getTextFromWebElement(sf.cd.contactNewValuePhoneNumText.get(i)).trim()
									.replaceAll("[^A-Za-z0-9]", "").replaceAll("x", "")
									+ " And matched with: " + phoneNumber + '\n'
									+ " New Contact has succesfully updated the duplicate Contact",
									true, false);
					break;
				}
			}
			sf.seleU.wait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Verifying contact creation or updation in verify history page is Unsuccessful", e);
			e.printStackTrace();

		}

	}

	/**
	 * @throws IOException
	 * 
	 * 
	 *                     1-Creating array to verify the ACR values for the
	 *                     contact, which is being passed to the test class
	 */
	public String[] creatingAcrValueArray(int choice) throws IOException {

		String methodName = "SFDC_Select Relationship Type@: ";
		Global.dataInput.acrPermission_FiedValue();

		List<String> roles = new ArrayList<String>();
		roles.clear();
		roles.removeAll(roles);
		// 0-> Account
		// 1-> Billing
		// 2-> General
		// 3-> Security & Contact
		// 4-> Service Change
		// 5-> Signing Authority
		// 6-> View All Cases
		// 7-> Technical
		// 8-> Usage
		// 9-> Site Contact

		switch (choice) {
		case 1:

			roles.add(Global.dataInput.contact_AcrValue.get(2));
			break;
		case 2:

			for (int k = 0; k < 7; k++) {
				roles.add(Global.dataInput.contact_AcrValue.get(k));
			}
			break;
		case 3:

			roles.add(Global.dataInput.contact_AcrValue.get(0));
			roles.add(Global.dataInput.contact_AcrValue.get(3));
			break;
		case 4:

			roles.add(Global.dataInput.contact_AcrValue.get(0));
			roles.add(Global.dataInput.contact_AcrValue.get(3));
			break;

		case 5:

			roles.add(Global.dataInput.contact_AcrValue.get(0));
			roles.add(Global.dataInput.contact_AcrValue.get(2));
			roles.add(Global.dataInput.contact_AcrValue.get(3));
			roles.add(Global.dataInput.contact_AcrValue.get(6));
			roles.add(Global.dataInput.contact_AcrValue.get(7));
			roles.add(Global.dataInput.contact_AcrValue.get(9));
			break;

		case 6:

			roles.add(Global.dataInput.contact_AcrValue.get(0));
			roles.add(Global.dataInput.contact_AcrValue.get(1));
			roles.add(Global.dataInput.contact_AcrValue.get(2));
			roles.add(Global.dataInput.contact_AcrValue.get(3));
			roles.add(Global.dataInput.contact_AcrValue.get(4));
			roles.add(Global.dataInput.contact_AcrValue.get(6));
			roles.add(Global.dataInput.contact_AcrValue.get(7));
			roles.add(Global.dataInput.contact_AcrValue.get(8));
			break;

		case 7:

			roles.add(Global.dataInput.contact_AcrValue.get(0));
			roles.add(Global.dataInput.contact_AcrValue.get(1));
			roles.add(Global.dataInput.contact_AcrValue.get(2));
			roles.add(Global.dataInput.contact_AcrValue.get(3));
			roles.add(Global.dataInput.contact_AcrValue.get(4));
			roles.add(Global.dataInput.contact_AcrValue.get(6));
			break;

		case 8:

			roles.add(Global.dataInput.contact_AcrValue.get(2));
			roles.add(Global.dataInput.contact_AcrValue.get(9));
			break;

		case 9:

			roles.add(Global.dataInput.contact_AcrValue.get(0));
			roles.add(Global.dataInput.contact_AcrValue.get(1));
			roles.add(Global.dataInput.contact_AcrValue.get(2));
			roles.add(Global.dataInput.contact_AcrValue.get(3));
			roles.add(Global.dataInput.contact_AcrValue.get(4));
			roles.add(Global.dataInput.contact_AcrValue.get(5));
			roles.add(Global.dataInput.contact_AcrValue.get(6));
			break;

		case 10:

			roles.add(Global.dataInput.contact_AcrValue.get(9));
			break;

		}

		String[] contact_AcrBusinessAccount = roles.toArray(new String[0]);
		Global.dataInput.contact_AcrValue.clear();
		return contact_AcrBusinessAccount;

	}

	/**
	 * 1- Verify Relationship is direct or indirect 2. Verifying ACR permission for
	 * Business, Service and Billing account 3. Verifying ACR permission for
	 * Business account in related contact list in the account related page 4.
	 * Remove relationship for service and billing account after verifying Acr
	 * value.
	 *
	 * @throws IOException
	 */
	public void validateAcrPermissionInContacts(String recordType, String account, String acrValue[],
			String rolesValue[], boolean removeRelationship) throws IOException {

		try {

			String methodName = "SFDC_Verify Relationship and ACR@: ";
			boolean isFound = false;
			int index = 0;

			// Click related tab in contact details page
			sf.seleU.wait(1000);
			sf.seleU.waitElementToBeVisible(sf.cd.contactRelatedTabButton);
			sf.seleU.clickElementByJSE(sf.cd.contactRelatedTabButton);
			reportStatusPass(methodName + "Clicked on the Related tab", true, false);
			sf.seleU.wait(1000);

			sf.seleU.waitElementToBeVisible(sf.cd.relatedAccountsViewAllClick.get(0));
			// Click on related account viewAll in contact details page
			sf.seleU.clickElementByJSE(sf.cd.relatedAccountsViewAllClick.get(0));
			reportStatusPass(methodName + "Clicked on the Related Accounts View all", true, false);
			sf.seleU.wait(3000);

			// Click on quick filter option in related account page list
			sf.seleU.clickElementByJSE(sf.cd.contactQuickFilterClick);
			reportStatusPass(methodName + "Clicked on related accounts Quick Filter button", true, false);
			sf.seleU.wait(2000);

			// Enter the account name to be filtered
			sf.seleU.clearAndEnterText(sf.cd.contactQuickFilterEnterAccountName, account);
			reportStatusPass(methodName + "Enter the Account name as " + account, true, false);
			sf.seleU.hardwait(3000);

			// Click on apply button after entering the account name
			sf.seleU.clickElementByJSE(sf.cd.contactQuickFilterApplyButton);
			sf.seleU.wait(2000);
			reportStatusPass(methodName + "Clicked on quick filter apply button ", true, false);

			// Click on Quick filter hide button
			sf.seleU.clickElementByJSE(sf.cd.contactHideFilterClick);

			for (int i = 0; i < sf.cd.contactAccountRecordTypeText.size(); i++) {
				sf.seleU.wait(2000);
				// Matching with Record type and Account number in the related account list
				// after filtering the account

				if (sf.seleU.getTextFromWebElement(sf.cd.contactAccountRecordTypeText.get(i)).trim()
						.contains(recordType)
						&& account.trim()
						.contains(sf.seleU.getTextFromWebElement(sf.cd.contactAccountNameText.get(i)).trim())) {

					// If the direct check box is checked then the relationship is direct between
					// the contact and account
					if (sf.seleU.getElementAttribute(sf.cd.contactAcrDirectCheckBox.get(i), "class").contains("checked")
							&& sf.seleU.getElementAttribute(sf.cd.contactAcrDirectCheckBox.get(i), "alt")
							.contains("True")) {
						sf.seleU.hardwait(2000);
						reportStatusPass(methodName
								+ "Record is found in the related account list and Relationship is Direct /n with the account"
								+ sf.seleU.getTextFromWebElement(sf.cd.contactAccountNameText.get(i)), true, false);
						isFound = true;
						index = i;
						break;
					} else {
						sf.seleU.hardwait(2000);
						reportStatusPass(methodName
								+ "Record is found in the related account list and Relationship is InDirect /n with the account "
								+ sf.seleU.getTextFromWebElement(sf.cd.contactAccountNameText.get(i)) + " ", true,
								false);
						isFound = true;
						index = i;
						break;
					}
				}
			}

			// If the Record is found in the Related Account List fetch ACR value and verify
			if (isFound) {
				sf.seleU.wait(2000);

				// For the Business contact if the record is selected "Endorser, Assessor,
				// Decider, Coach/Influencer and
				// Acr value is displayed as empty

				if (sf.cd.contactAcrPermissionText.get(index).getText().isEmpty()) {

					sf.seleU.hardwait(4000);
					if (sf.seleU.getTextFromWebElement(sf.cd.contactRelationshipTypeText.get(index))
							.contains(Global.dataInput.directRoleOptions.get(5))) {
						reportStatusPass(
								methodName + "ACR field is displayed and empty with the following role value selection "
										+ Global.dataInput.directRoleOptions.get(5) + " "
										+ Global.dataInput.directRoleOptions.get(2) + " "
										+ Global.dataInput.directRoleOptions.get(4) + " "
										+ Global.dataInput.directRoleOptions.get(1),
										true, false);
					}
				}
				// if ACR value is not displayed as empty
				else {

					sf.seleU.hardwait(3000);
					verifyFieldValueWithCollections("ACR field value is displayed",
							sf.cd.contactAcrPermissionText.get(index), acrValue);

					verifyFieldValueWithCollections("Role value is displayed",
							sf.cd.contactRelationshipTypeText.get(index), rolesValue);

					// Remove relationship between contact and account after adding the billing and
					// service in the related account list
					if (removeRelationship == true) {
						if (Global.dataInput.acc_RecordType_Service.equals(recordType)
								|| Global.dataInput.acc_RecordType_Billing.equals(recordType)) {
							removeRelationshipInRelatedAccount(index);
						}

					}
				}

				// ***Verifying ACR in the business account page related contact list Only for
				// Business Account Record Type.

				// Commented out as the ACR value is not displayed in the ACR relationship page
				// in business account
				// if (sf.dataInput.acc_RecordType_Business.equals(recordType)) {
				//
				// sf.seleU.hardwait(4000);
				// verifyingAcrInBusinessAccount(index, acrValue);
				//
				// // contact search is used to reach to related account list page
				//
				// }
			}

			// No Account found in the Related account list in the contact related page
			else {
				reportStatusFail(methodName + " No record found for the account in the related account list", true);
			}

			sf.seleU.wait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Verifying relationship and ACR value for the accounts is Unsuccessful", e);
			e.printStackTrace();

		}

	}

	/**
	 * 
	 * Verifying ACR permission for Business account in related contact list in the
	 * account related page
	 * 
	 * @throws IOException
	 */
	public void verifyingAcrInBusinessAccount(int recordIndex, String array[]) throws IOException {
		try {

			String methodName = "SFDC_Verify Acr In BusinessAccount Page@: ";

			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.cd.contactDropDownArrowClick.get(recordIndex));
			sf.seleU.wait(5000);

			// click on the view relationship arrow
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.cd.contactViewRelationshipOption);
			sf.seleU.wait(5000);

			// validation the condition in account contact relationship page
			if (sf.ar.accountContactRelationshipAcr.get(1).getText().isEmpty()) {
				reportStatusPass(methodName
						+ "ACR field is displayed and empty with the following role value selection in "
						+ "Account Contact Relationship " + Global.dataInput.directRoleOptions.get(5) + " "
						+ Global.dataInput.directRoleOptions.get(2) + " " + Global.dataInput.directRoleOptions.get(4)
						+ " " + Global.dataInput.directRoleOptions.get(1), true, false);
			} else {
				// Verify ACR Values
				verifyFieldValueWithCollections("In the Business Account Page ACR field value is displayed with ",
						sf.ar.accountContactRelationshipAcr.get(1), array);
			}

			sf.seleU.wait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Acr Field verification in the Business Account Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Remove relationship in the account related page
	 * 
	 * @throws IOException
	 */
	public void removeRelationshipInRelatedAccount(int recordIndex) throws IOException {
		try {

			String methodName = "SFDC_Verify Acr In BusinessAccount Page@: ";

			sf.seleU.hardwait(2000);
			// Click on dropdown arrow to remove relationship
			sf.seleU.clickElementByJSE(sf.cd.contactDropDownArrowClick.get(recordIndex));
			reportStatusPass(methodName + "Succesfully Clicked on Dropdown arrow ", true, false);
			sf.seleU.wait(3000);

			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.cd.contactRemoveRelationshipOption);
			reportStatusPass(methodName + "clicked on remove relatioship", true, false);

			// Click on confirmed pop up relationship button
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.cd.contactRemoveRelationshipButton);
			reportStatusPass(methodName + "clicked on confirmed remove relatioship button", true, false);
			sf.seleU.wait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Error in removing relationship in the related account list", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1. Click on Manage Relationship button 2. Verifying
	 *                     knowledge Link 3. Add relationship roles in manage
	 *                     relationship page for Direct role Business account
	 */

	public void manageRelatioshipAddRoles(String[] value, String contactEmailId) throws IOException {

		try {
			String methodName = "SFDC_Add roles for Direct Role Section@: ";
			boolean found = true;
			// click on manage relationship button
			// sf.seleU.hardwait(5000);
			sf.seleU.wait(3000);
			sf.seleU.waitElementToBeVisible(sf.cd.manageRelationshipTabButton);
			sf.seleU.clickElementByJSE(sf.cd.manageRelationshipTabButton);
			reportStatusPass(methodName + "Succesfully Clicked on ManageRelationShip button ", true, false);
			sf.seleU.hardwait(2000);

			// Provide max timeout as manage relationship page takes lot of time to load
			// sometimes
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
			sf.seleU.waitElementToBeVisible(sf.cd.iframe.get(1));
			sf.seleU.wait(10000);

			sf.seleU.switchToFrame(sf.cd.iframe.get(1));
			sf.seleU.wait(4000);
			// sf.seleU.hardwait(5000);

			// Verifying knowledge hyperlink - If its found at the first time then verify
			// the link else go back and
			// click manage relationship button again to navigate to the page and verify.

			while (found == true) {

				if (sf.seleU.isElementDisplayed(sf.cd.informationKnowledgeLink)) {

					sf.seleU.clickElementByJSE(sf.cd.informationKnowledgeLink);
					reportStatusPass(methodName + "Succesfully clicked on Knowledge Article Link More Information ",
							true, false);
					sf.seleU.hardwait(2000);
					verifyFieldPresent("Navigated to Knowledge article link verified and detail information about it ",
							sf.cd.checkKnowledgeLink);
					sf.seleU.hardwait(4000);
					sf.seleU.navigateBack();
					sf.seleU.switchToFrame(sf.cd.iframe.get(1));
					found = false;
					break;

				} else {
					sf.seleU.navigateBack();
					if (sf.seleU.isElementDisplayed(sf.cd.manageRelationshipTabButton)) {
						sf.seleU.clickElementByJSE(sf.cd.manageRelationshipTabButton);
						reportStatusPass(methodName + "Succesfully Clicked again on ManageRelationShip button ", true,
								false);

						sf.seleU.switchToFrame(sf.cd.iframe.get(1));
					}
				}
			}

			// Verify Direct, Indirect Header text, Contact first name, lastname, email
			// address
			verifyFieldPresent("Review Contact Details", sf.cd.reviewContactHeaderText);
			sf.seleU.wait(2000);

			verifyFieldPresent("Direct Role", sf.cd.directRoleHeaderText);
			sf.seleU.hardwait(2000);

			verifyFieldValueWithNoFormat("Contact First Name is Displayed as", sf.cd.contactFirstNameTextDisplay,
					Global.dataInput.contactFirstName);
			sf.seleU.hardwait(2000);

			verifyFieldValueWithNoFormat("Contact Last Name is Displayed as", sf.cd.contactLastNameTextDisplay,
					Global.dataInput.contactLastName);
			sf.seleU.hardwait(2000);
			sf.seleU.wait(1000);

			verifyFieldValueWithNoFormat("Contact Email is Displayed as", sf.cd.contactEmailTextDisplay,
					contactEmailId);
			sf.seleU.hardwait(1000);

			verifyFieldPresent("Indirect Role", sf.cd.inDirectRoleHeaderText);
			sf.seleU.wait(1000);

			// Click on direct role option
			sf.seleU.clickElementByJSE(sf.cd.accountNameClick);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "Succesfully Clicked on direct role ", true, false);

			sf.seleU.wait(2000);
			selectRolesAndClickNext(value, sf.cd.directBusinessCombineRolesText,
					sf.cd.directBusinessCombineRolesCheckBox, "Business Account for Direct ");
			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Error while adding relationship type in direct role options", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Add relationship roles in manage relationship in Indirect
	 *                     role for Business account
	 */

	public void manageIndirectBusinessRelatioshipAddRoles(String array[], String account)
			throws IOException, InterruptedException {

		try {
			sf.seleU.hardwait(3000);
			sf.seleU.wait(3000);
			clickManageRelationshipIndAccAndVerifyAddress(account, "Business");

			// Select roles for the business account
			selectRolesAndClickNext(array, sf.cd.indirectBusinessCombineRolesText,
					sf.cd.indirectBusinessCombineCheckBox, "Business Account");
			sf.seleU.wait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Error while adding the indirect role options for Business account", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Add relationship roles in manage relationship in Indirect
	 *                     role for Service account
	 */

	public void manageIndirectServiceRelatioshipAddRoles(String array[], String account)
			throws IOException, InterruptedException {

		try {
			// Click manage relationship button and verify Service address
			sf.seleU.hardwait(3000);
			sf.seleU.wait(3000);
			clickManageRelationshipIndAccAndVerifyAddress(account, "Service");
			sf.seleU.wait(2000);

			// Select roles for the service account
			selectRolesAndClickNext(array, sf.cd.indirectServiceCombineRolesText,
					sf.cd.indirectServiceCombineRoleCheckBox, "Service Account");
			sf.seleU.wait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Error while adding the indirect role options for service account", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Add relationship roles in manage relationship in Indirect
	 *                     role for Billing account
	 */

	public void manageIndirectRelationshipAddBillingAccountRoles(String array[], String account)
			throws IOException, InterruptedException {

		try {

			// Click manage relationship button and verify billing address
			sf.seleU.hardwait(3000);
			sf.seleU.wait(3000);
			clickManageRelationshipIndAccAndVerifyAddress(account, "Billing");

			// Select roles for the Billing account
			selectRolesAndClickNext(array, sf.cd.indirectBillingCombineRolesText,
					sf.cd.indirectBillingCombineRoleCheckBox, "Billing Account");

			sf.seleU.hardwait(3000);
		} catch (Throwable e) {
			reportStatusFail(" Error while adding indirect roles for billing account", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on manage relationship in contact details and enter
	 *                     the account(Business, Service or Billing) in the add
	 *                     indirect role section and verify the address getting
	 *                     populated fir the respective account.
	 */

	public void clickManageRelationshipIndAccAndVerifyAddress(String account, String accountType)
			throws IOException, InterruptedException {

		try {
			String methodName = "SFDC_Verify Address for all the Accounts@: ";

			// Click on manage relationship button
			// sf.seleU.hardwait(5000);
			// sf.seleU.wait(3000);
			sf.seleU.waitElementToBeVisible(sf.cd.manageRelationshipTabButton);
			sf.seleU.scrollToTop();
			sf.seleU.hardwait(2000);
			sf.seleU.waitElementToBeVisible(sf.cd.manageRelationshipTabButton);
			sf.seleU.clickElementByJSE(sf.cd.manageRelationshipTabButton);
			reportStatusPass(methodName + "Succesfully clicked on ManageRelationShip button ", true, false);

			// driver.manage().timeouts().implicitlyWait(1,TimeUnit.MINUTES) ;
			sf.seleU.wait(20000);
			// sf.seleU.wait(20000);
			sf.seleU.waitElementToBeVisible(sf.cd.iframe.get(1));
			// sf.seleU.hardwait(10000);

			sf.seleU.switchToFrame(sf.cd.iframe.get(1));
			// sf.seleU.hardwait(5000);
			// sf.seleU.wait(1000);

			sf.seleU.waitElementToBeVisible(sf.cd.addIndirectRoleBox);
			sf.seleU.ScrolltoElement(sf.cd.addIndirectRoleBox);
			sf.seleU.hardwait(2000);

			// Click on Indirect role section
			sf.seleU.clickElementByJSE(sf.cd.addIndirectRoleBox);
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + "Succesfully Clicked on Add Indirect Role Button", true, false);

			// Enter the account to be searched in the indirect role section
			sf.seleU.wait(2000);
			sf.seleU.enterText(sf.cd.indirectSearchAccount, account);
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.cd.indirectSearchAccount, Keys.ARROW_DOWN);
			sf.seleU.enterText(sf.cd.indirectSearchAccount, Keys.ENTER);
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + " Entered Account as " + account + " for account type " + accountType
					+ " to be searched for", true, false);

			// Verifying Account Record Type ::after entering the account it should populate
			// record type along with account no
			sf.seleU.hardwait(1000);
			if (sf.seleU.getElementAttribute(sf.cd.indirectSearchAccount, "class").contains("ng-not-empty")) {
				if (sf.seleU.getTextFromWebElement(sf.cd.indirectSearchAccount).trim().contains(accountType)) {

					reportStatusPass(methodName + "Succesfully validated and matched Expected AccountType"
							+ "for account " + account + " With Record Type as " + accountType + " With actual one "
							+ sf.seleU.getTextFromWebElement(sf.cd.indirectSearchAccount), true, true);
				} else {
					reportStatusFail(
							methodName + "Epected Record Type" + accountType + "is not matched with actual one "
									+ sf.seleU.getTextFromWebElement(sf.cd.indirectSearchAccount),
									true);
				}

			} else {
				reportStatusFail(methodName + "Record Type for the Account is not displayed or matched " + account,
						true);
			}
			sf.seleU.wait(2000);

			// Validating billing address based on the actual address

			if (accountType.trim().equals(Global.dataInput.acc_RecordType_Business)) {

				if (sf.seleU.getElementAttribute(sf.cd.billingAddressTextBox, "class").contains("ng-not-empty")) {
					reportStatusPass(methodName + "Billing address is populating for the account " + account, true,
							true);
				} else {
					reportStatusFail(methodName + "Billing address is not popluating for account " + account, true);
				}

			}

			sf.seleU.wait(2000);
			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Error while entering the account and validating the billing address", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Can select single or multiple role for(Business, Service
	 *                     or Billing) in the add indirect role section and click on
	 *                     next button.
	 */
	public void selectRolesAndClickNext(String array[], List<WebElement> roles, List<WebElement> checkBox,
			String accountType) throws IOException, InterruptedException {

		try {
			String methodName = "SFDC_Select roles and click next@: ";
			sf.seleU.hardwait(3000);

			// REset -> At first untick all the selected option
			for (int i = 0; i < roles.size(); i++) {
				// String text = sf.seleU.getTextFromWebElement(roles.get(i));
				sf.seleU.hardwait(2000);
				if (sf.seleU.getElementAttribute(checkBox.get(i), "class").contains("ng-not-empty")) {
					sf.seleU.clickElementByJSE(checkBox.get(i));
					reportStatusPass(methodName + "Clicked on " + sf.seleU.getTextFromWebElement(roles.get(i)) + " for "
							+ accountType + " for unclick and reset", true, false);
				}
			}

			// Now select depending on the role
			sf.seleU.hardwait(2000);
			for (int i = 0; i < roles.size(); i++) {
				String text = sf.seleU.getTextFromWebElement(roles.get(i));
				sf.seleU.wait(2000);
				// Traversing through each role and matching with the required one if matched
				// then click on it
				for (int k = 0; k < array.length; k++) {
					if (text.equals(array[k])) {
						// sf.seleU.hardwait(2000);
						sf.seleU.wait(1000);
						sf.seleU.clickElementByJSE(checkBox.get(i));
						reportStatusPass(methodName + "Clicked on " + sf.seleU.getTextFromWebElement(roles.get(i))
						+ " for " + accountType, true, false);
					}
				}
			}

			sf.seleU.hardwait(2000);
			sf.seleU.wait(2000);

			// Click on the save button
			if (sf.seleU.isElementPresent(sf.cd.directSaveButton)) {
				if (sf.seleU.isElementDisplayed(sf.cd.directSaveButton)) {
					sf.seleU.clickElementByJSE(sf.cd.directSaveButton);
					reportStatusPass(methodName + "Succesfully clicked on save button after adding the role ", true,
							false);
				}
			}

			sf.seleU.waitElementToBeVisible(sf.cd.indirectNextButton);
			sf.seleU.clickElementByJSE(sf.cd.indirectNextButton);

			reportStatusPass(methodName + "Succesfully clicked on Next button ", true, false);
			sf.seleU.wait(6000);

		} catch (Throwable e) {
			reportStatusFail(" Error while selecting the role for " + accountType, e);
			e.printStackTrace();
		}
	}

	/*
	 * Update the contact phone number in contact details page and click save.
	 */

	public void updateContactPhoneNumber(String contactNumber) throws IOException {
		try {
			String methodName = "SFDC_UpdateContactNo@: ";
			if (sf.seleU.isElementPresent(sf.ad.closeButton)) {
				sf.seleU.clickElementByJSE(sf.ad.closeButton);
			}
			// Verifying the contact phone number
			//sf.seleU.wait(3000);
            // Click edit and enter the number
			//sf.seleU.clickElementByJSE(sf.cd.closeAlert);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.cd.contactEditButton);
			reportStatusPass(methodName + "Clicked on the contact edit button Successfully ", true, false);
			sf.seleU.hardwait(3000);
			sf.seleU.clearAndEnterText(sf.cd.inputPhoneTextBox, contactNumber);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "Entered the updated phone no as " + contactNumber, true, false);
			sf.seleU.enterText(sf.cd.inputPhoneTextBox, Keys.TAB);
			sf.seleU.clickElementByJSE(sf.cd.enterHomePhoneInEdit);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.cd.contactOwner);
			sf.seleU.hardwait(3000);
			
			sf.seleU.clickElementByJSE(sf.cd.contactEditSaveButton);
			sf.seleU.hardwait(5000);			
			reportStatusPass(methodName + "Clicked on the save button Successfully ", true, false);
			//sf.seleU.refreshPage();
			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Verying the phone number was unsuccessfull", e);
			e.printStackTrace();
		}

	}

	/*
	 * verify the contact phone number in contact details page and click save.
	 */

	public void verifyContactPhoneNumber(String contactNumber) throws IOException {
		try {
			String methodName = "SFDC_Account@: ";
			if (sf.seleU.isElementPresent(sf.ad.closeButton)) {
				sf.seleU.clickElementByJSE(sf.ad.closeButton);
			}
			//sf.seleU.wait(2000);
			//sf.seleU.clickElementByJSE(sf.cd.closeAlert);
			//sf.seleU.wait(2000);
			// Verify the contact phone number
			sf.seleU.hardwait(3000);
			verifyFieldValueContains(methodName + " Contact Phone Number", sf.cd.conatctDetailsPhoneNoText,
					contactNumber);

		} catch (Throwable e) {
			reportStatusFail(" Verying the phone number was unsuccessfull", e);
			e.printStackTrace();
		}
	}

	/*
	 * Click on Manage Relationships and extract related business accounts in a list
	 */
	public void extractBusinessAccountsFromContact() throws IOException {
		try {
			String methodName = "SFDC_ContactDetails@: ";
			// Verify contact page
			sf.seleU.hardwait(3000);
			verifyFieldPresent("Contact Page Header", sf.cd.contactSpan);

			// Click on Manage Relationships and verify landing screen
			sf.seleU.clickElementByJSE(sf.cd.manageRelationshipTabButton);
			reportStatusPass(methodName + " Clicked on Manage Relationship tab in contact", true, true);
			sf.seleU.hardwait(5000);

			sf.seleU.switchToElementFrame(sf.cd.modifyRolesTagMessage);
			verifyFieldPresent("Roles Page Header", sf.cd.modifyRolesTagMessage.get(0));

			// Clear List for storing business accounts to be verified at later stage
			InputData_Communities.communitiesBusinessAccounts.clear();

			// Fill list with business accounts under Direct Roles and Indirect Roles with
			// Business Accounts
			for (int i = 0; i < sf.cd.directRoleBusinessAccounts.size(); i = i + 2) {
				InputData_Communities.primaryAccountForCommUser = sf.seleU
						.getTextFromWebElement(sf.cd.directRoleBusinessAccounts.get(i));
				InputData_Communities.communitiesBusinessAccounts.add(InputData_Communities.primaryAccountForCommUser);
				reportStatusPass(methodName + " Added business Account to list : "
						+ sf.seleU.getTextFromWebElement(sf.cd.directRoleBusinessAccounts.get(i)), true, true);
			}
			for (int i = 0; i < sf.cd.inDirectRoleBusinessAccounts.size(); i = i + 2) {
				InputData_Communities.communitiesBusinessAccounts
				.add(sf.seleU.getTextFromWebElement(sf.cd.inDirectRoleBusinessAccounts.get(i)));
				reportStatusPass(
						methodName + " Added business Account to list : "
								+ sf.seleU.getTextFromWebElement(sf.cd.inDirectRoleBusinessAccounts.get(i)),
								true, true);
			}
			sf.seleU.switchToDefaultContent();
		} catch (Throwable e) {
			reportStatusFail(" Extracting business accounts from contact was unsuccessfull", e);
			e.printStackTrace();
		}

	}

	/**
	 * @param fieldName
	 * @param element
	 * @param expectedText
	 * @throws IOException
	 * 
	 *                     Verify field contains the respective value
	 */
	public void verifyFieldValueWithNoFormat(String fieldName, WebElement element, String expectedText)
			throws IOException {
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
	 * @param DirectMsg
	 * @param acc
	 * @param array
	 * @throws IOException
	 * 
	 *                     Verifying and matching field values in the
	 *                     list/collection by passing WebElement
	 */
	public void verifyFieldValueWithCollections(String DirectMsg, WebElement acc, String array[]) throws IOException {
		try {
			sf.seleU.wait(2000);
			String expectedArrValue[] = sf.seleU.getTextFromWebElement(acc).trim().split(";");

			List<String> expectedValueList = new ArrayList<>(Arrays.asList(expectedArrValue));
			List<String> actualValueList = new ArrayList<String>(Arrays.asList(array));

			// sort lists for comparison
			Collections.sort(expectedValueList);
			Collections.sort(actualValueList);

			if (expectedValueList.equals(actualValueList)) {
				reportStatusPass(DirectMsg + " " + "and ts validated and matched with the expected values "
						+ AdditionalUtilities.getAsString(expectedValueList), true, true);

			} else {
				reportStatusFail(" All expected field value are not present :: " + "Expected field value is "
						+ "Applicaton displayed data is " + AdditionalUtilities.getAsString(expectedValueList)
						+ "  Actual Value--> " + AdditionalUtilities.getAsString(actualValueList), true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param DirectMsg
	 * @param actualValue
	 * @param expectedValue
	 * @throws IOException
	 * 
	 *                     Verifying and matching field values in the
	 *                     list/collection
	 */
	public void verifyFieldValueWithCollections(String DirectMsg, List<String> actualValue, List<String> expectedValue)
			throws IOException {
		try {
			sf.seleU.wait(1000);
			// sort lists for comparison
			Collections.sort(actualValue);
			Collections.sort(expectedValue);

			if (expectedValue.equals(actualValue)) {
				reportStatusPass(DirectMsg + " expected value -->" + AdditionalUtilities.getAsString(expectedValue),
						true, true);

			} else {
				reportStatusFail(" All expected field value are not present :: " + "Applicaton displayed data is "
						+ AdditionalUtilities.getAsString(expectedValue) + "  Actual Value--> "
						+ AdditionalUtilities.getAsString(actualValue), true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Log An Activity button
	 */
	public void logAnActivity() throws IOException {

		try {
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.cd.showMoreActionsButton);
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.cd.logASalesActivityButton);

			sf.seleU.wait(3000);
			reportStatusPass(methodName + " Clicked on Log An Activity button from contacts ", true, false);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not click on Log An Activity", e);
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * Verifying ACR permission for Business account in related contact list in the
	 * account related page - Validate fields Active, Start Date and End Date
	 * 
	 * @throws IOException
	 */
	public void verifyingAcrInBusinessAccountForDatesAndStatus() throws IOException {
		try {

			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.cd.contactDropDownArrowClick.get(0));
			sf.seleU.wait(5000);

			// click on the view relationship arrow
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.cd.contactViewRelationshipOption);
			sf.seleU.wait(5000);

			// validation the condition in account contact relationship page
			verifyFieldPresent("ACR Info - Active",sf.ar.acrInfoActive);
			verifyFieldPresent("ACR Info - Start Date",sf.ar.acrInfoStartDate);
			verifyFieldPresent("ACR Info - End Date",sf.ar.acrInfoEndDate);

			//Verify field are disabled
			verifyFieldReadOnly("ACR Info - Active Value", sf.ar.acrInfoActiveValue);
			verifyFieldReadOnly("ACR Info - Start Date Value", sf.ar.acrInfoStartDateValue);
			verifyFieldReadOnly("ACR Info - End Date Value", sf.ar.acrInfoEndDateValue);

			sf.seleU.wait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Acr Field verification in the Business Account Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is read only
	 */
	public void verifyFieldReadOnly(String fieldName, WebElement element) throws IOException {
		try {

			if (sf.seleU.getElementAttribute(element, "class").contains("read-only")) {
				reportStatusPass(methodName + " Validated " + fieldName + " field is read only and cannot be edited", true,
						true);
			} else {
				reportStatusFail(
						methodName + " Field " + fieldName + " is not a read only field, It should be a read-only", true);
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

	public void verifyFieldValueContains(String fieldName, WebElement element, String expectedText) throws IOException {
		try {
			if (!expectedText.equals("NA")) {
				// Verify Field value matches the expected result
				Global.dataInput.formatField = sf.seleU.getTextFromWebElement(element).trim()
						.replaceAll("[^A-Za-z0-9]", "").replaceAll("x", "");

				System.out.println(Global.dataInput.formatField);
				System.out.println(expectedText);

				if ((expectedText.trim()).contains(Global.dataInput.formatField)) {
					reportStatusPass("Validated " + fieldName + " is " + expectedText, true, true);
				} else {
					reportStatusFail("Actual Value for " + fieldName + " is " + Global.dataInput.formatField
							+ " And Expected One is " + expectedText, true);
				}
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
	
	
	/*
	 * Update the contact phone number in contact details page and click save.
	 */

	public void updateContactPhoneNumberThruDataGov(String contactNumber) throws IOException {
		try {
			String methodName = "SFDC_UpdateContactNo@: ";

			// Verifying the contact phone number
			sf.seleU.wait(2000);

			// Click edit and enter the number
			sf.seleU.clickElementByJSE(sf.ad.editPhoneButton);
			reportStatusPass(methodName + "Clicked on the contact edit button Successfully ", true, false);
			sf.seleU.hardwait(5000);

			sf.seleU.clearAndEnterText(sf.ad.enterPhone, contactNumber);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.ad.saveButton);
			reportStatusPass(methodName + "Clicked on the save button Successfully and Entered the updated phone no as " + contactNumber, true, false);
			

		} catch (Throwable e) {
			reportStatusFail(methodName + " Verying the phone number was unsuccessfull", e);
			e.printStackTrace();
		}

	}

}
