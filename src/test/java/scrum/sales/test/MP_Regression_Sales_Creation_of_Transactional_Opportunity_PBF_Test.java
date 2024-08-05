package scrum.sales.test;

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
 * @author Nandan.More, Date 31/Dec/2021
 * 
 *         MP Regression Sales Creation of Transactional Opportunity PBF Test
 *        
 *		 
 */
public class MP_Regression_Sales_Creation_of_Transactional_Opportunity_PBF_Test extends BaseDataProvider {

	/**
	 * @throws Exception
	 * 
	 *                   1. Login as Account Executive >> Click App launcher >> From
	 *                   All apps >> Select "R4B Sales Console" >> Select Accounts
	 *                   from dropdown >> All Accounts >> Select any existing
	 *                   Account >> click "Overview" tab under this account >> hit cases>>
	 *                   Open any created case >> hit "Add New Service" button from drop down
	 * 
	 *                   2. "Add New Service" button redirect user to the opp creation
	 *                   flow, which asks user to select and complete order same as Create quote by PBF
	 * 
	 */

		@Test(dataProvider = "PBFAgentData")
		public void test_ValidateTransactionalOpportunityPage(Hashtable<String, String> dataTable) throws Exception {

			SFDC_AllPages sfdc = new SFDC_AllPages();
			InputData_Communities.setDataForPBFE2E(dataTable);

			sfdc.login.loginToSFDC(InputData.userid_service);
			
			sfdc.home.closeTabIfOpen();
			Global.dataInput.tempBusinessAccountName = "Auto_QA_BA_ 210610012889";
			sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
			sfdc.accRelated.createOpportunityThrCase();
			sfdc.cOpp.enterTransactionalOpportunityDetails();

			sfdc.siteSelPBF.moveToSiteSelectionPageForDedInt();
			sfdc.siteSelPBF.verifyPBFLandingPage();
			sfdc.siteSelPBF.verifyLegendSection();

			if (InputData_Communities.commPBFAddNewSite.equals("Yes")) {
				// Verify New Site added in PBF appears in related accounts in SF

				// Extract No if service addesses before adding new site in PBF
				sfdc.login.loginToSFDCInNewTab(InputData.userid_service);
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

				sfdc.siteSelPBF.selectRDIOnNetAddress();

			}

			// Validate shopping Cart Page and Proceed
			sfdc.commPBFShopCart.validateShoppingCartPageAndProceed();

			// Click on Shop Plans for Business Internet
			sfdc.siteSelPBF.moveToSiteSelectionPageForDedInt();

			// Validate List of Business Products
			sfdc.planSelPBF.validateDedicatedInternetProductsNames();

			// Validate 3 year and monthly term options
			sfdc.planSelPBF.validatePlanTermsOptionsForRDI();

			// Validate Page Layout
			sfdc.planSelPBF.validateDedicatedInternetProductsDescription();

			// Validate discount and promo section
			sfdc.planSelPBF.validateDiscountAndPromoSectionsForRDI();

			// Add to Cart a product and proceed to checkout
			sfdc.planSelPBF.addToCartRDIProductAndProceed();
			
			if (!InputData_Communities.commPBFEditEthernetPlan.equals("No"))
				sfdc.shopCartPBF.editEthernetPlanBackToOriginal();

			// Validate User lands on Shopping Cart Page with added product
			sfdc.commPBFShopCart.validateShoppingCartPageWithAddedRDIProduct();

			// Validate Price details of added product
			if (sf.dataInput.env.equalsIgnoreCase("ITDEVSTAGE"))
				sfdc.commPBFShopCart.validatePriceDetailsForAddedRDIProduct();

			// Remove Product
			sfdc.commPBFShopCart.removeProduct();

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

			// Validate TCV margin
			sfdc.planSelPBF.validate_RDI_TCV();

			if (!(InputData_Communities.commPBFPromo.equalsIgnoreCase("AE") || InputData_Communities.commPBFPromo.equalsIgnoreCase("AO")))
			{
				// Validate waiveoff installation
				sfdc.shopCartPBF.waiveOffInstallationFees();

				// Add installation fees again
				sfdc.shopCartPBF.addInstallationFees();
						
			}
			

			// Proceed to checkout
			sfdc.commPBFShopCart.proceedToCheckout();

			// Validate Order Review Page
			sfdc.commPBFOrderReview.validateOrderReviewPageWithoutValues();

			// Validate Price Details on Order Review Page
			sfdc.commPBFShopCart.validatePriceDetailsForAddedRDIProduct();

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

			// Validate Credit and Fraud Check Warning Message
//			if (sf.dataInput.env.equalsIgnoreCase("ITDEVSTAGE"))
//				sfdc.commPBFOrderReview.validateCreditAndFraudCheckMessage();

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

				sfdc.login.loginToSFDC(InputData.userid_service);
				sfdc.home.openR4BSalesConsole();
				sfdc.home.closeTabIfOpenWithRefresh();
				sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
				sfdc.quoteDetails.verifyQuoteStatusAfterCreditCheck(sf.dataInput.quoteStatusFinalized,
						sf.dataInput.quoteStatusApproved);
				sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
				sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusReadyToSubmit);
				sfdc.orderDetails.verifyProductDetails();
				sfdc.comReviewSpecSheet.enterSpecSheetValues(true, dataTable);

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

