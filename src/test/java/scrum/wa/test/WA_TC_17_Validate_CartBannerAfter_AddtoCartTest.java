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

public class WA_TC_17_Validate_CartBannerAfter_AddtoCartTest extends MyListener{
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	//Test Case is for US WACC-1922
	@Test(dataProvider = "getDataPlan")
	public void validate_BannerTextAfterAddToCart_Plan_Addon_Device_DP_Accessories(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();	
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		// ***************LOGIN AS AE***********************//
		reachTillSelectPlanPage();
		
		// Select wireless plan
		//Validate cart Banner before Add To cart wireless plan
		sfdc.selectPro.validate_CartBannerTextNotDisplayed();
		//Validate cart Banner after Add To cart wireless plan
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size );
		sfdc.selectPro.validate_CartBannerText("Your item was added to your cart", "Continue shopping wireless plans");
		
		//Select AddOn
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		//Validate cart Banner before Add To cart AddOn
		sfdc.selectPro.validate_CartBannerTextNotDisplayed();
		//Validate cart Banner after Add To cart AddOn
		sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
		sfdc.selectPro.validate_CartBannerText("Your item was added to your cart", "Continue shopping add-ons");
		
		//select Device
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName, "PostWirelessPlans");
		//Validate cart Banner before Add To cart Device
		sfdc.selectPro.validate_CartBannerTextNotDisplayed();
		//Validate cart Banner after Add To cart Device
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.validate_CartBannerText("Your item was added to your cart", "Continue shopping devices");
		
		//Select Device Protection
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		//Validate cart Banner before Add To cart Device Protection
		sfdc.selectPro.validate_CartBannerTextNotDisplayed();
		//Validate cart Banner after Add To cart Device Protection
		sfdc.selectPro.clicknAddToCartBtnForDP();	
		sfdc.selectPro.validate_CartBannerText("Your item was added to your cart", "Continue shopping device protection");
		
		//Select Accessories
		sfdc.selectPro.select_Browse_Accessories();
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories",InputData_WA.WACC_AccessoryName);
		//Validate cart Banner before Add To cart Accessories
		sfdc.selectPro.validate_CartBannerTextNotDisplayed();
		//Validate cart Banner after Add To cart Accessories
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.selectPro.validate_CartBannerText("Your item was added to your cart", "Continue shopping accessories");
		
	}
	//Method is for common navigation for all test cases
	public void reachTillSelectPlanPage() throws Exception {
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
		
	}
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}
