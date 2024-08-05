package com.deprecated.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date: 28/MAY/2020
 *
 *         Verify Opportunity creation selecting these specific Primary &
 *         Secondary products Sub-Products will redirect to NEW ORG and OLD Org
 *         Based on the selection
 */
public class MP05_SalesEnab_TC_02_VerifyR4B_B2B_Navigation_ForProductSubProduct_OpportunityCreation_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              1- Create Opportunity: Select Product
	 * 
	 *                              2- Create Opportunity: Select Sub Product
	 * 
	 *                              3- Click on Next Button
	 * 
	 *                              4- Verify Company name is populated(for new org
	 *                              navigation)
	 * 
	 *                              5- Verify Successful Switcing from R4B to B2B
	 *                              (old org navigation)
	 * 
	 */

	@Test
	public void test_VerifyR4B_B2B_Navigation_ForProductSubProduct_OpportunityCreation()
			throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// ***************Select Opportunity***********************//
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.parentAccountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.verifyProductSubProductNavigationOldNewOrg();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
