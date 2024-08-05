package scrum.sales.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date 24/Jan/2020
 * 
 *         Verify Access To Opportunities Object for Account Executive
 *
 */
public class MP01_SalesEnab_TC_05_VerifyAccess_Opportunities_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 *                     Click on Opportunities object on the navigation bar on
	 *                     top.
	 * 
	 * 
	 *                     ___> Opportunities Page should open with "Recently
	 *                     viewed" list of Opportunities.
	 * 
	 *                     ___> There should be no errors.
	 */
	@Test
	public void test_VerifyAccessToOpportunitiesObject() throws IOException {
		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.opp.verifyOpportunitiesObject();
		softassert.assertAll();
	}
}
