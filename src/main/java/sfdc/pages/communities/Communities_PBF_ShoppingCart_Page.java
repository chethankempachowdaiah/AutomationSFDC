package sfdc.pages.communities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
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

import sfdc.pages.qm.SFDC_PBF_CablePlan_Selection_Page;

/**
 * @author Anukriti.Chawla, Date:07/07/2021
 *
 *         Communities Persona Based Buy Flow Page - Shopping Cart
 */
public class Communities_PBF_ShoppingCart_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public Communities_PBF_ShoppingCart_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "Communities_PBF_ShoppingCart@:";
	}

	/**
	 * 			Validate Shopping Cart Header
	 * 
	 * 			Click on Add Business Products
	 * 
	 * @throws IOException
	 */
	public void quickValidateShoppingCartPageAndProceed() throws IOException {

		try {
			//Validate Shopping Cart Header, Service Address SubHeading, Remove this site button and Add Business Products Button
			verifyFieldDisplayed("Shopping Cart Page Header", sf.comPBFShopCart.shoppingCartHeader);

			//Click on Add Business Products
			sf.seleU.clickElementByJSE(sf.comPBFShopCart.addBusinessProductsButton.get(0));
			sf.seleU.wait(4000);
			reportStatusPass(methodName + " Clicked on Add Business Products Button", true, true);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Shopping Cart Page", e);
			e.printStackTrace();
		}

	}
	/**
	 * 			Validate Shopping Cart Header, Service Address SubHeading, Remove this site button and Add Business Products Button
	 * 
	 * 			Verify Value of Selected Address
	 * 
	 * 			Click on Add Business Products
	 * 
	 * @throws IOException
	 */
	public void validateShoppingCartPageAndProceed() throws IOException {

		try {
			sf.seleU.wait(3000);
			//Validate Shopping Cart Header, Service Address SubHeading, Remove this site button and Add Business Products Button
			verifyFieldDisplayed("Shopping Cart Page Header", sf.comPBFShopCart.shoppingCartHeader);
			verifyFieldDisplayed("Service Address Sub Heading", sf.comPBFShopCart.serviceAddressSubHeading);
			verifyFieldDisplayed("Remove This Site Button", sf.comPBFShopCart.removeThisSiteButton.get(0));
			verifyFieldDisplayed("Add Business Products Button", sf.comPBFShopCart.addBusinessProductsButton.get(0));

			//Verify Value of Selected Address
			//verifyFieldValue("Selected Service Address", sf.comPBFShopCart.serviceAddressText, InputData_Communities.commPBFselectedAddress);

			//Click on Add Business Products
			sf.seleU.clickElementByJSE(sf.comPBFShopCart.addBusinessProductsButton.get(0));
			sf.seleU.wait(4000);
			reportStatusPass(methodName + "Clicked on Add Business Products Button", true, true);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Shopping Cart Page", e);
			e.printStackTrace();
		}

	}
	/**
	 * 			Validate Shopping Cart page has message for non serviceability
	 * 			
	 * 
	 * @throws IOException
	 */
	public void validateShoppingCartPageForNonServiceability() throws IOException {

		try {
			//Validate Shopping Cart Header, Service Address SubHeading, Remove this site button and Add Business Products Button
			verifyFieldDisplayed("Non Serviceability Error message", sf.comPBFShopCart.noServiceErrorMessage);
			verifyFieldNotDisplayed("Add Business Products Button", sf.comPBFShopCart.addBusinessProductsButton.get(0));

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Shopping Cart Page", e);
			e.printStackTrace();
		}

	}

	/**
	 * 			Click on Proceed to checkout
	 * 
	 * @throws IOException
	 */
	public static void proceedToCheckout() throws IOException {

		try {

			sf.seleU.wait(4000);
			//Click on Proceed to checkout
			sf.seleU.clickElementByJSE(sf.comPBFShopCart.proceedToCheckoutButton);
			sf.seleU.wait(4000);
			if (sf.seleU.isElementDisplayed(sf.comPBFShopCart.proceedToCheckoutButton))
				sf.seleU.clickOnElement(sf.comPBFShopCart.proceedToCheckoutButton);

			reportStatusPass(methodName + " Clicked on Proceed to Checkout Button", true, true);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in proceeding to checkout", e);
			e.printStackTrace();
		}

	}

	/**
	 * 			Click on Remove Business Products
	 * 
	 * @throws IOException
	 */
	public void removeProduct() throws IOException {

		try {
			//Click on Remove Business Products
			sf.seleU.clickOnElement(sf.comPBFShopCart.removeProductButton);
			sf.seleU.wait(60000);
			reportStatusPass(methodName + " Clicked on Remove Business Products Button", true, true);
			if(sf.seleU.isElementDisplayed(sf.comPBFShopCart.removeProductButton))
				sf.seleU.clickElementByJSE(sf.comPBFShopCart.removeProductButton);
			sf.seleU.wait(60000);
			//Validate Product is removed
			verifyFieldNotDisplayed("Product name", sf.comPBFShopCart.addedProductName);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in removing product on Shopping Cart Page", e);
			e.printStackTrace();
		}

	}

	/**
	 * 			Click on Remove Business Products
	 * 
	 * @throws IOException
	 */
	public void removeProduct_POC() throws IOException {

		try {
			//Click on Remove Business Products
			for (int i = 0; i<=3; i++) {
				if(sf.seleU.isElementDisplayed(sf.comPBFShopCart.removeProductButton)) {
					sf.seleU.clickElementByJSE(sf.comPBFShopCart.removeProductButton);
					sf.seleU.waitForLoading();
					reportStatusPass(methodName + " Clicked on Remove Button", true,true);
					sf.seleU.wait(5000);
				}
			}

			//Validate Product is removed
			verifyFieldNotDisplayed("Product name", sf.comPBFShopCart.addedProductName);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in removing product on Shopping Cart Page", e);
			e.printStackTrace();
		}

	}


	/**
	 * 			Main Line Product should be displayed under
	 * 		    the Products Column alongwith its Unit Price, Quantity and Line Price.
	 * 
	 * 			Validate the Pricing and Installation details on the Shopping cart page:
	 * 
	 * 				1.Mothly and One-time fees subtotal is displayed accurately.
	 * 
	 * 				2.Installation fees is waived if the product is eligible for installation.
	 * 
	 * 				3.Total Monthly and One-time fees panel displays the correct calculation of the product.
	 * 
	 * @throws IOException
	 */
	public void validatePriceDetailsForAddedRDIProduct() throws IOException {

		try {
			// commenting
			sf.seleU.wait(2000);
			if (InputData_Communities.commPBFPromo.equalsIgnoreCase("Yes")) {

				//Validate Unit Price
				verifyFieldValue("Unit Price for Product", sf.comPBFShopCart.addedProductUnitPriceWithPromo, InputData_Communities.commPBFProductPrice);

				//Validate Line Total
				verifyFieldValue("Line Total Price for Product", sf.comPBFShopCart.addedProductLineTotalWithPromo, InputData_Communities.commPBFProductPrice);
			}
			else {  

				//applying the discount for the etheret Fibre
				if (Integer.parseInt(InputData_Communities.discount) > 0) {

					InputData_Communities.commPBFCurrentEthernetPlanCost = String.valueOf(Double.parseDouble(InputData_Communities.commPBFCurrentEthernetPlanCost) - 
							((Double.parseDouble(InputData_Communities.commPBFCurrentEthernetPlanCost) * Double.parseDouble(InputData_Communities.discount)) / 100 ));
					//Validate Unit Price, Unit price  = monthly toltal - ethernet plan cost
					verifyFieldValue_WithFormat("Unit Price for Product with discount", sf.comPBFShopCart.addedSecondProductUnitPrice, String.valueOf((Double.parseDouble(InputData_Communities.commPBFProductPrice) - Double.parseDouble(InputData_Communities.commPBFCurrentEthernetPlanCost))));

					//Validate Line Total
					verifyFieldValue_WithFormat("Line Total Price for Product with discount", sf.comPBFShopCart.addedSecondProductLineTotal,  String.valueOf((Double.parseDouble(InputData_Communities.commPBFProductPrice) - Double.parseDouble(InputData_Communities.commPBFCurrentEthernetPlanCost))));

					//Validate Unit Price for ethernet access
					verifyFieldValue("Unit Price for Ethernet Access ", sf.comPBFShopCart.ethernetAccessSecondUnitPrice, InputData_Communities.commPBFCurrentEthernetPlanCost);

					//Validate Line Total for ethernet access
					verifyFieldValue("Line Total Price for Ethernet Access", sf.comPBFShopCart.ethernetAccessSecondLineTotal, InputData_Communities.commPBFCurrentEthernetPlanCost);

				} else {

					//Validate Unit Price
					verifyFieldValue_WithFormat("Unit Price for Product", sf.comPBFShopCart.addedProductUnitPrice, String.valueOf((Double.parseDouble(InputData_Communities.commPBFProductPrice) - Double.parseDouble(InputData_Communities.commPBFCurrentEthernetPlanCost))));

					//Validate Line Total
					verifyFieldValue_WithFormat("Line Total Price for Product", sf.comPBFShopCart.addedProductLineTotal,  String.valueOf((Integer.parseInt(InputData_Communities.commPBFProductPrice) - Integer.parseInt(InputData_Communities.commPBFCurrentEthernetPlanCost))));

					//Validate Unit Price
					verifyFieldValue("Unit Price for Ethernet Access ", sf.comPBFShopCart.ethernetAccessUnitPrice, InputData_Communities.commPBFCurrentEthernetPlanCost);

					//Validate Line Total
					verifyFieldValue("Line Total Price for Ethernet Access", sf.comPBFShopCart.ethernetAccessLineTotal, InputData_Communities.commPBFCurrentEthernetPlanCost);
				}
			}
			sf.seleU.ScrolltoElementPageCenter(sf.comPBFShopCart.addedProductQty);
			//Validate Quantity
			verifyFieldValue("Quantity for Product", sf.comPBFShopCart.addedProductQty, "1");

			//Validate Installation fees
			verifyFieldValue("Installation Fees for Product", sf.comPBFShopCart.addedProductInstallationLinetotal, InputData_Communities.commPBFInstallationFees);

			//Validate Monthly fees
			verifyFieldDisplayed("Monthly Fees Section", sf.comPBFShopCart.addedProductTotalMonthlyFeesLabel);
			verifyFieldValue_WithFormat("Monthly Fees for Product", sf.comPBFShopCart.addedProductMonthlyFeesSubtotal, InputData_Communities.commPBFProductPrice);
			sf.seleU.ScrolltoElementPageCenter(sf.comPBFShopCart.addedProductTotalOneTimeFeesLabel);
			//Validate One time fees
			verifyFieldDisplayed("One Time Fees Section", sf.comPBFShopCart.addedProductTotalOneTimeFeesLabel);
			verifyFieldValue("One Time fees for Product", sf.comPBFShopCart.addedProductOneTimeFeesSubtotal, InputData_Communities.commPBFOneTimeFees);

			//Validate monthly subtotal
			String totalMonthlyFees = sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalMonthlyFeesValue) +"."
					+ sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalMonthlyFeesSubValue);
			if (totalMonthlyFees.replaceAll(",", "").trim().contains(InputData_Communities.commPBFProductPrice))
				reportStatusPass(methodName + " Total Monthly Fees for Product is correct "  + InputData_Communities.commPBFProductPrice, true, false);
			else
				reportStatusFail(methodName + " Total Monthly Fees for Product is incorrect, Expected :  "  + InputData_Communities.commPBFProductPrice
						+ " Actual : " + totalMonthlyFees , true);

			//Validate one time fees subtotal
			String totalOneTimeFees = sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalOneTimeFeesValue) +"."
					+ sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalOneTimeFeesSubValue);
			if (totalOneTimeFees.contains(InputData_Communities.commPBFOneTimeFees))
				reportStatusPass(methodName + " Total Ome Time Fees for Product is correct "  + InputData_Communities.commPBFOneTimeFees, true, false);
			else
				reportStatusFail(methodName + " Total One Time Fees for Product is incorrect, Expected :  "  + InputData_Communities.commPBFOneTimeFees
						+ " Actual : " + totalOneTimeFees , true);


		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Price Details of added product on Shopping Cart Page", e);
			e.printStackTrace();
		}

	}

	/**
	 * 			Main Line Product should be displayed under
	 * 		    the Products Column alongwith its Unit Price, Quantity and Line Price.
	 * 
	 * 			Validate the Pricing and Installation details on the Shopping cart page:
	 * 
	 * 				1.Mothly and One-time fees subtotal is displayed accurately.
	 * 
	 * 				2.Installation fees is waived if the product is eligible for installation.
	 * 
	 * 				3.Total Monthly and One-time fees panel displays the correct calculation of the product.
	 * 
	 * @throws IOException
	 */
	public void validatePriceDetailsForAddedProduct() throws IOException {

		try {

			//			if (InputData_Communities.commPBFPromo.equalsIgnoreCase("Yes")) {
			//
			//				//Validate Unit Price
			//				verifyFieldValue("Unit Price for Product", sf.comPBFShopCart.addedProductUnitPriceWithPromo, InputData_Communities.commPBFProductPrice);
			//
			//				//Validate Line Total
			//				verifyFieldValue("Line Total Price for Product", sf.comPBFShopCart.addedProductLineTotalWithPromo, InputData_Communities.commPBFProductPrice);
			//			}
			//			else {
			//Validate Unit Price
			verifyFieldValue("Unit Price for Product", sf.comPBFShopCart.addedProductUnitPrice, InputData_Communities.commPBFProductPrice);

			//Validate Line Total
			verifyFieldValue("Line Total Price for Product", sf.comPBFShopCart.addedProductLineTotal, InputData_Communities.commPBFProductPrice);
			//			}
			sf.seleU.ScrolltoElementPageCenter(sf.comPBFShopCart.addedProductLineTotal);

			//Validate Quantity
			verifyFieldValue("Quantity for Product", sf.comPBFShopCart.addedProductQty, "1");

			//Validate Installation fees
			if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
				verifyFieldValue("Installation Fees for Product", sf.comPBFShopCart.addedProductInstallationLinetotal, InputData_Communities.commPBFInstallationFees);
			} else {
				verifyFieldValue("Installation Fees for Product", sf.comPBFShopCart.addedProductInstallationLinetotal, InputData_Communities.commPBFInstallationFeesFr);
			}

			//Validate Monthly fees
			verifyFieldDisplayed("Monthly Fees Section", sf.comPBFShopCart.addedProductTotalMonthlyFeesLabel);
			if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
				verifyFieldValue("Monthly Fees for Product", sf.comPBFShopCart.addedProductMonthlyFeesSubtotal, String.valueOf(AdditionalUtilities.roundOffDouble2DecimalPlaces(Double.parseDouble(InputData_Communities.commPBFProductPrice) + Double.parseDouble(InputData_Communities.commPBFOffAddOnPrice) * InputData_Communities.commPBFOffAddOnQty)));
			} else {
				verifyFieldValue("Monthly Fees for Product", sf.comPBFShopCart.addedProductMonthlyFeesSubtotal, String.valueOf(AdditionalUtilities.roundOffDouble2DecimalPlaces(Double.parseDouble(InputData_Communities.commPBFProductPrice) + Double.parseDouble(InputData_Communities.commPBFOffAddOnPrice) * InputData_Communities.commPBFOffAddOnQty)).replace(".", ","));
			}

			sf.seleU.ScrolltoElementPageCenter(sf.comPBFShopCart.addedProductTotalOneTimeFeesLabel);
			//Validate One time fees
			verifyFieldDisplayed("One Time Fees Section", sf.comPBFShopCart.addedProductTotalOneTimeFeesLabel);
			if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
				verifyFieldValue("One Time fees for Product", sf.comPBFShopCart.addedProductOneTimeFeesSubtotal, InputData_Communities.commPBFOneTimeFees);
			} else {
				verifyFieldValue("One Time fees for Product", sf.comPBFShopCart.addedProductOneTimeFeesSubtotal, InputData_Communities.commPBFOneTimeFeesFr);
			}

			//Validate monthly subtotal
			String totalMonthlyFees = sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalMonthlyFeesValue) +"."
					+ sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalMonthlyFeesSubValue);
			if (totalMonthlyFees.equalsIgnoreCase(String.valueOf(AdditionalUtilities.roundOffDouble2DecimalPlaces(Double.parseDouble(InputData_Communities.commPBFProductPrice) + Double.parseDouble(InputData_Communities.commPBFOffAddOnPrice) * InputData_Communities.commPBFOffAddOnQty))))
				reportStatusPass(methodName + " Total Monthly Fees for Product is correct "  + totalMonthlyFees, true, false);
			else
				reportStatusFail(methodName + " Total Monthly Fees for Product is incorrect, Expected :  "  + String.valueOf(AdditionalUtilities.roundOffDouble2DecimalPlaces(Double.parseDouble(InputData_Communities.commPBFProductPrice) + Double.parseDouble(InputData_Communities.commPBFOffAddOnPrice) * InputData_Communities.commPBFOffAddOnQty))
				+ " Actual : " + totalMonthlyFees , true);

			//Validate one time fees subtotal
			String totalOneTimeFees = sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalOneTimeFeesValue) +"."
					+ sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalOneTimeFeesSubValue);
			if (totalOneTimeFees.equalsIgnoreCase(InputData_Communities.commPBFOneTimeFees))
				reportStatusPass(methodName + " Total Ome Time Fees for Product is correct "  + InputData_Communities.commPBFOneTimeFees, true, false);
			else
				reportStatusFail(methodName + " Total One Time Fees for Product is incorrect, Expected :  "  + InputData_Communities.commPBFOneTimeFees
						+ " Actual : " + totalOneTimeFees , true);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Price Details of added product on Shopping Cart Page", e);
			e.printStackTrace();
		}

	}

	/**
	 * 			Validate the following on the Shopping Cart Page:
	 * 
	 * 			1.Site Panel should display the site addresse selected during the PBF Flow.
	 * 
	 * 			2.Main Line Product should be displayed under the Products Column
	 * 
	 * 			
	 * @throws IOException
	 */
	public void quickValidateShoppingCartPageWithAddedProduct() throws IOException {

		try {
			verifyFieldDisplayed("Service Address Sub Heading", sf.comPBFShopCart.serviceAddressSubHeading);
			verifyFieldDisplayed("Remove This Site Button", sf.comPBFShopCart.removeThisSiteButton.get(0));

			//Validate added product name
			if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
				verifyFieldValue("Added Product Name", sf.comPBFShopCart.addedProductName, InputData_Communities.commPBFAddProductName);
			} else {
				verifyFieldValue("Added Product Name", sf.comPBFShopCart.addedProductName, InputData_Communities.commPBFAddProductNameFr);	
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Shopping Cart Page with added product", e);
			e.printStackTrace();
		}

	}


	/**
	 * 			Validate the following on the Shopping Cart Page:
	 * 
	 * 			1.Site Panel should display the site addresse selected during the PBF Flow.
	 * 
	 * 			2.Main Line Product should be displayed under the Products Column
	 * 
	 * 			3.Validate the Main Line Product displays the greyed text with the Term associated with the Product
	 * 			 and the features that comes along with the Cable Internet Product
	 * 
	 * 			4.Hide details link is displayed which when clicked should hide the features and enable Show more details 
	 * 
	 * @throws IOException
	 */
	public void validateShoppingCartPageWithAddedInternetProduct() throws IOException {

		try {
			verifyFieldDisplayed("Shopping Cart Page Header", sf.comPBFShopCart.shoppingCartHeader);
			verifyFieldDisplayed("Service Address Sub Heading", sf.comPBFShopCart.serviceAddressSubHeading);
			verifyFieldDisplayed("Remove This Site Button", sf.comPBFShopCart.removeThisSiteButton.get(0));

			//Verify Value of Selected Address
			verifyFieldValue("Selected Service Address", sf.comPBFShopCart.serviceAddressText, InputData_Communities.commPBFselectedAddress);

			//Validate added product name
			if (InputData_Communities.commPBFBundledFirstProd.equals("TV")) {
				verifyFieldValue("Added Product Name", sf.comPBFShopCart.addedProductSecondName, InputData_Communities.commPBFAddProductName);
			} else {
				if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
					verifyFieldValue("Added Product Name", sf.comPBFShopCart.addedProductName, InputData_Communities.commPBFAddProductName);
				} else {
					verifyFieldValue("Added Product Name", sf.comPBFShopCart.addedProductName, InputData_Communities.commPBFAddProductNameFr);
				}
			}

			//Validate Term
			verifyFieldDisplayed("Added Product Term", sf.comPBFShopCart.addedProductTerm);

			if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
				//Validate Features
				verifyFieldValue("Added Product Features", sf.comPBFShopCart.addedProductIncludedFeatures, InputData_Communities.commPBFIncludedFeatures);
			} else {
				verifyFieldValue("Added Product Features", sf.comPBFShopCart.addedProductIncludedFeatures, InputData_Communities.commPBFIncludedFeaturesFr);
			}

