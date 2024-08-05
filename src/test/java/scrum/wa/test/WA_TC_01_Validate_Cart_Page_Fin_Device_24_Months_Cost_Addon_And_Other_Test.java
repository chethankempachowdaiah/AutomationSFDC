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


/**
 * @author Satish.Doraiswamy
 *Jan. 27, 2022
 *
 *
 */

public class WA_TC_01_Validate_Cart_Page_Fin_Device_24_Months_Cost_Addon_And_Other_Test extends MyListener {

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	/**
	 * @author Satish.Doraiswamy
	 *  Jan. 27, 2022
	 * Selecting 24Month Finance Plan and validating in CartPage for Plan, Add-on , Hide functions and Increasing the Quantity 
	 */

	// Test Case is for WACC-2389 validate_cart page with financed device 36 months
	@Test(dataProvider = "getDataPlan")
	public void Validate_CartPageFin24MonWithOtherVal(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

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
		sfdc.selectAddOn.validateLongDistancePlans(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,
				InputData_WA.WACC_AddOn_Availability);
		sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
		sfdc.selectAddOn.clickOnContinueToDevice();

		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand,
				InputData_WA.WACC_DeviceName, "PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();

		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();

		sfdc.commPBFShopCart.verifyFieldDisplayed("Shopping Cart Page Header", sf.comPBFShopCart.shoppingCartHeader);
		sfdc.commPBFShopCart.verifyFieldDisplayed("Shopping Cart Page Hide Option", sf.shopCartObj.device36FinHideDetails);
//		sfdc.shopCart.validate_Finance_Plan(DeviceFinancePlan.FINANCING24MONTH.getDeviceFinancePlan());
		
		
		
		
		




		
		


		//		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);
		//		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();

		/*
		 * reachTillCreateQuotePage(); 
		 * reachTillShoppingCartPage();
		 */
	}

	// Method is for common navigation for all test cases
	public void reachTillCreateQuotePage() throws Exception {
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


	}

	public void reachTillShoppingCartPage() throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();

		sfdc.selectPro.verifyWirelessProducts();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size );
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.validateLongDistancePlans(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,
				InputData_WA.WACC_AddOn_Availability);
		sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
				"PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.select_Browse_Accessories();
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
	}

	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}


}
