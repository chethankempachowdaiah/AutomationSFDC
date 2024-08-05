package scrum.service.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.utilities.GetExcelData;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Robin.Mangla, Date: 17/JAN/2022
 * 
 *         User Creates a ETM case from existing customer case
 * 
 *   
 */
public class MPOSS_43655_UserCreatesETMCaseFromCustomerCase_DataDrivenTest extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * 	 	                       Login as AE, Create a customer case from business account
	 *                             
	 *                             Login as Service, open the newly created case
	 *                             
	 *                             create ETM Case fill in all details like L1 to L5
	 *                             
	 *                             Answer the questions(not mandatory)
	 *                             
	 *                             validate new created ETM Case
	 *                             
	 * 
	 * 
	 */
	@Test(dataProvider="getETMCaseTestData", groups= {"sanity"})
	public void test_UserCreatesETMCaseFromCustomerCase_DataDriven(String ETML1, String ETML2, String ETML3, String ETML4, String communicationMethod) throws IOException, InterruptedException {

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

//		sfdc.csa.enterServiceAccountInfo(1);
//		sfdc.csa.clickOnNextInCreateServiceAccount();

//		sfdc.csa.checkCreateBillingAccount();
//		sfdc.cbia.enterBillingAccountInfo();
//		sfdc.cbia.verifyBillingAccountCreated();
//		sfdc.home.closeTabIfOpen();
////		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
////		sfdc.home.closeTabIfOpen();
////		sfdc.accDetails.searchBusAccGlobalSearch_DataGovern(Global.dataInput.businessAccountName);
////		sfdc.accRelated.approveAsDataGovernance();
////		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();

		//create internal guided case
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.cases.selectCaseCategory(Constants.SERVICE_TYPE_SHEET);
		sfdc.cases.createCaseProactivelyForETMCase();
		sfdc.caseDetails.validate_ProactivelyCreatedCaseDetails();	
		sfdc.caseDetails.createInternalGuidedCase();
		sfdc.caseDetails.enterETMCaseDetails(ETML1, ETML2, ETML3, ETML4, communicationMethod);
		sfdc.caseDetails.validateETMCaseDetails(ETML1, ETML2, ETML3, ETML4, communicationMethod);				
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();		
		softassert.assertAll();
	}
	
	@DataProvider	
	public Iterator<Object[]> getETMCaseTestData() throws IOException
	{
		ArrayList<Object[]> ETMCaseTestData = GetExcelData.getDataFromETMCaseExcel();
		return ETMCaseTestData.iterator();
	}
}
