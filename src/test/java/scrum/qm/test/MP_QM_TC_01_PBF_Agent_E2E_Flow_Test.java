package scrum.qm.test;

import java.awt.color.ProfileDataException;
import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.BaseDataProvider;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla
 * 
 *        
 *         
 */
public class MP_QM_TC_01_PBF_Agent_E2E_Flow_Test extends BaseDataProvider {

	/**
	 * @throws Exception 
	 */
	@Test(dataProvider = "PBFAgentData")
	public void test_validate_PBF_E2E_Agent(Hashtable<String, String> dataTable) throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		InputData_Communities.setDataForPBFE2E(dataTable);

		// Login to PBF and verify landing page
		sfdc.login.loginToSFDC(InputData.Profile_AE);
//		sfdc.home.closeTabIfOpen();
//		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
//		//Create Opportunity with required contact as signing authority
//		Global.dataInput.accountContact = InputData_Communities.commPBFContactFullName;
//		sfdc.accRelated.createOpportunity();
//		sfdc.cOpp.enterOpportunityDetails();
//		
//		sfdc.cOpp.selectOpportunityContactRole();
//		
		//Create Quote BY PBF
		sfdc.cQuote.clickCreateQuotePbfButton();
		sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();
		sfdc.siteSelPBF.verifyPBFLandingPage();
//		sfdc.siteSelPBF.verifyLegendSection();

		if (InputData_Communities.commPBFAddNewSite.equals("Yes")) {
			//Verify New Site added in PBF appears in related accounts in SF
			
//			//Extract No if service addesses before adding new site in PBF
//			sfdc.login.loginToSFDCInNewTab(InputData.Profile_AE);
//			sfdc.home.closeTabIfOpen();
//			sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
//			sfdc.accHirchy.extractAllRelatedServiceAccForBusAcc();
//			sfdc.accHirchy.extractNoOfRelatedServiceAcc();
//			
//			//Add New Site in PBF
//			sf.seleU.switchWindow(1);
			sfdc.commPBF.addNewSite();
			sfdc.commPBFAddSite.fillSiteInfo();
			sfdc.commPBFAddSite.verifyServiceAddressesTableLayout();
			sfdc.commPBFAddSite.selectAddress();
			
			sfdc.commPBFAddSite.confirmAndProceed();
//			sf.seleU.switchWindow(2);
//			
//			//Verify New Site added in PBF appears in related accounts in SF
//			sfdc.accHirchy.verifyNoOfRelatedServiceAccIncreased();
//			sf.seleU.switchWindow(1);
//			sf.seleU.closeRecentlyOpenedWindow();
			
		} else {
			//Verify All service addresses related to the account are listed, Skip the Step if Service Address has NA in Excel
//			if (!InputData_Communities.commPBFServiceAddresses.get(0).equals("NA")) {
//				sfdc.commPBF.verifyServiceAddresses();
//			
//				//Verify Service Table Layout
//				sfdc.commPBF.verifyServiceAddressesTableLayout();
//			}
//			
//			//Verify Service location has Business Internet plans
			sfdc.siteSelPBF.verifyIconInServiceColumn();
			
			sfdc.commPBF.selectAddress();
	
			
		}
		
		//Validate shopping Cart Page and Proceed
		sfdc.commPBFShopCart.validateShoppingCartPageAndProceed();
		
		//Click on Shop Plans for Business Internet
		sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();
		
//		//Validate List of Business Products		
//		sfdc.commPBFCablePlan.validateBusinessProductsNames();
//		
//		//Validate List of Business Products		
//		sfdc.planSelPBF.validateBusinessProductsSubNames();
//		
//		//Validate 3 year and monthly term options		
//		sfdc.planSelPBF.validatePlanTermsOptions();
		
//		//Validate Order of Speed of Plans Listing is decreasing order
//		sfdc.commPBFCablePlan.validateOrderOfSpeedOfPlans();
//		
//		//Validate Page Layout
//		sfdc.commPBFCablePlan.validateCablePlanSelectionPageLayout();
		
//		//Validate Description of Products
//		sfdc.commPBFCablePlan.validateProductsDescription();
//		
//		//Validate Tool Tip texts for all product types
//		sfdc.commPBFCablePlan.validateProductsToolTipTexts();
//		
//		//Validate All Plans Offers List
//		sfdc.commPBFCablePlan.validateAllPlansOffers();
//		
//		//Validate discount and promo section		
//		sfdc.planSelPBF.validateDiscountAndPromoSections();
//				
		// Add to Cart a product and proceed to checkout
		sfdc.commPBFCablePlan.addToCartProduct();
		
