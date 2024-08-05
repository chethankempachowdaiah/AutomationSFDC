package scrum.qm.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.lib_pages.SFDC_AllPages;

public class MP_QM_TC_01_PBF_Agent_E2E_BusinessTV_Test extends Base {

	/**
	 * @throws Exception
	 */
	@Test(dataProvider = "PBFAgentData")
	public void test_validate_PBF_E2E_Agent(Hashtable<String, String> dataTable) throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		InputData_Communities.setDataForPBFE2E(dataTable);

		sfdc.login.loginToSFDC(InputData.Profile_AE);

		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();

		// Create Quote BY PBF
		sfdc.cQuote.clickCreateQuotePbfButton();
		sfdc.siteSelPBF.moveToSiteSelectionPageForBusTV();
		sfdc.siteSelPBF.verifyPBFLandingPage();
		sfdc.siteSelPBF.verifyLegendSection();

		if (InputData_Communities.commPBFAddNewSite.equals("Yes")) {
			// Verify New Site added in PBF appears in related accounts in SF

			// Extract No if service addesses before adding new site in PBF
			sfdc.login.loginToSFDCInNewTab(InputData.Profile_AE);

			sfdc.home.closeTabIfOpen();
			sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
			sfdc.accHirchy.extractAllRelatedServiceAccForBusAcc();
			sfdc.accHirchy.extractNoOfRelatedServiceAcc();

			// Add New Site in PBF
			sf.seleU.switchWindow(1);
			sfdc.commPBF.addNewSite();
			sfdc.commPBFAddSite.fillSiteInfo();
			sfdc.commPBFAddSite.verifyServiceAddressesTableLayout();
			sfdc.commPBFAddSite.selectAddress();

			sfdc.commPBFAddSite.confirmAndProceed();
			sf.seleU.switchWindow(2);

			// Verify New Site added in PBF appears in related accounts in SF
			sfdc.accHirchy.verifyNoOfRelatedServiceAccIncreased();
			sf.seleU.switchWindow(1);
			sf.seleU.closeRecentlyOpenedWindow();

		} else {

			// Verify Service location has Business TV plans
			sfdc.siteSelPBF.verifyIconInServiceColumn();

			sfdc.siteSelPBF.selectBusinessInternetAddress();

		}

		// Validate shopping Cart Page and Proceed
		sfdc.commPBFShopCart.validateShoppingCartPageAndProceed();

		// Click on Shop Plans for Business TV
		sfdc.siteSelPBF.moveToSiteSelectionPageForBusTV();

		// Validating the TV Products
		sfdc.commPBFTVPlan.validateBusinessTVProductsNames();		

		// Validating the TV Sub Products
		sfdc.commPBFTVPlan.validateBusinessTVSubProductsNames();

		// Selection TV Products
		sfdc.commPBFTVPlan.selectTVProduct();

		// Validation Tv Addons
		sfdc.commPBFTVAddons.validateTVAddOnsProductNames();

		// Addons To be Selected
		sfdc.commPBFTVAddons.addTVAddon();

		sfdc.commPBFCablePlan.proceedtoCheckout();

		// Click on Edit button, Addons to be modified
		sfdc.commPBFShopCart.editTVAddons();

		// Addons To be Modified
		sfdc.commPBFTVAddons.validateEditAddonsTV();

		// Remove the TV Product Added
		sfdc.commPBFShopCart.removeTVAdded();

		// Validate shopping Cart Page and Proceed
		sfdc.commPBFShopCart.validateShoppingCartPageAndProceed();

		// Click on Shop Plans for Business TV
		sfdc.siteSelPBF.moveToSiteSelectionPageForBusTV();

		// Validating the TV Products
		sfdc.commPBFTVPlan.validateBusinessTVProductsNames();		

		// Validating the TV Sub Products
		sfdc.commPBFTVPlan.validateBusinessTVSubProductsNames();

		// Selection TV Products
		sfdc.commPBFTVPlan.selectTVProduct();

		// Validation Tv Addons
		sfdc.commPBFTVAddons.validateTVAddOnsProductNames();

		// Addons To be Selected

		sfdc.commPBFTVAddons.addTVAddon();
		
		sfdc.commPBFCablePlan.proceedtoCheckout();


		// validate Shopping cart Added TV Product with Addons
		sfdc.commPBFShopCart.validateShoppingCartPageWithAddedTVProduct();

		// QM and OM team Manage price validation
		//sfdc.commPBFShopCart.validatePriceDetailsForTVProduct();

		// QM and OM team Manage price validation
		//sfdc.planSelPBF.validate_TV_TCV();

		//To check order value>500
		if (InputData_Communities.commPBFFraudCheckRequired.equalsIgnoreCase("No"))
			sfdc.planSelPBF.checkMonthlyTotalForFraudReview();
				
		// Proceed to checkout
		sfdc.commPBFShopCart.proceedToCheckout();	

		// Add Site Contact
		if (InputData_Communities.commPBFAddSiteContact.equals("Yes"))
			sfdc.siteConPBF.addSiteContact();

