package scrum.service.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Robin.mangla, Date 25/FEB/2022
 * 
 *         Validate Reactivation of an Inactive business contact
 *
 * 
 */
public class Regression_ServiceEnab_TC50_ValidateReactivationOfInactiveBusinessContactServiceProfile_Test extends Base 
{
	/**
	 * @throws Exception
	 * 
	 */
	@Test(groups= {"sanity"})
	
	public void test_ValidateReactivationOfInactiveBusinessContactServiceProfile() throws Exception 
	{
		SFDC_AllPages sfdc = new SFDC_AllPages();
////		sfdc.login.loginToSFDC(InputData.Profile_AE);
////		sfdc.home.openR4BSalesConsole();
////		sfdc.home.closeTabIfOpen();
////		sfdc.acc.createNewBusinessAccount();
////		sfdc.cba.enterBusinessAccountInfo(true);
////		sfdc.cba.verifyBusinessAccountCreated();
////		sfdc.cc.enterCreateContactInfo(false);
////		sfdc.cc.verifyMarkPrimaryContactReadOnly();
////		sfdc.cc.verifyContactCreated();
////		sfdc.cc.clickOnNextInCreateContact();
////		sfdc.csa.noBillingAccountClickOnNext();
////		sfdc.home.closeTabIfOpen();		
////		sfdc.home.logout();
//		
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccountRevised();
		sfdc.cba.enterBusinessAccountInfoRevised();
		sfdc.cba.verifyBusinessAccountCreatedRevised();
		sfdc.cc.enterNewContactInfoForBusinessAccount(true, false, true, false);
		sfdc.cc.validateBusinessContact();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		//deactivate and validate business contact using service profile
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
	//	sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.contacts.searchContactFromGlobalSearch(Global.dataInput.contactName);	
		sfdc.accDetails.deactivateActivateBusinessContact(true);		
		sfdc.home.closeTabIfOpen();		
		sfdc.home.logout();
		
		//approve business account
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);		
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
