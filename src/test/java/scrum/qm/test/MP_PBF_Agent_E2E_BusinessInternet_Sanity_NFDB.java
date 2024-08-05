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

/**
 * @author chethan.k
 * Create quote in PBF> Add Business Internet Product > Accept quote Verbally/E-signature 
 * > Validate the fields 1)Quote status, 2)Validation check, 3)Credit Review, 4)Fraud Review in quote Review page.
 * 
 */
public class MP_PBF_Agent_E2E_BusinessInternet_Sanity_NFDB extends Base {

	/**
	 * @throws Exception
	 */
	@Test(dataProvider = "PBFAgentData")
	public void test_validate_PBF_E2E_Agent_BusinessInternet_Sanity(Hashtable<String, String> dataTable) throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		InputData_Communities.setDataForPBFE2E(dataTable);
		
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		
		//sf.dataInput.businessAccountName="DecemberTest";				
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();

		// Create Quote BY PBF
		sfdc.cQuote.clickCreateQuotePbfButton();
		sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();
		
		if (InputData_Communities.commPBFAddNewSite.equals("Yes")) {
			
			sfdc.commPBF.addNewSite();
			sfdc.commPBFAddSite.fillSiteInfo();
			sfdc.commPBFAddSite.selectAddress();

			sfdc.commPBFAddSite.confirmAndProceed();
			
		} else {

			sfdc.siteSelPBF.selectBusinessInternetAddress();

		}

		// Validate shopping Cart Page and Proceed
		//sfdc.commPBFShopCart.quickValidateShoppingCartPageAndProceed();

		// Click on Shop Plans for Business Internet
		//sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();

		// Add to Cart a product and proceed to checkout
		//sfdc.commPBFCablePlan.addToCartProduct();
		
		//sfdc.commPBFCablePlan.proceedtoCheckout();

		// Remove Product
		//sfdc.commPBFShopCart.removeProduct();

		// Validate shopping Cart Page and Proceed
		sfdc.commPBFShopCart.quickValidateShoppingCartPageAndProceed();

		// Click on Shop Plans for Business Internet
		sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();

		// Add to Cart a product and proceed to checkout
		sfdc.commPBFCablePlan.addToCartProduct();
		
		sfdc.commPBFCablePlan.proceedtoCheckout();

		// Validate User lands on Shopping Cart Page with added product
		sfdc.commPBFShopCart.quickValidateShoppingCartPageWithAddedProduct();

		// Proceed to checkout
		sfdc.commPBFShopCart.proceedToCheckout();

		// Add Site Contact
		if (InputData_Communities.commPBFAddSiteContact.equals("Yes"))
			sfdc.siteConPBF.addSiteContact();

		// Confirm Order
		if (InputData_Communities.commPBFESignature.equalsIgnoreCase("No"))
			sfdc.commPBFOrderReview.confirmAndPlaceOrder();
		else {
			sfdc.orRevPBF.sendQuoteForESigApproval();
			sfdc.gdPdf.generatePDFNextButton();

			if (InputData_Communities.commPBFAddSiteContact.equalsIgnoreCase("No"))
				sfdc.siteCon.selectExistingSiteContact();
			else
				sfdc.siteCon.verifySelectedContact();

			sfdc.orderDetails.verifyContractAndQuoteDetails(sf.dataInput.awaitingSign, sf.dataInput.awaitingSign, "",
					"");
			if (InputData_Communities.commPBFESignature.equalsIgnoreCase("Accept")) {

				sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(sf.dataInput.signingAuthEmailIdValue,
						"Signed");
				sf.seleU.hardwait(900000);
				sf.seleU.refreshPage();
				
			} else {
				sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(sf.dataInput.siteContactEmailId, "Reject");
			}
		}

		if (InputData_Communities.commPBFCreditCheckRequired.equals("Yes")) {
			if (InputData_Communities.commPBFESignature.equalsIgnoreCase("No")) {
				// Validate Order Confirmation Screen
				sfdc.orConfPBF.validateOrderConfirmationScreenForCreditOrFraudCheckAcc();
			}
			sfdc.home.logout();
			
			// Login with Credit Ops and approve credit check
			sfdc.login.loginToSFDC(InputData.Profile_CreditOps);
			sfdc.home.closeTabIfOpenWithRefresh();
			// sf.dataInput.quoteName = "00013178";
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			sfdc.quoteRelated.approveR4BQuoteForCreditCheck();
			sfdc.home.logout();
			//Login as AE and validate the quote status
			sfdc.login.loginToSFDC(InputData.Profile_AE);
			sfdc.quotes.searchQuoteGlobalSearchInDevStage(sf.dataInput.quoteName);
			sfdc.quoteDetails.verifyQuoteStatusAfterValidationCheck("Finalized","Not Applicable","Approved","Not Required");

			

		} else if (InputData_Communities.commPBFFraudCheckRequired.equals("Yes")) {

			if (InputData_Communities.commPBFESignature.equalsIgnoreCase("No")) {

				// Validate Order Confirmation Screen
				sfdc.orConfPBF.validateOrderConfirmationScreenForCreditOrFraudCheckAcc();
			}
			sfdc.home.logout();
			
			// Login with Fraud Ops and approve fraud check
			sfdc.login.navigateURL();
			// sf.dataInput.quoteName = "fraudCheckForPBF-00015592";
			sfdc.quoteDetails.checkCreditOrFraud(sf.dataInput.quoteName, false, "Approve");
			//Login as AE and validate the quote status
			sfdc.login.loginToSFDC(InputData.Profile_AE);
			sfdc.quotes.searchQuoteGlobalSearchInDevStage(sf.dataInput.quoteName);
			sfdc.quoteDetails.verifyQuoteStatusAfterValidationCheck("Finalized","Not Applicable","Not Required","Approved");


		} else {
			if (InputData_Communities.commPBFESignature.equalsIgnoreCase("No")) {
				// Validate Order Confirmation Screen
				sfdc.orConfPBF.validateOrderConfirmationScreen();

				// Go to Order Details
				sfdc.commPBFOrderConf.clickOnOrderNumberAndCheckNfdb();
				// Update order with site contact if required
				//sfdc.orders.updatePBFOrderWithSiteContact();
				
				sfdc.quoteDetails.verifyQuoteStatusAfterValidationCheck("Finalized","Not Applicable","Not Required","Not Required");
				
			} else if (InputData_Communities.commPBFESignature.equalsIgnoreCase("Accept")) {
				// Open order from Quote
				sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
				// Update order with site contact if required
				sfdc.orders.updatePBFOrderWithSiteContact();
			} else {
				// Open order from Quote
				sfdc.quoteRelated.verifyOrderNotCreatedInQuoteRelatedAfterApproved();

			}

		}
		if (sf.dataInput.userLanguage.equalsIgnoreCase("French"))
			sfdc.userProfile.changeLanguageToEnglish();
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
		return getDataSetsRunMode(Constants.TESTDATA_COMM_FILE, Constants.PBF_AGENT_SHEET_ALLENV_BI);
	}
}
