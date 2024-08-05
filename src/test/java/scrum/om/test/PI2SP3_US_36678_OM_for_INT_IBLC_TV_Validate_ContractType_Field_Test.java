package scrum.om.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

public class PI2SP3_US_36678_OM_for_INT_IBLC_TV_Validate_ContractType_Field_Test extends Base {

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * User Story : Add Contract Type to Manual Task (MPOSS-36678) 
	 * 
	 *                           
	 *           Create 3 premises where Serviceability_JSON is populated for Resi (1A0 /2N1) and Comm contract groups
                 Ensure that Contract Group and Contract Type are presented in General Information sections on the following Manual tasks:
         - Review Order Details
         - Create Account in SS Task Failure
         - Create Cable Order
         - Create Work Order in SS Task Failure

	 *           
	 *      
	 */

	@Test(dataProvider = "getE2EFlowTestData")
	public void test_Validate_ContractType_In_ManualQueue_TaskItems(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		//*********Login with AE profile*******************
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// ***************Extract the order no from excel sheet***********************//
		//	sf.dataInput.orderNumber = "00009835";
		sf.dataInput.orderNumber = dataTable.get("Order_No").trim();
		System.out.println(sf.dataInput.orderNumber);
		sfdc.orders.globalSearchOrdersWithOutAppLauncher(sf.dataInput.orderNumber);

		// ***************Extract Order details from details
		// tab***********************//
		sfdc.orderDetails.extractOrderDetails("Cable");
		sfdc.home.closeLastOpenedTab();
		sfdc.orderDetails.extractServiceAccountDetailsForManualQueue();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************LOGIN AS Delivery***********************//
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.closeTabIfOpen();
		System.out.println(dataTable.get("Office 365 AddOn"));

		// ***************Click on thr review order details task from the orchestration plan**********//
		sfdc.orders.globalSearchOrdersWithOutAppLauncher(sf.dataInput.orderNumber);	
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.clickOnReviewOrderDetailsTask();

		// ********Verify the contract type in the general information section for all the manual tasks *********//
		sfdc.cableTaskItems.reviewOrderDetailsComplete_Page(dataTable);
		sfdc.home.closeLastOpenedTab();

//		sfdc.orchPlan.changeCreateAccountInSSTaskFailureState();
//		sfdc.cableTaskItems.reviewOrderDetailsComplete_Page(dataTable);
//		sfdc.home.closeLastOpenedTab();
//
//		sfdc.orchPlan.clickOnCreateCableOrderTask();
//		sfdc.cableTaskItems.reviewOrderDetailsComplete_Page(dataTable);
//		sfdc.home.closeLastOpenedTab();
//
//		sfdc.orchPlan.clickOnCreateWorkOrderInSSTaskFailureTask();
//		sfdc.cableTaskItems.reviewOrderDetailsComplete_Page(dataTable);
//		sfdc.home.closeLastOpenedTab();

		sfdc.home.closeTabIfOpenWithRefresh();
		sfdc.home.logout();
		softassert.assertAll();
		
		// E2E
////		intializeChrome(false);
//		SFDC_AllPages sfdc = new SFDC_AllPages();
//
//		System.out.println(dataTable.get("Sl No"));
//
//		// ***************LOGIN AS Delivery***********************//
//		//				sfdc.login.loginToSFDC(InputData.Profile_Delivery);
//		//				sfdc.home.closeTabIfOpen();
//		//				sf.dataInput.orderNumber = "00010938";
//		//				Global.dataInput.businessAccountName = "BAAPankaj2";
//		//
//		//		// *************Verify and complete Review order Details task from the manual queue****
//		//		sfdc.mques.clickOnManualQueuesObject();
//		//		sf.dataInput.items_InQueue();
//		//
//		//		// 1. ***********Click on delivery specialist queue and pick Review order details task*********//
//		//		sfdc.mques.selectCableOrderProvisioningQueue();
//		//		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(0));
//		//		sfdc.home.closeTabIfOpen();
//		//		sfdc.home.logout();
//		//		driver.quit();
//
//		// **************Login with AE Profile *****************
//		intializeChrome(false);
//		sfdc.login.loginToSFDC(InputData.Profile_AE);
//		// // sfdc.home.openR4BSalesConsole();
//		sfdc.home.closeTabIfOpen();
//		sf.dataInput.orderNumber = "00010994";
//
//		// *******Extract Order details from details tab And AE profile Information***********//
//		sfdc.orders.globalSearchOrdersWithOutAppLauncher(sf.dataInput.orderNumber);
//		sfdc.orderDetails.extractOrderDetails();
//		sfdc.home.closeLastOpenedTab();
//
//		sfdc.orderDetails.extractAeProfileInformation();
//		sfdc.home.closeLastOpenedTab();
//
//		sfdc.orderDetails.extractContactDetailsFromOrderPage();
//		sfdc.home.closeLastOpenedTab();
//
//		// ***************Extract Order details from details
//		// tab***********************//
//		//	sfdc.orderRelated.extractCableProductDetailsInOrderRelated(dataTable);
//		//		//		//	sfdc.orderRelated.extractCableTVProductDetailsInOrderRelated(dataTable);
//		//				sfdc.home.closeLastOpenedTab();
//		sfdc.orderDecomPose.extractAttributeValueInOrder(dataTable);
//
//		// ***************Extract Order details from business account
//		// details***********************//
//		sfdc.orderDetails.verifyOrderNumberInOrderDetails();
//		sfdc.orderDetails.extractBusinessAccountDetailsForManualQueue();
//		sfdc.home.closeLastOpenedTab();
//
//		// ***************Extract order details from service account details******//
//		sfdc.orderDetails.extractServiceAccountDetailsForManualQueue();
//		sfdc.home.closeLastOpenedTabs(2);
//
//		// ***************Extract order details from site contact details******//
//		sfdc.orderDetails.extractSiteContactDetails();
//		sfdc.home.closeLastOpenedTab();
//
//		// ***************Extract service address details from orchestration plan page
//		// in related tab******//
//		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
//		sfdc.orderDetails.extractServiceAdreesFromOrchestrationPlanPage();
//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
//		//
//		// ***************LOGIN AS Delivery***********************//
//		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
//		sfdc.home.closeTabIfOpen();
//		sf.dataInput.orderNumber = "00010994";
//
//		// *************Verify and complete Review order Details task from the manual queue****
//		sfdc.mques.clickOnManualQueuesObject();
//		sf.dataInput.items_InQueue();
//
//		// 1. *****************************Click on delivery specialist queue and select
//		// Review order details task*********//
//		sfdc.mques.selectCableOrderProvisioningQueue();
//		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(0));
//
//		sfdc.cableTaskItems.reviewOrderDetailsComplete_Page(dataTable);
//		sfdc.home.closeTabIfOpenWithRefresh();
//		sfdc.home.logout();
//
//		//2. ********Verify Welcome Letter**************
//		//		sf.dataInput.orderNumber = "00010392";
//		sfdc.cableTaskItems.verifyEmailAtMailinatorForOrder(sf.dataInput.contactEmailAddress, "welcomeLetter");
//		sfdc.cableTaskItems.verifyFieldValuesInEmail_ForWelcomeLetter(dataTable);
//		driver.quit();
//		//		
//		//3. ***************Click on Create Account In SS task failure From Orchastration Plan*******//
//		intializeChrome(false);
//		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
//		sfdc.home.closeTabIfOpen();
//		sf.dataInput.orderNumber = "00010994";
//		//		sfdc.orders.globalSearchOrders(sf.dataInput.orderNumber);
//		sfdc.orders.globalSearchOrdersWithOutAppLauncher(sf.dataInput.orderNumber);
//		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
//		//		sfdc.orchPlan.verifyAllTaskItemsOrchPlanForCableOrder();
//
//		sfdc.orchPlan.changeCreateAccountInSSTaskFailureState();
//		sfdc.home.closeLastOpenedTab();
//		sfdc.orchPlan.clickOnCreateAccountInSSTaskFailureTask();
//		sfdc.cableTaskItems.createAccountInSSTaskFailureManualTask(dataTable);
//		sfdc.home.closeLastOpenedTab();
//
//		// ****Verify Update Account CAN And CAN creation auto task is completed**
//		sfdc.orchPlan.verifyUpdateAccountCreation_AndCANCreationCompleteTask();
//		sfdc.home.closeTabIfOpenWithRefresh();
//
//		//		//********Verify Billing Account CAN Details******//
//		//		sfdc.orders.globalSearchOrders(sf.dataInput.orderNumber);
//		//		sfdc.orderDetails.verifyBillingAccountNumber(sf.dataInput.billingCAN);
//		////		if(!dataTable.get("Office 365 AddOn").trim().equals("NA")) {
//		////			// Verify Invoice section
//		////			sfdc.orderDetails.verifyBillingAccountInvoiceLink(sf.dataInput.billingBAN);
//		////			sfdc.orderDetails.clickOnBillingAssetForOffice365Product(dataTable);
//		////			sfdc.home.closeLastOpenedTabs(2);
//		////		}
//		//		sfdc.saccRelated.verifyAssetDetailsForCable(dataTable, "billing");
//		//		sfdc.home.closeLastOpenedTabs(2);
//		//
//		//		// ***Verify Service Account CAN NO And Asset details******
//		//		//	sfdc.orderDetails.verifyCANNOInServiceAccountDetails();
//		//		sfdc.home.closeTabIfOpenWithRefresh();
//
//		// 4. ***************Verify Create Cable Order Task from the manual queue*******//
//		sfdc.mques.clickOnManualQueuesObject();
//		sf.dataInput.items_InQueue();
//		sfdc.mques.selectCableOrderProvisioningQueue();
//		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(14));
//		sfdc.cableTaskItems.createCableOrderTask_Page(dataTable);
//
//		if(!dataTable.get("IBLC Product_1").trim().equals("NA")) {
//			sfdc.cableTaskItems.iblcProvisioiningItems(dataTable);
//		}
//		sfdc.cableTaskItems.v21AccountAndSSInfo(dataTable);
//		sfdc.home.closeTabIfOpenWithRefresh();
//
//		// ***************Verify Orchestration Plan after create cable order task***********************//
//		sfdc.orders.globalSearchOrders(sf.dataInput.orderNumber);
//		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
//		sfdc.orchPlan.verifyOrchestrationPlanForCableOrderAfterCompleteTask();
//		sfdc.orchPlan.verifySendAppointmentLetterCompleteTask();
//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
//
//		//5. ********Verify Appointment letter task Item**************
//		// sf.dataInput.orderNumber = "00010301";
//		//		sf.dataInput.orderNumber = dataTable.get("Order_No").trim();
//		sfdc.cableTaskItems.verifyEmailAtMailinatorForOrder(sf.dataInput.contactEmailAddress, "Appointment Letter");
//		sfdc.cableTaskItems.verifyFieldValuesInEmail(dataTable);
//		driver.quit();
//
//		//6. ***************Click on Account Activation Task From Orchestration Plan*******//
//		intializeChrome(false);
//		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
//		sfdc.home.closeTabIfOpen();
//		sf.dataInput.orderNumber = "00010994";
//		sfdc.orders.globalSearchOrdersWithOutAppLauncher(sf.dataInput.orderNumber);
//		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
//		//		sfdc.orchPlan.verifyAllTaskItemsOrchPlanForCableOrder();
//
//		sfdc.orchPlan.clickOnAccountActivationTask();
//		sfdc.home.closeLastOpenedTab();
//
//		//****Verify PONR task is completed**********//
//		//		sfdc.orchPlan.verifyPONRTaskIsCompleted();
//		sfdc.home.closeTabIfOpenWithRefresh();
//
//		// 7. ***************Complete the office 365 task from the manual queue*******//
//
//		if(!dataTable.get("Office 365 AddOn").trim().equals("NA")) {
//
//			sfdc.mques.clickOnManualQueuesObject();
//			sf.dataInput.items_InQueue();
//
//			// *****************************Click on Office 365 queue and select
//			// // pick the order from the queue *********//
//			sfdc.mques.selectOffice365TaskQueue();
//			sfdc.manQue.pickUpOrderInQueueItems(sf.dataInput.queueItemsName.get(13));
//
//			// ********Verify the field values from the office 365 task *****//
//			sfdc.cableTaskItems.verifyOffice365TaskItems(dataTable);
//			sfdc.home.closeTabIfOpenWithRefresh();
//
//			// ********Verify Order is activated after office 365 task is completed *****//
//			sfdc.orders.globalSearchOrdersWithOutAppLauncher(sf.dataInput.orderNumber);
//			sfdc.orderDetails.verifyOrderIsActivated();
//
//			// ********Verify Orchestration plan and check office 365 task is completed *****//
//			sfdc.orderRelated.verifyOrchestrationPlanInOrder();
//			sfdc.orchPlan.verifyOrchestrationPlanForOffice365AfterCompleteTask();
//
//		} else {
//			// ********Verify Order is Activated *****//
//			sfdc.orders.globalSearchOrdersWithOutAppLauncher(sf.dataInput.orderNumber);
//			sfdc.orderDetails.verifyOrderIsActivated();	
//		}
//
//		//********Verify Billing Account CAN Details******//
//		//	sfdc.orders.globalSearchOrders(sf.dataInput.orderNumber);
//		sfdc.home.closeTabIfOpenWithRefresh();
//		sfdc.orders.globalSearchOrdersWithOutAppLauncher(sf.dataInput.orderNumber);
//		sfdc.orderDetails.verifyBillingAccountNumber(sf.dataInput.billingCAN);
//		if(!dataTable.get("Office 365 AddOn").trim().equals("NA")) {
//			// Verify Invoice section
//			sfdc.orderDetails.verifyBillingAccountInvoiceLink(sf.dataInput.billingBAN);
//			sfdc.orderDetails.clickOnBillingAssetForOffice365Product(dataTable);
//			sfdc.home.closeLastOpenedTabs(2);
//		}
//		sfdc.saccRelated.verifyAssetDetailsForCable(dataTable, "billing");
//		sfdc.home.closeTabIfOpenWithRefresh();
//		//		sfdc.home.closeLastOpenedTabs(2);
//
//		// ***Verify Service Account CAN NO And Asset details******
//		//	sfdc.orderDetails.verifyCANNOInServiceAccountDetails();
//
//		//8. ********Verify Closure letter task Item**************
//		// sf.dataInput.orderNumber = "00010301";
//		//		sf.dataInput.orderNumber = dataTable.get("Order_No").trim();
//		sfdc.cableTaskItems.verifyEmailAtMailinatorForOrder(sf.dataInput.contactEmailAddress, "closureLetter");
//		sfdc.cableTaskItems.verifyFieldValuesInEmail_ForClosureLetter(dataTable);
//		driver.quit();
//		softassert.assertAll();
//
//		//	 **********Close Tabs and Log out********//
//		//		sfdc.home.closeTabIfOpen();
//		//		sfdc.home.logout();
//		//		softassert.assertAll();
//
//	}


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

