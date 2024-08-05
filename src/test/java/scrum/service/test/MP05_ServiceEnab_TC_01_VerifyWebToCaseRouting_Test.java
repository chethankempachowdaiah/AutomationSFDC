package scrum.service.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, 09/06/2020
 * 
 *         ****************Design Steps******************
 * 
 *         1. Contact log in community 2. In Web to case channel, enter the
 *         information: 3. Service log in and validate case is created and case
 *         owner 1. Web to Case channel is displayed
 * 
 *         ***************Expected Result:*****************
 * 
 *         1. Case information is filled 2. Case is created in SF.
 *
 * 
 */
public class MP05_ServiceEnab_TC_01_VerifyWebToCaseRouting_Test extends Base {

	/**
	 * Setup> Case Assignment Rules
	 * 
	 * Rule Entries
	 * 
	 * 
	 * 1 (Case: Case OriginEQUALSWeb) AND (Account: Rogers Business
	 * IDEQUALSTMP0000000378) AND (Case: Case Record TypeEQUALSService) WLS Dedi
	 * (AB2)
	 * 
	 * 2 (Case: Case Record TypeEQUALSService) AND (Case: Case OriginEQUALSWeb) AND
	 * (Case: Case ProductEQUALSWireless Devices and Plans) AND (Case: Case
	 * ReasonEQUALSBilling Inquiry) MYBSG
	 * 
	 * 3 (Case: Case OriginEQUALSWeb) AND (Case: Case Record TypeEQUALSService) AND
	 * (Case: Case ProductEQUALSWireless Devices and Plans) AND (Case: Case
	 * ReasonEQUALSAccount Change) AB1
	 * 
	 * 4 (Case: Case OriginEQUALSWeb) AND (Case: Case Record TypeEQUALSService) AND
	 * (Case: Case ProductEQUALSWireless Devices and Plans) AND (Case: Case
	 * ReasonEQUALSOther) MYBSG10
	 * 
	 * 5 Case: Case OriginEQUALSWeb Overflow Queue
	 * 
	 * @throws IOException
	 */
	@Test(dataProvider = "getCaseRoutingInfo")
	public void test_VerifyWebToCaseRouting(Hashtable<String, String> dataTable) throws IOException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ***************LOGIN To Communitities and Create Case****************//
		sfdc.comLogin.loginToCommunities(dataTable);
		sfdc.comContactUs.createCaseViaWebEmail(dataTable);

		// ***************LOGIN To Salesforce and Verify Case Details****************//
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.cases.verifyCasesObject();
		sfdc.cases.selectAndOpenCase();
		sfdc.caseDetails.validateWebToCaseDetails(dataTable);

		// **********Close Tabs and Log out********//
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
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
	public Object[][] getCaseRoutingInfo() throws IOException {
		return getDataSets(Constants.MP_TESTDATA_FILE, Constants.CASE_ROUTING_SHEET);
	}

}
