package scrum.service.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Robin.Mangla, Date 24/MAR/2022
 * 
 *         Validate Account Executive is able to create a Service Account
 *
 * 
 */
public class MPOSS_57530_CreateServiceAccount_Test extends Base {

	/**
	 * @throws Exception
	 * 
	 */
	@Test(groups= {"regression"})
	public void test_CreateServiceAccount() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccountRevised();
		sfdc.cba.enterBusinessAccountInfoRevised();
		sfdc.cba.verifyBusinessAccountCreatedRevised();
		sfdc.cc.enterNewContactInfoForBusinessAccount(true, false, true, false);
		sfdc.cc.validateBusinessContact();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);	
		sfdc.acc.createNewServiceAccountRevised();
		sfdc.csa.enterServiceAccountInfoRevised();
		sfdc.csa.validateServiceAccountCreatedRevised();
		sfdc.premises.validatePremisesRevised();
		sfdc.home.closeTabIfOpen();		
		sfdc.home.logout();
		
		//approve business account
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);		
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
