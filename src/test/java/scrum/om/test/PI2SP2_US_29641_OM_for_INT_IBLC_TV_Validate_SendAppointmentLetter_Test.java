package scrum.om.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

public class PI2SP2_US_29641_OM_for_INT_IBLC_TV_Validate_SendAppointmentLetter_Test extends Base {
	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              User Story : Review Order Details (MPOSS-29641)
	 * 
	 *                              // For Internet and Business Phone & TV
	 * 
	 *                              1. Logged in successfully. 2. Created an cable
	 *                              order successfully (manually). 3. Able to see
	 *                              the order related page. 4. Successfully able to
	 *                              see the orchestration plan for the order created
	 *                              5. Completed all the task items from review
	 *                              order details till create cable order task
	 *                              (manually) 6. All the tasks completed is
	 *                              highlighted as green color 7. Sent Appointment
	 *                              letter auto task is triggered successfully i.
	 *                              Sent appointment letter task is completed
	 *                              successfully 8. User is able to see the
	 *                              appointment letter in mentioned email id of site
	 *                              contact and signing authority email id 9.
	 *                              Validating all the information in the
	 *                              appointment letter
	 * 
	 * 
	 * 
	 * 
	 */

	@Test(dataProvider = "getE2EFlowTestData")
	public void test_Validate_GBJ_INT_IBLC_TV_ManualQueue_TaskItems(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// *********Login with AE profile*******************

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
		sfdc.home.closeLastOpenedTab();

		sfdc.orderDetails.extractContactDetailsFromOrderPage();
		sfdc.home.closeLastOpenedTab();

		// ***************Extract Order details from details
		// tab***********************//
		sfdc.orderRelated.extractCableProductDetailsInOrderRelated(dataTable);
		// sfdc.orderRelated.extractCableTVProductDetailsInOrderRelated(dataTable);
		sfdc.home.closeLastOpenedTab();
		// sfdc.orderDecomPose.extractAttributeValueInOrder(dataTable);

		// ***************Extract Order details from business account
		// details***********************//
		sfdc.orderDetails.verifyOrderNumberInOrderDetails();
		sfdc.orderDetails.extractBusinessAccountDetailsForManualQueue();
		sfdc.home.closeLastOpenedTab();

		// ***************Extract order details from service account details******//
		// Access issue
		sfdc.orderDetails.extractServiceAccountDetailsForManualQueue();
		sfdc.home.closeLastOpenedTabs(2);

//		// ***************Extract order details from site contact details******//
//		sfdc.orderDetails.extractSiteContactDetails();
//		sfdc.home.closeLastOpenedTab();

		// ***************Extract service address details from orchestration plan page
		// in related tab******//
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orderDetails.extractServiceAdreesFromOrchestrationPlanPage();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ********Verify Appointment letter part**************
		// sf.dataInput.orderNumber = "00010301";
		sf.dataInput.orderNumber = dataTable.get("Order_No").trim();
		sfdc.cableTaskItems.verifyEmailAtMailinatorForOrder(sf.dataInput.contactEmailAddress, "Appointment Letter");
		sfdc.cableTaskItems.verifyFieldValuesInAppointmentEmail(dataTable);

		// Verify the appointement for site contact
//		sfdc.cableTaskItems.verifyEmailAtMailinatorForOrder(sf.dataInput.emailIdValue, "Apointment Letter");
//		sfdc.cableTaskItems.verifyFieldValuesInEmail(dataTable);

		softassert.assertAll();
		driver.quit();

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
