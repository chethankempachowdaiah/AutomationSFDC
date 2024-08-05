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

public class WA_TC_61_Validate_DeviceFunctionalityEdit_Test extends MyListener{

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	// Test Case is for US WACC-2459
	@Test(dataProvider = "getDataPlan")
	public void validate_ClickEditDevice(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
	
		reachTillClickEditOnProductSelection(InputData_WA.WACC_DeviceBrand,InputData_WA.WACC_DeviceName);
		sfdc.selectPro.fetchData();
		sfdc.selectPro.clickEditOnProductSelection("Wireless device:");
		
	}	
	
	// Test Case is for US WACC-2459
	@Test(dataProvider = "getDataPlan")
	public void validate_ChangeDeviceConfiguration_After_ClickEditDevice(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
	
		reachTillClickEditOnProductSelection(InputData_WA.WACC_DeviceBrand,InputData_WA.WACC_DeviceName);
		sfdc.selectPro.fetchData();
		sfdc.selectPro.clickEditOnProductSelection("Wireless device:");
		sfdc.shopWADevcs.changeDeviceConfiguration(InputData_WA.DeviceCapacity.DEVICE128GBCAPACITY.getDeviceCapacity());
		
	}	
		
	// Test Case is for US WACC-2459
	@Test(dataProvider = "getDataPlan")
	public void validate_ChangeDeviceSelection_FromDeviceListing(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
	
		reachTillClickEditOnProductSelection(InputData_WA.WACC_DeviceBrand,InputData_WA.WACC_DeviceName);
		sfdc.selectPro.fetchData();
		sfdc.selectPro.clickEditOnProductSelection("Wireless device:");
		sfdc.shopWADevcs.clickBackToBrowseDevices();
		sfdc.shopWADevcs.selectWirelessDevice("Apple", "iPhone 12","PostWirelessPlans");
		sfdc.selectPro.click_UpdateCart_AfterDevice_Plan_Selection();
		sf.seleU.waitTillLoading();
		sfdc.selectPro.click_Btn_OnEdit_Update_PopUp("Confirm updating cart");
	}			
		
	// Test Case is for US WACC-2459
	@Test(dataProvider = "getDataPlan")
	public void validate_ClickEditDeviceProtection(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
	
		reachTillClickEditOnProductSelection(InputData_WA.WACC_DeviceBrand,InputData_WA.WACC_DeviceName);
		sfdc.selectPro.fetchData();
		sfdc.selectPro.clickEditOnProductSelection("Device Protection:");
		
	}			
				
	// Test Case is for US WACC-2459
	@Test(dataProvider = "getDataPlan")
	public void validate_UpdateDeviceProtection(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
	
		reachTillClickEditOnProductSelection(InputData_WA.WACC_DeviceBrand,InputData_WA.WACC_DeviceName);
		//sfdc.selectPro.fetchData();
		sfdc.selectPro.clickEditOnProductSelection("Device Protection:");
		sfdc.selectPro.selectDeviceProtection("Device Protection");
		sfdc.selectPro.click_UpdateCart_AfterDevice_Plan_Selection();
		
	}			
	
	// Test Case is for US WACC-2459
	@Test(dataProvider = "getDataPlan")
	public void validate_ClickEditWirelessPlan(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
	
		reachTillClickEditOnProductSelection(InputData_WA.WACC_DeviceBrand,InputData_WA.WACC_DeviceName);
		sfdc.selectPro.fetchData();
		sfdc.selectPro.clickEditOnProductSelection("Rogers wireless plan:");
	}	
	
	// Test Case is for US WACC-2459
	@Test(dataProvider = "getDataPlan")
	public void validate_UpdateWirelessPlan(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
	
		reachTillClickEditOnProductSelection(InputData_WA.WACC_DeviceBrand,InputData_WA.WACC_DeviceName);
		sfdc.selectPro.fetchData();
		sfdc.selectPro.clickEditOnProductSelection("Rogers wireless plan:");
		sfdc.selectPro.selectDifferentPlanAfterClickEdit(InputData_WA.WACC_Data_Type, InputData_WA.plan_typePooled,"100GB");
		sfdc.selectPro.click_UpdateCart_AfterDevice_Plan_Selection();
		sf.seleU.waitTillLoading();
		sfdc.selectPro.click_Btn_OnEdit_Update_PopUp("Confirm updating cart");
		
	}	
	
	// Test Case is for US WACC-2459
	@Test(dataProvider = "getDataPlan")
	public void validate_ClickEditWirelessAddOns(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
	
		reachTillClickEditOnProductSelection(InputData_WA.WACC_DeviceBrand,InputData_WA.WACC_DeviceName);
		sfdc.selectPro.fetchData();
		sfdc.selectPro.clickEditOnProductSelection("Wireless plan Add ons:");
	}	
	
	// Test Case is for US WACC-2459
	@Test(dataProvider = "getDataPlan")
	public void validate_UpdateWirelessAddOns(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
	
		reachTillClickEditOnProductSelection(InputData_WA.WACC_DeviceBrand,InputData_WA.WACC_DeviceName);
		sfdc.selectPro.fetchData();
		sfdc.selectPro.clickEditOnProductSelection("Wireless plan Add ons:");
		sfdc.selectAddOn.leaveUpdateLongDistancePlans(InputData_WA.addOnType_LD,InputData_WA.addOnName_International_LDSaver,InputData_WA.WACC_AddOn_Availability);
		sfdc.selectPro.click_Btn_OnEdit_Update_PopUp("Confirm selection");
		
	}
	
	// Test Case is for US WACC-2459
	@Test(dataProvider = "getDataPlan")
	public void validate_Cancel_UpdatedDeviceSelection(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
	
		reachTillClickEditOnProductSelection(InputData_WA.WACC_DeviceBrand,InputData_WA.WACC_DeviceName);
		sfdc.selectPro.fetchData();
		sfdc.selectPro.clickEditOnProductSelection("Wireless device:");
		sfdc.shopWADevcs.clickBackToBrowseDevices();
		sfdc.shopWADevcs.selectWirelessDevice("Apple", "iPhone 12","PostWirelessPlans");
		sfdc.selectAddOn.closeSelection();
		sfdc.selectPro.click_Btn_OnEdit_Update_PopUp("Cancel and go back");
	}				
			
			
	//Method is for common navigation for all test cases
	public void reachTillClickEditOnProductSelection(String deviceBrand, String deviceModel) throws Exception {
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
		//sfdc.selectPro.selectPlanType();
		sfdc.selectPro.verifyWirelessProducts();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size );
		sfdc.selectPro.clickOnPlansAddToCart();
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.validateLongDistancePlans(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(deviceBrand, deviceModel, "PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();
	}			
				
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}	

}
