package scrum.wa.test;

import org.testng.annotations.Test;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_09_CreditDiscount_Device extends MyListener{
	
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	@Test
	public void logintoAEandFindOpportunity() throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchAccountFromGlobSearch(InputData_WA.account_Business_R4B, "Business");
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity(InputData_WA.contact_Business_R4B);
		sf.seleU.waitTillLoading();
		sfdc.cQuote.clickCreateQuotePbfButton();
		sfdc.shopWADevcs.selectWirelessDevice("Apple", "iPhone 12 Mini","ShopDeviceFirst");
		sfdc.shopWADevcs.validateDiscountAndCreditFunc();
		sfdc.shopWADevcs.checkDiscountAfterAttributeSelcn();
		sfdc.home.logout();
	}
}