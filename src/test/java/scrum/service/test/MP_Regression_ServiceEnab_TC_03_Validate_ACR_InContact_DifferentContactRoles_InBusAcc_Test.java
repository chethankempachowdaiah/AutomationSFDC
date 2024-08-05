package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Pankaj Agarwal
 * 
 *         * MP Release Regression_Service_TC07_Create contact from contact list
 *         and select business account - duplicate found- update contact
 * 
 *         MP Release Regression_Service_TC10_Validate ACR permissions when
 *         create contact and select Endorsor, Assessor, Decider and
 *         Coach/Influencer roles on a business account
 * 
 *         MP Release Regression_Service_TC11_Validate ACR permissions when
 *         create contact and select Signing Authority role on a business
 *         account
 *
 */
public class MP_Regression_ServiceEnab_TC_03_Validate_ACR_InContact_DifferentContactRoles_InBusAcc_Test extends Base {
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * 
	 * 
	 * 
	 *                              // Combining TestCase 7,8,10,11
	 * 
	 *                              1. AE log in and open Contact object, click on
	 *                              New 2. Fill in contact information. (use email
	 *                              address on an existing contact to get
	 *                              duplicate). Select business account type. Search
	 *                              account by using type ahead 3. Select a business
	 *                              account, and select relationship types:
	 *                              Endorsor, Assessor, Decider and Coach/Influencer
	 *                              4. Validate text for manage relationship option
	 *                              5. Validate contact is created and ACR
	 *                              permissions field on ACR in account and contact
	 *                              related page 6. Validate the updated contact
	 *                              phone number to verify new contact has replaced
	 *                              and it's been created
	 * 
	 *                              7. Create another contact using first contact
	 *                              email id 2. Fill in contact information. (use
	 *                              email address on an existing contact to get
	 *                              duplicate). Select business account type. Search
	 *                              account by using type ahead 9. Select a business
	 *                              account, and select relationship types: Signing
	 *                              Authority 10. Validate text for manage
	 *                              relationship option 11. Validate contact is
	 *                              created and ACR permissions field on ACR 12.
	 *                              Validate the updated contact phone number to
	 *                              verify new contact has replaced and it's been
	 *                              created
	 * 
	 *                              13. Create another contact using first contact
	 *                              email id 14. Fill in contact information. (use
	 *                              email address on an existing contact to get
	 *                              duplicate). Select business account type. Search
	 *                              account by using type ahead. 15. Select a
	 *                              business account, and select relationship types
	 *                              16. Validate duplicate. Select the duplicate
	 *                              contact and select create new contact instead of
	 *                              update contact 17. Validate text for manage
	 *                              relationship option 18. Validate contact is
	 *                              created
	 * 
	 */

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void test_ACR_permissions_InContact_EndorsorAssessorDeciderCoachInfluencer_SigningAuth_InBusAcc()
			throws IOException, InterruptedException {

	
		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.userid_ae);
	    sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// ***************Create business Account********//
		sfdc.acc.createNewBusinessAccount();
		sfdc.cba.enterBusinessAccountInfo(false);
		sfdc.cba.verifyBusinessAccountCreated();

		// ***************Create first Contact to use the email id of this for the
		// second contact********//
		// (A)***************Create First Contact********//
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreatedUpdated();
		sfdc.cc.clickOnNextInCreateContact();
		sf.seleU.switchWindow(1);
		sf.seleU.closeRecentlyOpenedWindow();
		sfdc.home.closeLastOpenedTab();  

		// (B.) ***************Create second Contact from New button and use first
		// contact email id to generate duplicate scenario and select role endorser,
		// accessor, decider, coach influencer
		// **********************//
		sfdc.contacts.selectAndClickNewContact();
		//	sfdc.cc.createContact();
		//		Global.dataInput.businessAccountName = "TESTAuto_PVT210729071983";
		//		Global.dataInput.duplicateEmailAddress = "yonosxar@mailinator.com";
		// Enter New Contact Information
		Global.dataInput.secondContact_prepareContactData();
		sfdc.cc.enterNewContactInfo(sfdc.cc.selectRelationshipType(3), false, false,
				Global.dataInput.businessAccountName);

