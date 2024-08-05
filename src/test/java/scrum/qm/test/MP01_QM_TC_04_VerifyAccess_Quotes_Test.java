package scrum.qm.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date 24/Jan/2020
 * 
 *         Verify Access To Quotes Object for Account Executive
 *
 */
public class MP01_QM_TC_04_VerifyAccess_Quotes_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 *                     Click on Quotes object on the navigation bar on top.
	 * 
	 *                     ___> Quotes Page should open with "Recently viewed" list
	 *                     of Quotes.
	 * 
	 *                     ___> There should be no errors.
	 */
	@Test
	public void test_VerifyAccessToQuotesObject() throws IOException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.quotes.verifyQuotesObject();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();

	}
}
