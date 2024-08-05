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
 * @author Robin.Mangla, Date: 04/NOV/2021
 * 
 *         User Creates a internal guided case from accounts
 *  
 */
public class MPOSS_50841_UserCreatesFraudCaseFromAccounts_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * 	                           Login as AE, Create a billing account from business account
	 *                             
	 *                             Login as Service, open the newly created account
	 *                             
	 *                             create internal case fill in all details like product family, category & sub category
	 *                             
	 *                             enter business account name and contact
	 *                             
	 *                             validate new created internal guided case
	 *                             
	 *                             Login as FraudOps and user can change case ownership and edit details
	 * 
	 * 
	 */
	@Test(groups= {"sanity"})
	public void test_UserCreatesFraudCaseFromAccounts() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		
		//create business account to capture business account, contact and billing info
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
//		sfdc.csa.checkCreateBillingAccount();
//		sfdc.cbia.enterBillingAccountInfo();
//		sfdc.cbia.verifyBillingAccountCreated();
//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
		
		//create internal guided case
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
//		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);		
		sfdc.accDetails.searchAccountFromGlobalSearch(InputData_Service.businessAccountForInternalGuidedCase, "Business");
		sfdc.caseDetails.createInternalGuidedCase();
		sfdc.caseDetails.enterCaseDetails();		
		sfdc.caseDetails.enterCaseRelatedDetails(true, false, true);		
		sfdc.accRelated.validateInternalGuidedCaseForAccounts();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//validate new case can be edited
		sfdc.login.loginToSFDC(InputData.Profile_FraudOps);
		sfdc.home.closeTabIfOpen();
		sfdc.caseDetails.searchCaseFromGlobalSearch(InputData.fraudCaseNumber);
		sfdc.caseDetails.changeCaseOwner(InputData.internalGuidedCaseUserName);
		sfdc.caseDetails.verifyUserIsAllowedToEditInternalGuidedCaseDetails();		
		sfdc.home.closeTabIfOpen();	
		sfdc.home.logout();
		
        //approve business account
//		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
//		sfdc.home.closeTabIfOpen();
//		sfdc.accDetails.searchBusAccGlobalSearch_DataGovern(Global.dataInput.businessAccountName);
//		sfdc.accRelated.approveAsDataGovernance();
//		sfdc.home.closeTabIfOpen();
		softassert.assertAll();
	}
}
