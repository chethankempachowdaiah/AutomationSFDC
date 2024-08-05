package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date 24/Jan/2020
 * 
 *         Verify Access To Contacts Object for Account Executive
 *
 */
public class MP01_ServiceEnab_TC_07_VerifyAccess_Contacts_Test extends Base {

	/**
	 * Click on Contacts object on the navigation bar on top.
	 * 
	 * ___> Contacts Page should open with "Recently viewed" list of Contacts.
	 * 
	 * ___> There should be no errors.
	 * 
	 * @throws IOException
	 */
	@Test(groups = { "Sanity" })
	public void test_VerifyAccessToContactsObject() throws IOException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.contacts.verifyContactsObject();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();

	}

}
