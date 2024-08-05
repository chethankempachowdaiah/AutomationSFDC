package com.regression_03.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya
 * 
 * 
 *         MP Release Regression_Sales_TC15_Validate that changes done through
 *         Orchestration actions are reflected on User Dashboard
 * 
 *         MP Release Regression_Sales_TC16_Validate the CAN number updated
 *         while completing task is reflected in Service Account
 *
 */
public class TC_05_Validate_Orchestration_Dahsboard_EditCAN_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              TC - 15 1. User Logged in Successfully and able
	 *                              to Click on R4B Console in App Launcher 2
	 *                              .Orders Object is present in drop Down option
	 *                              and able to select All Order option from drop
	 *                              down 3. Order Detail Open Successfully and able
	 *                              to check the orchestration Plan in related tab
	 *                              4. User is able to access the User Dashboard
	 *                              under Orchestration Plan 5. given List of
	 *                              Actions are displayed which can be performed at
	 *                              every Orchestration Item Name 6. Able to select
	 *                              Assign to me Task from actions and able to
	 *                              select and Save Task 7. Data Update column has
	 *                              been updated (Update will reflect after 15 to 20
	 *                              mins) - not included in automation
	 * 
	 * 
	 *                              TC- 16 8. Able to select Complete Task from
	 *                              actions and pop up window with heading opened as
	 *                              Complete task 9. Following details are
	 *                              displayed: Business Name, Billing Address,
	 *                              Signing Authority Contact, Sales Agent Contact,
	 *                              Site Name Service Address, Site Contact, CAN
	 *                              Number - Mandatory and Editable 10. CAN No is
	 *                              updated, Timestamp is updated in the service
	 *                              account details page 11. Verify the updated Can
	 *                              No, timestamp, account source, user modified by
	 *                              in the account details page. 12. Able to search
	 *                              in the global search with "Business Account" and
	 *                              "Service Account" and able to Validate that the
	 *                              CAN number entered in the above step is NOT
	 *                              reflecting as "Account Number" against "Business
	 *                              Account". But reflecting against "Service
	 *                              Account".
	 */
	@Test
	public void test_Validate_Orchestration_Dahsboard_EditCAN() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.userid_ae);
//		sfdc.home.openR4BSalesConsole();
//		sfdc.home.closeTabIfOpen();

		// ***************Clicked on app launcher and select all order option from the
		// dropdown and select active order from order list*******
		sfdc.orders.verifyOrdersObject();
		sfdc.orders.selectOrderFromMenuItems();
		// sfdc.orders.globalSearchOrders("00000618");

		// ***************Verify Modified time and date before update of
		// CanNo***************
		sfdc.orderDetails.verifydModifiedTimeInServiceBeforeUpdate();
		sfdc.home.closeLastOpenedTabs(1);

		// ***************Click on Orchestration plan number in order related
		// page********************
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();

		// ***************Select Order no and verify fields in Dashboard of
		// orchestration page********************
		sfdc.orchPlan.verifyOrchestrationPageDashboard();

		// ***************Select assign to me task**************************
		sfdc.orchPlan.selectAssignToMeTaskForOrder();

		// ***************Select complete task after selecting the order and verify the
		// fields text in the complete task popup*****
		// Update the canno in the complete task popup window
		sfdc.orchPlan.verifyOrchestrationPageDashboard();
		sfdc.orchPlan.selectCompleteTaskForOrder();
		sfdc.home.closeLastOpenedTabs(1);

		// ***************Verify updated canno, source and modified timestamp in service
		// details page********************
		sfdc.orderDetails.verifyCanNoAndModifiedTimeInServiceAfterUpdate();
		sfdc.home.closeTabIfOpen();

		// ***************Search business account from the global search and the verify
		// the canno with the business account********************
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.tempBusinessAccountName);
		sfdc.orderDetails.verifyCanNoWithBusinessAccountName();

		// **************Logout********//
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();

	}
}
