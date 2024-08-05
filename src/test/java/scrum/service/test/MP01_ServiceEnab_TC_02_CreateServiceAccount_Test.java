package scrum.service.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date 08/Jan/2019
 * 
 *         Validate Account Executive is able to create a Service Account
 *
 * 
 */
public class MP01_ServiceEnab_TC_02_CreateServiceAccount_Test extends Base {

	/**
	 * @throws Exception
	 * 
	 */
	@Test(groups= {"regression"})
	public void test_createServiceAccount() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewServiceAccount();
		sfdc.csa.enterServiceAccountInfo(1);
		sfdc.csa.verifyServiceAccountCreated();
		sfdc.csa.clickOnNextInCreateServiceAccount();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.validateServiceAccount(false);
		sfdc.premises.validatePremises();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();

	}
}
