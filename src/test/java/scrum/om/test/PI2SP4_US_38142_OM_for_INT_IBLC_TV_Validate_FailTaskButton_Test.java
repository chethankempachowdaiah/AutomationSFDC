package scrum.om.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.BaseBrowser;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

public class PI2SP4_US_38142_OM_for_INT_IBLC_TV_Validate_FailTaskButton_Test extends BaseBrowser {
	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              User Story : Fail Task Button (MPOSS-38142)
	 *                      Enhancement :: Fail task button is for all the manual tasks(review order details, create cable order,
	 *                              error handling tasks)
	 *                              // For Internet and Business Phone & TV
	 *                              
	 *   1. Ensure that "Fail Task" functionality is available for manual tasks Review Order Details and Create Cable Order
         2. Fail Task functionality should be presented below Validate Order Status and available 
         only after order status is validated
         3. Fail Task functionality should be not be presented when order status is "Cancelled"
         4. When agent selects "Fail Task" (Plus button):
    		- Failure Reason text box needs to be presented and becomes mandatory
    		- Button caption will change from "+" to checkmark
		 5. After agent populates Failure Reason, they will need to select "Complete".
   			- The task status to be flipped to "Failed" and it will stay available in the queue
   			- Notification and email will be sent to Account Executive
   			- This functionality should be the same as in Cable L1
		 6. If the agent selected Fail Task by mistake or they changed their mind before they selected "Complete":
   			-They will select checked button
   			- Failure Reason will disappear
		 7. Ensure that when agent picks the Failed task from the queue, the status of the task is flipped to "Running"
		 8. If this order is cancelled while the task is in the Failed status the task is to be cancelled and discarded. 
	 * 
	 *                            
	 */

	@Test(dataProvider = "getE2EFlowTestData")
	public void test_Validate_OM_INT_IBLC_TV_FailTaskButton_In_ManualTasks_TaskItems(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		intializeChrome(false);
		SFDC_AllPages sfdc = new SFDC_AllPages();
		sf.dataInput.orderNumber = "00385531";
		// ***************LOGIN AS Delivery***********************//
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.closeTabIfOpen();

//		// ***************Click on thr review order details task from the orchestration plan**********//

		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);	
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.clickOnReviewOrderDetailsTask();

		// ********Verify fail task button in the review order details *********//
		//	sfdc.cableTaskItems.reviewOrderDetailsComplete_Page(dataTable);
		sfdc.cableTaskItems.validateFailTaskButton(dataTable);
		sfdc.home.closeTabIfOpenWithRefresh();

		// *****************************Click on manual queue Object*******//	
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();

		// 1. ********Click on cable provisioning queue and verify failed status in the queue*********//
		sfdc.mques.selectCableOrderProvisioningQueue();
		sfdc.manQue.verifyOrderStatusFailed();
		sfdc.home.closeTabIfOpenWithRefresh();
		sfdc.home.logout();

		//2. *********Verify Failure Task Icon Notification With AE profile*******************
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		//	sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.orders.verifyNotificationIconStatus_ForTaskFailure();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************LOGIN AS Delivery***********************//
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.closeTabIfOpen();
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();

