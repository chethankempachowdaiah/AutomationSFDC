package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date: 18/JUNE/2020
 *
 *         Verify Link one account to a master account
 * 
 *         Verify Delink account from master account
 */
public class MP06_ServiceEnab_TC_02_Link_DeLink_Account_MasterAccount_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * 
	 *                              ****************Pre-requisite**************
	 * 
	 *                              Profile: Data Governance
	 * 
	 *                              Account status: Active
	 * 
	 * 
	 *                              *****************Design Steps***************
	 * 
	 *                              1. Data Governance log in and open a business
	 *                              account
	 * 
	 *                              2. Click on the Create Relationship
	 * 
	 *                              3. Select Master account
	 * 
	 *                              4. Search account to create a relationship by
	 *                              using: Legal Name, Trade Name R1DUNS, DUNS
	 * 
	 *                              5. Select relationship Type
	 * 
	 *                              6. Validate Party Relationship
	 * 
	 * 
	 *                              7. Validate account hierarchy
	 * 
	 *                              *****************Expected Result**************
	 * 
	 *                              1. Business account is displayed. Create
	 *                              relationship button is available
	 * 
	 *                              2. Create Relationship Omniscipt is invoked.
	 *                              Save for Later is not present
	 * 
	 *                              3. Master account is displayed. Select and
	 *                              choose as master account
	 * 
	 *                              4. Accounts are found using following fields:
	 *                              Legal Name, Trade Name, R1DUNS, DUNS, these
	 *                              fields can be used sequentially to narrow down
	 *                              the results
	 * 
	 *                              5. Relation Type is mandatory. Values in the
	 *                              picklist: Subsidiary, Franchise, Branch,
	 *                              Division, Affiliate, Joint Venture, Limited
	 *                              Partnership, Federated Franchise and Government
	 *                              Subsidiary.
	 * 
	 *                              6. Party relationship is created and
	 *                              relationship type is populated
	 * 
	 *                              7. Account hierarchy is displayed and
	 *                              relationship is displayed correctly
	 * 
	 * 
	 *                              ******************************************
	 * 
	 * 
	 * 
	 */
	@Test
	public void test_Link_DeLink_Account_MasterAccount() throws IOException, InterruptedException {

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
		sfdc.home.logout();

		// *********Login as Data Governance and Approve business Account******//
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();

		// ***************Link Account********//
		sfdc.acc.selectCreateRelationshipIcon();
		sfdc.linkMasterAcc.linkMasterAccount();
		sfdc.accHirchy.verifyAccountHierarchyAdded();
		sfdc.partyRelationships.verifyPartyRelationshipCreated();
		sfdc.home.closeTabIfOpen();

		// ***************Delink Account********//
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.acc.selectRemoveRelationshipIcon();
		sfdc.linkMasterAcc.delinkAccount();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accHirchy.verifyAccountHierarchyRemoved();
//		sfdc.partyRelationships.verifyPartyRelationshipRemoved();
		sfdc.home.closeTabIfOpen();

		sfdc.home.logout();
		softassert.assertAll();
	}
}
