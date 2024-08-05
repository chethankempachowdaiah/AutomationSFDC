package scrum.macd.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.framework.utilities.DateTimeUtilities;
import com.framework.utilities.PDFHelper;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class MACD_TC_14A_WACC4493_Verify_AddDP_In_OrderReviewPage_Test extends MyListener {

	// Author@: Pankaj Agarwal 
	//*****************************US Covered ********************************
	//US  WACC-4493 (DP: Quote Generation and Customer Approval),
	// WACC-5848 (Promotions for Add-Ons - Update Shopping Cart)
	// WACC-5994 (ADD/REMOVE: Remove Date Text Column)
	//*****************************High Level Scenario steps *******************
	//1. Adding the Addons, verifying the added addons in the changes to the wireless addons list, verify the selected date,
	//2. Verify the review place order page with action, product name and prices
	//3. Verify the pdf sent to the customer for proposal with product name and prices

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;


	@Test(dataProvider = "getDataPlan")
	public void validate_AddAppleCare_In_ReviewOrderPage(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCMacd(dataTable);	
		System.out.println(waData.WACC_Add_ExtaAddOns);
	
			reachTillReviewWilessLine("965810542","");
			sfdc.reviewWALine.validate_Product("40GB Pooled");
			sfdc.reviewWALine.validate_Product("iPhone 11");
			sfdc.reviewWALine.validate_Product("Canada + US");	
			// Add AddOns
			sfdc.reviewWALine.select_Add_addonsBtn();			
			sfdc.macdRemoveAddOn.validateAddOnPresent("Canada + US", "20.00");	

			String[] addOn = {"US LD","International LD Saver"};
			sfdc.macdSelAddon.validate_defaultNoOfferSelection(addOn);
			select_ExtraAddOn();

			sfdc.macdSelAddon.validateAndClickContinueToDateSelection();
			sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
			for(int i =0; i<waData.WACC_Multiple_AddOn.length; i++) {
				sfdc.macdSelAddon.selectEffectiveDate(waData.WACC_Multiple_AddOn[i],waData.WACC_Select_Effective_DateOption[i],"");
			}
			sfdc.macdRemoveAddOn.select_ConfirmEffectiveDateButton();

			// Validate Changes to the wireless section in the review page
			sfdc.reviewWALine.validateSuccessfullAddOnChangesMessage();
			sfdc.reviewWALine.validate_ChangesToWirelessAddOns(waData.WACC_Multiple_AddOn, waData.WACC_AddOnAction, waData.WACC_Multiple_AddOnPrice, "");	
			sfdc.reviewWALine.validateEffectiveDateInChangeToWirelessSection(waData.WACC_Multiple_AddOn);
			//	sfdc.reviewWALine.changeEffectiveDateInChangeToWirelessSection(waData.WACC_Multiple_AddOn);
			sfdc.reviewWALine.validateAndClickProccedToOrderSummary();

			// Validate the Apple Care product in the one time section of the review order page
			if(waData.WACC_AddOnAppleCareProductPresent)
			{
				sfdc.macdReviewPlaceOrder.validate_ProductInOneTimeSection("Apple Care", "199.00");
				sfdc.macdReviewPlaceOrder.validateTotalOneTimeScetion("199");
			} else {
				sfdc.macdReviewPlaceOrder.validateTotalOneTimeScetion("0");
			}

			// Validate the Device Protection or other add ons product in the change in the fee sction of the review order page
			sfdc.macdReviewPlaceOrder.verify_ExtraAddOn_ChangeInFee_Section(waData.WACC_Multiple_AddOn, waData.WACC_AddOnAction, waData.WACC_Multiple_AddOnPrice, 1);
			sfdc.macdReviewPlaceOrder.verify_CalculateTotalMonthlyFeesChanges(waData.WACC_Monthly_AddOnPrice_NoAppleCare);

			//***validate url in PDF Copy of Quote Summary to Customer
			sfdc.reOrder.select_SendPDFCopy_button();
			PDFHelper.deletefile_IfExist(Constants.DOWNLOADS_LOCATION +"Proposal.pdf");
			sfdc.reOrder.select_DownloadOrderSummary_button();
			
			sfdc.macdReviewPlaceOrder.verifyItemsBeforeTransactionsInPDF("Item(s) Added", Constants.DOWNLOADS_LOCATION +"Proposal.pdf");
			sfdc.macdReviewPlaceOrder.verifyItemsAddedRemovedInPDF("Item(s) Added", Constants.DOWNLOADS_LOCATION +"Proposal.pdf");
			sfdc.macdReviewPlaceOrder.verifyItemsAfterTransactionInPDF("Item(s) Added", Constants.DOWNLOADS_LOCATION +"Proposal.pdf");
		} 
	

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

	//Method is for common for selecting the extra addons
	public void select_ExtraAddOn() throws Exception {

		// ***************Select ADD ON***********************//
		for (int i = 0; i< sf.waData.WACC_Multiple_AddOn.length; i++) {

			String option = sf.waData.WACC_Multiple_AddOn[i].trim();
			switch (option){

			case "iPhone Visual Voicemail" : 
				sfdc.macdSelAddon.selectAddOn("Voicemail","iPhone Visual Voicemail");
				break;

			case "Apple Care" : 
				sfdc.macdSelAddon.selectAddOn("Device Protection","Apple Care");
				waData.WACC_AddOnAppleCareProductPresent = true;
				break;

			case "Device Protection" :
				sfdc.macdSelAddon.selectAddOn("Device Protection","Device Protection");
				break;

			case "Unlimited Canada to US/Intl SMS/MMS" :
				sfdc.macdSelAddon.selectAddOn("SMS","Unlimited Canada to US/Intl SMS/MMS");
				break;

			case "US LD" :
				sfdc.macdSelAddon.selectAddOn_LongDistance("US LD", "promo");
				break;

			case "International LD Saver" :
				sfdc.macdSelAddon.selectAddOn_LongDistance("International LD Saver", "promo");
				break;

			default : 
				System.out.println("none of the options are matched");

			}
			sfdc.reviewWALine.select_Add_addonsBtn();	
		}
	}



	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_MACD_ADD_ADDONS);
	}

}

