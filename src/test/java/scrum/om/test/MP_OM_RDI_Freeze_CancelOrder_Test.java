package scrum.om.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.framework.base.BaseBrowser;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

public class MP_OM_RDI_Freeze_CancelOrder_Test extends BaseBrowser {


	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                             4. RDI Order with running tasks - SD requests Cancellation before PONR
    								- Login as SD and pick up Review Order Details task from the queue
    								- Ensure the task status becomes Running
    								- On the order record page, select "Request Order Cancellation"
    								- Validate that the order status becomes Frozen
    								- Validate that AE receives notification
    								- Validate that the notification task Due Date is today + 2 days
    								- Open Review Order Details again and validate order status
    								- Ensure that proper message is presented (story 44802)
    								- Validate that the task status is still Running

									5a. RDI order is Frozen - Cancel the order
									    - Repeat Use Case 4 putting RDI order into Frozen state
									    - Login as AE and Cancel the frozen order
									    - Status will be cancel requested
									    - The order status becomes cancel requested, completed the review order details
									    - completed task will become cancelled
									    - Failed tasks will become Failed Discarded
									    - Ready and Pending tasks will become Discarded
									    - Supplemental order goes in status Activated
									    - Orchestration plan is unlinked from original order and linked to Supplemental order
	 * 
	 */ 


	@Test(dataProvider = "getE2EFlowTestData")
	public void test_Validate_GBJEndToEndFlow_Promotions(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

	//	intializeChrome(false);
		SFDC_AllPages sfdc = new SFDC_AllPages();
		//		System.out.println(dataTable.get("Dedicated_Internet_Product"));
		//
		sf.dataInput.orderNumber = "00384968";
		//	sf.omData.canceledOrderNumber = "00013483";
		System.out.println(sf.omData.notificationDueDate);

		// **************Login with AE profile*****************
		// sfdc.login.loginToSFDC(InputData.Profile_AE);
		// // sfdc.home.openR4BSalesConsole();
		// sfdc.home.closeTabIfOpen();
		// // // Thread.sleep(8000);
		// // ****Search the business account****
		// // sfdc.accDetails.searchAccount(Global.dataInput.tempBusinessAccountName);
		// sfdc.accDetails.searchBusAccGlobalSearch("BAAPankaj2");
		//
		// // *****Extract the credit check value entered for validation************
		// // if(dataTable.get("Credit_Check_Value_Enter").equals("Yes")) {
		// // sfdc.accDetails.extractCreditCheckRequiredValidationValues();
		// // }
		//
		// /****************************************************************/
		//// sfdc.login.loginToSFDC(InputData.userid_ae);
		//// sfdc.home.closeTabIfOpen();
		////
		//// sfdc.accDetails.searchAccount(sf.dataInput.account_ATL);
		//
		//
		// // ****Create Opportunity and select existing contact in
		// // opportunity***************//
		// sfdc.accRelated.createOpportunity();
		// sfdc.cOpp.enterOpportunityDetails();
		// sfdc.cOpp.selecetExistingContactInOpportunity("ContactPankajSign
		// ContactPankajSign");
		//
		// // *******Create Quote through GBJ*********//
		//  sfdc.cQuote.createQuoteGuidedByJourney_SelectExistingSigningAuthority();
		// sfdc.cQuote.createQuote_SelectBillingAccountNext();
		//
		// // ****Create Service Account with different access type group as per the
		// test
		// // data*****//
		//		 sfdc.cQuote.createQuote_SelectNewServiceAccount();
		//		 sfdc.csa.enterServiceAccountInfo(1);
		//		 sfdc.csa.verifyServiceAccountCreated();
		//		 sfdc.csa.checkServicability_AddServiceLocations(dataTable);
		//		 sfdc.csa.clickOnNextInCheckServicabilityInGbj();
		//		 sfdc.cQuote.createQuote_SelectCreatedNewServiceAccount();
		//		
		// // for existing service account use the below one
		// // sfdc.csa.createQuote_SelectCreatedServiceAccount(dataTable);
		//
		// // ***Add RDI Product in the cart based on the test data***********//
		// sfdc.gbjRDI.addRogersBusinessSolutions_RDI(dataTable, false);
		//
		// // If the test data does't have the second addon product based on the
		// dedicated
		// // internet speed then it will have the
		// // Quote review
		// if (dataTable.get("SecondAdds_On").equals("NA")) {
		// sfdc.quoteReview.verifyDetailsinQuoteReviewForRDI(dataTable, false);
		// }
		//
		// // *****Acccept Quote and create new site contact
		// sfdc.cpqHome.acceptQuote();
		// sfdc.gdPdf.sendQuoteEmail();
		//
		// // sfdc.siteCon.createNewSiteContact();
		//
		// sfdc.siteCon.selectExistingSiteContact_Scrum();


		// *********Login with AE profile*******************
		//		intializeChrome(false);
		//		sfdc.login.loginToSFDC(InputData.Profile_AE);
		//		// //Thread.sleep(7000);
		//		//		sfdc.home.openR4BSalesConsole();
		//		sfdc.home.closeTabIfOpen();
		//
		//		// ***************Extract the order no from excel sheet***********************//
		//		// sf.dataInput.orderNumber = dataTable.get("Order_Number");
		//		sf.dataInput.orderNumber = "00011165";
		//		System.out.println(sf.dataInput.orderNumber);
		//		sfdc.orders.globalSearchOrders(sf.dataInput.orderNumber);
		//
		//		// ***************Extract Order details from details
		//		// tab***********************//
		//		sfdc.orderDetails.extractOrderDetails();
		//		sfdc.home.closeTabIfOpen();
		//		sfdc.home.logout();
		//
		// ***************LOGIN AS Delivery***********************//

//		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
//		sfdc.home.closeTabIfOpen();
//
//		// *****************************Click on manual queue
//		// object***************************************************//
//		sfdc.mques.clickOnManualQueuesObject();
//		sf.dataInput.items_InQueue();
//
//		// 1. *****************************Click on delivery specialist queue and select
//		// Review order details task*********//
//		sfdc.mques.selectDeliverySpecialistQueue();
//		sfdc.manQue.pickUpOrderInQueueItems(sf.dataInput.queueItemsName.get(0));
////		driver.quit();
//		
		// ***************LOGIN AS Delivery***********************//
		intializeChrome(false);  
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.closeTabIfOpen();

		// *****verify order status is activated****************************
		//		sf.dataInput.orderNumber = "00008410";
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.verifyAllTaskItemsStaus("Running");
		sfdc.home.closeLastOpenedTab();

		//***Click on Request cancellation button********
		sfdc.orders.request_OrderCancellation();

		// Verify Order is in freezed status***
		sfdc.orderDetails.verifyCancelledRequestedStatusInOrderDetais(sf.dataInput.orderStatusFrozen);
		sfdc.home.closeTabIfOpen();

		// *****************************Click on manual queue
		// object***************************************************//
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();

		// 1. *****************************Click on delivery specialist queue and select
		// Review order details task and validate the status message for freeze*********//
		sfdc.mques.selectDeliverySpecialistQueue();
		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(0));
		sfdc.rODComplete.validateTaskStatus_ForFrozenOrder();
		driver.quit();

