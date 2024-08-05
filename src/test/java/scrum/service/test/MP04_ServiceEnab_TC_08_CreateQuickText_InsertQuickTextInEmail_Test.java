package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date: 29/MAY/2020
 * 
 *         Verify User can Create quick Text and use quick text in the email
 *
 * 
 */
public class MP04_ServiceEnab_TC_08_CreateQuickText_InsertQuickTextInEmail_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Login as service rep
	 * 
	 *                              Click on New Quick Text Button
	 * 
	 *                              Enter Quick Text Information
	 * 
	 *                              Validate Quick Text Information
	 * 
	 *                              Open a case and Click on compose email and
	 *                              select email body area
	 * 
	 *                              Attach Quick Text in Email body
	 * 
	 *                              Verify quick text content in email body
	 * 
	 * 
	 */
	@Test
	public void test_CreateQuickText_InsertQuickTextInEmail() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.quickText.selectQuickText();
		sfdc.quickText.createQuickText();
		sfdc.home.closeTabIfOpen();

		sfdc.cases.selectCaseCategory(Constants.SERVICE_TYPE_SHEET);
		sfdc.cases.createCaseProactively();
		sfdc.caseDetails.EmailUsingQuickText();
		sfdc.home.closeTabIfOpen();

		// sfdc.quickText.selectQuickText();
		// sfdc.quickText.deleteQuickText();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
