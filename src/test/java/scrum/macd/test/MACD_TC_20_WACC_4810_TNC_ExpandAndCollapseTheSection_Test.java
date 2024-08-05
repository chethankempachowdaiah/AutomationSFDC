package scrum.macd.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class MACD_TC_20_WACC_4810_TNC_ExpandAndCollapseTheSection_Test extends MyListener {

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-4810
	 *                              TNC user fills the details and goes to second section first section should be collapse
	 *                              and it can be edit and previous details should be remembered.
	 */
	@Test(dataProvider = "getDataPlan")
	public void TNC_validate_sectionCollapseAndExpand(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);
		
		reachTillReviewWirelessLine("965828064", "");
		sfdc.macdTelephNumChange.choose_ReasonToChangeTelePhNum("Change initiated by Rogers");
		sfdc.macdTelephNumChange.select_NumChangeLocation("ON", "TORONTO");
		sfdc.macdTelephNumChange.validate_stepOneLocation("ON");
		
	}
	
	
	
	public void reachTillReviewWirelessLine(String billingAcc, String phoneNum) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accManagement.selectBillingAccount(billingAcc);
		sfdc.accManagement.select_ManageAccountButton("Skip");
		sfdc.accManagement.select_AccManagementOption("Telephone Number Change");
		sfdc.macdTelephNumChange.click_SelectNumberButtonForTNC(phoneNum);
	}

	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}

}
