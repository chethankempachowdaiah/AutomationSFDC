package scrum.communities.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.BaseDataProvider;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.lib_pages.SFDC_AllPages;

public class MP_Communities_TC_08_PBF_Customer_E2E_CompactFlow_Sanity_Test extends BaseDataProvider {

	/**
	 * @throws Exception 
	 */
	@Test(dataProvider = "PBFCustomerData")
	public void test_validate_PBF_E2E_Customer(Hashtable<String, String> dataTable) throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		InputData_Communities.setDataForPBFE2E(dataTable);

		// ********************PBF_L1_E2E_Validate SA is able to place an order successfully_No Credit_No Fraud*************/
		// Login to PBF and verify landing page
		sfdc.commPBF.loginToCommunitiesPBF(sf.commData.communitiesPBFCredentialsForMultiAcc);
		
		if (sf.dataInput.frenchEnabled.equalsIgnoreCase("Yes"))
			sfdc.userProfile.changeToFrenchForCustomer();
		
		sfdc.commPBF.verifyPBFLandingPage();

		// Verify Business Account Dropdown Presence
		sfdc.commPBF.verifyBusinessAccDropdownPresent();

		//Select Business Account
		sfdc.commPBF.selectBusinessAccount();

		if (InputData_Communities.commPBFAddNewSite.equals("Yes")) {

			sfdc.commPBF.addNewSite();
			sfdc.commPBFAddSite.fillSiteInfo();
			sfdc.commPBFAddSite.selectAddress();

			sfdc.commPBFAddSite.confirmAndProceed();

		} else {

			sfdc.siteSelPBF.selectBusinessInternetAddress();

		}

		//Validate shopping Cart Page and Proceed
		sfdc.commPBFShopCart.validateShoppingCartPageAndProceed();

		sfdc.commPBFCablePlan.addToCartProduct();
		
		sfdc.commPBFCablePlan.proceedtoCheckout();

		//Validate User lands on Shopping Cart Page with added product
		sfdc.commPBFShopCart.validateShoppingCartPageWithAddedInternetProduct();

		//Proceed to checkout
		sfdc.commPBFShopCart.proceedToCheckout();

		//Validate Order Review Page
		sfdc.commPBFOrderReview.validateOrderReviewPageWithoutValues();

		//Validate Price Details on Order Review Page
		if(sf.dataInput.env.equalsIgnoreCase("ITDEVSTAGE"))
			sfdc.commPBFShopCart.validatePriceDetailsForAddedProduct();

		//Add Site Contact
		sfdc.siteConPBF.addSiteContact();

		//Validate Order Summary PDF
		sfdc.commPBFOrderReview.verifyOrderSummaryPDF();

		//Validate Cancel ANd Back redirection
		sfdc.commPBFOrderReview.verifyCancelAndBackRedirection();

		//Verify terms and conditions and Accept
		sfdc.commPBFOrderReview.verifyTermsAndCondtionsAndAccept();

		//Confirm Order
		sfdc.commPBFOrderReview.confirmAndPlaceOrder();

		if (InputData_Communities.commPBFCreditCheckRequired.equals("Yes")) {
			//Validate Order Confirmation Screen
			sfdc.commPBFOrderConf.validateOrderConfirmationScreenForCreditOrFraudCheckAcc();
			sfdc.comLogin.logoutFromCommunities();

			//Login with Credit Ops and approve credit check
			sfdc.login.navigateURL();
			sfdc.login.loginToSFDC(InputData.Profile_CreditOps);
			sfdc.home.closeTabIfOpenWithRefresh();
			//sf.dataInput.quoteName = "00013178";
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			// sfdc.quotes.searchQuoteGlobalSearch("BusinessPankaj03-00007170");
			sfdc.quoteRelated.approveR4BQuoteForCreditCheck();
			sfdc.home.logout();

			sfdc.login.loginToSFDC(InputData.Profile_AE);
			sfdc.home.openR4BSalesConsole();
			sfdc.home.closeTabIfOpenWithRefresh();
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			// sfdc.quotes.searchQuoteGlobalSearch("Aauto210319033924-00007728");
			sfdc.quoteDetails.verifyQuoteStatusAfterCreditCheck(sf.dataInput.quoteStatusFinalized, sf.dataInput.quoteStatusApproved);
			sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);

		} else if (InputData_Communities.commPBFFraudCheckRequired.equals("Yes")) {
			//Validate Order Confirmation Screen
			sfdc.commPBFOrderConf.validateOrderConfirmationScreenForCreditOrFraudCheckAcc();
			sfdc.comLogin.logoutFromCommunities();

			//Login with Fraud Ops and approve fraud check
			sfdc.login.navigateURL();
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
			//Validate Order Confirmation Screen
			sfdc.commPBFOrderConf.validateOrderConfirmationScreen();

			//Go to Order Details
			sfdc.commPBFOrderConf.clickOnOrderNumber();

			//Validate Order Details
			sfdc.comOrderDetails.verifyPBFOrderDetails();

			sfdc.comLogin.logoutFromCommunities();

			sfdc.login.navigateURL();
			sfdc.login.loginToSFDC(InputData.Profile_AE);
			sfdc.home.openR4BSalesConsole();
			sfdc.home.closeTabIfOpenWithRefresh();
			sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);

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
	public Object[][] PBFCustomerData() throws IOException {
		return getDataSetsRunMode(Constants.TESTDATA_COMM_FILE, Constants.PBF_CUSTOMER_SHEET_ALLENV);
	}
}

