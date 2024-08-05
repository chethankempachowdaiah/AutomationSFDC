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

public class WA_TC_62_Verify_DeviceOrderSummary_Test extends MyListener{

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	
	// Test Case is for US WACC-2132
	@Test(dataProvider = "getDataPlan")
	public void validate_FinacedDevice_OnShoppingCartPage(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
	
		reachTillSelectWirelessDevice(InputData_WA.WACC_DeviceBrand,InputData_WA.WACC_DeviceName);
		
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName, "PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		//sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		//sfdc.selectPro.clicknAddToCartBtnForDP();
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 1);
		
	}	
	
	// Test Case is for US WACC-2132
	@Test(dataProvider = "getDataPlan")
	public void validate_PayInFullDevice_OnShoppingCartPage(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
	
		reachTillSelectWirelessDevice(InputData_WA.WACC_DeviceBrand,InputData_WA.WACC_DeviceName);
		
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName, "PostWirelessPlans");
		sfdc.shopWADevcs.selectDevicePaywithFullPrice();
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		sfdc.shopCart.verify_OneTimeCost_itemName_Price(InputData_WA.WACC_DeviceName,"700.00", 1);
	}
	
	// Test Case is for US WACC-2132
	@Test(dataProvider = "getDataPlan")
	public void validate_ChangingDevice_PricePlan_Quantity_OnSummaryPage(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
	
		reachTillSelectWirelessDevice(InputData_WA.WACC_DeviceBrand,InputData_WA.WACC_DeviceName);
		
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName, "PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		sfdc.shopCart.clickProceedToCheckoutBtn();
		sfdc.reOrder.updateQty_SmryPage();
		sfdc.reOrder.clickCancelAndBackButton();
		sfdc.shopCart.updateCartQtyPlans("2");
	}	
	
	
	//Method is for common navigation for all test cases
	public void reachTillSelectWirelessDevice(String deviceBrand, String deviceModel) throws Exception {
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
		//sfdc.selectAddOn.validateLongDistancePlans(InputData_WA.addOnType_LD,InputData_WA.addOnName_International_LDSaver,
		//		"International LD Saver");
		//sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		sfdc.selectAddOn.validateLongDistancePlans(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
		sfdc.selectAddOn.clickOnContinueToDevice();
	}
	
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}
