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

public class MACD_TC_24_WACC4477_DP_ReviewCTN_Changes_Test extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
		
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-4477
	 *                              Add DP and review changes to your wireless add-on's box
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_AddDP_CTN_ReviewChanges_DP(Hashtable<String, String> dataTable) throws Exception {
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
		sfdc.reviewWALine.select_Add_addonsBtn();
		sfdc.macdSelAddon.validateAndClickContinueToDateSelection();
		sfdc.macdSelAddon.validate_ExtraDPInPlan("Device Protection");
		sfdc.macdRemoveAddOn.select_ConfirmEffectiveDateButton();
		String productName[] = {"Device Protection"}; String action[] = {"Adding"}; String price[] = {"8.99"}; String change = "";
		sfdc.reviewWALine.validate_ChangesToWirelessAddOns(productName, action, price, change);
		
	}
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-4477
	 *                              Add Apple care+ and review changes to your wireless add-on's box
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_AddDP_CTN_ReviewChanges_AppleCare(Hashtable<String, String> dataTable) throws Exception {
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
		sfdc.macdSelAddon.selectAddOn("Device Protection", "Apple Care");
		sfdc.reviewWALine.select_Add_addonsBtn();	
		sfdc.macdSelAddon.validateAndClickContinueToDateSelection();
		sfdc.macdSelAddon.validate_ExtraDPInPlan("Apple Care");
		sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
		String[] dateOptionList= { "Today:"};
		sfdc.macdSelAddon.validate_selectEffectiveDate_Options("Device Protection", dateOptionList);
		sfdc.macdSelAddon.AddAppleCareTextOnSelectEffectivedate("AppleCare+ becomes active immediately", "AppleCare+ is active as soon as you enroll.", "Please select today's date above to proceed.");
		sfdc.macdRemoveAddOn.select_ConfirmEffectiveDateButton();

		// Validate Changes to the wireless section in the review page
		sfdc.reviewWALine.validateSuccessfullAddOnChangesMessage();
		String productName[] = {"Apple Care"}; String action[] = {"Adding"}; String price[] = {"199.00"}; 
		sfdc.reviewWALine.validate_ChangesToWirelessAddOns(productName, action, price, "");	
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
