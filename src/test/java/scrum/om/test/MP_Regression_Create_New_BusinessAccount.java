package scrum.om.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

public class MP_Regression_Create_New_BusinessAccount extends Base {

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              1. RDI END TO END FLOW FROM ORDER CREATION TO
	 *                              MANUAL QUEUE TASK COMPLETION, Order Activation,
	 *                              Service Assets
	 * 
	 */ // 1. Many steps are commented out due to new changes been introduced (Like
	// community login Url).
	// Also, executing the regression suite scenario test cases we need to comment
	// out few steps

	@Test(dataProvider = "getE2EFlowTestData")
	public void test_Validate_GBJEndToEndFlow_Promotions(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {


		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ***************LOGIN AS AE***********************/
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		//*****Create Business Account******//
		sfdc.acc.createNewBusinessAccount();
		sfdc.cba.enterBusinessAccountInfo(false);
		sfdc.cba.verifyBusinessAccountCreated();

		//*****Create Contact**********//
		//Global.dataInput.contactEmailAddress = "rakesh.kumar@rci.rogers.com";
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreated();
		sfdc.cc.clickOnNextInCreateContact();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		//*****Login With DataGovernance profile to approve the business account
		// and enter credit check values for validation
		// //** based on the test data*********

		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
		sfdc.accRelated.approveAsDataGovernance();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ****Create Opportunity and select existing contact in
		// opportunity***************//

		// ***************LOGIN AS AE***********************/
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
//		Global.dataInput.tempBusinessAccountName = "Auto_QA210524086966";
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();

	}
	
	@DataProvider
	public Object[][] getE2EFlowTestData() throws IOException {
		return getDataSetsRunMode(Constants.MP_TESTDATA_FILE, Constants.GBJ_RDI_RESUME_SHEET);
	}
}


