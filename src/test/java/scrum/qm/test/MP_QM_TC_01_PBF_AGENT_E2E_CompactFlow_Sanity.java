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
public class MP_QM_TC_01_PBF_AGENT_E2E_CompactFlow_Sanity extends BaseDataProvider {
	/**
	 * @throws Exception
	 */
	@Test(dataProvider = "PBFAgentData")
	public void test_validate_PBF_E2E_Agent_BusIntTVOff365(Hashtable<String, String> dataTable) throws Exception {

		
		
		SFDC_AllPages sfdc = new SFDC_AllPages();
		InputData_Communities.setDataForPBFE2E(dataTable);

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		Global.dataInput.businessAccountName="TestAuto_210111122";
		sfdc.acc.createNewBusinessAccountRevised();
		sfdc.cba.enterBusinessAccountInfoRevised();
		sfdc.cba.verifyBusinessAccountCreatedRevised();
		sfdc.cc.enterNewContactInfoForBusinessAccount(true, false, true, false);
		sfdc.cc.validateBusinessContact();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
				
		//approve business account
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);		
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountLegalName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		Global.dataInput.businessAccountName="Reg-Rework";
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();

		// Create Quote BY PBF
		sfdc.cQuote.clickCreateQuotePbfButton();
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

			// Verify Service location has Business Internet plans
			sfdc.siteSelPBF.verifyIconInServiceColumn();

			sfdc.siteSelPBF.selectBusinessInternetAddress();

		}

		// Validate shopping Cart Page and Proceed
		sfdc.commPBFShopCart.validateShoppingCartPageAndProceed();

		if (!InputData_Communities.commPBFBundledFirstProd.equals("TV")) {
			// Click on Shop Plans for Business Internet
			sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();
	
			// Validate List of Business Products
			sfdc.commPBFCablePlan.validateBusinessProductsNames();
	
			// Add to Cart a product and proceed to checkout
			sfdc.commPBFCablePlan.addToCartProduct();
			
			sfdc.commPBFCablePlan.clickNextOnBusInt();
			
			//Validate Office 365 AddOn Names
			sfdc.off365AddOnPBF.validateOff365AddOnsProductNames();
			
			//Validate Office 365 AddOn Description
			//sfdc.off365AddOnPBF.validateOff365ProductsDescription();
			
			//Validate Office 365 Promo Section
			//sfdc.off365AddOnPBF.validateDiscountAndPromoSectionsForOff365AddOns();
			
			//Add to Cart an AddON and proceed to shopping Cart
			sfdc.off365AddOnPBF.addToCartOff365AddOnProductAndProceed();
	
			// Validate User lands on Shopping Cart Page with added product
			sfdc.commPBFShopCart.validateShoppingCartPageWithAddedInternetProduct();
	
			// QM and OM team Manage the Validation of Price details added product
			//sfdc.commPBFShopCart.validatePriceDetailsForAddedProduct();
	
			// Validate Office 365AddOn
			sfdc.commPBFShopCart.validateShoppingCartPageWithAddedOff365Qty();
			
			// QM and OM team Manage the Validation of TCV margin
			//sfdc.planSelPBF.validate_BusInt_Off365_TCV();
	
			// Validate waiveoff installation
			//sfdc.shopCartPBF.waiveOffInstallationFees();
	
			// Add installation fees again
			//sfdc.shopCartPBF.addInstallationFees();
			
			//Click on Add Business Products
			sfdc.commPBFShopCart.validateShoppingCartPageAndProceed();
			
		}

