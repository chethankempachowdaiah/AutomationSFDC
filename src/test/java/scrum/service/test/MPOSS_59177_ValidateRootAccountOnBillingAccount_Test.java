package scrum.service.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Robin.Mangla, Date 03/MAR/2022
 * 
 *         Validate Business Account is shown under Root Account Section for Billing account
 *
 * 
 */
public class MPOSS_59177_ValidateRootAccountOnBillingAccount_Test extends Base 
{
	/**
	 * @throws Exception
	 * 
	 */
	@Test(groups= {"sanity"})
	
	public void test_ValidateRootAccountOnBillingAccount() throws Exception 
	{
		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccount();
		sfdc.cba.enterBusinessAccountInfo(true);
		sfdc.cba.verifyBusinessAccountCreated();
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreated();
		sfdc.cc.clickOnNextInCreateContact();
		sfdc.csa.checkCreateBillingAccount();
		sfdc.cbia.enterBillingAccountInfo();
		sfdc.cbia.verifyBillingAccountCreated();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.validateBillingAccount(true);
		sfdc.home.closeTabIfOpen();
		
		//create second billing account
		sfdc.acc.createNewBillingAccount();
		Global.dataInput.prepareSecondBillingAccountData();
		sfdc.cbia.enterNewBillingAccountInfo();
		sfdc.cbia.verifyBillingAccountCreated();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.validateBillingAccount(true);
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//create child billing account
		sfdc.login.loginToSFDC(InputData.Profile_SystemAdmin);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchAccountFromGlobalSearch(Global.dataInput.billingAccountName, Global.dataInput.acc_RecordType_Billing);
		sfdc.accDetails.changeBillingParentAccount();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//validate root account AE profile
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchAccountFromGlobalSearch(Global.dataInput.billingAccountName, Global.dataInput.acc_RecordType_Billing);		
		sfdc.accDetails.validateRootAccount();		
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//validate root account from service profile
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchAccountFromGlobalSearch(Global.dataInput.billingAccountName, Global.dataInput.acc_RecordType_Billing);
		sfdc.accDetails.validateRootAccount();		
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//approve business account
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);		
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		
		//validate root account
		sfdc.accDetails.searchAccountFromGlobalSearch(Global.dataInput.billingAccountName, Global.dataInput.acc_RecordType_Billing);
		sfdc.accDetails.validateRootAccount();		
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
