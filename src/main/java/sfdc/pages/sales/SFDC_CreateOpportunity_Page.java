package sfdc.pages.sales;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya,Date 14/jan/2020
 * 
 *         SFDC Create Opportunity Page
 *
 */
public class SFDC_CreateOpportunity_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_CreateOpportunity_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * Date 14/Jan/2020
	 * 
	 * 1- Create Opportunity: Select Product
	 * 
	 * 2- Create Opportunity: Select Sub Product
	 * 
	 * 3- Click on Next Button
	 * 
	 * 4- Verify Company name is populated
	 * 
	 * 5- Enter Opportunity Close Date
	 * 
	 * 6- Select Opportunity Stage
	 * 
	 * 7- Select Opportunity Type
	 * 
	 * 8- Select Opportunity Amount
	 * 
	 * 9- Select Opportunity Additional Information
	 * 
	 * 10- Click on Next Button
	 * 
	 * 
	 * 
	 * 
	 * @throws IOException
	 */
	public void enterOpportunityDetails() throws IOException {
		try {

			String methodName = "SFDC_Create_Opportunity@: ";

			if (sf.dataInput.env.equals("PREFIT") || sf.dataInput.env.equals("ITPREPROD") || sf.dataInput.env.equals("WADEVQA") || sf.dataInput.env.equals("WADEVPRO")) {
				//ITQA, PREPROD, PROD
				// Create Opportunity: Select Site and Product Type
				sf.seleU.switchToFrame(sf.co.selectAproductiFrame);
				sf.seleU.selectTextFromDropDown(sf.co.availableSitesDropdown, Global.dataInput.opportunitySite);
				sf.seleU.wait(3000);
				sf.seleU.selectTextFromDropDown(sf.co.availableProductTypeDropdown,
						Global.dataInput.opportunityProductType);
				
				reportStatusPass(
						methodName + " Selected Availble Opportunity Site and product Type as "
								+ Global.dataInput.opportunitySite + " and " + Global.dataInput.opportunityProductType,
								true, false);
				sf.seleU.wait(1000); 
				
				/// Click on Next Button
				sf.seleU.clickElementByJSE(sf.co.siteAndProductTypeSelectionNextButton);
				reportStatusPass(methodName + " Clicked on Next Button in Select Product in Create Opportunity Page", true,
						false);
				sf.seleU.wait(2000); 
				
				// Create Opportunity: Select Product
				sf.seleU.selectTextFromDropDown(sf.co.productFamilyDropdown, Global.dataInput.opportunityProduct);
				reportStatusPass(methodName + " Selected Product as " + Global.dataInput.opportunityProduct, true, false);
				sf.seleU.hardwait(2000); // Create Opportunity: Select Sub Product

				// sf.dataInput.opportunitySubProduct = "Cable Internet";
				sf.seleU.selectTextFromDropDown(sf.co.productDropdown, Global.dataInput.opportunitySubProduct);
				reportStatusPass(methodName + " Selected Sub Product as " + Global.dataInput.opportunitySubProduct, true,
						false); 
				
				// Click on Next Button		
				sf.seleU.clickElementByJSE(sf.co.productSelection_nextBtn);
				reportStatusPass(methodName + " Clicked on Next Button in Select Product in Create Opportunity Page", true,
						false);
				sf.seleU.wait(5000); 
				
				if (sf.seleU.isElementPresent(sf.co.fileNotFound_nextBtn)) {
					sf.seleU.clickElementByJSE(sf.co.fileNotFound_nextBtn);
					reportStatusPass(methodName + " Clicked on File not found Next Button ", true, true);
					sf.seleU.wait(5000);
				} 
			}
			else  {
				//ITDevStage

				sf.seleU.switchToFrame(sf.co.selectAproductiFrame);
				//Do you require access to locations, pricing or term options that are not available in Vlocity?
				sf.seleU.selectTextFromDropDown(sf.co.businessCreditInput, "Business Credit");
				sf.seleU.wait(2000);
				//Did a Campaign influence this Opportunity?  -> No or Yes if Yes then search a active campaign from next drop down
				sf.seleU.selectTextFromDropDown(sf.co.opportunityCampaignInfluencerDropdown, Global.dataInput.no);
				//Is your opportunity for one of the below products?
				if (Global.dataInput.opportunityProduct.equalsIgnoreCase("Wireless")) {
					sf.seleU.selectTextFromDropDown(sf.co.availableR4BProductOptions, Global.dataInput.opportunityProduct);
				} else {	
				sf.seleU.selectTextFromDropDown(sf.co.availableR4BProductOptions, Global.dataInput.opportunitySubProduct);
				sf.seleU.wait(1000);
				}

				//Do you require access to locations, pricing or term options that are not available in Vlocity?
				sf.seleU.selectTextFromDropDown(sf.co.availableSecondDD, Global.dataInput.no);
				sf.seleU.wait(1000);
				reportStatusPass(methodName + "Selected Do you require access to locations  "+ Global.dataInput.no ,true, false);
				//Will your customer sign a non-negotiable contract with embedded web terms?
				sf.seleU.selectTextFromDropDown(sf.co.availableThirdDD, Global.dataInput.yes);
				reportStatusPass(methodName + "Selected Customer sign a non-negotiable  "+ Global.dataInput.yes ,true, false);
				sf.seleU.wait(1000);
				//			sf.seleU.selectTextFromDropDown(sf.co.availableProductTypeDropdown,Global.dataInput.opportunityProductType);

				//sf.seleU.selectTextFromDropDown(sf.co.availableProductTypeDropdown,Global.dataInput.opportunityProductType);

				//			reportStatusPass(
				//					methodName + " Selected Availble Opportunity Site and product Type as "
				//							+ Global.dataInput.opportunitySite + " and " + Global.dataInput.opportunityProductType,
				//					true, false);

				//reportStatusPass(methodName + " Selected R4B Product as "+ Global.dataInput.opportunityR4BProduct ,true, false);
				//sf.seleU.wait(1000);

				// Click on Next Button
				sf.seleU.clickElementByJSE(sf.co.opportunityRedirectQuestionsNextButton);
				reportStatusPass(methodName + " Clicked on Next Button in Select Product in Create Opportunity Page", true,
						false);
				sf.seleU.wait(2000);
				//Type of Site ->	Available Site
				sf.seleU.selectTextFromDropDown(sf.co.availableSitesDropdown, Global.dataInput.opportunitySite);
				sf.seleU.wait(1000);
				//Product -> Available Product Type
				sf.seleU.selectTextFromDropDown(sf.co.availableProductTypeDropdown,Global.dataInput.opportunityProductType);
				sf.seleU.wait(1000);

				sf.seleU.clickElementByJSE(sf.co.siteAndProductTypeSelectionNextButton);
				sf.seleU.wait(1000);

				// Create Opportunity: Select Product ( Product Family)
				sf.seleU.selectTextFromDropDown(sf.co.productFamilyDropdown, Global.dataInput.opportunityProduct);
				reportStatusPass(methodName + " Selected Product as " + Global.dataInput.opportunityProduct, true, false);
				sf.seleU.hardwait(1000);

				// Create Opportunity: Select Sub Product (Product)
				// sf.dataInput.opportunitySubProduct = "Cable Internet";
				sf.seleU.selectTextFromDropDown(sf.co.productDropdown, Global.dataInput.opportunitySubProduct);
				reportStatusPass(methodName + " Selected Sub Product as " + Global.dataInput.opportunitySubProduct, true,
						false);

				// Click on Next Button
				sf.seleU.clickElementByJSE(sf.co.productSelection_nextBtn);
				reportStatusPass(methodName + " Clicked on Next Button in Select Product in Create Opportunity Page", true,
						false);

				sf.seleU.wait(5000);
				
				if (sf.seleU.isElementPresent(sf.co.messageFNF)) 
				{
							if (sf.seleU.getTextFromWebElement(sf.co.messageFNF)
									.equals(InputData_Sales.messageFNF)) {
								reportStatusPass(methodName + " Validated message displayed when Credit limit value not assigned "
										+ "and checkbox not checked  as: " + InputData_Sales.messageFNF,
										true, true);
								sf.seleU.wait(2000);
				}
				}


				if (sf.seleU.isElementPresent(sf.co.fileNotFound_nextBtn)) {
					sf.seleU.clickElementByJSE(sf.co.fileNotFound_nextBtn);
					reportStatusPass(methodName + " Clicked on File not found Next Button ", true, true);
					sf.seleU.wait(3000);
				}
			}
			// Verify Company name is populated
			if (sf.seleU.getTextFromWebElement(sf.co.opportunityCompanyNameText)
					.equals(Global.dataInput.parentAccountName)) {
				reportStatusPass(methodName + " Company Name is Populated as " + Global.dataInput.parentAccountName,
						true, true);
			}

			// Enter Opportunity Close Date
			sf.seleU.clearAndEnterText(sf.co.opportunityClosedDateInput, InputData.opportunityCloseDate);
			reportStatusPass(methodName + " Selected Opportunity Close Date as " + InputData.opportunityCloseDate, true,
					false);

			// Select Opportunity Stage
			sf.seleU.selectTextFromDropDown(sf.co.opportunityStageDropdown, Global.dataInput.opportunityStage);
			reportStatusPass(methodName + " Selected Opportunity Stage as " + Global.dataInput.opportunityStage, true,
					false);

			// Select Opportunity Type
			sf.seleU.selectTextFromDropDown(sf.co.opportunityTypeDropdown, Global.dataInput.opportunityType);
			reportStatusPass(methodName + " Selected Opportunity Type as " + Global.dataInput.opportunityType, true,
					false);

			// Select Opportunity Amount
			sf.seleU.clearAndEnterText(sf.co.opportunityAmountInput, Global.dataInput.opportunityAmount);
			reportStatusPass(methodName + " Selected Opportunity Amount as " + Global.dataInput.opportunityAmount, true,
					false);

			// Select Campaign influence this Opportunity
			if (sf.seleU.isElementPresent(sf.co.opportunityCampaignInfluencerDropdown)) {
				try {
					sf.seleU.selectTextFromDropDown(sf.co.opportunityCampaignInfluencerDropdown, "No");
				}catch(Exception e) {
					sf.seleU.selectValueFromDropDown(sf.co.opportunityCampaignInfluencerDropdown, "No");
				}
				reportStatusPass(methodName + " Selected Campaign influence as " + "No", true, false);
			}
			// Select Opportunity Additional Information
			sf.seleU.clearAndEnterText(sf.co.opportunityNextStepInput, Global.dataInput.opportunityAdditionalInfo);
			reportStatusPass(methodName + " Selected Opportunity Additional Information as "
					+ Global.dataInput.opportunityAdditionalInfo, true, false);

			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.co.promptForOpportunityInformation_nextBtn);
			reportStatusPass(methodName + " Clicked on Next Button in Opportunity Creation Page ", true, false);

			sf.seleU.wait(7000);

		} catch (Throwable e) {
			reportStatusFail("Unable to enter opprortunity details  Opportunity Creation Page  ", e);
			e.printStackTrace();
		}
	}

	public void enterOpportunityDetailsDebug3() throws IOException {
		try {

			String methodName = "SFDC_Create_Opportunity@: ";

			// Create Opportunity: Select Site and Product Type
			sf.seleU.switchToFrame(sf.co.selectAproductiFrame);

			// Create Opportunity: Select Product
			sf.seleU.selectTextFromDropDown(sf.co.availbleProductsDebug3, "Internet");
			reportStatusPass(methodName + " Selected Product as " + Global.dataInput.opportunitySite, true, false);
			sf.seleU.hardwait(2000);

			// Create Opportunity: Select Sub Product
			sf.seleU.selectTextFromDropDown(sf.co.availbleSubProductsDebug3, "Cable");
			reportStatusPass(methodName + " Selected Sub Product as " + Global.dataInput.opportunityProductType, true,
					false);

			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.co.productSelection_nextBtn);
			reportStatusPass(methodName + " Clicked on Next Button in Select Product in Create Opportunity Page", true,
					false);
			sf.seleU.hardwait(5000);

			// Verify Company name is populated
			if (sf.seleU.getTextFromWebElement(sf.co.opportunityCompanyNameText)
					.equals(Global.dataInput.parentAccountName)) {
				reportStatusPass(methodName + " Company Name is Populated as " + Global.dataInput.parentAccountName,
						true, true);
			}

			// Enter Opportunity Close Date
			sf.seleU.clearAndEnterText(sf.co.opportunityClosedDateInput, InputData.opportunityCloseDate);
			reportStatusPass(methodName + " Selected Opportunity Close Date as " + InputData.opportunityCloseDate, true,
					false);

			// Select Opportunity Stage
			sf.seleU.selectTextFromDropDown(sf.co.opportunityStageDropdown, Global.dataInput.opportunityStage);
			reportStatusPass(methodName + " Selected Opportunity Stage as " + Global.dataInput.opportunityStage, true,
					false);

			// Select Opportunity Type
			sf.seleU.selectTextFromDropDown(sf.co.opportunityTypeDropdown, Global.dataInput.opportunityType);
			reportStatusPass(methodName + " Selected Opportunity Type as " + Global.dataInput.opportunityType, true,
					false);

			// Select Opportunity Amount
			sf.seleU.clearAndEnterText(sf.co.opportunityAmountInput, Global.dataInput.opportunityAmount);
			reportStatusPass(methodName + " Selected Opportunity Amount as " + Global.dataInput.opportunityAmount, true,
					false);

			// Select Opportunity Additional Information
			sf.seleU.clearAndEnterText(sf.co.opportunityNextStepInput, Global.dataInput.opportunityAdditionalInfo);
			reportStatusPass(methodName + " Selected Opportunity Additional Information as "
					+ Global.dataInput.opportunityAdditionalInfo, true, false);

			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.co.promptForOpportunityInformation_nextBtn);
			reportStatusPass(methodName + " Clicked on Next Button in Opportunity Creation Page ", true, false);

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail("Unable to enter opprortunity details  Opportunity Creation Page  ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1- Create Opportunity: Select Product
	 * 
	 *                     2- Create Opportunity: Select Sub Product
	 * 
	 *                     3- Click on Next Button
	 * 
	 *                     4- Verify Company name is populated(for new org
	 *                     navigation)
	 * 
	 *                     5- Verify Successful Switcing from R4B to B2B (old org
	 *                     navigation)
	 */
	public void verifyProductSubProductNavigationOldNewOrg() throws IOException {
		try {

			String methodName = "SFDC_Create_Opportunity@: ";

			Global.dataInput.oldOrgNewOrgSubProducts_PrepareOptions();

			// Verify Old Org Navigation
			int prodFamilyCount = Global.dataInput.oldOrgOpportunityProductFamily.size();

			for (int i = 0; i < prodFamilyCount; i++) {

				// Create Opportunity: Select Site and Product Type
				sf.seleU.switchToFrame(sf.co.selectAproductiFrame);
				sf.seleU.selectTextFromDropDown(sf.co.availableSitesDropdown, Global.dataInput.opportunitySite);
				sf.seleU.wait(1000);
				sf.seleU.selectTextFromDropDown(sf.co.availableProductTypeDropdown,
						Global.dataInput.opportunityProductType);
				reportStatusPass(
						methodName + " Selected Availble Opportunity Site and product Type as "
								+ Global.dataInput.opportunitySite + " and " + Global.dataInput.opportunityProductType,
								true, false);
				sf.seleU.wait(1000);

				// Click on Next Button
				sf.seleU.clickElementByJSE(sf.co.siteAndProductTypeSelectionNextButton);
				reportStatusPass(methodName + " Clicked on Next Button in Select Product in Create Opportunity Page",
						true, false);
				sf.seleU.wait(3000);

				// Create Opportunity: Select Product
				sf.seleU.selectTextFromDropDown(sf.co.productFamilyDropdown,
						Global.dataInput.oldOrgOpportunityProductFamily.get(i));
				reportStatusPass(methodName + " Selected Product Family as "
						+ Global.dataInput.oldOrgOpportunityProductFamily.get(i), true, false);
				sf.seleU.hardwait(2000);

				// Click on Next Button
				sf.seleU.clickElementByJSE(sf.co.productSelection_nextBtn);
				reportStatusPass(methodName + " Clicked on Next Button in Select Product in Create Opportunity Page",
						true, false);
				sf.seleU.hardwait(5000);

				sf.seleU.switchWindow(2);

				// Verify B2B/ITFULL
				if (Global.driver.getCurrentUrl().contains(InputData.oldOrg_url)) {
					reportStatusPass(methodName + " Successful Switcing from R4B to B2B for Product Family "
							+ Global.dataInput.oldOrgOpportunityProductFamily.get(i), true, true);
				} else {
					reportStatusFail("Error in switching to B2B Org for Product Family"
							+ Global.dataInput.oldOrgOpportunityProductFamily.get(i), true);
				}
				sf.seleU.switchWindow(1);
				sf.seleU.closeRecentlyOpenedWindow();
				sf.seleU.hardwait(3000);
				sf.seleU.clickElementByJSE(sf.co.opportunitySelectproductTab);
				sf.seleU.hardwait(3000);
				sf.seleU.refreshPage();

				sf.seleU.wait(3000);

			}

			// Verify New Org Navigation

			int productCount = Global.dataInput.newOrgOpportunityProduct.size();

			for (int j = 0; j < productCount; j++) {

				// Create Opportunity: Select Site and Product Type
				sf.seleU.switchToFrame(sf.co.selectAproductiFrame);
				sf.seleU.selectTextFromDropDown(sf.co.availableSitesDropdown, Global.dataInput.opportunitySite);
				sf.seleU.wait(1000);
				sf.seleU.selectTextFromDropDown(sf.co.availableProductTypeDropdown,
						Global.dataInput.opportunityProductType);
				reportStatusPass(
						methodName + " Selected Availble Opportunity Site and product Type as "
								+ Global.dataInput.opportunitySite + " and " + Global.dataInput.opportunityProductType,
								true, false);
				sf.seleU.wait(1000);

				// Click on Next Button
				sf.seleU.clickElementByJSE(sf.co.siteAndProductTypeSelectionNextButton);
				reportStatusPass(methodName + " Clicked on Next Button in Select Product in Create Opportunity Page",
						true, false);
				sf.seleU.wait(3000);

				// Create Opportunity: Select Product Family
				sf.seleU.selectTextFromDropDown(sf.co.productFamilyDropdown, Global.dataInput.opportunityProduct);
				reportStatusPass(methodName + " Selected Product as " + Global.dataInput.opportunityProduct, true,
						false);
				sf.seleU.hardwait(2000);

				// Create Opportunity: Select Product
				sf.seleU.selectTextFromDropDown(sf.co.productDropdown,
						Global.dataInput.newOrgOpportunityProduct.get(j));
				reportStatusPass(
						methodName + " Selected Sub Product as " + Global.dataInput.newOrgOpportunityProduct.get(j),
						true, false);

				// Click on Next Button
				sf.seleU.clickElementByJSE(sf.co.productSelection_nextBtn);
				reportStatusPass(methodName + " Clicked on Next Button in Select Product in Create Opportunity Page",
						true, false);
				sf.seleU.hardwait(5000);

				// Verify Company name is populated
				if (sf.seleU.getTextFromWebElement(sf.co.opportunityCompanyNameText)
						.equals(Global.dataInput.parentAccountName)) {
					reportStatusPass(
							methodName + " Company Name is Populated as " + Global.dataInput.parentAccountName
							+ " for sub-product " + Global.dataInput.newOrgOpportunityProduct.get(j),
							true, true);
				} else {
					reportStatusFail("Error in Selecting and verifying Opportunity Product and SubProduct  for "
							+ Global.dataInput.newOrgOpportunityProduct.get(j), true);

				}

				sf.seleU.navigateBack();
				sf.seleU.wait(3000);

			}

		} catch (

				Throwable e) {
			reportStatusFail("Error in Selecting and verifying Opportunity Product and SubProduct  ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Check on Create contact Check box
	 * 
	 *                     Click on next button in Opportunity CotactList
	 */
	public void opportunityCreateContact() throws IOException {
		try {
			String methodName = "SFDC_Create_Opportunity_createcontact@: ";

			// Check on Create contact Check box
			sf.seleU.clickElementByJSE(sf.co.opportunityCreateContactCheckbox);
			reportStatusPass(methodName + " Clicked on Create Contact checkbox in Opportunity Creation Page ", true,
					false);

			// Click on next button in Opportunity CotactList
			sf.seleU.clickElementByJSE(sf.co.opportunityContactList_nextBtn);
			reportStatusPass(methodName + " Clicked on  next button in Opportunity CotactList ", true, false);

			Global.dataInput.secondContact_prepareContactData();
			sf.seleU.hardwait(5000);

			// Click on 'Do you want to create a new contact?' checkbox
			if (sf.seleU.isElementPresent(sf.cc.searchContactCreateNewContactCheckbox)) {
				sf.seleU.clickElementByJSE(sf.cc.searchContactCreateNewContactCheckbox);
				reportStatusPass(methodName + " Clicked on  'Do you want to create a new contact?' checkbox ", true,
						false);
			}
			sf.seleU.hardwait(3000);
		} catch (Throwable e) {
			reportStatusFail("Unable to create contact in   Opportunity Creation Page  ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *                   Iterate and choose the appropriate Opportunity Contact Role
	 * 
	 *                   check on contactlist checkbox
	 * 
	 *                   select contact role
	 * 
	 *                   Verify contact is selected successfully
	 * 
	 *                   Click on next button in Opportunity CotactList
	 * 
	 */
	public void selectOpportunityContactRole() throws Exception {

		try {

			String methodName = "SFDC_Select Opportunity Contact Roles@: ";

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.co.opportunitySelectContactListCheckbox);
			boolean isContactSelected = false;
			int contactListSize = sf.co.opportunitySelectContactListCheckbox.size();

			Global.dataInput.contactRole_PrepareOptions();
			verifyDropdownOptions("Contact Role Options", sf.co.opportunityContactRoleListDropdown.get(0),
					Global.dataInput.contactRoleOptions);

			// Iterate on Contact list table and find out the correct conatct and select
			for (int i = 0; i < contactListSize; i++) {

				if (sf.seleU.getTextFromWebElement(sf.co.opportunityContactNameList.get(i))
						.equals(Global.dataInput.accountContact)) {

					// check on contactlist checkbox
					sf.seleU.clickElementByJSE(sf.co.opportunitySelectContactListCheckbox.get(i));

					// select contact role
					sf.seleU.selectTextFromDropDown(sf.co.opportunityContactRoleListDropdown.get(i),
							Global.dataInput.opportunityContactRole);

					isContactSelected = true;
					break;
				}
			}

			// Verify contact is selected successfully
			if (isContactSelected) {
				reportStatusPass(methodName + " Selected Contact for Opportunity as " + Global.dataInput.accountContact,
						true, true);
			} else {

				// check on contactlist checkbox
				sf.seleU.clickElementByJSE(sf.co.opportunitySelectContactListCheckbox.get(0));

				// select contact role
				sf.seleU.selectTextFromDropDown(sf.co.opportunityContactRoleListDropdown.get(0),
						Global.dataInput.opportunityContactRole);

				reportStatusFail(methodName
						+ "No contact created for opportunity , Therefore selected existing   first contact in contact list",
						true);
			}

			// Click on next button in Opportunity CotactList
			sf.seleU.clickElementByJSE(sf.co.opportunityContactList_nextBtn);
			reportStatusPass(methodName + " Clicked on  next button in Opportunity ContactList ", true, false);

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail("Error in Selecting Contact Role in Opportunity  ", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws Exception
	 * 
	 *                   check on contactlist checkbox
	 * 
	 *                   select contact role
	 * 
	 * 
	 * 
	 */

	public void selecetExistingContactInOpportunity() throws IOException {
		try {

			String methodName = "SFDC_Select Opportunity Contact Roles@: ";

			// check on contactlist checkbox
			sf.seleU.clickElementByJSE(sf.co.opportunitySelectContactListCheckbox.get(0));

			// select contact role
			sf.seleU.selectTextFromDropDown(sf.co.opportunityContactRoleListDropdown.get(0),
					Global.dataInput.opportunityContactRole);

			reportStatusPass(methodName + " Selected Contact for Opportunity ",
					true, true);
			sf.seleU.hardwait(2000);
			// Capturing signing authority email Id 
			sf.dataInput.signingAuthEmailIdValue = sf.seleU.getTextFromWebElement(sf.cq.emailId);
						//sf.dataInput.emailIdValue = sf.seleU.getTextFromWebElement(sf.cq.emailId);
			reportStatusPass(methodName + "Signing Authority EmailId is " + sf.dataInput.signingAuthEmailIdValue, true,
								true);
			
			// Click on next button in Opportunity CotactList
			sf.seleU.ScrolltoElementPageCenter(sf.co.opportunityContactList_nextBtn);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.co.opportunityContactList_nextBtn);
			reportStatusPass(methodName + " Clicked on  next button in Opportunity ContactList ", true, false);

			sf.seleU.hardwait(7000);
			sf.seleU.switchToDefaultContent();
			
		} catch (Throwable e) {
			reportStatusFail("Error in  Selecting Existing Contact in Opportunity  ", e);
			e.printStackTrace();
		}
	}


	/**
	 * @throws Exception
	 * 
	 *                   check on contactlist checkbox
	 * 
	 *                   select contact role
	 * 
	 * 
	 * 
	 */

	public void selecetExistingContactInOpportunity(String contact) throws IOException {
		try {

			String methodName = "SFDC_Select existing Opportunity Contact Roles@: ";
			boolean isContactSelected = false;
			sf.seleU.hardwait(5000);

			// Iterate on Conatct list table and find out the correct conatct and select
			for (int i = 0; i < sf.co.opportunitySelectContactListCheckbox.size(); i++) {

				if (sf.seleU.getTextFromWebElement(sf.co.opportunityExistingContactNameList.get(i)).trim()
						.equals(contact)) {
					sf.seleU.wait(4000);

					// check on contactlist checkbox
					sf.seleU.clickElementByJSE(sf.co.opportunitySelectContactListCheckbox.get(i));

					// select contact role
					sf.seleU.selectTextFromDropDown(sf.co.opportunityContactRoleListDropdown.get(i),
							Global.dataInput.opportunityContactRole);

					isContactSelected = true;
					break;
				}
			}

			// Verify contact is selected successfully
			if (isContactSelected) {
				reportStatusPass(methodName + " Selected Contact for Opportunity as " + contact, true, true);
			} else {

				// check on contactlist checkbox
				sf.seleU.clickElementByJSE(sf.co.opportunitySelectContactListCheckbox.get(0));

				// select contact role
				sf.seleU.selectTextFromDropDown(sf.co.opportunityContactRoleListDropdown.get(0),
						Global.dataInput.opportunityContactRole);

				reportStatusFail(methodName
						+ "No contact matched for opportunity , Therefore selected first contact in contact list",
						true);
			}
			sf.seleU.hardwait(4000);
			// Click on next button in Opportunity CotactList
			sf.seleU.clickElementByJSE(sf.co.opportunityContactList_nextBtn);
			reportStatusPass(methodName + " Clicked on  next button in Opportunity CotactList ", true, false);

			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail("Error in Selecting Existing Contact in Opportunity  ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param DropdownName
	 * @param dropdown
	 * @param dropdownOptionsData
	 * @throws IOException
	 * 
	 * 
	 *                     Verify the options present in the given dropdown list
	 */
	public void verifyDropdownOptions(String dropdownName, WebElement dropdown, List<String> dropdownOptionsData)
			throws IOException {
		try {

			String methodName = "SFDC_Select Opportunity Contact Roles@: ";

			boolean dropdownValidated = false;

			for (int i = 1; i < sf.seleU.getDropDownOptions(dropdown).size(); i++) {

				for (int j = 0; j < dropdownOptionsData.size(); j++) {

					if (sf.seleU.getDropDownOptions(dropdown).get(i).contains(dropdownOptionsData.get(j))) {
						dropdownValidated = true;
						break;
					}

				}

				if (dropdownValidated) {
					reportStatusPass(methodName + " Validated Drop down Values for " + dropdownName + " is "
							+ sf.seleU.getDropDownOptions(dropdown).get(i), true, true);
				} else {
					reportStatusFail(methodName + " Invalid Drop down Values for " + dropdownOptionsData.get(i), true);
				}

				dropdownValidated = false;
			}

		} catch (Throwable e) {
			reportStatusFail(" Invalid Info in Dropdown", e);
			e.printStackTrace();
		}
	}
	
	/* 
	 *  Enter opportunity details in transactional opportunity page 
	 * 
	 * @throws IOException
	 */
	public void enterTransactionalOpportunityDetails() throws IOException {
		try {

			String methodName = "SFDC_Create_Opportunity@: ";
			sf.seleU.switchToFrame(sf.co.selectAproductiFrame);
			//Do you require access to locations, pricing or term options that are not available in Vlocity?
			sf.seleU.selectTextFromDropDown(sf.co.businessCreditInput, "Business Credit");
			sf.seleU.wait(2000);
			// Create Opportunity: Select Product ( Product Family)
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.co.internetAndAdvancedNetworksButton);
			reportStatusPass(methodName + " Clicked on Product Family", true, false);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.co.businessInternetButton);
			reportStatusPass(methodName + " Clicked on Product ", true, false);
			sf.seleU.hardwait(2000);
			//Did a Campaign influence this Opportunity?  -> No or Yes if Yes then search a active campaign from next drop down
			sf.seleU.clickElementByJSE(sf.co.tranOppCampQues);
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.co.selectNo);
			sf.seleU.hardwait(2000);
			//sf.seleU.selectTextFromDropDown(sf.co.tranOppCampQues, Global.dataInput.no);
			sf.seleU.clickElementByJSE(sf.co.nextButtonTran);
			sf.seleU.hardwait(15000);

		} catch (Throwable e) {
			reportStatusFail("Unable to enter transactional opprortunity details  Opportunity Creation Page  ", e);
			e.printStackTrace();
		}
	}
	

	/**
	 * @Created on @Date: 02.02.2022 by @author Sakshi.Lnu
	 * 
	 *          1- Create Opportunity: Select Product : Wireless
	 * 
	 *          2- Create Opportunity: Select Sub Product : Transactional plans
	 * 
	 *          3- Click on Next Button
	 * 
	 *          4- Verify Company name is populated
	 * 
	 *          5- Enter Opportunity Close Date
	 * 
	 *          6- Select Opportunity Stage
	 * 
	 *          7- Select Opportunity Type
	 * 
	 *          8- Select Opportunity Amount
	 * 
	 *          9- Select Opportunity Additional Information
	 * 
	 *          10- Click on Next Button
	 * 
	 * 
	 * 
	 * 
	 * @throws IOException
	 */
	public void enterWirelessOpportunityDetails() throws IOException {
		try {

			String methodName = "SFDC_Create_Opportunity@: ";

			if (sf.dataInput.env.equals("PREFIT") || sf.dataInput.env.equals("ITPREPROD")
					|| sf.dataInput.env.equals("WADEVQA") || sf.dataInput.env.equals("WADEVPRO")) {
				// ITQA, PREPROD, PROD
				// Create Opportunity: Select Site and Product Type
				sf.seleU.switchToFrame(sf.co.selectAproductiFrame);
				sf.seleU.hardwait(4000);
				sf.seleU.selectTextFromDropDownWithoutHighlight(sf.co.availableSitesDropdown, Global.dataInput.opportunitySite);
				sf.seleU.selectTextFromDropDownWithoutHighlight(sf.co.availableProductTypeDropdown,
						Global.dataInput.opportunityProductType);

				
				/// Click on Next Button
				sf.seleU.clickElementByJSE(sf.co.siteAndProductTypeSelectionNextButton);
				sf.seleU.wait(2000);
				

				// Create Opportunity: Select Product
				sf.seleU.selectTextFromDropDownWithoutHighlight(sf.co.productFamilyDropdown, Global.dataInput.opportunityProduct);
				reportStatusPass(methodName + " Selected Product as " + Global.dataInput.opportunityProduct, true,
						false);
				sf.seleU.hardwait(2000); 
				// Create Opportunity: Select Sub Product

				// sf.dataInput.opportunitySubProduct = "Cable Internet";
				sf.seleU.selectTextFromDropDownWithoutHighlight(sf.co.productDropdown, Global.dataInput.opportunitySubProduct);
				reportStatusPass(methodName + " Selected Sub Product as " + Global.dataInput.opportunitySubProduct,
						true, false);

				// Click on Next Button
				sf.seleU.clickElementByJSE(sf.co.productSelection_nextBtn);
				reportStatusPass(methodName + " Clicked on Next Button in Select Product in Create Opportunity Page",
						true, false);
				sf.seleU.wait(5000);

				if (sf.seleU.isElementPresent(sf.co.fileNotFound_nextBtn)) {
					sf.seleU.clickElementByJSE(sf.co.fileNotFound_nextBtn);
					reportStatusPass(methodName + " Clicked on File not found Next Button ", true, true);
					sf.seleU.wait(3000);
				}
			} else {
				// ITDevStage

				// Create Opportunity: Select Site and Product Type
				sf.seleU.switchToFrame(sf.co.selectAproductiFrame);
				sf.seleU.selectTextFromDropDownWithoutHighlight(sf.co.opportunityCampaignInfluencerDropdown, Global.dataInput.no);
				// Is your opportunity for one of the below products?
					sf.seleU.selectTextFromDropDownWithoutHighlight(sf.co.availableR4BProductOptions,
							Global.dataInput.opportunityProduct);

				// Do you require access to locations, pricing or term options that are not
				// available in Vlocity?
				sf.seleU.selectTextFromDropDownWithoutHighlight(sf.co.availableSecondDD, Global.dataInput.no);
				// Will your customer sign a non-negotiable contract with embedded web terms?
				sf.seleU.selectTextFromDropDownWithoutHighlight(sf.co.availableThirdDD, Global.dataInput.yes);
				sf.seleU.wait(2000);

				reportStatusPass(methodName + " Selected R4B Product as " + Global.dataInput.opportunityR4BProduct,
						true, false);
				sf.seleU.wait(1000);

				// Click on Next Button
				sf.seleU.clickElementByJSE(sf.co.opportunityRedirectQuestionsNextButton);
				sf.seleU.wait(2000);
				// Type of Site -> Available Site
				sf.seleU.selectTextFromDropDownWithoutHighlight(sf.co.availableSitesDropdown, Global.dataInput.opportunitySite);
				sf.seleU.wait(2000);
				// Product -> Available Product Type
				sf.seleU.selectTextFromDropDownWithoutHighlight(sf.co.availableProductTypeDropdown,
						Global.dataInput.opportunityProductType);
				sf.seleU.wait(2000);
				sf.seleU.clickElementByJSE(sf.co.siteAndProductTypeSelectionNextButton);
				sf.seleU.wait(3000);
				// Create Opportunity: Select Product ( Product Family)
				sf.seleU.selectTextFromDropDownWithoutHighlight(sf.co.productFamilyDropdown, Global.dataInput.opportunityProduct);
				reportStatusPass(methodName + " Selected Product as " + Global.dataInput.opportunityProduct, true,
						false);
				sf.seleU.hardwait(2000);
				// Create Opportunity: Select Sub Product (Product)
				// sf.dataInput.opportunitySubProduct = "Cable Internet";
				sf.seleU.selectTextFromDropDownWithoutHighlight(sf.co.productDropdown, Global.dataInput.opportunitySubProduct);
				reportStatusPass(methodName + " Selected Sub Product as " + Global.dataInput.opportunitySubProduct,
						true, false);

				// Click on Next Button
				sf.seleU.clickElementByJSE(sf.co.productSelection_nextBtn);
				sf.seleU.wait(3000);
//				if (sf.seleU.isElementPresent(sf.co.messageFNF)) {
//					if (sf.seleU.getTextFromWebElement(sf.co.messageFNF).equals(InputData_Sales.messageFNF)) {
//						reportStatusPass(
//								methodName + " Validated message displayed when Credit limit value not assigned "
//										+ "and checkbox not checked  as: " + InputData_Sales.messageFNF,
//								true, true);
//						sf.seleU.wait(3000);
//					}
//				}

				if (sf.seleU.isElementPresent(sf.co.fileNotFound_nextBtn)) {
					sf.seleU.clickElementByJSE(sf.co.fileNotFound_nextBtn);
					reportStatusPass(methodName + " Clicked on File not found Next Button ", true, true);
					sf.seleU.wait(5000);
				}
			}
			// Verify Company name is populated
			if (sf.seleU.getTextFromWebElement(sf.co.opportunityCompanyNameText)
					.equals(Global.dataInput.parentAccountName)) {
				reportStatusPass(methodName + " Company Name is Populated as " + Global.dataInput.parentAccountName,
						true, true);
			}

			// Enter Opportunity Close Date
			sf.seleU.clearAndEnterText(sf.co.opportunityClosedDateInput, InputData.opportunityCloseDate);



			// Select Opportunity Stage
			sf.seleU.selectTextFromDropDownWithoutHighlight(sf.co.opportunityStageDropdown, Global.dataInput.opportunityStage);


			// Select Opportunity Type
			sf.seleU.selectTextFromDropDownWithoutHighlight(sf.co.opportunityTypeDropdown, Global.dataInput.opportunityType);
			// Select Opportunity Amount
			sf.seleU.clearAndEnterText(sf.co.opportunityAmountInput, Global.dataInput.opportunityAmount);


			// Select Campaign influence this Opportunity
				try{
					sf.seleU.selectTextFromDropDownWithoutHighlight(sf.co.opportunityCampaignInfluencerDropdown, "No");
				}catch(Exception e) {
					sf.seleU.selectValueFromDropDown(sf.co.opportunityCampaignInfluencerDropdown, "No");
				}
				
				reportStatusPass(methodName + " Selected Campaign influence as " + "No", true, false);
			// Select Opportunity Additional Information
			sf.seleU.clearAndEnterText(sf.co.opportunityNextStepInput, Global.dataInput.opportunityAdditionalInfo);

			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.co.promptForOpportunityInformation_nextBtn);
			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail("Unable to enter wireless opprortunity details  Opportunity Creation Page  ", e);
			e.printStackTrace();
		}
	}

/*
 * selectTextFromDropDownWithoutHighlight is throwing Exception
 */
	
	public void enterWirelessOpportunityDetails_FIT() throws IOException {
		try {

			String methodName = "SFDC_Create_Opportunity@: ";

			if (sf.dataInput.env.equals("PREFIT") || sf.dataInput.env.equals("ITPREPROD")
					|| sf.dataInput.env.equals("WADEVQA") || sf.dataInput.env.equals("WADEVPRO")) {
				// ITQA, PREPROD, PROD
				// Create Opportunity: Select Site and Product Type
				sf.seleU.switchToFrame(sf.co.selectAproductiFrame);
				sf.seleU.selectTextFromDropDownWithoutHighlight(sf.co.availableSitesDropdown, Global.dataInput.opportunitySite);
				sf.seleU.selectTextFromDropDownWithoutHighlight(sf.co.availableProductTypeDropdown,
						Global.dataInput.opportunityProductType);

				
				/// Click on Next Button
				sf.seleU.clickElementByJSE(sf.co.siteAndProductTypeSelectionNextButton);
				sf.seleU.wait(2000);
				

				// Create Opportunity: Select Product
				sf.seleU.selectTextFromDropDownWithoutHighlight(sf.co.productFamilyDropdown, Global.dataInput.opportunityProduct);
				reportStatusPass(methodName + " Selected Product as " + Global.dataInput.opportunityProduct, true,
						false);
				sf.seleU.hardwait(2000); 
				// Create Opportunity: Select Sub Product

				// sf.dataInput.opportunitySubProduct = "Cable Internet";
				sf.seleU.selectTextFromDropDownWithoutHighlight(sf.co.productDropdown, Global.dataInput.opportunitySubProduct);
				reportStatusPass(methodName + " Selected Sub Product as " + Global.dataInput.opportunitySubProduct,
						true, false);

				// Click on Next Button
				sf.seleU.clickElementByJSE(sf.co.productSelection_nextBtn);
				reportStatusPass(methodName + " Clicked on Next Button in Select Product in Create Opportunity Page",
						true, false);
				sf.seleU.wait(5000);

				if (sf.seleU.isElementPresent(sf.co.fileNotFound_nextBtn)) {
					sf.seleU.clickElementByJSE(sf.co.fileNotFound_nextBtn);
					reportStatusPass(methodName + " Clicked on File not found Next Button ", true, true);
					sf.seleU.wait(3000);
				}
			} else {
				// ITDevStage

				// Create Opportunity: Select Site and Product Type
				sf.seleU.switchToFrame(sf.co.selectAproductiFrame);
				sf.seleU.selectTextFromDropDownWithoutHighlight_Fit(sf.co.opportunityCampaignInfluencerDropdown, Global.dataInput.no);
				// Is your opportunity for one of the below products?
					sf.seleU.selectTextFromDropDownWithoutHighlight_Fit(sf.co.availableR4BProductOptions,
							Global.dataInput.opportunityProduct);

				// Do you require access to locations, pricing or term options that are not
				// available in Vlocity?
				sf.seleU.selectTextFromDropDownWithoutHighlight_Fit(sf.co.availableSecondDD, Global.dataInput.no);
				// Will your customer sign a non-negotiable contract with embedded web terms?
				sf.seleU.selectTextFromDropDownWithoutHighlight_Fit(sf.co.availableThirdDD, Global.dataInput.yes);
				sf.seleU.wait(2000);

/*				reportStatusPass(methodName + " Selected R4B Product as " + Global.dataInput.opportunityR4BProduct,
						true, false); */
				sf.seleU.wait(1000);

				// Click on Next Button
				sf.seleU.clickElementByJSE(sf.co.opportunityRedirectQuestionsNextButton);
				sf.seleU.wait(2000);
				// Type of Site -> Available Site
				sf.seleU.selectTextFromDropDownWithoutHighlight_Fit(sf.co.availableSitesDropdown, Global.dataInput.opportunitySite);
				sf.seleU.wait(2000);
				// Product -> Available Product Type
				sf.seleU.selectTextFromDropDownWithoutHighlight_Fit(sf.co.availableProductTypeDropdown,
						Global.dataInput.opportunityProductType);
				sf.seleU.wait(2000);
				sf.seleU.clickElementByJSE(sf.co.siteAndProductTypeSelectionNextButton);
				sf.seleU.wait(3000);
				// Create Opportunity: Select Product ( Product Family)
				sf.seleU.selectTextFromDropDownWithoutHighlight_Fit(sf.co.productFamilyDropdown, Global.dataInput.opportunityProduct);
				reportStatusPass(methodName + " Selected Product as " + Global.dataInput.opportunityProduct, true,
						false);
				sf.seleU.hardwait(2000);
				// Create Opportunity: Select Sub Product (Product)
				// sf.dataInput.opportunitySubProduct = "Cable Internet";
				sf.seleU.selectTextFromDropDownWithoutHighlight_Fit(sf.co.productDropdown, Global.dataInput.opportunitySubProduct);
				reportStatusPass(methodName + " Selected Sub Product as " + Global.dataInput.opportunitySubProduct,
						true, false);

				// Click on Next Button
				sf.seleU.clickElementByJSE(sf.co.productSelection_nextBtn);
				sf.seleU.wait(3000);
//				if (sf.seleU.isElementPresent(sf.co.messageFNF)) {
//					if (sf.seleU.getTextFromWebElement(sf.co.messageFNF).equals(InputData_Sales.messageFNF)) {
//						reportStatusPass(
//								methodName + " Validated message displayed when Credit limit value not assigned "
//										+ "and checkbox not checked  as: " + InputData_Sales.messageFNF,
//								true, true);
//						sf.seleU.wait(3000);
//					}
//				}

				if (sf.seleU.isElementPresent(sf.co.fileNotFound_nextBtn)) {
					sf.seleU.clickElementByJSE(sf.co.fileNotFound_nextBtn);
					reportStatusPass(methodName + " Clicked on File not found Next Button ", true, true);
					sf.seleU.wait(5000);
				}
			}
			// Verify Company name is populated
			if (sf.seleU.getTextFromWebElement(sf.co.opportunityCompanyNameText)
					.equals(Global.dataInput.parentAccountName)) {
				reportStatusPass(methodName + " Company Name is Populated as " + Global.dataInput.parentAccountName,
						true, true);
			}

			// Enter Opportunity Close Date
			sf.seleU.clearAndEnterText(sf.co.opportunityClosedDateInput, InputData.opportunityCloseDate);



			// Select Opportunity Stage
			sf.seleU.selectTextFromDropDownWithoutHighlight_Fit(sf.co.opportunityStageDropdown, Global.dataInput.opportunityStage);


			// Select Opportunity Type
			sf.seleU.selectTextFromDropDownWithoutHighlight_Fit(sf.co.opportunityTypeDropdown, Global.dataInput.opportunityType);
			// Select Opportunity Amount
			sf.seleU.clearAndEnterText(sf.co.opportunityAmountInput, Global.dataInput.opportunityAmount);


			// Select Campaign influence this Opportunity
				sf.seleU.selectTextFromDropDownWithoutHighlight_Fit(sf.co.opportunityCampaignInfluencerDropdown, "No");
				reportStatusPass(methodName + " Selected Campaign influence as " + "No", true, false);
			// Select Opportunity Additional Information
			sf.seleU.clearAndEnterText(sf.co.opportunityNextStepInput, Global.dataInput.opportunityAdditionalInfo);

			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.co.promptForOpportunityInformation_nextBtn);
			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail("Unable to enter wireless opprortunity details  Opportunity Creation Page  ", e);
			e.printStackTrace();
		}
	}

}

