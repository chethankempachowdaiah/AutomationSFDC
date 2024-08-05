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

public class MACD_TC_16_WACC5215_3881_PPCconflictDiscountAndReviewFinalChanges_Test extends MyListener {

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-3881 & WACC-5215
	 *                              adding voice and data only plans
	 *                              check conflicting discounts and review final changes
	 */
	@Test(dataProvider = "getDataPlan")
	public void ChooseNewVoiceAndDataPlanConflictDiscountsAndReviewFinalChanges(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);
		
		reachTillReviewWirelessLine("965828064", "");
		sfdc.reviewWALine.validate_Product("40GB Pooled");
		sfdc.reviewWALine.validate_Product("iPhone 11");
		sfdc.reviewWALine.validate_Product("Canada + US");
		sfdc.reviewWALine.checkChangeWirelessPlanBtnPresent();
		sfdc.macdChangeWirelessPlans.clickChangeWrlsPlanBtn();
		sfdc.reviewWALine.validate_Product("40GB");
		sfdc.macdChangeWirelessPlans.checkVoiceAndDataDefaultSelected();
		sfdc.macdChangeWirelessPlans.checkCurrentPlanTab("40GB");
		sfdc.macdChangeWirelessPlans.validatePooledAndStandalone();
		//sfdc.macdChangeWirelessPlans.validate_UnableToSelectMultiPlans();
		sfdc.macdChangeWirelessPlans.selectNewWirelessPlan_VoiceAndData("100GB");
		sfdc.macdChangeWirelessPlans.click_continueToDateSelection();
		sfdc.reviewWALine.validate_Product("40GB");
		sfdc.macdChangeWirelessPlans.validate_newAddedFeatures("100GB");
		sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
		String[] dateOptionList= { "Today:", "Next billing cycle:", "Choose a date (up to next 45 days)"};
		sfdc.macdChangeWirelessPlans.validate_selectEffectiveDate_Options(dateOptionList);
		sfdc.macdChangeWirelessPlans.selectEffectiveDate("Today:","");
		sfdc.macdChangeWirelessPlans.selectEffectiveDate("Next billing","");
		sfdc.macdChangeWirelessPlans.selectEffectiveDate("Choose a date","2022/05/25");
		sfdc.macdRemoveAddOn.select_ConfirmEffectiveDateButton();
		sfdc.macdChangeWirelessPlans.check_ConflictingDiscountAndClickProceedOrChangePlan("Remove & Proceed");
		sfdc.macdChangeWirelessPlans.check_CartSuccessMsg();
		sfdc.reviewWALine.validate_Product("40GB Pooled");
		sfdc.reviewWALine.validate_Product("iPhone 11");
		sfdc.reviewWALine.validate_Product("Canada + US");
		sfdc.reviewWALine.validate_Product("100GB");
		sfdc.reviewWALine.select_Add_addonsBtn();
		sfdc.reviewWALine.click_discardChanges();
		sfdc.reviewWALine.checkChangeWirelessPlanBtnPresent();
	}
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-5093 & WACC-3846
	 *                              adding data only plans
	 *                              check conflicting discounts and review final changes
	 */
	@Test(dataProvider = "getDataPlan")
	public void ChooseNewDataOnlyPlansConflictDiscountsAndReviewFinalChanges(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);
		
		reachTillReviewWirelessLine("967016759", "");
		sfdc.reviewWALine.validate_Product("100GB Pooled");
		sfdc.reviewWALine.validate_Product("Bring your own device");
		sfdc.reviewWALine.validate_Product("Canada + US");
		sfdc.reviewWALine.checkChangeWirelessPlanBtnPresent();
		sfdc.macdChangeWirelessPlans.clickChangeWrlsPlanBtn();
		sfdc.reviewWALine.validate_Product("100GB");
		sfdc.macdChangeWirelessPlans.checkVoiceAndDataDefaultSelected();
		sfdc.macdChangeWirelessPlans.checkCurrentPlanTab("100GB");
		sfdc.macdChangeWirelessPlans.validatePooledAndStandalone();
		//sfdc.macdChangeWirelessPlans.selectNewWirelessPlan_VoiceAndData("40GB");
		sfdc.macdChangeWirelessPlans.validate_UnableToSelectMultiPlans();
		sfdc.macdChangeWirelessPlans.clickOnDataOnlyTab();
		sfdc.macdChangeWirelessPlans.selectNewWirelessPlan_DataOnly("1GB");
		sfdc.macdChangeWirelessPlans.click_continueToDateSelection();
		sfdc.reviewWALine.validate_Product("100GB");
		sfdc.macdChangeWirelessPlans.validate_newAddedFeatures("1GB");
		sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
		String[] dateOptionList= { "Today:", "Next billing cycle:", "Choose a date (up to next 45 days)"};
		sfdc.macdChangeWirelessPlans.validate_selectEffectiveDate_Options(dateOptionList);
		sfdc.macdChangeWirelessPlans.selectEffectiveDate("Today:","");
		sfdc.macdChangeWirelessPlans.selectEffectiveDate("Next billing","");
		sfdc.macdChangeWirelessPlans.selectEffectiveDate("Choose a date","2022/05/25");
		sfdc.macdRemoveAddOn.select_ConfirmEffectiveDateButton();
		sfdc.macdChangeWirelessPlans.check_ConflictingDiscountAndClickProceedOrChangePlan("Remove & Proceed");
		sfdc.macdChangeWirelessPlans.check_CartSuccessMsg();
		sfdc.reviewWALine.validate_Product("40GB Pooled");
		sfdc.reviewWALine.validate_Product("iPhone 11");
		sfdc.reviewWALine.validate_Product("Canada + US");
		sfdc.reviewWALine.validate_Product("100GB");
		sfdc.reviewWALine.select_Add_addonsBtn();
		sfdc.reviewWALine.click_discardChanges();
		sfdc.reviewWALine.checkChangeWirelessPlanBtnPresent();
	}
	
	public void reachTillReviewWirelessLine(String billingAcc, String phoneNum) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accManagement.selectBillingAccount(billingAcc);
		sfdc.accManagement.select_ManageAccountButton("Skip");
		sfdc.accManagement.select_AccManagementOption("Price Plan Change");
		sfdc.accManagement.click_SelectNumberButton(phoneNum);
	}

	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}

}
