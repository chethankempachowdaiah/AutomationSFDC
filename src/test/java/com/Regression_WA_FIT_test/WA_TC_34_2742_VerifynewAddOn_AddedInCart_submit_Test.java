package com.Regression_WA_FIT_test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.framework.base.Base;
import com.framework.base.BaseDataProvider;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Satish.Doraiswamy
 * Feb. 28, 2022
 *  MP26
 *Test Case is for US WACC-2742
 */

public class WA_TC_34_2742_VerifynewAddOn_AddedInCart_submit_Test extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;


	@Test(dataProvider = "getDataPlan")
	public void Validate_newAddon_AddedCart_Submit(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		//sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		sfdc.accDetails.searchBusAccGlobalSearch(InputData_WA.account_Business_R4B);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity(InputData_WA.contact_Business_R4B);
		sfdc.cQuote.wait_Till_Load_CreateQuote_Button();
		sfdc.cQuote.clickCreateQuotePbfButton_FIT();
		sfdc.selectPro.verifyWirelessProducts();

		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,
				InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		// String []addOnType = {"Long Distance", "Roaming", "SMS", "Voicemail"};
		HashMap<String, Integer> addOnMap = new HashMap<String, Integer>() {
			{
				put("US LD", 15);
				put("International LD Saver", 7);
				put("Canada + US", 20);
				put("Unlimited Canada to US/Intl SMS/MMS", 5);
				put("Premium Voicemail to Text", 7);
				put("iPhone Visual Voicemail", 7);
			}
		};
		sfdc.selectAddOn.verify_ListofAddOn(addOnMap);
		// sfdc.selectAddOn.validateLongDistancePlans(InputData_WA.WACC_AddOn_Type,InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		// sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);

		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
				"PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();

		sfdc.selectPro.select_Browse_Accessories();
		sfdc.bAccessories.resetAccessoriesFilter();
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories",InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();

		sfdc.bAccessories.clickProceedToShoppingCartBtn();

		// Verify cart details
		sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price,
				InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price);
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 1);
		sfdc.shopCart.verify_AccessoryName_Price(InputData_WA.WACC_AccessoryName,InputData_WA.WACC_AccessoryCost, 1);
		sfdc.shopCart.validate_DPName_Price(InputData_WA.WACC_DeviceProtectionName,
				InputData_WA.WACC_DeviceProtectionCost, 1);

		sfdc.shopCart.clickProceedToCheckoutBtn();

		sfdc.reOrder.validateTermsAndConditions();
		sfdc.reOrder.acceptQuoteOptions();
		sfdc.genDoc.generateDocuement(InputData.quoteNumber);
		sfdc.quoteDetails.verifyQuoteNumberInQuotePage();
		sfdc.quoteDetails.verifyQuoteStatusWACC(InputData_WA.WACC_quoteStatus);

		if (InputData_WA.WACC_eSignature.equals("Yes")) {
			sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(InputData_WA.siteContactEmailId, "Signed");
			sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(Global.dataInput.finalisedStatus,
					Global.dataInput.signedStatus);
			// sf.seleU.hardwait(600000);
			sfdc.quoteRelated.getOrderNumberFromQuoteRelatedTab(Global.dataInput.orderNumber);

		} else {
			sfdc.quoteRelated.getOrderNumberFromQuoteRelatedTab(Global.dataInput.orderNumber);
		}

		sfdc.quoteRelated.clickOnOrderNoFromQuote();
		sf.seleU.clickElementByJSE(sf.qr.orderNameLinkInInQuoteRelated1);
		sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(Global.dataInput.orderStatusReadyToSubmit);
	}


	
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET_R4BPreFIT);
	}

}
