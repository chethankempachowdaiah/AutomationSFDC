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

public class MACD_TC_04_WACC3693_Verify_AddDisplayVoicemailResetWarning_Test extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	//Test Case is for US  WACC-3693
	@Test(dataProvider = "getDataPlan")
	public void validate_AddVoicemail_Confirm(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		reachTillReviewWirelessLine("965825151", "");
		sfdc.reviewWALine.validate_Product("40GB Pooled");
		sfdc.reviewWALine.validate_Product("iPhone 11");
		sfdc.reviewWALine.validate_Product("Premium Voicemail to Text");	
		sfdc.reviewWALine.select_Add_addonsBtn();		
		sfdc.macdRemoveAddOn.validateAddOnPresent("Premium Voicemail to Text", "7.00");
		sfdc.macdSelAddon.validate_ExistingAddOnNotDisplayed("Voicemail", "Premium Voicemail to Text");
		sfdc.macdSelAddon.selectAddOn("Voicemail","iPhone Visual Voicemail");
		sfdc.reviewWALine.select_Add_addonsBtn();		
		sfdc.macdRemoveAddOn.validate_VoiceMailWarningMsg("If this line has voicemail, it will be reset.", "Voicemail needs to be reset for any new voicemail-related add-on requests.");
		sfdc.macdRemoveAddOn.select_ConfirmAndProceedBtn();
		sfdc.macdSelAddon.validateAndClickContinueToDateSelection();
		sfdc.macdSelAddon.validate_ExtraAddonInPlan("iPhone Visual Voicemail");
		sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
	}
	//Test Case is for US  WACC-3693
		@Test(dataProvider = "getDataPlan")
		public void validate_AddVoicemail_Cancel(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	
			
			reachTillReviewWirelessLine("965825151", "");
			sfdc.reviewWALine.validate_Product("40GB Pooled");
			sfdc.reviewWALine.validate_Product("iPhone 11");
			sfdc.reviewWALine.validate_Product("Premium Voicemail to Text");			
			sfdc.reviewWALine.select_Add_addonsBtn();		
			sfdc.macdRemoveAddOn.validateAddOnPresent("Premium Voicemail to Text", "7.00");
			sfdc.macdSelAddon.validate_ExistingAddOnNotDisplayed("Voicemail", "Premium Voicemail to Text");
			sfdc.macdSelAddon.selectAddOn("Voicemail","iPhone Visual Voicemail");
			sfdc.reviewWALine.select_Add_addonsBtn();		
			sfdc.macdRemoveAddOn.validate_VoiceMailWarningMsg("If this line has voicemail, it will be reset.", "Voicemail needs to be reset for any new voicemail-related add-on requests.");
			sfdc.macdRemoveAddOn.select_CancelBtn();
			sfdc.macdSelAddon.validate_Add_AddOnPagePresent();
		}
		//Test Case is for US  WACC-3693
		@Test(dataProvider = "getDataPlan")
		public void validate_ADD_Addon_Confirm(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	
			
			reachTillReviewWirelessLine("965825151", "");
			sfdc.reviewWALine.validate_Product("40GB Pooled");
			sfdc.reviewWALine.validate_Product("iPhone 11");
			sfdc.reviewWALine.validate_Product("Premium Voicemail to Text");
			sfdc.reviewWALine.select_Add_addonsBtn();		
			sfdc.macdRemoveAddOn.validateAddOnPresent("Premium Voicemail to Text", "7.00");
			sfdc.macdSelAddon.validate_ExistingAddOnNotDisplayed("Voicemail", "Premium Voicemail to Text");
			sfdc.macdSelAddon.selectAddOn("SMS","Unlimited Canada to US/Intl SMS/MMS");
			sfdc.reviewWALine.select_Add_addonsBtn();			
			sfdc.macdSelAddon.validateAndClickContinueToDateSelection();
			sfdc.macdSelAddon.validate_ExtraAddonInPlan("Unlimited Canada to US/Intl SMS/MMS");
			sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
			
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