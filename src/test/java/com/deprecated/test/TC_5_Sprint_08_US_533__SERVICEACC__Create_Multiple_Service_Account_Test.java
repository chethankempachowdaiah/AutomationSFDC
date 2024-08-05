package com.deprecated.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

//***************************************************************************************//
//*************************______SPRINT 8_____*******************************************//
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//*********************____USER STORY : 533___ ******************************************//
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//***********Ability to create multiple service accounts*********************************//                                                              //
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//***************************************************************************************//

/**
 * @author Priyanka.Acharya
 * 
 *         1) The user should be able to create multiple service accounts from
 *         the create service account screen
 * 
 *         2) The service account will have the Account type = Service.
 *
 */
public class TC_5_Sprint_08_US_533__SERVICEACC__Create_Multiple_Service_Account_Test extends Base {

	@Test
	public void test_createServiceAccount() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.userid_ae);// ............................Login to sfdc application
		sfdc.home.closeTabIfOpen();// ..........................Close tab if it already open
		sfdc.acc.createNewBusinessAccount();// ....................Create Business Account
		sfdc.cba.enterBusinessAccountInfo(false);// .................Enter Account Details for Busniess Account
		sfdc.cba.verifyBusinessAccountCreated();// ..............Verify Busniess Account Successfully Created
		sfdc.cc.enterCreateContactInfo(false);// ....................Enter Contact Details
		sfdc.cc.verifyContactCreated();// ......................Verify Contact Created
		sfdc.cc.clickOnNextInCreateContact();// .......................Verify Contact is created
		sfdc.csa.enterServiceAccountInfo(1);// .................Enter Details for Service Account
		sfdc.csa.selectAddAnotherServiceAccount();// ...........Select Add Button to add another service account
		sfdc.csa.enterServiceAccountInfo(2);// .................Enter Details for Service Account
		sfdc.csa.clickOnNextInCreateServiceAccount();// .........Click on Next in Service Account Creation Page
		sfdc.home.closeTabIfOpen();// ..........................Close tab
		sfdc.accDetails.validateServiceAccount(true);// ........Verify Service Account Details
		sfdc.home.closeTabIfOpen();// ..........................Close tab
		softassert.assertAll();
	}
}
