package sfdc.pages.wireless;

import java.io.IOException;

import org.junit.Assert;

import com.framework.base.MyListener;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WACC_AccessoriesDetails_Page extends MyListener {
	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public static int quntityCount;
	public static SFDC_AllPages sfdc;

	public WACC_AccessoriesDetails_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * Method developed on @Date: 15.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To click Add to Cart button on the Accessories Details page
	 * 
	 */

	public void clicknAddToCartBtnAccDetailsPage() throws Exception {
		try {
			methodName = "Accessories Details page@: ";
			sf.seleU.waitForLoading();
			// Assert.assertEquals(true,
			// sf.seleU.isElementDisplayed(sf.accessoryDetailObj.addToCart));
			if (sf.seleU.isElementDisplayed(sf.accessoryDetailObj.addToCart) && sf.seleU.isElementEnabled(sf.accessoryDetailObj.addToCart)) {
				sf.seleU.wait(1000);
				sf.seleU.ScrolltoElement(sf.accessoryDetailObj.addToCart);
				sf.seleU.clickElementByJSE(sf.accessoryDetailObj.addToCart);
				sf.seleU.waitForLoading();
				reportStatusPass(methodName + "Accessories is added ", true, true);
			}
			else {
				reportStatusFail("Add To Cart button is not displayed/enabled. ", true);
			}
		} catch (Throwable e) {
			reportStatusFail("Error on clicking Add To Cart button. ", e);
			e.printStackTrace();
		}

	}
	/**
	 * Method developed on @Date: 15.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To enter Quantity on the Accessories Details page
	 * 
	 */

	public void enterQuantityAccDetailsPage(String quantity) throws IOException {
		try {
			methodName = "Shop for accessories page@: ";
			sf.seleU.clearText(sf.accessoryDetailObj.inputQuantity);
			sf.seleU.enterText(sf.accessoryDetailObj.inputQuantity, quantity);
			sf.seleU.clickEnter();
			sf.seleU.wait(2000);
			reportStatusPass(methodName + "Quantity has entered ", true, true);
		} catch (Throwable e) {
			reportStatusFail("Error on enter Quantity on Accessories Details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 15.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate Quantity on the Accessories Details page
	 * 
	 */
	public void validateQuantityOnAccDetailsPage(String quantity) throws IOException {
		try {
			methodName = "Accessories Details page@: ";
			Assert.assertEquals("1", sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.inputQuantity));
			sf.seleU.wait(2000);
			enterQuantityAccDetailsPage(quantity);
			// Validate Add To cart button is enabled
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + "Add to Cart button is enabled",sf.accessoryDetailObj.addToCart.isEnabled(), true);
			Assert.assertTrue(sf.accessoryDetailObj.addToCart.isEnabled());
			// Increase the quantity by clicking the Plus sign
			clickOnIncreaseOrDecreaseQuantity("+");
			quntityCount = Integer.parseInt(sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.inputQuantity));
			Assert.assertEquals(Integer.parseInt(quantity) + 1, quntityCount);

			// Validate Add To cart button is enabled
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + "Add to Cart button is enabled",sf.accessoryDetailObj.addToCart.isEnabled(), true);
			Assert.assertTrue(sf.accessoryDetailObj.addToCart.isEnabled());

			// Derease the quantity by clicking on minus sign
			clickOnIncreaseOrDecreaseQuantity("-");
			quntityCount = Integer.parseInt(sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.inputQuantity));
			Assert.assertEquals(Integer.parseInt(quantity), quntityCount);

			// Validate Add To cart button is enabled
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + "Add to Cart button is enabled",sf.accessoryDetailObj.addToCart.isEnabled(), true);
			Assert.assertTrue(sf.accessoryDetailObj.addToCart.isEnabled());

		} catch (Throwable e) {
			reportStatusFail("Error on increase or decrease Quantity on Accessories Details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 16.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate Quantity Error message on the Accessories Details page
	 * 
	 */

	public void validate_Quantity_ErrorMessage(String message) throws Exception {
		methodName = "Accessories Details page@: ";
		try {
			sf.seleU.ScrolltoElement(sf.accessoryDetailObj.inputQuantity);
			sf.seleU.mouseOverOnElement(sf.accessoryDetailObj.inputQuantity);
			sf.seleU.wait(2000);
			String toolTipText = sf.accessoryDetailObj.toolTipMessage.getText();
			System.out.println(toolTipText);

			if (sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.toolTipMessage).equalsIgnoreCase(message)) {
				sf.seleU.hardwait(2000);
				reportStatusPass(methodName + sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.toolTipMessage),
						true, true);
			}
		} catch (Throwable e) {
			reportStatusFail("ToolTip error message is not present for quantity on Accessories Details page", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 16.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate Add to Cart button on the Accessories Details page
	 * 
	 */
	public void validate_AddToCartButtonDisable() throws Exception {
		methodName = "Accessories Details page@: ";
		try {
			sf.seleU.hardwait(2000);
			sf.seleU.waitForLoading();
			System.out.println(sf.accessoryDetailObj.addToCart.isEnabled());
			System.out.println(sf.accessoryDetailObj.addToCart.isEnabled());
			if (sf.accessoryDetailObj.addToCart.isEnabled()) {
				reportStatusPass(methodName + " Add to Cart button is not enabled",
						sf.accessoryDetailObj.addToCart.isEnabled(), false);
			}
			Assert.assertFalse(sf.accessoryDetailObj.addToCart.isEnabled());

		} catch (Throwable e) {
			reportStatusFail("Add to Cart button is enabled on Accessories Details page", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 18.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate Navigation on the Accessories Details page
	 * 
	 * @throws Exception
	 *
	 */
	public void select_BackToAccessoriesLink() throws Exception {
		methodName = "Shop for Accessories page@: ";
		try {
			sf.seleU.scrollToTop();
			sf.seleU.hardwait(2000);
			sf.seleU.isElementDisplayed(sf.accessoryDetailObj.backToAccessoriesBtn);
			sf.seleU.clickElementByJSE(sf.accessoryDetailObj.backToAccessoriesBtn);
			sf.seleU.hardwait(2000);
			sf.seleU.waitForLoading();
			if (sf.seleU.getPageTitle().equalsIgnoreCase("c:buyFlowPersonaBasedMultiLanguage | Salesforce")) {
				reportStatusPass(methodName, true, true);
			} else {
				reportStatusFail("Shop for Accessories page not displayed.", true);
			}
		} catch (Exception e) {
			reportStatusFail("Error is getting to click on Back to Accessories Link on Accessories Details page", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 24.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate text on accessories Details Page.
	 * 
	 * @throws Exception
	 */
	public void validate_Accessory_Text(String text, String subText) throws Exception {

		methodName = "Accessories Details page@: ";
		sf.seleU.ScrolltoElement(sf.accessoryDetailObj.priceText);

		try {
			sf.seleU.hardwait(2000);
			if (sf.seleU.isElementDisplayed(sf.accessoryDetailObj.priceText)) {
				if(sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.priceText).contains(text) 
						&& sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.taxText).equalsIgnoreCase(subText)) {

				reportStatusPass(methodName + " Total price per single accessory. ",
						sf.seleU.isElementDisplayed(sf.accessoryDetailObj.priceText), true);
				reportStatusPass(methodName + " Before taxes, for all products and services. ",
						sf.seleU.isElementDisplayed(sf.accessoryDetailObj.taxText), true);

			} else {
				reportStatusFail("Total Price text and sub Text has not matched on Accessories Details page", true);
			}
			}
				else {
					reportStatusFail("Total Price text is not displayed on Accessories Details page", true);
			}
		} catch (Exception e) {
			reportStatusFail("Error in text on Accessories Details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 24.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate default value on Accessories details Page.
	 * 
	 * @throws Exception
	 */
	public void validate_default_Value_onAccessoryDetailsPage() throws Exception {
		methodName = "Accessories Details page@: ";
		try {
			sf.seleU.hardwait(2000);
			sf.seleU.waitTillLoading();
			if (sf.seleU.isElementSelected(sf.accessoryDetailObj.accessoriesColorList.get(0))
					&& sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.inputQuantity).equals("1")
					&& sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.colorName).equals("Black")) {

				reportStatusPass(methodName + "Default color: "
						+ sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.colorName) + "Default Value is 1", true,
						true);
			}
		} catch (Exception e) {
			reportStatusFail("Error in default value on Accessories Details page", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 24.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate default value on Accessories details Page.
	 * 
	 * @throws Exception
	 */
	public void select_color_onAccessoryDetailsPage() throws Exception {
		methodName = "Accessories Details page@: ";
		try {
			sf.seleU.hardwait(2000);
			if (sf.accessoryDetailObj.accessoriesColorList.size() > 1) {
				sf.seleU.clickElementByJSE(sf.accessoryDetailObj.accessoriesColorList.get(1));
				sf.seleU.hardwait(2000);
				reportStatusPass(methodName + " new Accessory Color has selected. ",
						sf.seleU.isElementSelected(sf.accessoryDetailObj.accessoriesColorList.get(1)), true);

			} else {
				reportStatusPass(methodName + " Accessory Color has not present. ", true, true);
			}

		} catch (Exception e) {
			reportStatusFail("Error in select color on Accessories Details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 24.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To increase or Decrease Quantity on Accessories details Page.(value must be
	 * "+" or "-")
	 * 
	 * @throws Exception
	 */
	public void clickOnIncreaseOrDecreaseQuantity(String text) throws Exception {
		methodName = "Accessories Details page@: ";
		try {
			sf.seleU.hardwait(2000);
			sf.seleU.ScrolltoElement(sf.accessoryDetailObj.quantityPlus);
			if (text.equals("+")) {
				sf.seleU.clickElementByJSE(sf.accessoryDetailObj.quantityPlus);
				sf.seleU.hardwait(2000);
				reportStatusPass(methodName + " quantity increased has clicked. ", true, true);
			} else if (text.equals("-")) {
				sf.seleU.clickElementByJSE(sf.accessoryDetailObj.quantityMinus);
				sf.seleU.hardwait(2000);
				reportStatusPass(methodName + " quantity increased has clicked. ", true, true);
			}

		} catch (Exception e) {
			reportStatusFail("Error in select color on Accessories Details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 24.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate brand name on Accessories details Page.
	 * 
	 * @throws Exception
	 */
	public void validate_BrandName_onAccessoryDetailsPage(String brand) throws Exception {
		methodName = "Accessories Details page@: ";
		try {
			sf.seleU.hardwait(2000);
			sf.seleU.waitForLoading();
			if (sf.seleU.isElementDisplayed(sf.accessoryDetailObj.brandNameAccDetails) && sf.seleU
					.getTextFromWebElement(sf.accessoryDetailObj.brandNameAccDetails).equalsIgnoreCase(brand)) {

				reportStatusPass(
						methodName + "Brand Name is displayed: "
								+ sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.brandNameAccDetails),
						true, true);
			} else {
				reportStatusFail("Brand name is not displayed on Accessories Details page. ", true);
			}
		} catch (Exception e) {
			reportStatusFail("Error in brand name on Accessories Details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 24.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate brand name on Accessories details Page.
	 * 
	 * @throws Exception
	 */
	public void validate_AccessoryName_onAccessoryDetailsPage(String accessory) throws Exception {
		methodName = "Accessories Details page@: ";
		try {
			sf.seleU.hardwait(2000);
			sf.seleU.waitTillLoading();
			if (sf.seleU.isElementDisplayed(sf.accessoryDetailObj.accessoryNameAccDetails) && sf.seleU
					.getTextFromWebElement(sf.accessoryDetailObj.accessoryNameAccDetails).equalsIgnoreCase(accessory)) {

				reportStatusPass(
						methodName + "Accessory Name is displayed: "
								+ sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.accessoryNameAccDetails),
						true, true);
			} else {
				reportStatusFail("Accessory Name is not displayed on Accessories Details page. ", true);
			}
		} catch (Exception e) {
			reportStatusFail("Error in accessory name on Accessories Details page", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 02.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate Default color option price on Accessories details Page.
	 * 
	 * @throws Exception
	 * 
	 * @throws Exception
	 */
	public void verifyDefaultoptionPrice() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Accessories Details page@: ";
			sf.seleU.hardwait(2000);
			if (sf.accessoryDetailObj.accessoriesColorList.size() > 0) {
				// verify default selected color is in left.
				sf.seleU.ScrolltoElement(sf.accessoryDetailObj.accessoriesColorList.get(0));
				if (sf.seleU.getElementAttribute(sf.accessoryDetailObj.accessoriesColorList.get(0), "class")
						.contains("selected-color")) {
					reportStatusPass(
							methodName + "Default Accessories color: " + sf.seleU.getElementAttribute(
									sf.accessoryDetailObj.accessoriesColorList.get(0), "data-id") + " is on the left",
							true, true);
					System.out.println(
							sf.seleU.getElementAttribute(sf.accessoryDetailObj.accessoriesColorList.get(0), "data-id"));
				} else {
					reportStatusFail(methodName + " Error in Default Accessories color", true);
				}
				// verify default price is lowest or equal to other accessories
				verify_DefaultPriceIsLowest();
			}

		} catch (Exception e) {
			reportStatusFail("Error is verifying default color price on accessories details Page", e);
			e.printStackTrace();
		}

	}
	// verify default price is lowest or equal to other accessories
	public void verify_DefaultPriceIsLowest() throws Exception {
		methodName = "Accessories Details page@: ";
		try {
			if (sf.accessoryDetailObj.accessoriesColorList.size() > 1) {
				double defaultPrice = get_PriceOnAccessoryDetailsPage();
				// iterate all color option
				if (sf.seleU.getElementAttribute(sf.accessoryDetailObj.accessoriesColorList.get(0), "data-id")
						.equalsIgnoreCase("black")) {
					reportStatusPass(methodName + " default price is Black", true, true);
				} else {
					reportStatusFail(methodName + "Default price is not Black. ", true);
				}

				for (int i = 1; i < sf.accessoryDetailObj.accessoriesColorList.size(); i++) {
					sf.seleU.hardwait(3000);
					// click on color option
					sf.seleU.clickElementByJSE(sf.accessoryDetailObj.accessoriesColorList.get(i));
					reportStatusPass(
							methodName + "Selected Accessories color: " + sf.seleU
									.getElementAttribute(sf.accessoryDetailObj.accessoriesColorList.get(i), "data-id"),
							true, true);
					// Retrieve discounted price
					double otherColorPrice = get_PriceOnAccessoryDetailsPage();
					if (otherColorPrice >= defaultPrice) {
						reportStatusPass(methodName + "Default Selected color price " + defaultPrice
								+ "is lower or equal to other color price. " + otherColorPrice, true, true);
					} else {
						reportStatusFail(
								methodName + "Default Selected color price is not Lowest or equal to other color. ",
								true);
					}
				}
			}

		} catch (Exception e) {
			reportStatusFail(
					"Error is verifying default price is lowest or equal to other accessories color on accessories details Page",
					e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 02.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Get Accessory Price in double on Accessories details Page.
	 * 
	 * @throws Exception
	 */
	public double get_PriceOnAccessoryDetailsPage() throws Exception {
		methodName = "Accessories Details page@: ";
		try {
			sf.seleU.hardwait(2000);
			String price = sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.priceOnADetails) + "."
					+ sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.desimalPriceOnADetails);
			reportStatusPass(methodName + "Accessory Price: " + price, true, true);
			return Double.parseDouble(price);

		} catch (Exception e) {
			reportStatusFail("Error is get price on accessories details Page", e);
			e.printStackTrace();
		}
		return 0.0;
	}
	/**
	 * Method developed on @Date: 03.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * To verify accessory available text on Accessories details Page.
	 * 
	 * @throws Exception
	 */
	public void verifyAccessoriesAvailability() throws Exception {
		// TODO Auto-generated method stub
		methodName = "Accessories Details page@: ";
		try {
			sf.seleU.waitForLoading();
			sf.seleU.hardwait(3000);
			if (sf.seleU.isElementDisplayed(sf.accessoryDetailObj.availableText) && 
					sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.availableText).equalsIgnoreCase("Available and in stock")) {
				reportStatusPass(methodName + "Accessory is available. ", true, true);
			} else if(sf.seleU.isElementDisplayed(sf.accessoryDetailObj.availableText) && 
					sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.availableText).equalsIgnoreCase("Delayed for future shipping")){
				if(sf.seleU.isElementDisplayed(sf.accessoryDetailObj.shippedTimeText) && 
						sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.shippedTimeText).equalsIgnoreCase("This item is available and will be shipped in 2-3 weeks.")) {
				reportStatusPass(methodName + "Accessory delayed text is available. ",true, true);
				}
				else {
					reportStatusFail(methodName + "Accessory delayed text is not available. ", true);				
				}
			}else if (sf.seleU.isElementDisplayed(sf.accessoryDetailObj.availableText) && 
					sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.availableText).equalsIgnoreCase("This product is not available or is discontinued")) {
				reportStatusPass(methodName + "Accessory is available. ", true, true);
			}
			else {
				reportStatusFail(methodName + "Accessory available or delayed text is not available. ", true);
			}
		} catch (Exception e) {
			reportStatusFail("Error in checking accessory availability on accessories details Page", e);
			e.printStackTrace();
		}
	}

	// validate special price is lower than Base price
	public void verify_discountedPrice() throws Exception {
		methodName = "Accessories Details page@: ";
		try {
			sf.seleU.hardwait(2000);
			if (sf.seleU.isElementDisplayed(sf.accessoryDetailObj.basePrice)) {
				// Retrieve base price
				String str = (sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.basePrice)).substring(1);
				double basePrice = Double.parseDouble(str);
				// Retrieve discounted price
				double discountedPrice = get_PriceOnAccessoryDetailsPage();
				if (discountedPrice < basePrice) {
					reportStatusPass(methodName + "discounted price: " + discountedPrice + " is less then base price: "
							+ basePrice, true, true);
				} else {
					reportStatusFail(methodName + "discounted price is not less than base Price.", true);
				}
			} else {
				reportStatusFail(methodName + "basePrice is not displayed.", true);
			}
		} catch (Exception e) {
			reportStatusFail("Error in checking accessory availability on accessories details Page", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 03.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * To verify Discounted Price for all available option and select other color on
	 * Accessories details Page.
	 * 
	 * @throws Exception
	 */
	public void select_otherColorAccessory_DiscountedPrice(String color) throws Exception {
		methodName = "Accessories Details page@: ";
		try {
			if (sf.accessoryDetailObj.accessoriesColorList.size() > 1) {
				for (int i = 0; i < sf.accessoryDetailObj.accessoriesColorList.size(); i++) {
					sf.seleU.hardwait(3000);
					// to click on color option
					sf.seleU.clickElementByJSE(sf.accessoryDetailObj.accessoriesColorList.get(i));
					reportStatusPass(
							methodName + "Selected Accessories color: " + sf.seleU
									.getElementAttribute(sf.accessoryDetailObj.accessoriesColorList.get(i), "data-id"),
							true, true);
					// validated discounted price
					verify_discountedPrice();
					// check if given color has selected or not
					if (sf.seleU.getElementAttribute(sf.accessoryDetailObj.accessoriesColorList.get(i), "data-id")
							.equalsIgnoreCase(color)) {
						reportStatusPass(
								methodName
										+ sf.seleU.getElementAttribute(
												sf.accessoryDetailObj.accessoriesColorList.get(i), "data-id")
										+ "color accessoriy has selected",
								true, true);
						break;
					}
				}
			}
		} catch (Exception e) {
			reportStatusFail(
					"Error is verifying default price is lowest or equal to other accessories color on accessories details Page",
					e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 31.03.2022 by @author Satish
	 * 
	 * To Validate Add to Cart button status
	 * 
	 */

	public boolean validate_AddToCartButton_Status() throws Exception {
		methodName = "Accessories Details page@: ";
		boolean status=false;
		try {
			sf.seleU.hardwait(2000);
			sf.seleU.waitForLoading();
			if (sf.accessoryDetailObj.addToCart.isEnabled()) {
			status=sf.accessoryDetailObj.addToCart.isEnabled();
			}
		} catch (Throwable e) {
			reportStatusInfo("Add to Cart button is disabled on Accessories Details page", false);
		}
		return status;
	}
}
