package sfdc.pages.qm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

import sfdc.pages.communities.Communities_PBF_CablePlanSelection_Page;

/**
 * @author Anukriti.Chawla, Date:19 Jan 2022
 *
 *         SFDC Persona Based Buy Flow Shopping Cart Page
 */
public class SFDC_PBF_MultiSite_ShoppingCart_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public static int addBusinessProductCount = 0;

	public SFDC_PBF_MultiSite_ShoppingCart_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_PBF_MultiSite_Shopping Cart@:";
	}

	/**
	 *   //Ac00461038//
	 * 		Multisite	
	 * 			
	 * 			Validate Shopping Cart Header, Service Address SubHeading, Remove this site button and Add Business Products Button
	 * 
	 * 			Verify Value of Selected Address- 
	 * 
	 * 
	 * @throws IOException
	 */
	public void validateShoppingCartWithMultipleSites() throws IOException {

		try {
			
			//Validate Shopping Cart Header, Service Address SubHeading, Remove this site button and Add Business Products Button
			verifyFieldDisplayed("Shopping Cart Page Header", sf.comPBFShopCart.shoppingCartHeader);
			
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
				reportStatusPass(methodName + " All expected addresses are present on shopping cart page : "
						+ " , Expected Addresses --> "
						+ AdditionalUtilities.getAsString(expectedAddresses) + " Actual addresses --> "
						+ AdditionalUtilities.getAsString(actualAddresses), true, true);
			} else {
				reportStatusFail(methodName
						+ " All expected addresses are not present on shopping cart page : "
						+ " , Expected Addresses --> " + AdditionalUtilities.getAsString(expectedAddresses)
						+ " Actual Addresses --> " + AdditionalUtilities.getAsString(actualAddresses), true);
			}
			//Verify Remove and Add buttons
			for(int j=0; j<sf.mulSiteShopCart.addedSitesBlocks.size(); j++) {
				verifyFieldDisplayed("Service Address Sub Heading for Site" + j, sf.mulSiteShopCart.serviceAddressSubHeading.get(j));
				verifyFieldDisplayed("Remove This Site Button for Site" + j, sf.comPBFShopCart.removeThisSiteButton.get(j));
				verifyFieldDisplayed("Add Business Products Button for Site" + j, sf.comPBFShopCart.addBusinessProductsButton.get(j));
			}
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Shopping Cart Page", e);
			e.printStackTrace();
		}

	}
	
	
	/** 
	 * 	//Ac00461038//
	 * 
	 * 			Add Business Products for all sites in cart
	 * 
	 * @throws IOException
	 */
	public void addMultipleProductsToCartForMultipleSites() throws IOException {

		try {
			addBusinessProductCount = InputData_Communities.mulPBFNumOfSites;
			//Add Products for multiple sites one by one
			for (int i=1; i<= InputData_Communities.mulPBFNumOfSites ; i++) {
				
				HashMap<String, String> siteWiseData = InputData_Communities.sitesData.get("Site" + i);
				
				InputData_Communities.mulPBFSiteProductName = siteWiseData.get("Product Name");
				InputData_Communities.mulPBFSiteProductTerm = siteWiseData.get("Product Term");
				InputData_Communities.mulPBFSiteProductPlanName = siteWiseData.get("Product Plan Name");
				InputData_Communities.mulPBFSiteProductAddOnName = siteWiseData.get("Product AddOn Name");
				InputData_Communities.mulPBFSiteProductAddOnQty =siteWiseData.get("Product AddOn Qty");
				InputData_Communities.mulPBFSiteProductIncludedFeatures = siteWiseData.get("Product Included Features");
				
				switch(InputData_Communities.mulPBFSiteProductName) {
					 
					case "RDI" :
						addRDIProductToCart(i-1);
						addBusinessProductCount = addBusinessProductCount -1;
						break;
						
					case "BusinessInternet,TV" :
						addBundledBITVProductToCart(i-1);
						addBusinessProductCount = addBusinessProductCount -1;
						break;
						
					case "BusinessInternet" :
						addBusIntProductToCart(i-1);
						break;
						
					case "TV" :
						addTVProductToCart(i-1);
						break;
						
					case "TV,BusinessInternet" :
						addBundledTVBIProductToCart(i-1);
						addBusinessProductCount = addBusinessProductCount -1;
						break;
						
					default :
						reportStatusFail(methodName + " Could not match the product name with existing known products to add product to site",  true);
				}
				
				
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in adding product on Shopping Cart Page", e);
			e.printStackTrace();
		}

	}
	
	public static void addRDIProductToCart(int siteNumber) throws IOException {
		try {
			//Click on Add Business Product buttin
			clickCorrectAddBusinessProduct(siteNumber);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Clicked on Add Business Product Button for Site" + siteNumber, true, true);
			
			SFDC_PBF_SiteSelection_Page selectSite = new SFDC_PBF_SiteSelection_Page();
			SFDC_PBF_CablePlan_Selection_Page planSel = new SFDC_PBF_CablePlan_Selection_Page();
			
			//Choose Dedicated Internet Tile for adding product
			selectSite.moveToSiteSelectionPageForDedInt();

			// Select 5 year term Product
			if(InputData_Communities.mulPBFSiteProductTerm.equalsIgnoreCase("5 year term") ) {
				planSel.selectYearTerm();
			}
			
			// Add to Cart a product and proceed to checkout
			planSel.addToCartRDIProductAndEthernetPlan();

			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in adding RDI product to site", e);
			e.printStackTrace();
		}
	}
	
	public static void addBundledTVBIProductToCart(int siteNumber) throws IOException {
		try {
			//Click on Add Business Product button
			clickCorrectAddBusinessProduct(siteNumber);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Clicked on Add Business Product Button for Site" + siteNumber, true, true);
			
			SFDC_PBF_SiteSelection_Page selectSite = new SFDC_PBF_SiteSelection_Page();
			Communities_PBF_CablePlanSelection_Page planSel = new Communities_PBF_CablePlanSelection_Page();
			SFDC_PBF_TVPlanSelection_Page commPBFTVPlan = new SFDC_PBF_TVPlanSelection_Page();
			SFDC_PBF_TV_AddOns_Selection_Page commPBFTVAddons = new SFDC_PBF_TV_AddOns_Selection_Page();
			SFDC_PBF_Off365AddOn_Selection_Page off365AddOnPBF = new SFDC_PBF_Off365AddOn_Selection_Page();
			
			//Set Product and AddOn Variable with required value
			InputData_Communities.commPBFTVAddOnName = InputData_Communities.mulPBFSiteProductAddOnName.split(",")[0];
			InputData_Communities.commPBFTVAddOnQty = Integer.parseInt(InputData_Communities.mulPBFSiteProductAddOnQty.split(",")[0]);
			InputData_Communities.commPBFOffAddOnName = InputData_Communities.mulPBFSiteProductAddOnName.split(",")[1];
			InputData_Communities.commPBFOffAddOnQty = Integer.parseInt(InputData_Communities.mulPBFSiteProductAddOnQty.split(",")[1]);
			InputData_Communities.commPBFAddTVProductName = InputData_Communities.mulPBFSiteProductPlanName.split(",")[0];
			InputData_Communities.commPBFAddProductName = InputData_Communities.mulPBFSiteProductPlanName.split(",")[1];
			
			//Change later on acc to French Data
			InputData_Communities.commPBFAddProductNameFr = InputData_Communities.commPBFAddProductName;
			
			//Click on Business TV Shop Plans
			selectSite.moveToSiteSelectionPageForBusTV();
			
			//Select TV Product
			commPBFTVPlan.selectTVProduct();
			
			// Addons To be Selected
			commPBFTVAddons.addTVAddon();
			
			planSel.proceedtoCheckout();
			
			//Click on Add Business Product buttin
			clickCorrectAddBusinessProduct(siteNumber);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Clicked on Add Business Product Button for Site" + siteNumber, true, true);
			
			// Click on Shop Plans for Business Internet
			selectSite.moveToSiteSelectionPageForBusInt();
	
			
			// Add to Cart a product and proceed to checkout
			planSel.addToCartInternetProduct();
			
			planSel.clickNextOnBusInt();
						
			//Add to Cart an AddON and proceed to shopping Cart
			off365AddOnPBF.addToCartOff365AddOnProductAndProceed();
	
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in adding bundled TV and Business Internet product to site", e);
			e.printStackTrace();
		}
	}
	
	public static void addBundledBITVProductToCart(int siteNumber) throws IOException {
		try {
			//Click on Add Business Product button
			clickCorrectAddBusinessProduct(siteNumber);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Clicked on Add Business Product Button for Site" + siteNumber, true, true);
			
			SFDC_PBF_SiteSelection_Page selectSite = new SFDC_PBF_SiteSelection_Page();
			Communities_PBF_CablePlanSelection_Page planSel = new Communities_PBF_CablePlanSelection_Page();
			SFDC_PBF_TVPlanSelection_Page commPBFTVPlan = new SFDC_PBF_TVPlanSelection_Page();
			SFDC_PBF_TV_AddOns_Selection_Page commPBFTVAddons = new SFDC_PBF_TV_AddOns_Selection_Page();
			SFDC_PBF_Off365AddOn_Selection_Page off365AddOnPBF = new SFDC_PBF_Off365AddOn_Selection_Page();
			SFDC_PBF_CablePlan_Selection_Page planSelPBF = new SFDC_PBF_CablePlan_Selection_Page();
			
			
			//Set Product and AddOn Variable with required value
			InputData_Communities.commPBFTVAddOnName = InputData_Communities.mulPBFSiteProductAddOnName.split(",")[1];
			InputData_Communities.commPBFTVAddOnQty = Integer.parseInt(InputData_Communities.mulPBFSiteProductAddOnQty.split(",")[1]);
			InputData_Communities.commPBFOffAddOnName = InputData_Communities.mulPBFSiteProductAddOnName.split(",")[0];
			InputData_Communities.commPBFOffAddOnQty = Integer.parseInt(InputData_Communities.mulPBFSiteProductAddOnQty.split(",")[0]);
			InputData_Communities.commPBFAddTVProductName = InputData_Communities.mulPBFSiteProductPlanName.split(",")[1];
			InputData_Communities.commPBFAddProductName = InputData_Communities.mulPBFSiteProductPlanName.split(",")[0];
			
			//Change later on acc to French Data
			InputData_Communities.commPBFAddProductNameFr = InputData_Communities.commPBFAddProductName;
			
			// Click on Shop Plans for Business Internet
			selectSite.moveToSiteSelectionPageForBusInt();
	
			//Choose Term
			// SelectMonthly
			if(InputData_Communities.mulPBFSiteProductTerm.equalsIgnoreCase("Monthly") ) {
				planSelPBF.selectMonthlyTerm();
			}
			
			// Add to Cart a product and proceed to checkout
			planSel.addToCartInternetProduct();
			
			planSel.clickNextOnBusInt();
			
			//Add to Cart an AddON and proceed to shopping Cart
			off365AddOnPBF.addToCartOff365AddOnProductAndProceed();
			
			//Click on Add Business Product buttin
			clickCorrectAddBusinessProduct(siteNumber);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Clicked on Add Business Product Button for Site" + siteNumber, true, true);
			
			//Click on Business TV Shop Plans
			selectSite.moveToSiteSelectionPageForBusTV();
			
			//Select TV Product
			commPBFTVPlan.selectTVProduct();
			// Addons To be Selected
			commPBFTVAddons.addTVAddon();
			
			planSel.proceedtoCheckout();
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in adding bundled TV and Business internet product to site", e);
			e.printStackTrace();
		}
	}
	
	public static void addBusIntProductToCart(int siteNumber) throws IOException {
		try {
			//Click on Add Business Product button
			clickCorrectAddBusinessProduct(siteNumber);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Clicked on Add Business Product Button for Site" + siteNumber, true, true);
			
			SFDC_PBF_SiteSelection_Page selectSite = new SFDC_PBF_SiteSelection_Page();
			Communities_PBF_CablePlanSelection_Page planSel = new Communities_PBF_CablePlanSelection_Page();
			SFDC_PBF_Off365AddOn_Selection_Page off365AddOnPBF = new SFDC_PBF_Off365AddOn_Selection_Page();
			SFDC_PBF_CablePlan_Selection_Page planSelPBF = new SFDC_PBF_CablePlan_Selection_Page();
			
			
			//Set Product and AddOn Variable with required value
			InputData_Communities.commPBFOffAddOnName = InputData_Communities.mulPBFSiteProductAddOnName;
			InputData_Communities.commPBFOffAddOnQty = Integer.parseInt(InputData_Communities.mulPBFSiteProductAddOnQty);
			InputData_Communities.commPBFAddProductName = InputData_Communities.mulPBFSiteProductPlanName;
			//Change later on acc to French Data
			InputData_Communities.commPBFAddProductNameFr = InputData_Communities.commPBFAddProductName;
			
			// Click on Shop Plans for Business Internet
			selectSite.moveToSiteSelectionPageForBusInt();
	
			//Choose Term
			// SelectMonthly
			if(InputData_Communities.mulPBFSiteProductTerm.equalsIgnoreCase("Monthly") ) {
				planSelPBF.selectMonthlyTerm();
			}
			
			// Add to Cart a product and proceed to checkout
			planSel.addToCartInternetProduct();
			
//			planSel.clickNextOnBusInt();
//						
//			//Add to Cart an AddON and proceed to shopping Cart
//			off365AddOnPBF.addToCartOff365AddOnProductAndProceed();
			planSel.proceedtoCheckout();
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in adding business internet product to site", e);
			e.printStackTrace();
		}
	}
	
	public static void addTVProductToCart(int siteNumber) throws IOException {
		try {
			//Click on Add Business Product button
			clickCorrectAddBusinessProduct(siteNumber);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Clicked on Add Business Product Button for Site" + siteNumber, true, true);
			
			SFDC_PBF_SiteSelection_Page selectSite = new SFDC_PBF_SiteSelection_Page();
			Communities_PBF_CablePlanSelection_Page planSel = new Communities_PBF_CablePlanSelection_Page();
			SFDC_PBF_TVPlanSelection_Page commPBFTVPlan = new SFDC_PBF_TVPlanSelection_Page();
			SFDC_PBF_TV_AddOns_Selection_Page commPBFTVAddons = new SFDC_PBF_TV_AddOns_Selection_Page();
			
			//Set Product and AddOn Variable with required value
			InputData_Communities.commPBFTVAddOnName = InputData_Communities.mulPBFSiteProductAddOnName;
			InputData_Communities.commPBFTVAddOnQty = Integer.parseInt(InputData_Communities.mulPBFSiteProductAddOnQty);
			InputData_Communities.commPBFAddTVProductName = InputData_Communities.mulPBFSiteProductPlanName;
			
			
			//Click on Business TV Shop Plans
			selectSite.moveToSiteSelectionPageForBusTV();
			
			//Select TV Product
			commPBFTVPlan.selectTVProduct();
			
			planSel.clickNextOnBusInt();
			
			// Addons To be Selected
			commPBFTVAddons.addTVAddon();
			
			planSel.proceedtoCheckout();
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in adding TV product to site", e);
			e.printStackTrace();
		}
	}
	
	public static void clickCorrectAddBusinessProduct(int siteNumber) throws IOException {
		try {
			//Click on Add Business Product button
				if (addBusinessProductCount == InputData_Communities.mulPBFNumOfSites)
					sf.seleU.clickElementByJSE(sf.comPBFShopCart.addBusinessProductsButton.get(siteNumber));
				
				if(addBusinessProductCount == (InputData_Communities.mulPBFNumOfSites-1))
					sf.seleU.clickElementByJSE(sf.comPBFShopCart.addBusinessProductsButton.get(siteNumber-1));
				
				if(addBusinessProductCount == (InputData_Communities.mulPBFNumOfSites-2))
					sf.seleU.clickElementByJSE(sf.comPBFShopCart.addBusinessProductsButton.get(siteNumber-2));
				
				if(addBusinessProductCount == (InputData_Communities.mulPBFNumOfSites-3))
					sf.seleU.clickElementByJSE(sf.comPBFShopCart.addBusinessProductsButton.get(siteNumber-3));
				
				if(addBusinessProductCount == (InputData_Communities.mulPBFNumOfSites-4))
					sf.seleU.clickElementByJSE(sf.comPBFShopCart.addBusinessProductsButton.get(siteNumber-4));
				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in adding TV product to site", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 	//Ac00461038//	
	 * 	Multisite
	 * 			Validate the following on the Shopping Cart Page:
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
	public void validateShoppingCartPageWithAddedProductsForAllSites() throws IOException {

		try {
			
			//Click on all Show more buttons to expand each block
			for(int k=0; k < InputData_Communities.mulPBFNumOfSites ;k++) {
				try{
					sf.seleU.clickElementByJSE(sf.mulSiteShopCart.showMoreButton.get(0));
				} catch (IndexOutOfBoundsException e) {}
				reportStatusPass(methodName + " Clicked on Show more Buttons for all sites", false, false);
				sf.seleU.wait(2000);
			}
			
			List<String> expectedProducts = new ArrayList<>();
			List<String> expectedProductTerms = new ArrayList<>();
			List<String> expectedAddOns = new ArrayList<>();
			List<String> expectedAddOnQty = new ArrayList<>();
			
			//Add Products for multiple sites one by one
			for (int i=0; i< InputData_Communities.mulPBFNumOfSites ; i++) {
				
				HashMap<String, String> siteWiseData = InputData_Communities.sitesData.get("Site" + (i+1));
				
				InputData_Communities.mulPBFSiteProductTerm = siteWiseData.get("Product Term");
				InputData_Communities.mulPBFSiteProductPlanName = siteWiseData.get("Product Plan Name");
				InputData_Communities.mulPBFSiteProductAddOnName = siteWiseData.get("Product AddOn Name");
				InputData_Communities.mulPBFSiteProductAddOnQty =siteWiseData.get("Product AddOn Qty");
				
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
				
				verifyFieldValue("Added Product Name", sf.mulSiteShopCart.addedProductName.get(i), expectedProducts.get(i));
				verifyFieldValue("Added Product Term", sf.mulSiteShopCart.addedProductTerm.get(i), expectedProductTerms.get(i));
				sf.seleU.scrollByCoOrdinates(1);
			}
			
			sf.seleU.scrollUpByCoOrdinates();
			
			//Verify Product Name
			for(int i=0; i < expectedAddOns.size() ; i++) {
				
				verifyFieldValue("Added Product Name", sf.mulSiteShopCart.addedAddOnsName.get(i), expectedAddOns.get(i));
				verifyFieldValue("Added AddOn Qty", sf.mulSiteShopCart.addedAddOnsQty.get(i), expectedAddOnQty.get(i));
				sf.seleU.scrollByCoOrdinates(1);
			}
			//Verify One Time fees section is present for all sites
			if (InputData_Communities.mulPBFNumOfSites == sf.mulSiteShopCart.oneTimeFeesForEachSection.size())
				reportStatusPass(methodName + " One Time fees section is present for all sites", false, true);
			else
				reportStatusFail(methodName + " One time fees section is not present for all sites", true);
			
			//Validate Total Monthly and One time fee section is present
			verifyFieldDisplayed("Total Monthly fees section", sf.comPBFShopCart.addedProductTotalMonthlyFeesLabel);
			verifyFieldDisplayed("Total One time fees section", sf.comPBFShopCart.addedProductTotalOneTimeFeesLabel);
			

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Shopping Cart Page with added product", e);
			e.printStackTrace();
		}

	}
	/**
	 *  	//Ac00461038//
	 * 
	 * @throws IOException
	 * 
	 *                     Waive installation fees
	 *                     
	 *                     Verify changes in prices after waiving off fees
	 * 
	 */
	public void testWaiveOffInstallationFeesForMulSite() throws IOException {
		try {
			
			//implement for multisite
			sf.seleU.clickElementByJSE(sf.shopCartPBF.waiveOffInstallationButton);
			reportStatusPass(methodName +" Clicked on Waive Off intsallation fees button", false, true);
			sf.seleU.wait(15000);
			
			verifyFieldValue("Unit Price for One time fees after waive off", sf.shopCartPBF.afterWaiveOffInstallationUnitPrice, "0.00");

			verifyFieldValue("Line Total for One time fees after waive off", sf.shopCartPBF.afterWaiveOffInstallationLineTotalPrice, "0.00");
			verifyFieldValue("One Time fees for Product", sf.comPBFShopCart.addedProductOneTimeFeesSubtotal, "0.00");
			
			//Validate one time fees subtotal
			String totalOneTimeFees = sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalOneTimeFeesValue) +"."
					 + sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalOneTimeFeesSubValue);
			if (totalOneTimeFees.equalsIgnoreCase("0.00") || totalOneTimeFees.equalsIgnoreCase("0.0"))
				reportStatusPass(methodName + " Total Ome Time Fees for Product is correct and waived OFF - 0.00", true, false);
			else
				reportStatusFail(methodName + " Total One Time Fees for Product is incorrect, Expected :  0.00, "
						+ " Actual : " + totalOneTimeFees , true);
			sf.seleU.wait(15000);
			SFDC_PBF_CablePlan_Selection_Page.verifyTCV("ONE TIME TCV", sf.planSelPBF.oneTimeTCV, Double.parseDouble("0.00"));
			
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying waive off installation is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * 	 	//Ac00461038//
	 * @throws IOException
	 * 
	 *                      Add Installation fees and verify price refreshes back to normal.
	 * 
	 */
	public void testAddInstallationFeesForMulSite() throws IOException {
		try {
			
			//implement for multisite add installation fees
			sf.seleU.wait(5000);
		
			sf.seleU.clickElementByJSE(sf.shopCartPBF.addInstallationFeeButton);
			reportStatusPass(methodName +" Clicked on Add intsallation fees button", false, true);
			sf.seleU.wait(15000);
			
			verifyFieldNotDisplayed("Cut - Off Unit Price", sf.shopCartPBF.afterWaiveOffInstallationUnitPrice);
			verifyFieldNotDisplayed("Cut - Off Line Total Price", sf.shopCartPBF.afterWaiveOffInstallationLineTotalPrice);
			verifyFieldValue("One Time fees for Product", sf.comPBFShopCart.addedProductOneTimeFeesSubtotal, InputData_Communities.commPBFOneTimeFees);
			
			//Validate one time fees subtotal
			String totalOneTimeFees = sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalOneTimeFeesValue) +"."
					 + sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalOneTimeFeesSubValue);
			if (totalOneTimeFees.contains(InputData_Communities.commPBFOneTimeFees))
				reportStatusPass(methodName + " Total Ome Time Fees for Product is correct " + InputData_Communities.commPBFOneTimeFees, true, false);
			else
				reportStatusFail(methodName + " Total One Time Fees for Product is incorrect, Expected :  " + InputData_Communities.commPBFOneTimeFees
						+ ", Actual : " + totalOneTimeFees , true);
			
			SFDC_PBF_CablePlan_Selection_Page.verifyTCV("ONE TIME TCV", sf.planSelPBF.oneTimeTCV, Double.parseDouble(InputData_Communities.commPBFOneTimeFees));
			
				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying Business internet icon in serviceability column is unscuccesfull", e);
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
}
