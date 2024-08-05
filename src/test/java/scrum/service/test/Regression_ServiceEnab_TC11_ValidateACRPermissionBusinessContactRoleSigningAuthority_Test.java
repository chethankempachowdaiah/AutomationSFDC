package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

import sfdc.pages.service.SFDC_Contacts_Page;

/**
 * @author Robin.Mangla, Date: 02/FEB/2022
 * 
 *         User create a contact from business account, choose role as Signing Authority
 *         
 *         validate contact is direct and active and  ACR Permission as General
 *  
 */

public class Regression_ServiceEnab_TC11_ValidateACRPermissionBusinessContactRoleSigningAuthority_Test extends Base 
{	
	@Test
	public void test_ValidateACRPermissionBusinessContactRoleSigningAuthority() throws IOException, InterruptedException 
	{
		SFDC_AllPages sfdc = new SFDC_AllPages();
		
		//******create business account and contact*********//
//		sfdc.login.loginToSFDC(InputData.Profile_AE);
//		sfdc.home.openR4BSalesConsole();
//		sfdc.home.closeTabIfOpen();		
//		sfdc.acc.createNewBusinessAccount();
//		sfdc.cba.enterBusinessAccountInfo(true);
//		sfdc.cba.verifyBusinessAccountCreated();		
//		sfdc.cc.enterCreateContactInfo(false, true);
//		sfdc.cc.verifyMarkPrimaryContactReadOnly();
//		sfdc.cc.verifyContactCreated();
//		sfdc.cc.clickOnNextInCreateContact();		
//		sfdc.csa.noBillingAccountClickOnNext();
				
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccountRevised();
		sfdc.cba.enterBusinessAccountInfoRevised();
		sfdc.cba.verifyBusinessAccountCreatedRevised();
		sfdc.cc.enterNewContactInfoForBusinessAccount(true, true, false, false);
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
        //log in to data governance to approve the role of signing authority and Approve the account
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.home.closeTabIfOpen();		
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();
        sfdc.contacts.searchContactFromGlobalSearch(Global.dataInput.contactName);
	    sfdc.conDetails.approveContactRoleSigningAuthority();
	    sfdc.home.closeTabIfOpen();
	    sfdc.home.logout();
	    
	    //log in to AE to validate ACR relationship
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.cc.validateContactForACRPermission(true);		
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
				
		//Approve the Business Account
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);		
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();		
	}
}
