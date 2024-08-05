package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Sakshi
 * 
 *         MP Release Regression_Service_TC30_Service case cannot be edited when
 *         case is in the queue for service profile_English
 * 
 * 
 * 
 *         MP Release Regression_Service_TC32_ Validate Case with In progress
 *         status cannot be changed to New_English
 * 
 */

public class MP14_ServiceEnab_CaseInQueue_NotEditable_InProgressCase_NotAllowedToNew_Test extends Base {
	/**
	 * @throws IOException
	 * 
	 * 
	 *                     ////--------------------- MP Release
	 *                     Regression_Service_TC30_Service case cannot be edited
	 *                     when case is in the queue for service profile_English
	 *                     ------------------------////
	 * 
	 *                     Login As Service
	 * 
	 *                     Open a case which is in the queue.
	 * 
	 *                     Edit case: Status, Account, Case Origin, Case Reason,
	 *                     Case Contact, Priority, Subject, Description, Product
	 *                     family, category, class, sub class, parent case, External
	 *                     Ticket source and External Ticket reference and save
	 * 
	 *                     Compose an email and send
	 * 
	 *                     Click on change owner and take the ownership
	 * 
	 *                     Edit case: Status, Account, Case Origin, Case Reason,
	 *                     Case Contact, Priority, Subject, Description, Product
	 *                     family, category, class, sub class, parent case, External
	 *                     Ticket source and External Ticket reference and save
	 * 
	 *                     Compose an email and send
	 * 
	 * 
	 * 
	 *                     ////--------------------- MP Release
	 *                     Regression_Service_TC32_ Validate Case with In progress
	 *                     status cannot be changed to New_English
	 *                     ---------------------////
	 * 
	 *                     Service log in and open a Tier 1 Support case.
	 * 
	 *                     Case status is: In Progress; Awaiting Customer Response;
	 *                     Awaiting Problem Ticket; Awaiting monitoring; Awaiting
	 *                     Third Party
	 * 
	 *                     Change Case status to New
	 * 
	 * 
	 */

	@Test
	public void test_ServiceCaseInQueue_NotEditable_InProgressCase_NotAllowedToNew()
			throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();

		//// --------------------- MP Release Regression_Service_TC30_Service case
		//// cannot be edited when case is in the queue for service profile_English
		//// ------------------------////

		// Default queue owner is not allowed to edit the case details
		sfdc.cases.selectCaseCategory(Constants.SERVICE_TYPE_SHEET);
		sfdc.cases.createCaseProactively();
		sfdc.caseDetails.changeCaseOwnerDefaultQueue();
		sfdc.caseDetails.verifyUserIsNotAllowedToEditCaseDetails(InputData.caseOriginPhone);

		// User is allowed to change the case details and send email.
		sfdc.caseDetails.changeCaseOwner();
		// sfdc.caseDetails.changeCaseOwnerToUser();
		sfdc.caseDetails.verifyUserIsAllowedToEditCaseDetails();
		sfdc.home.closeTabIfOpen();

		//// --------------------- MP Release Regression_Service_TC32_ Validate Case
		//// with In progress status cannot be changed to New_English
		//// ---------------------////

		// Search case having status as 'In Progress' and Try to change the status of
		// case to 'New' and validate error message
		if (sfdc.cases.searchTier1CaseByStatus(InputData.caseStatusInProgress))
			sfdc.caseDetails.verifyCaseStatusCannotBeChangedToNew();
		sfdc.home.closeTabIfOpen();

		// Search case having status as 'AwaitingCustomerResponse' and Try to change the
		// status of case to 'New' and validate error message
		if (sfdc.cases.searchTier1CaseByStatus(InputData.caseStatusAwaitingCustomerResponse))
			sfdc.caseDetails.verifyCaseStatusCannotBeChangedToNew();
		sfdc.home.closeTabIfOpen();

		// Search case having status as 'AwaitingProblemTicket' and Try to change the
		// status of case to 'New' and validate error message
		if (sfdc.cases.searchTier1CaseByStatus(InputData.caseStatusAwaitingProblemTicket))
			sfdc.caseDetails.verifyCaseStatusCannotBeChangedToNew();
		sfdc.home.closeTabIfOpen();

		/// Search case having status as 'AwaitingMonitoring' and Try to change the
		/// status of case to 'New' and validate error message
//		if (sfdc.cases.searchTier1CaseByStatus(InputData.caseStatusAwaitingMonitoring))
//			sfdc.caseDetails.verifyCaseStatusCannotBeChangedToNew();
//		sfdc.home.closeTabIfOpen();

		// Search case having status as 'AwaitingThirdParty' and Try to change the
		// status of case to 'New' and validate error message
//		if (sfdc.cases.searchTier1CaseByStatus(InputData.caseStatusAwaitingThirdParty))
//			sfdc.caseDetails.verifyCaseStatusCannotBeChangedToNew();
//		sfdc.home.closeTabIfOpen();

		sfdc.home.logout();
		softassert.assertAll();

	}

}
