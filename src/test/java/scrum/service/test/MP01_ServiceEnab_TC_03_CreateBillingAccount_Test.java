package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date 15/Jan/2020
 * 
 *         Validate Account Executive is able to create a Billing Account
 *
 * 
 */
public class MP01_ServiceEnab_TC_03_CreateBillingAccount_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              1. Sales log in and open a business account
	 * 
	 *                              2. Create account and select Billing
	 * 
	 *                              3. Enter billing account information (Parent
	 *                              account, Account Number and Account Source) .
	 * 
	 *                              4. Validate billing account is created
	 * 
	 */
	@Test(groups= {"regression"})
	
	public void test_createBillingAccount() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

//		sfdc.login.loginToSFDC(InputData_Sales.Profile_NIS);//InputData.userid_ae
		
		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBillingAccount();
		sfdc.cbia.enterBillingAccountInfo();
		sfdc.cbia.verifyBillingAccountCreated();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.validateBillingAccount(false);
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();

	}
}
