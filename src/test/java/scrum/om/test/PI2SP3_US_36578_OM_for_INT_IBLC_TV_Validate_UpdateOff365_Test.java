package scrum.om.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

public class PI2SP3_US_36578_OM_for_INT_IBLC_TV_Validate_UpdateOff365_Test extends Base {

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              User Story : Office 365 Task (MPOSS-36578)
	 * 
	 *                              // For Internet and office 365 product 1. Create
	 *                              Cable Order with Internet and office 365
	 *                              products manually from gbj. 3. Extract the other
	 *                              field item values from Business account, service
	 *                              account, orchestration page 4. Verify the excel
	 *                              data value items with the order product list in
	 *                              the order related 5. Login with the delivery
	 *                              profile 6. Select Office 365 task item 7. Pick
	 *                              Up the order from the list in running status i.
	 *                              Validate the field in the task. 8. Complete the
	 *                              task 9. Order status should be in complete
	 *                              status 10. order should be activated.
	 * 
	 * 
	 * 
	 */

	@Test(dataProvider = "getE2EFlowTestData")
	public void test_Validate_CreateCableOrder_ManualQueue_TaskItems(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// *********Login with AE profile*******************

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// ***************Extract the order no from excel sheet***********************//
		sfdc.orders.globalSearchOrdersWithOutAppLauncher(sf.dataInput.orderNumber);

		// ***************Extract Order details from details
		// tab***********************//
		sfdc.orderDetails.extractAeProfileInformation();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************LOGIN AS Delivery***********************//
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.closeTabIfOpen();
	//	sf.dataInput.orderNumber = "00009762";
		System.out.println(dataTable.get("Office 365 AddOn"));

		sfdc.orders.globalSearchOrdersWithOutAppLauncher(sf.dataInput.orderNumber);
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.verifyOrchestrationPlanForOffice365BeforeCompleteTask();
		sfdc.home.closeTabIfOpenWithRefresh();

		// *****************************Click on manual queue
		// object***************************************************//
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
		sfdc.orders.globalSearchOrdersWithOutAppLauncher(sf.dataInput.orderNumber);
		sfdc.orderDetails.verifyOrderIsActivated();
		
		// ********Verify Orchestration plan and check office 365 task is completed *****//
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.verifyOrchestrationPlanForOffice365AfterCompleteTask();
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
		return getDataSetsRunMode(Constants.MP_TESTDATA_FILE, Constants.IBLC_E2E_OM_SHEET);
	}
}
