package scrum.qm.test;

import java.io.IOException;
import java.util.Hashtable;

import org.apache.commons.codec.binary.Base64;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.lib_pages.SFDC_AllPages;

public class MP_Multisite_FraudHold_PBF_Agent_E2E_BuisnessInternet_Office365 extends Base {

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

		// Create Quote BY PBF
		sfdc.cQuote.clickCreateQuotePbfButton();
		sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();
		sfdc.mulSiteSelPBF.verifyMulSiteSelPage();
		sfdc.siteSelPBF.verifyLegendSection();

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
		
		//**** for fraud check			
		sfdc.planSelPBF.verifyMonthlyTotal_ForMultisiteOrders();

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

		//		// Validate Order Summary PDF
		//		sfdc.commPBFOrderReview.verifyOrderSummaryPDF();
		//
		//		// Validate Cancel ANd Back redirection
		//		sfdc.commPBFOrderReview.verifyCancelAndBackRedirection();
		//
		//		// Verify terms and conditions and Accept
		//		sfdc.mulSiteOrderReview.verifyTermsAndCondtionsAndAccept();

		sfdc.orRevPBF.verifyESigSection();	

		// Confirm Order
		System.out.println(InputData_Communities.commPBFESignature);
		if (InputData_Communities.commPBFESignature.equalsIgnoreCase("No")) {
			//***** Verify the prices in the PDF Aggreement	******		
			//			sfdc.gdPdf.verifyAllProductPricesInPDF(dataTable, Constants.AGREEMENT_PDF);

			sfdc.commPBFOrderReview.confirmAndPlaceOrder();
		}
		else {
			sfdc.orRevPBF.sendQuoteForESigApproval();
			sfdc.gdPdf.generatePDFNextButton();

			sfdc.orderDetails.verifyContractAndQuoteDetails(sf.dataInput.awaitingSign, sf.dataInput.awaitingSign, "","");
			sfdc.quoteDetails.verifyQuoteNumberInQuotePage();
			if (InputData_Communities.commPBFESignature.equalsIgnoreCase("Accept")) {

				//	sf.dataInput.siteContactEmailId = "xyx@mailinator.com";
				sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator_ForMultisiteOrders(sf.dataInput.siteContactEmailId, "Signed");
				sf.seleU.wait(60000);
				sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(sf.dataInput.finalisedStatus,
						sf.dataInput.signedStatus);
			} else {
				sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator_ForMultisiteOrders(sf.dataInput.siteContactEmailId, "Reject");
				sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(sf.dataInput.finalisedStatus,
						sf.dataInput.declinedStatus);

			}
			//		sfdc.quoteDetails.verifyQuoteNumberInQuotePage();
		}

		//		InputData_Communities.commPBFCreditCheckRequired = "Yes";
		//		InputData_Communities.commPBFFraudCheckRequired =  "No";
		//				sf.dataInput.fraudCheckStatus = "Yes"; 	sf.dataInput.fraudCheckStatus_PriceAbove_2000 = "No";
		System.out.println(InputData_Communities.commPBFFraudCheckRequired);

		if (InputData_Communities.commPBFCreditCheckRequired.equals("Yes") && 
				InputData_Communities.commPBFFraudCheckRequired.equals("No")) {

			if (InputData_Communities.commPBFESignature.equalsIgnoreCase("No")) {
				// Validate Order Confirmation Screen
				sfdc.orConfPBF.validateOrderConfirmationScreenForCreditOrFraudCheckAcc();
			}
			sfdc.home.logout(); 
			// Login with Credit Ops and approve credit check
			sfdc.login.loginToSFDC(InputData.Profile_CreditOps);
			sfdc.home.closeTabIfOpenWithRefresh();
			//	sf.dataInput.quoteName = "TESTAuto_PVT21080200103-00034879";
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			sfdc.quoteRelated.approveR4BQuoteForCreditCheck();
			sfdc.home.closeLastOpenedTab();
			sfdc.quoteRelated.checkStatusIn_R4BFieldHistory("Credit");
			//				sfdc.home.logout();

			sfdc.login.loginToSFDC(InputData.Profile_AE);
			sfdc.home.openR4BSalesConsole();
			sfdc.home.closeTabIfOpenWithRefresh();
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			sfdc.quoteDetails.verifyQuoteStatusAfterCreditCheck(sf.dataInput.quoteStatusFinalized,
					sf.dataInput.quoteStatusApproved);
			sfdc.quoteRelated.verifyMultisite_OrderNumberInQuoteRelatedAfterApproved(sf.dataInput.orderStatusInProgress);

			//				sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);

		} else if (InputData_Communities.commPBFFraudCheckRequired.equals("Yes") &&
				InputData_Communities.commPBFCreditCheckRequired.equals("No")) {

			if (InputData_Communities.commPBFESignature.equalsIgnoreCase("No")) {

				// Validate Order Confirmation Screen
				sfdc.orConfPBF.validateOrderConfirmationScreenForCreditOrFraudCheckAcc();
			}
			sfdc.home.logout();
			// Login with Fraud Ops and approve fraud check
			sfdc.login.loginToSFDC(InputData.Profile_FraudOps);
			sfdc.home.closeTabIfOpen();
			// sf.dataInput.quoteName = "Aauto210304089992-00032734";
			//	sf.dataInput.quoteName = "checkForAcc-00036443";
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);

