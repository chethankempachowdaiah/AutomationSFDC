package sfdc.pages.wireless;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.framework.base.MyListener;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Sandesh , Gaurav, Date : 21/09/2021
 * 
 *         Shop Wireless Devices Section
 *
 */
public class WACC_ShopWirelessDevices_Page extends MyListener {

	private static final Object[] Input = null;
	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public WACC_ShopWirelessDevices_Page() {
		sf = new SFDC_AllPageObjects();

	}

	/**
	 * @throws Exception /** Method developed on @Date: 15.09.2021 by @author
	 *                   Sandesh Kumbhar
	 * 
	 *                   Input- Brand Name which displays all wireless devices based
	 *                   on brand name selection
	 * 
	 */

	public void selectBrandName(String brandName) throws Exception {
		try {
			methodName = "Select Brand Name  ";

			for (int i = 0; i < sf.shopWADevobj.listBrands.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.shopWADevobj.listBrands.get(i)).equalsIgnoreCase(brandName)) {
					sf.seleU.clickElementByJSE(sf.shopWADevobj.listBrands.get(i));
					sf.seleU.waitTillLoading();
					reportStatusPass(methodName + " Input Brand Name is Selected " + brandName, true, true);
					sf.seleU.hardwait(1200);
					break;
				}

			}

		} catch (Exception e) {
			reportStatusFail("Failed to select the provided brand name on Device Listing Page", e);
			e.printStackTrace();
		}

	}

	public void clickOnViewDetailsBtn() throws Exception {
		try {
			methodName = "Wireless Devices View details button: ";
			// Check view details btn for selected brand devices is displayed
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.shopWADevobj.viewDetailsbtn));
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.shopWADevobj.viewDetailsbtn);
			sf.seleU.wait(2000);
			sf.seleU.waitForLoading();
			reportStatusPass(methodName + sf.seleU.isElementDisplayed(sf.shopWADevobj.backToBrowDevBtn)
					+ " Device details page is displayed", true, true);
			reportStatusPass(methodName + "Device details page is displayed ", true, true);
		} catch (Throwable e) {
			reportStatusFail("Error on clicking View details button on Device Listing Page", e);
			e.printStackTrace();
		}
	}

	public void clickAddToCartBtnDevListPage() throws Exception {
		try {
			methodName = "CLick on add to cart button on device Details page ";
			sf.seleU.waitTillLoading();
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.shopWADevobj.addToCartBtnDevDetPge));
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.shopWADevobj.addToCartBtnDevDetPge);
			sf.seleU.waitForLoading();
			reportStatusPass(methodName + "Device is added ", true, true);
		} catch (Throwable e) {
			reportStatusFail("Error on clicking add to cart button on Device Details Page", e);
		}
	}

	/**
	 * Method developed on @Date: 15.09.2021 by @author Gaurav Singh
	 * 
	 * To Select the wireless device
	 * 
	 */

	public void selectWirelessDevice(String BrandName, String DeviceName, String flowName) throws IOException {
		try {
			sf.seleU.waitTillLoading();
			if (flowName.equalsIgnoreCase("ShopDeviceFirst")) {
				sf.seleU.ScrolltoElement(sf.shopWADevobj.shopWirelessDeviceBtn);
				sf.seleU.wait(1200);
				sf.seleU.clickElementByJSE(sf.shopWADevobj.shopWirelessDeviceBtn);
			}
			sf.seleU.waitForLoading();
			try {
				if (sf.shopWADevobj.lightngContinueButtn.isDisplayed()) {
					sf.seleU.clickElementByJSE(sf.shopWADevobj.lightngContinueButtn);
				}
			} catch (Exception e) {
			}
			selectBrandName(BrandName);
			sf.seleU.wait(1000);
			String myXpath = ".//div[contains(@class,'device-name') and text()='" + DeviceName + "']";
			String fullPricexPath = myXpath + "/..//div[contains(@class,'amount')]/span";
			String financePricexPath = myXpath + "/..//span[contains(@class,'amount')]";
			String tapOnWirelssDetails = myXpath + "/..//button";
			sf.seleU.findElement(By.xpath(financePricexPath));
			WebElement slectDevice = sf.seleU.findElement(By.xpath(tapOnWirelssDetails));
			WebElement fp = sf.seleU.findElement(By.xpath(financePricexPath));
			String fullPrice = sf.seleU.findElement(By.xpath(fullPricexPath)).getText().replaceAll("[^0-9.,]", "");
			String financePrice = sf.seleU.findElement(By.xpath(financePricexPath)).getText().replaceAll("[^0-9.,]", "");
			reportStatusPass("Successfully fetched " + DeviceName + " full price " + fullPrice + " and financePrice "
					+ financePrice, true, true);
			sf.seleU.ScrolltoElement(fp);
			sf.seleU.wait(1200);
			sf.seleU.clickElementByJSE(slectDevice);
			sf.seleU.waitForLoading();
			sf.seleU.waitElementToBeVisible(sf.shopWADevobj.reachedDeviceDetailPage);
			Assert.assertTrue(sf.seleU.isElementDisplayed(sf.shopWADevobj.reachedDeviceDetailPage));
			sf.seleU.ScrolltoElement(sf.shopWADevobj.reachedDeviceDetailPage);
			reportStatusPass("Successfully selected wireless device and reached to device detail page", true, true);
		} catch (Exception e) {
			reportStatusFail("Failed to select wireless device", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 17.09.2021 by @author Gaurav Singh
	 * 
	 * Credit and discount validation method in device detail page before attribute
	 * selection
	 * 
	 */
	public void validateDiscountAndCreditFunc() throws Exception {
		commonOffersPresneceFunct(sf.shopWADevobj.verifyOfferfromDeviceDetail, sf.shopWADevobj.reachedDeviceDetailPage);
		commonOffersPresneceFunct(sf.shopWADevobj.financedPrice, sf.shopWADevobj.selectDifferentCapacity);
	}

	/**
	 * Method developed on @Date: 17.09.2021 by @author Gaurav Singh
	 * 
	 * 
	 * Discount validation post attribute (Size and colour) change in device detail
	 * page
	 * 
	 */
	public void checkDiscountAfterAttributeSelcn() throws IOException {
		try {
			sf.seleU.clickElementByJSE(sf.shopWADevobj.selectDifferentColor);
			hanldeLightningPopupOnAttriChange();
			sf.seleU.waitTillLoading();
			commonOffersPresneceFunct(sf.shopWADevobj.verifyOfferfromDeviceDetail,
					sf.shopWADevobj.reachedDeviceDetailPage);
			String oldFinancedPrice = sf.shopWADevobj.financedPrice.getText().replaceAll("[^0-9.]", "");
			commonOffersPresneceFunct(sf.shopWADevobj.financedPrice, sf.shopWADevobj.selectDifferentCapacity);
			sf.seleU.scrollToTop();
			sf.seleU.clickElementByJSE(sf.shopWADevobj.selectDifferentCapacity);
			hanldeLightningPopupOnAttriChange();
			commonOffersPresneceFunct(sf.shopWADevobj.verifyOfferfromDeviceDetail, sf.shopWADevobj.deviceModel);
			sf.seleU.waitTillLoading();
			String newPriceAftersizeChanged = sf.shopWADevobj.financedPrice.getText().replaceAll("[^0-9.]", "");
			commonOffersPresneceFunct(sf.shopWADevobj.financedPrice, sf.shopWADevobj.selectDifferentCapacity);
			Assert.assertNotSame("Old and new prices should not be equal post changing attribute", oldFinancedPrice,
					newPriceAftersizeChanged);
			reportStatusPass("Successfully validated  on attribute change, and finaince price changed", true, true);
		} catch (Exception e) {
			reportStatusFail("Failed to validate finance price on size change", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 17.09.2021 by @author Gaurav Singh
	 * 
	 * Credit and discount validation in device detail page
	 * 
	 */
	public void commonOffersPresneceFunct(WebElement e1, WebElement ScrollTo) throws Exception {
		String offerPresent = null;
		try {
			sf.seleU.waitTillLoading();
			sf.seleU.wait(1000);
			sf.seleU.ScrolltoElement(ScrollTo);
			sf.seleU.wait(1200);
			hanldeLightningPopupOnAttriChange();
			sf.seleU.waitElementToBeVisible(e1);
			if (e1.equals(sf.shopWADevobj.verifyOfferfromDeviceDetail)) {
				List<String> l2 = new ArrayList<>();
				logger.info("Offers size is : " + sf.shopWADevobj.verifyOfferfrmDevDetlList.size());
				if (sf.shopWADevobj.verifyOfferfrmDevDetlList.size() > 1) {
					Iterator<WebElement> i1 = sf.shopWADevobj.verifyOfferfrmDevDetlList.iterator();
					while (i1.hasNext()) {
						WebElement e2 = i1.next();
						l2.add(e2.getText());
					}
				}
				offerPresent = String.join(" ", l2);
			} else {
				offerPresent = e1.getText();
			}
			Assert.assertTrue(sf.seleU.isElementDisplayed(e1));
			colour = sf.seleU.getTextFromWebElement(sf.shopWADevobj.colourSelected);
			size = sf.seleU.getTextFromWebElement(sf.shopWADevobj.sizeSelected);
			reportStatusPass(
					"OffersPresent was : " + offerPresent + " when color was : " + colour + " and size was : " + size,
					true, true);
		} catch (Exception e) {
			reportStatusFail("Failed to verify " + offerPresent, e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 21.09.2021 by @author Gaurav Singh
	 *
	 * 
	 * Availability status validation in device detail page
	 *
	 */
	public void verifyAvaialibilityStatus(String availabilityDelayed, String availabilityInStock) throws IOException {
		try {
			fetchDeviceAvaiaibility(availabilityDelayed, availabilityInStock);
			sf.seleU.ScrolltoElement(sf.shopWADevobj.reachedDeviceDetailPage);
			try {
				sf.seleU.clickElementByJSE(sf.shopWADevobj.selectDifferentColor);
				sf.seleU.waitTillLoading();
				hanldeLightningPopupOnAttriChange();
			} catch (Exception e) {
			}
			fetchDeviceAvaiaibility(availabilityDelayed, availabilityInStock);
			clickAddToCartBtnDevListPage();
			sf.seleU.waitTillLoading();
			Assert.assertTrue(sf.seleU.isElementDisplayed(sf.shopWADevobj.getWirelessDeviceAdded));
		} catch (Exception e) {
			reportStatusFail("Failed to verify Avaialibility Status", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 21.09.2021 by @author Gaurav Singh
	 * 
	 * fetching Availability status validation in device detail page
	 * 
	 */
	String colour, size;

	public void fetchDeviceAvaiaibility(String availabilityDelayed, String availabilityInStock) throws Exception {
		sf.seleU.ScrolltoElement(sf.shopWADevobj.deviceModel);
		sf.seleU.wait(1200);
		String deviceDetails = null;
		try {
			Iterator<WebElement> i1 = sf.shopWADevobj.deviceAvailability.iterator();
			StringBuffer statement = new StringBuffer();
			while (i1.hasNext()) {
				deviceDetails = i1.next().getText();
				statement.append(deviceDetails).toString().trim();
			}
			if (statement.toString().contains(availabilityDelayed)) {
				Assert.assertEquals(statement.toString(), availabilityDelayed);
			} else {
				Assert.assertEquals(statement.toString(), availabilityInStock);
			}
			colour = sf.seleU.getTextFromWebElement(sf.shopWADevobj.colourSelected);
			size = sf.seleU.getTextFromWebElement(sf.shopWADevobj.sizeSelected);
			reportStatusPass("Availaibility Status of device was : " + statement + " when color was : " + colour
					+ " and size was : " + size, true, true);
			sf.seleU.ScrolltoElement(sf.shopWADevobj.financedPrice);
			Assert.assertTrue(sf.seleU.isElementDisplayed(sf.shopWADevobj.AddToCart));
			Assert.assertTrue(sf.shopWADevobj.AddToCart.isEnabled());
			reportStatusPass("Availaibility Status of device was : " + statement + " and Add To Cart was enabled : "
					+ sf.seleU.isElementDisplayed(sf.shopWADevobj.AddToCart), true, true);
		} catch (Exception e) {
			reportStatusFail("Failed to verify " + deviceDetails, e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 22.09.2021 by @author Sandesh Kumbhar
	 * 
	 * verifying the count of Devices displayed as per Brand selection
	 * 
	 */

	public int verifyCountOfSmartphones() {

		String CountSmartphone = sf.shopWADevobj.countNoSmartphones.getText().replaceAll("[^0-9]", "");
		int a = Integer.parseInt(CountSmartphone);
		int noOfDevices = sf.shopWADevobj.selectDeviceName.size() - 1;
		/*
		 * logger.info("Count of smartphones as per brand selected : " + noOfDevices);
		 * logger.info("Comapre and print values " + noOfDevices + " " + a);
		 */
		return noOfDevices;
	}

	/**
	 * Method developed on @Date: 21.09.2021 by @author Gaurav Singh
	 * 
	 * To handle ligtning popup on attribute selection on device detail page
	 * 
	 */
	public void hanldeLightningPopupOnAttriChange() {
		try {
			if (sf.shopWADevobj.okLigtngBtn.isDisplayed()) {
				sf.seleU.clickElementByJSE(sf.shopWADevobj.okLigtngBtn);
				sf.seleU.wait(800);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * Method developed on @Date: 30.09.2021 by @author Gaurav Singh
	 * 
	 * To handle skip With Adding Device Protection button.
	 * 
	 */
	public void skipWithAddingDevceProtetcion() {
		try {
			sf.seleU.waitTillLoading();
			sf.seleU.wait(1200);
			if (sf.shopWADevobj.skpWithoutAdingPrtcn.isDisplayed()) {
				sf.seleU.clickElementByJSE(sf.shopWADevobj.skpWithoutAdingPrtcn);
				sf.seleU.waitTillLoading();
				sf.seleU.wait(800);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * Method developed on @Date: 1.10.2021 by @author Gaurav Singh
	 * 
	 * To select full pay cost of the device on device detail screen
	 * 
	 */
	public void selectDevicePaywithFullPrice() throws Exception {
		try {
			sf.seleU.waitTillLoading();
			sf.seleU.ScrolltoElement(sf.shopWADevobj.financedPrice);
			if (sf.shopWADevobj.deviceFullPriceCheckbox.isDisplayed()
					&& sf.shopWADevobj.deviceFullPriceCheckbox.isEnabled()) {
				sf.seleU.clickElementByJSE(sf.shopWADevobj.deviceFullPriceCheckbox);
				sf.seleU.waitTillLoading();
				if (sf.shopWADevobj.FullPricePaySelected.isDisplayed()) {
					String financeSelected = sf.shopWADevobj.FullPricePaySelected.getText();
					if (financeSelected.equalsIgnoreCase("Full price")) {
						reportStatusPass("financeSelected value is " + financeSelected, true, true);
					}
				}
			}
		} catch (Exception e) {
			reportStatusFail("Failed to select full price pay", false);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 4.10.2021 by @author Gaurav Singh
	 * 
	 * To select 2 year finance plan on device detail screen
	 * 
	 */
	public void changeFinanceTenure() throws Exception {
		try {
			sf.seleU.waitTillLoading();
			String oldFinancePrice = sf.shopWADevobj.financedPrice.getText();
			if (sf.shopWADevobj.select2YrFinceOfDvce.isDisplayed()
					&& sf.shopWADevobj.select2YrFinceOfDvce.isEnabled()) {
				sf.seleU.ScrolltoElement(sf.shopWADevobj.selectDifferentCapacity);
				sf.seleU.clickElementByJSE(sf.shopWADevobj.select2YrFinceOfDvce);
				sf.seleU.waitTillLoading();
				if (sf.shopWADevobj.select3YrFinceOfDvce.getAttribute("class").contains("deselected")) {
					String NewFinancePrice = sf.shopWADevobj.financedPrice.getText();
					Assert.assertNotSame("Failed to select 2 years finance plan!", NewFinancePrice, oldFinancePrice);
					reportStatusPass("Selected 2 year finance tenure.", true, true);
				}
			}
		} catch (Exception e) {
			reportStatusFail("Failed to select 2 years finance plan!", false);
			e.printStackTrace();
		}
	}

	public void chckAvailbltyOfPrice(WebElement detailsPgePrceChck) {
		sf.seleU.waitTillLoading();
		sf.seleU.ScrolltoElement(sf.shopWADevobj.selectDifferentColor);
		String PriceInDetailsPge = detailsPgePrceChck.getText().replaceAll("[^0-9.]", "");
		Assert.assertNotNull(PriceInDetailsPge);
		sf.seleU.clickElementByJSE(sf.shopWADevobj.backToBrowDevBtn);
		sf.seleU.waitTillLoading();
		sf.seleU.waitElementToBeVisible(sf.shopWADevobj.countNoSmartphones);
	}

	/**
	 * Method developed on @Date: 13.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to retrieve device price from device details page.
	 * 
	 * @throws Exception
	 * 
	 */
	public double get_devicePriceOnDetailsPage() throws Exception {
		String str = "";
		methodName = "Device Details Page@: ";
		try {
			str = sf.seleU.getTextFromWebElement(sf.shopWADevobj.FincePriceDeviceDetwithoutDecimal) + "."
					+ sf.seleU.getTextFromWebElement(sf.shopWADevobj.FincePriceDeviceDetwithDecimal);
			reportStatusPass(methodName + "device Price: " + str, true, true);
		} catch (Exception e) {
			reportStatusFail("Error is get Footer price. ", e);
			e.printStackTrace();
		}
		return Double.parseDouble(str);
	}

	/**
	 * 
	 * Method developed on @Date: 21.01.2022 by @author Priyanka Tawade
	 * 
	 * Method to Click Back To Browse Devices.
	 */

	public void clickBackToBrowseDevices() throws Exception {
		try {
			methodName = "WACC click browse devices for updating a device @: ";
			sf.seleU.waitTillLoading();
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.shopWADevobj.clickBackToBrowseDevices));
			sf.seleU.clickElementByJSE(sf.shopWADevobj.clickBackToBrowseDevices);
			sf.seleU.wait(2000);
			reportStatusPass(methodName + "Back to browse devices button is clicked", true, true);
		} catch (Exception e) {
			reportStatusFail("sorry! not able to browse devices", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 10.01.2022 by @author Viswas Reddy
	 * 
	 * Method to retrieve device full price from device details page.
	 * 
	 * @throws Exception
	 * 
	 */
	public double get_deviceFullPriceOnDetailsPage(WebElement element1, WebElement element2) throws Exception {
		String str = "";
		methodName = "Device Details Page@: ";
		try {
			str = sf.seleU.getTextFromWebElement(element1) + "." + sf.seleU.getTextFromWebElement(element2);
			reportStatusPass(methodName + "device Price: " + str, true, true);
		} catch (Exception e) {
			reportStatusFail("Error in getting device full price. ", e);
			e.printStackTrace();
		}
		return Double.parseDouble(str);
	}

	/**
	 * Method developed on @Date: 12.01.2022 by @author Viswas Reddy
	 * 
	 * Method to retrieve footer prices except margin TCV from footer section.
	 * 
	 * @throws Exception
	 * 
	 */
	public double get_FooterPrice(WebElement element, String fieldName) throws Exception {
		String str = "";
		try {
			str = sf.seleU.getTextFromWebElement(element);
			reportStatusPass(fieldName + str, true, true);
			str = str.replace(",", "");
		} catch (Exception e) {
			reportStatusFail("Error in getting the " + fieldName + "Price ", e);
			e.printStackTrace();
		}
		return Double.parseDouble(str.replace("$", ""));
	}

	/**
	 * Method developed on @Date: 12.01.2022 by @author Viswas Reddy
	 * 
	 * Method to retrieve footer margin TCV price from footer section.
	 * 
	 * @throws Exception
	 * 
	 */
	public double get_FooterMarginTCV(WebElement element, String fieldName) throws Exception {
		String str = "";
		String strDollar = "";
		try {
			str = sf.seleU.getTextFromWebElement(element);
			reportStatusPass(fieldName + str, true, true);
			str = str.replace(",", "");
			String[] strAr1 = str.split(" ");
			strDollar = strAr1[0].replace("$", "");
		} catch (Exception e) {
			reportStatusFail("Error in getting the Margin TCV Price, ", e);
			e.printStackTrace();
		}
		return Double.parseDouble(strDollar);
	}

	/**
	 * Method developed on @Date: 13.01.2022 by @author Viswas Reddy
	 * 
	 * Method to retrieve number of financing months when we select a device.
	 * 
	 * @throws Exception
	 * 
	 */
	public int get_numberOfMonths(WebElement element) throws Exception {
		String str = "";
		try {
			str = sf.seleU.getTextFromWebElement(element);
			reportStatusPass("Finance number of months: " + str, true, true);
		} catch (Exception e) {
			reportStatusFail("Error in getting financed no. of months, ", e);
			e.printStackTrace();
		}
		return Integer.parseInt(str.replaceAll("[^\\d]", ""));
	}

	/**
	 * Method developed on @Date: 19.01.2022 by @author Viswas Reddy
	 * 
	 * Method to validate footer prices with addition.
	 * 
	 * @throws Exception
	 * 
	 */
	public void get_AddFooter(double presentVal, double prevVal, double fullPrice, String name) throws Exception {
		try {
			String sPresentVal = get_decimalToString(presentVal, 2);
			String sPrevVal = get_decimalToString(prevVal + fullPrice, 2);
			if (sPresentVal.equals(sPrevVal)) {
				reportStatusPass(name + "validation pass", true, true);
			} else {
				reportStatusFail(name + "validation fail", true);
			}
		} catch (Exception e) {
			reportStatusFail(name + "validation fail", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 19.01.2022 by @author Viswas Reddy
	 * 
	 * Method to validate footer prices with subtraction.
	 * 
	 * @throws Exception
	 * 
	 */
	public void get_SubFooter(double presentVal, double prevVal, double fullPrice, String name) throws Exception {
		try {
			String sPresentVal = get_decimalToString(presentVal, 2);
			String sPrevVal = get_decimalToString(prevVal - fullPrice, 2);
			if (sPresentVal.equals(sPrevVal)) {
				reportStatusPass(name + "validation pass", true, true);
			} else {
				reportStatusFail(name + "validation fail", true);
			}
		} catch (Exception e) {
			reportStatusFail(name + "validation fail", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 19.01.2022 by @author Viswas Reddy
	 * 
	 * Method to validate footer prices with multiplication.
	 * 
	 * @throws Exception
	 * 
	 */
	public void get_MultipleFooter(double presentVal, double prevVal, int number, String name) throws Exception {
		try {
			if (presentVal == prevVal * number) {
				reportStatusPass(name + "validation pass", true, true);
			} else {
				reportStatusFail(name + "validation fail", true);
			}
		} catch (Exception e) {
			reportStatusFail(name + "validation fail", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 19.01.2022 by @author Viswas Reddy
	 * 
	 * Method to validate footer prices with comparison.
	 * 
	 * @throws Exception
	 * 
	 */
	public void get_EqualsFooter(double presentVal, double prevVal, String name) throws Exception {
		try {
			if (presentVal == prevVal) {
				reportStatusPass(name + "validation pass", true, true);
			} else {
				reportStatusFail(name + "validation fail", true);
			}
		} catch (Exception e) {
			reportStatusFail(name + "validation fail", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 28.01.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate help bubble text for devices.
	 * 
	 * @throws Exception
	 * 
	 */
	public void validateHelpBubbleText(String deviceName, String toolTipText) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Device Listing/Details Page@: ";
			sf.seleU.hardwait(2000);
			sf.seleU.waitForLoading();
			for (int i = 0; i < sf.shopWADevobj.selectDeviceName.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.shopWADevobj.selectDeviceName.get(i))
						.equalsIgnoreCase(deviceName)) {
					helpBubbleText(toolTipText, i-1);
					break;
				}
			}
		} catch (Exception e) {
			reportStatusFail(methodName + " Error in help ToolTip Text.", e);
		}
	}

	/**
	 * Method developed on @Date: 28.01.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate help bubble text for devices.
	 * 
	 * @throws Exception
	 * 
	 */
	public void helpBubbleText(String toolTipText, int count) throws IOException {
		try {
			methodName = "Device Listing/Details Page@: ";
			if (count == 0) {
				sf.seleU.ScrolltoElement(sf.shopWADevobj.financeTenure);
				sf.seleU.moveToThenSlowClickElement(sf.shopWADevobj.helpIconDeviceDetails);

			} else {
				sf.seleU.ScrolltoElement(sf.shopWADevobj.deviceImageList.get(count+1));
				sf.seleU.moveToThenSlowClickElement(sf.shopWADevobj.helpToolTipIcon.get(count));
			}
			Thread.sleep(2000);	
			if (sf.seleU.getTextFromWebElement(sf.shopWADevobj.helpToolTipText).equalsIgnoreCase(toolTipText)) {
				reportStatusPass(methodName + " help ToolTip Text is matching", true, true);				
			} else {
				reportStatusFail(methodName + " help ToolTip Text is not matching", true);
			}
		} catch (Exception e) {
			reportStatusFail(methodName + " Error in help ToolTip Text.", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 11.02.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate Finance Month Text for devices.
	 * 
	 * @throws Exception
	 * 
	 */
	public void validateFinanceMonthText(String str) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Device Listing/Details Page@: ";
			for (int i = 0; i < sf.shopWADevobj.financeTextList.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.shopWADevobj.financeTextList.get(i)).contains(str)) {
					Assert.assertTrue(true);
					// reportStatusPass(methodName + " Finance Month Text is matching", true, true);
				}
			}
		} catch (Exception e) {
			reportStatusFail(methodName + " Error in Finance Month Text is not matching.", e);
		}
	}

	/**
	 * Method developed on @Date: 04.02.2022 by @author viswas reddy
	 * 
	 * Method to get number of fraction digits.
	 * 
	 * @throws Exception
	 * 
	 */
	public String get_decimalToString(double value, int idecimals) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(idecimals);
		return df.format(value);
	}

	/**
	 * Method developed on @Date: 09.02.2022 by @author viswas reddy
	 * 
	 * Method to validate in DP page no DP is selected then add to cart is disable
	 * and skip without DP button is enable.
	 * @throws Exception
	 * 
	 */
	public void validate_DPaddtoCartnotEnableandSkipDpEnable() throws Exception {
		try {
			sf.seleU.ScrolltoElement(sf.proSel.addToCartDP);
			if (sf.proSel.addToCartDP.isEnabled()) {
				reportStatusFail("add to cart is clickable without selecting any protection", true);
			} else if (!(sf.proSel.addToCartDP.isEnabled()) && sf.shopWADevobj.skpWithoutAdingPrtcn.isDisplayed()) {
				reportStatusPass("add to cart is not clickable because DP is not selected and skip DP is clickable",
						true, true);
			}
		} catch (Exception e) {
			reportStatusFail("validation failed in DP add to cart skip DP", true);
		}

	}

	/**
	 * Method developed on @Date: 09.02.2022 by @author viswas reddy
	 * 
	 * Method to validate in DP page click skip DP and land in wireless plans page
	 * or accessories
	 * @throws Exception
	 * 
	 */
	public void validate_landingOnWirelessPlansOrAccepage(String landingPage) throws Exception {
		try {
			sf.seleU.waitTillLoading();
			if (landingPage.equalsIgnoreCase("wireless plans")) {
				if (sf.seleU.getTextFromWebElement(sf.proSel.headerWirelessPlans)
						.equalsIgnoreCase("Select  your Rogers wireless plan")) {
					reportStatusPass("landed on wireless plans", true, true);
				}
			} else if (landingPage.equalsIgnoreCase("accessories page")) {
				if (sf.seleU.getTextFromWebElement(sf.proSel.headerAccePage).equalsIgnoreCase("Shop for accessories")) {
					reportStatusPass("landed on Accessories Page", true, true);
				}
			}
		} catch (Exception e) {
			reportStatusFail("validation failed landed on different page", true);
		}
	}

	/**
	 * Method developed on @Date: 11.02.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate Payment Option Text for devices.
	 * 
	 * @throws Exception
	 * 
	 */
	public void validatePaymentOptionText(String text) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Device Details Page@: ";
			if (sf.seleU.getTextFromWebElement(sf.shopWADevobj.PaymentOptionTextDeviceDet).equalsIgnoreCase(text)) {
				reportStatusPass(methodName + " Payment Option Text is matching", true, true);
			} else {
				reportStatusFail("Payment Option Text is not matching on Device Details Page.", true);
			}

		} catch (Exception e) {
			reportStatusFail("Error in Payment Option Text on Device Details Page.", true);
		}
	}

	/**
	 * Method developed on @Date: 11.02.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate Payment Option Text for devices.
	 * 
	 * @throws Exception
	 * 
	 */
	public void validate_24MonthFinanceOption() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Device Details Page@: ";
			if (sf.shopWADevobj.financeOption.get(0).getAttribute("onclick")==null	&& sf.shopWADevobj.financeOption.size() == 1) {
				reportStatusPass(methodName + " 24 Month Payment Option is not clickable", true, true);
			} else {
				reportStatusFail("24 Month Payment Option is clickable on Device Details Page.", true);
			}

		} catch (Exception e) {
			reportStatusFail("Error in 24 Month Payment Option is not clickable on Device Details Page.", true);
		}
	}
	
	/**
	 * Method developed on @Date: 10.02.2022 by @author viswas reddy
	 * 
	 * Method to validate in DP page DP is selected then add to cart is enabled
	 *  and skip without DP button is enabled.
	 * @throws Exception 
	 * @throws Exception 
	 * 
	 */
	public void validate_DPaddtoCartandSkipDpBothClickable() throws Exception {
		try {
			sf.seleU.ScrolltoElement(sf.proSel.addToCartDP);
			if (sf.proSel.addToCartDP.isEnabled() && sf.shopWADevobj.skpWithoutAdingPrtcn.isDisplayed()) {
				reportStatusPass("add to cart is clickable because DP is selected and skip DP is clickable", true, true);
			} 
		}catch (Exception e) {
			reportStatusFail("validation failed in DP add to cart skip DP", true);
		}
		
	}
  
	/**
	 * Method developed on @Date: 18.02.2022 by @author priyanka tawade
	 * 
	 * Method to change device configuration
	 * @throws Exception 
	 * @throws Exception 
	 * 
	 */
	public void changeDeviceConfiguration(String val) throws Exception {
		try {
			sf.seleU.hardwait(3000);
			WebElement ele = driver.findElement(By.xpath("//div[contains(@value,'"+val+"')]"));
			sf.seleU.clickElementByJSE(ele);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName +" Device Configuration changed to" +val+ "and Add to cart button is replace by Update Cart button" , true, true);
		}catch (Exception e) {
			reportStatusFail("Failed to change device configuration", true);
		}
		
	}
	
	/**
	 * Method developed on @Date: 06.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate applied filter on Device Listing Page.
	 * 
	 * @throws Exception
	 */
	public void verify_AppliedPriceFilterOnDeviceListingPage(String filter) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Device/Accessories Listing page@: ";
			sf.seleU.hardwait(2000);
			sf.seleU.scrollToTop();
			String temp = filter.replace('$', ' ');
			String[] str = temp.split("-");
			double lowPrice = Double.parseDouble(str[0].trim());
			double highestPrice = Double.parseDouble(str[1].trim());
			for (int i = 0; i < sf.shopWADevobj.deviceList.size(); i++) {
				double tempPrice = Double.parseDouble(sf.seleU.getTextFromWebElement(sf.shopWADevobj.devicePriceLstOnDeviceList.get(i)).replaceAll("[^0-9.]", ""));
				if (!(tempPrice >= lowPrice && tempPrice <= highestPrice)) {
					reportStatusFail(methodName + "Error in applied Price Filter.", true);
				}
			}
		} catch (Exception e) {
			reportStatusFail("Error is validating applied filter on Device/Accessories Listing Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 21.02.2022 by @author Jigyasa Dwivedi
	 * 
	 * To Validate applied capacity filter on Device Listing Page.
	 * 
	 * @throws Exception
	 */
	public void verify_AppliedCapacityFilterOnDeviceListingPage(String filter) throws Exception {
		// TODO Auto-generated method stub
		SFDC_AllPages sfdc = new SFDC_AllPages();
		try {
			methodName = "Device Listing page@: ";
			sf.seleU.hardwait(2000);
			
			for (int i=0;i<sf.shopWADevobj.deviceList.size();i++) {
				sf.seleU.wait(2000);
				sf.seleU.clickElementByJSE(sf.shopWADevobj.deviceList.get(i));
				sf.seleU.wait(2000);
				reportStatusPass(methodName + sf.seleU.isElementDisplayed(sf.shopWADevobj.backToBrowDevBtn), true,true);				
				verify_capacityListOnDeviceDetilsPage(filter);
				
				sf.seleU.clickElementByJSE(sf.shopWADevobj.backToBrowDevBtn);
				sf.seleU.hardwait(5000);
				sfdc.bAccessories.select_Filter_OnListingPage(filter);
			}
		} catch (Exception e) {
			reportStatusFail("Error is validating applied capacity filter on Device Listing Page", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 21.02.2022 by @author Jigyasa Dwivedi
	 * 
	 * To Validate applied capacity filter on Device details Page.
	 * 
	 * @throws Exception
	 */
	public void verify_capacityListOnDeviceDetilsPage(String filter) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Device Listing page@: ";
			boolean result = false;
			int filterCapacity = Integer.parseInt(filter.replaceAll("[^\\d]", "").trim());
			for (WebElement ele : sf.shopWADevobj.deviceCapacityList) {
				int deviceCapacity = Integer.parseInt(sf.seleU.getTextFromWebElement(ele).replaceAll("[^\\d]", "").trim());
				sf.seleU.ScrolltoElement(ele);
				if (filter.contains("more") && deviceCapacity >= filterCapacity) {
					result = true;									
				}
				else if (deviceCapacity == filterCapacity) {				
					result = true;	
				}
			}
			if(result==true) {
				reportStatusPass(methodName + "Capacity filter has applied: " + filter+ " for device: "+sf.seleU.getTextFromWebElement(sf.shopWADevobj.deviceModel), true, true);	
			}
		} catch (Exception e) {
			reportStatusFail("Error is validating applied filter on Device/Accessories Listing Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 23.02.2022 by @author Jigyasa Dwivedi
	 * Method to select Bring My Device on device Listing page.
	 * @throws Exception 
	 * 
	 */
	public void selectBringMyDeviceButton_DeviceListingPage() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "WACC Device Listing Page@: ";
			sf.seleU.hardwait(2000);
			if (sf.seleU.isElementDisplayed(sf.shopWADevobj.bringMyDeviceBtn)) {
				sf.seleU.clickElementByJSE(sf.shopWADevobj.bringMyDeviceBtn);
				reportStatusPass(methodName + "Bring my device button has clicked: ", true, true);
			} else
				reportStatusFail(methodName + "Bring my device button is not present.", true);

		} catch (Throwable e) {
			reportStatusFail("Error on selecting Bring my device button.", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 17.3.2022 by @author Jigyasa Dwivedi
	 * Method to get Device Capacity
	 * @throws Exception 
	 * 
	 */
	public String getDeviceSelectedCapacity() throws Exception {
		// TODO Auto-generated method stub
		try {
			return sf.seleU.getTextFromWebElement(sf.shopWADevobj.sizeSelected);
		} catch (Throwable e) {
			reportStatusFail("Error on retrive device selected capacity.", e);
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Method developed on @Date: 17.3.2022 by @author Jigyasa Dwivedi
	 * Method to get Device Capacity
	 * @throws Exception 
	 * 
	 */
	public String getDeviceSelectedColor() throws Exception {
		// TODO Auto-generated method stub
		try {
			return sf.seleU.getTextFromWebElement(sf.shopWADevobj.colourSelected);
		} catch (Throwable e) {
			reportStatusFail("Error on retrive device selected color.", e);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method developed on @Date: 29.04.2022 by @author viswas reddy
	 * Method to check 36 months finance option in device selection 
	 * @throws Exception 
	 * 
	 */
	public void check_36MonthsFinancingInDeviceSection() throws Exception {
		try {
			methodName = "Device Details Page@: ";
			sf.seleU.hardwait(2000);
			String str = "36 months (financing)";
			if(sf.seleU.getTextFromWebElement(sf.shopWADevobj.noOfMonthsFinancingText).contains(str)) {
				reportStatusPass(methodName+"choose payment option is 36 months (financing)", true, true);
				if(sf.seleU.isElementDisplayed(sf.shopWADevobj.thirtySixmonthsFinanceBox) 
						&& sf.seleU.getTextFromWebElement(sf.shopWADevobj.totalMonthlydetails).contains("36 mos")) {
					reportStatusPass(methodName+"36 months box is present and under total monthly fees 36 mos is present", true, true);
				}
			}else {
				reportStatusFail(methodName+"choose payment option is not 36 months (financing) or box not present", true);
			}
			
		}catch (Exception e) {
			reportStatusFail(methodName+"error in checking 36 months financing device option", true);
		}
	}
	

}