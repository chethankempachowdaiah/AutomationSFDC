package scrum.om.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

public class PI2SP3_US_38951_OM_for_INT_IBLC_TV_Validate_QuoteLink_Field_Test extends Base {

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * User Story (MPOSS-38951)  : 
		Verify Quote Link/ID is present in the order details page for all the account status
	 * 
	 *                                     
	 *      
	 */

	@Test(dataProvider = "getE2EFlowTestData")
	public void test_Validate_QuoteLink_Field_In_OrderDetails(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		//*********Login with AE profile*******************
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// ***************Extract the order no from excel sheet***********************//
		sf.dataInput.orderNumber = dataTable.get("Order_No").trim();
		sfdc.orders.globalSearchOrdersWithOutAppLauncher(sf.dataInput.orderNumber);

		// ***************EVerify the Quote Linka nd Quote Number is displayed*********//
		sfdc.orderDetails.verifyQuoteLinkInOrderDetails();
		sfdc.quoteDetails.verifyQuoteNumberInQuotePage();
		
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
