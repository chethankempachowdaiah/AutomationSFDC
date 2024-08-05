package scrum.wa.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.framework.utilities.PDFHelper;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_63_Verify_Accessory_Filter_Based_On_Model_Compatibility extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	// Test Case is for US WACC-4804 & WACC-3346
	@Test(dataProvider = "getDataPlan")
	public void validate_AccessoryList_BasedOnSeletedDeviceModel(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);
		
		reachTillSelectDeviceOptionPage();
		
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice("Apple", "iPhone 11","PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sfdc.selectPro.select_Browse_Accessories();		
		//Validate Accessory List for iPhone 11
		String[] FilterList = {"iPhone 11"};
		sfdc.bAccessories.validate_FilterBubble_OnListingPage(FilterList);
		String[] iPhone11AccessoryList= {"Flat USB-C to Lightning Charge/Sync Cable 6ft White", "Wallport Power Delivery Wall Charger Single USB-C 18W Gray", "Commuter Protective Case Black for iPhone 11", "Symmetry Protective Case Black for iPhone 11", "Otter + Pop Defender Case with Swappable PopTop Black for iPhone 11", "Dual Fast Charge Wall Charger USB-C 30W (18W PD + USB-A 12W) Black", "Charge/Sync Lightning to USB-C Premium Cable 6ft Black", "Wall Charger Fast Charge Power Delivery 20W Black", "Wall Charger Fast Charge Power Delivery 20W White (Cloud Dust)", "DropZone Rugged Case Black for  iPhone 11/XR", "Tempered Glass Screen Protector for iPhone 11/XR", "Wall Charger Dual USB 3.4A with Lightning Cable Black", "IPHONE 11/XR ANTIBACTERIAL TEMPERRED GLASS WITH INSTALLER", "LBT 7 FOOT BRAIDED LIGHTNING CABLE (BLK/WHT)", "LBT 10W FAST WIRELESS CHARGING STAND (BLK)", "LBT SINGLE-PORT 20W PD WALL CHARGER", "Wall Charger Single USB 5W No Cable White", "Charge/Sync Lightning Cable 6ft White", "20W USB-C Power Adapter White", "AirPods Pro Bluetooth Headphone with Wireless Charging Case White", "Charge/Sync Lightning Cable 3ft White", "30W USB-C Power Adapter White", "LBT QC3 ULTRA FAST WALL CHARGER", "Car Charger 2.4A with Single USB Port Black", "AirPods 2nd Gen In-Ear Bluetooth Headphones with Charging Case White"};
		sfdc.bAccessories.verify_AppliedDeviceModelFilterOnShopAccessoryPage(iPhone11AccessoryList);
		
		//Validate Accessory List for iPhone 13 Pro
		sfdc.bAccessories.clickOnFilterbutton();
		sfdc.bAccessories.select_DeviceModelFilter("Apple","iPhone 11");		
		sfdc.bAccessories.select_DeviceModelFilter("Apple","iPhone 13 Pro");
		sfdc.bAccessories.select_ViewButtonOnFilter();
		FilterList[0] = "iPhone 13 Pro";
		sfdc.bAccessories.validate_FilterBubble_OnListingPage(FilterList);
		String[] iPhone13ProAccessoryList= {"LBT SINGLE-PORT 20W PD WALL CHARGER", "AirPods Pro Bluetooth Headphone with Wireless Charging Case White", "Car Charger 2.4A with Single USB Port Black", "AirPods 2nd Gen In-Ear Bluetooth Headphones with Charging Case White"};
		sfdc.bAccessories.verify_AppliedDeviceModelFilterOnShopAccessoryPage(iPhone13ProAccessoryList);
		
		//Validate Accessory List for Samsung Galaxy A12
		sfdc.bAccessories.clickOnFilterbutton();
		sfdc.bAccessories.select_DeviceModelFilter("Apple","iPhone 13 Pro");		
		sfdc.bAccessories.select_DeviceModelFilter("Samsung","Samsung Galaxy A12");
		sfdc.bAccessories.select_ViewButtonOnFilter();
		FilterList[0] = "Samsung Galaxy A12";
		sfdc.bAccessories.validate_FilterBubble_OnListingPage(FilterList);
		String[] samsungGalaxyA12AccessoryList= {"64GB microSDXC Canvas Select Plus Class 10 Flash Memory Card SDCS2", "Flat USB-C to Lightning Charge/Sync Cable 6ft White", "Wallport Power Delivery Wall Charger Single USB-C 18W Gray", "Dual Fast Charge Wall Charger USB-C 30W (18W PD + USB-A 12W) Black", "Wall Charger Fast Charge Power Delivery 20W Black", "Wall Charger Fast Charge Power Delivery 20W White (Cloud Dust)", "Charge/Sync Cable USB-C 6ft White", "AirPods Pro Bluetooth Headphone with Wireless Charging Case White", "30W USB-C Power Adapter White", "Car Charger 2.4A with Single USB Port Black", "AirPods 2nd Gen In-Ear Bluetooth Headphones with Charging Case White"};
		sfdc.bAccessories.verify_AppliedDeviceModelFilterOnShopAccessoryPage(samsungGalaxyA12AccessoryList);
		
		// Validate Accessory List for HTC Nexus 9
		sfdc.bAccessories.clickOnFilterbutton();
		sfdc.bAccessories.select_DeviceModelFilter("Samsung","Samsung Galaxy A12");
		sfdc.bAccessories.select_DeviceModelFilter("HTC", "HTC Nexus 9");
		sfdc.bAccessories.select_ViewButtonOnFilter();
		FilterList[0] = "HTC Nexus 9";
		sfdc.bAccessories.validate_FilterBubble_OnListingPage(FilterList);
		String[] htcNexus9AccessoryList= {"LBT SINGLE-PORT 20W PD WALL CHARGER", "AirPods Pro Bluetooth Headphone with Wireless Charging Case White", "Car Charger 2.4A with Single USB Port Black", "AirPods 2nd Gen In-Ear Bluetooth Headphones with Charging Case White"};
		sfdc.bAccessories.verify_AppliedDeviceModelFilterOnShopAccessoryPage(htcNexus9AccessoryList);
	}

	// Test Case is for US WACC-4804 & WACC-3346
	@Test(dataProvider = "getDataPlan")
	public void validate_AccessoryList_BYOD_Option(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillSelectDeviceOptionPage();
		sfdc.selectAddOn.select_ContinueBYOD();
		sfdc.selectAddOn.validateBYODContinueButton();
		sfdc.selectPro.select_Browse_Accessories();	
		sfdc.bAccessories.validate_NoFilterBubble();
		sfdc.bAccessories.validate_NoFilterSelected();
	}
// Method is for common navigation for all test cases
public void reachTillSelectDeviceOptionPage() throws Exception {
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
	sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
	
}

@DataProvider
public Object[][] getDataPlan() throws IOException {
	return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
}

}
