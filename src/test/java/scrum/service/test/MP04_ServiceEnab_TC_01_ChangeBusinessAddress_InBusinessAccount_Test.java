package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Robin.Mangla, Date 06/JAN/2021
 *
 *         Change Business Address on the business account(name and business
 *         address validation in account evidence upload page validate task
 *         created, open task, validate old and new value)
 */
public class MP04_ServiceEnab_TC_01_ChangeBusinessAddress_InBusinessAccount_Test extends Base {

	/**
	 * *****************Design Steps******************
	 * 
	 * Create a new Business Account
	 * 
	 * Change the Business Address
	 * 
	 * Validate New Business Address
	 * 
	 * 
	 * *
	 * 
	 * @throws IOException ******************************************************
	 */
	@Test
	public void test_ChangeBusinessAddress_InBusinessAccount() throws IOException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ***************LOGIN AS AE***********************//
//		sfdc.login.loginToSFDC(InputData.Profile_AE);
//		sfdc.home.openR4BSalesConsole();
//		sfdc.home.closeTabIfOpen();

		// ***************Create business Account********//
//		sfdc.acc.createNewBusinessAccount();
//		sfdc.cba.enterNewBusinessAccountInfo(true);
//		sfdc.cba.verifyNewBusinessAccountCreated();

		// ***************Create Contact********//
//		sfdc.cc.enterCreateContactInfo(false);
//		sfdc.cc.verifyMarkPrimaryContactReadOnly();
//		sfdc.cc.verifyContactCreated();
//		sfdc.cc.clickOnNextInCreateContact();
//		sfdc.csa.noBillingAccountClickOnNext();
//		sfdc.home.closeTabIfOpen();
		
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccountRevised();
		sfdc.cba.enterBusinessAccountInfoRevised();
		sfdc.cba.verifyBusinessAccountCreatedRevised();
		sfdc.cc.enterNewContactInfoForBusinessAccount(true, false, true, false);
		sfdc.cc.validateBusinessContact();
		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
		
		// *************Change Business Address and Verify*******//
        sfdc.acc.selectChangeBusinessAddress();
        sfdc.acc.validateNewBusinessAddress();
        sfdc.home.closeTabIfOpen();		
		sfdc.home.logout();

		// *********Login as Data Governance and Approve business Account******//
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
