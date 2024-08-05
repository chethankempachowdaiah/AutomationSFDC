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

public class WA_TC_18_Validate_Filter_ShopForAceessoriesPageTest extends MyListener{
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	//Test case is for US WACC-1451
	@Test(dataProvider = "getDataPlan")
	public void validate_BrandName_DeviceName_ShopForAccessoriesPage(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	

		reachTillDeviceAdded(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sfdc.selectPro.select_Browse_Accessories();
		String options[] = {"All accessories", "Cases & screen protectors", "Power and cables"};
		String brandList[]= {"Motorola", "Google", "Uniden", "Apple", "Sonim", "Samsung", "ZTE"};
		sfdc.bAccessories.validate_FilterBar_ShopForAccessoriesPage(options, brandList);
		
	}
	
	//Test case is for US WACC-1965
	@Test(dataProvider = "getDataPlan")
	public void validate_Page_Filter(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	

		reachTillDeviceAdded(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sfdc.selectPro.select_Browse_Accessories();
		String[] deviceCompatiblie = { "Samsung", "Apple", "Google" };
		String[] brand = { "OtterBox" };
		String[] price = { "$0 - $15", "$16 - $30", "$31 - $50", "$51 - $80", "$81 or more" };
		sfdc.bAccessories.validate_DeviceCompatibility_Filter(deviceCompatiblie);
		sfdc.bAccessories.validate_Brand_Filter(brand);
		sfdc.bAccessories.validate_Price_Filter(price);
		sfdc.bAccessories.validate_PromosDiscount_Filter();
	}

	//Test case is for US WACC-1966
	@Test(dataProvider = "getDataPlan")
	public void validate_ResetFilterOnFilterBubble(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	

		reachTillDeviceAdded(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();	

		sfdc.selectPro.select_Browse_Accessories();
		//int preCount = sfdc.bAccessories.getCountOfAccessories();
		sfdc.bAccessories.select_Filter_OnListingPage("$51 - $80");
		sfdc.bAccessories.select_Filter_OnListingPage("Promos & discounts");
		String[] filter = { "$51 - $80", "Promos & discounts", InputData_WA.WACC_DeviceName };
		sfdc.bAccessories.validate_FilterBubble_OnListingPage(filter);
		sfdc.bAccessories.select_ResetFilterOnFilterBubble();
		sfdc.bAccessories.validate_NoFilterBubble();

		// verify count
		/*
		 * if (preCount == sfdc.bAccessories.getCountOfAccessories()) {
		 * Assert.assertTrue(true); }
		 */

	}
	
	//Test case is for US WACC-1966
	@Test(dataProvider = "getDataPlan")
	public void validate_CloseIconOnFilterBubble(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		reachTillDeviceAdded(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();
		
		sfdc.selectPro.select_Browse_Accessories();
		//int preCount = sfdc.bAccessories.getCountOfAccessories();
		sfdc.bAccessories.select_Filter_OnListingPage("$51 - $80");
		sfdc.bAccessories.select_Filter_OnListingPage("Promos & discounts");
		String[] filter = { "$51 - $80", "Promos & discounts" , InputData_WA.WACC_DeviceName};
		sfdc.bAccessories.validate_FilterBubble_OnListingPage(filter);
		sfdc.bAccessories.select_closeBtnOnFilterBubble();
		sfdc.bAccessories.validate_NoFilterBubble();
		
		// verify count
		/*
		 * if (preCount == sfdc.bAccessories.getCountOfAccessories()) {
		 * Assert.assertTrue(true); }
		 */
		sfdc.bAccessories.validate_NoFilterSelected(filter);

	}
	
	//Method is for common navigation for all test cases
	public void reachTillDeviceAdded(String deviceBrand, String deviceModel) throws Exception {
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
		sfdc.shopWADevcs.selectWirelessDevice(deviceBrand, deviceModel, "PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();

	}
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}

}
