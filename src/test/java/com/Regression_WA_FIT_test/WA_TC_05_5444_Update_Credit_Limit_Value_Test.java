package com.Regression_WA_FIT_test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.BaseDataProvider;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Satish.Doraiswamy
 *May 2, 2022
 * WACC-5444
 * Credit limit assigned does not update on the business account after credit user modifies the value on the credit approval record
 */
public class WA_TC_05_5444_Update_Credit_Limit_Value_Test extends BaseDataProvider {
	
	SFDC_AllPages sfdc = new SFDC_AllPages();


	@Test(dataProvider = "getDataPlan")
	public void CreateQuote_Credit_Check_BYOD(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		String totalCreditLimitAssignedValue=AdditionalUtilities.generateRandomDigit(4);
		test = reports.createTest(this.getClass().getName());
		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		// sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		
		 InputData_WA.setDataForWACCProducts(dataTable);
		 sfdc.accDetails.searchBusAccGlobalSearch(InputData.account_ATL);
		sfdc.accRelated.createOpportunity();
		sf.seleU.hardwait(4000);
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity(InputData_WA.contact_Business_R4B);
		sfdc.cQuote.wait_Till_Load_CreateQuote_Button();
		sfdc.cQuote.clickCreateQuotePbfButton_FIT();
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
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
	
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
		
		sf.seleU.hardwait(5000);
		sfdc.login.loginToSFDC(InputData.userid_creditOps);
		sfdc.home.closeTabIfOpenWithRefresh();
//		sf.dataInput.quoteName = "Sudha-00028754";
		sfdc.quotes.searchGlobalSearchWithFraudNCreditCheckPro(dataInput.quoteName);
		sfdc.quoteRelated.clickOnAppR4BQuoteForCreditCheck();
		sfdc.quoteDetails.editCreditLimitAssignedVale(totalCreditLimitAssignedValue);
		String actualTotalCreditLimitValaue=sfdc.accDetails.getTotalCreditLimitAssignedValue();
		sfdc.quoteRelated.approveQuoteForCreditCheck();
		sfdc.home.logout();
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(InputData.account_ATL);
		String expcetedTotalCreditLimitValaue=sfdc.accDetails.getTotalCreditLimitAssignedValue();
		Assert.assertEquals(actualTotalCreditLimitValaue, expcetedTotalCreditLimitValaue,"Actaul Value is "+actualTotalCreditLimitValaue+" "
				+ "and Expceted Value is "+expcetedTotalCreditLimitValaue+"");
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
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET_R4BPreFIT);
	}
}
