package scrum.service.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.utilities.GetExcelData;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Robin.Mangla, Date: 22/MAR/2022
 * 
 *         User Creates a ETM case from existing customer case
 * 
 *   
 */
public class MPOSS_57916_TC02_ValidateTechnicalSupportCaseAutoResolveWhenETML5Truck_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * 	 	                       Validate that technical support case is auto resolved when
	 *                             
	 *                             ETM L5 is Truck and ETM ticket is closed
	 *                             
	 * 
	 * 
	 */
	@Test(groups= {"sanity"})
	public void test_ValidateTechnicalSupportCaseAutoResolveWhenETML5Truck() throws IOException, InterruptedException {

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
		sfdc.cases.selectCaseCategory(Constants.TECH_SUPPORT_SHEET);
		sfdc.cases.createCaseProactivelyForETMCase();
//		sfdc.caseDetails.validate_ProactivelyCreatedCaseDetails();
		sfdc.caseDetails.validate_ProactivelyCreatedTechnicalSupportCaseDetails();
		sfdc.caseDetails.createInternalGuidedCase();		
		sfdc.caseDetails.enterETMCaseDetailsL5Truck();
		sfdc.caseDetails.validateETMCaseDetailsL5Truck();		
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//change status of ETM ticket to closed
		sfdc.login.loginToSFDC(InputData.Profile_SystemAdmin);
		sfdc.home.closeTabIfOpen();
		sfdc.caseDetails.searchCaseFromGlobalSearch(InputData.caseNumber);
		sfdc.caseDetails.changeETMStatus();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		//validate parent case status changed to closed
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.caseDetails.searchCaseFromGlobalSearch(InputData.caseNumber);
		sfdc.caseDetails.validateETMStatusAndBellNotification(false);
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
				
		//approve business account
//	    sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
//	    sfdc.home.closeTabIfOpen();
//	    sfdc.accDetails.searchBusAccGlobalSearch_DataGovern(Global.dataInput.businessAccountName);
//	    sfdc.accRelated.approveAsDataGovernance();
//	    sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
		softassert.assertAll();
	}	
}
