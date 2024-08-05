package com.Regression_WA_FIT_test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.BaseDataProvider;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;

public class WA_TC_59305_CreateQuote_ESign_Valid_Resume_Denied_Order_Test extends BaseDataProvider {
	/**
	 *
	 * In this Test Script covering Device with Plan and verifying E-sign 
	 * functionality.
	 * 
	 ** @param dataTable
	 * @throws Exception
	 * 
	 */
	SFDC_AllPages sfdc = new SFDC_AllPages();


	@Test(dataProvider = "getDataPlan")
	public void verifyQuoteESignDeclineSce(Hashtable<String, String> dataTable,Method method) throws Exception {
		sfdc = new SFDC_AllPages();
		InputData_WA.setDataForWACCProducts(dataTable);
		test = reports.createTest(this.getClass().getName()+"_"+method.getName());
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
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
				"PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		sfdc.shopCart.clickProceedToCheckoutBtn();
		sfdc.reOrder.acceptQuoteOptions();
		sfdc.genDoc.generateDocuement(InputData.quoteNumber);
		sfdc.quoteDetails.verifyQuoteNumberInQuotePage();
		

		if (InputData_WA.WACC_eSignature.equals("Yes")) {
			sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator_New(InputData_WA.siteContactEmailId,Global.dataInput.deniedStatus);
			sfdc.orderDetails.verifyQuoteStatusForESignOrdersWithGracePeriod(Global.dataInput.awaitingSign,
					InputData_WA.account_Business_R4B);
			sfdc.quoteDetails.clickOnQuoteTab_DeclineSce(InputData_WA.account_Business_R4B);
			sfdc.quoteDetails.verifyQuoteStatusWACC(Global.dataInput.deniedStatus);
		} else {
			sfdc.quoteDetails.verifyQuoteStatusWACC(Global.dataInput.deniedStatus);
		}
		
		sfdc.quoteRelated.getOrderNumberFromQuoteRelatedTab(Global.dataInput.orderNumber);
		sfdc.quoteDetails.validateResumeQuoteAndOtherButtons();
		
		
		
		sfdc.home.logout();
		sfdc.home.closeTabIfOpen();
			
		}


	/**
	 * @return
	 * @throws IOException
	 * 
	 *             Data Provider to fetch multiple set of data and assign them to 2D
	 *             Object Array
	 */
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET_R4BPreFIT);
	}
}