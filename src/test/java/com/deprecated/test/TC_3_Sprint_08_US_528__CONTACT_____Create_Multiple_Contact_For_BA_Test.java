package com.deprecated.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

//***************************************************************************************//
//*************************______SPRINT 8_____*******************************************//
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//*********************____USER STORY : 528___ ******************************************//
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//Ability to create multiple contacts from the Business Account confirmation page********//                                                              //
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//***************************************************************************************//

/**
 * @author Priyanka.Acharya
 * 
 *         After creating a business account, the user should be able to create
 *         multiple contacts. Create an association between the contacts and the
 *         business account.
 *
 */
public class TC_3_Sprint_08_US_528__CONTACT_____Create_Multiple_Contact_For_BA_Test extends Base {

	@Test
	public void test_createMultipleContact() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.userid_ae); // ........................... Login to Salesforce
		sfdc.home.closeTabIfOpen(); // ..........................Close if any other tab is open
		sfdc.acc.createNewBusinessAccount(); // ....................Create Business Account
		sfdc.cba.enterBusinessAccountInfo(false); // .................Enter deatils for Busniess Account
		sfdc.cba.verifyBusinessAccountCreated(); // ..............Verify Busniess Account is created successfully
		sfdc.cc.enterCreateContactInfo(false); // ....................Enter Contact Creation Details
		sfdc.cc.verifyContactCreated();// .......................Verify Contact is created
		sfdc.cc.selectCreateAnotherContact();// .................Select 'Create Another Contact'
		sfdc.cc.enterCreateContactInfo(false); // ....................Enter Contact Creation Details
		sfdc.cc.verifyContactCreated();// .......................Verify Contact is created
		sfdc.cc.clickOnNextInCreateContact();
		sfdc.home.closeTabIfOpen();// ...........................Close tabs
		softassert.assertAll();
	}
}
