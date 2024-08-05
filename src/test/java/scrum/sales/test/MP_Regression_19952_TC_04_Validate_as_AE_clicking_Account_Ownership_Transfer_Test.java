package scrum.sales.test;

import com.framework.base.Base;
import com.framework.base.Global;

import org.testng.annotations.Test;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Nandan.More, Date 27/July/2021
 * 
 *         MPOSS_19952_TC_04_Validate as AE , clicking "Account Ownership Transfer"  button, if the 
 *         current account owner does NOT have manager assigned.
 *         
 *         MPOSS_19952_TC_09_Validate as AE , clicking "Account Ownership Transfer"  button, if the 
 *         current account owner & New owner have managers assigned but not the Role.
 *
 *		   MPOSS-54115 TC_01 AE user validate if case my "Manager" user is either inactive in Salesforce or is not 
 *		   an active employee, the user should get a message when the user tries to click on "account Ownership Transfer"
 *		
 *		   MPOSS-54115 TC_02 NIS user validate if case my "Manager" user is either inactive in Salesforce or is not
 *		   an active employee, the user should get a message when the user tries to click on "account Ownership Transfer"
 *		  
 *         MPOSS-54115 TC_03 Business Admin user validate if case my "Manager" user is either inactive in Salesforce or is 
 *         not an active employee, the user should get a message when the user tries to click on "account Ownership Transfer"
 *		  
 *		   MPOSS-54115 TC_04 Data Governance user validate if case my "Manager" user is either inactive in Salesforce or is not 
 *		   an active employee, the user should get a message when the user tries to click on "account Ownership Transfer"
 *
 * 
 */

public class MP_Regression_19952_TC_04_Validate_as_AE_clicking_Account_Ownership_Transfer_Test extends Base {
	
	/**
	 * @throws Exception
	 * 
	 *                  Logged in user , should not have manager assigned to him/her in SFDC.
	 *                  1. Login as AE to SalesQA.
	 *					2. "R4B sales console" should be selected by default. 
	 *					3. Accounts >> All Accounts >> Select any Business Account 
	 *					4. In the highlight panel , next to the "Log an Activity" button , user can see new button as " Account Ownership Transfer".
	 *					5. Message will be displayed "Please update the current Owner {!Account OwnerName} User record with a Manager."
	 *
	 *					Logged in user , should NOT have ROLE assigned in SFDC.
	 *					1. Login as AE to SalesQA.
	 *					2. "R4B sales console" should be selected by default. 
	 *					3. Accounts >> All Accounts >> Select any Business Account 
	 *					4. In the highlight panel , next to the "Log an Activity" button , user can see new button as " Account Ownership Transfer".
	 *					5. No message will be displayed , user will be navigated to next window.
	 *					6. Heading as "Account Ownership Transfer" , having below fields:-
	 *					- Name of new Account Owner - Jasdeep SM ( Look-up field)
	 *					- Base Revenue Quota Transfer- MANDATORY - Select as 50 (0 , 50 , 75 , 100 , 125 in %)
	 *					- Acquisition Margin TCV Quota Transfer- MANDATORY - Enter as 1111 ( Text/Number field). hit NEXT.
	 *					7. Please update the new Owner {!NewAccountOwner} User record with a Role.
	 */
	@Test
	public void test_ValidateAccountOwnershipTransferIfCurrentUserNotManager() throws Exception , InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		
		//sfdc.login.loginToSFDC(InputData.Profile_AE);
	/*	sfdc.login.loginToSFDC(InputData_Sales.Profile_NIS);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		Global.dataInput.tempBusinessAccountName = "Auto_QA_BA_ 210610012889";
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
		sfdc.accDetails.accountOwnershipTransfer();
		sfdc.accDetails.validateAccountOwnershipTransferManagerAccess();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		Global.dataInput.tempBusinessAccountName = "Auto_QA_BA_ 210610012889";
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
		sfdc.accDetails.accountOwnershipTransfer();
		sfdc.accDetails.validateAccountOwnershipTransferManagerAccess();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();*/
		
		/*sfdc.login.loginToSFDC(InputData_Sales.Profile_DataGovernance);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		Global.dataInput.tempBusinessAccountName = "Auto_QA_BA_ 210610012889";
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
		sf.seleU.hardwait(6000);
		//sfdc.accDetails.accountOwnershipTransfer();
		sfdc.accDetails.validateAccountOwnershipTransferManagerAccess();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();*/
		
	/*	sfdc.login.loginToSFDC(InputData.Profile_ThirdParty);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		Global.dataInput.tempBusinessAccountName = "Auto_QA_BA_ 210610012889";
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
		sfdc.accDetails.accountOwnershipTransfer();
		sfdc.accDetails.validateAccountOwnershipTransferManagerAccess();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout(); 
		
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		Global.dataInput.tempBusinessAccountName = "Auto_QA_BA_ 210610012889";
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
		sfdc.accDetails.accountOwnershipTransfer();
		sfdc.accDetails.validateAccountOwnershipTransferManagerAccess();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		Global.dataInput.tempBusinessAccountName = "testDevstage";
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
		sfdc.accDetails.accountOwnershipTransfer();
		sfdc.accDetails.validateAccountOwnershipTransfer();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout(); */
		
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccountLWC();
		sfdc.cba.enterBusinessAccountInfoLWC(); 
		sfdc.cba.verifyBusinessAccountCreatedLWC();
		sfdc.cc.enterNewContactInfoLWC(true);
		sfdc.home.logout();

		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);		
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accDetails.accountOwnershipTransfer();
		sfdc.accDetails.validateAccountOwnershipTransferManagerAccess();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);		
		sfdc.home.closeTabIfOpen();  
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		sfdc.login.loginToSFDC(InputData_Sales.Profile_NIS);		
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsManager();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		sfdc.login.loginToSFDC(InputData.Profile_SalesManager);		
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsManager();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}

}


