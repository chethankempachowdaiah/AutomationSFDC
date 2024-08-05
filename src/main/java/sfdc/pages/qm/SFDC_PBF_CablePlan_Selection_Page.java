package sfdc.pages.qm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
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

/**
 * @author Anukriti.Chawla, Date: 3 Aug 2021
 *
 *         SFDC Persona Based Buy Flow Page - Cable PLan
 */
public class SFDC_PBF_CablePlan_Selection_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_PBF_CablePlan_Selection_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_PBF_CablePlanSelection@:";
	}

	/**
	 * 			
	 * 			Validate plan options are 3 year and 5 Year term	
	 * 
	 * @throws IOException
	 */
	public void validatePlanTermsOptionsForRDI() throws IOException {

		try {
			verifyFieldDisplayed("Three Year Term radio button", sf.planSelPBF.threeYearTermRadio);
			verifyFieldDisplayed("Five Year Term radio button", sf.planSelPBF.fiveYearTermRadio);



		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating plan term options", e);
			e.printStackTrace();
		}

	}
	public void selectYearTerm() throws IOException {

		try {

			sf.seleU.clickElementByJSE(sf.planSelPBF.fiveYearTermRadio);

			sf.seleU.wait(10000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in selecting 5 year plan term options", e);
			e.printStackTrace();
		}

	}
	public void selectMonthlyTerm() throws IOException {

		try {
			sf.seleU.wait(7000);
			sf.seleU.clickElementByJSE(sf.planSelPBF.monthlyTermText);

			reportStatusPass(methodName + " selected monthly term option",
					true, false);


		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in selecting Monthly plan term options", e);
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * Validate Name of Plans/Products
	 * 
	 * @throws IOException
	 */
	public void validateDedicatedInternetProductsNames() throws IOException {

		try {
			sf.seleU.wait(5000);
			List<String> expectedBusinessProducts = InputData_Communities.commPBFBusinessProducts;
			List<String> actualBusinessProducts = new ArrayList<String>();

			// Loop over all business plan names to be verified
			for (int i = 0; i < sf.planSelPBF.rdiPlansTitleHeader.size(); i++) {
				actualBusinessProducts.add(sf.seleU.getTextFromWebElement(sf.planSelPBF.rdiPlansTitleHeader.get(i)));
			}
			reportStatusPass(methodName + " Extracted RDI Plan names to compare with expected list", false, false);

			// sort lists for comparison
			Collections.sort(expectedBusinessProducts);
			Collections.sort(actualBusinessProducts);

			// Verify expectedBusiness plan list is equal to actualBusiness Plans List

			if (expectedBusinessProducts.equals(actualBusinessProducts)) {
				reportStatusPass(methodName + " All expected RDI Plans are present on PRoduct Selection Page: "
						+ AdditionalUtilities.getAsString(actualBusinessProducts), true, true);
			} else {
				reportStatusFail(methodName
						+ " All expected RDI Plans are not present on Product Selection Page: :: Expected Plans--> "
						+ AdditionalUtilities.getAsString(expectedBusinessProducts) + "  Actual Plans--> "
						+ AdditionalUtilities.getAsString(actualBusinessProducts), true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Dedicated Internet Products Names", e);
			e.printStackTrace();
		}

	}


	/**
	 * Validate the Product descriptions across each speed is displayed accurately
	 * 
	 * @throws IOException
	 */
	public void validateDedicatedInternetProductsDescription() throws IOException {

		try {

			for (int i=0; i< InputData_Communities.commPBFBusinessProducts.size(); i++) {
				sf.seleU.clickElementByJSE(sf.planSelPBF.rdiPlansViewDetailsButton.get(i));
				sf.seleU.wait(2000);
				reportStatusPass(methodName + " Product Details for Product: " + (i+1), true, true);
				for (int j=0; j< sf.planSelPBF.rdiPlansProductDetails.size() ; j++)
					reportStatusPass(methodName +  " " + (j+1) +": " +
							sf.seleU.getTextFromWebElement(sf.planSelPBF.rdiPlansProductDetails.get(j)), false, true);
				sf.seleU.clickElementByJSE(sf.planSelPBF.rdiPlansViewDetailsButton.get(i));
				sf.seleU.wait(2000);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating RDI plans descriptions", e);
			e.printStackTrace();
		}

	}

	/**
	 * 			
	 * 			Validate plan options are 3 year and monthly term	
	 * 
	 * @throws IOException
	 */
	public void validatePlanTermsOptionsForBI() throws IOException {

		try {
			verifyFieldDisplayed("Three Year Term radio button", sf.planSelPBF.threeYearTermRadio);
			verifyFieldDisplayed("Monthly Term radio button", sf.planSelPBF.monthlyTermRadio);


		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating plan term options", e);
			e.printStackTrace();
		}

	}

	/**
	 * 			
	 * Validate Name of Sub PLans/products
	 * 
	 * @throws IOException
	 */
	public void validateBusinessProductsSubNames() throws IOException {

		try {
			sf.seleU.wait(5000);
			List<String> expectedBusinessProducts = InputData_Communities.commPBFBusinessSubProducts;
			List<String> expectedBusinessProductsFr = InputData_Communities.commPBFBusinessSubProductsFr;
			List<String> actualBusinessProducts = new ArrayList<String>();

			// Loop over all business plan names to be verified
			for (int i = 0; i < sf.planSelPBF.subPlans.size(); i++) {
				actualBusinessProducts.add(sf.seleU.getTextFromWebElement(sf.planSelPBF.subPlans.get(i)));
			}
			reportStatusPass(methodName + " Extracted Business Sub Plan names to compare with expected list",
					false, false);

			// sort lists for comparison
			Collections.sort(expectedBusinessProducts);
			Collections.sort(expectedBusinessProductsFr);
			Collections.sort(actualBusinessProducts);

			// Verify expectedBusiness plan list is equal to actualBusiness Plans List
			if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
				if (expectedBusinessProducts.equals(actualBusinessProducts)) {
					reportStatusPass(methodName + " All expected Business Sub Plans are present on Cable Selection Page: "
							+ AdditionalUtilities.getAsString(actualBusinessProducts), true, true);
				} else {
					reportStatusFail(methodName + " All expected Business Sub Plans are not present on Cable Selection Page: :: Expected Plans--> "
							+ AdditionalUtilities.getAsString(expectedBusinessProducts) + "  Actual Plans--> "
							+ AdditionalUtilities.getAsString(actualBusinessProducts), true);
				}
			} else {
				if (expectedBusinessProductsFr.equals(actualBusinessProducts)) {
					reportStatusPass(methodName + " All expected Business Sub Plans are present on Cable Selection Page: "
							+ AdditionalUtilities.getAsString(actualBusinessProducts), true, true);
				} else {
					reportStatusFail(methodName + " All expected Business Sub Plans are not present on Cable Selection Page: :: Expected Plans--> "
							+ AdditionalUtilities.getAsString(expectedBusinessProductsFr) + "  Actual Plans--> "
							+ AdditionalUtilities.getAsString(actualBusinessProducts), true);
				}

			}


		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Business Sub Products Names", e);
			e.printStackTrace();
		}

	}

	/**
	 * 			
	 * Validate Discount and promo section for all product types
	 * 
	 * @throws IOException
	 */
	public void validateDiscountAndPromoSections() throws IOException {

		try {
			sf.seleU.wait(5000);

			// Loop over all business plan names to be verified
			for (int i = 0; i < InputData_Communities.commPBFProductSpeeds.size(); i++) {
				sf.seleU.ScrolltoElement(sf.planSelPBF.discountAndPromosButton.get(i));
				sf.seleU.clickElementByJSE(sf.planSelPBF.discountAndPromosButton.get(i));
				for (int j = 0; j < sf.planSelPBF.discountAndPromosList.size(); j++) {
					sf.seleU.wait(2000);
					reportStatusPass(methodName + " Promo no. " + (j+1) + " for " + InputData_Communities.commPBFProductSpeeds.get(i)
					+ " product is " + sf.seleU.getTextFromWebElement(sf.planSelPBF.discountAndPromosList.get(j)),
					false, true);
				}
				reportStatusPass(methodName + " Extracted all promos, closing discount and promo section",
						true, false);
				sf.seleU.clickElementByJSE(sf.planSelPBF.discountAndPromosButton.get(i));
				sf.seleU.wait(5000);

			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Discount and Promo section", e);
			e.printStackTrace();
		}

	}

	public void selectDiscountAndPromoSectionsProduct() throws IOException {

		try {
			sf.seleU.wait(5000);

			// Loop over all business plan names to be verified
			outerloop:
				for (int i = 0; i < InputData_Communities.commPBFProductSpeeds.size(); i++) {
					sf.seleU.ScrolltoElementPageCenter(sf.planSelPBF.discountAndPromosButton.get(i));
					sf.seleU.clickElementByJSE(sf.planSelPBF.discountAndPromosButton.get(i));
					sf.seleU.wait(10000);
					for (int j = 0; j < sf.planSelPBF.discountAndPromosList.size(); j++) {
						sf.seleU.wait(2000);

						String discountProduct = sf.seleU.getTextFromWebElement(sf.planSelPBF.discountAndPromosList.get(j)).replaceAll(" ","");
						if(discountProduct.equalsIgnoreCase(InputData_Communities.discountProduct.replaceAll(" ","")) || discountProduct.equalsIgnoreCase(InputData_Communities.discountProductFr)) {

							sf.seleU.ScrolltoElementPageCenter(sf.planSelPBF.discountAndPromosList.get(j));
							sf.seleU.clickOnElement(sf.planSelPBF.discountAndPromosList.get(j));
							reportStatusPass(methodName + InputData_Communities.discountProduct + " is selected", true, false);
							sf.seleU.wait(3000);
							sf.seleU.clickElementByJSE(sf.comPBFCablePlan.addToCartAllButtons.get(i));
							//		sf.seleU.wait(10000);
							sf.seleU.waitForLoading();
							verifyFieldDisplayed(" Remove From Cart Button", sf.gbj.removeFromCartAllButtons.get(0));
							sf.seleU.wait(5000);
							break outerloop;
						}
					}					
					sf.seleU.clickElementByJSE(sf.planSelPBF.discountAndPromosButton.get(i));
					sf.seleU.wait(10000);

				}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in select the Discount and Promo section Product", e);
			e.printStackTrace();
		}

	}

	/**
	 * Pankaj Agarwal 7th Feb			
	 * Select Internet Product type 
	 * 
	 * @throws IOException
	 */
	public void selectInternetProductType() throws IOException {

		try {
			sf.seleU.wait(3000);
			Boolean found = false;
			//*** Loop over all business plan names to be verified
			outerloop:
				for (int i = 0; i < sf.comPBFCablePlan.plansTitleHeader.size(); i++) {
					// first validate the product speed type 
					if(sf.seleU.getTextFromWebElement(sf.comPBFCablePlan.plansTitleHeader.get(i)).equalsIgnoreCase(InputData_Communities.commPBFProductSpeedType)) {
						sf.seleU.ScrolltoElementPageCenter(sf.comPBFCablePlan.plansTitleHeader.get(i));

						//*** validate the product the feature and click the product radio button
						for (int k = 0; k < sf.comPBFCablePlan.allPlansProductFeature.size(); k++) {	
							String productFeatureType = sf.seleU.getTextFromWebElement(sf.comPBFCablePlan.allPlansProductFeature.get(k));
							//			System.out.println(productFeatureType);
							//			System.out.println(InputData_Communities.commPBFAddProductName.trim());
							if (InputData_Communities.commPBFAddProductName.trim().equals(productFeatureType.trim())) {
								sf.seleU.ScrolltoElementPageCenter(sf.comPBFCablePlan.allPlansProductFeature.get(k));
								sf.seleU.clickElementByJSE(sf.comPBFCablePlan.allPlansProductFeatureRadioClick.get(k));
								reportStatusPass(methodName + productFeatureType + " is selected", true,true);
								found = true;
								break outerloop;
							}
						}
					}
				}
			if(!found) {
				reportStatusFail(methodName + InputData_Communities.commPBFAddProductName + " is not selected", false);
			}


		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in selecting the product type", e);
			e.printStackTrace();
		}

	}

	/**
	 * Pankaj Agarwal 7th Feb			
	 * Select Internet Product type 
	 * 
	 * @throws IOException
	 */
	public void selectInternetProductType_Monthly() throws IOException {

		try {
			sf.seleU.wait(3000);
			Boolean found = false;
			//*** Loop over all business plan names to be verified
			outerloop:
				for (int i = 0; i < sf.comPBFCablePlan.plansTitleHeader.size(); i++) {
					// first validate the product speed type 
					if(sf.seleU.getTextFromWebElement(sf.comPBFCablePlan.plansTitleHeader.get(i)).equalsIgnoreCase(InputData_Communities.commPBFProductSpeedType)) {
						sf.seleU.ScrolltoElementPageCenter(sf.comPBFCablePlan.plansTitleHeader.get(i));

						//*** validate the product the feature and click the product radio button
						for (int k = 0; k < sf.comPBFCablePlan.allPlansProductFeature.size(); k++) {	
							String productFeatureType = sf.seleU.getTextFromWebElement(sf.comPBFCablePlan.allPlansProductFeature.get(k));
							//			System.out.println(productFeatureType);
							//			System.out.println(InputData_Communities.commPBFAddProductName.trim());
							if (InputData_Communities.commPBFAddProductName.trim().equals(productFeatureType.trim())) {
								sf.seleU.ScrolltoElementPageCenter(sf.comPBFCablePlan.allPlansProductFeature.get(k));
								sf.seleU.clickElementByJSE(sf.comPBFCablePlan.allPlansProductFeatureRadioClick.get(k));
								reportStatusPass(methodName + productFeatureType + " is selected", true,true);
								found = true;
								sf.seleU.clickElementByJSE(sf.comPBFCablePlan.addToCartAllButtons.get(i));
								//		sf.seleU.wait(10000);
								sf.seleU.waitForLoading();
								verifyFieldDisplayed(" Remove From Cart Button", sf.gbj.removeFromCartAllButtons.get(0));
								break outerloop;
							}
						}
					}
				}
			if(!found) {
				reportStatusFail(methodName + InputData_Communities.commPBFAddProductName + " is not selected", false);
			}


		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in selecting the product type", e);
			e.printStackTrace();
		}

	}

	/*Created to handle a pop up when selecting products to add to cart
	 * Author: Prab Sharan Singh
	 */

	public void handlePBFpopup() throws IOException {

		try {
			sf.seleU.wait(3000);
			if(sf.planSelPBF.popuppresencemsg.size()>0) {

				sf.seleU.clickElementByJSE(sf.planSelPBF.popupokbtn);
				sf.seleU.wait(5000);
			}


		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in handling pop up", e);
			e.printStackTrace();
		}
	}

	/**
	 * 			
	 * Validate Discount and promo section for all product types
	 * 
	 * @throws IOException
	 */
	public void discountAndPromoSectionsForRDI() throws IOException {

		try {
			sf.seleU.wait(5000);
			if (Integer.parseInt(InputData_Communities.discount) > 0) {
				// Loop over all business plan names to be verified
				for (int i = 0; i < InputData_Communities.commPBFProductSpeeds.size(); i++) {
					sf.seleU.ScrolltoElement(sf.planSelPBF.discountAndPromosButtonForRDI.get(i));
					sf.seleU.clickElementByJSE(sf.planSelPBF.discountAndPromosButtonForRDI.get(i));
					verifyFieldDisplayed("Discount And Promotion section for Product " + (i+1), sf.planSelPBF.discountAndPromoSectionForRDI);
					sf.seleU.clickElementByJSE(sf.planSelPBF.discountAndPromosButtonForRDI.get(i));

				}
			} else
				verifyFieldDisabled("Promo and Discount Section", sf.planSelPBF.discountAndPromosButtonForRDI.get(0));

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Discount and Promo section", e);
			e.printStackTrace();
		}

	}


	/**
	 * 			
	 * Validate Discount and promo section for all product types
	 * 
	 * @throws IOException
	 */
	public void validateDiscountAndPromoSectionsForRDI() throws IOException {

		try {
			sf.seleU.wait(5000);
			if (Integer.parseInt(InputData_Communities.discount) > 0) {
				// Loop over all business plan names to be verified
				for (int i = 0; i < sf.planSelPBF.rdiPlansTitleHeader.size(); i++) {
					String planTile = sf.seleU.getTextFromWebElement(sf.planSelPBF.rdiPlansTitleHeader.get(i));
					if (InputData_Communities.commPBFAddProductName.contains(planTile)) {
						// Discount Price validation for Product
						sf.seleU.ScrolltoElementPageCenter(sf.planSelPBF.discountAndPromosButtonForRDI.get(i));
						sf.seleU.clickElementByJSE(sf.planSelPBF.discountAndPromosButtonForRDI.get(i));
						verifyFieldDisplayed("Discount And Promotion section for Plan "+planTile+" is Selected", sf.planSelPBF.discountAndPromoSectionForRDI);
						/*
						 * for (int j=1; j <= Integer.parseInt(InputData_Communities.discount); j++) {
						 * sf.seleU.clickOnElement(sf.planSelPBF.increment); sf.seleU.wait(2000); }
						 */
						sf.seleU.clearAndEnterText(sf.planSelPBF.discountValue, InputData_Communities.discount);
						sf.seleU.clickEnter();
						verifyFieldDisplayed(" Discount Section Displayed in Price Info", sf.planSelPBF.discountSectionInPrice);

						// Price validation after applied Discount
						String actualProductPrice = String.valueOf(Double.parseDouble(sf.seleU.getTextFromWebElement(sf.planSelPBF.price.get(i))+"."+sf.seleU.getTextFromWebElement(sf.planSelPBF.decimalPrice.get(i))));
						String expectedProductPrice = String.valueOf(Double.parseDouble(InputData_Communities.commPBFProductPrice) - 
								((Double.parseDouble(InputData_Communities.commPBFProductPrice) * Double.parseDouble(InputData_Communities.discount)) / 100 ));
						InputData_Communities.commPBFProductPrice = expectedProductPrice;
						if(actualProductPrice.equals(expectedProductPrice)) {
							reportStatusPass( " Actual price " +actualProductPrice+ " equals expected Price "+expectedProductPrice, true, true);
						} else {
							reportStatusFail(" Actual price " +actualProductPrice+ " Not equals expected Price "+expectedProductPrice, true);
						}						
						break;
					}
					//sf.seleU.clickElementByJSE(sf.planSelPBF.discountAndPromosButtonForRDI.get(i));
				}
			} 

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Discount and Promo section functionality", e);
			e.printStackTrace();
		}

	}

	/**
	 * Validates boundary value check of discount and promos for RDI Product
	 * @throws IOException
	 */

	public void validateBoundaryValueCheckDiscountAndPromoSectionsForRDI() throws IOException {

		try {
			sf.seleU.wait(5000);
			if (Integer.parseInt(InputData_Communities.discount) > 0) {
				// Loop over all business plan names to be verified
				for (int i = 0; i < sf.planSelPBF.rdiPlansTitleHeader.size(); i++) {
					String planTile = sf.seleU.getTextFromWebElement(sf.planSelPBF.rdiPlansTitleHeader.get(i));
					if (InputData_Communities.commPBFAddProductName.contains(planTile)) {
						// Discount Price validation for Product
						sf.seleU.ScrolltoElementPageCenter(sf.planSelPBF.discountAndPromosButtonForRDI.get(i));
						sf.seleU.clickElementByJSE(sf.planSelPBF.discountAndPromosButtonForRDI.get(i));
						verifyFieldDisplayed("Discount And Promotion section for Plan "+planTile+" is Selected", sf.planSelPBF.discountAndPromoSectionForRDI);

						// Validating the increment and decrement checks						
						verifyFieldDisabled(sf.planSelPBF.decrement);

						verifyFieldEnabled(sf.planSelPBF.increment);

						sf.seleU.clearAndEnterText(sf.planSelPBF.discountValue, "45");
						sf.seleU.clickEnter();

						verifyFieldDisabled(sf.planSelPBF.increment);

						verifyFieldEnabled(sf.planSelPBF.decrement);

						sf.seleU.clickOnElement(sf.planSelPBF.resetOffers);
						reportStatusPass("Discount and Promos will be zero", "Clicks on Reset Offers", false);

						verifyFieldDisabled(sf.planSelPBF.decrement);

						verifyFieldEnabled(sf.planSelPBF.increment);

						sf.seleU.clickElementByJSE(sf.planSelPBF.discountAndPromosButtonForRDI.get(i));
						break;
					}
				}
			} 

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating boundary checks of Discount and Promos", e);
			e.printStackTrace();
		}

	}


	/**
	 * Click on Add to Cart for Product
	 * 
	 * Validate once the product is added to the cart, the speed tile gets
	 * highlighted
	 * 
	 * Remove from Cart CTA is displayed on the Price Column"
	 * 
	 * Validate Price of selected product
	 * 
	 * Validate a Teal Translucent Bar is displayed for the user with Proceed to
	 * Shopping Cart CTA which when clicked should land user to the Shopping Cart
	 * Page.
	 * 
	 * @throws IOException
	 */
	public void addToCartRDIProductAndProceed() throws IOException {

		try {
			sf.seleU.wait(10000);

			// Add to Cart the selected product

			for (int i = 0; i < sf.planSelPBF.rdiPlansTitleHeader.size(); i++) {
				String planTile = sf.seleU.getTextFromWebElement(sf.planSelPBF.rdiPlansTitleHeader.get(i));
				if (InputData_Communities.commPBFAddProductName.contains(planTile)) {

					sf.seleU.clickElementByJSE(sf.comPBFCablePlan.addToCartAllButtons.get(i));
					reportStatusPass(methodName + " Added Product to Cart is " + InputData_Communities.commPBFAddProductName, true, true);
					sf.seleU.wait(60000);

					// Validate Price of Product
					//					validatePrice(" Plan/Product Price", sf.gbjCart.price.get(i),
					//							InputData_Communities.commPBFProductPrice);

					// Validate Remove from Cart is displayed
					verifyFieldDisplayed(" Remove From Cart Button", sf.gbj.removeFromCartAllButtons.get(0));
					break;
				}

			}

			// Validate speed tile gets highlighted
			//verifyFieldDisplayed(" Selected Tile Highlight Div", sf.comPBFCablePlan.selectedDivContainer);

			sf.seleU.wait(7000);

			// Validate Shop Cart Icon has 1

			if (sf.seleU.isElementPresent(sf.comPBFCablePlan.shoppingCartIconProductCount)
					&& sf.comPBFCablePlan.shoppingCartIconProductCount.getAttribute("innerHTML").equalsIgnoreCase("1"))
				reportStatusPass(methodName + " Shopping Cart Icon Product Count is 1", true, false);
			else
				reportStatusFail(methodName + " Exepcted Product Count(1) is not present on Shopping cart Icon", true);

			//Update Ethernet Plan if Requested
			if (!InputData_Communities.commPBFEditEthernetPlan.equals("No")) {
				sf.seleU.clickElementByJSE(sf.planSelPBF.nextButton);
				reportStatusPass(methodName +" Clicked on Next to update Ethernet Network Access Plan", false, true);
				sf.seleU.wait(15000);
				updateEthernetPlan();
			}

			// Proceed to Checkout
			if (sf.seleU.isElementDisplayed(sf.comPBFCablePlan.proceedToShoppingCartButton)) {
				sf.seleU.clickElementByJSE(sf.comPBFCablePlan.proceedToShoppingCartButton);
				sf.seleU.wait(5000);
				reportStatusPass(methodName + " Clicked on Proceed to ShoppingCart Button", true, true);
			}
		} catch (

				Throwable e) {
			reportStatusFail(methodName + " Error in Adding Product to Cart", e);
			e.printStackTrace();
		}

	}
	/**
	 * 
	 *  Created for Multisite
	 *
	 * Click on Add to Cart for Product
	 * 
	 * Validate once the product is added to the cart, the speed tile gets
	 * highlighted
	 * 
	 * Remove from Cart CTA is displayed on the Price Column"
	 *
	 * 
	 * Add ethernet plan 
	 * 
	 *  Proceed to
	 * Shopping Cart CTA which when clicked should land user to the Shopping Cart
	 * Page.
	 * 
	 * @throws IOException
	 */
	public void addToCartRDIProductAndEthernetPlan() throws IOException {

		try {

			sf.seleU.wait(4000);

			// Add to Cart the selected product

			for (int i = 0; i < sf.planSelPBF.rdiPlansTitleHeader.size(); i++) {
				String planTile = sf.seleU.getTextFromWebElement(sf.planSelPBF.rdiPlansTitleHeader.get(i));
				if (InputData_Communities.mulPBFSiteProductPlanName.contains(planTile)) {

					sf.seleU.clickElementByJSE(sf.comPBFCablePlan.addToCartAllButtons.get(i));
					reportStatusPass(methodName + " Added Product to Cart ", true, true);
					sf.seleU.wait(30000);
					// Validate Remove from Cart is displayed
					verifyFieldDisplayed(" Remove From Cart Button", sf.gbj.removeFromCartAllButtons.get(0));
					break;
				}

			}
			sf.seleU.wait(7000);

			sf.seleU.clickElementByJSE(sf.planSelPBF.nextButtonForEthernet);
			reportStatusPass(methodName +" Clicked on Next to update Ethernet Network Access Plan", false, true);
			sf.seleU.wait(15000);

			//Update Ethernet plan if not already selected
			verifyFieldDisplayed("Ethernet Access options Page Header", sf.planSelPBF.fibreAccessPageHeader);

			if(sf.seleU.isElementDisplayed(sf.planSelPBF.ethernetE100Option)
					&& InputData_Communities.mulPBFSiteProductAddOnName.equals("E100")) 
				sf.seleU.clickElementByJSE(sf.planSelPBF.ethernetE100Option);

			if(sf.seleU.isElementDisplayed(sf.planSelPBF.ethernetE1000Option)
					&&InputData_Communities.mulPBFSiteProductAddOnName.equals("E1000")) 
				sf.seleU.clickElementByJSE(sf.planSelPBF.ethernetE1000Option);

			sf.seleU.wait(5000);
			if(sf.shopCartPBF.updateCartButton.isEnabled()) {
				sf.seleU.clickElementByJSE(sf.shopCartPBF.updateCartButton);
				reportStatusPass(methodName +" Clicked on Update Cart", false, true);
				sf.seleU.wait(30000);
			}
			// Proceed to Checkout
			sf.seleU.clickOnElement(sf.comPBFCablePlan.proceedToShoppingCartButton);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Clicked on Proceed to checkout Button", true, true);


		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Adding Product to Cart", e);
			e.printStackTrace();
		}

	}
	public void addToCartRDIProduct() throws IOException {

		try {
			sf.seleU.wait(10000);

			// Add to Cart the selected product

			for (int i = 0; i < sf.planSelPBF.rdiPlansTitleHeader.size(); i++) {
				String planTile = sf.seleU.getTextFromWebElement(sf.planSelPBF.rdiPlansTitleHeader.get(i));
				if (InputData_Communities.commPBFAddProductName.contains(planTile)) {

					sf.seleU.clickElementByJSE(sf.comPBFCablePlan.addToCartAllButtons.get(i));
					reportStatusPass(methodName + " Added Product to Cart ", true, true);
					sf.seleU.wait(10000);

					// Validate Price of Product
					validatePrice(" Plan/Product Price", sf.gbjCart.price.get(i),
							InputData_Communities.commPBFProductPrice);

					sf.seleU.wait(10000);

					// Validate Remove from Cart is displayed
					verifyFieldDisplayed(" Remove From Cart Button", sf.gbj.removeFromCartAllButtons.get(0));
					break;
				}

			}

			// Validate speed tile gets highlighted
			//verifyFieldDisplayed(" Selected Tile Highlight Div", sf.comPBFCablePlan.selectedDivContainer);

			sf.seleU.wait(7000);


		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Adding Product to Cart", e);
			e.printStackTrace();
		}

	}

	public void proceedToCheckout() throws IOException {
		try {
			// Proceed to Checkout
			if (sf.seleU.isElementDisplayed(sf.comPBFCablePlan.proceedToShoppingCartButton)) {
				sf.seleU.clickElementByJSE(sf.comPBFCablePlan.proceedToShoppingCartButton);
				sf.seleU.wait(5000);
				reportStatusPass(methodName + " Clicked on Proceed to checkout Button", true, true);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Proceed to Checkout", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                      Edit Ethernet Plan to E1000/E100
	 * 
	 */
	public static void updateEthernetPlan() throws IOException {
		try {

			sf.seleU.wait(5000);


			verifyFieldDisplayed("Ethernet Access options Page Header", sf.planSelPBF.fibreAccessPageHeader);

			if(InputData_Communities.commPBFEditEthernetPlan.equalsIgnoreCase("E100")) 
				sf.seleU.clickElementByJSE(sf.planSelPBF.ethernetE100Option);
			else
				sf.seleU.clickElementByJSE(sf.planSelPBF.ethernetE1000Option);

			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.shopCartPBF.updateCartButton);
			reportStatusPass(methodName +" Clicked on Update Cart", false, true);
			sf.seleU.wait(30000);

			// Proceed to Checkout
			sf.seleU.clickOnElement(sf.comPBFCablePlan.proceedToShoppingCartButton);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Clicked on Proceed to checkout Button", true, true);

			verifyFieldDisplayed("Shopping Cart Header", sf.comPBFShopCart.shoppingCartHeader);

			if(InputData_Communities.commPBFEditEthernetPlan.equalsIgnoreCase("E100"))  {
				InputData_Communities.commPBFCurrentEthernetPlan = "E100";
				InputData_Communities.commPBFCurrentEthernetPlanCost = InputData_Communities.commPBFEthernetPlanE100Cost;
				//				verifyFieldDisplayed("Ethernet Access E100 Plan", sf.comPBFShopCart.ethernetAccessE100PlanLabel);
				//				verifyFieldNotDisplayed("Ethernet Access E1000 Plan", sf.comPBFShopCart.ethernetAccessE1000PlanLabel);
				//			
			}
			else {
				InputData_Communities.commPBFCurrentEthernetPlan = "E1000";
				InputData_Communities.commPBFCurrentEthernetPlanCost = InputData_Communities.commPBFEthernetPlanE1000Cost;
				//verifyFieldDisplayed("Ethernet Access E1000 Plan", sf.comPBFShopCart.ethernetAccessE1000PlanLabel);
				//verifyFieldNotDisplayed("Ethernet Access E100 Plan", sf.comPBFShopCart.ethernetAccessE100PlanLabel);

			}
			//Validate Line Total
			verifyFieldValue("Line Total Price for Ethernet Access", sf.comPBFShopCart.ethernetAccessLineTotal, InputData_Communities.commPBFCurrentEthernetPlanCost);


		} catch (Throwable e) {
			reportStatusFail(methodName + " Updating Ethernet Plan is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Validate Internet TCV
	 */
	public void validate_Internet_TCV() throws IOException {

		InputData_Communities.monthylyTotal_num = Double.parseDouble(InputData_Communities.commPBFProductPrice);

		if(InputData_Communities.commPBFProductTerm.equalsIgnoreCase("Monthly Term")) {
			InputData_Communities.recurringTCV_num = Double.parseDouble(InputData_Communities.commPBFProductPrice);
			InputData_Communities.totalCost_num = (Double.parseDouble(InputData_Communities.commPBFReccuringCost)) +50;
		} else {
			InputData_Communities.recurringTCV_num = Double.parseDouble(InputData_Communities.commPBFProductPrice) * 36;
			InputData_Communities.totalCost_num = (Double.parseDouble(InputData_Communities.commPBFReccuringCost) * 36) +50;
		}
		InputData_Communities.onetimeTCV_num = Double.parseDouble(InputData_Communities.commPBFOneTimeFees);
		InputData.totalTCV_num = InputData_Communities.recurringTCV_num + InputData_Communities.onetimeTCV_num;

		InputData_Communities.tcvMargin_num = InputData_Communities.totalTCV_num - InputData_Communities.totalCost_num;

		sf.seleU.wait(5000);

		verifyTCV("MONTHLY TOTAL", sf.planSelPBF.monthlyTotal, InputData_Communities.monthylyTotal_num);
		verifyTCV("RECURRING TCV", sf.planSelPBF.recurringTCV, InputData_Communities.recurringTCV_num);
		verifyTCV("ONE TIME TCV", sf.planSelPBF.oneTimeTCV, InputData_Communities.onetimeTCV_num);
		verifyTCV("TOTAL TCV", sf.planSelPBF.totalTCV, InputData_Communities.totalTCV_num);
		verifyTCV("TOTAL COSTS", sf.planSelPBF.totalCosts, InputData_Communities.totalCost_num);
		verifyTCV("TCV MARGIN $", sf.planSelPBF.tcvMarginValue, InputData_Communities.tcvMargin_num);
	}


	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Validate Internet TCV for discount Product
	 */
	public void validate_Internet_TCV_Discount_Product() throws IOException {

		InputData_Communities.monthylyTotal_num = Double.parseDouble(InputData_Communities.discountPrice);
		InputData_Communities.recurringTCV_num = Double.parseDouble(InputData_Communities.discountPrice) * 36;
		InputData_Communities.onetimeTCV_num = Double.parseDouble(InputData_Communities.commPBFOneTimeFees);
		InputData.totalTCV_num = InputData_Communities.recurringTCV_num + InputData_Communities.onetimeTCV_num;
		InputData_Communities.totalCost_num = (Double.parseDouble(InputData_Communities.commPBFReccuringCost) * 36) +50;
		InputData_Communities.tcvMargin_num = InputData_Communities.totalTCV_num - InputData_Communities.totalCost_num;

		sf.seleU.wait(10000);

		verifyTCV("MONTHLY TOTAL", sf.planSelPBF.monthlyTotal, InputData_Communities.monthylyTotal_num);
		verifyTCV("RECURRING TCV", sf.planSelPBF.recurringTCV, InputData_Communities.recurringTCV_num);
		verifyTCV("ONE TIME TCV", sf.planSelPBF.oneTimeTCV, InputData_Communities.onetimeTCV_num);
		verifyTCV("TOTAL TCV", sf.planSelPBF.totalTCV, InputData_Communities.totalTCV_num);
		verifyTCV("TOTAL COSTS", sf.planSelPBF.totalCosts, InputData_Communities.totalCost_num);
		verifyTCV("TCV MARGIN $", sf.planSelPBF.tcvMarginValue, InputData_Communities.tcvMargin_num);
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Validate Internet TCV
	 */
	public void validate_BusinessInternet_TCV_Shopping_Cart() throws IOException {

		InputData_Communities.monthylyTotal_num = Double.parseDouble("0");
		InputData_Communities.recurringTCV_num = Double.parseDouble("0") * 36;
		InputData_Communities.onetimeTCV_num = Double.parseDouble("0");
		InputData.totalTCV_num = InputData_Communities.recurringTCV_num + InputData_Communities.onetimeTCV_num;
		InputData_Communities.totalCost_num = (Double.parseDouble("0"));
		InputData_Communities.tcvMargin_num = InputData_Communities.totalTCV_num - InputData_Communities.totalCost_num;

		sf.seleU.wait(20000);

		verifyTCV("MONTHLY TOTAL", sf.planSelPBF.monthlyTotal, InputData_Communities.monthylyTotal_num);
		verifyTCV("RECURRING TCV", sf.planSelPBF.recurringTCV, InputData_Communities.recurringTCV_num);
		verifyTCV("ONE TIME TCV", sf.planSelPBF.oneTimeTCV, InputData_Communities.onetimeTCV_num);
		verifyTCV("TOTAL TCV", sf.planSelPBF.totalTCV, InputData_Communities.totalTCV_num);
		verifyTCV("TOTAL COSTS", sf.planSelPBF.totalCosts, InputData_Communities.totalCost_num);
		verifyTCV("TCV MARGIN $", sf.planSelPBF.tcvMarginValue, InputData_Communities.tcvMargin_num);
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Validate Internet + Office 365 AddOn
	 */
	public void validate_BusInt_Off365_TCV() throws IOException {

		try {
			InputData_Communities.monthylyTotal_num = Double.parseDouble(InputData_Communities.commPBFProductPrice) + 
					Double.parseDouble(InputData_Communities.commPBFOffAddOnPrice) * InputData_Communities.commPBFOffAddOnQty;
			
			if (InputData_Communities.commPBFProductTerm.contains("Month"))
				InputData_Communities.recurringTCV_num = InputData_Communities.monthylyTotal_num;
			else
				InputData_Communities.recurringTCV_num = InputData_Communities.monthylyTotal_num * 36;

			InputData_Communities.onetimeTCV_num = Double.parseDouble(InputData_Communities.commPBFOneTimeFees);
			InputData.totalTCV_num = InputData_Communities.recurringTCV_num + InputData_Communities.onetimeTCV_num;

			if (InputData_Communities.commPBFProductTerm.contains("Month"))
				InputData_Communities.totalCost_num = (Double.parseDouble(InputData_Communities.commPBFReccuringCost)  + Double.parseDouble(InputData_Communities.commPBFCostToUseOfficeAddOn)) +50;
			else
				InputData_Communities.totalCost_num = ((Double.parseDouble(InputData_Communities.commPBFReccuringCost)  + Double.parseDouble(InputData_Communities.commPBFCostToUseOfficeAddOn)) * 36) +50;

			InputData_Communities.tcvMargin_num = InputData_Communities.totalTCV_num - InputData_Communities.totalCost_num;

			sf.seleU.wait(30000);

			verifyTCV("MONTHLY TOTAL", sf.planSelPBF.monthlyTotal, InputData_Communities.monthylyTotal_num);
			verifyTCV("RECURRING TCV", sf.planSelPBF.recurringTCV, InputData_Communities.recurringTCV_num);
			verifyTCV("ONE TIME TCV", sf.planSelPBF.oneTimeTCV, InputData_Communities.onetimeTCV_num);
			verifyTCV("TOTAL TCV", sf.planSelPBF.totalTCV, InputData_Communities.totalTCV_num);
			if(!InputData_Communities.commPBFUser.equalsIgnoreCase("Var")) {
				verifyTCV("TOTAL COSTS", sf.planSelPBF.totalCosts, InputData_Communities.totalCost_num);
				verifyTCV("TCV MARGIN $", sf.planSelPBF.tcvMarginValue, InputData_Communities.tcvMargin_num);
			}
		} catch (Exception e) {
			reportStatusFail(" Error in TCV verification", e);
			e.printStackTrace();
		}
	}


	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Validate Internet + Office 365 + TV AddOn
	 */
	public void validate_BusInt_Off365_TV_TCV() throws IOException {

		try {
			InputData_Communities.monthylyTotal_num = Double.parseDouble(InputData_Communities.commPBFProductPrice) + Double.parseDouble(InputData_Communities.commPBFTVProductPrice)+ (Double.parseDouble(InputData_Communities.commPBFOffAddOnPrice) * InputData_Communities.commPBFOffAddOnQty) + 
					(+ Double.parseDouble(InputData_Communities.commPBFTVAddOnPrice) * InputData_Communities.commPBFTVAddOnQty);
			if (InputData_Communities.commPBFProductTerm.contains("Month"))
				InputData_Communities.recurringTCV_num = InputData_Communities.monthylyTotal_num;
			else
				InputData_Communities.recurringTCV_num = InputData_Communities.monthylyTotal_num * 36;

			InputData_Communities.onetimeTCV_num = Double.parseDouble(InputData_Communities.commPBFOneTimeFees);
			InputData.totalTCV_num = InputData_Communities.recurringTCV_num + InputData_Communities.onetimeTCV_num;

			if (InputData_Communities.commPBFProductTerm.contains("Month"))
				InputData_Communities.totalCost_num = (Double.parseDouble(InputData_Communities.commPBFReccuringCost)  + 
						Double.parseDouble(InputData_Communities.commPBFCostToUseOfficeAddOn)) +50 + 
				Double.parseDouble(InputData_Communities.commPBFCostToUseTV) + AdditionalUtilities.roundOffDouble2DecimalPlaces(Double.parseDouble(InputData_Communities.commPBFCostToUseTVAddOn));
			else
				InputData_Communities.totalCost_num = ((Double.parseDouble(InputData_Communities.commPBFReccuringCost)  + 
						Double.parseDouble(InputData_Communities.commPBFCostToUseOfficeAddOn)) * 36) +50 + 
				(Double.parseDouble(InputData_Communities.commPBFCostToUseTV) + AdditionalUtilities.roundOffDouble2DecimalPlaces(Double.parseDouble(InputData_Communities.commPBFCostToUseTVAddOn))) * 36;

			InputData_Communities.tcvMargin_num = InputData_Communities.totalTCV_num - InputData_Communities.totalCost_num;

			sf.seleU.wait(10000);

			verifyTCV("MONTHLY TOTAL", sf.planSelPBF.monthlyTotal, InputData_Communities.monthylyTotal_num);
			verifyTCV("RECURRING TCV", sf.planSelPBF.recurringTCV, InputData_Communities.recurringTCV_num);
			verifyTCV("ONE TIME TCV", sf.planSelPBF.oneTimeTCV, InputData_Communities.onetimeTCV_num);
			verifyTCV("TOTAL TCV", sf.planSelPBF.totalTCV, InputData_Communities.totalTCV_num);
			verifyTCV("TOTAL COSTS", sf.planSelPBF.totalCosts, InputData_Communities.totalCost_num);
			verifyTCV("TCV MARGIN $", sf.planSelPBF.tcvMarginValue, InputData_Communities.tcvMargin_num);
		} catch (Exception e) {
			reportStatusFail(" Error in TCV verification", e);
			e.printStackTrace();
		}
	}
	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Validate Dedicated Internet TCV
	 */
	public void validate_RDI_TCV() throws IOException {

		InputData_Communities.monthylyTotal_num = Double.parseDouble(InputData_Communities.commPBFProductPrice);
		//InputData_Communities.recurringTCV_num = ((Double.parseDouble(InputData_Communities.commPBFProductPrice) - Double.parseDouble(InputData_Communities.commPBFCurrentEthernetPlanCost)) * 36) + Integer.valueOf(InputData_Communities.commPBFCurrentEthernetPlanCost);
		if(InputData_Communities.commPBFProductTerm.equalsIgnoreCase("5 year term") ) {
			InputData_Communities.recurringTCV_num = ((Double.parseDouble(InputData_Communities.commPBFProductPrice) * 60) );	
		}
		else {
			InputData_Communities.recurringTCV_num = ((Double.parseDouble(InputData_Communities.commPBFProductPrice) * 36) );
		}

		InputData_Communities.onetimeTCV_num = Double.parseDouble(InputData_Communities.commPBFOneTimeFees);
		InputData.totalTCV_num = InputData_Communities.recurringTCV_num + InputData_Communities.onetimeTCV_num;

		if (InputData_Communities.commPBFTypeOfAddress.contains("NEAR")) {
			if(InputData_Communities.commPBFProductTerm.equalsIgnoreCase("5 year term") ) {
				InputData_Communities.totalCost_num = (Double.parseDouble(InputData_Communities.commPBFReccuringCost) * 60) + 10000 ;	
			}
			else
				InputData_Communities.totalCost_num = (Double.parseDouble(InputData_Communities.commPBFReccuringCost) * 36) + 10000 ;
		}
		else {

			if(InputData_Communities.commPBFProductTerm.equalsIgnoreCase("5 year term") ) {
				InputData_Communities.totalCost_num = (Double.parseDouble(InputData_Communities.commPBFReccuringCost) * 60) + 5000 ; 	
			}
			else {
				InputData_Communities.totalCost_num = (Double.parseDouble(InputData_Communities.commPBFReccuringCost) * 36) + 5000 ;}
		}
		InputData_Communities.tcvMargin_num = InputData_Communities.totalTCV_num - InputData_Communities.totalCost_num;

		sf.seleU.wait(30000);

		verifyTCV("MONTHLY TOTAL", sf.planSelPBF.monthlyTotal, InputData_Communities.monthylyTotal_num);
		verifyTCV("RECURRING TCV", sf.planSelPBF.recurringTCV, InputData_Communities.recurringTCV_num);
		verifyTCV("ONE TIME TCV", sf.planSelPBF.oneTimeTCV, InputData_Communities.onetimeTCV_num);
		verifyTCV("TOTAL TCV", sf.planSelPBF.totalTCV, InputData_Communities.totalTCV_num);
		if(!InputData_Communities.commPBFUser.equalsIgnoreCase("Var")) {
			verifyTCV("TOTAL COSTS", sf.planSelPBF.totalCosts, InputData_Communities.totalCost_num);
			verifyTCV("TCV MARGIN $", sf.planSelPBF.tcvMarginValue, InputData_Communities.tcvMargin_num);
		}
	}

	public void validate_TV_TCV() throws IOException {

		InputData_Communities.monthylyTotal_num = Double.parseDouble(InputData_Communities.commPBFTVProductPrice)+ (Double.parseDouble(InputData_Communities.commPBFTVAddOnPrice) * InputData_Communities.commPBFTVAddOnQty);

		if (InputData_Communities.commPBFProductTerm.contains("Month"))
			InputData_Communities.recurringTCV_num = InputData_Communities.monthylyTotal_num;
		else
			InputData_Communities.recurringTCV_num = InputData_Communities.monthylyTotal_num * 36;

		InputData_Communities.onetimeTCV_num = Double.parseDouble(InputData_Communities.commPBFTVOneTimeFees);
		InputData.totalTCV_num = InputData_Communities.recurringTCV_num + InputData_Communities.onetimeTCV_num;
		//InputData_Communities.totalCost_num = ((Double.parseDouble(InputData_Communities.commPBFTVReccuringCost)  + Double.parseDouble(InputData_Communities.commPBFCostToUseTVAddOn)) * 36) +25;
		//InputData_Communities.tcvMargin_num = InputData_Communities.totalTCV_num - InputData_Communities.totalCost_num;

		sf.seleU.wait(30000);

		verifyTCV("MONTHLY TOTAL", sf.planSelPBF.monthlyTotal, InputData_Communities.monthylyTotal_num);
		verifyTCV("RECURRING TCV", sf.planSelPBF.recurringTCV, InputData_Communities.recurringTCV_num);
		verifyTCV("ONE TIME TCV", sf.planSelPBF.oneTimeTCV, InputData_Communities.onetimeTCV_num);
		verifyTCV("TOTAL TCV", sf.planSelPBF.totalTCV, InputData_Communities.totalTCV_num);

		//		verifyTCV("TOTAL COSTS", sf.planSelPBF.totalCosts, InputData_Communities.totalCost_num);
		//		verifyTCV("TCV MARGIN $", sf.planSelPBF.tcvMarginValue, InputData_Communities.tcvMargin_num);
	}

	/**
	 * @param fieldName
	 * @param ele
	 * @param expectedValue
	 * @throws IOException
	 * 
	 *                     Verify field value matches the exepected value
	 */
	public static void verifyTCV(String fieldName, WebElement ele, double expectedValue_num) throws IOException {
		try {

			double actualValue_num;
			double expectedValueRoundedOff;

			sf.seleU.scrollByCoOrdinates(1);

			String text = sf.seleU.getTextFromWebElement(ele);
			if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
				if (text.contains(" ")) {
					text = text.replaceAll(" .*", "");
				}
				actualValue_num = Double.parseDouble(text.substring(1, text.length()).replaceAll(",", ""));
			} else {
				String array[]= text.replaceAll(" ", "").split("\\$");
				actualValue_num = Double.parseDouble(array[0].replaceAll(",", "."));
			}
			expectedValueRoundedOff = AdditionalUtilities.roundOffDouble2DecimalPlaces(expectedValue_num);

			System.out.println(".....**********Actual.......... :" + actualValue_num);
			System.out.println(".....**********Expected.........:" + expectedValueRoundedOff);

			if (actualValue_num == expectedValueRoundedOff) {
				reportStatusPass(methodName + " Validated product " + fieldName + " as  " + expectedValueRoundedOff,
						true, true);
			} else {
				reportStatusFail(methodName + " Expected value for product " + fieldName + " is " + expectedValue_num
						+ " Actual value for Product " + fieldName + " is " + actualValue_num, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field value verification", e);
			e.printStackTrace();
		}
	}

	/**Pankaj Agarwal 20 Jan 2022
	 * @param fieldName
	 * @param ele
	 * @param expectedValue
	 * @throws IOException
	 * 
	 *                     Verify monthly total is > 500
	 */
	public static void verifyMonthlyTotal() throws IOException {
		try {

			double actualValue_num;
			double fraudMonthlyCutoff_Price500;
			double fraudMonthlyCutoff_Price2000;
			double expectedMonthlyTotal;
			sf.dataInput.fraudCheckStatus = "No";
			sf.dataInput.fraudCheckStatus_PriceAbove_2000 = "No";

			sf.seleU.scrollByCoOrdinates(1);
			InputData_Communities.monthylyTotal_num = Double.parseDouble(InputData_Communities.commPBFProductPrice) + Double.parseDouble(InputData_Communities.commPBFTVProductPrice)+ 
					(Double.parseDouble(InputData_Communities.commPBFOffAddOnPrice) * InputData_Communities.commPBFOffAddOnQty) + (+ Double.parseDouble(InputData_Communities.commPBFTVAddOnPrice) * InputData_Communities.commPBFTVAddOnQty);

			String text = sf.seleU.getTextFromWebElement(sf.planSelPBF.monthlyTotal);
			if (text.contains(" ")) {
				text = text.replaceAll(" .*", "");
			}
			actualValue_num = Double.parseDouble(text.substring(1, text.length()).replaceAll(",", ""));
			expectedMonthlyTotal = AdditionalUtilities.roundOffDouble2DecimalPlaces(InputData_Communities.monthylyTotal_num);
			// cutoff value for fraud check
			fraudMonthlyCutoff_Price500 = AdditionalUtilities.roundOffDouble2DecimalPlaces(500.00);
			fraudMonthlyCutoff_Price2000 = AdditionalUtilities.roundOffDouble2DecimalPlaces(2000.00);

			if(expectedMonthlyTotal>fraudMonthlyCutoff_Price500 && expectedMonthlyTotal < fraudMonthlyCutoff_Price2000) {
				if (actualValue_num > fraudMonthlyCutoff_Price500) {
					reportStatusPass(methodName + " Should go for fraud check " + " as  " + actualValue_num + " is greater then "+
							fraudMonthlyCutoff_Price500,true, true);
					sf.dataInput.fraudCheckStatus = "Yes";
				} 				
			} else if (expectedMonthlyTotal > fraudMonthlyCutoff_Price2000) {
				if (actualValue_num > fraudMonthlyCutoff_Price2000) {
					reportStatusPass(methodName + " Should go for fraud check " + " as  " + actualValue_num + " is greater then "+
							fraudMonthlyCutoff_Price2000,true, true);
					sf.dataInput.fraudCheckStatus_PriceAbove_2000 = "Yes";
				} 
			}			
			else {
				reportStatusPass(methodName + " didn't go for fraud check as expected monthly total is less then fraud cutoff", false,true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field value verification", e);
			e.printStackTrace();
		}
	}

	/**Pankaj Agarwal 07 March 2022
	 * @throws IOException
	 * 
	 *   i.  Verify monthly total is > 500 < 2000 then it will go for fraud check HOLD 2X, so set the flag according to it
	 *   ii.  Verify monthly total is > 2000 then it will go for fraud check HOLD Infinity, so set the flag according to it
	 */
	public static void verifyMonthlyTotal_ForMultisiteOrders() throws IOException {
		try {

			double actualMonthlyValue_Num;
			double actualOneTimeTCV_Num;
			double fraudMonthlyCutoff_Price500;
			double fraudMonthlyCutoff_Price2000;
		
			sf.dataInput.fraudCheckStatus = "No";
			sf.dataInput.fraudCheckStatus_PriceAbove_2000 = "No";

			sf.seleU.scrollByCoOrdinates(1);

			String monthly_RecurningCharge = sf.seleU.getTextFromWebElement(sf.planSelPBF.monthlyTotalMultisite.get(0));
			String oneTime_TCV = sf.seleU.getTextFromWebElement(sf.planSelPBF.multisite_OneTimeTCV.get(0));
			if (monthly_RecurningCharge.contains(" ")) {
				monthly_RecurningCharge = monthly_RecurningCharge.replaceAll(" .*", "");
			}
			
			if (oneTime_TCV.contains(" ")) {
				oneTime_TCV = monthly_RecurningCharge.replaceAll(" .*", "");
			}
			actualMonthlyValue_Num = Double.parseDouble(monthly_RecurningCharge.substring(1, monthly_RecurningCharge.length()).replaceAll(",", ""));
			actualOneTimeTCV_Num = Double.parseDouble(oneTime_TCV.substring(1, oneTime_TCV.length()).replaceAll(",", ""));
			
			
			// cutoff value for fraud check
			fraudMonthlyCutoff_Price500 = AdditionalUtilities.roundOffDouble2DecimalPlaces(500.00);
			fraudMonthlyCutoff_Price2000 = AdditionalUtilities.roundOffDouble2DecimalPlaces(2000.00);

			if ((actualMonthlyValue_Num +  actualOneTimeTCV_Num) > fraudMonthlyCutoff_Price500) {
				reportStatusPass(methodName + " Should go for fraud check " + " as  " + actualMonthlyValue_Num + " is greater then "+
						fraudMonthlyCutoff_Price500,true, true);
				sf.dataInput.fraudCheckStatus = "Yes";
			} 				

			else if ((actualMonthlyValue_Num +  actualOneTimeTCV_Num) > fraudMonthlyCutoff_Price2000) {
				reportStatusPass(methodName + " Should go for fraud check " + " as  " + actualMonthlyValue_Num + " is greater then "+
						fraudMonthlyCutoff_Price2000,true, true);
				sf.dataInput.fraudCheckStatus_PriceAbove_2000 = "Yes";
			} 

			else {
				reportStatusPass(methodName + " didn't go for fraud check as expected monthly total is less then fraud cutoff", false,true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field value verification for Multisite FraudCheck", e);
			e.printStackTrace();
		}
	}
	/**
	 * @param fieldName
	 * @param ele
	 * @param expectedValue
	 * @throws IOException
	 * 
	 *                     Extract and check if monthly total is > 500
	 */
	public static void checkMonthlyTotalForFraudReview() throws IOException {
		try {

			double actualValue_num;
			double fraudMonthlyCutoff;
			double expectedMonthlyTotal;

			sf.seleU.scrollByCoOrdinates(1);

			String text = sf.seleU.getTextFromWebElement(sf.planSelPBF.monthlyTotal);
			if (text.contains(" ")) {
				text = text.replaceAll(" .*", "");
			}
			actualValue_num = Double.parseDouble(text.substring(1, text.length()).replaceAll(",", ""));

			// cutoff value for fraud check
			fraudMonthlyCutoff = AdditionalUtilities.roundOffDouble2DecimalPlaces(500.00);

			if(actualValue_num>=fraudMonthlyCutoff) {
				InputData_Communities.mulPBFFraudCheckRequired = "Yes";
				InputData_Communities.commPBFFraudCheckRequired = "Yes";
				reportStatusPass(methodName + " Should go for fraud check " + " as  " + actualValue_num + " is greater then "+
						fraudMonthlyCutoff,true, true);
			} else {
				InputData_Communities.mulPBFFraudCheckRequired = "No";
				InputData_Communities.commPBFFraudCheckRequired = "No";
				reportStatusPass(methodName + " didn't go for fraud check as expected monthly total is less then fraud cutoff", false,true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in checking monthly total is greater than cutoff or not", e);
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
	 *                     Verify Field is displayed
	 */
	public static void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
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
	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is disabled
	 */
	public void verifyFieldDisabled(String fieldName, WebElement element) throws IOException {
		try {

			if (element.getAttribute("class").contains("disabled")) {
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

	public void verifyFieldEnabled(WebElement element) throws IOException {
		try {
			sf.seleU.ScrolltoElementPageCenter(element);
			if (sf.seleU.isElementEnabled(element)) {
				reportStatusPass(methodName + " Validated field is enabled ", true,	true);
			} else {
				reportStatusFail(
						methodName + " Validated field is disabled ", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error occur while validating the Field ", e);
			e.printStackTrace();
		}
	}

	public void verifyFieldDisabled(WebElement element) throws IOException {
		try {
			sf.seleU.ScrolltoElementPageCenter(element);
			if (!sf.seleU.isElementEnabled(element)) {
				reportStatusPass(methodName + " Validated field is disabled ", true,	true);
			} else {
				reportStatusFail(
						methodName + " Validated field is enabled ", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error occur while validating the Field ", e);
			e.printStackTrace();
		}
	}
	/**
	 * @param fieldName
	 * @param ele
	 * @param expectedValue
	 * @throws Exception
	 * 
	 *                   Validate Price Field
	 */
	public void validatePrice(String fieldName, WebElement ele, String expectedValue) throws Exception {

		String actualValue = sf.seleU.getTextFromWebElement(ele);
		if (expectedValue.contains(actualValue)) {
			reportStatusPass(" Validated product " + fieldName + " as  " + expectedValue, true, true);
		} else {
			reportStatusFail(" Expected value for product " + fieldName + " is " + expectedValue
					+ " Actual value for Product " + fieldName + " is " + actualValue, true);
		}
	}

}

