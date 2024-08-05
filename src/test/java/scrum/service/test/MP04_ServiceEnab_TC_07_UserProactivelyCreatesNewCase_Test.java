package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date: 29/MAY/2020
 * 
 *         Verify User proactively create a new case
 * 
 *         MP Release Regression_Service_TC31_Create a new case for a community
 *         user_English
 *
 * 
 */
public class MP04_ServiceEnab_TC_07_UserProactivelyCreatesNewCase_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Login as Service , Select Cases, Select new Case
	 * 
	 *                              select case status, priority, type, sub-type,
	 *                              reason, origin, subject.
	 * 
	 *                              Enter description and internal comments
	 * 
	 *                              Click on Next Button
	 * 
	 *                              Verify Case details for Proactively Created case
	 * 
	 *                              ////--------------------- MP Release
	 *                              Regression_Service_TC31_Create a new case for a
	 *                              community user_English--------///
	 * 
	 *                              Service log in
	 * 
	 *                              Create a new case
	 * 
	 *                              Select Tier 1 Tech Support
	 * 
	 *                              Search for contact which is a community user,
	 *                              Case origin: Proactive. Fill in case subject
	 * 
	 *                              Validate case created
	 * 
	 *                              Validate open case auto response
	 * 
	 * 
	 */
	@Test
	public void test_UserProactivelyCreatesNewCase() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.cases.selectCaseCategory(Constants.SERVICE_TYPE_SHEET);		
		sfdc.cases.createCaseProactively();
		sfdc.caseDetails.validate_ProactivelyCreatedCaseDetails();
		sfdc.home.closeTabIfOpen();

		//// --------------------- MP Release Regression_Service_TC31_Create a new case
		//// for a community user_English--------/// To be executed once communities
		//// issue is resolved

//		sfdc.cases.selectCaseCategory(Constants.TECH_SUPPORT_SHEET);
//		sfdc.cases.chooseCaseContactAsCommunityUser();
//		sfdc.cases.createCaseProactively();
//		sfdc.caseDetails.validate_Tier1ProactiveCaseDetails();
//		sfdc.mailinatorPage.verifyEmailAtMailinator();

		sfdc.home.logout();
		softassert.assertAll();
	}
}