			//*** verify quote status before fraud approve
			sfdc.quoteDetails.verifyQuoteStatusAfterValidationCheck(sf.dataInput.quoteStatusAccepted,
					sf.dataInput.notApplicable, sf.dataInput.notReqStatus, sf.dataInput.orderStatusInProgress);
			//click on Related tab
			//	if(isApprove.equals("Approve")){
			//***Approve the quote
			sfdc.quoteRelated.pickApproveR4BQuoteForCreditOrFraudCheck_Approve("Fraud Review");
			sfdc.home.closeLastOpenedTab();
			sfdc.quoteRelated.checkStatusIn_R4BFieldHistory("Fraud");
			sfdc.home.closeTabIfOpen();
			sfdc.home.logout();

			sfdc.login.loginToSFDC(InputData.Profile_AE);
			sfdc.home.openR4BSalesConsole();
			sfdc.home.closeTabIfOpenWithRefresh();
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			// sfdc.quotes.searchQuoteGlobalSearch("Aauto210319033924-00007728");
			sfdc.quoteDetails.verifyQuoteStatusAfterFraudCheck(sf.dataInput.quoteStatusFinalized,
					sf.dataInput.quoteStatusApproved);
			sfdc.quoteRelated.verifyMultisite_OrderNumberInQuoteRelatedAfterApproved(sf.dataInput.orderStatusInProgress);

			//			sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
			//			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);

			//**** if both credit and fraud check is required
		} else if (InputData_Communities.commPBFFraudCheckRequired.equals("Yes") 
				&& InputData_Communities.commPBFCreditCheckRequired.equals("Yes")) {

			if (InputData_Communities.commPBFESignature.equalsIgnoreCase("No")) {
				// Validate Order Confirmation Screen
				sfdc.orConfPBF.validateOrderConfirmationScreenForCreditOrFraudCheckAcc();
			}
			sfdc.home.logout();
			//	sfdc.login.navigateURL();

			//  Login with fraud ops and approve
			//	sf.dataInput.quoteName = "commAccountTest-00036063";
			sfdc.login.loginToSFDC(InputData.Profile_FraudOps);
			sfdc.home.closeTabIfOpen();
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			// verify quote status before fraud approve
			sfdc.quoteDetails.verifyQuoteStatusAfterValidationCheck(sf.dataInput.quoteStatusAccepted,
					sf.dataInput.notApplicable, sf.dataInput.orderStatusInProgress, sf.dataInput.orderStatusInProgress);
			//click on Related tab
			//	if(isApprove.equals("Approve")){
			//approve the quote
			sfdc.quoteRelated.pickApproveR4BQuoteForCreditOrFraudCheck_Approve("Fraud Review");
			sfdc.home.closeLastOpenedTab();
			sfdc.quoteRelated.checkStatusIn_R4BFieldHistory("Fraud");
			sfdc.home.closeTabIfOpen();
			sfdc.home.logout();

			// Approve credit review
			sfdc.login.loginToSFDC(InputData.Profile_CreditOps);
			sfdc.home.closeTabIfOpen();
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			sfdc.quoteRelated.pickApproveR4BQuoteForCreditOrFraudCheck_Approve("Credit Check");
			sfdc.home.closeTabIfOpen();
			sfdc.home.logout();

			sfdc.login.loginToSFDC(InputData.Profile_AE);
			//			sfdc.home.openR4BSalesConsole();
			sfdc.home.closeTabIfOpenWithRefresh();
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			// sfdc.quotes.searchQuoteGlobalSearch("Aauto210319033924-00007728");
			sfdc.quoteDetails.verifyQuoteStatusAfterFraudCheck(sf.dataInput.quoteStatusFinalized,
					sf.dataInput.quoteStatusApproved);
			sfdc.quoteDetails.verifyQuoteStatusAfterCreditCheck(sf.dataInput.quoteStatusFinalized,
					sf.dataInput.quoteStatusApproved);
			sfdc.quoteRelated.verifyMultisite_OrderNumberInQuoteRelatedAfterApproved(sf.dataInput.orderStatusInProgress);
			//			sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
			//			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);
		}

		else {
			if (InputData_Communities.commPBFESignature.equalsIgnoreCase("No")) {
				// Validate Order Confirmation Screen
				sfdc.orConfPBF.validateOrderConfirmationScreen();

				// Go to Order Details
				sfdc.commPBFOrderConf.clickOnOrderNumber();
				// Update order with site contact if required
				sfdc.orders.updatePBFOrderWithSiteContact();
				sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);
				sfdc.orderDetails.verifyProductDetailsForTvBusInt();
			} else if (InputData_Communities.commPBFESignature.equalsIgnoreCase("Accept")) {
				// Open order from Quote
				sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
				// Update order with site contact if required
				sfdc.orders.updatePBFOrderWithSiteContact();
				sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);
				sfdc.orderDetails.verifyProductDetailsForTvBusInt();
			} else {
				// Open order from Quote
				sfdc.quoteRelated.verifyOrderNotCreatedInQuoteRelatedAfterApproved();

			}

		}

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
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
		return getDataSetsRunMode(Constants.TESTDATA_COMM_PRICING_VALIDATION_FILE, Constants.PBF_AGENT_SHEET_ALLENV_MULTISITE);
	}
}
