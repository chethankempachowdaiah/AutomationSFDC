package scrum.sales.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla, Date 30/Apr/2021
 * 
 *          MPOSS-33120_TC01_Validate that  Contract Support user when searching for any account in the gloabal search , results will be show 'Legal Name' column along with Account Name
 *		
 *			MPOSS-33120_TC02_Validate that  Contract Manager user when searching for any account in the gloabal search , results will be show 'Legal Name' column along with Account Name
 *
 *			MPOSS-33120_TC03_Validate that  AE user when searching for any account in the gloabal search , results will NOT show 'Legal Name' column along with Account Name

 * 
 */
public class Pl02_SP2_MPOSS_33120_LegalName__Verification_For_Accounts_SearchResult_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 *                                               
	 * @throws InterruptedException
	 * 
	 */
	@Test
	public void test_legalName_verification() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);

		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		
		//Serach for accounts
		sfdc.accDetails.searchAccRestrictedToAccountResults(InputData_Sales.accForSearch);
		
		//Validate legal name column is not present
		sfdc.acc.verifyLegalNameColumnNotExist();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//Login as Contract Manager
		sfdc.login.loginToSFDC(InputData.Profile_ContractSupport);
		sfdc.home.navigateURL();
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		
		//Serach for accounts
		sfdc.accDetails.searchAccRestrictedToAccountResults(InputData_Sales.accForSearch);
		
		//Validate legal name column is present
		sfdc.acc.verifyLegalNameColumnExist();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//Login as Contract Support
		sfdc.login.loginToSFDC(InputData.Profile_ContractManager);
		sfdc.home.navigateURL();
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		
		//Serach for accounts
		sfdc.accDetails.searchAccRestrictedToAccountResults(InputData_Sales.accForSearch);
		
		//Validate legal name column is present
		sfdc.acc.verifyLegalNameColumnExist();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		softassert.assertAll();

	}

}
