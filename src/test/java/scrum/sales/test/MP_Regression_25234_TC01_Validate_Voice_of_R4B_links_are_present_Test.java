package scrum.sales.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Nandan.More
 * 
 *          MPOSS 25234_TC01_Validate "Voice of R4B links" is present on the "R4B Sales console" at the bottom of the Account page under "links"
 *
 *		    MPOSS 25234_TC02_Validate "Voice of R4B links" is present on the "R4B Sales console" at the bottom of the Home page under "links"
 *
 *		    MPOSS 25234_TC03_Validate "Voice of R4B links" is present on the "R4B Sales console" at the bottom of the Contact page under "links"
 *	 
 *		    MPOSS 25234_TC04_Validate "Voice of R4B links" is present on the "R4B Sales console" at the bottom of the Opportunity page under "links"
 *

 *
 *MPOSS: Sales–21PI03-Sprint 6 - Addition of New Links 
 *
 *		    "MPOSS-42462_TC_01 Verify As A AE User ""Create New Salesforce Case"" Link is present under links.
 "
 "			MPOSS-42462_TC_03Verify  As A AE User ""Create New Compensation Case"" Link is present under links.
 "
 "			MPOSS-42462_TC_03 Verify As A AE User  ""Salesforce Classic Issues and Workarounds"" Link is present under links.
 "
 "			MPOSS-42462_TC_04 Verify As A AE User  ""Salesforce Lightning Sales Support Chatter Group"" Link is present under links.
 "
 "			MPOSS-42462_TC_05 Verify As A AE User  ""Wireless Offer Grid"" Link is present under links.
 "
 "			MPOSS-42462_TC_06 Verify As A AE User  ""Wireline Offer Grid"" Link is present under links.
 "
 "			MPOSS-42462_TC_07 Verify As A AE User  ""Mission Possible Hub"" Link is present under links.
 "
 "			MPOSS-42462_TC_08 Verify As A Sales Manager User  ""Create New Salesforce Case"" Link is present under links.
 "
 "			MPOSS-42462_TC_09 Verify  As A Sales Manager User  ""Create New Compensation Case"" Link is present under links.
 "
 "			MPOSS-42462_TC_10 Verify  As A Sales Manager User ""Salesforce Classic Issues and Workarounds"" Link is present under links.
 "
 "			MPOSS-42462_TC_11 Verify  As A Sales Manager User  ""Salesforce Lightning Sales Support Chatter Group"" Link is present under links.
 "
 "			MPOSS-42462_TC_12 Verify   As A Sales Manager User ""Wireless Offer Grid"" Link is present under links.
  "
 "			MPOSS-42462_TC_13 Verify  As A Sales Manager User ""Wireline Offer Grid""Link is present under links.
 "
 "			MPOSS-42462_TC_14 Verify  As A Sales Manager User ""Mission Possible Hub"" Link is present under links.
 "
 "			MPOSS-42462_TC_15 Verify As A Credit Ops User ""Create New Salesforce Case"" Link is present under links.
 "
 "			MPOSS-42462_TC_16 Verify  As A Credit Ops User ""Create New Compensation Case"" Link is present under links.
 "
 "			MPOSS-42462_TC_17 Verify As A Credit Ops User  ""Salesforce Classic Issues and Workarounds"" Link is present under links.
 "
 "			MPOSS-42462_TC_18 Verify As A Credit Ops User  ""Salesforce Lightning Sales Support Chatter Group"" Link is present under links.
 "
 "			MPOSS-42462_TC_19 Verify As A Credit Ops User  ""Wireless Offer Grid"" Link is present under links.
 " 
 "			MPOSS-42462_TC_20 Verify As A Credit Ops User  ""Wireline Offer Grid"" Link is present under links.
 "
 "			MPOSS-42462_TC_21 Verify As A Credit Ops User  ""Mission Possible Hub"" Link is present under links.
 "
 "			MPOSS-42462_TC_22 Verify As A Contract Support User ""Create New Salesforce Case"" Link is present under links.
 "
 "			MPOSS-42462_TC_23 Verify  As A Contract Support User ""Create New Compensation Case"" Link is present under links.
 "
 "			MPOSS-42462_TC_24 Verify As A Contract Support User  ""Salesforce Classic Issues and Workarounds"" Link is present under links.
 "
 "			MPOSS-42462_TC_25 Verify As A Contract Support User  ""Salesforce Lightning Sales Support Chatter Group"" Link is present under links.
 "
 "			MPOSS-42462_TC_26 Verify As A Contract Support User  ""Wireless Offer Grid"" Link is present under links.
 "
 "			MPOSS-42462_TC_27 Verify As A Contract Support User  ""Wireline Offer Grid"" Link is present under links.
 "
 "			MPOSS-42462_TC_28 Verify As A Contract Support User  ""Mission Possible Hub"" Link is present under links.
 "
 "			MPOSS-42462_TC_29 Verify As A Ops Manager User ""Create New Salesforce Case"" Link is present under links.
 "
 "			MPOSS-42462_TC_30 Verify  As A Ops Manager User ""Create New Compensation Case"" Link is present under links.
 "
 "			MPOSS-42462_TC_31 Verify As A Ops Manager User  ""Salesforce Classic Issues and Workarounds"" Link is present under links.
 "
 "			MPOSS-42462_TC_32 Verify As A Ops Manager User  ""Salesforce Lightning Sales Support Chatter Group"" Link is present under links.
 "
 "			MPOSS-42462_TC_33 Verify As A Ops Manager User  ""Wireless Offer Grid"" Link is present under links.
 "
 "			MPOSS-42462_TC_34 Verify As A Ops Manager User  ""Wireline Offer Grid"" Link is present under links.
 "
 "			MPOSS-42462_TC_35 Verify As A Ops Manager User  ""Mission Possible Hub"" Link is present under links.
 "*/

