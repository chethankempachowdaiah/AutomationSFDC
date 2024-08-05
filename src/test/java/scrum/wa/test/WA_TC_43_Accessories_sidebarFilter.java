package scrum.wa.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_43_Accessories_sidebarFilter extends MyListener {

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-2331 Validate side bar filters reset button
	 *                              Validate side bar filters view button
	 *                              Validate side bar filters click close button
	 *                              and US WACC-2297 validating multi DP tiles
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_AcceSidebarFilters(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillShopAccessoriesPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		// validate reset filters on side bar
		String[] filterList = { "Pixel 6", "$16 - $30" };
		sfdc.bAccessories.select_FilterOption(filterList, "Google");
		sfdc.bAccessories.validate_resetfiltersOnSidebar(filterList);
		// validate view button and check selected filters on listings
		sf.seleU.clickElementByJSE(sf.bAccessoriesObj.resetFilter);
		sf.seleU.wait(2000);
		String[] secondFilterList = { "$31 - $50", "Promos & discounts" };
		sfdc.bAccessories.select_FilterOption(secondFilterList, null);
		sf.seleU.clickElementByJSE(sf.bAccessoriesObj.viewBtn);
		// verify applied filter by checking result on Shop for accessory page
		sfdc.bAccessories.validate_FilterBubble_OnListingPage(secondFilterList);
		sf.seleU.wait(2000);
		sfdc.bAccessories.verify_AppliedFilterOnShopAccessoryPage("offer", "Promos & discounts");
		sfdc.bAccessories.verify_AppliedFilterOnShopAccessoryPage("Price", "$31 - $50");
		// Select the filter and without clicking view click the close icon on Filter bar
		String[] thirdFilterList = { "iPhone 11", "$51 - $80" };
		sfdc.bAccessories.select_FilterOption(thirdFilterList,"Apple");
		sf.seleU.clickElementByJSE(sf.bAccessoriesObj.filterCloseIcon);
		sfdc.bAccessories.validate_FilterBubble_OnListingPage(secondFilterList);
		sfdc.bAccessories.validate_NoFilterSelected(thirdFilterList);
	}
	
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-2331 first Device
	 *                              Validate side bar filters reset button
	 *                              Validate side bar filters view button
	 *                              Validate side bar filters click close button
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_AcceSidebarFiltersFirstDeviceandPlan(Hashtable<String, String> dataTable) throws Exception {

		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(waData.account_Business_R4B);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();
		sfdc.cQuote.clickCreateQuotePbfButton();
		sfdc.selectPro.selectShopeWirelessDevices();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
				"PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sf.seleU.waitTillLoading();
		
		// below lines are for US 2297
		// validate update DP by clicking edit and change DP
		sfdc.selectPro.selectDeviceProtection("Device Protection");
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sfdc.selectPro.clickEditOnProductSelection("Device Protection:");
		sfdc.selectPro.selectDeviceProtection("Apple Care");
		sfdc.selectPro.clickOnUpdateCartDP("Apple Care");
		//validate select different DP and click close edit and changes must reverted
		sfdc.selectPro.clickEditOnProductSelection("Device Protection");
		sfdc.selectPro.selectDeviceProtection("Device Protection");
		sfdc.selectPro.validate_closeBtnOnDP("Apple Care");
		// till here
		
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,
				InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.validateLongDistancePlans("Long Distance", "US LD", "No Offer");
		sfdc.selectAddOn.clickOnAddToCartAddOns();
		sf.seleU.wait(6000);
		sf.seleU.waitTillLoading();
		sfdc.selectPro.select_Browse_Accessories();
		// validate reset filters on side bar
		String[] filterList = { "Motorola Edge", "$16 - $30" };
		sfdc.bAccessories.select_FilterOption(filterList, "Motorola");
		sfdc.bAccessories.validate_resetfiltersOnSidebar(filterList);
		// validate view button and check selected filters on listings
		sf.seleU.clickElementByJSE(sf.bAccessoriesObj.resetFilter);
		sf.seleU.wait(2000);
		String[] secondFilterList = { "$51 - $80", "Promos & discounts" };
		sfdc.bAccessories.select_FilterOption(secondFilterList, null);
		sf.seleU.clickElementByJSE(sf.bAccessoriesObj.viewBtn);
		// verify applied filter by checking result on Shop for accessory page
		sfdc.bAccessories.validate_FilterBubble_OnListingPage(secondFilterList);
		sf.seleU.wait(2000);
		sfdc.bAccessories.verify_AppliedFilterOnShopAccessoryPage("offer", "Promos & discounts");
		sfdc.bAccessories.verify_AppliedFilterOnShopAccessoryPage("Price", "$51 - $80");
		// Select the filter and without clicking view click the close icon on Filter
		// bar
		String[] thirdFilterList = { "iPhone 13", "$31 - $50" };
		sfdc.bAccessories.select_FilterOption(thirdFilterList, "Apple");
		sf.seleU.clickElementByJSE(sf.bAccessoriesObj.filterCloseIcon);
		sfdc.bAccessories.validate_FilterBubble_OnListingPage(secondFilterList);
		sfdc.bAccessories.validate_NoFilterSelected(thirdFilterList);
	}

	public void reachTillShopAccessoriesPage(String deviceBrand, String deviceModel) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(waData.account_Business_R4B);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();
		sfdc.cQuote.clickCreateQuotePbfButton();
		sfdc.selectPro.verifyWirelessProducts();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,
				InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.validateLongDistancePlans("Long Distance", "US LD", "No Offer");
		sfdc.selectAddOn.clickOnAddToCartAddOns();
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(deviceBrand, deviceModel, "PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		
		//below lines are for US 2297
		sfdc.selectPro.selectDeviceProtection("Device Protection");
		sfdc.selectPro.clicknAddToCartBtnForDP();
		//validate by clicking edit DP radio buttons, update cart and I don't want DP available
		sfdc.selectPro.clickEditOnProductSelection("Device Protection:");
		sfdc.selectPro.selectDeviceProtection("Apple Care");
		sfdc.selectPro.Validate_DP_multiDP_singleDP();
		sfdc.selectPro.Validate_updateCart();;
		sfdc.selectPro.select_noDP();
		//till here
		
		sfdc.selectPro.select_Browse_Accessories();
	}

	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}
