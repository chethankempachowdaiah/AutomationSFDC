package scrum.wa.test;

import java.io.IOException;
//WA_TC_53_Validate_PortEligiblity_DuplicateCheck
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_53_Validate_PortEligiblity_DuplicateCheck extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	@Test(dataProvider = "getDataPlan")
	public void validate_AvailabilityTextOnAccessoryDetails(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		// reachTillShopAccessoriesPage();

		reachTillCreateQuotePage();
		reachTillShoppingCartPage();

		//sfdc.shopCart.updateCartQtyPlans("2");
		sfdc.shopCart.validateFlatDiscountQuoteText_Price("Flat discounting applied ($12.50/line)");
		sfdc.shopCart.clickProceedToCheckoutBtn();
		// Validate Flat discount on Order summary page.
		sfdc.shopCart.validateFlatDiscountQuoteText_Price("Flat discounting applied ($12.50/line)");
		sfdc.reOrder.validateTermsAndConditions();
		sfdc.reOrder.acceptQuoteOptions();
		sfdc.genDoc.generateDocuement(InputData.quoteNumber);
		sfdc.quoteDetails.verifyQuoteNumberInQuotePage();
		sfdc.quoteDetails.verifyQuoteStatusWACC(InputData_WA.WACC_quoteStatus);

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
		sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(Global.dataInput.orderStatusReadyToSubmit);
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		if (InputData_WA.env.equalsIgnoreCase("WADEVQA") || InputData_WA.env.equalsIgnoreCase("PREFIT")) {
			sfdc.comLogin.loginToWaccCommunities();
		} else {
			sfdc.comLogin.loginToCommunities();
		}

		// Global.dataInput.orderNumber = "00083697";

		// sf.seleU.hardwait(60000); // sfdc.comMyBusCases.verifyMyBusniessOrders();
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
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,
				InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.validateLongDistancePlans(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,
				InputData_WA.WACC_AddOn_Availability);
		sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
				"PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();

		sfdc.selectPro.select_Browse_Accessories();
	}

	public void reachTillCreateQuotePage() throws Exception {
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

	}

	public void reachTillShoppingCartPage() throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();

		sfdc.selectPro.verifyWirelessProducts();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,
				InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.validateLongDistancePlans(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,
				InputData_WA.WACC_AddOn_Availability);
		sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
				"PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();

		sfdc.selectPro.select_Browse_Accessories();
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);
		//sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();

		sfdc.bAccessories.clickProceedToShoppingCartBtn();

	}

	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}
