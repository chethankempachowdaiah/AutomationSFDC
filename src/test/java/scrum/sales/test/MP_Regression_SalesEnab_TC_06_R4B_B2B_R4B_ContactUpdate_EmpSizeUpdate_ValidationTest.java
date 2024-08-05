
package scrum.sales.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.BaseBrowser;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Pankaj Agarwal, Date: 18/Dec/2020
 *
 *         MP Release Regression_Sales_TC09_Validate contact update sync on
 *         existing Contact from R4B to B2B.
 * 
 *         MP Release Regression_Sales_TC10_Validate contact update sync on
 *         existing Contact from B2B to R4B.
 * 
 *         MP Release Regression_Sales_TC23_Validate Employee size is getting
 *         updated during account update flow is getting sync from R4B to B2B.
 * 
 *         MP Release Regression_Sales_TC24_Validate Employee size is getting
 *         updated during account update flow is getting sync from B2B to R4B.
 * 
 */
public class MP_Regression_SalesEnab_TC_06_R4B_B2B_R4B_ContactUpdate_EmpSizeUpdate_ValidationTest extends BaseBrowser {

	/**
	 *********************************************************************************************************************
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * 
	 *                              1. Login with an CSM admin profile in B2B. 2.
	 *                              Create a new Business Account and a contact. 3.
	 *                              Verify account and contact is created. 4. Change
	 *                              the contact phone and Business account Emp size
	 *                              5. Login with an AE profile in ITQA. 6. Search
	 *                              the Business account which was created in B2B
	 *                              and verify the updated emp size 7. Search the
	 *                              business contact which was created in B2B and
	 *                              verify the updated phone number 8. Change the
	 *                              emp size and the contact phone number 9. Switch
	 *                              to B2B and login to it with CSM admin profile
	 *                              10. Search for the business account and verify
	 *                              the emp size which was updated in R4B 11. Search
	 *                              for the business contact and verify the phone no
	 *                              which was updated in R4B 12. Log out from B2B
	 ************************************************************************************************************************
	 * 
	 */

	@Test
	public void test_R4B_B2B_R4B_TC_02_ContactUpdate_EmpSizeUpdate_Validation()
			throws IOException, InterruptedException {

		intializeChrome(false);
		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ***************LOGIN TO ITFULL***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.home.switchToB2BOrg();
		sfdc.login.loginToSFDC(InputData.oldOrg_username);

		// **************Clicking the new account tab and creating the parent business account*****//
		sfdc.b2bAccount.newAccount();
		sfdc.b2bAccount.enterAccountDetails(Global.dataInput.businessAccountName, Global.dataInput.b2b_malID);

		// **************Verify the business account is created**********************************//
		sfdc.b2bAccount.verifyAccountCreated();

		// **************Create the contact with status as active************************//
		sfdc.b2bContacts.createNewContact();
		sfdc.b2bContacts.enterContactDetails();

		// **************Verify contact is created**********************************//
		sfdc.b2bContacts.verifyContactCreated();

		// **************Search and update the contact phone in B2B*******************
		sfdc.b2bContacts.searchContactCreated(Global.dataInput.primaryContact);
		sfdc.b2bContacts.editContactPhoneNumber(Global.dataInput.b2b_updatedPhoneNumber);

		// **************Update Emp Size in B2B**********************************//
		sfdc.b2bAccount.searchAccountCreated(Global.dataInput.businessAccountName);
		sfdc.b2bAccount.selectChangeEmployeeSize(Global.dataInput.updatedEmployeeSize);

		// ***************LOGOUT ITFULL***************************//
		sfdc.home.logout_FromB2B();
		closeBrowser();

		// ***************LOGIN In R4B AS AE***********************//
		intializeChrome(false);
		sfdc = new SFDC_AllPages();
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();

		// **************Search for the same Business Account to verify the emp size
		// which was updated in B2B********//
		sfdc.accDetails.searchBusAccGlobalSearch_DataGovern(Global.dataInput.businessAccountName);

		// **************Verify the updated emp size in R4b***********************//
		sfdc.accDetails.verifyEmployeeSizeInDetails(Global.dataInput.updatedEmployeeSize);
		sfdc.home.closeTabIfOpen();

		// **************Verify the updated contact phone no in
		// R4b***********************//
		sfdc.contacts.searchContactFromGlobalSearch(Global.dataInput.primaryContact);
		sfdc.conDetails.verifyContactPhoneNumber(Global.dataInput.b2b_updatedPhoneNumber);
		sfdc.home.closeTabIfOpen();
	    sf.seleU.refreshPage();
       // sfdc.home.logout();
        //closeBrowser();
             
//        intializeChrome(false);
//		sfdc = new SFDC_AllPages();
//		sfdc.login.loginToSFDC(InputData.Profile_AE);
//		sfdc.home.closeTabIfOpen();
		
		// *************Change Employee Size in R4B *****************************//
//		Global.dataInput.businessAccountName = "TESTAuto_PVT210909055084";	
		sfdc.accDetails.searchBusAccGlobalSearch_DataGovern(Global.dataInput.businessAccountName);
		sfdc.acc.selectChangeEmployeeSize();
		sfdc.accDetails.enterInputForChangeInEmployeeSize(Global.dataInput.secondUpdatedEmployeeSize);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch_DataGovern(Global.dataInput.businessAccountName);
		sfdc.accDetails.verifyEmployeeSizeInDetails(Global.dataInput.secondUpdatedEmployeeSize);
		//sfdc.home.closeTabIfOpen();
		//sfdc.home.logout();
        //closeBrowser();
        
    	// *************Change Contact phone no in R4B *****************************//
        //intializeChrome(false);
		//sfdc = new SFDC_AllPages();
		//sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.contacts.searchContactFromGlobalSearch(Global.dataInput.primaryContact);
		Global.dataInput.thirdContact_prepareContactData();
		sfdc.conDetails.updateContactPhoneNumber(Global.dataInput.contactPhoneNumber);
		sfdc.home.closeTabIfOpen();

		// ***************LOGIN In B2B***********************//
		sfdc.home.switchToB2BOrg();
		sfdc.login.loginToSFDC(InputData.oldOrg_username);

		// ***************Search for the same Business Account to verify the emp size which was updated in R4B***//
		sfdc.b2bAccount.searchAccountCreated(Global.dataInput.businessAccountName);

		// **************Verify Emp Size in the B2B platform//	
		sfdc.b2bAccount.verifyEmployeeSize(Global.dataInput.secondUpdatedEmployeeSize);

		// ****************Search for the contact in B2B(ITFULL) **************************//
		sfdc.b2bContacts.searchContactCreated(Global.dataInput.primaryContact);
		// ****************Verify the contact phone number in contact details************//
		sfdc.b2bContacts.verifyContactPhoneNumber(Global.dataInput.contactPhoneNumber);

		// ***************LOGOUT ITFULL***********************//
		sfdc.home.logout_FromB2B();
		closeBrowser();
		softassert.assertAll();
	}
}
