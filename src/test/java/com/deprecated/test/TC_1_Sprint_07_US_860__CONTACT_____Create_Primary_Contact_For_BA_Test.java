package com.deprecated.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

//***************************************************************************************//
//*************************______SPRINT 7_____*******************************************//
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//*********************____USER STORY : 860___ ******************************************//
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//***********Ability to create and assign a primary contact for a Business Account*******//                                                              //
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//***************************************************************************************//

/**
 * @author Priyanka.Acharya
 * 
 * 
 *         1) After creating a business account, the user should be able to
 *         contact and designate it as a primary contact. Create an association
 *         between the contact and the business account.
 * 
 *         2) When a new business account is created and the user invokes the
 *         create contact Omni script, mark the first contact as the primary
 *         contact.
 *
 */
public class TC_1_Sprint_07_US_860__CONTACT_____Create_Primary_Contact_For_BA_Test extends Base {

	@Test
	public void test_createPrimaryContact() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.userid_ae); // ........................... Login to Salesforce
		sfdc.home.closeTabIfOpen(); // ..........................Close if any other tab is open
		sfdc.acc.createNewBusinessAccount(); // ....................Create Business Account
		sfdc.cba.enterBusinessAccountInfo(false); // .................Enter deatils for Busniess Account
		sfdc.cba.verifyBusinessAccountCreated(); // ..............Verify Busniess Account is created successfully
		sfdc.cc.enterCreateContactInfo(false); // ....................Enter Contact Creation Details
		sfdc.cc.verifyContactCreated();// .......................Verify Contact is created
		sfdc.cc.clickOnNextInCreateContact();
		sfdc.home.closeTabIfOpen();// ...........................Close tabs
		softassert.assertAll();
	}
}
