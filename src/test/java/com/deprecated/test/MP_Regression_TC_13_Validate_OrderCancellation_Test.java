package com.deprecated.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, 30/MAR/2020
 * 
 *         Verify Order Cancellation (In progress Order> cancel order)
 *
 */

public class MP_Regression_TC_13_Validate_OrderCancellation_Test extends Base {

	/**
	 * @author Priyanka.Acharya
	 * 
	 *         Create Business Account
	 * 
	 *         Create Service Accounts
	 * 
	 *         Create opportunity with the Business Account and new Service Contact
	 * 
	 *         Create Quote Shell
	 * 
	 *         Add Product from Atlantic Region with a Monthly Contract Term
	 * 
	 *         Configure the Quote and Create Order (Use Existing Site Contact)
	 * 
	 *         Complete Orchestration Task 1
	 * 
	 *         Cancel the Order before completion of Orchestration Task 2
	 * 
	 *         Order is Cancelled.
	 *
	 */

	@Test
	public void test_OrderCancellation() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// ***************Create business Account********//
		sfdc.acc.createNewBusinessAccount();
		sfdc.cba.enterBusinessAccountInfo(false);
		sfdc.cba.verifyBusinessAccountCreated();

		// ***************Create Contact********//
		sfdc.cc.enterCreateContactInfo(true);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreated();
		sfdc.cc.clickOnNextInCreateContact();

		// ***************Create Service Account********//
		sfdc.csa.enterServiceAccountInfo(1);
		sfdc.csa.clickOnNextInCreateServiceAccount();

		// ***************Create Billing Account********//
		sfdc.csa.checkCreateBillingAccount();
		sfdc.cbia.enterBillingAccountInfo();
		sfdc.cbia.verifyBillingAccountCreated();

		// ***************Verify Business, Service and Billing Account Created********//
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.validateBusinessAccount();// ..............VERIFY BUSINESS ACCOUNT
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.validateServiceAccount(true);// ...........VERIFY SERVICE ACCOUNT
		sfdc.premises.validatePremises();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.validateBillingAccount(true);// ...........VERIFY BILLING ACCOUNT
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// *********Login as Data Governance and Approve business Account******//
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.home.closeTabIfOpen();
		// sfdc.accDetails.searchAccount(sf.dataInput.businessAccountName);
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

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
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();

		// ***************Create New Contact in Opportunity***********************//
		sfdc.cOpp.opportunityCreateContact();
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.clickContactDetailsNext();
		sfdc.cc.verifyContactCreated();
		sfdc.cc.clickOnNextInCreateContact();

		// ***************Select Contact Role In Opportunity***********************//
		sfdc.cOpp.selectOpportunityContactRole();

		// ***************Verify Opportunity Details********//
		sfdc.oppDetails.validateOpportunityDetails(sf.dataInput.businessAccountName);

		// ***************Create Quote with Existing Service Account********//
		sfdc.cQuote.createQuote_SelectContatRole_SigningAuthority();
		sfdc.cQuote.createQuote_SelectExistingServiceAccount();
		sfdc.cpqHome.verifyQuoteNumberInCPQHome();
		sfdc.cpqHome.selectPriceList();
		sfdc.cpqHome.selectProductInHybridCart();

		// ***************Configure and Accpet Quote, Send Quote Email ********//
		sfdc.cpqHome.configureQuote();
		sfdc.cpqHome.acceptQuote();
		sfdc.gdPdf.sendQuoteNextButton();
		sfdc.siteCon.verifySelectExistingNewContactText();

		// ***************Select Existing Site Contact********//
		sfdc.siteCon.selectExistingSiteContact();

		// ***************Verify Order Submitted********//
		sfdc.orderDetails.verifySubmittedOrderInOrderDetails();

		// ***************Verify Orchestration Plan ********//
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();

		// ***************Verify Orchestration Task Created********//
		// sfdc.orchPlan.verifyOrchestrationTaskCreated();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************Login As Delivery********//
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// ***************Pick up the order in Account Provisioning Queue********//
		sfdc.mques.verifyManualQueuesObject();
		sfdc.mques.selectAccountProvisioningQueue();
		sfdc.manQue.verifyOrderInQueue();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************Verify Order cancelled********//
		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.orders.verifyOrdersObject();
		sfdc.orders.selectOrder();
		sfdc.orderDetails.verifySubmittedOrderInOrderDetails();
		sfdc.orderDetails.cancelOrder();
		sfdc.orderDetails.verifyCancelledOrderInOrderDetais();

		// ***************Verify Orchestration plan is cancelled ********//
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.verifyOrchestrationTaskCancelled();
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
}
