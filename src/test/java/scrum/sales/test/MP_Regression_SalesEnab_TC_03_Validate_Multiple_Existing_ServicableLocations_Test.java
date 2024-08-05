package scrum.sales.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Pankaj.Agarwal,
 * 
 *         MP Release Regression_Sales_TC29_Validate user is able to add
 *         Multiple serviceable location to Premise. MP Release
 * 
 *         Regression_Sales_TC30_Validate user is able to add Existing
 *         serviceable location to Premise for existing service account.
 *
 */
public class MP_Regression_SalesEnab_TC_03_Validate_Multiple_Existing_ServicableLocations_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              TC- 29 1. Create Business Account and contact.
	 *                              2. User is able to Click on New button on
	 *                              account page, below account type is visible for
	 *                              selection -Business, Service and Billing. 3.
	 *                              Able to select service account and able to hit
	 *                              on next, redirected to ""Select Service
	 *                              Accounts"" page. 4. User is able to chose parent
	 *                              account which is mandatory field and able to
	 *                              copy business address 5. User is able to enter
	 *                              Service account name and able to click on Hit 6.
	 *                              Service Account confirmation page is displayed.
	 *                              and able to Hit ""Next"" 7. Account Executive
	 *                              Service account is created , user is able to see
	 *                              page for ""Check Serviceability"" page 8 .When
	 *                              user types in "Vaughan" in the City filter ,
	 *                              table is populates all the serviceable locations
	 *                              present in city of Vaughan. 9. Able to select
	 *                              any Three locations from the table and hit "Add
	 *                              Service Location". 10. message "Serviceable
	 *                              locations are associated with parent premise" is
	 *                              visible 11. able to Refresh the tab and same
	 *                              serviceable location is seen under related TAB.
	 * 
	 *                              TC - 30 12.User is redirected to Premises
	 *                              details page after hitting the premises hyper
	 *                              link under details section of the account
	 *                              13.Check Serviceability"" page is displayed to
	 *                              the user. 14.When user types in ""Vaughan"" in
	 *                              the City filter , table is populates all the
	 *                              serviceable locations present in city of
	 *                              Vaughan. 15. User is able to select location
	 *                              from the table and hit ""Add Service Location"".
	 *                              16. Message ""Serviceable locations are
	 *                              associated with parent premise"" is visible
	 *                              17.User is able to refresh the TAB 18.Verify
	 *                              that above selected location is not presented in
	 *                              table after hitting Check Serviceability""
	 *                              button "
	 */
	@Test
	public void test_Validate_Multiple_Existing_ServicableLocations_Premises()
			throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.userid_ae);
		// sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// ***************Create business Account*******************************
		sfdc.acc.createNewBusinessAccount();
		sfdc.cba.enterBusinessAccountInfo(false);
		sfdc.cba.verifyBusinessAccountCreated();

		// ***************Create Contact****************************************
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreatedUpdated();
		sfdc.cc.clickOnNextInCreateContact();

		// ***************Create Srevice Account****************************************
		//sfdc.home.closeTabIfOpen();
		// Global.dataInput.tempBusinessAccountName = "ABQAAuto200713065266";
		//sfdc.acc.createNewServiceAccount();
		sfdc.csa.enterServiceAccountInfo(2);
		// sfdc.csa.enterServiceAccountInfoWithParameter(1,
		// Global.dataInput.tempBusinessAccountName);

		// ***************Verify service account got created and add multiple location
		// in servicability and verify in******
		// *************Verify the added service location in service account
		// details**********
		//sfdc.csa.verifyServiceAccountCreated();
		sfdc.csa.checkServicability_AddMultipleServiceLocations();
		sfdc.csa.clickOnPremisesLink();

		// *************Add service location in premise
		// details****************************
//		sfdc.csa.checkServicability_AndAddMultipleServiceLocationsInPremise();
//
		// **********Log
		// Out***********************************************************************
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();

	}
}
