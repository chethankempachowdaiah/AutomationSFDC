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

public class MP_OM_RDI_Order_with_running_task_Review_Order_Details_cancel_before_PONR2_Test extends BaseBrowser {
	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 								US: MPOSS-44823: (QA Only) Regression Testing - RDI Cancel Order
	 * 								 
	 *                              1. RDI order with no running tasks - cancel before PONR
                                        - No notification is sent to order owner
									    - Original order goes in status Cancelled
									    - Supplemental order goes in status Activated
									    - Orchestration plan is unlinked from original order and linked to Supplemental order
	 * 
	 *                              US : MPOSS-45480: Cancel Order - RDI - Hide Mandatory Fields
	 *                              
	 * 								2. When the order is cancelled and user selects Validate Order button, 
	 * 									hide all the mandatory input fields on Task UI
									 - Applicable tasks:
									          - Create/Verify Billing Account
									          - Review Spec Sheet
									          - Send Order to P&I
									          - Complete Circuit Design
									          - Monitor RPATS Fibre Build
									          - Complete IP Assignment
									          - Complete Access and Service Provisioning
	 */ 



	@Test(dataProvider = "getE2EFlowTestData")
	public void test_CancelOrder_RDI(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		intializeChrome(false);
		SFDC_AllPages sfdc = new SFDC_AllPages();
	//	setInputData(dataTable);
		//		System.out.println(dataTable.get("Dedicated_Internet_Product"));
		//
		sf.dataInput.orderNumber = "00384964";
		//		sf.omData.canceledOrderNumber = "00013380";
		//		sf.omData.supersededOrderNumber  = "00013382";
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


		//		//	 *********Login with AE profile*******************
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

		// ***************LOGIN AS Delivery***********************//
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.closeTabIfOpen();

		// *****************************Click on manual queue
		// object***************************************************//
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();

		// 1. *****************************Click on delivery specialist queue and select
		// Review order details task*********//
		sfdc.mques.selectDeliverySpecialistQueue();
		sfdc.manQue.pickUpOrderInQueueItems(sf.dataInput.queueItemsName.get(0));
		driver.quit();

		// ***************LOGIN AS AE***********************//
		intializeChrome(false);  
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		//	sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();  

		// *****verify order status is activated and all the task which are running****************************
		//		sf.dataInput.orderNumber = "00008410";
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();

		sfdc.orchPlan.verifyAllTaskItemsStaus("Running");
		sfdc.home.closeLastOpenedTab();

		//***RDI cancel Order and verify the order status********
		sfdc.orders.cancel_Order();
		sfdc.orderDetails.verifyCancelledRequestedStatusInOrderDetais(sf.dataInput.orderStatusCancelledRequested);

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		driver.quit();

		// ***************LOGIN AS Delivery***********************//
		intializeChrome(false);
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.closeTabIfOpen();

		sfdc.orders.verifyNotificationInIcon();
		sfdc.orders.verifyOpenTaskStatus_ForCancel_FromAE();	
		sfdc.home.closeTabIfOpen();

		// *****************************Click on manual queue
		// object***************************************************//
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();

		// 1. *****************************Click on delivery specialist queue and select
		// Review order details task*********//
		sfdc.mques.selectDeliverySpecialistQueue();
		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(0));

		//******Validate the task status and complete the task
		sfdc.rODComplete.validateTaskStatus_ForRequestCancellationOrder_AndComplete("RDI");
		driver.quit();

		// ******Verify order is canceled and superseded order is generated which is activated ****
		intializeChrome(false);  
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		//	sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();  
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);

		// Verify No orchestration plan for RDI in original order
		sfdc.orderRelated.verifyNoOrchestrationPlanInOrder();

		// Verify Superseded order generated
		sfdc.orderRelated.verifySupersededOrder();
		sfdc.home.closeTabIfOpen();
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.omData.supersededOrderNumber);
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();

		// Verify All completed tasks are canceled and superseded order is activated
		sfdc.orchPlan.verifyAllTaskItemsStaus("Cancelled");
		sfdc.home.closeLastOpenedTabs(1);
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
