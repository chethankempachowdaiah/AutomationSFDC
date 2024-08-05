package scrum.om.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date 20/02/2020
 * 
 *         Assets Creation - Verify that system auto creates Assets once order
 *         is activated.
 */
public class MP01_OM_TC_02_Assets_Creation_Test extends Base {

	/**
	 * @throws Exception
	 * 
	 *                   *************Pre-Requisite:***********
	 * 
	 *                   User is logged in as Service support agent.
	 * 
	 *                   User has access to "manual Queues"
	 * 
	 *                   *****************************************
	 * 
	 *                   **********Test Steps:*********************
	 * 
	 *                   _________Task1:_______
	 * 
	 *                   1. Go to "Manual Queues"
	 * 
	 *                   2. Click on "Account Provisioning Queue".
	 * 
	 *                   3. Click on "Pick Up" for Task1.
	 * 
	 *                   4. Click on Next.
	 * 
	 *                   5. Enter CAN.
	 * 
	 *                   6. Click on "Complete"
	 * 
	 *                   ________Task2:_______
	 * 
	 *                   1. Go to "Manual Queues"
	 * 
	 *                   2. Click on "Service Delivery Queue".
	 * 
	 *                   3. Click on "Pick Up" for Task2.
	 * 
	 *                   4. March the checkbox for order confirmation & Click on
	 *                   "Confirmed"
	 * 
	 *                   5. Click on "Complete"
	 * 
	 *                   ******************************************
	 * 
	 *                   **********Expected Result:****************
	 * 
	 *                   Task1 will be marked as complete and will disappear from
	 *                   Queue.
	 * 
	 *                   Task2 will also be marked complete and disappear from Q.
	 * 
	 *                   Order status will become activated.
	 * 
	 *                   Assets will be created and can be seen under Account.
	 * 
	 *                   ******************************************
	 */

	@Test
	public void test_assets_creation() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.userid_ae);

		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.parentAccountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.opportunityCreateContact();

		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.clickContactDetailsNext();
		sfdc.cc.verifyContactCreated();
		sfdc.cc.clickOnNextInCreateContact();

		sfdc.cOpp.selectOpportunityContactRole();
		sfdc.oppDetails.validateOpportunityDetails(sf.dataInput.parentAccountName);
		sfdc.cQuote.createQuote_SelectContatRole_SigningAuthority();
		// sfdc.cQuote.createQuote_SelectExistingServiceAccount();

		sfdc.cQuote.createQuote_SelectNewServiceAccount();

		sfdc.csa.enterServiceAccountInfo(1);
		sfdc.csa.clickOnNextInCreateServiceAccount();
		sfdc.cQuote.createQuote_SelectCreatedServiceAccount();

		sfdc.cpqHome.verifyQuoteNumberInCPQHome();
		sfdc.cpqHome.selectPriceList();
		sfdc.cpqHome.selectProductInHybridCart();

		sfdc.cpqHome.configureQuote();
		sfdc.cpqHome.acceptQuote();
		sfdc.gdPdf.sendQuoteNextButton();
		sfdc.siteCon.verifySelectExistingNewContactText();
		sfdc.siteCon.createNewSiteContact();

		sfdc.orderDetails.verifySubmittedOrderInOrderDetails();

		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		// sfdc.orchPlan.verifyOrchestrationTaskCreated();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		sfdc.login.loginToSFDC(InputData.Profile_Delivery);

		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		sfdc.mques.verifyManualQueuesObject();
		sfdc.mques.selectAccountProvisioningQueue();
		sfdc.manQue.pickUpOrderInQueue();
		sfdc.crSSOrd.createSuperSystemOrder();
		sfdc.mques.selectAccountProvisioningQueue();
		sfdc.manQue.verifyOrderNotInQueue();
		sfdc.home.closeTabIfOpen();

		sfdc.orders.verifyOrdersObject();
		sfdc.orders.selectOrder();
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.selectOrchestrationTaskCreateServiceAccountAndWorkOrder();
		sfdc.orchItem.verifyOrchestrationState();
		sfdc.home.closeTabIfOpen();

		sfdc.mques.verifyManualQueuesObject();
		sfdc.mques.selectServiceDeliveryQueue();
		sfdc.manQue.pickUpOrderInQueue();
		sfdc.complVlctyOdr.completeVlocityOrder();
		sfdc.mques.verifyManualQueuesObject();
		sfdc.mques.selectServiceDeliveryQueue();
		sfdc.manQue.verifyOrderNotInQueue();
		sfdc.home.closeTabIfOpen();

		sfdc.orders.verifyOrdersObject();
		sfdc.orders.selectOrder();
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.verifyOrchestrationPlanStateComplete();
		sfdc.orchPlan.selectVlocityOrderCompletion();
		sfdc.orchItem.verifyOrchestrationState();

		sfdc.home.closeTabIfOpen();

		sfdc.orders.verifyOrdersObject();
		sfdc.orders.selectOrder();
		sfdc.orderDetails.verifyActivatedOrderInOrderDetails();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		sfdc.acc.verifyAccountsObject();
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.parentAccountName);
		sfdc.accRelated.viewOrdersInAccountRelatedTab();
		sfdc.orderDetails.verifyActivatedOrderInOrderDetails();
		sfdc.accRelated.verifyAssetsCreated();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();

	}
}
