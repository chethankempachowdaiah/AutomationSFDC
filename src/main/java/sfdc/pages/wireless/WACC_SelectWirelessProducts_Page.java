package sfdc.pages.wireless;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.HashMap;
import java.util.List;


import org.apache.commons.collections4.map.HashedMap;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.framework.base.MyListener;
import com.framework.utilities.VerificationUtilities;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Sakshi.Lnu
 * 
 *
 */
public class WACC_SelectWirelessProducts_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static SFDC_AllPages sfdc;
	public static String methodName;

	public WACC_SelectWirelessProducts_Page() {
		sf = new SFDC_AllPageObjects();
		
	}

	/**
	 * @throws IOException
	 * 
	 *             1- Validate Products page 2- click shop Wireless Plans Link
	 * 
	 * 
	 */
	public void verifyWirelessProducts() throws IOException {

		try {
			//sf.seleU.waitTillLoading();
			sf.seleU.waitForLoading();
			methodName = "WACC_Products Page Validation@: ";
			// 1- Verify Products Page Objects
			sf.seleU.waitForLoading();
			// if
			// ((sf.seleU.getTextFromWebElementWithYellowHighlight(sf.proSel.productsHeader).equals(InputData_WA.productsHeader))
			// &&
			// (sf.seleU.getTextFromWebElementWithYellowHighlight(sf.proSel.productSubHeader).equals(InputData_WA.productsSubHeader)))
			// {
			//
			// reportStatusPass(methodName + "Products header and Subheader matched", true,
			// true);
			// } else {
			// reportStatusFail(methodName + " failed in matching products header and
			// subheader", true);
			// }
			sf.seleU.scrollToBottom();
			sf.seleU.waitForLoading();
			// click on Shop Wireless plan button t
			sf.seleU.clickElementByJSE(sf.proSel.shopWirelessPlansButton);
			// sf.seleU.waitTillLoading();
			sf.seleU.waitForLoading();
			// verify if users is on price plan screen
			if (sf.seleU.isElementDisplayed(sf.proSel.wirelessPlansHeader)) {
				reportStatusPass(methodName + "Shop wireless plan button is clicked", true, true);
			} else {

				reportStatusFail(methodName + "Error on clicking shop wireless plan button", true);
			}
			sf.seleU.wait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Invalid Products Selection Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             1- Validate Plan selection page
	 * 
	 * 
	 * 
	 */
	public void verifyPlansSelection() throws IOException {

		try {
			methodName = "WACC_Products Price-Plans Validation@: ";
			// 1- Verify Products Page Objects
			sf.seleU.waitForLoading();
			sf.seleU.hardwait(2000);
			if (sf.seleU.getTextFromWebElement(sf.proSel.wirelessPlansHeader).contains(InputData_WA.productsPlanHeader)
					&& sf.seleU.getTextFromWebElement(sf.proSel.wirelessPlansSubHeader)
							.contains(InputData_WA.productsPlanSubHeader)) {
				reportStatusPass(methodName + "User lands on Price plan page ", true, true);
			} else {
				reportStatusFail(methodName + " User doesn't land on Price plan page", true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Error on validating  Plans Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *             1- verify Voice and Data Plan type, Price 2- verify Data only
	 *             Plans type, Price
	 * 
	 */

	public void validateDataPlans() throws Exception {
		try {
			methodName = "WACC_Products Price-Plans Validation@: ";
			sf.seleU.waitForLoading();
			sf.seleU.hardwait(2000);
			// 1- Verify Products Page Objects
			if (sf.seleU.getTextFromWebElement(sf.proSel.wirelessPlansHeader).contains(InputData_WA.productsPlanHeader)
					&& sf.seleU.getTextFromWebElement(sf.proSel.wirelessPlansSubHeader).toString()
							.contains(InputData_WA.productsPlanSubHeader)) {
				reportStatusPass(methodName + "User lands on Price plan page ", true, true);
			} else {
				reportStatusFail(methodName + " User doesn't land on Price plan page", true);
			}

			// click on Voice and Data tab
			if (InputData_WA.WACC_Data_Type.equals(InputData_WA.voiceAndData)) {
				sf.seleU.clickElementByJSE(sf.proSel.voiceAndDataTab);
				reportStatusPass(methodName + "Plan selected : " + InputData_WA.WACC_Data_Type, true, false);
			}

			// click on Data Only tab
			else if (InputData_WA.WACC_Data_Type.equalsIgnoreCase(InputData_WA.dataOnly)) {
				sf.seleU.clickElementByJSE(sf.proSel.dataOnlyTab);
				reportStatusPass(methodName + " Plan selected : " + InputData_WA.WACC_Data_Type, true, false);
			} else {
				reportStatusFail(methodName + "No appropriate data plan is selected", true);
			}
			if (sf.seleU.getTextFromWebElement(sf.proSel.planValue).equals(InputData_WA.WACC_Plan_Size)) {
				reportStatusPass(methodName + " Plan value validated: " + InputData_WA.WACC_Plan_Size, true, false);
			} else {
				reportStatusFail(methodName + " error in validating plan value : " + InputData_WA.WACC_Plan_Size, true);
			}

			if (sf.seleU.getTextFromWebElement(sf.proSel.priceValue).equals(InputData_WA.WACC_Plan_Price)) {
				reportStatusPass(methodName + " Price Plan value validated for : " + InputData_WA.WACC_Data_Type + " "
						+ InputData_WA.WACC_Plan_Price, true, false);
			} else {
				reportStatusFail(methodName + " error in validating price plan  for : " + InputData_WA.WACC_Data_Type
						+ " " + InputData_WA.WACC_Plan_Price, true);
			}

			if (sf.seleU.isElementDisplayed(sf.proSel.postCreditAmountNote)) {
				if (sf.seleU.getTextFromWebElement(sf.proSel.postCreditAmountNote)
						.contains(InputData_WA.WACC_Credit_Amount_Available)) {
					reportStatusPass(methodName + " Post tax Credit amount is present upto:  "
							+ InputData_WA.WACC_Credit_Amount_Available, true, false);
				} else {
					reportStatusFail(methodName + " validation failed for credit present ", true);
				}
			}
		} catch (Throwable e) {
			reportStatusFail(" Invalid Plans Selection Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *             validate "Add to Cart" is disabled when no plan is selected
	 * 
	 */
	public void disabledAddToCart() throws Exception {
		try {
			methodName = "WACC Disabled Options Validation@: ";
			sf.seleU.highLightElement(sf.proSel.chosePlanTypeText);
			if (!((sf.proSel.standaloneOption.isSelected()) && (sf.proSel.pooledOption.isSelected()))) {
				// sf.seleU.ScrolltoElement(sf.proSel.disabledAddToCart);
				// Assert.assertEquals(true,
				// sf.seleU.isElementDisplayed(sf.proSel.disabledAddToCart));
				// sf.seleU.ScrolltoElement(sf.proSel.disabledAddOns);
				// Assert.assertEquals(true,
				// sf.seleU.isElementDisplayed(sf.proSel.disabledAddOns));
				if (sf.seleU.isElementDisplayed(sf.proSel.disabledAddToCart)
						&& sf.seleU.isElementDisplayed(sf.proSel.disabledAddOns)) {
					reportStatusPass(
							methodName + "Add to cart and AddOns button are Disabled before selecting any plan type ",
							true, true);
				} else {
					reportStatusFail(methodName + "Error in verifying disabled buttons", true);
				}
			}
			sf.seleU.wait(1000);
		}
		catch (Throwable e) {
			reportStatusFail(" Error in validating disable buttons on Plans selection page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *             1- validate "Add to Cart" is enabled if any of plan type
	 *             ("Standalone", "Pooled") is selected 2- If no plan is selected,
	 *             Select either "Standalone" or "pooled"
	 * 
	 */
	public void selectPlanType() throws Exception {
		try {
			methodName = "WACC Data Plan Type(Standalone/Pooled) Selection Validation@: ";

			sf.seleU.highLightElement(sf.proSel.chosePlanTypeText);
			if (InputData_WA.WACC_Plan_Type.equals(InputData_WA.plan_typeStandalone)
					&& !(sf.proSel.standaloneOption.isSelected())) {
				sf.seleU.clickElementByJSE(sf.proSel.standaloneOption);
				Assert.assertEquals(false, sf.seleU.isElementDisplayed(sf.proSel.disabledAddToCart));
				reportStatusPass(
						methodName + "Add to cart is Enabled and Plan type is selected " + InputData_WA.WACC_Plan_Type,
						true, true);
			} else if (InputData_WA.WACC_Plan_Type.equals(InputData_WA.plan_typePooled)
					&& !(sf.proSel.pooledOption.isSelected())) {
				sf.seleU.clickElementByJSE(sf.proSel.pooledOption);
				Assert.assertEquals(false, sf.seleU.isElementDisplayed(sf.proSel.disabledAddToCart));
				reportStatusPass(
						methodName + "Add to cart is Enabled and Plan type is selected " + InputData_WA.WACC_Plan_Type,
						true, true);
			} else {
				reportStatusFail(methodName + "Error on clicking any plan type", true);
			}
			sf.seleU.wait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Error in selecting plan types", e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @throws Exception
	 * 
	 *             Click on Add to cart option to Add Data plans and next page is
	 *             loaded Verify Add to cart button is clicked, Continue to AddOns
	 *             button is active, Price of plan is added at footer section in TCV
	 *             value
	 * 
	 * 
	 */
	public void clickOnPlansAddToCartAndVerifyFooterPrice() throws Exception {
		try {
			methodName = "WACC_Plans Add to Cart Validation@: ";
			// click on add to cart button
			sf.seleU.clickElementByJSE(sf.proSel.addToCart);
			sf.seleU.waitForLoading();
			Assert.assertEquals(true, sf.seleU.isElementDisplayedWithYellowHighlight(sf.proSel.continueToAddOns));
			reportStatusPass(methodName + "Add to cart  button is clicked", true, true);
			Assert.assertEquals(true, sf.proSel.continueToAddOns.isDisplayed());
			reportStatusPass(
					methodName + sf.seleU.getTextFromWebElement(sf.proSel.continueToAddOns) + " button is enabled",
					true, true);
			sf.seleU.waitForLoading();
			System.out.println(sf.seleU.getTextFromWebElement(sf.proSel.footerRecurringTcvPrice) + "and "
					+ InputData_WA.WACC_Plan_Price);
			System.out.println(
					sf.seleU.getTextFromWebElement(sf.proSel.totalCostsValue) + "and " + InputData_WA.WACC_Plan_Cost);
			sf.seleU.waitForLoading();
			if (sf.seleU.getTextFromWebElement(sf.proSel.footerRecurringTcvPrice).contains(InputData_WA.WACC_Plan_Price)
					&& sf.seleU.getTextFromWebElement(sf.proSel.totalCostsValue)
							.contains(InputData_WA.WACC_Plan_Cost)) {
				reportStatusPass(methodName + "Total price for plan selected is "
						+ sf.seleU.getTextFromWebElement(sf.proSel.footerRecurringTcvPrice) + " Total cost for plan is "
						+ sf.seleU.getTextFromWebElement(sf.proSel.totalCostsValue) + " Matched ", false, true);
			} else {
				reportStatusFail(
						methodName + " Error on Validating Price Value at footer after clicking on add to cart ", true);
			}
			sf.seleU.waitForLoading();
		} catch (Throwable e) {
			reportStatusFail(" error on clicking add to cart option on plans screen", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *             1- Click on Add on button and verified the plan selected
	 */

	public void clickAddOnButtonAndVerifyPage() throws Exception {
		try {
			methodName = "WACC Continue to Add-Ons Screen Validation@: ";
			// Add on button is enabled and click on addon Button
			sf.seleU.clickElementByJSE(sf.proSel.continueToAddOns);
			reportStatusPass(methodName + "continue to add-on  button is clicked", true, true);
			sf.seleU.waitForLoading();
			// verify if chosen plan is displayed on the add on screen
			if (InputData_WA.WACC_Data_Type.equals(InputData_WA.voiceAndData)) {
				System.out.println(sf.seleU.getTextFromWebElement(sf.proSel.productSelected));
				Assert.assertTrue(sf.seleU.getTextFromWebElement(sf.proSel.productSelected)
						.contains("Rogers wireless plan: " + InputData_WA.WACC_Data_Type + " "
								+ InputData_WA.WACC_Plan_Size + " " + InputData_WA.WACC_Plan_Type));
				reportStatusPass(methodName + "Plan selected is validated: "
						+ sf.seleU.getTextFromWebElement(sf.proSel.productSelected), false, true);
			} else {
				Assert.assertTrue(sf.seleU.getTextFromWebElement(sf.proSel.productSelected).contains(
						"Rogers wireless plan: " + InputData_WA.WACC_Data_Type + " " + InputData_WA.WACC_Plan_Size));
				reportStatusPass(methodName + "Plan selected is validated: "
						+ sf.seleU.getTextFromWebElement(sf.proSel.productSelected), false, true);
			}
			sf.seleU.wait(2000);
		} catch (Throwable e) {
			reportStatusFail(" error on verifying Add Ons Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *             1- verify Voice and Data Plan type, Price 2- verify Data only
	 *             Plans type, Price
	 * 
	 */

	public void selectDataPlanAndType(String dataPlanName, String planType, String planSize) throws Exception {
		try {
			//sf.seleU.waitTillLoading();
			sf.seleU.waitForLoading();
			methodName = "WACC_Products Price-Plans Validation@: ";
			
			// 1- Verify Products Page Objects
			// if ((sf.seleU.getTextFromWebElement(sf.proSel.wirelessPlansHeader).toString()
			// .equalsIgnoreCase(InputData_WA.productsPlanHeader))
			// &&
			// (sf.seleU.getTextFromWebElement(sf.proSel.wirelessPlansSubHeader).substring(2).toString()
			// .equalsIgnoreCase(InputData_WA.productsPlanSubHeader))) {
			//
			// reportStatusPass(methodName + "User lands on Price plan page ", true, true);
			// } else {
			// reportStatusFail(methodName + " User doesn't land on Price plan page", true);
			// }

			// click on Voice and Data tab
			if (dataPlanName.equalsIgnoreCase(InputData_WA.voiceAndData)) {
				sf.seleU.clickElementByJSE(sf.proSel.voiceAndDataTab);
				reportStatusPass(methodName + "Plan selected : " + dataPlanName, true, false);
			}

			// click on Data Only tab
			else if (dataPlanName.equalsIgnoreCase(InputData_WA.dataOnly)) {
				sf.seleU.clickElementByJSE(sf.proSel.dataOnlyTab);
				reportStatusPass(methodName + " Plan selected : " + dataPlanName, true, false);
			} else {
				reportStatusFail(methodName + "No appropriate data plan is selected", true);
			}
			WebElement ele= driver.findElement(By.xpath("//div[text()='"+planSize+"']/following-sibling::div//fieldset//span[contains(text(),'"+planType+"')]"));
			WebElement addToCartBtn = driver.findElement(By.xpath("(//div[text()='"+planSize+"']/../following-sibling::div//button)[1]"));
			
			sf.seleU.waitForLoading();
			sf.seleU.ScrolltoElement(ele);
			sf.seleU.waitForLoading();
			sf.seleU.clickElementByJSE(ele);
			reportStatusPass(methodName + " Plan Type selected : " + sf.seleU.getTextFromWebElement(ele), true, true);
			sf.seleU.clickElementByJSE(addToCartBtn);
			sf.seleU.waitForLoading();
			reportStatusPass(methodName + " AddTo Cart button clicked for Plan Type. ", true, true);
			/*
			 * 
			 * if (planType.equalsIgnoreCase("Standalone") &&
			 * !(sf.proSel.standaloneOption.isSelected())) {
			 * sf.seleU.clickElementByJSE(sf.proSel.standaloneOption);
			 * Assert.assertEquals(false,
			 * sf.seleU.isElementDisplayed(sf.proSel.disabledAddToCart));
			 * reportStatusPass(methodName +
			 * "Add to cart is Enabled and Plan type is selected " + planType, true, true);
			 * } else if (planType.equalsIgnoreCase("Pooled") &&
			 * !(sf.proSel.pooledOption.isSelected())) {
			 * sf.seleU.clickElementByJSE(sf.proSel.pooledOption);
			 * //Assert.assertEquals(false,
			 * sf.seleU.isElementDisplayed(sf.proSel.disabledAddToCart));
			 * reportStatusPass(methodName +
			 * "Add to cart is Enabled and Plan type is selected " + planType, true, true);
			 * } else { reportStatusFail(methodName + "Error on clicking any plan type",
			 * true); }
			 * 
			 * 
			 * 
			 * // sf.seleU.wait(2000); // sf.seleU.waitTillLoading();
			 * sf.seleU.ScrolltoElement(sf.proSel.addToCart);
			 * sf.seleU.clickElementByJSE(sf.proSel.addToCart); sf.seleU.waitTillLoading();
			 * // sf.seleU.clickOnElementNumberoftimes(sf.proSel.addToCart, 1); //
			 * sf.seleU.wait(4000);
			 */
		} catch (Throwable e) {
			reportStatusFail(" Invalid Plans Selection Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *             click on Add to cart
	 * 
	 */
	public void clickOnPlansAddToCart() throws Exception {
		try {
			methodName = "WACC_Plans Add to Cart Validation@: ";
			// click on add to cart button
			//sf.seleU.waitTillLoading();
			sf.seleU.waitForLoading();
			sf.seleU.clickElementByJSE(sf.proSel.addToCart);
			sf.seleU.waitForLoading();
			Assert.assertEquals(true, sf.seleU.isElementDisplayedWithYellowHighlight(sf.proSel.continueToAddOns));
			reportStatusPass(methodName + "Add to cart  button is clicked", true, true);
			Assert.assertEquals(true, sf.proSel.continueToAddOns.isDisplayed());
			reportStatusPass(
					methodName + sf.seleU.getTextFromWebElement(sf.proSel.continueToAddOns) + " button is enabled",
					true, true);
			sf.seleU.waitForLoading();
		} catch (Throwable e) {
			reportStatusFail(" error on clicking add to cart option on plans screen", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *             1- Click on Add on button and verified the plan selected
	 */

	public void continueToAddOnsButton() throws Exception {
		try {
			methodName = "WACC Continue to Add-Ons Screen Validation@: ";
			// Add on button is enabled and click on addon Button
			sf.seleU.waitTillLoading();
			sf.seleU.waitForLoading();
			sf.seleU.ScrolltoElement(sf.proSel.continueToAddOns);
			sf.seleU.clickElementByJSE(sf.proSel.continueToAddOns);
			sf.seleU.waitForLoading();
			sf.seleU.waitTillLoading();
			reportStatusPass(methodName + "continue to add-on  button is clicked", true, true);
			sf.seleU.waitForLoading();
			// verify if chosen plan is displayed on the add on screen
			// sf.seleU.wait(5000);
		} catch (Throwable e) {
			reportStatusFail(" error Add Ons Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *             Verify bottom links are present
	 */

	public void verifyBottomLinks() throws Exception {
		try {
			methodName = "WACC Bottom Links Validation@: ";
			// Add on button is enabled and click on addon Button
			sf.seleU.hardwait(5000);
			sf.seleU.scrollToBottom();
			VerificationUtilities.verifyFieldPresent("Ext Feature bottom link", sf.proSel.addExtraFeatBottomLink);
			VerificationUtilities.verifyFieldPresent("Chose device bottom link", sf.proSel.choseDeviceBottomlink);
			VerificationUtilities.verifyFieldPresent("Dev protection bottom link",
					sf.proSel.deviceProtectionBottomlink);
			sf.seleU.isElementDisplayed(sf.proSel.addExtraFeatBottomLink);
			sf.seleU.isElementDisplayed(sf.proSel.choseDeviceBottomlink);
			sf.seleU.isElementDisplayed(sf.proSel.deviceProtectionBottomlink);

		} catch (Throwable e) {
			reportStatusFail(" Error on verifying Bottom links ", e);
			e.printStackTrace();
		}
	}

	public void selectShopeWirelessDevices() throws Exception {
		try {
			methodName = "WACC_Select_Shop_Wirless_Device@: ";
			sf.seleU.waitTillLoading();
			// scroll to shop wireless devices button
			sf.seleU.ScrolltoElement(sf.proSel.shopWirelessDevBtn);
			// click on shop wireless devices button
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.proSel.shopWirelessDevBtn));
			reportStatusPass(methodName + "Shop Wireless Devices button is displayed", true, true);
			sf.seleU.clickElementByJSE(sf.proSel.shopWirelessDevBtn);
			sf.seleU.waitTillLoading();
			reportStatusPass(methodName + "Shop Wireless Devices button is clicked", true, true);
			sf.seleU.wait(5000);
		} catch (Throwable e) {
			reportStatusFail(" error on clicking Shop Wireless devices button on product selection screen", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 16.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Select the Browse accessories button
	 * 
	 */
	public void select_Browse_Accessories() throws Exception {
		try {
			methodName = "WACC Select Product Page@: ";
			if (sf.seleU.isElementDisplayed(sf.proSel.browseAccessories)) {
				sf.seleU.ScrolltoElement(sf.proSel.browseAccessories);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.proSel.browseAccessories);
				sf.seleU.waitForLoading();
				if (sf.seleU.getPageTitle().equalsIgnoreCase("c:buyFlowPersonaBasedMultiLanguage | Salesforce")) {
					sf.seleU.hardwait(2000);
					reportStatusPass(methodName + "Browse Asscssories button has clicked. ", true, true);
				}
			}
			else
				reportStatusFail("Browse Accessories button is not displayed on product selection screen", true);
		} catch (Throwable e) {
			reportStatusFail("Error on clicking Browse Accessories button on product selection screen", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 18.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To select Device Protection option on Rogers Wireless Plans
	 */

	public void selectDeviceProtection(String dProtection) {
		try {
			methodName = "WACC Select Product Page@: ";
			//sf.seleU.waitTillLoading();
			sf.seleU.waitForLoading();
	
			if (sf.seleU.isElementDisplayed(driver.findElement(By.xpath("//span[contains(text(),'"+dProtection +"')]/preceding-sibling::span")))) {
				WebElement eleDP= driver.findElement((By.xpath("//span[contains(text(),'"+dProtection +"')]/preceding-sibling::span")));
				sf.seleU.clickElementByJSE(eleDP);
			}
			else if(sf.seleU.isElementDisplayed(driver.findElement(By.xpath("//span[contains(text(),'"+dProtection +"')]/../preceding-sibling::span")))) {
				WebElement appleDP= driver.findElement(By.xpath("//span[contains(text(),'"+dProtection +"')]/../preceding-sibling::span"));
				sf.seleU.clickElementByJSE(appleDP);
			}
			sf.seleU.waitForLoading();
			sf.seleU.wait(800);			
			reportStatusPass(methodName + "DP has selected. ", true, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 16.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate Cart Banner text and SubText and color after click Add To Cart
	 * button
	 * 
	 */

	public void validate_CartBannerText(String text, String subText) throws Exception {
		try {
			methodName = "Cart Banner Text@: ";
			sf.seleU.scrollToTop();
			sf.seleU.waitTillLoading();
			sf.seleU.waitElementToBeVisible(sf.proSel.addToCartSuccessText);
			if(sf.seleU.isElementDisplayed(sf.proSel.addToCartSuccessText) &&
					sf.seleU.isElementDisplayed(sf.proSel.addToCartSuccessSubText)) {
				//System.out.println(sf.proSel.addToCartSuccessBanner.getCssValue("background-color"));
				String successText = sf.seleU.getTextFromWebElement(sf.proSel.addToCartSuccessText).trim();
				String successSubText = sf.seleU.getTextFromWebElement(sf.proSel.addToCartSuccessSubText).trim();
				String color = sf.proSel.addToCartSuccessBanner.getCssValue("background-color");
				System.out.println(successText);
				System.out.println(successSubText);
				System.out.println(color);
				if(successText.equalsIgnoreCase(text) && successSubText.equalsIgnoreCase(subText) && color.equals("rgba(250, 255, 252, 1)")) {
					sf.seleU.hardwait(2000);
					reportStatusPass(methodName + "After click Add to Cart button Cart Banner Text, SubText and light green color is displayed. ", true, true);
				}
				else {
					reportStatusFail(methodName + "After click Add to Cart button Cart Banner Text and Sub Text is not correct. ", true);
				}
			}
			else
			{
				reportStatusFail(methodName + "After click Add to Cart button Cart Banner Text is not displayed. ", true);
			}
				
		} catch (Throwable e) {
			reportStatusFail(
					" After click Add to Cart button Cart Banner Text, SubText and light green color is not displayed. ",
					e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 16.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate Cart Banner text and SubText after click Add To Cart button
	 * 
	 */

	public void validate_CartBannerTextNotDisplayed() throws Exception {
		try {
			methodName = "Cart Banner Text@: ";
			sf.seleU.scrollToTop();
			sf.seleU.hardwait(2000);
			if(!sf.seleU.isElementDisplayed(sf.proSel.addToCartSuccessText) &&
					!sf.seleU.isElementDisplayed(sf.proSel.addToCartSuccessSubText)) {
				sf.seleU.hardwait(2000);
					reportStatusPass(methodName + "before click Add to Cart button Cart Banner Text, SubText and light green color is not displayed. ", true, true);
							}
				
		} catch (Throwable e) {
			reportStatusFail(" error on validating success message banner before click add to cart", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 10.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * To click Add to Cart button for Device Protetion
	 * 
	 */

	public void clicknAddToCartBtnForDP() throws Exception {
		try {
			methodName = "Device Protection Selection page@: ";
			sf.seleU.waitTillLoading();
			if (sf.seleU.isElementDisplayed(sf.proSel.addToCart) && sf.seleU.isElementEnabled(sf.proSel.addToCart)) {
				sf.seleU.wait(2000);
				sf.seleU.ScrolltoElement(sf.proSel.addToCart);
				sf.seleU.clickElementByJSE(sf.proSel.addToCart);
				sf.seleU.waitForLoading();
				reportStatusPass(methodName + "Device Protection is added ", true, true);
			}
		} catch (Throwable e) {
			reportStatusFail("Error on clicking Add To Cart button. ", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * Method developed on @Date: 13.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to retrieve dp price .
	 * @throws Exception 
	 * 
	 */
	public double get_dpPrice(String dProtection) throws Exception {
		String str="";
		try {
			WebElement eleDP= driver.findElement((By.xpath("//span[contains(text(),'"+dProtection +"')]/preceding-sibling::span")));
			str= sf.seleU.getTextFromWebElement(eleDP).replaceAll("[^\\d.]", "").trim();
		reportStatusPass(methodName +"dp Price: " + str, true, true);
		}
		catch (Exception e) {
			reportStatusFail("Error is get Footer price. ", e);
			e.printStackTrace();
		}
		return Double.parseDouble(str);
	}
	/**
	 * Method developed on @Date: 10.01.2022 by @author Priyanka Tawade
	 * 
	 * Method to click Edit On Product Selection .
	 * @throws Exception 
	 * 
	 */
	public void clickEditOnProductSelection(String text) throws Exception {
		try {
			sf.seleU.hardwait(3000);
			WebElement ele = driver.findElement(By.xpath("//*[contains(text(),'"+text+"')]/../following-sibling::div//*[contains(text(),'Edit')]"));
			sf.seleU.clickElementByJSE(ele);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName +" Click Edit Successfully.." +text, true, true);
		}
		catch (Exception e) {
			reportStatusFail("Unable to edit product selection.. ", e);
			e.printStackTrace();
		}

	}
	/**
	 * Method developed on @Date: 17.01.2022 by @author Priyanka Tawade
	 * 
	 * Method to Fetch Data.
	 * @throws Exception 
	 * 
	 */
	
	public void fetchData()throws Exception{
		try {			
		 Map<String,String> fetcharray=new HashedMap<String,String>();
		 sf.seleU.hardwait(8000);
		 sf.seleU.waitTillLoading();
		 for(WebElement ele: sf.proSel.itemList) {
			 sf.seleU.hardwait(2000);
			 String str = sf.seleU.getTextFromWebElement(ele);
			String[] arr= str.split(":");
			sf.seleU.hardwait(2000);
			 fetcharray.put(arr[0].replaceAll("[^A-Za-z ]", "").trim(), arr[1].trim());
			 System.out.println(Arrays.toString(arr));			 			 
		 }
		 reportStatusPass(methodName +" Data fetch Successfully.." , true, true);
		}
		catch (Exception e) {
			reportStatusFail("Error is occur", e);
			e.printStackTrace();
			} 
	}
	
	/**
	 * Method developed on @Date: 19.01.2022 by @author Priyanka Tawade
	 * 
	 * Method to compare Fetch Data With Actual Data.
	 * @throws Exception 
	 * 
	 */
	public void comparingFetchDataWithActualData() throws Exception{
		
		try {
			
			Map<String,String> actualarray=new HashedMap<String,String>();
			
			 for(WebElement ele: sf.proSel.itemListAfterClickCloseBut) {
				 String str = sf.seleU.getTextFromWebElement(ele);
				String[] arr= str.split(":");
				 actualarray.put(arr[0].replaceAll("[^A-Za-z ]", "").trim(), arr[1].trim());
				 System.out.println(Arrays.toString(arr));
			 }
			 if(sf.proSel.itemList.equals(sf.proSel.itemListAfterClickCloseBut)) {
				 reportStatusPass(methodName +" Fetch data and Actual Data is match.." , true, true); 
			 }			 
		}
		catch (Exception e) {
			reportStatusFail("Error is occur", e);
			e.printStackTrace();
			}
	}

	/**
	 * Method developed on @Date: 20.01.2022 by @author Priyanka Tawade
	 * 
	 * Method to select different device 
	 * @throws Exception 
	 * 
	 */
	public void selectDifferentPlanAfterClickEdit(String dataPlanName, String planType, String planSize)throws Exception {
		try {
			sf.seleU.waitTillLoading();
			methodName = "WACC_Products Price-Plans Validation@: ";;
			WebElement ele= driver.findElement(By.xpath("//div[text()='"+planSize+"']/following-sibling::div//fieldset//span[contains(text(),'"+planType+"')]"));
			
			// click on Voice and Data tab
			if (dataPlanName.equalsIgnoreCase(InputData_WA.voiceAndData)) {
				sf.seleU.clickElementByJSE(sf.proSel.voiceAndDataTab);
				reportStatusPass(methodName + "Plan selected : " + dataPlanName, true, false);
			}
			// click on Data Only tab
			else if (dataPlanName.equalsIgnoreCase(InputData_WA.dataOnly)) {
				sf.seleU.clickElementByJSE(sf.proSel.dataOnlyTab);
				reportStatusPass(methodName + " Plan selected : " + dataPlanName, true, false);
			} else {
				reportStatusFail(methodName + "No appropriate data plan is selected", true);
			}			
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(ele);
			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Invalid Plans Selection Page", e);
			e.printStackTrace();
		}	
	}
	

	/**
	 * Method developed on @Date: 20.01.2022 by @author Priyanka Tawade
	 * 
	 * Method to click Button On Edit and Update PopUp
	 * @throws Exception 
	 * 
	 */
	public void click_Btn_OnEdit_Update_PopUp(String button)throws Exception {
		try {
			//Leave edit
			sf.seleU.hardwait(3000);
			WebElement ele= driver.findElement(By.xpath("//button[contains(text(),'"+button+"')]"));
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(ele);
			reportStatusPass(methodName + " Button clicked Successfully : " + button, true, true);
		}
		catch (Throwable e) {
			reportStatusFail(" Button not Clicked properly..", e);
			e.printStackTrace();
		}	

	}

	/**
	 * Method developed on @Date: 10.01.2022 by @author Sakshi.Lnu
	 * 
	 * Method to skip the device protection
	 * @throws Exception 
	 * 
	 */
	public void skipDeviceProtection() throws Exception {
		
		try {
			methodName = "Device Protection Selection page@: ";
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.proSel.skipDeviceProtectionLink);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName +" skipped the device protection plan", true, true);
		}
		catch (Exception e) {
			reportStatusFail("Error on skipping device protection p. ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 20.01.2022 by @author Jigyasa Dwivedi
	 * 
	 * To Validate List of Plan on Select Wireless Product Page.
	 * 
	 * @throws Exception
	 */
	public void verify_ListofPlan(HashMap pricePlanMap) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Select Product Page@: ";
			sf.seleU.hardwait(2000);
			sf.seleU.scrollToTop();
			// Iterate Plan
			for (int i = 0; i < sf.proSel.planSizeList.size(); i++) {
				String planSize = sf.seleU.getTextFromWebElement(sf.proSel.planSizeList.get(i));
				
				// validate plan size
				if (pricePlanMap.containsKey(planSize)) {
					List<WebElement> planTypeList = driver.findElements(By.xpath("//slot//div[contains(text(),'" + planSize
							+ "')]/following-sibling::div//span[contains(@class,'element__label radio-text')]"));
					// validate plan type
					String planTypeArr[] = { "Standalone", "Pooled" };
					int count = 0;
					for (WebElement planType : planTypeList) {
						if (sf.seleU.getTextFromWebElement(planType).equalsIgnoreCase(planTypeArr[count])) {
							count++;
							Assert.assertTrue(true);
						} else {
							reportStatusFail("Plan Type is not Standalone or pooled", true);
							continue;
						}
						
					} // validate plan type for loop close

					// validate plan price
					WebElement pricePlanList = driver.findElement(By.xpath("//slot//div[contains(text(),'" + planSize
							+ "')]/../following-sibling::div//div[@class='price']"));
					if(Integer.parseInt(sf.seleU.getTextFromWebElement(pricePlanList))==(Integer)pricePlanMap.get(planSize)) {
						
						reportStatusPass(methodName + planSize +" : "+pricePlanMap.get(planSize)+ " is matching with Requirement", true, true);
					}
					reportStatusPass(methodName +"Price Plan size, type and price is matching with requirement", true, true);
				} 
			
			} // Iterate Plan for loop close

		} catch (Exception e) {
			reportStatusFail("Error is validating Price Plan size, type and price on select product Page", e);
			e.printStackTrace();
		}
	}
			
	/**
	* Method developed on @Date: 27.01.2022 by @author Priyanka Tawade
	*
	* To click Update Cart button after selecting different device and different plan
	*
	* @throws Exception
	*
	*/
	public void click_UpdateCart_AfterDevice_Plan_Selection() throws Exception {
		try {
			methodName = "WACC update selection for updating device  @: ";
		sf.seleU.waitTillLoading();
		Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.proSel.updateCart));
		sf.seleU.clickElementByJSE(sf.proSel.updateCart);
		sf.seleU.wait(2000);
		reportStatusPass(methodName + "Update Cart button is clicked", true, true);
		} catch (Exception e) {
		reportStatusFail("Error is validating Device not updated ", e);
		e.printStackTrace();
		}
		}
	
	/**
	* Method developed on @Date: 14.02.2022 by @author viswas reddy
	*
	* Validating wireless Plans tile with text present in the tile
	*
	* @throws Exception
	*
	*/
	public void validate_WirelessPlansTile() throws Exception {
		try {
			methodName = "Wireless Plans Tile @: ";
			sf.seleU.waitTillLoading();
			if(sf.proSel.wirelessTile.isDisplayed() || sf.proSel.headerProductSelection.isDisplayed()) {
				reportStatusPass(methodName+ "wireless Tile is present ", true, true);
			}else {
				reportStatusFail(methodName+ "wireless Tile is not present ", true);
			}
			if(sf.seleU.getTextFromWebElement(sf.proSel.wirelessPlansTileheader).equalsIgnoreCase("Wireless Plans")
					 || sf.seleU.getTextFromWebElement(sf.proSel.shopWirelessPlansButton).equalsIgnoreCase("Shop Wireless Plans")) {
				reportStatusPass(methodName+ "Title of tile and text at red button is as expected ", true, true);
			}else {
				reportStatusFail(methodName+ "Title of tile and text at red button is not as expected", true);
			}
			if(sf.seleU.getTextFromWebElement(sf.proSel.wirelessPlansTileDescription).equalsIgnoreCase("Keep connected "
					+ "to your customers and your team with wireless plans")) {
				reportStatusPass(methodName+ "Description of Wireless Plans tile is as expected", true, true);
			}else {
				reportStatusFail(methodName+ "Description of Wireless Plans tile is not as expected", true);
			}
		} catch(Exception e) {
			reportStatusFail("Error is validating wireless Plans tile ", e);
			e.printStackTrace();
		}
	}	
	/**
	* Method developed on @Date: 01.03.2022 by @author Shruti Desai
	*
	* Validating wireless Plans tile After Click Edit on plan
	*
	* @throws Exception
	*
	*/
	public void validateSelectePlanTileAfterClickEdit() throws Exception {
		methodName = " WACC_Validate Selected plan tile @: ";
		//validating page title "Select your Rogers wireless plan".
		if(sf.proSel.wirelessPlansSubHeader.isDisplayed()) {
			reportStatusPass(methodName +" User lands on Price plan page with title: "+ sf.seleU.getTextFromWebElement(sf.proSel.wirelessPlansSubHeader), true, true);
		}else {
			reportStatusFail(methodName +"User doesn't land on Price plan page",true);
		}
		//validating red border and Added to cart text on the selected plan tile 
		WebElement border = driver.findElement(By.xpath("//*[contains(@style,'border: 3px ')]"));
		if(border.isDisplayed()) {
			WebElement addedToCartBtn = border.findElement(By.xpath(".//span[contains(text(),'Added')]"));
			if(addedToCartBtn.isDisplayed()) {
				reportStatusPass(methodName + "default plan selection has a red outline with Added to cart text", true, true);
			}else {
				reportStatusFail(methodName + "default plan selection has a red outline but Added to cart text is missing", true);
			}
		}else {
			reportStatusFail(methodName + " Error in validating selected plan", true);
		}
		//validate "Continue to add-ons" button.
		if(sf.proSel.continueToAddOns.isDisplayed()) {
			reportStatusPass(methodName + "Continue to Add On button is displayed", true, true);
		}else {
		reportStatusFail(methodName + "Continue to Add On button is not displayed", true);
		}
		
	}
	
	/**
	* Method developed on @Date: 02.03.2022 by @author Shruti Desai
	*
	* Validating wireless Plans tile After Click Edit on plan
	*
	* @throws Exception
	*
	*/
	public void validatePlanTileAfterEditingPlan() throws Exception {
		methodName = " WACC_Validate plan tile after plan Edition @: ";
		 //validation of Added to cart text updated to Update Cart button after plan edition 
		if(sf.proSel.updateCart.isDisplayed()) {
			reportStatusPass(methodName + "Added to cart status is replaced by the Update cart button after edtion of plan", true, true);
		}else {
			reportStatusFail(methodName + "Added to cart status is not replaced after edtion of plan", true);
		}
		//validating removal of red border
		WebElement border = sf.proSel.updateCart.findElement(By.xpath("./ancestor::*[contains(@style,'border: 0px ')]"));
		if(border.isDisplayed()) {
			reportStatusPass(methodName + "Red out line has been removed after editing the plan", true, true);
		}else{
			reportStatusFail(methodName + "Red out line has not been removed after editing the plan", true);
			}
		sf.seleU.clickElementByJSE(sf.proSel.updateCart);
		
	}

	/**
	* Method developed on @Date: 03.03.2022 by @author Shruti Desai
	*
	* Validating wireless Plans tile After Click Edit on plan
	*
	* @throws Exception
	*
	*/
	public void validatePlanChangeafterEditon() throws Exception {
		methodName = " WACC_Validate plan change after plan Edition @: ";
		//sf.seleU.clickElementByJSE(sf.proSel.updateCart);
		sf.seleU.hardwait(5000);
		//validate Confirm Updating Cart & Cancel and go back buttons
		if(sf.shopWADevobj.confirmUpdatingCartBtn.isDisplayed() && sf.shopWADevobj.cancelAndGoBackBtn.isDisplayed()) {
			reportStatusPass(methodName + " Confirm Updating Cart & Cancel and go back buttons are present.", true, false);
		} else {
			reportStatusFail(methodName + " Confirm Updating Cart & Cancel and go back buttons are not present.", true);
		}
		//click Confirm updating cart Button on the pop up
		sf.shopWADevobj.confirmUpdatingCartBtn.click();
		//validation of plan after plan edition 
		sf.seleU.waitTillLoading();
		//sf.seleU.hardwait(5000);
		WebElement border = driver.findElement(By.xpath("//*[contains(@style,'border: 3px ')]"));
		WebElement addedToCartBtn = border.findElement(By.xpath(".//span[contains(text(),'Added')]"));
		WebElement planSelected = border.findElement(By.xpath(".//div[@data-id='bucketsize']"));
		if (!sf.seleU.getTextFromWebElement(planSelected).equals(InputData_WA.WACC_Plan_Size) && border.isDisplayed() && addedToCartBtn.isDisplayed()) {
			reportStatusPass(methodName + "Plan has been changed displaying red border with Added to cart text. New plan selected : " + sf.seleU.getTextFromWebElement(planSelected), true, false);
		} else {
			reportStatusFail(methodName + " Plan has been not changed", true);
		}
		sf.seleU.waitTillLoading();
		sf.seleU.clickElementByJSE(sf.proSel.continueToAddOnsBtn);
		
		//validating add to cart button is disabled for Add Ons
		if(sf.seleU.isElementDisplayed(sf.proSel.disabledAddToCart) && sf.seleU.isElementDisplayed(sf.proSel.addExtraFeature)) {
			reportStatusPass(methodName + " User lands on the Add on page. Add to cart Button is disabled for Add Ons. So, no Add ons has been selected", true, false);
		} else {
			reportStatusFail(methodName + " User doesn't land Add on page", true);
		}
	}
	/**
	 * Method developed on @Date: 08.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate Browse Accessories and Continue to Shopping Cart button on the Product Selection Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void validate_BrowseAcc_ContinueShoppingCartBtn() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "WACC Product selection Page@: ";
			sf.seleU.ScrolltoElement(sf.proSel.continueToShoppingCart);
			if(sf.seleU.isElementDisplayed(sf.proSel.browseAccessories) && sf.seleU.isElementDisplayed(sf.proSel.continueToShoppingCart)) {
				reportStatusPass(methodName + " Browse Accessories and Continue to shopping cart button is displayed." , true, true);
			}
		}catch (Exception e) {
			reportStatusFail("Error on validating Browse Accessories button and Continue to Shopping cart button on product selection screen", true);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 08.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to select Continue to Shopping Cart button on the Product Selection Page.	 
	 * @throws Exception
	 * 
	 */
	public void selectContinueToShoppingCartBtn() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "WACC Product selection Page@: ";
			if(sf.seleU.isElementDisplayed(sf.proSel.continueToShoppingCart)) {	
				sf.seleU.ScrolltoElement(sf.proSel.continueToShoppingCart);
				sf.seleU.clickElementByJSE(sf.proSel.continueToShoppingCart);
				sf.seleU.hardwait(4000);
				Assert.assertEquals(sf.seleU.isElementDisplayed(sf.shopCartObj.shoppingCartHeader), true);
				reportStatusPass(methodName + " Continue to shopping cart button has clicked." , true, true);
			}
		}catch (Exception e) {
			reportStatusFail("Error on select Continue to Shopping cart button on product selection screen", true);
			e.printStackTrace();
		}
	}
		
	/**
	* Method developed on @Date: 07.03.2022 by @author Shruti Desai
	*
	* Validating add on page after clicking edit button
	*
	* @throws Exception
	*
	*/
	public void validateAddOnPage() throws Exception {
		methodName = " WACC_Validate Edit Add On  @: ";
		//validation of Add on page
		if(sf.seleU.isElementDisplayed(sf.proSel.addExtraFeature)) {
			reportStatusPass(methodName + " User lands on the Add on page",true,true);
		} else {
			reportStatusFail(methodName + " User doesn't land Add on page", true);
		}		
	}
	/**
	* Method developed on @Date: 07.03.2022 by @author Shruti Desai
	*
	* Validating add on after editing add on
	*
	* @throws Exception
	*
	*/
	public void validateEditAddOn() throws Exception {
		//choose different add on 
		//sfdc.selectAddOn.selectAddOn("SMS", "Unlimited Canada to US/Intl SMS/MMS","NA");
		//validation of confirm Selection and Cancel Edit buttons
		if(sf.addOnSel.confirmselectionButton.isDisplayed() && sf.addOnSel.cancelEditButton.isDisplayed()) {
			reportStatusPass(methodName + " Confirm Selection and Cancel Edit buttons are present.", true, false);
		} else {
			reportStatusFail(methodName + " Confirm Selection and Cancel Edit buttons are not present.", true);
		}
		//click confirm selection button
		sf.seleU.clickElementByJSE(sf.addOnSel.confirmselectionButton);
		//click continue button 
		sf.seleU.clickElementByJSE(sf.addOnSel.continueButtonForBYOD);
		
		//validateAddOn changed after Edition()
		if (!sf.seleU.getTextFromWebElement(sf.shopWADevobj.getOrderSeqList.get(1)).equals(InputData_WA.WACC_AddOn_Name)) {
			reportStatusPass(methodName + "Confirm selecton and Continue buttons are clicked. Add On has been changed. New Add On selected : " + sf.seleU.getTextFromWebElement(sf.shopWADevobj.getOrderSeqList.get(1)), true, false);
		} else {
			reportStatusFail(methodName + " Add On has been not changed", true);
		}
	}	
	/**
	* Method developed on @Date: 10.03.2022 by @author Shruti Desai
	*
	* Validating Device page after clicking edit button
	*
	* @throws Exception
	*
	*/
	public void validateChooseDevicePage() throws Exception {
		methodName = " WACC_Validate Choose Device page  @: ";
		//validation of Choose Device page
		WebElement addedToCartBtn = driver.findElement(By.xpath("//span[contains(text(),'Added')]"));
		
		if(sf.seleU.isElementDisplayed(sf.proSel.chooseDevice) && sf.seleU.isElementDisplayed(addedToCartBtn)) {
			reportStatusPass(methodName + " User lands on the Choose Device page. Added to cart text is displayed for selected device",true,true);
		} else {
			reportStatusFail(methodName + " User doesn't land Choose Device page", true);
		}
		
	}
	
	/**
	* Method developed on @Date: 10.03.2022 by @author Shruti Desai
	*
	* Validating updated device after clicking edit button
	*
	* @throws Exception
	*
	*/
	public void validateEditDevice() throws Exception {
		methodName = " WACC_Validate Edit Device  @: ";
		if (!(sf.shopWADevobj.getOrderSeqList.get(2).getText()).contains(InputData_WA.WACC_DeviceName)) {
			reportStatusPass(methodName + " Device has been updated. New Device selected : " + sf.seleU.getTextFromWebElement(sf.shopWADevobj.getOrderSeqList.get(2)), true, true);
		} else {
			reportStatusFail(methodName + " Device has been not Updated", true);
		}		
	}
	
	/**
	* Method developed on @Date: 09.03.2022 by @author viswas reddy
	*
	* Validating device protection radio buttons or check box button and tiles side by side
	*
	* @throws Exception
	*/
	public void Validate_DP_multiDP_singleDP() throws Exception{
		methodName = "WACC validation DP radio button and update cart@ ";
		try {
			if(sf.proSel.DPradioBtn.size()>1 && sf.proSel.DPtilesSideBySide.size()==2 
					&& sf.proSel.dpPrice.size()==2) {
				reportStatusPass(methodName+"for multiple DP's radio buttons and price is available "
						+ "and tiles are side by side", true, true);
			}else if(sf.proSel.DPcheckboxBtn.isDisplayed() && sf.proSel.dpPriceSingleDP.isDisplayed()){
				reportStatusPass(methodName+"for single DP's checkbox and price is available", true, true);
			}else {
				reportStatusFail(methodName+"DP's radio or checkbox button not available or tiles not side by side", true);
			}
		}catch (Exception e) {
			reportStatusFail(methodName+"valiadation failed for DP buttons", true);
			e.printStackTrace();
		}
	}
	
	/**
	* Method developed on @Date: 09.03.2022 by @author viswas reddy
	*
	* Validating device protection update cart button
	*
	* @throws Exception
	*/
	public void Validate_updateCart() throws Exception {
		methodName = "WACC validation DP update cart@ ";
		try {
			if(sf.proSel.updateCart.isDisplayed()) {
				reportStatusPass(methodName+"update cart button is available", true, true);
			}else {
				reportStatusFail(methodName+"update cart button not available ", true);
			}
		}catch (Exception e) {
			reportStatusFail(methodName+"valiadation failed for update cart button", true);
			e.printStackTrace();
		}
	}
	
	/**
	* Method developed on @Date: 09.03.2022 by @author viswas reddy
	*
	* Validating device protection I don't want any Device protection
	*
	* @throws Exception
	*/
	public void select_noDP() throws Exception {
		methodName = "Validation I don't want DP @: ";
		try {
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.proSel.iDontWantDP);
			sf.seleU.waitElementToBeVisible(sf.proSel.updateCart);
			sf.seleU.clickElementByJSE(sf.proSel.updateCart);
			sf.seleU.wait(2000);
			sf.seleU.waitTillLoading();
			sf.seleU.scrollToTop();
			if(sf.seleU.getTextFromWebElement(sf.proSel.nameOfSelctdDP)==null) {
				reportStatusPass(methodName+"clicked on I don't want DP button ", true, true);
			}else {
				reportStatusFail(methodName+"failed to click on I don't wnat DP button", true);
			}
		}catch (Exception e) {
			reportStatusFail(methodName+"valiadation failed for I don't want DP selection", true);
			e.printStackTrace();
		}
	}
	
	/**
	* Method developed on @Date: 10.03.2022 by @author viswas reddy
	*
	* Validating device protection update cart
	*
	* @throws Exception
	*/
	public void clickOnUpdateCartDP(String DPname) throws Exception {
		methodName = "Validation update cart in DP@: ";
		try {
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.proSel.updateCart);
			sf.seleU.wait(3000);
			sf.seleU.waitTillLoading();
			sf.seleU.scrollToTop();
			if(sf.seleU.getTextFromWebElement(sf.proSel.nameOfSelctdDP).contains(DPname)) {
				reportStatusPass(methodName+"Successfully updated DP to the cart", true, true);
			}else {
				reportStatusFail(methodName+"failed to update DP", true);
			}
		}catch (Exception e){
			reportStatusFail(methodName+"valiadation failed for updating DP to cart", true);
			e.printStackTrace();
		}
	}
	
	/**
	* Method developed on @Date: 10.03.2022 by @author viswas reddy
	*
	* Validating device protection close button and changes were discarded
	*
	* @throws Exception
	*/
	public void validate_closeBtnOnDP(String DPname) throws Exception {
		methodName = "Validation close btn in DP edit @: ";
		try {
			sf.seleU.wait(2000);
			sf.seleU.waitTillLoading();
			//sf.seleU.clickOnElement(sf.addOnSel.btnClose);
			sf.seleU.clickElementByJSE(sf.addOnSel.btnClose);
			sf.seleU.hardwait(2000);
			sf.seleU.clickOnElement(sf.proSel.leaveEdit);
			sf.seleU.wait(2000);
			sf.seleU.waitTillLoading();
			sf.seleU.scrollToTop();
			if(sf.seleU.getTextFromWebElement(sf.proSel.nameOfSelctdDP).contains(DPname)) {
				reportStatusPass(methodName+"Successfully close in DP edit is working", true, true);
			}else {
				reportStatusFail(methodName+"close in DP edit is not working", true);
			}
		}catch (Exception e){
			reportStatusFail(methodName+"valiadation failed for close button in DP edit", true);
			e.printStackTrace();
		}
	}
	
	/**
	* Method developed on @Date: 14.03.2022 by @author viswas reddy
	*
	* Validating device protection Caption and Apple care and Device protection description
	*
	* @throws Exception
	*/
	public void validate_DP_Caption(String noOfDPs) throws Exception {
		methodName = "Validation close btn in DP edit @: ";
		try {
			String caption = "Protect your device with additional security and safety";
			String AppleDescription = "Get up to 3 fulfilled service requests for your protected device in a rolling"
					+ " 12-month period, including up to 2 repairs for accidental damage from handling and 1 "
					+ "replacement for loss or theft.";
			String DPdescription = "Get up to 3 fulfilled service requests for your protected device in a rolling "
					+ "12-month period, including up to 2 repairs for accidental damage from handling and/or "
					+ "out-of-warranty malfunction and 1 replacement for loss or theft.";
			if(sf.seleU.getTextFromWebElement(sf.proSel.DPcaption).equalsIgnoreCase(caption)) {
				reportStatusPass(methodName+"Device Protection caption is as expected", true, true);
			}else {
				reportStatusFail(methodName+"Device Protection caption is not as expected", true);
			}
			if (noOfDPs == "multiDP") {
				if (sf.seleU.getTextFromWebElement(sf.proSel.AppleCareDesc).equalsIgnoreCase(AppleDescription)
						&& sf.seleU.getTextFromWebElement(sf.proSel.DeviceProtDesc).equalsIgnoreCase(DPdescription)) {
					reportStatusPass(methodName + "Apple care and Device Protection description is as expected", true,
							true);
				}
			} else if (sf.seleU.getTextFromWebElement(sf.proSel.SingleDPdesc).equalsIgnoreCase(DPdescription)) {
				reportStatusPass(methodName + "Single Device protection description is as expected", true, true);
			} else {
				reportStatusFail(methodName + "Apple care and Device Protection description or single DP "
						+ "is not as expected", true);
			}
		}catch (Exception e) {
			reportStatusFail(methodName+"valiadation failed for DP caption or apple care or DP description", true);
			e.printStackTrace();
		}
	}
}

