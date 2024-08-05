package scrum.macd.test;

import java.io.IOException;
import java.util.Base64;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.framework.utilities.PDFHelper;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class MACD_TC_15_WACC5108_UpdatedTermsandConditionsLink_Test extends MyListener{

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	//Test Case is for US  WACC-5108
	@Test(dataProvider = "getDataPlan")
	public void validate_TermsAndConditionLink(Hashtable<String, String> dataTable) throws Exception {
		
		
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
		sfdc.macdRemoveAddOn.select_ConfirmEffectiveDateButton();
		sfdc.reviewWALine.validateSuccessfullAddOnChangesMessage();
		String productName[] = {"Unlimited Canada to US/Intl SMS/MMS"}; String action[] = {"Adding"}; String price[] = {"5.00"};
		sfdc.reviewWALine.validate_ChangesToWirelessAddOns(productName, action, price, "");		
		
		sfdc.reviewWALine.validateAndClickProccedToOrderSummary();
		String termsConditionURL= "https://www.rogers.com/r4b/legal/business-wireless-terms-en.pdf";
		sfdc.macdReviewPlaceOrder.validate_TermsAndConditionLink(termsConditionURL);
		
		//validate url in PDF Copy of Quote Summary to Customer
		sfdc.reOrder.select_SendPDFCopy_button();
		PDFHelper.deletefile_IfExist(Constants.DOWNLOADS_LOCATION +"Proposal.pdf");
		sfdc.reOrder.select_DownloadOrderSummary_button();
		sfdc.macdReviewPlaceOrder.validate_text_InPDF(termsConditionURL,Constants.DOWNLOADS_LOCATION +"Proposal.pdf");					
	}
	
	//Method is for common navigation for all test cases
		public void reachTillReviewWirelessLine(String billingAcc, String phoneNum) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			
			// ***************LOGIN AS AE***********************//
			sfdc.login.loginToSFDC(InputData.Profile_AE);
			sfdc.home.closeTabIfOpen();
			billingAcc = "965827330";
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

