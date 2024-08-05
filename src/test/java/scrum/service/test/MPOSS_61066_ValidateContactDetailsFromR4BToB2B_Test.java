package scrum.service.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Robin.Mangla, Date 18/MAY/2022
 * 
 *         Validate contact created in R4B to B2B
 *
 * 
 */
public class MPOSS_61066_ValidateContactDetailsFromR4BToB2B_Test extends Base 
{

	/**
	 * @throws Exception
	 * 
	 */
	@Test(groups= {"sanity"})
	
	public void test_ValidateContactDetailsFromR4BToB2B() throws Exception 
	{
		SFDC_AllPages sfdc = new SFDC_AllPages();
		
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
				
		//approve business account
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);		
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//change contact details
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.contacts.searchContactFromGlobalSearch(Global.dataInput.contactName);
		Global.dataInput.secondContact_prepareContactData();
		sfdc.conDetails.updateContactPhoneNumber(Global.dataInput.contactPhoneNumber);
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//login to B2B to validate contact details
		sfdc.login.loginToSFDCForB2B();
		sfdc.accDetails.searchBusAccGlobalSearchInB2B(Global.dataInput.businessAccountName);
		sfdc.accDetails.validateAccountAndContactDetails();
		sfdc.home.logout();		
		softassert.assertAll();
	}
}
