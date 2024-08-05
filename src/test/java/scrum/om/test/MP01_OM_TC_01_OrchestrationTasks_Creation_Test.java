package scrum.om.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date 31/JAN/2020
 * 
 *         Orchestration Tasks - Creation - Verify that Order Orchestration
 *         tasks are created once order is submitted.
 *
 */
public class MP01_OM_TC_01_OrchestrationTasks_Creation_Test extends Base {
	/**
	 * @throws Exception
	 * 
	 *                   *************Pre-Requisite:***********
	 * 
	 *                   1. Business Account is created.
	 * 
	 *                   2. Service account is created.
	 * 
	 *                   3. Premises is stamped on service account.
	 * 
	 *                   4. Opportunity is created.
	 * 
	 *                   5. Quote is Accepted.
	 * 
	 *                   6. Order is submitted.
	 * 
	 *                   Order Status: In Progress
	 * 
	 *                   Status: Draft
	 * 
	 *                   *****************************************
	 * 
	 *                   **********Test Steps:*********************
	 * 
	 *                   1. Click on Order ID / Go to Orders page.
	 * 
	 *                   2. Retrieve the Order by order ID.
	 * 
	 *                   3. Click on "Related" Tab.
	 * 
	 *                   4. See Orchestration Plan.
	 * 
	 *                   ******************************************
	 * 
	 *                   **********Expected Result:****************
	 * 
	 *                   Orchestration Plan ID will be shown.
	 * 
	 *                   The ID will show the following:
	 * 
	 *                   1. Order Strated :Milestone.
	 * 
	 *                   2. Task1 : Create Service Account & Work Order in
	 *                   Supersytem : Manual task
	 * 
	 *                   3. Task2: Vlocity Order Completion : Manual task
	 * 
	 *                   4. Create Assets : Auto Task
	 * 
	 *                   5.Complete Order : Milestone.
	 * 
	 *                   ******************************************
	 * 
	 */
	@Test
	public void test_orchestrationTasks_Creation() throws Exception {

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

		sfdc.acc.verifyAccountsObject();
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.parentAccountName);
		sfdc.accRelated.viewOrdersInAccountRelatedTab();

		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		// sfdc.orchPlan.verifyOrchestrationTaskCreated();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		sfdc.login.loginToSFDC(InputData.Profile_OpsManager);
		sfdc.home.closeTabIfOpen();

		sfdc.orders.verifyOrdersObject();
		sfdc.orders.selectOrder();
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		// sfdc.orchPlan.verifyOrchestrationTaskCreated();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		softassert.assertAll();

	}
}
