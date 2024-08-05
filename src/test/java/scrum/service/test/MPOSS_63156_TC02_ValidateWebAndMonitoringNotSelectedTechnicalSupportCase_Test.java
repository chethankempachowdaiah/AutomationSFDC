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
 *        Validate User cannot select Web and Monitoring option from
 *        
 *        Case Origin options for technical support type case
 *
 * 
 */
public class MPOSS_63156_TC02_ValidateWebAndMonitoringNotSelectedTechnicalSupportCase_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 */
	@Test
	public void test_TC02_ValidateWebAndMonitoringNotSelectedTechnicalSupportCase() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.cases.selectCaseCategory(Constants.TECH_SUPPORT_SHEET);
		sfdc.cases.validateWebAndMonitoringNotSelected();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
