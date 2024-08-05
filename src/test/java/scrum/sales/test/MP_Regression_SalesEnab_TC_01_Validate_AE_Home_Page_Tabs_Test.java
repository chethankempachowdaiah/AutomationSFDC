package scrum.sales.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla, Date : 21/Sep/2020
 * 
 *         Test signin as AE and opens Home tab
 * 
 *         Verifies 3 tabs "My Performance", "My Activities", "MyAccounts" "PowerBi Reports" are
 *         present and default tab is "My Accounts" in home page
 *
 *
 *         Verifies Open tasks section under My Activities tab
 *
 *         Creates new task and verifies it appears in Open tasks section
 *
 *
 *         Validates My opportunities section under "My Accounts"
 * 
 *         Verifies My opportunities table has some data(opportunity)
 *
 *
 *         Create New Account from "My Accounts" --> Accounts Section
 * 
 *         Verifies newly created account is listed in the accounts section
 * 
 */
public class MP_Regression_SalesEnab_TC_01_Validate_AE_Home_Page_Tabs_Test extends Base {

	/**
	 * @param dataTable
	 * @throws Exception
	 * 
	 */
	@Test()
	public void test_verifyTabsinHomePage() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.home.openR4BSalesConsole();

		// ***Select Home Page and validate default and all tabs Name*****//
		sfdc.home.selectHome();
		sfdc.home.verifyActiveTabUnderHome();
		sfdc.home.verifyTabsUnderHome();

		// ***************Verify Open Task Section under My Activities********//
		sfdc.home.verifyOpenTaskSection();

		// ***************Create new task and verify********//
		sfdc.home.createNewTask();
		sfdc.home.closeTabIfOpen();
		sfdc.home.selectHome();
		sfdc.home.verifyOpenTaskSection();
		sfdc.home.verifyNewTaskInOpenTaskSection();

		// ***************Validates My opportunities section under "My
		// Accounts"********//
		sfdc.home.verifyOpportunitySection();
		sfdc.home.verifyOpportunitySectionTableData();

		// ***************Create New Account from "My Accounts"********//
		sfdc.home.verifyAccountsSection();
		sfdc.home.verifyAccountSectionTableData();
		sfdc.acc.createNewBusinessAccountLWC();
		sfdc.cba.enterBusinessAccountInfoLWC(); 
		sfdc.cba.verifyBusinessAccountCreatedLWC();
		sfdc.cc.enterNewContactInfoLWC(true);
		sfdc.home.closeTabIfOpen();

//		// ***************Create business Account********//
//		sfdc.acc.launchCreateNewBusinessAccount();
//		sfdc.cba.enterBusinessAccountInfo(false);
//		sfdc.cba.enterNewBusinessAccountInfo(false);
//		sfdc.cba.verifyBusinessAccountCreated();
//
//		// ***************Create Contact********//
//		sfdc.cc.enterCreateContactInfo(false);
//		sfdc.cc.verifyMarkPrimaryContactReadOnly();
//		sfdc.cc.verifyContactCreated();
//		sfdc.cc.clickOnNextInCreateContact();
//
//		// ***************Create Service Account********//
//		sfdc.csa.newEnterServiceAccInfo(1);
//		sfdc.csa.checkServicabilityAddServiceLocationsClickNext();
//		//sfdc.csa.clickOnNextInCreateServiceAccount();
//
//		// ***************Create Billing Account********//
//		sfdc.csa.checkCreateBillingAccount();
//		sfdc.cbia.enterBillingAccountInfo();
//		sfdc.cbia.verifyBillingAccountCreated();
//		sfdc.home.closeTabIfOpen();
		
		// ***************Verify newly created account appears in MyAccounts
		// list******//
		sfdc.home.selectHome();
		sfdc.home.verifyAccountsSection();
		sfdc.home.verifyNewAccountinMyAccountsTable();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

	}

}
