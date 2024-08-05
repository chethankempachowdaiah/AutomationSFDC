package scrum.om.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

public class OM_Regression_E2E_CableL2_For_All_INT_IBLC_TV_Test extends Base {
	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * 
	 * 
	 *                              // E2E => OM Cable L2 Flow
	 * 
	 */

	@Test(dataProvider = "getE2EFlowTestData")
	public void test_Validate_ReviewOrderDeatils_CableProvisioningQueue_For_INT_IBLC_TV(
			Hashtable<String, String> dataTable) throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		//	 ***************LOGIN AS Delivery***********************//
//		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
//		sfdc.home.closeTabIfOpen();

		sf.dataInput.orderNumber = "00118531";

		//	//	sf.dataInput.orderNumber = dataTable.get("Order_No").trim();
		//		System.out.println(sf.dataInput.orderNumber);
		//
		//1. ******Verify Order Pick up in Cable Order prov Queue To make it running***//
//		sfdc.mques.clickOnManualQueuesObject();
//		sf.dataInput.items_InQueue();
//		sfdc.mques.selectCableOrderProvisioningQueue();
//		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(0));
//		sf.seleU.closeRecentlyOpenedWindow();
//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();

		//2. ****AE logs in, Extract Order details and AE Profile********//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// Verify list view icon
		sfdc.orders.validateOrderListViewIcon();
		sfdc.orders.selectOrder();		

		sfdc.orderDetails.extractOrderDetails("Cable");
		sfdc.home.closeLastOpenedTab();
		sfdc.orderDetails.extractAeProfileInformation();
		sfdc.home.closeLastOpenedTab();
		sfdc.orderDetails.extractContactDetailsFromOrderPage();
		sfdc.home.closeLastOpenedTab();

		// ******Extract Order details from Decomposition tab********//
		// sfdc.orderRelated.extractCableProductDetailsInOrderRelated(dataTable);
		// sfdc.orderRelated.extractCableTVProductDetailsInOrderRelated(dataTable);
		// sfdc.home.closeLastOpenedTab();
		sfdc.orderDecomPose.extractAttributeValueInOrder(dataTable);
		//sfdc.orderDecomPose.extractWorkOrderAttributeValueInOrderDecomposition(dataTable);

		// ***************Extract Order details from business account
		// details***********************//
		sfdc.orderDetails.verifyOrderNumberInOrderDetails();
		sfdc.orderDetails.extractBusinessAccountDetailsForManualQueue();
		sfdc.home.closeLastOpenedTab();

		// ***************Extract order details from service account details******//
		sfdc.orderDetails.extractServiceAccountDetailsForManualQueue();
		sfdc.home.closeLastOpenedTabs(1);

		// ***************Extract order details from site contact details******//
		sfdc.orderDetails.extractSiteContactDetails();
		sfdc.home.closeLastOpenedTab();

		// ***************Extract service address details from orchestration plan page
		// in related tab******//
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orderDetails.extractServiceAdreesFromOrchestrationPlanPage();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************LOGIN AS Delivery***********************//
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.closeTabIfOpen();

