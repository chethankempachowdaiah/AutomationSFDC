package scrum.wa.test;

import org.testng.annotations.Test;

import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_22_Validate_FinanceDev_ShoppingCart extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	@Test
	public void validate_ShoppingCart_Finance_DeviceFirstFlow() throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		
		// ***************LOGIN AS AE***********************//
		reachTillShopAccessoriesnPage("iPhone 12");
		sfdc.selectPro.selectDeviceProtection("Apple Care");
		sfdc.selectPro.clicknAddToCartBtnForDP();

	}
	
	
	
	public void reachTillShopAccessoriesnPage(String DeviceModel) throws Exception {
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
		sfdc.selectPro.selectShopeWirelessDevices();
		sfdc.shopWADevcs.selectWirelessDevice("Apple", DeviceModel, "PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.selectDataPlanAndType("Voice and Data", "Pooled", "40GB");
		sfdc.selectPro.continueToAddOnsButton();
		

		

	}
}
