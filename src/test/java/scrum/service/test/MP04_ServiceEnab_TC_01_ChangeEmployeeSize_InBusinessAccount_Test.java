package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author priyanka.acharya, Date 28/April/2020
 *
 *         Change Employee size on the business account(name and business
 *         address validation in account evidence upload page validate task
 *         created, open task, validate old and new value)
 */
public class MP04_ServiceEnab_TC_01_ChangeEmployeeSize_InBusinessAccount_Test extends Base {

	/**
	 * *****************Design Steps******************
	 * 
	 * 1. AE log in and open a business account
	 * 
	 * 2. Validate Employee size field
	 * 
	 * 3. Click on the Change Employee size button
	 * 
	 * 4. Change Employee size (numeric and greater than 0)
	 * 
	 * 5. Validate Employee size is changed. And a task is created.
	 * 
	 * 6. Open the task, and validate change details
	 * 
	 * ******************Expected Results*****************
	 * 
	 * 1. Business account page is displayed
	 * 
	 * 2. Employee size field is not editable
	 * 
	 * 3. Change Employee size omniscript is invoked. Current employee size is
	 * present. There is option to change the employee size
	 * 
	 * 4. Employee size is changed
	 * 
	 * 5. Employee size is changed on the details page. In account history, the
	 * change detail is present. There is task created for Data Governce
	 * 
	 * 6. Change details is present in the task
	 * 
	 * *
	 * 
	 * @throws IOException ******************************************************
	 */
	@Test
	public void test_verifyEmployeeSizeUpdateInBusinessAccount() throws IOException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ***************LOGIN AS AE***********************//
//		sfdc.login.loginToSFDC(InputData.Profile_AE);
//		sfdc.home.openR4BSalesConsole();
//		sfdc.home.closeTabIfOpen();

		// ***************Create business Account********//
//		sfdc.acc.createNewBusinessAccount();
//		sfdc.cba.enterBusinessAccountInfo(true);
//		sfdc.cba.verifyBusinessAccountCreated();

		// ***************Create Contact********//
//		sfdc.cc.enterCreateContactInfo(false);
//		sfdc.cc.verifyMarkPrimaryContactReadOnly();
//		sfdc.cc.verifyContactCreated();
//		sfdc.cc.clickOnNextInCreateContact();

		// ***************Create Service Account********//
//		sfdc.csa.enterServiceAccountInfo(1);
//		sfdc.csa.clickOnNextInCreateServiceAccount();
//		sfdc.csa.noBillingAccountClickOnNext();

//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
		
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccountRevised();
		sfdc.cba.enterBusinessAccountInfoRevised();
		sfdc.cba.verifyBusinessAccountCreatedRevised();
		sfdc.cc.enterNewContactInfoForBusinessAccount(true, false, true, false);
		sfdc.cc.validateBusinessContact();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// *********Login as Data Governance and Approve business Account******//
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// *************Change Employee Size and Verify*******//
		sfdc.acc.selectChangeEmployeeSize();
		sfdc.accDetails.enterInputForChangeInEmployeeSize(Global.dataInput.updatedEmployeeSize);
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accDetails.verifyCustomerStoryForEmployeeSizeChangeCreated();
		sfdc.revwChangeEmplSize.verifyEmployeeSizeChangeTaskFields();

		// **********Close Tabs and Log out********//
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
