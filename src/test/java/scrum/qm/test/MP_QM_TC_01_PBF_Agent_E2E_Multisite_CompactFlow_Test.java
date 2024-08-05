package scrum.qm.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.BaseDataProvider;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla
 * 
 */
public class MP_QM_TC_01_PBF_Agent_E2E_Multisite_CompactFlow_Test extends BaseDataProvider {

	/**
	 * @throws Exception
	 */
	@Test(dataProvider = "PBFAgentData")
	public void test_validate_PBF_E2E_Agent_Multisite(Hashtable<String, String> dataTable) throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		InputData_Communities.setDataForMultisitePBFE2E(dataTable);
		sfdc.mulSiteSelPBF.getAddresses();
		
		sfdc.login.loginToSFDC(InputData.Profile_AE);
//		sfdc.home.closeTabIfOpen();
//		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
//		sfdc.accRelated.createOpportunity();
//		sfdc.cOpp.enterOpportunityDetails();
//		sfdc.cOpp.selecetExistingContactInOpportunity();
//
//		// Create Quote BY PBF
//		sfdc.cQuote.clickCreateQuotePbfButton();
//		sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();
//		sfdc.mulSiteSelPBF.verifyMulSiteSelPage();
//		sfdc.siteSelPBF.verifyLegendSection();

		//Select Multiple Sites for adding products
		if (!InputData_Communities.mulPBFAddNewSite.equals("No")) 
			sfdc.mulSiteSelPBF.addOrSelectMultiSites();
		else
			sfdc.mulSiteSelPBF.selectMultiSites();

		// Validate shopping Cart Page and Proceed
		sfdc.mulPBFShopCart.validateShoppingCartWithMultipleSites();

		// Select multiple combinations of products for multiple sites
		sfdc.mulPBFShopCart.addMultipleProductsToCartForMultipleSites();
	 	
		// Validate Shopping cart with Added products for all sites
		sfdc.mulPBFShopCart.validateShoppingCartPageWithAddedProductsForAllSites();
	
//		// Validate waiveoff installation
//		sfdc.mulPBFShopCart.testWaiveOffInstallationFeesForMulSite();
//	
//			// Add installation fees again
//		sfdc.mulPBFShopCart.testAddInstallationFeesForMulSite();
//		
		if (InputData_Communities.mulPBFFraudCheckRequired.equalsIgnoreCase("No"))
				sfdc.planSelPBF.checkMonthlyTotalForFraudReview();	
		
		// Proceed to checkout
		sfdc.commPBFShopCart.proceedToCheckout();

		// Validate Order Review Page
		sfdc.mulSiteOrderReview.validateOrderReviewPageWithoutValues();
		
		//Validate Order Review Page with added sites
		sfdc.mulSiteOrderReview.validateOrderReviewPageWithMultipleSites();
		
		sfdc.mulSiteOrderReview.validatOrderReviewPageWithAddedProductsForAllSites();		
		
		// Add Site Contact
		if (InputData_Communities.mulPBFAddSiteContact.equals("Yes"))
			sfdc.mulSiteOrderReview.addSiteContact();

		// Validate Order Summary PDF
		sfdc.commPBFOrderReview.verifyOrderSummaryPDF();

		// Validate Cancel ANd Back redirection
		sfdc.commPBFOrderReview.verifyCancelAndBackRedirection();

		// Verify terms and conditions and Accept
		sfdc.mulSiteOrderReview.verifyTermsAndCondtionsAndAccept();

		sfdc.orRevPBF.verifyESigSection();

