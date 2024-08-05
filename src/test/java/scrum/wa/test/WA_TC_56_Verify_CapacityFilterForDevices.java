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

public class WA_TC_56_Verify_CapacityFilterForDevices extends MyListener {
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
		sfdc.bAccessories.select_Filter_OnListingPage("256GB");
		// validate filter on filter bubble
		String[] firstFilterList = { "256GB"};
		sfdc.bAccessories.validate_FilterBubble_OnListingPage(firstFilterList);
		
		// verify applied filter by checking result on Shop for accessory page		
		sfdc.shopWADevcs.verify_AppliedCapacityFilterOnDeviceListingPage("256GB");
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
			sfdc.bAccessories.select_Filter_OnListingPage("512GB or more");
			// validate filter on filter bubble
			String[] firstFilterList = { "512GB or more"};
			sfdc.bAccessories.validate_FilterBubble_OnListingPage(firstFilterList);
			
			// verify applied filter by checking result on Shop for accessory page		
			sfdc.shopWADevcs.verify_AppliedCapacityFilterOnDeviceListingPage("512GB or more");
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
		  sfdc.accRelated.createOpportunity(); sfdc.cOpp.enterOpportunityDetails();
		  sfdc.cOpp.selecetExistingContactInOpportunity();
		  sfdc.cQuote.clickCreateQuotePbfButton();
		  sfdc.selectPro.verifyWirelessProducts();
		  sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type,InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size );
		  sfdc.selectPro.continueToAddOnsButton();
		  sfdc.selectAddOn.clickOnContinueToDevice();
		 
	}

	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}
