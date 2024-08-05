package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date 15/Jan/2020
 * 
 *         Validate Account Executive is able to create a contact
 *
 * 
 */
public class MP01_ServiceEnab_TC_04_CreateContact_Test extends Base {

	@Test
	public void test_createContact() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.userid_ae);
		//sfdc.login.loginToSFDC(InputData_Sales.Profile_NIS);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccount();
		sfdc.cba.enterBusinessAccountInfo(false);
		sfdc.cba.verifyBusinessAccountCreated();
		sfdc.cc.enterCreateContactInfo(false);// ..................CREATE CONTACT
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreated();
		sfdc.cc.clickOnNextInCreateContact();
		sfdc.csa.enterServiceAccountInfo(1);// ...............CREATE SERVICE ACCOUNT
		sfdc.csa.clickOnNextInCreateServiceAccount();
		sfdc.csa.checkCreateBillingAccount();
		sfdc.cbia.enterBillingAccountInfo();// ...............CREATE BILLING ACCOUNT
		sfdc.cbia.verifyBillingAccountCreated();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.validateBusinessAccount();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// *********Login as Data Governance and Approve Busniess Account******//
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.userid_ae);
		//sfdc.login.loginToSFDC(InputData_Sales.Profile_NIS);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		sfdc.cc.createContact();
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.clickContactDetailsNext();
		sfdc.cc.verifyContactCreated();
		sfdc.cc.clickOnNextInCreateContact();
		sfdc.home.closeTabIfOpen();

		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.cc.changePrimaryContact();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.validateBusinessAccount();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}

}
