package com.Regression_WA_FIT_test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.data.InputData_WA.DeviceCapacity;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Satish.Doraiswamy
 *Mar. 15, 2022
 * MP26
 * US 3344
 */
public class WA_TC_56_3344_Verify_CapacityFilterForDevices_Test extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	// Test Case is for US WACC-3344	
	@Test(dataProvider = "getDataPlan")
	public void validate_256GB_CapacityFilter_OnDeviceListingPage(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillDeviceListingPage();

		// Apply Filter and click view button
		sfdc.bAccessories.select_Filter_OnListingPage(DeviceCapacity.DEVICE256GBCAPACITY.getDeviceCapacity());
		// validate filter on filter bubble
		String[] firstFilterList = {DeviceCapacity.DEVICE256GBCAPACITY.getDeviceCapacity()};
		sfdc.bAccessories.validate_FilterBubble_OnListingPage(firstFilterList);
		
		// verify applied filter by checking result on Shop for accessory page		
		sfdc.shopWADevcs.verify_AppliedCapacityFilterOnDeviceListingPage(DeviceCapacity.DEVICE256GBCAPACITY.getDeviceCapacity());
		}

	// Test Case is for US WACC-3344	
		@Test(dataProvider = "getDataPlan")
		public void validate_512GB_OrMore_CapacityFilter_OnDeviceListingPage(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);

			reachTillDeviceListingPage();

			// Apply Filter and click view button
			sfdc.bAccessories.select_Filter_OnListingPage(DeviceCapacity.DEVICE512GBORMORECAPACITY.getDeviceCapacity());
			// validate filter on filter bubble
			String[] firstFilterList = {DeviceCapacity.DEVICE512GBORMORECAPACITY.getDeviceCapacity()};
			sfdc.bAccessories.validate_FilterBubble_OnListingPage(firstFilterList);
			
			// verify applied filter by checking result on Shop for accessory page		
			sfdc.shopWADevcs.verify_AppliedCapacityFilterOnDeviceListingPage(DeviceCapacity.DEVICE512GBORMORECAPACITY.getDeviceCapacity());
			}
	// Method is for common navigation for all test cases
	public void reachTillDeviceListingPage()
			throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		
		  sfdc.home.closeTabIfOpen();
		  sfdc.accDetails.searchBusAccGlobalSearch(InputData_WA.account_Business_R4B);
		  sfdc.accRelated.createOpportunity(); sfdc.cOpp.enterOpportunityDetails();
		  sfdc.cOpp.selecetExistingContactInOpportunity(InputData_WA.contact_Business_R4B);
		  sfdc.cQuote.clickCreateQuotePbfButton_FIT();
		  sfdc.selectPro.verifyWirelessProducts();
		  sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type,InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size );
		  sfdc.selectPro.continueToAddOnsButton();
		  sfdc.selectAddOn.clickOnContinueToDevice();
		 
	}

	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET_R4BPreFIT);
	}
}
