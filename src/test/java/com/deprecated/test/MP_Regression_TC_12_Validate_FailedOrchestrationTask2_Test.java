package com.deprecated.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya
 *
 *         Verify that Service Support Agents can Fail Orchestration Task2 in
 *         service delivery queue
 */
public class MP_Regression_TC_12_Validate_FailedOrchestrationTask2_Test extends Base {
	/**
	 *********** Pre-Requisutes:**************
	 * 
	 * User is logged in as Service support agent.
	 * 
	 * User has access to "manual Queues"
	 * 
	 * Sales Rep has created this order for which Tasks are created.
	 * 
	 * ***************Test Steps***************
	 * 
	 * Task1:
	 * 
	 * 1. Go to "Manual Queues"
	 * 
	 * 2. Click on "Account Provisioning Queue".
	 * 
	 * 3. Click on "Pick Up" for Task1.
	 * 
	 * 4. Click on Next.
	 * 
	 * 5. Enter CAN which is not already existing one..
	 * 
	 * 6. Click on "Complete"
	 * 
	 * 
	 * 
	 * Task2:
	 * 
	 * 1. Service support agent to Pick Up Task2 from Service Delivery Queue.
	 * 
	 * 2. Click on "Fail" checkbox.
	 * 
	 * 3. Enter Comments in "Business Failure" Text Box.
	 * 
	 * 4. Click on "Complete"
	 * 
	 * 5. Sales Rep to click on failure Task created under Order Activities.
	 * 
	 * 6. Enter Comments.
	 * 
	 * 7. Mark Task Status as "Completed".
	 * 
	 * 8. Click on "Save".
	 * 
	 * ******************Expected Result*************
	 * 
	 * Task 1:
	 * 
	 * 1. Task will be marked "Failed".
	 * 
	 * 2.Task Failure Activity will be created under the order.
	 * 
	 * 
	 * 
	 * Task2:
	 * 
	 * 1. Task will be marked "Failed".
	 * 
	 * 2. Task Failure Activity will be created under the order.
	 * 
	 * @throws Exception
	 * 
	 * 
	 * 
	 */
	@Test
	public void test_validate_FailedOrchestrationTask2() throws Exception {

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
		// sfdc.accDetails.searchAccount(sf.dataInput.businessAccountName);
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
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
		Global.dataInput.thirdContact_prepareContactData();
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
		sfdc.mques.verifyManualQueuesObject();
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
		sfdc.complVlctyOdr.failTask2();
		sfdc.mques.verifyManualQueuesObject();
		sfdc.mques.selectServiceDeliveryQueue();
		sfdc.manQue.verifyOrderStatusFailed();
		sfdc.home.closeTabIfOpen();

		// ********Verify "Vlocity Order Completion" State Failed***//
		sfdc.orders.verifyOrdersObject();
		sfdc.orders.selectOrder();
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.selectVlocityOrderCompletion();
		sfdc.orchItem.verifyOrchestrationState();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// ********Verify Task Failure Activity is created under the order.********//
		sfdc.orders.verifyOrdersObject();
		sfdc.orders.selectOrder();
		sfdc.orderDetails.verifyTaskFailureActivity();
		sfdc.orderFailure.markOrderFailureTaskCompleted();

		// **********Close Tabs and Log out********//
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
