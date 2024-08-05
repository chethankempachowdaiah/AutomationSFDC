package scrum.wa.test;

import org.testng.annotations.Test;
import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Sandesh Date : 25/09/2021
 * 
 *         Verify shopWirelessDevices
 *
 */
public class WA_TC_13_VerifyAddonScreen_BYOD_ContinueToDevBtn extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              WA_TC_13_VerifyAddonScreen_BYOD_ContinueToDevBtn_Enablity & BYOD Page-backToBrowseButton
	 * 
	 */
	SFDC_AllPageObjects sf;

	SFDC_AllPages sfdc = new SFDC_AllPages();

	@Test()
	public void verifyAddOnScreenPage_Test() throws Exception {

		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		// sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchAccountFromGlobalSearch(InputData_WA.account_Business_R4B, "Business");
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();
		sfdc.cQuote.clickCreateQuotePbfButton();
		sfdc.selectPro.verifyWirelessProducts();
		sfdc.selectPro.selectDataPlanAndType("Voice and Data", "Pooled", "40GB");
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.verifyEnabledElementOnAddPage();
		sfdc.selectAddOn.verifyButtonProperty();
		sfdc.selectAddOn.ValidateByodAddOnBackToBrowse();
		sfdc.home.logout();

	}
}