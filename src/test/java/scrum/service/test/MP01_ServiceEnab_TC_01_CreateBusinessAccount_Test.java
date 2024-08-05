package scrum.service.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date 08/Jan/2020
 * 
 *         Validate Account Executive is able to create a bunsiness Account
 *
 * 
 */
public class MP01_ServiceEnab_TC_01_CreateBusinessAccount_Test extends Base 
{

	/**
	 * @throws Exception
	 * 
	 */
	@Test(groups= {"sanity"})
	
	public void test_createbusinessAccount() throws Exception 
	{
		SFDC_AllPages sfdc = new SFDC_AllPages();
		
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		if (sf.dataInput.env.equals("ITDEVSTAGE")|| sf.dataInput.env.equals("ITQATEST"))
		{
		//New LWC Script /New business account creation flow (MP27) 
		sfdc.acc.createNewBusinessAccountLWC();
		sfdc.cba.enterBusinessAccountInfoLWC(); 
		sfdc.cba.verifyBusinessAccountCreatedLWC();
		sfdc.cc.enterNewContactInfoLWC(true);

		sfdc.home.selectHome();



		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accDetails.newServiceAccountButton();
		sfdc.csa.enterServiceAccInfoLWC();
		sfdc.csa.selectAddress();
		sfdc.home.logout();

		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);		
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		}
		else
		{
			//Old  Script /Old business account creation flow 
			sfdc.acc.createNewBusinessAccount();
			sfdc.cba.enterBusinessAccountInfo(true);
			sfdc.cba.verifyBusinessAccountCreated();
			sfdc.cc.enterCreateContactInfo(false);
			sfdc.cc.verifyMarkPrimaryContactReadOnly();
			sfdc.cc.verifyContactCreated();
			sfdc.cc.clickOnNextInCreateContact();
			sfdc.csa.enterServiceAccountInfo(1);
			sfdc.csa.clickOnNextInCreateServiceAccount();

			sfdc.csa.checkCreateBillingAccount();
			sfdc.cbia.enterBillingAccountInfo();
			sfdc.cbia.verifyBillingAccountCreated();
			sfdc.accDetails.validateBillingAccount(true);
			
//			sfdc.accDetails.validateBusinessAccount();
//			sfdc.home.closeTabIfOpen();
//			sfdc.accDetails.validateServiceAccount(true);
//			sfdc.premises.validatePremises();
//			sfdc.home.closeTabIfOpen();
//			sfdc.accDetails.validateBillingAccount(true);
//			sfdc.home.closeTabIfOpen();
			
			sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);		
			sfdc.home.closeTabIfOpen();
			sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
//			sfdc.accDetails.searchBusAccGlobalSearch_DataGovern(Global.dataInput.businessAccountName);
			sfdc.accRelated.approveAsDataGovernance();
			sfdc.home.closeTabIfOpen();
			sfdc.home.logout();
			
		}
		softassert.assertAll();
	}
}
