package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author priyanka.acharya, Date 01/May/2020
 *
 *         Change Legal Name on the business account and Reject (chnage the
 *         legal name, upload the evidence, Approval History, Account is locked
 *         and pending approval with ony one icon. Reject from data governance,
 *         see account is unlokced, status chnaged from pending approval , Icons
 *         are back, Legal name changed )
 */
public class MP04_ServiceEnab_TC_04_ChangeLegalNameBusinessAccount_Reject_Test extends Base {
	/**
	 * *****************Design Steps******************
	 * 
	 * 1. AE log in and open a business account
	 * 
	 * 2. Click on the Change Legal Name button on the left
	 * 
	 * 3. Change Legal Name and Next
	 * 
	 * 4. Validate Status on the business account and Legal Name
	 * 
	 * 5. Reject the Legal Name Change
	 * 
	 * 6. Validate Legal Name, status, account history on the business account
	 * 
	 * ******************Expected Results*****************
	 * 
	 * 1. Business account is displayed. User is the account owner. Change Legal
	 * Name button is present
	 * 
	 * 2. Change Legal Name page is displayed. Current Legal Name is present and
	 * there is option to change. Upload a evidence document is mandatory
	 * 
	 * 3. Legal Name is changed
	 * 
	 * 4. Status on the business account is changed to Pending Approval. Actions on
	 * the account are not present
	 * 
	 * 5. Legal Name change is rejected.
	 * 
	 * 6. Legal Name is changed. Actions on the account are present. Status is
	 * changed to Active. In the Account History, change details are captured
	 * 
	 * @throws IOException ******************************************************
	 */

	@Test
	public void test_verifyLegalNameChangeAndRejectionInBusinessAccount() throws IOException {

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
//		sfdc.home.logout();
		
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
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// **********Change Legal Name and Verify ********//
		sfdc.acc.selectChangeLegalName();
		sfdc.chngLegalName.changeLegalName();
		sfdc.accDetails.verifyLegalNameChangePendingApproval();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// *********Login as Data Governance and Reject Busniess Account******//
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.rejectAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// **********Verify Rejected Legal Name change ********//
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accDetails.verifyLegalNameChangeApprovedRejected();

		// **********Close Tabs and Log out********//
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();

	}
}
