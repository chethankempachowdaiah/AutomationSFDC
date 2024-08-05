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
 * @author Robin.Mangla, Date: 21/JAN/2022
 * 
 *         User update contact when duplicate contact found and add indirect relationship.
 *  
 */

public class Regression_ServiceEnab_TC07_UserModifyDuplicateContactFoundBusinessAccount_Test extends Base {
	
/**
*         Login as AE, create a business account and contact
*                             
*         Login as AE and create new contact and fill same email address of previous to find duplicate contact
*          
*         Update the contact details and role
*         
*         Validate new created contact and role
*/

	@Test
	public void test_UserModifyDuplicateContactFoundBusinessAccount() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		
		// create a business account and contact
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
//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
		
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccountRevised();
		sfdc.cba.enterBusinessAccountInfoRevised();
		sfdc.cba.verifyBusinessAccountCreatedRevised();
		sfdc.cc.enterNewContactInfoForBusinessAccount(true, false, true, false);
		sfdc.cc.validateBusinessContact();
		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
		
        //search for duplicate contact and update contact and role		
//		sfdc.login.loginToSFDC(InputData.Profile_AE);
//		sfdc.home.closeTabIfOpen();
		sfdc.contacts.selectAndClickNewContact();
		Global.dataInput.secondContact_prepareContactData();
		sfdc.cc.enterNewContactInfo(true, true);		
		sfdc.cc.validateNewContactForDuplicateContact();
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
