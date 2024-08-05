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

public class WA_TC_60_CreditsInShoppingCartWithDevices_Test extends MyListener{

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	// Test Case is for US WACC-2285
	@Test(dataProvider = "getDataPlan")
	public void validate_CorrectCreditsPresent_UsingRegularDevice(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		reachTillContinueToAddons();
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName, "PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.shopCart.click_ProceedToShopCartBtn();
		
		//select credit on shopping cart page and click apply offer
		sfdc.shopCart.selectCreditAvailable(InputData_WA.WACC_Use_Credit_Option);
		//sfdc.shopCart.getPostTaxCreditIfAvailable();
		sf.seleU.waitTillLoading();
		sfdc.shopCart.clickProceedToCheckoutBtn();
		
		//select PDF copy button on summary page
		sfdc.reOrder.select_SendPDFCopy_button();
		sf.seleU.waitTillLoading();
		sfdc.reOrder.select_BackToReviewOrder_button();
		sf.seleU.waitTillLoading();
		sfdc.reOrder.clickCancelAndBackButton();
		
		//update cart quantity on shopping cart page
		sfdc.shopCart.updateCartQtyPlans("2");
		sfdc.shopCart.clickProceedToCheckoutBtn();
		
		//select PDF copy button on summary page after updating cart quantity
		sfdc.reOrder.select_SendPDFCopy_button();
		
	}	
	
	// Test Case is for US WACC-2285
	@Test(dataProvider = "getDataPlan")
	public void validate_CorrectCreditsPresent_UsingBYODDevice(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		reachTillContinueToAddons();
		//Select BYOD Device
		sfdc.selectAddOn.select_ContinueBYOD();
		sfdc.selectAddOn.validateBYODContinueButton();
		sfdc.shopCart.click_ProceedToShopCartBtn();
		
		//select credit on shopping cart page and click apply offer
		sfdc.shopCart.selectCreditAvailable(InputData_WA.WACC_Use_Credit_Option);
		//sfdc.shopCart.getPostTaxCreditIfAvailable();
		sf.seleU.waitTillLoading();
		sfdc.shopCart.clickProceedToCheckoutBtn();
		
		//select PDF copy button on summary page
		sfdc.reOrder.select_SendPDFCopy_button();
		sf.seleU.waitTillLoading();
		sfdc.reOrder.select_BackToReviewOrder_button();
		sf.seleU.waitTillLoading();
		sfdc.reOrder.clickCancelAndBackButton();
		
		//update cart quantity on shopping cart page
		sfdc.shopCart.updateCartQtyPlans("2");
		sfdc.shopCart.clickProceedToCheckoutBtn();
		
		//select PDF copy button on summary page after updating cart quantity
		sfdc.reOrder.select_SendPDFCopy_button();
		
	}		
		
	//Method is for common navigation for all test cases
	public void reachTillContinueToAddons() throws Exception {
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
		sfdc.selectPro.clickOnPlansAddToCart();
		sfdc.selectPro.continueToAddOnsButton();
	}	
	
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}
