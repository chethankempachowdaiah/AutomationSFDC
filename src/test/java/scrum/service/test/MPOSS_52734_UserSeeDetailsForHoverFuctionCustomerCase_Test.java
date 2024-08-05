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
 * @author Robin.Mangla, Date: 11/FEB/2022
 * 
 *         Validate user can see case details while hovering through various fields
 * 
 *   
 */
public class MPOSS_52734_UserSeeDetailsForHoverFuctionCustomerCase_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * 	 	                       Login as AE, Create a customer case from business account
	 *                             
	 *                             Validate contact, business account & billing account details by click over function
	 * 
	 * 
	 */
	@Test(groups= {"sanity"})
	public void test_UserSeeDetailsForHoverFuctionCustomerCase() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		
		//create business account to capture business account, contact and billing info
		sfdc.login.loginToSFDC(InputData.Profile_AE);
//		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
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

		//create internal guided case using AE profile
		sfdc.cases.selectCaseCategory(Constants.SERVICE_TYPE_SHEET);
		sfdc.cases.createCaseProactivelyForETMCase();
		sfdc.caseDetails.validateHoverFunctionDetails();		
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();	
		
		//create internal guided case using service profile
	    sfdc.login.loginToSFDC(InputData.Profile_Service);
	    sfdc.home.closeTabIfOpen();
		sfdc.cases.selectCaseCategory(Constants.SERVICE_TYPE_SHEET);
		sfdc.cases.createCaseProactivelyForETMCase();
		sfdc.caseDetails.validateHoverFunctionDetails();		
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
