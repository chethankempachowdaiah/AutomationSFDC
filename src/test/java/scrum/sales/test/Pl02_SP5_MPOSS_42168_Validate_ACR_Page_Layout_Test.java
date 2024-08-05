package scrum.sales.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla, Date 7/June/2021
 * 
 *         MPOSS-42168_TC01_Validate that in R4B Sales and service console, the Account-Contact Relationship displays the new added fields in the page layout
 *         
 *         MPOSS-42168_TC02_Validate that in R4B Sales and service console, the Account-Contact Relationship displays the new fields that are Read-Only at the page layout
 */
public class Pl02_SP5_MPOSS_42168_Validate_ACR_Page_Layout_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 *                                               
	 * @throws InterruptedException
	 * 
	 */
	@Test
	public void test_validate_acr_pagelyout() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);

		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		
		//Open any Business Account
		sfdc.acc.verifyAccountsObject();
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.parentAccountName);
		//sfdc.acc.openAnyActiveBusinessAccountFromList();
		
		//Validate acr info
		sfdc.accRelated.viewAllContactsFromContactsTab();
		sfdc.conDetails.verifyingAcrInBusinessAccountForDatesAndStatus();
		
		sfdc.home.logout();

		softassert.assertAll();

	}

}
