package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Service;
import com.sfdc.lib_pages.SFDC_AllPages;

import sfdc.pages.service.SFDC_Contacts_Page;

/**
 * @author Robin.Mangla, Date: 03/FEB/2022
 * 
 *         User create a contact from business account, choose role as Admin
 *         
 *         click on manage relationship and assign role to billing, service account
 *         
 *         validate contact relationship with all three accounts and validate ACR depending upon the role assigned
 *  
 */

public class Regression_ServiceEnab_TC12_ValidateACRPermissionBusinessContactManageRelationshipRoleAdmin_Test extends Base 
{	
	@Test
	public void test_ValidateACRPermissionBusinessContactManageRelationshipRoleAdmin() throws Exception 
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
//		sfdc.csa.checkCreateBillingAccount();
//		sfdc.cbia.enterBillingAccountInfo();
//		sfdc.cbia.verifyBillingAccountCreated();
//		sfdc.home.closeTabIfOpen();
//		sfdc.contacts.searchContactFromGlobalSearch(Global.dataInput.contactName);
		
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccountRevised();
		sfdc.cba.enterBusinessAccountInfoRevised();
		sfdc.cba.verifyBusinessAccountCreatedRevised();
		sfdc.cc.enterNewContactInfoForBusinessAccount(true, false, true, false);
		sfdc.cc.validateBusinessContact();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);	
		sfdc.acc.createNewServiceAccountRevised();
		sfdc.csa.enterServiceAccountInfoRevised();
		sfdc.csa.validateServiceAccountCreatedRevised();
		sfdc.premises.validatePremisesRevised();
		sfdc.home.closeTabIfOpen();
		sfdc.contacts.searchContactFromGlobalSearch(Global.dataInput.contactName);
		sfdc.conDetails.createRelationshipContactBillingAndServiceAccount();
		sfdc.accRelated.validateRelationshipContactBillingServiceAccountForACRPermission(false);
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