				sfdc.login.loginToSFDC(InputData.userid_service);
				sfdc.home.openR4BSalesConsole();
				sfdc.home.closeTabIfOpenWithRefresh();
				sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
				// sfdc.quotes.searchQuoteGlobalSearch("Aauto210319033924-00007728");
				sfdc.quoteDetails.verifyQuoteStatusAfterFraudCheck(sf.dataInput.quoteStatusFinalized,
						sf.dataInput.quoteStatusApproved);
				sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
				sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusReadyToSubmit);
				sfdc.orderDetails.verifyProductDetails();
				sfdc.comReviewSpecSheet.enterSpecSheetValues(true, dataTable);

			} else {
				if (InputData_Communities.commPBFESignature.equalsIgnoreCase("No")) {
					// Validate Order Confirmation Screen
					sfdc.orConfPBF.validateOrderConfirmationScreenForRDI();

					// Go to Order Details
					sfdc.commPBFOrderConf.clickOnOrderNumber();
					// Update order with site contact if required
					sfdc.orders.updatePBFOrderWithSiteContact();
					sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusReadyToSubmit);
					sfdc.orderDetails.verifyProductDetails();
					sfdc.comReviewSpecSheet.enterSpecSheetValues(true, dataTable);

				} else if (InputData_Communities.commPBFESignature.equalsIgnoreCase("Accept")) {
					// Open order from Quote
					sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
					// Update order with site contact if required
					sfdc.orders.updatePBFOrderWithSiteContact();
					sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusReadyToSubmit);
					sfdc.orderDetails.verifyProductDetails();
				} else {
					// Open order from Quote
					sfdc.quoteRelated.verifyOrderNotCreatedInQuoteRelatedAfterApproved();

				}

			}
			
			sfdc.login.loginToSFDC(InputData.Profile_ThirdParty);
			sfdc.home.closeTabIfOpen();
			Global.dataInput.tempBusinessAccountName = "Auto_QA_BA_ 210610012889";
			sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
			sfdc.accRelated.createOpportunityThrCase();
			sfdc.cOpp.enterTransactionalOpportunityDetails();

			sfdc.siteSelPBF.moveToSiteSelectionPageForDedInt();
			sfdc.siteSelPBF.verifyPBFLandingPage();
			sfdc.siteSelPBF.verifyLegendSection();

			if (InputData_Communities.commPBFAddNewSite.equals("Yes")) {
				// Verify New Site added in PBF appears in related accounts in SF

				// Extract No if service addesses before adding new site in PBF
				sfdc.login.loginToSFDCInNewTab(InputData.userid_service);
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

				sfdc.siteSelPBF.selectRDIOnNetAddress();

			}

			// Validate shopping Cart Page and Proceed
			sfdc.commPBFShopCart.validateShoppingCartPageAndProceed();

			// Click on Shop Plans for Business Internet
			sfdc.siteSelPBF.moveToSiteSelectionPageForDedInt();

			// Validate List of Business Products
			sfdc.planSelPBF.validateDedicatedInternetProductsNames();

			// Validate 3 year and monthly term options
			sfdc.planSelPBF.validatePlanTermsOptionsForRDI();

			// Validate Page Layout
			sfdc.planSelPBF.validateDedicatedInternetProductsDescription();

			// Validate discount and promo section
			sfdc.planSelPBF.validateDiscountAndPromoSectionsForRDI();

			// Add to Cart a product and proceed to checkout
			sfdc.planSelPBF.addToCartRDIProductAndProceed();
			
			if (!InputData_Communities.commPBFEditEthernetPlan.equals("No"))
				sfdc.shopCartPBF.editEthernetPlanBackToOriginal();

			// Validate User lands on Shopping Cart Page with added product
			sfdc.commPBFShopCart.validateShoppingCartPageWithAddedRDIProduct();

			// Validate Price details of added product
			if (sf.dataInput.env.equalsIgnoreCase("ITDEVSTAGE"))
				sfdc.commPBFShopCart.validatePriceDetailsForAddedRDIProduct();

			// Remove Product
			sfdc.commPBFShopCart.removeProduct();

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

			// Validate TCV margin
			sfdc.planSelPBF.validate_RDI_TCV();

			if (!(InputData_Communities.commPBFPromo.equalsIgnoreCase("AE") || InputData_Communities.commPBFPromo.equalsIgnoreCase("AO")))
			{
				// Validate waiveoff installation
				sfdc.shopCartPBF.waiveOffInstallationFees();

				// Add installation fees again
				sfdc.shopCartPBF.addInstallationFees();
						
			}
			

			// Proceed to checkout
			sfdc.commPBFShopCart.proceedToCheckout();

			// Validate Order Review Page
			sfdc.commPBFOrderReview.validateOrderReviewPageWithoutValues();

			// Validate Price Details on Order Review Page
			sfdc.commPBFShopCart.validatePriceDetailsForAddedRDIProduct();

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

			// Validate Credit and Fraud Check Warning Message
