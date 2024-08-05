package scrum.sales.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla, Date 28/May/2021
 * 
 * 			MPOSS-38685_TC01_As  Credit Ops  Validate that Account has NEW Business Account layout.
 * 
 * 			MPOSS-38685_TC01_As  Fraud Ops  Validate that Account has NEW Business Account layout.
 * 
 * 			MPOSS-38685_TC01_As  ThirdParty  Validate that Account has NEW Business Account layout.
 */
public class Pl02_SP3_MPOSS_38685_Validate_Cx_360_Layout_Business_Acc_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 *           1. Login as Credit Ops/Fraud Ops/Third Party to OneQA box
	 *         
	 *           2. Validate that by default the app is selected as "R4B Sales Console".
	 *           
	 *           3. Go to Accounts >> Select any Active Business Account.
	 *         
	 *           4. Validate that Account has NEW Business Account layout.
	 *                              
	 * @throws InterruptedException
	 * 
	 */
	@Test
	public void test_validateApprovalsIndSubIndEditBusinessAccount() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		//*********** Login As Credit Ops******************////
		sfdc.login.loginToSFDC(InputData.Profile_CreditOps);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		
		//***********Open any Active Business Account************///
		sfdc.acc.verifyAccountsObject();
		sfdc.acc.selectAndOpenAnyBusAcc();
				
		//***********Validate New business account layout*******///
		sfdc.accDetails.validateBusAccDetailsPageLayout();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//*********** Login As Fraud Ops******************////
		sfdc.login.loginToSFDC(InputData.Profile_FraudOps);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		
		//***********Open any Active Business Account************///
		sfdc.acc.verifyAccountsObject();
		sfdc.acc.selectAndOpenAnyBusAcc();
				
		//***********Validate New business account layout*******///
		sfdc.accDetails.validateBusAccDetailsPageLayout();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//*********** Login As Credit Ops******************////
		sfdc.login.loginToSFDC(InputData.Profile_ThirdParty);
		sfdc.home.closeTabIfOpen();
		
		//***********Open any Active Business Account************///
		sfdc.acc.verifyAccountsObject();
		sfdc.acc.selectAndOpenAnyBusAcc();
				
		//***********Validate New business account layout*******///
		sfdc.accDetails.validateBusAccDetailsPageLayout();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

	}

}
