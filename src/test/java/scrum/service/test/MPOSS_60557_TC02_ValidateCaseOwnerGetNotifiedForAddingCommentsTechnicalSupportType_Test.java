package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Robin.Mangla, Date: 30/MAR/2022
 * 
 *         validate Case owner get notified when case is modified by another user
 * 
 */
public class MPOSS_60557_TC02_ValidateCaseOwnerGetNotifiedForAddingCommentsTechnicalSupportType_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 */
	@Test
	public void test_ValidateCaseOwnerGetNotifiedForAddingCommentsTechnicalSupportType() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.cases.selectCaseCategory(Constants.TECH_SUPPORT_SHEET);
		sfdc.cases.createCaseProactively();
		sfdc.caseDetails.validate_ProactivelyCreatedTechnicalSupportCaseDetails();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//add comments
		sfdc.login.loginToSFDC(InputData.Profile_SystemAdmin);
		sfdc.home.closeTabIfOpen();
		sfdc.caseDetails.searchCaseFromGlobalSearch(InputData.caseNumber);
		sfdc.caseDetails.editCommentsField();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//validate case owner notified
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.caseDetails.searchCaseFromGlobalSearch(InputData.caseNumber);
		sfdc.caseDetails.validateCaseOwnerNotified();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
