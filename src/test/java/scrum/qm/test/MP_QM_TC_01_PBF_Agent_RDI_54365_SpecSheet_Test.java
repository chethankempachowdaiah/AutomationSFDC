package scrum.qm.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.BaseDataProvider;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla
 * 
 */
public class MP_QM_TC_01_PBF_Agent_RDI_54365_SpecSheet_Test extends BaseDataProvider {

	/**
	 * @throws Exception
	 */
	@Test(dataProvider = "PBFAgentData")
	public void test_validate_PBF_E2E_Agent_RDI(Hashtable<String, String> dataTable) throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		InputData_Communities.setDataForPBFE2E(dataTable);

//                      sfdc.login.loginToSFDC(InputData.Profile_AE);
//                      sfdc.home.openR4BSalesConsole();
//                      sfdc.home.closeTabIfOpen();
//
//                      sfdc.acc.createNewBusinessAccount();
//                      sfdc.cba.enterBusinessAccountInfo(false);
//                      sfdc.cba.verifyBusinessAccountCreated();
//
//                      sfdc.cc.enterCreateContactInfo(false);
//                      sfdc.cc.verifyMarkPrimaryContactReadOnly();
//                      sfdc.cc.verifyContactCreated();
//                      sfdc.cc.clickOnNextInCreateContact();
//
//                      sfdc.csa.enterServiceAccountInfo(1);
//                      sfdc.csa.clickOnNextInCreateServiceAccount();
//                      sfdc.csa.noBillingAccountClickOnNext();
//                      sfdc.home.closeTabIfOpen();
//                      sfdc.home.logout();
//
//                      sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
//                      sfdc.home.closeTabIfOpen();
//                      sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
//                      sfdc.accRelated.approveAsDataGovernance();
//                      sfdc.home.closeTabIfOpen();
//                      sfdc.home.logout();

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();

		// Create Quote BY PBF
		sfdc.cQuote.clickCreateQuotePbfButton();
		sfdc.siteSelPBF.moveToSiteSelectionPageForDedInt();
		sfdc.siteSelPBF.verifyPBFLandingPage();
		sfdc.siteSelPBF.verifyLegendSection();

		if (InputData_Communities.commPBFAddNewSite.equals("Yes")) {
			// Verify New Site added in PBF appears in related accounts in SF

			sfdc.commPBF.addNewSite();
			sfdc.commPBFAddSite.fillSiteInfo();
			sfdc.commPBFAddSite.verifyServiceAddressesTableLayout();
			sfdc.commPBFAddSite.selectAddress();

			sfdc.commPBFAddSite.confirmAndProceed();

		} else {

			sfdc.siteSelPBF.selectRDIOnNetAddress();

		}

		// Validate shopping Cart Page and Proceed
		sfdc.commPBFShopCart.validateShoppingCartPageAndProceed();

		// Click on Shop Plans for Business Internet
		sfdc.siteSelPBF.moveToSiteSelectionPageForDedInt();

		// Add to Cart a product and proceed to checkout
		sfdc.planSelPBF.addToCartRDIProductAndProceed();
		
		if (!InputData_Communities.commPBFEditEthernetPlan.equals("No"))
			sfdc.shopCartPBF.editEthernetPlanBackToOriginal();
		
		// Validate User lands on Shopping Cart Page with added product
		sfdc.commPBFShopCart.validateShoppingCartPageWithAddedRDIProduct();

		sfdc.planSelPBF.validate_RDI_TCV();

		// Proceed to checkout
		sfdc.commPBFShopCart.proceedToCheckout();

		// Validate Order Review Page
		sfdc.commPBFOrderReview.validateOrderReviewPageWithoutValues();

		// Add Site Contact
		if (InputData_Communities.commPBFAddSiteContact.equals("Yes"))
			sfdc.siteConPBF.addSiteContact();
		
		sfdc.commPBFOrderReview.confirmAndPlaceOrder();

		if (InputData_Communities.commPBFCreditCheckRequired.equals("Yes")) {

			// Validate Order Confirmation Screen
			sfdc.orConfPBF.validateOrderConfirmationScreenForCreditOrFraudCheckAcc();

			sfdc.home.logout();
			// Login with Credit Ops and approve credit check
			sfdc.login.loginToSFDC(InputData.Profile_CreditOps);
			sfdc.home.closeTabIfOpenWithRefresh();
			// sf.dataInput.quoteName = "00013178";
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			sfdc.quoteRelated.approveR4BQuoteForCreditCheck();
			sfdc.home.logout();

			sfdc.login.loginToSFDC(InputData.Profile_AE);
			sfdc.home.openR4BSalesConsole();
			sfdc.home.closeTabIfOpenWithRefresh();
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			sfdc.quoteDetails.verifyQuoteStatusAfterCreditCheck(sf.dataInput.quoteStatusFinalized,
					sf.dataInput.quoteStatusApproved);
			sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusReadyToSubmit);
			
			
		} else if (InputData_Communities.commPBFFraudCheckRequired.equals("Yes")) {

			// Validate Order Confirmation Screen
			sfdc.orConfPBF.validateOrderConfirmationScreenForCreditOrFraudCheckAcc();
			
			sfdc.home.logout();
			// Login with Fraud Ops and approve fraud check
			sfdc.login.navigateURL();
			// sf.dataInput.quoteName = "fraudCheckForPBF-00015592";
			sfdc.quoteDetails.checkCreditOrFraud(sf.dataInput.quoteName, false, "Approve");

			sfdc.login.loginToSFDC(InputData.Profile_AE);
			sfdc.home.openR4BSalesConsole();
			sfdc.home.closeTabIfOpenWithRefresh();
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			// sfdc.quotes.searchQuoteGlobalSearch("Aauto210319033924-00007728");
			sfdc.quoteDetails.verifyQuoteStatusAfterFraudCheck(sf.dataInput.quoteStatusFinalized,
					sf.dataInput.quoteStatusApproved);
			sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusReadyToSubmit);
			sfdc.orderDetails.verifyProductDetails();

		} else {
			// Validate Order Confirmation Screen
				sfdc.orConfPBF.validateOrderConfirmationScreenForRDI();

				// Go to Order Details
				sfdc.commPBFOrderConf.clickOnOrderNumber();
				// Update order with site contact if required
				sfdc.orders.updatePBFOrderWithSiteContact();
				sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusReadyToSubmit);
				sfdc.orderDetails.verifyProductDetails();
		}
		
		if(InputData_Communities.commPBFRDISpecSheetBillAddressSelect.equals("Yes"))
			sfdc.comReviewSpecSheet.selectBillingAddress();
		else
			sfdc.comReviewSpecSheet.selectNoBillingAddress();
		
		sfdc.comReviewSpecSheet.enterSpecSheetValues(true, dataTable);
		
		if(InputData_Communities.commPBFRDISpecSheetBillAddressSelect.equals("Yes"))
			sfdc.orderDetails.verifyBillingAddress();
		else
			sfdc.orderDetails.verifyNoBillingAddress();

	}

	/**
	 * @return
	 * @throws IOException
	 * 
	 *                     Data Provider to fetch multiple set of data and assign
	 *                     them to 2D Object Array
	 */
	@DataProvider
	public Object[][] PBFAgentData() throws IOException {
		return getDataSetsRunMode(Constants.TESTDATA_COMM_FILE, Constants.PBF_AGENT_SHEET_RDI_SPECSHEET);
	}
}
