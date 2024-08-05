package com.deprecated.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti Chawla, 17/MAR/2021
 * 
 *         Verify Order Cancellation after task 1 failed
 * 
 *         MP Release Regression_OM_TC03_Validation of Order cancellation after
 *         Task 1 is failed_Atlantic
 * 
 */

public class MP_OM_TC_01_Validation_Order_Cancellation_After_Task1_Failed_ATL_Test extends Base {

	/**
	 * @author Anukriti.Chawla
	 * 
	 * 
	 *         ///// MP Release Regression_OM_TC03_Validation of Order cancellation
	 *         after Task 1 is failed_Atlantic
	 * 
	 *         Create a new Business Account and a contact.
	 * 
	 *         Create opportunity. Click on Create Guided By Journey Quote.
	 * 
	 *         Select Signing Authority. Create new Service account: enter all
	 *         required details , account is created
	 * 
	 *         Click on Next and User Lands on Rogers for Business page. Click on
	 *         Internet and then Next.
	 * 
	 *         Add 150U Business internet pro along with Promo and then Next.
	 * 
	 *         Add Microsoft 365 Business Standard add-ons and Click on next.
	 * 
	 *         Add Basic TV Private as bundle and Add Multiple TV Addons as STB.
	 * 
	 *         Check out/Next button.
	 * 
	 *         Validate the product details section on Quote Review Page.
	 * 
	 *         Product Details should have all the bundled products in product
	 *         details section in Quote Review page.
	 * 
	 *         Click on Accept Quote. Create site contact and click on Next.
	 * 
	 *         Login with a Delivery profile.
	 * 
	 *         Go to App launcher and open 'Manual Queues'. will show recently
	 *         viewed by default.
	 * 
	 *         Select Account Provisioning Queue from the list in order to complete
	 *         task 1.
	 * 
	 *         Account Provisioning Queue page gets opened which includes Related
	 *         and Details tab.
	 * 
	 *         Click on 'Pick up' button for the order created in this flow.
	 * 
	 *         Validate the information of Account, Contact, Supersystem product
	 *         details associated with the Order.
	 * 
	 *         Click on Assign to me and then Fail Task.
	 * 
	 *         Go to Account Provisioning Queue and validate Order from step 2
	 * 
	 *         Check the Order status in AE profile and then Click on ""Cancel"".
	 * 
	 *         On Account Provisioning Queue validate order Status.Order is removed
	 *         from Account Provisioning Queue, Order status is 'Cancelled'
	 * 
	 *         Search Order# and verify Order status in Sales Force
	 * 
	 *         On Sales Force Order page validate 'Activity' Order Failure
	 * 
	 *         On Order lightning screen verify orchestration plan state".
	 * 
	 *         Orchestration plan state is 'Complete'"
	 *
	 */

	@Test(dataProvider = "getTestData_TaskFail_OrderCancel_GBJ")
	public void test_OrderCancellation(Hashtable<String, String> dataTable) throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ***************LOGIN AS AE***********************//
		/*
		 * sfdc.login.loginToSFDC(InputData.userid_ae); sfdc.home.openR4BSalesConsole();
		 * sfdc.home.closeTabIfOpen();
		 */
		// ***************Create business Account********//
		/*
		 * sfdc.acc.createNewBusinessAccount();
		 * sfdc.cba.enterBusinessAccountInfo(false);
		 * sfdc.cba.verifyBusinessAccountCreated();
		 */
		// ***************Create Contact********//
		/*
		 * sfdc.cc.enterCreateContactInfo(true);
		 * sfdc.cc.verifyMarkPrimaryContactReadOnly(); sfdc.cc.verifyContactCreated();
		 * sfdc.cc.clickOnNextInCreateContact();
		 */
		// ***************Verify Business, Service and Billing Account Created********//
		/*
		 * sfdc.home.closeTabIfOpen(); sfdc.accDetails.validateBusinessAccount();//
		 * ..............VERIFY BUSINESS ACCOUNT sfdc.home.closeTabIfOpen();
		 * sfdc.home.logout();
		 */
		// *********Login as Data Governance and Approve business Account******//
		/*
		 * sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		 * sfdc.home.closeTabIfOpen();
		 * sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName
		 * ); sfdc.accRelated.approveAsDataGovernance(); sfdc.home.closeTabIfOpen();
		 * sfdc.home.logout();
		 */
		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		/*
		 * sfdc.home.openR4BSalesConsole(); sfdc.home.closeTabIfOpen();
		 */

