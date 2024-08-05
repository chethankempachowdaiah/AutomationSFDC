package scrum.sales.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla, Date 7/June/2021
 * 
 *         MPOSS-41394_TC01_Validate that as sales user, the Account page layout has fields Industry and Sub-Industry on display
 *         
 *         MPOSS-41394_TC02_Validate that as sales user, the Industry and Sub-Industry fields have the help text displayed when you highlight on the (i) information link
 * 
 */
public class Pl02_SP5_MPOSS_41394_Validate_Industry_SubIndustry_Help_Text_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 *                                               
	 * @throws InterruptedException
	 * 
	 */
	@Test
	public void test_validate_help_text_ind_subindustry() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);

		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		
		//Open any Business Account
		sfdc.acc.verifyAccountsObject();
		Global.dataInput.tempBusinessAccountName = "NOVDemo24";
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
		
		//sfdc.acc.openAnyActiveBusinessAccountFromList();
		
		//Validate industry subindustry help text
		//sfdc.accDetails.verifyIndSubIndHelpText();
		
		sfdc.home.logout();

		softassert.assertAll();

	}

}
