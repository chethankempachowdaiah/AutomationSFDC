package scrum.wa.test;

import org.testng.annotations.Test;
import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Sandesh Date : 21/09/2021
 * 
 *      Verify shopWirelessDevices
 *
 */
public class WA_TC_08_ShopWirelessDevices_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                             Verify accordion 
	 * 
	 */
	SFDC_AllPageObjects sf;
	
	SFDC_AllPages sfdc = new SFDC_AllPages();

	@Test()
	public void verifyShopWirelessDevicesPage_Test() throws Exception {

		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		// sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(waData.account_Business_R4B);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity(InputData_WA.contact_Business_R4B);
		sfdc.cQuote.clickCreateQuotePbfButton();
		sfdc.selectPro.selectShopeWirelessDevices();
		sfdc.shopWADevcs.selectBrandName("Apple");
		//sfdc.shopWADevcs.verifyCountOfSmartphones();
		sfdc.shopWADevcs.clickOnViewDetailsBtn();
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sf.seleU.getTextFromWebElement(sf.shopWADevobj.getWirelessDeviceAdded);
		sfdc.selectPro.selectDataPlanAndType("Voice and Data", "Pooled", "40GB");
		//sfdc.selectPro.clickOnPlansAddToCart();
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.validateLongDistancePlans("Long Distance", "US LD", "No Offer");
		sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
		//sfdc.selectAddOn.clickOnAddToCartAddOns();
		sfdc.selectAddOn.validateAccordionSeqDeviceFirst(InputData_WA.DevicePlanArray);
		sfdc.home.logout();

	}
}
