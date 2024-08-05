package scrum.communities.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.BaseBrowser;
import com.framework.base.BaseDataProvider;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla Created On 19-Oct-2020
 * 
 *         ////--- ----////
 * 
 *         Login to Communities portal
 * 
 *         Validate that the customer lands on the page with communities badge
 *
 *         Click on Create new case
 * 
 *  	   Fill case form and Submit
 * 
 * 		   Validate Case details
 * 
 * 		   Validate case reason in Salesforce
 * 
 *         ////-----////
 *
 * 
 */
public class MP_Communities_TC_06_Case_Creation_Service_Test extends BaseDataProvider {

	SFDC_AllPages sfdc = new SFDC_AllPages();

	@BeforeTest()
	public void launchBrowserAndLogin() throws IOException {

		//intializeChrome(false);
		sfdc = new SFDC_AllPages();
		
		test = reports.createTest(this.getClass().getName());
		//
		
		// ****LOGIN To Communities******//
		sfdc.comLogin.loginToCommunities();
		
	}

	/**
	 * @throws IOException
	 * @throws InterruptedException 
	 * 
	 */
	@Test(dataProvider = "caseCreationDataService")
	public void test_communities_case_creation(Hashtable<String, String> dataTable) throws IOException, InterruptedException {

		InputData_Communities.setDataForCreateCase(dataTable);

		// ***Create case***//
		sfdc.comMyBusCases.navigateToCreateCasePage();
		sfdc.comMyBusCases.createServiceCase();
		
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
			sfdc.comLogin.loginToCommunities();
		}
			
		sfdc.comHome.openCommunityCases();

	}

	@AfterTest()
	public void logout() throws IOException {

		sfdc.comLogin.logoutFromCommunities();
		//softassert.assertAll();
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
		return getDataSetsRunMode(Constants.TESTDATA_COMM_FILE, Constants.W2C_SERVICE_SHEET);
	}
}
