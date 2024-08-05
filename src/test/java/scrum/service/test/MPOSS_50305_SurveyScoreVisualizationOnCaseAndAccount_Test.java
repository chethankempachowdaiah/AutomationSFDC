package scrum.service.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Service;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Robin.Mangla, Date 21/DEC/2021
 * 
 *         Validate Relationship Score for Business Account & TouchPoint Score for Customer Case is shown
 *
 */
public class MPOSS_50305_SurveyScoreVisualizationOnCaseAndAccount_Test extends Base {

	/**
	 * @throws Exception
	 * 
	 * Login as Service
	 * 
	 * Open a business account with relationship score
	 * 
	 * validate relationship score is shown
	 * 
	 * Open a Customer Case with TouchPoint Score
	 * 
	 * validate TouchPoint Score is shown
	 * 
	 */
	@Test(groups= {"sanity"})
	
	public void test_SurveyScoreVisualizationOnCaseAndAccount() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		
		// Validate Relationship Score shown on Business Account, MPOSS-52467, MPOSS-52476
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
//		sfdc.accDetails.searchBusAccGlobalSearch(InputData_Service.relationshipScoreBusinessAccount);
		sfdc.accDetails.validateRelationshipScore();
		sfdc.home.closeTabIfOpen();
		
		//Validate Touch Point Score shown on Case Record, MPOSS-52468, MPOSS-52478
//		sfdc.home.openServiceConsole();
		sfdc.home.openSurveyResponses();
//		sfdc.home.closeTabIfOpen();
//		sfdc.caseDetails.searchCaseFromGlobalSearch(InputData_Service.touchPointScoreCustomerCase);
		sfdc.caseRelated.validateTouchPointScoreAndDetails();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();	
	}
}
