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
 *         PBF_36347_TC01_Internal User_Verify Next button right alignment
 * 
 *         PBF_36347_TC02_Internal User_Verify Next button name
 * 
 *         PBF_36347_TC03_Internal User_Verify Next button navigation
 * 
 *         PBF_36347_TC04_Internal User_Verify Next button enable and disable
 *         properties
 * 
 *         PBF_36347_TC05_External User_Verify Next button right alignment
 * 
 *         PBF_36347_TC06_External User_Verify Next button name
 * 
 *         PBF_36347_TC07_External User_Verify Next button navigation
 * 
 *         PBF_36347_TC08_External User_Verify Next button enable and disable
 *         properties
 * 
 *         PBF_36347_TC10_French_ Internal User_Verify Next button properties
 * 
 * 
 * 
 */
public class Pl02SP1_MPOSS_36347_Validate_PBF_SiteSelection_NextButton_Internal_ExternalUser extends Base {

	/**
	 * @throws IOException
	 * 
	 * 
	 *                              PBF_36347_TC01_Internal User_Verify Next button
	 *                              right alignment 1. Login as AE in PBF
	 * 
	 *                              2. Navigate to Site selection screen
	 * 
	 *                              3. Observe Next button alignment - Next button
	 *                              should be right aligned
	 * 
	 *                              PBF_36347_TC02_Internal User_Verify Next button
	 *                              name
	 * 
	 *                              1. Login as AE in PBF
	 * 
	 *                              2. Navigate to Site selection screen
	 * 
	 *                              3. Observe Next button Name - 'Next'
	 * 
	 *                              PBF_36347_TC03_Internal User_Verify Next button
	 *                              navigation
	 * 
	 *                              1. Login as AE in PBF
	 * 
	 *                              2. Navigate to Site selection screen
	 * 
	 *                              3. Select any address and Click on the Next
	 *                              button
	 * 
	 *                              4. Navigated to the shopping cart selection
	 *                              screen
	 * 
	 *                              PBF_36347_TC04_Internal User_Verify Next button
	 *                              enable and disable properties
	 * 
	 *                              PBF_36347_TC10_French_ Internal User_Verify Next
	 *                              button properties
	 * 
	 *                              1. Internal user login should be successful
	 * 
	 *                              2. Navigated to Site selection screen
	 * 
	 *                              3. Next button name translated in French as per
	 *                              spec
	 * 
	 * 
	 *                              PBF_36347_TC05_External User_Verify Next button
	 *                              right alignment
	 * 
	 *                              PBF_36347_TC06_External User_Verify Next button
	 *                              name
	 * 
	 *                              PBF_36347_TC07_External User_Verify Next button
	 *                              navigation
	 * 
	 *                              PBF_36347_TC08_External User_Verify Next button
	 *                              enable and disable properties
	 * 
	 *                              PBF_36347_TC10_French_ Internal User_Verify Next
	 *                              button properties
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void test_validate_PBF_SiteSelection_nextButton() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ***********
		// Login to PBF and verify landing page
		sfdc.commPBF.loginToCommunitiesPBF(InputData_Communities.externalUserPBF);
		sfdc.commPBF.verifyPBFLandingPageRogersLogo();

		// Verify next button is disabled
		sfdc.commPBF.verifyNextButtonDisabled();

		// Verify next button is right aligned
		sfdc.commPBF.verifyNextButtonRightAlligned();

		// Select first address from list
		sfdc.commPBF.selectAddress();

		// Verify Next button presence, enabled and its text
		sfdc.commPBF.verifyNextButtonPresenceAndText();
		sfdc.commPBF.verifyNextButtonEnabled();

		// Click on next and validate landing page
		sfdc.commPBF.verifyNextButtonNavigation();
		// Logout
		sfdc.comLogin.logoutFromCommunities();

		// Login to SFDC and land on site selection page
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.commPBF.navigateToSiteSelectionPage();

		// Verify next button is disabled
		sfdc.commPBF.verifyNextButtonDisabled();

		// Verify next button is right aligned
		sfdc.commPBF.verifyNextButtonRightAlligned();

		// Select first address from list
		sfdc.commPBF.selectAddress();

		// Verify Next button presence, enabled and its text
		sfdc.commPBF.verifyNextButtonPresenceAndText();
		sfdc.commPBF.verifyNextButtonEnabled();

		// Click on next and validate landing page
		sfdc.commPBF.verifyNextButtonNavigation();
		sfdc.home.closeTabIfOpen();

		// Change User language to French
		sfdc.userProfile.changeLanguageToFrench();

		// Verify Next button presence, enabled and its text
		sfdc.home.closeTabIfOpen();
		sfdc.commPBF.navigateToSiteSelectionPage();
		sfdc.commPBF.verifyNextButtonPresenceAndText();

		// Change user language back to English
		sfdc.userProfile.changeLanguageToEnglish();
		sfdc.home.closeTabIfOpen();
		sfdc.home.closeBrowser();

		initializeMozilla(false,false);
		sfdc = new SFDC_AllPages();
		sfdc.commPBF.loginToCommunitiesPBF(InputData_Communities.externalUserPBF);
		sfdc.commPBF.verifyPBFLandingPageRogersLogo();

		// Verify next button is disabled
		sfdc.commPBF.verifyNextButtonDisabled();

		// Verify next button is right aligned
		sfdc.commPBF.verifyNextButtonRightAlligned();

		// Select first address from list
		sfdc.commPBF.selectAddress();

		// Verify Next button presence, enabled and its text
		sfdc.commPBF.verifyNextButtonPresenceAndText();
		sfdc.commPBF.verifyNextButtonEnabled();

	}

}
