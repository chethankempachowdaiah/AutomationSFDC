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

public class MACD_TC_08_WACC4769_Verify_RemoveDPSelectEffectiveDate_Test extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	//Test Case is for US  WACC-4769
	@Test(dataProvider = "getDataPlan")
	public void validate_RemoveDP_SelectEffectiveDateFromCalendar(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		reachTillReviewWirelessLine("965796451", "");
		sfdc.reviewWALine.validate_Product("20GB Pooled");
		sfdc.reviewWALine.validate_Product("iPhone 11");
		sfdc.reviewWALine.validate_Product("Device Protection");
		sfdc.reviewWALine.select_Remove_addonsBtn();
		sfdc.macdRemoveAddOn.validate_DPsectionPresent();
		sfdc.macdRemoveAddOn.validateAddOnPresent("Device Protection", "15.99");
		sfdc.macdRemoveAddOn.selectAddOnAndClickRemove("Device Protection");		
		sfdc.macdRemoveAddOn.reviewSelectedDPtoRemove();
		sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
		String[] dateOptionList= { "Today:", "Next billing cycle:", "Choose a date (up to next 45 days)"};
		sfdc.macdSelAddon.validate_selectEffectiveDate_Options("Device Protection", dateOptionList);
		sfdc.macdSelAddon.selectEffectiveDateFromCalendar("Device Protection", "30 April");
		sfdc.macdRemoveAddOn.select_ConfirmEffectiveDateButton();				
	}
	//Test Case is for US  WACC-4769
		//@Test(dataProvider = "getDataPlan")
		public void validate_AddDP_SelectEffectiveDate(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	
			
			reachTillReviewWirelessLine("965796451", "");
			sfdc.reviewWALine.validate_Product("20GB Pooled");
			sfdc.reviewWALine.validate_Product("iPhone 11");
			sfdc.reviewWALine.validate_Product("Device Protection");
			sfdc.reviewWALine.select_Remove_addonsBtn();
			sfdc.macdRemoveAddOn.validate_DPsectionPresent();
			sfdc.macdRemoveAddOn.validateAddOnPresent("Device Protection", "15.99");
			sfdc.macdRemoveAddOn.selectAddOnAndClickRemove("Device Protection");		
			sfdc.macdRemoveAddOn.reviewSelectedDPtoRemove();
			sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
			String[] dateOptionList= { "Today:", "Next billing cycle:", "Choose a date (up to next 45 days)"};
			sfdc.macdSelAddon.validate_selectEffectiveDate_Options("Device Protection", dateOptionList);
			sfdc.macdSelAddon.selectEffectiveDate("Device Protection", "Choose a date", "2022/04/30");			
		}
	//Test Case is for US  WACC-4769
		//@Test(dataProvider = "getDataPlan")
		public void validate_AddDP_SelectEffectiveDate_Today(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	
			
			reachTillReviewWirelessLine("965796451", "");
			sfdc.reviewWALine.validate_Product("20GB Pooled");
			sfdc.reviewWALine.validate_Product("iPhone 11");
			sfdc.reviewWALine.validate_Product("Device Protection");
			sfdc.reviewWALine.select_Remove_addonsBtn();
			sfdc.macdRemoveAddOn.validate_DPsectionPresent();
			sfdc.macdRemoveAddOn.validateAddOnPresent("Device Protection", "15.99");
			sfdc.macdRemoveAddOn.selectAddOnAndClickRemove("Device Protection");		
			sfdc.macdRemoveAddOn.reviewSelectedDPtoRemove();
			sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
			String[] dateOptionList= { "Today:", "Next billing cycle:", "Choose a date (up to next 45 days)"};
			sfdc.macdSelAddon.validate_selectEffectiveDate_Options("Device Protection", dateOptionList);
			sfdc.macdSelAddon.selectEffectiveDate("Device Protection", "Today", "");			
		}
	//Test Case is for US  WACC-4769
		//@Test(dataProvider = "getDataPlan")
		public void validate_AddDP_SelectEffectiveDate_NextBillingCycle(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	
			
			reachTillReviewWirelessLine("965796451", "");
			sfdc.reviewWALine.validate_Product("20GB Pooled");
			sfdc.reviewWALine.validate_Product("iPhone 11");
			sfdc.reviewWALine.validate_Product("Device Protection");
			sfdc.reviewWALine.select_Remove_addonsBtn();
			sfdc.macdRemoveAddOn.validate_DPsectionPresent();
			sfdc.macdRemoveAddOn.validateAddOnPresent("Device Protection", "15.99");
			sfdc.macdRemoveAddOn.selectAddOnAndClickRemove("Device Protection");		
			sfdc.macdRemoveAddOn.reviewSelectedDPtoRemove();
			sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
			String[] dateOptionList= { "Today:", "Next billing cycle:", "Choose a date (up to next 45 days)"};
			sfdc.macdSelAddon.validate_selectEffectiveDate_Options("Device Protection", dateOptionList);
			sfdc.macdSelAddon.selectEffectiveDate("Device Protection", "Next billing cycle", "");			
		}
		
//Method is for common navigation for all test cases
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