package com.regression_01.test;

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
import com.sfdc.lib_pages.SFDC_AllPages;

public class MP_Regression_QM_GBJ_E2E_INT_TV_Standalone_Bundled_Test extends Base {

	@Test(dataProvider = "getE2EFlowTestData")
	public void test_Validate_GBJEndToEndFlow_INT_TV(Hashtable<String, String> dataTable) throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		setInputData(dataTable);

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
//
//		sfdc.login.loginToSFDC(InputData.userid_ae);
//		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
//		sfdc.accRelated.createOpportunity();
//		sfdc.cOpp.enterOpportunityDetails();
//		sfdc.cOpp.selecetExistingContactInOpportunity();

		/****************/
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();

		if (dataTable.get("Region").contains("ATL")) {
			sfdc.accDetails.searchBusAccGlobalSearch(InputData.account_ATL);
		} else {
			sfdc.accDetails.searchBusAccGlobalSearch(InputData.account_ON);
		}

		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();

		// sfdc.opp.verifyOpportunitiesObject();
		// sfdc.opp.selectOpportunity(sf.dataInput.account_ON);

		/******************/

		sfdc.cQuote.createQuoteGuidedByJourney_SelectExistingSigningAuthority();
		sfdc.cQuote.createQuote_SelectExistingServiceAccount();

		if (dataTable.get("Product _Type").equals(InputData.tvProduct)) {
			sfdc.gbjCart.addRogersBusinessSolutions_TV(dataTable, true);
			sfdc.gbjCart.addRogersTVAddOns(dataTable, true);
			sfdc.gbjCart.hitCheckOut();

			// sfdc.quoteReview.verifyDetailsinQuoteReview(dataTable, true);
			sfdc.quoteReview.verifyDetailsinQuoteReview(dataTable, false);

		} else {
			sfdc.gbjCart.addRogersBusinessSolutions_Internet(dataTable, true, false);
			sfdc.gbjCart.addRogersOffice365AddOns(dataTable, true);
			sfdc.gbjCart.hitNext();
			sfdc.gbjCart.addRogersBusinessSolutions_TV(dataTable, true);
			sfdc.gbjCart.addRogersTVAddOns(dataTable, true);

			// sfdc.quoteReview.verifyDetailsinQuoteReview(dataTable, true);
			sfdc.quoteReview.verifyDetailsinQuoteReview(dataTable, false);
			sfdc.quoteReview.addFreeInstallationInGBJ();

		}

		// sfdc.cpqHome.acceptQuote();
		sfdc.gbjCart.acceptQuoteInGBJ();
		// sfdc.gdPdf.verifyAllProductPricesInPDF(dataTable, Constants.ACCEPTED_PDF);
		sfdc.gdPdf.sendQuoteNextButton();
		// sfdc.siteCon.verifySelectExistingNewContactText();
		sfdc.siteCon.createNewSiteContact();
		// sfdc.siteCon.selectExistingSiteContact_Scrum();

		sfdc.orderDetails.verifySubmittedOrderInOrderDetails();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// if (Global.dataInput.orderNumber.length() > 0) {
		if (false) {

			sfdc.login.loginToSFDC(InputData.Profile_Delivery);
			sfdc.home.closeTabIfOpen();

			sfdc.mques.verifyManualQueuesObject();
			sfdc.mques.selectAccountProvisioningQueue();
			sfdc.manQue.pickUpOrderInQueue();
			if (dataTable.get("Product _Type").contains(InputData.internetProduct)
					&& dataTable.get("Product _Type").contains(InputData.tvProduct)) {
				sfdc.crSSOrd.verifyInternetTvContractCodes(dataTable);
			}
			sfdc.crSSOrd.createSuperSystemOrder();
			sfdc.home.closeTabIfOpen();

			sfdc.mques.verifyManualQueuesObject();
			sfdc.mques.selectServiceDeliveryQueue();
			sfdc.manQue.pickUpOrderInQueue();
			sfdc.complVlctyOdr.completeVlocityOrder();
			sfdc.home.closeTabIfOpen();

			sfdc.mques.verifyManualQueuesObject();

			if (!dataTable.get("Office 365 AddOn").equals("NA")) {
				sfdc.mques.selectOffice365();
				sfdc.manQue.pickUpOrderInQueue();
				sfdc.complVlctyOdr.completeOffice365Order();
				sfdc.home.closeTabIfOpen();

				sfdc.mques.verifyManualQueuesObject();
				sfdc.mques.selectOffice365();
				sfdc.manQue.verifyOrderNotInQueue();
				sfdc.home.closeTabIfOpen();
			}
			sfdc.home.logout();
			sfdc.login.loginToSFDC(InputData.userid_ae);
			sfdc.home.closeTabIfOpen();

			// **********Verify Order Activated and Assets Created********//
			sfdc.acc.verifyAccountsObject();
			sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
			sfdc.accRelated.viewOrdersInAccountRelatedTab();
			sfdc.orderDetails.verifyActivatedOrderInOrderDetails();
			sfdc.accRelated.verifyAssetsCreated();
			sfdc.accRelated.verifyAssetsDetails(dataTable);
			sfdc.home.closeTabIfOpen();

			// **********Verify Opportunity is closed own********//
			// sfdc.opp.verifyOpportunitiesObject();
			// sfdc.opp.selectOpportunity(Global.dataInput.businessAccountName);
			sfdc.opp.searchOpportunityInGlobalSearch(Global.dataInput.businessAccountName);
			sfdc.oppDetails.verifyOpportunityIsClosedOwn();
			sfdc.home.closeTabIfOpen();

			// ***************Verify Billing Account Created********//
			sfdc.acc.verifyAccountsObject();
			sfdc.accDetails.verifyBillingAccountCreated(InputData.billingCAN);

			if (!dataTable.get("Office 365 AddOn").equals("NA")) {
				sfdc.home.closeTabIfOpen();
				sfdc.accDetails.verifyBillingAccountCreated(InputData.billingBAN);
				sfdc.accDetails.verifyAssetsInBillingAccount();
			}

			// **********Close Tabs and Log out********//
			sfdc.home.closeTabIfOpen();
			sfdc.home.logout();

		}
		softassert.assertAll();

	}

	/**
	 * @param dataTable
	 * 
	 *                  Read Input data from datasheet and assign it to input
	 *                  variables
	 */
	public void setInputData(Hashtable<String, String> dataTable) {

		Global.dataInput.quoteProductName = dataTable.get("Internet Product Name");
		Global.dataInput.office365ProductName = dataTable.get("Office 365 AddOn");
		Global.dataInput.tvProductName = dataTable.get("TV Product Name");

		Global.dataInput.address = dataTable.get("Address");
		Global.dataInput.addressStreet = dataTable.get("Address Street");
		Global.dataInput.addressCity = dataTable.get("Address City");
		Global.dataInput.addressState = dataTable.get("Address State");
		Global.dataInput.addressCountry = dataTable.get("Address Country");
		Global.dataInput.serviceAddress = dataTable.get("CSA_Service Address");

		test.log(Status.PASS, MarkupHelper.createLabel(dataTable.toString(), ExtentColor.GREEN));
	}

	/**
	 * @return
	 * @throws IOException
	 * 
	 *                     Data Provider to fetch multiple set of data and assign
	 *                     them to 2D Object Array
	 */
	@DataProvider
	public Object[][] getE2EFlowTestData() throws IOException {
		return getDataSetsRunMode(Constants.MP_TESTDATA_FILE, Constants.INT_TV_E2E_SHEET);
	}

}
