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

public class MACD_TC_17_WACC5944_ReplaceDeviceProtectionWithAppleCareTest extends MyListener {

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

//	//Test Case is for US  WACC-4472
//	@Test(dataProvider = "getDataPlan")
//	public void validate_Conflict_ExistingDP_AddAppleCare(Hashtable<String, String> dataTable) throws Exception {
//		sfdc = new SFDC_AllPages();
//		sf = new SFDC_AllPageObjects();
//		test = reports.createTest(this.getClass().getName());
//		InputData_WA.setDataForWACCProducts(dataTable);	
//
//		reachTillReviewWilessLine("965796451","");
//		sfdc.reviewWALine.validate_Product("20GB Pooled");
//		sfdc.reviewWALine.validate_Product("iPhone 11");
//		sfdc.reviewWALine.validate_Product("Device Protection");
//		sfdc.reviewWALine.select_Add_addonsBtn();
//		sfdc.macdSelAddon.validate_ExistingAddOn("Device Protection", "15.99");
//		String[] addonList= {"Apple Care"};
//		sfdc.macdSelAddon.validate_AddOnPresent("Device Protection",addonList);
//		sfdc.macdSelAddon.validate_ExistingAddOnNotDisplayed("Device Protection", "Device Protection");
//		sfdc.macdSelAddon.selectAddOn("Device Protection", "Apple Care");
//		sfdc.reviewWALine.select_Add_addonsBtn();	
//		sfdc.macdSelAddon.check_ConflictPopUpMessaageAndProcced("Replace");
//		sfdc.macdSelAddon.validateAndClickContinueToDateSelection();
//
//		// Validate Wireless plan change Page
//		sfdc.macdSelAddon.validate_ExtraDPInPlan("Apple Care");
//		sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
//		String[] dateOptionList= { "Today:"};
//		sfdc.macdSelAddon.validate_selectEffectiveDate_Options("Device Protection", dateOptionList);
//		sfdc.macdSelAddon.AddAppleCareTextOnSelectEffectivedate("AppleCare+ becomes active immediately", "AppleCare+ is active as soon as you enroll.", "Please select today's date above to proceed.");
//		sfdc.macdRemoveAddOn.select_ConfirmEffectiveDateButton();
//
//		// Validate Changes to the wireless section in the review page
//		sfdc.reviewWALine.validateSuccessfullAddOnChangesMessage();
//		String productName[] = {"Apple Care","Device Protection"}; String action[] = {"Adding","Removing"}; String price[] = {"199.00"}; String cancelAction[] = {"","Cancel change"};
//		sfdc.reviewWALine.validate_ChangesToWirelessAddOns_ForDPAndAppleCareTogether(productName, action, price, cancelAction);	
//		//	sfdc.reviewWALine.validateEffectiveDateInChangeToWirelessSection(waData.WACC_Multiple_AddOn);
//	}
	
	//Test Case is for US  WACC-4472
		@Test(dataProvider = "getDataPlan")
		public void validate_Conflict_VoiceMail_AddAppleCare(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	

			reachTillReviewWilessLine("965823578","");
			sfdc.reviewWALine.validate_Product("20GB Pooled");
			sfdc.reviewWALine.validate_Product("iPhone 11");
			sfdc.reviewWALine.validate_Product("Device Protection");
			sfdc.reviewWALine.select_Add_addonsBtn();
			
			//i. Validate Add Add Voice mail
			sfdc.macdRemoveAddOn.validateAddOnPresent("iPhone Visual Voicemail", "7.00");
			sfdc.macdSelAddon.validate_ExistingAddOnNotDisplayed("Voicemail", "iPhone Visual Voicemail");
			sfdc.macdSelAddon.selectAddOn("Voicemail","Premium Voicemail to Text");
			
			// Validate No Voice Mail Addon Option not displayed
			sfdc.macdSelAddon.validate_No_VoiceMail_AddOn_NotDisplayed();
			sfdc.reviewWALine.select_Add_addonsBtn();		
			sfdc.macdRemoveAddOn.validate_VoiceMailWarningMsg("If this line has voicemail, it will be reset.", "Voicemail needs to be reset for any new voicemail-related add-on requests.");
			sfdc.macdRemoveAddOn.select_ConfirmAndProceedBtn();
			sfdc.macdSelAddon.check_ConflictPopUpMessaageAndProcced("Replace");
			
			//ii. Validate Add Device Protection
			String[] addonList= {"Apple Care"};
			sfdc.macdSelAddon.validate_AddOnPresent("Device Protection",addonList);
			sfdc.macdSelAddon.selectAddOn("Device Protection", "Apple Care");
			sfdc.reviewWALine.select_Add_addonsBtn();	
			sfdc.macdSelAddon.validateAndClickContinueToDateSelection();

			// Validate Wireless plan change Page
			sfdc.macdSelAddon.validate_ExtraAddonInPlan("Premium Voicemail to Text");
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
	//		sfdc.reviewWALine.validateEffectiveDateInChangeToWirelessSection(productName);
			
			String productName1[] = {"Premium Voicemail to Text","iPhone Visual Voicemail"}; String action1[] = {"Adding","Removing"}; String price1[] = {"7.00"}; String cancelAction1[] = {"",""};
			sfdc.reviewWALine.validate_ChangesToWirelessAddOns_ForDPAndAppleCareTogether(productName1, action1, price1, cancelAction1);	
			
		}

//	//Test Case is for US  WACC-4472
//	@Test(dataProvider = "getDataPlan")
//	public void validate_Conflict_ExistingAppleCare_AddNewDP(Hashtable<String, String> dataTable) throws Exception {
//		sfdc = new SFDC_AllPages();
//		sf = new SFDC_AllPageObjects();
//		test = reports.createTest(this.getClass().getName());
//		InputData_WA.setDataForWACCProducts(dataTable);	
//
//		reachTillReviewWilessLine("965806706","");
//		sfdc.reviewWALine.validate_Product("60GB Pooled");
//		sfdc.reviewWALine.validate_Product("iPhone 11");
//		sfdc.reviewWALine.validate_Product("Apple Care +");
//		sfdc.reviewWALine.select_Add_addonsBtn();	
//		sfdc.macdSelAddon.validate_ExistingAddOn("Apple Care", "199");
//		sfdc.macdSelAddon.validate_ExistingAddOnNotDisplayed("Device Protection", "Apple Care");
//			
//		sfdc.macdSelAddon.validate_DeviceProtectionIsNotAvailableMsg();
//		sfdc.macdSelAddon.validateAndClickContinueToDateSelection();
//	}

	//Method is for common navigation for all test cases
	public void reachTillReviewWilessLine(String billingAcc, String phoneNum) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accManagement.searchBillingAccountAccountGlobalSearch(billingAcc);
		sfdc.accManagement.select_ManageAccountButton("Skip");
		sfdc.accManagement.select_AccManagementOption("Add-On");
		sfdc.accManagement.click_SelectNumberButton(phoneNum);
	}
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}

