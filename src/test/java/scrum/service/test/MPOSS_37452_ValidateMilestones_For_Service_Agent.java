package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

//    validate if the milestone Timer has started or not

public class MPOSS_37452_ValidateMilestones_For_Service_Agent extends Base {
	
	@Test
	public void test_serviceUserMilestoneSection () throws IOException{
	SFDC_AllPages sfdc = new SFDC_AllPages();

	sfdc.login.loginToSFDC(InputData.Profile_Service);
	sfdc.home.closeTabIfOpen();
	sfdc.cases.selectCaseCategory(Constants.SERVICE_TYPE_SHEET);
	sfdc.cases.createCaseProactively();
	sfdc.caseDetails.validate_ProactivelyCreatedCaseDetails();
	sfdc.caseDetails.validate_MileStoneStatus();
	sfdc.home.closeTabIfOpen();
	sfdc.home.logout();
	softassert.assertAll();
	}

}
