package com.deprecated.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka Acharya, Date 17/Jan/2020
 * 
 *         Validate that user is redirected to Quote details page once he/she
 *         selects "On Net" as Serviceability from the "Select Service Account"
 *         page.
 * 
 *         Verify that user can select ONLY 1 service account and if user select
 *         the account other than ON-Net, "Next" button will not be displayed.
 *
 * 
 */
public class MP01_SalesEnab_TC_02_CreateQuote_OnNetServiceability_Test extends Base {

	/**
	 * @throws Exception
	 * 
	 * 
	 *                   1. Login as Account Executive >> Click App launcher >> From
	 *                   All apps >> Select "R4B Sales Console" >> Select Accounts
	 *                   from dropdown >> All Accounts >> Select any existing
	 *                   Account >> click "Related" tab under this account >> hit
	 *                   "New Opportunity" button.
	 * 
	 *                   2. "New Opportunity" button redirect user to the omniscript
	 *                   flow, which asks user to select
	 * 
	 *                   3. Complete below stages : SELECT A PRODUCT and Sub Product
	 *                   >> Hit NEXT >>
	 * 
	 *                   4. User will be redirected to Opportunity Creation page.
	 * 
	 *                   5. Select all the Mandatory Fields and hit NEXT.
	 * 
	 *                   6. User will be redirected to Select Opportunity Contact
	 *                   Roles page.
	 * 
	 *                   7. Select "Create Account" checkbox at the bottom of the
	 *                   page and hit Next.
	 * 
	 *                   8. User will be redirected to "Contact Details" page.
	 * 
	 *                   9. Enter all the mandatory details and hit Next .
	 * 
	 *                   10. Validate that user can successfully create new contact.
	 * 
	 *                   11. Select the Contact created in the above step and give
	 *                   contact role as "Signing Authority"
	 * 
	 *                   12. Hit Next
	 * 
	 *                   13. Validate that New Opportunity is created with
	 *                   Opportunity name as syntax <Account Name> - <Opportunity
	 *                   Type>- <Product>- <Sub-Product>
	 * 
	 *                   14. Hit 'Create Quote' button.
	 * 
	 *                   15. Select the Signing Authority and hit Next.
	 * 
	 *                   16. User will be redirected to "Select Service Account"
	 *                   Select one account name and give serviceability as "On NET"
	 *                   , Hit NEXT
	 * 
	 *                   17. Validate that Quote shell has been created.
	 * 
	 */
	@Test
	public void test_createQuoteOnNetServicability() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// sfdc.acc.selectAccount();
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.parentAccountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.opportunityCreateContact();

		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.clickContactDetailsNext();
		sfdc.cc.verifyContactCreated();
		sfdc.cc.clickOnNextInCreateContact();

		sfdc.cOpp.selectOpportunityContactRole();
		// sfdc.cOpp.selecetExistingContactInOpportunity();
		sfdc.oppDetails.validateOpportunityDetails(sf.dataInput.parentAccountName);

		sfdc.cQuote.createQuote_SelectContatRole_SigningAuthority();
		// sfdc.cQuote.createQuote_SelectExistingSigningAuthority();
		sfdc.cQuote.createQuote_SelectExistingServiceAccount();

		if (dataInput.env.equals("ITDEVSTAGE")) {
			sfdc.cpqHome.verifyQuoteNumberInCPQHome();
			sfdc.cpqHome.clickOnReviewQuoteRecordButton();
			sfdc.cpqQuoteRec.verifyQuoteInQuoteRecord();
			sfdc.cpqQuoteRec.clickOnOpportunityLink();
			sfdc.oppRelated.verifyQuoteInOpportunityRelated();
			sfdc.cpqQuoteRec.verifyQuoteInQuoteRecord();
		}
		softassert.assertAll();

	}

}
