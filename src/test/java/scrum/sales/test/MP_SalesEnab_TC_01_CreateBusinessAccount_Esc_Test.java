package scrum.sales.test;

import org.testng.annotations.Test;
import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Nandan.More, Date 18/Apr/2022
 * 
 *         Validate ESC (Second Duplicate Check)database functionality while creating bunsiness Account
 *
 * 
 */
public class MP_SalesEnab_TC_01_CreateBusinessAccount_Esc_Test extends Base 
{

	/**
	 * @throws Exception
	 * 
	 */
	@Test(groups= {"sanity"})
	
	public void test_escLegalName() throws Exception 
	{
		SFDC_AllPages sfdc = new SFDC_AllPages();
		
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		sfdc.acc.createNewBusinessAccountLWC();
		sfdc.cba.enterBusinessAccountInfoESC(); 
		sfdc.cba.verifyBusinessAccountCreatedLWC();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
