package com.Regression_WA_Prod_test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.BaseDataProvider;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;

public class WA_TC_04_VerifyOrderStatus_Community_Scrape_Test extends BaseDataProvider {
	/**
	 * @author Sakshi.Lnu
	 * @Date 05-May-2021
	 ** @param dataTable
	 * @throws Exception
	 * 
	 */
	SFDC_AllPages sfdc = new SFDC_AllPages();

	@Test(dataProvider = "getDataPlan")
	public void launchBrowserAndLogin(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);
		if (InputData_WA.env.equalsIgnoreCase("WADEVQA") || InputData_WA.env.equalsIgnoreCase("PREFIT")) {
			sfdc.comLogin.loginToWaccCommunities();
		} else {
			sfdc.comLogin.loginToCommunities();
		}

	//	 Global.dataInput.orderNumber = "00083167";

		// sf.seleU.hardwait(60000);
	//	sfdc.comMyBusCases.verifyMyBusniessOrders();
		sfdc.comOrderDetails.clickOnIncompleteOrderNumber();
		sfdc.comOrdFlow.selectRequestToPortOrNewNumber(InputData_WA.WACC_requestTypeForNumber);
		sfdc.comOrdFlow.provideCustomerInformationAndSubmitOrder(InputData_WA.WACC_requestTypeForNumber,
				InputData_WA.WACC_DeviceType);
		sfdc.comLogin.logoutFromCommunities();
		sfdc.login.loginToSFDCInNewTab(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.orders.selectOrder();
		sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(Global.dataInput.orderStatusInProgress);
		sfdc.home.closeTabIfOpen();
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
		// return getDataSetsRunMode(Constants.WA_TESTDATA_FILE,
		// Constants.WIRELESS_PLANS_SELECTION_SHEET_PreProd);
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
	
}
