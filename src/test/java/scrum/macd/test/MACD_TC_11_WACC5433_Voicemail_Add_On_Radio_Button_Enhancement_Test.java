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

public class MACD_TC_11_WACC5433_Voicemail_Add_On_Radio_Button_Enhancement_Test extends MyListener {

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	/**Author@ Pankaj Agarwal
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                            ==>  Test Case is for US WACC-5433  <==
     *                             
	 */
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
			
			// Validate No Voice Mail Addon Option not displayed
			sfdc.macdSelAddon.validate_No_VoiceMail_AddOn_NotDisplayed();
			sfdc.reviewWALine.select_Add_addonsBtn();		
			sfdc.macdRemoveAddOn.validate_VoiceMailWarningMsg("If this line has voicemail, it will be reset.", "Voicemail needs to be reset for any new voicemail-related add-on requests.");
			sfdc.macdRemoveAddOn.select_ConfirmAndProceedBtn();
			
			// Validate No Voice Mail Addon Option is displayed and selected
			sfdc.macdSelAddon.validate_No_VoiceMail_AddOn_RadioButton();
			sfdc.macdSelAddon.select_No_VoiceMail_AddOn_RadioButton_AndUpdate();			
			sfdc.macdSelAddon.validateContinueToDateSelectionNotEnabled();
					
			// Validate Update button by selecting the voicemail addon again
			sfdc.macdSelAddon.selectAddOn("Voicemail","iPhone Visual Voicemail");
			sfdc.macdSelAddon.select_UpdateAddOnButton();
			sfdc.macdRemoveAddOn.validate_VoiceMailWarningMsg("If this line has voicemail, it will be reset.", "Voicemail needs to be reset for any new voicemail-related add-on requests.");
			sfdc.macdRemoveAddOn.select_ConfirmAndProceedBtn();		
			sfdc.macdSelAddon.validateAndClickContinueToDateSelection();
			sfdc.macdSelAddon.validate_ExtraAddonInPlan("iPhone Visual Voicemail");
			sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
		}
	
	public void reachTillReviewWirelessLine(String billingAcc, String phoneNum) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accManagement.selectBillingAccount_ByHomeNavigation(billingAcc);
		sfdc.accManagement.select_ManageAccountButton("Skip");
		sfdc.accManagement.select_AccManagementOption("Add-On");
		sfdc.accManagement.click_SelectNumberButton(phoneNum);
	}

	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}
