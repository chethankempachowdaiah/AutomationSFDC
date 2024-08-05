package scrum.communities.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.BaseBrowser;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla Created On 19-Oct-2020
 * 
 *         ////-----//// Login to Communities portal
 * 
 *         Validate that the customer lands on the page with communities badge
 * 
 *         Click on Create new case
 * 
 *         ////-----//// Create Case with upload attachment
 * 
 *         Go to Case details and verify attachment exist
 * 
 *         Delete One attachment and verify deleted successfully ////-----////
 * 
 *         ////-----//// Open the same case in Salesforce(Service Agent)
 * 
 *         Verify the attachments added in communities are visible in Salesforce
 *         ////-----////
 * 
 *         ////--- MP Release Regression_CE_TC15_Verify customers and agents are
 *         able to upload the attachment to existing cases_Google Chrome--////
 * 
 *         Login to communities
 * 
 *         Add attachments to exiting case through Case Details tab
 * 
 *         Validate the attachments uploaded are visible in Salesforce(Service
 *         Agent) under same case
 * 
 *         ////-----////
 * 
 *         ////---MP Release Regression_CE_TC16_Verify that customer is not able
 *         to delete attachments uploaded by Service Agent_Google Chrome--////
 * 
 *         Login to communities
 * 
 *         Create simple case without attachment
 * 
 *         Login to Salesforce as Service Agent, upload attachment to same case
 * 
 *         Login back to communities
 * 
 *         Validate the attachments uploaded in Salesforce cannot be deleted in
 *         communities ////-----////
 * 
 */
public class MP_Communities_TC_03_VerifyCaseAttachment_Upload_Delete_Test extends BaseBrowser {

	/**
	 * @throws IOException
	 * @throws InterruptedException 
	 * 
	 */
	@Test
	public void test_Communities_VerifyCaseAttachment_Upload_Delete() throws IOException, InterruptedException {

		intializeChrome(false, false);
		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ****LOGIN To Communitities******//
		sfdc.comLogin.loginToCommunities();

		// ***Verify Community Badge *******//
		//Communityh badge is not thre. Clicking on Dshboard signs you out
		//sfdc.comHome.verifyCommunityBadge();

		// ****Create Case with Attachment and validate it can be deleted successfully
		// from Case Details Page***///
		sfdc.comMyBusCases.navigateToCreateCasePage();
		sfdc.comMyBusCases.createServiceCaseDataCentreOthers(true);
		sfdc.comCaseDetails.myBusinessCasesVerifyAndDeleteAttachment();
		sfdc.comLogin.logoutFromCommunities();

		// ***** Open sf in new browser and validate case attachment
		
		sfdc.home.navigateURL();
		sfdc.login.loginToSFDC(sf.dataInput.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.cases.verifyCasesObject();
		sfdc.cases.searchCaseGloballyAndOpen();
		sfdc.caseRelated.verifyCaseAttachments();
		sfdc.home.logout();

		// ---!! MP Release Regression_CE_TC15_Verify customers and agents are able to
		// upload the attachment to existing cases_Google Chrome--!!//

		// ***LOGIN to Communities to add attachment to existing case****//

		sfdc.comLogin.loginToCommunities();
		sfdc.comHome.openCommunityCases();
		sfdc.comCaseDetails.myBusinessCasesVerifyAndDeleteAttachment();
		sfdc.comHome.openCommunityCases();
		sfdc.comCaseDetails.myBusinessCasesUploadAttachmentInExistingCase();
		sfdc.comLogin.logoutFromCommunities();


		// ***** Open sf in new browser and validate case attachment added to existing
		// case
		sfdc.home.navigateURL();
		
		sfdc.login.loginToSFDC(sf.dataInput.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.cases.verifyCasesObject();
		sfdc.cases.searchCaseGloballyAndOpen();
		sfdc.caseRelated.verifyCaseAttachments();
		sfdc.home.logout();
		
		// --!!!MP Release Regression_CE_TC16_Verify that customer is not able to delete
		// attachments uploaded by Service Agent_Google Chrome!!--//

		// ****Create case in communities****//

		sfdc.comLogin.loginToCommunities();
		sfdc.comMyBusCases.navigateToCreateCasePage();
		sfdc.comMyBusCases.createServiceCaseDataCentreOthers(false);
		sfdc.comLogin.logoutFromCommunities();
		
		// ****Login to Salesforce as Service Agent to upload attachment in Case****//
		sfdc = new SFDC_AllPages();
		sfdc.home.navigateURL();
		sfdc.login.loginToSFDC(sf.dataInput.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.cases.verifyCasesObject();
		sfdc.cases.searchCaseGloballyAndOpen();
		sfdc.caseRelated.UploadCaseAttachments();

		// Enable customer view for file uploaded to be able to view file in communities
		sfdc.caseRelated.enableCustomerViewForAttachment();
		sfdc.home.logout();
		
		// ****Open same case in communities and validate user is not able to delete
		// attachment by Service Agent in Salesforce****//
		sfdc = new SFDC_AllPages();

		sfdc.comLogin.loginToCommunities();
		sfdc.comCaseDetails.myBusinessCasesVerifyAttachmentNotDeletable();
		
		softassert.assertAll();


	}

}
