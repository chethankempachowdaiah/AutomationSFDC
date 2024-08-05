package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Robin.Mangla, Date: 02/MARCH/2022
 * 
 *         Verify User proactively create a new case
 * 
 *
 * 
 */
public class MPOSS_59408_ValidateErrorMessageForCaseWithNoContact_Test extends Base 
{

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Login as Service , Select Cases, Select new Case
	 * 
	 *                              select case status, priority, type, sub-type,
	 *                              reason, origin, subject.
	 * 
	 *                              Enter description and internal comments
	 * 
	 *                              Click on Next Button
	 * 
	 *                              Verify Case details for Proactively Created case
	 * 
	 * 
	 */
	@Test
	public void test_ValidateErrorMessageForCaseWithNoContact() throws IOException, InterruptedException 
	{
		SFDC_AllPages sfdc = new SFDC_AllPages();
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.cases.selectCaseCategory(Constants.SERVICE_TYPE_SHEET);		
		sfdc.cases.createCaseProactively();		
		sfdc.caseDetails.validateErrorMessage();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();		
		softassert.assertAll();
	}
}