		// ********************Validate and review the contact***************//
		sfdc.cc.validateUpdatedContact(false, Global.dataInput.duplicateEmailAddress);

		// ********************Validate and verify the new contact in contact details
		// age***************//
		sfdc.conDetails.verifyUpdatedContactInDetails(Global.dataInput.duplicateEmailAddress, Global.dataInput.businessAccountName);
		sfdc.conDetails.clickOnBusinessAccountFromContactDetails();

		// ********************Verify Relationship and ACR Value in in related contact
		// list in account related page***

		sfdc.accRelated.validateAcrPermissionInAccountRelated(Global.dataInput.accountContact,
				sfdc.conDetails.creatingAcrValueArray(1), sfdc.cc.selectRelationshipType(3), false);

		// ********************Search the contact to go to view history in contact
		// related page and verify updated phone
		// number***********************************
		sfdc.home.closeTabIfOpen();
	//	Global.dataInput.accountContact = "vwhisbjg rvdskkfi";
		sfdc.contacts.clickContactAndSearch(Global.dataInput.accountContact);
		sfdc.conDetails.validateUpdatedContactDetailsInHistory(Global.dataInput.contactPhoneNumber);

		// (C.) ***************Create third Contact from New button and use first
		// contact email id and select role signing authority**********************//
		sfdc.home.closeTabIfOpen();
		sfdc.contacts.selectAndClickNewContact();

		Global.dataInput.thirdContact_prepareContactData();
		sfdc.cc.enterNewContactInfo(sfdc.cc.selectRelationshipType(2), false, false,
				Global.dataInput.businessAccountName);

		// ********************Validate and review the contact***************//
		sfdc.cc.validateUpdatedContact(false, Global.dataInput.duplicateEmailAddress);

		// ********************Validate and verify the new contact in contact details
		// age***************//
		sfdc.conDetails.verifyUpdatedContactInDetails(Global.dataInput.duplicateEmailAddress,
				Global.dataInput.businessAccountName);

		sfdc.conDetails.clickOnBusinessAccountFromContactDetails();

		// ****Verify Relationship and ACR Value in in related contact list in account
		// related page***

		sfdc.accRelated.validateAcrPermissionInAccountRelated(Global.dataInput.siteContactName,
				sfdc.conDetails.creatingAcrValueArray(2), sfdc.cc.selectRelationshipType(2), false);

		// ********************Search the contact to go to view history in contact
		// related page and verify updated phone
		// number***********************************

		sfdc.home.closeTabIfOpen();
		sfdc.contacts.clickContactAndSearch(Global.dataInput.siteContactName);
		sfdc.conDetails.validateUpdatedContactDetailsInHistory(Global.dataInput.contactPhoneNumber);

		// sfdc.conDetails.validateUpdatedContactDetailsInHistory(Global.dataInput.contactPhoneNumber);

		// ****************************TestCase8*********************************************************
		// Select New Contact Option instead of update contact
		// (C.) ***************Create Second Contact from New button and enter email id
		// of first contact for duplicacy********//
		// sfdc.home.closeTabIfOpen();
		// sfdc.contacts.verifyContactsObject();
		// sfdc.contacts.clickOnNewContactButton();
		//
		// // Enter New Contact Information with duplicate email id of first created
		// contact*******//
		// // sf.dataInput.duplicateEmailAddress=sf.dataInput.contactEmailAddress;
		// sf.dataInput.fourthContact_prepareContactData();
		// sfdc.cc.enterNewContactInfo(relationshipType,true,false);
		//
		// //********************Validate and select new contact option instead of
		// update and review the contact***************//
		// sfdc.cc.validateUpdatedContact(true,sf.dataInput.duplicateEmailAddress);
		//
		// //********************Validate and verify the new contact in contact details
		// age***************//
		// sfdc.conDetails.verifyUpdatedContactInDetails(sf.dataInput.duplicateEmailAddress,
		// sf.dataInput.businessAccountName);
		//
		// //********************Search the contact to go to view history in contact
		// related page and verify updated phone
		// number***********************************
		// sfdc.conDetails.validateUpdatedContactDetails(sf.dataInput.contactPhoneNumber);

		// *****************************logout*****************************************************
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
