package scrum.service.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Service;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Robin.Mangla, Date 14/FEB/2022
 * 
 *         Validate Relationship Score for Business Account of customer case
 *
 */
public class MPOSS_55295_ValidateRelationshipScoreIsDisplayedBusinessAccountOfCustomerCase_Test extends Base {

	/**
	 * @throws Exception
	 * 
	 * Login as Service
	 * 
	 * Open a case with relationship score
	 * 
	 * validate relationship score is shown on business account
	 * 
	 */
	@Test(groups= {"sanity"})
	
	public void test_ValidateRelationshipScoreIsDisplayedBusinessAccountOfCustomerCase() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		
		// Validate Relationship Score shown on Business Account for AE Profile
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openServiceConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.caseDetails.searchCaseFromGlobalSearch(InputData_Service.relationshipScoreCustomerCase);
		sfdc.caseDetails.validateRelationshipScore();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
				
		//validate Relationship Score shown on Business Account for Service Profile
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.openServiceConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.caseDetails.searchCaseFromGlobalSearch(InputData_Service.relationshipScoreCustomerCase);
		sfdc.caseDetails.validateRelationshipScore();
		sfdc.home.closeTabIfOpen();		
		sfdc.home.logout();
		softassert.assertAll();	
	}
}
