package scrum.macd.test;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MACD_TC_03_WACC4003And3764_Verify_RemoveDisplayVoicemailResetWarning_Test extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	//Test Case is for US  WACC-4003
	//@Test(dataProvider = "getDataPlan")
	public void validate_RemoveVoicemail_Confirm(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		reachTillReviewWirelessLine("965825151", "");
		sfdc.reviewWALine.validate_Product("40GB Pooled");
		sfdc.reviewWALine.validate_Product("iPhone 11");
		sfdc.reviewWALine.validate_Product("Premium Voicemail to Text");			
		sfdc.reviewWALine.select_Remove_addonsBtn();		
		sfdc.macdRemoveAddOn.validateAddOnPresent("Premium Voicemail to Text", "7.00");
		sfdc.macdRemoveAddOn.selectAddOnAndClickRemove("Premium Voicemail to Text");
		sfdc.macdRemoveAddOn.validate_VoiceMailWarningMsg("If this line has voicemail, it will be reset.", "This process will delete all saved messages and any personalized greeting. The new Voicemail password will be the last 4 digits of the new wireless number.");
		sfdc.macdRemoveAddOn.select_ConfirmAndProceedBtn();
		sfdc.macdRemoveAddOn.reviewSelectedAddontoRemove("Premium Voicemail to Text");
		sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();		
	}
	//Test Case is for US  WACC-4003
		//@Test(dataProvider = "getDataPlan")
		public void validate_RemoveVoicemail_Cancel(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	
			
			reachTillReviewWirelessLine("965825151", "");
			sfdc.reviewWALine.validate_Product("40GB Pooled");
			sfdc.reviewWALine.validate_Product("iPhone 11");
			sfdc.reviewWALine.validate_Product("Premium Voicemail to Text");			
			sfdc.reviewWALine.select_Remove_addonsBtn();			
			sfdc.macdRemoveAddOn.validateAddOnPresent("Premium Voicemail to Text", "07.00");
			sfdc.macdRemoveAddOn.selectAddOnAndClickRemove("Premium Voicemail to Text");
			sfdc.macdRemoveAddOn.validate_VoiceMailWarningMsg("If this line has voicemail, it will be reset.", "This process will delete all saved messages and any personalized greeting. The new Voicemail password will be the last 4 digits of the new wireless number.");
			sfdc.macdRemoveAddOn.select_CancelBtn();
			sfdc.macdRemoveAddOn.validateAddOnPresent("Premium Voicemail to Text", "07.00");
		}
		//Test Case is for US  WACC-4003
		//@Test(dataProvider = "getDataPlan")
		public void validate_RemoveAddon_Confirm(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	
			
			reachTillReviewWirelessLine("965810542", "");
			sfdc.reviewWALine.validate_Product("40GB Pooled");
			sfdc.reviewWALine.validate_Product("iPhone 11");
			sfdc.reviewWALine.validate_Product("Canada + US");			
			sfdc.reviewWALine.select_Remove_addonsBtn();			
			sfdc.macdRemoveAddOn.validateAddOnPresent("Canada + US", "20.00");
			sfdc.macdRemoveAddOn.selectAddOnAndClickRemove("Canada + US");	
			sfdc.macdRemoveAddOn.reviewSelectedAddontoRemove("Canada + US");
			sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
		}
		//Test Case is for US  WACC-3764
		@Test(dataProvider = "getDataPlan")
		public void validate_RemoveAddon_NamePriceDescription(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	
			
			reachTillReviewWirelessLine("965826217", "");
			sfdc.reviewWALine.validate_Product("40GB Pooled");
			sfdc.reviewWALine.validate_Product("iPhone 11");
			sfdc.reviewWALine.validate_Product("iPhone Visual Voicemail");
			sfdc.reviewWALine.validate_Product("Canada + US");
			sfdc.reviewWALine.validate_Product("Unlimited Canada to US/Intl SMS/MMS");
			sfdc.reviewWALine.select_Remove_addonsBtn();
			HashMap<String, String> addOnMap = new HashMap<String, String>() {
				{
					put("International LD Saver", "7:Save up to 98% on calls from Canada to over 100 destinations");
					put("Canada + US", "20:Use your plan in the US just like you do at home without roaming fees");
					put("Unlimited Canada to US/Intl SMS/MMS", "5:Unlimited text, picture and video messaging from Canada to United States and International destinations");
					put("iPhone Visual Voicemail", "7:Get a quick and easy way to manage your messages via the pre-loaded iPhone Visual Voicemail app! Includes Enhanced Voicemail – receive up to 35, five-minute messages and save each one for up to 10 days. Record a personalized greeting up to 3 minutes long. View total number of missed calls and unheard voicemail messages from the phone button on the home screen of your iPhone. No need to dial to check in to check voicemail messages or to enter a password Listen to your voicemail messages in any order you want. The text and audio files are delivered directly to your text messaging inbox instead of your iPhone Visual Voicemail app");
				}
			};
			sfdc.macdSelAddon.verify_RemoveListofAddOn(addOnMap);
			sfdc.macdRemoveAddOn.validate_RemoveAddOnAndContineBtnDisabled();
			sfdc.macdRemoveAddOn.selectAddOnAndClickRemove("Canada + US");	
			sfdc.macdRemoveAddOn.reviewSelectedAddontoRemove("Canada + US");
			sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
		}	
		
		//Test Case is for US  WACC-4003
		//@Test(dataProvider = "getDataPlan")
		public void validate_RemoveVoicemailANdOtherAddOn_Confirm(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	
			
			reachTillReviewWirelessLine("965826217", "");
			sfdc.reviewWALine.validate_Product("40GB Pooled");
			sfdc.reviewWALine.validate_Product("iPhone 11");
			sfdc.reviewWALine.validate_Product("iPhone Visual Voicemail");
			sfdc.reviewWALine.validate_Product("Canada + US");
			sfdc.reviewWALine.validate_Product("Unlimited Canada to US/Intl SMS/MMS");
			sfdc.reviewWALine.select_Remove_addonsBtn();		
			sfdc.macdRemoveAddOn.validateAddOnPresent("iPhone Visual Voicemail", "7.00");
			sfdc.macdRemoveAddOn.validateAddOnPresent("Canada + US", "20.00");
			sfdc.macdRemoveAddOn.validateAddOnPresent("Unlimited Canada to US/Intl SMS/MMS", "5.00");
			String[] addonList= {"iPhone Visual Voicemail","Canada + US","Unlimited Canada to US/Intl SMS/MMS"};  
			sfdc.macdRemoveAddOn.selectMultipleAddOnAndClickRemove(addonList);
			sfdc.macdRemoveAddOn.validate_VoiceMailWarningMsg("If this line has voicemail, it will be reset.", "This process will delete all saved messages and any personalized greeting. The new Voicemail password will be the last 4 digits of the new wireless number.");
			sfdc.macdRemoveAddOn.select_ConfirmAndProceedBtn();
			sfdc.macdRemoveAddOn.reviewSelectedAddontoRemove("iPhone Visual Voicemail");
			sfdc.macdRemoveAddOn.reviewSelectedAddontoRemove("Canada + US");
			sfdc.macdRemoveAddOn.reviewSelectedAddontoRemove("Unlimited Canada to US/Intl SMS/MMS");
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