//			int hideEleSize = sf.comPBFShopCart.hideDetailsLink.size();
//			//Hide Details
//			for (int i=0; i<hideEleSize; i++) {
//				//sf.seleU.clickElementByJSE(sf.comPBFShopCart.hideDetailsLink.get(0));
//				sf.seleU.clickElementByJSE(sf.comPBFShopCart.hideDetailsLink.get(i));
//				sf.seleU.wait(3000);
//			}
//			reportStatusPass(methodName + " Clicked on Hide Details", true, false);
//			verifyFieldNotDisplayed("Included Features", sf.comPBFShopCart.addedProductIncludedFeatures);
//
//			//Show More Details
//			for (int i=0; i<hideEleSize;i++) {
//				//sf.seleU.clickElementByJSE(sf.comPBFShopCart.showMoreDetailsLink.get(0));
//				sf.seleU.clickElementByJSE(sf.comPBFShopCart.showMoreDetailsLink.get(i));
//			}
//			reportStatusPass(methodName + " Clicked on Show More Details", true, false);
//			verifyFieldDisplayed("Included Features", sf.comPBFShopCart.addedProductIncludedFeatures);


		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Shopping Cart Page with added product", e);
			e.printStackTrace();
		}

	}

	/**
	 * 			Validate the following on the Shopping Cart Page:
	 * 
	 * 			1.Site Panel should display the site addresse selected during the PBF Flow.
	 * 
	 * 			2.Main Line Product should be displayed under the Products Column
	 * 
	 * 			3.Validate the Main Line Product displays the greyed text with the Term associated with the Product
	 * 			 and the features that comes along with the Cable Internet Product
	 * 
	 * @throws IOException
	 */
	public void validateShoppingCartPageWithAddedRDIProduct() throws IOException {

		try {
			verifyFieldDisplayed("Shopping Cart Page Header", sf.comPBFShopCart.shoppingCartHeader);
			verifyFieldDisplayed("Service Address Sub Heading", sf.comPBFShopCart.serviceAddressSubHeading);
			verifyFieldDisplayed("Remove This Site Button", sf.comPBFShopCart.removeThisSiteButton.get(0));

			//Verify Value of Selected Address
			//verifyFieldValue("Selected Service Address", sf.comPBFShopCart.serviceAddressText, InputData_Communities.commPBFselectedAddress);

			//Validate added product name
			verifyFieldValue("Added Product Name", sf.comPBFShopCart.addedProductName, InputData_Communities.commPBFAddProductName);

			//Validate Term
			verifyFieldDisplayed("Added Product Term", sf.comPBFShopCart.addedProductTerm);


		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Shopping Cart Page with added product", e);
			e.printStackTrace();
		}

	}

	/**
	 * 			Validate the following on the Shopping Cart Page:
	 * 
	 * 			1.Site Panel should display the site addresse selected during the PBF Flow.
	 * 
	 * 			2.Main Line Product should be displayed under the Products Column
	 * 
	 * 			3.Validate the Main Line Product displays the greyed text with the Term associated with the Product
	 * 			 and the features that comes along with the Cable Internet Product
	 * 
	 * @throws IOException
	 */
	public void validateShoppingCartPageWithAddedOff365Product() throws IOException {

		try {
			verifyFieldValue("Office365 AddOn Product NAme", sf.shopCartPBF.off365AddOnProductName, InputData_Communities.commPBFOffAddOnName);
			verifyFieldValue("Office365 AddOn Product Qty", sf.shopCartPBF.off365AddOnProductQty, String.valueOf(InputData_Communities.commPBFOffAddOnQty));
			verifyFieldValue("Office365 AddOn Product Price", sf.shopCartPBF.off365AddOnProductPrice, InputData_Communities.commPBFOffAddOnPrice);
			String offAddOnLineTotal = sf.seleU.getTextFromWebElement(sf.shopCartPBF.off365AddOnProductLineTotal);
			offAddOnLineTotal = offAddOnLineTotal.replaceAll("/mo", "").replaceAll("\\$", "").replaceAll(",","").trim();
			String expectedOffAddOnLineTotal = String.valueOf(
					Double.parseDouble(InputData_Communities.commPBFOffAddOnPrice) * InputData_Communities.commPBFOffAddOnQty);
			if (offAddOnLineTotal.contains(expectedOffAddOnLineTotal))
				reportStatusPass(methodName + " Office365 AddOn Line Total is correct "  + expectedOffAddOnLineTotal, true, false);
			else
				reportStatusFail(methodName + " Office365 AddOn Line Total is incorrect, Expected :  "  + expectedOffAddOnLineTotal
						+ " Actual : " + offAddOnLineTotal , true);

			//				SFDC_PBF_CablePlan_Selection_Page.verifyTCV("Office365 AddOn Line Total", sf.shopCartPBF.off365AddOnProductLineTotal, Double.parseDouble(InputData_Communities.commPBFOffAddOnPrice) * InputData_Communities.commPBFOffAddOnQty);
			//				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Shopping Cart Page with added product", e);
			e.printStackTrace();
		}

	}
	/**
	 * 			
	 * @throws IOException
	 */
	public void validateShoppingCartPageWithAddedOff365Qty() throws IOException {

		try {
			verifyFieldValue("Office365 AddOn Product NAme", sf.shopCartPBF.off365AddOnProductName, InputData_Communities.commPBFOffAddOnName);
			verifyFieldValue("Office365 AddOn Product Qty", sf.shopCartPBF.off365AddOnProductQty, String.valueOf(InputData_Communities.commPBFOffAddOnQty));
							
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Shopping Cart Page with added product", e);
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
	 *                     Verify Field value matches the expected result
	 */
	public static void verifyFieldValue_WithFormat(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).replaceAll("/mo", "").replaceAll("\\$", "").replaceAll(",", "").trim().contains(expectedText)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail(methodName + " Actual Value for " + fieldName + " is " + element.getText().replaceAll("/mo", "").replaceAll("\\$", "")
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

	public void validateShoppingCartPageWithAddedTVProduct() throws IOException {

		try {
			verifyFieldDisplayed(" Shopping Cart Page Header", sf.comPBFShopCart.shoppingCartHeader);
			verifyFieldDisplayed(" Service Address Sub Heading", sf.comPBFShopCart.serviceAddressSubHeading);
			verifyFieldDisplayed("Remove This Site Button", sf.comPBFShopCart.removeThisSiteButton.get(0));

			//Verify Value of Selected Address
			verifyFieldValue("Selected Service Address", sf.comPBFShopCart.serviceAddressText, InputData_Communities.commPBFselectedAddress);

			//Validate added product name
			verifyFieldValue("Added Product Name", sf.comPBFShopCart.productNameTV, InputData_Communities.commPBFAddTVProductName);
			sf.seleU.ScrolltoElementPageCenter( sf.comPBFShopCart.addonsNameTV);
			//Validate Term
			verifyFieldDisplayed("Added TV Addon Name", sf.comPBFShopCart.addonsNameTV);

			verifyFieldDisplayed("Hide Details for TV Addon Button", sf.comPBFShopCart.hideDetailsTVAddons);

			verifyFieldDisplayed("Remove Product- TV Button", sf.comPBFShopCart.removeTV);
			verifyFieldDisplayed("Edit Addons Button", sf.comPBFShopCart.addonsEditTV);
			verifyFieldDisplayed("Monthly Fees Section for TV", sf.comPBFShopCart.monthlyFeesSectionLabelTV);
			verifyFieldDisplayed("One Time fees Section for TV", sf.comPBFShopCart.oneTimeInstallationFeesSectionTV);


		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Shopping Cart Page with added product", e);
			e.printStackTrace();
		}

	}

	public void validatePriceDetailsForBusIntOff365TVProduct() throws IOException {

		try {
			//////***********Validate Business Internet Product Prices **********////////

			//Validate Unit Price
			sf.seleU.ScrolltoElementPageCenter(sf.comPBFShopCart.addedProductUnitPrice);
			verifyFieldValue("Unit Price for Product", sf.comPBFShopCart.addedProductUnitPrice, InputData_Communities.commPBFProductPrice);
			//Validate Line Total
			verifyFieldValue("Line Total Price for Product", sf.comPBFShopCart.addedProductLineTotal, InputData_Communities.commPBFProductPrice);

			//Validate Quantity
			verifyFieldValue("Quantity for Product", sf.comPBFShopCart.addedProductQty, "1");

			//Validate Installation fees
			verifyFieldValue("Installation Fees for Product", sf.comPBFShopCart.addedProductInstallationLinetotal, InputData_Communities.commPBFInstallationFees);

			//Validate One time fees
			verifyFieldDisplayed("One Time Fees Section", sf.comPBFShopCart.addedProductTotalOneTimeFeesLabel);
			verifyFieldValue("One Time fees for Product", sf.comPBFShopCart.addedProductOneTimeFeesSubtotal, InputData_Communities.commPBFOneTimeFees);

			/////************ Validate Office 365 AddOn Product Prices**********////////
			if (!InputData_Communities.commPBFOffAddOnName.equals("NA")) {
				validateShoppingCartPageWithAddedOff365Product();
			}

			/////*************Validate TV Product Prices *********************///////

			//Validate Unit Price
			sf.seleU.ScrolltoElementPageCenter(sf.comPBFShopCart.unitPriceTV);
			verifyFieldValue("Unit Price for Product", sf.comPBFShopCart.unitPriceTV, InputData_Communities.commPBFTVProductPrice);

			//Validate Line Total
			verifyFieldValue("Line Total Price for Product", sf.comPBFShopCart.linePriceTV, InputData_Communities.commPBFTVProductPrice);

			//Validate Quantity
			verifyFieldValue("Quantity for Product", sf.comPBFShopCart.addedProductQuantityTV, "1");

			//Validate One time Installation fees
			verifyFieldDisplayed("One Time Fees Section", sf.comPBFShopCart.oneTimeInstallationFeesSectionTV);
			verifyFieldValue("One Time fees for Product", sf.comPBFShopCart.oneTimeInstallationFeesTV, InputData_Communities.commPBFTVOneTimeFees);

			/////*************Validate TV AddOn Price***********************/////////
			//Validate price details for TV Addons
			validateTVAddOnsPrice();

			/////////******************Validate common Prices *****************/////////

			//Validate Monthly fees
			verifyFieldDisplayed("Monthly Fees Section", sf.comPBFShopCart.monthlyFeesSectionLabelTV);
			verifyFieldValue("Monthly Fees for Product", sf.comPBFShopCart.addedProductMonthlyFeesSubtotal, String.valueOf(AdditionalUtilities.roundOffDouble2DecimalPlaces(
					Double.parseDouble(InputData_Communities.commPBFProductPrice) + 
					(Double.parseDouble(InputData_Communities.commPBFOffAddOnPrice) * InputData_Communities.commPBFOffAddOnQty) +
					Double.parseDouble(InputData_Communities.commPBFTVProductPrice) + 
					(Double.parseDouble(InputData_Communities.commPBFTVAddOnPrice) * InputData_Communities.commPBFTVAddOnQty))));	

			//Validate monthly subtotal
			String totalMonthlyFees = sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalMonthlyFeesValue);
			totalMonthlyFees = totalMonthlyFees.replaceAll("/mo", "").replaceAll("\\$", "").trim();
			String expectedMonthlyFees = String.valueOf(
					Double.parseDouble(InputData_Communities.commPBFProductPrice) + 
					(Double.parseDouble(InputData_Communities.commPBFOffAddOnPrice) * InputData_Communities.commPBFOffAddOnQty) +
					Double.parseDouble(InputData_Communities.commPBFTVProductPrice) + 
					(Double.parseDouble(InputData_Communities.commPBFTVAddOnPrice) * InputData_Communities.commPBFTVAddOnQty));
			if (expectedMonthlyFees.contains(totalMonthlyFees))
				reportStatusPass(methodName + " Total Monthly Fees for Product is correct "  + expectedMonthlyFees, true, false);
			else
				reportStatusFail(methodName + " Total Monthly Fees for Product is incorrect, Expected :  "  + expectedMonthlyFees
						+ " Actual : " + totalMonthlyFees , true);

			//Validate one time fees subtotal
			String totalOneTimeFees = sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalOneTimeFeesValue);
			totalOneTimeFees = totalOneTimeFees.replaceAll("\\$", "").replaceAll("/mo", "").trim();	
			String expectedTotalOneTimeFees = String.valueOf(Double.parseDouble(InputData_Communities.commPBFTVOneTimeFees) + Double.parseDouble(InputData_Communities.commPBFOneTimeFees));
			if (expectedTotalOneTimeFees.contains(totalOneTimeFees))
				reportStatusPass(methodName + " Total One Time Fees for Product is correct "  + expectedTotalOneTimeFees, true, false);
			else
				reportStatusFail(methodName + " Total One Time Fees for Product is incorrect, Expected :  "  + expectedTotalOneTimeFees
						+ " Actual : " + totalOneTimeFees , true);


		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Price Details of added product on Shopping Cart Page", e);
			e.printStackTrace();
		}

	}
	public void validatePriceDetailsForTVBusIntOff365Product() throws IOException {

		try {
			//////***********Validate Business Internet Product Prices **********////////

			//Validate Unit Price
			verifyFieldValue("Unit Price for Business Internet Product", sf.comPBFShopCart.addedSecondProductUnitPrice, InputData_Communities.commPBFProductPrice);
			//Validate Line Total
			verifyFieldValue("Line Total Price for Bus Int Product", sf.comPBFShopCart.addedSecondProductLineTotal, InputData_Communities.commPBFProductPrice);

			//Validate Quantity
			verifyFieldValue("Quantity for Bus Internet Product", sf.comPBFShopCart.addedSecondProductQty, "1");

			//Validate Installation fees
			verifyFieldValue("Installation Fees for Product", sf.comPBFShopCart.addedProductInstallationLinetotal, InputData_Communities.commPBFInstallationFees);

			//Validate One time fees
			verifyFieldDisplayed("One Time Fees Section", sf.comPBFShopCart.addedProductTotalOneTimeFeesLabel);
			verifyFieldValue("One Time fees for Product", sf.comPBFShopCart.addedProductOneTimeFeesSubtotal, InputData_Communities.commPBFOneTimeFees);

			/////************ Validate Office 365 AddOn Product Prices**********////////
			if (!InputData_Communities.commPBFOffAddOnName.equals("NA")) {
				validateShoppingCartPageWithAddedOff365Product();
			}

			/////*************Validate TV Product Prices *********************///////

			//Validate Unit Price
			verifyFieldValue("Unit Price for Product", sf.comPBFShopCart.unitPriceTV, InputData_Communities.commPBFTVProductPrice);

			//Validate Line Total
			verifyFieldValue("Line Total Price for Product", sf.comPBFShopCart.linePriceTV, InputData_Communities.commPBFTVProductPrice);

			//Validate Quantity
			verifyFieldValue("Quantity for Product", sf.comPBFShopCart.addedProductQuantityTV, "1");

			//Validate One time Installation fees
			verifyFieldDisplayed("One Time Fees Section", sf.comPBFShopCart.oneTimeInstallationFeesSectionTV);
			verifyFieldValue("One Time fees for Product", sf.comPBFShopCart.oneTimeInstallationFeesTV, InputData_Communities.commPBFTVOneTimeFees);

			/////*************Validate TV AddOn Price***********************/////////
			//Validate price details for TV Addons
			validateTVAddOnsPrice();

			/////////******************Validate common Prices *****************/////////

			//Validate Monthly fees
			verifyFieldDisplayed("Monthly Fees Section", sf.comPBFShopCart.monthlyFeesSectionLabelTV);

			String monthlyFees = sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductMonthlyFeesSubtotal);
			monthlyFees = monthlyFees.replaceAll("/mo", "").replaceAll("\\$", "").replaceAll(",", "").trim();
			String expectedMonthlyFees = String.valueOf(
					AdditionalUtilities.roundOffDouble2DecimalPlaces(
							Double.parseDouble(InputData_Communities.commPBFProductPrice) + 
							(Double.parseDouble(InputData_Communities.commPBFOffAddOnPrice) * InputData_Communities.commPBFOffAddOnQty) +
							Double.parseDouble(InputData_Communities.commPBFTVProductPrice) + 
							(Double.parseDouble(InputData_Communities.commPBFTVAddOnPrice) * InputData_Communities.commPBFTVAddOnQty)));

			if (expectedMonthlyFees.contains(monthlyFees) || monthlyFees.contains(expectedMonthlyFees)) {
				reportStatusPass(methodName + " Monthly Fees for Product is correct "  + expectedMonthlyFees, true, false);
			}

			else
				reportStatusFail(methodName + " Monthly Fees for Product is incorrect, Expected :  "  + expectedMonthlyFees
						+ " Actual : " + monthlyFees , true);

			//			SFDC_PBF_CablePlan_Selection_Page.verifyTCV("Monthly Fees for Product", sf.comPBFShopCart.addedProductMonthlyFeesSubtotal, AdditionalUtilities.roundOffDouble2DecimalPlaces(
			//					Double.parseDouble(InputData_Communities.commPBFProductPrice) + 
			//					(Double.parseDouble(InputData_Communities.commPBFOffAddOnPrice) * InputData_Communities.commPBFOffAddOnQty) +
			//					Double.parseDouble(InputData_Communities.commPBFTVProductPrice) + 
			//					(Double.parseDouble(InputData_Communities.commPBFTVAddOnPrice) * InputData_Communities.commPBFTVAddOnQty)));	
			//			
			//Validate monthly subtotal
			String totalMonthlyFees = sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalMonthlyFeesValue);
			totalMonthlyFees = totalMonthlyFees.replaceAll("/mo", "").replaceAll("\\$", "").trim();
			expectedMonthlyFees = String.valueOf(
					Double.parseDouble(InputData_Communities.commPBFProductPrice) + 
					(Double.parseDouble(InputData_Communities.commPBFOffAddOnPrice) * InputData_Communities.commPBFOffAddOnQty) +
					Double.parseDouble(InputData_Communities.commPBFTVProductPrice) + 
					(Double.parseDouble(InputData_Communities.commPBFTVAddOnPrice) * InputData_Communities.commPBFTVAddOnQty));
			if (expectedMonthlyFees.contains(totalMonthlyFees))
				reportStatusPass(methodName + " Total Monthly Fees for Product is correct "  + expectedMonthlyFees, true, false);
			else
				reportStatusFail(methodName + " Total Monthly Fees for Product is incorrect, Expected :  "  + expectedMonthlyFees
						+ " Actual : " + totalMonthlyFees , true);

			//Validate one time fees subtotal
			String totalOneTimeFees = sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalOneTimeFeesValue);
			totalOneTimeFees = totalOneTimeFees.replaceAll("\\$", "").replaceAll("/mo", "").trim();	
			String expectedTotalOneTimeFees = String.valueOf(Double.parseDouble(InputData_Communities.commPBFTVOneTimeFees) + Double.parseDouble(InputData_Communities.commPBFOneTimeFees));
			if (expectedTotalOneTimeFees.contains(totalOneTimeFees))
				reportStatusPass(methodName + " Total One Time Fees for Product is correct "  + expectedTotalOneTimeFees, true, false);
			else
				reportStatusFail(methodName + " Total One Time Fees for Product is incorrect, Expected :  "  + expectedTotalOneTimeFees
						+ " Actual : " + totalOneTimeFees , true);


		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Price Details of added product on Shopping Cart Page", e);
			e.printStackTrace();
		}

	}
	public void validatePriceDetailsForTVProduct() throws IOException {

		try {
			//Validate Unit Price
			verifyFieldValue("Unit Price for Product", sf.comPBFShopCart.unitPriceTV, InputData_Communities.commPBFTVProductPrice);

			//Validate Quantity
			verifyFieldValue("Quantity for Product", sf.comPBFShopCart.addedProductQuantityTV, "1");

			//Validate Line Total
			verifyFieldValue("Line Total Price for Product", sf.comPBFShopCart.linePriceTV, InputData_Communities.commPBFTVProductPrice);

			//Validate Monthly fees
			verifyFieldDisplayed("Monthly Fees Section", sf.comPBFShopCart.monthlyFeesSectionLabelTV);
			//verifyFieldValue("Monthly Fees for Product", sf.comPBFShopCart.addedProductMonthlyFeesSubtotal, InputData_Communities.commPBFTVProductPrice);

			//Validate Unit Price for Addons
			verifyFieldValue("Unit Price for Addon ", sf.comPBFShopCart.unitPriceAddonsTV, InputData_Communities.commPBFTVAddOnPrice);

			//Validate Quantity for Addons
			verifyFieldValue("Quantity for Addon", sf.comPBFShopCart.quantityAddonsTV, String.valueOf(InputData_Communities.commPBFTVAddOnQty));

			//Validate Line Price for Addons
			String actualTotalLinePriceAddons = sf.seleU.getTextFromWebElement(sf.comPBFShopCart.linePriceAddonsTV);
			actualTotalLinePriceAddons = actualTotalLinePriceAddons.replaceAll("/mo", "").replaceAll("\\$", "").trim();
			String expectedTotalLinePriceAddons =  String.valueOf(Float.parseFloat(InputData_Communities.commPBFTVAddOnPrice) * InputData_Communities.commPBFTVAddOnQty);
			if (actualTotalLinePriceAddons.equalsIgnoreCase(expectedTotalLinePriceAddons))
				reportStatusPass(methodName + " Total Line Price for Addons is correct "  + actualTotalLinePriceAddons, true, true);
			else
				reportStatusFail(methodName + " Total Line Price for Addons is incorrect, Expected :  "  + expectedTotalLinePriceAddons
						+ " Actual : " + actualTotalLinePriceAddons , true);

			//Validate monthly subtotal
			String actualtotalMonthlyFees = sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductMonthlyFeesSubtotal);
			actualtotalMonthlyFees = actualtotalMonthlyFees.replaceAll("/mo", "").replaceAll("\\$", "").trim();
			String expectedtotalMonthlyFees = String.valueOf(Float.parseFloat(InputData_Communities.commPBFTVProductPrice) + Float.parseFloat(expectedTotalLinePriceAddons));
			//expectedtotalMonthlyFees = String.format("%.2f", Float.parseFloat(expectedtotalMonthlyFees));
			if (actualtotalMonthlyFees.equalsIgnoreCase(expectedtotalMonthlyFees))
				reportStatusPass(methodName + " Total Monthly Fees for Product is correct "  + actualtotalMonthlyFees, true, false);
			else
				reportStatusFail(methodName + " Total Monthly Fees for Product is incorrect, Expected :  "  + expectedtotalMonthlyFees
						+ " Actual : " + actualtotalMonthlyFees , true);

			//Validate One time Installation fees
			verifyFieldDisplayed("One Time Fees Section", sf.comPBFShopCart.oneTimeInstallationFeesSectionTV);
			verifyFieldValue("One Time fees for Product", sf.comPBFShopCart.oneTimeInstallationFeesTV, InputData_Communities.commPBFTVOneTimeFees);

			//Validate one time fees subtotal
			String totalOneTimeFees = sf.seleU.getTextFromWebElement(sf.comPBFShopCart.oneTimeInstallationFeesTV);
			totalOneTimeFees = totalOneTimeFees.replaceAll("\\$", "").replaceAll("/mo", "").trim();	
			if (totalOneTimeFees.equalsIgnoreCase(InputData_Communities.commPBFTVOneTimeFees))
				reportStatusPass(methodName + " Total One Time Fees for Product is correct "  + InputData_Communities.commPBFTVOneTimeFees, true, false);
			else
				reportStatusFail(methodName + " Total One Time Fees for Product is incorrect, Expected :  "  + InputData_Communities.commPBFTVOneTimeFees
						+ " Actual : " + totalOneTimeFees , true);


		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Price Details of added product on Shopping Cart Page", e);
			e.printStackTrace();
		}


	}
	/**
	 * 			Validate the TV ADDOns price
	 * 
	 * @throws IOException
	 */
	public void validateTVAddOnsPrice() throws IOException {

		try {
			sf.seleU.ScrolltoElementPageCenter(sf.comPBFShopCart.addonsNameTV);
			verifyFieldValue("TV AddOn Product NAme", sf.comPBFShopCart.addonsNameTV, InputData_Communities.commPBFTVAddOnName);
			verifyFieldValue("TV AddOn Product Line Total", sf.comPBFShopCart.addedOnsLinePriceTV, String.valueOf(Double.parseDouble(InputData_Communities.commPBFTVAddOnPrice) * InputData_Communities.commPBFTVAddOnQty));
			verifyFieldValue("TV AddOn Product Price", sf.comPBFShopCart.addedOnsUnitPriceTV, InputData_Communities.commPBFTVAddOnPrice);
			verifyFieldValue("TV AddOn Installation Price", sf.comPBFShopCart.oneTimeInstallationFeesTV, InputData_Communities.commPBFTVAddOnInstallationPrice);


		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Price Details of added Addon on Shopping Cart Page", e);
			e.printStackTrace();
		}

	}

	public void removeTVAdded() throws IOException {
		try {
			sf.seleU.ScrolltoElementPageCenter(sf.comPBFShopCart.removeTV);
			sf.seleU.clickElementByJSE(sf.comPBFShopCart.removeTV);
			sf.seleU.wait(20000);
			reportStatusPass(methodName + "Clicked on Remove button to Remove TV product", false, false);
			
		}
		catch (Throwable e) {
			reportStatusFail(methodName + " Error in removing added product on Shopping Cart Page", e);
			e.printStackTrace();
		}
	}

	public void editTVAddons() throws IOException {
		try {
			sf.seleU.ScrolltoElementPageCenter(sf.comPBFShopCart.addonsEditTV);
			sf.seleU.clickElementByJSE(sf.comPBFShopCart.addonsEditTV);
			reportStatusPass(methodName + "Clicked on Edit button to modofy the Addons", false, false);
		}
		catch (Throwable e) {
			reportStatusFail(methodName + " Error in Addons edit click on Shopping Cart Page", e);
			e.printStackTrace();
		}
	}
}
