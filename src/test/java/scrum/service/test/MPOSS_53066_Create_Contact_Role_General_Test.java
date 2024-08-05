package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
* @author Robin.Mangla, Date: 08/NOV/2021
* 
*         User Creates a contact when relationship type is none, RWOS role is mandatory
*  
*/

public class MPOSS_53066_Create_Contact_Role_General_Test extends Base {

/**
*  
*        Login as Service, create a new contact with access level as business, relationship type as none
*                             
*        validate that new contact has been created with relationship type none and chosen RWOS role
*          
*        
*/

	@Test
	public void test_Create_Contact_Role_General() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

     	sfdc.login.loginToSFDC(InputData.Profile_Service);	
		sfdc.home.closeTabIfOpen();
		sfdc.contacts.selectAndClickNewContact();
		sfdc.cc.enterNewContactInfo(false, true, false, false, true);
		sfdc.cc.validateNewContactWithRWOSRole();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}

}
