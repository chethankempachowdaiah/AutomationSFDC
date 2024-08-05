package scrum.sales.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla, Date 26/May/2021
 * 
 * 			MPOSS-38950_TC01_Validate that as AE, on creation business account - the fields Industry and Sub Industry  are mandate fields 
 * 		
 * 			MPOSS-38950_TC02_Validate that as AE, On Populated Industry/Sub-Industry fields on Assigned Accounts can be changed but proof needs to be submitted and approved by DG
 */
public class Pl02_SP3_MPOSS_38950_Validate_Industry_SubIndustry_Edit_Approvals_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 *         - Industry field is mandatory on Business Account creation
	 *         
	 *         - Sub Industry is optional on Business Account creation
	 *         
	 *         -Industry fields can be populated on currently assigned accounts
	 *         
	 *         - Sub-Industry fields can be populated on currently assigned accounts
	 *         
	 *         - Populated Industry/Sub-Industry fields on Assigned Accounts can be changed but proof needs to be submitted and approved by DG
	 *         
	 *         - When Sub-Industry fields are blank and the user populates the fields DG approval is not needed
	 * 
	 *                              
	 * @throws InterruptedException
	 * 
	 */
	@Test
	public void test_validateApprovalsIndSubIndEditBusinessAccount() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		
		//***********Validate Industry is mandatory and subindustry is optional************///
		sfdc.acc.launchCreateNewBusinessAccount();
		sfdc.cba.verifyIndustrySubIndustryFieldType();
				
		//Create new business account
		sfdc.cba.enterBusinessAccountInfo(false);
		sfdc.cba.verifyBusinessAccountCreated();
		
		// ***************Create Contact********//
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreated();
		sfdc.cc.clickOnNextInCreateContact();

		// ***************Create Service Account********//
		sfdc.csa.enterServiceAccountInfo(1);
		sfdc.csa.clickOnNextInCreateServiceAccount();
		sfdc.csa.noBillingAccountClickOnNext();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
	
		//*******Approve the change in DG*****//
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//*********Verify status of account after approval********///
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accDetails.verifyAccountStatusInDetails(sf.dataInput.status_Active);
		
		//******************Validate If we edit Industry and Sub industry it goes for approval to DG******//
		sfdc.accDetails.editIndustryForBusinessAccount();
		sfdc.accDetails.verifyAccountStatusInDetails(sf.dataInput.status_PendingApproval);
		sfdc.home.logout();
		
		//*******Approve the change in DG*****//
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.verifyApprovalRequestForIndSubIndChange();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//*********Verify status of account after approval********///
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accDetails.verifyAccountStatusInDetails(sf.dataInput.status_Active);
		
		//*******Validate Sub-Industy can be changed when blank without DG approval**********//
		sfdc.accDetails.editSubIndustryForBusinessAccount(1);
		sfdc.accDetails.verifyAccountStatusInDetails(sf.dataInput.status_Active);
		
		//*******Validate if we edit Sub industry it goes for approval to DG******//
		sfdc.accDetails.editSubIndustryForBusinessAccount(2);
		sfdc.accDetails.verifyAccountStatusInDetails(sf.dataInput.status_PendingApproval);
		sfdc.home.logout();
		
		//*******Approve the change in DG*****//
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.verifyApprovalRequestForIndSubIndChange();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//*********Verify status of account after approval********///
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accDetails.verifyAccountStatusInDetails(sf.dataInput.status_Active);
		
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();

	}

}