		// Confirm Order
		if (InputData_Communities.mulPBFESignature.equalsIgnoreCase("No"))
			sfdc.commPBFOrderReview.confirmAndPlaceOrder();
		else {
			sfdc.orRevPBF.sendQuoteForESigApproval();
			sfdc.gdPdf.generatePDFNextButton();
//
//			if (InputData_Communities.commPBFAddSiteContact.equalsIgnoreCase("No"))
//				sfdc.siteCon.selectExistingSiteContact();
//			else
//				sfdc.siteCon.verifySelectedContact();
//
//			sfdc.orderDetails.verifyContractAndQuoteDetails(sf.dataInput.awaitingSign, sf.dataInput.awaitingSign, "",
//					"");
//			if (InputData_Communities.commPBFESignature.equalsIgnoreCase("Accept")) {
//
//				sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(sf.dataInput.siteContactEmailId, "Signed");
//				sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(sf.dataInput.finalisedStatus,
//						sf.dataInput.signedStatus);
//				//
//
//			} else {
//				sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(sf.dataInput.siteContactEmailId, "Reject");
//				sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(sf.dataInput.finalisedStatus,
//						sf.dataInput.declinedStatus);
//
//			}
//			sfdc.quoteDetails.verifyQuoteNumberInQuotePage();
		}
//
//		if (InputData_Communities.commPBFCreditCheckRequired.equals("Yes")) {
//
//			if (InputData_Communities.commPBFESignature.equalsIgnoreCase("No")) {
//
//				// Validate Order Confirmation Screen
//				sfdc.orConfPBF.validateOrderConfirmationScreenForCreditOrFraudCheckAcc();
//
//			}
//
//			sfdc.home.logout();
//			// Login with Credit Ops and approve credit check
//			sfdc.login.loginToSFDC(InputData.Profile_CreditOps);
//			sfdc.home.closeTabIfOpenWithRefresh();
//			// sf.dataInput.quoteName = "00013178";
//			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
//			sfdc.quoteRelated.approveR4BQuoteForCreditCheck();
//			sfdc.home.logout();
//
//			sfdc.login.loginToSFDC(InputData.Profile_AE);
//			sfdc.home.openR4BSalesConsole();
//			sfdc.home.closeTabIfOpenWithRefresh();
//			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
//			sfdc.quoteDetails.verifyQuoteStatusAfterCreditCheck(sf.dataInput.quoteStatusFinalized,
//					sf.dataInput.quoteStatusApproved);
//			sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
//			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);
//
//		} else
		
		//if (InputData_Communities.mulPBFFraudCheckRequired.equalsIgnoreCase("Yes")) {

//			if (InputData_Communities.mulPBFESignature.equalsIgnoreCase("No")) {
//
//				// Validate Order Confirmation Screen
//				sfdc.orConfPBF.validateOrderConfirmationScreenForCreditOrFraudCheckAcc();
//			}
			//sfdc.home.logout();
		//	sf.dataInput.quoteName="Aauto210304089992-00032740";
//			sfdc.login.loginToSFDC(InputData.Profile_FraudOps);
//			sfdc.home.closeTabIfOpen();
//			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
//
//			//*** verify quote status before fraud approve
//			sfdc.quoteDetails.verifyQuoteStatusAfterValidationCheck(sf.dataInput.quoteStatusAccepted,
//					sf.dataInput.notApplicable, sf.dataInput.notReqStatus, sf.dataInput.orderStatusInProgress);
//			//click on Related tab
//			
//			//***Approve the quote
//			sfdc.quoteRelated.pickApproveR4BQuoteForCreditOrFraudCheck_Approve("Fraud Review");
//			sfdc.home.closeLastOpenedTab();
//			sfdc.quoteRelated.checkStatusIn_R4BFieldHistory("Fraud");
//			sfdc.home.closeTabIfOpen();
//			sfdc.home.logout();

//			sfdc.login.loginToSFDC(InputData.Profile_AE);
////			sfdc.home.openR4BSalesConsole();
////			sfdc.home.closeTabIfOpenWithRefresh();
////			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
//			sfdc.quoteDetails.verifyQuoteStatusAfterFraudCheck(sf.dataInput.quoteStatusFinalized,
//					sf.dataInput.quoteStatusApproved);
//			sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
//			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);
//			sfdc.orderDetails.verifyProductDetailsForMultisite();
//
//		} else {
//			if (InputData_Communities.mulPBFESignature.equalsIgnoreCase("No")) {
////				// Validate Order Confirmation Screen
////				sfdc.orConfPBF.validateOrderConfirmationScreen();
////
////				// Go to Order Details
////				sfdc.commPBFOrderConf.clickOnOrderNumber();
////				// Update order with site contact if required
////				sfdc.orders.updatePBFOrderWithSiteContact();
////				sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);
////				sfdc.orderDetails.verifyProductDetailsForTvBusInt();
//			}
		//}
//			} else if (InputData_Communities.mulPBFESignature.equalsIgnoreCase("Accept")) {
//				// Open order from Quote
//				sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
//				// Update order with site contact if required
//				sfdc.orders.updatePBFOrderWithSiteContact();
//				sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);
//				sfdc.orderDetails.verifyProductDetailsForTvBusInt();
//			} else {
//				// Open order from Quote
//				sfdc.quoteRelated.verifyOrderNotCreatedInQuoteRelatedAfterApproved();
//
//			}

		//}

//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
//		softassert.assertAll();
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
		return getDataSetsRunMode(Constants.TESTDATA_COMM_FILE, Constants.PBF_AGENT_SHEET_ALLENV_MULTISITE);
	}
}
