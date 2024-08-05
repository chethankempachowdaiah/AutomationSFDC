package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Pankaj.Agarwal
 * 
 *         MP Release Regression_Service_TC12_Validate ACR permissions when user
 *         modify the role relationships on contacts for business, service and
 *         billing account
 * 
 * 
 *         MP Release Regression_Service_TC13_Validate ACR permissions when user
 *         modify the role relationships on contacts for business, service and
 *         billing account_Signing Autority|General|Site Contact
 * 
 * 
 *         MP Release Regression_Service_TC16_Validate Manage relationship on a
 *         business contact with AE profile
 *
 * 
 */
public class MP_Regression_ServiceEnab_TC_04_ModifyRoles_Business_Service_Billing_Account_Validate_ACR_Test
		extends Base {
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Combining test case 12, 13 and 16
	 *                              *********************************************************************************************************************
	 *                              A. Create Business account and the contact B.
	 *                              Create Service and Billing Account C. Search for
	 *                              the created contact and Click on button "Manage
	 *                              relationship" - In the Direct roles section,
	 *                              Click on the account and select Endorser,
	 *                              Assessor, Decider and Coach/Influencer roles D.
	 *                              Verify the relationship and ACR values for the
	 *                              contact in the related account list E. In add
	 *                              indirect roles section of Manage relationship
	 *                              page, search for previous business account
	 *                              select roles, for service account select roles
	 *                              Signing authority and site contact is selected.
	 *                              F. In add indirect roles section, search for
	 *                              billing account select role Signing authority G.
	 *                              Verify the relationship and ACR value in the
	 *                              related account list page for business, service
	 *                              and billing account which was added in indirect
	 *                              role section. H. Remove the relationship for
	 *                              service and billing account in related account
	 *                              list page.
	 * 
	 *                              I. Now add roles for business account again in
	 *                              direct roles as signing authority J. 3. In add
	 *                              indirect roles section, search for service
	 *                              account select roles General and site contact K.
	 *                              In add indirect roles section, search for
	 *                              billing account select role General L. Verify
	 *                              the relationship and ACR value in the related
	 *                              account list page for business, service and
	 *                              billing account which was added in in direct and
	 *                              indirect role section.
	 *********************************************************************************************************************
	 * 
	 */

	@Test
	public void test_ModifyRoles_Business_Service_Billing_Account_Validate_ACR()
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

		// ***************Create Contact********//
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreatedUpdated();
		sfdc.cc.clickOnNextInCreateContact();

		// ***************Create Service Account********//
		sfdc.csa.enterServiceAccountInfo(1);
		sfdc.csa.clickOnNextInCreateServiceAccount();

		// ***************Create Billing Account********//
		sfdc.csa.checkCreateBillingAccount();
		sfdc.cbia.enterBillingAccountInfo();
		sfdc.cbia.verifyBillingAccountCreated();
		sf.seleU.switchWindow(1);
		sf.seleU.closeRecentlyOpenedWindow();
		sfdc.home.closeLastOpenedTab();  
		sfdc.home.closeTabIfOpen();
		
		// ******************Searching Contact**************************
		sfdc.contacts.clickContactAndSearch(sf.dataInput.primaryContact);
		// sfdc.contacts.searchContactFromGlobalSearch("qdxwqfrq jxphfjil");

		// ******************Adding Direct Role for Business Account in Manage
		// Relationship**************************
		sfdc.conDetails.manageRelatioshipAddRoles(sfdc.cc.selectRelationshipType(3), sf.dataInput.contactEmailAddress);
		sfdc.home.closeLastOpenedTab();

		// ********************Verifying ACR Values for Business Contact in account
		// related list**********************************************
		sfdc.conDetails.validateAcrPermissionInContacts(sf.dataInput.acc_RecordType_Business,
				sf.dataInput.businessAccountName, sfdc.conDetails.creatingAcrValueArray(1),
				sfdc.cc.selectRelationshipType(3), false);

		sfdc.home.closeLastOpenedTabs(1);

		// ******************Adding Indirect Billing Account roles in Manage
		// Relationship**************************
		sfdc.conDetails.manageIndirectRelationshipAddBillingAccountRoles(sfdc.cc.selectRelationshipType(2),
				sf.dataInput.billingAccountName);

		// **************Verifying ACR Values for Billing Account in related account
		// list*****************************
		sfdc.conDetails.validateAcrPermissionInContacts(sf.dataInput.acc_RecordType_Billing,
				sf.dataInput.billingAccountName, sfdc.conDetails.creatingAcrValueArray(6),
				sfdc.cc.selectRelationshipType(2), true);

		sfdc.home.closeLastOpenedTabs(1);

		// ******************Adding InDirect Role for Business
		// Account**************************
		sfdc.conDetails.manageIndirectBusinessRelatioshipAddRoles(sfdc.cc.selectRelationshipType(4),
				sf.dataInput.businessAccountName);

		// ********************Verifying ACR Values for Business
		// Account**********************************************
		sfdc.conDetails.validateAcrPermissionInContacts(sf.dataInput.acc_RecordType_Business,
				sf.dataInput.businessAccountName, sfdc.conDetails.creatingAcrValueArray(2),
				sfdc.cc.selectRelationshipType(4), false);
		sfdc.home.closeLastOpenedTabs(1);

		// ******************Adding Indirect Service Account roles in Manage
		// Relationship**************************
		sfdc.conDetails.manageIndirectServiceRelatioshipAddRoles(sfdc.cc.selectRelationshipType(5),
				sf.dataInput.serviceAccountName);

		// **************Verifying ACR Values for Service Account in account related
		// list*********************************
		sfdc.conDetails.validateAcrPermissionInContacts(sf.dataInput.acc_RecordType_Service,
				sf.dataInput.serviceAccountName, sfdc.conDetails.creatingAcrValueArray(5),
				sfdc.cc.selectRelationshipType(5), true);

		sfdc.home.closeLastOpenedTab();

		// *****************for Test Case 13
		// **************************************************//

		// ******************Adding Direct Role for Business
		// Account**************************// Add signing authority
		sfdc.conDetails.manageRelatioshipAddRoles(sfdc.cc.selectRelationshipType(2), sf.dataInput.contactEmailAddress);
		sfdc.home.closeLastOpenedTab();

		// ********************Verifying ACR Values for Business Contact in related
		// account list**********************************************
		sfdc.conDetails.validateAcrPermissionInContacts(sf.dataInput.acc_RecordType_Business,
				sf.dataInput.businessAccountName, sfdc.conDetails.creatingAcrValueArray(9),
				sfdc.cc.selectRelationshipType(2), false);
		sfdc.home.closeLastOpenedTabs(1);

		// ******************Adding Indirect Billing Account
		// roles**************************
		sfdc.conDetails.manageIndirectRelationshipAddBillingAccountRoles(sfdc.cc.selectRelationshipType(9),
				sf.dataInput.billingAccountName);

		// sfdc.conDetails.manageIndirectRelationshipAddBillingAccountRoles(sfdc.cc.selectRelationshipType(9),
		// "ABiQAAuto210413053660");

		// **************Verifying ACR Values for Billing Account in related account
		// list**********************************************
		sfdc.conDetails.validateAcrPermissionInContacts(sf.dataInput.acc_RecordType_Billing,
				sf.dataInput.billingAccountName, sfdc.conDetails.creatingAcrValueArray(1),
				sfdc.cc.selectRelationshipType(9), false);
		sfdc.home.closeLastOpenedTab();

		// ******************Adding Indirect Service Account
		// roles******************************
		sfdc.conDetails.manageIndirectServiceRelatioshipAddRoles(sfdc.cc.selectRelationshipType(8),
				sf.dataInput.serviceAccountName);

		// **************Verifying ACR Values for Service
		// Account**********************************************
		sfdc.conDetails.validateAcrPermissionInContacts(sf.dataInput.acc_RecordType_Service,
				sf.dataInput.serviceAccountName, sfdc.conDetails.creatingAcrValueArray(8),
				sfdc.cc.selectRelationshipType(8), false);
		sfdc.home.closeLastOpenedTab();

		// *************Log Out*******************
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();

	}
}
