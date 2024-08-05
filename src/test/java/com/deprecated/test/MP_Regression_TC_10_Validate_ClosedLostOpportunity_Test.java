package com.deprecated.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date 27/03/2020
 * 
 *         Vallidate user is Unable to activate order due to Lost opportunity
 *
 */
public class MP_Regression_TC_10_Validate_ClosedLostOpportunity_Test extends Base {

	/**
	 * Create Business Account
	 * 
	 * Create Service Account
	 * 
	 * Create opportunity with the Business Account and existing Service Contact
	 * 
	 * Select “Off Net Serviceability"
	 * 
	 * Verify Opportunity is Lost
	 * 
	 * Validate that below 3 fields are the required fields :
	 * 
	 * -Closed Lost Primary
	 * 
	 * ..................Reason: a. Serviceability b. Promotion Pricing
	 * 
	 * -Closed Lost Secondary
	 * 
	 * ..................Reason: a. No cable Coverage b. Promotion Pricing
	 * 
	 * -Competitor Lost To
	 * 
	 * ......Reason: a. Bell b. Telus c. Shaw d. Cogeco e.Zayo/Allstream
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void test_validate_ClosedLostOpportunity() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// ***************Create business Account********//
		sfdc.acc.createNewBusinessAccount();
		sfdc.cba.enterBusinessAccountInfo(false);
		sfdc.cba.verifyBusinessAccountCreated();

		// ***************Create Contact********//
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreated();
		sfdc.cc.clickOnNextInCreateContact();

		// ***************Create Service Account********//
		sfdc.csa.enterServiceAccountInfo(1);
		sfdc.csa.clickOnNextInCreateServiceAccount();

		// ***************Create Billing Account********//
		sfdc.csa.checkCreateBillingAccount();
		sfdc.cbia.enterBillingAccountInfo();
		sfdc.cbia.verifyBillingAccountCreated();

		// ***************Verify Business, Service and Billing Account Created********//
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.validateBusinessAccount();// ..............VERIFY BUSINESS ACCOUNT
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.validateServiceAccount(true);// ...........VERIFY SERVICE ACCOUNT
		sfdc.premises.validatePremises();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.validateBillingAccount(true);// ...........VERIFY BILLING ACCOUNT
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// *********Login as Data Governance and Approve business Account******//
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.home.closeTabIfOpen();
		// sfdc.accDetails.searchAccount(sf.dataInput.businessAccountName);
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// ***************Create Opportunity***********************//
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();

		// ***************Select Contact Role In Opportunity***********************//
		sfdc.cOpp.selectOpportunityContactRole();

		// ***************Verify Opportunity Details********//
		sfdc.oppDetails.validateOpportunityDetails(sf.dataInput.businessAccountName);

		// ***************Create Quote With Existing Service Account********//
		sfdc.cQuote.createQuote_SelectContatRole_SigningAuthority();
		sfdc.cQuote.createQuote_SelectExistingServiceAccount();

		// **********Validate Closed Lost Opportunity********//
		sfdc.oppClosedInfo.validateClosedLostOpportunity();
		sfdc.oppDetails.verifyClosedLostOpportunityDetails();

		// **********Close Tabs and Log out********//
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();

	}
}
