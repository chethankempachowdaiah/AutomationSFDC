package scrum.wa.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_46_Verify_36MonthPlanRemovedForDevices extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	// Test Case is for US WACC-4206
	@Test(dataProvider = "getDataPlan")
	public void validate_HelpBubble_DeviceListing_DeviceDetails(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillCreateQuotePage();

		sfdc.selectPro.verifyWirelessProducts();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		sfdc.selectAddOn.clickOnContinueToDevice();

		sfdc.shopWADevcs.validateHelpBubbleText(InputData_WA.WACC_DeviceName,"With financing and 0% interest, you’ll pay only $0 down for this phone and then pay off the remaining cost and applicable taxes in equal payments over 24 months");
		sfdc.shopWADevcs.validateFinanceMonthText("24 mos");
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,"PostWirelessPlans");
		sfdc.shopWADevcs.helpBubbleText("With financing and 0% interest, you’ll pay only $0 down for this phone and then pay off the remaining cost and applicable taxes in equal payments over 24 months",0);
		sfdc.shopWADevcs.validateFinanceMonthText("24 mos");
		sfdc.shopWADevcs.validatePaymentOptionText("24 months (financing)");
		sfdc.shopWADevcs.validate_24MonthFinanceOption();
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
	}

	// Method is for common navigation for all test cases
	public void reachTillCreateQuotePage() throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(waData.account_Business_R4B);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();
		sfdc.cQuote.clickCreateQuotePbfButton();
	}

	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}

}
