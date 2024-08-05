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

public class MACD_TC_08_WACC5493_PromotionsForAddOns_Test extends MyListener {

	
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-5493
	 *                              Validate Add-on's long distance no offer default and 
	 *                              when promo applied price striked out and new price display 
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_promotionsForLongDistanceAddOns(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillReviewWirelessLine("965828064", "");
		sfdc.reviewWALine.validate_Product("40GB Pooled");
		sfdc.reviewWALine.validate_Product("iPhone 11");
		sfdc.reviewWALine.validate_Product("Canada + US");
		sfdc.reviewWALine.select_Add_addonsBtn();		
		sfdc.macdRemoveAddOn.validateAddOnPresent("Canada + US", "20.00");
		String[] addOn = {"US LD","International LD Saver"};
		sfdc.macdSelAddon.validate_defaultNoOfferSelection(addOn);
		sfdc.macdSelAddon.selectAddOn_LongDistance("US LD", "promo");
		sfdc.macdSelAddon.selectAddOn_LongDistance("International LD Saver", "promo");

	}
	
	public void reachTillReviewWirelessLine(String billingAcc, String phoneNum) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();

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
