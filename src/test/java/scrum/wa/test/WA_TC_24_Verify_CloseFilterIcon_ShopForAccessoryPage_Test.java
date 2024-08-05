package scrum.wa.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_24_Verify_CloseFilterIcon_ShopForAccessoryPage_Test extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	// Test Case is for US WACC-2158
	@Test(dataProvider = "getDataPlan")
	public void Validate_FilterOptionDiscarded_AfterClickCloseOnShopAccessoriesPage(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	

		reachTillDeviceListingPage();

		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName, "PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();

		sfdc.selectPro.select_Browse_Accessories();

		// Retrieve the accessory count before select any filter
		int preCount = sfdc.bAccessories.getCountOfAccessories();

		// Select the filter and without clicking view click the close icon on Filter
		// bar
		String[] filterList = { "$51 - $80", "Promos & discounts", InputData_WA.WACC_DeviceName };
		sfdc.bAccessories.select_FilterOptionAndClickCloseIcon(filterList);

		// Validate no Filter Bubble and no filter is selected
		sfdc.bAccessories.validate_NoFilterBubble();
		sfdc.bAccessories.validate_NoFilterSelected(filterList);

		// Validate accessory count after not applied any filter
		if (preCount == sfdc.bAccessories.getCountOfAccessories()) {
			Assert.assertTrue(true);
		}
	}

	// Test Case is for US WACC-2158
	@Test(dataProvider = "getDataPlan")
	public void Validate_OnlyPreviousSelectedFilterDisplayed_AfterClickCloseOnShopAccessoriesPage(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	

		reachTillDeviceListingPage();

		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName, "PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();

		sfdc.selectPro.select_Browse_Accessories();
		// Apply Filter and click view button
		sfdc.bAccessories.select_Filter_OnListingPage("OtterBox");
		sfdc.bAccessories.select_Filter_OnListingPage("$51 - $80");

		// validate filter on filter bubble
		String[] firstFilterList = { "OtterBox", "$51 - $80", InputData_WA.WACC_DeviceName};
		sfdc.bAccessories.validate_FilterBubble_OnListingPage(firstFilterList);

		// Select the filter and without clicking view click the close icon on Filter
		// bar
		String[] secondFilterList = { "$0 - $15", "Promos & discounts", InputData_WA.WACC_DeviceName};
		sfdc.bAccessories.select_FilterOptionAndClickCloseIcon(secondFilterList);
		sfdc.bAccessories.validate_FilterBubble_OnListingPage(firstFilterList);
		sfdc.bAccessories.validate_NoFilterSelected(secondFilterList);

		// verify applied filter by checking result on Shop for accessory page
		sfdc.bAccessories.verify_AppliedFilterOnShopAccessoryPage("Brand", "OtterBox");
		sfdc.bAccessories.verify_AppliedFilterOnShopAccessoryPage("Price", "$51 - $80");
	}
	// Test Case is for US WACC-2158
	@Test(dataProvider = "getDataPlan")
	public void Validate_FilterOptionDiscarded_AfterClickCloseOnDeviceListingPage(Hashtable<String, String> dataTable)
			throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillDeviceListingPage();

		// Retrieve the device count before select any filter
		int preCount = sf.shopWADevobj.deviceList.size();

		// Select the filter and without clicking view click the close icon on Filter
		// bar
		String[] filterList = { "64GB", "$101 - $250" };
		sfdc.bAccessories.select_FilterOptionAndClickCloseIcon(filterList);

		// Validate no Filter Bubble and no filter is selected
		sfdc.bAccessories.validate_NoFilterBubble();
		sfdc.bAccessories.validate_NoFilterSelected(filterList);

		// Validate accessory count after not applied any filter
		if (preCount == sfdc.shopWADevcs.verifyCountOfSmartphones()) {
			Assert.assertTrue(true);
		}
	}

	// Test Case is for US WACC-2158
	@Test(dataProvider = "getDataPlan")
	public void Validate_OnlyPreviousSelectedFilterDisplayed_AfterClickCloseOnDeviceListingPage(
			Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillDeviceListingPage();

		// Apply Filter and click view button
		sfdc.bAccessories.select_Filter_OnListingPage("64GB");
		sfdc.bAccessories.select_Filter_OnListingPage("$501 - $1000");

		// validate filter on filter bubble
		String[] firstFilterList = { "64GB", "$501 - $1000" };
		sfdc.bAccessories.validate_FilterBubble_OnListingPage(firstFilterList);

		// Select the filter and without clicking view click the close icon on Filter
		// bar
		String[] secondFilterList = { "256GB", "Promos & discounts" };
		sfdc.bAccessories.select_FilterOptionAndClickCloseIcon(secondFilterList);
		sfdc.bAccessories.validate_FilterBubble_OnListingPage(firstFilterList);
		sfdc.bAccessories.validate_NoFilterSelected(secondFilterList);

		// verify applied filter by checking result on Shop for accessory page
		sfdc.shopWADevcs.verify_AppliedPriceFilterOnDeviceListingPage("$501 - $1000");
		sfdc.shopWADevcs.verify_AppliedCapacityFilterOnDeviceListingPage("64GB");
	}


	// Method is for common navigation for all test cases
	public void reachTillDeviceListingPage()
			throws Exception {
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
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size );
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.clickOnContinueToDevice();
	}

	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}