		// ***************Create new Site Contact***********************//
		// sfdc.contacts.verifyContactsObject();
		// sfdc.contacts.clickOnNewContactButton();
		// sfdc.cc.selectSiteContactRadio();
		// Global.dataInput.thirdContact_prepareContactData();
		// sfdc.cc.enterCreateContactInfo(true);
		// sfdc.cc.clickContactDetailsNext();
		// sfdc.cc.contactSearchAccount(true);
		// sfdc.cc.verifySiteContactCreatedSuccessfully();
		// sfdc.conDetails.validateSiteContact();
		// sfdc.home.closeTabIfOpen();

		// ***************Create Opportunity***********************//
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccNameForOrderCancelATL);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();

		// ***************Select Contact in Opportunity***********************//
		sfdc.cOpp.selecetExistingContactInOpportunity();

		// ***************Create Quote with New Service Account********//
		sfdc.cQuote.createQuoteGuidedByJourney_SelectExistingSigningAuthority();
		sfdc.cQuote.createQuote_SelectNewServiceAccount();
		sfdc.csa.enterServiceAccountInfo(1);
		sfdc.csa.clickOnNextInCreateServiceAccount();
		sfdc.cQuote.createQuote_SelectCreatedServiceAccount();

		// *************** Internet Product is added***********//
		if (!dataTable.get("Internet Product Name").equals("NA")) {
			sfdc.gbjCart.addRogersBusinessSolutions_Internet(dataTable, false, false);
			sfdc.gbjCart.addRogersOffice365AddOns(dataTable, false);
			sfdc.gbjCart.hitNext();
		}

		// ***************Configure and Accpet Quote, Send Quote Email ********//
		sfdc.gbjIBLC.add_BusinessPhone_GBJ(dataTable, false);
		sfdc.gbjIBLC.addALine_IBLC(dataTable);
		sfdc.gbjIBLC.editLine_IBLC(dataTable);
		sfdc.gbjCart.hitNext();
		sfdc.gbjCart.hitCheckOut();

		// ***************Verify Product Details on Quote Review Page**********///
		// sfdc.quoteReview.verifyDetailsinQuoteReview(dataTable);
		sfdc.quoteReview.verifyBusinessPhoneDetailsInQuoteReview(dataTable);

		sfdc.gbjCart.acceptQuoteInGBJ();
		sfdc.gbjCart.handleEmailActionError();

		sfdc.gdPdf.sendQuoteNextButton();
		sfdc.gbjCart.handleEmailActionError();

		// ***************Select Existing Site Contact********//
		sfdc.siteCon.selectExistingSiteContact_Scrum();
		sfdc.gbjCart.handleEmailActionError();

		// *************** Fill Provisioning Details**********//
		sfdc.gbjProv.verifyProvisioningDetailsPage();
		sfdc.gbjProv.fillProvisioningLineAndPortDetails(dataTable);
		sfdc.gbjProv.uploadInvoiceOnProvisioningPage();
		sfdc.gbjProv.uploadEmergencyDisclaimerOnProvisioningPage();
		sfdc.gbjProv.fillDirectoryDetailsOnProvisioningPage();
		sfdc.gbjCart.hitNext();

		// ***************Verify Order Submitted********//
		sfdc.orderDetails.verifySubmittedOrderInOrderDetails();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************Login As Delivery********//
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// ***************Pick up the order in Account Provisioning Queue********//
		sfdc.mques.verifyManualQueuesObject();
		sfdc.mques.selectAccountProvisioningQueue();
		sfdc.manQue.pickUpOrderInQueue();
		sfdc.crSSOrd.failTask1();
		sfdc.home.closeTabIfOpen();
		sfdc.mques.verifyManualQueuesObject();
		sfdc.mques.selectAccountProvisioningQueue();
		sfdc.manQue.verifyOrderStatusFailed();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************Verify Order cancelled********//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.orders.verifyOrdersObject();
		sfdc.orders.selectOrder();
		sfdc.orderDetails.verifySubmittedOrderInOrderDetails();
		sfdc.orderDetails.cancelOrder();
		sfdc.home.closeTabIfOpen();
		sfdc.orders.verifyOrdersObject();
		sfdc.orders.selectOrder();

		sfdc.orderDetails.verifyCancelledOrderInOrderDetais();

		// ***************Verify Orchestration plan is cancelled ********//
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.verifyOrchestrationPlanStateComplete();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************Login As Delivery********//
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// ***************Verify Queue Task 1 --> Order is removed********//
		sfdc.mques.verifyManualQueuesObject();
		sfdc.mques.selectAccountProvisioningQueue();
		sfdc.manQue.verifyOrderNotInQueue();

		// **********Close Tabs and Log out********//
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
	public Object[][] getTestData_TaskFail_OrderCancel_GBJ() throws IOException {
		return getDataSetsRunMode(Constants.MP_TESTDATA_FILE, Constants.GBJ_TASKFAIL_ORDERCANCEL_ATL_SHEET);
	}
}
