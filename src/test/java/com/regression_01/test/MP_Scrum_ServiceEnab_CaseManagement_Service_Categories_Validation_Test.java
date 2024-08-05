package com.regression_01.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.framework.base.BaseDataProvider;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.MP_Service_CaseCategory_Lib;
import com.sfdc.lib_pages.SFDC_AllPages;

@Listeners(com.framework.base.MyListener.class)
public class MP_Scrum_ServiceEnab_CaseManagement_Service_Categories_Validation_Test extends BaseDataProvider {

	/**
	 * @throws IOException
	 * 
	 *                     Note : For scrum specific run with multiple data set
	 *                     please do below changes in BASE
	 * 
	 *                     prev: Aftermethod, beforemethod
	 * 
	 *                     for scrum: aftertest, beforetest.
	 * 
	 *                     1. Login as Service rep
	 * 
	 *                     2. Select cases and create new case of Service type
	 */
	@BeforeTest
	public void setCaseCategory() throws IOException {
		SFDC_AllPages sfdc = new SFDC_AllPages();
		test = reports.createTest(this.getClass().getName());
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.cases.selectCaseCategory(Constants.SERVICE_TYPE_SHEET);
	}

	@Test(priority = 1, dataProvider = "getCaseManagementCategories")
	public void prepareCaseDateForAllOptions(Hashtable<String, String> dataTable) throws Exception {
		MP_Service_CaseCategory_Lib.prepareDateToVerifyAllOptions(dataTable);
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Select and verify values present in category
	 *                              dropdown as per the given input data
	 */
	@Test(priority = 2, dataProvider = "getCaseManagementCategories")
	public void test_verifyCaseCategory(Hashtable<String, String> dataTable) throws Exception {
		SFDC_AllPages sfdc = new SFDC_AllPages();

		MP_Service_CaseCategory_Lib.identifyCase(dataTable);
		// sfdc.cases.verifyCaseCategory(dataTable, true);....// from MP14 customer
		// reason can't be updated from SF. It can only be updated from Communities , so
		// skipping Customer Reason Validation in SF
		sfdc.cases.verifyCaseCategory(dataTable, false);
	}

	/**
	 * @return
	 * @throws IOException
	 * 
	 *                     Data Provider to fetch multiple set of data and assign
	 *                     them to 2D Object Array
	 */
	@DataProvider
	public Object[][] getCaseManagementCategories() throws IOException {
		if (InputData.frenchEnabled.equals("Yes")) {
			return getDataSets(Constants.CASE_MANAGEMENT_CATEGORIES_FILE_FRENCH, Constants.SERVICE_TYPE_SHEET);

		} else {
			return getDataSets(Constants.CASE_MANAGEMENT_CATEGORIES_FILE, Constants.SERVICE_TYPE_SHEET);

		}
	}
}
