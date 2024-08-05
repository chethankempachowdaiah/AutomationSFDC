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

public class MACD_TC_06_WACC3695_Verify_AddOrRemove_DisplayAddonsReviewScreen_Test extends MyListener{

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	//Test Case is for US  WACC-3695
	@Test(dataProvider = "getDataPlan")
	public void validate_ReviewAddons(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		reachTillReviewWirelessLine("965830490", "");
		sfdc.reviewWALine.select_Add_addonsBtn();
		sfdc.macdSelAddon.selectAddOn("Voicemail","iPhone Visual Voicemail");
		sfdc.reviewWALine.select_Add_addonsBtn();
		
	//	sfdc.macdRemoveAddOn.validate_VoiceMailWarningMsg("If this line has voicemail, it will be reset.", "Voicemail needs to be reset for any new voicemail-related add-on requests.");
		//sfdc.macdRemoveAddOn.select_ConfirmAndProceedBtn();
		sfdc.macdSelAddon.validateAndClickContinueToDateSelection();
		sfdc.macdSelAddon.validate_ExtraAddonInPlan("iPhone Visual Voicemail");
		sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
		sfdc.macdRemoveAddOn.select_ConfirmEffectiveDateButton();
		
		sfdc.reviewWALine.verify_addonsMessageBar();
		String productName[] = {"iPhone Visual Voicemail"}; String action[] = {"Adding"}; String price[] = {"7.00"}; String change = "Cancel change";
		sfdc.reviewWALine.validate_ChangesToWirelessAddOns(productName, action, price,change);
		
		/*
		 * When User click on the Add add-ons button in the overall add-ons section ,
		 * User should navigate back to the add-ons selection screen, with previous
		 * choices selected and editable
		 */
		sfdc.reviewWALine.select_Add_addonsBtn();
		
		
	}
	

	//Test Case is for US  WACC-3695
	@Test(dataProvider = "getDataPlan")
	public void validate_RemoveExistingAddons(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		reachTillReviewWirelessLine("965830490", "");
		sfdc.reviewWALine.select_Add_addonsBtn();
		sfdc.macdSelAddon.selectAddOn("Voicemail","iPhone Visual Voicemail");
		sfdc.reviewWALine.select_Add_addonsBtn();
	//	sfdc.macdRemoveAddOn.validate_VoiceMailWarningMsg("If this line has voicemail, it will be reset.", "Voicemail needs to be reset for any new voicemail-related add-on requests.");
		//sfdc.macdRemoveAddOn.select_ConfirmAndProceedBtn();
		sfdc.macdSelAddon.validateAndClickContinueToDateSelection();
		sfdc.macdSelAddon.validate_ExtraAddonInPlan("iPhone Visual Voicemail");
		sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
		sfdc.macdRemoveAddOn.select_ConfirmEffectiveDateButton();
		
		sfdc.reviewWALine.click_RemoveCurrentAddOns();
		
	}
	
	//Test Case is for US  WACC-3695
		@Test(dataProvider = "getDataPlan")
		public void validate_DiscardChangesToAddons(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	
			
			reachTillReviewWirelessLine("965830490", "");
			sfdc.reviewWALine.select_Add_addonsBtn();
			sfdc.macdSelAddon.selectAddOn("Voicemail","iPhone Visual Voicemail");
			sfdc.reviewWALine.select_Add_addonsBtn();
		//	sfdc.macdRemoveAddOn.validate_VoiceMailWarningMsg("If this line has voicemail, it will be reset.", "Voicemail needs to be reset for any new voicemail-related add-on requests.");
			//sfdc.macdRemoveAddOn.select_ConfirmAndProceedBtn();
			sfdc.macdSelAddon.validateAndClickContinueToDateSelection();
			sfdc.macdSelAddon.validate_ExtraAddonInPlan("iPhone Visual Voicemail");
			sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
			sfdc.macdRemoveAddOn.select_ConfirmEffectiveDateButton();
			
			sfdc.reviewWALine.click_discardChanges();
			
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
