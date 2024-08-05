package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date 05/May/2020
 * 
 *         Validate Account Executive is able to create a Child bunsiness
 *         Account(create account with existing account name and address and ,
 *         select check box for duplicate and option 2 for child ACCOUNT
 *         validate parent and child account)
 *
 * 
 */
public class MP04_ServiceEnab_TC_02_CreateChildBusinessAccount_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 *                              1. Sales log in and select account object
	 * 
	 *                              2. Open an business account
	 * 
	 *                              3. Click Create Account button on the account
	 *                              summary section
	 * 
	 *                              4. Select Account Record Type: Business
	 * 
	 *                              5. Create business Account With Existing Account
	 *                              Name & address
	 * 
	 *                              6. There is duplicate found, Select Option "No,
	 *                              but I would like to create a new subsidiary or
	 *                              child within the selected account"
	 * 
	 *                              7. Enter contact information. "Mark as Primary
	 *                              Contact" will be selected automatically and will
	 *                              be read-only
	 * 
	 *                              8. Enter service account information
	 * 
	 *                              9. Validate business account(With Parent
	 *                              Account) created.
	 * 
	 * @throws InterruptedException
	 * 
	 */
	@Test
	public void test_createChildBusinessAccount() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// ***Create business Account With Existing Account Name & address***//
		sfdc.acc.createNewBusinessAccount();
		sfdc.cba.enterBusinessAccountInfo(true);
		sfdc.cba.validateAndSelectChildAccount();

		// ***************Create Contact********//
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreated();
		sfdc.cc.clickOnNextInCreateContact();

		// ***************Create Service Account********//
//		sfdc.csa.enterServiceAccountInfo(1);
//		sfdc.csa.clickOnNextInCreateServiceAccount();
		sfdc.csa.noBillingAccountClickOnNext();

		// *********Validate Business Account******//
		sfdc.accDetails.validateBusinessAccount();
		sfdc.home.closeTabIfOpen();

		// **********Close Tabs and Log out********//
		sfdc.home.logout();
		softassert.assertAll();

	}

}
