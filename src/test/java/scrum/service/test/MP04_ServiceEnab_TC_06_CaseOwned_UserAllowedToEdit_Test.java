package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.lib_pages.SFDC_EmailToCase_Lib;

/**
 * @author Priyanka.Acharya, Date: 19/MAY/2020
 * 
 *         Verify User is able to edit or send email on case when take the
 *         ownership
 *
 * 
 */
public class MP04_ServiceEnab_TC_06_CaseOwned_UserAllowedToEdit_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Create a case via Email to case
	 * 
	 *                              Verify the created case and select the same and
	 *                              take the ownership
	 * 
	 *                              Verify Service Rep is allowed to edit case
	 *                              status , priority and Origin, Status, priority,
	 *                              Type,Case Origin,Case Reason, Subject after
	 *                              taking the ownership
	 * 
	 *                              Verify user is allowed to 'mark status as
	 *                              complete ' and change status after taking the
	 *                              ownership
	 * 
	 *                              Verify user is allowed send an email after
	 *                              taking the ownership
	 */
	@Test
	public void test_CaseOwned_UserAllowedToEdit() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();

		SFDC_EmailToCase_Lib.initiateEmail();
		sfdc.cases.verifyCasesObject();
		sfdc.cases.verifyNewCasePresent();
		sfdc.cases.selectCaseInQueue();
		sfdc.caseDetails.changeCaseOwner();
		sfdc.caseDetails.validateEmailToCaseDetails(false);
		sfdc.caseDetails.verifyUserIsAllowedToEditCaseDetails();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
