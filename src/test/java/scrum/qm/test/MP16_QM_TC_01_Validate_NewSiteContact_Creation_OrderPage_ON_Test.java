package scrum.qm.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

public class MP16_QM_TC_01_Validate_NewSiteContact_Creation_OrderPage_ON_Test extends Base {
	/**
	 * 
	 * MP Release Regression_QM_TC03_Validate new site contact creation from Order
	 * Layout Page_ON
	 * 
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              1. Create Opportunity and create quote by GBJ
	 * 
	 *                              2. Select service account with fibre on-net or
	 *                              near-net
	 * 
	 *                              3. Select RDI Product based on the speed and
	 *                              contract terms
	 * 
	 *                              4. Quote review of the product selected
	 * 
	 *                              6. Verify order is created in draft status and
	 *                              select update order details button to create
	 *                              site contact and select the upcoming task
	 *                              notification and click complete button
	 * 
	 *                              7. Verify the order product items in order
	 *                              related
	 * 
	 *                              8.Click on Next and Validate the Order Created
	 *                              with Status as Ready to Submit.
	 * 
	 *                              9.Click on Select Site Contact and Create a New
	 *                              Contact.
	 * 
	 *                              11.Validate the Quote Line Item and Order
	 *                              Products.(Both should have same products).
	 *                              12.Validate Orchestration Plan Created for the
	 *                              Order. 13.Validate Task Created and Tagged in
	 *                              Order Layout Page. 14.Validate the subject as
	 *                              Quote Finalized – Select / Add Site Contact From
	 *                              Order page, Order Number(Order Number
	 *                              Displayed), Due Date(Order Date+1 Day),Priority:
	 *                              High 15.Validate the ACR between Site contact
	 *                              and Business Account as Direct Relationship,
	 *                              Indirect Relationship with Service Account.
	 */

	@Test(dataProvider = "getE2EFlowTestData")
	public void test_Validate_GBJ_QM_RDI_newSiteContact_creation_from_Order_Layout_Page_ON(
			Hashtable<String, String> dataTable) throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// *****Create Business Account******//
		sfdc.acc.createNewBusinessAccount();
		sfdc.cba.enterBusinessAccountInfo(false);
		sfdc.cba.verifyBusinessAccountCreated();

		// *****Create Contact**********//
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreated();
		sfdc.cc.clickOnNextInCreateContact();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// *****Login With DataGovernance profile to approve the business account and
		// enter credit check values for validation
		// ** based on the test data*********

		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.home.closeTabIfOpen();
		// sfdc.accDetails.searchBusAccGlobalSearch("BusinessPankaj03");
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
		sfdc.accRelated.approveAsDataGovernance();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// **************Login with AE profile*************************
		sfdc.login.loginToSFDC(InputData.Profile_AE);
//		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpenWithRefresh();

		// ****Search the business account****

		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
		/****************************************************************/

		// ****Create Opportunity and select existing contact in
		// opportunity***************//
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();

		// *******Create Quote through GBJ*********//
		sfdc.cQuote.createQuoteGuidedByJourney_SelectExistingSigningAuthority();
		sfdc.cQuote.createQuote_SelectBillingAccountNext();

		// ****Create Service Account with different access type group as per the test
		// data*****//
		sfdc.cQuote.createQuote_SelectNewServiceAccount();
		sfdc.csa.enterServiceAccountInfo(1);
		sfdc.csa.verifyServiceAccountCreated();
		sfdc.csa.checkServicability_AddServiceLocations(dataTable);
		sfdc.csa.clickOnNextInCheckServicabilityInGbj();
		sfdc.cQuote.createQuote_SelectCreatedNewServiceAccount();

		// ***Add RDI Product in the cart based on the test data***********//
		sfdc.gbjRDI.addRogersBusinessSolutions_RDI(dataTable, false);
		sfdc.quoteReview.verifyDetailsinQuoteReviewForRDI(dataTable, false);

		// *****Acccept Quote and create new site contact
		sfdc.cpqHome.acceptQuote();
		sfdc.gdPdf.sendQuoteNextButton();

		// Select No site contact option from the site contact page and click next,
		// click on update order details button
		sfdc.siteCon.verifyTextAndSelectNoSiteContact_Scrum();
		sfdc.orders.updateOrderDetails();

		// Click on new site contact after clicking update order details button
		sfdc.siteCon.createNewSiteContactAfterRsumeQuote();

		// click on upcoming task notification and click complete task button
		sfdc.orders.verifyUpcomingTasksDueAndComplete();
		sfdc.home.closeLastOpenedTabs(2);
		
		sfdc.orderRelated.verifyNoOrchestrationPlanInOrder();
		sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusReadyToSubmit);

		// sfdc.siteCon.selectExistingSiteContact_Scrum();

		// ******Compare Order product items with Quote Line items in Quote
		// Related**********
		// Extract the product details from order relatd tab
		sfdc.orderRelated.extractProductItemsInOrderRelated(dataTable);
		sfdc.home.closeTabIfOpenWithRefresh();
		sfdc.orders.globalSearchOrders(sf.dataInput.orderNumber);	
		
		// Click on the Quote link in the Order product details and extract the product from quote line items

		sfdc.orderDetails.verifyQuoteLinkInOrderDetails();
		sfdc.quoteRelated.verifyProductsInQuoteLineItem();
		sfdc.orderRelated.compareOrderProductWithQuoteLineItesm(dataTable);

		// Extract the site contact details to move to contact related and verify ACR
		sfdc.home.closeTabIfOpenWithRefresh();
		sfdc.contacts.searchContactFromGlobalSearch(Global.dataInput.siteContactName);
//		sfdc.contacts.searchContactFromGlobalSearch("iwbrqony xninbcoi");

		// ********************Verifying ACR(Account Contact Relationship) Values in
		// contact related tab for Site
		// Contact**********************************************

		sfdc.conDetails.validateAcrPermissionInContacts(Global.dataInput.acc_RecordType_Business,
				Global.dataInput.tempBusinessAccountName, sfdc.conDetails.creatingAcrValueArray(1),
				sfdc.cc.selectRelationshipType(9), false);
		sfdc.home.closeLastOpenedTabs(1);

		// **************Verifying ACR Values for Service Account in account related
		// list*********************************
		sfdc.conDetails.validateAcrPermissionInContacts(Global.dataInput.acc_RecordType_Service,
				Global.dataInput.serviceAccountName, sfdc.conDetails.creatingAcrValueArray(1),
				sfdc.cc.selectRelationshipType(12), false);

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
