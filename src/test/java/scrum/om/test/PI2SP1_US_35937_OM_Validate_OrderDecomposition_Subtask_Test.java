package scrum.om.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

public class PI2SP1_US_35937_OM_Validate_OrderDecomposition_Subtask_Test extends Base {
	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              User Story : Subtask order layout page
	 *                              enhancement (MPOSS-35937)
	 * 
	 * 
	 *                              1. Ensure that the Order Record page has the
	 *                              following tabs available for AE, SD and BA
	 *                              profiles: - Related - existing tab - Details -
	 *                              existing tab - Order Decomposition - new tab
	 * 
	 *                              2. Ensure that new tab "Order Decomposition"
	 *                              presents the decomposition of commercial product
	 *                              into technical products and attributes.
	 * 
	 *                              3. Decomposition view to be built in the LWC
	 *                              format.
	 * 
	 *                              4. The existing view is to be removed from the
	 *                              order page.
	 * 
	 *                              5. Compare previous view with the new to ensure
	 *                              no functionality is missing (Sys admin profile)
	 * 
	 */

	@Test(dataProvider = "getE2EFlowTestData")
	public void test_Validate_OrderDecomposition(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// **************Login with AE profile*************************
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		// sfdc.login.loginToSFDC(InputData.Profile_BusinessAdmin);
		// sfdc.home.openR4BSalesConsole();
		// sfdc.home.closeTabIfOpen();
		//
		// // ***************Clicked on app launcher and select all order option from
		// the
		// // dropdown and select active order from order list*******
		// sfdc.orders.verifyOrdersObject();
		// sfdc.orders.selectOrderFromMenuItems();
		sf.dataInput.orderNumber = "00261664";
		sfdc.orders.globalSearchOrders(sf.dataInput.orderNumber);

//		// Verify Order Details and Order Related Tab Button
//		sfdc.orderDetails.verifyOrderNumberInOrderDetails();
//		sfdc.orderRelated.verifyOrderRelatedTabButton();
//
//		// Verify Decomposition Tab and the decompose product and attribute of the
//		// product
		sfdc.orderDecomPose.clickDecompositionInOrder();
		sfdc.orderDecomPose.extractWorkOrderAttributeValueInOrderDecomposition(dataTable);
		sfdc.orderDecomPose.verifyDecompositionAttributeInOrder();

		// **********Close Tabs and Log out********//
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// **************Login with BA profile*************************

		sfdc.login.loginToSFDC(InputData.Profile_BusinessAdmin);
		// sfdc.home.openR4BSalesConsole();
		// sfdc.home.closeTabIfOpen();
		//
		// // ***************Clicked on app launcher and select all order option from
		// the
		// // dropdown and select active order from order list*******
		// sfdc.orders.verifyOrdersObject();
		// sfdc.orders.selectOrderFromMenuItems();
		// sf.dataInput.orderNumber = "00008176";
		sfdc.orders.globalSearchOrders(sf.dataInput.orderNumber);

		// Verify Order Details and Order Related Tab Button
		sfdc.orderDetails.verifyOrderNumberInOrderDetails();
		sfdc.orderRelated.verifyOrderRelatedTabButton();

		// Verify Decomposition Tab and the decompose product and attribute of the
		// product
		sfdc.orderDecomPose.clickDecompositionInOrder();
		sfdc.orderDecomPose.verifyDecompositionInOrder();
		sfdc.orderDecomPose.verifyDecompositionAttributeInOrder();

		// **********Close Tabs and Log out********//
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// **************Login with SD profile*************************
		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		// sfdc.login.loginToSFDC(InputData.Profile_BusinessAdmin);
		// sfdc.home.openR4BSalesConsole();
		// sfdc.home.closeTabIfOpen();
		//
		// // ***************Clicked on app launcher and select all order option from
		// the
		// // dropdown and select active order from order list*******
		// sfdc.orders.verifyOrdersObject();
		// sfdc.orders.selectOrderFromMenuItems();
		// sf.dataInput.orderNumber = "00008176";
		sfdc.orders.globalSearchOrders(sf.dataInput.orderNumber);

		// Verify Order Details and Order Related Tab Button
		sfdc.orderDetails.verifyOrderNumberInOrderDetails();
		sfdc.orderRelated.verifyOrderRelatedTabButton();

		// Verify Decomposition Tab and the decompose product and attribute of the
		// product
		sfdc.orderDecomPose.clickDecompositionInOrder();
		sfdc.orderDecomPose.verifyDecompositionInOrder();
		sfdc.orderDecomPose.verifyDecompositionAttributeInOrder();

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
