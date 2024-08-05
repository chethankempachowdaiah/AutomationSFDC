package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date 24/Jan/2020
 * 
 *         Verify Access To Account Object for Account Executive, Sales Manager
 *         and Delivery 
 *
 * 
 */
public class MP01_ServiceEnab_TC_06_VerifyAccess_Accounts_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Click on Account object on the navigation bar on
	 *                              top.
	 * 
	 *                              ___> Accounts Page should open with "Recently
	 *                              viewed" list of accounts.
	 * 
	 *                              ___> There should be no errors.
	 */
	@Test(dataProvider = "getProfiles")
	public void test_VerifyAccessToAccountsObject(String profile) throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(profile);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.verifyAccountsObject();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}

	@DataProvider
	public Object[][] getProfiles() {
		classname = getClass().getSimpleName();
		return sf.dataInput.getMultipleUserProfiles();
	}

}
