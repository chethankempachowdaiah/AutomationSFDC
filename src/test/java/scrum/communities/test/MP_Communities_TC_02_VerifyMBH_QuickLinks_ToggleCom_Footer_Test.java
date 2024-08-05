package scrum.communities.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.BaseBrowser;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya
 * 
 *         MPOSS-2320 TC001[Web view][EN] Verify that the MBH quicklinks
 *         includes the communities for elligible customers.
 * 
 *         MPOSS-2321 TC001[Web View] [EN]Verify that MBH preference portal list
 *         is updated to include & toggle communities
 * 
 *         MPOSS-2780 TC001 [Web view][EN] Verify that the footer is build
 *         correctly in Communities to match mybusinesshub.rogers.com
 * 
 *         ///MP13Release tcNames
 * 
 * 
 *         MP Release Regression_CE_TC06_Validation of Footer on communities
 *         page_Google Chrome
 * 
 *         //Digital Scrum Regression TC01, TC02, TC50
 *
 *         Digital Scrum Regression_CE_TC01 _Validation of quicklinks and
 *         navigation to MBH from Communities_Google Chrome
 * 
 *         Digital Scrum Regression_CE_TC02 _Validation of quicklinks and
 *         navigation to MBH from Communities_Mozilla Firefox
 *
 *         Digital Scrum Regression_CE_TC50_Validation of Footer on communities
 *         page_Google Chrome
 * 
 */
public class MP_Communities_TC_02_VerifyMBH_QuickLinks_ToggleCom_Footer_Test extends BaseBrowser {

	/**
	 * @throws IOException
	 * 
	 *                              Validate the header of the page for the presence
	 *                              of Quicklinks,Validate that link to MBH is
	 *                              present in quicklinks and works as expected.
	 * 
	 *                              //*************Below Block commented , not part
	 *                              of digital Scrum Click on the preference tab
	 *                              "Toggle to hide and unhide the communities in
	 *                              'My Business Hub Associated Product & Services'
	 *                              & Validate in the dashboard page".Click on Add
	 *                              services .Verify that communities portal is NOT
	 *                              offered as a selectable portal in the Add
	 *                              additional services flow //*************
	 * 
	 *                              Validate the following on Footer
	 * 
	 *                              1.Present horizontal line to separate header
	 *                              from body 2.(Left align) Present copyright
	 *                              Rogers Communications stamp 3.Present link to
	 *                              Privacy Policy
	 *                              https://www.rogers.com/web/content/Privacy-CRTC
	 *                              4.Present link for Terms and Conditions
	 *                              http://www.rogers.com/web/content/support-terms?customer_type=Residential
	 *                              5.Present link to Contact Us page
	 *                              http://www.rogers.com/small-business/contact-us#/existing-services
	 *                              6.Present link to FAQs
	 *                              https://mybusinesshub.rogers.com/faqs 7.Present
	 *                              CTA to toggle language between EN & FR a.)Update
	 *                              Salesforce user record language preference upon
	 *                              toggle
	 * @throws InterruptedException
	 * 
	 * 
	 */
	@Test
	public void test_VerifyMBH_QuickLinks_ToggleCom_Footer() throws IOException, InterruptedException {

		initializeMozilla(true, true);
		
		SFDC_AllPages sfdc = new SFDC_AllPages();

		//// ---!!Digital Scrum Regression_CE_TC02 _Validation of quicklinks and
		//// navigation to MBH from Communities_Mozilla Firefox---///
		// ****LOGIN To Communitities *******//
		sfdc.comLogin.loginToCommunities();

		// **** Verify Quick Links*******//
		sfdc.comHome.verifyQuickLinks();
		sfdc.home.closeBrowser();

		//// ---!!Digital Scrum Regression_CE_TC01 _Validation of quicklinks and
		//// navigation to MBH from Communities_Google Chrome---///
		// ****LOGIN To Communitities *******//
		intializeChrome(false, true);
		sfdc = new SFDC_AllPages();
		sfdc.comLogin.loginToCommunities();

		// **** Verify Quick Links*******//
		sfdc.comHome.verifyQuickLinks();

		//// ---!!Digital Scrum Regression_CE_TC50_Validation of Footer on communities
		//// page_Google Chrome---///

		// **** Verify Footer Links*******//
		sfdc.comHome.verifyCommunityFooter();

		// *********Below Block commented as not part of Digital Scrum*********
		// *****Verify Preference tab "Toggle to hide and unhide****//
		sfdc.comHome.selectAndClickPreferencesToggle();
		sfdc.comHome.verifyCommunityBadgeNotDisplayed();
		sfdc.comHome.selectAndClickPreferencesToggle();
		sfdc.comHome.verifyAddMoreServices();
		sfdc.comHome.verifyCommunityBadgeDisplayed();

		softassert.assertAll();

	}

}
