package scrum.om.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author priyanka.acharya, Date 13/04/2020
 *
 *         Verify Task State and ownership in Manual Queue for ready and running
 *         task in both Task 1 and Task 2
 */
public class MP03_OM_TC_01_VerifyTaskState_Ready_Running_Task1_Task2_Test extends Base {

	@Test
	public void test_verifyTaskState_Ready_Running_Task1_Task2() throws Exception {

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
		sfdc.csa.noBillingAccountClickOnNext();
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

		// ***************Create Quote ********//
		sfdc.cQuote.createQuote_SelectExistingSigningAuthority();
		sfdc.cQuote.createQuote_SelectExistingServiceAccount();
		sfdc.cpqHome.verifyQuoteNumberInCPQHome();
		sfdc.cpqHome.selectPriceList();
		sfdc.cpqHome.selectProductInHybridCart();

		// ***************Configure and Accept Quote, Send Quote Email ********//
		sfdc.cpqHome.configureQuote();
		sfdc.cpqHome.acceptQuote();
		sfdc.gdPdf.sendQuoteNextButton();
		sfdc.siteCon.verifySelectExistingNewContactText();
		sfdc.siteCon.createNewSiteContact();

		// ***************Verify Order Submitted********//
		sfdc.orderDetails.verifySubmittedOrderInOrderDetails();

		// ***************Verify Orchestration Plan ********//
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************Login As Delivery********//
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// ***************Pick up the order in Account Provisioning Queue********//
		sfdc.mques.verifyManualQueuesObject();
		sfdc.mques.selectAccountProvisioningQueue();
		sfdc.manQue.verifyOrderReadyAndNotAssigned();

		// ***************verify order is ready and not assigned********//
		// sfdc.manQue.pickUpOrderInQueue();
		// sfdc.crSSOrd.selectCancelInSuperSystemOrder();
		// sfdc.mques.selectAccountProvisioningQueue();
		// sfdc.manQue.verifyOrderReadyAndNotAssigned();

		// ***************verify order is Running and Assigned********//
		sfdc.manQue.pickUpOrderInQueue();
		// sfdc.crSSOrd.assignTask();
		sfdc.crSSOrd.selectCancelInSuperSystemOrder();
		sfdc.mques.verifyManualQueuesObject();
		sfdc.mques.selectAccountProvisioningQueue();
		sfdc.manQue.verifyOrderRunningAndAssigned();

		// ***************verify order in queue once Task 1 completed********//
		sfdc.manQue.pickUpOrderInQueue();
		sfdc.crSSOrd.selectNextAndEnterCAN();
		sfdc.mques.verifyManualQueuesObject();
		sfdc.mques.selectAccountProvisioningQueue();
		sfdc.manQue.verifyOrderNotInQueue();
		sfdc.home.closeTabIfOpen();

		// ********Pick up the order in Service Delivery Queue********//
		sfdc.mques.verifyManualQueuesObject();
		sfdc.mques.selectServiceDeliveryQueue();
		sfdc.manQue.verifyOrderReadyAndNotAssigned();

		// ***************verify order is ready and not assigned********//
		// sfdc.manQue.pickUpOrderInQueue();
		// sfdc.crSSOrd.selectCancelInSuperSystemOrder();
		// sfdc.mques.selectServiceDeliveryQueue();
		// sfdc.manQue.verifyOrderReadyAndNotAssigned();

		// ***************verify order is Running and Assigned********//
		sfdc.manQue.pickUpOrderInQueue();
		// sfdc.complVlctyOdr.assignTask();
		sfdc.crSSOrd.selectCancelInSuperSystemOrder();
		sfdc.mques.verifyManualQueuesObject();
		sfdc.mques.selectServiceDeliveryQueue();
		sfdc.manQue.verifyOrderRunningAndAssigned();

		// ***************verify order in queue once Task 2 completed********//
		sfdc.manQue.pickUpOrderInQueue();
		sfdc.complVlctyOdr.selectConfirmAndClickComplte();
		sfdc.mques.verifyManualQueuesObject();
		sfdc.mques.selectServiceDeliveryQueue();
		sfdc.manQue.verifyOrderNotInQueue();

		// **********Close Tabs and Log out********//
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();

	}

}
