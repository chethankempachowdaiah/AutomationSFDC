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

public class MACD_TC_07_WACC3688_5698_SelectDateOfAddOnRemovalAndDiscardChanges_Test extends MyListener {

	
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-3688 & WACC-5698
	 *                              Validate Add-on's removal effective date page and selected date options 
	 *                              and default date today selected.
	 *                              validate click on edit and working as expected.
	 *                              Validate Discard changes button
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_AddOnRemovalEffectiveDateAndDiscardChanges(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		//InputData_WA.setDataForWACCProducts(dataTable);
		InputData_WA.setDataForWACCMacd(dataTable);	
		
		reachTillReviewWirelessLine("965828064", "");
		sfdc.reviewWALine.validate_Product("40GB Pooled");
		sfdc.reviewWALine.validate_Product("iPhone 11");
		sfdc.reviewWALine.validate_Product("Canada + US");
		sfdc.reviewWALine.select_Remove_addonsBtn();
		sfdc.macdRemoveAddOn.validateAddOnPresent("Canada + US", "20.00");
		sfdc.macdRemoveAddOn.selectAddOnAndClickRemove("Canada + US");
		sfdc.macdRemoveAddOn.reviewSelectedAddontoRemove("Canada + US");
		sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
		String[] dateOptionList= { "Today:", "Next billing cycle:", "Choose a date (up to next 45 days)"};
		sfdc.macdSelAddon.validate_selectEffectiveDate_Options("Canada + US", dateOptionList);
		//sfdc.macdRemoveAddOn.validate_effectiveDateOptions();
		sfdc.macdRemoveAddOn.clickOnEdit();
		sfdc.macdRemoveAddOn.validateAddOnPresent("Canada + US", "20.00");
		sf.seleU.clickElementByJSE(sf.macdRemoveAddOnObj.AddonCheckBox);
		sfdc.macdRemoveAddOn.selectAddOnAndClickRemove("Canada + US");
		sfdc.macdSelAddon.selectEffectiveDate("Canada + US","Today:","");
		sfdc.macdSelAddon.selectEffectiveDate("Canada + US","Next billing","");
		sfdc.macdSelAddon.selectEffectiveDate("Canada + US","Choose a date","2022/04/25");
		//sfdc.macdRemoveAddOn.clickConfirmEffectDate();
		sfdc.macdRemoveAddOn.select_ConfirmEffectiveDateButton();
		
		sfdc.reviewWALine.validateSuccessfullAddOnChangesMessage();
		sfdc.reviewWALine.validate_ChangesToWirelessAddOns(waData.WACC_Multiple_AddOn, waData.WACC_AddOnAction, waData.WACC_Multiple_AddOnPrice, "");	
		sfdc.reviewWALine.click_discardChangesAndCancel();
		sfdc.reviewWALine.click_discardChanges();
	}
	
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
