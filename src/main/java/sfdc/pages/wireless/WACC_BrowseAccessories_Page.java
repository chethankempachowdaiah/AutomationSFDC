package sfdc.pages.wireless;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.framework.base.MyListener;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;


public class WACC_BrowseAccessories_Page extends MyListener {

	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public static int quntityCount;
	public static SFDC_AllPages sfdc;

	public WACC_BrowseAccessories_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * Method developed on @Date: 15.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Select the Shop for accessories
	 * 
	 */

	public void clickOnViewDetailsBtn(String option, String product) throws Exception {
		methodName = "Accessories Details page@: "; boolean found = false;
		try {
			sf.seleU.waitForLoading();
			sf.seleU.clickElementByJSE(driver.findElement(By.xpath("//span[(text()='" + option + "')]")));
			sf.seleU.waitForLoading();
			reportStatusPass(" All accessories has clicked. ",true, true);
			for (int i = 0; i < sf.bAccessoriesObj.accessoriesList.size(); i++) {
				sf.seleU.hardwait(1000);
				String actaulAccName= sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.accessoriesList.get(i)).trim();
				if (actaulAccName.equalsIgnoreCase(product.trim())) {
					//sf.seleU.hardwait(2000);
					sf.seleU.ScrolltoElement(sf.bAccessoriesObj.viewDetails.get(i));
					sf.seleU.clickElementByJSE(sf.bAccessoriesObj.viewDetails.get(i));
					sf.seleU.waitForLoading();
					reportStatusPass(methodName + " Accessories Details page is displayed: ",
							sf.seleU.isElementDisplayed(sf.accessoryDetailObj.backToAccessoriesBtn), true);
					found = true;
					break;
				}
			}
			if(found) {
				reportStatusPass(methodName + " accessory was found", false, true);
			} else {
				reportStatusFail("Not found the accessory details", true);
			}
		} catch (Throwable e) {
			reportStatusFail("Error on clicking View details button on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 16.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate accessories_tile_details on the Shop for accessories
	 * 
	 */
	public void accessories_tile_details() throws Exception {
		methodName = "Shop for Accessories page@: ";
		try {
			sf.seleU.waitForLoading();
			sf.seleU.hardwait(2000);
			for (int i = 0; i < sf.bAccessoriesObj.accessoriesList.size(); i++) {
				if (sf.seleU.isElementDisplayed(sf.bAccessoriesObj.brandList.get(i))&& sf.seleU.isElementDisplayed(sf.bAccessoriesObj.accessoriesPrice.get(i))
						&& sf.seleU.isElementDisplayed(sf.bAccessoriesObj.viewDetails.get(i))) {
					sf.seleU.hardwait(2000);
					reportStatusPass(methodName + sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.accessoriesList.get(i))+ "on View Details button is displayed",
							true, true);
					reportStatusPass(methodName + sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.accessoriesList.get(i))+ "on brand name is displayed: "
									+ sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.brandList.get(i)),true, true);
					reportStatusPass(methodName + sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.accessoriesList.get(i))+ "on price is displayed: "
									+ sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.accessoriesPrice.get(i)),true, true);
				} else {
					reportStatusFail("Error in Accessory tile Info. ", true);
				}
			}

		} catch (Throwable e) {
			reportStatusFail("error in Accessories tiles info on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 16.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate brand filter on the Shop for accessories
	 * 
	 * @throws Exception
	 * 
	 */
	public void validate_Brand_Filter(String[] brandList) throws Exception {
		methodName = "Shop for Accessories page@: ";
		sf.seleU.waitTillLoading();
		try {
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.bAccessoriesObj.filter);

			sf.seleU.hardwait(2000);
			for (String str : brandList) {
				boolean result = false;
				for (int i = 0; i < sf.bAccessoriesObj.brandFilter.size(); i++) {
					sf.seleU.hardwait(2000);
					if (str.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.brandFilter.get(i)))) {
						reportStatusPass(methodName + str + " Brand name in Filter",
								sf.bAccessoriesObj.brandFilter.get(i).isDisplayed(), true);
						result = sf.bAccessoriesObj.brandFilter.get(i).isDisplayed();
						break;
					}

				}
				Assert.assertTrue(result);
			}
		} catch (Throwable e) {
			reportStatusFail("Error in getting Brand list in Filter on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 08.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate brand filter on the Shop for accessories
	 * 
	 * @throws Exception
	 * 
	 */
	public void validate_DeviceCompatibility_Filter(String[] brandList) throws Exception {
		methodName = "Shop for Accessories page@: ";
		sf.seleU.waitTillLoading();

		try {
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.bAccessoriesObj.filter);
			// String[] brandList = brand.split(",");
			sf.seleU.hardwait(2000);
			for (String str : brandList) {
				boolean result = false;
				for (int i = 0; i < sf.bAccessoriesObj.deviceCompatibilityList.size(); i++) {
					sf.seleU.hardwait(2000);
					if (str.equalsIgnoreCase(
							sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.deviceCompatibilityList.get(i)))) {
						reportStatusPass(methodName + str + " Device name in Filter",
								sf.bAccessoriesObj.deviceCompatibilityList.get(i).isDisplayed(), true);
						result = sf.bAccessoriesObj.deviceCompatibilityList.get(i).isDisplayed();
						break;
					}

				}
				Assert.assertTrue(result);
			}
		} catch (Throwable e) {
			reportStatusFail("Error in getting device list in Filter on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 16.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate price filter on the Shop for accessories
	 * 
	 * @throws Exception
	 * 
	 */
	public void validate_Price_Filter(String[] priceList) throws Exception {
		methodName = "Shop for Accessories page@: ";

		try {
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.bAccessoriesObj.filter);
			// String[] priceList = price.split(",");
			for (String str : priceList) {
				boolean result = false;
				for (int i = 0; i < sf.bAccessoriesObj.priceFilter.size(); i++) {
					sf.seleU.hardwait(2000);
					if (str.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.priceFilter.get(i)))) {
						reportStatusPass(methodName + str + " Price value in Filter. ",sf.bAccessoriesObj.priceFilter.get(i).isDisplayed(), true);
						result = sf.bAccessoriesObj.priceFilter.get(i).isDisplayed();
						break;
					}
				}
				Assert.assertTrue(result);
			}
		} catch (Throwable e) {
			reportStatusFail("Error is getting price list in filter on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 16.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate price filter on the Shop for accessories
	 * @throws Exception
	 * 
	 */
	public void validate_PromosDiscount_Filter() throws Exception {
		methodName = "Shop for Accessories page@: ";
		try {
			if (sf.seleU.isElementDisplayed(sf.bAccessoriesObj.offers)) {
				// sf.seleU.clickElementByJSE(sf.bAccessoriesObj.offersExpandIcon);
				sf.seleU.hardwait(3000);
				reportStatusPass(methodName + " Offers and Promo Discount in Filter. ",sf.seleU.isElementPresent(sf.bAccessoriesObj.PromosDiscounts), true);
				Assert.assertTrue(sf.seleU.isElementPresent(sf.bAccessoriesObj.PromosDiscounts));
			} else {
				reportStatusFail(methodName + " Offers and Promo Discount in Filter not present. ", true);
			}
		} catch (Throwable e) {
			reportStatusFail("Error is getting Promos & Discounts in filter on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}
//

	/**
	 * Method developed on @Date: 22.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To apply filter on the Shop for accessories
	 * 
	 * @throws Exception
	 */
	public void select_Filter_OnListingPage(String text) throws Exception {
		methodName = "Shop for Device/Accessories Listing page@: ";
		try {
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.bAccessoriesObj.filter);
			select_FilterOption_FilterBar(text);
			sf.seleU.clickElementByJSE(sf.bAccessoriesObj.viewBtn);
			reportStatusPass(methodName + text + " filter selected. ", true, true);

		} catch (Exception e) {
			reportStatusFail("Error is getting while apply filter on Device/Accessories Listing Page", e);
			e.printStackTrace();
		}
	}

	public void select_FilterOption_FilterBar(String text) throws Exception {
		methodName = "Shop for Accessories page@: ";
		try {
			sf.seleU.hardwait(2000);
			for (WebElement element : sf.bAccessoriesObj.filterList) {
				if (sf.seleU.getTextFromWebElement(element).equalsIgnoreCase(text)) {
					sf.seleU.clickElementByJSE(element);
					reportStatusPass(methodName + text + " filter selected. ", true, true);
					break;
				}
			}
		} catch (Exception e) {
			reportStatusFail("Error is getting while select filter on Shop for accessories Page", e);
			e.printStackTrace();
		}

	}

	/**
	 * Method developed on @Date: 22.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate applied filter on the Shop for accessories
	 * 
	 * @throws Exception
	 */
	public void validate_FilterBubble_OnListingPage(String[] filterList) throws Exception {
		methodName = "Shop for Device/ ACcessories page@: ";
		try {
			sf.seleU.ScrolltoElement(sf.bAccessoriesObj.filterBubble.get(0));
			for (int i = 0; i < sf.bAccessoriesObj.filterBubble.size(); i++) {
				if (sf.seleU.isElementDisplayed(sf.bAccessoriesObj.filterBubble.get(i)) && sf.seleU.isElementDisplayed(sf.bAccessoriesObj.filterBubbleCloseIcon.get(i))
						&& sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.filterBubble.get(i)).equalsIgnoreCase(filterList[i])) {
					sf.seleU.hardwait(2000);
					reportStatusPass(methodName + filterList[i] + " Filter Bubble present. ",true, true);
				} else {
					reportStatusFail(methodName + filterList[i] + " Filter Bubble not present. ", true);
				}
			}
		} catch (Exception e) {
			reportStatusFail("Error is getting while verify applied filter on Listing Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 22.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate Accessories count on the Shop for accessories
	 * 
	 * @throws Exception
	 */
	public int getCountOfAccessories() throws Exception {
		sf.seleU.hardwait(2000);
		sf.seleU.waitForLoading();
		String CountAccessories = sf.bAccessoriesObj.accessoriesCount.getText().replaceAll("[^0-9]", "");
		int a = Integer.parseInt(CountAccessories);
		int noOfAccessories = sf.bAccessoriesObj.accessoriesList.size() - 1;
		System.out.println("NumberoOfAccessories: "+noOfAccessories);
		return a;
	}

	/**
	 * Method developed on @Date: 22.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate no Filter on the Shop for accessories
	 * 
	 * @throws Exception
	 */
	public void validate_NoFilterSelected(String[] filterList) throws Exception {
		methodName = "Shop for Accessories page@: ";
		try {
			sf.seleU.clickElementByJSE(sf.bAccessoriesObj.filter);
			sf.seleU.hardwait(2000);
			for (String filter : filterList) {
				for (WebElement element : sf.bAccessoriesObj.filterList) {
					if (sf.seleU.getTextFromWebElement(element).equalsIgnoreCase(filter) && !element.isSelected()) {
						reportStatusPass(methodName + " No Filter Selected. ", true, true);
					}
				}
			}
			sf.seleU.clickElementByJSE(sf.bAccessoriesObj.filterCloseIcon);
		} catch (Exception e) {
			reportStatusFail("Error is getting while check filter on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 22.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate Proceed to Shopping Cart button on the Shop for accessories
	 * 
	 * @throws Exception
	 */
	public void clickProceedToShoppingCartBtn() throws IOException {
		methodName = "WACC Shopping Cart Validation@: ";
		try {
			sf.seleU.waitForLoading();
			// Verify proceed to shopping Cart is displayed and clicked
			if (sf.seleU.isElementDisplayed(sf.bAccessoriesObj.proceedToShoppingCartBtn)) {
				// click on proceed to shopping Cart button
				sf.seleU.clickElementByJSE(sf.bAccessoriesObj.proceedToShoppingCartBtn);
				sf.seleU.waitForLoading();
				//Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.shopCartObj.shoppingCartHeader));				
				reportStatusPass(methodName + " Successfully clicked on proceed to shopping Cart button", true, true);
			}
			else {
				reportStatusFail(methodName + " Failure on Clicking proceed to shopping Cart button", true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Error on Clicking Pproceed to shopping Cart button", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 22.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate to click on Reset Filter on the Shop for accessories
	 * 
	 * @throws Exception
	 */
	public void select_ResetFilterOnFilterBubble() throws Exception {
		methodName = "Shop for Accessories page@: ";
		sf.seleU.scrollToTop();
		try {
			if (sf.seleU.isElementDisplayed(sf.bAccessoriesObj.resetFilter)) {
				sf.seleU.clickElementByJSE(sf.bAccessoriesObj.resetFilter);
				sf.seleU.hardwait(2000);
				reportStatusPass(methodName + " Successfully clicked on Reset Filter button. ", true, true);
			} else {
				reportStatusFail(methodName + " Reset Filter button is not present. ", true);
			}
		} catch (Exception e) {
			reportStatusFail("Error is getting while click on Reset filter on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 22.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate to no Filter Bubble on the Shop for accessories
	 * 
	 * @throws Exception
	 */
	public void validate_NoFilterBubble() throws Exception {
		methodName = "Shop for Accessories page@: ";

		try {
			sf.seleU.hardwait(2000);
			sf.seleU.waitForLoading();			
			if (sf.bAccessoriesObj.filterBubble.size() == 0) {
				reportStatusPass(methodName + " No Filter bubble present. ", true, true);
			} else {
				reportStatusFail(methodName + " Filter bubble present. ", true);
			}
		} catch (Exception e) {
			reportStatusFail("Error in Filter bubble on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 22.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to click on close button on the Filter bubble.
	 * 
	 * @throws Exception
	 */
	public void select_closeBtnOnFilterBubble() throws Exception {
		methodName = "Shop for Accessories page@: ";
		try {
			sf.seleU.hardwait(2000);
			sf.seleU.scrollToTop();
			if (sf.bAccessoriesObj.filterBubble.size() > 0) {				
				ArrayList<WebElement> lst = new ArrayList<WebElement>();
				for(WebElement ele:sf.bAccessoriesObj.filterBubbleCloseIcon) {
					lst.add(ele);
				}				
				for (int i = 0; i < lst.size(); i++) {
					sf.seleU.clickElementByJSE(lst.get(i));
					reportStatusPass(methodName + "Filter bubble has closed", true, true);
				}
			} else {
				reportStatusFail(methodName + " FilterBubble is not present. ", true);
			}
		} catch (Exception e) {
			reportStatusFail("Error in click on close button in Filter bubble on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 22.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To validate Device Compatibility section on filter bar on the Shop for accessories
	 * 
	 * @throws Exception
	 */
	public void validate_FilterBar_ShopForAccessoriesPage(String[] optionList, String[] brandList) throws Exception {
		try {
			methodName = "Shop for Accessories page@: ";
			sf.seleU.waitTillLoading();
			for (String option : optionList) {
				sf.seleU.hardwait(2000);
				WebElement ele = driver.findElement(By.xpath("//span[contains(text(), '" + option + "')]"));
				if (sf.seleU.isElementDisplayed(ele)) {
					// click on option
					sf.seleU.clickElementByJSE(ele);
					sf.seleU.waitForLoading();
					reportStatusPass(methodName + "user has selected option: " + option, true, true);
				} else {
					reportStatusFail(methodName + "error in select option: " + option, true);
				}
				// click on Filter icon
				sf.seleU.ScrolltoElement(sf.bAccessoriesObj.filter);
				sf.seleU.waitElementToBeVisible(sf.bAccessoriesObj.filter);
				sf.seleU.clickElementByJSE(sf.bAccessoriesObj.filter);
				sf.seleU.hardwait(2000);
				// To Check Device Compatibility option is present and click on that
				if (sf.seleU.isElementDisplayed(sf.bAccessoriesObj.deviceCompatibility)
						&& sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.deviceCompatibility)
								.equalsIgnoreCase("Device Compatibility")) {
//					sf.seleU.clickElementByJSE(sf.bAccessoriesObj.dCExpandIcon);
					sf.seleU.hardwait(2000);
					reportStatusPass(methodName + " Device Compatibility is diaplayed in filter sidebar. ", true, true);
				} else {
					reportStatusFail(" Error in Device Compatibility is diaplayed in filter sidebar", true);
				}

				// validate Brand and related devices list
				if(InputData_WA.env.contains("ITQATEST") || InputData_WA.env.contains("PREPROD") ) {
					validate_DeviceCompatibility_Device_Model_Value_ByExpanding(brandList);
				}else {
				validate_BrandAndDevice(brandList);
				}
				sf.seleU.clickElementByJSE(sf.bAccessoriesObj.filterCloseIcon);
			}

		} catch (Exception e) {
			reportStatusFail("Error is checking brand and device name in filter on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 22.11.2021 by @author Jigyasa Dwivedi
	 * 
	 * To validate Brand and related devices list
	 * 
	 * @throws Exception
	 */

	public void validate_BrandAndDevice(String[] brandList) throws Exception {
		methodName = "Shop for Accessories page@: ";
		// validate Brand and related devices list
		try {
			for (String brand : brandList) {
				sf.seleU.hardwait(2000);
				WebElement ele=driver.findElement(By.xpath("//div[text()='" + brand + "']/preceding-sibling::div"));
				if(!brand.equalsIgnoreCase(InputData_WA.WACC_DeviceBrand)) {
					sf.seleU.clickElementByJSE(ele);
					}
				List<WebElement> deviceList = driver.findElements(By.xpath("//div[text()='" + brand + "']/../following-sibling::div//label"));
				if (deviceList.size() > 0) {
					reportStatusPass(methodName + brand + ": Device list is present in Filter Bar. ", true, true);
					for (WebElement device : deviceList) {
						System.out.println("The Brand name selected is" + brand + ": " + sf.seleU.getTextFromWebElement(device));
					}
				} else {
					reportStatusFail(methodName + brand + ": Device list is not present in Filter Bar. ", true);
				}

			}
		} catch (Exception e) {
			reportStatusFail("Error is checking brand and device name in filter on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 01.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate specialOffer text, sort By option present on Accessories details Page.
	 * 
	 * @throws Exception
	 */
	public void validate_specialOfferText_SortByOption_onAccessoryDetailsPage() throws Exception {

		methodName = "Shop for Accessories page@: ";
		try {
			sf.seleU.waitForLoading();
			int preCount = getCountOfAccessories();
			if (sf.bAccessoriesObj.specialOfferTextList.size() >= preCount) {
				for (int i = 0; i < sf.bAccessoriesObj.specialOfferTextList.size(); i++) {
					if (sf.seleU.isElementDisplayed(sf.bAccessoriesObj.specialOfferTextList.get(i))
							&& sf.seleU.isElementDisplayed(sf.bAccessoriesObj.accessoriesList.get(i))) {
						sf.seleU.hardwait(2000);
						reportStatusPass(methodName + "Accessory Name: "+ sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.accessoriesList.get(i))
								+ "present with Special Offer text. ", true, true);
					}
				}
			}
			if (sf.seleU.isElementDisplayed(sf.bAccessoriesObj.sortBy)
					&& sf.seleU.isElementDisplayed(sf.bAccessoriesObj.filter)) {
				reportStatusPass(methodName + "SortBy  & Filter Option is present. ", true, true);
			}
		} catch (Exception e) {
			reportStatusFail("Error in special offer on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 01.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate details on Shop for Accessory Page.
	 * 
	 * @throws Exception
	 */
	public void validate_DetailsOnShopAccessoryPage(String[] optionList) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Shop for Accessories page@: ";
			sf.seleU.hardwait(2000);
			sf.seleU.waitForLoading();
			for (String option : optionList) {
				if (sf.seleU.isElementDisplayed(driver.findElement(By.xpath("//span[contains(text(), '" + option + "')]")))) {
					sf.seleU.clickElementByJSE(driver.findElement(By.xpath("//span[contains(text(), '" + option + "')]")));
					sf.seleU.waitForLoading();
					reportStatusPass(methodName + "user has selected option: " + option, true, true);
				} else {
					reportStatusFail(methodName + "error in select option: " + option, true);
				}
				validate_specialOfferText_SortByOption_onAccessoryDetailsPage();
				accessories_tile_details();
			}

		} catch (Exception e) {
			reportStatusFail("Error is checking brand and device name in filter on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 06.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to click on Filter button, select Filter and click on close button on FilterBar on Shop for Accessory Page.
	 * 
	 * @throws Exception
	 */
	public void select_FilterOptionAndClickCloseIcon(String[] filterList) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Shop for Accessories page@: ";
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.bAccessoriesObj.filter);
			// Select filter check boxes
			for (String filter : filterList) {
				select_FilterOption_FilterBar(filter);
			}
			sf.seleU.clickElementByJSE(sf.bAccessoriesObj.filterCloseIcon);
			reportStatusPass(methodName + " Close icon has clicked. ", true, true);
		} catch (Exception e) {
			reportStatusFail("Error is clicking close icon in filter on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 06.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate applied filter on Shop for Accessory Page.
	 * 
	 * @throws Exception
	 */
	public void verify_AppliedFilterOnShopAccessoryPage(String filterType, String filter) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Shop for Accessories page@: ";
			sf.seleU.hardwait(2000);
			sf.seleU.scrollToTop();
			if (filterType.equalsIgnoreCase("brand")) {
				for (int i = 0; i < sf.bAccessoriesObj.accessoriesList.size(); i++) {
					if (!sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.brandList.get(i)).equalsIgnoreCase(filter)) {
						reportStatusFail(methodName + "Error in applied Brand Filter.", true);
					}
				}
			} else if (filterType.equalsIgnoreCase("offer")) {
				for (int i = 0; i < sf.bAccessoriesObj.accessoriesList.size(); i++) {
					if (!sf.seleU.isElementDisplayed(sf.bAccessoriesObj.specialOfferTextList.get(i))) {
						reportStatusFail(methodName + "Error in applied Offer Filter.", true);
					}
				}
			} else if (filterType.equalsIgnoreCase("price")) {
				String temp = filter.replace('$', ' ');
				String[] str = temp.split("-");
				double lowPrice = Double.parseDouble(str[0].trim());
				double highestPrice = Double.parseDouble(str[1].trim());
				for (int i = 0; i < sf.bAccessoriesObj.accessoriesList.size(); i++) {
					double tempPrice = get_PriceOnShopForAccessory(i);
					if (!(tempPrice >= lowPrice && tempPrice <= highestPrice)) {
						reportStatusFail(methodName + "Error in applied Price Filter.", true);
					}
				}
			}
		} catch (Exception e) {
			reportStatusFail("Error is validating applied filter on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 06.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Get Accessory Price in double on Shop For Accessory Page.
	 * 
	 * @throws Exception
	 */
	public double get_PriceOnShopForAccessory(int index) throws Exception {
		methodName = "Shop For Accessories page@: ";
		try {
			sf.seleU.hardwait(2000);
			String price = sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.priceListWIthoutDecimal.get(index)) + "."
					+ sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.priceListWithDesimal.get(index));
			reportStatusPass(methodName + "Accessory Price: " + price, true, true);
			return Double.parseDouble(price);

		} catch (Exception e) {
			reportStatusFail("Error is get price on Shop For Accessories page. ", e);
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Method developed on @Date: 08.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Get Footer Price.
	 * 
	 * @throws Exception
	 */
	public double get_FooterPrice() throws Exception {
		// TODO Auto-generated method stub
		methodName = "Footer Price@: ";
		try {
			sf.seleU.hardwait(2000);
			sf.seleU.waitForLoading();
			if (sf.seleU.isElementDisplayed(sf.bAccessoriesObj.footerTotalTCVPrice)) {
				String price = sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.footerTotalTCVPrice);
				reportStatusPass(methodName + price, true, true);
				return Double.parseDouble(price.replaceAll("[^\\d.]", "").trim());
			} else {
				reportStatusFail("TotalTCV is not displayed in Footer price. ", true);
			}
		} catch (Exception e) {
			reportStatusFail("Error is get Footer price. ", e);
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * Method developed on @Date: 08.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * To Validate Footer Price.
	 * 
	 * @throws Exception
	 */
	public void validate_FooterPrice(double totalTCV, String item, String planSize) throws Exception {
		// TODO Auto-generated method stub
		methodName = "Footer Price@: ";
		double price = 0, temp = 0;
		try {
			String str = "";
			sf.seleU.hardwait(2000);
			sf.seleU.waitForLoading();
			if (item.equalsIgnoreCase("wireless Plan")) {
				String elePrice = sf.seleU.getTextFromWebElement(driver.findElement(By.xpath("//div[text()='"+planSize+"']/../following-sibling::div//*[@class='price']")));
				String eleDecimalPrice = sf.seleU.getTextFromWebElement(driver.findElement(By.xpath("(//div[text()='"+planSize+"']/../following-sibling::div//*[contains(@class,'price-secondary ')])[1]")));
				str = elePrice + "." + eleDecimalPrice;
			} 
			else if (item.equalsIgnoreCase("addOn")) {
				if (sf.seleU.isElementSelected(sf.addOnSel.USLD_AddOn)) {
					str = sf.seleU.getTextFromWebElement(sf.addOnSel.USLDpriceValue).replaceAll("[^\\d.]", "").trim();
				} else if (sf.seleU.isElementSelected(sf.addOnSel.InternationalLDSaver_AddOn)) {
					str = sf.seleU.getTextFromWebElement(sf.addOnSel.intLDSaverpriceValue).replaceAll("[^\\d.]", "")
							.trim();
				}
			}
			else if (item.equalsIgnoreCase("accessories")) {
				str = sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.priceOnADetails) + "."
						+ sf.seleU.getTextFromWebElement(sf.accessoryDetailObj.desimalPriceOnADetails);
			} 
			price = Double.parseDouble(str);
			reportStatusPass(methodName + item + " Price: " + price, true, true);
			// price=sfdc.accessoryDetails.get_PriceOnAccessoryDetailsPage();
			temp = totalTCV + price;
			if (temp == get_FooterPrice()) {
				reportStatusPass(methodName + temp, true, true);
			} else {
				reportStatusFail("Error in validating footer price.", true);
			}
		} catch (Exception e) {
			reportStatusFail("Error is get Footer price. ", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 04.02.2022 by @author viswas reddy
	 * 
	 * To select filters on accessories page
	 * 
	 * @throws Exception
	 */
	public void select_FilterOption(String[] filterList, String brand) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Shop for Accessories page@: ";
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.bAccessoriesObj.filter);
			if (brand != null) {
				for (int i = 0; i < sf.bAccessoriesObj.brandnames.size(); i++) {
					if (sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.brandnames.get(i)).equalsIgnoreCase(brand)) {
						String brandWebelement = "(//div[@class='nds-col label-style']/preceding-sibling::div)[" + i
								+ "]";
						sf.seleU.clickElementByJSE(driver.findElement(By.xpath(brandWebelement)));
					}
				}
			}
			// Select filter check boxes
			for (String filter : filterList) {
				select_FilterOption_FilterBar(filter);
			}
		} catch (Exception e) {
			reportStatusFail("Error in selecting filter on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 04.02.2022 by @author viswas reddy
	 * 
	 * To reset filters on accessories page
	 * 
	 * @throws Exception
	 */
	public void validate_resetfiltersOnSidebar(String[] filterList) throws Exception{
		sf.seleU.waitForLoading();
		try {
			methodName = "Shop for Accessories page@: ";
			sf.seleU.clickElementByJSE(sf.bAccessoriesObj.sidebarResetFilters);
			sf.seleU.hardwait(2000);
			for (String filter : filterList) {
				for (WebElement element : sf.bAccessoriesObj.filterList) {
					if (sf.seleU.getTextFromWebElement(element).equalsIgnoreCase(filter) && !element.isSelected()) {
						reportStatusPass(methodName + " reset filter clicked then No Filter Selected. ", true, true);
					}
				}
			}
			sf.seleU.clickElementByJSE(sf.bAccessoriesObj.filterCloseIcon);
		} catch (Exception e) {
			reportStatusFail("Error in resetting filter on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}	
	/**
	 * Method developed on @Date: 16.02.2022 by @author viswas reddy
	 * 
	 * validation of original price and discounted price according to 
	 * special offer tile in accessories page
	 * 
	 * @throws Exception
	 */
	public void validate_originalandDiscPricePresentAccessoriesPage() throws Exception {
		methodName = "Shop for Accessories page@: ";
		try {
			for (int i = 0; i <= sf.bAccessoriesObj.specialoffer.size()-1; i++) {
				if (sf.seleU.isElementDisplayed(sf.bAccessoriesObj.AcceOrigPrice.get(i)) && 
						sf.seleU.isElementDisplayed(sf.bAccessoriesObj.accessoriesPrice.get(i))){
					reportStatusPass(methodName + "Sprecial Offer Price and original price present and grayed out ", true, true);
				}else {
					reportStatusFail(methodName + "Sprecial Offer Price or original price is not present ", true);
				}
			}
		} catch(Exception e){
			reportStatusFail(methodName + "error in checking special offer price or original price ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 17.02.2022 by @author viswas reddy
	 * 
	 * validation of special offer tile and text present in accessories page
	 * 
	 * @throws Exception
	 */
	public void validate_specialOfferColorandText() throws Exception {
		methodName = "Shop for Accessories page@: ";
		try {
			for (int i = 0; i <= sf.bAccessoriesObj.specialoffer.size()-1; i++) {
				String backgrpound = sf.bAccessoriesObj.specialoffer.get(i).getCssValue("background");
				System.out.println(backgrpound);
				if(sf.bAccessoriesObj.specialoffer.get(i).getCssValue("background")
						.contains("rgb(255, 191, 63)")){
					reportStatusPass(methodName + "Special offer background color is yellow ", true, true);
				}else {
					reportStatusFail(methodName + "Special offer background color is not yellow ", true);
				}
				if(sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.specialOfferTextList.get(i)).equalsIgnoreCase("Special Offer")) {
					reportStatusPass(methodName + "Special offer text is present ", true, true);
				}else {
					reportStatusFail(methodName + "Special Offer text is wrong ", true);
				}
			}
		}catch(Exception e) {
			reportStatusFail(methodName + "error in validating special offer tile ", true);
		}
	}
	
	/**
	 * Method developed on @Date: 18.02.2022 by @author viswas reddy
	 * 
	 * validation of Accessories page tiles 
	 * 
	 * @throws Exception
	 */
	public void validate_AcceTilesAreDisplayed() throws Exception {
		methodName = "Shop for Accessories page@: ";
		try {
			if(sf.bAccessoriesObj.allAccessoriesTile.isDisplayed()) {
				reportStatusPass(methodName + "All accessories tile is present ", true, true);
			}
			if(sf.bAccessoriesObj.powerAndCablesTile.isDisplayed()) {
				reportStatusPass(methodName + "power and cables tile is present ", true, true);
			}
			if(sf.bAccessoriesObj.casesAndScreenProtectors.isDisplayed()) {
				reportStatusPass(methodName + "Cases and Screen Protectors tile is present ", true, true);
			}
		}catch(Exception e) {
			reportStatusFail(methodName + "error in validating Accessories tiles ", true);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 22.02.2022 by @author viswas reddy
	 * 
	 * validation of Accessories page sort by and filters displayed and working
	 * 
	 * @throws Exception
	 */
	public void validate_AcceSortbyAndFilters() throws Exception {
		methodName = "Shop for Accessories page@: ";
		try {
			if(sf.bAccessoriesObj.filter.isDisplayed() || sf.bAccessoriesObj.sortByFeatured.isDisplayed()) {
				sf.seleU.clickElementByJSE(sf.bAccessoriesObj.filter);
				sf.seleU.wait(2000);
				sf.seleU.clickElementByJSE(sf.bAccessoriesObj.filterCloseIcon);
				reportStatusPass(methodName + "filters and sort by featured is displayed and working ", true, true);
			}else {
				reportStatusFail(methodName + "filters is not displayed ", true);
			}
		}catch (Exception e) {
			reportStatusFail(methodName + "error in validating sort by and filters ", true);
			e.printStackTrace();
		}
	}	
	/**
	 * Method developed on @Date: 23.02.2022 by @author viswas reddy
	 * 
	 * validation of Accessories page sort by options
	 * 
	 * @throws Exception
	 */
	public void select_sortByOptions(String sortOption) throws Exception{
		methodName = "Shop for Accessories page@: ";
		try {
			sf.seleU.wait(1000);
			sf.seleU.waitTillLoading();
			sf.seleU.scrollToTop();
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.bAccessoriesObj.sortBy);
			if(sortOption.equalsIgnoreCase("High to Low")) {
				sf.seleU.clickOnElement(sf.bAccessoriesObj.sortHightoLow);
				sf.seleU.wait(1000);
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.bAccessoriesObj.sortByhigh));
				reportStatusPass(methodName + "Sort by high to low selected ", true, true);
			}else if(sortOption.equalsIgnoreCase("Low to High")) {
				sf.seleU.clickOnElement(sf.bAccessoriesObj.sortLowtoHigh);
				sf.seleU.wait(1000);
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.bAccessoriesObj.sortByLow));
				reportStatusPass(methodName + "Sort by low to high selected ", true, true);
			}
			verify_sorting(sortOption);
		}catch (Exception e) {
			reportStatusFail(methodName + "error in changing sorting options ", true);
			e.printStackTrace();
		}
	}	
	/**
	 * Method developed on @Date: 24.02.2022 by @author viswas reddy
	 * 
	 * validation of Accessories page sort by options and validating accessories list price accordingly
	 * 
	 * @throws Exception
	 */
	public void verify_sorting(String sortName) throws Exception {
		methodName = "Shop for Accessories page@: ";
		try {
			for(int i=0;i<=sf.bAccessoriesObj.accessoriesList.size()-2;i++) {
				 String accename = sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.accessoriesList.get(i));
				 sf.seleU.ScrolltoElement(sf.bAccessoriesObj.accessoriesList.get(i));
				String price = sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.priceListWIthoutDecimal.get(i));
				String pricedec = sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.priceListWithDesimal.get(i));
				if(pricedec==null) {
					pricedec = "00";
				}
				String accePriceTotal = price +"."+ pricedec;
				double accePrice = Double.parseDouble(accePriceTotal);
				String priceNext = sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.priceListWIthoutDecimal.get(i+1));
				String priceNextdec = sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.priceListWithDesimal.get(i+1));
				if(priceNextdec==null) {
					priceNextdec = "00";
				}
				String accePriceNextTotal = priceNext +"."+ priceNextdec;
				double accePriceNext = Double.parseDouble(accePriceNextTotal);
				if(sortName.equalsIgnoreCase("High to Low")) {
					if(accePrice>=accePriceNext) {
						reportStatusPass(methodName + accename +" is sorted accordingly", true, true);
					}else {
						reportStatusFail(methodName + accename +"is not sorted accordingly", true);
					}
				}
				if (sortName.equalsIgnoreCase("Low to High")) {
					if(accePrice<=accePriceNext) {
						reportStatusPass(methodName + accename +" is sorted accordingly", true, true);
					}else {
						reportStatusFail(methodName + accename +"is not sorted accordingly", true);
					}
				}
			}
		}catch (Exception e) {
			reportStatusFail(methodName + "error in validating lists price according to sorting option ", true);
			e.printStackTrace();
		}
	}	
	/**
	 * Method developed on @Date: 25.02.2022 by @author viswas reddy
	 * 
	 * validation of Accessories page cases and screen protectors tile accessory count and heading text
	 * 
	 * @throws Exception
	 */
	public void validate_acceCasesAndScreenProtectorsTile(String heading) throws Exception {
		methodName = "Shop for Accessories page@: ";
		try {
			sf.seleU.scrollToTop();
			sf.seleU.clickElementByJSE(sf.bAccessoriesObj.casesAndScreenProtectors);
			sf.seleU.wait(2000);
			if(sf.bAccessoriesObj.accessoriesCount.isDisplayed() || 
					sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.casesAndScreenProtText).equalsIgnoreCase(heading)) {
				reportStatusPass("Cases and screen protectors accessories list number displayed and "
						+ "heading text is as expected", true, true);
			}
		}catch (Exception e) {
			reportStatusFail(methodName + "error in validating cases and screen protectors count or heading text", true);
			e.printStackTrace();
		}
	}	
	/**
	 * Method developed on @Date: 25.02.2022 by @author viswas reddy
	 * 
	 * validation of Accessories page power and cables tile accessory count and heading text
	 * 
	 * @throws Exception
	 */
	public void validate_accePowerAndCablesTile(String heading) throws Exception {
		methodName = "Shop for Accessories page@: ";
		try {
			sf.seleU.scrollToTop();
			sf.seleU.clickElementByJSE(sf.bAccessoriesObj.powerAndCablesTile);
			sf.seleU.wait(2000);
			if(sf.bAccessoriesObj.accessoriesCount.isDisplayed() ||
					sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.powerAndCablesText).equalsIgnoreCase(heading)) {
				reportStatusPass("Power and cables accessories list number displayed", true, true);
			}
		}catch (Exception e) {
			reportStatusFail(methodName + "error in validating power and cables count or heading text", true);
			e.printStackTrace();
		}
	}
	/**
	 * @author Satish.Doraiswamy
	 *Mar. 2, 2022
	 * Validate the Brand & Accessories in filter by expanding Brand name
	 */

	public void validate_DeviceCompatibility_Device_Model_Value_ByExpanding(String[] brandList) throws Exception {
		methodName = "Shop for Accessories page@: ";
		// validate Brand and related devices list
		try {
			for (String brand : brandList) {
				sf.seleU.hardwait(2000);
//				Clicking on brand expand icon
				String elementPath="//div[contains(text(),'Device Compatib')]//ancestor::div[contains(@class,'cart-pane')]//div[text()='" + brand + "']//../preceding-sibling::div//span";
				WebElement element=driver.findElement(By.xpath(elementPath));
				sf.seleU.ScrolltoElement(element);
				sf.seleU.hardwait(2000);
				if(!brand.equalsIgnoreCase(InputData_WA.WACC_DeviceBrand)) {
				sf.seleU.clickElementByJSE(element);
				}
				 
				List<WebElement> deviceList = driver
						.findElements(By.xpath("//div[text()='" + brand + "']/../following-sibling::div//label"));
				if (deviceList.size() > 0) {
					reportStatusPass(methodName + brand + ": Device list is present in Filter Bar. ", true, true);
					for (WebElement device : deviceList) {
						reportStatusPass(methodName + "The Brand name selected is" + brand + ": " + sf.seleU.getTextFromWebElement(device), true, true);
						System.out.println(
								"The Brand name selected is" + brand + ": " + sf.seleU.getTextFromWebElement(device));
					}
				} else {
					reportStatusFail(methodName + brand + ": Device list is not present in Filter Bar. ", true);
				}

			}
		} catch (Exception e) {
			reportStatusFail("Error is checking brand and device name in filter on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 04.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to select device model under Device compatibility section in Filter bar on Shop For Accessory Page.
	 * 
	 * @throws Exception
	 */
	public void select_DeviceModelFilter(String deviceBrand, String modelName) throws Exception {
		methodName = "Shop for Accessories page@: ";
		try {
			sf.seleU.hardwait(2000);
			outerloop:
			for (int i=1;i<sf.bAccessoriesObj.deviceBrandListInDC.size();i++) {
				if (sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.deviceBrandListInDC.get(i)).equalsIgnoreCase(deviceBrand)) {
					List<WebElement> deviceList = driver.findElements(By.xpath("//div[text()='" + deviceBrand + "']/../following-sibling::div//label"));
					if(deviceList.size()==0) {
						sf.seleU.clickElementByJSE(sf.bAccessoriesObj.deviceBrandexpandIconInDC.get(i-1));;
						deviceList = driver.findElements(By.xpath("//div[text()='" + deviceBrand + "']/../following-sibling::div//label"));
						}
					
					for (WebElement element : deviceList) {
						if (sf.seleU.getTextFromWebElement(element).equalsIgnoreCase(modelName)) {
							sf.seleU.clickElementByJSE(element);
							reportStatusPass(methodName + modelName + " device Model selected in FilterBar. ", true, true);
							break outerloop;
						}
					}
				}
			}
		} catch (Exception e) {
			reportStatusFail("Error is getting while select device model under FilterBar on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 04.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to click View button in Filter bar on Shop For Accessory Page.
	 * 
	 * @throws Exception
	 */
	public void select_ViewButtonOnFilter() throws Exception {
		methodName = "Shop for Accessories page@: ";
		try {
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.bAccessoriesObj.viewBtn);
			reportStatusPass(methodName + " View button has clicked in FilterBar.", true, true);
		} catch (Exception e) {
			reportStatusFail("Error inclicking on View button in FilterBar on Shop for accessories Page", e);
			e.printStackTrace();
		}

	}
	/**
	 * Method developed on @Date: 04.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to verify accessoryList based on selected device model on Shop For Accessory Page.
	 * 
	 * @throws Exception
	 */
	public void verify_AppliedDeviceModelFilterOnShopAccessoryPage(String[]	accessoryArr) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Shop for Accessories page@: ";
			sf.seleU.hardwait(2000);
			sf.seleU.waitForLoading();
			sf.seleU.scrollToTop();
			if(sf.bAccessoriesObj.accessoriesList.size()==accessoryArr.length) {
				ArrayList<String> accList=new ArrayList<String>();
				for (WebElement ele : sf.bAccessoriesObj.accessoriesList) {					
					accList.add(sf.seleU.getTextFromWebElement(ele));
				}
				for(String acc: accessoryArr) {
					if(accList.contains(acc))
						reportStatusPass(methodName + acc +" is present.", true, true);
					else
						reportStatusFail(methodName + acc +" is not present.", true);
				}
			}
		} catch (Exception e) {
			reportStatusFail("Error is validating applied filter on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 04.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to click on Filter
	 * @throws Exception
	 */
	public void clickOnFilterbutton() throws Exception {
		methodName = "DeviceListing/Shop for Accessories page@: ";
		try {
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.bAccessoriesObj.filter);
			reportStatusPass(methodName + " Filter button has clicked.", true, true);
		} catch (Exception e) {
			reportStatusFail("Error inclicking on Filter button on DeviceListing/Shop for accessories Page", e);
			e.printStackTrace();
		}

	}
	/*
	 * @Author: Satish
	 * Method used to click on reset filter option in 
	 * Accessories Page
	 */
	
	public void resetAccessoriesFilter() throws Exception {
		boolean deviceFilter = false;
		String deviceName=null;
		try {
			methodName = " WACC_Reset_Accessories_Filter_Button @: ";
			deviceFilter=sf.seleU.isElementDisplayed(sf.proSel.deviceFilterOptionName);
			if(deviceFilter) {
				deviceName=sf.seleU.getTextFromWebElementWithYellowHighlight(sf.proSel.deviceFilterOptionName);
				Assert.assertTrue(methodName+"Applied Device Name is "+deviceName+"", deviceFilter);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.proSel.deviceFilterResetButton);
				sf.seleU.waitForLoading();
				deviceFilter=sf.seleU.isElementDisplayed(sf.proSel.deviceFilterOptionName);
				if(!deviceFilter) {
					reportStatusPass(methodName + "Reset Filter done successfully. ", true, true);
				}else {
					reportStatusFail(methodName + "Reset Filter not done successfully", true);
				}
			}
		} 
		catch (Throwable e) {
			reportStatusFail("Error on clicking Browse Accessories button on product selection screen", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 24.03.2022 by @author Satish
	 * 
	 * To Select Accessories directly
	 * 
	 */

	public void clickOnAccessoriesViewDetailsBtn(String option, String product) throws Exception {
		sf.seleU.waitTillLoading();
		methodName = "Accessories Details page@: ";
		try {
			sf.seleU.hardwait(2000);
			sf.seleU.waitTillLoading();
			sf.seleU.clickElementByJSE(driver.findElement(By.xpath("//span[(text()='" + option + "')]")));
			sf.seleU.hardwait(2000);
			sf.seleU.waitForLoading();
			WebElement element = driver.findElement(By.xpath("//div[contains(text(),'"+product+"')]//following-sibling::button"));
					sf.seleU.hardwait(2000);
					sf.seleU.ScrolltoElement(element);
					sf.seleU.clickElementByJSE(element);
//					sf.seleU.hardwait(8000);
					sf.seleU.waitForLoading();
					reportStatusPass(methodName + " Accessories Details page is displayed: ",
							sf.seleU.isElementDisplayed(sf.accessoryDetailObj.backToAccessoriesBtn), true);
		} catch (Throwable e) {
			reportStatusFail("Error on clicking View details button on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 25.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method To Validate no Filter on the Shop for accessories
	 * @throws Exception 
	 * 
	 */
	public void validate_NoFilterSelected() throws Exception {
		// TODO Auto-generated method stub
		methodName = "Shop for Accessories page@: ";
		try {
			sf.seleU.clickElementByJSE(sf.bAccessoriesObj.filter);
			sf.seleU.hardwait(2000);
			for (WebElement element : sf.bAccessoriesObj.filterList) {				
				if (!element.isSelected()) {
					reportStatusPass(methodName + " No Filter Selected. ", true, true);
				}
			}
			sf.seleU.clickElementByJSE(sf.bAccessoriesObj.filterCloseIcon);
		} catch (Exception e) {
			reportStatusFail("Error is getting while check filter on Shop for accessories Page", e);
			e.printStackTrace();
		}
	}
}
