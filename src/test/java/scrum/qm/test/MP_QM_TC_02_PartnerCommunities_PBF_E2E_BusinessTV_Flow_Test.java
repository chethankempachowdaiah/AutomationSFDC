package scrum.qm.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData_Communities;
import com.sfdc.data.InputData_PartnerCommunities;
import com.sfdc.lib_pages.SFDC_AllPages;

public class MP_QM_TC_02_PartnerCommunities_PBF_E2E_BusinessTV_Flow_Test extends Base {

	/**
	 * @throws Exception
	 * 
	 * 
	 *                   1. Login as Dealer
	 * 
	 *                   2. Click on Accounts from Sales Menu
	 * 
	 *                   3. Create business account
	 * 
	 * 
	 */
	@Test(dataProvider = "PBFAgentData")
	public void test_e2ePBFPartnerCommunitites(Hashtable<String, String> dataTable) throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		InputData_Communities.setDataForPBFE2E(dataTable);
		if (InputData_Communities.commPBFUser.equalsIgnoreCase("Dealer")) {
			sfdc.partnerCommLogin.loginToPartnerCommunities(InputData_PartnerCommunities.partnerCommunities_dealer_userid);
		} else {
			sfdc.partnerCommLogin.loginToPartnerCommunities(InputData_PartnerCommunities.partnerCommunities_var_userid);
		}


		// Validate newly created business acc
		sfdc.partnerCommAccDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);

		//Create new Opportunity
		sfdc.accRelated.createOpportunity();
		sfdc.partnerCommHome.switchToWindowTwo();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();

		//sfdc.partnerCommOppDetails.validateOpportunityDetails(Global.dataInput.businessAccountName);

		//Launch Create Quote By PBF
		sfdc.cQuote.clickCreateQuotePbfButton();
		sf.seleU.switchWindow(3);

		sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();
		sfdc.siteSelPBF.verifyPBFLandingPage();
		sfdc.siteSelPBF.verifyLegendSection();

		if (InputData_Communities.commPBFAddNewSite.equals("Yes")) {
			//Verify New Site added in PBF appears in related accounts in SF

			sfdc.commPBF.addNewSite();
			sfdc.commPBFAddSite.fillSiteInfo();
			sfdc.commPBFAddSite.verifyServiceAddressesTableLayout();
			sfdc.commPBFAddSite.selectAddress();

			sfdc.commPBFAddSite.confirmAndProceed();

		} else {

			//Verify Service location has Business Internet plans
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

		// Selection TV Products
		sfdc.commPBFTVPlan.selectTVProduct();

		// Addons To be Selected
		sfdc.commPBFTVAddons.addTVAddon();

		sfdc.commPBFCablePlan.proceedtoCheckout();


		// validate Shopping cart Added TV Product with Addons
		sfdc.commPBFShopCart.validateShoppingCartPageWithAddedTVProduct();

		// Validate Price Details
		sfdc.commPBFShopCart.validatePriceDetailsForTVProduct();

		// Validate TCV margin
		//sfdc.planSelPBF.validate_TV_TCV();

		// Proceed to checkout
		sfdc.commPBFShopCart.proceedToCheckout();	

		//Add Site Contact
		if (InputData_Communities.commPBFAddSiteContact.equals("Yes"))
			sfdc.siteConPBF.addSiteContact();

		//Validate Order Summary PDF
		sfdc.commPBFOrderReview.verifyOrderSummaryPDF();

		//Validate Cancel ANd Back redirection
		sfdc.commPBFOrderReview.verifyCancelAndBackRedirection();

		//Verify terms and conditions and Accept
		sfdc.commPBFOrderReview.verifyTermsAndCondtionsAndAccept();

		sfdc.orRevPBF.verifyESigSection();

		//Validate Credit and Fraud Check Warning Message
		sfdc.commPBFOrderReview.validateCreditAndFraudCheckMessage();

		//Confirm Order	
		if(InputData_Communities.commPBFESignature.equalsIgnoreCase("No"))
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

				sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(sf.dataInput.siteContactEmailId , "Signed");
				sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(sf.dataInput.finalisedStatus, sf.dataInput.signedStatus);

			} else {
				sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(sf.dataInput.siteContactEmailId, "Reject");
				sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(sf.dataInput.finalisedStatus, sf.dataInput.declinedStatus);

			}
			sfdc.quoteDetails.verifyQuoteNumberInQuotePage();	
		}

		if (InputData_Communities.commPBFFraudCheckRequired.equals("Yes")) {
			//Validate Order Confirmation Screen
			if(InputData_Communities.commPBFESignature.equalsIgnoreCase("No")) {

				//Validate Order Confirmation Screen
				sfdc.orConfPBF.validateOrderConfirmationScreenForCreditOrFraudCheckAcc();
			}
			sfdc.home.logout();
			//Login with Fraud Ops and approve fraud check
			sfdc.login.navigateURL();
			//sf.dataInput.quoteName = "fraudCheckForPBF-00015592";
			sfdc.quoteDetails.checkCreditOrFraud(sf.dataInput.quoteName, false, "Approve");

			if (InputData_Communities.commPBFUser.equalsIgnoreCase("Dealer")) {
				sfdc.partnerCommLogin.loginToPartnerCommunities(InputData_PartnerCommunities.partnerCommunities_dealer_userid);
			} else {
				sfdc.partnerCommLogin.loginToPartnerCommunities(InputData_PartnerCommunities.partnerCommunities_var_userid);
			}

			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			sfdc.quoteDetails.verifyQuoteStatusAfterFraudCheck(sf.dataInput.quoteStatusFinalized, sf.dataInput.quoteStatusApproved);
			sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);

		} else {
			if(InputData_Communities.commPBFESignature.equalsIgnoreCase("No")) {
				//Validate Order Confirmation Screen
				sfdc.orConfPBF.validateOrderConfirmationScreen();

				//Go to Order Details
				sfdc.commPBFOrderConf.clickOnOrderNumber();
				//Update order with site contact if required
				sfdc.orders.updatePBFOrderWithSiteContact();

				sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);
				sfdc.orderDetails.verifyProductDetails();
			} else if(InputData_Communities.commPBFESignature.equalsIgnoreCase("Accept") ){
				//Open order from Quote
				sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
				//Update order with site contact if required
				sfdc.orders.updatePBFOrderWithSiteContact();
				sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);
				sfdc.orderDetails.verifyProductDetails();
			} else {
				//Open order from Quote
				sfdc.quoteRelated.verifyOrderNotCreatedInQuoteRelatedAfterApproved();

			}

		}
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

