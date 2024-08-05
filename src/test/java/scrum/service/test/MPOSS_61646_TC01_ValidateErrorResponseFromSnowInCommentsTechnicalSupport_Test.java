package scrum.service.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.utilities.GetExcelData;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Robin.Mangla, Date: 14/APR/2022
 * 
 *         Validate Error Response From Snow In Comments TechnicalSupport
 * 
 *   
 */
public class MPOSS_61646_TC01_ValidateErrorResponseFromSnowInCommentsTechnicalSupport_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 */
	@Test(groups= {"sanity"})
	public void test_TC01_ValidateErrorResponseFromSnowInCommentsTechnicalSupport() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.cases.selectCaseCategory(Constants.TECH_SUPPORT_SHEET);
		sfdc.cases.createCaseProactively();
		sfdc.caseDetails.validate_ProactivelyCreatedTechnicalSupportCaseDetails();
        sfdc.caseDetails.validateErrorResponseFromSnowInComments();
        sfdc.home.closeTabIfOpen();
        sfdc.home.logout();		
		softassert.assertAll();
	}	
}
