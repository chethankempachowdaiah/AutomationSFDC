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

public class MACD_TC_01_WACC4465_Verify_RemoveDPOptionTest extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	// Test Case is for US WACC-4465
	@Test(dataProvider = "getDataPlan")
	public void validate_RemoveDP_Option(Hashtable<String, String> dataTable) throws Exception {
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
		sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
	}

	// Test Case is for US WACC-4465
	@Test(dataProvider = "getDataPlan")
	public void validate_RemoveAppleCare_Option(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillReviewWirelessLine("965806706", "");
		sfdc.reviewWALine.validate_Product("60GB Pooled");
		sfdc.reviewWALine.validate_Product("iPhone 11");
		sfdc.reviewWALine.validate_Product("Apple Care +");
		sfdc.reviewWALine.select_Remove_addonsBtn();
		sfdc.macdRemoveAddOn.validate_DPsectionPresent();
		sfdc.macdRemoveAddOn.validateAppleCarePresent("AppleCare+ can't be removed",
				"AppleCare+ is a one-time charge and can't be removed once enrolled.");
		sfdc.macdRemoveAddOn.validate_RemoveAddOnAndContineBtnDisabled();
	}

	// Test Case is for US WACC-4465
	@Test(dataProvider = "getDataPlan")
	public void validate_DPSectionNot_Present(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillReviewWirelessLine("965809536", "");
		sfdc.reviewWALine.validate_Product("60GB Pooled");
		sfdc.reviewWALine.validate_Product("iPhone 11");
		sfdc.reviewWALine.select_Remove_addonsBtn();
		sfdc.macdRemoveAddOn.validate_DPsectionNotPresent();
		sfdc.macdRemoveAddOn.validate_RemoveAddOnAndContineBtnDisabled();
	}

//Method is for common navigation for all test cases
	public void reachTillReviewWirelessLine(String billingAcc, String phoneNum) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accManagement.selectBillingAccount(billingAcc);
		sfdc.accManagement.sfdc.accManagement.select_ManageAccountButton("Skip");
		sfdc.accManagement.select_AccManagementOption("Add-On");
		sfdc.accManagement.click_SelectNumberButton(phoneNum);
	}

	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}

}