package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
* @author Bhoomi Kishor, Date 11/Apr/2022
* 
*      Validate "new contact" button is added in case layout and user is able to create a contact from case layout.
*		 
*/


public class MPOSS_56305_Validate_New_Contact_Button_And_Create_Contact_From_CaseLayout extends Base  {
	
	@Test(groups= {"Regression"})
	
	public void test_ValidateNewContactButtonAndCreateContactFromCaseLayout() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		//Create New Case
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.cases.selectCaseCategory(Constants.TECH_SUPPORT_SHEET);
		sfdc.cases.createGuidedCasewithBusinessphone();
		sfdc.caseDetails.validate_GuidedCasewithBusinessphone();
		
		//Verify the New Contact button
		sfdc.caseDetails.validateNewContactButton();
		
		//Create the New Contact from Case Layout
		sfdc.cc.enterNewContactInfo(false, true, false, true, false);
		sfdc.contacts.searchContactFromGlobalSearch(Global.dataInput.contactName);
		sfdc.cc.validateNewContactforCase();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();

}

}
