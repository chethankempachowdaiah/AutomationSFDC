package scrum.wa.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_59_Verify_DeviceFlow_EditFunction_Price_PlanFirst_Test extends Base{
	
	/**
	 * @author Shruti.desai1, Date : 01/03/2022
	 * 
	 *     WACC-2363: Device Flow Edit Function Price Plan First
	 *
	 */
	
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	//Test Case is for US WACC-2363
	@Test(dataProvider = "getDataPlan")
	public void validate_Edit_WirelessPlan(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	

		reachTillDeviceProtection(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,InputData_WA.WACC_DeviceProtectionName);
		//click edit button next to wireless plan
		sfdc.selectPro.clickEditOnProductSelection("Rogers wireless plan");
		sf.seleU.wait(6000);
		//Edit plan
		sfdc.selectPro.validateSelectePlanTileAfterClickEdit();
		sfdc.selectPro.selectDifferentPlanAfterClickEdit(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type, "60GB");
		sfdc.selectPro.validatePlanTileAfterEditingPlan();
		//verify plan has been changed
		sfdc.selectPro.validatePlanChangeafterEditon();
		
				
	}
	@Test(dataProvider = "getDataPlan")
	public void validate_Edit_AddOns(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	

		reachTillDeviceProtection(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,InputData_WA.WACC_DeviceProtectionName);
		//click edit button next to Wireless plan Add ons
		sfdc.selectPro.clickEditOnProductSelection("Wireless plan Add ons");
		sf.seleU.wait(6000);
		//Validate add on after edition
		sfdc.selectPro.validateAddOnPage();
		sfdc.selectAddOn.selectAddOn("SMS", "Unlimited Canada to US/Intl SMS/MMS","NA");
		sf.seleU.waitTillLoading();
		sfdc.selectPro.validateEditAddOn();
				
	}
	
	//click Edit button next to device and change the device selection
		@Test(dataProvider = "getDataPlan")
		public void validate_Edit_Device(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	

			reachTillDeviceProtection(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,InputData_WA.WACC_DeviceProtectionName);
			//click edit button next to Wireless plan Add ons
			sfdc.selectPro.clickEditOnProductSelection("Wireless device");//Choose your device
			sf.seleU.wait(6000);
			//Validate add on after edition
			sfdc.selectPro.validateChooseDevicePage();
			sfdc.shopWADevcs.clickBackToBrowseDevices();
			sfdc.shopWADevcs.selectWirelessDevice("Apple", "iPhone 12","PostWirelessPlans");
			sf.seleU.waitTillLoading();
			//
			sfdc.selectPro.click_UpdateCart_AfterDevice_Plan_Selection();
			sf.seleU.waitTillLoading();
			sfdc.selectPro.click_Btn_OnEdit_Update_PopUp("Confirm updating cart");
			sf.seleU.waitTillLoading();
			sfdc.selectPro.validateEditDevice();
		}
		
	
	
		
	//Method is for common navigation for all test cases
	public void reachTillDeviceProtection(String deviceBrand, String deviceModel, String deviceProtection) throws Exception {
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
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(deviceBrand, deviceModel, "PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		//sfdc.selectPro.selectDeviceProtection(deviceProtection);
		//sfdc.selectPro.clicknAddToCartBtnForDP();
		
	}
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}
