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

public class MP_OM_Cable_order_no_running_tasks_fallout_task_CreateAccount_Failed_Test extends BaseBrowser {

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
		                     4. Cable order with no running tasks, fallout task is Failed - order cancellation before PONR (dependency on story 44547)
						    - Fallout task Create Account in SS has failed, callout task status is Running
						    - Delivery agent picks fallout task, checks premises in Supersystem and realizes the order is created in error. Delivery agent fails fallout task, requesting order cancellation in the failure reason box
						    - Callout Task status also becomes Failed
						    - Notification goes to AE
						    - AE cancels the order
						    - Original order goes in status Cancelled
						    - Supplemental order goes in status Activated
						    - Orchestration plan is unlinked from original order and linked to Supplemental order
						     - Review Order Details task becomes "Cancelled"
						     - Callout task Create Account in SS and it's fallout tasks become "Failed Discarded"
						     - The rest of the tasks become Discarded
						     - Notification go to the order owner and to the person who failed fallout task if different from the owner
	 * 
	 */ 


	@Test(dataProvider = "getRDIOrderCancel")
	public void test_Validate_MP_OM_Cable_order_no_running_tasks_fallout_task_CreateAccount_Failed(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		intializeChrome(false);
		SFDC_AllPages sfdc = new SFDC_AllPages();
		setInputData(dataTable);

		sf.dataInput.orderNumber = "00385536";
	//	sf.omData.canceledOrderNumber = "00063711";
		//		sf.omData.supersededOrderNumber  = "00013382";
		System.out.println(sf.omData.notificationDueDate);

		// ***************LOGIN AS Delivery***********************//
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.closeTabIfOpen();

		// *************Verify and complete Review order Details task from the manual queue****
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();

		// 1. *****************************Click on delivery specialist queue and select
		// Review order details task*********//
		sfdc.mques.selectCableOrderProvisioningQueue();
		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(0));

		sfdc.rODComplete.completeReviewOrderDetailsTask("completeTask", "cable");
		sfdc.home.closeTabIfOpen();

		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.verifyOrchestrationPlanForWelcomeLetterAfterCompleteTask();

		// if the task is not completed manually close the welcome letter task window after changing the status
		if(sf.omData.welcomeEmailTaskStatus_CompletedManually == true) {
			sfdc.home.closeLastOpenedTab();
		}

		//3. ***************Click on Create Account In SS task failure From Orchastration Plan*******//
		//		sfdc.orders.verifyRPATaskFailNotificationIconStatus("createAccount");
		//		sfdc.home.closeTabIfOpen();
		//		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
		//		sfdc.orderRelated.verifyOrchestrationPlanInOrder();

		//******Change the state of the task to be ready status from pending**********//
		sfdc.orchPlan.changeCreateAccountInSSTaskFailureState("Ready");
		sfdc.home.closeLastOpenedTab();
	
		//********Verify Task failure for create account in SS task failure*******
		sfdc.orchPlan.clickOnCreateAccountInSSTaskFailureTask();
		sfdc.cableTaskItems.clickOnCANNoRadioInCreateAccountManualTask(dataTable);

		// ********Verify fail task button in the review order details *********//
		//	sfdc.cableTaskItems.reviewOrderDetailsComplete_Page(dataTable);
		sfdc.cableTaskItems.validateFailTaskButton(dataTable);
		sfdc.home.closeLastOpenedTab();
		sfdc.orchPlan.verifyAllTaskItemsStaus("Failed");
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		//2. *********Verify Failure Task Icon Notification With AE profile, Cancel the order*******************
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		//	sfdc.orders.verifyNotificationIconStatus();
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);

		//3. ****Cable cancel Order********
		sfdc.orders.cancel_Order();

		//4.*******Original order goes in status Cancelled**********
		sfdc.orderDetails.verifyCancelledOrderInOrderDetaisTab();
//		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);

		// Verify No orchestration plan for RDI in original order
		sfdc.orderRelated.verifyNoOrchestrationPlanInOrder();

		// Verify Superseded order generated
		sfdc.orderRelated.verifySupersededOrder();
		sfdc.home.closeTabIfOpen();
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.omData.supersededOrderNumber);
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();

		// 5.**************Verify create account call out and fall task goes into "Failed Discarded" status, 
		// all other tasks are "Discarded", Review Order details task is cancelled *********************
		sfdc.orchPlan.verifyAllTaskItemsStaus("Failed Discarded");
		sfdc.orchPlan.verifyAllTaskItemsStaus("Cancelled");
		sfdc.home.closeLastOpenedTabs(1);

		//*****Verify superseded order is activated
		sfdc.orderDetails.verifyOrderIsActivated();	
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		//6. ***************LOGIN AS Delivery and verify the notification SD receives***********************//
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.closeTabIfOpen();
		sfdc.orders.verifyNotificationInIcon();
		sfdc.orders.verifyOpenTaskStatus_ForCancel_FromAE();	
		
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
	public Object[][] getRDIOrderCancel() throws IOException {
		return getDataSetsRunMode(Constants.MP_TESTDATA_FILE, Constants.GBJ_RDI_RESUME_SHEET);
	}
}
