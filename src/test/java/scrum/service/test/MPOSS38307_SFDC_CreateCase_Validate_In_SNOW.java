package scrum.service.test;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.framework.base.BaseDataProvider;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Service;
import com.sfdc.lib_pages.SFDC_AllPages;

public class MPOSS38307_SFDC_CreateCase_Validate_In_SNOW extends BaseDataProvider{
	SFDC_AllPages sfdc=new SFDC_AllPages();
	

	
	@Test(dataProvider = "SFDC_CreateCase")
	public void createCase_Validate_SNOW (Hashtable<String, String> dataTable) throws Exception{
		
		InputData_Service.setDataForSFDCCase(dataTable);
		sfdc.home.navigateURL();
		sfdc.login.loginToSFDC(InputData.Profile_Service);
//		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.cases.selectCaseCategory(Constants.TECH_SUPPORT_SHEET);
		sfdc.cases.createCaseProactivelyFromExcel();
		//sfdc.caseDetails.validate_ProactivelyCreatedCaseDetails();
		sfdc.caseDetails.changeCaseOwnerTier2Wireline();
		sfdc.caseDetails.validate_ExternalTicket(true);

		//sfdc.home.closeTabIfOpen();
		//sfdc.home.logout();	
		
		//Verify the SalesForce case details in the SNOW Portal
		sfdc.snowPortalCase.launchSnowPortal();

	}
	
//	@AfterTest()
//		public void logout() {
//		sfdc.snowPortalCase.snowlogout();
//			
//		}
	/**
	 * @return
	 * @throws IOException
	 * 
	 *                     Data Provider to fetch multiple set of data and assign
	 *                     them to 2D Object Array
	 */
	@DataProvider
	public Object[][] SFDC_CreateCase() throws IOException {
		return getDataSetsRunMode(Constants.MP_TESTDATA_FILE, Constants.Proactive_Case_SUPPORT_SHEET);
	}
	

}
