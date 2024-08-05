package com.Regression_WA_Prod_test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.BaseDataProvider;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;

public class WA_TC_02_Verify_CreditCheck_Order_Approval_Scrape_Test extends BaseDataProvider {
	/**
	 * @author Sakshi.Lnu
	 * @Date 05-May-2021
	 ** @param dataTable
	 * @throws Exception
	 * 
	 */
	SFDC_AllPages sfdc = new SFDC_AllPages();

	@BeforeTest()
	public void launchBrowserAndLogin() throws Exception {

		sfdc = new SFDC_AllPages();
		test = reports.createTest(this.getClass().getName());
		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		// sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
	}

	@Test(dataProvider = "getDataPlan")
	public void test_verifyWirelessPlansAndAddToCart(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		InputData_WA.setDataForWACCProducts(dataTable);
		sfdc.accDetails.searchAccountFromGlobSearch(InputData_WA.account_Business_R4B, "Business");
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterWirelessOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity(InputData_WA.contact_Business_R4B);
		sfdc.cQuote.clickCreateQuotePbfButton();
		sfdc.selectPro.verifyWirelessProducts();
		if (InputData_WA.WACC_Data_Type.equalsIgnoreCase("Voice and Data")) {
			sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,
					InputData_WA.WACC_Plan_Size);
		} else {
			sfdc.selectPro.validateDataPlans();
			sfdc.selectPro.clickOnPlansAddToCart();
		}
		sf.seleU.hardwait(5000);
		sfdc.selectPro.continueToAddOnsButton();
		sf.seleU.hardwait(5000);
		if (InputData_WA.WACC_AddOn_Status.equalsIgnoreCase("Yes")) {
			if (InputData_WA.WACC_AddOn_Type.equalsIgnoreCase("Long Distance")) {
				sf.seleU.hardwait(4000);
				sfdc.selectAddOn.validateLongDistancePlans("Long Distance", "US LD", "No Offer");
			} else if (InputData_WA.WACC_AddOn_Type.equalsIgnoreCase("SMS")) {
				sf.seleU.hardwait(4000);
				sfdc.selectAddOn.expandAddOnsWindow();
				sfdc.selectAddOn.validateSMSPlans();
			} else if (InputData_WA.WACC_AddOn_Type.equalsIgnoreCase("Roaming")) {
				sf.seleU.hardwait(4000);
				sfdc.selectAddOn.expandAddOnsWindow();
				sfdc.selectAddOn.selectRoamingPlans();
			} else if (InputData_WA.WACC_AddOn_Type.equalsIgnoreCase("Voicemail")) {
				sf.seleU.hardwait(4000);
				sfdc.selectAddOn.expandAddOnsWindow();
				sfdc.selectAddOn.validateVoicemailPlans();
			}
			sfdc.selectAddOn.clickOnAddToCartAddOns();
		}
		if (!InputData_WA.env.contains("PROD")) {
			sfdc.selectAddOn.continueByodDeviceAndClickOnShopCart();
		} else {
			sfdc.selectAddOn.clickOnShopCart();
		}
		sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price,
				InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price);
		sfdc.shopCart.clickProceedToCheckoutBtn();
		sfdc.reOrder.acceptQuoteOptions();
		sfdc.genDoc.generateDocuement(InputData.quoteNumber);
		sf.seleU.hardwait(5000);

		//get quote number and name		
		sfdc.quoteDetails.verifyQuoteNumberInQuotePage();		
		//In progress
		sfdc.quoteDetails.verifyQuoteStatusWACC("Accepted");
		// login with CreditOps
		sfdc.home.logout();
		sfdc.login.loginToSFDC(InputData.Profile_CreditOps);
		sfdc.home.closeTabIfOpenWithRefresh();
		// sf.dataInput.quoteName = "Sakshi_Account-00011543";
		sfdc.quotes.searchQuoteGlobalSearch(dataInput.quoteName);
		sfdc.quoteRelated.approveR4BQuoteForCreditCheck();
		sfdc.home.logout();
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.quotes.searchQuoteGlobalSearch(dataInput.quoteName);
		sfdc.quoteDetails.verifyQuoteStatusAfterCreditCheck(Global.dataInput.quoteStatusFinalized,
				Global.dataInput.quoteStatusApproved);
	}
	@AfterTest()
	public void logout() throws IOException {
		sfdc.home.logout();
		softassert.assertAll();
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
		// return getDataSetsRunMode(Constants.WA_TESTDATA_FILE,
		// Constants.WIRELESS_PLANS_SELECTION_SHEET_PreProd);
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}
