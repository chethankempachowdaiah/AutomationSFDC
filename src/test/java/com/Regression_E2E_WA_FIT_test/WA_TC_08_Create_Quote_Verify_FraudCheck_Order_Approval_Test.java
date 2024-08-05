package com.Regression_E2E_WA_FIT_test;

import java.io.IOException;
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

public class WA_TC_08_Create_Quote_Verify_FraudCheck_Order_Approval_Test extends BaseDataProvider {
	/**
	 * @author Sakshi.Lnu
	 * @Date 05-May-2021
	 ** @param dataTable
	 * @throws Exception
	 * 
	 */
	SFDC_AllPages sfdc = new SFDC_AllPages();

	@BeforeMethod()
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
	    // use u.s.a account... not canada
		sfdc.accDetails.searchBusAccGlobalSearch(InputData_WA.account_Business_R4B);
		sfdc.accRelated.createOpportunity();
//		sfdc.cOpp.enterWirelessOpportunityDetails();
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
		
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,"PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
//		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
//		sfdc.selectPro.clicknAddToCartBtnForDP();
		
		/*if (!InputData_WA.env.contains("PROD")) {
			sfdc.selectAddOn.continueByodDeviceAndClickOnShopCart();
		} else {
			sfdc.selectAddOn.clickOnShopCart();
		}*/
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
//		sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price,
//				InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price);
		sfdc.shopCart.updateCartQtyPlans("4");
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
		sfdc.login.loginToSFDC(InputData.userid_fraudOps);
		sfdc.home.closeTabIfOpenWithRefresh();
		sfdc.quotes.searchGlobalSearchWithFraudNCreditCheckPro(dataInput.quoteName);
		sfdc.r4Bquoteapp.approveR4BQuote();
		sfdc.home.logout();
		
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpenWithRefresh();
		sfdc.quotes.searchGlobalSearchWithFraudNCreditCheckPro(dataInput.quoteName);
		sfdc.quoteDetails.verifyQuoteStatusAfterFraudCheck(Global.dataInput.quoteStatusFinalized,
				Global.dataInput.quoteStatusApproved);
		
		/*
		 *Community Part for process the order
		 *
		 */
		
	/*	if (InputData_WA.env.contains("WA")) {
			sfdc.comLogin.loginToWaccCommunities();
		} else {
			sfdc.comLogin.loginToCommunities();
		}
	   // sfdc.comLogin.loginToWaccCommunities();
		sf.seleU.hardwait(5000);
		sfdc.comMyBusCases.verifyMyBusniessOrders();
		sfdc.comOrderDetails.clickOnIncompleteOrderNumber();
		sfdc.comOrdFlow.selectRequestToPortOrNewNumber(InputData_WA.WACC_requestTypeForNumber);
		sfdc.comOrdFlow.provideCustomerInformationAndSubmitOrder(InputData_WA.WACC_requestTypeForNumber,
				InputData_WA.WACC_DeviceType);
		sfdc.comLogin.logoutFromCommunities();
		sfdc.login.loginToSFDCInNewTab(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.orders.selectOrder();
		sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(Global.dataInput.orderStatusInProgress);
		sfdc.home.closeTabIfOpen();*/
		
	}
	
	
	
	@AfterMethod()
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
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET_R4BPreFIT);
	}


}

