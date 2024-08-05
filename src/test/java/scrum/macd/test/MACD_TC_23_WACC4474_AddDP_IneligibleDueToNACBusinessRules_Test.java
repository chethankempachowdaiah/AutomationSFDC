package scrum.macd.test;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MACD_TC_23_WACC4474_AddDP_IneligibleDueToNACBusinessRules_Test extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
		
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-4474
	 *                              user can be able to add DP when order is within 60 days of creation according 
	 *                              to NAC business rules
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_AddDP_AvailableWithin60days(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillReviewWirelessLine("965828064", "");
		sfdc.reviewWALine.validate_Product("40GB Pooled");
		sfdc.reviewWALine.validate_Product("iPhone 11");
		sfdc.reviewWALine.validate_Product("Canada + US");
		sfdc.reviewWALine.select_Add_addonsBtn();
		sfdc.macdRemoveAddOn.validateAddOnPresent("Canada + US", "20");
		sfdc.macdSelAddon.selectAddOn("Device Protection", "Device Protection");
		}
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-4474
	 *                              user cannot be able to add DP when order is after 60 days of creation according 
	 *                              to NAC business rules
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_AddDP_NotAvailableAfter60days(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillReviewWirelessLine("965791346", "1010002841");
		sfdc.reviewWALine.validate_Product("40GB Standalone");
		sfdc.reviewWALine.validate_Product("iPhone 11");
		sfdc.reviewWALine.validate_Product("Canada + US");
		sfdc.reviewWALine.select_Add_addonsBtn();
		sfdc.macdRemoveAddOn.validateAddOnPresent("Canada + US", "20");
		sfdc.macdSelAddon.validate_AddDP_NACbusinessRuleAFter60Days();
	}
		
	public void reachTillReviewWirelessLine(String billingAcc, String phoneNum) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		
		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accManagement.selectBillingAccount(billingAcc);
		sfdc.accManagement.select_ManageAccountButton("Skip");
		sfdc.accManagement.select_AccManagementOption("Add-On");
		sfdc.accManagement.click_SelectNumberButton(phoneNum);
	}
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
		
}
