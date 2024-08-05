package scrum.wa.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_54_ValidateAcceListingsQuickFiltersAndDefaultState extends MyListener {

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-1603 
	 *                              Validate Accessories default listings page sorting and filters applied
	 *                              and US WACC-2297 validating multi DP tiles
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_AcceDefaultListingsPageSortingAndFilters(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillShopAccessoriesPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sfdc.bAccessories.select_ResetFilterOnFilterBubble();
		sfdc.bAccessories.validate_AcceTilesAreDisplayed();
		sfdc.bAccessories.validate_AcceSortbyAndFilters();
		
		//validates sorting
		sfdc.bAccessories.select_sortByOptions("High to Low");
		sfdc.bAccessories.select_sortByOptions("Low to High");
		//validates accessories cases and screen protectors, and power and cables tiles and it's heading text
		sfdc.bAccessories.validate_acceCasesAndScreenProtectorsTile(InputData_WA.WACC_CasesAndScreenProtectors_Text);
		sfdc.bAccessories.validate_accePowerAndCablesTile(InputData_WA.WACC_PowerAndCables_Text);
		sf.seleU.clickElementByJSE(sf.bAccessoriesObj.allAccessoriesTile);
		
		//validate device compatibility, brand, and price
		String[] deviceCompatiblie = { "Samsung", "Apple", "Google" };
		String[] brand = { "OtterBox" };
		String[] price = { "$0 - $15", "$16 - $30", "$31 - $50", "$51 - $80", "$81 or more" };
		sfdc.bAccessories.validate_DeviceCompatibility_Filter(deviceCompatiblie);
		sfdc.bAccessories.validate_Brand_Filter(brand);
		sfdc.bAccessories.validate_Price_Filter(price);
		sfdc.bAccessories.validate_PromosDiscount_Filter();
		
		// Apply Filter and click view button
		sfdc.bAccessories.select_Filter_OnListingPage("OtterBox");
		sfdc.bAccessories.select_Filter_OnListingPage("$51 - $80");
		sfdc.bAccessories.select_Filter_OnListingPage("Promos & discounts");
		
		// validate filter on filter bubble
		String[] FilterList = { "OtterBox", "$51 - $80" ,"Promos & discounts"};
		sfdc.bAccessories.validate_FilterBubble_OnListingPage(FilterList);
		// verify applied filter by checking result on Shop for accessory page
		sfdc.bAccessories.verify_AppliedFilterOnShopAccessoryPage("Brand", "OtterBox");
		sfdc.bAccessories.verify_AppliedFilterOnShopAccessoryPage("Price", "$51 - $80");
		sfdc.bAccessories.verify_AppliedFilterOnShopAccessoryPage("offer", "Promos & discounts");
		
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
		sfdc.selectPro.selectShopeWirelessDevices();
		sfdc.shopWADevcs.selectWirelessDevice(deviceBrand, deviceModel, "PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		// below lines are for US 2295
		//validating radio buttons, DP caption, DP's description and Prices available
		sfdc.selectPro.selectDeviceProtection("Device Protection");
		sfdc.selectPro.Validate_DP_multiDP_singleDP();
		sfdc.selectPro.validate_DP_Caption("multiDP");
		sfdc.selectPro.clicknAddToCartBtnForDP();
		//till here
		
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,
				InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,
				InputData_WA.WACC_AddOn_Availability);
		sf.seleU.wait(6000);
		sf.seleU.waitTillLoading();
		sfdc.selectPro.select_Browse_Accessories();
	}
	
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}
