package scrum.qm.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.data.InputData_PartnerCommunities;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla, 14/05/2021
 * 
 *         Create business account from Partner communities
 *
 * 
 */
public class MP_QM_TC_03_PartnerCommunities_PBF_E2E_BusIntTVOff365_CompactFlow_Test extends Base {

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
	public void test_e2ePBFPartnerCommunitites_BusIntOff365TV(Hashtable<String, String> dataTable) throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		InputData_Communities.setDataForPBFE2E(dataTable);
		if (InputData_Communities.commPBFUser.equalsIgnoreCase("Dealer"))
			sfdc.partnerCommLogin.loginToPartnerCommunities(InputData_PartnerCommunities.partnerCommunities_dealer_userid);
		else
			sfdc.partnerCommLogin.loginToPartnerCommunities(InputData_PartnerCommunities.partnerCommunities_var_userid);
		
//		sfdc.partnerCommHome.launchCreateNewBusinessAccount();
//
//		// Create Account
//		sfdc.acc.selectBusinessAccType();
//		sfdc.cba.enterBusinessAccountInfo(false);
//		sfdc.cba.verifyBusinessAccountCreated();
//		sfdc.cc.enterCreateContactInfo(false);
//		sfdc.cc.verifyMarkPrimaryContactReadOnly();
//		sfdc.cc.verifyContactCreated();
//		sfdc.cc.clickOnNextInCreateContact();
//		sfdc.csa.enterServiceAccountInfo(1);
//		sfdc.csa.clickOnNextInCreateServiceAccount();
//		sfdc.csa.checkCreateBillingAccount();
//		sfdc.cbia.enterBillingAccountInfo();
//		sfdc.cbia.verifyBillingAccountCreated();
//		sfdc.home.navigateURL();
//		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
//		sfdc.home.closeTabIfOpen();
//		// sfdc.accDetails.searchAccount(sf.dataInput.businessAccountName);
//		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
//		sfdc.accRelated.approveAsDataGovernance();
//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
//		sfdc.partnerCommLogin.loginToPartnerCommunities(InputData_PartnerCommunities.partnerCommunities_dealer_userid);

//		
		// Validate newly created business acc
		sfdc.partnerCommAccDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
//		
////		
//		//Create new Opportunity
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
		
		//Validate shopping Cart Page and Proceed
		sfdc.commPBFShopCart.validateShoppingCartPageAndProceed();
		
		if (!InputData_Communities.commPBFBundledFirstProd.equals("TV")) {
			
			//Click on Shop Plans for Business Internet
			sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();
			
			// Validate List of Business Products
			sfdc.commPBFCablePlan.validateBusinessProductsNames();
	
			// Add to Cart a product and proceed to checkout
			sfdc.commPBFCablePlan.addToCartProduct();
			
			sfdc.commPBFCablePlan.clickNextOnBusInt();
			
			//Validate Office 365 AddOn Names
			sfdc.off365AddOnPBF.validateOff365AddOnsProductNames();
			
			//Validate Office 365 AddOn Description
			sfdc.off365AddOnPBF.validateOff365ProductsDescription();
			
			//Validate Office 365 Promo Section
			sfdc.off365AddOnPBF.validateDiscountAndPromoSectionsForOff365AddOns();
			
			//Add to Cart an AddON and proceed to shopping Cart
			sfdc.off365AddOnPBF.addToCartOff365AddOnProductAndProceed();
	
			// Validate User lands on Shopping Cart Page with added product
			sfdc.commPBFShopCart.validateShoppingCartPageWithAddedInternetProduct();
	
			// Validate Price details of added product
			sfdc.commPBFShopCart.validatePriceDetailsForAddedProduct();
	
			// Validate Price details of added Office 365AddOn
			sfdc.commPBFShopCart.validateShoppingCartPageWithAddedOff365Product();
			
			// Validate TCV margin
			sfdc.planSelPBF.validate_BusInt_Off365_TCV();
	
			// Validate waiveoff installation
			sfdc.shopCartPBF.waiveOffInstallationFees();
	
			// Add installation fees again
			sfdc.shopCartPBF.addInstallationFees();
			
			//Click on Add Business Products
			sfdc.commPBFShopCart.validateShoppingCartPageAndProceed();
		}
		
		//Click on Business TV Shop Plans
		sfdc.siteSelPBF.moveToSiteSelectionPageForBusTV();
		
		//Validate TV product names
		sfdc.commPBFTVPlan.validateBusinessTVProductsNames();

		//Validate TV product Subnames
		sfdc.commPBFTVPlan.validateBusinessTVSubProductsNames();

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
			
			sfdc.commPBFShopCart.validatePriceDetailsForTVBusIntOff365Product();

		} else {
		
			sfdc.commPBFShopCart.validatePriceDetailsForBusIntOff365TVProduct();
		}
		
		// Validate TCV margin
		sfdc.planSelPBF.validate_BusInt_Off365_TV_TCV();

		// Proceed to checkout
		sfdc.commPBFShopCart.proceedToCheckout();

		// Validate Order Review Page
		sfdc.commPBFOrderReview.validateOrderReviewPageWithoutValues();

		// Validate Price Details on Order Review Page
		if (InputData_Communities.commPBFBundledFirstProd.equals("TV")) 	
			sfdc.commPBFShopCart.validatePriceDetailsForTVBusIntOff365Product();
		else
			sfdc.commPBFShopCart.validatePriceDetailsForBusIntOff365TVProduct();


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
			
			if (InputData_Communities.commPBFUser.equalsIgnoreCase("Dealer"))
				sfdc.partnerCommLogin.loginToPartnerCommunities(InputData_PartnerCommunities.partnerCommunities_dealer_userid);
			else
				sfdc.partnerCommLogin.loginToPartnerCommunities(InputData_PartnerCommunities.partnerCommunities_var_userid);
			
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
				sfdc.orderDetails.verifyProductDetailsForTvBusInt();
			} else if(InputData_Communities.commPBFESignature.equalsIgnoreCase("Accept") ){
				//Open order from Quote
				sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
				//Update order with site contact if required
				sfdc.orders.updatePBFOrderWithSiteContact();
				sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);
				sfdc.orderDetails.verifyProductDetailsForTvBusInt();
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
		return getDataSetsRunMode(Constants.TESTDATA_COMM_FILE, Constants.PBF_AGENT_SHEET_ALLENV_BITVOFF);
	}
}