		// ***************LOGIN AS AE***********************//
		intializeChrome(false);
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();

		//*****Verify the notification to AE after SD request cancellation*******
		sfdc.orders.verifyNotificationInIconToAE();
		sfdc.orders.verifyOpenTaskStatus_ForRequestCancelFromSD();	
		sfdc.home.closeTabIfOpen();

		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();

		// Verify Tasks are still running
		sfdc.orchPlan.verifyAllTaskItemsStaus("Running");

		//***Cancel the order in frozen status
//		sfdc.home.closeTabIfOpen();
//		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
		sfdc.home.closeLastOpenedTab();

		//5a.***************RDI cancel Order*****************************************************
		//***RDI cancel Order********
		sfdc.orders.cancel_Order();
		sfdc.orderDetails.verifyCancelledRequestedStatusInOrderDetais(sf.dataInput.orderStatusCancelledRequested);		
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************LOGIN AS Delivery***********************//
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.closeTabIfOpen();

		// Verify the notification to SD From AE
		sfdc.orders.verifyNotificationInIcon();
		sfdc.orders.verifyOpenTaskStatus_ForCancel_FromAE();	
		sfdc.home.closeTabIfOpen();

		// *****************************Click on manual queue
		// object***************************************************//
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();

		// 1. *****************************Click on delivery specialist queue and select
		// Review order details task and complete the task so that it can be cancelled*********//
		sfdc.mques.selectDeliverySpecialistQueue();
		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(0));
		sfdc.rODComplete.validateTaskStatus_ForRequestCancellationOrder_AndComplete("RDI");
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
	//	driver.quit();

	//	intializeChrome(false);  
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		//	sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();  
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
		// Verify No orchestration plan for RDI in original order
		sfdc.orderDetails.verifyCancelledOrderInOrderDetaisTab();
		sfdc.orderRelated.verifyNoOrchestrationPlanInOrder();

		// Verify Superseded order generated
		sfdc.orderRelated.verifySupersededOrder();
		sfdc.home.closeTabIfOpen();
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.omData.supersededOrderNumber);
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();

		// Verify All completed tasks are canceled
		sfdc.orchPlan.verifyAllTaskItemsStaus("Cancelled");
		sfdc.home.closeLastOpenedTab();
		sfdc.orderDetails.verifyOrderIsActivated();	

		// **********Close Tabs and Log out********//
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		driver.quit();


		//	softassert.assertAll();
	}

	/**
	 * @param dataTable
	 * 
	 *                  Read Input data from datasheet and assign it to input
	 *                  variables
	 */
	public void setInputData(Hashtable<String, String> dataTable) {

		// Global.dataInput.quoteProductName = dataTable.get("Internet Product Name");
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

	@DataProvider
	public Object[][] getE2EFlowTestData() throws IOException {
		return getDataSetsRunMode(Constants.MP_TESTDATA_FILE, Constants.GBJ_RDI_RESUME_SHEET);
	}
}
