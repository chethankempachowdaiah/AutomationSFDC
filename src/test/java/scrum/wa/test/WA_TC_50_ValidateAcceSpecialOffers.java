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

public class WA_TC_50_ValidateAcceSpecialOffers extends MyListener {

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-1589 
	 *                              Validate Accessories special offer tile with text and prices
	 *                              and US WACC-2295 validating DP tile when single DP option is present
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_AcceDiscountandOriginalPrice(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillShopAccessoriesPage("Samsung", "Samsung Galaxy S21 Ultra 5G");
		sfdc.bAccessories.validate_originalandDiscPricePresentAccessoriesPage();
		sfdc.bAccessories.validate_specialOfferColorandText();
	
	}
	
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
		sfdc.selectPro.selectShopeWirelessDevices();
		sfdc.shopWADevcs.selectWirelessDevice(deviceBrand, deviceModel, "PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sf.seleU.waitTillLoading();
		
		//below lines for US-2295
		//validating when Single DP is present
		sfdc.selectPro.Validate_DP_multiDP_singleDP();
		sfdc.selectPro.validate_DP_Caption("singleDP");
		//till here
		
		sfdc.shopWADevcs.skipWithAddingDevceProtetcion();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,
				InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,
				InputData_WA.WACC_AddOn_Availability);
//		sfdc.selectAddOn.validateLongDistancePlans("Long Distance", "US LD", "No Offer");
//		sfdc.selectAddOn.clickOnAddToCartAddOns();
		sf.seleU.wait(6000);
		sf.seleU.waitTillLoading();
		sfdc.selectPro.select_Browse_Accessories();
		 

	}
	
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}

}
