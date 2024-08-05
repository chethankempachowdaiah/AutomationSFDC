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

public class MACD_TC_25_WACC5880_AppleCareEffectiveDateInReviewCTNScreen_Test extends MyListener{

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	// Author@: Jigyasa Dwivedi
	//Test Case is for US  WACC-5880
	@Test(dataProvider = "getDataPlan")
	public void WACC5880_validate_AppleCareEffectiveDate_ReviewCTNScreen(Hashtable<String, String> dataTable) throws Exception {
				
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		//test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		reachTillReviewWirelessLine("969506567", "");
		sfdc.reviewWALine.validate_Product("60GB Standalone");
		sfdc.reviewWALine.validate_Product("iPhone 11");
		sfdc.reviewWALine.validate_Product("Canada + US");
		sfdc.reviewWALine.validate_Product("Device Protection");	
		sfdc.reviewWALine.select_Add_addonsBtn();		
		sfdc.macdRemoveAddOn.validateAddOnPresent("Canada + US", "20");
		sfdc.macdRemoveAddOn.validateAddOnPresent("Device Protection", "8.99");		
 		sfdc.macdSelAddon.selectAddOn("Device Protection", "Apple Care");
		sfdc.reviewWALine.select_Add_addonsBtn();
		sfdc.macdSelAddon.check_ConflictPopUpMessaageAndProcced("Replace");
		sfdc.macdSelAddon.validateAndClickContinueToDateSelection();		
		sfdc.macdSelAddon.validate_ExtraAddonInPlan("Apple Care");
		sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
		sfdc.macdRemoveAddOn.select_ConfirmEffectiveDateButton();		
		sfdc.reviewWALine.validateSuccessfullAddOnChangesMessage();
		String productName[] = {"Apple Care"}; 
		String action[] = {"Adding"}; String price[] = {"199.00"};
		sfdc.reviewWALine.validate_ChangesToWirelessAddOns(productName, action, price, "");
		sfdc.reviewWALine.validateEffectiveDateCalendarIcon_AppleCare();
		sfdc.reviewWALine.validateAndClickProccedToOrderSummary();		
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

