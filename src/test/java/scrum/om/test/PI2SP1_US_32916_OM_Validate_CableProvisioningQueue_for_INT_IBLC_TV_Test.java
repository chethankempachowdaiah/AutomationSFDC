package scrum.om.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

public class PI2SP1_US_32916_OM_Validate_CableProvisioningQueue_for_INT_IBLC_TV_Test extends Base {

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              User Story : Review Order Details (MPOSS-32916)
	 * 
	 *                              // for Internet and TV 1. Create Cable Order
	 *                              with Internet and TV products manually. 2.
	 *                              Extract the field value from the order
	 *                              decomposition tab i.e promo code, speed for
	 *                              validation 3. Extract the other field item
	 *                              values from Business account, service account,
	 *                              orchestration page 4. Verify the excel data
	 *                              value items with the order product list in the
	 *                              order related 5. Login with the delivery profile
	 *                              6. Select cable provisioning queue items 7. Pick
	 *                              Up review order detail task 8. Verify all the
	 *                              information in it and validate the field value
	 *                              which we extracted previously for Internet and
	 *                              TV. 9. Complete the task 10. Login with AE
	 *                              profile and validate the orchetsration plan to
	 *                              see whether the review order detail task is
	 *                              completed.
	 * 
	 */

	@Test(dataProvider = "getE2EFlowTestData")
	public void test_Validate_ReviewOrderDeatils_CableProvisioningQueue_For_INT_IBLC_TV(
			Hashtable<String, String> dataTable) throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		System.out.println(dataTable.get("Sl No"));

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		// sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// ***************Extract the order no from excel sheet***********************//
		// sf.dataInput.orderNumber = dataTable.get("Order_Number");
		sf.dataInput.orderNumber = dataTable.get("Order_No").trim();
		System.out.println(sf.dataInput.orderNumber);
		sfdc.orders.globalSearchOrdersWithOutAppLauncher(sf.dataInput.orderNumber);

		// ***************Extract Order details from details
		// tab***********************//
		sfdc.orderDetails.extractOrderDetails("Cable");
//		sfdc.home.closeLastOpenedTab();

		sfdc.orderDetails.extractContactDetailsFromOrderPage();
		sfdc.home.closeLastOpenedTab();

		// ***************Extract Order details from details
		// tab***********************//
		sfdc.orderRelated.extractCableProductDetailsInOrderRelated(dataTable);
//		//	sfdc.orderRelated.extractCableTVProductDetailsInOrderRelated(dataTable);
		sfdc.home.closeLastOpenedTab();
		sfdc.orderDecomPose.extractAttributeValueInOrder(dataTable);

		// ***************Extract Order details from business account
		// details***********************//
		sfdc.orderDetails.verifyOrderNumberInOrderDetails();
		sfdc.orderDetails.extractBusinessAccountDetailsForManualQueue();
		sfdc.home.closeLastOpenedTab();

		// ***************Extract order details from service account details******//
		// Access issue
		sfdc.orderDetails.extractServiceAccountDetailsForManualQueue();
		sfdc.home.closeLastOpenedTabs(2);

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

		// // *****************************Click on manual queue
		// // object***************************************************//

		// sfdc.home.closeLastOpenedTab();
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();

		// 1. *****************************Click on delivery specialist queue and select
		// Review order details task*********//
		sfdc.mques.selectCableOrderProvisioningQueue();
		sfdc.manQue.pickUpOrderInQueueItems(sf.dataInput.queueItemsName.get(0));

		sfdc.cableTaskItems.reviewOrderDetailsComplete_Page(dataTable);
		sfdc.home.closeTabIfOpenWithRefresh();
		sfdc.home.logout();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		// sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// *****verify order status is activated****************************
		sfdc.orders.globalSearchOrders(sf.dataInput.orderNumber);

		// Verify the orchestration plan for RDI
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.verifyOrchestrationPlanForCableOrderAfterCompleteTask();
		sfdc.home.closeLastOpenedTab();

		// **********Close Tabs and Log out********//
		sfdc.home.closeTabIfOpen();
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
