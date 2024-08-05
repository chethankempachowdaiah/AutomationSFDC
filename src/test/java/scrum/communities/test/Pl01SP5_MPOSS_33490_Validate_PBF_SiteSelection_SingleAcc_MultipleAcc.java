package scrum.communities.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla
 * 
 *         MPOSS-33490_TC 01_ Validate PBF-SiteSelection|MultiAccount
 *         filter_Single Business Account
 * 
 *         MPOSS-33490_TC 03_ Validate PBF-SiteSelection|MultiAccount
 *         filter_Multiple Business Account-Default Account in dropdown
 * 
 *         MPOSS-33490_TC 04_ Validate PBF-SiteSelection|MultiAccount
 *         filter_Multiple Business Account-Available Addresses
 * 
 * 
 */
public class Pl01SP5_MPOSS_33490_Validate_PBF_SiteSelection_SingleAcc_MultipleAcc extends Base {

	/**
	 * @throws IOException
	 * 
	 *                              MPOSS-33490_TC 01_ Validate
	 *                              PBF-SiteSelection|MultiAccount filter_Single
	 *                              Business Account 1. Login to
	 *                              https://itdevstage-r4b.cs142.force.com/s/Persona-buy-flow
	 * 
	 *                              2. On this page, check the drop down for
	 *                              selecting business account
	 * 
	 *                              3. Validate the available addresses for the
	 *                              single account
	 * 
	 *                              MPOSS-33490_TC 03_ Validate
	 *                              PBF-SiteSelection|MultiAccount filter_Multiple
	 *                              Business Account-Default Account in dropdown 1.
	 *                              Login to
	 *                              https://itdevstage-r4b.cs142.force.com/s/Persona-buy-flow
	 *                              with user having Multiple account
	 * 
	 *                              2. Validate the Heading, sub heading and drop
	 *                              down
	 * 
	 *                              3. Drop down should be there with PRIMARY
	 *                              ACCOUNT AS DISPLAYED BY DEFAULT.
	 * 
	 *                              MPOSS-33490_TC 04_ Validate
	 *                              PBF-SiteSelection|MultiAccount filter_Multiple
	 *                              Business Account-Available Addresses 1. Login to
	 *                              https://itdevstage-r4b.cs142.force.com/s/Persona-buy-flow
	 *                              with user having Multiple account
	 *
	 *                              2. Select any account from the drop down.
	 * 
	 *                              3. Validate the addresses available in the
	 *                              section below.
	 * 
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void test_validate_PBF_SiteSelection() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ***********MPOSS-33490_TC 04_ Validate PBF-SiteSelection|MultiAccount
		// filter_Multiple Business Account-Available Addresses
		// ***********MPOSS-33490_TC 03_ Validate PBF-SiteSelection|MultiAccount
		// filter_Multiple Business Account-Default Account in dropdown
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.home.closeTabIfOpen();
		sfdc.home.openR4BSalesConsole();

		// Search for contact to extract related business accounts
		sfdc.contacts.searchContactGlobalSearchByEmail(sf.commData.multipleBusAccountCommUserEmail);
		sfdc.conDetails.extractBusinessAccountsFromContact();
		sfdc.home.closeTabIfOpen();

		// Extract All Premises addresses from service accounts
		sfdc.premises.extractRelatedPremisesForAllBusAcc();
		sfdc.home.logout();

		// Login to PBF and verify landing page
		sfdc.commPBF.loginToCommunitiesPBF(sf.commData.communitiesPBFCredentialsForMultiAcc);
		sfdc.commPBF.verifyPBFLandingPage();

		// Verify Business Account Dropdown Presence
		sfdc.commPBF.verifyBusinessAccDropdownPresent();

		// Verify Primary Account value is set by default in dropdown
		sfdc.commPBF.verifyBusAccDefaultValue();

		// Verify Expected Business Accounts are present in dropdown
		sfdc.commPBF.verifyBusinessAccountValues();

		// Verify service addresses for each business account
		sfdc.commPBF.verifyServiceAddressesForAllBusAccounts();
		sfdc.comLogin.logoutFromCommunities();

		// ********************MPOSS-33490_TC 01_ Validate
		// PBF-SiteSelection|MultiAccount filter_Single Business Account*************/
		navigateURL();
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.home.closeTabIfOpen();
		sfdc.home.openR4BSalesConsole();

		// Search for contact to extract related business account
		sfdc.contacts.searchContactGlobalSearchByEmail(InputData_Communities.singleBusAccountCommUserEmail);
		sfdc.conDetails.extractBusinessAccountsFromContact();
		sfdc.home.closeTabIfOpen();

		// Extract All Premises addresses from service accounts
		sfdc.premises.extractRelatedPremisesForAllBusAcc();
		sfdc.home.logout();

		// Login to PBF and verify landing page
		sfdc.commPBF.loginToCommunitiesPBF(InputData_Communities.communitiesPBFCredentialsForSingleAcc);
		sfdc.commPBF.verifyPBFLandingPage();

		// Verify Business Account Dropdown Absence
		sfdc.commPBF.verifyBusinessAccDropdownNotPresent();

		// Verify service addresses for single business account
		sfdc.commPBF.verifyServiceAddressesForSingleBusAcc();
		sfdc.comLogin.logoutFromCommunities();
	}

}
