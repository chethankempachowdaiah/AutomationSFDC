package scrum.sales.test;

import com.framework.base.Base;
import com.framework.base.Global;

import org.testng.annotations.Test;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Nandan.More, Date 12/Oct/2021
 * 
 *         21PI04-SP1\MPOSS-49967_Campaign Related List on Leads, Contacts and Account pages
 *         
 *         "MPOSS-49967_TC_01 Verify Campaign Related List and Fields are present on the Lead layout. 
			"
			"MPOSS-49967_TC_02 Verify Campaign Related List  and Fields are present on the Contact layout
			"
			"MPOSS-49967_TC_03 Verify Campaign Related List  and Fields are present on the Account layout
			"
			"MPOSS-49967_TC_04 Verify Campaign Related List and Fields are present on the Lead layout. 
			"
			"MPOSS-49967_TC_05 Verify Campaign Related List  and Fields are present on the Contact layout
			"
			"MPOSS-49967_TC_06 Verify Campaign Related List  and Fields are present on the Account layout
			"
			"MPOSS-49967_TC_07 Verify Campaign Related List and Fields are present on the Lead layout. 
			"
			"MPOSS-49967_TC_08 Verify Campaign Related List  and Fields are present on the Contact layout
			"
			"MPOSS-49967_TC_09 Verify Campaign Related List  and Fields are present on the Account layout
			"
			"MPOSS-49967_TC_10 Verify Campaign Related List and Fields are present on the Lead layout. 
			"
			"MPOSS-49967_TC_11 Verify Campaign Related List  and Fields are present on the Contact layout
			"
			"MPOSS-49967_TC_12 Verify Campaign Related List  and Fields are present on the Account layout
			"
			"MPOSS-49967_TC_13 Verify Campaign Related List and Fields are present on the Lead layout. 
			"
			"MPOSS-49967_TC_14 Verify Campaign Related List  and Fields are present on the Contact layout
			"
			"MPOSS-49967_TC_15 Verify Campaign Related List  and Fields are present on the Account layout
			"
			"MPOSS-49967_TC_16 Verify Campaign Record type should reflect Standard Record Type when we logged as a Marketing User on the Lead/Contact/Account layout.
			"
			"MPOSS-49967_TC_17 Verify Campaign Record type should reflect Standard Record Type when we logged as a Business Admin User on the Lead/Contact/Account layout.
			"
			"MPOSS-49967_TC_18 Verify Campaign Record type should reflect Standard Record Type when we logged as an Account Executive User on the Lead/Contact/Account layout.
			"
			"MPOSS-49967_TC_19 Verify Campaign Record type should reflect Standard Record Type when we logged as a Sales manager User on the Lead/Contact/Account layout.
			"
			"MPOSS-49967_TC_20 Verify Campaign Record type should reflect Standard Record Type when we logged as a Sales Executive User on the Lead/Contact/Account layout.
			"

		   MPOSS-51807-NIS Account Executive can see Campaign History Related List
		    1. Campaign History Related List on Guided Selling Lead Layout
			2.Campaign History Related List on Guided Selling Contact Lead Layout
			3.Campaign History Related List fields: Campaign Name, Start Date, Type


 *
 * 
 */

public class MP_21PI04_SP01_49967_Campaign_Related_List_on_Leads_Contacts_and_Account_pages_Test extends Base {
	
