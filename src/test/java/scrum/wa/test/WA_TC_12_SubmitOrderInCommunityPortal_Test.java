package scrum.wa.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.BaseDataProvider;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;


public class WA_TC_12_SubmitOrderInCommunityPortal_Test extends BaseDataProvider {
	/**
	 * @author Sakshi.Lnu 
	 * @Date  05-May-2021
	 * This Test script includes all the test cases from Sprint 1.2 and Sprint 1.3 for Wireless Accelaration 
	 * 
	 *         
	 * 
	 *         * @param dataTable
	 * @throws Exception
	 * 
	 */
	SFDC_AllPages sfdc = new SFDC_AllPages();
	@Test(dataProvider = "getDataPlan")
	
	public void test_submitOrderInCommunityPortal(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		// ***************LOGIN AS AE***********************//
		Global.dataInput.orderNumber = "00058137";
	    sfdc.comLogin.loginToWaccCommunities();
		sfdc.comMyBusCases.verifyMyBusniessOrders();
		sfdc.comOrderDetails.clickOnIncompleteOrderNumber();
		sfdc.comOrdFlow.selectRequestToPortOrNewNumber(InputData_WA.WACC_requestTypeForNumber); 
		sfdc.comOrdFlow.provideCustomerInformationAndSubmitOrder(InputData_WA.WACC_requestTypeForNumber, InputData_WA.WACC_DeviceType);
		sfdc.comLogin.logoutFromCommunities();		
		softassert.assertAll();
	}

	/**
	 * @return
	 * @throws IOException
	 * 
	 *             Data Provider to fetch multiple set of data and assign them to 2D
	 *             Object Array
	 */
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}