package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date 16/July/2020
 * 
 *         Enable community access for a business contact successfully
 * 
 *         Troubleshoot community access
 * 
 *         Creating and Validating service group when Enable community User
 *
 */
public class MP05_ServiceEnab_TC_02_EnableCommunity_ValidateServiceGroup_TrobuleshootAccess_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 *                     <<<Creating service group when Enable community User>>>
	 * 
	 *                     ***************** Design Steps**********************
	 * 
	 *                     1. AE log in and open a business contact 2. Click on the
	 *                     Enable Community User 4. Click on New and create Support
	 *                     Group 5. Open the business account and validate the
	 *                     service group related list
	 * 
	 *                     3. Validate community user is created and email is
	 *                     received 4. Activate the user in MBH 5. Click again on
	 *                     the Enable Community user
	 * 
	 *                     ******************* Expected Result**********************
	 * 
	 *                     1. Contact page is displayed. Contact record type is
	 *                     Business Contact. Enable Community User button is
	 *                     available 2. Enable Community User is displayed 4. User
	 *                     is able to create service groups. Following fields are
	 *                     mandatory: Name, Service Phone, Support Email, Products
	 *                     Supported, Hours of Operation. Hours of Operation is
	 *                     default 8:30 AM to 5:00 PM 5. In the related list,
	 *                     Service groups are displayed.
	 * 
	 *                     3. Confirmation of enable community user is displayed 4.
	 *                     Activation email is received 5. Community user is
	 *                     activated in MBH 6. Error message is displayed: Community
	 *                     user is existing.
	 * 
	 *                     <<<<<<<<<<Troubleshoot User>>>>>>>>>>>>>>
	 * 
	 *                     ***************** Design Steps**********************
	 * 
	 *                     1. AE log in and open a business contact 2. Click on
	 *                     Troubleshoot User 3. Validate User status
	 * 
	 *                     ******************Expected Result**********************
	 * 
	 * 
	 *                     1. Contact page is displayed. Buttons: Enable community
	 *                     user and Troubleshooting user are available 2. Enable
	 *                     Community user is displayed. User GUid is displayed 3.
	 *                     User status: Activation Time out. Resend the activation
	 *                     link.
	 * 
	 */
	@Test
	public void test_EnableCommunity_ValidateServiceGroup_TrobuleshootAccess() throws IOException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// ***************Create new Business Contact********//
		sfdc.contacts.selectAndClickNewContact();
		
		//Steps added to accommodate new create contact flow
		sfdc.cc.enterNewContactInfo(false, false, false, false, false);// ..................CREATE NEW CONTACT
		sfdc.cc.validateNewContact();
		
//		sfdc.cc.selectBusinessContactRadio();
//		sfdc.cc.enterCreateContactInfo(false);
		// sfdc.cc.clickContactDetailsNext();
		// sfdc.cc.contactSearchAccount(false);
		// sfdc.cc.verifySiteContactCreatedSuccessfully();
//		sfdc.cc.clickContactDetailsNext();
//		sfdc.cc.verifyContactCreated();
//		sfdc.cc.clickOnNextInCreateContact();

		// ***************Enable Community User********//
		// sfdc.contacts.clickContactAndSearch(sf.dataInput.accountContact);
		sfdc.enableComUser.enableCommunityUser();
		sfdc.enableComUser.verifyCommunityUserExists();

		// ***************Troubleshoot User********//
		sfdc.enableComUser.troubleshootUser();

		// ***************Validate Service Group********// Deprecated
		// sfdc.accRelated.verifyServiceGroupsAddedToAccount();

		// **********Close Tabs and Log out********//
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
