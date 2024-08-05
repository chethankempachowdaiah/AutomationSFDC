package scrum.om.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date 24/Jan/2020
 *
 *         Verify Access To Orders Object for Account Executive
 */
public class MP01_OM_TC_04_VerifyAccess_Orders_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 *                     Click on Orders object on the navigation bar on top.
	 * 
	 *                     ___> Orders Page should open with "Recently viewed" list
	 *                     of Orders .
	 * 
	 *                     ___> There should be no errors.
	 */
	@Test
	public void test_VerifyAccessToOrdersObject() throws IOException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.orders.verifyOrdersObject();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
