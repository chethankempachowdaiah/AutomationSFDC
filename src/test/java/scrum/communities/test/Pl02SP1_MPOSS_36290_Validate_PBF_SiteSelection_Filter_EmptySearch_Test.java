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
 *         PBF_36290_01_Agent Flow_Validate the message on the service account
 *         table when there is no records found for the search
 * 
 *         PBF_36290_02_Coustomer Flow_Validate the message on the service
 *         account table when there is no records found for the search
 * 
 *         PBF_36290_03_Agent Flow_Validate the message on the service account
 *         table when there is no records for the business account
 * 
 *         PBF_36290_04_Coustomer Flow_Validate the message on the service
 *         account table when there is no records for the business account
 * 
 *         PBF_36290_06_French Verbiage_ Agent flow_Validate the message on the
 *         service account table when there is no records found for the search
 * 
 *         PBF_36290_07_French Verbiage_ Customer flow_Validate the message on
 *         the service account table when there is no records found for the
 *         search-Mozilla
 * 
 *         PBF_36290_08_Coustomer Flow_Validate the message on the service
 *         account table when there is no records found for the search - Chrome
 * 
 *         PBE_36290_010_Validate the message on the service account table for
 *         Multiple Business Account
 * 
 */
public class Pl02SP1_MPOSS_36290_Validate_PBF_SiteSelection_Filter_EmptySearch_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 *                              Observe the service address table
	 *
	 *                              Validate the message display on the service
	 *                              address table
	 *
	 *                              Message should be display on the address table:
	 *                              No Sites found select "Add new site" above to
	 *                              add a location
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void test_validate_PBF_SiteSelection_SearchFilter() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// *******PBF_36290_01_Agent Flow_Validate the message on the service account
		// table when there is no records found for the search***//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.commPBF.navigateToSiteSelectionPage();

		// Search random string in filter by keyword
		sfdc.commPBF.searchRandomAddress();

		// Validate Message on datatable for no records found
		sfdc.commPBF.verifyNoSitesFoundMessage();
		sfdc.home.closeTabIfOpen();

		// *******PBF_36290_06_French Verbiage_ Agent flow_Validate the message on the
		// service account table when there is no records found for the search***//
		// Change User language to French
		sfdc.userProfile.changeLanguageToFrench();

		// Verify Message on datatable in French for no records found
		sfdc.home.closeTabIfOpen();
		sfdc.commPBF.navigateToSiteSelectionPage();
		sfdc.commPBF.searchRandomAddress();
		sfdc.commPBF.verifyNoSitesFoundMessage();
		sfdc.home.closeTabIfOpen();

		// Change user language back to English
		sfdc.userProfile.changeLanguageToEnglish();
		sfdc.home.closeTabIfOpen();

		// *******PBF_36290_02_Coustomer Flow_Validate the message on the service
		// account table when there is no records found for the search**///
		// Login to PBF and verify landing page
		sfdc.commPBF.loginToCommunitiesPBF(InputData_Communities.communitiesPBFCredentialsForMultiAcc);
		sfdc.commPBF.verifyPBFLandingPageRogersLogo();

		// Search random string in filter by keyword
		sfdc.commPBF.searchRandomAddress();

		// Validate Message on datatable for no records found
		sfdc.commPBF.verifyNoSitesFoundMessage();
		sfdc.comLogin.logoutFromCommunities();

		// *******PBF_36290_04_Coustomer Flow_Validate the message on the service
		// account table when there is no records for the business account**//
		sfdc.commPBF.loginToCommunitiesPBF(InputData_Communities.noServiceAddressCommUser);
		sfdc.commPBF.verifyPBFLandingPageRogersLogo();

		// Validate Message on datatable for no records found
		sfdc.commPBF.verifyNoSitesFoundMessage();
		sfdc.comLogin.logoutFromCommunities();
		sfdc.home.closeBrowser();

		// *******PBF_36290_07_French Verbiage_ Customer flow_Validate the message on
		// the service account table when there is no records found for the
		// search-Mozilla**//
		initializeMozilla(false,false);
		sfdc = new SFDC_AllPages();
		sfdc.commPBF.loginToCommunitiesPBF(InputData_Communities.communitiesPBFCredentialsForMultiAcc);
		sfdc.commPBF.changeLanguageToFrench();
		sfdc.commPBF.searchRandomAddress();

		// Validate Message on datatable for no records found
		sfdc.commPBF.verifyNoSitesFoundMessage();

		sfdc.commPBF.changeLanguageToEnglish();
		sfdc.comLogin.logoutFromCommunities();

	}

}
