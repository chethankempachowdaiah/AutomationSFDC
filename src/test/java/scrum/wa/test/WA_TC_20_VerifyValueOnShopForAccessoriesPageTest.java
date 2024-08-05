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

public class WA_TC_20_VerifyValueOnShopForAccessoriesPageTest extends MyListener{
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	//Test case is for US WACC-1963
	@Test(dataProvider = "getDataPlan")
	public void validate_Assceeories_Tile_Details(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	

		reachTillShopAccessoriesPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		
		sfdc.bAccessories.accessories_tile_details();
		
	}
	
	//Test case is for US WACC-1973
	@Test(dataProvider = "getDataPlan")
	public void validate_otherColorAccessories_AddToCart(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	

		reachTillShopAccessoriesPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories",InputData_WA.WACC_AccessoryName);
		
		sfdc.accessoryDetails.verify_DefaultPriceIsLowest();
		sfdc.accessoryDetails.select_otherColorAccessory_DiscountedPrice("White");
		sfdc.accessoryDetails.enterQuantityAccDetailsPage("10");
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.selectPro.validate_CartBannerText("Your item was added to your cart", "Continue shopping accessories");
	}
	
	//Test case is for US WACC-1973
	@Test(dataProvider = "getDataPlan")
	public void validate_DefaultSelectedColorPrice(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	

		reachTillShopAccessoriesPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories",InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.verifyDefaultoptionPrice();
		sfdc.accessoryDetails.enterQuantityAccDetailsPage("15");
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.selectPro.validate_CartBannerText("Your item was added to your cart", "Continue shopping accessories");
	
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
		sfdc.selectPro.select_Browse_Accessories();
	}
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}
