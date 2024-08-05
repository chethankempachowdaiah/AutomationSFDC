package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Service;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Robin.Mangla, Date: 30/SEP/2021
 * 
 *         User Creates a contact from account
 *  
 */

public class MPOSS_48829_Create_Contact_From_Accounts_Test extends Base {

/*
*                             Login as AE, open an existing business account
*                             
*                             create a new contact with access level as business, relationship type as Admin
*                             
*                             validate new contact role as admin	  
*/
	
	@Test
	public void test_Create_Contact_From_Accounts() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
//		sfdc.accDetails.searchBusAccGlobalSearch(InputData.account_ON);
		sfdc.accDetails.searchBusAccGlobalSearch(InputData_Service.businessAccountForInternalGuidedCase);
		sfdc.accRelated.clickNewContact();				
		sfdc.cc.enterNewContactInfo(true, true, false, true, false);// ..................CREATE NEW CONTACT		
		sfdc.accRelated.validateNewCreateContact();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();		
		softassert.assertAll();		
	}

}