		//3. **Verify and complete Review order Details task from the manual queue****//	
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();
		sfdc.mques.selectCableOrderProvisioningQueue();
		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(0));
		sfdc.cableTaskItems.reviewOrderDetailsComplete_Page(dataTable);
		sfdc.home.closeTabIfOpen();
		sf.seleU.closeRecentlyOpenedWindow();
		//	sfdc.orders.selectOrder();
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.verifyOrchestrationPlanForWelcomeLetterAfterCompleteTask();
		sfdc.home.closeLastOpenedTab();

		//Commented by Prab Sharan Singh/ Since unable to receive emails in mailinator
		// 4. ********Verify Welcome Letter Email**************
	/*sfdc.cableTaskItems.verifyEmailAtMailinatorForOrder(sf.dataInput.contactEmailAddress, "welcomeLetter");
		sfdc.cableTaskItems.verifyFieldValuesInEmail_ForWelcomeLetter(dataTable);
		sf.seleU.switchWindow(1);
		sf.seleU.closeRecentlyOpenedWindow();*/
		sf.seleU.wait(2000);
		sfdc.orderRelated.clickonletterlink("WelcomeLetterCable");
		sfdc.cableTaskItems.verifyFieldValuesInEmail_ForWelcomeLetter(dataTable);
		sf.seleU.switchToDefaultContent();
		sf.seleU.wait(5000);
		sfdc.home.closeLastOpenedTab();

		// 5. ****Click on Create Account In SS task failure From Orchastration Plan*******//
		// ***** verify RPA failed task notification for fall out task******
		// sfdc.orders.verifyRPATaskFailNotificationIconStatus("createAccount");
		// sfdc.home.closeTabIfOpen();
		//sfdc.orders.selectOrder();
		//sfdc.orderRelated.verifyOrchestrationPlanInOrder();	
		//sfdc.home.closeLastOpenedTab();

		// Adding a delay of 15mins for the create account in SS RPA job to get the response	
		sf.seleU.wait(20000);
		sf.seleU.refreshPage();

	//	sfdc.orchPlan.changeCreateAccountInSSTaskFailureState("Ready");
	//	sfdc.home.closeLastOpenedTab();
	
	//  Picking up the task from the Manual Queue since unable to pick it up from the orchestration Plan
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();
		sfdc.mques.selectCableErrorHandlingQueue();
		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(15));
		sfdc.cableTaskItems.enterCANNO_InCreateAccountInSSTaskFailureManualTask(dataTable);
		sfdc.home.closeTabIfOpen();
		sf.seleU.closeRecentlyOpenedWindow();
	//	Unable to pick up task from the orchestration plan
		/*sfdc.orchPlan.clickOnCreateAccountInSSTaskFailureTask();
		sfdc.cableTaskItems.enterCANNO_InCreateAccountInSSTaskFailureManualTask(dataTable);
		sfdc.home.closeLastOpenedTab();*/

		//####### In Order to Bypass the Defect Completing the task manually#####
		//		sfdc.orchPlan.changeCreateAccountInSSTaskFailureState_ToComplete("Completed");
		//		sfdc.home.closeLastOpenedTab();

		//6. ***Verify Update Account CAN And CAN creation auto task is completed*******//
		
		sfdc.orchPlan.verifyUpdateAccountCreation_AndCANCreationCompleteTask();
		sfdc.home.closeTabIfOpen();

		// 7. *****Verify Create Cable Order Task from the manual queue*******//	
		// This will not be skipped for Office 365 or IBLC product if included in the product

		if (!dataTable.get("Office 365 AddOn").trim().equals("NA")
				|| !dataTable.get("IBLC Product_1").trim().equals("NA")) {

			sfdc.mques.clickOnManualQueuesObject();
			sf.dataInput.items_InQueue();
			sfdc.mques.selectCableOrderProvisioningQueue();
			sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(14));
			sfdc.cableTaskItems.createCableOrderTask_Page(dataTable);

			if (!dataTable.get("IBLC Product_1").trim().equals("NA")) {
				sfdc.cableTaskItems.iblcProvisioiningItems(dataTable);
			}
			sfdc.cableTaskItems.v21AccountAndSSInfo(dataTable);
			sfdc.home.closeTabIfOpenWithRefresh();
			sf.seleU.closeRecentlyOpenedWindow();
			sfdc.home.closeTabIfOpen();
		} else {
			// for orders which is not having office 365 or IBLC orders, commenting out as
			// RPA notification is not getting triggered

			// sfdc.orders.verifyRPATaskFailNotificationIconStatus("createWorkOrder");
			sfdc.home.closeTabIfOpen();
			sfdc.orders.selectOrder();
			//		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
			sfdc.orderRelated.verifyOrchestrationPlanInOrder();

			// ***Change the state of the work order task and complete the task******//		
			// Adding a delay of 15mins for the RPA job to get the response
			sf.seleU.wait(20000);
			sf.seleU.refreshPage();

			sfdc.orchPlan.changeWorkOrderInSSTaskFailureState();
			sfdc.home.closeLastOpenedTab();
		//Picking up the task from the manual queue, unable to pick up from the orchestration plan	
			sfdc.mques.clickOnManualQueuesObject();
			sf.dataInput.items_InQueue();
			sfdc.mques.selectCableErrorHandlingQueue();
			sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(16));
			sfdc.cableTaskItems.superSystemInfoForWorkOrder(dataTable);
			sfdc.home.closeTabIfOpen();
			sf.seleU.closeRecentlyOpenedWindow();
		// Commented because unable to pick up the task from the orchestration plan	
		/*	sfdc.orchPlan.clickOnCreateWorkOrderInSSTaskFailureTask();
			sfdc.cableTaskItems.superSystemInfoForWorkOrder(dataTable);
			sfdc.home.closeTabIfOpen();*/
		}

		//8. *****Verify Create cable order and Appointment Letter task is completed***//
		//sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
		sfdc.orders.selectOrder();
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		if (!dataTable.get("Office 365 AddOn").trim().equals("NA")
				|| !dataTable.get("IBLC Product_1").trim().equals("NA")) {
			sfdc.orchPlan.verifyOrchestrationPlanForCableOrderAfterCompleteTask();
		}
		
		
		//sfdc.orchPlan.verifySendAppointmentLetterCompleteTask();	