public class MP_Regression_25234_TC01_Validate_Voice_of_R4B_links_are_present_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 *           1. Login as AE.
	 *			 2. Validate that by default the app is selected as " R4B Sales Console".
	 * 			 3. GoTo >> any page / any detail page ( Account , contact , opportunities )
	 *			 4. Validate user will see the "Voice of R4B links" is present on the "R4B Sales console" at the bottom of the page under "links".
	 *			 5. Once selected user will be navigated to Voice of R4B in a new tab browser.
	 * @throws InterruptedException
	 * 
	 */
	@Test
	public void test_verifyLinksPresentOnPage() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.selectAccountTab();
		sfdc.acc.verifyVoiceOfLinksPresentAtAcc();
		sfdc.home.closeTabIfOpen();

		sfdc.home.selectHome();
		sfdc.home.verifyVoiceOfLinksPresentAtHome();
		sfdc.home.closeTabIfOpen();

		sfdc.cc.selectContacts();
		sfdc.cc.verifyVoiceOfLinksPresentAtContact();
		sfdc.home.closeTabIfOpen();

		sfdc.opp.selectOpportunityTab();
		sfdc.opp.verifyVoiceOfLinksPresentAtOpp();
		sfdc.home.closeTabIfOpen();
		
		sfdc.home.selectHome();
		sfdc.acc.verifyCreateNewSalesforcecaselink();
		sfdc.acc.verifyCreateNewCompensationCaselink();
		sfdc.acc.verifySalesforceClassicIAWlink();
		sfdc.acc.verifySalesforceLightingSalesSSCGlink();
		sfdc.acc.verifyWirelessOfferGridlink();
		sfdc.acc.verifyWirelineOfferGridlink();
		sfdc.acc.verifyMissionPossibleHubLink();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		/*
		sfdc.login.loginToSFDC(InputData.Profile_SalesManager);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.home.selectHome();
		sfdc.acc.verifyCreateNewSalesforcecaselink();
		sfdc.acc.verifyCreateNewCompensationCaselink();
		sfdc.acc.verifySalesforceClassicIAWlink();
		sfdc.acc.verifySalesforceLightingSalesSSCGlink();
		sfdc.acc.verifyWirelessOfferGridlink();
		sfdc.acc.verifyWirelineOfferGridlink();
		sfdc.acc.verifyMissionPossibleHubLink();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		sfdc.login.loginToSFDC(InputData.Profile_CreditOps);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.home.selectHome();
		sfdc.acc.verifyCreateNewSalesforcecaselink();
		sfdc.acc.verifyCreateNewCompensationCaselink();
		sfdc.acc.verifySalesforceClassicIAWlink();
		sfdc.acc.verifySalesforceLightingSalesSSCGlink();
		sfdc.acc.verifyWirelessOfferGridlink();
		sfdc.acc.verifyWirelineOfferGridlink();
		sfdc.acc.verifyMissionPossibleHubLink();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		
		sfdc.login.loginToSFDC(InputData.Profile_ContractSupport);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.home.selectHome();
		sfdc.acc.verifyCreateNewSalesforcecaselink();
		sfdc.acc.verifyCreateNewCompensationCaselink();
		sfdc.acc.verifySalesforceClassicIAWlink();
		sfdc.acc.verifySalesforceLightingSalesSSCGlink();
		sfdc.acc.verifyWirelessOfferGridlink();
		sfdc.acc.verifyWirelineOfferGridlink();
		sfdc.acc.verifyMissionPossibleHubLink();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		sfdc.login.loginToSFDC(InputData.Profile_OpsManager);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.home.selectHome();
		sfdc.acc.verifyCreateNewSalesforcecaselink();
		sfdc.acc.verifyCreateNewCompensationCaselink();
		sfdc.acc.verifySalesforceClassicIAWlink();
		sfdc.acc.verifySalesforceLightingSalesSSCGlink();
		sfdc.acc.verifyWirelessOfferGridlink();
		sfdc.acc.verifyWirelineOfferGridlink();
		sfdc.acc.verifyMissionPossibleHubLink();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout(); 
		
		sfdc.login.loginToSFDC(InputData_Sales.Profile_NIS);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.home.selectHome();
		sfdc.acc.verifyCreateNewSalesforcecaselink();
		sfdc.acc.verifyCreateNewCompensationCaselink();
		sfdc.acc.verifySalesforceClassicIAWlink();
		sfdc.acc.verifySalesforceLightingSalesSSCGlink();
		sfdc.acc.verifyWirelessOfferGridlink();
		sfdc.acc.verifyWirelineOfferGridlink();
		sfdc.acc.verifyMissionPossibleHubLink();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout(); 
		*/
		softassert.assertAll();
		

	}

}
