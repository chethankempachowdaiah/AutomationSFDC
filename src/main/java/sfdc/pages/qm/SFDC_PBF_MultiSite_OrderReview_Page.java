package sfdc.pages.qm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

import sfdc.pages.communities.Communities_PBF_ShoppingCart_Page;

/**
 * @author Anukriti.Chawla, Date:2 Feb 2022
 *
 *         SFDC Persona Based Buy Flow Page
 * 
 */
public class SFDC_PBF_MultiSite_OrderReview_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;
	List<String>  expectedProductsCategory = new ArrayList<>();
	
	public SFDC_PBF_MultiSite_OrderReview_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_PBF_OrderReview@:";
	}

	/**
	 *   //Ac00461038//
	 * 		Multisite	
	 * 			
	 * 			Verify Value of Selected Address- 
	 * 
	 * 
	 * @throws IOException
	 */
	public void validateOrderReviewPageWithMultipleSites() throws IOException {

		try {
			
			List<String> expectedAddresses = new ArrayList<>();
			List<String> actualAddresses = new ArrayList<>();
			
			//Expected List of addresses
			for (int i=1; i<= InputData_Communities.selectedAddress.size() ; i++) {
				expectedAddresses.add(InputData_Communities.selectedAddress.get("Site" + i));
			}	
			
			//Actual list of addresses
			for(int j=0; j<sf.mulSiteShopCart.addedSitesBlocks.size();j++) {
				String address = sf.seleU.getTextFromWebElement(sf.mulSiteShopCart.serviceAddressText.get(j));
				address = address.split(",")[0];
				if (address.contains("-"))
					address = address.split("-")[1];
				actualAddresses.add(address);
			}
			// Sort expected and actualValues list to compare
			Collections.sort(expectedAddresses);
			Collections.sort(actualAddresses);
			sf.seleU.scrollByCoOrdinates(3);
			// Verify Lists for expected and actual reasons are equal
			if (expectedAddresses.equals(actualAddresses)) {
				reportStatusPass(methodName + " All expected addresses are present on order review page : "
						+ " , Expected Addresses --> "
						+ AdditionalUtilities.getAsString(expectedAddresses) + " Actual addresses --> "
						+ AdditionalUtilities.getAsString(actualAddresses), true, true);
			} else {
				reportStatusFail(methodName
						+ " All expected addresses are not present on order review page : "
						+ " , Expected Addresses --> " + AdditionalUtilities.getAsString(expectedAddresses)
						+ " Actual Addresses --> " + AdditionalUtilities.getAsString(actualAddresses), true);
			}
			
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Order Review Page with added sites", e);
			e.printStackTrace();
		}

	}
	/**
	 * 			Validate the Order Review Page for the account info section:
	 * 
	 * 				1.Order contact: Verify has values for Order Owner(Full name, Phone Number, E-mail address)
	 * 
	 * 				2.Signing Authority: Verify has values for Signing Authority (Full name, Phone Number, E-mail address)
	 * 
	 * 				3.Billing Address: Verify has values for address associated to the Business Account"
	 * 
	 *  		 
	 * @throws IOException
	 */
	public void validateOrderReviewPageWithoutValues() throws IOException {

		try {
				verifyFieldDisplayed("Order Review Page Header", sf.comPBFOrderReview.orderReviewPageHeader);
				verifyFieldDisplayed("Order Contact Block", sf.comPBFOrderReview.orderContactLabel);
				verifyFieldDisplayed("Signing Authority Block", sf.comPBFOrderReview.signingAuthorityLabel);
				verifyFieldDisplayed("Billing Address Block", sf.comPBFOrderReview.orderContactBillingAddressLabel);
				
				//Verify Value of Contact, Signing Authority and Billing Address
				verifyFieldHasValue("Order Contact Full Name", sf.comPBFOrderReview.orderContactName);
				
				verifyFieldHasValue("Order Signing Authority Phone Number", sf.comPBFOrderReview.signingAuthorityPhoneNumber);
				verifyFieldHasValue("Order Contact Phone Number", sf.comPBFOrderReview.orderContactPhoneNumber);
				verifyFieldHasValue("Order Contact Email ID", sf.comPBFOrderReview.orderContactEmailID);
				verifyFieldHasValue("Order Signing Authority Full Name", sf.comPBFOrderReview.signingAuthorityName);
				verifyFieldHasValue("Order Signing Authority Email ID", sf.comPBFOrderReview.signingAuthorityEmailID);
				verifyFieldHasValue("Order Contact Billing Address", sf.comPBFOrderReview.orderContactBillingAddressValue);
				
				sf.dataInput.siteContactName = sf.seleU.getTextFromWebElement(sf.comPBFOrderReview.signingAuthorityName);
				sf.dataInput.siteContactEmailId = sf.seleU.getTextFromWebElement(sf.comPBFOrderReview.signingAuthorityEmailID);
				
				//Scroll down
				sf.seleU.scrollByCoOrdinates(2);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Order Review Page", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * 	//Ac00461038//	
	 * 	Multisite
	 * 			Validate the following on the Order Review Page:
	 * 
	 * 			1.Main Line Product should be displayed under the Products Column
	 * 
	 * 			2.Validate the Main Line Product displays the greyed text with the Term associated with the Product
	 * 			  
	 * 			3. AddOns are added perfectly to the cart
	 * 
	 *  		4. Validate AddOns expected Qty
	 *  
	 *  		5. Verfiy Onet time fees section for all sites
	 *  
	 *  		6. Verify Tota mnthly and one time fee section on cart page
	 * 
	 * @throws IOException
	 */
	public void validatOrderReviewPageWithAddedProductsForAllSites() throws IOException {

		try {
			
			//Click on all Show more buttons to expand each block
			for(int k=0; k < InputData_Communities.mulPBFNumOfSites ;k++) {
				try{
					sf.seleU.clickElementByJSE(sf.mulSiteShopCart.showMoreButton.get(0));
				} catch (IndexOutOfBoundsException e) {}
				reportStatusPass(methodName + " Clicked on Show more Buttons for all sites", false, false);
				sf.seleU.wait(2000);
			}
			
			
			List<String> expectedProductTerms = new ArrayList<>();
			List<String> expectedAddOns = new ArrayList<>();
			List<String> expectedAddOnQty = new ArrayList<>();
			List<String> expectedProducts = new ArrayList<>();
			
			//Add Products for multiple sites one by one
			for (int i=0; i< InputData_Communities.mulPBFNumOfSites ; i++) {
				
				HashMap<String, String> siteWiseData = InputData_Communities.sitesData.get("Site" + (i+1));
				
				InputData_Communities.mulPBFSiteProductTerm = siteWiseData.get("Product Term");
				InputData_Communities.mulPBFSiteProductPlanName = siteWiseData.get("Product Plan Name");
				InputData_Communities.mulPBFSiteProductAddOnName = siteWiseData.get("Product AddOn Name");
				InputData_Communities.mulPBFSiteProductAddOnQty =siteWiseData.get("Product AddOn Qty");
				expectedProductsCategory.add(siteWiseData.get("Product Name"));
				//Add Product Names to list
				if(InputData_Communities.mulPBFSiteProductPlanName.contains(",")) {
					expectedProducts.add(InputData_Communities.mulPBFSiteProductPlanName.split(",")[0]);
					expectedProducts.add(InputData_Communities.mulPBFSiteProductPlanName.split(",")[1]);
					//If two products, Term is to be added twice, once here and once out of if else
					if (InputData_Communities.mulPBFSiteProductTerm .equalsIgnoreCase("3 year term"))
						expectedProductTerms.add("36-month term");
					else if(InputData_Communities.mulPBFSiteProductTerm.equalsIgnoreCase("5 year term"))
						expectedProductTerms.add("60-month term");
					else
						expectedProductTerms.add(InputData_Communities.mulPBFSiteProductTerm);
				} else {
					
					expectedProducts.add(InputData_Communities.mulPBFSiteProductPlanName);
				
				}
				//Add in months the term
				if (InputData_Communities.mulPBFSiteProductTerm.equalsIgnoreCase("3 year term"))
					expectedProductTerms.add("36-month term");
				else if(InputData_Communities.mulPBFSiteProductTerm.equalsIgnoreCase("5 year term"))
					expectedProductTerms.add("60-month term");
				else
					expectedProductTerms.add(InputData_Communities.mulPBFSiteProductTerm);
				
				//Add Addon
				if (!InputData_Communities.mulPBFSiteProductAddOnName.equals("NA")) {
					if(InputData_Communities.mulPBFSiteProductAddOnName.contains(",")) {
						
						expectedAddOns.add(InputData_Communities.mulPBFSiteProductAddOnName.split(",")[0]);
						expectedAddOns.add(InputData_Communities.mulPBFSiteProductAddOnName.split(",")[1]);
						expectedAddOnQty.add(InputData_Communities.mulPBFSiteProductAddOnQty.split(",")[0]);
						expectedAddOnQty.add(InputData_Communities.mulPBFSiteProductAddOnQty.split(",")[1]);
					}else {
						expectedAddOns.add(InputData_Communities.mulPBFSiteProductAddOnName);
						expectedAddOnQty.add(InputData_Communities.mulPBFSiteProductAddOnQty);
					}
				}
				
			}
			
			//Verify Product Name
			for(int i=0; i < expectedProducts.size() ; i++) {
				
				verifyFieldValue("Added Product Name", sf.mulSiteOrderReview.addedProductName.get(i), expectedProducts.get(i));
				verifyFieldValue("Added Product Term", sf.mulSiteOrderReview.addedProductTerm.get(i), expectedProductTerms.get(i));
				sf.seleU.scrollByCoOrdinates(1);
			}
			
			sf.seleU.scrollUpByCoOrdinates();
			
			//Verify Product Name
			for(int i=0; i < expectedAddOns.size() ; i++) {		
				verifyFieldValue("Added Product Name", sf.mulSiteOrderReview.addedAddOnsName.get(i), expectedAddOns.get(i));
				verifyFieldValue("Added AddOn Qty", sf.mulSiteOrderReview.addedAddOnsQty.get(i), expectedAddOnQty.get(i));
				sf.seleU.scrollByCoOrdinates(1);
			}
			//Verify One Time fees section is present for all sites
			if (InputData_Communities.mulPBFNumOfSites == sf.mulSiteOrderReview.oneTimeFeesForEachSection.size())
				reportStatusPass(methodName + " One Time fees section is present for all sites", false, true);
			else
				reportStatusFail(methodName + " One time fees section is not present for all sites", true);
			
			//Validate Total Monthly and One time fee section is present
			verifyFieldDisplayed("Total Monthly fees section", sf.comPBFShopCart.addedProductTotalMonthlyFeesLabel);
			verifyFieldDisplayed("Total One time fees section", sf.comPBFShopCart.addedProductTotalOneTimeFeesLabel);
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Order Review Page with added product", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * 			Validate the Terms and Conditions section should be displayed
	 * 			with 2 CTAs which should open the Business Agreeement PDFs
	 * 
	 *  		Validate user checks in the Terms and Conditions checkbox
	 *  
	 * @throws IOException
	 */
	public void verifyTermsAndCondtionsAndAccept() throws IOException {

		try {
				//Validate Terms and conditions Block and Body
				verifyFieldDisplayed("Terms And Conditions Header", sf.comPBFOrderReview.termAndConditionsSection);
				
				//Click on show details for agent
				if (InputData_Communities.mulPBFUser.equalsIgnoreCase("Agent"))
					sf.seleU.clickElementByJSE(sf.comPBFOrderReview.showDetails);
					
				sf.seleU.wait(2000);
				verifyFieldDisplayed("Terms And Conditions Text", sf.comPBFOrderReview.termAndConditionsText);
				
				//Validate 2 Agreements Link Buttons are present
				verifyFieldDisplayed("Terms And Conditions Open Rogers Business Agreement Button", sf.comPBFOrderReview.rogersBusinessAgreementButton);
				
				verifyFieldDisplayed("Terms And Conditions Productt Agreement for Business Button", sf.comPBFOrderReview.rogersBusinessInternetTermsButton);
				
				sf.seleU.clickElementByJSE(sf.comPBFOrderReview.rogersBusinessAgreementButton);
				sf.seleU.wait(5000);
				reportStatusPass(methodName + " Clicked on Rogers Business Agreement", false, true);
				sf.seleU.switchWindow(2);
				if (sf.seleU.getCurrentUrl().toString().contains(InputData_Communities.commPBFBusinessGeneralTermURL)) {
					reportStatusPass(methodName + " Rogers Business Agreement  Opened Correctly", true, true);
				} else {
					reportStatusFail(methodName + " Business Agreement Failed to Open", true);
				}
				sf.seleU.switchWindow(1);
				sf.seleU.closeRecentlyOpenedWindow();
				
				if (expectedProductsCategory.contains("RDI")) {
					verifyFieldDisplayed("Terms And Conditions Productt Agreement for Dedicated Internet Button", sf.comPBFOrderReview.rogersRDITermsButton);
					
					sf.seleU.clickElementByJSE(sf.comPBFOrderReview.rogersRDITermsButton);
					sf.seleU.wait(5000);
					reportStatusPass(methodName + " Cicked on Rogers Dedicated Internet Terms Button", true, false);
					sf.seleU.switchWindow(2);
					if (sf.seleU.getCurrentUrl().toString().contains(InputData_Communities.commPBFDedicatedInternetTermURL)) {
						
						reportStatusPass(methodName + " Rogers Dedicated Internet Terms Opened Correctly", false, true);
						
					} else {
						reportStatusFail(methodName + " Dedicated Internet Terms Failed to Open", true);
					}
					sf.seleU.switchWindow(1);
					sf.seleU.closeRecentlyOpenedWindow();
				}
				
				if(expectedProductsCategory.contains("TV") || expectedProductsCategory.contains("Business Internet,TV") || expectedProductsCategory.contains("TV,Business Internet")) {
						verifyFieldDisplayed("Terms And Conditions Productt Agreement for TV Button", sf.comPBFOrderReview.rogersBusinessTVTermsButton);
						
						sf.seleU.clickElementByJSE(sf.comPBFOrderReview.rogersBusinessTVTermsButton);
						sf.seleU.wait(5000);
						reportStatusPass(methodName + " Cicked on Rogers Business TV Terms Button", false, false);
						sf.seleU.switchWindow(2);
						if (sf.seleU.getCurrentUrl().toString().contains(InputData_Communities.commPBFBusinessTVTermURL)) {
							
							reportStatusPass(methodName + " Rogers Business TV Terms Opened Correctly", true, true);
							
						} else {
							reportStatusFail(methodName + " Business TV Terms Failed to Open", true);
						}
						sf.seleU.switchWindow(1);
						sf.seleU.closeRecentlyOpenedWindow();
				}
					
				if(expectedProductsCategory.contains("Business Internet")|| expectedProductsCategory.contains("Business Internet,TV") || expectedProductsCategory.contains("TV,Business Internet")) {
					verifyFieldDisplayed("Terms And Conditions Productt Agreement for Business Internet Button", sf.comPBFOrderReview.rogersBusinessInternetTermsButton);
					
					sf.seleU.clickElementByJSE(sf.comPBFOrderReview.rogersBusinessInternetTermsButton);
					sf.seleU.wait(5000);
					reportStatusPass(methodName + " Cicked on Rogers Business Internet Terms Button", false, false);
					sf.seleU.switchWindow(2);
					if (sf.seleU.getCurrentUrl().toString().contains(InputData_Communities.commPBFBusinessInternetTermURL)) {
						
						reportStatusPass(methodName + " Rogers Business Internet Terms Opened Correctly", true, true);
						
					} else {
						reportStatusFail(methodName + " Business Internet Terms Failed to Open", true);
					}
					sf.seleU.switchWindow(1);
					sf.seleU.closeRecentlyOpenedWindow();
					
				}
			
				//Accept Terms and Conditions
				sf.seleU.wait(5000);
				sf.seleU.scrollByCoOrdinates(3);
				
				//Checkbox not there for Agent
				if (!(InputData_Communities.mulPBFUser.equalsIgnoreCase("Agent"))) {
					sf.seleU.clickElementByJSE(sf.comPBFOrderReview.termsAndConditionsCheckbox);
					reportStatusPass(methodName + " Accepted Termes and conditions", true, false);
				}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating terms and condintions on Order Review Page", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * 			Validate user is able to click on the Add contact CTA
	 * 
	 * 			User selects from an existing contact and proceed to land back on Order Review Page
	 * 
	 * @throws IOException
	 */
	public void addSiteContact() throws IOException {

		try {
			//Add Site contact for each site
			for (int i=0; i< InputData_Communities.mulPBFNumOfSites; i++) {
				
				sf.seleU.clickElementByJSE(sf.mulSiteOrderReview.addContactButton.get(0));
				sf.seleU.wait(5000);
				reportStatusPass(methodName + " Moved to Site Contact Page for Site " + i, true, true);
				
				//Extract Contact Name from Site Contact 
				//sf.dataInput.siteContactName =  sf.seleU.getTextFromWebElement(sf.comPBF.siteContactName);
				sf.seleU.clickElementByJSE(sf.comPBF.serviceAddressRadioButtons.get(0));
				sf.seleU.wait(5000);
				sf.seleU.clickElementByJSE(sf.siteConPBF.continueButton);
				sf.seleU.wait(5000);
				reportStatusPass(methodName + " Site Contact added to Order", true, true);
					
			}
			//Scroll down
			sf.seleU.scrollByCoOrdinates(2);
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in adding Site Contact on Order Review Page", e);
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
	 *                     Verify Field has value/text
	 */
	public void verifyFieldHasValue(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field has some value/non-empty
			if (!sf.seleU.getTextFromWebElement(element).isEmpty())
				reportStatusPass(
						methodName + " " + fieldName + " field has value : " + sf.seleU.getTextFromWebElement(element),
						true, true);
			else
				reportStatusFail(methodName + " " + fieldName + " field value is not populated and is empty", true);

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
