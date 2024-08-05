package scrum.service.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.BaseBrowser;
import com.framework.base.BaseDataProvider;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Rajat Rathi
 * 
 *   PI02SP2_MPOSS_53191_WEB2CASE_WireLine_DarkFiber
 *   
 *   PI02SP2_MPOSS_53191_WEB2CASE_WireLine_Wavelength
 *   
 *   PI02SP2_MPOSS_53191_WEB2CASE_WireLine_Cable_AgentManually_changes_Owner 
 * 
 * 
 */
public class MPOSS_53191_WEB2CASE_WireLine_DarkFiber extends BaseDataProvider {
	
	SFDC_AllPages sfdc = new SFDC_AllPages();

	@BeforeTest()
	public void launchBrowserAndLogin() throws IOException {

		sfdc = new SFDC_AllPages();
		test = reports.createTest(this.getClass().getName());

		// ****LOGIN To Communities******//
		sfdc.comLogin.loginToCommunities();
		//sfdc.comHome.verifyCommunityBadge();
	}

	/**
	 * @throws IOException
	 * @throws InterruptedException 
	 * 
	 */
	@Test(dataProvider = "caseCreationDataService")
	public void test_communities_verify_case_layout_reasons_List_accountNames_and_case_comments(Hashtable<String, String> dataTable) throws IOException, InterruptedException {

		InputData_Communities.setDataForCreateCase(dataTable);

		// ***Create case***//
		sfdc.comMyBusCases.navigateToCreateCasePage();
		sfdc.comMyBusCases.createTechnicalCase();
		
		// ** Verify Case Details***//
		if (InputData_Communities.commCaseDoDetailsValidations.equalsIgnoreCase("Yes"))
			sfdc.comCaseDetails.validateCaseDetails();
		
		if (InputData_Communities.commCaseDoSalesForceValidations.equalsIgnoreCase("Yes")) {
			sfdc.home.navigateURL();
			sfdc.login.loginToSFDC(InputData.Profile_Service);
			sfdc.home.closeTabIfOpen();
			sfdc.cases.verifyCasesObject();
			sfdc.cases.searchCaseGloballyAndOpen();
			sfdc.caseDetails.verifyCaseCustomerReason();
			
			//   If the case Product(category in excel is Business Internet then validate External Ticket and change CaseOwner to
			//    Tier2wireline to generate External Ticket
			if(InputData_Communities.commCaseCategory.equalsIgnoreCase("Business Internet (Cable Internet)")) {
				sfdc.caseDetails.validate_ExternalTicket(false);
				sfdc.caseDetails.changeCaseOwnerTier2Wireline();
				sfdc.caseDetails.validate_ExternalTicket(true);
			}
					
			sfdc.comLogin.loginToCommunities();
		}
			
		sfdc.comHome.openCommunityCases();

	}

	@AfterTest()
	public void logout() throws IOException {

		sfdc.comLogin.logoutFromCommunities();
		softassert.assertAll();
	}

	/**
	 * @return
	 * @throws IOException
	 * 
	 *                     Data Provider to fetch multiple set of data and assign
	 *                     them to 2D Object Array
	 */
	@DataProvider
	public Object[][] caseCreationDataService() throws IOException {
		return getDataSetsRunMode(Constants.TESTDATA_COMM_FILE, Constants.W2C_TECH_SUPPORT_SHEET);
	}	

}

