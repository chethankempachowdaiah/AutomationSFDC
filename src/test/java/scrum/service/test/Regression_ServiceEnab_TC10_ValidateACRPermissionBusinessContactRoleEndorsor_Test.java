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
 *         User create a contact from business account, choose role as Endorser, Assessor, Decider
 *         
 *         validate contact is direct and active and ACR Permission as General
 *  
 */

public class Regression_ServiceEnab_TC10_ValidateACRPermissionBusinessContactRoleEndorsor_Test extends Base 
{	
	@Test
	public void test_ValidateACRPermissionBusinessContactRoleEndorsor() throws IOException, InterruptedException 
	{
		SFDC_AllPages sfdc = new SFDC_AllPages();
		
		//******create business account and contact*********//
//		sfdc.login.loginToSFDC(InputData.Profile_AE);
//		sfdc.home.openR4BSalesConsole();
//		sfdc.home.closeTabIfOpen();		
//		sfdc.acc.createNewBusinessAccount();
//		sfdc.cba.enterBusinessAccountInfo(true);
//		sfdc.cba.verifyBusinessAccountCreated();
//		sfdc.cc.enterCreateContactInfo(false, false);
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
		sfdc.cc.enterNewContactInfoForBusinessAccount(true, false, false, true);		
		sfdc.cc.validateContactForACRPermission(false);				
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
