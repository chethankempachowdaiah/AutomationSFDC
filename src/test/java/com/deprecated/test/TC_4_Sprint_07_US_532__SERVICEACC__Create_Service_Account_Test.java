package com.deprecated.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

//***************************************************************************************//
//*************************______SPRINT 7_____*******************************************//
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//*********************____USER STORY : 532___ ******************************************//
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//***********Ability to create a service account*****************************************//                                                              //
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//***************************************************************************************//

/**
 * @author Priyanka.Acharya
 *
 *
 *
 *         1) The user should be able to create a service account after
 *         selecting a primary contact in the create account flow
 * 
 *         2) User can populate the below attributes will creating the account
 *         Account Name (Omni script) Account Number (Omni script) Parent
 *         Account (Omni script) Service Address with street, state, country and
 *         postal code (Omni script)
 * 
 *         3) The service account will have the Account type = Service.
 */
public class TC_4_Sprint_07_US_532__SERVICEACC__Create_Service_Account_Test extends Base {

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
		sfdc.cc.clickOnNextInCreateContact();
		sfdc.csa.enterServiceAccountInfo(1);// .................Enter Details for Service Account
		sfdc.csa.clickOnNextInCreateServiceAccount();// ........Click on Next in Service Account Creation Page
		sfdc.home.closeTabIfOpen();// ..........................Close tab
		sfdc.accDetails.validateServiceAccount(true);// ........Verify Service Account Details
		sfdc.home.closeTabIfOpen();// ..........................Close tab
		softassert.assertAll();
	}
}
