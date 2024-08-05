package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date: 16/JUNE/2020
 *
 *         Verify Creating and Removing a relationship between a contact and a
 *         new business account
 */
public class MP06_ServiceEnab_TC_01_Create_Remove_Relationship_Contact_BusinessAccount_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Click on Contact Related Tab
	 * 
	 *                              Click on 'Add Relationship Button'
	 * 
	 *                              Enter Account Name to be added as relationship
	 * 
	 *                              Select Available Role
	 * 
	 *                              Save Relationship
	 * 
	 *                              Verify Relationship added successfully
	 * 
	 *                              Verify Direct Relationship Options( View, Edit)
	 * 
	 *                              Verify Indirect Relationship Options(View, Edit,
	 *                              Remove)
	 */
	@Test
	public void test_CreateAndRemoveRelationship_Contact_BusinessAccount() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ***************LOGIN AS AE***********************//
//		sfdc.login.loginToSFDC(InputData.userid_ae);
//		sfdc.home.openR4BSalesConsole();
//		sfdc.home.closeTabIfOpen();

		// ***************Create business Account********//
//		sfdc.acc.createNewBusinessAccount();
//		sfdc.cba.enterBusinessAccountInfo(true);
//		sfdc.cba.verifyBusinessAccountCreated();

		// ***************Create Contact********//
//		sfdc.cc.enterCreateContactInfo(false);
//		sfdc.cc.verifyMarkPrimaryContactReadOnly();
//		sfdc.cc.verifyContactCreated();
//		sfdc.cc.clickOnNextInCreateContact();

		// ***************Create Service Account********//
//		sfdc.csa.enterServiceAccountInfo(1);
//		sfdc.csa.clickOnNextInCreateServiceAccount();
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
		
		// *********Create and Remove Contact and Account Relationship********//
		sfdc.contacts.verifyContactsObject();
		sfdc.contacts.searchContact();
		sfdc.conDetails.createAndRemoveRelationshipContactBA();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
