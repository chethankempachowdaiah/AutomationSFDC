package scrum.wa.test;

import java.io.IOException;
import java.util.Hashtable;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_66_RestrictionOnPortInFromRogersNumber_Test extends MyListener {

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-2938 
	 *                              Validate roger's port in numbers, 
	 *                              it should not accept roger's wireless number to port in
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_RogersPortInNumbersWireless(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillShopAccessoriesPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		sfdc.shopCart.updateCartQtyPlans("6");
		sf.seleU.wait(6000);
		sfdc.shopCart.clickProceedToCheckoutBtn();
		sf.seleU.wait(5000);
		//sfdc.reOrder.acceptQuoteOptions();
		sf.seleU.clickElementByJSE(sf.revOrderObj.verbalQuoteAcceptOption);
		sf.seleU.wait(3000);
		sf.seleU.clickElementByJSE(sf.revOrderObj.placeOrderButton);
		//sfdc.genDoc.generateDocuement(InputData.quoteNumber);
		sf.seleU.hardwait(10000);
		sf.seleU.waitElementToBeVisible(sf.genDocObj.confirmOrder);
		sf.seleU.clickElementByJSE(sf.genDocObj.confirmOrder);
		sf.seleU.hardwait(10000);
		sf.seleU.clickElementByJSE(sf.genDocObj.doneButton);
		sf.seleU.hardwait(4000);
		sfdc.quoteDetails.verifyQuoteNumberInQuotePage();

		if (InputData_WA.WACC_eSignature.equals("Yes")) {
			sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(InputData_WA.siteContactEmailId, "Signed");
			sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(Global.dataInput.awaitingSign,
					Global.dataInput.signedStatus);
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
		sfdc.comOrderDetails.clickOnIncompleteOrderNumber();
		sfdc.reOrder.enterMultipleTransferNum("416901");
		sfdc.reOrder.checkRestrictionPortInRogers();
		sfdc.comLogin.logoutFromCommunities();
	}
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-2938 
	 *                              Validate roger's port in numbers, 
	 *                              it should not accept roger's landline number to port in
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_RogersPortInNumbersLandline(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillShopAccessoriesPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		sfdc.shopCart.updateCartQtyPlans("6");
		sf.seleU.wait(6000);
		sfdc.shopCart.clickProceedToCheckoutBtn();
		sf.seleU.wait(5000);
		//sfdc.reOrder.acceptQuoteOptions();
		sf.seleU.clickElementByJSE(sf.revOrderObj.verbalQuoteAcceptOption);
		sf.seleU.wait(3000);
		sf.seleU.clickElementByJSE(sf.revOrderObj.placeOrderButton);
		//sfdc.genDoc.generateDocuement(InputData.quoteNumber);
		sf.seleU.hardwait(10000);
		sf.seleU.waitElementToBeVisible(sf.genDocObj.confirmOrder);
		sf.seleU.clickElementByJSE(sf.genDocObj.confirmOrder);
		sf.seleU.hardwait(10000);
		sf.seleU.clickElementByJSE(sf.genDocObj.doneButton);
		sf.seleU.hardwait(4000);
		sfdc.quoteDetails.verifyQuoteNumberInQuotePage();

		if (InputData_WA.WACC_eSignature.equals("Yes")) {
			sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(InputData_WA.siteContactEmailId, "Signed");
			sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(Global.dataInput.awaitingSign,
					Global.dataInput.signedStatus);
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
		sfdc.comOrderDetails.clickOnIncompleteOrderNumber();
		sfdc.reOrder.enterMultipleTransferNum("416319");
		sfdc.reOrder.checkRestrictionPortInRogers();
		sfdc.comLogin.logoutFromCommunities();
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
		sfdc.shopWADevcs.skipWithAddingDevceProtetcion();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,
				InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,
				InputData_WA.WACC_AddOn_Availability);
		sf.seleU.wait(6000);
		sf.seleU.waitTillLoading();
		
	}
	
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}

}

