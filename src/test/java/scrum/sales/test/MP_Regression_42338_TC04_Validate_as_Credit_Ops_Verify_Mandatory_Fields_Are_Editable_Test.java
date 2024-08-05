package scrum.sales.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;
/**
 *         
 *         
 *         MPOSS-42338_TC04_Validate as Credit Ops - the fields Credit limit, Last credit review and Credit Risk field is editable for any assignment on account
 *         
 *    
 */

public class MP_Regression_42338_TC04_Validate_as_Credit_Ops_Verify_Mandatory_Fields_Are_Editable_Test extends Base {
	
	/**
	 * @throws IOException
	 * Pre Condition- Login as Credit Ops
	 *  1.Go to Accounts >> pick any account >> Overview tab
	 *	2. On the Credit & Fraud section >> Credit Ops profile is able to edit the following fields
	 *	Credit Limit
	 *	Last Credit Review
	 *	Credit Risk Value
	 *  
	 *  Expected- Credit Ops - the fields Credit limit, Last credit review and Credit Risk field is editable                                             
	 * @throws InterruptedException
	 * 
	 */
	@Test
	public void test_VerifyMandatoryFieldsAreEditableForCredOps() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		
		//Login as Contract Manager
		sfdc.login.loginToSFDC(InputData.Profile_CreditOps);
		//sfdc.home.navigateURL();
		sfdc.home.closeTabIfOpen();
		sfdc.home.openR4BSalesConsole();
		Global.dataInput.tempBusinessAccountName = "Cr_Check_rg";
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
		//Verify Mandatory Fields Are Editable For CredOps Profile
	    sfdc.accDetails.verifyMandatoryFieldsAreEditable();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		softassert.assertAll();

	}

}

