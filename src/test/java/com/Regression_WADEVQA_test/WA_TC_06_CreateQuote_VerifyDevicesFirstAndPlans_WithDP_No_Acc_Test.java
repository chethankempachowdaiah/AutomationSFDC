package com.Regression_WADEVQA_test;

import java.io.IOException;
import java.util.Hashtable;

import org.apache.commons.codec.binary.Base64;
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

public class WA_TC_06_CreateQuote_VerifyDevicesFirstAndPlans_WithDP_No_Acc_Test extends BaseDataProvider {
	/**
	 * @author Sakshi.Lnu
	 * @Date 05-May-2021
	 ** @param dataTable
	 * @throws Exception
	 * 
	 */
	SFDC_AllPages sfdc = new SFDC_AllPages();

	//@BeforeMethod()
	public void launchBrowserAndLogin() throws Exception {
//		byte[] encodedBytes = Base64.encodeBase64("Soujanya@2405".getBytes());
//		String encodedString = new String(encodedBytes);
//		System.out.println(encodedString);
		sfdc = new SFDC_AllPages();
		test = reports.createTest(this.getClass().getName());
		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		// sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
	}

	@Test(dataProvider = "getDataPlan")
	public void CreateQuote_DeviceFirst_NoDP_NoAcc(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		InputData_WA.setDataForWACCProducts(dataTable);
		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		//sfdc.accDetails.searchAccountFromGlobSearch(InputData_WA.account_Business_R4B, "Business");
		sfdc.accDetails.searchBusAccGlobalSearch(InputData_WA.account_Business_R4B);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterWirelessOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity(InputData_WA.contact_Business_R4B);
		sfdc.cQuote.clickCreateQuotePbfButton();
		sfdc.selectPro.selectShopeWirelessDevices();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
				"PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		/*
		 * if (InputData_WA.WACC_AddOn_Status.equalsIgnoreCase("Yes")) { if
		 * (InputData_WA.WACC_AddOn_Type.equalsIgnoreCase("Long Distance")) {
		 * sf.seleU.hardwait(4000);
		 * sfdc.selectAddOn.validateLongDistancePlans("Long Distance", "US LD",
		 * "No Offer"); } else if (InputData_WA.WACC_AddOn_Type.equalsIgnoreCase("SMS"))
		 * { sf.seleU.hardwait(4000); sfdc.selectAddOn.expandAddOnsWindow();
		 * sfdc.selectAddOn.validateSMSPlans(); } else if
		 * (InputData_WA.WACC_AddOn_Type.equalsIgnoreCase("Roaming")) {
		 * sf.seleU.hardwait(4000); sfdc.selectAddOn.expandAddOnsWindow();
		 * sfdc.selectAddOn.selectRoamingPlans(); }else if
		 * (InputData_WA.WACC_AddOn_Type.equalsIgnoreCase("Voicemail")) {
		 * sf.seleU.hardwait(4000); sfdc.selectAddOn.expandAddOnsWindow();
		 * sfdc.selectAddOn.validateVoicemailPlans(); }
		 * sfdc.selectAddOn.clickOnAddToCartAddOns(); }
		 */
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		//sfdc.selectAddOn.clickOnShopCart();
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		//sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price,InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price);
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_Plan_Type, InputData_WA.WACC_Plan_Price, 1);
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price, 1);
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 1);
		sfdc.shopCart.validate_DPName_Price(InputData_WA.WACC_DeviceProtectionName,InputData_WA.WACC_DeviceProtectionCost, 1);
		sfdc.shopCart.clickProceedToCheckoutBtn();
		sfdc.reOrder.acceptQuoteOptions();
		sfdc.genDoc.generateDocuement(InputData.quoteNumber);
		sfdc.quoteDetails.verifyQuoteNumberInQuotePage();
		
		if (InputData_WA.WACC_eSignature.equals("Yes")) {
			sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(InputData_WA.siteContactEmailId, "Signed");
			for (int i = 0; i< 5; i++) {
				sf.seleU.hardwait(60000);
			}
			
			sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(Global.dataInput.awaitingSign,
					Global.dataInput.signedStatus);
			sfdc.quoteDetails.verifyQuoteStatusWACC(InputData_WA.WACC_quoteStatus);

		} else {
			sfdc.quoteDetails.verifyQuoteStatusWACC(InputData_WA.WACC_quoteStatus);
		}
//		Global.dataInput.orderNumber = "00085919";
		sfdc.quoteRelated.getOrderNumberFromQuoteRelatedTab(Global.dataInput.orderNumber);
		String language=InputData_WA.language;
		if(language==null || language.equals("") || language.equalsIgnoreCase("English")) {
			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(Global.dataInput.orderStatusReadyToSubmit);
		}
		else if(language.equalsIgnoreCase("French")){
			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(Global.dataInput.orderStatusReadyToSubmitFr);			
		}

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		if (dataInput.env.contains("WA")) {
			sfdc.comLogin.loginToWaccCommunities();
		} else {
			sfdc.comLogin.loginToCommunities();
		}
		//sf.seleU.hardwait(60000);
		//sfdc.comMyBusCases.verifyMyBusniessOrders();
		sfdc.comOrderDetails.clickOnIncompleteOrderNumber();
		sfdc.comOrdFlow.selectRequestToPortOrNewNumber(InputData_WA.WACC_requestTypeForNumber);
		sfdc.comOrdFlow.provideCustomerInformationAndSubmitOrder(InputData_WA.WACC_requestTypeForNumber,
				InputData_WA.WACC_DeviceType);
		sfdc.comLogin.logoutFromCommunities();
		sfdc.login.loginToSFDCInNewTab(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.orders.selectOrder();
		if(language==null || language.equals("") || language.equalsIgnoreCase("English")) {
			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(Global.dataInput.orderStatusInProgress);
		}
		else if(language.equalsIgnoreCase("French")){
			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(Global.dataInput.orderStatusInProgressFr);		
		}
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
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}