		sfdc.commPBFCablePlan.proceedtoCheckout();
		
		//Validate User lands on Shopping Cart Page with added product
		sfdc.commPBFShopCart.validateShoppingCartPageWithAddedInternetProduct();
		
		//Validate Price details of added product
		sfdc.commPBFShopCart.validatePriceDetailsForAddedProduct();
		
//		//Remove Product
//		sfdc.commPBFShopCart.removeProduct();
//		
//		//Validate shopping Cart Page and Proceed
//		sfdc.commPBFShopCart.validateShoppingCartPageAndProceed();
//		
//		//Click on Shop Plans for Business Internet
//		sfdc.siteSelPBF.moveToSiteSelectionPage();
//		
//				
//		//Add to Cart a product and proceed to checkout
//		sfdc.commPBFCablePlan.addToCartProductAndProceed();
//		
//		//Validate User lands on Shopping Cart Page with added product
//		sfdc.commPBFShopCart.validateShoppingCartPageWithAddedProduct();
//		
		//Validate TCV margin
		sfdc.planSelPBF.validate_Internet_TCV();
		
//		//Validate waiveoff installation
//		sfdc.shopCartPBF.waiveOffInstallationFees();
//		
//		//Add installation fees again
//		sfdc.shopCartPBF.addInstallationFees();
		
		//Proceed to checkout
		sfdc.commPBFShopCart.proceedToCheckout();
		
		//Validate Order Review Page
		sfdc.commPBFOrderReview.validateOrderReviewPage();
		
		//Validate Price Details on Order Review Page
		sfdc.commPBFShopCart.validatePriceDetailsForAddedProduct();
		
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
			sfdc.siteCon.selectSpecificExistingSiteContact(InputData_Communities.commPBFContactFullName);
			sfdc.orderDetails.verifyContractAndQuoteDetails(sf.dataInput.awaitingSign, sf.dataInput.awaitingSign, "",
					"");
			if (InputData_Communities.commPBFESignature.equalsIgnoreCase("Accept")) {
				
				sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(InputData_Communities.commPBFSAEmailID, "Signed");
				sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(sf.dataInput.finalisedStatus, sf.dataInput.signedStatus);
			
			} else {
				sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(InputData_Communities.commPBFSAEmailID, "Reject");
				sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(sf.dataInput.finalisedStatus, sf.dataInput.declinedStatus);
	
			}
				
		}
		
		if (InputData_Communities.commPBFCreditCheckRequired.equals("Yes")) {
			//Validate Order Confirmation Screen
			sfdc.orConfPBF.validateOrderConfirmationScreenForCreditOrFraudCheckAcc();
			sfdc.home.logout();
			
			//Login with Credit Ops and approve credit check
			sfdc.login.loginToSFDC(InputData.Profile_CreditOps);
			sfdc.home.closeTabIfOpenWithRefresh();
			//sf.dataInput.quoteName = "00013178";
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			sfdc.quoteRelated.approveR4BQuoteForCreditCheck();
			sfdc.home.logout();
			
			sfdc.login.loginToSFDC(InputData.Profile_AE);
			sfdc.home.openR4BSalesConsole();
			sfdc.home.closeTabIfOpenWithRefresh();
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			sfdc.quoteDetails.verifyQuoteStatusAfterCreditCheck(sf.dataInput.quoteStatusFinalized, sf.dataInput.quoteStatusApproved);
			sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);
			
		} else if (InputData_Communities.commPBFFraudCheckRequired.equals("Yes")) {
			//Validate Order Confirmation Screen
			sfdc.orConfPBF.validateOrderConfirmationScreenForCreditOrFraudCheckAcc();
			sfdc.home.logout();
			//Login with Fraud Ops and approve fraud check
			sfdc.login.navigateURL();
			//sf.dataInput.quoteName = "fraudCheckForPBF-00015592";
			sfdc.quoteDetails.checkCreditOrFraud(sf.dataInput.quoteName, false, "Approve");
			
			sfdc.login.loginToSFDC(InputData.Profile_AE);
			sfdc.home.openR4BSalesConsole();
			sfdc.home.closeTabIfOpenWithRefresh();
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			// sfdc.quotes.searchQuoteGlobalSearch("Aauto210319033924-00007728");
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
		return getDataSetsRunMode(Constants.TESTDATA_COMM_FILE, Constants.PBF_AGENT_SHEET);
	}
}
