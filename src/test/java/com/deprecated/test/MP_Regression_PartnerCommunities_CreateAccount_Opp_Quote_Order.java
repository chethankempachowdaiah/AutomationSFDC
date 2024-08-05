package com.deprecated.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_PartnerCommunities;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla, 14/05/2021
 * 
 *         Create business account from Partner communities
 *
 * 
 */
public class MP_Regression_PartnerCommunities_CreateAccount_Opp_Quote_Order extends Base {

	/**
	 * @throws Exception
	 * 
	 * 
	 *                   1. Login as Dealer
	 * 
	 *                   2. Click on Accounts from Sales Menu
	 * 
	 *                   3. Create business account
	 * 
	 * 
	 */
	@Test(dataProvider = "getE2EFlowTestData")
	public void test_e2ePartnerCommunitites(Hashtable<String, String> dataTable) throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.partnerCommLogin.loginToPartnerCommunities(InputData_PartnerCommunities.partnerCommunities_dealer_userid);
		sfdc.partnerCommHome.launchCreateNewBusinessAccount();

		// Create Account
		sfdc.acc.selectBusinessAccType();
		sfdc.cba.enterBusinessAccountInfo(false);
		sfdc.cba.verifyBusinessAccountCreated();
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreated();
		sfdc.cc.clickOnNextInCreateContact();
		sfdc.csa.enterServiceAccountInfo(1);
		sfdc.csa.clickOnNextInCreateServiceAccount();
		sfdc.csa.checkCreateBillingAccount();
		sfdc.cbia.enterBillingAccountInfo();
		sfdc.cbia.verifyBillingAccountCreated();
		sfdc.home.navigateURL();
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.home.closeTabIfOpen();
		// sfdc.accDetails.searchAccount(sf.dataInput.businessAccountName);
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		sfdc.partnerCommLogin.loginToPartnerCommunities(InputData_PartnerCommunities.partnerCommunities_dealer_userid);

//		
		// Validate newly created business account
		// Global.dataInput.businessAccountName="Auto_QA_BA_ 210519095259";
		sfdc.partnerCommAccDetails.validateBusinessAccount();
//		
//		//Create new Opportunity
		sfdc.accRelated.createOpportunity();
		sfdc.partnerCommHome.switchToWindowTwo();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.opportunityCreateContact();

		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.clickContactDetailsNext();
		sfdc.cc.verifyContactCreated();
		sfdc.cc.clickOnNextInCreateContact();

		sfdc.cOpp.selectOpportunityContactRole();
		// sfdc.cOpp.selecetExistingContactInOpportunity();

		sfdc.partnerCommOppDetails.validateOpportunityDetails(Global.dataInput.businessAccountName);

		// Create Quote by guided journey
		sfdc.cQuote.createQuoteGuidedByJourney_SelectExistingSigningAuthority();
		sfdc.cQuote.createQuote_SelectFirstServiceAccount();

		sfdc.gbjRDI.addRogersBusinessSolutions_RDI(dataTable, false);
		// Quote review
		if (dataTable.get("SecondAdds_On").equals("NA")) {
			sfdc.quoteReview.verifyDetailsinQuoteReviewForRDI(dataTable, false);
		}

		// *****Acccept Quote and create new site contact
		sfdc.cpqHome.acceptQuote();
		sfdc.gdPdf.sendQuoteNextButton();

		// sfdc.siteCon.createNewSiteContact();

		sfdc.siteCon.selectExistingSiteContact_Scrum();
		sfdc.orderDetails.verifyOrderNumberInOrderDetails();
	}

	/**
	 * @param dataTable
	 * 
	 *                  Read Input data from datasheet and assign it to input
	 *                  variables
	 */
	public void setInputData(Hashtable<String, String> dataTable) {

		// Global.dataInput.quoteProductName = dataTable.get("Internet Product Name");
		Global.dataInput.office365ProductName = dataTable.get("Office 365 AddOn");
		Global.dataInput.tvProductName = dataTable.get("TV Product Name");

		Global.dataInput.address = dataTable.get("Address");
		Global.dataInput.addressStreet = dataTable.get("Address Street");
		Global.dataInput.addressCity = dataTable.get("Address City");
		Global.dataInput.addressState = dataTable.get("Address State");
		Global.dataInput.addressCountry = dataTable.get("Address Country");
		Global.dataInput.serviceAddress = dataTable.get("CSA_Service Address");

		test.log(Status.PASS, MarkupHelper.createLabel(dataTable.toString(), ExtentColor.GREEN));
	}

	@DataProvider
	public Object[][] getE2EFlowTestData() throws IOException {
		return getDataSetsRunMode(Constants.MP_TESTDATA_FILE, Constants.GBJ_RDI_RESUME_SHEET);
	}
}
