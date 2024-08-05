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
 *         MP Release Regression_Sales_TC03_Validate Account sync Positive flow
 *         from R4B to B2B.
 * 
 *         MP Release Regression_Sales_TC04_Validate Account sync Positive flow
 *         from B2B to R4B.
 * 
 *         MP Release Regression_Sales_TC06_Validate status update sync when
 *         Account becomes Inactive in B2B
 * 
 *         MP Release Regression_Sales_TC07_Validate Contact sync Positive flow
 *         from B2B to R4B.
 * 
 *         MP Release Regression_Sales_TC08_Validate Contact sync Positive flow
 *         from R4B to B2B.
 * 
 *         MP Release Regression_Sales_TC21_Validate Employee size populated
 *         during account creation flow is getting sync from R4B to B2B.
 * 
 *         MP Release Regression_Sales_TC22_Validate Employee size populated
 *         during account creation flow is getting sync from B2B to R4B.
 *         
 *         MPOSS 25459_TC01_Validate As AE , when user create new Business Account , in "Country" drop down user can see "United states" instead of "US"
 *         
 *         MP Release Regression_Sales_TC03_Validate Account sync Positive flow from R4B to B2B.
 *         
 *         Validate Account is be created in B2B and synced into R4B successfully.
 * 
 * 
 */
public class MP_Regression_SalesEnab_TC_05_R4B_B2B_R4B_Account_Contact_EmpSize_AccStatusUpdate_ValidationTest
		extends BaseBrowser {

	/**
	 * ******************************************************************************************************************
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              1. Login with an AE profile in ITQA. 2. Create a
	 *                              new Business Account and a contact. 3. Switch to
	 *                              DG profile and Activate it. 4. Login with an CSM
	 *                              admin profile in B2B. 5. Search for the R4B
	 *                              created account in B2B and verify the emp size.
	 *                              6. Search for the R4B created contact in B2B 7.
	 *                              Create the business account and the contact in
	 *                              B2B 8. Verify whether it's created or not 9.
	 *                              Flip the account status from active to inactive
	 *                              9. Login with an AE profile in ITQA. 10. Search
	 *                              for the R4B created account in B2B and verify
	 *                              the updated account status 11. Verify the the
	 *                              emp size which was entered in B2B during the
	 *                              account creation 11. Search for the R4B created
	 *                              contact in B2B 12. Logout of R4B
	 *************************************************************************************************************************
	 * 
	 */

	@Test
	public void test_R4B_B2B_R4B_TC_05_Account_Contact_EmpSize_AccStatusUpdate()
			throws IOException, InterruptedException {

		intializeChrome(false);
		SFDC_AllPages sfdc = new SFDC_AllPages();
	

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		
		// ***************Create business Account********//
		sfdc.acc.createNewBusinessAccountLWC();
		sfdc.cba.enterBusinessAccountInfoLWC(); 
		sfdc.cba.verifyBusinessAccountCreatedLWC();

		// ***************Create Contact********//
		sfdc.cc.enterNewContactInfoLWC(true);
		sfdc.home.selectHome();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		// *********Login as Data Governance and Approve business Account******//
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		

		// ***************LOGIN TO ITFULL***********************/
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
		sfdc.home.closeTabIfOpen();
		sfdc.home.switchToB2BOrg();
		sfdc.login.loginToSFDC(InputData.oldOrg_username);

		// ***************Search the Business Account which was created in B2b*******//
		sfdc.b2bAccount.searchAccountCreated(sf.dataInput.tempBusinessAccountName);
		
		// ***************Search the Business Account in B2B which was created in R4B *******//
		//Test Case- Account is be created in R4B and synced into B2B successfully.
		sfdc.b2bAccount.searchAccountCreatedValidation(sf.dataInput.tempBusinessAccountName);

		// **************Verify EmpSize***********************************************//
		sfdc.b2bAccount.verifyEmployeeSize(sf.dataInput.numberOfEmployees);

		// ***************Search the created contact of R4B in B2B*******//
		sfdc.b2bAccount.homeTabClick();
		sfdc.b2bContacts.searchContactCreated(sf.dataInput.primaryContact);

		// ***************Creating new Business Account in B2B*******//
		sfdc.b2bAccount.newAccount();
		sfdc.b2bAccount.enterAccountDetails(sf.dataInput.secondBusinessAccountName, sf.dataInput.b2b_secondMalID);

		// ***************Verify New Business Account got created in B2B*******//
		sfdc.b2bAccount.verifyAccountCreated();

		// **************Create the contact with status as active************************//
		sfdc.b2bContacts.createNewContact();
		sf.dataInput.secondContact_prepareContactData();
		sfdc.b2bContacts.enterContactDetails();

		// **************Verify contact is created**********************************//
		sfdc.b2bContacts.verifyContactCreated();

		// ***************flip the account status in B2B**********************************//
		sfdc.b2bAccount.searchAccountCreated(sf.dataInput.secondBusinessAccountName);
		sfdc.b2bAccount.flipActiveStatus();
		sfdc.home.logout_FromB2B();
		if (sf.dataInput.env.equals("ITQATEST") )	
			sf.seleU.openURL("https://r4b--itqatest.my.salesforce.com/");

		// ***************LOGIN In R4B AS AE***********************//
		//intializeChrome(false);
		//sfdc = new SFDC_AllPages();
		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.closeTabIfOpen();
	
		// **********Validation- Account is be created in B2B and synced into R4B successfully.and Search Account in R4B***********//
		sfdc.accDetails.b2BSyncR4BSearchBusAccGlobalSearch(sf.dataInput.secondBusinessAccountName);
		
		//sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.parentAccountName);
		// **************Search Account in R4B**********************************//
		//sfdc.accDetails.searchBusAccGlobalSearch_DataGovern(sf.dataInput.secondBusinessAccountName);
		// **************Verify Emp Size in R4B**********************************//
		sfdc.accDetails.verifyEmployeeSizeInDetails(sf.dataInput.numberOfEmployees);

		// **************Verify updated Account status inR4B*******************************//
		sfdc.accDetails.verifyAccountStatusInDetails();
		sfdc.home.closeTabIfOpen();

		// **************Search Contact to check whether its created or not in R4B ******************//
		sfdc.contacts.searchContactFromGlobalSearch(sf.dataInput.accountContact);
		sfdc.home.closeTabIfOpen();

		// ***************LOGOUT ITFULL***********************//
		sfdc.home.logout();
		softassert.assertAll();

	}
}
