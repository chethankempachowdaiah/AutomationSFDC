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

public class WA_TC_30_VerifyStockAvailabilityOnAccessoriesDetailsPageTest extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	// Test case is for US WACC-1972
	@Test(dataProvider = "getDataPlan")
	public void validate_AvailabilityTextOnAccessoryDetails(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillShopAccessoriesPage();

		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.verifyAccessoriesAvailability();
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.accessoryDetails.select_BackToAccessoriesLink();
		
		sfdc.bAccessories.clickOnViewDetailsBtn("Cases & screen protectors", "Symmetry Protective Case for Samsung Galaxy S20 Ultra");
		sfdc.accessoryDetails.verifyAccessoriesAvailability();
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.accessoryDetails.select_BackToAccessoriesLink();
		
		sfdc.bAccessories.clickOnViewDetailsBtn("Cases & screen protectors", "Defender Protective Case for Samsung Galaxy A20");
		sfdc.accessoryDetails.verifyAccessoriesAvailability();
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.accessoryDetails.select_BackToAccessoriesLink();
		
		sfdc.bAccessories.clickOnViewDetailsBtn("Power and cables","Car Charger 2.4A with Single USB Port");
		sfdc.accessoryDetails.verifyAccessoriesAvailability();
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
	}

	// Method is for common navigation for all test cases
	public void reachTillShopAccessoriesPage() throws Exception {
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
		sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
				"PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();

		sfdc.selectPro.select_Browse_Accessories();
	}

	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}
