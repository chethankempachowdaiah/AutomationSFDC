package com.deprecated.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, 30/March/2020
 * 
 *         Validate that a user with Account Executive Profile cannot complete
 *         the Orchestration Tasks
 *
 */
public class MP_Regression_TC_14_VerifyAccess_AE_NoAccess_ManualQueues_Test extends Base {

	/**
	 * Login As AE
	 * 
	 * Verify There is no 'manual queue' object accessible from AE
	 * 
	 * @throws IOException
	 */
	@Test
	public void test_Verify_AE_NoAccess_ManualQueues() throws IOException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.mques.verifyNoAccessToManualQueues();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}