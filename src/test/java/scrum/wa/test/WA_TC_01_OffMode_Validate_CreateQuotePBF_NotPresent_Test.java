package scrum.wa.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date : 21/05/2021
 * 
 *         “ Create Quote By PBF” Button should not be visible to AE, Data Gov,
 *         Delivery and Fraud Ops Profile in Prod Env
 *
 */
public class WA_TC_01_OffMode_Validate_CreateQuotePBF_NotPresent_Test extends Base {

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              User logs in from the given profile ( AE, Data
	 *                              Gov, Delivery ,Fraud Ops and Credit Ops)
	 * 
	 *                              Open Existing Opportunity( For AE Create
	 *                              Opportunity)
	 * 
	 *                              Verify " Create Quote By PBF" Button is not
	 *                              present
	 */
	@Test(dataProvider = "getWAProfiles")
	public void test_OffMode_Validate_CreateQuotePBF_NotPresent(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(dataTable.get("Profile"));
		sfdc.home.closeTabIfOpen();

		if (dataTable.get("Create_Opp").equals("Yes")) {
			sfdc.accDetails.searchBusAccGlobalSearch(InputData.account_ON);

			sfdc.accRelated.createOpportunity();

			// sfdc.cOpp.enterOpportunityDetails();
			// sfdc.cOpp.selecetExistingContactInOpportunity();

			sfdc.cOpp.verifyProductSubProductNavigationOldNewOrg();

		} else {
			// sfdc.opp.searchOpportunityInGlobalSearch("Wireless");

			sfdc.opp.viewAnyAvailableOpportunity();
			sfdc.cQuote.verifyCreateQuotePBF_NotPresent();
		}

		// Verify Wireless Tile is not present in "Create Quote By GBJ"

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}

	@DataProvider
	public Object[][] getWAProfiles() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WA_PVT);
	}
}
