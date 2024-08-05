package com.deprecated.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author priyanka.acharya, Date 19/FEB/2020
 *
 *         Verify Activation of an order for Ontario Region
 */
public class MP_Regression_VerifyOrderActivation_ON_Test extends Base {

	/**
	 * @throws Exception
	 * 
	 *                   LOGIN AS AE
	 * 
	 *                   Create business Account
	 * 
	 *                   Create Contact
	 * 
	 *                   Create Service Account
	 * 
	 *                   Create Billing Account
	 * 
	 *                   Verify Business, Service and Billing Account Created
	 * 
	 *                   Login as Data Governance and Approve business Account
	 * 
	 *                   LOGIN AS AE
	 * 
	 *                   Create Opportunity
	 * 
	 *                   Create New Contact in Opportunity
	 * 
	 *                   Select Contact Role In Opportunity
	 * 
	 *                   Verify Opportunity Details
	 * 
	 *                   Create Quote
	 * 
	 *                   Configure and Accpet Quote, Send Quote Email
	 * 
	 *                   Create New Site Contact
	 * 
	 *                   Verify Order Submitted
	 * 
	 *                   Verify Orchestration Plan
	 * 
	 *                   Verify Orchestration Task Created
	 * 
	 *                   Login As Delivery
	 * 
	 *                   Pick up the order in Account Provisioning Queue
	 * 
	 *                   Verify "Create Service Account And WorkOrder" State
	 *                   Completed
	 * 
	 *                   Pick up the order in Service Delivery Queue
	 * 
	 *                   Verify "Vlocity Order Completion" State Completed
	 * 
	 *                   Verify Order Activated
	 * 
	 *                   LOGIN AS AE
	 * 
	 *                   Verify Order Activated and Assets Created
	 * 
	 *                   Close Tabs and Log out
	 */
	@Test
	public void test_verifyOrderActivationONRegion() throws Exception {

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
		sfdc.cc.enterCreateContactInfo(false);
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
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

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

		// ***************Create Quote ********//
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

		// ***************Create New Site Contact********//
		sfdc.siteCon.createNewSiteContact();

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
		sfdc.manQue.pickUpOrderInQueue();
		sfdc.crSSOrd.createSuperSystemOrder();
		sfdc.mques.selectAccountProvisioningQueue();
		sfdc.manQue.verifyOrderNotInQueue();
		sfdc.home.closeTabIfOpen();

		// *****Verify "Create Service Account And WorkOrder" State Completed*****//
		sfdc.orders.verifyOrdersObject();
		sfdc.orders.selectOrder();
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.selectOrchestrationTaskCreateServiceAccountAndWorkOrder();
		sfdc.orchItem.verifyOrchestrationState();
		sfdc.home.closeTabIfOpen();

		// ********Pick up the order in Service Delivery Queue********//
		sfdc.mques.verifyManualQueuesObject();
		sfdc.mques.selectServiceDeliveryQueue();
		sfdc.manQue.pickUpOrderInQueue();
		sfdc.complVlctyOdr.completeVlocityOrder();
		sfdc.mques.selectServiceDeliveryQueue();
		sfdc.manQue.verifyOrderNotInQueue();
		sfdc.home.closeTabIfOpen();

		// ********Verify "Vlocity Order Completion" State Completed***//
		sfdc.orders.verifyOrdersObject();
		sfdc.orders.selectOrder();
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.verifyOrchestrationPlanStateComplete();
		sfdc.orchPlan.selectVlocityOrderCompletion();
		sfdc.orchItem.verifyOrchestrationState();
		sfdc.home.closeTabIfOpen();

		// ***************Verify Order Activated********//
		sfdc.orders.verifyOrdersObject();
		sfdc.orders.selectOrder();
		sfdc.orderDetails.verifyActivatedOrderInOrderDetails();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// **********Verify Order Activated and Assets Created********//
		sfdc.acc.verifyAccountsObject();
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
		sfdc.accRelated.viewOrdersInAccountRelatedTab();
		sfdc.orderDetails.verifyActivatedOrderInOrderDetails();
		sfdc.accRelated.verifyAssetsCreated();

		// **********Close Tabs and Log out********//
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************Login As Delivery********//
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// ***************Verify Billing Account Created********//
		sfdc.acc.verifyAccountsObject();
		sfdc.accDetails.verifyBillingAccountCreated(sf.dataInput.billingCAN);

		// **********Close Tabs and Log out********//
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();

	}
}
