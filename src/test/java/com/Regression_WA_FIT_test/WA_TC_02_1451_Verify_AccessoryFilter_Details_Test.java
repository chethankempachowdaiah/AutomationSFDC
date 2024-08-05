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
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_02_1451_Verify_AccessoryFilter_Details_Test extends MyListener{
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	// MP26
	//Test case is for US WACC-1451
	@Test(dataProvider = "getDataPlan")
	public void validate_BrandName_DeviceName_ShopForAccessoriesPage(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	

		reachTillDeviceAdded(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sfdc.selectPro.select_Browse_Accessories();
//		String options[] = {"All accessories", "Cases & screen protectors", "Power and cables"};
		String options[] = {"All accessories",  "Power and cables","Memory","Cases & screen protectors","Adapters","Audio and headphones"};//"Headset/Bluetooth"
//		String brandList[]= {"Motorola", "TCL", "Google", "Uniden", "Apple", "Sonim", "Samsung", "ZTE"};
		String brandList[]= { "Alcatel","Apple","BlackBerry","CAT","CradlePoint", "Doro","Google", "HTC","Huawei","Inseego","Motorola","Sonim", "Samsung"};
		sfdc.bAccessories.validate_FilterBar_ShopForAccessoriesPage(options, brandList);
		
	}
	

	
	//Method is for common navigation for all test cases
	public void reachTillDeviceAdded(String deviceBrand, String deviceModel) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		
		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(InputData_WA.account_Business_R4B);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity(InputData_WA.contact_Business_R4B);
		sfdc.cQuote.wait_Till_Load_CreateQuote_Button();
		sfdc.cQuote.clickCreateQuotePbfButton_FIT();
		sfdc.selectPro.verifyWirelessProducts();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size );
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(deviceBrand, deviceModel, "PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();

	}
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET_R4BPreFIT);
	}

}
