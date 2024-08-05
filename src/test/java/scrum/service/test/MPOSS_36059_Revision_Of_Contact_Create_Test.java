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
 * @author Robin.Mangla, Date: 27/SEP/2021
 * 
 *         User Creates a contact 
 *  
 */

public class MPOSS_36059_Revision_Of_Contact_Create_Test extends Base {
	
/**
*         Login as Service, create a new contact with access level as business, relationship type as Signing Authority
*                             
*         Login as Data Governance and approve the relationship as signing authority
*          
*         Login as Service to validate new created signing authority
*/

	@Test
	public void test_Revision_Of_Contact_Create() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.contacts.selectAndClickNewContact();				
		sfdc.cc.enterNewContactInfo(false, true, true, false, false);		
		sfdc.cc.validateNewContact();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
//      log in to data governance to approve the role of signing authority
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.home.closeTabIfOpen();		
        sfdc.contacts.searchContactFromGlobalSearch(Global.dataInput.contactName);
	    sfdc.conDetails.approveContactRoleSigningAuthority();
	    sfdc.home.closeTabIfOpen();
	    sfdc.home.logout();
	    
//      log in to service to check whether status of contact changed to signing authority
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.contacts.searchContactFromGlobalSearch(Global.dataInput.contactName);		
		sfdc.conDetails.validateContactRoleSigningAuthority();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}

}