//		//Click on Add Business Products
//		sfdc.commPBFShopCart.validateShoppingCartPageAndProceed();
//		
		//Click on Business TV Shop Plans
		sfdc.siteSelPBF.moveToSiteSelectionPageForBusTV();
		
		//Validate TV product names
		sfdc.commPBFTVPlan.validateBusinessTVProductsNames();

		//Validate TV product Subnames
		//sfdc.commPBFTVPlan.validateBusinessTVSubProductsNames();

		//Select TV Product
		sfdc.commPBFTVPlan.selectTVProduct();
		
		sfdc.commPBFCablePlan.clickNextOnBusInt();
		
		// Validation Tv Addons
		sfdc.commPBFTVAddons.validateTVAddOnsProductNames();

		// Addons To be Selected
		sfdc.commPBFTVAddons.addTVAddon();
		
		sfdc.commPBFCablePlan.proceedtoCheckout();
		
		// validate Shopping cart Added TV Product with Addons
		sfdc.commPBFShopCart.validateShoppingCartPageWithAddedTVProduct();

		// after adding TV product if the business internet and office 365 products needs to be added
		if (InputData_Communities.commPBFBundledFirstProd.equals("TV")) {
			
			//Validate shopping Cart Page and Proceed
			sfdc.commPBFShopCart.validateShoppingCartPageAndProceed();
			
			//Click on Shop Plans for Business Internet
			sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();
			
			// Add to Cart a product and proceed to checkout
			sfdc.commPBFCablePlan.addToCartProduct();
			
			sfdc.commPBFCablePlan.clickNextOnBusInt();
			
			//Add to Cart an AddON and proceed to shopping Cart
			sfdc.off365AddOnPBF.addToCartOff365AddOnProductAndProceed();

			// Validate User lands on Shopping Cart Page with added product
			sfdc.commPBFShopCart.validateShoppingCartPageWithAddedInternetProduct();
			
			// QM and OM team Manage the Validation of Price details added Office 365AddOn
			//sfdc.commPBFShopCart.validatePriceDetailsForTVBusIntOff365Product();

		} 
		//Commented pricing validation
		/*
			 * else {
			 * 
			 * sfdc.commPBFShopCart.validatePriceDetailsForBusIntOff365TVProduct(); }
			 */
		// QM and OM team Manage the Validation of TCV margin
		// sfdc.planSelPBF.validate_BusInt_Off365_TV_TCV();

		//To check order value>500
		if (InputData_Communities.commPBFFraudCheckRequired.equalsIgnoreCase("No"))
			sfdc.planSelPBF.checkMonthlyTotalForFraudReview();
				
		// Proceed to checkout
		sfdc.commPBFShopCart.proceedToCheckout();

		// Validate Order Review Page
		sfdc.commPBFOrderReview.validateOrderReviewPageWithoutValues();
		
		// // QM and OM team Manage the Validation of Price details on Order Review Page
		/*
		 * if (InputData_Communities.commPBFBundledFirstProd.equals("TV"))
		 * sfdc.commPBFShopCart.validatePriceDetailsForTVBusIntOff365Product(); else
		 * sfdc.commPBFShopCart.validatePriceDetailsForBusIntOff365TVProduct();
		 */

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

//		// Validate Credit and Fraud Check Warning Message
//		sfdc.commPBFOrderReview.validateCreditAndFraudCheckMessage();

		// Confirm Order
		if (InputData_Communities.commPBFESignature.equalsIgnoreCase("No"))
			sfdc.commPBFOrderReview.confirmAndPlaceOrder();
		else {
			sfdc.orRevPBF.sendQuoteForESigApproval();
			sfdc.gdPdf.generatePDFNextButton();

			sfdc.orderDetails.verifyContractAndQuoteDetails(sf.dataInput.awaitingSign, sf.dataInput.awaitingSign, "",
					"");
			if (InputData_Communities.commPBFESignature.equalsIgnoreCase("Accept")) {

				sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(sf.dataInput.siteContactEmailId, "Signed");
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
			sfdc.orderDetails.verifyProductDetailsForTvBusInt();

		} else {
			if (InputData_Communities.commPBFESignature.equalsIgnoreCase("No")) {
				// Validate Order Confirmation Screen
				sfdc.orConfPBF.validateOrderConfirmationScreenForRDI();

				// Go to Order Details
				sfdc.commPBFOrderConf.clickOnOrderNumber();
				// Update order with site contact if required
				sfdc.orders.updatePBFOrderWithSiteContact();
				sfdc.orderDetails.validateOrderDetails();
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
		return getDataSetsRunMode(Constants.TESTDATA_COMM_FILE, Constants.PBF_AGENT_SHEET_ALLENV_BITVOFF);
	}
}
