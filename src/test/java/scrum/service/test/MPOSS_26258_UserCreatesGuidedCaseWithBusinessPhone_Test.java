package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Robin.Mangla
 */

public class MPOSS_26258_UserCreatesGuidedCaseWithBusinessPhone_Test extends Base {
	@Test
	
	public void test_UserCreatesGuidedCaseWithBusinessPhone() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.cases.selectCaseCategory(Constants.TECH_SUPPORT_SHEET);
		sfdc.cases.createGuidedCasewithBusinessphone();
		sfdc.caseDetails.validate_GuidedCasewithBusinessphone();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();

}
	
}
