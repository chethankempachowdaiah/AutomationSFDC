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
 * 		   MPOSS-57227 Create Lead Conversion LWC Omniscript
 *         
 *         MPOSS-57227_TC01_Validate that, As a Sales User(AE, NIS AE, Dealer Champ, Sales Manager), Marketing User, I should be able to convert a Lead using the new Lead Conversion LWC.
 *         
 *         MPOSS-57227_TC02_Validate that as sales user, After the Lead Conversion I should land on the new business account creation page.
 *         
 *         MPOSS-59514 Update lead as per the standard updated Lead Feature after conversion
 *         
 *         MPOSS-59514_TC_01_Validate of Update lead as per the standard updated Lead Feature after conversion.
 *         
 *         MPOSS-59514_TC_02_Validate After Conversion Lead should not be visible to the users in the list view.
 *         
 *         MPOSS-59514_TC_03_Validate Converted Lead should be reportable. 
 *         
 *         Applicable Profiles Sales User(AE, NIS AE, Dealer Champ, Sales Manager), Marketing Use
 * 
 */
public class Pl22_SP5_MPOSS_57227_Validate_Lead_Conversion_LWC_to_Bus_Acc_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void test_validate_lead_conversion_LWC() throws IOException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);

		sfdc.home.openR4BSalesConsole();
		sfdc.lead.createLead();  
		sfdc.lead.covertButtonLead();
		sfdc.lead.covertLeadToBusAcc();
		sfdc.cba.verifyBusinessAccountCreatedLWC();
		sfdc.lead.covertLeadToBusAccContact();	
		sfdc.lead.ValidateDetailsOnBusAcc();
		sfdc.home.logout();
		
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);		
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(InputData_Sales.leadBusAccountName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
	
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.lead.selectLead();
		sfdc.lead.searchLead();
		sfdc.home.openReports();
		sfdc.home.validateReports();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout(); 
		softassert.assertAll();

	}

}