//Commented out because no mails are being received in Mailinator
		// 9. ********Verify Send Appointment letter Email *******
		/*sfdc.cableTaskItems.verifyEmailAtMailinatorForOrder(sf.dataInput.contactEmailAddress, "Appointment Letter");
		sfdc.cableTaskItems.verifyFieldValuesInAppointmentEmail(dataTable);
		sf.seleU.switchWindow(1);
		sf.seleU.closeRecentlyOpenedWindow();*/
		


		// 10. ***************Click on Account Activation Task and Complete it****		
		sfdc.orchPlan.clickOnAccountActivationTask();
		sfdc.home.closeLastOpenedTab();

		// ****Verify PONR task is completed**********//
		sfdc.orchPlan.verifyPONRTaskIsCompleted();
		//sfdc.home.closeTabIfOpen();
		sfdc.home.closeLastOpenedTab();
		// Verify details for Appointment Letter
		sfdc.orderRelated.clickonletterlink("Appointment Letter");
		sfdc.cableTaskItems.verifyFieldValuesInAppointmentEmail(dataTable);
		sf.seleU.switchToDefaultContent();
		sf.seleU.wait(5000);
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		//		//11.********Order cancel Once PONR is reached*************//
		//		if (!dataTable.get("Office 365 AddOn").trim().equals("NA")) {
		//			sfdc.login.loginToSFDC(InputData.Profile_AE);
		//			sfdc.home.closeTabIfOpen(); 
		//			sfdc.orders.selectOrder();
		//			// ****Cable cancel Order********
		//			sfdc.orders.cancel_Order();
		//			sfdc.home.closeTabIfOpen();
		//			sfdc.home.logout();
		//		}

		// ******************************************************
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.closeTabIfOpen();

		// 12. ***************Complete the office 365 task from the manual queue*******//
		if (!dataTable.get("Office 365 AddOn").trim().equals("NA")) {

			sfdc.mques.clickOnManualQueuesObject();
			sf.dataInput.items_InQueue();
			// *****************************Click on Office 365 queue and select
			// // pick the order from the queue *********//
			sfdc.mques.selectOffice365TaskQueue();
			sfdc.manQue.pickUpOrderInQueueItems(sf.dataInput.queueItemsName.get(13));

			// ********Verify the field values from the office 365 task *****//
			sfdc.cableTaskItems.verifyOffice365TaskItems(dataTable);
			sfdc.home.closeTabIfOpenWithRefresh();

			// ********Verify Order is activated after office 365 task is completed *****//
			//	sfdc.orders.selectOrder();
			sfdc.orders.selectOrder();
			sfdc.orderDetails.verifyOrderIsActivated();

			//**Verify Orchestration plan and check office 365 task is completed **//
			// sfdc.orderRelated.verifyOrchestrationPlanInOrder();
			// sfdc.orchPlan.verifyOrchestrationPlanForOffice365AfterCompleteTask();

			// //****complete closure letter task manually********//
			// sfdc.orchPlan.completeSendClosureLetterTaskManually();

		} else {
			// ********Verify Order is Activated *****//
			sfdc.orders.selectOrder();
			sfdc.orderDetails.verifyOrderIsActivated();

			//**** verify order decomposition field itesm
			sfdc.orderDecomPose.extractWorkOrderAttributeValueInOrderDecomposition(dataTable);
			sfdc.orderRelated.verifyOrchestrationPlanInOrder();	

			// ****complete closure letter task manually if not complete automatically*****//
			sfdc.orchPlan.verifyOrchestrationPlanForClosureLetterAfterCompleteTask();
		}

		// 13. ********Verify Billing Account CAN Details******//
		sfdc.home.closeTabIfOpenWithRefresh();
		sfdc.orders.selectOrder();
		//	sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
		//		sf.dataInput.tempBusinessAccountName = "MP21BTF";
		sfdc.orderDetails.verifyBillingAccountNumber(sf.dataInput.billingCAN, "Cable");

		//14.************************Verify Invoice section Only For Off365 Product*******
		if(!dataTable.get("Office 365 AddOn").trim().equals("NA")) {		 
			sfdc.orderDetails.verifyBillingAccountInvoiceLink(sf.dataInput.billingBAN);
			sfdc.orderDetails.clickOnBillingAssetForOffice365Product(dataTable);
			sfdc.home.closeLastOpenedTabs(2);		
		}		 
		sfdc.saccRelated.verifyAssetDetailsForCable(dataTable, "billing");
		sfdc.home.closeLastOpenedTabs(2);

		//15. ***Verify Service Account CAN NO And Asset details***********//
		sfdc.orderDetails.verifyCANNOInServiceAccountDetails("Cable");
		sfdc.saccRelated.verifyAssetDetailsForCable(dataTable, "Service");
		sfdc.home.closeTabIfOpen();
		
		//Verify Closure Letter details from the Activity Tab
		sfdc.orders.selectOrder();
		sfdc.orderRelated.clickonletterlink("Closure Letter");
		sfdc.cableTaskItems.verifyFieldValuesInEmail_ForClosureLetter(dataTable);
		sf.seleU.switchToDefaultContent();
		sf.seleU.wait(5000);
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		//Commented out because no mails are being received in Mailinator
		// 16. ********Verify Closure letter task Item**************
		/*sfdc.cableTaskItems.verifyEmailAtMailinatorForOrder(sf.dataInput.contactEmailAddress, "closureLetter");
		sfdc.cableTaskItems.verifyFieldValuesInEmail_ForClosureLetter(dataTable);
		sf.seleU.switchWindow(1);
		sf.seleU.closeRecentlyOpenedWindow();*/
		
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
		return getDataSetsRunMode(Constants.MP_TESTDATA_FILE, Constants.IBLC_E2E_OM_SHEET);
	}
}
