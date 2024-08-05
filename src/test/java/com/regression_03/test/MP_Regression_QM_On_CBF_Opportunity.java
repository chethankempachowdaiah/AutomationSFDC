package com.regression_03.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

public class MP_Regression_QM_On_CBF_Opportunity extends Base {
	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              MP Release Regression_Sales_TC37_Validate create
	 *                              Quote by GBJ should exists on "Self-Serve
	 *                              Customer Buy Flow Layout" opportunity record.
	 * 
	 * 
	 * 
	 *                              1. Login as AE
	 * 
	 *                              2. Validate that by default the app is selected
	 *                              as "" R4B Sales Console"".
	 * 
	 *                              3. Open an opportunity with record type as
	 *                              ""Self-Serve Customer Buy Flow Layout"" and
	 *                              Select Opportunity from menu dropdown and select
	 *                              CBF Opportunity record from the dropdown list.
	 * 
	 *                              4. Validate button for ""Create Quote by GBJ""
	 *                              should exists on "Self-Serve Customer Buy Flow
	 *                              Layout"" opportunity record.
	 * 
	 *                              5.User should be able to successfuly create a
	 *                              quote on a CBF opportunity."
	 * 
	 */

	@Test(dataProvider = "getE2EFlowTestData")
	public void test_Validate_GBJ_CreateQuote_On_CBF_Opportunity(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// *******Select Opportunity from menu dropdown and select CBF Opportunity
		// record*********//
		sfdc.opp.verifyOpportunitiesObject();
		sfdc.opp.selectCBFOpportunityOption();
		sfdc.opp.selectOpportunityFromCBFRecordsList();

		// *******Create Quote through GBJ*********//
		sfdc.cQuote.createQuoteGuidedByJourney_SelectExistingSigningAuthority();
		sfdc.cQuote.createQuote_SelectBillingAccountNext();

		// ****Create Service Account with different access type group as per the test
		// data*****//
		sfdc.cQuote.createQuote_SelectNewServiceAccount();
		sfdc.csa.checkServicability_AddServiceLocations(dataTable);
		sfdc.csa.clickOnNextInCheckServicability();
		sfdc.csa.createQuote_SelectCreatedServiceAccount(dataTable);

		// ***Add RDI Product in the cart based on the test data***********//
		sfdc.gbjRDI.addRogersBusinessSolutions_RDI(dataTable, false);
		sfdc.quoteReview.verifyDetailsinQuoteReviewForRDI(dataTable, false);

		// *****Acccept Quote and create new site contact
		sfdc.cpqHome.acceptQuote();
		sfdc.cpqHome.clickContinue();
		sfdc.gdPdf.sendQuoteNextButton();

		sfdc.siteCon.createNewSiteContact();

		sfdc.orderDetails.verifyOrderNumberInOrderDetails();
		sfdc.orderDetails.verifyStatusInOrderDetails(sf.dataInput.orderStatusDraft);

		// **********Close Tabs and Log out********//
		sfdc.home.closeTabIfOpenWithRefresh();
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
	public Object[][] getE2EFlowTestData() throws IOException {
		return getDataSetsRunMode(Constants.MP_TESTDATA_FILE, Constants.GBJ_RDI_RESUME_SHEET);
	}
}
