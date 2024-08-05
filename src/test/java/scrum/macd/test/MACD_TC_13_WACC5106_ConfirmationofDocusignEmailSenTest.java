package scrum.macd.test;

import java.io.IOException;
import java.util.Base64;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class MACD_TC_13_WACC5106_ConfirmationofDocusignEmailSenTest extends MyListener{

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	// Author@: Pankaj Agarwal
	//Test Case is for US  WACC-5106
	@Test(dataProvider = "getDataPlan")
	public void validate_ExistingDP_AddAppleCare(Hashtable<String, String> dataTable) throws Exception {
		
		
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		reachTillReviewWirelessLine("965827330", "");
		sfdc.reviewWALine.validate_Product("64GB");
		sfdc.reviewWALine.validate_Product("iPhone 11");
		sfdc.reviewWALine.validate_Product("iPhone Visual Voicemail");	
		sfdc.reviewWALine.select_Add_addonsBtn();		
		sfdc.macdRemoveAddOn.validateAddOnPresent("iPhone Visual Voicemail", "7.00");
		sfdc.macdSelAddon.validate_ExistingAddOnNotDisplayed("Voicemail", "iPhone Visual Voicemail");
		sfdc.macdSelAddon.selectAddOn("Voicemail","Premium Voicemail to Text");
		sfdc.reviewWALine.select_Add_addonsBtn();		
		sfdc.macdRemoveAddOn.validate_VoiceMailWarningMsg("If this line has voicemail, it will be reset.", "Voicemail needs to be reset for any new voicemail-related add-on requests.");
		sfdc.macdRemoveAddOn.select_ConfirmAndProceedBtn();
		sfdc.macdSelAddon.validateAndClickContinueToDateSelection();
		sfdc.macdSelAddon.validate_ExtraAddonInPlan("Premium Voicemail to Text");
		sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
		sfdc.macdRemoveAddOn.select_ConfirmEffectiveDateButton();
		sfdc.reviewWALine.validateSuccessfullAddOnChangesMessage();
		String productName[] = {"Premium Voicemail to Text"}; String action[] = {"Adding"}; String price[] = {"199.00"};
		sfdc.reviewWALine.validate_ChangesToWirelessAddOns(productName, action, price, "");	
		// Accept the Quote With E-sign
		sfdc.reviewWALine.validateAndClickProccedToOrderSummary();
		InputData_WA.WACC_eSignature = "Yes";
		sfdc.reOrder.acceptQuoteOptions();
		sfdc.genDoc.generateDocuement(InputData.quoteNumber);
		sfdc.quoteDetails.verifyQuoteNumberInQuotePage();
			
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

