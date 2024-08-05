package sfdc.pages.wireless;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData_WA;
import com.sfdc.page_objects.SFDC_AllPageObjects;


/**
 * @author Sakshi Lnu, Date : 30/May/2021
 * 
 *         Verify Shopping cart Objects
 *
 */
public class WACC_ShoppingCart_Page extends MyListener {

	
	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;
	
	public WACC_ShoppingCart_Page() {
		sf = new SFDC_AllPageObjects();		
	}

	/**
	 * @throws Exception
	 * 
	 *                   1- Verify Cart details 2- Verify Monthly Fees Subtotal 3-
	 *                   Verify quantity of plan 4- Verify One time Fees Total
	 * 
	 * 
	 */

	public void verifyCartDetails(String planSize, String planPrice, String planType, String addOnName,
			String addOnPrice) throws Exception {
		try {
			methodName = "WACC Shopping Cart Validation@: ";
			sf.seleU.waitForLoading();
			Assert.assertTrue(sf.seleU.isElementDisplayed(sf.addOnSel.shopCartHeader));
			// clcik to view add on details
			String cartHeader= sf.seleU.getTextFromWebElement(sf.shopCartObj.myCartHeader);
			if (cartHeader.contains("My Cart") || cartHeader.contains("Mon panier")) {
				reportStatusPass(methodName + sf.seleU.getTextFromWebElement(sf.shopCartObj.myCartHeader)
						+ " is Displayed on shopping Cart Page ", true, true);
			} else {
				reportStatusFail(methodName + " Failed in validating Shopping Cart Page", true);
			}
			// verify shopping cart product details and amount added/
//Validation : Need to validate the Card value.
//			verifyFieldValue(" Product Name ", sf.shopCartObj.productName, planSize);
			sf.seleU.waitForLoading();
			
			verifyFieldValue(" Unit Price ", sf.shopCartObj.productUnitPrice, planPrice);
			sf.seleU.waitForLoading();
			verifyFieldValue("Plan Type selected ", sf.shopCartObj.planType, planType);
			sf.seleU.waitForLoading();
			// click to see more deatils and verify the chose plan type and hide details
			// link are present
			sf.seleU.clickElementByJSE(sf.shopCartObj.seeHideDetailsLink);
			reportStatusPass(methodName + "See More Details link is clicked to verify details ", true, true);
			// hide the details
			sf.seleU.clickElementByJSE(sf.shopCartObj.seeHideDetailsLink);
			reportStatusPass(methodName + "Details are verified and Hide Details link is clicked ", true, true);
			sf.seleU.waitForLoading();
			if(InputData_WA.env.contains("ITQATEST") ||InputData_WA.env.contains("PREPROD")) {
				String xpathValue="//div[contains(@class,'nds-grid')]//div[contains(text(),'"+addOnName+"')]";
				WebElement element =driver.findElement(By.xpath(xpathValue));
				verifyFieldValue("Add On Name ", element, addOnName);
			}else {
				verifyFieldValue("Add On Name ", sf.shopCartObj.addOnName, addOnName);
			}
			sf.seleU.waitForLoading();
			verifyFieldValue("Add On Unit Price ", sf.shopCartObj.addOnUnitPrice, addOnPrice);
		} catch (Throwable e) {
			reportStatusFail(" error on validating added plan and AddOn", e);
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
			sf.seleU.waitForLoading();
			if (sf.seleU.getTextFromWebElement(element).trim().contains(expectedText)) {
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
	 *                     Verify footer price items and Click to Procced to
	 *                     Checkout for place order
	 * 
	 */
	public void clickProceedToCheckoutBtn() throws IOException {
		methodName = "WACC Shopping Cart Validation@: ";
		try {
			sf.seleU.waitForLoading();
			sf.seleU.waitTillLoading();
			// Verify proceed to checkout is displayed and clicked
			if (sf.seleU.isElementDisplayed(sf.shopCartObj.proceedToCheckoutBtn)) {
				// click on proceed to checkout button
				sf.seleU.clickElementByJSE(sf.shopCartObj.proceedToCheckoutBtn);
				sf.seleU.waitForLoading();
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.shopCartObj.placeOrderHeader));
				reportStatusPass(methodName + " Successfully clicked on Proceed to Checkout Button", true, true);
			} else {
				reportStatusFail(methodName + " Failure on Clicking Proceed to Checkout button", true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Error on Clicking Proceed to checkout Button", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     To go back to Wireless product screen and to shop again
	 * 
	 */
	public void clickShopProducts() throws IOException {
		methodName = "WACC Shopping Cart Validation@: ";
		try {
			// click on shop products button to go back
			sf.seleU.clickElementByJSE(sf.shopCartObj.shopProductsButton);
			if (sf.seleU.isElementDisplayedWithYellowHighlight(sf.shopCartObj.shopWirelessPlansButton)) {
				reportStatusPass(methodName + "Shop Products button is clicked and "
						+ sf.seleU.getTextFromWebElement(sf.shopCartObj.shopWirelessPlansButton) + " is displayed",
						true, true);
				sf.seleU.waitForLoading();
				sf.seleU.clickElementByJSE(sf.shopCartObj.shopWirelessPlansButton);
				sf.seleU.waitForLoading();
				sf.seleU.clickElementByJSE(sf.shopCartObj.proceedToShopCartButton);
				sf.seleU.waitForLoading();
			} else {
				reportStatusFail(methodName + " Failure on Clicking shop products button", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error on Clicking shop products Button", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 10.06.2021 by @author Sakshi.Lnu
	 * 
	 * @throws IOException
	 * 
	 *                     To add the available credit option if available there are
	 *                     three options: None, and two prices i.e $300, $350
	 *                     currently
	 * 
	 */
	public void getPostTaxCreditIfAvailable() throws IOException {
		methodName = "WACC Shopping Cart Validation@: ";
		Boolean ifCreditSel = false;
		try {
			// To verify if the post Tax credit is not available
			if (InputData_WA.WACC_ifCreditAvailable.contains("No")) {
				reportStatusPass(methodName + "Post Tax Credit is not selected", true, true);
			}
			// if post tax credit is avaialble then select the options as per test data
			// provided
			else if (InputData_WA.WACC_ifCreditAvailable.contains("Yes")
					&& (sf.seleU.isElementDisplayedWithYellowHighlight(sf.shopCartObj.postTaxCreditreditHeader))) {
				reportStatusPass(methodName + sf.seleU.getTextFromWebElement(sf.shopCartObj.postTaxCreditreditHeader),
						true, true);

				for (int i = 0; i <= sf.shopCartObj.postTaxCreditOfferOptions.size(); i++) {

					if (sf.shopCartObj.postTaxCreditOfferOptions.get(i).getText()
							.contains(InputData_WA.WACC_Use_Credit_Option)) {
						sf.seleU.clickElementByJSE(sf.shopCartObj.postTaxCreditOfferOptions.get(i));
						sf.seleU.wait(2000);
						reportStatusPass(
								methodName + "Post tax credit option is clicked: "
										+ sf.seleU
												.getTextFromWebElement(sf.shopCartObj.postTaxCreditOfferOptions.get(i)),
								true, true);
						sf.seleU.hardwait(3000);
						ifCreditSel = true;
						break;
					}
				}
			}
			if ((ifCreditSel) && !(InputData_WA.WACC_Use_Credit_Option.equals("None"))) {
				sf.seleU.clickElementByJSE(sf.shopCartObj.applyOfferButton);
				reportStatusPass(methodName + " Apply Offer button Is clicked", true, true);
				sf.seleU.hardwait(10000);
				verifyFieldValue("Select post tax credit: ", sf.shopCartObj.selectedCredit,
						InputData_WA.WACC_Use_Credit_Option);	
			}
		} catch (Throwable e) {
			reportStatusFail(" Error on validating post tax credit feature", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 14.06.2021 by @author Sakshi.Lnu
	 * 
	 * @throws Exception
	 * @throws InterruptedException
	 * 
	 * 
	 */
	public void updatePlanQuantity() throws InterruptedException, Exception {

		methodName = "WACC Shopping Cart Validation@: ";
		try {
			if (InputData_WA.WACC_Plan_Quantity.equals("1")) {
				reportStatusPass(methodName + "Plan Quantity is : "
						+ sf.seleU.getTextFromWebElement(sf.shopCartObj.updateQuantityDropdown), true, true);

			} else {
				sf.seleU.clickOnElement(sf.shopCartObj.updateQuantityDropdown);
				sf.seleU.waitForListOfElements(sf.shopCartObj.updateQuantityDropdownOptions);
				for (int i = 0; i <= sf.shopCartObj.updateQuantityDropdownOptions.size(); i++) {
					if (sf.shopCartObj.updateQuantityDropdownOptions.get(i).getText()
							.equals(InputData_WA.WACC_Plan_Quantity)) {
						sf.seleU.clickOnElement(sf.shopCartObj.updateQuantityDropdownOptions.get(i));
						sf.seleU.waitForLoading();
						reportStatusPass(
								methodName + "Updated Quantity is : "
										+ sf.seleU.getTextFromWebElement(sf.shopCartObj.updateQuantityDropdown),
								true, true);
						break;
					}
				}
				sf.seleU.waitForLoading();
				verifyFieldValue("Updated AddOns", sf.shopCartObj.updatedAddOnQuantity,
						InputData_WA.WACC_Plan_Quantity);
				sf.seleU.wait(2000);
				verifyFieldValue("Recurring TCV ", sf.shopCartObj.footerRecurringTcvPrice,
						InputData_WA.WACC_AddOn_TotalPrice);
				verifyFieldValue("Total  Costs ", sf.shopCartObj.totalCostsValue, InputData_WA.WACC_Final_TotalCosts);
			}
		} catch (Throwable e) {
			reportStatusFail(" Error on updating quantity", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 29.09.2021 by @author Gaurav Singh
	 * 
	 * Clicks on hide details of added device on cart.
	 * 
	 * 
	 */
	public void hideDetailsOfDevice(String HideDeviceText) throws IOException {
		try {
			sf.seleU.waitTillLoading();
			if (sf.shopCartObj.hideDetailsDevice.isDisplayed()) {
				sf.seleU.ScrolltoElement(sf.shopCartObj.fetchDeviceNameforPlanFlow);
				sf.seleU.clickElementByJSE(sf.shopCartObj.hideDetailsDevice);
				sf.seleU.waitTillLoading();
				Assert.assertEquals("Failed to hide the link!", HideDeviceText,
						sf.shopCartObj.hideDetailsDevice.getText());
				reportStatusPass("Successfully click the hide link of the device in cart!", true, true);
			}
		} catch (Exception e) {
			reportStatusFail("Failed to click the hide link of device", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 29.09.2021 by @author Gaurav Singh
	 * 
	 * Validate the added device details on cart.
	 * 
	 * 
	 */
	public void fetchAddedDeviceDetails(String deviceName, String deviceFinancePrice, String deviceFinanceTime,
			String flowName) throws Exception {
		try {
			sf.seleU.waitTillLoading();
			String financePrice;
			sf.seleU.ScrolltoElement(sf.shopCartObj.postTaxCreditreditHeader);
			if (sf.shopCartObj.fetchDeviceNameforPlanFlow.isDisplayed()) {
				String a = sf.shopCartObj.fetchDeviceNameforPlanFlow.getText();
				if (deviceName.equals(a)) {
					reportStatusPass("Successfully validated the added device Name on cart with device details page.!",
							true, true);
				} else {
					reportStatusFail("Failed to validate the added the device name on cart", false);
				}
				if (flowName.equalsIgnoreCase("FinanceFlow")) {
					String CartdeviceFinanceTime = sf.shopCartObj.fetchDeviceInstlmntFrame.getText();
					if (CartdeviceFinanceTime.contains("month") && deviceFinanceTime.contains("month")) {
						reportStatusPass("Device Financing is on monthly basis", true, true);
					} else {
						reportStatusFail("Failed to validate the added the device details on cart", false);
					}
					financePrice = sf.shopCartObj.fetchDevicefinancePrceCart.getText().replaceAll("[^0-9.]", "");
					deviceFinancePrice = deviceFinancePrice.substring(0, deviceFinancePrice.length() - 1);
				} else {
					financePrice = sf.shopCartObj.fullDevicePriceCart.getText().replaceAll("[^0-9.]", "");
				}
				int financePrice1 = returnIntergerPrice(financePrice);
				int deviceFinancePrice1 = returnIntergerPrice(deviceFinancePrice);
				if (financePrice1 == deviceFinancePrice1) {
					reportStatusPass("Successfully validated the Financed price of device in details and cart!", true,
							true);
				} else {
					reportStatusFail("Finance Price of added device differs in cart and device details page", false);
				}
			}
		} catch (Exception e) {
			reportStatusFail("Failed to validated the added the device details on cart", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 04.10.2021 by @author Gaurav Singh
	 * 
	 * Update the device qty in cart.
	 * 
	 * 
	 */
	public void updateCartQtyPlans(String updateQTY) throws Exception {
		String oldMonthlyPrice, oldOneTimePrice, newMonthlyPrice, newOneTimePriceString;
		try {
			sf.seleU.waitTillLoading();
			if (sf.shopCartObj.monthlyPriceTotal.isDisplayed()) {
				oldMonthlyPrice = sf.shopCartObj.monthlyPriceTotal.getText();
				oldOneTimePrice = sf.shopCartObj.OneTimeprice.getText();
				sf.seleU.ScrolltoElement(sf.shopCartObj.myCartHeader);
				sf.seleU.wait(800);
				//sf.seleU.clickElementByJSE(sf.shopCartObj.updateQuantityDropdown);
				sf.seleU.clickElementByJSE(sf.shopCartObj.updateQuantityDropdown1);
				//Iterator<WebElement> i1 = sf.shopCartObj.updateQuantityDropdownOptions.iterator();
				Iterator<WebElement> i1 = sf.shopCartObj.updateQuantityDropdownOptions1.iterator();
				while (i1.hasNext()) {
					WebElement ele = i1.next();
					String dropDownOptions = ele.getAttribute("data-value");
					if (dropDownOptions.equalsIgnoreCase(updateQTY)) {
						String newDropDownval = ele.getAttribute("data-value");
						sf.seleU.clickElementByJSE(ele);
						sf.seleU.hardwait(2000);
						sf.seleU.waitForLoading();
						Assert.assertNotSame("Failed to update Quantity Dropdown!", newDropDownval, dropDownOptions);
						break;
					}
				}
				sf.seleU.waitTillLoading();
				sf.seleU.wait(1500);
				newMonthlyPrice = sf.shopCartObj.monthlyPriceTotal.getText();
				newOneTimePriceString = sf.shopCartObj.OneTimeprice.getText();
				Assert.assertNotSame("Post Quantity update monthly prices are same", newMonthlyPrice, oldMonthlyPrice);
				Assert.assertNotSame("Post Quantity update One time prices are same", newOneTimePriceString,
						oldOneTimePrice);
				reportStatusPass("Updated the cart quantity!", true, true);
			}
		} catch (Exception e) {
			reportStatusFail("Failed to Update the cart quantity!", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 05.10.2021 by @author Gaurav Singh
	 * 
	 * Validate the Striked Out price In Cart.
	 * 
	 * 
	 */
	public void verifyStrikedOutPriceCart() throws Exception {
		try {
			sf.seleU.waitTillLoading();
			String originalPriceString, discountedPriceString;
			int originalPrice, discountedPrice;
			originalPriceString = sf.shopCartObj.StrikeOutPriceCart.getText().replaceAll("[^0-9.]", "");
			originalPrice = returnIntergerPrice(originalPriceString);
			discountedPriceString = sf.shopCartObj.fetchDevicefinancePrceCart.getText().replaceAll("[^0-9.]", "");
			discountedPrice = returnIntergerPrice(discountedPriceString);
			Assert.assertNotSame("Only one Original price was displayed", originalPrice, discountedPrice);
			reportStatusPass("Successfully verified the strike out and original price in cart!", true, true);
		} catch (Exception e) {
			reportStatusFail("Failed to Validate the strikeout cart price for device!", false);
			e.printStackTrace();
		}

	}

	/**
	 * Method developed on @Date: 05.10.2021 by @author Gaurav Singh
	 * 
	 * Returns the price in integer.
	 * 
	 * 
	 */
	public int returnIntergerPrice(String Price) {
		Double PriceVal = Double.parseDouble(Price);
		int PriceValInIntgr = (int) Math.round(PriceVal);
		return PriceValInIntgr;
	}

	/**
	 * Method developed on @Date: 05.10.2021 by @author Gaurav Singh
	 * 
	 * Validates the price in cart for BYOD
	 * 
	 * 
	 */
	public void checkonetimefeesInCart() throws Exception {
		try {
			sf.seleU.waitTillLoading();
			sf.seleU.waitElementToBeVisible(sf.shopCartObj.myCartHeader);
			sf.seleU.scrollToBottom();
			Assert.assertEquals("One time fees not equal to zero so some device is added", "0",
					sf.shopCartObj.OneTimeprice.getText());
			reportStatusPass("Successfully verified BYOD device added in cart wth zero one time fees", true, true);
		} catch (Exception e) {
			reportStatusFail("Failed to Validate the BYOD device added in cart wth zero one time price!", false);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 29.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate Added Accessories, color, quantity in Cart on the Shopping Cart
	 * Page
	 * 
	 * @throws Exception
	 * 
	 */
	public void verify_Accessories_Color_Quantity_InCart(String text, int quantity) throws Exception {
		try {
			sf.seleU.hardwait(2000);
			methodName = "WACC Shopping Cart Validation@: ";
			WebElement accessories = driver.findElement(By.xpath("//*[text()='" + text + "']"));
			WebElement color = driver
					.findElement(By.xpath("//*[text()='" + text + "']/parent::div/following-sibling::div//span"));
			sf.seleU.ScrolltoElement(accessories);
			// verify accessories and color is displayed
			if (sf.seleU.isElementDisplayed(accessories) && sf.seleU.isElementDisplayed(color)) {
				sf.seleU.hardwait(2000);
				reportStatusPass(methodName + text + ": accessories and accessories color is displayed. ", true, true);
			}
			// when color is not present with accessories
			else if (sf.seleU.isElementDisplayed(accessories) && !sf.seleU.isElementDisplayed(color)) {
				sf.seleU.hardwait(2000);
				reportStatusPass(methodName + text + ": accessories has added and accessories color has not present. ",
						true, true);
			} else {
				sf.seleU.hardwait(2000);
				reportStatusFail("Error in added accessories and color on WACC Shopping Cart page", true);
			}

			// verify the accessories quantity in input box
			int quantityInput = getAccessoryQuantity(text);
			if (quantityInput== quantity) {
				sf.seleU.hardwait(2000);
				reportStatusPass(methodName + " Quantity on shopping Cart Page is" + quantity, true, true);
			} else {
				sf.seleU.hardwait(2000);
				reportStatusFail("Quantity on shopping Cart Page is not matched. ", true);
			}
		} catch (Exception e) {
			reportStatusFail("Failed to Validate the added Assessories in cart!", true);
			e.printStackTrace();
		}
	}

	public int getAccessoryQuantity(String item) throws Exception {
		int quantity=0;
		try {
			quantity = Integer.parseInt(sf.seleU.getTextFromWebElement(driver.findElement(By.xpath("//div[contains(text(),'" + item + "')]/../following-sibling::div[2]"))));
			
		} catch (Exception e) {
			reportStatusFail("Error to retrive Assessories quantity in cart!", true);
			e.printStackTrace();
		}
		return quantity;
	}
	

	/**
	 * Method developed on @Date: 29.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate the quantity drop down value in Cart on the Shopping Cart
	 * Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void validate_QuantityDropDown_InCart(String text, String quantity) throws Exception {
		try {
			methodName = "WACC Shopping Cart Validation@: ";
			sf.seleU.hardwait(2000);
			WebElement quantityInput = driver.findElement(By.xpath("//div[text()='" + text
					+ "']/following-sibling::div//*[@class='slds-input slds-combobox__input']"));

			sf.seleU.ScrolltoElement(quantityInput);
			sf.seleU.clickElementByJSE(quantityInput);
			sf.seleU.hardwait(2000);
			if (sf.shopCartObj.accessoriesQuantityList.size() == 10) {
				int count = 1;
				for (WebElement ele : sf.shopCartObj.accessoriesQuantityList) {

					if (Integer.parseInt(sf.seleU.getElementAttribute(ele, "data-value")) == count) {
						sf.seleU.hardwait(2000);
						reportStatusPass(methodName + " value in Quantity dropdown is: " + count, true, true);
					}
					count++;
				}
			}
		} catch (Exception e) {
			reportStatusFail("Error in accessories quantity dropdown on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 30.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to increase the quantity in Cart on the Shopping Cart Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void validate_IncreaseAccQuantity_InCart(String text, int quantity) throws Exception {
		try {
			methodName = "WACC Shopping Cart Validation@: ";
			sf.seleU.hardwait(2000);
			WebElement quantityInput = driver.findElement(By.xpath("//div[text()='" + text
					+ "']/following-sibling::div//*[@class='slds-input slds-combobox__input']"));

			sf.seleU.ScrolltoElement(quantityInput);
			// Click on QuantityInput DropDown box
			sf.seleU.clickElementByJSE(quantityInput);
			selectQuantity(quantityInput, quantity);
		} catch (Exception e) {
			reportStatusFail("Error in accessories quantity dropdown on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 30.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to select Quantity in Cart on the Shopping Cart Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void selectQuantity(WebElement quantityInput, int quantity) throws Exception {
		try {
			// if quantity is lesser or equal to 10 select value from drop down.
			if (quantity <= 10) {
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.shopCartObj.accessoriesQuantityList.get(quantity - 1));
				sf.seleU.hardwait(2000);
				reportStatusPass(methodName + " User is able to select Quantity " + quantity + " from dropdown", true,
						true);
			}

			// if quantity is greater than 10 enter value in quantity drop box
			else if (quantity > 10) {
				sf.seleU.clickElementByJSE(sf.shopCartObj.accessoriesQuantityList.get(9));
				sf.seleU.hardwait(7000);
				sf.seleU.enterText(quantityInput, Integer.toString(quantity));

				// if quantity is greater than 100 alert should present
				if (quantity > 100) {
					sf.seleU.clickEnter();
					sf.seleU.hardwait(2000);
					String err = sf.seleU.switchToAlert("OK");
					if (err.equalsIgnoreCase(" Quantity should be between 1-100 to update cart.")) {
						reportStatusPass(methodName + "Quantity is not allowed more than 100. ", true, true);
					} else {
						reportStatusFail("Error in retriving alert text", true);
					}
					Assert.assertEquals(sf.seleU.getTextFromWebElement(quantityInput), "100");
				}
			}
			sf.seleU.clickEnter();
		} catch (Exception e) {
			reportStatusFail("Error in accessories quantity dropdown on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 29.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate the remove link in Cart on the Shopping Cart Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void validate_RemoveAccLink_InCart(String text) throws Exception {
		try {
			methodName = "WACC Shopping Cart Page@: ";
			sf.seleU.hardwait(2000);
			WebElement remove = driver.findElement(By.xpath("//div[text()='" + text
					+ "']/parent::div/following-sibling::div//button[contains(@class,'neutral remove-button')]"));
			sf.seleU.ScrolltoElement(remove);
			if (sf.seleU.isElementDisplayed(remove)) {
				sf.seleU.hardwait(2000);
				reportStatusPass(methodName + "Remove link is present with accessory " + text, true, true);
			} else
				reportStatusFail("Remove link is not present with accessory", true);

		} catch (Exception e) {
			reportStatusFail("Error in accessories quantity dropdown on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 29.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to click on remove Accessory link in Cart on the Shopping Cart Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void click_RemoveAccLink_InCart(String text) throws Exception {
		try {
			methodName = "WACC Shopping Cart Page@: ";
			sf.seleU.hardwait(2000);
			WebElement accessory = driver.findElement(By.xpath("//div[text()='" + text + "']"));
			WebElement remove = driver.findElement(By.xpath("//div[text()='" + text
					+ "']/ancestor::div/following-sibling::div//button[contains(@class,'neutral remove-button')]"));
			if (sf.seleU.isElementDisplayed(accessory) && sf.seleU.isElementDisplayed(remove)) {
				sf.seleU.clickElementByJSE(remove);
				sf.seleU.waitForLoading();
				reportStatusPass(methodName + "Remove link is present with accessory and Clicked. " + text, true, true);
			} else
				reportStatusFail("Remove link is not present with accessory", true);
		} catch (Exception e) {
			reportStatusFail("Error in click remove link on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 29.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate Accessory has removed in Cart on the Shopping Cart Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void validate_AccessoryRemoved_InCart(String text) throws Exception {
		try {
			methodName = "WACC Shopping Cart Page@: ";
			sf.seleU.waitForLoading();
			sf.seleU.hardwait(2000);
			// Verify accessory has removed
			if (!sf.seleU.getTextFromWebElement(sf.shopCartObj.monthlyPriceTotal).equals("0")) {
				reportStatusPass(methodName + "Accessory has removed. " + text, true, true);
			} else {
				reportStatusFail("Error in Remove accessory WACC Shopping Cart.", true);
			}

		} catch (Exception e) {
			reportStatusFail("Error in validating accessories on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 08.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate added DP and Accessories in Cart on the Shopping Cart Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void verifyDevice_DP_AccDetailsOnCart(String dpName, String dpPrice,
			String accessory, String accessoryPrice,int quantity) throws Exception {
		// TODO Auto-generated method stub
		methodName = "WACC Shopping Cart Page@: ";
		try {
			sf.seleU.hardwait(3000);
			sf.seleU.waitTillLoading();
			verify_OneTimeCost_itemName_Price(dpName, dpPrice, quantity);
			verify_AccessoryName_Price(accessory, accessoryPrice, quantity);

		} catch (Exception e) {
			reportStatusFail("Error in added DP and Accessorie on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 08.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate added items in Cart on the Shopping Cart Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void verify_MonthlyProductName_Price(String item, String price, int quantity) throws Exception {
		// TODO Auto-generated method stub
		methodName = "WACC Shopping Cart Page@: ";
		try {
			sf.seleU.waitForLoading();
			WebElement eleItem = driver.findElement(By.xpath("(//*[contains(text(),'" + item + "')])[1]"));
			WebElement eleUnitPrice = driver.findElement(By.xpath("(//*[contains(text(),'" + item+ "')]/../../following-sibling::div//*[contains(text(),'" + price + "')])[1]"));
			WebElement eleQuantity = driver.findElement(By.xpath("//*[contains(text(),'" + item+ "')]/../../following-sibling::div[2]//span | //*[contains(text(),'" + item
					+ "')]/../../following-sibling::div[2]"));
			WebElement eleLinePrice = driver.findElement(By.xpath("//*[contains(text(),'" + item+ "')]/../../following-sibling::div[3]//div[contains(text(),'/mo')]"));
			sf.seleU.ScrolltoElement(eleItem);
			if (validate_itemAndPrice(eleItem, eleUnitPrice, eleQuantity, eleLinePrice, item, price, quantity)) {
				reportStatusPass(methodName + item + " is displayed under Monthly Cost and Price is: $" + price
						+ " and Quantity is: " + quantity, true, true);
			}
			else
				reportStatusFail(methodName + item + " is not displayed under Monthly Cost.", true);
		} catch (Exception e) {
			reportStatusFail("Error in add item "+ item +" value on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 10.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate added items name and price in Cart on the Shopping Cart
	 * Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void verify_OneTimeCost_itemName_Price(String item, String price, int quantity) throws Exception {
		try {
			methodName = "WACC Shopping Cart Page@: ";
			
			WebElement eleItem = driver.findElement(By.xpath("(//*[contains(text(),'" + item + "')])[1]"));
			WebElement eleUnitPrice = driver.findElement(By.xpath("(//div[contains(text(),'" + item + "')]/following-sibling::div/*[contains(text(),'$"+price+"')])[1]"));
			WebElement eleQuantity = driver.findElement(By.xpath("//div[contains(text(),'" + item + "')]/following-sibling::div[2]"));
			WebElement eleLinePrice = driver.findElement(By.xpath("//div[contains(text(),'" + item + "')]/following-sibling::div[3]"));
			sf.seleU.ScrolltoElement(eleItem);
			if(validate_itemAndPrice(eleItem,eleUnitPrice, eleQuantity, eleLinePrice, item, price, quantity )) {
				reportStatusPass(methodName + item + " is displayed under OneTime Cost and Price is: $" + price + " and Quantity is: "+ quantity, true, true);
			}
			
		} catch (Exception e) {
			reportStatusFail("Error in add item value on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
			
	}
		public boolean validate_itemAndPrice(WebElement eleItem, WebElement eleUnitPrice, WebElement eleQuantity,WebElement eleLinePrice, String item, String price, int quantity) throws Exception {
			try {
				double linePrice=0, temp=0;
				if (sf.seleU.isElementDisplayed(eleItem) && sf.seleU.isElementDisplayed(eleUnitPrice) && sf.seleU.isElementDisplayed(eleQuantity)) {
					String eleLP = sf.seleU.getTextFromWebElement(eleLinePrice).replaceAll("[^0-9.,]", "");
					String language=InputData_WA.language;
					if(language==null || language.equals("") || language.equalsIgnoreCase("English")) {
						linePrice = Double.parseDouble(eleLP);
						temp =Double.parseDouble(price)*(double)quantity;
					}
					else if(language.equalsIgnoreCase("French")){						
						linePrice = Double.parseDouble(eleLP.replace(",", "."));
						temp =Double.parseDouble(price.replace(",", "."));
						temp= temp*(double)quantity;
					}	
					
					//quantity == Integer.parseInt(sf.seleU.getTextFromWebElement(eleQuantity)) 
					if(linePrice== temp) {
						return true;
					}					
				} else {
					reportStatusFail(item + price + " has not present on WACC Shopping Cart page!", true);
					return false;
				}
			} catch (Exception e) {
				reportStatusFail("Error in add item value on WACC Shopping Cart page!", true);
				e.printStackTrace();				
			}
			return false;
		}

	/**
	 * Method developed on @Date: 08.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to remove Combo in Cart on the Shopping Cart Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void selectRemoveCombo() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "WACC Shopping Cart Page@: ";
			sf.seleU.hardwait(5000);
			if (sf.seleU.isElementDisplayed(sf.shopCartObj.removeCombo)) {
				sf.seleU.clickElementByJSE(sf.shopCartObj.removeCombo);
				sf.seleU.waitForLoading();
				sf.seleU.waitTillLoading();
				reportStatusPass(methodName + "remove button has clicked.", true, true);
			} else {
				reportStatusFail(methodName + "remove option is not displayed.", true);
			}
		} catch (Exception e) {
			reportStatusFail("Error in remove the combo from cart on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 21.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to Validate Quantity in Cart on the Shopping Cart Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void validateQuantityInCart(String plan, String addOn, String device, String dp, String accessory,
			int quantity, int accQuantity) throws Exception {
		// TODO Auto-generated method stub
		try {
			sf.seleU.hardwait(3000);
			WebElement planQuantity = driver.findElement(By.xpath("(//*[contains(text(),'" + plan
					+ "')])[1]/../../following-sibling::div//*[@class='slds-truncate']"));
			WebElement deviceQuantity = driver.findElement(By.xpath(
					"(//*[contains(text(),'" + device + "')])[1]/parent::div/parent::div/following-sibling::div[2]"));

			verifyComboQuantity(planQuantity, plan, quantity);
			verifyComboQuantity(sf.shopCartObj.addOnQuantity, addOn, quantity);
			verifyComboQuantity(deviceQuantity, device, quantity);
			verifyItemsQuantity("setUpFee", "System Setup Fee", quantity);
			verifyItemsQuantity("dp", dp, quantity);
			verifyItemsQuantity("accessory", accessory, accQuantity);

		} catch (Exception e) {
			reportStatusFail("Error in validating the cart items quantity in cart on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 21.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to Verify combo Quantity in Cart on the Shopping Cart Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void verifyComboQuantity(WebElement ele, String item, int quantity) throws Exception {
		try {
			methodName = "WACC Shopping Cart Page@: ";
			WebElement eleItem = driver.findElement(
					By.xpath("(//div[contains(@class,'body--regular')][contains(normalize-space(),'" + item + "')])"));

			if (sf.seleU.isElementDisplayed(eleItem)
					&& Integer.parseInt(sf.seleU.getTextFromWebElement(ele)) == quantity) {
				reportStatusPass(methodName + item + " quantity has matched with selected " + quantity, true, true);
			} else {
				reportStatusFail("Item and Quantity has not matched with selected quantity", true);
			}

		} catch (Exception e) {
			reportStatusFail("Error in verify the Combo quantity in cart on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 21.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to Verify items Quantity in Cart on the Shopping Cart Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void verifyItemsQuantity(String itemType, String item, int quantity) throws Exception {
		try {
			methodName = "WACC Shopping Cart Page@: ";
			WebElement eleQuantity;
			WebElement eleItem = driver.findElement(By.xpath("(//*[contains(text(),'" + item + "')])[1]"));

			if (itemType.equalsIgnoreCase("accessory")) {
				eleQuantity = driver.findElement(By.xpath("//div[text()='" + item+ "']/../following-sibling::div//*[@class='slds-truncate']"));
			} else {
				eleQuantity = driver.findElement(By.xpath("(//*[contains(text(),'" + item + "')])[1]/following-sibling::div[2]"));
			}
			if (sf.seleU.isElementDisplayed(eleItem)
					&& Integer.parseInt(sf.seleU.getTextFromWebElement(eleQuantity)) == quantity) {
				reportStatusPass(methodName + item + " quantity has matched with slected " + quantity, true, true);
			} else {
				reportStatusFail("Item and Quantity has not matched with selected quantity!", true);
			}

		} catch (Exception e) {
			reportStatusFail("verify the item quantity in cart on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 21.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to Verify combo has removed and accessory is present in Cart on the
	 * Shopping Cart Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void validate_ComboRemoved_AccessoryPresent() throws Exception {
		try {
			methodName = "WACC Shopping Cart Page@: ";
			sf.seleU.hardwait(2000);
			if (sf.seleU.getTextFromWebElement(sf.shopCartObj.monthlyFeeSubTotal).contains("$0")
					&& sf.seleU.getTextFromWebElement(sf.shopCartObj.monthlyPriceTotal).contains("0")) {
				reportStatusPass(methodName + " plan Combo has removed.", true, true);
				if (sf.seleU.isElementDisplayed(sf.shopCartObj.accessoryInCart)) {
					reportStatusPass(methodName + " plan Combo has removed and accessory remain present.", true, true);
				} else {
					reportStatusFail("Accessory is not present on Shopping cart page. ", true);
				}
			}

		} catch (Exception e) {
			reportStatusFail("Error in Validating accessory after removing the combo plan on WACC Shopping Cart page!",
					true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 22.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to Verify Empty Cart text on the Shopping Cart Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void validate_EmptyCart() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "WACC Shopping Cart Page@: ";
			sf.seleU.waitForLoading();
			sf.seleU.hardwait(2000);
			if (sf.seleU.isElementDisplayed(sf.shopCartObj.emptyCartText)) {
				reportStatusPass(methodName + " Cart is Empty.", true, true);
			} else {
				reportStatusFail("Cart is not Empty on WACC Shopping Cart page!", true);
			}
		} catch (Exception e) {
			reportStatusFail("Error in validate empty cart on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 22.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to Verify Alert is present on the Shopping Cart Page.
	 *
	 * @throws Exception
	 * 
	 */
	public void validate_RemovePlanComboAlert(String text, String command) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "WACC Shopping Cart Page@: ";
			sf.seleU.hardwait(2000);
			// Alert alt = driver.switchTo().alert();
			sf.seleU.waitForLoading();
			if (sf.seleU.getTextFromWebElement(sf.shopCartObj.alertText).equalsIgnoreCase(text)
					&& sf.seleU.getTextFromWebElement(sf.shopCartObj.alertOK).equalsIgnoreCase("Yes, continue")
					&& sf.seleU.getTextFromWebElement(sf.shopCartObj.alertCancel).equalsIgnoreCase("Cancel")) {
				reportStatusPass(methodName + " Alert Text has matched.", true, true);
				if (command.equalsIgnoreCase("OK")) {
					sf.seleU.clickElementByJSE(sf.shopCartObj.alertOK);
					sf.seleU.hardwait(2000);
					sf.seleU.waitForLoading();
					reportStatusPass(methodName + " Continue button clicked on Alert.", true, true);
				} else {
					sf.seleU.clickElementByJSE(sf.shopCartObj.alertCancel);
					sf.seleU.hardwait(2000);
					sf.seleU.waitForLoading();
					reportStatusPass(methodName + " Cancel button clicked on Alert.", true, true);
				}
			} else {
				reportStatusFail("Alert Text has not matched.!", true);
			}

		} catch (Exception e) {
			reportStatusFail("Error in Validating Alert on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 23.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to Verify Flat discounting on quote on the Shopping Cart Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void validateFlatDiscountQuoteText_Price(String text) throws IOException {
		// TODO Auto-generated method stub
		try {
			methodName = "WACC Shopping Cart Page@: ";
			sf.seleU.waitForLoading();
			sf.seleU.hardwait(2000);
			sf.seleU.waitForLoading();
			sf.seleU.hardwait(2000);
			// check Flat Text Discount Present
			int count = sf.shopCartObj.flatDiscountText.size();
			for (int i = 0; i < sf.shopCartObj.flatDiscountText.size(); i++) {
				if (sf.seleU.isElementDisplayed(sf.shopCartObj.flatDiscountText.get(i)) && sf.seleU
						.getTextFromWebElement(sf.shopCartObj.flatDiscountText.get(i)).equalsIgnoreCase(text)) {
					reportStatusPass(methodName + " Flat DiscountText is Present!", true, true);
					System.out.println(sf.shopCartObj.productUnitPriceLst.get(i).getCssValue("text-decoration"));
					// check original price has crossed out
					if (sf.shopCartObj.productUnitPriceLst.get(i).getCssValue("text-decoration")
							.contains("line-through")
							&& sf.shopCartObj.productLineTotalLst.get(i).getCssValue("text-decoration")
									.contains("line-through")) {
						reportStatusPass(methodName + " Original Unit price & Line Total has striked out!", true, true);
						System.out.println(sf.shopCartObj.flatDiscountUnitPrice.get(i).getCssValue("color")
								+ sf.shopCartObj.flatDiscountLineTotal.get(i).getCssValue("color"));
												
						if(sf.seleU.isElementDisplayed(sf.shopCartObj.planQuantityInput)) {
							int temp = Integer.parseInt(sf.seleU.getTextFromWebElement(sf.shopCartObj.planQuantityInput));
							if (count < temp) {
								count=temp;
							}
						}
						else {
							int temp = Integer.parseInt(sf.seleU.getTextFromWebElement(sf.revOrderObj.planQuantity));
							if (count < temp) {
								count=temp;
							}
						}					
						
						double discount= Double.parseDouble(sf.seleU.getTextFromWebElement(sf.shopCartObj.flatDiscountText.get(i)).replaceAll("[^0-9.]", ""));
						
						// check discount price in red color
						if (sf.shopCartObj.flatDiscountUnitPrice.get(i).getCssValue("color")
								.equals("rgba(218, 41, 28, 1)")
								&& sf.shopCartObj.flatDiscountLineTotal.get(i).getCssValue("color")
										.equals("rgba(218, 41, 28, 1)")) {
							
							reportStatusPass(methodName + " Flat Discount Unit price & Line Total is in Red Color!",
									true, true);
							validatePriceAfterDiscountApplied(discount, count, i);
						} // check discount price in red color if close
						else {
							reportStatusFail(
									"Flat Discount Unit price & Line Total is not in Red Color! on WACC Shopping Cart page!",
									true);
						}
					} // check original price has crossed out if close
					else {
						reportStatusFail(
								"Original Unit price & Line Total has not striked out on WACC Shopping Cart page!",
								true);
					}
				} // check Flat Text Discount Present if close
				else {
					reportStatusFail("Flat Discount Text is not displayed or text not match on Shopping Cart Page! ",
							true);
				}
			}
		} // try close
		catch (Exception e) {
			reportStatusFail("Error in Validating Flat Discount Text on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 23.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to Verify Flat discounting text not present on the Shopping Cart Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void validateNoFlatDiscountQuoteText() throws IOException {
		// TODO Auto-generated method stub
		try {
			methodName = "WACC Shopping Cart Page@: ";
			sf.seleU.waitForLoading();
			sf.seleU.hardwait(2000);
			sf.seleU.waitForLoading();
			sf.seleU.hardwait(2000);

			// check Flat Text Discount Not Present
			if (sf.shopCartObj.flatDiscountText.size() == 0) {
				reportStatusPass(methodName + " Flat DiscountText is not Present!", true, true);
			} else {
				reportStatusFail("Flat Discount Text is displayed on Shopping Cart Page! ", true);
			}
		} // try close
		catch (Exception e) {
			reportStatusFail("Error in Validating Flat Discount Text on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 28.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to click on Shop Product button on the Shopping Cart Page.
	 * 
	 * @throws Exception
	 * 
	 * 
	 */
	public void clickOnShopProductOnShopCart() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "WACC Shopping Cart Page@: ";
			sf.seleU.hardwait(2000);
			sf.seleU.waitForLoading();
			// check Flat Text Discount Not Present
			if (sf.seleU.isElementDisplayed(sf.shopCartObj.shopProductsButton)) {
				sf.seleU.clickElementByJSE(sf.shopCartObj.shopProductsButton);
				reportStatusPass(methodName + "Shop Product button has clicked", true, true);
				sf.seleU.waitForLoading();
				sf.seleU.hardwait(1000);
				Assert.assertTrue(sf.seleU.isElementDisplayed(sf.proSel.productsHeader));
			} else {
				reportStatusFail("Shop Product button has not clicked on Shopping Cart Page! ", true);
			}
		} // try close
		catch (Exception e) {
			reportStatusFail("Error in clicking on Shop Product button on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 29.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate discounted Recurring TCV Price, Total TCV Price & Monthly
	 * Recurring charge Price in footer on the Shopping Cart Page.
	 * 
	 * @throws Exception
	 * 
	 * 
	 */
	public void validateFlatDiscountedPriceInFooter(double discount, double[] price) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "WACC Shopping Cart Page@: ";
			sf.seleU.hardwait(2000);
			sf.seleU.waitForLoading();
			int count = sf.shopCartObj.flatDiscountText.size();
			
			String quantityInput = sf.seleU.getTextFromWebElement(driver.findElement(By.xpath("//div[contains(text(),'Fin Business')]/../../following-sibling::div//*[@class='slds-input slds-combobox__input']")));
			int temp = Integer.parseInt(quantityInput);
			if (count < temp) {
				count=temp;
			}
			// check Flat Text Discount applied in Footer
			if (sf.seleU.isElementDisplayed(sf.shopCartObj.footerRecurringTcvPrice)
					&& sf.seleU.isElementDisplayed(sf.shopCartObj.totalTcvPrice)
					&& sf.seleU.isElementDisplayed(sf.shopCartObj.monthlyRecurringPrice)) {
				reportStatusPass(methodName+ "Recurring TCV Price, Total TCV Price & Monthly Recurring charge Price is displayed. ", true,true);
				double recurringTCVPrice = Double.parseDouble(sf.seleU.getTextFromWebElement(sf.shopCartObj.footerRecurringTcvPrice).replaceAll("[^0-9.]", ""));
				double totalTCVPrice = Double.parseDouble(sf.seleU.getTextFromWebElement(sf.shopCartObj.footerRecurringTcvPrice).replaceAll("[^0-9.]", ""));
				double monthlyRecurringPrice = Double.parseDouble(sf.seleU.getTextFromWebElement(sf.shopCartObj.footerRecurringTcvPrice).replaceAll("[^0-9.]", ""));
				//Validating the price before discount applied and after discount applied
				if ((price[0] * count) == (recurringTCVPrice + (discount * count))
						&& (price[1] * count) == (totalTCVPrice + (discount * count))
						&& (price[2] * count) == (monthlyRecurringPrice + (discount * count))) {
					reportStatusPass(methodName+ "Recurring TCV Price, Total TCV Price & Monthly Recurring charge Price are calculated correctly. ",true, true);
				} else {
					reportStatusFail("Recurring TCV Price, Total TCV Price & Monthly Recurring charge Price are not calculated correctly. ",true);
				}

			} else {
				reportStatusFail("Recurring TCV Price, Total TCV Price & Monthly Recurring charge Price is not displayed on Shopping Cart Page! ",true);
			}

		} // try close
		catch (Exception e) {
			reportStatusFail("Error in validating Recurring TCV Price, Total TCV Price & Monthly Recurring charge Price on WACC Shopping Cart page!",true);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 29.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to get footer Price on the Shopping Cart Page.
	 * 
	 * @throws Exception
	 * 
	 * 
	 */
	public double[] getFooterPrice() throws Exception {
		// TODO Auto-generated method stub
		double[] price = new double[3];
		try {
			methodName = "WACC Shopping Cart Page@: ";
			sf.seleU.hardwait(2000);
			sf.seleU.waitForLoading();

			// check Flat Text Discount applied in Footer
			if (sf.seleU.isElementDisplayed(sf.shopCartObj.footerRecurringTcvPrice)
					&& sf.seleU.isElementDisplayed(sf.shopCartObj.totalTcvPrice)
					&& sf.seleU.isElementDisplayed(sf.shopCartObj.monthlyRecurringPrice)) {
				reportStatusPass(methodName
						+ "Recurring TCV Price, Total TCV Price & Monthly Recurring charge Price is displayed. ", true,
						true);
				price[0] = Double.parseDouble(sf.seleU.getTextFromWebElement(sf.shopCartObj.footerRecurringTcvPrice).replaceAll("[^0-9.]", ""));
				price[1] = Double.parseDouble(sf.seleU.getTextFromWebElement(sf.shopCartObj.footerRecurringTcvPrice).replaceAll("[^0-9.]", ""));
				price[2] = Double.parseDouble(sf.seleU.getTextFromWebElement(sf.shopCartObj.footerRecurringTcvPrice).replaceAll("[^0-9.]", ""));

			} else {
				reportStatusFail(
						"Recurring TCV Price, Total TCV Price & Monthly Recurring charge Price is not displayed on Shopping Cart Page! ",
						true);
			}

		} // try close
		catch (Exception e) {
			reportStatusFail("Error in clicking on Shop Product button on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
		return price;
	}
	/**
	 * Method developed on @Date: 23.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to Verify Flat discounting on quote on the Shopping Cart Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void validatePriceAfterDiscountApplied(double discount,int count, int i) throws IOException {
		// TODO Auto-generated method stub
		try {
			methodName = "WACC Shopping Cart Page@: ";
			sf.seleU.hardwait(2000);
			double unitPrice=Double.parseDouble(sf.seleU.getTextFromWebElement(sf.shopCartObj.productUnitPriceLst.get(i)).replaceAll("[^0-9.]", ""));
			double discountedUnitPrice=Double.parseDouble(sf.seleU.getTextFromWebElement(sf.shopCartObj.flatDiscountUnitPrice.get(i)).replaceAll("[^0-9.]", ""));
			
			if(discountedUnitPrice + discount == unitPrice) {
				
				double disLineTotal = Double.parseDouble(sf.seleU.getTextFromWebElement(sf.shopCartObj.flatDiscountLineTotal.get(i)).replaceAll("[^0-9.]", ""));
				double lineTotal = Double.parseDouble(sf.seleU.getTextFromWebElement(sf.shopCartObj.productLineTotalLst.get(i)).replaceAll("[^0-9.]", ""));
				if(sf.shopCartObj.addOnLst.size()>0) {
					String quantityInput = sf.seleU.getTextFromWebElement(driver.findElement(By.xpath("//div[contains(text(),'Fin Business')]/../../following-sibling::div//*[@class='slds-input slds-combobox__input']")));
					int temp = Integer.parseInt(quantityInput);
					double addOnTotal = Double.parseDouble(sf.seleU.getTextFromWebElement(sf.shopCartObj.addOntotalPriceLst.get(i)).replaceAll("[^0-9.]", ""));
					lineTotal=lineTotal+(addOnTotal-(temp*discount));
					
					Assert.assertEquals(disLineTotal, lineTotal, 0);
					reportStatusPass(methodName+ "Discount has applied. ", true,true);	
				}
				else {
					Assert.assertEquals((disLineTotal+(count*discount)), lineTotal, 0);									
					reportStatusPass(methodName+ "Discount has applied. ", true,true);	
				}								
			}
			else{
				reportStatusFail("Discount has not applied on WACC Shopping Cart page!", true);
			}
		
		} // try close
		catch (Exception e) {
			reportStatusFail("Error in applied Discount in product line on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 05.01.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate DP is part MRC or OTC based product in Cart on the Shopping Cart Page.
	 * @throws Exception 
	 * 
	 */
	public void validate_ProductCostType(String item) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "WACC Shopping Cart Page@: ";
			if(item.equalsIgnoreCase("Device Protection")) {
				WebElement eleMonthly=driver.findElement(By.xpath("(//div[contains(text(),'Monthly')]/following-sibling::div[1]//*[contains(text(),'" + item + "')])[1]"));
				if(sf.seleU.isElementDisplayed(eleMonthly)) {
					reportStatusPass(methodName+ " item is part of MRC based product. ", true,true);
				}
			}
			else {
				WebElement eleOneTime=driver.findElement(By.xpath("//div[contains(text(),'One-time')]/following-sibling::*//div[contains(text(),'" + item + "')]"));
				if(sf.seleU.isElementDisplayed(eleOneTime)) {
					reportStatusPass(methodName+ " item is part of OTC based Product. ", true,true);
				}
			}
		}
		catch (Exception e) {
			reportStatusFail("Error in applied Discount in prpduct line on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 05.01.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate DP Name and Price in Cart on the Shopping Cart Page.
	 * @throws Exception 
	 * 
	 */
	public void validate_DPName_Price(String dpName, String dpCost, int quantity) throws Exception {
		try {
			methodName = "WACC Shopping Cart Page@: ";
			if(InputData_WA.WACC_DeviceProtectionName.equalsIgnoreCase("Apple Care")) {
				verify_OneTimeCost_itemName_Price(dpName, dpCost, quantity);
				reportStatusPass(methodName+ " item is part of OTC based Product. ", true,true);
			}
			else {
				verify_MonthlyProductName_Price(dpName, dpCost, quantity);
			}
		}
		catch (Exception e) {
			reportStatusFail("Error in DP Name and price value on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}
	
	/*
	 * Verifying the Finance
	 */
	
	public void validate_Finance_Plan(String financeMonth) throws IOException {
		
		String path = "//span[contains(text(),'"+financeMonth+"')]";
		try {
		String capturedText= sf.seleU.getTextFromWebElement(By.xpath(path));
		Assert.assertEquals("Shipping Cart  : Actual Finance plan is "+capturedText+" and Expected is "+financeMonth+"", capturedText.trim(), financeMonth.trim());
		}catch(Exception e) {
			reportStatusFail(" Finance Month is mismatch in  WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 20.01.2022 by @author viswas reddy
	 * 
	 * Method to get accessory price.
	 * @throws Exception 
	 */
	public double get_accPrice(String name) throws Exception {
		String str = "";
		str = sf.seleU.getTextFromWebElement(driver.findElement(By.xpath("(//div[contains(text(),'"+name+"')]/following-sibling::div/*[contains(text(),'$')])[1]")));
		return Double.parseDouble(str.replace("$", ""));
	}
	
	/**
	 * Method developed on @Date: 21.01.2022 by @author viswas reddy
	 * 
	 * Method to get flat discounting price.
	 * @throws Exception 
	 */
	public double get_flatDiscountPrice() throws Exception {
		String str = "";	
		str = sf.seleU.getTextFromWebElement(sf.shopCartObj.flatDiscount);
		return Double.parseDouble(str.replaceAll("[^0-9.]", ""));
	}
	/**
	 * Method developed on @Date: 28.01.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate added Accessory name and price in Cart on the Shopping Cart
	 * Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void verify_AccessoryName_Price(String item, String price, int quantity) throws Exception {
		try {
			methodName = "WACC Shopping Cart Page@: ";

			WebElement eleItem = driver.findElement(By.xpath("(//*[contains(text(),'" + item + "')])[1]"));
			WebElement eleUnitPrice = driver.findElement(By.xpath("//div[contains(text(),'" + item+ "')]/../following-sibling::div/*[contains(text(),'$" + price + "') or contains(text(),'" + price + "$')]"));
			WebElement eleQuantity = driver.findElement(By.xpath("//div[contains(text(),'" + item + "')]/../following-sibling::div[2]"));
			WebElement eleLinePrice = driver.findElement(By.xpath("//div[contains(text(),'" + item + "')]/../following-sibling::div[3]"));
			sf.seleU.ScrolltoElement(eleItem);
			if(validate_itemAndPrice(eleItem, eleUnitPrice, eleQuantity, eleLinePrice, item, price, quantity))
				reportStatusPass(methodName+ " Accessory Name "+item +" And Accessory Price "+ price+" displayed.", true,true);
		} catch (Exception e) {
			reportStatusFail("Error in add item value on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 31.01.2022 by @author viswas reddy
	 * 
	 * Update the accessories qty in cart.
	 * 
	 * @throws Exception
	 */
	public void updateCartQtyPlansAcce(String updateQTY) throws Exception {
		String oldMonthlyPrice, oldOneTimePrice, newMonthlyPrice, newOneTimePriceString;
		try {
			sf.seleU.waitTillLoading();
			if (sf.shopCartObj.monthlyPriceTotal.isDisplayed()) {
				oldMonthlyPrice = sf.shopCartObj.monthlyPriceTotal.getText();
				oldOneTimePrice = sf.shopCartObj.OneTimeprice.getText();
				sf.seleU.ScrolltoElement(sf.shopCartObj.myCartHeader);
				sf.seleU.wait(800);
				sf.seleU.clickElementByJSE(sf.shopCartObj.updateQuantityDropdownAcce);
				Iterator<WebElement> i1 = sf.shopCartObj.updateQuantityDropdownOptionsAcce.iterator();
				while (i1.hasNext()) {
					WebElement ele = i1.next();
					String dropDownOptions = ele.getAttribute("data-value");
					if (dropDownOptions.equalsIgnoreCase(updateQTY)) {
						String newDropDownval = ele.getAttribute("data-value");
						sf.seleU.clickElementByJSE(ele);						
						sf.seleU.hardwait(2000);
						sf.seleU.waitForLoading();
						Assert.assertNotSame("Failed to update Quantity Dropdown!", newDropDownval, dropDownOptions);
						break;
					}
				}
				sf.seleU.waitTillLoading();
				sf.seleU.wait(1500);
				newMonthlyPrice = sf.shopCartObj.monthlyPriceTotal.getText();
				newOneTimePriceString = sf.shopCartObj.OneTimeprice.getText();
				Assert.assertNotSame("Post Quantity update monthly prices are same", newMonthlyPrice, oldMonthlyPrice);
				Assert.assertNotSame("Post Quantity update One time prices are same", newOneTimePriceString,
						oldOneTimePrice);
				reportStatusPass("Updated the cart quantity!", true, true);
			}
		} catch (Exception e) {
			reportStatusFail("Failed to Update the cart quantity!", e);
			e.printStackTrace();
		}
	}
	
	/**
	* Method developed on @Date: 02.02.2022 by @author Priyanka Tawade
	*
	* To Click proceed To Shopping Cart
	*
	* @throws Exception
	*
	*/
	public void click_ProceedToShopCartBtn() throws Exception {
		try {
			methodName = "WACC click shop cart for proceed to summary page @: ";
		sf.seleU.waitTillLoading();
		Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.shopCartObj.proceedToShoppingCart));
		sf.seleU.clickElementByJSE(sf.shopCartObj.proceedToShoppingCart);
		sf.seleU.wait(2000);
		reportStatusPass(methodName + "Proceed To Shopping Cart button is clicked", true, true);
		} catch (Exception e) {
		reportStatusFail("Error is validating..", e);
		e.printStackTrace();
		}
		}
	
	/**
	 * Method developed on @Date: 10.02.2022 by @author Shruti Desai
	 * 
	 * verifying the The order of the accordion sections should be when device first in the shopping cart
	 * 
	 */
	public void validateAccordionSeqDeviceFirstShoppingCart(String[] Input) throws Exception {
		try {
			sf.seleU.waitTillLoading();
			sf.seleU.wait(1500);
			List <WebElement> monthlyFeesNamelstShoppingCart = sf.shopCartObj.monthlyFeeNameLst;
			String[] inputDataArray = new String[Input.length];
			int a=0;
			sf.seleU.waitTillLoading();
			outer:
				for(WebElement actualTxt:monthlyFeesNamelstShoppingCart) {
					inner:
					for(int i=a;i<Input.length;) {
						if(i==inputDataArray.length) {
							break outer;
						}
						if (actualTxt.getText().contains(Input[i])) {
							reportStatusPass(actualTxt.getText(), true, true);
							break inner;
						}
						
						else {  break inner;  }
					}
					a++;
				}
			
			if(sf.shopCartObj.updateQuantityDropdown1.isDisplayed()) {
				reportStatusPass("Quatntity update button is present",true,true);
			}else{
				reportStatusFail("Quatntity update button is not present", true);
			}			
		} catch (Exception e) {
			reportStatusFail("failed To Verify first 3 sequences of Accordion Seq", e);
		}
	}

	public void verifyFullPriceDeviceInTheOneTimeFeeSection_ShoppingCartPage() throws IOException, InterruptedException {
		WebElement fullPriceDevice =sf.shopCartObj.oneTimeFeeNameLst.get(1);
		if(fullPriceDevice.isDisplayed()){
			reportStatusPass("Full Price Device is part of One-Time Fee section on Shopping cart page.", true,true);
		}else {
			reportStatusFail("Full Price Device is part of One-Time Fee section on Shopping cart page.", true);
		}
		
	}	
	/**
	 * Method developed on @Date: 18.02.2022
	 * 
	 * Method to validate added items name and price in Cart on the Shopping Cart
	 * Page .
	 * 
	 * @throws Exception
	 * 
	 */
	public void verify_AccessoryName_Price_Fit(String item, String price, int quantity) throws Exception {
		try {
			methodName = "WACC Shopping Cart Page@: ";

			WebElement eleItem = driver.findElement(By.xpath("(//div[contains(text(),'" + item + "')])[1]"));
			WebElement eleUnitPrice = driver.findElement(By.xpath("//div[contains(text(),'" + item+ "')]/../following-sibling::div[1]"));
			WebElement eleQuantity = driver.findElement(By.xpath("//div[contains(text(),'" + item + "')]/../following-sibling::div[2]"));
			WebElement eleLinePrice = driver.findElement(By.xpath("//div[contains(text(),'" + item + "')]/../following-sibling::div[3]"));
			sf.seleU.ScrolltoElement(eleItem);
			validate_itemAndPrice(eleItem, eleUnitPrice, eleQuantity, eleLinePrice, item, price, quantity);
		} catch (Exception e) {
			reportStatusFail("Error in add item value on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}	
	/**
	 * Method developed on @Date: 18.02.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate System SetUp Fee is not strike-Out in Cart on the Shopping Cart
	 * Page.
	 * @throws Exception
	 * 
	 */
	public void verify_SystemSetUpFeeNotStrickedOut() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "WACC Shopping Cart Page@: ";
			sf.seleU.hardwait(5000);
			// check System Setup Fee Discount not Present and Setup fee waived text is not present.
			// check original price has crossed out
			if (!setupFeeWaivedPresent() && !sf.shopCartObj.systemSetUpPriceList.get(0).getCssValue("text-decoration").contains("line-through")
					&& !sf.shopCartObj.systemSetUpPriceList.get(1).getCssValue("text-decoration").contains("line-through")) {
				reportStatusPass(methodName + " Setup fee waived text is not present and System Setup Fee Unit price & Line Total has not striked out!", true,true);
				// check price not in red color
				if (!sf.shopCartObj.systemSetUpPriceList.get(0).getCssValue("color").equals("rgba(218, 41, 28, 1)")
						&& !sf.shopCartObj.systemSetUpPriceList.get(0).getCssValue("color").equals("rgba(218, 41, 28, 1)")) {
					reportStatusPass(methodName + " System Setup Fee Unit price & Line Total are not in Red Color!", true,true);
				} // check price is not in red color if close
				else {
					reportStatusFail("Setup fee waived text is present/System Setup Fee Unit price/Line Total are in Red Color! on WACC Shopping Cart page!", true);
				}
			}else {
				reportStatusFail("System Setup Fee Unit price & Line Total has striked out! on WACC Shopping Cart page!", true);
			}
		}catch (Exception e) {
			reportStatusFail("Error in Validating System Setup Fee on WACC Shopping Cart page!", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 18.02.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate Setup fee waived text is not present in Cart on the Shopping Cart
	 * Page.
	 * @throws Exception
	 * 
	 */
	public boolean setupFeeWaivedPresent() {
		try {
			return sf.seleU.isElementDisplayed(sf.shopCartObj.setupFeeWaivedText);
		}catch (Exception e) {	
			return false;
		}
	}
	/**
	 * Method developed on @Date: 18.02.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate Shopping Cart Page is displayed.
	 * @throws Exception
	 * 
	 */
	public void validate_ShoppingCartPage_Displayed() throws Exception {
		try {
			sf.seleU.waitForLoading();
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.shopCartObj.shoppingCartHeader))
				reportStatusPass("Shopping Cart page is displayed", true,true);
		}catch (Exception e) {	
			reportStatusFail("Shopping Cart page is not displayed", true);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 17.3.2022 by @author Jigyasa Dwivedi
	 * Method to get Device Capacity
	 * @throws Exception 
	 * 
	 */
	public void validateRelatedDeviceDetailsForDP(String dpName, String deviceBrand, String deviceCapacity, String deviceColor) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "WACC Shopping Cart Page@: ";
			String dpDetails = null, dName= null;
			if(dpName.equalsIgnoreCase("Device Protection")) {
				dpDetails = sf.seleU.getTextFromWebElement(sf.shopCartObj.dpDetails);
			}else {
				dpDetails = sf.seleU.getTextFromWebElement(sf.shopCartObj.appleCareDetails);
			}
			if(deviceBrand.equalsIgnoreCase("Apple"))
				dName="iPhone";
			else
				dName="Android";
			
			if(dpDetails.contains(deviceBrand) && dpDetails.contains(dName) 
					&& dpDetails.contains(deviceCapacity) && dpDetails.contains(deviceColor)) {
				reportStatusPass(methodName+"Device Brand, Device Name, Device Capacity and Device Color are displayed under DP. ", true,true);
			}else {
				reportStatusFail(methodName+"Device Brand, Device Name, Device Capacity and Device Color are  not displayed under DP. ", true);
			}
		}catch (Exception e) {	
			reportStatusFail("error is displayed to validate device details under dp on Shopping Cart page. ", true);
			e.printStackTrace();
    }
  }
	
	/**
	 * Method developed on @Date: 09.03.2022 by @author Priyanka Tawade
	 * 
	 * Method to verify item name.
	 * 
	 * @throws Exception
	 * 
	 */
	public boolean verify_itemName(String item) throws Exception {
		// TODO Auto-generated method stub
		methodName = "WACC Shopping Cart Page@: ";
		try {
			sf.seleU.hardwait(3000);
			sf.seleU.waitForLoading();
			boolean b = sf.seleU.isElementDisplayed(driver.findElement(By.xpath("(//*[contains(text(),'" + item + "')])[1]")));
			reportStatusPass(item, true, true);
			return b;
		}
		catch (Exception e) {	
			reportStatusFail(item+ "is not displayed", false);
			return false;
		}
	}
	
	/**
	 * Method developed on @Date: 09.03.2022 by @author Priyanka Tawade
	 * 
	 * Method to verify item name.
	 * 
	 * @throws Exception
	 * 
	 */
	public void verify_PricePlanFirst() throws Exception {
		// TODO Auto-generated method stub
		methodName = "WACC Shopping Cart Page@: ";
		try {
			sf.seleU.hardwait(3000);
			sf.seleU.waitForLoading();
			if(verify_itemName(InputData_WA.WACC_Plan_Size)) {
				reportStatusPass("1. " +InputData_WA.WACC_Plan_Size+ " Price plan is displayed First", true, true);	
			}
			if(verify_itemName(InputData_WA.WACC_AddOn_Name)) {
				reportStatusPass("2. " +InputData_WA.WACC_AddOn_Name+ " is displayed ", true, true);	
			}
			if(verify_itemName(InputData_WA.WACC_DeviceName)) {
				reportStatusPass("3. " +InputData_WA.WACC_DeviceName+ " is displayed ", true, true);	
			}			
		}
		catch (Exception e) {	
			reportStatusFail("Price plan not displayed first", false);
		}
	}
	
	/**
	* Method developed on @Date: 11.03.2022 by @author Priyanka Tawade
	*
	* To click show more details for seeing finance term of device
	*
	* @throws Exception
	*
	*/
	public void clickShowMoreDetails(String term) throws Exception {

		try {
			methodName = "WACC Click show more details for seeing finance term @: ";
		sf.seleU.waitTillLoading();
		Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.shopCartObj.hideDetailsDevice));
		sf.seleU.clickElementByJSE(sf.shopCartObj.hideDetailsDevice);
		Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.shopCartObj.showMoreDetailsLink));
		sf.seleU.clickElementByJSE(sf.shopCartObj.showMoreDetailsLink);
		Assert.assertEquals(true, sf.seleU.isElementDisplayed(driver.findElement(By.xpath("(//*[contains(text(),'" + term + "')])[1]"))));
		sf.seleU.wait(2000);
		reportStatusPass(methodName + "User can see finance term of device.." +term , true, true);
		} catch (Exception e) {
		reportStatusFail("User cannot able to see finance term", e);
		e.printStackTrace();
		}
	}
	
	/**
	* Method developed on @Date: 15.03.2022 by @author Priyanka Tawade
	*
	* To verify Discounted and Original Price of device
	*
	* @throws Exception
	*
	*/
	public void verify_DiscountDevices() throws Exception {

		try {
			methodName = "WACC Shopping Cart Page@:  ";
		sf.seleU.waitTillLoading();
		String originalPrice = sf.seleU.getTextFromWebElement(sf.shopCartObj.originalPrice);
		String discountPrice = sf.seleU.getTextFromWebElement(sf.shopCartObj.discountedPrice);
		
		if(originalPrice.contains(InputData_WA.WACC_DeviceCost) && discountPrice.contains(InputData_WA.DiscountDevice.DiscountDevicePriceForIphone11.getDiscountDevicePrice())) {
		//Assert.assertEquals(true, originalPrice);
		//Assert.assertEquals(true, discountPrice);
		reportStatusPass(methodName + "User can see Original price and Discounted price of device = " +originalPrice+ " " +discountPrice , true, true);
		}
		
		} catch (Exception e) {
		reportStatusFail("User cannot able to see  Original price and Discounted price of device", e);
		e.printStackTrace();
		}
	}
	
	/**
	* Method developed on @Date: 23.03.2022 by @author Priyanka Tawade
	*
	* To validate credit Available for plan and devices
	*
	* @throws Exception
	*
	*/
	public void selectCreditAvailable(String value)  throws Exception{
	
		try {
			  methodName = "WACC Shopping Cart Page@:  ";
			  sf.seleU.waitTillLoading();
			  sf.seleU.hardwait(2000);
			  WebElement credit = driver.findElement(By.xpath("//input[contains(@type,'radio')][contains(@value,'" +value+ "')][1]"));
			  if(sf.seleU.isElementDisplayed(credit)) { 
			  sf.seleU.clickElementByJSE(credit);
			  sf.seleU.hardwait(2000);
			  sf.seleU.clickElementByJSE(sf.shopCartObj.applyOfferButton);
			  reportStatusPass(methodName + "" +value+ 
					  " Credit is selected for plan and devices and Apply Offer button Is clicked", true, true);
			  
			  }
			} catch (Exception e) {
		reportStatusFail("User cannot able to select credits", e);
		e.printStackTrace();
		}
	}	
	
	//Accessories Brand Name validation removed
	public boolean validate_itemAndPrice( WebElement eleUnitPrice, WebElement eleQuantity,WebElement eleLinePrice, String item, String price, int quantity) throws Exception {
		try {
			if (sf.seleU.isElementDisplayed(eleUnitPrice) && sf.seleU.isElementDisplayed(eleQuantity)) {
				double linePrice = Double.parseDouble(sf.seleU.getTextFromWebElement(eleLinePrice).replaceAll("[^0-9.]", ""));
				double temp =Double.parseDouble(price)*(double)quantity;
				//quantity == Integer.parseInt(sf.seleU.getTextFromWebElement(eleQuantity)) 
				if(linePrice== temp) {
					return true;
				}					
			} else {
				reportStatusFail(item + price + " has not present on WACC Shopping Cart page!", true);
				return false;
			}
		} catch (Exception e) {
			reportStatusFail("Error in add item value on WACC Shopping Cart page!", true);
			e.printStackTrace();				
		}
		return false;
	}
}