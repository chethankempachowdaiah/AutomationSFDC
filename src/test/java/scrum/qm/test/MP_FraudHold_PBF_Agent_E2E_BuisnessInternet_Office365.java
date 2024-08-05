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

public class MP_FraudHold_PBF_Agent_E2E_BuisnessInternet_Office365 extends Base{
	/**
	 * @throws Exception
	 * 
	 * Fraud hold
	 */
	@Test(dataProvider = "PBFAgentData")
	public void test_validate_PBF_E2E_Agent_BusIntOff365_FraudHold(Hashtable<String, String> dataTable) throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();
//		byte[] encodedBytes = Base64.encodeBase64("Mainfra9!!!!".getBytes());
//		String encodedString = new String(encodedBytes);
//		System.out.println(encodedString);
		InputData_Communities.setDataForPBFE2E(dataTable);

		//		sfdc.login.loginToSFDC(InputData.Profile_AE);
		//		sfdc.home.openR4BSalesConsole();
		//		sfdc.home.closeTabIfOpen();
		//
		//		sfdc.acc.createNewBusinessAccount();
		//		sfdc.cba.enterBusinessAccountInfo(false);
		//		sfdc.cba.verifyBusinessAccountCreated();
		//
		//		sfdc.cc.enterCreateContactInfo(false);
		//		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		//		sfdc.cc.verifyContactCreated();
		//		sfdc.cc.clickOnNextInCreateContact();
		//
		//		sfdc.csa.enterServiceAccountInfo(1);
		//		sfdc.csa.clickOnNextInCreateServiceAccount();
		//		sfdc.csa.noBillingAccountClickOnNext();
		//		sfdc.home.closeTabIfOpen();
		//		sfdc.home.logout();
		//
		//		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		//		sfdc.home.closeTabIfOpen();
		//		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		//		sfdc.accRelated.approveAsDataGovernance();
		//		sfdc.home.closeTabIfOpen();
		//		sfdc.home.logout();
		//		sf.dataInput.businessAccountName = "SS DevStage";
		sfdc.login.loginToSFDC(InputData.Profile_AE);
	/*	sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();
		//				sf.dataInput.signingAuthEmailIdValue = "flow@mailinator.com";
		//				Create Quote BY PBF
		sfdc.cQuote.clickCreateQuotePbfButton_New();
		sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();
		sfdc.siteSelPBF.verifyPBFLandingPage();
		sfdc.siteSelPBF.verifyLegendSection();

		if (InputData_Communities.commPBFAddNewSite.equals("Yes")) {
			// Verify New Site added in PBF appears in related accounts in SF

			// Extract No if service addesses before adding new site in PBF
			//			sfdc.login.loginToSFDCInNewTab(InputData.Profile_AE);
			//			sfdc.home.closeTabIfOpen();
			//			sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
			//			sfdc.accHirchy.extractAllRelatedServiceAccForBusAcc();
			//			sfdc.accHirchy.extractNoOfRelatedServiceAcc();
			//
			//			// Add New Site in PBF
			//			sf.seleU.switchWindow(1);
			sfdc.commPBF.addNewSite();
			sfdc.commPBFAddSite.fillSiteInfo();
			sfdc.commPBFAddSite.verifyServiceAddressesTableLayout();
			sfdc.commPBFAddSite.selectAddress();

			sfdc.commPBFAddSite.confirmAndProceed();
			//			sf.seleU.switchWindow(2);
			//
			//			// Verify New Site added in PBF appears in related accounts in SF
			//			sfdc.accHirchy.verifyNoOfRelatedServiceAccIncreased();
			//			sf.seleU.switchWindow(1);
			//			sf.seleU.closeRecentlyOpenedWindow();

		} else {

			// Verify Service location has Business Internet plan
			sfdc.siteSelPBF.verifyIconInServiceColumn();
			sfdc.siteSelPBF.selectBusinessInternetAddress();
		}
*/
		// Validate shopping Cart Page and Proceed
		sfdc.commPBFShopCart.validateShoppingCartPageAndProceed();

		if (!InputData_Communities.commPBFBundledFirstProd.equals("TV")) {
			// Click on Shop Plans for Business Internet
			sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();

			sfdc.planSelPBF.selectInternetProductType();

			// Add to Cart a product and proceed to checkout
			sfdc.commPBFCablePlan.addToCartProduct_Quickly();

			sfdc.commPBFCablePlan.clickNextOnBusInt();

			//Add to Cart an AddON and proceed to shopping Cart
			sfdc.off365AddOnPBF.addToCartOff365AddOnProductAndProceed();

			// Validate Price details of added Office 365AddOn
			sfdc.commPBFShopCart.validateShoppingCartPageWithAddedOff365Product();		
		}

