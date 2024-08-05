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
 *         Verify User is not able to edit or send email on case when case is in
 *         the queue
 *
 * 
 */
public class MP04_ServiceEnab_TC_05_CaseInQueue_UserNotAllowedToEdit_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Create a case via Email to case
	 * 
	 *                              Verify the created case and select the same
	 * 
	 *                              Verify Service Rep is not allowed to edit case
	 *                              status , priority and Origin, Status, priority,
	 *                              Type,Case Origin,Case Reason, Subject before
	 *                              taking the ownership
	 * 
	 *                              Verify user is not allowed to 'mark status as
	 *                              complete ' and change status before taking the
	 *                              ownership
	 * 
	 *                              Verify user is not allowed send an email before
	 *                              taking the ownership
	 */
	@Test
	public void test_CaseInQueue_UserNotAllowedToEdit() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();

		SFDC_EmailToCase_Lib.initiateEmail();
		sfdc.cases.verifyCasesObject();
		sfdc.cases.verifyNewCasePresent();
		sfdc.cases.selectCaseInQueue();
		sfdc.caseDetails.verifyUserIsNotAllowedToEditCaseDetails(InputData.caseOriginEmail);

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
