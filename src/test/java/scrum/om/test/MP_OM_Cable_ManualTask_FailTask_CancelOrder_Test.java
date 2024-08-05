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

public class MP_OM_Cable_ManualTask_FailTask_CancelOrder_Test extends BaseBrowser {

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                            3.  Cable only order with no running tasks, 1 manual task is Failed - order cancellation before PONR
                                      - Delivery agent fails Review Order Details task
									   - AE receives failed task notification and cancels the order
									   - Original order goes in status Cancelled
									   - Supplemental order goes in status Activated
									   - Orchestration plan is unlinked from original order and linked to Supplemental order
									   - Review Order Details task goes into "Failed Discarded" status, all other tasks are "Discarded"
									   - Notifications are sent to the order owner (Company Authorized By) and to the person who failed the task, if different from the order owner
	 * 
	 */ 


	@Test(dataProvider = "getRDIOrderCancel")
	public void test_Validate_RDI_Cancel_Order(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		intializeChrome(false);
		SFDC_AllPages sfdc = new SFDC_AllPages();
		setInputData(dataTable);

		sf.dataInput.orderNumber = "00111080";
		//	sf.omData.canceledOrderNumber = "00060872";
		//		sf.omData.supersededOrderNumber  = "00013382";
		System.out.println(sf.omData.notificationDueDate);

		//	 *********Login with Delivery profile*******************
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.closeTabIfOpen();

		//1. ***************Click on the review order details task from the orchestration plan**********//
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);	
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.clickOnReviewOrderDetailsTask();

		// ********Verify fail task button in the review order details *********//
		//	sfdc.cableTaskItems.reviewOrderDetailsComplete_Page(dataTable);
		sfdc.cableTaskItems.validateFailTaskButton(dataTable);
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		//2. *********Verify Failure Task Icon Notification With AE profile, Cancel the order*******************
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
	    sfdc.orders.verifyNotificationIconStatus_ForTaskFailure();
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);

		//3. ****Cable cancel Order********
		sfdc.orders.cancel_Order();

		//4.*******Original order goes in status Cancelled**********
		sfdc.orderDetails.verifyCancelledOrderInOrderDetaisTab();
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);

		// Verify No orchestration plan for RDI in original order
		sfdc.orderRelated.verifyNoOrchestrationPlanInOrder();

		// Verify Superseded order generated
		sfdc.orderRelated.verifySupersededOrder();
		sfdc.home.closeTabIfOpen();
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.omData.supersededOrderNumber);
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();

		// 5. Verify Review Order Details task goes into "Failed Discarded" status, all other tasks are "Discarded"
		sfdc.orchPlan.verifyAllTaskItemsStaus("Failed Discarded");
		sfdc.home.closeLastOpenedTabs(1);

		//*****Verify superseded order is activated
		sfdc.orderDetails.verifyOrderIsActivated();	

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
