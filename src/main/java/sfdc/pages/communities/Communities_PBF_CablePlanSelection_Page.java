package sfdc.pages.communities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla, Date:07/07/2021
 *
 *         Communities Persona Based Buy Flow Page - Cable Plan Selection
 */
public class Communities_PBF_CablePlanSelection_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public Communities_PBF_CablePlanSelection_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "Communities_PBF_CablePlanSelection@:";
	}

	/**
	 * 
	 * Validate Name of Plans/Products
	 * 
	 * @throws IOException
	 */
	public void validateBusinessProductsNames() throws IOException {

		try {
			sf.seleU.wait(5000);
			List<String> expectedBusinessProducts = InputData_Communities.commPBFBusinessProducts;
			List<String> expectedBusinessProductsFr = InputData_Communities.commPBFBusinessProductsFr;
			List<String> actualBusinessProducts = new ArrayList<String>();

			// Loop over all business plan names to be verified
			for (int i = 0; i < sf.comPBFCablePlan.plansTitleHeader.size(); i++) {
				actualBusinessProducts.add(sf.seleU.getTextFromWebElement(sf.comPBFCablePlan.plansTitleHeader.get(i)));
			}
			reportStatusPass(methodName + " Extracted Business Plan names to compare with expected list", false, false);

			// sort lists for comparison
			Collections.sort(expectedBusinessProducts);
			Collections.sort(actualBusinessProducts);

			// Verify expectedBusiness plan list is equal to actualBusiness Plans List
			if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
				
				if (expectedBusinessProducts.equals(actualBusinessProducts)) {
					reportStatusPass(methodName + " All expected Business Plans are present on Cable Selection Page: "
							+ AdditionalUtilities.getAsString(actualBusinessProducts), true, true);
				} else {
					reportStatusFail(methodName
							+ " All expected Business Plans are not present on Cable Selection Page: :: Expected Plans--> "
							+ AdditionalUtilities.getAsString(expectedBusinessProducts) + "  Actual Plans--> "
							+ AdditionalUtilities.getAsString(actualBusinessProducts), true);
				}
			} else {
				Collections.sort(expectedBusinessProductsFr);
				if (expectedBusinessProductsFr.equals(actualBusinessProducts)) {
					reportStatusPass(methodName + " All expected Business Plans are present on Cable Selection Page: "
							+ AdditionalUtilities.getAsString(actualBusinessProducts), true, true);
				} else {
					reportStatusFail(methodName
							+ " All expected Business Plans are not present on Cable Selection Page: :: Expected Plans--> "
							+ AdditionalUtilities.getAsString(expectedBusinessProductsFr) + "  Actual Plans--> "
							+ AdditionalUtilities.getAsString(actualBusinessProducts), true);
				}
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Business Products Names", e);
			e.printStackTrace();
		}

	}

	/**
	 * Validate Speed tiles are displayed on decreasing order of speed.
	 * 
	 * @throws IOException
	 */
	public void validateOrderOfSpeedOfPlans() throws IOException {

		try {

			sf.seleU.wait(5000);
			List<String> expectedPlanSpeeds = InputData_Communities.commPBFProductSpeeds;
			List<String> actualPlanSpeeds = new ArrayList<String>();

			// Loop over all sppeds to be verified
			for (int i = 0; i < sf.comPBFCablePlan.downloadSpeeds.size(); i++) {
				String speed = sf.seleU.getTextFromWebElement(sf.comPBFCablePlan.downloadSpeeds.get(i));
				speed = speed.replace("Up to ", "").replace("jusqu’à ", "");
				actualPlanSpeeds.add(speed);
			}
			reportStatusPass(methodName + " Extracted Business Plan names to compare with expected list", false, false);

			// Verify expectedspped order list is equal to actualSpeed Order List
			if (expectedPlanSpeeds.equals(actualPlanSpeeds)) {
				reportStatusPass(methodName + " Speeds are Present in decreasing order as expected: "
						+ AdditionalUtilities.getAsString(actualPlanSpeeds), true, true);
			} else {
				reportStatusFail(methodName + " Speeds are not present in Decreasing order: :: Expected Order--> "
						+ AdditionalUtilities.getAsString(expectedPlanSpeeds) + "  Actual Order--> "
						+ AdditionalUtilities.getAsString(actualPlanSpeeds), true);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating order of speed is decreasing order", e);
			e.printStackTrace();
		}

	}

	/**
	 * Validate speed column, product/promotions and pricing column are correctly
	 * aligned
	 * 
	 * @throws IOException
	 */
	public void validateCablePlanSelectionPageLayout() throws IOException {

		try {

			for (int i = 1; i <= InputData_Communities.commPBFBusinessProducts.size(); i++) {

				verifyFieldDisplayed("Speed Plans Column/Tile for Product " + i,
						sf.comPBFCablePlan.packageSpeedDiv.get(i - 1));
				verifyFieldDisplayed("Product Details Column/Tile for Product " + i,
						sf.comPBFCablePlan.packageFeaturesDiv.get(i - 1));
				verifyFieldDisplayed("Pricing Column/Tile for Product " + i,
						sf.comPBFCablePlan.packagePricingDiv.get(i - 1));

			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating cable plan selection page layout", e);
			e.printStackTrace();
		}

	}

	/**
	 * Validate Prices displayed across each products are for 3 Year Terms.
	 * 
	 * @throws IOException
	 */
	public void validateProductsAre3YearTerm() throws IOException {

		try {		
			if (sf.comPBFCablePlan.threeYearTermText.size() ==  InputData_Communities.commPBFBusinessProducts.size() ||
					sf.comPBFCablePlan.monthlyTermText.size() ==  InputData_Communities.commPBFBusinessProducts.size()) {
				reportStatusPass(methodName +
						" Validated All products are for 3-Year/Monthly term", true, true);
			} else {
				reportStatusFail(methodName +
						" All Products are not for 3-year/Monthly term", true);
			}
		} 
		catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Products term as 3 -Years/Monthly", e);
			e.printStackTrace();
		} 

	}

	/**
	 * Validate the Product descriptions across each speed is displayed accurately
	 * 
	 * @throws IOException
	 */
	public void validateProductsDescription() throws IOException {

		try {
			if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
				verifyFieldValue("Description for Gigabit Plan", sf.comPBFCablePlan.shortDescGigaBitPack,
						InputData_Communities.commPBFGigaBitPlanDesc);
				verifyFieldValue("Description for 500u Plan", sf.comPBFCablePlan.shortDesc500UPack,
						InputData_Communities.commPBF500uPlanDesc);
				verifyFieldValue("Description for 150u Plan", sf.comPBFCablePlan.shortDesc150UPack,
						InputData_Communities.commPBF150uPlanDesc);
				verifyFieldValue("Description for 30u Plan", sf.comPBFCablePlan.shortDesc30UPack,
						InputData_Communities.commPBF30uPlanDesc);
			} else {
				verifyFieldValue("Description for Gigabit Plan", sf.comPBFCablePlan.shortDescGigaBitPack,
						InputData_Communities.commPBFGigaBitPlanDescFr);
				verifyFieldValue("Description for 500u Plan", sf.comPBFCablePlan.shortDesc500UPack,
						InputData_Communities.commPBF500uPlanDescFr);
				verifyFieldValue("Description for 150u Plan", sf.comPBFCablePlan.shortDesc150UPack,
						InputData_Communities.commPBF150uPlanDescFr);
				verifyFieldValue("Description for 30u Plan", sf.comPBFCablePlan.shortDesc30UPack,
						InputData_Communities.commPBF30uPlanDescFr);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating cable plans descriptions", e);
			e.printStackTrace();
		}

	}

	/**
	 * Validate clicking on the tooltip displays the correct description of each
	 * internet products
	 * 
	 * @throws IOException
	 */
	public void validateProductsToolTipTexts() throws IOException {

		try {
			// Validate ToolTip for Advantage Wifi
			for (int i = 0; i < sf.comPBFCablePlan.toolTipIconForAdvantageWiFi.size(); i++) {
				sf.seleU.ScrolltoElement(sf.comPBFCablePlan.toolTipIconForAdvantageWiFi.get(i));
				sf.seleU.clickElementByJSE(sf.comPBFCablePlan.toolTipIconForAdvantageWiFi.get(i));
				sf.seleU.wait(2000);
				if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
					verifyFieldValue("ToolTip Text for Advantage Wifi, Product " + (i + 1),
							sf.comPBFCablePlan.activeToolTipText, InputData_Communities.commPBFadvantageWiFiToolTip);
				} else  {
					verifyFieldValue("ToolTip Text for Advantage Wifi, Product " + (i + 1),
							sf.comPBFCablePlan.activeToolTipText, InputData_Communities.commPBFadvantageWiFiToolTipFr);
				}
				sf.seleU.clickElementByJSE(sf.comPBFCablePlan.closeToolTipIcon);
			}

			// Validate ToolTip for Advantage Wifi LTE Backup
			for (int i = 0; i < sf.comPBFCablePlan.toolTipIconForAdvantageWiFiLTEbackup.size(); i++) {
				sf.seleU.ScrolltoElement(sf.comPBFCablePlan.toolTipIconForAdvantageWiFiLTEbackup.get(i));
				sf.seleU.clickElementByJSE(sf.comPBFCablePlan.toolTipIconForAdvantageWiFiLTEbackup.get(i));
				sf.seleU.wait(2000);
				if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
					verifyFieldValue("ToolTip Text for Advantage Wifi LTE Backup, Product " + (i + 1),
							sf.comPBFCablePlan.activeToolTipText,
							InputData_Communities.commPBFadvantageWiFiLTEBackupToolTip);
				} else {
					verifyFieldValue("ToolTip Text for Advantage Wifi LTE Backup, Product " + (i + 1),
							sf.comPBFCablePlan.activeToolTipText,
							InputData_Communities.commPBFadvantageWiFiLTEBackupToolTipFr);
				}
				sf.seleU.clickElementByJSE(sf.comPBFCablePlan.closeToolTipIcon);
			}

			// Validate ToolTip for Business Internet
			for (int i = 0; i < sf.comPBFCablePlan.toolTipIconForBusinessInternet.size(); i++) {
				sf.seleU.ScrolltoElement(sf.comPBFCablePlan.toolTipIconForBusinessInternet.get(i));
				sf.seleU.clickElementByJSE(sf.comPBFCablePlan.toolTipIconForBusinessInternet.get(i));
				sf.seleU.wait(2000);
				if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
					verifyFieldValue("ToolTip Text for Business Internet, Product " + (i + 1),
							sf.comPBFCablePlan.activeToolTipText, InputData_Communities.commPBFbusinessInternetToolTip);
				} else {
					verifyFieldValue("ToolTip Text for Business Internet, Product " + (i + 1),
							sf.comPBFCablePlan.activeToolTipText, InputData_Communities.commPBFbusinessInternetToolTipFr);
				}
				sf.seleU.clickElementByJSE(sf.comPBFCablePlan.closeToolTipIcon);
			}

			// Validate ToolTip for Business Internet LTE Backup
			for (int i = 0; i < sf.comPBFCablePlan.toolTipIconForBusinessInternetLTEbackup.size(); i++) {
				sf.seleU.ScrolltoElement(sf.comPBFCablePlan.toolTipIconForBusinessInternetLTEbackup.get(i));
				sf.seleU.clickElementByJSE(sf.comPBFCablePlan.toolTipIconForBusinessInternetLTEbackup.get(i));
				sf.seleU.wait(2000);
				if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
					verifyFieldValue("ToolTip Text for Business INternet LTE Backup, Product " + (i + 1),
							sf.comPBFCablePlan.activeToolTipText,
							InputData_Communities.commPBFbusinessInternetLTEBackupToolTip);
				} else {
					verifyFieldValue("ToolTip Text for Business INternet LTE Backup, Product " + (i + 1),
							sf.comPBFCablePlan.activeToolTipText,
							InputData_Communities.commPBFbusinessInternetLTEBackupToolTipFr);
				}
				sf.seleU.clickElementByJSE(sf.comPBFCablePlan.closeToolTipIcon);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Products Tool Tip Texts", e);
			e.printStackTrace();
		}

	}

	/**
	 * Validate All Plans Include section displays the correct plans offered.
	 * 
	 * @throws IOException
	 */
	public void validateAllPlansOffers() throws IOException {

		try {

			List<String> expectedOffers = InputData_Communities.commPBFAllPlansOffers;
			List<String> actualOffers = new ArrayList<String>();

			// Loop over all business plan offers to be verified
			for (int i = 0; i < sf.comPBFCablePlan.allPlansIncludeList.size(); i++) {
				actualOffers.add(sf.seleU.getTextFromWebElement(sf.comPBFCablePlan.allPlansIncludeList.get(i)));
			}
			reportStatusPass(methodName + " Extracted Business Plan Offers to compare with expected list", false,
					false);

			// sort lists for comparison
			Collections.sort(expectedOffers);
			Collections.sort(actualOffers);

			// Verify expectedBusiness plan offers list is equal to actualBusiness Plans
			// List

			if (expectedOffers.equals(actualOffers)) {
				reportStatusPass(
						methodName + " All expected Business Plans Offers are present on Cable Selection Page: "
								+ AdditionalUtilities.getAsString(actualOffers),
								true, true);
			} else {
				reportStatusFail(methodName
						+ " All expected Business Plans Offers are not present on Cable Selection Page: :: Expected Plans--> "
						+ AdditionalUtilities.getAsString(expectedOffers) + "  Actual Plans--> "
						+ AdditionalUtilities.getAsString(actualOffers), true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating All Plan Offers", e);
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
	 * Validate a Teal Translucent Bar is displayed for the user 
	 * 
	 * @throws IOException
	 */
	public void addToCartInternetProduct() throws IOException {

		try {
			sf.seleU.wait(10000);
			// Select Product mentioned in data
			if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
				sf.seleU.clickElementByJSE(driver.findElement(
						By.xpath("//*[text()='" + InputData_Communities.commPBFAddProductName + "']")));
				reportStatusPass(methodName + " Selected Product  " + InputData_Communities.commPBFAddProductName, true,
						true);
			}

			// Add to Cart the selected product

			for (int i = 0; i < sf.comPBFCablePlan.plansTitleHeader.size(); i++) {
				String planTile = sf.seleU.getTextFromWebElement(sf.comPBFCablePlan.plansTitleHeader.get(i));
				if (InputData_Communities.commPBFAddProductName.contains(planTile) || InputData_Communities.commPBFAddProductNameFr.contains(planTile)
						|| (InputData_Communities.commPBFAddProductName.contains("1G") && planTile.contains("Giga"))) {

					sf.seleU.clickElementByJSE(sf.comPBFCablePlan.addToCartAllButtons.get(i));
					reportStatusPass(methodName + " Added Product to Cart ", true, true);
					sf.seleU.wait(15000);

					// Validate Remove from Cart is displayed
					verifyFieldDisplayed(" Remove From Cart Button", sf.gbj.removeFromCartAllButtons.get(0));
					break;
				}

			}

			// Validate speed tile gets highlighted
			verifyFieldDisplayed(" Selected Tile Highlight Div", sf.comPBFCablePlan.selectedDivContainer);

			sf.seleU.wait(7000);

		} catch (

				Throwable e) {
			reportStatusFail(methodName + " Error in Adding Product to Cart", e);
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
	 * Validate a Teal Translucent Bar is displayed for the user 
	 * 
	 * @throws IOException
	 */
	public void addToCartProduct() throws IOException {

		try {

			sf.seleU.wait(10000);
			// Select Product mentioned in data
			if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
				sf.seleU.clickElementByJSE(driver.findElement(
						By.xpath("//*[text()='" + InputData_Communities.commPBFAddProductName+ "']")));
				reportStatusPass(methodName + " Selected Product  " + InputData_Communities.commPBFAddProductName, true,
						true);
			}

			// Validate Product is 3 Year term
			validateProductsAre3YearTerm();

			// Add to Cart the selected product

			for (int i = 0; i < sf.comPBFCablePlan.plansTitleHeader.size(); i++) {
				String planTile = sf.seleU.getTextFromWebElement(sf.comPBFCablePlan.plansTitleHeader.get(i));
				if (InputData_Communities.commPBFAddProductName.contains(planTile) || InputData_Communities.commPBFAddProductNameFr.contains(planTile)
						|| (InputData_Communities.commPBFAddProductName.contains("1G") && planTile.contains("Giga"))) {

					sf.seleU.clickElementByJSE(sf.comPBFCablePlan.addToCartAllButtons.get(i));
					reportStatusPass(methodName + " Added Product to Cart ", true, true);
					sf.seleU.wait(15000);

					// Validate Price of Product
					if(!InputData_Communities.commPBFProductPrice.isEmpty())
						validatePrice(" Plan/Product Price", sf.gbjCart.price.get(i),
								InputData_Communities.commPBFProductPrice);

					// Validate Remove from Cart is displayed
					verifyFieldDisplayed(" Remove From Cart Button", sf.gbj.removeFromCartAllButtons.get(0));
					break;
				}

			}

			// Validate speed tile gets highlighted
			verifyFieldDisplayed(" Selected Tile Highlight Div", sf.comPBFCablePlan.selectedDivContainer);

			sf.seleU.wait(7000);

			// Validate Shop Cart Icon has 1

			if (sf.seleU.isElementPresent(sf.comPBFCablePlan.shoppingCartIconProductCount)
					&& (sf.comPBFCablePlan.shoppingCartIconProductCount.getAttribute("innerHTML").equalsIgnoreCase("1")
							|| sf.comPBFCablePlan.shoppingCartIconProductCount.getAttribute("innerHTML").equalsIgnoreCase("2")))
				reportStatusPass(methodName + " Shopping Cart Icon Product Count is 1 or 2(bundled)", true, false);
			else
				reportStatusFail(methodName + " Exepcted Product Count(1/2) is not present on Shopping cart Icon", true);

		} catch (

				Throwable e) {
			reportStatusFail(methodName + " Error in Adding Product to Cart", e);
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
	 */
	public void addToCartProduct_Quickly() throws IOException {

		try {

			sf.seleU.wait(5000);

			//*** Loop over all business plan names to be verified
			for (int i = 0; i < sf.comPBFCablePlan.plansTitleHeader.size(); i++) {
				// first validate the product speed type 
				if(sf.seleU.getTextFromWebElement(sf.comPBFCablePlan.plansTitleHeader.get(i)).equalsIgnoreCase(InputData_Communities.commPBFProductSpeedType)) {
					sf.seleU.ScrolltoElementPageCenter(sf.comPBFCablePlan.plansTitleHeader.get(i));
					sf.seleU.clickElementByJSE(sf.comPBFCablePlan.addToCartAllButtons.get(i));				
					sf.seleU.wait(15000);
					reportStatusPass(methodName + InputData_Communities.commPBFAddProductName + " is added", true,true);
					// Validate Remove from Cart is displayed
					verifyFieldDisplayed(" Remove From Cart Button", sf.gbj.removeFromCartAllButtons.get(0));

					// Validate Price of Product
					if(sf.dataInput.env.equalsIgnoreCase("ITDEVSTAGE"))
						validatePrice(" Plan/Product Price", sf.gbjCart.price.get(i),
								InputData_Communities.commPBFProductPrice);


					break;

				}
			}

		} catch (

				Throwable e) {
			reportStatusFail(methodName + " Error in Adding Product to Cart", e);
			e.printStackTrace();
		}

	}



	/**
	 * Proceed to
	 * Shopping Cart CTA which when clicked should land user to the Shopping Cart
	 * Page.
	 * 
	 * @throws IOException
	 */
	public void proceedtoCheckout() throws IOException {

		try {

			// Proceed to Checkout
			sf.seleU.clickElementByJSE(sf.comPBFCablePlan.proceedToShoppingCartButton);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Clicked on Proceed to Shopping Cart Button", true, true);

		} catch (

				Throwable e) {
			reportStatusFail(methodName + " Error in Proceeding to Shopping Cart", e);
			e.printStackTrace();
		}

	}

	/**
	 * Click on Next on Business INternet page to move to AddOn Page
	 * 
	 * @throws IOException
	 */
	public void clickNextOnBusInt() throws IOException {

		try {

			sf.seleU.clickElementByJSE(sf.planSelPBF.nextButtonForInternet);
			sf.seleU.wait(2000);
			reportStatusPass(methodName + " Clicked on Next for Office 365 AddOns/TV AddOn", true, true);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in moving to Off 365 page", e);
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
