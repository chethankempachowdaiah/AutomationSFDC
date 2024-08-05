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
 * @author Anukriti.Chawla
 * 
 */
public class MP_QM_TC_01_PBF_Agent_E2E_BusinessInternet_CompactFlow_Test extends Base {

	/**
	 * @throws Exception
	 */
	@Test(dataProvider = "PBFAgentData")
	public void test_validate_PBF_E2E_Agent(Hashtable<String, String> dataTable) throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();
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

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		
		if (sf.dataInput.frenchEnabled.equalsIgnoreCase("Yes")) {
			sfdc.userProfile.changeLanguageToFrench();
		}
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

//			// Extract No if service addesses before adding new site in PBF
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

		// Click on Shop Plans for Business Internet
		sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();

		// Validate List of Business Products
		sfdc.commPBFCablePlan.validateBusinessProductsNames();

		// Validate List of Business Products
		sfdc.planSelPBF.validateBusinessProductsSubNames();

		// Validate 3 year and monthly term options
		sfdc.planSelPBF.validatePlanTermsOptionsForBI();

		// Validate Order of Speed of Plans Listing is decreasing order
		sfdc.commPBFCablePlan.validateOrderOfSpeedOfPlans();

		// Validate Page Layout
		sfdc.commPBFCablePlan.validateCablePlanSelectionPageLayout();

		// Validate Description of Products
		sfdc.commPBFCablePlan.validateProductsDescription();

		// Validate Tool Tip texts for all product types
		sfdc.commPBFCablePlan.validateProductsToolTipTexts();

		// Validate All Plans Offers List - Deferred due to 49117
		//sfdc.commPBFCablePlan.validateAllPlansOffers();
		 
		// Validate discount and promo section
		if(InputData_Communities.commPBFPromo.equalsIgnoreCase("Yes")) {
		sfdc.planSelPBF.validateDiscountAndPromoSections();

		// Select Discount product
		sfdc.planSelPBF.selectDiscountAndPromoSectionsProduct();
		
		sfdc.commPBFCablePlan.addToCartProduct();

		// Click on Proceed to check out
		sfdc.commPBFCablePlan.proceedtoCheckout();

		// Validate TCV margin QM and OM team Manage price validation
		//sfdc.planSelPBF.validate_Internet_TCV_Discount_Product();

		// Remove Product
		sfdc.commPBFShopCart.removeProduct();
		
		// Validate TCV margin QM and OM team Manage price validation in Shopping cart page
		//sfdc.planSelPBF.validate_BusinessInternet_TCV_Shopping_Cart();
		}
				
		//commented by Prab Sharan due to the script failure
		// Validate shopping Cart Page and Proceed
		sfdc.commPBFShopCart.validateShoppingCartPageAndProceed();

		// Click on Shop Plans for Business Internet
		sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();

		// Add to Cart a product and proceed to checkout
		sfdc.commPBFCablePlan.addToCartProduct();

		sfdc.commPBFCablePlan.proceedtoCheckout();

		// Validate User lands on Shopping Cart Page with added product
		sfdc.commPBFShopCart.validateShoppingCartPageWithAddedInternetProduct();
    
		// Validate TCV margin QM and OM team Manage price validation
		//sfdc.planSelPBF.validate_Internet_TCV();

		// Validate waiveoff installation
		sfdc.shopCartPBF.waiveOffInstallationFees();

		// Add installation fees again
		sfdc.shopCartPBF.addInstallationFees();

		//To check order value>500
		if (InputData_Communities.commPBFFraudCheckRequired.equalsIgnoreCase("No"))
			sfdc.planSelPBF.checkMonthlyTotalForFraudReview();
				
		// Proceed to checkout
		sfdc.commPBFShopCart.proceedToCheckout();

		// Validate Order Review Page
		sfdc.commPBFOrderReview.validateOrderReviewPageWithoutValues();

		// QM and OM team Manage price validation on Order Review Page
		//sfdc.commPBFShopCart.validatePriceDetailsForAddedProduct();

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

			if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
			sfdc.orderDetails.verifyContractAndQuoteDetails(sf.dataInput.awaitingSign, sf.dataInput.awaitingSign, "","");
			} else {
				sfdc.orderDetails.verifyContractAndQuoteDetails(sf.dataInput.awaitingSignFr, sf.dataInput.awaitingSignFr, "","");
			}
			if (InputData_Communities.commPBFESignature.equalsIgnoreCase("Accept")) {
				
				sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(sf.dataInput.siteContactEmailId, "Signed");
				if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
				sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(sf.dataInput.finalisedStatus,sf.dataInput.signedStatus);
				} else {
				sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(sf.dataInput.finalisedStatusFr,sf.dataInput.signedStatusFr);
				}
				//

			} else {
				sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(sf.dataInput.siteContactEmailId, "Reject");
				if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
				sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(sf.dataInput.deniedStatus,
						sf.dataInput.declinedStatus);
				} else {
					sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(sf.dataInput.deniedStatusFr,
							sf.dataInput.declinedStatusFr);
				}

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
			
			if (InputData_Communities.commPBFCreditCheckRequired.equals("Yes") ) {
				if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
					sfdc.quoteDetails.verifyQuoteStatusAfterCreditCheck(sf.dataInput.quoteStatusFinalized,sf.dataInput.quoteStatusApproved);
				} else {
					sfdc.quoteDetails.verifyQuoteStatusAfterCreditCheck(sf.dataInput.quoteStatusFinalizedFr,sf.dataInput.quoteStatusApprovedFr);
				}
			} else {
				if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
					sfdc.quoteDetails.verifyQuoteStatusAfterFraudCheck(sf.dataInput.quoteStatusFinalized,sf.dataInput.quoteStatusApproved);
				} else {
					sfdc.quoteDetails.verifyQuoteStatusAfterFraudCheck(sf.dataInput.quoteStatusFinalizedFr,sf.dataInput.quoteStatusApprovedFr);
				}
			}
			sfdc.orderDetails.validateOrderDetailsAfterApproval();
//					sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
//					sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);
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
	
		if (InputData_Communities.commPBFAddSiteContact.equals("Yes")) {
			sfdc.home.closeTabIfOpen();
			sfdc.contacts.searchContactFromGlobalSearch(sf.dataInput.siteContactName);
			sfdc.contacts.validateSiteContactRole(sf.dataInput.businessAccountName, InputData.siteContactRole);
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
		return getDataSetsRunMode(Constants.TESTDATA_COMM_FILE, Constants.PBF_AGENT_SHEET_ALLENV_BI);
	}
}

