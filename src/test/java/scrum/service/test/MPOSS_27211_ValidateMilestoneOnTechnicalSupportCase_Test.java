package scrum.service.test;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Service;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Robin.Mangla, Date: 08/MAR/2022
 * 
 *         Verify User proactively create a new case 
 *         change priority to high and validate milestone time
 *         change case status to resolved and validate milestone time
 * 
 *
 * 
 */
public class MPOSS_27211_ValidateMilestoneOnTechnicalSupportCase_Test extends Base  {
	/**
	 * @throws MalformedURLException 
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Login as Service , Select Cases, Select new Case
	 * 
	 *                              select case status, priority, type, sub-type,
	 *                              reason, origin, subject.
	 * 
	 *                              Enter description and internal comments
	 * 
	 *                              Click on Next Button
	 * 
	 *                              Verify Case details for Proactively Created case
	 *                              
	 *                              Change  Priority to Critical and validate Milestone
	 *                              
	 *                              Fill in mandatory fields, change Case Status to resolved and validate milestone
	 *                              
	 * 
	 * 
	 */
		
	@Test
	public void test_ValidateMilestoneOnTechnicalSupportCase() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		
		//create business, service and billing account
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
//		sfdc.accDetails.validateBillingAccount(true);
//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
		
		//create internal guided case
//		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(InputData_Service.businessAccountForInternalGuidedCase);
		sfdc.accRelated.validateEntitlementAssosciated();		
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.cases.selectCaseCategory(Constants.TECH_SUPPORT_SHEET);
		sfdc.cases.createCaseProactivelyForETMCase();
		sfdc.caseDetails.validate_ProactivelyCreatedTechnicalSupportCaseDetails();
//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
		
//		sfdc.login.loginToSFDC(InputData.Profile_AE);
//		sfdc.home.openR4BSalesConsole();
//		sfdc.home.closeTabIfOpen();
//		sfdc.acc.createNewBusinessAccountRevised();
//		sfdc.cba.enterBusinessAccountInfoRevised();
//		sfdc.cba.verifyBusinessAccountCreatedRevised();
//		sfdc.cc.enterNewContactInfoForBusinessAccount(true, false, true, false);
//		sfdc.cc.validateBusinessContact();
//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
        
		//approve business account
//		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);		
//		sfdc.home.closeTabIfOpen();
//		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
//		sfdc.accRelated.approveAsDataGovernance();
//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
		
		//validate Entitlements
//		sfdc.login.loginToSFDC(InputData.Profile_AE);
//		sfdc.home.closeTabIfOpen();
//		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
//		sfdc.accDetails.searchBusAccGlobalSearch(InputData_Service.businessAccountForInternalGuidedCase);
//		sfdc.accRelated.validateEntitlementAssosciated();		
//		sfdc.home.closeTabIfOpen();	
//		sfdc.home.logout();

		//create case and change priority
//		sfdc.login.loginToSFDC(InputData.Profile_Service);
//		sfdc.home.closeTabIfOpen();
//		sfdc.cases.selectCaseCategory(Constants.TECH_SUPPORT_SHEET);
//		sfdc.cases.createCaseProactively();
//		sfdc.caseDetails.validate_ProactivelyCreatedTechnicalSupportCaseDetails();
		sfdc.caseDetails.changeCasePriorityAndValidateMilestone();
		sfdc.caseDetails.changeCaseStatusAndValidateMilestone();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
