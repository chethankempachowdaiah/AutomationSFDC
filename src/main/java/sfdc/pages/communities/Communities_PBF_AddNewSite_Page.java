package sfdc.pages.communities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

import sfdc.pages.qm.SFDC_PBF_SiteSelection_Page;

/**
 * @author Anukriti.Chawla, Date:21/07/2021
 *
 *         Communities Persona Based Buy Flow Add new site Page
 */
public class Communities_PBF_AddNewSite_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public Communities_PBF_AddNewSite_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "Communities_PBF_AddNewSite@:";
	}

	/**
	 * @throws IOException
	 * 
	 *                     "On the Step 1 of the Add New Site:
	 *                     
	 *                     1.Validate the UI with verbiages as per mock up is displayed.
	 *                     
	 *                     2.Validate all the mandatory and optional fields are displayed 
	 *                     which only accepts characters that are permissible on the fields
	 *                     
	 *                     3.Validate the ""Search for my site"" CTA is displayed which is 
	 *                     enabled only when the user has entered all the mandatory fields."
	 *                     
	 *                     2a.Optional Fields:Unit/Floor and Direction and Optional Fields
	 *                     2b.Mandatory Fields:Street No./Street Name/Street Type/City/Province/Postal code are Mandatory fields
	 *                     2c.Supporting values:All fields are alphanumeric except Province which is 2 letter abbreviations of canadian provinces.
	 *                     2d.Field Type:
	 *                     	1.Unit/Floor,Street No./Street Name/City/Postal Code are Input fields.
	 *                     2.Street Type/Direction are text fields.
	 *                     3.Province is a drop-down.
	 *                     
	 *                     3.Search for my Site CTA is functional and clicking on it collapses Step 1 and expands Step 2     
	 * 
	 */
	//Changes made by Prab Sharan Singh
	public void fillSiteInfo() throws IOException {
		try {
			verifyFieldDisplayed("Add New Site Address", sf.comPBFAddSite.addNewSiteHeader);
			
			//Verify Search for my site button is diabled as all mandatory details are yet not filled
			verifyFieldDisabled("Search My site", sf.comPBFAddSite.searchMySiteButton);
			sf.seleU.hardwait(3000);
			//Fill Details
			sf.seleU.clearAndEnterText(sf.cba.billingAddressInput, InputData_Communities.commPBFnewsiteaddress);
			
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.cba.businessAddress);
			sf.seleU.hardwait(6000);
			
			
			//Verify Search for my site button is now enabled
			verifyFieldEnabled("Search My site", sf.comPBFAddSite.searchMySiteButton);
			sf.seleU.wait(10000);
			//Click on Search my site
			sf.seleU.clickElementByJSE(sf.comPBFAddSite.searchMySiteButton);
			sf.seleU.wait(4000);
			reportStatusPass(methodName + " Clicked on Search My Site button", true, true);
				/*verifyFieldDisplayed("Add New Site Address", sf.comPBFAddSite.addNewSiteHeader);
				//Verify fields are mandatory
				verifyFieldIsRequired("Street Number", sf.comPBFAddSite.streetNoInput);
				verifyFieldIsRequired("Street Name", sf.comPBFAddSite.streetNameInput);
				verifyFieldIsRequired("Street Type", sf.comPBFAddSite.streetTypeInput);
				verifyFieldIsRequired("City", sf.comPBFAddSite.cityInput);
				verifyFieldIsRequired("Postal Code", sf.comPBFAddSite.postalCodeInput);
				
//				//Enter Wrong pattern for postal code and validate error message
//				if(InputData_Communities.commPBFUser=="Agent") {
//					sf.seleU.clearAndEnterText(sf.comPBFAddSite.postalCodeInput, AdditionalUtilities.generateRandomCharacters(6));
//					sf.seleU.enterText(sf.comPBFAddSite.postalCodeInput,  Keys.TAB);
//					reportStatusPass(methodName + " Entered random characters in postal code", false, false);
//					verifyFieldDisplayed("Error message for pattern not match for postal code", sf.comPBFAddSite.patternNotMatchError);
//					
//					sf.seleU.refreshPage();
//					sf.seleU.wait(10000);
//					SFDC_PBF_SiteSelection_Page.moveToSiteSelectionPage();
//					Communities_PBF_Page.addNewSite();
//				}
				//Fill Details
				sf.seleU.clearAndEnterText(sf.comPBFAddSite.streetNoInput, InputData_Communities.commPBFSiteAddressStreetNo);
				sf.seleU.clearAndEnterText(sf.comPBFAddSite.streetNameInput, InputData_Communities.commPBFSiteAddressStreetName);
				sf.seleU.clearAndEnterText(sf.comPBFAddSite.streetTypeInput, InputData_Communities.commPBFSiteAddressStreetType);
				sf.seleU.clearAndEnterText(sf.comPBFAddSite.cityInput, InputData_Communities.commPBFSiteAddressCity);
				reportStatusPass(methodName + " Entered :\n Street no- " + InputData_Communities.commPBFSiteAddressStreetNo
						+ "\n Street Type- " + InputData_Communities.commPBFSiteAddressStreetType
						+ "\n Street Name- " + InputData_Communities.commPBFSiteAddressStreetName
						+ "\n City- " + InputData_Communities.commPBFSiteAddressCity
						, true, true);
				
				
				//Fill Province
				sf.seleU.clickOnElement( sf.comPBFAddSite.provinceInput);
				sf.seleU.wait(2000);
				sf.seleU.clickOnElement(driver.findElement(By.xpath("//span[.='" +  InputData_Communities.commPBFSiteAddressProvince + "']/parent::div")));
				
				//Verify Search for my site button is diabled as all mandatory details are yet not filled
				verifyFieldDisabled("Search My site", sf.comPBFAddSite.searchMySiteButton);
				
				
				//Fill Valid postal code
				sf.seleU.clearAndEnterText(sf.comPBFAddSite.postalCodeInput, InputData_Communities.commPBFSiteAddressPostalCode);
				sf.seleU.enterText(sf.comPBFAddSite.postalCodeInput,  Keys.TAB);
				reportStatusPass(methodName + " Entered Province- " + InputData_Communities.commPBFSiteAddressProvince 
						+ " and Postal code- " + InputData_Communities.commPBFSiteAddressPostalCode, false, false);
				
				//Verify Search for my site button is now enabled
				verifyFieldEnabled("Search My site", sf.comPBFAddSite.searchMySiteButton);
				sf.seleU.wait(10000);
				//Click on Search my site
				sf.seleU.clickElementByJSE(sf.comPBFAddSite.searchMySiteButton);
				sf.seleU.wait(4000);
				reportStatusPass(methodName + " Clicked on Search My Site button", true, true);*/
				
				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Filling Site address is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     User selects on one of the addresses displayed      
	 * 
	 */
	public void selectAddress() throws IOException {
		try {
			
				//Verify Step1 collapsed and Step2 is expanded
				verifyFieldNotDisplayed("Step1 Details", sf.comPBFAddSite.searchAddressOpenDIV);
				verifyFieldDisplayed("Step2 Details", sf.comPBFAddSite.selectAddressOpenDIV);
				
				if (InputData_Communities.commPBFAddProductName.contains("bps")) {
					sf.seleU.clickElementByJSE(sf.comPBF.filterInputBoxFibre);
					sf.seleU.wait(5000);
					sf.seleU.enterText(sf.comPBF.filterInputBoxFibre, InputData_Communities.commPBFSiteAddressStreetName);
					reportStatusPass(methodName + " Searched " + InputData_Communities.commPBFSiteAddressStreetName + " in filter by keywords Box", true, true);
					
					sf.seleU.wait(5000);
					InputData_Communities.commPBFselectedAddress = sf.seleU.getTextFromWebElement(sf.comPBFAddSite.serviceAddressValuesFibre.get(0));
					sf.seleU.clickElementByJSE(sf.comPBF.serviceAddressFibreRadioButtons.get(0));
	
				} else {
					sf.seleU.clickElementByJSE(sf.comPBF.filterInputBox);
					sf.seleU.wait(5000);
					sf.seleU.enterText(sf.comPBF.filterInputBox, InputData_Communities.commPBFSiteAddressStreetName);
					reportStatusPass(methodName + " Searched " + InputData_Communities.commPBFSiteAddressStreetName + " in filter by keywords Box", true, true);
					
					sf.seleU.wait(5000);
					InputData_Communities.commPBFselectedAddress = sf.seleU.getTextFromWebElement(sf.comPBFAddSite.serviceAddressValues.get(0));
					sf.seleU.clickElementByJSE(sf.comPBF.serviceAddressRadioButtons.get(0));
	
				}
				
				sf.seleU.wait(5000);
				reportStatusPass(methodName + " Selected first address from list : "
						+ InputData_Communities.commPBFselectedAddress, true, true);
				
				sf.seleU.clickElementByJSE(sf.comPBFAddSite.confirmAddressButton);
				sf.seleU.wait(3000);
				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Selecting SIte address is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	
	/**
	 * @throws IOException
	 * 
	 *         On Step 2 of the Add New Site:
	 *         
	 *         	1. Based on the Address entered on the Step 1 of the Add new site page, below columns are presented on the Serviceability Data Table:
	 *         		a.Unit/Floor
	 *         		b.Street Address
	 *         		c.City
	 *         	    d.Province
	 *         		e.Postal Code
	 *          2. I don't see my address row is displayed.   
	 *          
	 *          Validate user is able to validate the following additional details on the Step 2:
	 *          
	 *           1.Filter by Keywords field is displayed which should allow the search for Strings and display the search results based on that.
	 *           2.Pagination : Search Results should display upto 5 results and offer pagination for results more than 5.
	 *           3.Sorting : Sorting of all the columns is available on the data-table.
	 *           4.Total : Total should display the total number of search results for entered address.
	 *           
	 *           
	 */
	public void verifyServiceAddressesTableLayout() throws IOException {
		try {
			
				verifyFieldDisplayed("I don't see my address option", sf.comPBFAddSite.iDontSeeMyAddressOption);
				verifySortingOnTable();
				Communities_PBF_Page.verifyPaginationOnTable();
				verifyFieldDisplayed("Total Addresses",sf.comPBF.totalServiceAddresses);
				//Filter validation covered in selectadress()
		
		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying Service address table layout is unscuccesfull", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *         Select I dont see my address 
	 *           
	 */
	public void selectIDontSeeMyAddress() throws IOException {
		try {
			
				sf.seleU.clickElementByJSE(sf.comPBF.serviceAddressRadioButtons.get(0));

				sf.seleU.wait(5000);
				reportStatusPass(methodName + " Selected I dont see my address from list : "
						, true, true);
			
				sf.seleU.clickElementByJSE(sf.comPBFAddSite.confirmAddressButton);
				sf.seleU.wait(3000);
				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Selecting radio button is unscuccesfull", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *         Verify Non serviceability error message 
	 *           
	 */
	public void verifyNonServiceablityErrorMessage() throws IOException {
		try {
			
			verifyFieldDisplayed("Non Serviceability Error message", sf.comPBFAddSite.noServiceErrorMessage);
			
				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Validating non serviceability message is unscuccesfull", e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @throws IOException
	 * 
	 *                     Verify Table Sorting
	 */
	
	public static void verifySortingOnTable() throws IOException {
		try {
			
			if ((sf.seleU.getElementAttribute(sf.comPBFAddSite.streetAddressHeaderButton, InputData_Communities.dataSortableAttributeName)).equalsIgnoreCase("true"))
				reportStatusPass(methodName + " Sorting by Street Address is doable", true, true);
			else
				reportStatusFail(methodName + " Sorting by Street Address is not working", true);
			

			if ((sf.seleU.getElementAttribute(sf.comPBFAddSite.cityHeaderButton, InputData_Communities.dataSortableAttributeName)).equalsIgnoreCase("true"))
				reportStatusPass(methodName + "Sorting by City is doable", true, true);
			else
				reportStatusFail(methodName + "Sorting by City is not working", true);
			

			if ((sf.seleU.getElementAttribute(sf.comPBFAddSite.provinceHeaderButton, InputData_Communities.dataSortableAttributeName)).equalsIgnoreCase("true"))
				reportStatusPass(methodName + "Sorting by Province is doable", true, true);
			else
				reportStatusFail(methodName + "Sorting by Province is not working", true);
			

			if ((sf.seleU.getElementAttribute(sf.comPBFAddSite.postalCodeHeaderButton, InputData_Communities.dataSortableAttributeName)).equalsIgnoreCase("true"))
				reportStatusPass(methodName + "Sorting by Postal Code is doable", true, true);
			else
				reportStatusFail(methodName + "Sorting by Postal Code is not working", true);
						
		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying Sorting of Table is unsuccesfull", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 * 					  Ensure Edit links are displayed against Step 1 and Step 2 and both the Steps should be collapsed
	 * 					
	 * 					  "Validate the following on Step 3 of Add New Site:
	 * 
	 * 						1.Site address details are visible to the user. 
	 * 						This is the address user selected on Step 2
	 * 
	 * 					  "Validate the CTAs on the Add New Site Page:
	 * 
	 * 						1.Cancel and Back which should take the user back to the Site selection Page. 
	 * 						2.Save and Add Site which when clicked lands the user to the Shopping cart page
	 * 
	 *                    Confirm and Proceed to save site     
	 * 
	 */
	public void confirmAndProceed() throws IOException {
		try {
				sf.seleU.wait(3000);
				verifyFieldDisplayed("Cancel And Back Button", sf.comPBFAddSite.cancelAndBackButton);
				verifyFieldDisplayed("Edit Button for Step1", sf.comPBFAddSite.searchAddressEditButton);
				verifyFieldDisplayed("Edit Button for Step2", sf.comPBFAddSite.selectAddressEditButton);
				verifyFieldValue("Selected Address in Step3", sf.comPBFAddSite.selectedAddressText, InputData_Communities.commPBFSiteAddressStreetName);
				
				sf.seleU.clickElementByJSE(sf.comPBFAddSite.saveSiteButton);
				sf.seleU.wait(13000);
				reportStatusPass(methodName + " Clicked on confirm and save site address", true, true);
				
				if(sf.seleU.isElementDisplayed(sf.comPBF.nextButton))
					sf.seleU.clickElementByJSE(sf.comPBF.nextButton);
				sf.seleU.wait(60000);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Saving SIte address is unscuccesfull", e);
			e.printStackTrace();
		}
	}

		/**
	 * @throws IOException
	 * 
	 *                     Verify Field is disabled
	 */
	public void verifyFieldDisabled(String fieldName, WebElement element) throws IOException {
		try {

			if (!element.isEnabled()) {
				reportStatusPass(methodName + " Validated " + fieldName + " field is disabled and not displayed", true,
						true);
			} else {
				reportStatusFail(
						methodName + " Field " + fieldName + " is not a disabled field, It should be a disabled", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is enabled
	 */
	public void verifyFieldEnabled(String fieldName, WebElement element) throws IOException {
		
		try {

			if (element.isEnabled()) {
				reportStatusPass(methodName + " Validated " + fieldName + " field is enabled and displayed", true,
						true);
			} else {
				reportStatusFail(methodName + " Field " + fieldName + " is not enabled field, It should be a enabled",
						true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                     Return Field is enabled/not
	 */
	public boolean isFieldEnabled(String fieldName, WebElement element) throws IOException {
		
		boolean enabled = false;
		
		try {

			if (element.isEnabled()) {
				enabled = true;
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
		return enabled;
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
	 *                     /**
	 * @param field
	 * @throws IOException
	 * 
	 *                     verify given field is mandatory fields
	 */
	public void verifyFieldIsRequired(String fieldName, WebElement field) throws IOException {

		try {

			if (field.getAttribute("required").equals("true")) {
				reportStatusPass(methodName + " Validated " + fieldName + " field is required field", true, true);
			} else {
				reportStatusFail(
						methodName + " Field " + fieldName + "is not a mandatory field, It should be a mandatory field",
						true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in validating required field", e);
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
				reportStatusPass(methodName + " Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(methodName + " " + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

}
