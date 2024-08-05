package scrum.om.test;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date 24/Jan/2020
 * 
 *         Verify Access To Manual Queues Object for Delivery and Ops Manager 
 *
 */
public class MP01_OM_TC_05_VerifyAccess_ManualQueues_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 *                     Click on Manual Queues object on the navigation bar on
	 *                     top.
	 * 
	 *                     ___> Manual Queues Page should open with "Recently
	 *                     viewed" list of Queues.
	 * 
	 *                     ___> There should be no errors.
	 * 
	 *                     ___> 2 Queue names should be displayed:
	 * 
	 *                     ..........Account Provisioning Queue
	 * 
	 *                     ..........Service Delivery Queue.
	 * 
	 */
	@Test(dataProvider = "getProfiles")
	public void test_VerifyAccessToManualQueuesObject(String profile) throws IOException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(profile);
		sfdc.home.closeTabIfOpen();
		sfdc.mques.verifyManualQueuesObject();
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
