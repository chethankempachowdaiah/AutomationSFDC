package testpoc.mpdemo.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

//***************************************************************************************//
//*************************___MP SANITY AUTOMATION DEMO__********************************//
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//*********************____MP Release: MP01___ ******************************************//
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//***********Validate Account Executive is able to create a bunsiness Account************//                                                              //
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//***************************************************************************************//

public class MP_ServiceEnab_TC_01_CreateBusinessAccount_Demo_Test extends Base {

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
	 *                              5. Enter account information and select use
	 *                              different address for service account
	 * 
	 *                              6. If there is duplicate found, select third
	 *                              action: create a new account; If there is no
	 *                              duplicate found, click Next
	 * 
	 *                              7. Enter contact information. "Mark as Primary
	 *                              Contact" will be selected automatically and will
	 *                              be read-only
	 * 
	 *                              8. Enter service account information
	 * 
	 *                              9. Check the checkbox Create billing account
	 * 
	 *                              10. Enter billing account information
	 * 
	 *                              11. Validate business account, service, and
	 *                              billing account are created.
	 * @throws InterruptedException
	 * 
	 */
	@Test
	public void test_CreateBusinessAccount() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);

		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		/*
		 * sfdc.acc.createNewBusinessAccount();
		 * 
		 * sfdc.cba.enterBusinessAccountInfo(); sfdc.cba.verifyBusinessAccountCreated();
		 * 
		 * sfdc.cc.enterCreateContactInfo(false);
		 * sfdc.cc.verifyMarkPrimaryContactReadOnly(); sfdc.cc.verifyContactCreated();
		 * sfdc.cc.clickOnNextInCreateContact();
		 * 
		 * sfdc.csa.enterServiceAccountInfo(1);
		 * sfdc.csa.clickOnNextInCreateServiceAccount();
		 * sfdc.csa.noBillingAccountClickOnNext();
		 * 
		 * sfdc.home.closeTabIfOpen(); sfdc.home.logout();
		 * 
		 * sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		 * sfdc.home.closeTabIfOpen(); sfdc.accDetails.validateBusinessAccount();
		 * sfdc.accRelated.approveAsDataGovernance(); sfdc.home.closeTabIfOpen();
		 */

		sfdc.home.logout();

		softassert.assertAll();

	}

}
