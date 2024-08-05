package scrum.wa.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

	public class WA_TC_49_Verify_PayInFullDevice_In_Shopping_Cart_Test extends Base{
		
		/**
		 * @author Shruti.desai1
		 * 
		 *   WACC-2357:   Device Shopping Cart & Order Summary - Device First
		 *
		 */
		
		SFDC_AllPages sfdc;
		SFDC_AllPageObjects sf;
	
		//Test Case is for US WACC-2758
		@Test(dataProvider = "getDataPlan")
		public void VerifyPayInFullDeviceIn_Shopping_Cart(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);

			reachTillSelectDevice();
			sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,"PostWirelessPlans");
			sfdc.shopWADevcs.selectDevicePaywithFullPrice();
			sfdc.shopCart.click_ProceedToShopCartBtn();
			sfdc.shopCart.verifyFullPriceDeviceInTheOneTimeFeeSection_ShoppingCartPage();
		}
	
	//Method is for common navigation for all test cases
	public void reachTillSelectDevice() throws Exception {
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
		sfdc.selectPro.verifyWirelessProducts();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.clickOnAddToCartAddOns();
		sfdc.selectAddOn.clickOnContinueToDevice();

	}

	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}
