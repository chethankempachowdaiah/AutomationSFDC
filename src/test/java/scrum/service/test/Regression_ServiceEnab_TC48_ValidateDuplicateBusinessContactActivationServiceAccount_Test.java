package scrum.service.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Robin.Mangla, Date 24/FEB/2022
 * 
 *         Validate Duplicate Business Contact reactivation
 *
 * 
 */
public class Regression_ServiceEnab_TC48_ValidateDuplicateBusinessContactActivationServiceAccount_Test extends Base {

	/**
	 * @throws Exception
	 * 
	 */
	@Test(groups= {"sanity"})
	
	public void test_ValidateDuplicateInactiveBusinessContactActivationServiceAccount() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		//create business contact
//		sfdc.login.loginToSFDC(InputData.Profile_AE);
//		sfdc.home.openR4BSalesConsole();
//		sfdc.home.closeTabIfOpen();
//		sfdc.acc.createNewBusinessAccount();
//		sfdc.cba.enterBusinessAccountInfo(true);
//		sfdc.cba.verifyBusinessAccountCreated();
//		sfdc.cc.enterCreateContactInfo(false);
//		sfdc.cc.verifyMarkPrimaryContactReadOnly();
//		sfdc.cc.verifyContactCreated();
//		sfdc.cc.clickOnNextInCreateContact();
//		sfdc.csa.noBillingAccountClickOnNext();
//		sfdc.accDetails.deactivateActivateBusinessContact(false);		
//		sfdc.home.closeTabIfOpen();
		
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccountRevised();
		sfdc.cba.enterBusinessAccountInfoRevised();
		sfdc.cba.verifyBusinessAccountCreatedRevised();
		sfdc.cc.enterNewContactInfoForBusinessAccount(true, false, true, false);
		sfdc.cc.validateBusinessContact();
		sfdc.accDetails.deactivateActivateBusinessContact(false);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);	
		sfdc.acc.createNewServiceAccountRevised();
		sfdc.csa.enterServiceAccountInfoRevised();
		sfdc.csa.validateServiceAccountCreatedRevised();
		sfdc.premises.validatePremisesRevised();
		sfdc.home.closeTabIfOpen();
				
		//create duplicate contact
		
		sfdc.contacts.selectAndClickNewContact();
		Global.dataInput.secondContact_prepareContactData();	
		sfdc.cc.enterNewContactInfo(false, false);
		sfdc.conDetails.validateDuplicateBusinessContactActivation(false);
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