		if (!InputData_Communities.commPBFAddTVProductName.equals("NA")) {
			// Add TV Product
			//Click on Add Business Products
			sfdc.commPBFShopCart.quickValidateShoppingCartPageAndProceed();

			//Click on Business TV Shop Plans
			sfdc.siteSelPBF.moveToSiteSelectionPageForBusTV();

			//Select TV Product
			sfdc.commPBFTVPlan.selectTVProduct();

			sfdc.commPBFCablePlan.clickNextOnBusInt();

			// Addons To be Selected
			sfdc.commPBFTVAddons.addTVAddon();

			// validate Shopping cart Added TV Product with Addons
			//	sfdc.commPBFShopCart.validateShoppingCartPageWithAddedTVProduct();

			// proceed to shopping cart page
			sfdc.commPBFCablePlan.proceedtoCheckout();
			
		}
		
		//**** for fraud check			
		sfdc.planSelPBF.verifyMonthlyTotal();

		// Proceed to checkout
		sfdc.commPBFShopCart.proceedToCheckout();

		// Validate Order Review Page
		sfdc.commPBFOrderReview.validateOrderReviewPageWithoutValues();
		sfdc.commPBFOrderReview.validateCreditAndFraudCheckMessage();
		sfdc.orRevPBF.verifyESigSection();

		//		// Validate Credit and Fraud Check Warning Message
		//		sfdc.commPBFOrderReview.validateCreditAndFraudCheckMessage();

		// Confirm Order
		if (InputData_Communities.commPBFESignature.equalsIgnoreCase("No")) {
			sfdc.gdPdf.verifyAllProductPricesInPDF(dataTable, Constants.AGREEMENT_PDF);
			sfdc.commPBFOrderReview.confirmAndPlaceOrder();
		}
		else {
			sfdc.orRevPBF.sendQuoteForESigApproval();
			sfdc.gdPdf.generatePDFNextButton();

			if (InputData_Communities.commPBFAddSiteContact.equalsIgnoreCase("No"))
				sfdc.siteCon.selectExistingSiteContact();
			else
				sfdc.siteCon.verifySelectedContact();

			sfdc.orderDetails.verifyContractAndQuoteDetails(sf.dataInput.awaitingSign, sf.dataInput.awaitingSign, "","");
			sfdc.quoteDetails.verifyQuoteNumberInQuotePage();
			sfdc.quoteRelated.checkR4BFieldHistory_awaitingesign();
			if (InputData_Communities.commPBFESignature.equalsIgnoreCase("Accept")) {

				sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(sf.dataInput.siteContactEmailId, "Signed");
				sf.seleU.wait(60000);
				sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(sf.dataInput.finalisedStatus,
						sf.dataInput.signedStatus);
			} else {
				sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(sf.dataInput.siteContactEmailId, "Reject");
				sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(sf.dataInput.finalisedStatus,
						sf.dataInput.declinedStatus);

			}
			//		sfdc.quoteDetails.verifyQuoteNumberInQuotePage();
		}

		//		InputData_Communities.commPBFCreditCheckRequired = "No";
		//		InputData_Communities.commPBFFraudCheckRequired =  "Yes";
		//				sf.dataInput.fraudCheckStatus = "Yes"; 	sf.dataInput.fraudCheckStatus_PriceAbove_2000 = "No";

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
			sfdc.orders.updatePBFOrderWithSiteContact();
			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);

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
			//	sf.dataInput.quoteName = "SS DevStage-00032502";
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
			sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
			sfdc.orders.updatePBFOrderWithSiteContact();
			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);

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
			//	 sf.dataInput.quoteName = "Reg-Rework-00030907";
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
			sfdc.home.openR4BSalesConsole();
			sfdc.home.closeTabIfOpenWithRefresh();
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			// sfdc.quotes.searchQuoteGlobalSearch("Aauto210319033924-00007728");
			sfdc.quoteDetails.verifyQuoteStatusAfterFraudCheck(sf.dataInput.quoteStatusFinalized,
					sf.dataInput.quoteStatusApproved);
			sfdc.quoteDetails.verifyQuoteStatusAfterCreditCheck(sf.dataInput.quoteStatusFinalized,
					sf.dataInput.quoteStatusApproved);
			sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
			sfdc.orders.updatePBFOrderWithSiteContact();
			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);
		}

		else{
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
		return getDataSetsRunMode(Constants.TESTDATA_COMM_PRICING_VALIDATION_FILE, Constants.PBF_AGENT_SHEET_BITVOFF_FRAUD_ESIGN);
	}
}
