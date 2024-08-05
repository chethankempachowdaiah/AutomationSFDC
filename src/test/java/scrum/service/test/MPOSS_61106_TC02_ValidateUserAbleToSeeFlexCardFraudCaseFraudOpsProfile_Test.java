package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Robin.Mangla, Date: 05/MAY/2022
 * 
 *         User able to flex card details on fraud case with FraudOps Profile
 * 
 *   
 */
public class MPOSS_61106_TC02_ValidateUserAbleToSeeFlexCardFraudCaseFraudOpsProfile_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * 
	 * 
	 */
	@Test(groups= {"sanity"})
	public void test_TC02_ValidateUserAbleToSeeFlexCardFraudCaseFraudOpsProfile() throws IOException, InterruptedException {

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
		sfdc.login.loginToSFDC(InputData.Profile_FraudOps);
		sfdc.home.closeTabIfOpen();
		sfdc.cases.selectCaseCategory(Constants.TECH_SUPPORT_SHEET);
		sfdc.cases.createCaseProactively();
		sfdc.caseDetails.validate_ProactivelyCreatedTechnicalSupportCaseDetailsFraudOpsProfile();
		sfdc.caseDetails.createInternalGuidedCase();
		sfdc.caseDetails.enterCaseDetailsFraudOpsProfile();
		sfdc.caseDetails.enterCaseRelatedDetails(false, false, false);
		sfdc.caseDetails.validateUserAbleToSeeFlexCardDetails();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
				
        //approve business account
//		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
//		sfdc.home.closeTabIfOpen();
//		sfdc.accDetails.searchBusAccGlobalSearch_DataGovern(Global.dataInput.businessAccountName);
//		sfdc.accRelated.approveAsDataGovernance();
//		sfdc.home.closeTabIfOpen();
		softassert.assertAll();
	}
}
