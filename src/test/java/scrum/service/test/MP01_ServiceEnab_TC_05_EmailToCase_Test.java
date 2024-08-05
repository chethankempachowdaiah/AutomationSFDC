package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.lib_pages.SFDC_EmailToCase_Lib;

/**
 * @author Priyanka.Acharya, Date10/FEB/2020
 * 
 *         Email to Case Sanity Test(Validate Email to Case creation and
 *         communication)
 *
 * 
 */
public class MP01_ServiceEnab_TC_05_EmailToCase_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              1. Login as service rep
	 * 
	 *                              2. Email is sent to service rep for new case
	 *                              creation
	 * 
	 *                              3. Service rep status is set to availble in omni
	 *                              channel
	 * 
	 *                              4. verify case is created and validate required
	 *                              attributes of the cases
	 * 
	 *                              5. Service rep responds to the case and verify
	 *                              the reponse in the case tab
	 */
	@Test
	public void test_emailToCase() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();

		sfdc.cases.verifyCasesObject();

		sfdc.omniChannel.changeStatusToOffline();

		SFDC_EmailToCase_Lib.initiateEmail();
		sfdc.cases.verifyNewCasePresent();

		// sfdc.omniChannel.changeStatusToAvailble();
		// sfdc.caseDetails.validateEmailToCaseDetails(true);

		sfdc.cases.selectCaseInQueue();
		sfdc.caseDetails.changeCaseOwner();
		sfdc.caseDetails.validateEmailToCaseDetails(false);

		sfdc.caseRelated.validateCaseEmails();
		sfdc.caseRelated.agentReply();
		sfdc.caseRelated.verifyAgentReply();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();

	}
}
