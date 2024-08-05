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

public class WA_TC_04_CreateQuote_VerifyDevicesFirstAndPlans_NoDP_No_Acc_Test extends BaseDataProvider {
	/**
	 * @author Sakshi.Lnu
	 * @Date 05-May-2021
	 ** 
	 * @param dataTable
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
	public void CreateQuote_verifyPlansWithDev_NoDP_NoAcc(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		 InputData_WA.setDataForWACCProducts(dataTable);

		sfdc.accDetails.searchBusAccGlobalSearch(waData.account_Business_R4B);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity(InputData_WA.contact_Business_R4B);
		sfdc.cQuote.wait_Till_Load_CreateQuote_Button();
		sfdc.cQuote.clickCreateQuotePbfButton_FIT();
		sfdc.selectPro.selectShopeWirelessDevices();
		// sfdc.selectPro.verifyBottomLinks();

		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
				"PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		// skip the protection
		sfdc.selectPro.skipDeviceProtection();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size );
		sf.seleU.hardwait(120000);
		sfdc.selectPro.continueToAddOnsButton();
		sf.seleU.hardwait(120000);
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
			}else if (InputData_WA.WACC_AddOn_Type.equalsIgnoreCase("Voicemail")) {
				sf.seleU.hardwait(4000);
				sfdc.selectAddOn.expandAddOnsWindow();
				sfdc.selectAddOn.validateVoicemailPlans();
			} 
			sfdc.selectAddOn.clickOnAddToCartAddOns();
		}
		sfdc.selectAddOn.clickOnShopCart();
		sfdc.shopCart.clickProceedToCheckoutBtn();
		sfdc.reOrder.acceptQuoteOptions();
		sfdc.genDoc.generateDocuement(InputData.quoteNumber);
		sfdc.quoteDetails.verifyQuoteNumberInQuotePage();

		if (InputData_WA.WACC_eSignature.equals("Yes")) {
			sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(InputData_WA.siteContactEmailId, "Signed");
			sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(Global.dataInput.awaitingSign,
					Global.dataInput.signedStatus);
			// sf.seleU.hardwait(600000);
			sfdc.quoteDetails.verifyQuoteStatusWACC(InputData_WA.WACC_quoteStatus);

		} else {
			sfdc.quoteDetails.verifyQuoteStatusWACC(InputData_WA.WACC_quoteStatus);
		}
		sfdc.quoteRelated.getOrderNumberFromQuoteRelatedTab(Global.dataInput.orderNumber);
		//sfdc.quoteRelated.clickOnOrderNoFromQuote();
		//sf.seleU.clickElementByJSE(sf.qr.orderNameLinkInInQuoteRelated1);
		sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(Global.dataInput.orderStatusReadyToSubmit);

		sfdc.home.closeTabIfOpen(); 
		sfdc.home.logout();
		//Global.dataInput.orderNumber = "00118165";
		if (InputData_WA.env.contains("WA")) {
			sfdc.comLogin.loginToWaccCommunities();
		} else {
			sfdc.comLogin.loginToCommunities();
		}
		//sf.seleU.hardwait(60000);
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
		sfdc.home.closeTabIfOpen();
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
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET_R4BPreFIT);
	}
}