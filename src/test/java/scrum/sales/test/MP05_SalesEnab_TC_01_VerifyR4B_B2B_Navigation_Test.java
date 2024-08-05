package scrum.sales.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date: 28/MAY/2020
 *
 *         Verify user can redirect from R4B org to B2B org hitting this button
 *         on Home page in the new org.
 */
public class MP05_SalesEnab_TC_01_VerifyR4B_B2B_Navigation_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Select + Icon on top right corner
	 * 
	 *                              Select 'Switch to B2B Org Option'
	 * 
	 *                              Verify Login to B2B Successful
	 */

	@Test
	public void test_VerifyR4B_B2B_Navigation() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.home.switchToB2BOrg();

		// ***************LOGIN TO ITFULL***********************//
		sfdc.login.loginToSFDC(InputData.oldOrg_username);
		sfdc.home.logout();
		softassert.assertAll();
	}
}
