package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Robin.Mangla, Date: 12/MAY/2022
 * 
 *        Validate User can create Web to Case from Community
 *
 * 
 */
public class MPOSS_63156_TC03_ValidateWebToCaseCreationFromCommunity_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 */
	@Test
	public void test_TC03_ValidateWebToCaseCreationFromCommunity() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();
				
		sfdc.comLogin.loginToCommunitiesForFraudCaseSearch();
		sfdc.cases.createWebToCase();
		sfdc.comLogin.logoutFromCommunities();

		//validate case origin as web
		sfdc.login.loginToSFDCForWebToCase(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.caseDetails.searchCaseFromGlobalSearch(InputData.caseNumber);
		sfdc.caseDetails.validateCaseOriginWeb();		
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
