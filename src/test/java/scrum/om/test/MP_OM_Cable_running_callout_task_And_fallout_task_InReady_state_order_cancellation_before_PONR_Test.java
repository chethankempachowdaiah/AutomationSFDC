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

public class MP_OM_Cable_running_callout_task_And_fallout_task_InReady_state_order_cancellation_before_PONR_Test extends BaseBrowser{

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * 5a. Cable only order with running callout task and fallout task in Ready state - order cancellation before PONR
     - Callout task Create Work Order in SS has failed
     - Fallout task status becomes Ready in the error handling queue
     - Callout task status is Running
     - AE cancels the order
     - Original order goes into status "Cancel Requested"
     - Fallout task becomes Frozen while Callout task is still running, preventing cancellation process to go through
     - Notification goes to Delivery agent who is the order owner as order cancelled
     - Delivery agent selects callout task from orchestration plan and completes it manually

	 * 
	 */ 

	@Test(dataProvider = "getRDIOrderCancel")
	public void test_Validate_Cable_running_callout_task_And_fallout_task_InReady_state_order_cancellation_before_PONR(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		intializeChrome(false);
		SFDC_AllPages sfdc = new SFDC_AllPages();
		setInputData(dataTable);

		sf.dataInput.orderNumber = "00111089";
	//	sf.omData.canceledOrderNumber = "00063711";
		//		sf.omData.supersededOrderNumber  = "00013382";
		System.out.println(sf.omData.notificationDueDate);

		// ***************LOGIN AS Delivery***********************//
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.closeTabIfOpen();

//		// *************Verify and complete Review order Details task from the manual queue****
//		sfdc.mques.clickOnManualQueuesObject();
//		sf.dataInput.items_InQueue();
//
//		// 1. *****************************Click on delivery specialist queue and select
//		// Review order details task*********//
//		sfdc.mques.selectCableOrderProvisioningQueue();
//		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(0));
//
//		sfdc.rODComplete.completeReviewOrderDetailsTask("completeTask", "cable");
//		sfdc.home.closeTabIfOpenWithRefresh();
//		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
//		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
//		sfdc.orchPlan.verifyOrchestrationPlanForWelcomeLetterAfterCompleteTask();
//		
//		// if the task is not completed manually close the welcome letter task window after changing the status
//		if(sf.omData.welcomeEmailTaskStatus_CompletedManually == true) {
//			sfdc.home.closeLastOpenedTab();
//		}
//		
		//3. ***************Click on Create Account In SS task failure From Orchastration Plan*******//
//		sfdc.orders.verifyRPATaskFailNotificationIconStatus("createAccount");
//		sfdc.home.closeTabIfOpen();
//		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
//		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		
		//******Change the state of the task to be ready status from pending**********//
		sfdc.orchPlan.changeCreateAccountInSSTaskFailureState("Ready");
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		//3. *********Cancel the order*******************
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
		sfdc.orders.cancel_Order();

		//4.*******Original order goes in status Cancel Requested*********
		sfdc.orderDetails.verifyCancelledRequestedStatusInOrderDetais(sf.dataInput.orderStatusCancelledRequested);
	//	sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
		
		//5.*******Fallout-Auto task becomes Discarded while Callout task is still running*********
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.verifyAllTaskItemsStaus("Frozen");
		sfdc.orchPlan.verifyAllTaskItemsStaus("Running");

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		driver.quit();

		//6. ***************LOGIN AS Delivery and verify the notification SD receives***********************//
		intializeChrome(false);
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
