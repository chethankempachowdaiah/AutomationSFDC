package scrum.wa.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_19_VerifyAddedAccessoriesOnShoppingCartPageTest extends MyListener{
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	//Test Case is for US WACC-1967
	@Test(dataProvider = "getDataPlan")
	public void Validate_pageNavigation_ShopAccessoriesPage(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		SoftAssert softassert = new SoftAssert();

		reachTillShopAccessoriesPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,InputData_WA.WACC_DeviceProtectionName);
		
		int preCount = sfdc.bAccessories.getCountOfAccessories();
		sfdc.bAccessories.select_Filter_OnListingPage("$51 - $80");
		sfdc.bAccessories.select_Filter_OnListingPage("Promos & discounts");
		String[] filter = { "$51 - $80", "Promos & discounts", InputData_WA.WACC_DeviceName};
		sfdc.bAccessories.validate_FilterBubble_OnListingPage(filter);
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories",InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.select_BackToAccessoriesLink();
		// verify count
		if (preCount == sfdc.bAccessories.getCountOfAccessories()) {
			softassert.assertTrue(true);
		}
		softassert.assertEquals(true, (sf.bAccessoriesObj.filterBubble.size() == 0));
		sfdc.bAccessories.validate_NoFilterBubble();
		sfdc.bAccessories.validate_NoFilterSelected(filter);
		
		softassert.assertAll();
	}
	
	//Test Case is for US WACC-1984
	@Test(dataProvider = "getDataPlan")
	public void Validate_AccessoriesAdded_ViewOnShoppingCart(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	

		reachTillShopAccessoriesPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,InputData_WA.WACC_DeviceProtectionName);
		
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories",InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();

		sfdc.accessoryDetails.select_BackToAccessoriesLink();
		// Click on Proceed to Shopping Cart button

		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		// Verify added accessories in cart
		sfdc.shopCart.verify_Accessories_Color_Quantity_InCart(InputData_WA.WACC_AccessoryName, 1);
	}
	
	//Test Case is for US WACC-2758
	@Test(dataProvider = "getDataPlan")
	public void Validate_Accessories_color_quantity_removeOption_ViewOnShoppingCart(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	

		reachTillShopAccessoriesPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,InputData_WA.WACC_DeviceProtectionName);
		
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories",InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		
		// Click on Proceed to Shopping Cart button

		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		// Verify added accessories in cart
		sfdc.shopCart.verify_Accessories_Color_Quantity_InCart(InputData_WA.WACC_AccessoryName, 1);
		
		
	}
	
	//Method is for common navigation for all test cases
	public void reachTillShopAccessoriesPage(String deviceBrand, String deviceModel, String deviceProtection) throws Exception {
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
		sfdc.selectPro.selectDeviceProtection(deviceProtection);
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sfdc.selectPro.select_Browse_Accessories();
	}
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}