		// Validate Order Summary PDF
		sfdc.commPBFOrderReview.verifyOrderSummaryPDF();

		// Validate Cancel ANd Back redirection
		sfdc.commPBFOrderReview.verifyCancelAndBackRedirection();

		// Verify terms and conditions and Accept
		sfdc.commPBFOrderReview.verifyTermsAndCondtionsAndAccept();

		sfdc.orRevPBF.verifyESigSection();

		//				// Validate Credit and Fraud Check Warning Message
		//				sfdc.commPBFOrderReview.validateCreditAndFraudCheckMessage();

		// Confirm Order
		if (InputData_Communities.commPBFESignature.equalsIgnoreCase("No"))
			sfdc.commPBFOrderReview.confirmAndPlaceOrder();
		else {
			sfdc.orRevPBF.sendQuoteForESigApproval();
			sfdc.gdPdf.generatePDFNextButton();

			sfdc.orderDetails.verifyContractAndQuoteDetails(sf.dataInput.awaitingSign, sf.dataInput.awaitingSign, "",
					"");
			if (InputData_Communities.commPBFESignature.equalsIgnoreCase("Accept")) {

				sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(sf.dataInput.contactEmailAddress, "Signed");
				sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(sf.dataInput.finalisedStatus,
						sf.dataInput.signedStatus);
				//

			} else {
				sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(sf.dataInput.siteContactEmailId, "Reject");
				sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(sf.dataInput.finalisedStatus,
						sf.dataInput.declinedStatus);

			}
			sfdc.quoteDetails.verifyQuoteNumberInQuotePage();
		}
		//Validate screen for Quote created in case of credit or fraud required
		if (InputData_Communities.commPBFCreditCheckRequired.equals("Yes")
				|| InputData_Communities.commPBFFraudCheckRequired.equalsIgnoreCase("Yes")) {

			if (InputData_Communities.commPBFESignature.equalsIgnoreCase("No"))
				sfdc.orConfPBF.validateOrderConfirmationScreenForCreditOrFraudCheckAcc();

			sfdc.home.logout();
		}
		
		//Approve Credit Check
		if (InputData_Communities.commPBFCreditCheckRequired.equals("Yes")) {
			
			// Login with Credit Ops and approve credit check
			sfdc.login.loginToSFDC(InputData.Profile_CreditOps);
			sfdc.home.closeTabIfOpenWithRefresh();
			// sf.dataInput.quoteName = "00013178";
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			sfdc.quoteRelated.approveR4BQuoteForCreditCheck();
			sfdc.home.logout();
		}
		
		//Approve Fraud Check
		if (InputData_Communities.commPBFFraudCheckRequired.equalsIgnoreCase("Yes")) {

			sfdc.login.loginToSFDC(InputData.Profile_FraudOps);
			sfdc.home.closeTabIfOpen();
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);

			//***Approve the quote
			sfdc.quoteRelated.pickApproveR4BQuoteForCreditOrFraudCheck_Approve("Fraud Review");
			sfdc.home.closeTabIfOpen();
			sfdc.home.logout();
		}
				
		//Go back to AE after credit/fraud approvals
		if (InputData_Communities.commPBFCreditCheckRequired.equals("Yes")
				|| InputData_Communities.commPBFFraudCheckRequired.equalsIgnoreCase("Yes")) {

			sfdc.login.loginToSFDC(InputData.Profile_AE);
			sfdc.home.openR4BSalesConsole();
			sfdc.home.closeTabIfOpenWithRefresh();
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			
			if (InputData_Communities.commPBFCreditCheckRequired.equals("Yes") )
				sfdc.quoteDetails.verifyQuoteStatusAfterCreditCheck(sf.dataInput.quoteStatusFinalized,
					sf.dataInput.quoteStatusApproved);
			else
				sfdc.quoteDetails.verifyQuoteStatusAfterFraudCheck(sf.dataInput.quoteStatusFinalized,
					sf.dataInput.quoteStatusApproved);
			
			sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);
			// Update order with site contact if required
			sfdc.orders.updatePBFOrderWithSiteContact();
			sfdc.orderDetails.verifyProductDetails();

		} else {
			if (InputData_Communities.commPBFESignature.equalsIgnoreCase("No")) {
				// Validate Order Confirmation Screen
				sfdc.orConfPBF.validateOrderConfirmationScreenForRDI();

				// Go to Order Details
				sfdc.commPBFOrderConf.clickOnOrderNumber();
				// Update order with site contact if required
				sfdc.orders.updatePBFOrderWithSiteContact();
				sfdc.orderDetails.validateOrderDetails();
				sfdc.orderDetails.verifyProductDetails();

			} else if (InputData_Communities.commPBFESignature.equalsIgnoreCase("Accept")) {
				// Open order from Quote
				sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
				// Update order with site contact if required
				sfdc.orders.updatePBFOrderWithSiteContact();
				sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);
				sfdc.orderDetails.verifyProductDetails();
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
		return getDataSetsRunMode(Constants.TESTDATA_COMM_FILE, Constants.PBF_AGENT_SHEET_ALLENV_TV);
	}
}