//			if (sf.dataInput.env.equalsIgnoreCase("ITDEVSTAGE"))
//				sfdc.commPBFOrderReview.validateCreditAndFraudCheckMessage();

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

				sfdc.login.loginToSFDC(InputData.Profile_ThirdParty);
				sfdc.home.openR4BSalesConsole();
				sfdc.home.closeTabIfOpenWithRefresh();
				sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
				sfdc.quoteDetails.verifyQuoteStatusAfterCreditCheck(sf.dataInput.quoteStatusFinalized,
						sf.dataInput.quoteStatusApproved);
				sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
				sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusReadyToSubmit);
				sfdc.orderDetails.verifyProductDetails();
				sfdc.comReviewSpecSheet.enterSpecSheetValues(true, dataTable);

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

				sfdc.login.loginToSFDC(InputData.Profile_ThirdParty);
				sfdc.home.openR4BSalesConsole();
				sfdc.home.closeTabIfOpenWithRefresh();
				sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
				// sfdc.quotes.searchQuoteGlobalSearch("Aauto210319033924-00007728");
				sfdc.quoteDetails.verifyQuoteStatusAfterFraudCheck(sf.dataInput.quoteStatusFinalized,
						sf.dataInput.quoteStatusApproved);
				sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
				sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusReadyToSubmit);
				sfdc.orderDetails.verifyProductDetails();
				sfdc.comReviewSpecSheet.enterSpecSheetValues(true, dataTable);

			} else {
				if (InputData_Communities.commPBFESignature.equalsIgnoreCase("No")) {
					// Validate Order Confirmation Screen
					sfdc.orConfPBF.validateOrderConfirmationScreenForRDI();

					// Go to Order Details
					sfdc.commPBFOrderConf.clickOnOrderNumber();
					// Update order with site contact if required
					sfdc.orders.updatePBFOrderWithSiteContact();
					sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusReadyToSubmit);
					sfdc.orderDetails.verifyProductDetails();
					sfdc.comReviewSpecSheet.enterSpecSheetValues(true, dataTable);

				} else if (InputData_Communities.commPBFESignature.equalsIgnoreCase("Accept")) {
					// Open order from Quote
					sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
					// Update order with site contact if required
					sfdc.orders.updatePBFOrderWithSiteContact();
					sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusReadyToSubmit);
					sfdc.orderDetails.verifyProductDetails();
				} else {
					// Open order from Quote
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
			return getDataSetsRunMode(Constants.TESTDATA_COMM_FILE, Constants.PBF_AGENT_SHEET_ALLENV_RDI);
		}
	}
