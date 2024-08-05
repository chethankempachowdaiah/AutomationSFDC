package sfdc.pages.qm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla, Date: 3 Nov 2021
 *
 *         SFDC Persona Based Buy Flow Page - Office365
 */
public class SFDC_PBF_Off365AddOn_Selection_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_PBF_Off365AddOn_Selection_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_PBF_Off365AddOnSelection@:";
	}
	
	
	/**
	 * 
	 * Validate Name of Plans/Products
	 * 
	 * @throws IOException
	 */
	public void validateOff365AddOnsProductNames() throws IOException {

		try {
			sf.seleU.wait(5000);
			List<String> expectedBusinessProducts = InputData_Communities.commPBFOff365AdOnsList;
			List<String> actualBusinessProducts = new ArrayList<String>();

			// Loop over all business plan names to be verified
			for (int i = 0; i < sf.offAddOnsPBF.offAddOnsProductNames.size(); i++) {
				actualBusinessProducts.add(sf.seleU.getTextFromWebElement( sf.offAddOnsPBF.offAddOnsProductNames.get(i)));
			}
			reportStatusPass(methodName + " Extracted Office 365 AddOns names to compare with expected list", false, false);

			// sort lists for comparison
			Collections.sort(expectedBusinessProducts);
			Collections.sort(actualBusinessProducts);

			// Verify expectedBusiness plan list is equal to actualBusiness Plans List

			if (expectedBusinessProducts.equals(actualBusinessProducts)) {
				reportStatusPass(methodName + " All expected Office 365 AddOns are present on PRoduct Selection Page: "
						+ AdditionalUtilities.getAsString(actualBusinessProducts), true, true);
			} else {
				reportStatusFail(methodName
						+ " All expected Office 365 AddOns are not present on Product Selection Page: :: Expected Plans--> "
						+ AdditionalUtilities.getAsString(expectedBusinessProducts) + "  Actual Plans--> "
						+ AdditionalUtilities.getAsString(actualBusinessProducts), true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Office 365 AddOns Names", e);
			e.printStackTrace();
		}

	}
	
	
	/**
	 * Validate the Product descriptions across each speed is displayed accurately
	 * 
	 * @throws IOException
	 */
	public void validateOff365ProductsDescription() throws IOException {

		try {
			if (sf.dataInput.env.equals("ITDEVSTAGE")) {
				for (int j=0; j< sf.offAddOnsPBF.productDetailsAddOns.size(); j++)
					reportStatusPass(methodName +  " " + (j+1) +": " +
							sf.seleU.getTextFromWebElement(sf.offAddOnsPBF.productDetailsAddOns.get(j)), true, true);
				
			} else {
				for (int i=0; i< InputData_Communities.commPBFOff365AdOnsList.size(); i++) {
					sf.seleU.clickElementByJSE(sf.offAddOnsPBF.viewMoreDetailsButton.get(i));
					sf.seleU.wait(2000);
					reportStatusPass(methodName + " Product Details for Product: " + (i+1), true, true);
					for (int j=0; j< sf.offAddOnsPBF.productDetailsAddOns.size(); j++)
						reportStatusPass(methodName +  " " + (j+1) +": " +
								sf.seleU.getTextFromWebElement(sf.offAddOnsPBF.productDetailsAddOns.get(j)), true, true);
					sf.seleU.clickElementByJSE(sf.offAddOnsPBF.viewMoreDetailsButton.get(i));
					sf.seleU.wait(2000);
				}
			}
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Off 365 descriptions", e);
			e.printStackTrace();
		}

	}
	
	
	
	/**
	 * 			
	 * Validate Discount and promo section for all product types
	 * 
	 * @throws IOException
	 */
	public void validateDiscountAndPromoSectionsForOff365AddOns() throws IOException {

		try {
			if (!sf.dataInput.env.equals("ITDEVSTAGE")) {
				
				sf.seleU.wait(5000);
				verifyFieldDisabled("Promo and Discount Section", sf.planSelPBF.discountAndPromosButtonForRDI.get(0));
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Discount and Promo section", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * Click on Add to Cart for Product
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
	public void addToCartOff365AddOnProductAndProceed() throws IOException {

		try {
			sf.seleU.wait(6000);
			
			// Add to Cart the selected product
			for (int i = 0; i < sf.offAddOnsPBF.offAddOnsProductNames.size(); i++) {
				String planTile = sf.seleU.getTextFromWebElement(sf.offAddOnsPBF.offAddOnsProductNames.get(i));
				if (InputData_Communities.commPBFOffAddOnName.contains(planTile)) {
					for(int j=1; j< InputData_Communities.commPBFOffAddOnQty;j++) {
						sf.seleU.clickElementByJSE(sf.offAddOnsPBF.increementAddOnButton.get(i));
						//sf.seleU.wait(500);
					}
					sf.seleU.wait(5000);				
					sf.seleU.clickElementByJSE(sf.comPBFCablePlan.addToCartAllButtons.get(i));
					reportStatusPass(methodName + " Added AddOn to Cart ", true, true);
					sf.seleU.wait(10000);

					// Validate Price of Product
					if(InputData_Communities.commPBFOffAddOnPrice!="0")
					validatePrice(" Plan/Product Price", sf.gbjCart.price.get(i),
							String.valueOf(Double.parseDouble(InputData_Communities.commPBFOffAddOnPrice) * InputData_Communities.commPBFOffAddOnQty));

					// Validate Remove from Cart is displayed
//					if(sf.dataInput.env.equals("ITDEVSTAGE"))
//						verifyFieldDisplayed(" Remove From Cart Button", sf.offAddOnsPBF.addedToCartButton);
//					else
						verifyFieldDisplayed(" Remove From Cart Button", sf.gbj.removeFromCartAllButtons.get(0));
					break;
				}

			}

			sf.seleU.wait(15000);

			// Proceed to Checkout
			if (sf.seleU.isElementDisplayed(sf.comPBFCablePlan.proceedToShoppingCartButton)) {
				sf.seleU.clickElementByJSE(sf.comPBFCablePlan.proceedToShoppingCartButton);
				sf.seleU.wait(5000);
				reportStatusPass(methodName + " Clicked on Proceed to Shopping Cart Button", true, true);
			}
		} catch (

		Throwable e) {
			reportStatusFail(methodName + " Error in Adding Office 365 AddOn to Cart", e);
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