	/**
	 * @throws Exception
	 * 
	 *                  
	 */
	@Test
	public void test_ValidateCampaignRelatedListonLeadsContactsAndAccountpages() throws Exception , InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		/*sfdc.home.openCampaign();
		sfdc.campaignPage.validateCampaignRecordType();
		sfdc.home.closeTabIfOpen();
		//Campaign Related List and Fields are present on the Lead layout. 
		sfdc.lead.selectAndOpenAnyLead();
		sfdc.campaignPage.leadLayout();
		sfdc.campaignPage.verifyCampaignFields();
		
		sfdc.home.closeTabIfOpen();
		//Campaign Related List and Fields are present on the Contact layout. 
		sfdc.cc.selectAndOpenAnyContacts();
		sfdc.campaignPage.contactLayout();
		sfdc.campaignPage.verifyCampaignFields();
		
		sfdc.home.closeTabIfOpen();*/
		//Campaign Related List and Fields are present on the Account layout. 
		sfdc.acc.selectAccountTab();
	//	sfdc.acc.openAnyActiveBusinessAccountFromList();
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.parentAccountName);
		sfdc.campaignPage.accountLayout();
		sfdc.campaignPage.verifyCampaignFields();
		
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
	
		sfdc.login.loginToSFDC(InputData_Sales.Profile_MarketingUser);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		//Campaign Related List and Fields are present on the Lead layout. 
	/*	sfdc.lead.selectAndOpenAnyLead();
		sfdc.campaignPage.leadLayout();
		sfdc.campaignPage.verifyCampaignFields();
		
		sfdc.home.closeTabIfOpen();
		//Campaign Related List and Fields are present on the Contact layout. 
		sfdc.cc.selectAndOpenAnyContacts();
		sfdc.campaignPage.contactLayout();
		sfdc.campaignPage.verifyCampaignFields();
		
		sfdc.home.closeTabIfOpen(); */
		//Campaign Related List and Fields are present on the Account layout. 
		sfdc.acc.selectAccountTab();
		//sfdc.acc.openAnyActiveBusinessAccountFromList();
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.parentAccountName);
		sfdc.campaignPage.accountLayout();
		sfdc.campaignPage.verifyCampaignFields();
		sfdc.home.closeTabIfOpen();		
		sfdc.home.logout();
		
		sfdc.login.loginToSFDC(InputData_Sales.Profile_SalesExecutive);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		//Campaign Related List and Fields are present on the Lead layout. 
	/*	sfdc.lead.selectAndOpenAnyLead();
		sfdc.campaignPage.leadLayout();
		sfdc.campaignPage.verifyCampaignFields();
		
		sfdc.home.closeTabIfOpen();
		//Campaign Related List and Fields are present on the Contact layout. 
		sfdc.cc.selectAndOpenAnyContacts();
		sfdc.campaignPage.contactLayout();
		sfdc.campaignPage.verifyCampaignFields();
		
		sfdc.home.closeTabIfOpen(); */
		//Campaign Related List and Fields are present on the Account layout. 
		sfdc.acc.selectAccountTab();
		//sfdc.acc.openAnyActiveBusinessAccountFromList();
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.parentAccountName);
		sfdc.campaignPage.accountLayout();
		sfdc.campaignPage.verifyCampaignFields();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		sfdc.login.loginToSFDC(InputData.Profile_SalesManager);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		//Campaign Related List and Fields are present on the Account layout. 
		sfdc.acc.selectAccountTab();
	//	sfdc.acc.openAnyActiveBusinessAccountFromList();//sfdc.acc.selectAndOpenAnyBusAcc();
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.parentAccountName);
		sfdc.campaignPage.accountLayout();
		sfdc.campaignPage.verifyCampaignFields();
		sfdc.home.closeTabIfOpen();
		//Campaign Related List and Fields are present on the Lead layout. 
	/*	sfdc.lead.selectAndOpenAnyLead();
		sfdc.campaignPage.leadLayout();
		sfdc.campaignPage.verifyCampaignFields();
		
		sfdc.home.closeTabIfOpen();
		//Campaign Related List and Fields are present on the Contact layout. 
		sfdc.cc.selectAndOpenAnyContacts();
		sfdc.campaignPage.contactLayout();
		sfdc.campaignPage.verifyCampaignFields();
		*/
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout(); 
	
		sfdc.login.loginToSFDC(InputData.Profile_BusinessAdmin);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.selectAccountTab();
		//	sfdc.acc.openAnyActiveBusinessAccountFromList();//sfdc.acc.selectAndOpenAnyBusAcc();
			sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.parentAccountName);
			sfdc.campaignPage.accountLayout();
			sfdc.campaignPage.verifyCampaignFields();
			sfdc.home.closeTabIfOpen();
		//Campaign Related List and Fields are present on the Lead layout. 
	/*	sfdc.lead.selectAndOpenAnyLead();
		sfdc.campaignPage.leadLayout();
		sfdc.campaignPage.verifyCampaignFields();
		
		sfdc.home.closeTabIfOpen();
		//Campaign Related List and Fields are present on the Contact layout. 
		sfdc.cc.selectAndOpenAnyContacts();
		sfdc.campaignPage.contactLayout();
		sfdc.campaignPage.verifyCampaignFields();
		sfdc.home.closeTabIfOpen();*/
		sfdc.home.logout(); 
		
		sfdc.login.loginToSFDC(InputData_Sales.Profile_NIS);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.selectAccountTab();
		//	sfdc.acc.openAnyActiveBusinessAccountFromList();//sfdc.acc.selectAndOpenAnyBusAcc();
			sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.parentAccountName);
			sfdc.campaignPage.accountLayout();
			sfdc.campaignPage.verifyCampaignFields();
			sfdc.home.closeTabIfOpen();
	/*	sfdc.home.openGuidedSellingByRingDNA();
		sfdc.gsringDNA.selectLeadGS();
		sfdc.campaignPage.leadLayout();
		sfdc.campaignPage.verifyCampaignFields();
		
		sfdc.gsringDNA.selectContactGS();
		sfdc.campaignPage.contactLayout();
		sfdc.campaignPage.verifyCampaignFields(); */
			sfdc.home.logout(); 
		softassert.assertAll();
;
		
	}

}