		// 3. ************Click on cable provisioning queue pick the order in review order details task as failed status
		// And pick the order, it status will change as running after picking
		sfdc.mques.selectCableOrderProvisioningQueue();
		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(0));
		sfdc.home.closeLastOpenedTab();
		//	sfdc.mques.selectCableOrderProvisioningQueue();
		sfdc.manQue.verifyOrderRunning();
		driver.quit();
		//		sfdc.home.closeTabIfOpen();

		// 4. *************Complete the welcome letter task***********************
		intializeChrome(false);
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.closeTabIfOpen();
		sfdc.orders.selectOrder();
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.clickOnReviewOrderDetailsTask();
		sfdc.orchPlan.completeReviewOrderDetailsTask();
		sfdc.home.closeLastOpenedTab();
		sfdc.orchPlan.verifyOrchestrationPlanForWelcomeLetterAfterCompleteTask();
		sfdc.home.closeTabIfOpen();

		//5. ***************Click on Create Account In SS task failure From Orchastration Plan*******//
		//		sfdc.home.closeTabIfOpen();
		sfdc.orders.selectOrder();
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();

		//******Change the state of the task and complete the task**********//
		sfdc.orchPlan.changeCreateAccountInSSTaskFailureState("Ready");
		sfdc.home.closeLastOpenedTab();

		//ii. ********Verify Task failure for create account in SS task failure*******
		//		sfdc.orchPlan.clickOnCreateAccountInSSTaskFailure();
		sfdc.orchPlan.clickOnCreateAccountInSSTaskFailureTask();
		sfdc.cableTaskItems.clickOnCANNoRadioInCreateAccountManualTask(dataTable);

		// ********Verify fail task button in the review order details *********//
		//	sfdc.cableTaskItems.reviewOrderDetailsComplete_Page(dataTable);
		sfdc.cableTaskItems.validateFailTaskButton(dataTable);
		sfdc.home.closeTabIfOpenWithRefresh();

		// *****************************Click on manual queue Object*******//	
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();

		// 1. ********Click on cable error handling queue and verify failed status in the queue*********//
		sfdc.mques.selectCableErrorHandlingQueue();
		sfdc.manQue.verifyOrderStatusFailed();
		sfdc.home.closeTabIfOpenWithRefresh();
		sfdc.home.logout();

		//2. *********Verify Failure Task Icon Notification With AE profile*******************
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.orders.verifyNotificationIconStatus_ForTaskFailure();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		driver.quit();

		// ***************LOGIN AS Delivery***********************//
		intializeChrome(false);
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.closeTabIfOpen();
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();

		// 3. ************Click on cable provisioning queue pick the order for create account task as failed status
		// And pick the order, it status will change as running after picking
		sfdc.mques.selectCableErrorHandlingQueue();
		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(15));
		sfdc.home.closeLastOpenedTab();
		//		sfdc.mques.selectCableOrderProvisioningQueue();
		sfdc.manQue.verifyOrderRunning();

		//4. ************Change the create account in SS manual task status from running to complete******
		sfdc.home.closeTabIfOpen();
		sfdc.orders.selectOrder();
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.clickOnCreateAccountInSSTaskFailureTask();
		sfdc.cableTaskItems.enterCANNO_InCreateAccountInSSTaskFailureManualTask(dataTable);

		//5. ****Verify Update Account CAN And CAN creation auto task is completed*******//
		sfdc.home.closeTabIfOpen();
		sfdc.orders.selectOrder();
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.verifyUpdateAccountCreation_AndCANCreationCompleteTask();
		sfdc.home.closeTabIfOpenWithRefresh();

		//*********************************************************************************************
		//iii. ********Verify Fail task button for create cable order task****************//
		// ***************LOGIN AS Delivery***********************//

		// ***************Click on the review order details task from the orchestration plan**********//
		sfdc.orders.selectOrder();
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.clickOnCreateCableOrderTask();

		// ********Verify fail task button in the review order details *********//
		//	sfdc.cableTaskItems.reviewOrderDetailsComplete_Page(dataTable);
		sfdc.cableTaskItems.validateFailTaskButton(dataTable);
		sfdc.home.closeTabIfOpenWithRefresh();

		// *****************************Click on manual queue Object*******//	
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();

		// 1. ********Click on cable provisioning queue and verify failed status in the queue*********//
		sfdc.mques.selectCableOrderProvisioningQueue();
		sfdc.manQue.verifyOrderStatusFailed();
		sfdc.home.closeTabIfOpenWithRefresh();
		sfdc.home.logout();

		//2. *********Verify Failure Task Icon Notification With AE profile*******************
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.orders.verifyNotificationIconStatus_ForTaskFailure();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		driver.quit();

		// ***************LOGIN AS Delivery***********************//
		intializeChrome(false);
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.closeTabIfOpen();
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();

		// 3. ************Click on cable provisioning queue pick the order in review order details task as failed status
		// And pick the order, it status will change as running after picking
		sfdc.mques.selectCableOrderProvisioningQueue();
		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(14));
		sfdc.home.closeLastOpenedTab();
		//		sfdc.mques.selectCableOrderProvisioningQueue();
		sfdc.manQue.verifyOrderRunning();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// Cancel the Order And fail task button should not be present in the manual tasks
		//4. *********Login With AE profile*******************
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.orders.selectOrder();
		sfdc.orders.cancel_Order();
		sfdc.home.closeLastOpenedTab();
		sfdc.orderDetails.verifyCancelledRequestedStatusInOrderDetais(sf.dataInput.orderStatusCancelledRequested);
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.verifyAllTaskItemsStaus("Frozen");

		// Click on create cable order task, to verify fail task button is not visible when order is canceled
		sfdc.orchPlan.clickOnCreateCableOrderTask();
		sfdc.cableTaskItems.validateFailTaskButton_ForCanceledOrder(dataTable);
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		driver.quit();
		//	softassert.assertAll();

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
		return getDataSetsRunMode(Constants.MP_TESTDATA_FILE, Constants.IBLC_E2E_OM_SHEET);
	}
}
