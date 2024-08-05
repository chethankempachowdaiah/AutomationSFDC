package scrum.sales.test;

import com.framework.base.Base;
import com.framework.base.Global;

import org.testng.annotations.Test;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Nandan.More, Date 12/Oct/2021
 * 
 *          21PI04-SP1_MPOSS-49955_Active checkbox on Campaigns is automatically checked
 *         
 *          MPOSS-49967_TC_01 Verify Active checkbox on Campaigns is automatically checked if the current date falls between the start and End dates of the campaign.(All upcoming records.)
 *
 *			MPOSS-49967_TC_02 Verify Active checkbox on Campaigns is not automatically checked if the current date not falls between the start and End dates of the campaign.(All upcoming records.)
 *			
 *			MPOSS-49967_TC_03 Verify Active Checkbox  fuctionality is applicatble only for all upcoming records.
 *			
 * 
 */

public class MP_21PI04_SP01_49955_Active_checkbox_on_Campaigns_is_automatically_checked_Test extends Base {
	
	/**
	 * @throws Exception
	 * 
	 *                  
	 */
	@Test
	public void test_ValidateActiveCheckboxonCampaignsisautomaticallychecked() throws Exception , InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData_Sales.Profile_MarketingUser);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.home.openCampaign();
		sfdc.campaignPage.validateActiveCheckbox();
		sfdc.campaignPage.validateActiveCheckboxNotChecked();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}

}


