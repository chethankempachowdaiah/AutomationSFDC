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

public class WA_TC_21_VerifyAccessoryDetailsPageTest extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	//Test Case is for US WACC-1971
	@Test(dataProvider = "getDataPlan")
	public void validate_Details_OnShopAccessories_and_AccessoryDetails(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		// ***************LOGIN AS AE***********************//
		reachTillShopAccessoriesPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();

		sfdc.selectPro.select_Browse_Accessories();
		//Validate specialOffer text
		String options[] = {"All accessories", "Cases & screen protectors", "Power and cables", "Audio and headphones", "Adapters"};
		sfdc.bAccessories.validate_DetailsOnShopAccessoryPage(options);
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories",InputData_WA.WACC_AccessoryName);
		
		//validate brand name on Accessories details page. 
		sfdc.accessoryDetails.validate_BrandName_onAccessoryDetailsPage(InputData_WA.WACC_AccessoryBrand); 
		sfdc.accessoryDetails.validate_AccessoryName_onAccessoryDetailsPage(InputData_WA.WACC_AccessoryName);
	}
	
	//Test Case is for US WACC-1971
	@Test(dataProvider = "getDataPlan")
	public void validate_BrandName_OnAccessoryDetails(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		// ***************LOGIN AS AE***********************//
		reachTillShopAccessoriesPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();

		sfdc.selectPro.select_Browse_Accessories();
		sfdc.bAccessories.select_ResetFilterOnFilterBubble();
		//Validate specialOffer text
		String options[] = {"All accessories", "Cases & screen protectors", "Power and cables", "Audio and headphones", "Adapters"};
		sfdc.bAccessories.validate_DetailsOnShopAccessoryPage(options);
		sfdc.bAccessories.clickOnViewDetailsBtn("Power and cables","Car Charger 2.4A with Single USB Port Black");
		
		//validate brand name on Accessories details page. 
		sfdc.accessoryDetails.validate_BrandName_onAccessoryDetailsPage("Ventev"); 
		sfdc.accessoryDetails.validate_AccessoryName_onAccessoryDetailsPage("Car Charger 2.4A with Single USB Port Black");
		
	}
	//Method is for common navigation for all test cases
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
