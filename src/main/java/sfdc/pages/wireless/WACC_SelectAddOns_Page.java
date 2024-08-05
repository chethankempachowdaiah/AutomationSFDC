package sfdc.pages.wireless;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;   

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.framework.utilities.VerificationUtilities;
import com.sfdc.data.InputData_WA;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Sakshi Lnu, Date : 05/May/2021
 * 
 *         Select Add-Ons for wireless products
 *
 */
public class WACC_SelectAddOns_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public WACC_SelectAddOns_Page() {
		sf = new SFDC_AllPageObjects();

	}

	/**
	 * @throws Exception
	 * 
	 *                   1- validate "Continue to Add-ons" button is clicked
	 * 
	 */
	public void verifyEditPricePlanAccordion() throws Exception {
		try {
			methodName = "WACC Continue to Add-Ons Page Validation@: ";
			// clcik to view add on details
			Boolean status = sf.seleU.isElementDisplayed(sf.addOnSel.editbuttton);
			if (status) {
				reportStatusPass(methodName + "Validated Edit button is present on AddOns page", true, true);
			} else {
				reportStatusFail(methodName + " Edit Button for plans is not present on AddOns selection page", true);
			}
			sf.seleU.wait(1000);
		} catch (Throwable e) {
			reportStatusFail(" error on clicking edit button option", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *                   validate "Long distance AddOns" as per the plan selected
	 * 
	 */
	public void validateLongDistancePlans(String addOnType, String addOnName, String addOnAvailability)
			throws Exception {
		try {
			methodName = "WACC Long_Distance_Plans Details Validation@: ";
			// Select AddOns
			if (addOnType.equalsIgnoreCase(InputData_WA.addOnType_LD)
					&& (addOnName.equalsIgnoreCase(InputData_WA.addOnName_US_LD))
					&& (addOnAvailability.equalsIgnoreCase(InputData_WA.LD_NoOffer))) {
				System.out.println("Entered this ld no offer if");
				sf.seleU.clickElementByJSE(sf.addOnSel.noOfferUSLDRadio);
				sf.seleU.wait(1000);
				if (sf.seleU.isElementDisplayed(sf.addOnSel.disabledAddToCart)) {
					reportStatusPass(methodName + "Add to cart is Disabled before selecting any addOn ", true, true);
				} else {
					reportStatusFail(methodName + "Error in verifying disabled buttons on addOns Page", true);
				}
				sf.seleU.clickElementByJSE(sf.addOnSel.USLD_AddOn);
				sf.seleU.wait(1000);
				if (sf.seleU.isElementDisplayed(sf.addOnSel.addToCart)) {
					reportStatusPass(methodName + "Add to cart is enabled after selecting any addOn ", true, true);
				} else {
					reportStatusFail(methodName + "Error in verifying enabled buttons on addOns Page", true);
				}
				reportStatusPass(methodName + addOnName + " and " + addOnAvailability + " is selected ", true, false);
			}

			// click on Data Only tab
			else if (addOnType.equalsIgnoreCase(InputData_WA.addOnType_LD)
					&& (addOnName.equalsIgnoreCase(InputData_WA.addOnName_US_LD))
					&& (addOnAvailability.equalsIgnoreCase(InputData_WA.USLD_WirelessLD))) {
				sf.seleU.clickElementByJSE(sf.addOnSel.wirelessUSLDRadio);
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.disabledAddToCart));
				sf.seleU.clickElementByJSE(sf.addOnSel.USLD_AddOn);
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.addToCart));
				reportStatusPass(methodName + addOnName + " and " + addOnAvailability + " is selected ", true, false);
			} else if (addOnType.equals(InputData_WA.addOnType_LD)
					&& (InputData_WA.WACC_AddOn_Name.equals(InputData_WA.addOnName_International_LDSaver))
					&& (addOnAvailability.equals(InputData_WA.LD_NoOffer))) {
				sf.seleU.clickElementByJSE(sf.addOnSel.noOfferIntLDsaverRadio);
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.disabledAddToCart));

				sf.seleU.clickElementByJSE(sf.addOnSel.InternationalLDSaver_AddOn);

				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.addToCart));
				reportStatusPass(methodName + addOnName + " and " + addOnAvailability + " is selected ", true, false);
			} else if (addOnType.equalsIgnoreCase(InputData_WA.addOnType_LD)
					&& (addOnName.equalsIgnoreCase(InputData_WA.addOnName_International_LDSaver))
					&& (addOnAvailability.equalsIgnoreCase(InputData_WA.IntLD_WirelessIntLD))) {
				sf.seleU.clickElementByJSE(sf.addOnSel.wirelessIntLDSaverRadio);
				reportStatusPass(methodName + " Clicked on wireless int lDSaver", true, true);
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.disabledAddToCart));

				sf.seleU.clickElementByJSE(sf.addOnSel.InternationalLDSaver_AddOn);

				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.addToCart));
				reportStatusPass(methodName + addOnName + " and " + addOnAvailability + " is selected ", true, false);
			} else {
				reportStatusFail(methodName + " No valid Long distance Plans found", true);
			}

			sf.seleU.wait(1000);
		} catch (Throwable e) {
			reportStatusFail(" error on chosing Long distance AddOns", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *                   validate "SMS AddOns" as per the plan selected
	 * 
	 */
	public void validateSMSPlans() throws Exception {
		try {
			methodName = "WACC SMS Details Validation@: ";
			// Select AddOns
			if (InputData_WA.WACC_AddOn_Type.equals(InputData_WA.addOnType_SMS)
					&& (InputData_WA.WACC_AddOn_Name.equals(InputData_WA.addOnName_SMS))) {
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.disabledAddToCart));
				sf.seleU.clickElementByJSE(sf.addOnSel.unlimitedSMS_AddOn);
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.addToCart));
				reportStatusPass(methodName + InputData_WA.WACC_AddOn_Name + " and " + InputData_WA.WACC_AddOn_Type
						+ " is selected ", true, false);
			} else {
				reportStatusFail(methodName + " No valid SMS Plans found", true);
			}

			sf.seleU.wait(3000);
		} catch (Throwable e) {
			reportStatusFail(" error on chosing SMS Plans", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *                   validate "Roaming AddOns" as per the plan selected
	 * 
	 */
	public void validateRoamingPlans() throws Exception {
		try {
			methodName = "WACC Roaming Details Validation@: ";
			// Select AddOns
			if (InputData_WA.WACC_AddOn_Type.equals(InputData_WA.addOnType_Roaming)
					&& (InputData_WA.WACC_AddOn_Name.equals(InputData_WA.addOnName_Roaming))) {
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.disabledAddToCart));
				sf.seleU.clickElementByJSE(sf.addOnSel.roaming_AddOn);
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.addToCart));
				reportStatusPass(methodName + InputData_WA.WACC_AddOn_Name + " and " + InputData_WA.WACC_AddOn_Type
						+ " is selected ", true, false);
			} else {
				reportStatusFail(methodName + " No valid Roaming Plans found", true);
			}

			sf.seleU.wait(3000);
		} catch (Throwable e) {
			reportStatusFail(" error on chosing Roaming Plans", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *                   validate "Voicemail AddOns" as per the plan selected
	 * 
	 */
	public void validateVoicemailPlans() throws Exception {
		try {
			methodName = "WACC VoiceMail_Plans Details Validation@: ";
			// Select AddOns
			if (InputData_WA.WACC_AddOn_Type.equals(InputData_WA.addOnType_Voicemail)
					&& (InputData_WA.WACC_AddOn_Name.equals(InputData_WA.addOnName_Premium_Voicemail))) {
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.disabledAddToCart));
				sf.seleU.clickElementByJSE(sf.addOnSel.premiumVoiceMailText);
				sf.seleU.wait(2000);
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.addToCart));
				reportStatusPass(methodName + InputData_WA.WACC_AddOn_Name + " and "
						+ InputData_WA.WACC_AddOn_Availability + " is selected ", true, false);
			}

			// click on Data Only tab
			else if (InputData_WA.WACC_AddOn_Type.equals(InputData_WA.addOnType_Voicemail)
					&& (InputData_WA.WACC_AddOn_Name.equals(InputData_WA.addOnName_iPH_Voicemail))) {
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.disabledAddToCart));
				sf.seleU.clickElementByJSE(sf.addOnSel.iPhVoicemailText);
				sf.seleU.wait(2000);
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.addToCart));
				reportStatusPass(methodName + InputData_WA.WACC_AddOn_Name + " and "
						+ InputData_WA.WACC_AddOn_Availability + " is selected ", true, false);
			} else {
				reportStatusFail(methodName + " No valid Voicemail Plans found", true);
			}
			sf.seleU.waitForLoading();
		} catch (Throwable e) {
			reportStatusFail(" error on chosing Voice mail Plans", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *                   Expand the AddOn window as per the addOn selection made
	 * 
	 */
	public void expandAddOnsWindow() throws Exception {
		try {
			methodName = "WACC Addons Expanded for detailed Validation@: ";
			sf.seleU.wait(1000);
			// Select AddOns
			if (InputData_WA.WACC_AddOn_Type.equalsIgnoreCase(InputData_WA.addOnType_Voicemail)) {
				sf.seleU.hardwait(5000);
				sf.seleU.clickOnElement(sf.addOnSel.expandVoicemail);
				sf.seleU.wait(1000);
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.premiumVoiceMailText));
				reportStatusPass(methodName + InputData_WA.WACC_AddOn_Type + " is expanded ", true, false);
			}

			// click on Data Only tab
			else if (InputData_WA.WACC_AddOn_Type.equalsIgnoreCase(InputData_WA.addOnType_SMS)) {
				sf.seleU.hardwait(5000);
				sf.seleU.clickOnElement(sf.addOnSel.expandSMS);
				sf.seleU.wait(1000);
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.unlimitedSMS_AddOn));
				reportStatusPass(methodName + InputData_WA.WACC_AddOn_Type + " is expanded ", true, false);
			} else if (InputData_WA.WACC_AddOn_Type.equalsIgnoreCase(InputData_WA.addOnType_Roaming)) {
				sf.seleU.hardwait(5000);
				sf.seleU.clickElementByJSE(sf.addOnSel.expandRoaming);
				sf.seleU.wait(1000);
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.roaming_AddOn));
				reportStatusPass(methodName + InputData_WA.WACC_AddOn_Type + " is expanded ", true, false);
			}
			sf.seleU.wait(3000);
		} catch (Throwable e) {
			reportStatusFail(" error on clicking expand option", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *                   Add to cart is clicked to Add the addOns in the shopping
	 *                   bucket Verify that After clicking Add to cart for Add ons ,
	 *                   "Update Cart" option is enabled and verify that Add on
	 *                   price is added to Total value
	 * 
	 */

	public void clickOnAddToCartAddOns() throws Exception {
		try {
			methodName = "WACC Adding AddOns Validation @: ";
			// to move back to price plan selection
			sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
			// click on Add to cart button to add the ADD Ons
			reportStatusPass(methodName + "ADd To cart for AddOns button is clicked ", true, true);
			sf.seleU.wait(3000);
			reportStatusPass(methodName + sf.seleU.isElementDisplayed(sf.addOnSel.updateCart), true, true);
			sf.seleU.waitForLoading();
//			VerificationUtilities.compareFieldValueIfContains("Footer price After adding AddOn" ,
//					sf.seleU.getTextFromWebElement(sf.addOnSel.footerRecurringTcvPrice), InputData_WA.WACC_AddOn_TotalPrice);
//			sf.seleU.wait(2000);
		} catch (Throwable e) {
			reportStatusFail("Error on clicking Add to cart on addOn Screen", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *                   Proceed to Shopping Cart button is clicked Verify User
	 *                   lands on Correct Page
	 * 
	 */

	public void continueByodDeviceAndClickOnShopCart() throws Exception {
		try {
			methodName = "WACC Products Shopping Cart Validation @: ";
			// to move back to price plan selection
			sf.seleU.waitForLoading();
		//	Assert.assertTrue(sf.seleU.isElementDisplayed(sf.addOnSel.deviceTypeSelectHeader));
		sf.seleU.clickElementByJSE(sf.addOnSel.byodSelectionButton);
			reportStatusPass(methodName + "BYOD option is clicked", true, true);
			sf.seleU.waitForLoading();
			sf.seleU.clickElementByJSE(sf.addOnSel.continueButtonForBYOD);
			sf.seleU.clickElementByJSE(sf.addOnSel.proceedToShopCartButton);
			// click on Add to cart button to add the ADD Ons
			// sfdc.selectAddOn.continueDeviceAndClickOnShopCart();
			reportStatusPass(methodName + "Proceed to shopping Cart button is clicked ", true, true);
			sf.seleU.waitForLoading();
		} catch (Throwable e) {
			reportStatusFail("Error on clicking shopping cart button", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *                   select "Roaming AddOns"
	 */
	public void selectRoamingPlans() throws Exception {
		try {
			methodName = "WACC Roaming Details Validation@: ";
			// Select AddOns
			reportStatusPass(methodName + sf.seleU.isElementDisplayed(sf.addOnSel.disabledAddToCart), true, true);
			sf.seleU.clickElementByJSE(sf.addOnSel.roaming_AddOn);
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.addToCart));
			reportStatusPass(methodName + "Roaming plan is selected ", true, false);
			//sf.seleU.clickOnElementNumberoftimes(sf.addOnSel.addToCart, 1);
			sf.seleU.wait(3000);
		} catch (Throwable e) {
			reportStatusFail(" error on chosing Roaming Plans", e);
			e.printStackTrace();
		}
	}

	public void clickOnContinueToDevice() throws Exception {
		try {
			methodName = "WACC_Plans - Continue to Device : ";
			// click on continue to device button
			sf.seleU.clickElementByJSE(sf.addOnSel.btnContiueToDev);
			sf.seleU.waitForLoading();
			reportStatusPass(methodName + "Continue to device  button is clicked", true, true);
			Assert.assertEquals(true,
					sf.seleU.isElementDisplayedWithYellowHighlight(sf.shopWADevobj.SelectAppleDevice));
			reportStatusPass(methodName + "user is on Device listing Page", true, true);

		} catch (Throwable e) {
			reportStatusFail(" error on clicking continue to device on add-ons screen", e);
			e.printStackTrace();

		}
	}

	/**
	 * @throws Exception
	 * 
	 *                   1- validate "Continue to Add-ons" button is clicked
	 * 
	 */
	public void verifyAddonScreen() throws Exception {
		try {
			methodName = "WACC Add-Ons Page Validation When User selects Price Plan first@: ";
			// clcik to view add on details
			Boolean status = sf.seleU.isElementDisplayed(sf.addOnSel.btnContiueToDev);
			if (status) {
				reportStatusPass(methodName + "Validated Continue to device button is present on AddOns page", true,true);
			} else {
				reportStatusFail(methodName + " Edit Button for plans is not present on AddOns selection page", true);
			}
			sf.seleU.wait(1000);
		} catch (Throwable e) {
			reportStatusFail(" error on clicking edit button option", e);
			e.printStackTrace();
		}
	}

	/*
	 * Method Name - Verify proceed to checkout button is disabled when user on Add
	 * On screen Continue to device and
	 * 
	 * @author-Sandesh Kumbhar
	 * 
	 */

	public void verifyEnabledElementOnAddPage() throws Exception {
		try {
			methodName = "WACC Enabled Options Validatio on Add ON screenn@: ";
			sf.seleU.highLightElement(sf.addOnSel.btnContiueToDev);
			if (((sf.addOnSel.btnContiueToDev.isDisplayed()) && (sf.addOnSel.btnContiueToDev.isEnabled())
					&& ((sf.addOnSel.byodSelectionButton.isDisplayed())
							&& (sf.addOnSel.byodSelectionButton.isEnabled())))) {
				reportStatusPass(methodName
						+ "Add on screen has 2 buttons Continue to Device & Bring your own device are displayed and enabled ",
						true, true);
			}
			sf.seleU.wait(1000);
		}
		catch (Throwable e) {
			reportStatusFail(" Continue to Devices and Bring your own device buttons are not displaying on Add on screen", e);
			e.printStackTrace();
		}
	}

	public void verifyDisabledElement(WebElement element) throws Exception {
		try {
			methodName = "WACC Disabled Options/Field Validation: ";
			sf.seleU.highLightElement(element);
			if ((!(element.isEnabled()))) {
				reportStatusPass(methodName + " " + element + " This is not Enabled(Disabled)", true, true);
			}
			sf.seleU.wait(1000);
		}
		catch (Throwable e) {
			reportStatusFail(" Element is displayed and Enabled", e);
			e.printStackTrace();
		}
	}

	public void verifyButtonProperty() throws Exception {
		try {
			verifyEnabledElementOnAddPage();
			verifyDisabledElement(sf.addOnSel.proceedToShopCartButton);
		}
		catch (Throwable e) {
			reportStatusFail(" Button Properties on add-ons page failed", e);
			e.printStackTrace();
		}
	}

	public void ValidateByodAddOnBackToBrowse() throws Exception {
		try {
			methodName = "WACC clicking BYOD on Add Onscreen & Back to Browse btn fucntionality on BYOD page@: ";
			sf.seleU.highLightElement(sf.addOnSel.byodSelectionButton);
			if (((sf.addOnSel.byodSelectionButton.isDisplayed()) && (sf.addOnSel.byodSelectionButton.isEnabled()))) {
				sf.seleU.clickElementByJSE(sf.addOnSel.byodSelectionButton);
				sf.seleU.waitForLoading();
				reportStatusPass(methodName
						+ "User is on BYOD page and Back to Browse devices button is displayed ",
						true, true);
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.btnBackToBrowseDev));
				sf.seleU.clickElementByJSE(sf.addOnSel.btnBackToBrowseDev);
				sf.seleU.waitForLoading();
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.shopWADevobj.smartphonestab));
				reportStatusPass(methodName
						+ "User is on BYOD page and clicks on Back to Browse devices button and is Navigated to Device Listings page ",
						true, true);

			}
			sf.seleU.wait(1000);
		}
		catch (Throwable e) {
			reportStatusFail(" Error in Bring your own device & back to browse device page navigation", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 29.09.2021 by @author Gaurav Singh
	 * 
	 * Clicks on proceed to shopping cart.
	 * 
	 * 
	 */
	public void clickOnShopCart() throws Exception {
		try {
		sf.seleU.waitForLoading();
		sf.seleU.clickElementByJSE(sf.addOnSel.proceedToShopCartButton);
		sf.seleU.waitTillLoading();
		Assert.assertTrue(sf.shopCartObj.proceedToCheckoutBtn.isDisplayed()&&sf.shopCartObj.proceedToCheckoutBtn.isEnabled());
		reportStatusPass("Reached to cart page.", true, true);
		}
		catch(Exception e) {
			reportStatusFail("Failed to click Shopping Cart button", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 28.09.2021 by @author Gaurav Singh
	 * 
	 * verifying the The order of the accordion sections should be when device first
	 * 
	 */
	public void validateAccordionSeqDeviceFirst(String[] Input) throws Exception {
		try {
			sf.seleU.waitTillLoading();
			sf.seleU.wait(1500);
			String[] fetchedArray = new String[Input.length];
			List<String> fetchEdArryWithtNull = new ArrayList<String>();
			int a=0;
			sf.seleU.waitTillLoading();
			for(int i=0; i<sf.shopWADevobj.getOrderSeqList.size(); i++) {
					fetchedArray[i] = sf.shopWADevobj.getOrderSeqList.get(i).getText();
			}
			for(String s : fetchedArray) {
				if(s!=null) {
					fetchEdArryWithtNull.add(s);
				}
			}
			outer:
			for(String actualTxt:fetchEdArryWithtNull) {
				inner:
				for(int i=a;i<Input.length;) {
					if(i==fetchedArray.length) {
						break outer;
					}
					if (actualTxt.contains(Input[i])) {
						reportStatusPass(actualTxt, true, true);
						break inner;
					}
					else {  break inner;  }
				}
				a++;
			}
//			accordionsCmnFunc(input4, sf.shopWADevobj.getAccordionOrderAddon);
			accordionsCmnFunc(Input[4], sf.shopWADevobj.getAccordionOrderAcc);

		} catch (Exception e) {
			reportStatusFail("failed To Verify first 3 sequences of Accordion Seq", e);
		}

	}

	public void accordionsCmnFunc(String input4, WebElement element) throws Exception {
		String fetchedEleText4 = sf.seleU.getTextFromWebElement(element);
		if (fetchedEleText4.contains(input4)) {
			reportStatusPass(fetchedEleText4, true, true);
		} else {
			reportStatusFail(fetchedEleText4, false);
		}
    }
	
	/**
	 * Method developed on @Date: 5.10.2021 by @author Gaurav Singh
	 * 
	 * Navigate to BYOD section
	 * 
	 */
	public void navigateToBYOD() throws Exception {
		try {
		if (((sf.addOnSel.byodSelectionButton.isDisplayed()) && (sf.addOnSel.byodSelectionButton.isEnabled()))) {
			sf.seleU.clickElementByJSE(sf.addOnSel.byodSelectionButton);
			sf.seleU.waitTillLoading();
			Assert.assertTrue(sf.seleU.isElementDisplayed(sf.addOnSel.btnBackToBrowseDev));
			reportStatusPass("User is on BYOD page",true, true);
		}
		}
		catch (Exception e) {
			reportStatusFail("failed To Navigatr to BYOD section", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 21.01.2022 by @author Jigyasa Dwivedi
	 * 
	 * To Validate List of AddOn on Select Wireless Product Page.
	 * 
	 * @throws Exception
	 */
	public void verify_ListofAddOn(HashMap addOnPriceMap) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Select AddOn Page@: ";
			sf.seleU.hardwait(2000);;
			sf.seleU.scrollToTop();
			// Iterate AddOn
			for (int i = 0; i < sf.addOnSel.addOnHeadingList.size(); i++) {
				String addOnHeader = sf.seleU.getTextFromWebElement(sf.addOnSel.addOnHeadingList.get(i));
				sf.seleU.clickElementByJSE(sf.addOnSel.addOnHeadingList.get(i));
				// validate addOn
				if (addOnHeader.equalsIgnoreCase("Voicemail")) {
					// Iterate Addon under voice mail
					for (int j = 0; j < sf.addOnSel.voiceMailAddOnList.size(); j++) {
						String addOn = sf.seleU.getTextFromWebElement(sf.addOnSel.voiceMailAddOnList.get(j));
						validate_AddonName_Price(addOnPriceMap, addOn);
					}
				} else {
					// Iterate Addon
					for (int j = 0; j < sf.addOnSel.addOnList.size(); j++) {
						String addOn = sf.seleU.getTextFromWebElement(sf.addOnSel.addOnList.get(j));
						validate_AddonName_Price(addOnPriceMap, addOn);
					}
				}
			}
		} catch (Exception e) {
			reportStatusFail("Error is validating AddOn Name and price on select AddOn Page", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 21.01.2022 by @author Jigyasa Dwivedi
	 * 
	 * To Validate AddOn name and price on Select Wireless Product Page.
	 * 
	 * @throws Exception
	 */
	public void validate_AddonName_Price(HashMap addOnPriceMap, String addOn) throws Exception {
		try {
			methodName = "Select AddOn Page@: ";
			WebElement addOnPrice = driver.findElement(By.xpath("(//*[contains(text(),'" + addOn
					+ "')]/ancestor::div[contains(@class,'bottom_large')]//div[contains(@class,'item-price_text promo')]/span)[1]"));

			// Validate addon is matched with hashmap key
			if (addOnPriceMap.containsKey(addOn)) {
				int price = Integer.parseInt(sf.seleU.getTextFromWebElement(addOnPrice).split("\\.")[0].replaceAll("[^0-9]", ""));
				//validate addon price is matching with hashmap value
				if ((Integer) addOnPriceMap.get(addOn) == price) {
					reportStatusPass(
							methodName + addOn + " : " + addOnPriceMap.get(addOn) + " Addon name and Price is matching with Requirement",
							true, true);
				} else {
					reportStatusFail("AddOn Price is not matching", true);
				}
			}
		} catch (Exception e) {
			reportStatusFail("Error is validating AddOn Name and price on select AddOn Page", e);
			e.printStackTrace();
		}

	}
	/**
	 * Method developed on @Date: 24.01.2022 by @author Jigyasa Dwivedi
	 * 
	 * To Validate List of AddOn on Select Wireless Product Page.
	 * 
	 * @throws Exception
	 */
	public void selectAddOn(String wACC_AddOn_Type, String wACC_AddOn_Name, String wACC_AddOn_Availability)
			throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Select AddOn Page@: ";
			WebElement addOnHeader = driver.findElement(By.xpath("//div[contains(text(),'" + wACC_AddOn_Type + "')]"));
			sf.seleU.clickElementByJSE(addOnHeader);
			// validate addOn
			if (wACC_AddOn_Type.equalsIgnoreCase("Voicemail") || wACC_AddOn_Type.equalsIgnoreCase("Messagerie vocale")) {
				// Iterate Addon under voice mail
				for (int j = 0; j < sf.addOnSel.voiceMailAddOnList.size(); j++) {
					if (sf.seleU.getTextFromWebElement(sf.addOnSel.voiceMailAddOnList.get(j)).equalsIgnoreCase(wACC_AddOn_Name)) {
						sf.seleU.clickElementByJSE(sf.addOnSel.voiceMailAddOnRadioBtnList.get(j));
						reportStatusPass(
								methodName + sf.seleU.getTextFromWebElement(sf.addOnSel.voiceMailAddOnList.get(j))+" Selected.",true, true);
						break;
					}
				}
			} else {
				// Iterate Addon
				for (int j = 0; j < sf.addOnSel.addOnList.size(); j++) {
					//validate add on name and click on addon
					if (sf.seleU.getTextFromWebElement(sf.addOnSel.addOnList.get(j)).equalsIgnoreCase(wACC_AddOn_Name)) {
						sf.seleU.clickElementByJSE(sf.addOnSel.addOnradioBtnList.get(j));
						reportStatusPass(
								methodName + sf.seleU.getTextFromWebElement(sf.addOnSel.addOnList.get(j))+" Selected.",true, true);
					// validate promo code AddOn_Availability
						List<WebElement> offerList = driver.findElements(By.xpath("//*[contains(text(), '"+wACC_AddOn_Name+"')]/ancestor::div[contains(@class,'around_large slds-m-bottom_large')]//*[contains(@class,'label radio-options_text')]"));
						List<WebElement> offerRadioBtnList = driver.findElements(By.xpath("//*[contains(text(), '"+wACC_AddOn_Name+"')]/ancestor::div[contains(@class,'around_large slds-m-bottom_large')]//*[contains(@class,'label radio-options_text')]/preceding-sibling::span"));
					if(offerList.size()>0) {
						int count=0;
						for(WebElement ele: offerList) {
							String offer=(sf.seleU.getTextFromWebElement(ele));
							if(offer.equalsIgnoreCase("International LD Saver") || offer.equalsIgnoreCase("US LD") ||
									offer.equalsIgnoreCase("Interurbains aux États-Unis") || offer.equalsIgnoreCase("InterÉpargne outre-mer")) {
								sf.seleU.clickElementByJSE(offerRadioBtnList.get(count));
								reportStatusPass(
										methodName + sf.seleU.getTextFromWebElement(ele)+" promo Selected.",true, true);
								break;
							}
							count++;
						}// offerList for close
					}
				}//validate add on name if close
				}//addOn list for close			
			}
			sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
			sf.seleU.waitForLoading();
			reportStatusPass(methodName + "Add to Cart Selected for AddOn "+ wACC_AddOn_Name ,true, true);
		} catch (Exception e) {
			reportStatusFail("Error is selecting addon and click add to cart on select AddOn Page", e);
		}
	}

	/**
	* Method developed on @Date: 14.01.2022 by @author Sandesh Kumbhar
	*
	* To click close button on
	*
	* @throws Exception
	*
	*/
	public void closeSelection() throws Exception {
		try {
			methodName = "WACC close selection for user selections @: ";
		sf.seleU.waitTillLoading();
		Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.addOnSel.btnClose));
		sf.seleU.clickElementByJSE(sf.addOnSel.btnClose);
		sf.seleU.wait(2000);
		reportStatusPass(methodName + "Close button is clicked", true, true);
		} catch (Exception e) {
		reportStatusFail("Error is validating applied filter on Shop for accessories Page", e);
		e.printStackTrace();
		}
		}
	/**
	* Method developed on @Date: 24.01.2022 by @author Priyanka Tawade
	*
	* To Leave Editing Wireless AddOns
	*
	* @throws Exception
	*
	*/
	public void leaveUpdateLongDistancePlans(String addOnType, String addOnName, String addOnAvailability) throws Exception {
		try {
			methodName = "WACC Long_Distance_Plans Details Validation@: ";
			// Select AddOns
			if (addOnType.equalsIgnoreCase(InputData_WA.addOnType_LD)
					&& (addOnName.equalsIgnoreCase(InputData_WA.addOnName_International_LDSaver))
					&& (addOnAvailability.equalsIgnoreCase(InputData_WA.LD_NoOffer))) {
				System.out.println("Entered this ld no offer if");
				sf.seleU.clickElementByJSE(sf.addOnSel.noOfferUSLDRadio);
				sf.seleU.wait(1000);				
				sf.seleU.clickElementByJSE(sf.addOnSel.InternationalLDSaver_AddOn);
				sf.seleU.wait(1000);
				
				reportStatusPass(methodName + addOnName + " and " + addOnAvailability + " is selected ", true, false);
			}			
		} catch (Throwable e) {
			reportStatusFail(" error on chosing Long distance AddOns", e);
	e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 14.02.2022 by @author Jigyasa Dwivedi
	 * Method to select BYOD option.
	 * @throws Exception 
	 * 
	 */
	public void select_ContinueBYOD() throws Exception {
		try {
			methodName = "WACC Select Product Page@: ";
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.addOnSel.byodSelectionButton);
			reportStatusPass(methodName + "BYOD option is clicked", true, true);
			sf.seleU.waitForLoading();
		} catch (Throwable e) {
			reportStatusFail("Error on clicking BYOD option", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 23.02.2022 by @author Jigyasa Dwivedi
	 * Method to validate Edit Button present.
	 * @throws Exception 
	 * 
	 */
	public void validateEditButtonPresent(String[] productList) throws Exception {
		try {
			methodName = "WACC Select Product Page@: ";
			sf.seleU.hardwait(2000);
			for (int i = 0; i < productList.length; i++) {
				WebElement ele = driver.findElement(By.xpath("//*[contains(text(),'" + productList[i]+ "')]/../following-sibling::div//*[contains(text(),'Edit')]"));
				if (sf.seleU.isElementDisplayed(ele)) {
					reportStatusPass(methodName + "Edit button is present for Product: " + productList[i], true, true);
				} else
					reportStatusFail(methodName + "Edit button is not present for Product: " + productList[i], true);
			}
		} catch (Throwable e) {
			reportStatusFail("Error on clicking BYOD option", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 23.02.2022 by @author Jigyasa Dwivedi
	 * Method to validate Continue Button present while select BYOD option on select product page.
	 * @throws Exception 
	 * 
	 */
	public void validateBYODContinueButton() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "WACC Select Product Page@: ";
			sf.seleU.hardwait(2000);
			if (sf.seleU.isElementDisplayed(sf.addOnSel.continueButtonForBYOD)) {
				sf.seleU.clickElementByJSE(sf.addOnSel.continueButtonForBYOD);
				sf.seleU.waitForLoading();
				reportStatusPass(methodName + "Continue button is present and clicked for BYOD: ", true, true);
				Assert.assertTrue(sf.seleU.isElementDisplayed(sf.proSel.browseAccessories));
			} else
				reportStatusFail(methodName + "Continue button is not present for BYOD: ", true);

		} catch (Throwable e) {
			reportStatusFail("Error on clicking BYOD option", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 23.02.2022 by @author Jigyasa Dwivedi
	 * Method to validate Continue to Plans Button present on select product page, while select bring my device option.
	 * @throws Exception 
	 * 
	 */
	public void validateContinueToPlansButton() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "WACC Select Product Page@: ";
			sf.seleU.hardwait(2000);
			if (sf.seleU.isElementDisplayed(sf.addOnSel.continueToPlansButton)) {
				sf.seleU.clickElementByJSE(sf.addOnSel.continueToPlansButton);
				sf.seleU.waitForLoading();
				reportStatusPass(methodName + "Continue to plans button is present & clicked for BYOD: ", true, true);
				Assert.assertTrue(sf.seleU.isElementDisplayed(sf.proSel.wirelessPlansSubHeader));								
			} else {
				reportStatusFail(methodName + "Continue to plans button is not present for BYOD: ", true);
			}
		} catch (Throwable e) {
			reportStatusFail("Error on clicking BYOD option", e);
			e.printStackTrace();
		}
	}
}
