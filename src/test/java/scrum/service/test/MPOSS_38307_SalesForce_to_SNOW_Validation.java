package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Rajat Rathi
 * 
 *   Pi2SP03_MPOSS_38307_SalesForce_to_SNOW_Validation
 *   Validation in Snow Portal
 *   
 */

public class MPOSS_38307_SalesForce_to_SNOW_Validation extends Base {

	@Test
	public void SalesForceTOSnowValidation() throws Exception {
		
	SFDC_AllPages sfdc = new SFDC_AllPages();
	sfdc.login.loginToSFDC(InputData.Profile_Service);
	sfdc.home.closeTabIfOpen();
	
	sfdc.cases.selectCaseCategory(Constants.TECH_SUPPORT_SHEET);
	sfdc.cases.createCaseProactively();
	sfdc.caseDetails.changeCaseOwnerTier2Wireline();
	sfdc.caseDetails.validate_ExternalTicket(true);
	
	//Verify the SalesForce case details in the SNOW Portal
	sfdc.snowPortalCase.launchSnowPortal();

}
}