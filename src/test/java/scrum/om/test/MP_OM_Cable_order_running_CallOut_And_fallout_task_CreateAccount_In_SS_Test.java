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

public class MP_OM_Cable_order_running_CallOut_And_fallout_task_CreateAccount_In_SS_Test extends BaseBrowser{

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
		                    5. Cable only order with running callout task and running fallout task - order cancellation before PONR
						     - Callout task Create Work Order in SS has failed
						     - Delivery agent picks fallout task and starts troubleshooting,
						     - Fallout task status becomes Running
						     - Callout Task status also becomes Running (Story 44547)
						     - AE cancels the order
						     - Original order goes into status "Cancel Requested"
						     - Notification goes to Delivery agent who is Running fallout task and to the order owner if different
						     - Delivery agent validates the order and completes fallout task
						     - Fallout task becomes Completed which makes Callout task to complete
						     - Both fallout and callout tasks immediately become Cancelled
						     - The rest of the tasks become Discarded
						     - Original order goes into status "Cancelled"
						     - Supplemental order is in status "Activated"
	 * 
	 */ 


	@Test(dataProvider = "getRDIOrderCancel")
	public void test_Validate_MP_OM_Cable_order_running_CallOut_And_fallout_task_CreateAccount_In_SS(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		intializeChrome(false);
		SFDC_AllPages sfdc = new SFDC_AllPages();
		setInputData(dataTable);

		sf.dataInput.orderNumber = "00385546";
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
		// comment out as task is not reflecting in the cable error handling queue
		//		sfdc.home.closeTabIfOpen();
		//		sfdc.mques.clickOnManualQueuesObject();
		//		sf.dataInput.items_InQueue();
		//
		//		// 3. ************Click on cable provisioning queue pick the order for create account task as failed status
		//		// And pick the order, it status will change as running after picking
		//		sfdc.mques.selectCableErrorHandlingQueue();
		//		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(15));

		//******Change the state of the task to be ready status from pending**********//
		sfdc.orchPlan.changeCreateAccountInSSTaskFailureState("Running");

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		//2. *********AE Cancels the order*******************
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		//	sfdc.orders.verifyNotificationIconStatus();
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);

		//3. ****Cable cancel Order********
		sfdc.orders.cancel_Order();

		//4.*******Original order goes in status Cancelled**********
		sfdc.orderDetails.verifyCancelledRequestedStatusInOrderDetais(sf.dataInput.orderStatusCancelledRequested);

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		//5. ***************LOGIN AS Delivery and verify the notification SD receives***********************//
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.closeTabIfOpen();
		sfdc.orders.verifyNotificationInIcon();
		sfdc.orders.verifyOpenTaskStatus_ForCancel_FromAE();	

		//6.***Delivery agent picks the fallout task validates the order and completes fallout task****
		sfdc.home.closeTabIfOpen();
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
		//	sfdc.orchPlan.pickCompleteCircuitDesign("Create Account in SS Task Failure");

		// pick the task and click on the radio button for can enter
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.clickOnCreateAccountInSSTaskFailureTask();
		sfdc.cableTaskItems.clickOnCANNoRadioInCreateAccountManualTask(dataTable);

		// verify the task status by clicking on the check box and complete the task
		sfdc.rODComplete.validateTaskStatus_ForCanceledOrder("cable");
		sfdc.home.closeLastOpenedTabs(1);
		sfdc.orchPlan.verifyAllTaskItemsStaus("Cancelled");
		sfdc.home.closeLastOpenedTabs(1);

		//*******Original order goes in status Cancelled**********
		//	sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
		sfdc.orderDetails.verifyCancelledOrderInOrderDetaisTab();

		// Verify No orchestration plan for RDI in original order
		sfdc.orderRelated.verifyNoOrchestrationPlanInOrder();

		// Verify Superseded order generated
		sfdc.orderRelated.verifySupersededOrder();
		sfdc.home.closeTabIfOpen();
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.omData.supersededOrderNumber);

		//*****Verify superseded order is activated
		sfdc.orderDetails.verifyOrderIsActivated();	

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


